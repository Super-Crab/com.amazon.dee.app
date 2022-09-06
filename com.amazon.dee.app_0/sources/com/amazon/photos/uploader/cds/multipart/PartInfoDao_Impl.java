package com.amazon.photos.uploader.cds.multipart;

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
import com.amazon.photos.uploader.cds.multipart.PartInfoDao;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public final class PartInfoDao_Impl implements PartInfoDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<PartInfo> __deletionAdapterOfPartInfo;
    private final EntityInsertionAdapter<PartInfo> __insertionAdapterOfPartInfo;
    private final PartInfoConverters __partInfoConverters = new PartInfoConverters();
    private final SharedSQLiteStatement __preparedStmtOfDeleteByRequestId;
    private final SharedSQLiteStatement __preparedStmtOfDeleteCompletedRequest;
    private final SharedSQLiteStatement __preparedStmtOfUpdatePartInfoState;
    private final SharedSQLiteStatement __preparedStmtOfUpdatePartInfoStateByRequestId;
    private final EntityDeletionOrUpdateAdapter<PartInfo> __updateAdapterOfPartInfo;

    public PartInfoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfPartInfo = new EntityInsertionAdapter<PartInfo>(roomDatabase) { // from class: com.amazon.photos.uploader.cds.multipart.PartInfoDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `part_info` (`part_id`,`upload_request_id`,`part_upload_state`,`part_enqueue_timestamp`,`part_upload_start_timestamp`,`part_upload_complete_timestamp`,`part_md5`,`part_size`,`part_offset`,`service_upload_id`,`node_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PartInfo partInfo) {
                supportSQLiteStatement.bindLong(1, partInfo.getPartId());
                supportSQLiteStatement.bindLong(2, partInfo.getUploadRequestId());
                String fromPartInfoState = PartInfoDao_Impl.this.__partInfoConverters.fromPartInfoState(partInfo.getPartUploadState());
                if (fromPartInfoState == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, fromPartInfoState);
                }
                supportSQLiteStatement.bindLong(4, partInfo.getPartEnqueueTimestamp());
                supportSQLiteStatement.bindLong(5, partInfo.getPartUploadStartTimestamp());
                supportSQLiteStatement.bindLong(6, partInfo.getPartUploadCompleteTimestamp());
                if (partInfo.getPartMd5() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, partInfo.getPartMd5());
                }
                supportSQLiteStatement.bindLong(8, partInfo.getPartSize());
                supportSQLiteStatement.bindLong(9, partInfo.getPartOffset());
                if (partInfo.getServiceUploadId() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, partInfo.getServiceUploadId());
                }
                if (partInfo.getNodeId() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, partInfo.getNodeId());
                }
            }
        };
        this.__deletionAdapterOfPartInfo = new EntityDeletionOrUpdateAdapter<PartInfo>(roomDatabase) { // from class: com.amazon.photos.uploader.cds.multipart.PartInfoDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `part_info` WHERE `part_id` = ? AND `upload_request_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PartInfo partInfo) {
                supportSQLiteStatement.bindLong(1, partInfo.getPartId());
                supportSQLiteStatement.bindLong(2, partInfo.getUploadRequestId());
            }
        };
        this.__updateAdapterOfPartInfo = new EntityDeletionOrUpdateAdapter<PartInfo>(roomDatabase) { // from class: com.amazon.photos.uploader.cds.multipart.PartInfoDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `part_info` SET `part_id` = ?,`upload_request_id` = ?,`part_upload_state` = ?,`part_enqueue_timestamp` = ?,`part_upload_start_timestamp` = ?,`part_upload_complete_timestamp` = ?,`part_md5` = ?,`part_size` = ?,`part_offset` = ?,`service_upload_id` = ?,`node_id` = ? WHERE `part_id` = ? AND `upload_request_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PartInfo partInfo) {
                supportSQLiteStatement.bindLong(1, partInfo.getPartId());
                supportSQLiteStatement.bindLong(2, partInfo.getUploadRequestId());
                String fromPartInfoState = PartInfoDao_Impl.this.__partInfoConverters.fromPartInfoState(partInfo.getPartUploadState());
                if (fromPartInfoState == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, fromPartInfoState);
                }
                supportSQLiteStatement.bindLong(4, partInfo.getPartEnqueueTimestamp());
                supportSQLiteStatement.bindLong(5, partInfo.getPartUploadStartTimestamp());
                supportSQLiteStatement.bindLong(6, partInfo.getPartUploadCompleteTimestamp());
                if (partInfo.getPartMd5() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, partInfo.getPartMd5());
                }
                supportSQLiteStatement.bindLong(8, partInfo.getPartSize());
                supportSQLiteStatement.bindLong(9, partInfo.getPartOffset());
                if (partInfo.getServiceUploadId() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, partInfo.getServiceUploadId());
                }
                if (partInfo.getNodeId() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, partInfo.getNodeId());
                }
                supportSQLiteStatement.bindLong(12, partInfo.getPartId());
                supportSQLiteStatement.bindLong(13, partInfo.getUploadRequestId());
            }
        };
        this.__preparedStmtOfDeleteByRequestId = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.cds.multipart.PartInfoDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM part_info WHERE upload_request_id = ?";
            }
        };
        this.__preparedStmtOfDeleteCompletedRequest = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.cds.multipart.PartInfoDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM part_info WHERE part_upload_state = 'FAILED' OR part_upload_state = 'SUCCEEDED'";
            }
        };
        this.__preparedStmtOfUpdatePartInfoStateByRequestId = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.cds.multipart.PartInfoDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE part_info SET part_upload_state = ?  WHERE upload_request_id =?";
            }
        };
        this.__preparedStmtOfUpdatePartInfoState = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.cds.multipart.PartInfoDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE part_info SET part_upload_state = ?  WHERE part_id = ? AND upload_request_id =?";
            }
        };
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public void deleteBulkParts(long j, List<Long> list) {
        PartInfoDao.DefaultImpls.deleteBulkParts(this, j, list);
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
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

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public void deleteCompletedRequest() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteCompletedRequest.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteCompletedRequest.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public void deletePartInfo(PartInfo partInfo) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfPartInfo.handle(partInfo);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public void deletePartsByRequestId(long j) {
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

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public List<PartInfo> getAllByStateForRequestId(PartUploadState partUploadState, long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from part_info WHERE upload_request_id=? and part_upload_state = ?", 2);
        acquire.bindLong(1, j);
        String fromPartInfoState = this.__partInfoConverters.fromPartInfoState(partUploadState);
        if (fromPartInfoState == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, fromPartInfoState);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "part_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "upload_request_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "part_upload_state");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "part_enqueue_timestamp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "part_upload_start_timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "part_upload_complete_timestamp");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "part_md5");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "part_size");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "part_offset");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "service_upload_id");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "node_id");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                int i = columnIndexOrThrow;
                arrayList.add(new PartInfo(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), this.__partInfoConverters.fromPartInfoStateString(query.getString(columnIndexOrThrow3)), query.getLong(columnIndexOrThrow4), query.getLong(columnIndexOrThrow5), query.getLong(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getLong(columnIndexOrThrow8), query.getLong(columnIndexOrThrow9), query.getString(columnIndexOrThrow10), query.getString(columnIndexOrThrow11)));
                columnIndexOrThrow = i;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public long getAllCountByRequestId(long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) from part_info WHERE upload_request_id=? ", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getLong(0) : 0L;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public long getAllCountByStateRequestId(long j, PartUploadState partUploadState) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) from part_info WHERE upload_request_id=? and part_upload_state = ?", 2);
        acquire.bindLong(1, j);
        String fromPartInfoState = this.__partInfoConverters.fromPartInfoState(partUploadState);
        if (fromPartInfoState == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, fromPartInfoState);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getLong(0) : 0L;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public List<PartInfo> getAllPendingRequestId(long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from part_info WHERE upload_request_id=? and part_upload_state in  ('RUNNING', 'FAILED', 'ENQUEUED')", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "part_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "upload_request_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "part_upload_state");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "part_enqueue_timestamp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "part_upload_start_timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "part_upload_complete_timestamp");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "part_md5");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "part_size");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "part_offset");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "service_upload_id");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "node_id");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                int i = columnIndexOrThrow;
                arrayList.add(new PartInfo(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), this.__partInfoConverters.fromPartInfoStateString(query.getString(columnIndexOrThrow3)), query.getLong(columnIndexOrThrow4), query.getLong(columnIndexOrThrow5), query.getLong(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getLong(columnIndexOrThrow8), query.getLong(columnIndexOrThrow9), query.getString(columnIndexOrThrow10), query.getString(columnIndexOrThrow11)));
                columnIndexOrThrow = i;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public long getCompletedPartsBytesCount(long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT IFNULL(SUM(part_size),0) as BytesCompleted from part_info WHERE upload_request_id=? and part_upload_state = 'SUCCEEDED'", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getLong(0) : 0L;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public long insert(PartInfo partInfo) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfPartInfo.insertAndReturnId(partInfo);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public void updatePartInfo(PartInfo partInfo) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfPartInfo.handle(partInfo);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public void updatePartInfoState(long j, long j2, PartUploadState partUploadState) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdatePartInfoState.acquire();
        String fromPartInfoState = this.__partInfoConverters.fromPartInfoState(partUploadState);
        if (fromPartInfoState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromPartInfoState);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdatePartInfoState.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public void updatePartInfoStateByRequestAndPartId(long j, List<Long> list, PartUploadState partUploadState) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE part_info SET part_upload_state = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append("  WHERE upload_request_id =");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" and part_id in (");
        StringUtil.appendPlaceholders(newStringBuilder, list.size());
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        String fromPartInfoState = this.__partInfoConverters.fromPartInfoState(partUploadState);
        if (fromPartInfoState == null) {
            compileStatement.bindNull(1);
        } else {
            compileStatement.bindString(1, fromPartInfoState);
        }
        compileStatement.bindLong(2, j);
        int i = 3;
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

    @Override // com.amazon.photos.uploader.cds.multipart.PartInfoDao
    public void updatePartInfoStateByRequestId(long j, PartUploadState partUploadState) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdatePartInfoStateByRequestId.acquire();
        String fromPartInfoState = this.__partInfoConverters.fromPartInfoState(partUploadState);
        if (fromPartInfoState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromPartInfoState);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdatePartInfoStateByRequestId.release(acquire);
        }
    }
}
