package com.amazon.photos.uploader;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadCompleter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/amazon/photos/uploader/MetricData;", "", "startTime", "", "file", "Ljava/io/File;", "(JLjava/io/File;)V", "getFile", "()Ljava/io/File;", "getStartTime", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MetricData {
    @NotNull
    private final File file;
    private final long startTime;

    public MetricData(long j, @NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        this.startTime = j;
        this.file = file;
    }

    public static /* synthetic */ MetricData copy$default(MetricData metricData, long j, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            j = metricData.startTime;
        }
        if ((i & 2) != 0) {
            file = metricData.file;
        }
        return metricData.copy(j, file);
    }

    public final long component1() {
        return this.startTime;
    }

    @NotNull
    public final File component2() {
        return this.file;
    }

    @NotNull
    public final MetricData copy(long j, @NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return new MetricData(j, file);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof MetricData)) {
                return false;
            }
            MetricData metricData = (MetricData) obj;
            return this.startTime == metricData.startTime && Intrinsics.areEqual(this.file, metricData.file);
        }
        return true;
    }

    @NotNull
    public final File getFile() {
        return this.file;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public int hashCode() {
        long j = this.startTime;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        File file = this.file;
        return i + (file != null ? file.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MetricData(startTime=");
        outline107.append(this.startTime);
        outline107.append(", file=");
        outline107.append(this.file);
        outline107.append(")");
        return outline107.toString();
    }
}
