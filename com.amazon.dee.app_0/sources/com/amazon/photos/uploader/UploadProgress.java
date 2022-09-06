package com.amazon.photos.uploader;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadProgress.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/amazon/photos/uploader/UploadProgress;", "", "progress", "", "maxProgress", "(JJ)V", "getMaxProgress", "()J", "getProgress", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadProgress {
    private final long maxProgress;
    private final long progress;

    public UploadProgress(long j, long j2) {
        this.progress = j;
        this.maxProgress = j2;
    }

    public static /* synthetic */ UploadProgress copy$default(UploadProgress uploadProgress, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = uploadProgress.progress;
        }
        if ((i & 2) != 0) {
            j2 = uploadProgress.maxProgress;
        }
        return uploadProgress.copy(j, j2);
    }

    public final long component1() {
        return this.progress;
    }

    public final long component2() {
        return this.maxProgress;
    }

    @NotNull
    public final UploadProgress copy(long j, long j2) {
        return new UploadProgress(j, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UploadProgress)) {
                return false;
            }
            UploadProgress uploadProgress = (UploadProgress) obj;
            return this.progress == uploadProgress.progress && this.maxProgress == uploadProgress.maxProgress;
        }
        return true;
    }

    public final long getMaxProgress() {
        return this.maxProgress;
    }

    public final long getProgress() {
        return this.progress;
    }

    public int hashCode() {
        long j = this.progress;
        long j2 = this.maxProgress;
        return (((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UploadProgress(progress=");
        outline107.append(this.progress);
        outline107.append(", maxProgress=");
        return GeneratedOutlineSupport1.outline87(outline107, this.maxProgress, ")");
    }
}
