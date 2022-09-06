package com.amazon.photos.discovery.model;

import androidx.room.Embedded;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocalFolderCover.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/amazon/photos/discovery/model/LocalFolderCover;", "", "folderId", "", "latestItem", "Lcom/amazon/photos/discovery/model/LocalItem;", "(JLcom/amazon/photos/discovery/model/LocalItem;)V", "getFolderId", "()J", "getLatestItem", "()Lcom/amazon/photos/discovery/model/LocalItem;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LocalFolderCover {
    private final long folderId;
    @Embedded
    @NotNull
    private final LocalItem latestItem;

    public LocalFolderCover(long j, @NotNull LocalItem latestItem) {
        Intrinsics.checkParameterIsNotNull(latestItem, "latestItem");
        this.folderId = j;
        this.latestItem = latestItem;
    }

    public static /* synthetic */ LocalFolderCover copy$default(LocalFolderCover localFolderCover, long j, LocalItem localItem, int i, Object obj) {
        if ((i & 1) != 0) {
            j = localFolderCover.folderId;
        }
        if ((i & 2) != 0) {
            localItem = localFolderCover.latestItem;
        }
        return localFolderCover.copy(j, localItem);
    }

    public final long component1() {
        return this.folderId;
    }

    @NotNull
    public final LocalItem component2() {
        return this.latestItem;
    }

    @NotNull
    public final LocalFolderCover copy(long j, @NotNull LocalItem latestItem) {
        Intrinsics.checkParameterIsNotNull(latestItem, "latestItem");
        return new LocalFolderCover(j, latestItem);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof LocalFolderCover)) {
                return false;
            }
            LocalFolderCover localFolderCover = (LocalFolderCover) obj;
            return this.folderId == localFolderCover.folderId && Intrinsics.areEqual(this.latestItem, localFolderCover.latestItem);
        }
        return true;
    }

    public final long getFolderId() {
        return this.folderId;
    }

    @NotNull
    public final LocalItem getLatestItem() {
        return this.latestItem;
    }

    public int hashCode() {
        long j = this.folderId;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        LocalItem localItem = this.latestItem;
        return i + (localItem != null ? localItem.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocalFolderCover(folderId=");
        outline107.append(this.folderId);
        outline107.append(", latestItem=");
        outline107.append(this.latestItem);
        outline107.append(")");
        return outline107.toString();
    }
}
