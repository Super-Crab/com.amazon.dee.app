package com.amazon.photos.uploader.cds.error;

import com.amazon.clouddrive.cdasdk.cdus.UploadErrorCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CdsErrorCodes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"toUploadErrorCode", "Lcom/amazon/clouddrive/cdasdk/cdus/UploadErrorCode;", "", "AndroidPhotosUploader_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsErrorCodesKt {
    @Nullable
    public static final UploadErrorCode toUploadErrorCode(@NotNull String toUploadErrorCode) {
        Intrinsics.checkParameterIsNotNull(toUploadErrorCode, "$this$toUploadErrorCode");
        try {
            return UploadErrorCode.valueOf(toUploadErrorCode);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
