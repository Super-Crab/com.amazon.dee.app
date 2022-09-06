package com.amazon.alexa.handsfree.vendor.bridge;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceAppPackageInfoProvider_Factory implements Factory<VoiceAppPackageInfoProvider> {
    private final Provider<Context> arg0Provider;

    public VoiceAppPackageInfoProvider_Factory(Provider<Context> provider) {
        this.arg0Provider = provider;
    }

    public static VoiceAppPackageInfoProvider_Factory create(Provider<Context> provider) {
        return new VoiceAppPackageInfoProvider_Factory(provider);
    }

    public static VoiceAppPackageInfoProvider newVoiceAppPackageInfoProvider(Context context) {
        return new VoiceAppPackageInfoProvider(context);
    }

    public static VoiceAppPackageInfoProvider provideInstance(Provider<Context> provider) {
        return new VoiceAppPackageInfoProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceAppPackageInfoProvider mo10268get() {
        return provideInstance(this.arg0Provider);
    }
}
