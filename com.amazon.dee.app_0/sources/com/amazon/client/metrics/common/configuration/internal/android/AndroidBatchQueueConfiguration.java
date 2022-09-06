package com.amazon.client.metrics.common.configuration.internal.android;

import com.amazon.client.metrics.common.configuration.BatchQueueType;
import com.amazon.client.metrics.common.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.common.configuration.internal.IBatchQueueConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.BatchQueueConfiguration;
/* loaded from: classes11.dex */
public class AndroidBatchQueueConfiguration implements IBatchQueueConfiguration {
    private final BatchQueueConfiguration mDelegateBatchQueueConfiguration;

    public AndroidBatchQueueConfiguration(BatchQueueType batchQueueType, String str) throws MetricsConfigurationException {
        try {
            this.mDelegateBatchQueueConfiguration = new BatchQueueConfiguration(com.amazon.client.metrics.thirdparty.configuration.BatchQueueType.valueOf(batchQueueType.name()), str);
        } catch (com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException e) {
            throw new MetricsConfigurationException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IBatchQueueConfiguration
    public BatchQueueType getBatchQueueType() {
        return BatchQueueType.valueOf(this.mDelegateBatchQueueConfiguration.getBatchQueueType().name());
    }

    public BatchQueueConfiguration getDelegateBatchQueueConfiguration() {
        return this.mDelegateBatchQueueConfiguration;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IBatchQueueConfiguration
    public String getDirectoryPrefix() {
        return this.mDelegateBatchQueueConfiguration.getDirectoryPrefix();
    }

    public AndroidBatchQueueConfiguration(BatchQueueConfiguration batchQueueConfiguration) {
        if (batchQueueConfiguration != null) {
            this.mDelegateBatchQueueConfiguration = batchQueueConfiguration;
            return;
        }
        throw new NullPointerException("Delegate BatchQueueConfiguration may note be null");
    }
}
