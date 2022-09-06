package com.amazon.commscore.commsidentity.database.roomdb;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.amazon.commscore.commsidentity.database.dao.AccountDao;
import com.amazon.commscore.commsidentity.database.dao.AccountDao_Impl;
import com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao;
import com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao_Impl;
import com.amazon.commscore.commsidentity.database.dao.IdentityV2Dao;
import com.amazon.commscore.commsidentity.database.dao.IdentityV2Dao_Impl;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes12.dex */
public final class CommsCoreIdentityDatabase_Impl extends CommsCoreIdentityDatabase {
    private volatile AccountDao _accountDao;
    private volatile CommsIdentityDao _commsIdentityDao;
    private volatile IdentityV2Dao _identityV2Dao;

    @Override // com.amazon.commscore.commsidentity.database.roomdb.CommsCoreIdentityDatabase
    public AccountDao accountDao() {
        AccountDao accountDao;
        if (this._accountDao != null) {
            return this._accountDao;
        }
        synchronized (this) {
            if (this._accountDao == null) {
                this._accountDao = new AccountDao_Impl(this);
            }
            accountDao = this._accountDao;
        }
        return accountDao;
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `identityv2_table`");
            writableDatabase.execSQL("DELETE FROM `account`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            if (!GeneratedOutlineSupport1.outline188(writableDatabase, "PRAGMA wal_checkpoint(FULL)")) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // com.amazon.commscore.commsidentity.database.roomdb.CommsCoreIdentityDatabase
    public CommsIdentityDao commsIdentityDao() {
        CommsIdentityDao commsIdentityDao;
        if (this._commsIdentityDao != null) {
            return this._commsIdentityDao;
        }
        synchronized (this) {
            if (this._commsIdentityDao == null) {
                this._commsIdentityDao = new CommsIdentityDao_Impl(this);
            }
            commsIdentityDao = this._commsIdentityDao;
        }
        return commsIdentityDao;
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "identityv2_table", "account");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.mo201create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(1) { // from class: com.amazon.commscore.commsidentity.database.roomdb.CommsCoreIdentityDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `identityv2_table` (`directed_id` TEXT NOT NULL, `homegroup_pce_id` TEXT, `comms_id` TEXT, `comms_provision_status` TEXT, `pce_id` TEXT, `aor` TEXT, `hashed_comms_id` TEXT, `homegroup_id` TEXT, `firstName` TEXT, `lastName` TEXT, `phoneticLastName` TEXT, `nickName` TEXT, `pronunciationFirstName` TEXT, `sourceNickName` TEXT, `pronunciationLastName` TEXT, `phoneticFirstName` TEXT, PRIMARY KEY(`directed_id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `account` (`last_name` TEXT, `speaker_provisioned` INTEGER NOT NULL, `person_id_v2` TEXT, `comms_id` TEXT, `signed_in_user` INTEGER NOT NULL, `first_name` TEXT, `comms_provisioned` INTEGER NOT NULL, `phonetic_last_name` TEXT, `phone_number` TEXT, `phone_country_code` TEXT, `comms_provision_status` TEXT, `enrolled_in_alexa` INTEGER NOT NULL, `directed_id` TEXT NOT NULL, `is_child` INTEGER NOT NULL, `phonetic_first_name` TEXT, PRIMARY KEY(`directed_id`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '20b29c3c3df1b8f4809e5bad66145abb')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `identityv2_table`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `account`");
                if (((RoomDatabase) CommsCoreIdentityDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) CommsCoreIdentityDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) CommsCoreIdentityDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) CommsCoreIdentityDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) CommsCoreIdentityDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) CommsCoreIdentityDatabase_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) CommsCoreIdentityDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                CommsCoreIdentityDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) CommsCoreIdentityDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) CommsCoreIdentityDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) CommsCoreIdentityDatabase_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("directed_id", new TableInfo.Column("directed_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 1, null, 1));
                hashMap.put("homegroup_pce_id", new TableInfo.Column("homegroup_pce_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put(MetricKeys.COMMS_ID, new TableInfo.Column(MetricKeys.COMMS_ID, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("comms_provision_status", new TableInfo.Column("comms_provision_status", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("pce_id", new TableInfo.Column("pce_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR, new TableInfo.Column(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("hashed_comms_id", new TableInfo.Column("hashed_comms_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put(MetricKeys.HOMEGROUP_ID, new TableInfo.Column(MetricKeys.HOMEGROUP_ID, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("firstName", new TableInfo.Column("firstName", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("lastName", new TableInfo.Column("lastName", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME, new TableInfo.Column(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, new TableInfo.Column(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME, new TableInfo.Column(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, new TableInfo.Column(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME, new TableInfo.Column(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("identityv2_table", hashMap, GeneratedOutlineSupport1.outline135(hashMap, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME, new TableInfo.Column(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1), 0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "identityv2_table");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("identityv2_table(com.amazon.commscore.commsidentity.database.schema.IdentityV2).\n Expected:\n", tableInfo, "\n Found:\n", read));
                }
                HashMap hashMap2 = new HashMap(15);
                hashMap2.put("last_name", new TableInfo.Column("last_name", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap2.put("speaker_provisioned", new TableInfo.Column("speaker_provisioned", "INTEGER", true, 0, null, 1));
                hashMap2.put("person_id_v2", new TableInfo.Column("person_id_v2", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap2.put(MetricKeys.COMMS_ID, new TableInfo.Column(MetricKeys.COMMS_ID, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap2.put("signed_in_user", new TableInfo.Column("signed_in_user", "INTEGER", true, 0, null, 1));
                hashMap2.put("first_name", new TableInfo.Column("first_name", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap2.put("comms_provisioned", new TableInfo.Column("comms_provisioned", "INTEGER", true, 0, null, 1));
                hashMap2.put("phonetic_last_name", new TableInfo.Column("phonetic_last_name", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap2.put("phone_number", new TableInfo.Column("phone_number", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap2.put("phone_country_code", new TableInfo.Column("phone_country_code", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap2.put("comms_provision_status", new TableInfo.Column("comms_provision_status", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap2.put("enrolled_in_alexa", new TableInfo.Column("enrolled_in_alexa", "INTEGER", true, 0, null, 1));
                hashMap2.put("directed_id", new TableInfo.Column("directed_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 1, null, 1));
                hashMap2.put("is_child", new TableInfo.Column("is_child", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo("account", hashMap2, GeneratedOutlineSupport1.outline135(hashMap2, "phonetic_first_name", new TableInfo.Column("phonetic_first_name", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1), 0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "account");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("account(com.amazon.commscore.commsidentity.database.schema.Account).\n Expected:\n", tableInfo2, "\n Found:\n", read2));
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "20b29c3c3df1b8f4809e5bad66145abb", "52a069e965fc6b3d564abcf66a486b56")).build());
    }

    @Override // com.amazon.commscore.commsidentity.database.roomdb.CommsCoreIdentityDatabase
    public IdentityV2Dao identityV2Dao() {
        IdentityV2Dao identityV2Dao;
        if (this._identityV2Dao != null) {
            return this._identityV2Dao;
        }
        synchronized (this) {
            if (this._identityV2Dao == null) {
                this._identityV2Dao = new IdentityV2Dao_Impl(this);
            }
            identityV2Dao = this._identityV2Dao;
        }
        return identityV2Dao;
    }
}
