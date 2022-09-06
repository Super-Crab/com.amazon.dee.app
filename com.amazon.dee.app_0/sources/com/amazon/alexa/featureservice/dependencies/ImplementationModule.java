package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.alexa.featureservice.implementation.AlexaMobileAndroidFeatureServiceImpl;
import com.amazon.alexa.featureservice.implementation.FeatureCache;
import com.amazon.alexa.featureservice.implementation.FeatureRegistryUtil;
import com.amazon.alexa.featureservice.implementation.FeatureServiceViewModel;
import com.amazon.alexa.featureservice.recordTrigger.RecordTriggerService;
import com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo;
import com.amazon.alexa.featureservice.service.FeatureSubscriptionManager;
import com.amazon.alexa.featureservice.sessionManagement.SessionManager;
import com.amazon.alexa.featureservice.util.Analytics;
import com.amazon.alexa.featureservice.util.SafeEventBus;
import com.amazon.alexa.featureservice.util.TimeUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.dee.app.http.CoralService;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
public class ImplementationModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public FeatureServiceV2 providesFeatureService(PlatformFeatureServiceV2 platformFeatureServiceV2) {
        return platformFeatureServiceV2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public FeatureServiceViewModel providesFeatureServiceViewModel(FeatureDataRepo featureDataRepo, FeatureRegistryUtil featureRegistryUtil, RecordTriggerService recordTriggerService, FeatureCache featureCache, FeatureSubscriptionManager featureSubscriptionManager, Analytics analytics, SafeEventBus safeEventBus, Gson gson, TimeUtil timeUtil, FeatureServiceConfiguration featureServiceConfiguration) {
        return new FeatureServiceViewModel(featureDataRepo, featureRegistryUtil, recordTriggerService, featureCache, featureSubscriptionManager, analytics, safeEventBus, gson, timeUtil, featureServiceConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PlatformFeatureServiceV2 providesPlatformFeatureService(FeatureServiceViewModel featureServiceViewModel, SessionManager sessionManager, SafeEventBus safeEventBus, Analytics analytics) {
        return new AlexaMobileAndroidFeatureServiceImpl(featureServiceViewModel, sessionManager, safeEventBus, analytics);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public RecordTriggerService providesRecordTriggerService(Lazy<CoralService> lazy, Lazy<Mobilytics> lazy2) {
        return new RecordTriggerService(lazy, lazy2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public SessionManager providesSessionManager(Lazy<EventBus> lazy, FeatureServiceConfiguration featureServiceConfiguration) {
        return new SessionManager(lazy, featureServiceConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public FeatureServiceConfiguration providesTestConfiguration() {
        return new FeatureServiceConfiguration();
    }
}
