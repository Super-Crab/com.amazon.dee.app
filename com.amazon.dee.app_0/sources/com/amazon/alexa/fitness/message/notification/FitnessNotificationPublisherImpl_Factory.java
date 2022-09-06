package com.amazon.alexa.fitness.message.notification;

import android.content.Context;
import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class FitnessNotificationPublisherImpl_Factory implements Factory<FitnessNotificationPublisherImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<ILog> logProvider;

    public FitnessNotificationPublisherImpl_Factory(Provider<Context> provider, Provider<ILog> provider2) {
        this.contextProvider = provider;
        this.logProvider = provider2;
    }

    public static FitnessNotificationPublisherImpl_Factory create(Provider<Context> provider, Provider<ILog> provider2) {
        return new FitnessNotificationPublisherImpl_Factory(provider, provider2);
    }

    public static FitnessNotificationPublisherImpl newFitnessNotificationPublisherImpl(Context context, ILog iLog) {
        return new FitnessNotificationPublisherImpl(context, iLog);
    }

    public static FitnessNotificationPublisherImpl provideInstance(Provider<Context> provider, Provider<ILog> provider2) {
        return new FitnessNotificationPublisherImpl(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessNotificationPublisherImpl mo10268get() {
        return provideInstance(this.contextProvider, this.logProvider);
    }
}
