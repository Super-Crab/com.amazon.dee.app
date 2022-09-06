package com.amazon.client.metrics.common.configuration;

import com.amazon.client.metrics.common.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration;
import com.amazon.client.metrics.common.configuration.internal.NullMetricsConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidMetricsConfiguration;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.Map;
/* loaded from: classes11.dex */
public class MetricsConfiguration implements IMetricsConfiguration {
    private final IMetricsConfiguration mDelegateMetricsConfiguration;

    @Deprecated
    public MetricsConfiguration(NetworkConfiguration networkConfiguration, BatchQueueConfiguration batchQueueConfiguration, CodecConfiguration codecConfiguration, HttpConfiguration httpConfiguration, Map<PriorityChannelPair, BatchPipelineConfiguration> map) throws MetricsConfigurationException {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            this.mDelegateMetricsConfiguration = new AndroidMetricsConfiguration(networkConfiguration, batchQueueConfiguration, codecConfiguration, httpConfiguration, map);
        } else {
            this.mDelegateMetricsConfiguration = new NullMetricsConfiguration();
        }
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public BatchQueueConfiguration getBatchQueueConfiguration() {
        return this.mDelegateMetricsConfiguration.getBatchQueueConfiguration();
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public String getBatchQueueDirectoryName(PriorityChannelPair priorityChannelPair) {
        return this.mDelegateMetricsConfiguration.getBatchQueueDirectoryName(priorityChannelPair);
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public CodecConfiguration getCodecConfiguration() {
        return this.mDelegateMetricsConfiguration.getCodecConfiguration();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IMetricsConfiguration getDelegateMetricsConfiguration() {
        return this.mDelegateMetricsConfiguration;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public HttpConfiguration getHttpConfiguration() {
        return this.mDelegateMetricsConfiguration.getHttpConfiguration();
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public NetworkConfiguration getNetworkConfiguration() {
        return this.mDelegateMetricsConfiguration.getNetworkConfiguration();
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public BatchPipelineConfiguration getPipelineConfiguration(PriorityChannelPair priorityChannelPair) {
        return this.mDelegateMetricsConfiguration.getPipelineConfiguration(priorityChannelPair);
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public void setPassThroughMode() throws MetricsConfigurationException {
        this.mDelegateMetricsConfiguration.setPassThroughMode();
    }

    public MetricsConfiguration(NetworkConfiguration networkConfiguration, CodecConfiguration codecConfiguration, HttpConfiguration httpConfiguration, Map<PriorityChannelPair, BatchPipelineConfiguration> map) throws MetricsConfigurationException {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            this.mDelegateMetricsConfiguration = new AndroidMetricsConfiguration(networkConfiguration, codecConfiguration, httpConfiguration, map);
        } else {
            this.mDelegateMetricsConfiguration = new NullMetricsConfiguration();
        }
    }

    MetricsConfiguration(IMetricsConfiguration iMetricsConfiguration) {
        if (iMetricsConfiguration != null) {
            this.mDelegateMetricsConfiguration = iMetricsConfiguration;
            return;
        }
        throw new NullPointerException("Delegate MetricsConfiguration may not be null");
    }
}
