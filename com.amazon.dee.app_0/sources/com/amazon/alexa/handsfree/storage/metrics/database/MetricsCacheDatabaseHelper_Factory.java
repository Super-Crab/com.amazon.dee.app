package com.amazon.alexa.handsfree.storage.metrics.database;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MetricsCacheDatabaseHelper_Factory implements Factory<MetricsCacheDatabaseHelper> {
    private final Provider<Context> contextProvider;

    public MetricsCacheDatabaseHelper_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static MetricsCacheDatabaseHelper_Factory create(Provider<Context> provider) {
        return new MetricsCacheDatabaseHelper_Factory(provider);
    }

    public static MetricsCacheDatabaseHelper newMetricsCacheDatabaseHelper(Context context) {
        return new MetricsCacheDatabaseHelper(context);
    }

    public static MetricsCacheDatabaseHelper provideInstance(Provider<Context> provider) {
        return new MetricsCacheDatabaseHelper(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsCacheDatabaseHelper mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
