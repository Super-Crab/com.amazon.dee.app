package com.amazon.photos.uploader.internal.utils;

import com.amazon.photos.uploader.internal.OpenForTesting;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FileUtils.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\n\u0010\u0007\u001a\u00020\u0004*\u00020\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/photos/uploader/internal/utils/FileUtils;", "", "()V", "isSinglePartUploadAllowed", "", "file", "Ljava/io/File;", "isValidFile", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class FileUtils {
    public static final Companion Companion = new Companion(null);
    private static final long ONE_MEGA_BYTE = 1048576;
    private static final long UPLOAD_SIZE_MAX_SIZE_BYTES = 16777216;

    /* compiled from: FileUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/internal/utils/FileUtils$Companion;", "", "()V", "ONE_MEGA_BYTE", "", "UPLOAD_SIZE_MAX_SIZE_BYTES", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final boolean isSinglePartUploadAllowed(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return isValidFile(file) && file.length() < UPLOAD_SIZE_MAX_SIZE_BYTES;
    }

    public final boolean isValidFile(@NotNull File isValidFile) {
        Intrinsics.checkParameterIsNotNull(isValidFile, "$this$isValidFile");
        return isValidFile.length() > 0 && isValidFile.isFile();
    }
}
