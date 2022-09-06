package com.amazon.commscore.commsidentity.database.dao;

import android.database.Cursor;
import androidx.core.util.Pair;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.commscore.commsidentity.database.schema.Account;
import com.amazon.commscore.commsidentity.database.schema.IdentityV2;
import com.amazon.commscore.commsidentity.database.schema.Name;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import io.reactivex.rxjava3.core.Flowable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public final class CommsIdentityDao_Impl extends CommsIdentityDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Account> __deletionAdapterOfAccount;
    private final EntityDeletionOrUpdateAdapter<IdentityV2> __deletionAdapterOfIdentityV2;
    private final EntityInsertionAdapter<Account> __insertionAdapterOfAccount;
    private final EntityInsertionAdapter<IdentityV2> __insertionAdapterOfIdentityV2;

    public CommsIdentityDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfAccount = new EntityInsertionAdapter<Account>(roomDatabase) { // from class: com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao_Impl.1
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
        this.__insertionAdapterOfIdentityV2 = new EntityInsertionAdapter<IdentityV2>(roomDatabase) { // from class: com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `identityv2_table` (`directed_id`,`homegroup_pce_id`,`comms_id`,`comms_provision_status`,`pce_id`,`aor`,`hashed_comms_id`,`homegroup_id`,`firstName`,`lastName`,`phoneticLastName`,`nickName`,`pronunciationFirstName`,`sourceNickName`,`pronunciationLastName`,`phoneticFirstName`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, IdentityV2 identityV2) {
                if (identityV2.getDirectedId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, identityV2.getDirectedId());
                }
                if (identityV2.getHomeGroupPceId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, identityV2.getHomeGroupPceId());
                }
                if (identityV2.getCommsId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, identityV2.getCommsId());
                }
                if (identityV2.getCommsProvisionStatus() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, identityV2.getCommsProvisionStatus());
                }
                if (identityV2.getPceId() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, identityV2.getPceId());
                }
                if (identityV2.getAor() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, identityV2.getAor());
                }
                if (identityV2.getHashedCommsId() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, identityV2.getHashedCommsId());
                }
                if (identityV2.getHomeGroupId() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, identityV2.getHomeGroupId());
                }
                Name name = identityV2.getName();
                if (name != null) {
                    if (name.getFirstName() == null) {
                        supportSQLiteStatement.bindNull(9);
                    } else {
                        supportSQLiteStatement.bindString(9, name.getFirstName());
                    }
                    if (name.getLastName() == null) {
                        supportSQLiteStatement.bindNull(10);
                    } else {
                        supportSQLiteStatement.bindString(10, name.getLastName());
                    }
                    if (name.getPhoneticLastName() == null) {
                        supportSQLiteStatement.bindNull(11);
                    } else {
                        supportSQLiteStatement.bindString(11, name.getPhoneticLastName());
                    }
                    if (name.getNickName() == null) {
                        supportSQLiteStatement.bindNull(12);
                    } else {
                        supportSQLiteStatement.bindString(12, name.getNickName());
                    }
                    if (name.getPronunciationFirstName() == null) {
                        supportSQLiteStatement.bindNull(13);
                    } else {
                        supportSQLiteStatement.bindString(13, name.getPronunciationFirstName());
                    }
                    if (name.getSourceNickName() == null) {
                        supportSQLiteStatement.bindNull(14);
                    } else {
                        supportSQLiteStatement.bindString(14, name.getSourceNickName());
                    }
                    if (name.getPronunciationLastName() == null) {
                        supportSQLiteStatement.bindNull(15);
                    } else {
                        supportSQLiteStatement.bindString(15, name.getPronunciationLastName());
                    }
                    if (name.getPhoneticFirstName() == null) {
                        supportSQLiteStatement.bindNull(16);
                        return;
                    } else {
                        supportSQLiteStatement.bindString(16, name.getPhoneticFirstName());
                        return;
                    }
                }
                supportSQLiteStatement.bindNull(9);
                supportSQLiteStatement.bindNull(10);
                supportSQLiteStatement.bindNull(11);
                supportSQLiteStatement.bindNull(12);
                supportSQLiteStatement.bindNull(13);
                supportSQLiteStatement.bindNull(14);
                supportSQLiteStatement.bindNull(15);
                supportSQLiteStatement.bindNull(16);
            }
        };
        this.__deletionAdapterOfAccount = new EntityDeletionOrUpdateAdapter<Account>(roomDatabase) { // from class: com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao_Impl.3
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
        this.__deletionAdapterOfIdentityV2 = new EntityDeletionOrUpdateAdapter<IdentityV2>(roomDatabase) { // from class: com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao_Impl.4
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `identityv2_table` WHERE `directed_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, IdentityV2 identityV2) {
                if (identityV2.getDirectedId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, identityV2.getDirectedId());
                }
            }
        };
    }

    @Override // com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao
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

    @Override // com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao
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

    @Override // com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao
    public List<Account> getAllAccounts() {
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

    @Override // com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao
    public List<IdentityV2> getAllIdentityV2() {
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
        Name name;
        ArrayList arrayList;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM identityv2_table", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "directed_id");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "homegroup_pce_id");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, MetricKeys.COMMS_ID);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "comms_provision_status");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "pce_id");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.PhoneNumberEntry.COLUMN_AOR);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "hashed_comms_id");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, MetricKeys.HOMEGROUP_ID);
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "firstName");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "lastName");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME);
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME);
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME);
            int i = columnIndexOrThrow8;
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME);
            int i2 = columnIndexOrThrow7;
            int i3 = columnIndexOrThrow6;
            ArrayList arrayList2 = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13) && query.isNull(columnIndexOrThrow14) && query.isNull(columnIndexOrThrow15) && query.isNull(columnIndexOrThrow16)) {
                    arrayList = arrayList2;
                    name = null;
                    IdentityV2 identityV2 = new IdentityV2();
                    int i4 = columnIndexOrThrow14;
                    identityV2.setDirectedId(query.getString(columnIndexOrThrow));
                    identityV2.setHomeGroupPceId(query.getString(columnIndexOrThrow2));
                    identityV2.setCommsId(query.getString(columnIndexOrThrow3));
                    identityV2.setCommsProvisionStatus(query.getString(columnIndexOrThrow4));
                    identityV2.setPceId(query.getString(columnIndexOrThrow5));
                    int i5 = i3;
                    int i6 = columnIndexOrThrow;
                    identityV2.setAor(query.getString(i5));
                    int i7 = i2;
                    identityV2.setHashedCommsId(query.getString(i7));
                    int i8 = i;
                    identityV2.setHomeGroupId(query.getString(i8));
                    identityV2.setName(name);
                    ArrayList arrayList3 = arrayList;
                    arrayList3.add(identityV2);
                    arrayList2 = arrayList3;
                    columnIndexOrThrow = i6;
                    i3 = i5;
                    i2 = i7;
                    i = i8;
                    columnIndexOrThrow14 = i4;
                }
                name = new Name();
                arrayList = arrayList2;
                name.setFirstName(query.getString(columnIndexOrThrow9));
                name.setLastName(query.getString(columnIndexOrThrow10));
                name.setPhoneticLastName(query.getString(columnIndexOrThrow11));
                name.setNickName(query.getString(columnIndexOrThrow12));
                name.setPronunciationFirstName(query.getString(columnIndexOrThrow13));
                name.setSourceNickName(query.getString(columnIndexOrThrow14));
                name.setPronunciationLastName(query.getString(columnIndexOrThrow15));
                name.setPhoneticFirstName(query.getString(columnIndexOrThrow16));
                IdentityV2 identityV22 = new IdentityV2();
                int i42 = columnIndexOrThrow14;
                identityV22.setDirectedId(query.getString(columnIndexOrThrow));
                identityV22.setHomeGroupPceId(query.getString(columnIndexOrThrow2));
                identityV22.setCommsId(query.getString(columnIndexOrThrow3));
                identityV22.setCommsProvisionStatus(query.getString(columnIndexOrThrow4));
                identityV22.setPceId(query.getString(columnIndexOrThrow5));
                int i52 = i3;
                int i62 = columnIndexOrThrow;
                identityV22.setAor(query.getString(i52));
                int i72 = i2;
                identityV22.setHashedCommsId(query.getString(i72));
                int i82 = i;
                identityV22.setHomeGroupId(query.getString(i82));
                identityV22.setName(name);
                ArrayList arrayList32 = arrayList;
                arrayList32.add(identityV22);
                arrayList2 = arrayList32;
                columnIndexOrThrow = i62;
                i3 = i52;
                i2 = i72;
                i = i82;
                columnIndexOrThrow14 = i42;
            }
            ArrayList arrayList4 = arrayList2;
            query.close();
            roomSQLiteQuery.release();
            return arrayList4;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao
    public Pair<Flowable<IdentityV2>, Flowable<Account>> getIdentityV2AndAccountDetails(String str) {
        this.__db.beginTransaction();
        try {
            Pair<Flowable<IdentityV2>, Flowable<Account>> identityV2AndAccountDetails = super.getIdentityV2AndAccountDetails(str);
            this.__db.setTransactionSuccessful();
            return identityV2AndAccountDetails;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao
    public IdentityV2 getIdentityV2ForDirectedId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        IdentityV2 identityV2;
        int i;
        Name name;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM identityv2_table WHERE directed_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "directed_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "homegroup_pce_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, MetricKeys.COMMS_ID);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "comms_provision_status");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "pce_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.PhoneNumberEntry.COLUMN_AOR);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "hashed_comms_id");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, MetricKeys.HOMEGROUP_ID);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "firstName");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "lastName");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME);
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME);
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME);
                if (query.moveToFirst()) {
                    if (query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13) && query.isNull(columnIndexOrThrow14) && query.isNull(columnIndexOrThrow15) && query.isNull(columnIndexOrThrow16)) {
                        i = columnIndexOrThrow7;
                        name = null;
                        identityV2 = new IdentityV2();
                        identityV2.setDirectedId(query.getString(columnIndexOrThrow));
                        identityV2.setHomeGroupPceId(query.getString(columnIndexOrThrow2));
                        identityV2.setCommsId(query.getString(columnIndexOrThrow3));
                        identityV2.setCommsProvisionStatus(query.getString(columnIndexOrThrow4));
                        identityV2.setPceId(query.getString(columnIndexOrThrow5));
                        identityV2.setAor(query.getString(columnIndexOrThrow6));
                        identityV2.setHashedCommsId(query.getString(i));
                        identityV2.setHomeGroupId(query.getString(columnIndexOrThrow8));
                        identityV2.setName(name);
                    }
                    i = columnIndexOrThrow7;
                    name = new Name();
                    name.setFirstName(query.getString(columnIndexOrThrow9));
                    name.setLastName(query.getString(columnIndexOrThrow10));
                    name.setPhoneticLastName(query.getString(columnIndexOrThrow11));
                    name.setNickName(query.getString(columnIndexOrThrow12));
                    name.setPronunciationFirstName(query.getString(columnIndexOrThrow13));
                    name.setSourceNickName(query.getString(columnIndexOrThrow14));
                    name.setPronunciationLastName(query.getString(columnIndexOrThrow15));
                    name.setPhoneticFirstName(query.getString(columnIndexOrThrow16));
                    identityV2 = new IdentityV2();
                    identityV2.setDirectedId(query.getString(columnIndexOrThrow));
                    identityV2.setHomeGroupPceId(query.getString(columnIndexOrThrow2));
                    identityV2.setCommsId(query.getString(columnIndexOrThrow3));
                    identityV2.setCommsProvisionStatus(query.getString(columnIndexOrThrow4));
                    identityV2.setPceId(query.getString(columnIndexOrThrow5));
                    identityV2.setAor(query.getString(columnIndexOrThrow6));
                    identityV2.setHashedCommsId(query.getString(i));
                    identityV2.setHomeGroupId(query.getString(columnIndexOrThrow8));
                    identityV2.setName(name);
                } else {
                    identityV2 = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return identityV2;
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

    @Override // com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao
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

    @Override // com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao
    public void insertIdentityV2AndAccountDetails(IdentityV2 identityV2, Account account) {
        this.__db.beginTransaction();
        try {
            super.insertIdentityV2AndAccountDetails(identityV2, account);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao
    public void delete(IdentityV2 identityV2) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfIdentityV2.handle(identityV2);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao
    public Long insert(IdentityV2 identityV2) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfIdentityV2.insertAndReturnId(identityV2);
            this.__db.setTransactionSuccessful();
            return Long.valueOf(insertAndReturnId);
        } finally {
            this.__db.endTransaction();
        }
    }
}
