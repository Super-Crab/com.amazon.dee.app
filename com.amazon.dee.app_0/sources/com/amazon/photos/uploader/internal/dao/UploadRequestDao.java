package com.amazon.photos.uploader.internal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.alexa.accessory.kota.KotaJobSchedulerService;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadRequestDao.kt */
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0005\b!\u0018\u0000 >2\u00020\u0001:\u0001>B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u001e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH%J(\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\b0\f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0017J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\tH'J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rH'J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\bH'J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0019\u001a\u00020\u0016H'J\b\u0010\u001a\u001a\u00020\u0014H'J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H'J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u0015\u001a\u00020\u0016H'J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u001d\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\tH'J\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160\bJ\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160\bH'J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH'J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u0015\u001a\u00020\u0016H'J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0006H'J\"\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0\bJ$\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0\bH%J\u0010\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\rH'J\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\r0\bH'J$\u0010+\u001a\u00020\u00042\b\u0010,\u001a\u0004\u0018\u00010\u00162\b\u0010-\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000f\u001a\u00020\tH'J\u0018\u0010.\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010/\u001a\u00020\tH'J\u0018\u00100\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\u0006\u00101\u001a\u00020\tH'J\u0018\u00102\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H'J\u0010\u00103\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\rH'J\u0018\u00104\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\u0006\u00105\u001a\u00020\u0014H'J\u001a\u00106\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\b\u00107\u001a\u0004\u0018\u00010\u0016H'J\u0018\u00108\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\u0006\u00109\u001a\u00020:H'J\u0018\u0010;\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010<\u001a\u00020\u0016H'J\u0018\u0010=\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H'¨\u0006?"}, d2 = {"Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "", "()V", "bulkUpdateState", "", "state", "Lcom/amazon/photos/uploader/UploadState;", "requestIds", "", "", "bulkUpdateStateInternal", "cancelUploadRequests", "", "Lcom/amazon/photos/uploader/UploadRequest;", "deleteByRequestId", "requestId", "deleteByState", "deleteRequest", "getAllRequests", "getCountForState", "", "queue", "", "getCountForStateInAllQueues", "getPendingRequestByFilepath", "filepath", "getPendingRequestsCount", "getPrioritizedPendingRequestsForQueue", "getPrioritizedRequestsForState", "getRequestById", "getRequestsByFilePaths", "filePaths", "getRequestsByFilePathsInternal", "getRequestsByIds", "getRequestsForQueue", "getRequestsForState", "getRequestsForStateAndIds", ContactsModuleConstants.CONTACT_IDS, "getRequestsForStateAndIdsInternal", "insertRequest", "request", "insertRequests", "requests", "updateContentSignatures", SierraContentProviderContract.MD5_VALUE, "visualDigest", "updateCreationTimeMillis", "creationTimeMillis", "updateFileSize", "fileSize", "updateQueue", KotaJobSchedulerService.UPDATE_REQUEST_KEY, "updateRequestAttempts", "attempts", "updateRequestBlocker", "blocker", "updateRequestErrorCategory", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "updateRequestErrorCode", "errorCode", "updateRequestState", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class UploadRequestDao {
    private static final int BATCH_SIZE = 900;
    public static final Companion Companion = new Companion(null);

    /* compiled from: UploadRequestDao.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao$Companion;", "", "()V", "BATCH_SIZE", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void bulkUpdateState(UploadState uploadState, List<Long> list) {
        Iterable withIndex;
        int collectionSizeOrDefault;
        if (list.size() <= 900) {
            bulkUpdateStateInternal(uploadState, list);
            return;
        }
        withIndex = CollectionsKt___CollectionsKt.withIndex(list);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : withIndex) {
            Integer valueOf = Integer.valueOf(((IndexedValue) obj).getIndex() / 900);
            Object obj2 = linkedHashMap.get(valueOf);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(valueOf, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList<List<Long>> arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Iterable<IndexedValue> iterable = (Iterable) entry.getValue();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (IndexedValue indexedValue : iterable) {
                arrayList2.add(Long.valueOf(((Number) indexedValue.getValue()).longValue()));
            }
            arrayList.add(arrayList2);
        }
        for (List<Long> list2 : arrayList) {
            bulkUpdateStateInternal(uploadState, list2);
        }
    }

    @Query("UPDATE upload_request SET state = :state WHERE id IN (:requestIds)")
    protected abstract void bulkUpdateStateInternal(@NotNull UploadState uploadState, @NotNull List<Long> list);

    @Transaction
    @NotNull
    public Map<UploadState, List<UploadRequest>> cancelUploadRequests(@NotNull List<Long> requestIds) {
        List<UploadState> listOf;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(requestIds, "requestIds");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new UploadState[]{UploadState.ENQUEUED, UploadState.RUNNING, UploadState.BLOCKED, UploadState.FAILED});
        for (UploadState uploadState : listOf) {
            List<UploadRequest> requestsForStateAndIds = getRequestsForStateAndIds(uploadState, requestIds);
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(requestsForStateAndIds, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (UploadRequest uploadRequest : requestsForStateAndIds) {
                arrayList2.add(Long.valueOf(uploadRequest.getId()));
            }
            arrayList.addAll(arrayList2);
            linkedHashMap.put(uploadState, requestsForStateAndIds);
        }
        bulkUpdateState(UploadState.CANCELLED, arrayList);
        return linkedHashMap;
    }

    @Query("DELETE FROM upload_request WHERE id = :requestId")
    public abstract void deleteByRequestId(long j);

    @Query("DELETE FROM upload_request WHERE state = :state")
    public abstract void deleteByState(@NotNull UploadState uploadState);

    @Delete
    public abstract void deleteRequest(@NotNull UploadRequest uploadRequest);

    @Query("SELECT * FROM upload_request")
    @Transaction
    @NotNull
    public abstract List<UploadRequest> getAllRequests();

    @Query("SELECT COUNT(id) FROM upload_request WHERE state = :state AND queue = :queue")
    public abstract int getCountForState(@NotNull String str, @NotNull UploadState uploadState);

    @Query("SELECT COUNT(id) FROM upload_request WHERE state = :state")
    public abstract int getCountForStateInAllQueues(@NotNull UploadState uploadState);

    @Query("SELECT * FROM upload_request WHERE file_path = :filepath AND state in ('RUNNING', 'BLOCKED', 'ENQUEUED')")
    @Nullable
    public abstract UploadRequest getPendingRequestByFilepath(@NotNull String str);

    @Query("SELECT COUNT(id) FROM upload_request WHERE state in ('RUNNING', 'BLOCKED', 'ENQUEUED')")
    public abstract int getPendingRequestsCount();

    @Query("SELECT COUNT(id) FROM upload_request WHERE queue = :queue AND state in ('RUNNING', 'BLOCKED', 'ENQUEUED')")
    public abstract int getPendingRequestsCount(@NotNull String str);

    @Query("SELECT * FROM upload_request WHERE queue = :queue AND state in ('RUNNING', 'BLOCKED', 'ENQUEUED') ORDER BY priority DESC")
    @Transaction
    @NotNull
    public abstract List<UploadRequest> getPrioritizedPendingRequestsForQueue(@NotNull String str);

    @Query("SELECT * FROM upload_request WHERE state = :state ORDER BY priority DESC")
    @Transaction
    @NotNull
    public abstract List<UploadRequest> getPrioritizedRequestsForState(@NotNull UploadState uploadState);

    @Query("SELECT * FROM upload_request WHERE queue = :queue AND state = :state ORDER BY priority DESC")
    @Transaction
    @NotNull
    public abstract List<UploadRequest> getPrioritizedRequestsForState(@NotNull String str, @NotNull UploadState uploadState);

    @Query("SELECT * FROM upload_request WHERE id = :requestId")
    @NotNull
    public abstract UploadRequest getRequestById(long j);

    @NotNull
    public final List<UploadRequest> getRequestsByFilePaths(@NotNull List<String> filePaths) {
        Iterable withIndex;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(filePaths, "filePaths");
        if (filePaths.size() <= 900) {
            return getRequestsByFilePathsInternal(filePaths);
        }
        withIndex = CollectionsKt___CollectionsKt.withIndex(filePaths);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : withIndex) {
            Integer valueOf = Integer.valueOf(((IndexedValue) obj).getIndex() / 900);
            Object obj2 = linkedHashMap.get(valueOf);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(valueOf, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList<List<String>> arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Iterable<IndexedValue> iterable = (Iterable) entry.getValue();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (IndexedValue indexedValue : iterable) {
                arrayList2.add((String) indexedValue.getValue());
            }
            arrayList.add(arrayList2);
        }
        ArrayList arrayList3 = new ArrayList();
        for (List<String> list : arrayList) {
            arrayList3.addAll(getRequestsByFilePathsInternal(list));
        }
        return arrayList3;
    }

    @Query("SELECT * FROM upload_request WHERE file_path in (:filePaths)")
    @Transaction
    @NotNull
    public abstract List<UploadRequest> getRequestsByFilePathsInternal(@NotNull List<String> list);

    @Query("SELECT * FROM upload_request WHERE id in (:requestIds)")
    @Transaction
    @NotNull
    public abstract List<UploadRequest> getRequestsByIds(@NotNull List<Long> list);

    @Query("SELECT * FROM upload_request WHERE queue = :queue")
    @Transaction
    @NotNull
    public abstract List<UploadRequest> getRequestsForQueue(@NotNull String str);

    @Query("SELECT * FROM upload_request WHERE state = :state")
    @Transaction
    @NotNull
    public abstract List<UploadRequest> getRequestsForState(@NotNull UploadState uploadState);

    @Query("SELECT * FROM upload_request WHERE queue = :queue AND state = :state")
    @Transaction
    @NotNull
    public abstract List<UploadRequest> getRequestsForState(@NotNull String str, @NotNull UploadState uploadState);

    @NotNull
    public final List<UploadRequest> getRequestsForStateAndIds(@NotNull UploadState state, @NotNull List<Long> ids) {
        Iterable withIndex;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        if (ids.size() <= 900) {
            return getRequestsForStateAndIdsInternal(state, ids);
        }
        withIndex = CollectionsKt___CollectionsKt.withIndex(ids);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : withIndex) {
            Integer valueOf = Integer.valueOf(((IndexedValue) obj).getIndex() / 900);
            Object obj2 = linkedHashMap.get(valueOf);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(valueOf, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList<List<Long>> arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Iterable<IndexedValue> iterable = (Iterable) entry.getValue();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (IndexedValue indexedValue : iterable) {
                arrayList2.add(Long.valueOf(((Number) indexedValue.getValue()).longValue()));
            }
            arrayList.add(arrayList2);
        }
        ArrayList arrayList3 = new ArrayList();
        for (List<Long> list : arrayList) {
            arrayList3.addAll(getRequestsForStateAndIdsInternal(state, list));
        }
        return arrayList3;
    }

    @Query("SELECT * FROM upload_request WHERE state = :state AND id in (:ids)")
    @Transaction
    @NotNull
    protected abstract List<UploadRequest> getRequestsForStateAndIdsInternal(@NotNull UploadState uploadState, @NotNull List<Long> list);

    @Insert(onConflict = 1)
    public abstract long insertRequest(@NotNull UploadRequest uploadRequest);

    @Insert(onConflict = 1)
    @NotNull
    public abstract List<Long> insertRequests(@NotNull List<UploadRequest> list);

    @Query("UPDATE upload_request SET md5 = :md5, visual_digest = :visualDigest WHERE id = :requestId")
    public abstract void updateContentSignatures(@Nullable String str, @Nullable String str2, long j);

    @Query("UPDATE upload_request SET creation_time_millis = :creationTimeMillis WHERE id = :requestId")
    public abstract void updateCreationTimeMillis(long j, long j2);

    @Query("UPDATE upload_request SET file_size = :fileSize WHERE id = :requestId")
    public abstract void updateFileSize(long j, long j2);

    @Query("UPDATE upload_request SET queue = :queue WHERE id = :requestId")
    public abstract void updateQueue(long j, @NotNull String str);

    @Update
    public abstract void updateRequest(@NotNull UploadRequest uploadRequest);

    @Query("UPDATE upload_request SET attempt_count = :attempts WHERE id = :requestId")
    public abstract void updateRequestAttempts(long j, int i);

    @Query("UPDATE upload_request SET blocker = :blocker WHERE id = :requestId")
    public abstract void updateRequestBlocker(long j, @Nullable String str);

    @Query("UPDATE upload_request SET error_category = :errorCategory WHERE id = :requestId")
    public abstract void updateRequestErrorCategory(long j, @NotNull UploadErrorCategory uploadErrorCategory);

    @Query("UPDATE upload_request SET error_code = :errorCode WHERE id = :requestId")
    public abstract void updateRequestErrorCode(long j, @NotNull String str);

    @Query("UPDATE upload_request SET state = :state WHERE id = :requestId")
    public abstract void updateRequestState(long j, @NotNull UploadState uploadState);
}
