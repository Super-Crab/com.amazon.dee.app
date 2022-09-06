package com.amazon.deecomms.calling.controller;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallPayloadValidator_Factory implements Factory<CallPayloadValidator> {
    private final Provider<Integer> maxRetryCountProvider;
    private final Provider<LazyComponent<Mobilytics>> mobilyticsProvider;

    public CallPayloadValidator_Factory(Provider<LazyComponent<Mobilytics>> provider, Provider<Integer> provider2) {
        this.mobilyticsProvider = provider;
        this.maxRetryCountProvider = provider2;
    }

    public static CallPayloadValidator_Factory create(Provider<LazyComponent<Mobilytics>> provider, Provider<Integer> provider2) {
        return new CallPayloadValidator_Factory(provider, provider2);
    }

    public static CallPayloadValidator newCallPayloadValidator(LazyComponent<Mobilytics> lazyComponent, int i) {
        return new CallPayloadValidator(lazyComponent, i);
    }

    public static CallPayloadValidator provideInstance(Provider<LazyComponent<Mobilytics>> provider, Provider<Integer> provider2) {
        return new CallPayloadValidator(provider.mo10268get(), provider2.mo10268get().intValue());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallPayloadValidator mo10268get() {
        return provideInstance(this.mobilyticsProvider, this.maxRetryCountProvider);
    }
}
