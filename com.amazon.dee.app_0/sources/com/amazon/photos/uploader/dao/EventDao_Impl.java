package com.amazon.photos.uploader.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.photos.uploader.Event;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public final class EventDao_Impl extends EventDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Event> __insertionAdapterOfEvent;
    private final SharedSQLiteStatement __preparedStmtOfDeleteOldEvents$AndroidPhotosUploader_release;

    public EventDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfEvent = new EntityInsertionAdapter<Event>(roomDatabase) { // from class: com.amazon.photos.uploader.dao.EventDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `events` (`id`,`description`,`event_time_millis`) VALUES (nullif(?, 0),?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Event event) {
                supportSQLiteStatement.bindLong(1, event.getId());
                if (event.getDescription() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, event.getDescription());
                }
                supportSQLiteStatement.bindLong(3, event.getEventTimeMillis());
            }
        };
        this.__preparedStmtOfDeleteOldEvents$AndroidPhotosUploader_release = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.dao.EventDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM events WHERE event_time_millis < ?";
            }
        };
    }

    @Override // com.amazon.photos.uploader.dao.EventDao
    public void deleteOldEvents$AndroidPhotosUploader_release(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteOldEvents$AndroidPhotosUploader_release.acquire();
        acquire.bindLong(1, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteOldEvents$AndroidPhotosUploader_release.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.dao.EventDao
    public List<Event> getEvents() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM events ORDER BY id ASC", 0);
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "description");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "event_time_millis");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new Event(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3)));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.uploader.dao.EventDao
    public long insertEvent$AndroidPhotosUploader_release(Event event) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfEvent.insertAndReturnId(event);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }
}
