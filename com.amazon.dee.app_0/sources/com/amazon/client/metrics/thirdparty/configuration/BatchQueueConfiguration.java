package com.amazon.client.metrics.thirdparty.configuration;
@Deprecated
/* loaded from: classes11.dex */
public class BatchQueueConfiguration {
    private BatchQueueType mBatchQueueType;
    private String mDirectoryPrefix;

    public BatchQueueConfiguration(BatchQueueType batchQueueType, String str) throws MetricsConfigurationException {
        if (batchQueueType != null) {
            if (str != null) {
                this.mBatchQueueType = batchQueueType;
                this.mDirectoryPrefix = str;
                return;
            }
            throw new MetricsConfigurationException("directoryPrefix is null in configuration");
        }
        throw new MetricsConfigurationException("batchQueueType is null in configuration");
    }

    public BatchQueueType getBatchQueueType() {
        return this.mBatchQueueType;
    }

    public String getDirectoryPrefix() {
        return this.mDirectoryPrefix;
    }
}
