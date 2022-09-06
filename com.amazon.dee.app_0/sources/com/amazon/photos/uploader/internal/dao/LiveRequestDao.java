package com.amazon.photos.uploader.internal.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.dao.RequestSummary;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: LiveRequestDao.kt */
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\ba\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\t\u001a\u00020\nH'J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001e\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f2\u0006\u0010\t\u001a\u00020\nH'J,\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102\u0006\u0010\t\u001a\u00020\nH'J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00120\u00032\u0006\u0010\t\u001a\u00020\nH'J*\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00120\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102\u0006\u0010\t\u001a\u00020\nH'J$\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0010H'J2\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0010H'J\"\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00120\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0010H'J0\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00120\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0010H'¨\u0006\u0016"}, d2 = {"Lcom/amazon/photos/uploader/internal/dao/LiveRequestDao;", "", "getPendingRequestSummary", "Landroidx/lifecycle/LiveData;", "Lcom/amazon/photos/uploader/dao/RequestSummary;", "queue", "", "getRequestSummary", "getRequestSummaryForState", "state", "Lcom/amazon/photos/uploader/UploadState;", "getRequestsForStateDataSource", "Landroidx/paging/DataSource$Factory;", "", "Lcom/amazon/photos/uploader/UploadRequest;", "queues", "", "getRequestsForStateLiveData", "", "getRequestsForStatesDataSource", "states", "getRequestsForStatesLiveData", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface LiveRequestDao {
    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state in ('RUNNING', 'BLOCKED', 'ENQUEUED')")
    @NotNull
    LiveData<RequestSummary> getPendingRequestSummary();

    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE queue = :queue AND state in ('RUNNING', 'BLOCKED', 'ENQUEUED')")
    @NotNull
    LiveData<RequestSummary> getPendingRequestSummary(@NotNull String str);

    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request")
    @NotNull
    LiveData<RequestSummary> getRequestSummary();

    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state = :state")
    @NotNull
    LiveData<RequestSummary> getRequestSummaryForState(@NotNull UploadState uploadState);

    @Query("SELECT COUNT(id) as count, SUM(file_size) as totalSize FROM upload_request WHERE state = :state AND queue = :queue")
    @NotNull
    LiveData<RequestSummary> getRequestSummaryForState(@NotNull UploadState uploadState, @NotNull String str);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATE_QUERY)
    @Transaction
    @NotNull
    DataSource.Factory<Integer, UploadRequest> getRequestsForStateDataSource(@NotNull UploadState uploadState);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATE_WITH_QUEUE)
    @Transaction
    @NotNull
    DataSource.Factory<Integer, UploadRequest> getRequestsForStateDataSource(@NotNull Set<String> set, @NotNull UploadState uploadState);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATE_QUERY)
    @Transaction
    @NotNull
    LiveData<List<UploadRequest>> getRequestsForStateLiveData(@NotNull UploadState uploadState);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATE_WITH_QUEUE)
    @Transaction
    @NotNull
    LiveData<List<UploadRequest>> getRequestsForStateLiveData(@NotNull Set<String> set, @NotNull UploadState uploadState);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATES_QUERY)
    @Transaction
    @NotNull
    DataSource.Factory<Integer, UploadRequest> getRequestsForStatesDataSource(@NotNull Set<? extends UploadState> set);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATES_WITH_QUEUE)
    @Transaction
    @NotNull
    DataSource.Factory<Integer, UploadRequest> getRequestsForStatesDataSource(@NotNull Set<String> set, @NotNull Set<? extends UploadState> set2);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATES_QUERY)
    @Transaction
    @NotNull
    LiveData<List<UploadRequest>> getRequestsForStatesLiveData(@NotNull Set<? extends UploadState> set);

    @Query(UploadStateQueries.GET_REQUESTS_FOR_STATES_WITH_QUEUE)
    @Transaction
    @NotNull
    LiveData<List<UploadRequest>> getRequestsForStatesLiveData(@NotNull Set<String> set, @NotNull Set<? extends UploadState> set2);
}
