package com.amazon.photos.discovery.internal.util;

import android.os.Environment;
import com.amazon.imageutilities.JpegVisualDigest;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.jetbrains.annotations.NotNull;
/* compiled from: FileUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/FileUtils;", "", "()V", "createJpegVisualDigest", "Lcom/amazon/imageutilities/JpegVisualDigest;", "algorithm", "", "exists", "", "file", "Ljava/io/File;", "getCameraDirectory", "getExternalStorageDirectory", "type", "getFileExtension", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class FileUtils {
    public static /* synthetic */ JpegVisualDigest createJpegVisualDigest$default(FileUtils fileUtils, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = MessageDigestAlgorithms.MD5;
        }
        return fileUtils.createJpegVisualDigest(str);
    }

    @NotNull
    public final JpegVisualDigest createJpegVisualDigest(@NotNull String algorithm) {
        Intrinsics.checkParameterIsNotNull(algorithm, "algorithm");
        return new JpegVisualDigest(algorithm);
    }

    public final boolean exists(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return file.exists();
    }

    @NotNull
    public final File getCameraDirectory() {
        String str = Environment.DIRECTORY_DCIM;
        Intrinsics.checkExpressionValueIsNotNull(str, "Environment.DIRECTORY_DCIM");
        return getExternalStorageDirectory(str);
    }

    @NotNull
    public final File getExternalStorageDirectory(@NotNull String type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(type);
        Intrinsics.checkExpressionValueIsNotNull(externalStoragePublicDirectory, "Environment.getExternalS…ragePublicDirectory(type)");
        return externalStoragePublicDirectory;
    }

    @NotNull
    public final String getFileExtension(@NotNull File file) {
        String extension;
        Intrinsics.checkParameterIsNotNull(file, "file");
        if (!exists(file)) {
            return "";
        }
        extension = FilesKt__UtilsKt.getExtension(file);
        return extension;
    }
}
