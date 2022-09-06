package com.amazon.alexa.mobilytics.lifecycle;

import dagger.internal.Factory;
/* loaded from: classes9.dex */
public final class Lifecycle_Factory implements Factory<Lifecycle> {
    private static final Lifecycle_Factory INSTANCE = new Lifecycle_Factory();

    public static Lifecycle_Factory create() {
        return INSTANCE;
    }

    public static Lifecycle newLifecycle() {
        return new Lifecycle();
    }

    public static Lifecycle provideInstance() {
        return new Lifecycle();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Lifecycle mo10268get() {
        return provideInstance();
    }
}
