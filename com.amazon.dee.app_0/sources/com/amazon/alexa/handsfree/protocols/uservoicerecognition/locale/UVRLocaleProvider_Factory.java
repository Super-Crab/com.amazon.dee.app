package com.amazon.alexa.handsfree.protocols.uservoicerecognition.locale;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class UVRLocaleProvider_Factory implements Factory<UVRLocaleProvider> {
    private static final UVRLocaleProvider_Factory INSTANCE = new UVRLocaleProvider_Factory();

    public static UVRLocaleProvider_Factory create() {
        return INSTANCE;
    }

    public static UVRLocaleProvider newUVRLocaleProvider() {
        return new UVRLocaleProvider();
    }

    public static UVRLocaleProvider provideInstance() {
        return new UVRLocaleProvider();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UVRLocaleProvider mo10268get() {
        return provideInstance();
    }
}
