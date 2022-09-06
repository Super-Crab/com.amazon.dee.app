package com.amazon.client.metrics.common.configuration;
/* loaded from: classes11.dex */
public interface BatchPipelineConfiguration {
    BatchQueueType getBatchQueueType();

    BatchTransmitterType getBatchTransmitterType();

    long getCheckBatchOpenTimeMillis();

    String getDirectoryPrefix();

    long getExpiryTimeMillis();

    long getMaxBatchOpenTimeMillis();

    long getMaxBatchQueueCapacityBytes();

    long getMaxBatchQueueEntries();

    long getMaxNumEntriesPerBatch();

    long getMaxSizePerBatchBytes();

    long getPurgePeriodMillis();

    long getTransmissionPeriodMillis();

    @Deprecated
    void setBatchQueueType(BatchQueueType batchQueueType);

    void setDirectoryPrefix(String str);

    void updateBatchPipelineConfigurationBasedOnRemoteSettings(BatchPipelineConfiguration batchPipelineConfiguration);
}
