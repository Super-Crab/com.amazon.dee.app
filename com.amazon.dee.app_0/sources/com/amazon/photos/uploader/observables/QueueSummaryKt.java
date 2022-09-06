package com.amazon.photos.uploader.observables;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: QueueSummary.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"hasPendingRequests", "", "Lcom/amazon/photos/uploader/observables/QueueSummary;", "AndroidPhotosUploader_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class QueueSummaryKt {
    public static final boolean hasPendingRequests(@NotNull QueueSummary hasPendingRequests) {
        Intrinsics.checkParameterIsNotNull(hasPendingRequests, "$this$hasPendingRequests");
        return hasPendingRequests.getBlockedRequestCount() > 0 || hasPendingRequests.getQueuedRequestCount() > 0 || hasPendingRequests.getRunningRequestCount() > 0;
    }
}
