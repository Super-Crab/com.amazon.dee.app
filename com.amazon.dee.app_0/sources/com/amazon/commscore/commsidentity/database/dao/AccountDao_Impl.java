package com.amazon.commscore.commsidentity.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.commscore.commsidentity.database.schema.Account;
import com.amazon.deecomms.common.metrics.MetricKeys;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public final class AccountDao_Impl implements AccountDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Account> __deletionAdapterOfAccount;
    private final EntityInsertionAdapter<Account> __insertionAdapterOfAccount;

    public AccountDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfAccount = new EntityInsertionAdapter<Account>(roomDatabase) { // from class: com.amazon.commscore.commsidentity.database.dao.AccountDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `account` (`last_name`,`speaker_provisioned`,`person_id_v2`,`comms_id`,`signed_in_user`,`first_name`,`comms_provisioned`,`phonetic_last_name`,`phone_number`,`phone_country_code`,`comms_provision_status`,`enrolled_in_alexa`,`directed_id`,`is_child`,`phonetic_first_name`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Account account) {
                if (account.getLastName() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, account.getLastName());
                }
                supportSQLiteStatement.bindLong(2, account.isSpeakerProvisioned() ? 1L : 0L);
                if (account.getPersonIdV2() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, account.getPersonIdV2());
                }
                if (account.getCommsId() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, account.getCommsId());
                }
                supportSQLiteStatement.bindLong(5, account.isSignedInUser() ? 1L : 0L);
                if (account.getFirstName() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, account.getFirstName());
                }
                supportSQLiteStatement.bindLong(7, account.isCommsProvisioned() ? 1L : 0L);
                if (account.getPhoneticLastName() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, account.getPhoneticLastName());
                }
                if (account.getPhoneNumber() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, account.getPhoneNumber());
                }
                if (account.getPhoneCountryCode() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, account.getPhoneCountryCode());
                }
                if (account.getCommsProvisionStatus() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, account.getCommsProvisionStatus());
                }
                supportSQLiteStatement.bindLong(12, account.isEnrolledInAlexa() ? 1L : 0L);
                if (account.getDirectedId() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, account.getDirectedId());
                }
                supportSQLiteStatement.bindLong(14, account.isIsChild() ? 1L : 0L);
                if (account.getPhoneticFirstName() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, account.getPhoneticFirstName());
                }
            }
        };
        this.__deletionAdapterOfAccount = new EntityDeletionOrUpdateAdapter<Account>(roomDatabase) { // from class: com.amazon.commscore.commsidentity.database.dao.AccountDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `account` WHERE `directed_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Account account) {
                if (account.getDirectedId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, account.getDirectedId());
                }
            }
        };
    }

    @Override // com.amazon.commscore.commsidentity.database.dao.AccountDao
    public void delete(Account account) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfAccount.handle(account);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.commscore.commsidentity.database.dao.AccountDao
    public Account getAccountDetailsForDirectedId(String str) {
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
        int columnIndexOrThrow14;
        Account account;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM account WHERE directed_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "last_name");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "speaker_provisioned");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "person_id_v2");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, MetricKeys.COMMS_ID);
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "signed_in_user");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "first_name");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "comms_provisioned");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "phonetic_last_name");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "phone_number");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "phone_country_code");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "comms_provision_status");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "enrolled_in_alexa");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "directed_id");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "is_child");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "phonetic_first_name");
            if (query.moveToFirst()) {
                account = new Account();
                account.setLastName(query.getString(columnIndexOrThrow));
                account.setSpeakerProvisioned(query.getInt(columnIndexOrThrow2) != 0);
                account.setPersonIdV2(query.getString(columnIndexOrThrow3));
                account.setCommsId(query.getString(columnIndexOrThrow4));
                account.setSignedInUser(query.getInt(columnIndexOrThrow5) != 0);
                account.setFirstName(query.getString(columnIndexOrThrow6));
                account.setCommsProvisioned(query.getInt(columnIndexOrThrow7) != 0);
                account.setPhoneticLastName(query.getString(columnIndexOrThrow8));
                account.setPhoneNumber(query.getString(columnIndexOrThrow9));
                account.setPhoneCountryCode(query.getString(columnIndexOrThrow10));
                account.setCommsProvisionStatus(query.getString(columnIndexOrThrow11));
                account.setEnrolledInAlexa(query.getInt(columnIndexOrThrow12) != 0);
                account.setDirectedId(query.getString(columnIndexOrThrow13));
                account.setIsChild(query.getInt(columnIndexOrThrow14) != 0);
                account.setPhoneticFirstName(query.getString(columnIndexOrThrow15));
            } else {
                account = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return account;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.amazon.commscore.commsidentity.database.dao.AccountDao
    public List<Account> getAll() {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        boolean z;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM account", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "last_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "speaker_provisioned");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "person_id_v2");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, MetricKeys.COMMS_ID);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "signed_in_user");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "first_name");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "comms_provisioned");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "phonetic_last_name");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "phone_number");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "phone_country_code");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "comms_provision_status");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "enrolled_in_alexa");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "directed_id");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "is_child");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "phonetic_first_name");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Account account = new Account();
                    ArrayList arrayList2 = arrayList;
                    account.setLastName(query.getString(columnIndexOrThrow));
                    account.setSpeakerProvisioned(query.getInt(columnIndexOrThrow2) != 0);
                    account.setPersonIdV2(query.getString(columnIndexOrThrow3));
                    account.setCommsId(query.getString(columnIndexOrThrow4));
                    account.setSignedInUser(query.getInt(columnIndexOrThrow5) != 0);
                    account.setFirstName(query.getString(columnIndexOrThrow6));
                    account.setCommsProvisioned(query.getInt(columnIndexOrThrow7) != 0);
                    account.setPhoneticLastName(query.getString(columnIndexOrThrow8));
                    account.setPhoneNumber(query.getString(columnIndexOrThrow9));
                    account.setPhoneCountryCode(query.getString(columnIndexOrThrow10));
                    account.setCommsProvisionStatus(query.getString(columnIndexOrThrow11));
                    account.setEnrolledInAlexa(query.getInt(columnIndexOrThrow12) != 0);
                    account.setDirectedId(query.getString(columnIndexOrThrow13));
                    int i3 = i2;
                    if (query.getInt(i3) != 0) {
                        i = columnIndexOrThrow13;
                        z = true;
                    } else {
                        i = columnIndexOrThrow13;
                        z = false;
                    }
                    account.setIsChild(z);
                    int i4 = columnIndexOrThrow15;
                    account.setPhoneticFirstName(query.getString(i4));
                    arrayList = arrayList2;
                    arrayList.add(account);
                    columnIndexOrThrow15 = i4;
                    columnIndexOrThrow13 = i;
                    i2 = i3;
                }
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
    }

    @Override // com.amazon.commscore.commsidentity.database.dao.AccountDao
    public Long insert(Account account) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfAccount.insertAndReturnId(account);
            this.__db.setTransactionSuccessful();
            return Long.valueOf(insertAndReturnId);
        } finally {
            this.__db.endTransaction();
        }
    }
}
