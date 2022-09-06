package com.amazon.tarazed.sessionmanager;

import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class TarazedSessionAndroidService_MembersInjector implements MembersInjector<TarazedSessionAndroidService> {
    private final Provider<ActivityTracker> activityTrackerProvider;
    private final Provider<TarazedController> controllerProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedResourceManager> resourceManagerProvider;

    public TarazedSessionAndroidService_MembersInjector(Provider<TarazedSessionLogger> provider, Provider<TarazedResourceManager> provider2, Provider<TarazedController> provider3, Provider<ActivityTracker> provider4) {
        this.loggerProvider = provider;
        this.resourceManagerProvider = provider2;
        this.controllerProvider = provider3;
        this.activityTrackerProvider = provider4;
    }

    public static MembersInjector<TarazedSessionAndroidService> create(Provider<TarazedSessionLogger> provider, Provider<TarazedResourceManager> provider2, Provider<TarazedController> provider3, Provider<ActivityTracker> provider4) {
        return new TarazedSessionAndroidService_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectActivityTracker(TarazedSessionAndroidService tarazedSessionAndroidService, ActivityTracker activityTracker) {
        tarazedSessionAndroidService.activityTracker = activityTracker;
    }

    public static void injectController(TarazedSessionAndroidService tarazedSessionAndroidService, TarazedController tarazedController) {
        tarazedSessionAndroidService.controller = tarazedController;
    }

    public static void injectLogger(TarazedSessionAndroidService tarazedSessionAndroidService, TarazedSessionLogger tarazedSessionLogger) {
        tarazedSessionAndroidService.logger = tarazedSessionLogger;
    }

    public static void injectResourceManager(TarazedSessionAndroidService tarazedSessionAndroidService, TarazedResourceManager tarazedResourceManager) {
        tarazedSessionAndroidService.resourceManager = tarazedResourceManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TarazedSessionAndroidService tarazedSessionAndroidService) {
        injectLogger(tarazedSessionAndroidService, this.loggerProvider.mo10268get());
        injectResourceManager(tarazedSessionAndroidService, this.resourceManagerProvider.mo10268get());
        injectController(tarazedSessionAndroidService, this.controllerProvider.mo10268get());
        injectActivityTracker(tarazedSessionAndroidService, this.activityTrackerProvider.mo10268get());
    }
}
