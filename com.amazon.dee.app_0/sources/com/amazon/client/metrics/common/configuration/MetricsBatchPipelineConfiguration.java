package com.amazon.client.metrics.common.configuration;

import com.amazon.client.metrics.common.configuration.internal.NullMetricsBatchPipelineConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidMetricsBatchPipelineConfiguration;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
/* loaded from: classes11.dex */
public class MetricsBatchPipelineConfiguration implements BatchPipelineConfiguration {
    private final BatchPipelineConfiguration mDelegateBatchPipelineConfiguration;

    @Deprecated
    public MetricsBatchPipelineConfiguration(long j, long j2, int i, int i2, int i3, int i4, long j3, long j4, long j5, BatchTransmitterType batchTransmitterType) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            this.mDelegateBatchPipelineConfiguration = new AndroidMetricsBatchPipelineConfiguration(j, j2, i, i2, i3, i4, j3, j4, j5, batchTransmitterType);
        } else {
            this.mDelegateBatchPipelineConfiguration = new NullMetricsBatchPipelineConfiguration();
        }
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public BatchQueueType getBatchQueueType() {
        return this.mDelegateBatchPipelineConfiguration.getBatchQueueType();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public BatchTransmitterType getBatchTransmitterType() {
        return this.mDelegateBatchPipelineConfiguration.getBatchTransmitterType();
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public long getCheckBatchOpenTimeMillis() {
        return this.mDelegateBatchPipelineConfiguration.getCheckBatchOpenTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BatchPipelineConfiguration getDelegateBatchPipelineConfiguration() {
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
        this.mDelegateBatchPipelineConfiguration.setBatchQueueType(batchQueueType);
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public void setDirectoryPrefix(String str) {
        this.mDelegateBatchPipelineConfiguration.setDirectoryPrefix(str);
    }

    @Override // com.amazon.client.metrics.common.configuration.BatchPipelineConfiguration
    public void updateBatchPipelineConfigurationBasedOnRemoteSettings(BatchPipelineConfiguration batchPipelineConfiguration) {
    }

    public MetricsBatchPipelineConfiguration(BatchQueueType batchQueueType, String str, long j, long j2, int i, int i2, int i3, int i4, long j3, long j4, long j5, BatchTransmitterType batchTransmitterType) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            this.mDelegateBatchPipelineConfiguration = new AndroidMetricsBatchPipelineConfiguration(batchQueueType, str, j, j2, i, i2, i3, i4, j3, j4, j5, batchTransmitterType);
        } else {
            this.mDelegateBatchPipelineConfiguration = new NullMetricsBatchPipelineConfiguration();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MetricsBatchPipelineConfiguration(BatchPipelineConfiguration batchPipelineConfiguration) {
        if (batchPipelineConfiguration != null) {
            this.mDelegateBatchPipelineConfiguration = batchPipelineConfiguration;
            return;
        }
        throw new NullPointerException("Delegate BatchPipelineConfiguration may not be null");
    }
}
