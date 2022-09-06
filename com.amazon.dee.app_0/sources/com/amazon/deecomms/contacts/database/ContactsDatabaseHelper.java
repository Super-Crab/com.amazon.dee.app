package com.amazon.deecomms.contacts.database;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.database.DatabaseUtils;
import com.amazon.deecomms.common.database.ICommsDatabase;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.Set;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
/* loaded from: classes12.dex */
public class ContactsDatabaseHelper implements ICommsDatabase {
    private static final String COMMA_SEP = ",";
    private static final String DEFAULT_ZERO = " DEFAULT 0";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String SQL_CREATE_ADDRESSES = "CREATE TABLE Addresses ( _id INTEGER PRIMARY KEY,serverContactId TEXT,value TEXT,addressType TEXT,rawType TEXT, FOREIGN KEY(serverContactId) REFERENCES Contacts(serverContactId))";
    private static final String SQL_CREATE_CONTACTS = "CREATE TABLE Contacts ( _id INTEGER PRIMARY KEY,firstName TEXT,lastName TEXT,phoneticFirstName TEXT,phoneticLastName TEXT,pronunciationFirstName TEXT,pronunciationLastName TEXT,phoneticFirstNameSortFormat TEXT,phoneticLastNameSortFormat TEXT,nickName TEXT,sourceDeviceId TEXT,serverContactId TEXT UNIQUE,deviceContactId TEXT,ownerCommsId TEXT,alexaEnabled TEXT,bulkImport INTEGER DEFAULT 0,shouldDisplay INTEGER DEFAULT 0,canDropInOnMe INTEGER DEFAULT 0,canIDropInOnHim INTEGER DEFAULT 0,isNameEmpty INTEGER DEFAULT 0,isBlocked INTEGER DEFAULT 0,isEverRefreshed INTEGER DEFAULT 0,company TEXT,isChild INTEGER DEFAULT 0,relationship TEXT,referenceCommsId TEXT,referenceContactId TEXT,isLinked INTEGER DEFAULT 0,contactSourceType TEXT,sourceNickName TEXT,isMerged INTEGER DEFAULT 0,shouldMerge INTEGER DEFAULT 0)";
    private static final String SQL_CREATE_EMAIL_ADDRESSES = "CREATE TABLE EmailAddresses ( _id INTEGER PRIMARY KEY,serverContactId TEXT,email TEXT,emailType TEXT,emailRawType TEXT, FOREIGN KEY(serverContactId) REFERENCES Contacts(serverContactId))";
    private static final String SQL_CREATE_MERGED_CONTACT_IDENTIFIERS = "CREATE TABLE MergedContactIds ( _id INTEGER PRIMARY KEY,serverContactId TEXT,identifier TEXT, FOREIGN KEY(serverContactId) REFERENCES Contacts(serverContactId))";
    private static final String SQL_CREATE_PHONE_NUMBERS = "CREATE TABLE PhoneNumbers ( _id INTEGER PRIMARY KEY,serverContactId TEXT,number TEXT,numberType TEXT,numberRawType TEXT,commsId TEXT,aor TEXT,isHomeGroup INTEGER DEFAULT 0,parentHomeGroup TEXT,hashedPhoneNumber TEXT,isCoboCallable INTEGER DEFAULT 0, FOREIGN KEY(serverContactId) REFERENCES Contacts(serverContactId))";
    private static final String SQL_CREATE_UNGETTABLE_COMMSIDS = "CREATE TABLE UngettableCommsIds ( commsId TEXT PRIMARY KEY )";
    private static final String TEXT_TYPE = " TEXT";
    private static final String UNIQUE = " UNIQUE";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactsDatabaseHelper.class);
    public static final Set<Integer> DATABASE_VERSIONS_THAT_REQUIRE_FORCE_UPDATE_SET = new HashSet();

    static {
        DATABASE_VERSIONS_THAT_REQUIRE_FORCE_UPDATE_SET.add(15);
        DATABASE_VERSIONS_THAT_REQUIRE_FORCE_UPDATE_SET.add(17);
        DATABASE_VERSIONS_THAT_REQUIRE_FORCE_UPDATE_SET.add(21);
        DATABASE_VERSIONS_THAT_REQUIRE_FORCE_UPDATE_SET.add(27);
    }

    private void upgradeFromVersion1ToVersion2(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE PhoneNumbers ADD COLUMN commsId TEXT;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 1 -> 2 ", e);
        }
    }

    private void upgradeFromVersion2ToVersion3(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table PhoneNumbers add column isHomeGroup INTEGER DEFAULT 0;");
            sQLiteDatabase.execSQL("alter table PhoneNumbers add column aor TEXT;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 2 -> 3 ", e);
        }
    }

    private void upgradeFromVersion5ToVersion6(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table Contacts add column canDropInOnMe INTEGER DEFAULT 0;");
            sQLiteDatabase.execSQL("alter table Contacts add column canIDropInOnHim INTEGER DEFAULT 0;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 5 -> 6", e);
        }
    }

    private void upgradeFromVersion6ToVersion7(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table Contacts add column isNameEmpty INTEGER DEFAULT 0;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 6 -> 7", e);
        }
    }

    private void upgradeToVersion11(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(SQL_CREATE_UNGETTABLE_COMMSIDS);
        } catch (SQLException e) {
            LOG.e("Exception while upgrading to v11", e);
        }
    }

    private void upgradeToVersion12(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table Contacts add column isBlocked INTEGER DEFAULT 0;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 11 -> 12", e);
        }
    }

    private void upgradeToVersion15(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE PhoneNumbers ADD COLUMN numberRawType TEXT");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 14 -> 15", e);
        }
    }

    private void upgradeToVersion17(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN company TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN nickName TEXT");
            sQLiteDatabase.execSQL(SQL_CREATE_EMAIL_ADDRESSES);
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 16 -> 17", e);
        }
    }

    private void upgradeToVersion18(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN bulkImport INTEGER");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN shouldDisplay INTEGER");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN ownerCommsId TEXT");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 17 -> 18", e);
        }
    }

    private void upgradeToVersion19(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE PhoneNumbers ADD COLUMN hashedPhoneNumber TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE PhoneNumbers ADD COLUMN isCoboCallable INTEGER DEFAULT 0");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 18 -> 19", e);
        }
    }

    private void upgradeToVersion20(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN isEverRefreshed INTEGER");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 19 -> 20", e);
        }
    }

    private void upgradeToVersion21(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN phoneticFirstName TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN phoneticLastName TEXT");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 20 -> 21", e);
        }
    }

    private void upgradeToVersion22(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN isChild INTEGER");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN relationship TEXT");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 21 -> 22", e);
        }
    }

    private void upgradeToVersion23(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN phoneticFirstNameSortFormat TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN phoneticLastNameSortFormat TEXT");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 22 -> 23", e);
        }
    }

    private void upgradeToVersion24(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN referenceCommsId TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN referenceContactId TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN isLinked INTEGER");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 23 -> 24", e);
        }
    }

    private void upgradeToVersion25(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN contactSourceType TEXT");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 24 -> 25", e);
        }
    }

    private void upgradeToVersion27(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN sourceNickName TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN isMerged INTEGER");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN shouldMerge INTEGER");
            sQLiteDatabase.execSQL(SQL_CREATE_ADDRESSES);
            sQLiteDatabase.execSQL(SQL_CREATE_MERGED_CONTACT_IDENTIFIERS);
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 26 -> 27", e);
        }
    }

    private void upgradeToVersion28(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts DROP COLUMN sourceDeviceName;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 27 -> 28", e);
        }
    }

    private void upgradeToVersion29(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN pronunciationFirstName TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE Contacts ADD COLUMN pronunciationLastName TEXT");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from 28 -> 29", e);
        }
    }

    @Override // com.amazon.deecomms.common.database.ICommsDatabase
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(SQL_CREATE_CONTACTS);
        sQLiteDatabase.execSQL(SQL_CREATE_PHONE_NUMBERS);
        sQLiteDatabase.execSQL(SQL_CREATE_UNGETTABLE_COMMSIDS);
        sQLiteDatabase.execSQL(SQL_CREATE_EMAIL_ADDRESSES);
        sQLiteDatabase.execSQL(SQL_CREATE_ADDRESSES);
        sQLiteDatabase.execSQL(SQL_CREATE_MERGED_CONTACT_IDENTIFIERS);
        sQLiteDatabase.execSQL(DatabaseUtils.createUniqueIndex(ContactProviderContract.PhoneNumberEntry.TABLE_NAME, ContactProviderContract.PhoneNumberEntry.INDEX_NAME, "serverContactId", "number", "commsId"));
        sQLiteDatabase.execSQL(DatabaseUtils.createUniqueIndex(ContactProviderContract.EmailAddressEntry.TABLE_NAME, ContactProviderContract.EmailAddressEntry.INDEX_NAME, "serverContactId", "email"));
    }

    @Override // com.amazon.deecomms.common.database.ICommsDatabase
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        switch (i) {
            case 1:
                upgradeFromVersion1ToVersion2(sQLiteDatabase);
            case 2:
                upgradeFromVersion2ToVersion3(sQLiteDatabase);
            case 3:
            case 4:
            case 5:
                upgradeFromVersion5ToVersion6(sQLiteDatabase);
            case 6:
                upgradeFromVersion6ToVersion7(sQLiteDatabase);
            case 7:
            case 8:
            case 9:
            case 10:
                upgradeToVersion11(sQLiteDatabase);
            case 11:
                upgradeToVersion12(sQLiteDatabase);
            case 12:
            case 13:
            case 14:
                upgradeToVersion15(sQLiteDatabase);
            case 15:
            case 16:
                upgradeToVersion17(sQLiteDatabase);
            case 17:
                upgradeToVersion18(sQLiteDatabase);
            case 18:
                upgradeToVersion19(sQLiteDatabase);
            case 19:
                upgradeToVersion20(sQLiteDatabase);
            case 20:
                upgradeToVersion21(sQLiteDatabase);
            case 21:
                upgradeToVersion22(sQLiteDatabase);
            case 22:
                upgradeToVersion23(sQLiteDatabase);
            case 23:
                upgradeToVersion24(sQLiteDatabase);
            case 24:
                upgradeToVersion25(sQLiteDatabase);
            case 25:
            case 26:
                upgradeToVersion27(sQLiteDatabase);
            case 27:
                upgradeToVersion28(sQLiteDatabase);
                break;
            case 28:
                break;
            default:
                throw new IllegalStateException(GeneratedOutlineSupport1.outline49("onUpgrade() with unknown oldVersion ", i));
        }
        upgradeToVersion29(sQLiteDatabase);
    }
}
