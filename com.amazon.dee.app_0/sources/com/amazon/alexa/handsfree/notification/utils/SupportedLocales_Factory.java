package com.amazon.alexa.handsfree.notification.utils;

import android.content.Context;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class SupportedLocales_Factory implements Factory<SupportedLocales> {
    private final Provider<Context> contextProvider;
    private final Provider<DeviceTypeInformationProvider> deviceTypeInformationProvider;

    public SupportedLocales_Factory(Provider<Context> provider, Provider<DeviceTypeInformationProvider> provider2) {
        this.contextProvider = provider;
        this.deviceTypeInformationProvider = provider2;
    }

    public static SupportedLocales_Factory create(Provider<Context> provider, Provider<DeviceTypeInformationProvider> provider2) {
        return new SupportedLocales_Factory(provider, provider2);
    }

    public static SupportedLocales newSupportedLocales(Context context, DeviceTypeInformationProvider deviceTypeInformationProvider) {
        return new SupportedLocales(context, deviceTypeInformationProvider);
    }

    public static SupportedLocales provideInstance(Provider<Context> provider, Provider<DeviceTypeInformationProvider> provider2) {
        return new SupportedLocales(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SupportedLocales mo10268get() {
        return provideInstance(this.contextProvider, this.deviceTypeInformationProvider);
    }
}
