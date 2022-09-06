package com.amazon.deecomms.core;

import android.content.Context;
import android.telecom.TelecomManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesTelecomManagerFactory implements Factory<TelecomManager> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesTelecomManagerFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesTelecomManagerFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesTelecomManagerFactory(androidModule, provider);
    }

    public static TelecomManager provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (TelecomManager) Preconditions.checkNotNull(androidModule.providesTelecomManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static TelecomManager proxyProvidesTelecomManager(AndroidModule androidModule, Context context) {
        return (TelecomManager) Preconditions.checkNotNull(androidModule.providesTelecomManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TelecomManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
