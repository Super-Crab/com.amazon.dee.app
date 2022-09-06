package com.amazon.alexa.alertsca.dependencies;

import android.app.NotificationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesAndroidNotificationManagerFactory implements Factory<NotificationManager> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesAndroidNotificationManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesAndroidNotificationManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesAndroidNotificationManagerFactory(applicationModule);
    }

    public static NotificationManager provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesAndroidNotificationManager(applicationModule);
    }

    public static NotificationManager proxyProvidesAndroidNotificationManager(ApplicationModule applicationModule) {
        return (NotificationManager) Preconditions.checkNotNull(applicationModule.providesAndroidNotificationManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationManager mo10268get() {
        return provideInstance(this.module);
    }
}
