package com.amazon.alexa.featureservice.service;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.amazon.alexa.featureservice.constants.FeatureServiceMetrics;
import com.amazon.alexa.featureservice.data.registry.FeatureListProvider;
import com.amazon.alexa.featureservice.database.entity.Feature;
import com.amazon.alexa.featureservice.recordTrigger.RecordTriggerService;
import com.amazon.alexa.featureservice.recordTrigger.RequestTreatment;
import com.amazon.alexa.featureservice.sessionManagement.SessionManager;
import com.amazon.alexa.featureservice.storage.FeatureServiceStorage;
import com.amazon.alexa.featureservice.util.ExceptionUtil;
import com.amazon.alexa.featureservice.util.FeatureSubscriptionMap;
import com.amazon.alexa.featureservice.util.LatencyUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.TimelineLatencyAction;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dagger.Lazy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import okhttp3.Response;
import org.joda.time.DateTimeUtils;
/* loaded from: classes7.dex */
public class DefaultFeatureServiceV2 implements PlatformFeatureServiceV2, SessionManager.Listener {
    private static final String TAG = "DefaultFeatureServiceV2";
    @VisibleForTesting
    MultiFilterSubscriber.FilterUuid appAuthenticatedFilterUuid;
    @VisibleForTesting
    MultiFilterSubscriber appAuthenticatedSubscriber;
    private final Lazy<CoralService> coralServiceLazy;
    private final String currentRequestURL;
    final Lazy<EventBus> eventBusLazy;
    @VisibleForTesting
    Map<String, Feature> featureCache;
    @VisibleForTesting
    Map<String, Feature> featureOverrideCache;
    @VisibleForTesting
    List<String> featureRegistryList;
    private FeatureSubscriptionManager featureSubscriptionManager;
    @VisibleForTesting
    long featuresLastRefreshed;
    @VisibleForTesting
    Set<String> instantlyPropagateFeaturesRegistrySet;
    @VisibleForTesting
    boolean isPrefetchComplete;
    private Lazy<Mobilytics> mobilyticsLazy;
    @VisibleForTesting
    FeatureServiceStorage persistentStore;
    private final Lazy<RecordTriggerService> recordTriggerService;
    private SessionManager sessionManager;
    @VisibleForTesting
    MultiFilterSubscriber.FilterUuid signOutFilterUuid;
    @VisibleForTesting
    MultiFilterSubscriber signOutSubscriber;
    private List<String> updatedFeaturesList;
    @VisibleForTesting
    boolean userLoggedOut;

    @VisibleForTesting
    DefaultFeatureServiceV2(Lazy<EventBus> lazy, FeatureServiceStorage featureServiceStorage, Lazy<CoralService> lazy2, Lazy<Mobilytics> lazy3, Lazy<RecordTriggerService> lazy4, FeatureSubscriptionManager featureSubscriptionManager) {
        this.isPrefetchComplete = false;
        this.userLoggedOut = false;
        this.persistentStore = featureServiceStorage;
        this.eventBusLazy = lazy;
        this.mobilyticsLazy = lazy3;
        this.recordTriggerService = lazy4;
        this.featureOverrideCache = new ConcurrentHashMap();
        this.featureCache = new ConcurrentHashMap();
        this.coralServiceLazy = lazy2;
        this.currentRequestURL = FeatureConstants.FEATURE_SERVICE_ENDPOINT;
        this.featureRegistryList = Arrays.asList(FeatureListProvider.getFeatureList());
        this.instantlyPropagateFeaturesRegistrySet = new HashSet(Arrays.asList(FeatureListProvider.getInstantlyPropagatedFeatureList()));
        this.sessionManager = new SessionManager(this, this.eventBusLazy);
        this.featureSubscriptionManager = featureSubscriptionManager;
        this.featuresLastRefreshed = -1L;
        this.updatedFeaturesList = new CopyOnWriteArrayList();
    }

    static /* synthetic */ RecordTriggerService lambda$new$0(Lazy lazy, Lazy lazy2) {
        return new RecordTriggerService(lazy, lazy2);
    }

    private void maybeRecordTrigger(@NonNull Feature feature) {
        if (feature.shouldRecordTrigger) {
            this.recordTriggerService.mo358get().record(new RequestTreatment(feature.featureName, feature.treatment, feature.allocationId));
        }
    }

    private void notifySubscribers() {
        for (String str : this.updatedFeaturesList) {
            this.featureSubscriptionManager.notifySubscribers(str);
        }
        this.updatedFeaturesList.clear();
    }

    @VisibleForTesting
    JsonObject aggregateRequestFeatures() {
        JsonObject jsonObject = new JsonObject();
        HashSet hashSet = new HashSet(this.featureRegistryList);
        ArrayList<Feature> arrayList = new ArrayList();
        try {
            arrayList.addAll(this.persistentStore.loadAll().get());
        } catch (InterruptedException e) {
            Log.e("DefaultFeatureServiceV2", "Thread that was loading features from persistent store was interrupted.", e);
            recordMetricsEvent("room_thread_error", "interrupt_error", "RoomDatabase", e);
        } catch (ExecutionException e2) {
            Log.e("DefaultFeatureServiceV2", "Thread that was loading features from persistent store could not complete the task.", e2);
            recordMetricsEvent("room_thread_error", "execution_error", "RoomDatabase", e2);
        }
        for (Feature feature : arrayList) {
            hashSet.add(feature.featureName);
        }
        jsonObject.add(NetworkConstants.FEATURES_KEY, new Gson().toJsonTree(hashSet).getAsJsonArray());
        return jsonObject;
    }

    @VisibleForTesting
    Map<String, Feature> buildFeatureMap(List<Feature> list, boolean z) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        JsonArray jsonArray = new JsonArray();
        for (Feature feature : list) {
            if (z) {
                concurrentHashMap.put(feature.featureName, feature);
                if (!this.featureCache.containsKey(feature.featureName) || !feature.equals(this.featureCache.get(feature.featureName))) {
                    String.format("Feature %s updated: %s", feature.featureName, feature.toString());
                }
            } else if (isFeatureAlreadyInUse(feature) && !this.instantlyPropagateFeaturesRegistrySet.contains(feature.featureName)) {
                String str = feature.featureName;
                concurrentHashMap.put(str, this.featureCache.get(str));
            } else {
                if (isInstantPropagationUpdateNeeded(feature)) {
                    jsonArray.add(feature.featureName);
                    Log.i("DefaultFeatureServiceV2", "Force Propagating Feature: " + feature);
                }
                concurrentHashMap.put(feature.featureName, feature);
            }
            if (!this.featureCache.containsKey(feature.featureName) || !feature.equals(this.featureCache.get(feature.featureName))) {
                if (!this.updatedFeaturesList.contains(feature.featureName)) {
                    this.updatedFeaturesList.add(feature.featureName);
                }
            }
        }
        if (jsonArray.size() > 0) {
            this.eventBusLazy.mo358get().publish(new Message.Builder().setEventType(FeatureConstants.FEATURES_UPDATED_EVENT).setPayload(jsonArray.toString()).build());
        }
        return concurrentHashMap;
    }

    @VisibleForTesting
    List<Feature> buildFeatureObjectListFromResponse(JsonObject jsonObject) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            JsonObject asJsonObject = jsonObject.get(entry.getKey()).getAsJsonObject();
            if (asJsonObject.has(FeatureConstants.Network.TREATMENT)) {
                String str = "";
                if (asJsonObject.has(FeatureConstants.Network.TRIGGER_ON_USE)) {
                    if (asJsonObject.get(FeatureConstants.Network.TRIGGER_ON_USE).getAsBoolean() && !asJsonObject.has(FeatureConstants.Network.ALLOCATION_ID)) {
                        Log.w("DefaultFeatureServiceV2", String.format("Allocation id is required for feature with triggerOnUse field is true. Since the Allocation id is missing, the feature %s will not be saved.", entry.getKey()));
                        recordMetricsEvent(FeatureServiceMetrics.EventType.REFRESH_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.MODEL_ERROR, FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, null);
                    } else {
                        String key = entry.getKey();
                        String asString = asJsonObject.get(FeatureConstants.Network.TREATMENT).getAsString();
                        boolean asBoolean = asJsonObject.get(FeatureConstants.Network.TRIGGER_ON_USE).getAsBoolean();
                        if (asJsonObject.get(FeatureConstants.Network.ALLOCATION_ID) != null) {
                            str = asJsonObject.get(FeatureConstants.Network.ALLOCATION_ID).getAsString();
                        }
                        arrayList.add(new Feature(key, asString, asBoolean, str));
                    }
                } else {
                    String.format("RequestTrigger missing for feature %s from response. Saving %s", entry.getKey(), "feature with \"triggerOnUse\" value as false.");
                    String key2 = entry.getKey();
                    String asString2 = asJsonObject.get(FeatureConstants.Network.TREATMENT).getAsString();
                    if (asJsonObject.get(FeatureConstants.Network.ALLOCATION_ID) != null) {
                        str = asJsonObject.get(FeatureConstants.Network.ALLOCATION_ID).getAsString();
                    }
                    arrayList.add(new Feature(key2, asString2, false, str));
                    recordMetricsEvent(FeatureServiceMetrics.EventType.REFRESH_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.MODEL_ERROR, FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, null);
                }
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Treatment missing for feature");
                outline107.append(entry.getKey());
                outline107.append(" from response.");
                Log.w("DefaultFeatureServiceV2", outline107.toString());
                recordMetricsEvent(FeatureServiceMetrics.EventType.REFRESH_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.MISSING_TREATMENT, FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, null);
            }
        }
        return arrayList;
    }

    public void clearCache() {
        try {
            this.persistentStore.clearAll().get();
        } catch (InterruptedException e) {
            Log.e("DefaultFeatureServiceV2", "Thread that was deleting features from persistent store was interrupted.", e);
            recordMetricsEvent("room_thread_error", "interrupt_error", "RoomDatabase", e);
        } catch (ExecutionException e2) {
            Log.e("DefaultFeatureServiceV2", "Thread that was deleting features from persistent store could not complete the task.", e2);
            recordMetricsEvent("room_thread_error", "execution_error", "RoomDatabase", e2);
        }
        this.featureCache.clear();
        this.featureOverrideCache.clear();
    }

    @VisibleForTesting
    void fetchFeaturesFromBackend() {
        Log.i("DefaultFeatureServiceV2", String.format("featureServiceV2::PeriodicRefresh: Invoking server call at endpoint %s", this.currentRequestURL));
        LatencyInfra latencyInfra = (LatencyInfra) ComponentRegistry.getInstance().getLazy(LatencyInfra.class).mo10268get();
        if (latencyInfra != null) {
            latencyInfra.recordTimeline(TimelineLatencyAction.START_RECORD_TIMELINE, LatencyUtil.getTimelineArgumentBuilder("prefetch").withMetricsOption(true).withPerfTestOption(true).withCustomerOption(true).build());
        }
        this.featuresLastRefreshed = DateTimeUtils.currentTimeMillis();
        JsonObject aggregateRequestFeatures = aggregateRequestFeatures();
        String str = "Feature refresh network request payload: " + aggregateRequestFeatures;
        this.coralServiceLazy.mo358get().request(this.currentRequestURL).post(aggregateRequestFeatures).asRaw().enqueue(new CoralService.Callback<Response>() { // from class: com.amazon.alexa.featureservice.service.DefaultFeatureServiceV2.1
            @Override // com.dee.app.http.CoralService.Callback
            public void onFailure(CoralService.Call<Response> call, CoralServiceException coralServiceException) {
                call.cancel();
                Log.e("DefaultFeatureServiceV2", "Server call to /api/features failed.", coralServiceException);
                if (ExceptionUtil.isNonActionableHTTPError(coralServiceException)) {
                    DefaultFeatureServiceV2 defaultFeatureServiceV2 = DefaultFeatureServiceV2.this;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FeatureServiceMetrics.HTTP_ERROR_PREFIX);
                    outline107.append(coralServiceException.getStatusCode());
                    defaultFeatureServiceV2.recordMetricsEvent("non_actionable_http_error", outline107.toString(), FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, coralServiceException);
                } else {
                    DefaultFeatureServiceV2 defaultFeatureServiceV22 = DefaultFeatureServiceV2.this;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(FeatureServiceMetrics.HTTP_ERROR_PREFIX);
                    outline1072.append(coralServiceException.getStatusCode());
                    defaultFeatureServiceV22.recordMetricsEvent(FeatureServiceMetrics.EventType.REFRESH_HTTP_ERROR, outline1072.toString(), FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, coralServiceException);
                }
                DefaultFeatureServiceV2.this.onFetchFeaturesFromBackendCompleted();
            }

            @Override // com.dee.app.http.CoralService.Callback
            public void onResult(CoralService.Call<Response> call, Response response) {
                if (response != null) {
                    try {
                    } catch (IOException e) {
                        Log.e("DefaultFeatureServiceV2", "Exception caught while reading response body.", e);
                    }
                    if (response.body() != null) {
                        String string = response.body().string();
                        String str2 = "Feature Refresh network response payload: " + string;
                        DefaultFeatureServiceV2.this.handleNetworkResponse(string);
                        DefaultFeatureServiceV2.this.recordMetricsEvent(FeatureServiceMetrics.EventType.REFRESH_SUCCESS, FeatureServiceMetrics.EventType.REFRESH_SUCCESS, FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, null);
                        DefaultFeatureServiceV2.this.onFetchFeaturesFromBackendCompleted();
                    }
                }
                DefaultFeatureServiceV2.this.recordMetricsEvent(FeatureServiceMetrics.EventType.REFRESH_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.EMPTY_RESPONSE, FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, null);
                DefaultFeatureServiceV2.this.onFetchFeaturesFromBackendCompleted();
            }
        });
    }

    @Override // com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2
    public Map<String, String> getAllFeatures() {
        HashMap hashMap = new HashMap();
        Map<String, Feature> map = this.featureCache;
        if (map == null) {
            return hashMap;
        }
        for (Map.Entry<String, Feature> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().treatment);
        }
        return hashMap;
    }

    @VisibleForTesting
    Feature getFeatureValueFromCache(@NonNull String str) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str));
        if (isTimeToRefreshFeatures()) {
            fetchFeaturesFromBackend();
        }
        if (this.featureOverrideCache.containsKey(str)) {
            return this.featureOverrideCache.get(str);
        }
        if (!this.featureCache.containsKey(str)) {
            return null;
        }
        return this.featureCache.get(str);
    }

    @Override // com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2
    public String getSerializedFeatureCache() {
        return transformFeatureCache().toString();
    }

    @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2
    public String getTreatmentAndRecordTrigger(@NonNull String str, @NonNull String str2) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str));
        Preconditions.checkArgument(!TextUtils.isEmpty(str2));
        String.format("Treatment requested for %s", str);
        Feature processFeatureRequest = processFeatureRequest(str, str2, true);
        if (processFeatureRequest != null) {
            String str3 = processFeatureRequest.treatment;
            maybeRecordTrigger(processFeatureRequest);
            String.format("Retrieved treatment for feature %s, returning %s", str, str3);
            return str3;
        }
        String.format("Feature %s not found; adding to featureCache and returning default %s", str, str2);
        this.featureCache.put(str, new Feature(str, str2, false, ""));
        return str2;
    }

    @VisibleForTesting
    void handleNetworkResponse(String str) {
        if (!TextUtils.isEmpty(str)) {
            saveToPersistentCache(new ArrayList(responseParser(str)));
            return;
        }
        Log.i("DefaultFeatureServiceV2", "Empty response received from server for feature access");
        recordMetricsEvent(FeatureServiceMetrics.EventType.REFRESH_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.EMPTY_RESPONSE, FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, null);
    }

    @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2
    public boolean hasAccess(@NonNull String str, boolean z) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(Boolean.valueOf(z));
        Log.i("DefaultFeatureServiceV2", "Checking access for feature:  " + str + " .");
        Feature processFeatureRequest = processFeatureRequest(str, z ? FeatureConstants.T1_DEFAULT : "C_DEFAULT", false);
        if (processFeatureRequest != null) {
            z = isFeatureAccessible(processFeatureRequest.treatment);
        }
        String.format("featureServiceV2::hasAccess feature:%s value:%s.", str, Boolean.valueOf(z));
        return z;
    }

    public void initialize() {
        this.appAuthenticatedSubscriber = this.eventBusLazy.mo358get().getSubscriber();
        this.signOutSubscriber = this.eventBusLazy.mo358get().getSubscriber();
        subscribeToAppAuthenticated();
    }

    @VisibleForTesting
    boolean isFeatureAccessible(String str) {
        return !"C".equalsIgnoreCase(str) && !"C_DEFAULT".equalsIgnoreCase(str);
    }

    @VisibleForTesting
    boolean isFeatureAlreadyInUse(Feature feature) {
        return this.featureCache.containsKey(feature.featureName);
    }

    @VisibleForTesting
    boolean isInstantPropagationUpdateNeeded(Feature feature) {
        if (this.instantlyPropagateFeaturesRegistrySet.contains(feature.featureName)) {
            if (this.featureCache.containsKey(feature.featureName)) {
                return !feature.treatment.equals(this.featureCache.get(feature.featureName).treatment);
            }
            return true;
        }
        return false;
    }

    @VisibleForTesting
    boolean isTimeToRefreshFeatures() {
        return this.featuresLastRefreshed != -1 && DateTimeUtils.currentTimeMillis() - this.featuresLastRefreshed > 3600000;
    }

    public /* synthetic */ void lambda$subscribeToAppAuthenticated$2$DefaultFeatureServiceV2(Message message) {
        onAppAuthenticated();
    }

    public /* synthetic */ void lambda$subscribeToSignOut$4$DefaultFeatureServiceV2(Message message) {
        onSignOutSuccess();
    }

    void loadFeaturesFromPersistentCache(boolean z) {
        List<Feature> arrayList = new ArrayList<>();
        try {
            arrayList = this.persistentStore.loadAll().get();
        } catch (InterruptedException e) {
            Log.e("DefaultFeatureServiceV2", "Thread that was loading features from persistent store was interrupted.", e);
            recordMetricsEvent("room_thread_error", "interrupt_error", "RoomDatabase", e);
        } catch (ExecutionException e2) {
            Log.e("DefaultFeatureServiceV2", "Thread that was loading features from persistent store could not complete the task.", e2);
            recordMetricsEvent("room_thread_error", "execution_error", "RoomDatabase", e2);
        }
        this.featureCache = buildFeatureMap(arrayList, z);
        notifySubscribers();
    }

    @VisibleForTesting
    void notifyFeaturesAreReady() {
        Log.i("DefaultFeatureServiceV2", "Notifying all features are ready.");
        this.eventBusLazy.mo358get().publish(new Message.Builder().setEventType("featureServiceV2:internal:featuresUpdated").setPayload(getSerializedFeatureCache()).build());
    }

    @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2
    public void observeFeature(String str, FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        this.featureSubscriptionManager.addSubscriber(str, featureUpdateListener);
        if (processFeatureRequest(str, null, false, true) != null) {
            featureUpdateListener.onFeatureUpdate(str);
        }
    }

    @VisibleForTesting
    void onAppAuthenticated() {
        prefetch();
        unSubscribeToAppAuthenticated();
        subscribeToSignOut();
        this.sessionManager.initialize();
        if (this.userLoggedOut) {
            recordMetricsEvent(FeatureServiceMetrics.EventType.NEW_SESSION, FeatureServiceMetrics.EventName.RE_LOGIN, FeatureServiceMetrics.Subcomponent.SESSION_MANAGEMENT, null);
        } else {
            recordMetricsEvent(FeatureServiceMetrics.EventType.NEW_SESSION, FeatureServiceMetrics.EventName.COLD_START, FeatureServiceMetrics.Subcomponent.SESSION_MANAGEMENT, null);
        }
        this.userLoggedOut = false;
    }

    @VisibleForTesting
    void onFetchFeaturesFromBackendCompleted() {
        loadFeaturesFromPersistentCache(false);
        notifyFeaturesAreReady();
        this.isPrefetchComplete = true;
        LatencyInfra latencyInfra = (LatencyInfra) ComponentRegistry.getInstance().getLazy(LatencyInfra.class).mo10268get();
        if (latencyInfra != null) {
            latencyInfra.recordTimeline(TimelineLatencyAction.END_TIMELINE, LatencyUtil.getTimelineArgumentBuilder("prefetch").build());
        }
        Log.i("DefaultFeatureServiceV2", "Prefetch completed.");
    }

    @Override // com.amazon.alexa.featureservice.sessionManagement.SessionManager.Listener
    public void onSessionChanged() {
        loadFeaturesFromPersistentCache(true);
        notifyFeaturesAreReady();
        recordMetricsEvent(FeatureServiceMetrics.EventType.NEW_SESSION, FeatureServiceMetrics.EventName.WARM_START, FeatureServiceMetrics.Subcomponent.SESSION_MANAGEMENT, null);
    }

    @VisibleForTesting
    void onSignOutSuccess() {
        clearCache();
        unSubscribeToSignOut();
        subscribeToAppAuthenticated();
        this.sessionManager.terminate();
        this.userLoggedOut = true;
    }

    public void overrideFeature(String str, String str2) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OverriddenAllocation");
        outline107.append(new Date().toString());
        this.featureOverrideCache.put(str, new Feature(str, str2, false, outline107.toString()));
        String str3 = "Feature" + str + " overridden with treatment " + str2;
    }

    @VisibleForTesting
    void prefetch() {
        Log.i("DefaultFeatureServiceV2", "Initiating Prefetch.");
        fetchFeaturesFromBackend();
    }

    @Override // com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2
    public void prefetchFeatures(String[] strArr) {
    }

    @VisibleForTesting
    Feature processFeatureRequest(String str, String str2, boolean z) {
        return processFeatureRequest(str, str2, z, false);
    }

    @VisibleForTesting
    void recordMetricsEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Exception exc) {
        this.mobilyticsLazy.mo358get().recordCriticalEvent(GeneratedOutlineSupport1.outline72(FeatureServiceMetrics.METRICS_NAME_PREFIX, str), GeneratedOutlineSupport1.outline72(FeatureServiceMetrics.METRICS_NAME_PREFIX, str), str2, str3, exc);
    }

    List<Feature> responseParser(String str) {
        ArrayList arrayList = new ArrayList();
        JsonObject jsonObject = new JsonObject();
        JsonElement parse = new JsonParser().parse(str);
        if (!parse.isJsonNull()) {
            jsonObject = parse.getAsJsonObject();
        } else {
            recordMetricsEvent(FeatureServiceMetrics.EventType.REFRESH_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.EMPTY_RESPONSE, FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, null);
        }
        if (jsonObject.has(FeatureConstants.Network.TREATMENTS)) {
            return buildFeatureObjectListFromResponse(jsonObject.getAsJsonObject(FeatureConstants.Network.TREATMENTS).getAsJsonObject());
        }
        recordMetricsEvent(FeatureServiceMetrics.EventType.REFRESH_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.EMPTY_RESPONSE, FeatureServiceMetrics.Subcomponent.REFRESH_FEATURES, null);
        return arrayList;
    }

    @VisibleForTesting
    void saveNonExistentFeature(Feature feature, boolean z) {
        this.featureCache.put(feature.featureName, feature);
        if (z) {
            Log.i("DefaultFeatureServiceV2", String.format("Adding feature %s to persistent store with default treatment value %s", feature.featureName, feature.treatment));
            ArrayList arrayList = new ArrayList();
            arrayList.add(feature);
            saveToPersistentCache(arrayList);
        }
    }

    @VisibleForTesting
    void saveToPersistentCache(List<Feature> list) {
        if (list.size() > 0) {
            try {
                this.persistentStore.save(list).get();
            } catch (InterruptedException e) {
                Log.e("DefaultFeatureServiceV2", "Thread that was saving features to persistent store was interrupted.", e);
                recordMetricsEvent("room_thread_error", "interrupt_error", "RoomDatabase", e);
            } catch (ExecutionException e2) {
                Log.e("DefaultFeatureServiceV2", "Thread that was saving features to persistent store could not complete the task.", e2);
                recordMetricsEvent("room_thread_error", "execution_error", "RoomDatabase", e2);
            }
            Log.i("DefaultFeatureServiceV2", "Features saved to persistent store successfully.");
        }
    }

    @VisibleForTesting
    void subscribeToAppAuthenticated() {
        this.appAuthenticatedFilterUuid = this.appAuthenticatedSubscriber.subscribeFilter($$Lambda$DefaultFeatureServiceV2$mECLwJya63FvvZsV9k3NAwskcU.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.featureservice.service.-$$Lambda$DefaultFeatureServiceV2$xwzXioz8rfn9B9tLG9phPpWIyvM
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DefaultFeatureServiceV2.this.lambda$subscribeToAppAuthenticated$2$DefaultFeatureServiceV2(message);
            }
        });
    }

    @VisibleForTesting
    void subscribeToSignOut() {
        this.signOutFilterUuid = this.signOutSubscriber.subscribeFilter($$Lambda$DefaultFeatureServiceV2$efpJZOMnWG98IldsVO9WgtAGvDY.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.featureservice.service.-$$Lambda$DefaultFeatureServiceV2$2Xdmb8WvGYW03LlOIxeX1UyMp1A
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DefaultFeatureServiceV2.this.lambda$subscribeToSignOut$4$DefaultFeatureServiceV2(message);
            }
        });
    }

    @VisibleForTesting
    public void terminate() {
        unSubscribeToAppAuthenticated();
        unSubscribeToSignOut();
        this.sessionManager.terminate();
    }

    @VisibleForTesting
    JsonObject transformFeatureCache() {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, Feature> entry : this.featureCache.entrySet()) {
            JsonObject jsonObject2 = new JsonObject();
            Feature value = entry.getValue();
            jsonObject2.addProperty(FeatureConstants.Network.TREATMENT, value.treatment);
            jsonObject2.addProperty(FeatureConstants.Network.SHOULD_RECORD_TRIGGER, Boolean.valueOf(value.shouldRecordTrigger));
            jsonObject2.addProperty(FeatureConstants.Network.ALLOCATION_ID, value.allocationId);
            jsonObject.add(value.featureName, jsonObject2);
        }
        return jsonObject;
    }

    @VisibleForTesting
    void unSubscribeToAppAuthenticated() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.appAuthenticatedFilterUuid;
        if (filterUuid != null) {
            this.appAuthenticatedSubscriber.unsubscribeFilter(filterUuid);
        }
    }

    @VisibleForTesting
    void unSubscribeToSignOut() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.signOutFilterUuid;
        if (filterUuid != null) {
            this.signOutSubscriber.unsubscribeFilter(filterUuid);
        }
    }

    @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2
    public void unsubscribe(FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        this.featureSubscriptionManager.removeSubscriber(featureUpdateListener);
    }

    @VisibleForTesting
    Feature processFeatureRequest(String str, String str2, boolean z, boolean z2) {
        Feature featureValueFromCache = getFeatureValueFromCache(str);
        if (featureValueFromCache == null) {
            try {
                featureValueFromCache = this.persistentStore.load(str).get();
            } catch (InterruptedException e) {
                Log.e("DefaultFeatureServiceV2", String.format("Thread that was loading feature %s from persistent store was interrupted.", str), e);
                recordMetricsEvent("room_thread_error", "interrupt_error", "RoomDatabase", e);
            } catch (ExecutionException e2) {
                Log.e("DefaultFeatureServiceV2", String.format("Thread that was loading feature %s from persistent store could not complete the task.", str), e2);
                recordMetricsEvent("room_thread_error", "execution_error", "RoomDatabase", e2);
            }
            if (!z2) {
                if (featureValueFromCache != null) {
                    this.featureCache.put(str, featureValueFromCache);
                } else {
                    Log.i("DefaultFeatureServiceV2", "Feature " + str + " does not exist in the cache and in persistent store. Returning default value.");
                    saveNonExistentFeature(new Feature(str, str2, false, ""), z);
                }
            }
        }
        return featureValueFromCache;
    }

    public DefaultFeatureServiceV2(Lazy<EventBus> lazy, FeatureServiceStorage featureServiceStorage, final Lazy<CoralService> lazy2, final Lazy<Mobilytics> lazy3) {
        this(lazy, featureServiceStorage, lazy2, lazy3, new Lazy() { // from class: com.amazon.alexa.featureservice.service.-$$Lambda$DefaultFeatureServiceV2$bGAE0b_IDlw9vMUIjGqxipbGSdA
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return new RecordTriggerService(Lazy.this, lazy3);
            }
        }, new FeatureSubscriptionManager(new FeatureSubscriptionMap()));
    }
}
