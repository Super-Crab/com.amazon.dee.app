package com.amazon.alexa.mobilytics;

import com.amazon.alexa.mobilytics.executor.Executor;
import com.amazon.alexa.mobilytics.session.SessionManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultMobilytics_Factory implements Factory<DefaultMobilytics> {
    private final Provider<MobilyticsConfiguration> configurationProvider;
    private final Provider<Executor> executorProvider;
    private final Provider<SessionManager> sessionManagerProvider;

    public DefaultMobilytics_Factory(Provider<MobilyticsConfiguration> provider, Provider<Executor> provider2, Provider<SessionManager> provider3) {
        this.configurationProvider = provider;
        this.executorProvider = provider2;
        this.sessionManagerProvider = provider3;
    }

    public static DefaultMobilytics_Factory create(Provider<MobilyticsConfiguration> provider, Provider<Executor> provider2, Provider<SessionManager> provider3) {
        return new DefaultMobilytics_Factory(provider, provider2, provider3);
    }

    public static DefaultMobilytics newDefaultMobilytics(MobilyticsConfiguration mobilyticsConfiguration, Executor executor, SessionManager sessionManager) {
        return new DefaultMobilytics(mobilyticsConfiguration, executor, sessionManager);
    }

    public static DefaultMobilytics provideInstance(Provider<MobilyticsConfiguration> provider, Provider<Executor> provider2, Provider<SessionManager> provider3) {
        return new DefaultMobilytics(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultMobilytics mo10268get() {
        return provideInstance(this.configurationProvider, this.executorProvider, this.sessionManagerProvider);
    }
}
