package com.amazon.alexa.mobilytics.executor;

import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.configuration.ConfigManager;
import com.amazon.alexa.mobilytics.configuration.EndpointManager;
import com.amazon.alexa.mobilytics.configuration.RecordChecker;
import com.amazon.alexa.mobilytics.connector.ConnectorManager;
import com.amazon.alexa.mobilytics.lifecycle.Lifecycle;
import com.amazon.alexa.mobilytics.session.SessionManager;
import com.amazon.alexa.mobilytics.timeline.TimelineDataPublisher;
import com.amazon.alexa.mobilytics.timeline.TimelineManager;
import com.amazon.alexa.mobilytics.timeline.TimelineStorage;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultExecutor_Factory implements Factory<DefaultExecutor> {
    private final Provider<ConfigManager> configManagerProvider;
    private final Provider<MobilyticsConfiguration> configurationProvider;
    private final Provider<ConnectorManager> connectorManagerProvider;
    private final Provider<EndpointManager> endpointManagerProvider;
    private final Provider<Lifecycle> lifecycleProvider;
    private final Provider<RecordChecker> recordCheckerProvider;
    private final Provider<SessionManager> sessionManagerProvider;
    private final Provider<TimelineDataPublisher> timelineDataPublisherProvider;
    private final Provider<TimelineManager> timelineManagerProvider;
    private final Provider<TimelineStorage> timelineStorageProvider;

    public DefaultExecutor_Factory(Provider<MobilyticsConfiguration> provider, Provider<ConfigManager> provider2, Provider<Lifecycle> provider3, Provider<SessionManager> provider4, Provider<TimelineManager> provider5, Provider<TimelineStorage> provider6, Provider<TimelineDataPublisher> provider7, Provider<RecordChecker> provider8, Provider<ConnectorManager> provider9, Provider<EndpointManager> provider10) {
        this.configurationProvider = provider;
        this.configManagerProvider = provider2;
        this.lifecycleProvider = provider3;
        this.sessionManagerProvider = provider4;
        this.timelineManagerProvider = provider5;
        this.timelineStorageProvider = provider6;
        this.timelineDataPublisherProvider = provider7;
        this.recordCheckerProvider = provider8;
        this.connectorManagerProvider = provider9;
        this.endpointManagerProvider = provider10;
    }

    public static DefaultExecutor_Factory create(Provider<MobilyticsConfiguration> provider, Provider<ConfigManager> provider2, Provider<Lifecycle> provider3, Provider<SessionManager> provider4, Provider<TimelineManager> provider5, Provider<TimelineStorage> provider6, Provider<TimelineDataPublisher> provider7, Provider<RecordChecker> provider8, Provider<ConnectorManager> provider9, Provider<EndpointManager> provider10) {
        return new DefaultExecutor_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static DefaultExecutor newDefaultExecutor(MobilyticsConfiguration mobilyticsConfiguration, ConfigManager configManager, Lifecycle lifecycle, SessionManager sessionManager, TimelineManager timelineManager, TimelineStorage timelineStorage, TimelineDataPublisher timelineDataPublisher, RecordChecker recordChecker, ConnectorManager connectorManager, EndpointManager endpointManager) {
        return new DefaultExecutor(mobilyticsConfiguration, configManager, lifecycle, sessionManager, timelineManager, timelineStorage, timelineDataPublisher, recordChecker, connectorManager, endpointManager);
    }

    public static DefaultExecutor provideInstance(Provider<MobilyticsConfiguration> provider, Provider<ConfigManager> provider2, Provider<Lifecycle> provider3, Provider<SessionManager> provider4, Provider<TimelineManager> provider5, Provider<TimelineStorage> provider6, Provider<TimelineDataPublisher> provider7, Provider<RecordChecker> provider8, Provider<ConnectorManager> provider9, Provider<EndpointManager> provider10) {
        return new DefaultExecutor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultExecutor mo10268get() {
        return provideInstance(this.configurationProvider, this.configManagerProvider, this.lifecycleProvider, this.sessionManagerProvider, this.timelineManagerProvider, this.timelineStorageProvider, this.timelineDataPublisherProvider, this.recordCheckerProvider, this.connectorManagerProvider, this.endpointManagerProvider);
    }
}
