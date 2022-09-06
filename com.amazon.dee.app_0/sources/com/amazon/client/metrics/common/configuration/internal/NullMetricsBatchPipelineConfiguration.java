package com.amazon.client.metrics.common.configuration.internal;

import com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration;
import com.amazon.client.metrics.common.configuration.BatchQueueType;
import com.amazon.client.metrics.common.configuration.BatchTransmitterType;
/* loaded from: classes11.dex */
public class NullMetricsBatchPipelineConfiguration implements BatchPipelineConfiguration {
    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public BatchQueueType getBatchQueueType() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public BatchTransmitterType getBatchTransmitterType() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getCheckBatchOpenTimeMillis() {
        return 0L;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public String getDirectoryPrefix() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getExpiryTimeMillis() {
        return 0L;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getMaxBatchOpenTimeMillis() {
        return 0L;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getMaxBatchQueueCapacityBytes() {
        return 0L;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getMaxBatchQueueEntries() {
        return 0L;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getMaxNumEntriesPerBatch() {
        return 0L;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getMaxSizePerBatchBytes() {
        return 0L;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getPurgePeriodMillis() {
        return 0L;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getTransmissionPeriodMillis() {
        return 0L;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public void setBatchQueueType(BatchQueueType batchQueueType) {
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public void setDirectoryPrefix(String str) {
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public void updateBatchPipelineConfigurationBasedOnRemoteSettings(BatchPipelineConfiguration batchPipelineConfiguration) {
    }
}
