package com.amazon.photos.uploader.cds.multipart;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocalValidationException.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/LocalValidationException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "errorCodeString", "(Ljava/lang/String;Ljava/lang/String;)V", "getErrorCodeString", "()Ljava/lang/String;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LocalValidationException extends Exception {
    @Nullable
    private final String errorCodeString;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocalValidationException(@NotNull String message, @Nullable String str) {
        super(message);
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.errorCodeString = str;
    }

    @Nullable
    public final String getErrorCodeString() {
        return this.errorCodeString;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalValidationException(@NotNull String message) {
        this(message, null);
        Intrinsics.checkParameterIsNotNull(message, "message");
    }
}
