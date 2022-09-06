package com.amazon.photos.uploader.blockers;

import com.amazon.photos.uploader.Queue;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: QueueBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/uploader/blockers/QueueBlockerEvaluator;", "", "getBlocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "queue", "Lcom/amazon/photos/uploader/Queue;", "queryBlocker", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface QueueBlockerEvaluator {
    @Nullable
    Blocker getBlocker(@NotNull Queue queue);

    @Nullable
    Blocker queryBlocker(@NotNull Queue queue);
}
