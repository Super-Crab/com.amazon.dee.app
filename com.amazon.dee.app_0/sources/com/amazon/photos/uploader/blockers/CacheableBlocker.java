package com.amazon.photos.uploader.blockers;

import androidx.annotation.WorkerThread;
import kotlin.Metadata;
/* compiled from: CacheableBlocker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'¨\u0006\u0004"}, d2 = {"Lcom/amazon/photos/uploader/blockers/CacheableBlocker;", "", "invalidateCacheBlocking", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface CacheableBlocker {
    @WorkerThread
    void invalidateCacheBlocking();
}