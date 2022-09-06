package com.amazon.alexa.fitness.util;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class CompositeDisposableManager_Factory implements Factory<CompositeDisposableManager> {
    private static final CompositeDisposableManager_Factory INSTANCE = new CompositeDisposableManager_Factory();

    public static CompositeDisposableManager_Factory create() {
        return INSTANCE;
    }

    public static CompositeDisposableManager newCompositeDisposableManager() {
        return new CompositeDisposableManager();
    }

    public static CompositeDisposableManager provideInstance() {
        return new CompositeDisposableManager();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CompositeDisposableManager mo10268get() {
        return provideInstance();
    }
}
