package com.amazon.deecomms.core;

import android.app.NotificationManager;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesNotificationManagerFactory implements Factory<NotificationManager> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesNotificationManagerFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesNotificationManagerFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesNotificationManagerFactory(androidModule, provider);
    }

    public static NotificationManager provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (NotificationManager) Preconditions.checkNotNull(androidModule.providesNotificationManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static NotificationManager proxyProvidesNotificationManager(AndroidModule androidModule, Context context) {
        return (NotificationManager) Preconditions.checkNotNull(androidModule.providesNotificationManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
