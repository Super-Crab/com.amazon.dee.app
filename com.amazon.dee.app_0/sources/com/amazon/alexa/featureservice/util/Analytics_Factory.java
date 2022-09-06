package com.amazon.alexa.featureservice.util;

import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class Analytics_Factory implements Factory<Analytics> {
    private final Provider<Mobilytics> mobilyticsLazyProvider;

    public Analytics_Factory(Provider<Mobilytics> provider) {
        this.mobilyticsLazyProvider = provider;
    }

    public static Analytics_Factory create(Provider<Mobilytics> provider) {
        return new Analytics_Factory(provider);
    }

    public static Analytics newAnalytics(Lazy<Mobilytics> lazy) {
        return new Analytics(lazy);
    }

    public static Analytics provideInstance(Provider<Mobilytics> provider) {
        return new Analytics(DoubleCheck.lazy(provider));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Analytics mo10268get() {
        return provideInstance(this.mobilyticsLazyProvider);
    }
}
