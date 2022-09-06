package com.amazon.client.metrics.common.configuration.internal;

import com.amazon.client.metrics.common.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration;
import com.amazon.client.metrics.common.configuration.BatchQueueConfiguration;
import com.amazon.client.metrics.common.configuration.CodecConfiguration;
import com.amazon.client.metrics.common.configuration.HttpConfiguration;
import com.amazon.client.metrics.common.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.common.configuration.NetworkConfiguration;
/* loaded from: classes11.dex */
public class NullMetricsConfiguration implements IMetricsConfiguration {
    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public BatchQueueConfiguration getBatchQueueConfiguration() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public String getBatchQueueDirectoryName(PriorityChannelPair priorityChannelPair) {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public CodecConfiguration getCodecConfiguration() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public HttpConfiguration getHttpConfiguration() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public NetworkConfiguration getNetworkConfiguration() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public BatchPipelineConfiguration getPipelineConfiguration(PriorityChannelPair priorityChannelPair) {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public void setPassThroughMode() throws MetricsConfigurationException {
    }
}
