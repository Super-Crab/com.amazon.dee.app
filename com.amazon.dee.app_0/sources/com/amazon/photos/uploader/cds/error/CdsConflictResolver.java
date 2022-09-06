package com.amazon.photos.uploader.cds.error;

import androidx.annotation.WorkerThread;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: CdsConflictResolver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH'¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/cds/error/CdsConflictResolver;", "", "resolveConflict", "Lcom/amazon/photos/uploader/UploadResponse;", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "conflictNodeId", "", "uploadException", "Lcom/amazon/photos/uploader/cds/error/UploadException;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface CdsConflictResolver {
    @WorkerThread
    @NotNull
    UploadResponse resolveConflict(@NotNull UploadRequest uploadRequest, @NotNull String str, @NotNull UploadException uploadException);
}
