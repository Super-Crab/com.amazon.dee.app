package com.amazon.photos.discovery.dedupe;

import androidx.annotation.WorkerThread;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Deduplicator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/Deduplicator;", "", "deduplicate", "Lcom/amazon/photos/discovery/dedupe/DeduplicatorResult;", "request", "Lcom/amazon/photos/discovery/dedupe/DeduplicationRequest;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface Deduplicator {
    @WorkerThread
    @NotNull
    DeduplicatorResult deduplicate(@NotNull DeduplicationRequest deduplicationRequest) throws InterruptedException, RetryDedupeException, NoRetryDedupeException;
}
