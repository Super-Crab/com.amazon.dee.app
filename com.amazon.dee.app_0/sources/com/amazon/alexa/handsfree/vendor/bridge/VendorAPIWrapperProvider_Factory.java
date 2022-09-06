package com.amazon.alexa.handsfree.vendor.bridge;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VendorAPIWrapperProvider_Factory implements Factory<VendorAPIWrapperProvider> {
    private final Provider<Context> arg0Provider;

    public VendorAPIWrapperProvider_Factory(Provider<Context> provider) {
        this.arg0Provider = provider;
    }

    public static VendorAPIWrapperProvider_Factory create(Provider<Context> provider) {
        return new VendorAPIWrapperProvider_Factory(provider);
    }

    public static VendorAPIWrapperProvider newVendorAPIWrapperProvider(Context context) {
        return new VendorAPIWrapperProvider(context);
    }

    public static VendorAPIWrapperProvider provideInstance(Provider<Context> provider) {
        return new VendorAPIWrapperProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VendorAPIWrapperProvider mo10268get() {
        return provideInstance(this.arg0Provider);
    }
}
