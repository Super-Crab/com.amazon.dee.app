package com.amazon.photos.uploader.observables;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadSummary.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"hasUploadFinished", "", "Lcom/amazon/photos/uploader/observables/UploadSummary;", "AndroidPhotosUploader_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadSummaryKt {
    public static final boolean hasUploadFinished(@NotNull UploadSummary hasUploadFinished) {
        Intrinsics.checkParameterIsNotNull(hasUploadFinished, "$this$hasUploadFinished");
        if (!hasUploadFinished.getGlobalBlockers().isEmpty()) {
            return false;
        }
        for (QueueSummary queueSummary : hasUploadFinished.getQueueSummaries()) {
            if (QueueSummaryKt.hasPendingRequests(queueSummary)) {
                return false;
            }
        }
        return true;
    }
}
