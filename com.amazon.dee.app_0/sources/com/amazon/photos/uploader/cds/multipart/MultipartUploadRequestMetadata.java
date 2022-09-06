package com.amazon.photos.uploader.cds.multipart;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MultipartUploadRequestMetadata.kt */
@Entity(tableName = "multipart_upload_request_metadata")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J^\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006&"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadata;", "", "uploadRequestId", "", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "", "uploadId", "partSize", "totalNumberOfParts", "multipartUploadStartTime", "multipartUploadExpirationTime", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "getMultipartUploadExpirationTime", "()Ljava/lang/String;", "getMultipartUploadStartTime", "getNodeId", "getPartSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTotalNumberOfParts", "getUploadId", "getUploadRequestId", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadata;", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipartUploadRequestMetadata {
    @ColumnInfo(name = "multipart_expiration_time")
    @Nullable
    private final String multipartUploadExpirationTime;
    @ColumnInfo(name = "multipart_start_time")
    @Nullable
    private final String multipartUploadStartTime;
    @ColumnInfo(name = "node_id")
    @NotNull
    private final String nodeId;
    @ColumnInfo(name = "part_size")
    @Nullable
    private final Long partSize;
    @ColumnInfo(name = "total_parts")
    @Nullable
    private final Long totalNumberOfParts;
    @ColumnInfo(name = "upload_id")
    @Nullable
    private final String uploadId;
    @PrimaryKey
    @ColumnInfo(name = "upload_request_id")
    private final long uploadRequestId;

    public MultipartUploadRequestMetadata(long j, @NotNull String nodeId, @Nullable String str, @Nullable Long l, @Nullable Long l2, @Nullable String str2, @Nullable String str3) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        this.uploadRequestId = j;
        this.nodeId = nodeId;
        this.uploadId = str;
        this.partSize = l;
        this.totalNumberOfParts = l2;
        this.multipartUploadStartTime = str2;
        this.multipartUploadExpirationTime = str3;
    }

    public final long component1() {
        return this.uploadRequestId;
    }

    @NotNull
    public final String component2() {
        return this.nodeId;
    }

    @Nullable
    public final String component3() {
        return this.uploadId;
    }

    @Nullable
    public final Long component4() {
        return this.partSize;
    }

    @Nullable
    public final Long component5() {
        return this.totalNumberOfParts;
    }

    @Nullable
    public final String component6() {
        return this.multipartUploadStartTime;
    }

    @Nullable
    public final String component7() {
        return this.multipartUploadExpirationTime;
    }

    @NotNull
    public final MultipartUploadRequestMetadata copy(long j, @NotNull String nodeId, @Nullable String str, @Nullable Long l, @Nullable Long l2, @Nullable String str2, @Nullable String str3) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        return new MultipartUploadRequestMetadata(j, nodeId, str, l, l2, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof MultipartUploadRequestMetadata)) {
                return false;
            }
            MultipartUploadRequestMetadata multipartUploadRequestMetadata = (MultipartUploadRequestMetadata) obj;
            return this.uploadRequestId == multipartUploadRequestMetadata.uploadRequestId && Intrinsics.areEqual(this.nodeId, multipartUploadRequestMetadata.nodeId) && Intrinsics.areEqual(this.uploadId, multipartUploadRequestMetadata.uploadId) && Intrinsics.areEqual(this.partSize, multipartUploadRequestMetadata.partSize) && Intrinsics.areEqual(this.totalNumberOfParts, multipartUploadRequestMetadata.totalNumberOfParts) && Intrinsics.areEqual(this.multipartUploadStartTime, multipartUploadRequestMetadata.multipartUploadStartTime) && Intrinsics.areEqual(this.multipartUploadExpirationTime, multipartUploadRequestMetadata.multipartUploadExpirationTime);
        }
        return true;
    }

    @Nullable
    public final String getMultipartUploadExpirationTime() {
        return this.multipartUploadExpirationTime;
    }

    @Nullable
    public final String getMultipartUploadStartTime() {
        return this.multipartUploadStartTime;
    }

    @NotNull
    public final String getNodeId() {
        return this.nodeId;
    }

    @Nullable
    public final Long getPartSize() {
        return this.partSize;
    }

    @Nullable
    public final Long getTotalNumberOfParts() {
        return this.totalNumberOfParts;
    }

    @Nullable
    public final String getUploadId() {
        return this.uploadId;
    }

    public final long getUploadRequestId() {
        return this.uploadRequestId;
    }

    public int hashCode() {
        long j = this.uploadRequestId;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.nodeId;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.uploadId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Long l = this.partSize;
        int hashCode3 = (hashCode2 + (l != null ? l.hashCode() : 0)) * 31;
        Long l2 = this.totalNumberOfParts;
        int hashCode4 = (hashCode3 + (l2 != null ? l2.hashCode() : 0)) * 31;
        String str3 = this.multipartUploadStartTime;
        int hashCode5 = (hashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.multipartUploadExpirationTime;
        if (str4 != null) {
            i2 = str4.hashCode();
        }
        return hashCode5 + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MultipartUploadRequestMetadata(uploadRequestId=");
        outline107.append(this.uploadRequestId);
        outline107.append(", nodeId=");
        outline107.append(this.nodeId);
        outline107.append(", uploadId=");
        outline107.append(this.uploadId);
        outline107.append(", partSize=");
        outline107.append(this.partSize);
        outline107.append(", totalNumberOfParts=");
        outline107.append(this.totalNumberOfParts);
        outline107.append(", multipartUploadStartTime=");
        outline107.append(this.multipartUploadStartTime);
        outline107.append(", multipartUploadExpirationTime=");
        return GeneratedOutlineSupport1.outline91(outline107, this.multipartUploadExpirationTime, ")");
    }

    public /* synthetic */ MultipartUploadRequestMetadata(long j, String str, String str2, Long l, Long l2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : l, (i & 16) != 0 ? null : l2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4);
    }
}
