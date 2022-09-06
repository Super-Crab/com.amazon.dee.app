package com.amazon.photos.discovery.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocalFolderStats.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/amazon/photos/discovery/model/LocalFolderStats;", "", "folderId", "", "itemCount", "(JJ)V", "getFolderId", "()J", "getItemCount", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LocalFolderStats {
    private final long folderId;
    private final long itemCount;

    public LocalFolderStats(long j, long j2) {
        this.folderId = j;
        this.itemCount = j2;
    }

    public static /* synthetic */ LocalFolderStats copy$default(LocalFolderStats localFolderStats, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = localFolderStats.folderId;
        }
        if ((i & 2) != 0) {
            j2 = localFolderStats.itemCount;
        }
        return localFolderStats.copy(j, j2);
    }

    public final long component1() {
        return this.folderId;
    }

    public final long component2() {
        return this.itemCount;
    }

    @NotNull
    public final LocalFolderStats copy(long j, long j2) {
        return new LocalFolderStats(j, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof LocalFolderStats)) {
                return false;
            }
            LocalFolderStats localFolderStats = (LocalFolderStats) obj;
            return this.folderId == localFolderStats.folderId && this.itemCount == localFolderStats.itemCount;
        }
        return true;
    }

    public final long getFolderId() {
        return this.folderId;
    }

    public final long getItemCount() {
        return this.itemCount;
    }

    public int hashCode() {
        long j = this.folderId;
        long j2 = this.itemCount;
        return (((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocalFolderStats(folderId=");
        outline107.append(this.folderId);
        outline107.append(", itemCount=");
        return GeneratedOutlineSupport1.outline87(outline107, this.itemCount, ")");
    }
}
