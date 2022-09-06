package com.amazon.alexa.fitness.util;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class DefaultDisposableManagerFactory_Factory implements Factory<DefaultDisposableManagerFactory> {
    private static final DefaultDisposableManagerFactory_Factory INSTANCE = new DefaultDisposableManagerFactory_Factory();

    public static DefaultDisposableManagerFactory_Factory create() {
        return INSTANCE;
    }

    public static DefaultDisposableManagerFactory newDefaultDisposableManagerFactory() {
        return new DefaultDisposableManagerFactory();
    }

    public static DefaultDisposableManagerFactory provideInstance() {
        return new DefaultDisposableManagerFactory();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultDisposableManagerFactory mo10268get() {
        return provideInstance();
    }
}
