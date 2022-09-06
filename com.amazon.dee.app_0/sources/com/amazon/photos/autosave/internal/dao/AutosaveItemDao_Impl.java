package com.amazon.photos.autosave.internal.dao;

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
import com.amazon.photos.autosave.internal.db.Converters;
import com.amazon.photos.autosave.model.AutosaveItem;
import com.amazon.photos.autosave.model.AutosaveState;
import com.amazon.photos.autosave.model.MediaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public final class AutosaveItemDao_Impl extends AutosaveItemDao {
    private final Converters __converters = new Converters();
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<AutosaveItem> __deletionAdapterOfAutosaveItem;
    private final EntityInsertionAdapter<AutosaveItem> __insertionAdapterOfAutosaveItem;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final SharedSQLiteStatement __preparedStmtOfUpdateStateByFilePath;
    private final EntityDeletionOrUpdateAdapter<AutosaveItem> __updateAdapterOfAutosaveItem;

    public AutosaveItemDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfAutosaveItem = new EntityInsertionAdapter<AutosaveItem>(roomDatabase) { // from class: com.amazon.photos.autosave.internal.dao.AutosaveItemDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `autosave_item` (`id`,`local_item_id`,`unified_id`,`folder_id`,`file_path`,`state`,`media_type`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AutosaveItem autosaveItem) {
                supportSQLiteStatement.bindLong(1, autosaveItem.getId());
                supportSQLiteStatement.bindLong(2, autosaveItem.getLocalItemId());
                supportSQLiteStatement.bindLong(3, autosaveItem.getUnifiedId());
                supportSQLiteStatement.bindLong(4, autosaveItem.getFolderId());
                if (autosaveItem.getFilePath() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, autosaveItem.getFilePath());
                }
                String fromAutosaveState = AutosaveItemDao_Impl.this.__converters.fromAutosaveState(autosaveItem.getState());
                if (fromAutosaveState == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, fromAutosaveState);
                }
                String fromMediaType = AutosaveItemDao_Impl.this.__converters.fromMediaType(autosaveItem.getMediaType());
                if (fromMediaType == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, fromMediaType);
                }
            }
        };
        this.__deletionAdapterOfAutosaveItem = new EntityDeletionOrUpdateAdapter<AutosaveItem>(roomDatabase) { // from class: com.amazon.photos.autosave.internal.dao.AutosaveItemDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `autosave_item` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AutosaveItem autosaveItem) {
                supportSQLiteStatement.bindLong(1, autosaveItem.getId());
            }
        };
        this.__updateAdapterOfAutosaveItem = new EntityDeletionOrUpdateAdapter<AutosaveItem>(roomDatabase) { // from class: com.amazon.photos.autosave.internal.dao.AutosaveItemDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `autosave_item` SET `id` = ?,`local_item_id` = ?,`unified_id` = ?,`folder_id` = ?,`file_path` = ?,`state` = ?,`media_type` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AutosaveItem autosaveItem) {
                supportSQLiteStatement.bindLong(1, autosaveItem.getId());
                supportSQLiteStatement.bindLong(2, autosaveItem.getLocalItemId());
                supportSQLiteStatement.bindLong(3, autosaveItem.getUnifiedId());
                supportSQLiteStatement.bindLong(4, autosaveItem.getFolderId());
                if (autosaveItem.getFilePath() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, autosaveItem.getFilePath());
                }
                String fromAutosaveState = AutosaveItemDao_Impl.this.__converters.fromAutosaveState(autosaveItem.getState());
                if (fromAutosaveState == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, fromAutosaveState);
                }
                String fromMediaType = AutosaveItemDao_Impl.this.__converters.fromMediaType(autosaveItem.getMediaType());
                if (fromMediaType == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, fromMediaType);
                }
                supportSQLiteStatement.bindLong(8, autosaveItem.getId());
            }
        };
        this.__preparedStmtOfUpdateStateByFilePath = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.autosave.internal.dao.AutosaveItemDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE autosave_item SET state = ? WHERE file_path =?";
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.autosave.internal.dao.AutosaveItemDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM autosave_item WHERE id = ?";
            }
        };
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public void deleteById(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteById.acquire();
        acquire.bindLong(1, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteById.release(acquire);
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public void deleteItem(AutosaveItem autosaveItem) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfAutosaveItem.handle(autosaveItem);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public void deleteItemsByIds(List<Long> list) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("DELETE FROM autosave_item WHERE id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, list.size());
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        int i = 1;
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

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public List<AutosaveItem> getAllItems() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM autosave_item", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "local_item_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "media_type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new AutosaveItem(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), this.__converters.fromAutosaveStateString(query.getString(columnIndexOrThrow6)), this.__converters.fromMediaTypeString(query.getString(columnIndexOrThrow7))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public int getCountForState(AutosaveState autosaveState) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) FROM autosave_item WHERE state = ?", 1);
        String fromAutosaveState = this.__converters.fromAutosaveState(autosaveState);
        if (fromAutosaveState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromAutosaveState);
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

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public AutosaveItem getItemById(long j) {
        AutosaveItem autosaveItem;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM autosave_item WHERE id = ?", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "local_item_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "media_type");
            if (query.moveToFirst()) {
                autosaveItem = new AutosaveItem(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), this.__converters.fromAutosaveStateString(query.getString(columnIndexOrThrow6)), this.__converters.fromMediaTypeString(query.getString(columnIndexOrThrow7)));
            } else {
                autosaveItem = null;
            }
            return autosaveItem;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public List<AutosaveItem> getItemsByFolderIdAndState(long j, AutosaveState autosaveState) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM autosave_item WHERE folder_id = ? AND state = ?", 2);
        acquire.bindLong(1, j);
        String fromAutosaveState = this.__converters.fromAutosaveState(autosaveState);
        if (fromAutosaveState == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, fromAutosaveState);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "local_item_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "media_type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new AutosaveItem(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), this.__converters.fromAutosaveStateString(query.getString(columnIndexOrThrow6)), this.__converters.fromMediaTypeString(query.getString(columnIndexOrThrow7))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public List<AutosaveItem> getItemsByLocalIds(List<Long> list) {
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM autosave_item WHERE local_item_id IN (");
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
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "local_item_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "media_type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new AutosaveItem(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), this.__converters.fromAutosaveStateString(query.getString(columnIndexOrThrow6)), this.__converters.fromMediaTypeString(query.getString(columnIndexOrThrow7))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public List<AutosaveItem> getItemsByMediaTypeAndState(MediaType mediaType, AutosaveState autosaveState) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM autosave_item WHERE media_type = ? AND state = ?", 2);
        String fromMediaType = this.__converters.fromMediaType(mediaType);
        if (fromMediaType == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromMediaType);
        }
        String fromAutosaveState = this.__converters.fromAutosaveState(autosaveState);
        if (fromAutosaveState == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, fromAutosaveState);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "local_item_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "media_type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new AutosaveItem(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), this.__converters.fromAutosaveStateString(query.getString(columnIndexOrThrow6)), this.__converters.fromMediaTypeString(query.getString(columnIndexOrThrow7))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public List<AutosaveItem> getItemsForState(AutosaveState autosaveState) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM autosave_item WHERE state = ?", 1);
        String fromAutosaveState = this.__converters.fromAutosaveState(autosaveState);
        if (fromAutosaveState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromAutosaveState);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "local_item_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "media_type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new AutosaveItem(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), this.__converters.fromAutosaveStateString(query.getString(columnIndexOrThrow6)), this.__converters.fromMediaTypeString(query.getString(columnIndexOrThrow7))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public long insertItem(AutosaveItem autosaveItem) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfAutosaveItem.insertAndReturnId(autosaveItem);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public void updateItem(AutosaveItem autosaveItem) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfAutosaveItem.handle(autosaveItem);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveItemDao
    public void updateStateByFilePath(String str, AutosaveState autosaveState) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateStateByFilePath.acquire();
        String fromAutosaveState = this.__converters.fromAutosaveState(autosaveState);
        if (fromAutosaveState == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromAutosaveState);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateStateByFilePath.release(acquire);
        }
    }
}
