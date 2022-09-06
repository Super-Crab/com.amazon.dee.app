package com.amazon.deecomms.contacts.database.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.common.database.CommsDatabaseHelper;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.messaging.provider.MessagingProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteConstraintException;
import net.sqlcipher.database.SQLiteDatabase;
/* loaded from: classes12.dex */
public class ContactsProvider extends ContentProvider {
    private static final int COMMS_ID_JOIN_CONTACT_JOIN_EMAIL_ADDRESSES_CODE = 8;
    private static final int COMMS_ID_TO_CONTACTS_CODE = 3;
    private static final int COMMS_ID_TO_CONTACTS_WITH_DROP_IN_CODE = 5;
    private static final int CONTACTS_METADATA = 4;
    private static final int CONTACT_ADDRESS_CODE = 9;
    private static final int CONTACT_CODE = 0;
    private static final int CONTACT_JOIN_MERGED_JOIN_PHONE_NUMBER_CODE = 11;
    private static final int CONTACT_JOIN_PHONE_NUMBER_CODE = 2;
    private static final int EMAIL_ADDRESSES_CODE = 7;
    private static final int MERGED_CONTACT_IDS_CODE = 10;
    private static final int PHONE_NUMBER_CODE = 1;
    private static final ImmutableMap<Integer, List<String>> RESULTCODE_TO_SELECTION_ARGS = ImmutableMap.builder().mo7828put(1, ImmutableList.of("serverContactId", "commsId", "number")).mo7828put(0, ImmutableList.of("serverContactId")).mo7828put(7, ImmutableList.of("serverContactId", "email")).mo7828put(9, ImmutableList.of("serverContactId", "value")).mo7828put(10, ImmutableList.of("serverContactId", "identifier")).mo7826build();
    private static final int UNGETTABLE_COMMS_IDS = 6;
    private SQLiteDatabase mApplyBatchDb;
    private CommsDatabaseHelper mDbHelper;
    private List<Uri> mDelayedNotifications;
    private final UriMatcher uriMatcher = new UriMatcher(-1);
    private boolean mApplyingBatch = false;

    private long insertOrUpdateById(SQLiteDatabase sQLiteDatabase, Uri uri, String str, ContentValues contentValues) throws SQLException {
        try {
            return sQLiteDatabase.insertOrThrow(str, null, contentValues);
        } catch (SQLiteConstraintException e) {
            if (update(uri, contentValues) <= 0) {
                throw e;
            }
            return -1L;
        }
    }

    private void sendNotification(Uri uri) {
        if (this.mApplyingBatch) {
            synchronized (this.mDelayedNotifications) {
                if (!this.mDelayedNotifications.contains(uri)) {
                    this.mDelayedNotifications.add(uri);
                }
            }
            return;
        }
        getContext().getContentResolver().notifyChange(uri, null);
    }

    @VisibleForTesting
    void addUri(UriMatcher uriMatcher) {
        String authorityId = ContactProviderContract.getAuthorityId(getContext());
        uriMatcher.addURI(authorityId, ContactProviderContract.CONTACT_PATH, 0);
        uriMatcher.addURI(authorityId, "phoneNumber", 1);
        uriMatcher.addURI(authorityId, ContactProviderContract.CONTACTS_JOIN_PHONE_NUMBER_PATH, 2);
        uriMatcher.addURI(authorityId, ContactProviderContract.COMMS_ID_TO_CONTACTS_PATH, 3);
        uriMatcher.addURI(authorityId, ContactProviderContract.COMMS_ID_TO_CONTACTS_MSG_PATH, 5);
        uriMatcher.addURI(authorityId, ContactProviderContract.UNGETTABLE_COMMSIDS_PATH, 6);
        uriMatcher.addURI(authorityId, ContactProviderContract.EMAIL_ADDRESS_PATH, 7);
        uriMatcher.addURI(authorityId, ContactProviderContract.COMMS_ID_TO_CONTACTS_TO_EMAIL_ADDRESS_PATH, 8);
        uriMatcher.addURI(authorityId, ContactProviderContract.CONTACT_ADDRESS_PATH, 9);
        uriMatcher.addURI(authorityId, ContactProviderContract.MERGED_CONTACT_IDS_PATH, 10);
        uriMatcher.addURI(authorityId, ContactProviderContract.CONTACTS_JOIN_MERGED_JOIN_PHONE_NUMBER_PATH, 11);
    }

    @Override // android.content.ContentProvider
    @NonNull
    public synchronized ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        ContentProviderResult[] applyBatch;
        SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        this.mApplyingBatch = true;
        this.mApplyBatchDb = writableDatabase;
        applyBatch = super.applyBatch(arrayList);
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
        this.mApplyingBatch = false;
        this.mApplyBatchDb = null;
        synchronized (this.mDelayedNotifications) {
            for (Uri uri : this.mDelayedNotifications) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            this.mDelayedNotifications.clear();
        }
        return applyBatch;
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, String str, String[] strArr) {
        int delete;
        SQLiteDatabase sQLiteDatabase = this.mApplyBatchDb;
        if (sQLiteDatabase == null) {
            sQLiteDatabase = this.mDbHelper.getWritableDatabase();
        }
        int match = this.uriMatcher.match(uri);
        if (match == 0) {
            delete = sQLiteDatabase.delete(ContactProviderContract.ContactDatabaseEntry.TABLE_NAME, str, strArr);
        } else if (match == 1) {
            delete = sQLiteDatabase.delete(ContactProviderContract.PhoneNumberEntry.TABLE_NAME, str, strArr);
        } else if (match == 6) {
            delete = sQLiteDatabase.delete(ContactProviderContract.UngettableCommsIds.TABLE_NAME, str, strArr);
        } else if (match == 7) {
            delete = sQLiteDatabase.delete(ContactProviderContract.EmailAddressEntry.TABLE_NAME, str, strArr);
        } else if (match == 9) {
            delete = sQLiteDatabase.delete(ContactProviderContract.AddressEntry.TABLE_NAME, str, strArr);
        } else if (match != 10) {
            return -1;
        } else {
            delete = sQLiteDatabase.delete(ContactProviderContract.MergedContactIdentifier.TABLE_NAME, str, strArr);
        }
        sendNotification(uri);
        return delete;
    }

    @Override // android.content.ContentProvider
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        long insertOrUpdateById;
        SQLiteDatabase sQLiteDatabase = this.mApplyBatchDb;
        if (sQLiteDatabase == null) {
            sQLiteDatabase = this.mDbHelper.getWritableDatabase();
        }
        int match = this.uriMatcher.match(uri);
        if (match == 0) {
            insertOrUpdateById = insertOrUpdateById(sQLiteDatabase, uri, ContactProviderContract.ContactDatabaseEntry.TABLE_NAME, contentValues);
        } else if (match == 1) {
            insertOrUpdateById = insertOrUpdateById(sQLiteDatabase, uri, ContactProviderContract.PhoneNumberEntry.TABLE_NAME, contentValues);
        } else if (match == 6) {
            insertOrUpdateById = sQLiteDatabase.insertWithOnConflict(ContactProviderContract.UngettableCommsIds.TABLE_NAME, null, contentValues, 4);
        } else if (match == 7) {
            insertOrUpdateById = insertOrUpdateById(sQLiteDatabase, uri, ContactProviderContract.EmailAddressEntry.TABLE_NAME, contentValues);
        } else if (match == 9) {
            insertOrUpdateById = insertOrUpdateById(sQLiteDatabase, uri, ContactProviderContract.AddressEntry.TABLE_NAME, contentValues);
        } else if (match != 10) {
            return null;
        } else {
            insertOrUpdateById = insertOrUpdateById(sQLiteDatabase, uri, ContactProviderContract.MergedContactIdentifier.TABLE_NAME, contentValues);
        }
        sendNotification(uri);
        return ContentUris.withAppendedId(uri, insertOrUpdateById);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mDbHelper = CommsDatabaseHelper.getInstance(getContext());
        this.mDelayedNotifications = new ArrayList();
        addUri(this.uriMatcher);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(@NonNull Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        net.sqlcipher.Cursor query;
        SQLiteDatabase readableDatabase = this.mDbHelper.getReadableDatabase();
        switch (this.uriMatcher.match(uri)) {
            case 0:
                query = readableDatabase.query(ContactProviderContract.ContactDatabaseEntry.TABLE_NAME, strArr, str, strArr2, null, null, str2);
                break;
            case 1:
                query = readableDatabase.query(ContactProviderContract.PhoneNumberEntry.TABLE_NAME, strArr, str, strArr2, null, null, str2);
                break;
            case 2:
                query = readableDatabase.query(GeneratedOutlineSupport1.outline93(GeneratedOutlineSupport1.outline116(ContactProviderContract.ContactDatabaseEntry.TABLE_NAME, " LEFT OUTER JOIN ", ContactProviderContract.PhoneNumberEntry.TABLE_NAME, " ON ", ContactProviderContract.ContactDatabaseEntry.TABLE_NAME_PREFIX), "serverContactId", " = ", ContactProviderContract.PhoneNumberEntry.TABLE_NAME_PREFIX, "serverContactId"), strArr, str, strArr2, null, null, str2);
                break;
            case 3:
                query = readableDatabase.query(ContactProviderContract.COMMS_IDS_TO_CONTACTS_JOIN_TABLE, strArr, str, strArr2, null, null, str2);
                break;
            case 4:
            default:
                return null;
            case 5:
                query = readableDatabase.query("PhoneNumbers LEFT JOIN Contacts ON Contacts.serverContactId = PhoneNumbers.serverContactId LEFT JOIN PhoneNumbers AS PhoneNumbersAlias ON PhoneNumbers.parentHomeGroup = PhoneNumbersAlias.commsId LEFT JOIN Contacts AS ContactsAlias ON PhoneNumbersAlias.serverContactId = ContactsAlias.serverContactId", strArr, str, strArr2, null, null, str2);
                break;
            case 6:
                query = readableDatabase.query(ContactProviderContract.UngettableCommsIds.TABLE_NAME, strArr, str, strArr2, null, null, str2);
                break;
            case 7:
                query = readableDatabase.query(ContactProviderContract.EmailAddressEntry.TABLE_NAME, strArr, str, strArr2, null, null, str2);
                break;
            case 8:
                query = readableDatabase.query(GeneratedOutlineSupport1.outline93(GeneratedOutlineSupport1.outline116(ContactProviderContract.COMMS_IDS_TO_CONTACTS_JOIN_TABLE, " LEFT OUTER JOIN ", ContactProviderContract.EmailAddressEntry.TABLE_NAME, " ON ", ContactProviderContract.ContactDatabaseEntry.TABLE_NAME_PREFIX), "serverContactId", " = ", ContactProviderContract.EmailAddressEntry.TABLE_NAME_PREFIX, "serverContactId"), strArr, str, strArr2, null, null, str2);
                break;
            case 9:
                query = readableDatabase.query(ContactProviderContract.AddressEntry.TABLE_NAME, strArr, str, strArr2, null, null, str2);
                break;
            case 10:
                query = readableDatabase.query(ContactProviderContract.MergedContactIdentifier.TABLE_NAME, strArr, str, strArr2, null, null, str2);
                break;
            case 11:
                StringBuilder outline2 = GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport1.outline116(ContactProviderContract.ContactDatabaseEntry.TABLE_NAME, " LEFT OUTER JOIN ", ContactProviderContract.MergedContactIdentifier.TABLE_NAME, " ON ", ContactProviderContract.ContactDatabaseEntry.TABLE_NAME_PREFIX), "serverContactId", " = ", ContactProviderContract.MergedContactIdentifier.TABLE_NAME_PREFIX, "identifier"), " LEFT OUTER JOIN ", ContactProviderContract.PhoneNumberEntry.TABLE_NAME, " ON ", ContactProviderContract.MergedContactIdentifier.TABLE_NAME_PREFIX), "identifier", " = ", ContactProviderContract.PhoneNumberEntry.TABLE_NAME_PREFIX, "serverContactId"), " OR ", ContactProviderContract.ContactDatabaseEntry.TABLE_NAME_PREFIX, "serverContactId", " = ");
                outline2.append(ContactProviderContract.PhoneNumberEntry.TABLE_NAME_PREFIX);
                outline2.append("serverContactId");
                query = readableDatabase.query(outline2.toString(), strArr, str, strArr2, null, null, str2);
                break;
        }
        query.setNotificationUri(getContext().getContentResolver(), uri);
        return query;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int update;
        if (str == null && strArr == null) {
            return update(uri, contentValues);
        }
        SQLiteDatabase sQLiteDatabase = this.mApplyBatchDb;
        if (sQLiteDatabase == null) {
            sQLiteDatabase = this.mDbHelper.getWritableDatabase();
        }
        int match = this.uriMatcher.match(uri);
        if (match == 0) {
            update = sQLiteDatabase.update(ContactProviderContract.ContactDatabaseEntry.TABLE_NAME, contentValues, str, strArr);
        } else if (match == 1) {
            update = sQLiteDatabase.update(ContactProviderContract.PhoneNumberEntry.TABLE_NAME, contentValues, str, strArr);
        } else if (match == 7) {
            update = sQLiteDatabase.update(ContactProviderContract.EmailAddressEntry.TABLE_NAME, contentValues, str, strArr);
        } else if (match == 9) {
            update = sQLiteDatabase.update(ContactProviderContract.AddressEntry.TABLE_NAME, contentValues, str, strArr);
        } else if (match != 10) {
            return -1;
        } else {
            update = sQLiteDatabase.update(ContactProviderContract.MergedContactIdentifier.TABLE_NAME, contentValues, str, strArr);
        }
        sendNotification(uri);
        getContext().getContentResolver().notifyChange(MessagingProviderContract.Conversations.getContentUri(getContext()), null);
        return update;
    }

    private int update(Uri uri, ContentValues contentValues) {
        List<String> mo7740get = RESULTCODE_TO_SELECTION_ARGS.mo7740get(Integer.valueOf(this.uriMatcher.match(uri)));
        if (mo7740get == null) {
            return -1;
        }
        String str = Joiner.on(" = ? and ").join(mo7740get) + " = ?";
        String[] strArr = new String[mo7740get.size()];
        for (int i = 0; i < mo7740get.size(); i++) {
            strArr[i] = contentValues.getAsString(mo7740get.get(i));
        }
        return update(uri, contentValues, str, strArr);
    }
}
