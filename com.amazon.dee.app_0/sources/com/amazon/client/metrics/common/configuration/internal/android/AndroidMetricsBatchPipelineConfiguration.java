package com.amazon.client.metrics.common.configuration.internal.android;

import com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration;
import com.amazon.client.metrics.common.configuration.BatchQueueType;
import com.amazon.client.metrics.common.configuration.BatchTransmitterType;
import com.amazon.client.metrics.thirdparty.configuration.MetricsBatchPipelineConfiguration;
/* loaded from: classes11.dex */
public class AndroidMetricsBatchPipelineConfiguration implements BatchPipelineConfiguration {
    private final com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration mDelegateBatchPipelineConfiguration;

    @Deprecated
    public AndroidMetricsBatchPipelineConfiguration(long j, long j2, int i, int i2, int i3, int i4, long j3, long j4, long j5, BatchTransmitterType batchTransmitterType) {
        this.mDelegateBatchPipelineConfiguration = new MetricsBatchPipelineConfiguration(j, j2, i, i2, i3, i4, j3, j4, j5, com.amazon.client.metrics.thirdparty.configuration.BatchTransmitterType.valueOf(batchTransmitterType.name()));
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public BatchQueueType getBatchQueueType() {
        return BatchQueueType.valueOf(this.mDelegateBatchPipelineConfiguration.getBatchQueueType().name());
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public BatchTransmitterType getBatchTransmitterType() {
        return BatchTransmitterType.valueOf(this.mDelegateBatchPipelineConfiguration.getBatchTransmitterType().name());
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getCheckBatchOpenTimeMillis() {
        return this.mDelegateBatchPipelineConfiguration.getCheckBatchOpenTimeMillis();
    }

    public com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration getDelegateBatchPipelineConfiguration() {
        return this.mDelegateBatchPipelineConfiguration;
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public String getDirectoryPrefix() {
        return this.mDelegateBatchPipelineConfiguration.getDirectoryPrefix();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getExpiryTimeMillis() {
        return this.mDelegateBatchPipelineConfiguration.getExpiryTimeMillis();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getMaxBatchOpenTimeMillis() {
        return this.mDelegateBatchPipelineConfiguration.getMaxBatchOpenTimeMillis();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getMaxBatchQueueCapacityBytes() {
        return this.mDelegateBatchPipelineConfiguration.getMaxBatchQueueCapacityBytes();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getMaxBatchQueueEntries() {
        return this.mDelegateBatchPipelineConfiguration.getMaxBatchQueueEntries();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getMaxNumEntriesPerBatch() {
        return this.mDelegateBatchPipelineConfiguration.getMaxNumEntriesPerBatch();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getMaxSizePerBatchBytes() {
        return this.mDelegateBatchPipelineConfiguration.getMaxSizePerBatchBytes();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getPurgePeriodMillis() {
        return this.mDelegateBatchPipelineConfiguration.getPurgePeriodMillis();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getTransmissionPeriodMillis() {
        return this.mDelegateBatchPipelineConfiguration.getTransmissionPeriodMillis();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public void setBatchQueueType(BatchQueueType batchQueueType) {
        this.mDelegateBatchPipelineConfiguration.setBatchQueueType(com.amazon.client.metrics.thirdparty.configuration.BatchQueueType.valueOf(batchQueueType.name()));
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public void setDirectoryPrefix(String str) {
        this.mDelegateBatchPipelineConfiguration.setDirectoryPrefix(str);
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public void updateBatchPipelineConfigurationBasedOnRemoteSettings(BatchPipelineConfiguration batchPipelineConfiguration) {
    }

    public AndroidMetricsBatchPipelineConfiguration(BatchQueueType batchQueueType, String str, long j, long j2, int i, int i2, int i3, int i4, long j3, long j4, long j5, BatchTransmitterType batchTransmitterType) {
        this.mDelegateBatchPipelineConfiguration = new MetricsBatchPipelineConfiguration(com.amazon.client.metrics.thirdparty.configuration.BatchQueueType.valueOf(batchQueueType.name()), str, j, j2, i, i2, i3, i4, j3, j4, j5, com.amazon.client.metrics.thirdparty.configuration.BatchTransmitterType.valueOf(batchTransmitterType.name()));
    }

    public AndroidMetricsBatchPipelineConfiguration(com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration batchPipelineConfiguration) {
        if (batchPipelineConfiguration != null) {
            this.mDelegateBatchPipelineConfiguration = batchPipelineConfiguration;
            return;
        }
        throw new NullPointerException("Batch Pipeline Configuration may not be null");
    }
}
