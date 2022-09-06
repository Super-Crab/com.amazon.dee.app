package com.amazon.alexa.mobilytics.connector;

import com.amazon.alexa.mobilytics.configuration.EndpointManager;
import com.amazon.alexa.mobilytics.connector.ConnectorExecutor;
import com.amazon.alexa.mobilytics.connector.DCMConnector;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class ConnectorManager_Factory implements Factory<ConnectorManager> {
    private final Provider<ConnectorExecutor.Factory> connectorExecutorFactoryProvider;
    private final Provider<DCMConnector.Factory> dcmConnectorFactoryProvider;
    private final Provider<DefaultKinesisConnector.Factory> defaultKinesisConnectorFactoryProvider;
    private final Provider<EndpointManager> endpointManagerProvider;

    public ConnectorManager_Factory(Provider<DefaultKinesisConnector.Factory> provider, Provider<DCMConnector.Factory> provider2, Provider<EndpointManager> provider3, Provider<ConnectorExecutor.Factory> provider4) {
        this.defaultKinesisConnectorFactoryProvider = provider;
        this.dcmConnectorFactoryProvider = provider2;
        this.endpointManagerProvider = provider3;
        this.connectorExecutorFactoryProvider = provider4;
    }

    public static ConnectorManager_Factory create(Provider<DefaultKinesisConnector.Factory> provider, Provider<DCMConnector.Factory> provider2, Provider<EndpointManager> provider3, Provider<ConnectorExecutor.Factory> provider4) {
        return new ConnectorManager_Factory(provider, provider2, provider3, provider4);
    }

    public static ConnectorManager newConnectorManager(DefaultKinesisConnector.Factory factory, DCMConnector.Factory factory2, EndpointManager endpointManager, ConnectorExecutor.Factory factory3) {
        return new ConnectorManager(factory, factory2, endpointManager, factory3);
    }

    public static ConnectorManager provideInstance(Provider<DefaultKinesisConnector.Factory> provider, Provider<DCMConnector.Factory> provider2, Provider<EndpointManager> provider3, Provider<ConnectorExecutor.Factory> provider4) {
        return new ConnectorManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConnectorManager mo10268get() {
        return provideInstance(this.defaultKinesisConnectorFactoryProvider, this.dcmConnectorFactoryProvider, this.endpointManagerProvider, this.connectorExecutorFactoryProvider);
    }
}
