package com.amazon.alexa.handsfree.storage.initialization;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class DspApkVersionCodeStore_Factory implements Factory<DspApkVersionCodeStore> {
    private static final DspApkVersionCodeStore_Factory INSTANCE = new DspApkVersionCodeStore_Factory();

    public static DspApkVersionCodeStore_Factory create() {
        return INSTANCE;
    }

    public static DspApkVersionCodeStore newDspApkVersionCodeStore() {
        return new DspApkVersionCodeStore();
    }

    public static DspApkVersionCodeStore provideInstance() {
        return new DspApkVersionCodeStore();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DspApkVersionCodeStore mo10268get() {
        return provideInstance();
    }
}
