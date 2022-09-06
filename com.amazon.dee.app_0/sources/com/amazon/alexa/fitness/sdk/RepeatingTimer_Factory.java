package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class RepeatingTimer_Factory implements Factory<RepeatingTimer> {
    private final Provider<Long> durationInMillisProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MessageProcessor> messageProcessorProvider;

    public RepeatingTimer_Factory(Provider<MessageProcessor> provider, Provider<ILog> provider2, Provider<Long> provider3) {
        this.messageProcessorProvider = provider;
        this.logProvider = provider2;
        this.durationInMillisProvider = provider3;
    }

    public static RepeatingTimer_Factory create(Provider<MessageProcessor> provider, Provider<ILog> provider2, Provider<Long> provider3) {
        return new RepeatingTimer_Factory(provider, provider2, provider3);
    }

    public static RepeatingTimer newRepeatingTimer(MessageProcessor messageProcessor, ILog iLog, long j) {
        return new RepeatingTimer(messageProcessor, iLog, j);
    }

    public static RepeatingTimer provideInstance(Provider<MessageProcessor> provider, Provider<ILog> provider2, Provider<Long> provider3) {
        return new RepeatingTimer(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get().longValue());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RepeatingTimer mo10268get() {
        return provideInstance(this.messageProcessorProvider, this.logProvider, this.durationInMillisProvider);
    }
}
