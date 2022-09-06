package com.amazon.client.metrics.common.configuration.internal;

import com.amazon.client.metrics.common.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration;
import com.amazon.client.metrics.common.configuration.BatchQueueConfiguration;
import com.amazon.client.metrics.common.configuration.CodecConfiguration;
import com.amazon.client.metrics.common.configuration.HttpConfiguration;
import com.amazon.client.metrics.common.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.common.configuration.NetworkConfiguration;
/* loaded from: classes11.dex */
public interface IMetricsConfiguration {
    @Deprecated
    BatchQueueConfiguration getBatchQueueConfiguration();

    String getBatchQueueDirectoryName(PriorityChannelPair priorityChannelPair);

    CodecConfiguration getCodecConfiguration();

    HttpConfiguration getHttpConfiguration();

    NetworkConfiguration getNetworkConfiguration();

    BatchPipelineConfiguration getPipelineConfiguration(PriorityChannelPair priorityChannelPair);

    void setPassThroughMode() throws MetricsConfigurationException;
}
