package com.amazon.photos.uploader.cds.multipart;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PartInfo.kt */
@Entity(indices = {@Index(name = "idx_upload_request_id", value = {"upload_request_id"})}, primaryKeys = {"part_id", "upload_request_id"}, tableName = "part_info")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001By\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J}\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020/HÖ\u0001J\t\u00100\u001a\u00020\u000bHÖ\u0001R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014¨\u00061"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/PartInfo;", "", "partId", "", "uploadRequestId", "partUploadState", "Lcom/amazon/photos/uploader/cds/multipart/PartUploadState;", "partEnqueueTimestamp", "partUploadStartTimestamp", "partUploadCompleteTimestamp", "partMd5", "", "partSize", "partOffset", "serviceUploadId", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "(JJLcom/amazon/photos/uploader/cds/multipart/PartUploadState;JJJLjava/lang/String;JJLjava/lang/String;Ljava/lang/String;)V", "getNodeId", "()Ljava/lang/String;", "getPartEnqueueTimestamp", "()J", "getPartId", "getPartMd5", "getPartOffset", "getPartSize", "getPartUploadCompleteTimestamp", "getPartUploadStartTimestamp", "getPartUploadState", "()Lcom/amazon/photos/uploader/cds/multipart/PartUploadState;", "getServiceUploadId", "getUploadRequestId", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PartInfo {
    @ColumnInfo(name = "node_id")
    @Nullable
    private final String nodeId;
    @ColumnInfo(name = "part_enqueue_timestamp")
    private final long partEnqueueTimestamp;
    @ColumnInfo(name = "part_id")
    private final long partId;
    @ColumnInfo(name = "part_md5")
    @Nullable
    private final String partMd5;
    @ColumnInfo(name = "part_offset")
    private final long partOffset;
    @ColumnInfo(name = "part_size")
    private final long partSize;
    @ColumnInfo(name = "part_upload_complete_timestamp")
    private final long partUploadCompleteTimestamp;
    @ColumnInfo(name = "part_upload_start_timestamp")
    private final long partUploadStartTimestamp;
    @ColumnInfo(name = "part_upload_state")
    @NotNull
    private final PartUploadState partUploadState;
    @ColumnInfo(name = "service_upload_id")
    @Nullable
    private final String serviceUploadId;
    @ColumnInfo(name = "upload_request_id")
    private final long uploadRequestId;

    public PartInfo() {
        this(0L, 0L, null, 0L, 0L, 0L, null, 0L, 0L, null, null, 2047, null);
    }

    public PartInfo(long j, long j2, @NotNull PartUploadState partUploadState, long j3, long j4, long j5, @Nullable String str, long j6, long j7, @Nullable String str2, @Nullable String str3) {
        Intrinsics.checkParameterIsNotNull(partUploadState, "partUploadState");
        this.partId = j;
        this.uploadRequestId = j2;
        this.partUploadState = partUploadState;
        this.partEnqueueTimestamp = j3;
        this.partUploadStartTimestamp = j4;
        this.partUploadCompleteTimestamp = j5;
        this.partMd5 = str;
        this.partSize = j6;
        this.partOffset = j7;
        this.serviceUploadId = str2;
        this.nodeId = str3;
    }

    public final long component1() {
        return this.partId;
    }

    @Nullable
    public final String component10() {
        return this.serviceUploadId;
    }

    @Nullable
    public final String component11() {
        return this.nodeId;
    }

    public final long component2() {
        return this.uploadRequestId;
    }

    @NotNull
    public final PartUploadState component3() {
        return this.partUploadState;
    }

    public final long component4() {
        return this.partEnqueueTimestamp;
    }

    public final long component5() {
        return this.partUploadStartTimestamp;
    }

    public final long component6() {
        return this.partUploadCompleteTimestamp;
    }

    @Nullable
    public final String component7() {
        return this.partMd5;
    }

    public final long component8() {
        return this.partSize;
    }

    public final long component9() {
        return this.partOffset;
    }

    @NotNull
    public final PartInfo copy(long j, long j2, @NotNull PartUploadState partUploadState, long j3, long j4, long j5, @Nullable String str, long j6, long j7, @Nullable String str2, @Nullable String str3) {
        Intrinsics.checkParameterIsNotNull(partUploadState, "partUploadState");
        return new PartInfo(j, j2, partUploadState, j3, j4, j5, str, j6, j7, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof PartInfo)) {
                return false;
            }
            PartInfo partInfo = (PartInfo) obj;
            return this.partId == partInfo.partId && this.uploadRequestId == partInfo.uploadRequestId && Intrinsics.areEqual(this.partUploadState, partInfo.partUploadState) && this.partEnqueueTimestamp == partInfo.partEnqueueTimestamp && this.partUploadStartTimestamp == partInfo.partUploadStartTimestamp && this.partUploadCompleteTimestamp == partInfo.partUploadCompleteTimestamp && Intrinsics.areEqual(this.partMd5, partInfo.partMd5) && this.partSize == partInfo.partSize && this.partOffset == partInfo.partOffset && Intrinsics.areEqual(this.serviceUploadId, partInfo.serviceUploadId) && Intrinsics.areEqual(this.nodeId, partInfo.nodeId);
        }
        return true;
    }

    @Nullable
    public final String getNodeId() {
        return this.nodeId;
    }

    public final long getPartEnqueueTimestamp() {
        return this.partEnqueueTimestamp;
    }

    public final long getPartId() {
        return this.partId;
    }

    @Nullable
    public final String getPartMd5() {
        return this.partMd5;
    }

    public final long getPartOffset() {
        return this.partOffset;
    }

    public final long getPartSize() {
        return this.partSize;
    }

    public final long getPartUploadCompleteTimestamp() {
        return this.partUploadCompleteTimestamp;
    }

    public final long getPartUploadStartTimestamp() {
        return this.partUploadStartTimestamp;
    }

    @NotNull
    public final PartUploadState getPartUploadState() {
        return this.partUploadState;
    }

    @Nullable
    public final String getServiceUploadId() {
        return this.serviceUploadId;
    }

    public final long getUploadRequestId() {
        return this.uploadRequestId;
    }

    public int hashCode() {
        long j = this.partId;
        long j2 = this.uploadRequestId;
        int i = ((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        PartUploadState partUploadState = this.partUploadState;
        int i2 = 0;
        int hashCode = partUploadState != null ? partUploadState.hashCode() : 0;
        long j3 = this.partEnqueueTimestamp;
        long j4 = this.partUploadStartTimestamp;
        long j5 = this.partUploadCompleteTimestamp;
        int i3 = (((((((i + hashCode) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + ((int) (j5 ^ (j5 >>> 32)))) * 31;
        String str = this.partMd5;
        int hashCode2 = str != null ? str.hashCode() : 0;
        long j6 = this.partSize;
        long j7 = this.partOffset;
        int i4 = (((((i3 + hashCode2) * 31) + ((int) (j6 ^ (j6 >>> 32)))) * 31) + ((int) ((j7 >>> 32) ^ j7))) * 31;
        String str2 = this.serviceUploadId;
        int hashCode3 = (i4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.nodeId;
        if (str3 != null) {
            i2 = str3.hashCode();
        }
        return hashCode3 + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PartInfo(partId=");
        outline107.append(this.partId);
        outline107.append(", uploadRequestId=");
        outline107.append(this.uploadRequestId);
        outline107.append(", partUploadState=");
        outline107.append(this.partUploadState);
        outline107.append(", partEnqueueTimestamp=");
        outline107.append(this.partEnqueueTimestamp);
        outline107.append(", partUploadStartTimestamp=");
        outline107.append(this.partUploadStartTimestamp);
        outline107.append(", partUploadCompleteTimestamp=");
        outline107.append(this.partUploadCompleteTimestamp);
        outline107.append(", partMd5=");
        outline107.append(this.partMd5);
        outline107.append(", partSize=");
        outline107.append(this.partSize);
        outline107.append(", partOffset=");
        outline107.append(this.partOffset);
        outline107.append(", serviceUploadId=");
        outline107.append(this.serviceUploadId);
        outline107.append(", nodeId=");
        return GeneratedOutlineSupport1.outline91(outline107, this.nodeId, ")");
    }

    public /* synthetic */ PartInfo(long j, long j2, PartUploadState partUploadState, long j3, long j4, long j5, String str, long j6, long j7, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? 0L : j2, (i & 4) != 0 ? PartUploadState.ENQUEUED : partUploadState, (i & 8) != 0 ? System.currentTimeMillis() : j3, (i & 16) != 0 ? 0L : j4, (i & 32) != 0 ? 0L : j5, (i & 64) != 0 ? null : str, (i & 128) != 0 ? 0L : j6, (i & 256) != 0 ? 0L : j7, (i & 512) != 0 ? null : str2, (i & 1024) != 0 ? null : str3);
    }
}
