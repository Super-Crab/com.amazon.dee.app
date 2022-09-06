package com.amazon.photos.uploader;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.annotation.VisibleForTesting;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FileSizeCategoryProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0003\f\r\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0002J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\nJ\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/uploader/FileSizeCategoryProvider;", "", "()V", "getFileSize", "", "filePath", "", "getFileSizeCategory", "Lcom/amazon/photos/uploader/FileSizeCategoryProvider$FileSizeCategory;", "fileSize", "getFileSizeCategory$AndroidPhotosUploader_release", "getFileSizeSafe", "Companion", "FileSizeCategory", "FileSizeGroupCategory", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class FileSizeCategoryProvider {
    public static final Companion Companion = new Companion(null);
    private static final long ONE_GIG_BYTE = 1073741824;
    private static final long ONE_MEGA_BYTE = 1048576;

    /* compiled from: FileSizeCategoryProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/FileSizeCategoryProvider$Companion;", "", "()V", "ONE_GIG_BYTE", "", "ONE_MEGA_BYTE", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: FileSizeCategoryProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0016\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a¨\u0006\u001b"}, d2 = {"Lcom/amazon/photos/uploader/FileSizeCategoryProvider$FileSizeCategory;", "", "sizeGroupCategory", "Lcom/amazon/photos/uploader/FileSizeCategoryProvider$FileSizeGroupCategory;", "maxBytesForCategory", "", "(Ljava/lang/String;ILcom/amazon/photos/uploader/FileSizeCategoryProvider$FileSizeGroupCategory;J)V", "getMaxBytesForCategory$AndroidPhotosUploader_release", "()J", "getSizeGroupCategory$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/FileSizeCategoryProvider$FileSizeGroupCategory;", "UNKNOWN_SIZE", "LESS_THAN_2MB", "BETWEEN_2MB_5MB", "BETWEEN_5MB_10MB", "BETWEEN_10MB_16MB", "BETWEEN_16MB_32MB", "BETWEEN_32MB_64MB", "BETWEEN_64MB_128MB", "BETWEEN_128MB_264MB", "BETWEEN_264MB_512MB", "BETWEEN_512MB_1024MB", "BETWEEN_1GB_2GB", "BETWEEN_2GB_4GB", "BETWEEN_4GB_8GB", "BETWEEN_8GB_16GB", "MORE_THAN_16GB", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public enum FileSizeCategory {
        UNKNOWN_SIZE(FileSizeGroupCategory.UNKNOWN_FILE, 0),
        LESS_THAN_2MB(FileSizeGroupCategory.SMALL_FILE, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE),
        BETWEEN_2MB_5MB(FileSizeGroupCategory.SMALL_FILE, CacheDataSink.DEFAULT_FRAGMENT_SIZE),
        BETWEEN_5MB_10MB(FileSizeGroupCategory.SMALL_FILE, 10485760),
        BETWEEN_10MB_16MB(FileSizeGroupCategory.SMALL_FILE, 16777216),
        BETWEEN_16MB_32MB(FileSizeGroupCategory.BIG_FILE, 33554432),
        BETWEEN_32MB_64MB(FileSizeGroupCategory.BIG_FILE, 67108864),
        BETWEEN_64MB_128MB(FileSizeGroupCategory.BIG_FILE, 134217728),
        BETWEEN_128MB_264MB(FileSizeGroupCategory.BIG_FILE, 276824064),
        BETWEEN_264MB_512MB(FileSizeGroupCategory.BIG_FILE, 536870912),
        BETWEEN_512MB_1024MB(FileSizeGroupCategory.BIG_FILE, 1073741824),
        BETWEEN_1GB_2GB(FileSizeGroupCategory.BIG_FILE, 2147483648L),
        BETWEEN_2GB_4GB(FileSizeGroupCategory.BIG_FILE, 4294967296L),
        BETWEEN_4GB_8GB(FileSizeGroupCategory.BIG_FILE, 8589934592L),
        BETWEEN_8GB_16GB(FileSizeGroupCategory.BIG_FILE, 17179869184L),
        MORE_THAN_16GB(FileSizeGroupCategory.BIG_FILE, Long.MAX_VALUE);
        
        private final long maxBytesForCategory;
        @NotNull
        private final FileSizeGroupCategory sizeGroupCategory;

        FileSizeCategory(FileSizeGroupCategory fileSizeGroupCategory, long j) {
            this.sizeGroupCategory = fileSizeGroupCategory;
            this.maxBytesForCategory = j;
        }

        public final long getMaxBytesForCategory$AndroidPhotosUploader_release() {
            return this.maxBytesForCategory;
        }

        @NotNull
        public final FileSizeGroupCategory getSizeGroupCategory$AndroidPhotosUploader_release() {
            return this.sizeGroupCategory;
        }
    }

    /* compiled from: FileSizeCategoryProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/FileSizeCategoryProvider$FileSizeGroupCategory;", "", "(Ljava/lang/String;I)V", "UNKNOWN_FILE", "SMALL_FILE", "BIG_FILE", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public enum FileSizeGroupCategory {
        UNKNOWN_FILE,
        SMALL_FILE,
        BIG_FILE
    }

    private final FileSizeCategory getFileSizeCategory(long j) {
        FileSizeCategory fileSizeCategory;
        FileSizeCategory[] values = FileSizeCategory.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                fileSizeCategory = null;
                break;
            }
            fileSizeCategory = values[i];
            if (j < fileSizeCategory.getMaxBytesForCategory$AndroidPhotosUploader_release()) {
                break;
            }
            i++;
        }
        return fileSizeCategory != null ? fileSizeCategory : FileSizeCategory.MORE_THAN_16GB;
    }

    private final long getFileSizeSafe(String str) {
        try {
            return getFileSize(str);
        } catch (Exception unused) {
            return -1L;
        }
    }

    @VisibleForTesting
    public final long getFileSize(@NotNull String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        return new File(filePath).length();
    }

    @NotNull
    public final FileSizeCategory getFileSizeCategory$AndroidPhotosUploader_release(@NotNull String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        return getFileSizeCategory(getFileSizeSafe(filePath));
    }
}
