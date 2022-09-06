package com.amazon.photos.uploader;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Uploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/uploader/Uploader;", "", "cancelUpload", "", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "clearCompleted", "startUpload", "completer", "Lcom/amazon/photos/uploader/UploadCompleter;", "progressListener", "Lcom/amazon/photos/uploader/UploadProgressListener;", "updateUploaderState", "uploaderState", "Lcom/amazon/photos/uploader/UploaderState;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface Uploader {
    void cancelUpload(@NotNull UploadRequest uploadRequest);

    void clearCompleted();

    void startUpload(@NotNull UploadRequest uploadRequest, @NotNull UploadCompleter uploadCompleter, @NotNull UploadProgressListener uploadProgressListener);

    void updateUploaderState(@NotNull UploaderState uploaderState);
}
