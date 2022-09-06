package com.amazon.photos.uploader.observables;

import amazon.speech.simclient.settings.Settings;
import com.amazon.photos.uploader.AbandonReason;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbandonedRequestInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/photos/uploader/observables/AbandonedRequestInfo;", "", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/photos/uploader/AbandonReason;", "(Lcom/amazon/photos/uploader/UploadRequest;Ljava/lang/Throwable;Lcom/amazon/photos/uploader/UploadErrorCategory;Lcom/amazon/photos/uploader/AbandonReason;)V", "getErrorCategory", "()Lcom/amazon/photos/uploader/UploadErrorCategory;", "getEx", "()Ljava/lang/Throwable;", "getReason", "()Lcom/amazon/photos/uploader/AbandonReason;", "getUploadRequest", "()Lcom/amazon/photos/uploader/UploadRequest;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AbandonedRequestInfo {
    @NotNull
    private final UploadErrorCategory errorCategory;
    @Nullable
    private final Throwable ex;
    @NotNull
    private final AbandonReason reason;
    @NotNull
    private final UploadRequest uploadRequest;

    public AbandonedRequestInfo(@NotNull UploadRequest uploadRequest, @Nullable Throwable th, @NotNull UploadErrorCategory errorCategory, @NotNull AbandonReason reason) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        this.uploadRequest = uploadRequest;
        this.ex = th;
        this.errorCategory = errorCategory;
        this.reason = reason;
    }

    @NotNull
    public final UploadErrorCategory getErrorCategory() {
        return this.errorCategory;
    }

    @Nullable
    public final Throwable getEx() {
        return this.ex;
    }

    @NotNull
    public final AbandonReason getReason() {
        return this.reason;
    }

    @NotNull
    public final UploadRequest getUploadRequest() {
        return this.uploadRequest;
    }
}
