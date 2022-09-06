package com.amazon.alexa.handsfree.storage.initialization;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class AppInitializationStatusStore_Factory implements Factory<AppInitializationStatusStore> {
    private static final AppInitializationStatusStore_Factory INSTANCE = new AppInitializationStatusStore_Factory();

    public static AppInitializationStatusStore_Factory create() {
        return INSTANCE;
    }

    public static AppInitializationStatusStore newAppInitializationStatusStore() {
        return new AppInitializationStatusStore();
    }

    public static AppInitializationStatusStore provideInstance() {
        return new AppInitializationStatusStore();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AppInitializationStatusStore mo10268get() {
        return provideInstance();
    }
}
