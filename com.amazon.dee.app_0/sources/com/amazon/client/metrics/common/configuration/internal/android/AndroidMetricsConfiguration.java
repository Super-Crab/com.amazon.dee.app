package com.amazon.client.metrics.common.configuration.internal.android;

import com.amazon.client.metrics.common.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.common.batch.creator.internal.PriorityChannelPairConverter;
import com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration;
import com.amazon.client.metrics.common.configuration.BatchQueueConfiguration;
import com.amazon.client.metrics.common.configuration.CodecConfiguration;
import com.amazon.client.metrics.common.configuration.HttpConfiguration;
import com.amazon.client.metrics.common.configuration.MetricsConfigurationConverter;
import com.amazon.client.metrics.common.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.common.configuration.NetworkConfiguration;
import com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import java.util.Map;
/* loaded from: classes11.dex */
public class AndroidMetricsConfiguration implements IMetricsConfiguration {
    private final MetricsConfiguration mDelegateThirdPartyMetricsConfiguration;

    @Deprecated
    public AndroidMetricsConfiguration(NetworkConfiguration networkConfiguration, BatchQueueConfiguration batchQueueConfiguration, CodecConfiguration codecConfiguration, HttpConfiguration httpConfiguration, Map<PriorityChannelPair, BatchPipelineConfiguration> map) throws MetricsConfigurationException {
        try {
            this.mDelegateThirdPartyMetricsConfiguration = new MetricsConfiguration(MetricsConfigurationConverter.convertNetworkConfiguration_fromCommonToThirdParty(networkConfiguration), MetricsConfigurationConverter.convertBatchQueueConfiguration_fromCommonToThirdParty(batchQueueConfiguration), MetricsConfigurationConverter.convertCodecConfiguration_fromCommonToThirdParty(codecConfiguration), MetricsConfigurationConverter.convertHttpConfiguration_fromCommonToThirdParty(httpConfiguration), MetricsConfigurationConverter.convertBatchPipelineConfigurationMap_fromCommonToThirdParty(map));
        } catch (com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException e) {
            throw new MetricsConfigurationException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public BatchQueueConfiguration getBatchQueueConfiguration() {
        return MetricsConfigurationConverter.convertBatchQueueConfiguration_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsConfiguration.getBatchQueueConfiguration());
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public String getBatchQueueDirectoryName(PriorityChannelPair priorityChannelPair) {
        return this.mDelegateThirdPartyMetricsConfiguration.getBatchQueueDirectoryName(PriorityChannelPairConverter.convertCommonToThirdParty(priorityChannelPair));
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public CodecConfiguration getCodecConfiguration() {
        return MetricsConfigurationConverter.convertCodecConfiguration_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsConfiguration.getCodecConfiguration());
    }

    public MetricsConfiguration getDelegateThirdPartyMetricsConfiguration() {
        return this.mDelegateThirdPartyMetricsConfiguration;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public HttpConfiguration getHttpConfiguration() {
        return MetricsConfigurationConverter.convertHttpConfiguration_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsConfiguration.getHttpConfiguration());
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public NetworkConfiguration getNetworkConfiguration() {
        return MetricsConfigurationConverter.convertNetworkConfiguration_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsConfiguration.getNetworkConfiguration());
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public BatchPipelineConfiguration getPipelineConfiguration(PriorityChannelPair priorityChannelPair) {
        return MetricsConfigurationConverter.convertBatchPipelineConfiguration_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsConfiguration.getPipelineConfiguration(PriorityChannelPairConverter.convertCommonToThirdParty(priorityChannelPair)));
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration
    public void setPassThroughMode() throws MetricsConfigurationException {
        try {
            this.mDelegateThirdPartyMetricsConfiguration.setPassThroughMode();
        } catch (com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException e) {
            throw new MetricsConfigurationException(e.getMessage(), e);
        }
    }

    public AndroidMetricsConfiguration(NetworkConfiguration networkConfiguration, CodecConfiguration codecConfiguration, HttpConfiguration httpConfiguration, Map<PriorityChannelPair, BatchPipelineConfiguration> map) throws MetricsConfigurationException {
        try {
            this.mDelegateThirdPartyMetricsConfiguration = new MetricsConfiguration(MetricsConfigurationConverter.convertNetworkConfiguration_fromCommonToThirdParty(networkConfiguration), MetricsConfigurationConverter.convertCodecConfiguration_fromCommonToThirdParty(codecConfiguration), MetricsConfigurationConverter.convertHttpConfiguration_fromCommonToThirdParty(httpConfiguration), MetricsConfigurationConverter.convertBatchPipelineConfigurationMap_fromCommonToThirdParty(map));
        } catch (com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException e) {
            throw new MetricsConfigurationException(e.getMessage(), e);
        }
    }

    public AndroidMetricsConfiguration(MetricsConfiguration metricsConfiguration) {
        if (metricsConfiguration != null) {
            this.mDelegateThirdPartyMetricsConfiguration = metricsConfiguration;
            return;
        }
        throw new NullPointerException("Delegate MetricsConfiguration may not be null");
    }
}
