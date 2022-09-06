package com.amazon.deecomms.core;

import android.content.Context;
import android.telephony.TelephonyManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesTelephonyManagerFactory implements Factory<TelephonyManager> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesTelephonyManagerFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesTelephonyManagerFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesTelephonyManagerFactory(androidModule, provider);
    }

    public static TelephonyManager provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (TelephonyManager) Preconditions.checkNotNull(androidModule.providesTelephonyManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static TelephonyManager proxyProvidesTelephonyManager(AndroidModule androidModule, Context context) {
        return (TelephonyManager) Preconditions.checkNotNull(androidModule.providesTelephonyManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TelephonyManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
