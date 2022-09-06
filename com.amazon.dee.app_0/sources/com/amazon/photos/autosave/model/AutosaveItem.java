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
/* compiled from: AutosaveItem.kt */
@Entity(indices = {@Index(unique = true, value = {"local_item_id"})}, tableName = "autosave_item")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\t\u0010\u001f\u001a\u00020\fHÆ\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\bHÖ\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011¨\u0006'"}, d2 = {"Lcom/amazon/photos/autosave/model/AutosaveItem;", "", "id", "", "localItemId", "unifiedId", "folderId", "filePath", "", "state", "Lcom/amazon/photos/autosave/model/AutosaveState;", "mediaType", "Lcom/amazon/photos/autosave/model/MediaType;", "(JJJJLjava/lang/String;Lcom/amazon/photos/autosave/model/AutosaveState;Lcom/amazon/photos/autosave/model/MediaType;)V", "getFilePath", "()Ljava/lang/String;", "getFolderId", "()J", "getId", "getLocalItemId", "getMediaType", "()Lcom/amazon/photos/autosave/model/MediaType;", "getState", "()Lcom/amazon/photos/autosave/model/AutosaveState;", "getUnifiedId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveItem {
    @ColumnInfo(name = "file_path")
    @NotNull
    private final String filePath;
    @ColumnInfo(name = "folder_id")
    private final long folderId;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private final long id;
    @ColumnInfo(name = "local_item_id")
    private final long localItemId;
    @ColumnInfo(name = "media_type")
    @NotNull
    private final MediaType mediaType;
    @ColumnInfo(name = "state")
    @NotNull
    private final AutosaveState state;
    @ColumnInfo(name = "unified_id")
    private final long unifiedId;

    public AutosaveItem(long j, long j2, long j3, long j4, @NotNull String filePath, @NotNull AutosaveState state, @NotNull MediaType mediaType) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
        this.id = j;
        this.localItemId = j2;
        this.unifiedId = j3;
        this.folderId = j4;
        this.filePath = filePath;
        this.state = state;
        this.mediaType = mediaType;
    }

    public final long component1() {
        return this.id;
    }

    public final long component2() {
        return this.localItemId;
    }

    public final long component3() {
        return this.unifiedId;
    }

    public final long component4() {
        return this.folderId;
    }

    @NotNull
    public final String component5() {
        return this.filePath;
    }

    @NotNull
    public final AutosaveState component6() {
        return this.state;
    }

    @NotNull
    public final MediaType component7() {
        return this.mediaType;
    }

    @NotNull
    public final AutosaveItem copy(long j, long j2, long j3, long j4, @NotNull String filePath, @NotNull AutosaveState state, @NotNull MediaType mediaType) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
        return new AutosaveItem(j, j2, j3, j4, filePath, state, mediaType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AutosaveItem)) {
                return false;
            }
            AutosaveItem autosaveItem = (AutosaveItem) obj;
            return this.id == autosaveItem.id && this.localItemId == autosaveItem.localItemId && this.unifiedId == autosaveItem.unifiedId && this.folderId == autosaveItem.folderId && Intrinsics.areEqual(this.filePath, autosaveItem.filePath) && Intrinsics.areEqual(this.state, autosaveItem.state) && Intrinsics.areEqual(this.mediaType, autosaveItem.mediaType);
        }
        return true;
    }

    @NotNull
    public final String getFilePath() {
        return this.filePath;
    }

    public final long getFolderId() {
        return this.folderId;
    }

    public final long getId() {
        return this.id;
    }

    public final long getLocalItemId() {
        return this.localItemId;
    }

    @NotNull
    public final MediaType getMediaType() {
        return this.mediaType;
    }

    @NotNull
    public final AutosaveState getState() {
        return this.state;
    }

    public final long getUnifiedId() {
        return this.unifiedId;
    }

    public int hashCode() {
        long j = this.id;
        long j2 = this.localItemId;
        long j3 = this.unifiedId;
        long j4 = this.folderId;
        int i = ((((((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) ((j4 >>> 32) ^ j4))) * 31;
        String str = this.filePath;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        AutosaveState autosaveState = this.state;
        int hashCode2 = (hashCode + (autosaveState != null ? autosaveState.hashCode() : 0)) * 31;
        MediaType mediaType = this.mediaType;
        if (mediaType != null) {
            i2 = mediaType.hashCode();
        }
        return hashCode2 + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AutosaveItem(id=");
        outline107.append(this.id);
        outline107.append(", localItemId=");
        outline107.append(this.localItemId);
        outline107.append(", unifiedId=");
        outline107.append(this.unifiedId);
        outline107.append(", folderId=");
        outline107.append(this.folderId);
        outline107.append(", filePath=");
        outline107.append(this.filePath);
        outline107.append(", state=");
        outline107.append(this.state);
        outline107.append(", mediaType=");
        outline107.append(this.mediaType);
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ AutosaveItem(long j, long j2, long j3, long j4, String str, AutosaveState autosaveState, MediaType mediaType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, j2, j3, j4, str, autosaveState, mediaType);
    }
}
