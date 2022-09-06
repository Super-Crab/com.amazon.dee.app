package com.amazon.deecomms.core;

import android.content.Context;
import android.os.BatteryManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesBatteryManagerFactory implements Factory<BatteryManager> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesBatteryManagerFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesBatteryManagerFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesBatteryManagerFactory(androidModule, provider);
    }

    public static BatteryManager provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (BatteryManager) Preconditions.checkNotNull(androidModule.providesBatteryManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static BatteryManager proxyProvidesBatteryManager(AndroidModule androidModule, Context context) {
        return (BatteryManager) Preconditions.checkNotNull(androidModule.providesBatteryManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BatteryManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
