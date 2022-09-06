package com.amazon.photos.discovery.internal.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MutableCloudItem.kt */
@Entity(indices = {@Index(unique = true, value = {"id"}), @Index(unique = true, value = {"node_id"})}, tableName = "cloud_item")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007BA\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\b\u0010\u001e\u001a\u00020\u0000H\u0016J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003JS\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0096\u0002J\b\u0010+\u001a\u00020,H\u0016J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\n\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\t\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001e\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R \u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0004¨\u0006."}, d2 = {"Lcom/amazon/photos/discovery/internal/model/MutableCloudItem;", "", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "", "(Ljava/lang/String;)V", "unifiedId", "", "(Ljava/lang/String;J)V", "id", "dateUploaded", "dateTaken", SierraContentProviderContract.MD5_VALUE, "visualDigest", "(JJLjava/lang/String;JJLjava/lang/String;Ljava/lang/String;)V", "getDateTaken", "()J", "setDateTaken", "(J)V", "getDateUploaded", "setDateUploaded", "getId", "setId", "getMd5", "()Ljava/lang/String;", "setMd5", "getNodeId", "getUnifiedId", "setUnifiedId", "getVisualDigest", "setVisualDigest", "clone", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MutableCloudItem implements Cloneable {
    @ColumnInfo(name = "date_taken")
    private long dateTaken;
    @ColumnInfo(name = "date_uploaded")
    private long dateUploaded;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = SierraContentProviderContract.MD5_VALUE)
    @Nullable
    private String md5;
    @ColumnInfo(name = "node_id")
    @NotNull
    private final String nodeId;
    @ColumnInfo(name = "unified_id")
    private long unifiedId;
    @ColumnInfo(name = "visual_digest")
    @Nullable
    private String visualDigest;

    public MutableCloudItem(long j, long j2, @NotNull String nodeId, long j3, long j4, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        this.id = j;
        this.unifiedId = j2;
        this.nodeId = nodeId;
        this.dateUploaded = j3;
        this.dateTaken = j4;
        this.md5 = str;
        this.visualDigest = str2;
    }

    public static /* synthetic */ MutableCloudItem copy$default(MutableCloudItem mutableCloudItem, long j, long j2, String str, long j3, long j4, String str2, String str3, int i, Object obj) {
        return mutableCloudItem.copy((i & 1) != 0 ? mutableCloudItem.id : j, (i & 2) != 0 ? mutableCloudItem.unifiedId : j2, (i & 4) != 0 ? mutableCloudItem.nodeId : str, (i & 8) != 0 ? mutableCloudItem.dateUploaded : j3, (i & 16) != 0 ? mutableCloudItem.dateTaken : j4, (i & 32) != 0 ? mutableCloudItem.md5 : str2, (i & 64) != 0 ? mutableCloudItem.visualDigest : str3);
    }

    public final long component1() {
        return this.id;
    }

    public final long component2() {
        return this.unifiedId;
    }

    @NotNull
    public final String component3() {
        return this.nodeId;
    }

    public final long component4() {
        return this.dateUploaded;
    }

    public final long component5() {
        return this.dateTaken;
    }

    @Nullable
    public final String component6() {
        return this.md5;
    }

    @Nullable
    public final String component7() {
        return this.visualDigest;
    }

    @NotNull
    public final MutableCloudItem copy(long j, long j2, @NotNull String nodeId, long j3, long j4, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        return new MutableCloudItem(j, j2, nodeId, j3, j4, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(MutableCloudItem.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            MutableCloudItem mutableCloudItem = (MutableCloudItem) obj;
            return this.id == mutableCloudItem.id && this.unifiedId == mutableCloudItem.unifiedId && !(Intrinsics.areEqual(this.nodeId, mutableCloudItem.nodeId) ^ true) && this.dateUploaded == mutableCloudItem.dateUploaded && this.dateTaken == mutableCloudItem.dateTaken && !(Intrinsics.areEqual(this.md5, mutableCloudItem.md5) ^ true) && !(Intrinsics.areEqual(this.visualDigest, mutableCloudItem.visualDigest) ^ true);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.amazon.photos.discovery.internal.model.MutableCloudItem");
    }

    public final long getDateTaken() {
        return this.dateTaken;
    }

    public final long getDateUploaded() {
        return this.dateUploaded;
    }

    public final long getId() {
        return this.id;
    }

    @Nullable
    public final String getMd5() {
        return this.md5;
    }

    @NotNull
    public final String getNodeId() {
        return this.nodeId;
    }

    public final long getUnifiedId() {
        return this.unifiedId;
    }

    @Nullable
    public final String getVisualDigest() {
        return this.visualDigest;
    }

    public int hashCode() {
        return this.nodeId.hashCode();
    }

    public final void setDateTaken(long j) {
        this.dateTaken = j;
    }

    public final void setDateUploaded(long j) {
        this.dateUploaded = j;
    }

    public final void setId(long j) {
        this.id = j;
    }

    public final void setMd5(@Nullable String str) {
        this.md5 = str;
    }

    public final void setUnifiedId(long j) {
        this.unifiedId = j;
    }

    public final void setVisualDigest(@Nullable String str) {
        this.visualDigest = str;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MutableCloudItem(id=");
        outline107.append(this.id);
        outline107.append(", unifiedId=");
        outline107.append(this.unifiedId);
        outline107.append(", nodeId=");
        outline107.append(this.nodeId);
        outline107.append(", dateUploaded=");
        outline107.append(this.dateUploaded);
        outline107.append(", dateTaken=");
        outline107.append(this.dateTaken);
        outline107.append(", md5=");
        outline107.append(this.md5);
        outline107.append(", visualDigest=");
        return GeneratedOutlineSupport1.outline91(outline107, this.visualDigest, ")");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Ignore
    public MutableCloudItem(@NotNull String nodeId) {
        this(0L, 0L, nodeId, 0L, 0L, null, null);
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
    }

    @NotNull
    /* renamed from: clone */
    public MutableCloudItem m4338clone() {
        return copy$default(this, 0L, 0L, null, 0L, 0L, null, null, 127, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Ignore
    public MutableCloudItem(@NotNull String nodeId, long j) {
        this(0L, j, nodeId, 0L, 0L, null, null);
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
    }
}
