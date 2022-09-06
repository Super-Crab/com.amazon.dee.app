package com.amazon.alexa.featureservice.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.amazon.alexa.featureservice.database.entity.Feature;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes7.dex */
public final class FeatureFlagDao_Impl implements FeatureFlagDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Feature> __deletionAdapterOfFeature;
    private final EntityInsertionAdapter<Feature> __insertionAdapterOfFeature;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public FeatureFlagDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfFeature = new EntityInsertionAdapter<Feature>(roomDatabase) { // from class: com.amazon.alexa.featureservice.database.dao.FeatureFlagDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Feature` (`featureName`,`treatment`,`should_record_trigger`,`allocation_id`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Feature feature) {
                String str = feature.featureName;
                if (str == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, str);
                }
                String str2 = feature.treatment;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str2);
                }
                supportSQLiteStatement.bindLong(3, feature.shouldRecordTrigger ? 1L : 0L);
                String str3 = feature.allocationId;
                if (str3 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, str3);
                }
            }
        };
        this.__deletionAdapterOfFeature = new EntityDeletionOrUpdateAdapter<Feature>(roomDatabase) { // from class: com.amazon.alexa.featureservice.database.dao.FeatureFlagDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `Feature` WHERE `featureName` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Feature feature) {
                String str = feature.featureName;
                if (str == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, str);
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.alexa.featureservice.database.dao.FeatureFlagDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Feature";
            }
        };
    }

    @Override // com.amazon.alexa.featureservice.database.dao.FeatureFlagDao
    public int delete(Feature feature) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int handle = this.__deletionAdapterOfFeature.handle(feature) + 0;
            this.__db.setTransactionSuccessful();
            return handle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.alexa.featureservice.database.dao.FeatureFlagDao
    public int deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    @Override // com.amazon.alexa.featureservice.database.dao.FeatureFlagDao
    public Feature get(String str) {
        boolean z = true;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM FEATURE WHERE featureName = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Feature feature = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "featureName");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, FeatureConstants.Network.TREATMENT);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "should_record_trigger");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "allocation_id");
            if (query.moveToFirst()) {
                feature = new Feature();
                feature.featureName = query.getString(columnIndexOrThrow);
                feature.treatment = query.getString(columnIndexOrThrow2);
                if (query.getInt(columnIndexOrThrow3) == 0) {
                    z = false;
                }
                feature.shouldRecordTrigger = z;
                feature.allocationId = query.getString(columnIndexOrThrow4);
            }
            return feature;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.alexa.featureservice.database.dao.FeatureFlagDao
    public List<Feature> getAll() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM Feature", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "featureName");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, FeatureConstants.Network.TREATMENT);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "should_record_trigger");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "allocation_id");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                Feature feature = new Feature();
                feature.featureName = query.getString(columnIndexOrThrow);
                feature.treatment = query.getString(columnIndexOrThrow2);
                feature.shouldRecordTrigger = query.getInt(columnIndexOrThrow3) != 0;
                feature.allocationId = query.getString(columnIndexOrThrow4);
                arrayList.add(feature);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.alexa.featureservice.database.dao.FeatureFlagDao
    public long insert(Feature feature) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfFeature.insertAndReturnId(feature);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }
}
