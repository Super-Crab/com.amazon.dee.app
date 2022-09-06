package com.amazon.photos.discovery.internal.worker;

import com.amazon.photos.discovery.internal.model.MutableLocalFolder;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MediaStoreEntry.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/MediaStoreEntry;", "", "localItem", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "localFolder", "Lcom/amazon/photos/discovery/internal/model/MutableLocalFolder;", "(Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;Lcom/amazon/photos/discovery/internal/model/MutableLocalFolder;)V", "getLocalFolder", "()Lcom/amazon/photos/discovery/internal/model/MutableLocalFolder;", "getLocalItem", "()Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MediaStoreEntry {
    @NotNull
    private final MutableLocalFolder localFolder;
    @NotNull
    private final MutableLocalItem localItem;

    public MediaStoreEntry(@NotNull MutableLocalItem localItem, @NotNull MutableLocalFolder localFolder) {
        Intrinsics.checkParameterIsNotNull(localItem, "localItem");
        Intrinsics.checkParameterIsNotNull(localFolder, "localFolder");
        this.localItem = localItem;
        this.localFolder = localFolder;
    }

    public static /* synthetic */ MediaStoreEntry copy$default(MediaStoreEntry mediaStoreEntry, MutableLocalItem mutableLocalItem, MutableLocalFolder mutableLocalFolder, int i, Object obj) {
        if ((i & 1) != 0) {
            mutableLocalItem = mediaStoreEntry.localItem;
        }
        if ((i & 2) != 0) {
            mutableLocalFolder = mediaStoreEntry.localFolder;
        }
        return mediaStoreEntry.copy(mutableLocalItem, mutableLocalFolder);
    }

    @NotNull
    public final MutableLocalItem component1() {
        return this.localItem;
    }

    @NotNull
    public final MutableLocalFolder component2() {
        return this.localFolder;
    }

    @NotNull
    public final MediaStoreEntry copy(@NotNull MutableLocalItem localItem, @NotNull MutableLocalFolder localFolder) {
        Intrinsics.checkParameterIsNotNull(localItem, "localItem");
        Intrinsics.checkParameterIsNotNull(localFolder, "localFolder");
        return new MediaStoreEntry(localItem, localFolder);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof MediaStoreEntry)) {
                return false;
            }
            MediaStoreEntry mediaStoreEntry = (MediaStoreEntry) obj;
            return Intrinsics.areEqual(this.localItem, mediaStoreEntry.localItem) && Intrinsics.areEqual(this.localFolder, mediaStoreEntry.localFolder);
        }
        return true;
    }

    @NotNull
    public final MutableLocalFolder getLocalFolder() {
        return this.localFolder;
    }

    @NotNull
    public final MutableLocalItem getLocalItem() {
        return this.localItem;
    }

    public int hashCode() {
        MutableLocalItem mutableLocalItem = this.localItem;
        int i = 0;
        int hashCode = (mutableLocalItem != null ? mutableLocalItem.hashCode() : 0) * 31;
        MutableLocalFolder mutableLocalFolder = this.localFolder;
        if (mutableLocalFolder != null) {
            i = mutableLocalFolder.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaStoreEntry(localItem=");
        outline107.append(this.localItem);
        outline107.append(", localFolder=");
        outline107.append(this.localFolder);
        outline107.append(")");
        return outline107.toString();
    }
}
