package com.amazon.photos.uploader.cds.error;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadException.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00060\u0001j\u0002`\u0002B+\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\tR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/photos/uploader/cds/error/UploadException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "errorCode", "httpCode", "", "conflictNodeId", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getConflictNodeId", "()Ljava/lang/String;", "setConflictNodeId", "(Ljava/lang/String;)V", "getErrorCode", "getHttpCode", "()I", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadException extends Exception {
    @Nullable
    private String conflictNodeId;
    @NotNull
    private final String errorCode;
    private final int httpCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadException(@Nullable String str, @NotNull String errorCode, int i, @Nullable String str2) {
        super(str);
        Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
        this.errorCode = errorCode;
        this.httpCode = i;
        this.conflictNodeId = str2;
    }

    @Nullable
    public final String getConflictNodeId() {
        return this.conflictNodeId;
    }

    @NotNull
    public final String getErrorCode() {
        return this.errorCode;
    }

    public final int getHttpCode() {
        return this.httpCode;
    }

    public final void setConflictNodeId(@Nullable String str) {
        this.conflictNodeId = str;
    }

    public /* synthetic */ UploadException(String str, String str2, int i, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, (i2 & 8) != 0 ? null : str3);
    }
}
