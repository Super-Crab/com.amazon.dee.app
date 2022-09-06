package com.amazon.rtcsc.capabilityagent.avs;

import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WrappedAlexaConnection_Factory implements Factory<WrappedAlexaConnection> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<ScheduledExecutorService> executorServiceProvider;

    public WrappedAlexaConnection_Factory(Provider<AlexaServicesConnection> provider, Provider<ScheduledExecutorService> provider2) {
        this.alexaServicesConnectionProvider = provider;
        this.executorServiceProvider = provider2;
    }

    public static WrappedAlexaConnection_Factory create(Provider<AlexaServicesConnection> provider, Provider<ScheduledExecutorService> provider2) {
        return new WrappedAlexaConnection_Factory(provider, provider2);
    }

    public static WrappedAlexaConnection newWrappedAlexaConnection(AlexaServicesConnection alexaServicesConnection, ScheduledExecutorService scheduledExecutorService) {
        return new WrappedAlexaConnection(alexaServicesConnection, scheduledExecutorService);
    }

    public static WrappedAlexaConnection provideInstance(Provider<AlexaServicesConnection> provider, Provider<ScheduledExecutorService> provider2) {
        return new WrappedAlexaConnection(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WrappedAlexaConnection mo10268get() {
        return provideInstance(this.alexaServicesConnectionProvider, this.executorServiceProvider);
    }
}
