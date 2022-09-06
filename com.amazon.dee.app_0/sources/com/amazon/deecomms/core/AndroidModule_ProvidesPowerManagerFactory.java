package com.amazon.deecomms.core;

import android.content.Context;
import android.os.PowerManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesPowerManagerFactory implements Factory<PowerManager> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesPowerManagerFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesPowerManagerFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesPowerManagerFactory(androidModule, provider);
    }

    public static PowerManager provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (PowerManager) Preconditions.checkNotNull(androidModule.providesPowerManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static PowerManager proxyProvidesPowerManager(AndroidModule androidModule, Context context) {
        return (PowerManager) Preconditions.checkNotNull(androidModule.providesPowerManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PowerManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
