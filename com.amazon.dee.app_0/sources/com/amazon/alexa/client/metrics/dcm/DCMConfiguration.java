package com.amazon.alexa.client.metrics.dcm;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Stage;
import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.Priority;
import com.amazon.client.metrics.common.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.common.configuration.BatchQueueType;
import com.amazon.client.metrics.common.configuration.BatchTransmitterType;
import com.amazon.client.metrics.common.configuration.CodecConfiguration;
import com.amazon.client.metrics.common.configuration.CodecType;
import com.amazon.client.metrics.common.configuration.HttpConfiguration;
import com.amazon.client.metrics.common.configuration.HttpRequestSignerType;
import com.amazon.client.metrics.common.configuration.MetricsBatchPipelineConfiguration;
import com.amazon.client.metrics.common.configuration.MetricsConfiguration;
import com.amazon.client.metrics.common.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.common.configuration.MetricsNetworkConfiguration;
import com.amazon.client.metrics.common.configuration.NetworkType;
import com.amazon.client.metrics.common.configuration.TransportType;
import com.amazon.devicesetupservice.scap.v1.BleConnectionPriority;
import dagger.Lazy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public class DCMConfiguration {
    private static final int MAX_SIZE = 65536;
    private final Lazy<ClientConfiguration> config;

    /* loaded from: classes6.dex */
    private static class ConnectServiceHttpConfiguration extends HttpConfiguration {
        public ConnectServiceHttpConfiguration(ClientConfiguration clientConfiguration) throws MetricsConfigurationException {
            super(HttpRequestSignerType.OAUTH, Stage.PROD.equals(clientConfiguration.getStage()) ? "https://device-metrics-us.amazon.com:443" : "https://dp-mont.integ.amazon.com:443", "https://device-metrics-us-2.amazon.com:443");
        }
    }

    public DCMConfiguration(Lazy<ClientConfiguration> lazy) {
        this.config = lazy;
    }

    public String getDeviceType() {
        return this.config.mo358get().getMetricsDeviceType();
    }

    public MetricsConfiguration getMetricsConfiguration() {
        MetricsNetworkConfiguration metricsNetworkConfiguration;
        CodecConfiguration codecConfiguration;
        try {
            HashSet hashSet = new HashSet();
            hashSet.add(NetworkType.WIFI);
            hashSet.add(NetworkType.ETHERNET);
            metricsNetworkConfiguration = new MetricsNetworkConfiguration(TransportType.HTTP, hashSet);
            codecConfiguration = new CodecConfiguration(CodecType.PROTOCOL_BUFFERS, "1.0");
        } catch (MetricsConfigurationException e) {
            e = e;
        }
        try {
            ConnectServiceHttpConfiguration connectServiceHttpConfiguration = new ConnectServiceHttpConfiguration(this.config.mo358get());
            HashMap hashMap = new HashMap();
            MetricsBatchPipelineConfiguration metricsBatchPipelineConfiguration = new MetricsBatchPipelineConfiguration(BatchQueueType.NON_VOLATILE, DriveModeVoxUiConstants.NORMAL, TimeUnit.MILLISECONDS.convert(5L, TimeUnit.MINUTES), TimeUnit.MILLISECONDS.convert(3L, TimeUnit.MINUTES), 65536, 65536, 5242880, 65536, TimeUnit.MILLISECONDS.convert(7L, TimeUnit.DAYS), TimeUnit.MILLISECONDS.convert(1L, TimeUnit.DAYS), TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES), BatchTransmitterType.PERIODIC);
            MetricsBatchPipelineConfiguration metricsBatchPipelineConfiguration2 = new MetricsBatchPipelineConfiguration(BatchQueueType.NON_VOLATILE, BleConnectionPriority.HIGH, TimeUnit.MILLISECONDS.convert(1L, TimeUnit.SECONDS), TimeUnit.MILLISECONDS.convert(1L, TimeUnit.SECONDS) / 2, 65536, 65536, 1048576, 65536, TimeUnit.MILLISECONDS.convert(7L, TimeUnit.DAYS), TimeUnit.MILLISECONDS.convert(1L, TimeUnit.DAYS), TimeUnit.MILLISECONDS.convert(5L, TimeUnit.MINUTES), BatchTransmitterType.URGENT);
            hashMap.put(new PriorityChannelPair(Priority.NORMAL, Channel.ANONYMOUS), metricsBatchPipelineConfiguration);
            hashMap.put(new PriorityChannelPair(Priority.HIGH, Channel.ANONYMOUS), metricsBatchPipelineConfiguration2);
            return new MetricsConfiguration(metricsNetworkConfiguration, codecConfiguration, connectServiceHttpConfiguration, hashMap);
        } catch (MetricsConfigurationException e2) {
            e = e2;
            throw new RuntimeException(e);
        }
    }
}
