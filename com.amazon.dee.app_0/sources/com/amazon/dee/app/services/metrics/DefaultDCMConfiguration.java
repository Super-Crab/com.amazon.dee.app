package com.amazon.dee.app.services.metrics;

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
import com.amazon.client.metrics.common.transport.OAuthHelper;
import com.amazon.dee.app.BuildConfig;
import com.amazon.devicesetupservice.scap.v1.BleConnectionPriority;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class DefaultDCMConfiguration implements DCMConfiguration {
    private static final int MAX_SIZE = 65536;

    /* loaded from: classes12.dex */
    private static class ConnectServiceHttpConfiguration extends HttpConfiguration {
        public ConnectServiceHttpConfiguration() throws MetricsConfigurationException {
            super(HttpRequestSignerType.OAUTH, BuildConfig.IS_PROD_ENVIRONMENT ? "https://device-metrics-us.amazon.com:443" : "https://dp-mont.integ.amazon.com:443", "https://device-metrics-us-2.amazon.com:443");
        }
    }

    @Override // com.amazon.dee.app.services.metrics.DCMConfiguration
    public String getDeviceType() {
        return "A2TF17PFR55MTB";
    }

    public MetricsConfiguration getMetricsConfiguration() {
        try {
            HashSet hashSet = new HashSet();
            hashSet.add(NetworkType.WIFI);
            hashSet.add(NetworkType.ETHERNET);
            MetricsNetworkConfiguration metricsNetworkConfiguration = new MetricsNetworkConfiguration(TransportType.HTTP, hashSet);
            CodecConfiguration codecConfiguration = new CodecConfiguration(CodecType.PROTOCOL_BUFFERS, "1.0");
            ConnectServiceHttpConfiguration connectServiceHttpConfiguration = new ConnectServiceHttpConfiguration();
            HashMap hashMap = new HashMap();
            MetricsBatchPipelineConfiguration metricsBatchPipelineConfiguration = new MetricsBatchPipelineConfiguration(BatchQueueType.NON_VOLATILE, DriveModeVoxUiConstants.NORMAL, TimeUnit.MILLISECONDS.convert(5L, TimeUnit.MINUTES), TimeUnit.MILLISECONDS.convert(3L, TimeUnit.MINUTES), 65536, 65536, 5242880, 65536, TimeUnit.MILLISECONDS.convert(7L, TimeUnit.DAYS), TimeUnit.MILLISECONDS.convert(1L, TimeUnit.DAYS), TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES), BatchTransmitterType.PERIODIC);
            MetricsBatchPipelineConfiguration metricsBatchPipelineConfiguration2 = new MetricsBatchPipelineConfiguration(BatchQueueType.NON_VOLATILE, BleConnectionPriority.HIGH, TimeUnit.MILLISECONDS.convert(1L, TimeUnit.SECONDS), TimeUnit.MILLISECONDS.convert(1L, TimeUnit.SECONDS) / 2, 65536, 65536, 1048576, 65536, TimeUnit.MILLISECONDS.convert(7L, TimeUnit.DAYS), TimeUnit.MILLISECONDS.convert(1L, TimeUnit.DAYS), TimeUnit.MILLISECONDS.convert(5L, TimeUnit.MINUTES), BatchTransmitterType.URGENT);
            hashMap.put(new PriorityChannelPair(Priority.NORMAL, Channel.ANONYMOUS), metricsBatchPipelineConfiguration);
            hashMap.put(new PriorityChannelPair(Priority.HIGH, Channel.ANONYMOUS), metricsBatchPipelineConfiguration2);
            return new MetricsConfiguration(metricsNetworkConfiguration, codecConfiguration, connectServiceHttpConfiguration, hashMap);
        } catch (MetricsConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.amazon.dee.app.services.metrics.DCMConfiguration
    public OAuthHelper getOAuthHelper() {
        return null;
    }
}
