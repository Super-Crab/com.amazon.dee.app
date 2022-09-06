package com.amazon.photos.uploader.dao;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RequestSummary.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/uploader/dao/RequestSummary;", "", "count", "", "totalSize", "", "(IJ)V", "getCount", "()I", "setCount", "(I)V", "getTotalSize", "()J", "setTotalSize", "(J)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class RequestSummary {
    private int count;
    private long totalSize;

    public RequestSummary(int i, long j) {
        this.count = i;
        this.totalSize = j;
    }

    public static /* synthetic */ RequestSummary copy$default(RequestSummary requestSummary, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = requestSummary.count;
        }
        if ((i2 & 2) != 0) {
            j = requestSummary.totalSize;
        }
        return requestSummary.copy(i, j);
    }

    public final int component1() {
        return this.count;
    }

    public final long component2() {
        return this.totalSize;
    }

    @NotNull
    public final RequestSummary copy(int i, long j) {
        return new RequestSummary(i, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof RequestSummary)) {
                return false;
            }
            RequestSummary requestSummary = (RequestSummary) obj;
            return this.count == requestSummary.count && this.totalSize == requestSummary.totalSize;
        }
        return true;
    }

    public final int getCount() {
        return this.count;
    }

    public final long getTotalSize() {
        return this.totalSize;
    }

    public int hashCode() {
        long j = this.totalSize;
        return (this.count * 31) + ((int) (j ^ (j >>> 32)));
    }

    public final void setCount(int i) {
        this.count = i;
    }

    public final void setTotalSize(long j) {
        this.totalSize = j;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RequestSummary(count=");
        outline107.append(this.count);
        outline107.append(", totalSize=");
        return GeneratedOutlineSupport1.outline87(outline107, this.totalSize, ")");
    }
}
