package com.amazon.photos.discovery.model;

import androidx.annotation.VisibleForTesting;
import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import androidx.room.Ignore;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.alexa.sharing.api.models.Message;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocalItem.kt */
@DatabaseView("SELECT * FROM local_item")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b6\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0083\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u0012\u001a\u00020\u0003¢\u0006\u0002\u0010\u0013B\u0091\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u0012\u001a\u00020\u0003¢\u0006\u0002\u0010\u0016J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0006HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003Jº\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0012\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010=J\u0013\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010A\u001a\u00020BHÖ\u0001J\t\u0010C\u001a\u00020\bHÖ\u0001R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0015\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b!\u0010\u001cR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0016\u0010\u0014\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u001a\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b+\u0010\u001c¨\u0006D"}, d2 = {"Lcom/amazon/photos/discovery/model/LocalItem;", "", "id", "", "unifiedId", "type", "Lcom/amazon/photos/discovery/model/ItemType;", "filePath", "", "duration", "width", "height", "size", "dateAdded", "dateTaken", "dateModified", SierraContentProviderContract.MD5_VALUE, "visualDigest", Message.SERIALIZED_NAME_PARENT_ID, "(JJLcom/amazon/photos/discovery/model/ItemType;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;JJJJLjava/lang/String;Ljava/lang/String;J)V", "startProcessing", "endProcessing", "(JJLcom/amazon/photos/discovery/model/ItemType;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;JJJJJJLjava/lang/String;Ljava/lang/String;J)V", "getDateAdded", "()J", "getDateModified", "getDateTaken", "getDuration", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEndProcessing", "getFilePath", "()Ljava/lang/String;", "getHeight", "getId", "getMd5", "getParentId", "getSize", "getStartProcessing", "getType", "()Lcom/amazon/photos/discovery/model/ItemType;", "getUnifiedId", "getVisualDigest", "getWidth", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJLcom/amazon/photos/discovery/model/ItemType;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;JJJJJJLjava/lang/String;Ljava/lang/String;J)Lcom/amazon/photos/discovery/model/LocalItem;", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LocalItem {
    @ColumnInfo(name = "date_added")
    private final long dateAdded;
    @ColumnInfo(name = "date_modified")
    private final long dateModified;
    @ColumnInfo(name = "date_taken")
    private final long dateTaken;
    @ColumnInfo(name = "duration")
    @Nullable
    private final Long duration;
    @ColumnInfo(name = "end_processing")
    private final long endProcessing;
    @ColumnInfo(name = "file_path")
    @Nullable
    private final String filePath;
    @ColumnInfo(name = "height")
    @Nullable
    private final Long height;
    @ColumnInfo(name = "id")
    private final long id;
    @ColumnInfo(name = SierraContentProviderContract.MD5_VALUE)
    @Nullable
    private final String md5;
    @ColumnInfo(name = "parent_id")
    private final long parentId;
    @ColumnInfo(name = "size")
    private final long size;
    @ColumnInfo(name = "start_processing")
    private final long startProcessing;
    @ColumnInfo(name = "type")
    @NotNull
    private final ItemType type;
    @ColumnInfo(name = "unified_id")
    private final long unifiedId;
    @ColumnInfo(name = "visual_digest")
    @Nullable
    private final String visualDigest;
    @ColumnInfo(name = "width")
    @Nullable
    private final Long width;

    public LocalItem(long j, long j2, @NotNull ItemType type, @Nullable String str, @Nullable Long l, @Nullable Long l2, @Nullable Long l3, long j3, long j4, long j5, long j6, long j7, long j8, @Nullable String str2, @Nullable String str3, long j9) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.id = j;
        this.unifiedId = j2;
        this.type = type;
        this.filePath = str;
        this.duration = l;
        this.width = l2;
        this.height = l3;
        this.size = j3;
        this.dateAdded = j4;
        this.dateTaken = j5;
        this.dateModified = j6;
        this.startProcessing = j7;
        this.endProcessing = j8;
        this.md5 = str2;
        this.visualDigest = str3;
        this.parentId = j9;
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
        return this.type;
    }

    @Nullable
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

    public final long component8() {
        return this.size;
    }

    public final long component9() {
        return this.dateAdded;
    }

    @NotNull
    public final LocalItem copy(long j, long j2, @NotNull ItemType type, @Nullable String str, @Nullable Long l, @Nullable Long l2, @Nullable Long l3, long j3, long j4, long j5, long j6, long j7, long j8, @Nullable String str2, @Nullable String str3, long j9) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new LocalItem(j, j2, type, str, l, l2, l3, j3, j4, j5, j6, j7, j8, str2, str3, j9);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof LocalItem)) {
                return false;
            }
            LocalItem localItem = (LocalItem) obj;
            return this.id == localItem.id && this.unifiedId == localItem.unifiedId && Intrinsics.areEqual(this.type, localItem.type) && Intrinsics.areEqual(this.filePath, localItem.filePath) && Intrinsics.areEqual(this.duration, localItem.duration) && Intrinsics.areEqual(this.width, localItem.width) && Intrinsics.areEqual(this.height, localItem.height) && this.size == localItem.size && this.dateAdded == localItem.dateAdded && this.dateTaken == localItem.dateTaken && this.dateModified == localItem.dateModified && this.startProcessing == localItem.startProcessing && this.endProcessing == localItem.endProcessing && Intrinsics.areEqual(this.md5, localItem.md5) && Intrinsics.areEqual(this.visualDigest, localItem.visualDigest) && this.parentId == localItem.parentId;
        }
        return true;
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

    @Nullable
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

    @Nullable
    public final String getMd5() {
        return this.md5;
    }

    public final long getParentId() {
        return this.parentId;
    }

    public final long getSize() {
        return this.size;
    }

    public final long getStartProcessing() {
        return this.startProcessing;
    }

    @NotNull
    public final ItemType getType() {
        return this.type;
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
        long j = this.id;
        long j2 = this.unifiedId;
        int i = ((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        ItemType itemType = this.type;
        int i2 = 0;
        int hashCode = (i + (itemType != null ? itemType.hashCode() : 0)) * 31;
        String str = this.filePath;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        Long l = this.duration;
        int hashCode3 = (hashCode2 + (l != null ? l.hashCode() : 0)) * 31;
        Long l2 = this.width;
        int hashCode4 = (hashCode3 + (l2 != null ? l2.hashCode() : 0)) * 31;
        Long l3 = this.height;
        int hashCode5 = l3 != null ? l3.hashCode() : 0;
        long j3 = this.size;
        long j4 = this.dateAdded;
        long j5 = this.dateTaken;
        long j6 = this.dateModified;
        long j7 = this.startProcessing;
        long j8 = this.endProcessing;
        int i3 = (((((((((((((hashCode4 + hashCode5) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + ((int) (j5 ^ (j5 >>> 32)))) * 31) + ((int) (j6 ^ (j6 >>> 32)))) * 31) + ((int) (j7 ^ (j7 >>> 32)))) * 31) + ((int) (j8 ^ (j8 >>> 32)))) * 31;
        String str2 = this.md5;
        int hashCode6 = (i3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.visualDigest;
        if (str3 != null) {
            i2 = str3.hashCode();
        }
        long j9 = this.parentId;
        return ((hashCode6 + i2) * 31) + ((int) ((j9 >>> 32) ^ j9));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocalItem(id=");
        outline107.append(this.id);
        outline107.append(", unifiedId=");
        outline107.append(this.unifiedId);
        outline107.append(", type=");
        outline107.append(this.type);
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
    @VisibleForTesting
    @Ignore
    public LocalItem(long j, long j2, @NotNull ItemType type, @Nullable String str, @Nullable Long l, @Nullable Long l2, @Nullable Long l3, long j3, long j4, long j5, long j6, @Nullable String str2, @Nullable String str3, long j7) {
        this(j, j2, type, str, l, l2, l3, j3, j4, j5, j6, 0L, 0L, str2, str3, j7);
        Intrinsics.checkParameterIsNotNull(type, "type");
    }
}
