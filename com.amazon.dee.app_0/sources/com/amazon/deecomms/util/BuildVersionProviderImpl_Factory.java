package com.amazon.deecomms.util;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class BuildVersionProviderImpl_Factory implements Factory<BuildVersionProviderImpl> {
    private static final BuildVersionProviderImpl_Factory INSTANCE = new BuildVersionProviderImpl_Factory();

    public static BuildVersionProviderImpl_Factory create() {
        return INSTANCE;
    }

    public static BuildVersionProviderImpl newBuildVersionProviderImpl() {
        return new BuildVersionProviderImpl();
    }

    public static BuildVersionProviderImpl provideInstance() {
        return new BuildVersionProviderImpl();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BuildVersionProviderImpl mo10268get() {
        return provideInstance();
    }
}
