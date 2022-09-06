package com.amazon.photos.uploader.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadWorkerConfiguration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/uploader/internal/UploadWorkerConfiguration;", "", "maxAttempts", "", "(I)V", "getMaxAttempts", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadWorkerConfiguration {
    private final int maxAttempts;

    public UploadWorkerConfiguration(int i) {
        this.maxAttempts = i;
    }

    public static /* synthetic */ UploadWorkerConfiguration copy$default(UploadWorkerConfiguration uploadWorkerConfiguration, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = uploadWorkerConfiguration.maxAttempts;
        }
        return uploadWorkerConfiguration.copy(i);
    }

    public final int component1() {
        return this.maxAttempts;
    }

    @NotNull
    public final UploadWorkerConfiguration copy(int i) {
        return new UploadWorkerConfiguration(i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof UploadWorkerConfiguration) && this.maxAttempts == ((UploadWorkerConfiguration) obj).maxAttempts;
        }
        return true;
    }

    public final int getMaxAttempts() {
        return this.maxAttempts;
    }

    public int hashCode() {
        return this.maxAttempts;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("UploadWorkerConfiguration(maxAttempts="), this.maxAttempts, ")");
    }
}
