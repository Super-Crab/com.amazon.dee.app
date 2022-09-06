package com.amazon.client.metrics.common.configuration.internal;

import com.amazon.client.metrics.common.configuration.BatchQueueType;
/* loaded from: classes11.dex */
public class NullBatchQueueConfiguration implements IBatchQueueConfiguration {
    @Override // com.amazon.client.metrics.common.configuration.internal.IBatchQueueConfiguration
    public BatchQueueType getBatchQueueType() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IBatchQueueConfiguration
    public String getDirectoryPrefix() {
        return null;
    }
}
