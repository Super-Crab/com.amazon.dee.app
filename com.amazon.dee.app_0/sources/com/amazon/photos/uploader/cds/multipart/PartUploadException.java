package com.amazon.photos.uploader.cds.multipart;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PartUploadException.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/PartUploadException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "errorCode", "(Ljava/lang/String;Ljava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PartUploadException extends Exception {
    @NotNull
    private final String errorCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PartUploadException(@Nullable String str, @NotNull String errorCode) {
        super(str);
        Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
        this.errorCode = errorCode;
    }

    @NotNull
    public final String getErrorCode() {
        return this.errorCode;
    }
}
