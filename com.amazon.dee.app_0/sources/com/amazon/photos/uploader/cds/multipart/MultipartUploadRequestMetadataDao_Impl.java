package com.amazon.photos.uploader.cds.multipart;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public final class MultipartUploadRequestMetadataDao_Impl implements MultipartUploadRequestMetadataDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<MultipartUploadRequestMetadata> __insertionAdapterOfMultipartUploadRequestMetadata;
    private final PartInfoConverters __partInfoConverters = new PartInfoConverters();
    private final SharedSQLiteStatement __preparedStmtOfDeleteRequest;

    public MultipartUploadRequestMetadataDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfMultipartUploadRequestMetadata = new EntityInsertionAdapter<MultipartUploadRequestMetadata>(roomDatabase) { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `multipart_upload_request_metadata` (`upload_request_id`,`node_id`,`upload_id`,`part_size`,`total_parts`,`multipart_start_time`,`multipart_expiration_time`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MultipartUploadRequestMetadata multipartUploadRequestMetadata) {
                supportSQLiteStatement.bindLong(1, multipartUploadRequestMetadata.getUploadRequestId());
                if (multipartUploadRequestMetadata.getNodeId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, multipartUploadRequestMetadata.getNodeId());
                }
                if (multipartUploadRequestMetadata.getUploadId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, multipartUploadRequestMetadata.getUploadId());
                }
                if (multipartUploadRequestMetadata.getPartSize() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, multipartUploadRequestMetadata.getPartSize().longValue());
                }
                if (multipartUploadRequestMetadata.getTotalNumberOfParts() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindLong(5, multipartUploadRequestMetadata.getTotalNumberOfParts().longValue());
                }
                if (multipartUploadRequestMetadata.getMultipartUploadStartTime() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, multipartUploadRequestMetadata.getMultipartUploadStartTime());
                }
                if (multipartUploadRequestMetadata.getMultipartUploadExpirationTime() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, multipartUploadRequestMetadata.getMultipartUploadExpirationTime());
                }
            }
        };
        this.__preparedStmtOfDeleteRequest = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM multipart_upload_request_metadata where upload_request_id=?";
            }
        };
    }

    @Override // com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao
    public void deleteRequest(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteRequest.acquire();
        acquire.bindLong(1, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteRequest.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao
    public List<MultipartUploadRequestMetadata> getAll() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from multipart_upload_request_metadata", 0);
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "upload_request_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "node_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "part_size");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "total_parts");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "multipart_start_time");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "multipart_expiration_time");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new MultipartUploadRequestMetadata(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)), query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5)), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7)));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao
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

    @Override // com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao
    public MultipartUploadRequestMetadata getMultipartMetadataByRequestId(long j) {
        MultipartUploadRequestMetadata multipartUploadRequestMetadata;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from multipart_upload_request_metadata WHERE upload_request_id=? ", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Long l = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "upload_request_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "node_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "upload_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "part_size");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "total_parts");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "multipart_start_time");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "multipart_expiration_time");
            if (query.moveToFirst()) {
                long j2 = query.getLong(columnIndexOrThrow);
                String string = query.getString(columnIndexOrThrow2);
                String string2 = query.getString(columnIndexOrThrow3);
                Long valueOf = query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4));
                if (!query.isNull(columnIndexOrThrow5)) {
                    l = Long.valueOf(query.getLong(columnIndexOrThrow5));
                }
                multipartUploadRequestMetadata = new MultipartUploadRequestMetadata(j2, string, string2, valueOf, l, query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7));
            } else {
                multipartUploadRequestMetadata = null;
            }
            return multipartUploadRequestMetadata;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao
    public String getNodeIdForUploadRequest(long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT node_id from multipart_upload_request_metadata WHERE upload_request_id=? ", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        String str = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                str = query.getString(0);
            }
            return str;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao
    public String getServiceUploadIdForUploadRequest(long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT upload_id from multipart_upload_request_metadata WHERE upload_request_id=? ", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        String str = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                str = query.getString(0);
            }
            return str;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao
    public long insert(MultipartUploadRequestMetadata multipartUploadRequestMetadata) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfMultipartUploadRequestMetadata.insertAndReturnId(multipartUploadRequestMetadata);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }
}
