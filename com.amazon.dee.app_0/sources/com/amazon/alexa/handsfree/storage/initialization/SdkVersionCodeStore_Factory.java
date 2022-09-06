package com.amazon.alexa.handsfree.storage.initialization;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class SdkVersionCodeStore_Factory implements Factory<SdkVersionCodeStore> {
    private static final SdkVersionCodeStore_Factory INSTANCE = new SdkVersionCodeStore_Factory();

    public static SdkVersionCodeStore_Factory create() {
        return INSTANCE;
    }

    public static SdkVersionCodeStore newSdkVersionCodeStore() {
        return new SdkVersionCodeStore();
    }

    public static SdkVersionCodeStore provideInstance() {
        return new SdkVersionCodeStore();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SdkVersionCodeStore mo10268get() {
        return provideInstance();
    }
}
