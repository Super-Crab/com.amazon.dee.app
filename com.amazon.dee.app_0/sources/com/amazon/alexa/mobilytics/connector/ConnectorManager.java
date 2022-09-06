package com.amazon.alexa.mobilytics.connector;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.configuration.Endpoint;
import com.amazon.alexa.mobilytics.configuration.EndpointManager;
import com.amazon.alexa.mobilytics.configuration.KinesisEndpoint;
import com.amazon.alexa.mobilytics.configuration.Region;
import com.amazon.alexa.mobilytics.connector.ConnectorExecutor;
import com.amazon.alexa.mobilytics.connector.DCMConnector;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazonaws.regions.Regions;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class ConnectorManager {
    private static final String TAG = Log.tag(ConnectorManager.class);
    private final ConnectorExecutor.Factory connectorExecutorFactory;
    private final DCMConnector.Factory dcmConnectorFactory;
    private final DefaultKinesisConnector.Factory defaultKinesisConnectorFactory;
    private final EndpointManager endpointManager;
    private List<ConnectorExecutor> defaultConnectorExecutors = new ArrayList();
    private List<ConnectorExecutor> connectorExecutors = new ArrayList();

    @Inject
    public ConnectorManager(@NonNull DefaultKinesisConnector.Factory factory, @NonNull DCMConnector.Factory factory2, @NonNull EndpointManager endpointManager, @NonNull ConnectorExecutor.Factory factory3) {
        this.defaultKinesisConnectorFactory = (DefaultKinesisConnector.Factory) Preconditions.checkNotNull(factory);
        this.dcmConnectorFactory = (DCMConnector.Factory) Preconditions.checkNotNull(factory2);
        this.endpointManager = (EndpointManager) Preconditions.checkNotNull(endpointManager);
        this.connectorExecutorFactory = (ConnectorExecutor.Factory) Preconditions.checkNotNull(factory3);
    }

    private void createDcmConnectorExecutors() {
        for (Endpoint endpoint : this.endpointManager.dcmEndpoints()) {
            this.connectorExecutors.add(this.connectorExecutorFactory.create(this.dcmConnectorFactory.create(endpoint, endpoint.tag() + "DcmConnector")));
        }
    }

    private void createDefaultConnectorExecutors(@NonNull List<MobilyticsConnector> list) {
        for (MobilyticsConnector mobilyticsConnector : list) {
            this.defaultConnectorExecutors.add(this.connectorExecutorFactory.create(mobilyticsConnector));
        }
    }

    private void createKinesisConnectorExecutors() {
        for (Map<Regions, Endpoint> map : this.endpointManager.endpoints()) {
            this.connectorExecutors.add(this.connectorExecutorFactory.create(this.defaultKinesisConnectorFactory.create(map, ((KinesisEndpoint) map.get(Region.DEFAULT.awsRegion())).tag() + "KinesisConnector")));
        }
    }

    private void generateConnectorExecutors() {
        this.connectorExecutors.clear();
        createDcmConnectorExecutors();
        createKinesisConnectorExecutors();
        this.connectorExecutors.addAll(this.defaultConnectorExecutors);
    }

    public List<ConnectorExecutor> connectorExecutors() {
        generateConnectorExecutors();
        return Collections.unmodifiableList(this.connectorExecutors);
    }

    public void initialize(@NonNull MobilyticsConfiguration mobilyticsConfiguration) {
        ArrayList arrayList = new ArrayList();
        if (mobilyticsConfiguration.connectors() != null) {
            for (MobilyticsConnector mobilyticsConnector : mobilyticsConfiguration.connectors()) {
                if (mobilyticsConnector != null) {
                    arrayList.add(mobilyticsConnector);
                }
            }
        }
        createDefaultConnectorExecutors(arrayList);
    }
}
