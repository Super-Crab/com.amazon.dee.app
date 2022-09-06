package com.amazon.deecomms.core;

import android.app.ActivityManager;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesActivityManagerFactory implements Factory<ActivityManager> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesActivityManagerFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesActivityManagerFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesActivityManagerFactory(androidModule, provider);
    }

    public static ActivityManager provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (ActivityManager) Preconditions.checkNotNull(androidModule.providesActivityManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ActivityManager proxyProvidesActivityManager(AndroidModule androidModule, Context context) {
        return (ActivityManager) Preconditions.checkNotNull(androidModule.providesActivityManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ActivityManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
