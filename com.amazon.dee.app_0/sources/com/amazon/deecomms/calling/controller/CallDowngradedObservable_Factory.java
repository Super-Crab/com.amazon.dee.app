package com.amazon.deecomms.calling.controller;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class CallDowngradedObservable_Factory implements Factory<CallDowngradedObservable> {
    private static final CallDowngradedObservable_Factory INSTANCE = new CallDowngradedObservable_Factory();

    public static CallDowngradedObservable_Factory create() {
        return INSTANCE;
    }

    public static CallDowngradedObservable newCallDowngradedObservable() {
        return new CallDowngradedObservable();
    }

    public static CallDowngradedObservable provideInstance() {
        return new CallDowngradedObservable();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallDowngradedObservable mo10268get() {
        return provideInstance();
    }
}
