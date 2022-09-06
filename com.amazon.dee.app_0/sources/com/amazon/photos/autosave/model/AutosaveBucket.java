package com.amazon.photos.autosave.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AutosaveBucket.kt */
@Entity(indices = {@Index(unique = true, value = {"bucket_path"})}, tableName = "autosave_bucket")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/amazon/photos/autosave/model/AutosaveBucket;", "", "id", "", "bucketPath", "", "folderId", "(JLjava/lang/String;J)V", "getBucketPath", "()Ljava/lang/String;", "getFolderId", "()J", "getId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveBucket {
    @ColumnInfo(name = "bucket_path")
    @NotNull
    private final String bucketPath;
    @ColumnInfo(name = "folder_id")
    private final long folderId;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private final long id;

    public AutosaveBucket(long j, @NotNull String bucketPath, long j2) {
        Intrinsics.checkParameterIsNotNull(bucketPath, "bucketPath");
        this.id = j;
        this.bucketPath = bucketPath;
        this.folderId = j2;
    }

    public static /* synthetic */ AutosaveBucket copy$default(AutosaveBucket autosaveBucket, long j, String str, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = autosaveBucket.id;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            str = autosaveBucket.bucketPath;
        }
        String str2 = str;
        if ((i & 4) != 0) {
            j2 = autosaveBucket.folderId;
        }
        return autosaveBucket.copy(j3, str2, j2);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.bucketPath;
    }

    public final long component3() {
        return this.folderId;
    }

    @NotNull
    public final AutosaveBucket copy(long j, @NotNull String bucketPath, long j2) {
        Intrinsics.checkParameterIsNotNull(bucketPath, "bucketPath");
        return new AutosaveBucket(j, bucketPath, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AutosaveBucket)) {
                return false;
            }
            AutosaveBucket autosaveBucket = (AutosaveBucket) obj;
            return this.id == autosaveBucket.id && Intrinsics.areEqual(this.bucketPath, autosaveBucket.bucketPath) && this.folderId == autosaveBucket.folderId;
        }
        return true;
    }

    @NotNull
    public final String getBucketPath() {
        return this.bucketPath;
    }

    public final long getFolderId() {
        return this.folderId;
    }

    public final long getId() {
        return this.id;
    }

    public int hashCode() {
        long j = this.id;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.bucketPath;
        int hashCode = str != null ? str.hashCode() : 0;
        long j2 = this.folderId;
        return ((i + hashCode) * 31) + ((int) ((j2 >>> 32) ^ j2));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AutosaveBucket(id=");
        outline107.append(this.id);
        outline107.append(", bucketPath=");
        outline107.append(this.bucketPath);
        outline107.append(", folderId=");
        return GeneratedOutlineSupport1.outline87(outline107, this.folderId, ")");
    }

    public /* synthetic */ AutosaveBucket(long j, String str, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, str, j2);
    }
}
