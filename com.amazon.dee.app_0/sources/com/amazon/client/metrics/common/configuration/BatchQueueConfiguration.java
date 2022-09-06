package com.amazon.client.metrics.common.configuration;

import com.amazon.client.metrics.common.configuration.internal.IBatchQueueConfiguration;
import com.amazon.client.metrics.common.configuration.internal.NullBatchQueueConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidBatchQueueConfiguration;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
@Deprecated
/* loaded from: classes11.dex */
public class BatchQueueConfiguration implements IBatchQueueConfiguration {
    private final IBatchQueueConfiguration mDelegateBatchQueueConfiguration;

    public BatchQueueConfiguration(BatchQueueType batchQueueType, String str) throws MetricsConfigurationException {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            this.mDelegateBatchQueueConfiguration = new AndroidBatchQueueConfiguration(batchQueueType, str);
        } else {
            this.mDelegateBatchQueueConfiguration = new NullBatchQueueConfiguration();
        }
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IBatchQueueConfiguration
    public BatchQueueType getBatchQueueType() {
        return this.mDelegateBatchQueueConfiguration.getBatchQueueType();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IBatchQueueConfiguration getDelegateBatchQueueConfiguration() {
        return this.mDelegateBatchQueueConfiguration;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IBatchQueueConfiguration
    public String getDirectoryPrefix() {
        return this.mDelegateBatchQueueConfiguration.getDirectoryPrefix();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BatchQueueConfiguration(IBatchQueueConfiguration iBatchQueueConfiguration) {
        if (iBatchQueueConfiguration != null) {
            this.mDelegateBatchQueueConfiguration = iBatchQueueConfiguration;
            return;
        }
        throw new NullPointerException("BatchQueue Configuration may not be null");
    }
}
