package com.amazon.photos.uploader.cds.multipart;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MultipartUploadRequestMetadataDao.kt */
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\ba\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H'J\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH'J\u0012\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\bH'¨\u0006\u0012"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;", "", "deleteRequest", "", "requestId", "", "getAll", "", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadata;", "getAllCountByStateRequestId", "state", "Lcom/amazon/photos/uploader/cds/multipart/PartUploadState;", "getMultipartMetadataByRequestId", "getNodeIdForUploadRequest", "", "getServiceUploadIdForUploadRequest", "insert", "multipartUploadRequestMetadata", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface MultipartUploadRequestMetadataDao {
    @Query("DELETE FROM multipart_upload_request_metadata where upload_request_id=:requestId")
    void deleteRequest(long j);

    @Query("SELECT * from multipart_upload_request_metadata")
    @Transaction
    @NotNull
    List<MultipartUploadRequestMetadata> getAll();

    @Query("SELECT COUNT(*) from part_info WHERE upload_request_id=:requestId and part_upload_state = :state")
    long getAllCountByStateRequestId(long j, @NotNull PartUploadState partUploadState);

    @Query("SELECT * from multipart_upload_request_metadata WHERE upload_request_id=:requestId ")
    @Nullable
    MultipartUploadRequestMetadata getMultipartMetadataByRequestId(long j);

    @Query("SELECT node_id from multipart_upload_request_metadata WHERE upload_request_id=:requestId ")
    @Nullable
    String getNodeIdForUploadRequest(long j);

    @Query("SELECT upload_id from multipart_upload_request_metadata WHERE upload_request_id=:requestId ")
    @Nullable
    String getServiceUploadIdForUploadRequest(long j);

    @Insert(onConflict = 1)
    long insert(@NotNull MultipartUploadRequestMetadata multipartUploadRequestMetadata);
}
