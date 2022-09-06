package com.amazon.photos.discovery.internal.model;

import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.photos.discovery.model.ItemType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MutableLocalItem.kt */
@Entity(foreignKeys = {@ForeignKey(childColumns = {"parent_id"}, entity = MutableLocalFolder.class, onDelete = 5, parentColumns = {"id"})}, indices = {@Index(unique = true, value = {"id"}), @Index(unique = true, value = {"file_path"}), @Index({"parent_id"})}, tableName = "local_item")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\bJ\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u001f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB'\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\nB\u0091\u0001\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u0011\u001a\u00020\u0007\u0012\u0006\u0010\u0012\u001a\u00020\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u0007\u0012\u0006\u0010\u0015\u001a\u00020\u0007\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u0018J\b\u0010>\u001a\u00020\u0000H\u0016J\t\u0010?\u001a\u00020\u0007HÆ\u0003J\t\u0010@\u001a\u00020\u0007HÆ\u0003J\t\u0010A\u001a\u00020\u0007HÆ\u0003J\t\u0010B\u001a\u00020\u0007HÆ\u0003J\t\u0010C\u001a\u00020\u0007HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0007HÆ\u0003J\t\u0010G\u001a\u00020\u0007HÆ\u0003J\t\u0010H\u001a\u00020\u0003HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\u0010\u0010J\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010K\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010L\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010M\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\"J\t\u0010N\u001a\u00020\u0007HÆ\u0003Jº\u0001\u0010O\u001a\u00020\u00002\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010PJ\u0013\u0010Q\u001a\u00020R2\b\u0010S\u001a\u0004\u0018\u00010TH\u0096\u0002J\b\u0010U\u001a\u00020VH\u0016J\t\u0010W\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\u0011\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0013\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\u001e\u0010\u0012\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\"\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010\u0015\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001a\"\u0004\b'\u0010\u001cR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\"\u0010\u000f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b*\u0010\"\"\u0004\b+\u0010$R\u001e\u0010\u000b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001a\"\u0004\b-\u0010\u001cR\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R \u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010)\"\u0004\b1\u00102R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001aR\"\u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b4\u0010\"\"\u0004\b5\u0010$R\u001e\u0010\u0014\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u001a\"\u0004\b7\u0010\u001cR\u001e\u0010\t\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u001a\"\u0004\b9\u0010\u001cR \u0010\u0017\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010)\"\u0004\b;\u00102R\"\u0010\u000e\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b<\u0010\"\"\u0004\b=\u0010$¨\u0006X"}, d2 = {"Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "", "type", "Lcom/amazon/photos/discovery/model/ItemType;", "filePath", "", Message.SERIALIZED_NAME_PARENT_ID, "", "(Lcom/amazon/photos/discovery/model/ItemType;Ljava/lang/String;J)V", "unifiedId", "(Lcom/amazon/photos/discovery/model/ItemType;Ljava/lang/String;JJ)V", "id", "itemType", "duration", "width", "height", "size", "dateAdded", "dateTaken", "dateModified", "startProcessing", "endProcessing", SierraContentProviderContract.MD5_VALUE, "visualDigest", "(JJLcom/amazon/photos/discovery/model/ItemType;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;JJJJJLjava/lang/String;Ljava/lang/String;J)V", "getDateAdded", "()J", "setDateAdded", "(J)V", "getDateModified", "setDateModified", "getDateTaken", "setDateTaken", "getDuration", "()Ljava/lang/Long;", "setDuration", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getEndProcessing", "setEndProcessing", "getFilePath", "()Ljava/lang/String;", "getHeight", "setHeight", "getId", "setId", "getItemType", "()Lcom/amazon/photos/discovery/model/ItemType;", "getMd5", "setMd5", "(Ljava/lang/String;)V", "getParentId", "getSize", "setSize", "getStartProcessing", "setStartProcessing", "getUnifiedId", "setUnifiedId", "getVisualDigest", "setVisualDigest", "getWidth", "setWidth", "clone", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJLcom/amazon/photos/discovery/model/ItemType;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;JJJJJLjava/lang/String;Ljava/lang/String;J)Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "equals", "", "other", "", "hashCode", "", "toString", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MutableLocalItem implements Cloneable {
    @ColumnInfo(name = "date_added")
    private long dateAdded;
    @ColumnInfo(name = "date_modified")
    private long dateModified;
    @ColumnInfo(name = "date_taken")
    private long dateTaken;
    @ColumnInfo(name = "duration")
    @Nullable
    private Long duration;
    @ColumnInfo(name = "end_processing")
    private long endProcessing;
    @ColumnInfo(name = "file_path")
    @NotNull
    private final String filePath;
    @ColumnInfo(name = "height")
    @Nullable
    private Long height;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "type")
    @NotNull
    private final ItemType itemType;
    @ColumnInfo(name = SierraContentProviderContract.MD5_VALUE)
    @Nullable
    private String md5;
    @ColumnInfo(name = "parent_id")
    private final long parentId;
    @ColumnInfo(name = "size")
    @Nullable
    private Long size;
    @ColumnInfo(name = "start_processing")
    private long startProcessing;
    @ColumnInfo(name = "unified_id")
    private long unifiedId;
    @ColumnInfo(name = "visual_digest")
    @Nullable
    private String visualDigest;
    @ColumnInfo(name = "width")
    @Nullable
    private Long width;

    public MutableLocalItem(long j, long j2, @NotNull ItemType itemType, @NotNull String filePath, @Nullable Long l, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4, long j3, long j4, long j5, long j6, long j7, @Nullable String str, @Nullable String str2, long j8) {
        Intrinsics.checkParameterIsNotNull(itemType, "itemType");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        this.id = j;
        this.unifiedId = j2;
        this.itemType = itemType;
        this.filePath = filePath;
        this.duration = l;
        this.width = l2;
        this.height = l3;
        this.size = l4;
        this.dateAdded = j3;
        this.dateTaken = j4;
        this.dateModified = j5;
        this.startProcessing = j6;
        this.endProcessing = j7;
        this.md5 = str;
        this.visualDigest = str2;
        this.parentId = j8;
    }

    public static /* synthetic */ MutableLocalItem copy$default(MutableLocalItem mutableLocalItem, long j, long j2, ItemType itemType, String str, Long l, Long l2, Long l3, Long l4, long j3, long j4, long j5, long j6, long j7, String str2, String str3, long j8, int i, Object obj) {
        return mutableLocalItem.copy((i & 1) != 0 ? mutableLocalItem.id : j, (i & 2) != 0 ? mutableLocalItem.unifiedId : j2, (i & 4) != 0 ? mutableLocalItem.itemType : itemType, (i & 8) != 0 ? mutableLocalItem.filePath : str, (i & 16) != 0 ? mutableLocalItem.duration : l, (i & 32) != 0 ? mutableLocalItem.width : l2, (i & 64) != 0 ? mutableLocalItem.height : l3, (i & 128) != 0 ? mutableLocalItem.size : l4, (i & 256) != 0 ? mutableLocalItem.dateAdded : j3, (i & 512) != 0 ? mutableLocalItem.dateTaken : j4, (i & 1024) != 0 ? mutableLocalItem.dateModified : j5, (i & 2048) != 0 ? mutableLocalItem.startProcessing : j6, (i & 4096) != 0 ? mutableLocalItem.endProcessing : j7, (i & 8192) != 0 ? mutableLocalItem.md5 : str2, (i & 16384) != 0 ? mutableLocalItem.visualDigest : str3, (i & 32768) != 0 ? mutableLocalItem.parentId : j8);
    }

    public final long component1() {
        return this.id;
    }

    public final long component10() {
        return this.dateTaken;
    }

    public final long component11() {
        return this.dateModified;
    }

    public final long component12() {
        return this.startProcessing;
    }

    public final long component13() {
        return this.endProcessing;
    }

    @Nullable
    public final String component14() {
        return this.md5;
    }

    @Nullable
    public final String component15() {
        return this.visualDigest;
    }

    public final long component16() {
        return this.parentId;
    }

    public final long component2() {
        return this.unifiedId;
    }

    @NotNull
    public final ItemType component3() {
        return this.itemType;
    }

    @NotNull
    public final String component4() {
        return this.filePath;
    }

    @Nullable
    public final Long component5() {
        return this.duration;
    }

    @Nullable
    public final Long component6() {
        return this.width;
    }

    @Nullable
    public final Long component7() {
        return this.height;
    }

    @Nullable
    public final Long component8() {
        return this.size;
    }

    public final long component9() {
        return this.dateAdded;
    }

    @NotNull
    public final MutableLocalItem copy(long j, long j2, @NotNull ItemType itemType, @NotNull String filePath, @Nullable Long l, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4, long j3, long j4, long j5, long j6, long j7, @Nullable String str, @Nullable String str2, long j8) {
        Intrinsics.checkParameterIsNotNull(itemType, "itemType");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        return new MutableLocalItem(j, j2, itemType, filePath, l, l2, l3, l4, j3, j4, j5, j6, j7, str, str2, j8);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(MutableLocalItem.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            MutableLocalItem mutableLocalItem = (MutableLocalItem) obj;
            return this.id == mutableLocalItem.id && this.unifiedId == mutableLocalItem.unifiedId && this.itemType == mutableLocalItem.itemType && !(Intrinsics.areEqual(this.filePath, mutableLocalItem.filePath) ^ true) && !(Intrinsics.areEqual(this.duration, mutableLocalItem.duration) ^ true) && !(Intrinsics.areEqual(this.width, mutableLocalItem.width) ^ true) && !(Intrinsics.areEqual(this.height, mutableLocalItem.height) ^ true) && !(Intrinsics.areEqual(this.size, mutableLocalItem.size) ^ true) && this.dateAdded == mutableLocalItem.dateAdded && this.dateTaken == mutableLocalItem.dateTaken && this.dateModified == mutableLocalItem.dateModified && this.startProcessing == mutableLocalItem.startProcessing && this.endProcessing == mutableLocalItem.endProcessing && !(Intrinsics.areEqual(this.md5, mutableLocalItem.md5) ^ true) && !(Intrinsics.areEqual(this.visualDigest, mutableLocalItem.visualDigest) ^ true) && this.parentId == mutableLocalItem.parentId;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.amazon.photos.discovery.internal.model.MutableLocalItem");
    }

    public final long getDateAdded() {
        return this.dateAdded;
    }

    public final long getDateModified() {
        return this.dateModified;
    }

    public final long getDateTaken() {
        return this.dateTaken;
    }

    @Nullable
    public final Long getDuration() {
        return this.duration;
    }

    public final long getEndProcessing() {
        return this.endProcessing;
    }

    @NotNull
    public final String getFilePath() {
        return this.filePath;
    }

    @Nullable
    public final Long getHeight() {
        return this.height;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final ItemType getItemType() {
        return this.itemType;
    }

    @Nullable
    public final String getMd5() {
        return this.md5;
    }

    public final long getParentId() {
        return this.parentId;
    }

    @Nullable
    public final Long getSize() {
        return this.size;
    }

    public final long getStartProcessing() {
        return this.startProcessing;
    }

    public final long getUnifiedId() {
        return this.unifiedId;
    }

    @Nullable
    public final String getVisualDigest() {
        return this.visualDigest;
    }

    @Nullable
    public final Long getWidth() {
        return this.width;
    }

    public int hashCode() {
        return this.filePath.hashCode();
    }

    public final void setDateAdded(long j) {
        this.dateAdded = j;
    }

    public final void setDateModified(long j) {
        this.dateModified = j;
    }

    public final void setDateTaken(long j) {
        this.dateTaken = j;
    }

    public final void setDuration(@Nullable Long l) {
        this.duration = l;
    }

    public final void setEndProcessing(long j) {
        this.endProcessing = j;
    }

    public final void setHeight(@Nullable Long l) {
        this.height = l;
    }

    public final void setId(long j) {
        this.id = j;
    }

    public final void setMd5(@Nullable String str) {
        this.md5 = str;
    }

    public final void setSize(@Nullable Long l) {
        this.size = l;
    }

    public final void setStartProcessing(long j) {
        this.startProcessing = j;
    }

    public final void setUnifiedId(long j) {
        this.unifiedId = j;
    }

    public final void setVisualDigest(@Nullable String str) {
        this.visualDigest = str;
    }

    public final void setWidth(@Nullable Long l) {
        this.width = l;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MutableLocalItem(id=");
        outline107.append(this.id);
        outline107.append(", unifiedId=");
        outline107.append(this.unifiedId);
        outline107.append(", itemType=");
        outline107.append(this.itemType);
        outline107.append(", filePath=");
        outline107.append(this.filePath);
        outline107.append(", duration=");
        outline107.append(this.duration);
        outline107.append(", width=");
        outline107.append(this.width);
        outline107.append(", height=");
        outline107.append(this.height);
        outline107.append(", size=");
        outline107.append(this.size);
        outline107.append(", dateAdded=");
        outline107.append(this.dateAdded);
        outline107.append(", dateTaken=");
        outline107.append(this.dateTaken);
        outline107.append(", dateModified=");
        outline107.append(this.dateModified);
        outline107.append(", startProcessing=");
        outline107.append(this.startProcessing);
        outline107.append(", endProcessing=");
        outline107.append(this.endProcessing);
        outline107.append(", md5=");
        outline107.append(this.md5);
        outline107.append(", visualDigest=");
        outline107.append(this.visualDigest);
        outline107.append(", parentId=");
        return GeneratedOutlineSupport1.outline87(outline107, this.parentId, ")");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Ignore
    public MutableLocalItem(@NotNull ItemType type, @NotNull String filePath, long j) {
        this(0L, 0L, type, filePath, null, null, null, 0L, 0L, 0L, 0L, SystemClock.elapsedRealtime(), 0L, null, null, j);
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
    }

    @NotNull
    /* renamed from: clone */
    public MutableLocalItem m4339clone() {
        return copy$default(this, 0L, 0L, null, null, null, null, null, null, 0L, 0L, 0L, 0L, 0L, null, null, 0L, 65535, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    @Ignore
    public MutableLocalItem(@NotNull ItemType type, @NotNull String filePath, long j, long j2) {
        this(0L, j, type, filePath, 0L, null, null, 0L, 0L, 0L, 0L, 0L, 0L, null, null, j2);
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
    }
}
