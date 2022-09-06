package com.amazon.photos.uploader.internal.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.internal.Converters;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public final class UploadRequestDao_Impl extends UploadRequestDao {
    private final Converters __converters = new Converters();
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<UploadRequest> __deletionAdapterOfUploadRequest;
    private final EntityInsertionAdapter<UploadRequest> __insertionAdapterOfUploadRequest;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByRequestId;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByState;
    private final SharedSQLiteStatement __preparedStmtOfUpdateContentSignatures;
    private final SharedSQLiteStatement __preparedStmtOfUpdateCreationTimeMillis;
    private final SharedSQLiteStatement __preparedStmtOfUpdateFileSize;
    private final SharedSQLiteStatement __preparedStmtOfUpdateQueue;
    private final SharedSQLiteStatement __preparedStmtOfUpdateRequestAttempts;
    private final SharedSQLiteStatement __preparedStmtOfUpdateRequestBlocker;
    private final SharedSQLiteStatement __preparedStmtOfUpdateRequestErrorCategory;
    private final SharedSQLiteStatement __preparedStmtOfUpdateRequestErrorCode;
    private final SharedSQLiteStatement __preparedStmtOfUpdateRequestState;
    private final EntityDeletionOrUpdateAdapter<UploadRequest> __updateAdapterOfUploadRequest;

    public UploadRequestDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfUploadRequest = new EntityInsertionAdapter<UploadRequest>(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `upload_request` (`id`,`file_path`,`upload_path`,`content_date`,`md5`,`visual_digest`,`suppress_duplication`,`rename_on_name_conflict`,`upload_category`,`state`,`queue`,`current_progress`,`max_progress`,`error_code`,`error_category`,`blocker`,`total_attempt_count`,`attempt_count`,`max_attempts_exceeded`,`creation_time_millis`,`file_size`,`priority`,`add_to_family_vault`,`app_data`,`parent_id`,`content_uri`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, UploadRequest uploadRequest) {
                supportSQLiteStatement.bindLong(1, uploadRequest.getId());
                if (uploadRequest.getFilePath() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, uploadRequest.getFilePath());
                }
                if (uploadRequest.getUploadPath() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, uploadRequest.getUploadPath());
                }
                if (uploadRequest.getContentDate() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, uploadRequest.getContentDate());
                }
                if (uploadRequest.getMd5() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, uploadRequest.getMd5());
                }
                if (uploadRequest.getVisualDigest() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, uploadRequest.getVisualDigest());
                }
                supportSQLiteStatement.bindLong(7, uploadRequest.getSuppressDeduplication() ? 1L : 0L);
                supportSQLiteStatement.bindLong(8, uploadRequest.getRenameOnNameConflict() ? 1L : 0L);
                if (uploadRequest.getUploadCategory() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, uploadRequest.getUploadCategory());
                }
                String fromUploadState = UploadRequestDao_Impl.this.__converters.fromUploadState(uploadRequest.getState());
                if (fromUploadState == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, fromUploadState);
                }
                if (uploadRequest.getQueue() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, uploadRequest.getQueue());
                }
                supportSQLiteStatement.bindLong(12, uploadRequest.getCurrentProgress());
                supportSQLiteStatement.bindLong(13, uploadRequest.getMaxProgress());
                if (uploadRequest.getErrorCode() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, uploadRequest.getErrorCode());
                }
                String fromUploadErrorCategory = UploadRequestDao_Impl.this.__converters.fromUploadErrorCategory(uploadRequest.getErrorCategory());
                if (fromUploadErrorCategory == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, fromUploadErrorCategory);
                }
                String fromBlocker = UploadRequestDao_Impl.this.__converters.fromBlocker(uploadRequest.getBlocker());
                if (fromBlocker == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, fromBlocker);
                }
                supportSQLiteStatement.bindLong(17, uploadRequest.getTotalAttemptCount());
                supportSQLiteStatement.bindLong(18, uploadRequest.getAttemptCount());
                supportSQLiteStatement.bindLong(19, uploadRequest.getMaxAttemptsExceeded() ? 1L : 0L);
                supportSQLiteStatement.bindLong(20, uploadRequest.getCreationTimeMillis());
                supportSQLiteStatement.bindLong(21, uploadRequest.getFileSize());
                supportSQLiteStatement.bindLong(22, uploadRequest.getPriority());
                supportSQLiteStatement.bindLong(23, uploadRequest.getAddToFamilyVault() ? 1L : 0L);
                if (uploadRequest.getAppData() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, uploadRequest.getAppData());
                }
                if (uploadRequest.getParentId() == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, uploadRequest.getParentId());
                }
                String fromContentUri = UploadRequestDao_Impl.this.__converters.fromContentUri(uploadRequest.getContentUri());
                if (fromContentUri == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, fromContentUri);
                }
            }
        };
        this.__deletionAdapterOfUploadRequest = new EntityDeletionOrUpdateAdapter<UploadRequest>(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `upload_request` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, UploadRequest uploadRequest) {
                supportSQLiteStatement.bindLong(1, uploadRequest.getId());
            }
        };
        this.__updateAdapterOfUploadRequest = new EntityDeletionOrUpdateAdapter<UploadRequest>(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `upload_request` SET `id` = ?,`file_path` = ?,`upload_path` = ?,`content_date` = ?,`md5` = ?,`visual_digest` = ?,`suppress_duplication` = ?,`rename_on_name_conflict` = ?,`upload_category` = ?,`state` = ?,`queue` = ?,`current_progress` = ?,`max_progress` = ?,`error_code` = ?,`error_category` = ?,`blocker` = ?,`total_attempt_count` = ?,`attempt_count` = ?,`max_attempts_exceeded` = ?,`creation_time_millis` = ?,`file_size` = ?,`priority` = ?,`add_to_family_vault` = ?,`app_data` = ?,`parent_id` = ?,`content_uri` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, UploadRequest uploadRequest) {
                supportSQLiteStatement.bindLong(1, uploadRequest.getId());
                if (uploadRequest.getFilePath() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, uploadRequest.getFilePath());
                }
                if (uploadRequest.getUploadPath() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, uploadRequest.getUploadPath());
                }
                if (uploadRequest.getContentDate() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, uploadRequest.getContentDate());
                }
                if (uploadRequest.getMd5() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, uploadRequest.getMd5());
                }
                if (uploadRequest.getVisualDigest() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, uploadRequest.getVisualDigest());
                }
                supportSQLiteStatement.bindLong(7, uploadRequest.getSuppressDeduplication() ? 1L : 0L);
                supportSQLiteStatement.bindLong(8, uploadRequest.getRenameOnNameConflict() ? 1L : 0L);
                if (uploadRequest.getUploadCategory() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, uploadRequest.getUploadCategory());
                }
                String fromUploadState = UploadRequestDao_Impl.this.__converters.fromUploadState(uploadRequest.getState());
                if (fromUploadState == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, fromUploadState);
                }
                if (uploadRequest.getQueue() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, uploadRequest.getQueue());
                }
                supportSQLiteStatement.bindLong(12, uploadRequest.getCurrentProgress());
                supportSQLiteStatement.bindLong(13, uploadRequest.getMaxProgress());
                if (uploadRequest.getErrorCode() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, uploadRequest.getErrorCode());
                }
                String fromUploadErrorCategory = UploadRequestDao_Impl.this.__converters.fromUploadErrorCategory(uploadRequest.getErrorCategory());
                if (fromUploadErrorCategory == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, fromUploadErrorCategory);
                }
                String fromBlocker = UploadRequestDao_Impl.this.__converters.fromBlocker(uploadRequest.getBlocker());
                if (fromBlocker == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, fromBlocker);
                }
                supportSQLiteStatement.bindLong(17, uploadRequest.getTotalAttemptCount());
                supportSQLiteStatement.bindLong(18, uploadRequest.getAttemptCount());
                supportSQLiteStatement.bindLong(19, uploadRequest.getMaxAttemptsExceeded() ? 1L : 0L);
                supportSQLiteStatement.bindLong(20, uploadRequest.getCreationTimeMillis());
                supportSQLiteStatement.bindLong(21, uploadRequest.getFileSize());
                supportSQLiteStatement.bindLong(22, uploadRequest.getPriority());
                supportSQLiteStatement.bindLong(23, uploadRequest.getAddToFamilyVault() ? 1L : 0L);
                if (uploadRequest.getAppData() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, uploadRequest.getAppData());
                }
                if (uploadRequest.getParentId() == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, uploadRequest.getParentId());
                }
                String fromContentUri = UploadRequestDao_Impl.this.__converters.fromContentUri(uploadRequest.getContentUri());
                if (fromContentUri == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, fromContentUri);
                }
                supportSQLiteStatement.bindLong(27, uploadRequest.getId());
            }
        };
        this.__preparedStmtOfDeleteByRequestId = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM upload_request WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeleteByState = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM upload_request WHERE state = ?";
            }
        };
        this.__preparedStmtOfUpdateRequestState = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_request SET state = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfUpdateRequestBlocker = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_request SET blocker = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfUpdateRequestAttempts = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_request SET attempt_count = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfUpdateRequestErrorCode = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_request SET error_code = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfUpdateRequestErrorCategory = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.10
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_request SET error_category = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfUpdateCreationTimeMillis = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.11
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_request SET creation_time_millis = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfUpdateQueue = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.12
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_request SET queue = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfUpdateFileSize = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.13
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_request SET file_size = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfUpdateContentSignatures = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl.14
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE upload_request SET md5 = ?, visual_digest = ? WHERE id = ?";
            }
        };
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    protected void bulkUpdateStateInternal(UploadState uploadState, List<Long> list) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE upload_request SET state = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" WHERE id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, list.size());
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            compileStatement.bindNull(1);
        } else {
            compileStatement.bindString(1, fromUploadState);
        }
        int i = 2;
        for (Long l : list) {
            if (l == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public Map<UploadState, List<UploadRequest>> cancelUploadRequests(List<Long> list) {
        this.__db.beginTransaction();
        try {
            Map<UploadState, List<UploadRequest>> cancelUploadRequests = super.cancelUploadRequests(list);
            this.__db.setTransactionSuccessful();
            return cancelUploadRequests;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void deleteByRequestId(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteByRequestId.acquire();
        acquire.bindLong(1, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByRequestId.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void deleteByState(UploadState uploadState) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteByState.acquire();
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByState.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void deleteRequest(UploadRequest uploadRequest) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfUploadRequest.handle(uploadRequest);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public List<UploadRequest> getAllRequests() {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request", 0);
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i2 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i3 = i;
                        long j3 = query.getLong(i3);
                        int i4 = columnIndexOrThrow14;
                        String string8 = query.getString(i4);
                        i = i3;
                        columnIndexOrThrow14 = i4;
                        int i5 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i5;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i5));
                        int i6 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i6;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i6));
                        int i7 = columnIndexOrThrow17;
                        int i8 = query.getInt(i7);
                        int i9 = columnIndexOrThrow18;
                        int i10 = query.getInt(i9);
                        columnIndexOrThrow17 = i7;
                        int i11 = columnIndexOrThrow19;
                        int i12 = query.getInt(i11);
                        columnIndexOrThrow19 = i11;
                        int i13 = columnIndexOrThrow20;
                        boolean z3 = i12 != 0;
                        long j4 = query.getLong(i13);
                        columnIndexOrThrow20 = i13;
                        int i14 = columnIndexOrThrow21;
                        long j5 = query.getLong(i14);
                        columnIndexOrThrow21 = i14;
                        int i15 = columnIndexOrThrow22;
                        int i16 = query.getInt(i15);
                        columnIndexOrThrow22 = i15;
                        int i17 = columnIndexOrThrow23;
                        int i18 = query.getInt(i17);
                        columnIndexOrThrow23 = i17;
                        int i19 = columnIndexOrThrow24;
                        boolean z4 = i18 != 0;
                        String string9 = query.getString(i19);
                        columnIndexOrThrow24 = i19;
                        int i20 = columnIndexOrThrow25;
                        String string10 = query.getString(i20);
                        columnIndexOrThrow25 = i20;
                        columnIndexOrThrow18 = i9;
                        int i21 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i21;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i8, i10, z3, j4, j5, i16, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i21))));
                        columnIndexOrThrow = i2;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public int getCountForState(String str, UploadState uploadState) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) FROM upload_request WHERE state = ? AND queue = ?", 2);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        int i = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public int getCountForStateInAllQueues(UploadState uploadState) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) FROM upload_request WHERE state = ?", 1);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        this.__db.assertNotSuspendingTransaction();
        int i = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public UploadRequest getPendingRequestByFilepath(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        UploadRequest uploadRequest;
        int i;
        boolean z;
        int i2;
        boolean z2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE file_path = ? AND state in ('RUNNING', 'BLOCKED', 'ENQUEUED')", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                if (query.moveToFirst()) {
                    long j = query.getLong(columnIndexOrThrow);
                    String string = query.getString(columnIndexOrThrow2);
                    String string2 = query.getString(columnIndexOrThrow3);
                    String string3 = query.getString(columnIndexOrThrow4);
                    String string4 = query.getString(columnIndexOrThrow5);
                    String string5 = query.getString(columnIndexOrThrow6);
                    boolean z3 = query.getInt(columnIndexOrThrow7) != 0;
                    boolean z4 = query.getInt(columnIndexOrThrow8) != 0;
                    String string6 = query.getString(columnIndexOrThrow9);
                    UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                    String string7 = query.getString(columnIndexOrThrow11);
                    long j2 = query.getLong(columnIndexOrThrow12);
                    long j3 = query.getLong(columnIndexOrThrow13);
                    String string8 = query.getString(columnIndexOrThrow14);
                    UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(columnIndexOrThrow15));
                    Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(columnIndexOrThrow16));
                    int i3 = query.getInt(columnIndexOrThrow17);
                    int i4 = query.getInt(columnIndexOrThrow18);
                    if (query.getInt(columnIndexOrThrow19) != 0) {
                        i = columnIndexOrThrow20;
                        z = true;
                    } else {
                        i = columnIndexOrThrow20;
                        z = false;
                    }
                    long j4 = query.getLong(i);
                    long j5 = query.getLong(columnIndexOrThrow21);
                    int i5 = query.getInt(columnIndexOrThrow22);
                    if (query.getInt(columnIndexOrThrow23) != 0) {
                        i2 = columnIndexOrThrow24;
                        z2 = true;
                    } else {
                        i2 = columnIndexOrThrow24;
                        z2 = false;
                    }
                    uploadRequest = new UploadRequest(j, string, string2, string3, string4, string5, z3, z4, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i3, i4, z, j4, j5, i5, z2, query.getString(i2), query.getString(columnIndexOrThrow25), this.__converters.fromContentUriString(query.getString(columnIndexOrThrow26)));
                } else {
                    uploadRequest = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return uploadRequest;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public int getPendingRequestsCount() {
        int i = 0;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) FROM upload_request WHERE state in ('RUNNING', 'BLOCKED', 'ENQUEUED')", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public List<UploadRequest> getPrioritizedPendingRequestsForQueue(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE queue = ? AND state in ('RUNNING', 'BLOCKED', 'ENQUEUED') ORDER BY priority DESC", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i2 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i3 = i;
                        long j3 = query.getLong(i3);
                        int i4 = columnIndexOrThrow14;
                        String string8 = query.getString(i4);
                        i = i3;
                        int i5 = columnIndexOrThrow12;
                        int i6 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i6;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i6));
                        int i7 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i7;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i7));
                        int i8 = columnIndexOrThrow17;
                        int i9 = query.getInt(i8);
                        int i10 = columnIndexOrThrow18;
                        int i11 = query.getInt(i10);
                        columnIndexOrThrow17 = i8;
                        int i12 = columnIndexOrThrow19;
                        int i13 = query.getInt(i12);
                        columnIndexOrThrow19 = i12;
                        int i14 = columnIndexOrThrow20;
                        boolean z3 = i13 != 0;
                        long j4 = query.getLong(i14);
                        columnIndexOrThrow20 = i14;
                        int i15 = columnIndexOrThrow21;
                        long j5 = query.getLong(i15);
                        columnIndexOrThrow21 = i15;
                        int i16 = columnIndexOrThrow22;
                        int i17 = query.getInt(i16);
                        columnIndexOrThrow22 = i16;
                        int i18 = columnIndexOrThrow23;
                        int i19 = query.getInt(i18);
                        columnIndexOrThrow23 = i18;
                        int i20 = columnIndexOrThrow24;
                        boolean z4 = i19 != 0;
                        String string9 = query.getString(i20);
                        columnIndexOrThrow24 = i20;
                        int i21 = columnIndexOrThrow25;
                        String string10 = query.getString(i21);
                        columnIndexOrThrow25 = i21;
                        columnIndexOrThrow18 = i10;
                        int i22 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i22;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i9, i11, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i22))));
                        columnIndexOrThrow12 = i5;
                        columnIndexOrThrow = i2;
                        columnIndexOrThrow14 = i4;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public List<UploadRequest> getPrioritizedRequestsForState(UploadState uploadState) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE state = ? ORDER BY priority DESC", 1);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
            } catch (Throwable th) {
                th = th;
                roomSQLiteQuery = acquire;
            }
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                int i = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    long j = query.getLong(columnIndexOrThrow);
                    String string = query.getString(columnIndexOrThrow2);
                    String string2 = query.getString(columnIndexOrThrow3);
                    String string3 = query.getString(columnIndexOrThrow4);
                    String string4 = query.getString(columnIndexOrThrow5);
                    String string5 = query.getString(columnIndexOrThrow6);
                    boolean z = query.getInt(columnIndexOrThrow7) != 0;
                    boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                    String string6 = query.getString(columnIndexOrThrow9);
                    int i2 = columnIndexOrThrow;
                    UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                    String string7 = query.getString(columnIndexOrThrow11);
                    long j2 = query.getLong(columnIndexOrThrow12);
                    int i3 = i;
                    long j3 = query.getLong(i3);
                    int i4 = columnIndexOrThrow14;
                    String string8 = query.getString(i4);
                    int i5 = columnIndexOrThrow12;
                    i = i3;
                    int i6 = columnIndexOrThrow15;
                    columnIndexOrThrow15 = i6;
                    UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i6));
                    int i7 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i7;
                    Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i7));
                    int i8 = columnIndexOrThrow17;
                    int i9 = query.getInt(i8);
                    int i10 = columnIndexOrThrow18;
                    int i11 = query.getInt(i10);
                    columnIndexOrThrow17 = i8;
                    int i12 = columnIndexOrThrow19;
                    int i13 = query.getInt(i12);
                    columnIndexOrThrow19 = i12;
                    int i14 = columnIndexOrThrow20;
                    boolean z3 = i13 != 0;
                    long j4 = query.getLong(i14);
                    columnIndexOrThrow20 = i14;
                    int i15 = columnIndexOrThrow21;
                    long j5 = query.getLong(i15);
                    columnIndexOrThrow21 = i15;
                    int i16 = columnIndexOrThrow22;
                    int i17 = query.getInt(i16);
                    columnIndexOrThrow22 = i16;
                    int i18 = columnIndexOrThrow23;
                    int i19 = query.getInt(i18);
                    columnIndexOrThrow23 = i18;
                    int i20 = columnIndexOrThrow24;
                    boolean z4 = i19 != 0;
                    String string9 = query.getString(i20);
                    columnIndexOrThrow24 = i20;
                    int i21 = columnIndexOrThrow25;
                    String string10 = query.getString(i21);
                    columnIndexOrThrow25 = i21;
                    columnIndexOrThrow18 = i10;
                    int i22 = columnIndexOrThrow26;
                    columnIndexOrThrow26 = i22;
                    arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i9, i11, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i22))));
                    columnIndexOrThrow12 = i5;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow14 = i4;
                }
                this.__db.setTransactionSuccessful();
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public UploadRequest getRequestById(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        UploadRequest uploadRequest;
        int i;
        boolean z;
        int i2;
        boolean z2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE id = ?", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
            if (query.moveToFirst()) {
                long j2 = query.getLong(columnIndexOrThrow);
                String string = query.getString(columnIndexOrThrow2);
                String string2 = query.getString(columnIndexOrThrow3);
                String string3 = query.getString(columnIndexOrThrow4);
                String string4 = query.getString(columnIndexOrThrow5);
                String string5 = query.getString(columnIndexOrThrow6);
                boolean z3 = query.getInt(columnIndexOrThrow7) != 0;
                boolean z4 = query.getInt(columnIndexOrThrow8) != 0;
                String string6 = query.getString(columnIndexOrThrow9);
                UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                String string7 = query.getString(columnIndexOrThrow11);
                long j3 = query.getLong(columnIndexOrThrow12);
                long j4 = query.getLong(columnIndexOrThrow13);
                String string8 = query.getString(columnIndexOrThrow14);
                UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(columnIndexOrThrow15));
                Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(columnIndexOrThrow16));
                int i3 = query.getInt(columnIndexOrThrow17);
                int i4 = query.getInt(columnIndexOrThrow18);
                if (query.getInt(columnIndexOrThrow19) != 0) {
                    i = columnIndexOrThrow20;
                    z = true;
                } else {
                    i = columnIndexOrThrow20;
                    z = false;
                }
                long j5 = query.getLong(i);
                long j6 = query.getLong(columnIndexOrThrow21);
                int i5 = query.getInt(columnIndexOrThrow22);
                if (query.getInt(columnIndexOrThrow23) != 0) {
                    i2 = columnIndexOrThrow24;
                    z2 = true;
                } else {
                    i2 = columnIndexOrThrow24;
                    z2 = false;
                }
                uploadRequest = new UploadRequest(j2, string, string2, string3, string4, string5, z3, z4, string6, fromUploadStateString, string7, j3, j4, string8, fromUploadErrorCategoryString, fromBlockerString, i3, i4, z, j5, j6, i5, z2, query.getString(i2), query.getString(columnIndexOrThrow25), this.__converters.fromContentUriString(query.getString(columnIndexOrThrow26)));
            } else {
                uploadRequest = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return uploadRequest;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public List<UploadRequest> getRequestsByFilePathsInternal(List<String> list) {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM upload_request WHERE file_path in (");
        int size = list.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
        int i = 1;
        for (String str : list) {
            if (str == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, str);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i2 = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i3 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i4 = i2;
                        long j3 = query.getLong(i4);
                        int i5 = columnIndexOrThrow14;
                        String string8 = query.getString(i5);
                        i2 = i4;
                        int i6 = columnIndexOrThrow2;
                        int i7 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i7;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i7));
                        int i8 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i8;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i8));
                        int i9 = columnIndexOrThrow17;
                        int i10 = query.getInt(i9);
                        int i11 = columnIndexOrThrow18;
                        int i12 = query.getInt(i11);
                        columnIndexOrThrow17 = i9;
                        int i13 = columnIndexOrThrow19;
                        columnIndexOrThrow19 = i13;
                        boolean z3 = query.getInt(i13) != 0;
                        int i14 = columnIndexOrThrow20;
                        long j4 = query.getLong(i14);
                        columnIndexOrThrow20 = i14;
                        int i15 = columnIndexOrThrow21;
                        long j5 = query.getLong(i15);
                        columnIndexOrThrow21 = i15;
                        int i16 = columnIndexOrThrow22;
                        int i17 = query.getInt(i16);
                        columnIndexOrThrow22 = i16;
                        int i18 = columnIndexOrThrow23;
                        columnIndexOrThrow23 = i18;
                        boolean z4 = query.getInt(i18) != 0;
                        int i19 = columnIndexOrThrow24;
                        String string9 = query.getString(i19);
                        columnIndexOrThrow24 = i19;
                        int i20 = columnIndexOrThrow25;
                        String string10 = query.getString(i20);
                        columnIndexOrThrow25 = i20;
                        columnIndexOrThrow18 = i11;
                        int i21 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i21;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i10, i12, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i21))));
                        columnIndexOrThrow2 = i6;
                        columnIndexOrThrow14 = i5;
                        columnIndexOrThrow = i3;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public List<UploadRequest> getRequestsByIds(List<Long> list) {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM upload_request WHERE id in (");
        int size = list.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
        int i = 1;
        for (Long l : list) {
            if (l == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i2 = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i3 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i4 = i2;
                        long j3 = query.getLong(i4);
                        int i5 = columnIndexOrThrow14;
                        String string8 = query.getString(i5);
                        i2 = i4;
                        int i6 = columnIndexOrThrow2;
                        int i7 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i7;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i7));
                        int i8 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i8;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i8));
                        int i9 = columnIndexOrThrow17;
                        int i10 = query.getInt(i9);
                        int i11 = columnIndexOrThrow18;
                        int i12 = query.getInt(i11);
                        columnIndexOrThrow17 = i9;
                        int i13 = columnIndexOrThrow19;
                        columnIndexOrThrow19 = i13;
                        boolean z3 = query.getInt(i13) != 0;
                        int i14 = columnIndexOrThrow20;
                        long j4 = query.getLong(i14);
                        columnIndexOrThrow20 = i14;
                        int i15 = columnIndexOrThrow21;
                        long j5 = query.getLong(i15);
                        columnIndexOrThrow21 = i15;
                        int i16 = columnIndexOrThrow22;
                        int i17 = query.getInt(i16);
                        columnIndexOrThrow22 = i16;
                        int i18 = columnIndexOrThrow23;
                        columnIndexOrThrow23 = i18;
                        boolean z4 = query.getInt(i18) != 0;
                        int i19 = columnIndexOrThrow24;
                        String string9 = query.getString(i19);
                        columnIndexOrThrow24 = i19;
                        int i20 = columnIndexOrThrow25;
                        String string10 = query.getString(i20);
                        columnIndexOrThrow25 = i20;
                        columnIndexOrThrow18 = i11;
                        int i21 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i21;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i10, i12, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i21))));
                        columnIndexOrThrow2 = i6;
                        columnIndexOrThrow14 = i5;
                        columnIndexOrThrow = i3;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public List<UploadRequest> getRequestsForQueue(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE queue = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i2 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i3 = i;
                        long j3 = query.getLong(i3);
                        int i4 = columnIndexOrThrow14;
                        String string8 = query.getString(i4);
                        i = i3;
                        int i5 = columnIndexOrThrow12;
                        int i6 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i6;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i6));
                        int i7 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i7;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i7));
                        int i8 = columnIndexOrThrow17;
                        int i9 = query.getInt(i8);
                        int i10 = columnIndexOrThrow18;
                        int i11 = query.getInt(i10);
                        columnIndexOrThrow17 = i8;
                        int i12 = columnIndexOrThrow19;
                        int i13 = query.getInt(i12);
                        columnIndexOrThrow19 = i12;
                        int i14 = columnIndexOrThrow20;
                        boolean z3 = i13 != 0;
                        long j4 = query.getLong(i14);
                        columnIndexOrThrow20 = i14;
                        int i15 = columnIndexOrThrow21;
                        long j5 = query.getLong(i15);
                        columnIndexOrThrow21 = i15;
                        int i16 = columnIndexOrThrow22;
                        int i17 = query.getInt(i16);
                        columnIndexOrThrow22 = i16;
                        int i18 = columnIndexOrThrow23;
                        int i19 = query.getInt(i18);
                        columnIndexOrThrow23 = i18;
                        int i20 = columnIndexOrThrow24;
                        boolean z4 = i19 != 0;
                        String string9 = query.getString(i20);
                        columnIndexOrThrow24 = i20;
                        int i21 = columnIndexOrThrow25;
                        String string10 = query.getString(i21);
                        columnIndexOrThrow25 = i21;
                        columnIndexOrThrow18 = i10;
                        int i22 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i22;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i9, i11, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i22))));
                        columnIndexOrThrow12 = i5;
                        columnIndexOrThrow = i2;
                        columnIndexOrThrow14 = i4;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public List<UploadRequest> getRequestsForState(String str, UploadState uploadState) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE queue = ? AND state = ?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, fromUploadState);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i2 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i3 = i;
                        long j3 = query.getLong(i3);
                        int i4 = columnIndexOrThrow14;
                        String string8 = query.getString(i4);
                        i = i3;
                        int i5 = columnIndexOrThrow12;
                        int i6 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i6;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i6));
                        int i7 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i7;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i7));
                        int i8 = columnIndexOrThrow17;
                        int i9 = query.getInt(i8);
                        int i10 = columnIndexOrThrow18;
                        int i11 = query.getInt(i10);
                        columnIndexOrThrow17 = i8;
                        int i12 = columnIndexOrThrow19;
                        int i13 = query.getInt(i12);
                        columnIndexOrThrow19 = i12;
                        int i14 = columnIndexOrThrow20;
                        boolean z3 = i13 != 0;
                        long j4 = query.getLong(i14);
                        columnIndexOrThrow20 = i14;
                        int i15 = columnIndexOrThrow21;
                        long j5 = query.getLong(i15);
                        columnIndexOrThrow21 = i15;
                        int i16 = columnIndexOrThrow22;
                        int i17 = query.getInt(i16);
                        columnIndexOrThrow22 = i16;
                        int i18 = columnIndexOrThrow23;
                        int i19 = query.getInt(i18);
                        columnIndexOrThrow23 = i18;
                        int i20 = columnIndexOrThrow24;
                        boolean z4 = i19 != 0;
                        String string9 = query.getString(i20);
                        columnIndexOrThrow24 = i20;
                        int i21 = columnIndexOrThrow25;
                        String string10 = query.getString(i21);
                        columnIndexOrThrow25 = i21;
                        columnIndexOrThrow18 = i10;
                        int i22 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i22;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i9, i11, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i22))));
                        columnIndexOrThrow12 = i5;
                        columnIndexOrThrow14 = i4;
                        columnIndexOrThrow = i2;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    protected List<UploadRequest> getRequestsForStateAndIdsInternal(UploadState uploadState, List<Long> list) {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT ");
        newStringBuilder.append("*");
        newStringBuilder.append(" FROM upload_request WHERE state = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" AND id in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 1);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        int i = 2;
        for (Long l : list) {
            if (l == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i2 = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i3 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i4 = i2;
                        long j3 = query.getLong(i4);
                        int i5 = columnIndexOrThrow14;
                        String string8 = query.getString(i5);
                        i2 = i4;
                        int i6 = columnIndexOrThrow12;
                        int i7 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i7;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i7));
                        int i8 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i8;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i8));
                        int i9 = columnIndexOrThrow17;
                        int i10 = query.getInt(i9);
                        int i11 = columnIndexOrThrow18;
                        int i12 = query.getInt(i11);
                        columnIndexOrThrow17 = i9;
                        int i13 = columnIndexOrThrow19;
                        int i14 = query.getInt(i13);
                        columnIndexOrThrow19 = i13;
                        int i15 = columnIndexOrThrow20;
                        boolean z3 = i14 != 0;
                        long j4 = query.getLong(i15);
                        columnIndexOrThrow20 = i15;
                        int i16 = columnIndexOrThrow21;
                        long j5 = query.getLong(i16);
                        columnIndexOrThrow21 = i16;
                        int i17 = columnIndexOrThrow22;
                        int i18 = query.getInt(i17);
                        columnIndexOrThrow22 = i17;
                        int i19 = columnIndexOrThrow23;
                        int i20 = query.getInt(i19);
                        columnIndexOrThrow23 = i19;
                        int i21 = columnIndexOrThrow24;
                        boolean z4 = i20 != 0;
                        String string9 = query.getString(i21);
                        columnIndexOrThrow24 = i21;
                        int i22 = columnIndexOrThrow25;
                        String string10 = query.getString(i22);
                        columnIndexOrThrow25 = i22;
                        columnIndexOrThrow18 = i11;
                        int i23 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i23;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i10, i12, z3, j4, j5, i18, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i23))));
                        columnIndexOrThrow12 = i6;
                        columnIndexOrThrow14 = i5;
                        columnIndexOrThrow = i3;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public long insertRequest(UploadRequest uploadRequest) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfUploadRequest.insertAndReturnId(uploadRequest);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public List<Long> insertRequests(List<UploadRequest> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            List<Long> insertAndReturnIdsList = this.__insertionAdapterOfUploadRequest.insertAndReturnIdsList(list);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void updateContentSignatures(String str, String str2, long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateContentSignatures.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        acquire.bindLong(3, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateContentSignatures.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void updateCreationTimeMillis(long j, long j2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateCreationTimeMillis.acquire();
        acquire.bindLong(1, j2);
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateCreationTimeMillis.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void updateFileSize(long j, long j2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateFileSize.acquire();
        acquire.bindLong(1, j2);
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateFileSize.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void updateQueue(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateQueue.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateQueue.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void updateRequest(UploadRequest uploadRequest) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfUploadRequest.handle(uploadRequest);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void updateRequestAttempts(long j, int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateRequestAttempts.acquire();
        acquire.bindLong(1, i);
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateRequestAttempts.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void updateRequestBlocker(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateRequestBlocker.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateRequestBlocker.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void updateRequestErrorCategory(long j, UploadErrorCategory uploadErrorCategory) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateRequestErrorCategory.acquire();
        String fromUploadErrorCategory = this.__converters.fromUploadErrorCategory(uploadErrorCategory);
        if (fromUploadErrorCategory == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadErrorCategory);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateRequestErrorCategory.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void updateRequestErrorCode(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateRequestErrorCode.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateRequestErrorCode.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public void updateRequestState(long j, UploadState uploadState) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateRequestState.acquire();
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateRequestState.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public int getPendingRequestsCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) FROM upload_request WHERE queue = ? AND state in ('RUNNING', 'BLOCKED', 'ENQUEUED')", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        int i = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public List<UploadRequest> getPrioritizedRequestsForState(String str, UploadState uploadState) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE queue = ? AND state = ? ORDER BY priority DESC", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, fromUploadState);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                    int i = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        boolean z = query.getInt(columnIndexOrThrow7) != 0;
                        boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                        String string6 = query.getString(columnIndexOrThrow9);
                        int i2 = columnIndexOrThrow;
                        UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                        String string7 = query.getString(columnIndexOrThrow11);
                        long j2 = query.getLong(columnIndexOrThrow12);
                        int i3 = i;
                        long j3 = query.getLong(i3);
                        int i4 = columnIndexOrThrow14;
                        String string8 = query.getString(i4);
                        i = i3;
                        int i5 = columnIndexOrThrow12;
                        int i6 = columnIndexOrThrow15;
                        columnIndexOrThrow15 = i6;
                        UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i6));
                        int i7 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i7;
                        Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i7));
                        int i8 = columnIndexOrThrow17;
                        int i9 = query.getInt(i8);
                        int i10 = columnIndexOrThrow18;
                        int i11 = query.getInt(i10);
                        columnIndexOrThrow17 = i8;
                        int i12 = columnIndexOrThrow19;
                        int i13 = query.getInt(i12);
                        columnIndexOrThrow19 = i12;
                        int i14 = columnIndexOrThrow20;
                        boolean z3 = i13 != 0;
                        long j4 = query.getLong(i14);
                        columnIndexOrThrow20 = i14;
                        int i15 = columnIndexOrThrow21;
                        long j5 = query.getLong(i15);
                        columnIndexOrThrow21 = i15;
                        int i16 = columnIndexOrThrow22;
                        int i17 = query.getInt(i16);
                        columnIndexOrThrow22 = i16;
                        int i18 = columnIndexOrThrow23;
                        int i19 = query.getInt(i18);
                        columnIndexOrThrow23 = i18;
                        int i20 = columnIndexOrThrow24;
                        boolean z4 = i19 != 0;
                        String string9 = query.getString(i20);
                        columnIndexOrThrow24 = i20;
                        int i21 = columnIndexOrThrow25;
                        String string10 = query.getString(i21);
                        columnIndexOrThrow25 = i21;
                        columnIndexOrThrow18 = i10;
                        int i22 = columnIndexOrThrow26;
                        columnIndexOrThrow26 = i22;
                        arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i9, i11, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i22))));
                        columnIndexOrThrow12 = i5;
                        columnIndexOrThrow14 = i4;
                        columnIndexOrThrow = i2;
                    }
                    this.__db.setTransactionSuccessful();
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.internal.dao.UploadRequestDao
    public List<UploadRequest> getRequestsForState(UploadState uploadState) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM upload_request WHERE state = ?", 1);
        String fromUploadState = this.__converters.fromUploadState(uploadState);
        if (fromUploadState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromUploadState);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
                columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_path");
                columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "content_date");
                columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "suppress_duplication");
                columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "rename_on_name_conflict");
                columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "upload_category");
                columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "state");
                columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "queue");
                columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_progress");
                columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "max_progress");
                roomSQLiteQuery = acquire;
            } catch (Throwable th) {
                th = th;
                roomSQLiteQuery = acquire;
            }
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "error_code");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "error_category");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "blocker");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_attempt_count");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_attempts_exceeded");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "creation_time_millis");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "file_size");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "priority");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "add_to_family_vault");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "app_data");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "content_uri");
                int i = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    long j = query.getLong(columnIndexOrThrow);
                    String string = query.getString(columnIndexOrThrow2);
                    String string2 = query.getString(columnIndexOrThrow3);
                    String string3 = query.getString(columnIndexOrThrow4);
                    String string4 = query.getString(columnIndexOrThrow5);
                    String string5 = query.getString(columnIndexOrThrow6);
                    boolean z = query.getInt(columnIndexOrThrow7) != 0;
                    boolean z2 = query.getInt(columnIndexOrThrow8) != 0;
                    String string6 = query.getString(columnIndexOrThrow9);
                    int i2 = columnIndexOrThrow;
                    UploadState fromUploadStateString = this.__converters.fromUploadStateString(query.getString(columnIndexOrThrow10));
                    String string7 = query.getString(columnIndexOrThrow11);
                    long j2 = query.getLong(columnIndexOrThrow12);
                    int i3 = i;
                    long j3 = query.getLong(i3);
                    int i4 = columnIndexOrThrow14;
                    String string8 = query.getString(i4);
                    int i5 = columnIndexOrThrow12;
                    i = i3;
                    int i6 = columnIndexOrThrow15;
                    columnIndexOrThrow15 = i6;
                    UploadErrorCategory fromUploadErrorCategoryString = this.__converters.fromUploadErrorCategoryString(query.getString(i6));
                    int i7 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i7;
                    Blocker fromBlockerString = this.__converters.fromBlockerString(query.getString(i7));
                    int i8 = columnIndexOrThrow17;
                    int i9 = query.getInt(i8);
                    int i10 = columnIndexOrThrow18;
                    int i11 = query.getInt(i10);
                    columnIndexOrThrow17 = i8;
                    int i12 = columnIndexOrThrow19;
                    int i13 = query.getInt(i12);
                    columnIndexOrThrow19 = i12;
                    int i14 = columnIndexOrThrow20;
                    boolean z3 = i13 != 0;
                    long j4 = query.getLong(i14);
                    columnIndexOrThrow20 = i14;
                    int i15 = columnIndexOrThrow21;
                    long j5 = query.getLong(i15);
                    columnIndexOrThrow21 = i15;
                    int i16 = columnIndexOrThrow22;
                    int i17 = query.getInt(i16);
                    columnIndexOrThrow22 = i16;
                    int i18 = columnIndexOrThrow23;
                    int i19 = query.getInt(i18);
                    columnIndexOrThrow23 = i18;
                    int i20 = columnIndexOrThrow24;
                    boolean z4 = i19 != 0;
                    String string9 = query.getString(i20);
                    columnIndexOrThrow24 = i20;
                    int i21 = columnIndexOrThrow25;
                    String string10 = query.getString(i21);
                    columnIndexOrThrow25 = i21;
                    columnIndexOrThrow18 = i10;
                    int i22 = columnIndexOrThrow26;
                    columnIndexOrThrow26 = i22;
                    arrayList.add(new UploadRequest(j, string, string2, string3, string4, string5, z, z2, string6, fromUploadStateString, string7, j2, j3, string8, fromUploadErrorCategoryString, fromBlockerString, i9, i11, z3, j4, j5, i17, z4, string9, string10, this.__converters.fromContentUriString(query.getString(i22))));
                    columnIndexOrThrow12 = i5;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow14 = i4;
                }
                this.__db.setTransactionSuccessful();
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }
}
