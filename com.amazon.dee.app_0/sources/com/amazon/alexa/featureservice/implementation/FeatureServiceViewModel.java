package com.amazon.alexa.featureservice.implementation;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.amazon.alexa.featureservice.constants.FeatureServiceMetrics;
import com.amazon.alexa.featureservice.recordTrigger.RecordTriggerService;
import com.amazon.alexa.featureservice.recordTrigger.RequestTreatment;
import com.amazon.alexa.featureservice.repo.model.Feature;
import com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo;
import com.amazon.alexa.featureservice.service.FeatureSubscriptionManager;
import com.amazon.alexa.featureservice.util.Analytics;
import com.amazon.alexa.featureservice.util.SafeEventBus;
import com.amazon.alexa.featureservice.util.TimeUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import dagger.internal.Preconditions;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class FeatureServiceViewModel {
    private static final String TAG = "FeatureServiceViewModel";
    private Analytics analytics;
    private FeatureCache featureCache;
    private FeatureDataRepo featureDataRepo;
    private FeatureRegistryUtil featureRegistryUtil;
    private FeatureServiceConfiguration featureServiceConfiguration;
    private FeatureSubscriptionManager featureSubscriptionManager;
    private Gson gson;
    private List<String> instantlyPropogatedFeatureList;
    private RecordTriggerService recordTriggerService;
    private SafeEventBus safeEventBus;
    private TimeUtil timeUtil;
    private long timeLastRefreshedMillis = -1;
    private boolean userHasLoggedOut = false;
    private boolean isUserAuthenticated = false;

    @Inject
    public FeatureServiceViewModel(FeatureDataRepo featureDataRepo, FeatureRegistryUtil featureRegistryUtil, RecordTriggerService recordTriggerService, FeatureCache featureCache, FeatureSubscriptionManager featureSubscriptionManager, Analytics analytics, SafeEventBus safeEventBus, Gson gson, TimeUtil timeUtil, FeatureServiceConfiguration featureServiceConfiguration) {
        this.featureDataRepo = featureDataRepo;
        this.featureRegistryUtil = featureRegistryUtil;
        this.recordTriggerService = recordTriggerService;
        this.featureCache = featureCache;
        this.featureSubscriptionManager = featureSubscriptionManager;
        this.analytics = analytics;
        this.safeEventBus = safeEventBus;
        this.gson = gson;
        this.timeUtil = timeUtil;
        this.featureServiceConfiguration = featureServiceConfiguration;
        init();
    }

    private Feature getFeatureFromLocal(final String str) {
        Preconditions.checkNotNull(str);
        if (this.featureCache.getFeatureOverride(str) != null) {
            return this.featureCache.getFeatureOverride(str);
        }
        if (this.featureCache.getFeature(str) == null) {
            Feature blockingGet = this.featureDataRepo.singleFeatureUpdates(str).subscribeOn(Schedulers.computation()).doOnError(new Consumer() { // from class: com.amazon.alexa.featureservice.implementation.-$$Lambda$FeatureServiceViewModel$GNuuWDql9XfEItsYTrTwmmD6lgM
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    Throwable th = (Throwable) obj;
                    GeneratedOutlineSupport1.outline158("getFeatureFromLocal error for ", str);
                }
            }).onErrorReturnItem(new Feature()).blockingGet();
            if (blockingGet == null || blockingGet.getFeatureName() == null) {
                return null;
            }
            this.featureCache.cacheFeature(blockingGet);
            return blockingGet;
        }
        return this.featureCache.getFeature(str);
    }

    private List<String> getListOfFeaturesStoredInDb() {
        ArrayList arrayList = new ArrayList();
        for (Feature feature : this.featureDataRepo.allFeatureUpdates().first(new ArrayList()).subscribeOn(Schedulers.computation()).blockingGet()) {
            arrayList.add(feature.getFeatureName());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Retrieved feature list from db: ");
        outline107.append(this.gson.toJson(arrayList));
        outline107.toString();
        return arrayList;
    }

    private void init() {
        this.instantlyPropogatedFeatureList = this.featureRegistryUtil.getInstantlyPropagatedFeatureList();
    }

    private boolean isTruthy(String str) {
        return !str.equals("C") && !str.equals("C_DEFAULT");
    }

    private void recordTriggerIfRequired(Feature feature) {
        if (feature == null || !feature.isTriggerOnUse()) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Recording trigger for feature: ");
        outline107.append(feature.getFeatureName());
        outline107.toString();
        this.recordTriggerService.record(new RequestTreatment(feature.getFeatureName(), feature.getTreatment(), feature.getAllocationId()));
    }

    private Single<List<Feature>> refreshFeatures(List<String> list) {
        return this.featureDataRepo.refreshFeatures(list).subscribeOn(Schedulers.computation()).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.featureservice.implementation.-$$Lambda$FeatureServiceViewModel$6xqGQt6B0Zg9EvkX1S0IrsnFm4Q
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FeatureServiceViewModel.this.lambda$refreshFeatures$0$FeatureServiceViewModel((List) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.featureservice.implementation.-$$Lambda$FeatureServiceViewModel$M6UGBPrKeRHggOfb9_WqZ2sfX2I
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FeatureServiceViewModel.this.lambda$refreshFeatures$1$FeatureServiceViewModel((Throwable) obj);
            }
        });
    }

    private void refreshFeaturesIfStale() {
        if (shouldDownloadFeaturesFromRemote()) {
            refreshFeaturesFromRemote().subscribe(refreshFeaturesSubscriber(FeatureConstants.Identifiers.REFRESH_FEATURES_IF_STALE));
        }
    }

    @NonNull
    private DisposableSingleObserver<List<Feature>> refreshFeaturesSubscriber(final String str) {
        return new DisposableSingleObserver<List<Feature>>() { // from class: com.amazon.alexa.featureservice.implementation.FeatureServiceViewModel.2
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(Throwable th) {
                FeatureServiceViewModel.this.analytics.recordCriticalMetric(FeatureServiceMetrics.EventType.REFRESH_PAYLOAD_ERROR, FeatureServiceViewModel.class.getSimpleName(), str, th);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(List<Feature> list) {
                if (list == null || list.size() <= 0) {
                    return;
                }
                String unused = FeatureServiceViewModel.TAG;
                FeatureServiceViewModel.this.safeEventBus.publish("featureServiceV2:internal:featuresUpdated", FeatureServiceViewModel.this.getSerializedFeatureCache());
            }
        };
    }

    private void saveFeatureToDb(final Feature feature) {
        this.featureDataRepo.insertFeature(feature).subscribeOn(Schedulers.computation()).subscribe(new DisposableCompletableObserver() { // from class: com.amazon.alexa.featureservice.implementation.FeatureServiceViewModel.3
            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                String unused = FeatureServiceViewModel.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully saved feature to db : ");
                outline107.append(feature.toString());
                outline107.toString();
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                Log.w(FeatureServiceViewModel.TAG, "Error while saving feature to db", th);
            }
        });
    }

    private boolean shouldDownloadFeaturesFromRemote() {
        long featureRefreshTime = (!this.featureServiceConfiguration.isEnabled() || this.featureServiceConfiguration.getFeatureRefreshTime() <= 0) ? 3600000L : this.featureServiceConfiguration.getFeatureRefreshTime();
        GeneratedOutlineSupport1.outline153("Feature refresh time: ", featureRefreshTime);
        return this.timeLastRefreshedMillis != -1 && this.timeUtil.getCurrentTimeMillis() - this.timeLastRefreshedMillis > featureRefreshTime;
    }

    private void updateFeatureCacheAndNotifySubscribers(Feature feature) {
        Feature feature2 = this.featureCache.getFeature(feature.getFeatureName());
        this.featureCache.cacheFeature(feature);
        if (feature2 == null || !feature.equals(feature2)) {
            this.featureSubscriptionManager.notifySubscribers(feature.getFeatureName());
        }
    }

    public void clearAllFeatures() {
        this.featureDataRepo.deleteAllFeatures().subscribeOn(Schedulers.computation()).subscribe(new DisposableSingleObserver<Integer>() { // from class: com.amazon.alexa.featureservice.implementation.FeatureServiceViewModel.1
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(Throwable th) {
                FeatureServiceViewModel.this.analytics.recordCriticalMetric("room_thread_error", "execution_error", "RoomDatabase", th);
                Log.w(FeatureServiceViewModel.TAG, "Error while clearing features", th);
                FeatureServiceViewModel.this.featureCache.clear();
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(Integer num) {
                String unused = FeatureServiceViewModel.TAG;
                FeatureServiceViewModel.this.featureCache.clear();
            }
        });
    }

    public Map<String, String> getFeatureTreatmentMap() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Feature> entry : this.featureCache.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().getTreatment());
        }
        return hashMap;
    }

    public String getSerializedFeatureCache() {
        return this.gson.toJson(this.featureCache.getAllAsMap());
    }

    public String getTreatmentAndRecordTrigger(String str, String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        refreshFeaturesIfStale();
        Feature featureFromLocal = getFeatureFromLocal(str);
        String str3 = "(getTreatmentAndRecordTrigger) getFeatureFromLocal returned: " + featureFromLocal;
        if (featureFromLocal != null) {
            String treatment = featureFromLocal.getTreatment();
            recordTriggerIfRequired(featureFromLocal);
            return treatment;
        }
        Feature feature = new Feature();
        feature.setAllocationId("");
        feature.setTreatment(str2);
        feature.setFeatureName(str);
        updateFeatureCacheAndNotifySubscribers(feature);
        saveFeatureToDb(feature);
        return str2;
    }

    public boolean hasAccess(String str, boolean z) {
        Preconditions.checkNotNull(str);
        refreshFeaturesIfStale();
        Feature featureFromLocal = getFeatureFromLocal(str);
        String str2 = "(hasAccess) getFeatureFromLocal returned: " + featureFromLocal;
        if (featureFromLocal != null) {
            return isTruthy(featureFromLocal.getTreatment());
        }
        Feature feature = new Feature();
        feature.setFeatureName(str);
        String str3 = z ? FeatureConstants.T1_DEFAULT : "C_DEFAULT";
        feature.setAllocationId("");
        feature.setTreatment(str3);
        this.featureCache.cacheFeature(feature);
        return z;
    }

    public /* synthetic */ void lambda$refreshFeatures$0$FeatureServiceViewModel(List list) throws Throwable {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully downloaded Feature List: ");
        outline107.append(this.gson.toJson(list));
        outline107.toString();
        this.timeLastRefreshedMillis = this.timeUtil.getCurrentTimeMillis();
        this.analytics.recordCriticalMetric(FeatureServiceMetrics.EventType.REFRESH_SUCCESS, FeatureServiceMetrics.EventType.REFRESH_SUCCESS, FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, null);
        ArrayList arrayList = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Feature feature = (Feature) it2.next();
            if (this.featureCache.getFeature(feature.getFeatureName()) == null) {
                updateFeatureCacheAndNotifySubscribers(feature);
            }
            if (this.instantlyPropogatedFeatureList.contains(feature.getFeatureName())) {
                arrayList.add(feature.getFeatureName());
            }
        }
        if (arrayList.size() > 0) {
            String json = this.gson.toJson(arrayList);
            this.safeEventBus.publish(FeatureConstants.FEATURES_UPDATED_EVENT, json);
            String str = "Instantly propagating features: " + json;
        }
    }

    public /* synthetic */ void lambda$refreshFeatures$1$FeatureServiceViewModel(Throwable th) throws Throwable {
        Log.w(TAG, "Error refreshing features", th);
        this.analytics.recordCriticalMetric(FeatureServiceMetrics.EventType.REFRESH_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.EMPTY_RESPONSE, FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, th);
    }

    public /* synthetic */ void lambda$reloadFeaturesFromDb$2$FeatureServiceViewModel(List list) throws Throwable {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            updateFeatureCacheAndNotifySubscribers((Feature) it2.next());
        }
    }

    public /* synthetic */ void lambda$reloadFeaturesFromDb$3$FeatureServiceViewModel(Throwable th) throws Throwable {
        Log.w(TAG, "Error while reloading features from DB", th);
        this.analytics.recordCriticalMetric(FeatureServiceMetrics.EventType.NEW_SESSION, FeatureServiceViewModel.class.getSimpleName(), "reloadFeaturesFromDb", th);
    }

    public void overrideFeature(String str, String str2) {
        Feature feature = new Feature();
        feature.setFeatureName(str);
        feature.setAllocationId("");
        feature.setTreatment(str2);
        this.featureCache.overrideFeature(feature);
    }

    public void prefetchNewlyAddedFeatures(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (this.featureCache.getFeature(str) == null) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.featureRegistryUtil.setAdditionalFeatures(arrayList);
        if (!this.isUserAuthenticated) {
            return;
        }
        refreshFeatures(arrayList).subscribe(refreshFeaturesSubscriber(FeatureConstants.Identifiers.PREFETCH_NEWLY_ADDED_FEATURES));
    }

    public void processUserAction(int i) {
        if (i == 0) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Processing successful user signin. Relogin = ");
            outline107.append(this.userHasLoggedOut);
            outline107.toString();
            if (this.userHasLoggedOut) {
                this.analytics.recordCriticalMetric(FeatureServiceMetrics.EventType.NEW_SESSION, FeatureServiceMetrics.EventName.RE_LOGIN, FeatureServiceMetrics.Subcomponent.SESSION_MANAGEMENT, null);
            } else {
                this.analytics.recordCriticalMetric(FeatureServiceMetrics.EventType.NEW_SESSION, FeatureServiceMetrics.EventName.COLD_START, FeatureServiceMetrics.Subcomponent.SESSION_MANAGEMENT, null);
            }
            this.userHasLoggedOut = false;
            this.isUserAuthenticated = true;
        }
        if (1 == i) {
            this.userHasLoggedOut = true;
            this.isUserAuthenticated = false;
        }
    }

    public Single<List<Feature>> refreshFeaturesFromRemote() {
        HashSet hashSet = new HashSet(this.featureRegistryUtil.getRegisteredFeatures());
        hashSet.addAll(getListOfFeaturesStoredInDb());
        return refreshFeatures(new ArrayList(hashSet));
    }

    public Single<List<Feature>> reloadFeaturesFromDb() {
        return this.featureDataRepo.allFeatureUpdates().first(new ArrayList()).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.featureservice.implementation.-$$Lambda$FeatureServiceViewModel$p_fg_SSBh7gdWATIgFsAWoeNoIc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FeatureServiceViewModel.this.lambda$reloadFeaturesFromDb$2$FeatureServiceViewModel((List) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.featureservice.implementation.-$$Lambda$FeatureServiceViewModel$8L-hwzN8O-h6ATsiEDcsX0xLpds
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FeatureServiceViewModel.this.lambda$reloadFeaturesFromDb$3$FeatureServiceViewModel((Throwable) obj);
            }
        }).subscribeOn(Schedulers.computation());
    }

    public void subscribeToFeatureUpdates(String str, FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(featureUpdateListener);
        this.featureSubscriptionManager.addSubscriber(str, featureUpdateListener);
        if (getFeatureFromLocal(str) != null) {
            featureUpdateListener.onFeatureUpdate(str);
        }
    }

    public void unsubscribeListenerFromFeatureUpdates(FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        this.featureSubscriptionManager.removeSubscriber(featureUpdateListener);
    }
}
