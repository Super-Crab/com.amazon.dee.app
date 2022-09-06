package com.amazon.alexa.featureservice.implementation;

import android.util.Log;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.featureservice.constants.FeatureServiceMetrics;
import com.amazon.alexa.featureservice.repo.model.Feature;
import com.amazon.alexa.featureservice.sessionManagement.SessionManager;
import com.amazon.alexa.featureservice.util.Analytics;
import com.amazon.alexa.featureservice.util.SafeEventBus;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class AlexaMobileAndroidFeatureServiceImpl implements PlatformFeatureServiceV2, SessionManager.Listener {
    private static final String TAG = "AlexaMobileAndroidFeatureServiceImpl";
    private Analytics analytics;
    private MultiFilterSubscriber appAuthSubscriber;
    private SafeEventBus safeEventBus;
    private SessionManager sessionManager;
    private MultiFilterSubscriber signOutSubscriber;
    private FeatureServiceViewModel viewModel;

    @Inject
    public AlexaMobileAndroidFeatureServiceImpl(FeatureServiceViewModel featureServiceViewModel, SessionManager sessionManager, SafeEventBus safeEventBus, Analytics analytics) {
        this.viewModel = featureServiceViewModel;
        this.sessionManager = sessionManager;
        this.safeEventBus = safeEventBus;
        this.analytics = analytics;
        sessionManager.setListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyFeaturesUpdated() {
        this.safeEventBus.publish("featureServiceV2:internal:featuresUpdated", getSerializedFeatureCache());
    }

    private void refreshFeatures() {
        this.viewModel.refreshFeaturesFromRemote().ignoreElement().subscribe(new DisposableCompletableObserver() { // from class: com.amazon.alexa.featureservice.implementation.AlexaMobileAndroidFeatureServiceImpl.2
            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                AlexaMobileAndroidFeatureServiceImpl.this.notifyFeaturesUpdated();
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                Log.e(AlexaMobileAndroidFeatureServiceImpl.TAG, "Error while refreshing features", th);
            }
        });
    }

    private void subscribeToAppAuthenticatedEvent() {
        this.appAuthSubscriber = this.safeEventBus.subscribe($$Lambda$AlexaMobileAndroidFeatureServiceImpl$iwmT1kRMwclT9e92AqppbHwzKbA.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.featureservice.implementation.-$$Lambda$AlexaMobileAndroidFeatureServiceImpl$_PrQsn19QwPoQNoMsBblCTHdNvY
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaMobileAndroidFeatureServiceImpl.this.lambda$subscribeToAppAuthenticatedEvent$1$AlexaMobileAndroidFeatureServiceImpl(message);
            }
        });
    }

    private void subscribeToSignOut() {
        this.signOutSubscriber = this.safeEventBus.subscribe($$Lambda$AlexaMobileAndroidFeatureServiceImpl$vZmahlcKQcqPK4EZSYBNrl04gRg.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.featureservice.implementation.-$$Lambda$AlexaMobileAndroidFeatureServiceImpl$IGz0nk2mGl2qdRlnrFIiwF82i2Q
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaMobileAndroidFeatureServiceImpl.this.lambda$subscribeToSignOut$3$AlexaMobileAndroidFeatureServiceImpl(message);
            }
        });
    }

    @Override // com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2
    public Map<String, String> getAllFeatures() {
        return this.viewModel.getFeatureTreatmentMap();
    }

    @Override // com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2
    public String getSerializedFeatureCache() {
        return this.viewModel.getSerializedFeatureCache();
    }

    @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2
    public String getTreatmentAndRecordTrigger(String str, String str2) {
        return this.viewModel.getTreatmentAndRecordTrigger(str, str2);
    }

    @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2
    public boolean hasAccess(String str, boolean z) {
        return this.viewModel.hasAccess(str, z);
    }

    public void initialize() {
        subscribeToAppAuthenticatedEvent();
    }

    public /* synthetic */ void lambda$subscribeToAppAuthenticatedEvent$1$AlexaMobileAndroidFeatureServiceImpl(Message message) {
        this.viewModel.processUserAction(0);
        refreshFeatures();
        this.safeEventBus.unsubscribe(this.appAuthSubscriber);
        subscribeToSignOut();
        this.sessionManager.initialize();
    }

    public /* synthetic */ void lambda$subscribeToSignOut$3$AlexaMobileAndroidFeatureServiceImpl(Message message) {
        this.viewModel.clearAllFeatures();
        this.safeEventBus.unsubscribe(this.signOutSubscriber);
        subscribeToAppAuthenticatedEvent();
        this.sessionManager.terminate();
        this.viewModel.processUserAction(1);
    }

    @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2
    public void observeFeature(String str, FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        this.viewModel.subscribeToFeatureUpdates(str, featureUpdateListener);
    }

    @Override // com.amazon.alexa.featureservice.sessionManagement.SessionManager.Listener
    public void onSessionChanged() {
        this.analytics.recordCriticalMetric(FeatureServiceMetrics.EventType.NEW_SESSION, FeatureServiceMetrics.EventName.WARM_START, FeatureServiceMetrics.Subcomponent.SESSION_MANAGEMENT, null);
        this.viewModel.reloadFeaturesFromDb().subscribe(new DisposableSingleObserver<List<Feature>>() { // from class: com.amazon.alexa.featureservice.implementation.AlexaMobileAndroidFeatureServiceImpl.1
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(Throwable th) {
                Log.e(AlexaMobileAndroidFeatureServiceImpl.TAG, "Error loading features from db", th);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(List<Feature> list) {
                if (list == null || list.size() <= 0) {
                    return;
                }
                AlexaMobileAndroidFeatureServiceImpl.this.notifyFeaturesUpdated();
            }
        });
    }

    @Override // com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2
    public void prefetchFeatures(String[] strArr) {
        this.viewModel.prefetchNewlyAddedFeatures(strArr);
    }

    @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2
    public void unsubscribe(FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        this.viewModel.unsubscribeListenerFromFeatureUpdates(featureUpdateListener);
    }
}
