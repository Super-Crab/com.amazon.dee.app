package com.amazon.photos.discovery.model;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CloudItem.kt */
@DatabaseView("SELECT * FROM cloud_item")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003JS\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011¨\u0006#"}, d2 = {"Lcom/amazon/photos/discovery/model/CloudItem;", "", "id", "", "unifiedId", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "", "dateUploaded", "dateTaken", SierraContentProviderContract.MD5_VALUE, "visualDigest", "(JJLjava/lang/String;JJLjava/lang/String;Ljava/lang/String;)V", "getDateTaken", "()J", "getDateUploaded", "getId", "getMd5", "()Ljava/lang/String;", "getNodeId", "getUnifiedId", "getVisualDigest", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CloudItem {
    @ColumnInfo(name = "date_taken")
    private final long dateTaken;
    @ColumnInfo(name = "date_uploaded")
    private final long dateUploaded;
    @ColumnInfo(name = "id")
    private final long id;
    @ColumnInfo(name = SierraContentProviderContract.MD5_VALUE)
    @Nullable
    private final String md5;
    @ColumnInfo(name = "node_id")
    @NotNull
    private final String nodeId;
    @ColumnInfo(name = "unified_id")
    private final long unifiedId;
    @ColumnInfo(name = "visual_digest")
    @Nullable
    private final String visualDigest;

    public CloudItem(long j, long j2, @NotNull String nodeId, long j3, long j4, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        this.id = j;
        this.unifiedId = j2;
        this.nodeId = nodeId;
        this.dateUploaded = j3;
        this.dateTaken = j4;
        this.md5 = str;
        this.visualDigest = str2;
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
    public final CloudItem copy(long j, long j2, @NotNull String nodeId, long j3, long j4, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        return new CloudItem(j, j2, nodeId, j3, j4, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof CloudItem)) {
                return false;
            }
            CloudItem cloudItem = (CloudItem) obj;
            return this.id == cloudItem.id && this.unifiedId == cloudItem.unifiedId && Intrinsics.areEqual(this.nodeId, cloudItem.nodeId) && this.dateUploaded == cloudItem.dateUploaded && this.dateTaken == cloudItem.dateTaken && Intrinsics.areEqual(this.md5, cloudItem.md5) && Intrinsics.areEqual(this.visualDigest, cloudItem.visualDigest);
        }
        return true;
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
        long j = this.id;
        long j2 = this.unifiedId;
        int i = ((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        String str = this.nodeId;
        int i2 = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        long j3 = this.dateUploaded;
        long j4 = this.dateTaken;
        int i3 = (((((i + hashCode) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) ((j4 >>> 32) ^ j4))) * 31;
        String str2 = this.md5;
        int hashCode2 = (i3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.visualDigest;
        if (str3 != null) {
            i2 = str3.hashCode();
        }
        return hashCode2 + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CloudItem(id=");
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
}
