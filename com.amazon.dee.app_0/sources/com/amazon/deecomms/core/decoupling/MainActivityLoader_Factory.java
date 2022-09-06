package com.amazon.deecomms.core.decoupling;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class MainActivityLoader_Factory implements Factory<MainActivityLoader> {
    private static final MainActivityLoader_Factory INSTANCE = new MainActivityLoader_Factory();

    public static MainActivityLoader_Factory create() {
        return INSTANCE;
    }

    public static MainActivityLoader newMainActivityLoader() {
        return new MainActivityLoader();
    }

    public static MainActivityLoader provideInstance() {
        return new MainActivityLoader();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MainActivityLoader mo10268get() {
        return provideInstance();
    }
}
