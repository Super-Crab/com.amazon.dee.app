package com.amazon.alexa.assetManagementService.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.alexa.assetManagementService.entity.AssetMapping;
import com.amazon.alexa.assetManagementService.model.constants.LocalDBQuery;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public final class AssetMappingDao_Impl implements AssetMappingDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<AssetMapping> __insertionAdapterOfAssetMapping;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public AssetMappingDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfAssetMapping = new EntityInsertionAdapter<AssetMapping>(roomDatabase) { // from class: com.amazon.alexa.assetManagementService.dao.AssetMappingDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `AssetMapping` (`bundleId`,`resourceId`,`assetURL`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AssetMapping assetMapping) {
                String str = assetMapping.bundleId;
                if (str == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, str);
                }
                String str2 = assetMapping.resourceId;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str2);
                }
                String str3 = assetMapping.assetURL;
                if (str3 == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, str3);
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.alexa.assetManagementService.dao.AssetMappingDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return LocalDBQuery.DELETE_ALL_ASSET_MAPPINGS_QUERY;
            }
        };
    }

    @Override // com.amazon.alexa.assetManagementService.dao.AssetMappingDao
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    @Override // com.amazon.alexa.assetManagementService.dao.AssetMappingDao
    public List<AssetMapping> get(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from AssetMapping WHERE bundleId = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "bundleId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "resourceId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, LocalDBQuery.ASSET_URL_COLUMN);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                AssetMapping assetMapping = new AssetMapping();
                assetMapping.bundleId = query.getString(columnIndexOrThrow);
                assetMapping.resourceId = query.getString(columnIndexOrThrow2);
                assetMapping.assetURL = query.getString(columnIndexOrThrow3);
                arrayList.add(assetMapping);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.alexa.assetManagementService.dao.AssetMappingDao
    public List<AssetMapping> getAll() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(LocalDBQuery.SELECT_ALL_ASSET_MAPPINGS_QUERY, 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "bundleId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "resourceId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, LocalDBQuery.ASSET_URL_COLUMN);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                AssetMapping assetMapping = new AssetMapping();
                assetMapping.bundleId = query.getString(columnIndexOrThrow);
                assetMapping.resourceId = query.getString(columnIndexOrThrow2);
                assetMapping.assetURL = query.getString(columnIndexOrThrow3);
                arrayList.add(assetMapping);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.alexa.assetManagementService.dao.AssetMappingDao
    public List<String> getAllBundleIds() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(LocalDBQuery.SELECT_ALL_BUNDLE_IDS_QUERY, 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.alexa.assetManagementService.dao.AssetMappingDao
    public void insert(AssetMapping assetMapping) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAssetMapping.insert((EntityInsertionAdapter<AssetMapping>) assetMapping);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
