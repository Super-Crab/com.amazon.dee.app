package com.amazon.photos.uploader.internal.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.dao.RequestSummary;
import com.amazon.photos.uploader.dao.StateWithCount;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: SnapshotRequestDao.kt */
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\ba\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H'J\b\u0010\t\u001a\u00020\u0003H'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH'J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH'J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\u0006\u0010\u000b\u001a\u00020\fH'J$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u000b\u001a\u00020\fH'J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH'J*\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH'J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH'J\b\u0010\u0016\u001a\u00020\u0003H'¨\u0006\u0017"}, d2 = {"Lcom/amazon/photos/uploader/internal/dao/SnapshotRequestDao;", "", "getPendingRequestSummary", "Lcom/amazon/photos/uploader/dao/RequestSummary;", "queue", "", "getRequestCountByStates", "", "Lcom/amazon/photos/uploader/dao/StateWithCount;", "getRequestSummary", "getRequestSummaryForState", "state", "Lcom/amazon/photos/uploader/UploadState;", "getRequestSummaryForStates", "states", "", "getRequestsForState", "Lcom/amazon/photos/uploader/UploadRequest;", "queues", "getRequestsForStates", "getRequestsWithBlockers", "blockers", "getUploadedToFamilyVaultSummary", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface SnapshotRequestDao {
    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state in ('RUNNING', 'BLOCKED', 'ENQUEUED')")
    @NotNull
    RequestSummary getPendingRequestSummary();

    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE queue = :queue AND state in ('RUNNING', 'BLOCKED', 'ENQUEUED')")
    @NotNull
    RequestSummary getPendingRequestSummary(@NotNull String str);

    @Query("SELECT state, COUNT(*) AS count FROM upload_request GROUP BY (state)")
    @NotNull
    List<StateWithCount> getRequestCountByStates();

    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request")
    @NotNull
    RequestSummary getRequestSummary();

    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state = :state")
    @NotNull
    RequestSummary getRequestSummaryForState(@NotNull UploadState uploadState);

    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state = :state AND queue = :queue")
    @NotNull
    RequestSummary getRequestSummaryForState(@NotNull UploadState uploadState, @NotNull String str);

    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state IN (:states)")
    @NotNull
    RequestSummary getRequestSummaryForStates(@NotNull Set<? extends UploadState> set);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATE_QUERY)
    @Transaction
    @NotNull
    List<UploadRequest> getRequestsForState(@NotNull UploadState uploadState);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATE_WITH_QUEUE)
    @Transaction
    @NotNull
    List<UploadRequest> getRequestsForState(@NotNull Set<String> set, @NotNull UploadState uploadState);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATES_QUERY)
    @Transaction
    @NotNull
    List<UploadRequest> getRequestsForStates(@NotNull Set<? extends UploadState> set);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATES_WITH_QUEUE)
    @Transaction
    @NotNull
    List<UploadRequest> getRequestsForStates(@NotNull Set<String> set, @NotNull Set<? extends UploadState> set2);

    @Query("SELECT * FROM upload_request WHERE state in ('RUNNING', 'BLOCKED', 'ENQUEUED') AND blocker IN(:blockers) ORDER BY id DESC")
    @Transaction
    @NotNull
    List<UploadRequest> getRequestsWithBlockers(@NotNull Set<String> set);

    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state = 'SUCCEEDED' AND add_to_family_vault = 1 ORDER BY id DESC")
    @NotNull
    RequestSummary getUploadedToFamilyVaultSummary();
}
