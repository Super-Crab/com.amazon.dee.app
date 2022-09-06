package com.amazon.deecomms.contacts.util;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.loader.content.CursorLoader;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.database.DatabaseUtils;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.LanguageUtil;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.ContactAddress;
import com.amazon.deecomms.contacts.model.ContactEmailAddress;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.ContactUploadInfo;
import com.amazon.deecomms.contacts.model.DropInState;
import com.amazon.deecomms.contacts.model.EmailAddressType;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.contacts.model.IdentityRawData;
import com.amazon.deecomms.contacts.model.PhoneNumberType;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes12.dex */
public final class ContactsProviderUtils {
    private static final String COMMA = ",";
    private static final int DELETE_CONTACTS_BATCH_SIZE = 500;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactsProviderUtils.class);
    private static final String WHERE_BY_CONTACT_ID = "serverContactId = ?";
    private static String sContactsSortOrder;

    /* loaded from: classes12.dex */
    public enum ContentProviderOperationType {
        INSERT,
        UPDATE
    }

    private ContactsProviderUtils() {
    }

    public static void addUpdateBlockStatusOperation(@NonNull String str, boolean z, @NonNull ArrayList<ContentProviderOperation> arrayList) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED, Boolean.valueOf(z));
        arrayList.add(ContentProviderOperation.newUpdate(ContactProviderContract.getContactsUri(CommsDaggerWrapper.getComponent().getContext())).withSelection(WHERE_BY_CONTACT_ID, new String[]{str}).withValues(contentValues).build());
    }

    public static boolean canIDropInOnHomeGroup(String str) {
        Context context = CommsDaggerWrapper.getComponent().getContext();
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            Cursor mo3665loadInBackground = new CursorLoader(context, ContactProviderContract.getContactJoinPhoneNumberUri(context), new String[]{ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM}, "commsId = ?", new String[]{str}, null).mo3665loadInBackground();
            if (mo3665loadInBackground != null) {
                try {
                    if (mo3665loadInBackground.moveToFirst() && mo3665loadInBackground.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM) != -1) {
                        if (mo3665loadInBackground.getInt(mo3665loadInBackground.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM)) == 1) {
                            z = true;
                        }
                        mo3665loadInBackground.close();
                        return z;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            mo3665loadInBackground.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            }
            if (mo3665loadInBackground != null) {
                mo3665loadInBackground.close();
            }
        }
        return false;
    }

    public static boolean deleteAllContacts(Context context) {
        LOG.d("deleteAllContacts");
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getContactsUri(context)).build());
        arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getPhoneNumberUri(context)).build());
        arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getEmailAddressUri(context)).build());
        arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getContactAddressUri(context)).build());
        arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getMergedContactIdsUri(context)).build());
        try {
            context.getContentResolver().applyBatch(ContactProviderContract.getAuthorityId(context), arrayList);
            LOG.d("deleteAllContacts: succeeded");
            return true;
        } catch (OperationApplicationException | RemoteException e) {
            LOG.e("Failed while deleting all contacts", e);
            return false;
        }
    }

    public static boolean deleteAndInsertContacts(@NonNull Context context, @NonNull List<? extends Contact> list) {
        String commsId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("deleteAndInsertContacts", false);
        String homeGroupId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("deleteAndInsertContacts", false);
        if (!Strings.isNullOrEmpty(commsId) && !Strings.isNullOrEmpty(homeGroupId)) {
            LOG.i(String.format("deleteAndInsertContacts: %d contacts", Integer.valueOf(list.size())));
            ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
            Set<String> fetchLocalContactsCommsIds = fetchLocalContactsCommsIds(context);
            Set<String> fetchContactIdsForLogInUser = fetchContactIdsForLogInUser(context, commsId, homeGroupId);
            HashSet hashSet = new HashSet();
            int intValue = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.CONTACTS_PHONE_NUMBER_DB_MAX_LENGTH).intValue();
            for (Contact contact : list) {
                if (contact != null && !TextUtils.isEmpty(contact.getId())) {
                    hashSet.addAll(contact.getCommsIds());
                    fetchLocalContactsCommsIds.removeAll(contact.getCommsIds());
                    if (fetchContactIdsForLogInUser.remove(contact.getId())) {
                        insertOrUpdateContactInternal(arrayList, contact, ContentProviderOperationType.UPDATE, intValue, context);
                    } else {
                        insertOrUpdateContactInternal(arrayList, contact, ContentProviderOperationType.INSERT, intValue, context);
                    }
                } else {
                    LOG.e("Inappropriate contact object. Ignoring the insert");
                }
            }
            removeUngettableCommsIDIfObtained((String[]) hashSet.toArray(new String[hashSet.size()]), arrayList, context);
            deleteStaleContactsFromLocalDatabase(arrayList, fetchContactIdsForLogInUser, context);
            markCommsIdUngettable(context, (String[]) fetchLocalContactsCommsIds.toArray(new String[fetchLocalContactsCommsIds.size()]));
            updateParentHomeGroupInfoInTable(arrayList, list, context);
            try {
                context.getContentResolver().applyBatch(ContactProviderContract.getAuthorityId(context), arrayList);
                LOG.d("deleteAndInsertContacts: succeeded");
                return true;
            } catch (OperationApplicationException | RemoteException e) {
                LOG.e("Failed while deleting & inserting all contacts", e);
                return false;
            }
        }
        LOG.d("User is deprovisioned before deleteAndInsertContacts, exiting...");
        return false;
    }

    public static boolean deleteContacts(@NonNull Context context, @NonNull Set<String> set) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        deleteContactsInternal(arrayList, set.iterator(), context);
        try {
            context.getContentResolver().applyBatch(ContactProviderContract.getAuthorityId(context), arrayList);
            LOG.d("deleteContacts: succeeded");
            return true;
        } catch (OperationApplicationException | RemoteException e) {
            LOG.e("Failed while deleting contacts", e);
            return false;
        }
    }

    @VisibleForTesting
    protected static void deleteContactsInternal(ArrayList<ContentProviderOperation> arrayList, Iterator<String> it2, Context context) {
        StringBuilder sb = new StringBuilder(" IN (");
        ArrayList arrayList2 = new ArrayList();
        StringBuilder sb2 = sb;
        int i = 1;
        while (it2.hasNext()) {
            sb2.append("?,");
            arrayList2.add(it2.next());
            if (i % 500 == 0 || !it2.hasNext()) {
                String sb3 = sb2.replace(sb2.length() - 1, sb2.length(), ")").toString();
                String[] strArr = (String[]) arrayList2.toArray(new String[0]);
                arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getContactsUri(context)).withSelection(GeneratedOutlineSupport1.outline72("serverContactId", sb3), strArr).build());
                arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getPhoneNumberUri(context)).withSelection(GeneratedOutlineSupport1.outline72("serverContactId", sb3), strArr).build());
                arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getEmailAddressUri(context)).withSelection(GeneratedOutlineSupport1.outline72("serverContactId", sb3), strArr).build());
                arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getContactAddressUri(context)).withSelection(GeneratedOutlineSupport1.outline72("serverContactId", sb3), strArr).build());
                arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getMergedContactIdsUri(context)).withSelection(GeneratedOutlineSupport1.outline72("serverContactId", sb3), strArr).build());
                sb2 = new StringBuilder(" IN (");
                arrayList2 = new ArrayList();
            }
            i++;
        }
        int i2 = i - 1;
        if (i2 > 0) {
            LOG.d(String.format("deleteContactsInternal: %s contacts deleted", Integer.valueOf(i2)));
        }
    }

    @VisibleForTesting
    protected static void deleteStaleContactsFromLocalDatabase(@NonNull ArrayList<ContentProviderOperation> arrayList, @NonNull Set<String> set, @NonNull Context context) {
        if (CommsDaggerWrapper.getComponent().getCapabilitiesManager().isBatchContactsDownloadEnabled()) {
            LOG.i("For paginated contacts download,we do not delete stale contacts from local DB for a batch.");
        } else {
            deleteContactsInternal(arrayList, set.iterator(), context);
        }
    }

    public static Cursor fetchAllHomeGroupIDs(Context context) {
        return context.getContentResolver().query(ContactProviderContract.getPhoneNumberUri(context), new String[]{"commsId"}, "isHomeGroup = ?", new String[]{"1"}, null);
    }

    public static ContactEntry fetchContactEntryForCommId(@NonNull Context context, @NonNull String str) {
        Cursor query = context.getContentResolver().query(ContactProviderContract.getContactJoinPhoneNumberUri(context), getColumnsForContactEntry(), "commsId = ?", new String[]{str}, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    ContactEntry contactEntryFromCursor = getContactEntryFromCursor(query);
                    query.close();
                    return contactEntryFromCursor;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
            return null;
        }
        return null;
    }

    private static Set<String> fetchContactIdsForChild(@NonNull Context context, @NonNull String str) {
        return fetchContactsIdsFromDB(context, "ownerCommsId IN (?)", new String[]{str});
    }

    private static Set<String> fetchContactIdsForLogInUser(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        return fetchContactsIdsFromDB(context, "ownerCommsId IN (?, ?)", new String[]{str, str2});
    }

    private static Set<String> fetchContactsIdsFromDB(@NonNull Context context, String str, String[] strArr) {
        HashSet hashSet = new HashSet();
        Cursor query = context.getContentResolver().query(ContactProviderContract.getContactsUri(context), new String[]{"serverContactId"}, str, strArr, null);
        if (query != null) {
            try {
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    hashSet.add(query.getString(0));
                    query.moveToNext();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
        }
        return hashSet;
    }

    public static Cursor fetchHomeGroupMembers(Context context, String str) {
        return context.getContentResolver().query(ContactProviderContract.getPhoneNumberUri(context), new String[]{"commsId"}, "parentHomeGroup = ? GROUP BY commsId", new String[]{str}, null);
    }

    public static IdentityRawData fetchIdentityRawDataForCommsId(Context context, String str) {
        Cursor query = context.getContentResolver().query(ContactProviderContract.getPhoneNumberUri(context), new String[]{ContactProviderContract.PhoneNumberEntry.COLUMN_AOR}, "commsId = ?", new String[]{str}, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(query.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR));
                    IdentityRawData identityRawData = new IdentityRawData();
                    identityRawData.setAor(string);
                    identityRawData.setUser(str);
                    query.close();
                    return identityRawData;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
            return null;
        }
        return null;
    }

    @NonNull
    private static Set<String> fetchLocalContactsCommsIds(@NonNull Context context) {
        HashSet hashSet = new HashSet();
        Cursor query = context.getContentResolver().query(ContactProviderContract.getPhoneNumberUri(context), new String[]{"commsId"}, DatabaseUtils.generateNonNullAndEmptySelection("commsId"), null, null);
        try {
            if (query != null) {
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    String string = query.getString(0);
                    if (!TextUtils.isEmpty(string)) {
                        hashSet.add(string);
                    }
                    query.moveToNext();
                }
            } else {
                LOG.e("Could not open cursor to get CommsIds for contact");
            }
            if (query != null) {
                query.close();
            }
            return hashSet;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static boolean findIsHomeGroupFromCursor(Cursor cursor) {
        return cursor != null && cursor.moveToFirst() && cursor.getInt(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_IS_HOME_GROUP)) == 1;
    }

    @Nullable
    public static String getAorFromCursor(@Nullable Cursor cursor) {
        String str = null;
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR) != -1) {
                str = cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR));
            }
            cursor.moveToNext();
        }
        return str;
    }

    @NonNull
    public static List<String> getCoBoEnabledContactIds(@NonNull Context context) {
        HashSet hashSet = new HashSet();
        Cursor query = context.getContentResolver().query(ContactProviderContract.getPhoneNumberUri(context), new String[]{"serverContactId"}, "isCoboCallable = ?", new String[]{"1"}, null);
        try {
            if (query == null) {
                LOG.e("failed to getCoBoEnabled contactsIds from DB");
                ArrayList arrayList = new ArrayList(hashSet);
                if (query != null) {
                    query.close();
                }
                return arrayList;
            }
            int columnIndex = query.getColumnIndex("serverContactId");
            while (columnIndex != -1 && query.moveToNext()) {
                String string = query.getString(columnIndex);
                if (!Strings.isNullOrEmpty(string)) {
                    hashSet.add(string);
                }
            }
            ArrayList arrayList2 = new ArrayList(hashSet);
            query.close();
            return arrayList2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static String[] getColumnsForContactEntry() {
        return new String[]{ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_WITH_TABLE_NAME_SERVER_CONTACT_ID, "firstName", "lastName", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED, "company", "ownerCommsId", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_BULK_IMPORT, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_NAME_EMPTY, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_EVER_REFRESHED, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP};
    }

    public static String getCommsIdFromServerContactId(@NonNull Context context, String str) {
        Cursor query = context.getContentResolver().query(ContactProviderContract.getPhoneNumberUri(context), new String[]{"commsId"}, "PhoneNumbers.serverContactId = ?", new String[]{str}, null);
        String str2 = null;
        try {
            if (query == null) {
                LOG.e("ContactsProviderUtils::getCommsIdFromServerContactId: Query execution failed!");
                if (query != null) {
                    query.close();
                }
                return null;
            }
            query.moveToFirst();
            while (true) {
                if (query.isAfterLast()) {
                    break;
                }
                String string = query.getString(query.getColumnIndex("commsId"));
                if (!Strings.isNullOrEmpty(string)) {
                    str2 = string;
                    break;
                }
                query.moveToNext();
            }
            query.close();
            return str2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static List<String> getCommsIdsFromCursor(Cursor cursor) {
        cursor.moveToFirst();
        ArrayList arrayList = new ArrayList();
        while (!cursor.isAfterLast()) {
            String string = cursor.getString(cursor.getColumnIndex("commsId"));
            if (!Utils.isNullOrEmpty(string)) {
                arrayList.add(string);
            }
            cursor.moveToNext();
        }
        return arrayList;
    }

    public static List<ContactEntry> getContactEntriesFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(getContactEntryFromCursor(cursor));
        }
        return arrayList;
    }

    public static ContactEntry getContactEntryFromCursor(Cursor cursor) {
        if (cursor == null || cursor.isAfterLast()) {
            return null;
        }
        ContactEntry contactEntry = new ContactEntry();
        ContactName contactName = new ContactName();
        FullContactName fullContactName = new FullContactName();
        if (cursor.getColumnIndex("firstName") != -1) {
            contactName.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
        }
        if (cursor.getColumnIndex("lastName") != -1) {
            contactName.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME) != -1) {
            contactName.setPhoneticFirstName(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME)));
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME) != -1) {
            contactName.setPhoneticLastName(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME)));
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME) != -1) {
            contactName.setPronunciationFirstName(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME)));
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME) != -1) {
            contactName.setPronunciationLastName(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME)));
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME) != -1) {
            contactName.setNickName(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME)));
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME) != -1) {
            contactName.setSourceNickName(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME)));
        }
        boolean z = false;
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_MERGED) != -1) {
            contactEntry.setIsMerged(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_MERGED)) == 1);
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_MERGE) != -1) {
            contactEntry.setShouldMerge(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_MERGE)) == 1);
        }
        contactEntry.setContactName(contactName);
        if (cursor.getColumnIndex("company") != -1) {
            contactEntry.setCompany(cursor.getString(cursor.getColumnIndex("company")));
        }
        fullContactName.setContactName(contactName);
        fullContactName.setCompany(contactEntry.getCompany());
        contactEntry.setFullContactName(fullContactName);
        if (cursor.getColumnIndex("serverContactId") != -1) {
            contactEntry.setId(cursor.getString(cursor.getColumnIndex("serverContactId")));
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED) != -1) {
            contactEntry.setAlexaEnabled(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED))) != 0);
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID) != -1) {
            contactEntry.setDeviceContactId(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID)));
        }
        if (cursor.getColumnIndex("ownerCommsId") != -1) {
            contactEntry.setOwnerCommsId(cursor.getString(cursor.getColumnIndex("ownerCommsId")));
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID) != -1) {
            contactEntry.setSourceDeviceId(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID)));
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_NAME_EMPTY) != -1) {
            contactEntry.setIsNameEmpty(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_NAME_EMPTY))) == 1);
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME) != -1) {
            contactEntry.setDropInState(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME)) == 1 ? DropInState.ON : DropInState.OFF);
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED) != -1) {
            contactEntry.setBlocked(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED))) == 1);
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM) != -1) {
            contactEntry.setCanIDropInOnHim(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM)) == 1);
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_BULK_IMPORT) != -1) {
            contactEntry.setBulkImport(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_BULK_IMPORT)) == 1);
        }
        if (cursor.getColumnIndex("ownerCommsId") != -1) {
            contactEntry.setOwnerCommsId(cursor.getString(cursor.getColumnIndex("ownerCommsId")));
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_EVER_REFRESHED) != -1) {
            contactEntry.setEverRefreshed(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_EVER_REFRESHED)) == 1);
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD) != -1) {
            if (cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD)) == 1) {
                z = true;
            }
            contactEntry.setChild(z);
        }
        if (cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP) != -1) {
            contactEntry.setRelationship(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP)));
        }
        return contactEntry;
    }

    public static Set<String> getContactIdsForCurrentUser(@NonNull Context context) {
        String commsId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("getContactIdsForCurrentUser", false);
        String homeGroupId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("getContactIdsForCurrentUser", false);
        if (!Strings.isNullOrEmpty(commsId) && !Strings.isNullOrEmpty(homeGroupId)) {
            return fetchContactIdsForLogInUser(context, commsId, homeGroupId);
        }
        LOG.d("User is deprovisioned, cannot fetch Contact IDs, exiting...");
        return new HashSet();
    }

    public static String getContactsSortOrder(Context context) {
        String str = sContactsSortOrder;
        if (str != null) {
            return str;
        }
        String string = context.getString(R.string.full_name_sort_db_query_format);
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("COALESCE(NULLIF(");
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("printf(\"", string, "\",", "firstName", ",");
        outline116.append("lastName");
        outline116.append(")");
        outline1.append(outline116.toString());
        outline1.append(",");
        StringBuilder outline2 = GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(outline1, "''", ")", ",", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME), ",", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, ",", "company");
        outline2.append(")");
        String sb = outline2.toString();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder outline22 = GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(sb2, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_NAME_EMPTY, ",", " CASE WHEN SUBSTR(", sb), ",", "1, 1) GLOB '[A-Z]*' THEN 0 ", " WHEN SUBSTR(", sb), ",", " 1, 1) GLOB '[a-z]*' THEN 0 ", " WHEN SUBSTR(", sb), ",", " 1, 1) GLOB '[0-9]*' THEN 1 ", " ELSE 2 END", ",");
        outline22.append(sb);
        outline22.append(" COLLATE NOCASE ASC");
        sContactsSortOrder = sb2.toString();
        return sContactsSortOrder;
    }

    public static String getContactsSortOrderByJP(Context context) {
        String str = sContactsSortOrder;
        if (str != null) {
            return str;
        }
        StringBuilder outline2 = GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport1.outline116("COALESCE(NULLIF(", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME_SORT_FORMAT, ",", "''", ")"), ",", "NULLIF(", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME_SORT_FORMAT, ","), "''", ")", ",", "NULLIF("), "lastName", ",", "''", ")"), ",", "NULLIF(", "firstName", ","), "''", ")", ",", "NULLIF("), ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, ",", "''", ")"), ",", "NULLIF(", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, ","), "''", ")", ",", "NULLIF("), "company", ",", "''", ")");
        outline2.append(")");
        String sb = outline2.toString();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder outline22 = GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(sb2, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_NAME_EMPTY, ",", " CASE WHEN SUBSTR(", sb), ",", "1, 1) GLOB '[ぁ-ん]*' THEN 0 ", " WHEN SUBSTR(", sb), ",", "1, 1) GLOB '[A-Z]*' THEN 1 ", " WHEN SUBSTR(", sb), ",", " 1, 1) GLOB '[a-z]*' THEN 1 ", " WHEN SUBSTR(", sb), ",", " 1, 1) GLOB '[0-9]*' THEN 2 ", " ELSE 3 END", ",");
        outline22.append(sb);
        outline22.append(" COLLATE NOCASE ASC");
        sContactsSortOrder = sb2.toString();
        return sContactsSortOrder;
    }

    public static List<ContactEmailAddress> getEmailAddressesFromCursor(Cursor cursor) {
        cursor.moveToFirst();
        ArrayList arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (!cursor.isAfterLast()) {
            String string = cursor.getString(cursor.getColumnIndex("email"));
            if (!Utils.isNullOrEmpty(string) && !linkedHashSet.contains(string)) {
                ContactEmailAddress contactEmailAddress = new ContactEmailAddress();
                contactEmailAddress.setEmailAddress(string);
                contactEmailAddress.setType(EmailAddressType.valueOf(cursor.getString(cursor.getColumnIndex(ContactProviderContract.EmailAddressEntry.COLUMN_EMAIL_ADDRESS_TYPE))));
                contactEmailAddress.setRawType(cursor.getString(cursor.getColumnIndex(ContactProviderContract.EmailAddressEntry.COLUMN_EMAIL_ADDRESS_RAW_TYPE)));
                arrayList.add(contactEmailAddress);
                linkedHashSet.add(string);
                cursor.moveToNext();
            } else {
                cursor.moveToNext();
            }
        }
        return arrayList;
    }

    public static String getHomeGroupIdFromCommsId(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LOG.e("getHomeGroupIdFromCommsId Comms ID is empty");
            return null;
        }
        String homeGroupIdFromCommsIdFromDb = getHomeGroupIdFromCommsIdFromDb(context, str);
        if (!TextUtils.isEmpty(homeGroupIdFromCommsIdFromDb)) {
            return homeGroupIdFromCommsIdFromDb;
        }
        if (isCommsIdUngettable(context, str)) {
            LOG.d("CommsId is ungettable. Not doing the server call.");
            return null;
        }
        String commsId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("getHomeGroupIdFromCommsId", false);
        String homeGroupId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("getHomeGroupIdFromCommsId", false);
        if (!TextUtils.isEmpty(commsId) && !TextUtils.isEmpty(homeGroupId)) {
            try {
                Contact contact = (Contact) new ACMSClient(MetricKeys.OP_GET_OR_CREATE_CONTACT).request(MessageFormat.format(AppUrl.GET_OR_CREATE_URL, commsId, str)).addMetricMetadata("source", "ContactsProviderUtils.getHomeGroupIdFromCommsId").addQueryParameter(Constants.ACMS_CONTACTS_QUERY_PREFERENCE_LEVEL, Constants.ACMS_CONTACT_PREFERENCE_LEVEL_HG).addQueryParameter(Constants.HOMEGROUP_ID_PREF, homeGroupId).authenticatedAsCurrentCommsUser().get().mo3640execute().convert(Contact.class);
                if (contact != null && contact.getHomeGroupIdentity() != null) {
                    return contact.getHomeGroupIdentity().getId();
                }
            } catch (ServiceException e) {
                LOG.e("Fetch Home Group Id failed", e);
                if (e.getHttpResponseCode() != null && e.getHttpResponseCode().intValue() == 400) {
                    LOG.d("Marking comms id ungettable");
                    markCommsIdUngettable(context, str);
                }
            }
            LOG.e("Home Group ID returned from server is null");
            return null;
        }
        LOG.e("Identity not present");
        return null;
    }

    public static String getHomeGroupIdFromCommsIdFromDb(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LOG.e("getHomeGroupIdFromCommsIdFromDb Comms ID is empty");
            return null;
        }
        Cursor query = context.getContentResolver().query(ContactProviderContract.getPhoneNumberUri(context), null, "commsId = ?", new String[]{str}, null);
        if (query != null) {
            try {
                int columnIndex = query.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP);
                query.moveToFirst();
                while (columnIndex != -1 && !query.isAfterLast()) {
                    if (query.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_IS_HOME_GROUP) != -1 && query.getInt(query.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_IS_HOME_GROUP)) == 1) {
                        query.close();
                        return str;
                    } else if (!Strings.isNullOrEmpty(query.getString(columnIndex))) {
                        String string = query.getString(columnIndex);
                        query.close();
                        return string;
                    } else {
                        query.moveToNext();
                    }
                }
            } finally {
            }
        }
        if (query != null) {
            query.close();
        }
        return null;
    }

    @Nullable
    public static String getParentHomeGroupFromCursor(@Nullable Cursor cursor) {
        int columnIndex;
        if (cursor == null || (columnIndex = cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP)) == -1) {
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String string = cursor.getString(columnIndex);
            if (!Strings.isNullOrEmpty(string)) {
                return string;
            }
            cursor.moveToNext();
        }
        return null;
    }

    @NonNull
    public static List<ContactPhoneNumber> getPhoneNumbersByContactId(@NonNull Context context, @Nullable String str) {
        if (Strings.isNullOrEmpty(str)) {
            LOG.e("getPhoneNumbersByContactId: serverContactId is empty");
            return null;
        }
        Cursor query = context.getContentResolver().query(ContactProviderContract.getContactJoinMergedJoinPhoneNumberUri(context), null, GeneratedOutlineSupport1.outline77(ContactProviderContract.PhoneNumberEntry.COLUMN_NAME_WITH_TABLE_NAME_SERVER_CONTACT_ID, " = ?", " OR ", "MergedContactIds.serverContactId", " = ?"), new String[]{str, str}, null);
        if (query != null) {
            try {
                List<ContactPhoneNumber> phoneNumbersFromCursor = getPhoneNumbersFromCursor(query);
                query.close();
                return phoneNumbersFromCursor;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
        }
        return new ArrayList();
    }

    public static List<ContactPhoneNumber> getPhoneNumbersFromCursor(Cursor cursor) {
        cursor.moveToFirst();
        ArrayList arrayList = new ArrayList();
        while (!cursor.isAfterLast()) {
            String string = cursor.getString(cursor.getColumnIndex("number"));
            if (Utils.isNullOrEmpty(string)) {
                cursor.moveToNext();
            } else {
                ContactPhoneNumber contactPhoneNumber = new ContactPhoneNumber();
                contactPhoneNumber.setPhoneNumber(string);
                contactPhoneNumber.setType(PhoneNumberType.valueOf(cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_NUMBER_TYPE))));
                contactPhoneNumber.setRawType(cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_NUMBER_RAW_TYPE)));
                contactPhoneNumber.setObfuscatedPhoneNumber(cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_HASHED_PHONE_NUMBER)));
                contactPhoneNumber.setCoboEnabled(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_IS_COBO_CALLABLE)) != 0);
                contactPhoneNumber.setContactSource(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE)));
                arrayList.add(contactPhoneNumber);
                cursor.moveToNext();
            }
        }
        return arrayList;
    }

    @NonNull
    public static List<String> getReferenceContactIdsFromChild(@NonNull String str, @NonNull Context context) {
        ArrayList arrayList = new ArrayList();
        Cursor query = context.getContentResolver().query(ContactProviderContract.getContactsUri(context), new String[]{ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_CONTACT_ID}, "ownerCommsId = ?", new String[]{str}, null);
        try {
            if (query == null) {
                LOG.e("failed whie quering referenceContactId from database!");
                if (query != null) {
                    query.close();
                }
                return arrayList;
            }
            int columnIndex = query.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_CONTACT_ID);
            while (columnIndex != -1 && query.moveToNext()) {
                String string = query.getString(columnIndex);
                if (!Strings.isNullOrEmpty(string)) {
                    arrayList.add(string);
                }
            }
            query.close();
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static void insertAddressValues(@NonNull ArrayList<ContentProviderOperation> arrayList, @NonNull Contact contact, @NonNull Context context) {
        List<ContactAddress> addresses = contact.getAddresses();
        if (addresses != null && addresses.size() != 0) {
            LOG.i(String.format("insertAddressValues: %s contactAddresses", Integer.valueOf(addresses.size())));
            for (ContactAddress contactAddress : addresses) {
                if (TextUtils.isEmpty(contactAddress.getType())) {
                    LOG.w("contactAddress for contact is empty");
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("serverContactId", contact.getId());
                    contentValues.put("value", contactAddress.getValue());
                    contentValues.put(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE, contactAddress.getRawType());
                    contentValues.put("addressType", contactAddress.getType());
                    arrayList.add(ContentProviderOperation.newInsert(ContactProviderContract.getContactAddressUri(context)).withValues(contentValues).build());
                }
            }
            return;
        }
        LOG.w("insertAddressValues: contactAddresses is null/empty");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void insertCommsIdValues(java.util.ArrayList<android.content.ContentProviderOperation> r8, com.amazon.deecomms.contacts.model.Contact r9, android.content.Context r10) {
        /*
            boolean r0 = r9.isHomeGroup()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            if (r0 == 0) goto L1d
            com.amazon.deecomms.contacts.model.IdentityRawData r0 = r9.getHomeGroupIdentity()
            if (r0 == 0) goto L15
            r1.add(r0)
            goto L30
        L15:
            com.amazon.comms.log.CommsLogger r0 = com.amazon.deecomms.contacts.util.ContactsProviderUtils.LOG
            java.lang.String r2 = "No home group Id to add. Ignoring table insert."
            r0.e(r2)
            goto L30
        L1d:
            java.util.List r0 = r9.getCommsIdentities()
            if (r0 == 0) goto L29
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L31
        L29:
            com.amazon.comms.log.CommsLogger r0 = com.amazon.deecomms.contacts.util.ContactsProviderUtils.LOG
            java.lang.String r2 = "No commsIds to add. Ignoring table insert."
            r0.w(r2)
        L30:
            r0 = r1
        L31:
            com.amazon.comms.log.CommsLogger r1 = com.amazon.deecomms.contacts.util.ContactsProviderUtils.LOG
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            int r3 = r0.size()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r4 = 0
            r2[r4] = r3
            java.lang.String r3 = "insertCommsIdValues: %s commsIds"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            r1.d(r2)
            java.util.Iterator r0 = r0.iterator()
        L4e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lc7
            java.lang.Object r1 = r0.next()
            com.amazon.deecomms.contacts.model.IdentityRawData r1 = (com.amazon.deecomms.contacts.model.IdentityRawData) r1
            android.content.ContentValues r2 = new android.content.ContentValues
            r2.<init>()
            java.lang.String r3 = r1.getId()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            java.lang.String r4 = ""
            java.lang.String r5 = "commsId"
            if (r3 == 0) goto L87
            com.amazon.comms.log.CommsLogger r3 = com.amazon.deecomms.contacts.util.ContactsProviderUtils.LOG
            java.lang.String r6 = "CommsId entry missing id for contact:"
            java.lang.StringBuilder r6 = com.android.tools.r8.GeneratedOutlineSupport.outline1(r6)
            java.lang.String r7 = r9.getId()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.e(r6)
            r2.put(r5, r4)
            goto L8e
        L87:
            java.lang.String r3 = r1.getId()
            r2.put(r5, r3)
        L8e:
            java.lang.String r3 = r9.getId()
            java.lang.String r5 = "serverContactId"
            r2.put(r5, r3)
            java.lang.String r1 = r1.getAor()
            java.lang.String r3 = "aor"
            r2.put(r3, r1)
            boolean r1 = r9.isHomeGroup()
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            java.lang.String r3 = "isHomeGroup"
            r2.put(r3, r1)
            java.lang.String r1 = "number"
            r2.put(r1, r4)
            android.net.Uri r1 = com.amazon.deecomms.contacts.database.provider.ContactProviderContract.getPhoneNumberUri(r10)
            android.content.ContentProviderOperation$Builder r1 = android.content.ContentProviderOperation.newInsert(r1)
            android.content.ContentProviderOperation$Builder r1 = r1.withValues(r2)
            android.content.ContentProviderOperation r1 = r1.build()
            r8.add(r1)
            goto L4e
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.contacts.util.ContactsProviderUtils.insertCommsIdValues(java.util.ArrayList, com.amazon.deecomms.contacts.model.Contact, android.content.Context):void");
    }

    private static void insertEmailAddressValues(@NonNull ArrayList<ContentProviderOperation> arrayList, @NonNull Contact contact, @NonNull Context context) {
        List<ContactEmailAddress> emails = contact.getEmails();
        if (emails != null && emails.size() != 0) {
            for (ContactEmailAddress contactEmailAddress : emails) {
                if (TextUtils.isEmpty(contactEmailAddress.getEmailAddress())) {
                    CommsLogger commsLogger = LOG;
                    StringBuilder outline1 = GeneratedOutlineSupport.outline1("Email Address for contact:");
                    outline1.append(contact.getId());
                    commsLogger.e(outline1.toString());
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(ContactProviderContract.EmailAddressEntry.COLUMN_EMAIL_ADDRESS_TYPE, contactEmailAddress.getType().name());
                    contentValues.put(ContactProviderContract.EmailAddressEntry.COLUMN_EMAIL_ADDRESS_RAW_TYPE, contactEmailAddress.getRawType());
                    contentValues.put("email", contactEmailAddress.getEmailAddress());
                    contentValues.put("serverContactId", contact.getId());
                    arrayList.add(ContentProviderOperation.newInsert(ContactProviderContract.getEmailAddressUri(context)).withValues(contentValues).build());
                }
            }
            return;
        }
        LOG.d("insertEmailAddressesValues: emailAddressList is null/empty");
    }

    private static void insertMergedContactIds(@NonNull ArrayList<ContentProviderOperation> arrayList, @NonNull Contact contact, @NonNull Context context) {
        List<String> mergedContactIdentifiers = contact.getMergedContactIdentifiers();
        if (mergedContactIdentifiers != null && mergedContactIdentifiers.size() != 0) {
            LOG.i(String.format("insertMergedContactIds: %s mergedContactIds", Integer.valueOf(mergedContactIdentifiers.size())));
            for (String str : mergedContactIdentifiers) {
                if (str == null) {
                    LOG.w("mergedContactId for contact is null");
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("serverContactId", contact.getId());
                    contentValues.put("identifier", str);
                    arrayList.add(ContentProviderOperation.newInsert(ContactProviderContract.getMergedContactIdsUri(context)).withValues(contentValues).build());
                }
            }
            return;
        }
        LOG.w("insertMergedContactIds: mergedContactIds is null/empty");
    }

    public static boolean insertOrUpdateContact(Context context, Contact contact, ContentProviderOperationType contentProviderOperationType) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("insertOrUpdateContact: ");
        outline1.append(LOG.sensitive(contact.getId()));
        commsLogger.d(outline1.toString());
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        insertOrUpdateContactInternal(arrayList, contact, contentProviderOperationType, CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.CONTACTS_PHONE_NUMBER_DB_MAX_LENGTH).intValue(), context);
        try {
            context.getContentResolver().applyBatch(ContactProviderContract.getAuthorityId(context), arrayList);
            LOG.d("insertOrUpdateContact: succeeded");
            return true;
        } catch (OperationApplicationException | RemoteException e) {
            LOG.e("Failed while inserting contact", e);
            return false;
        }
    }

    private static void insertOrUpdateContactInternal(ArrayList<ContentProviderOperation> arrayList, Contact contact, ContentProviderOperationType contentProviderOperationType, int i, Context context) {
        insertOrUpdateContactValues(arrayList, contact, contentProviderOperationType, context);
        if (contentProviderOperationType == ContentProviderOperationType.UPDATE) {
            arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getPhoneNumberUri(context)).withSelection(WHERE_BY_CONTACT_ID, new String[]{contact.getId()}).build());
            arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getEmailAddressUri(context)).withSelection(WHERE_BY_CONTACT_ID, new String[]{contact.getId()}).build());
            arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getContactAddressUri(context)).withSelection(WHERE_BY_CONTACT_ID, new String[]{contact.getId()}).build());
            arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getMergedContactIdsUri(context)).withSelection(WHERE_BY_CONTACT_ID, new String[]{contact.getId()}).build());
        }
        insertPhoneNumberValues(arrayList, contact, i, context);
        insertEmailAddressValues(arrayList, contact, context);
        insertAddressValues(arrayList, contact, context);
        insertMergedContactIds(arrayList, contact, context);
        insertCommsIdValues(arrayList, contact, context);
    }

    public static boolean insertOrUpdateContactListForChild(@NonNull Context context, @NonNull List<Contact> list, @NonNull String str) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        int intValue = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.CONTACTS_PHONE_NUMBER_DB_MAX_LENGTH).intValue();
        Set<String> fetchContactIdsForChild = fetchContactIdsForChild(context, str);
        for (Contact contact : list) {
            if (fetchContactIdsForChild.remove(contact.getId())) {
                insertOrUpdateContactInternal(arrayList, contact, ContentProviderOperationType.UPDATE, intValue, context);
            } else {
                insertOrUpdateContactInternal(arrayList, contact, ContentProviderOperationType.INSERT, intValue, context);
            }
        }
        deleteContactsInternal(arrayList, fetchContactIdsForChild.iterator(), context);
        updateParentHomeGroupInfoForContactsOfChild(context, arrayList);
        try {
            context.getContentResolver().applyBatch(ContactProviderContract.getAuthorityId(context), arrayList);
            LOG.d("insertOrUpdateContatList: succeeded");
            return true;
        } catch (OperationApplicationException | RemoteException e) {
            LOG.e("Failed while insertOrUpdate contact list", e);
            return false;
        }
    }

    private static void insertOrUpdateContactValues(ArrayList<ContentProviderOperation> arrayList, Contact contact, ContentProviderOperationType contentProviderOperationType, Context context) {
        ContentValues contentValues = new ContentValues();
        String firstName = contact.getContactName().getFirstName();
        String lastName = contact.getContactName().getLastName();
        String phoneticFirstName = contact.getContactName().getPhoneticFirstName();
        String phoneticLastName = contact.getContactName().getPhoneticLastName();
        String pronunciationFirstName = contact.getContactName().getPronunciationFirstName();
        String pronunciationLastName = contact.getContactName().getPronunciationLastName();
        String nickName = contact.getContactName().getNickName();
        String sourceNickName = contact.getContactName().getSourceNickName();
        String company = contact.getCompany();
        if (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(nickName) && TextUtils.isEmpty(sourceNickName) && TextUtils.isEmpty(company) && contact.getPhoneNumbers().size() > 0) {
            firstName = contact.getPhoneNumbers().get(0).getPhoneNumber();
            contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_NAME_EMPTY, (Integer) 1);
        } else {
            contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_NAME_EMPTY, (Integer) 0);
        }
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME, phoneticFirstName);
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME, phoneticLastName);
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME, pronunciationFirstName);
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME, pronunciationLastName);
        if (!TextUtils.isEmpty(phoneticFirstName)) {
            char charAt = phoneticFirstName.charAt(0);
            if (LanguageUtil.isHiragana(charAt) || LanguageUtil.isKatakana(charAt)) {
                contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME_SORT_FORMAT, LanguageUtil.toHiragana(phoneticFirstName));
            }
        }
        if (!TextUtils.isEmpty(phoneticLastName)) {
            char charAt2 = phoneticLastName.charAt(0);
            if (LanguageUtil.isHiragana(charAt2) || LanguageUtil.isKatakana(charAt2)) {
                contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME_SORT_FORMAT, LanguageUtil.toHiragana(phoneticLastName));
            }
        }
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, nickName);
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, sourceNickName);
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED, Boolean.valueOf(contact.isAlexaEnabled()));
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_BULK_IMPORT, Boolean.valueOf(contact.isBulkImport()));
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID, contact.getDeviceContactId());
        contentValues.put("serverContactId", contact.getId());
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID, contact.getSourceDeviceId());
        contentValues.put("ownerCommsId", contact.getOwnerCommsId());
        contentValues.put("company", contact.getCompany());
        contentValues.put("ownerCommsId", contact.getOwnerCommsId());
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME, Integer.valueOf(contact.canDropInOnMe()));
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM, Integer.valueOf(contact.canIDropInOnHim()));
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_BULK_IMPORT, Boolean.valueOf(contact.isBulkImport()));
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_DISPLAY, Boolean.valueOf(contact.shouldDisplay()));
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED, Boolean.valueOf(contact.isBlocked()));
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD, Boolean.valueOf(contact.isChild()));
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP, contact.getRelationship());
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_COMMS_ID, contact.getReferenceCommsId());
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_CONTACT_ID, contact.getReferenceContactId());
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_LINKED, Boolean.valueOf(contact.isLinked()));
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE, contact.getContactSourceType());
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_MERGED, Boolean.valueOf(contact.isMerged()));
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_MERGE, Boolean.valueOf(contact.isShouldMerge()));
        if (contentProviderOperationType == ContentProviderOperationType.INSERT) {
            arrayList.add(ContentProviderOperation.newInsert(ContactProviderContract.getContactsUri(context)).withValues(contentValues).build());
        } else {
            arrayList.add(ContentProviderOperation.newUpdate(ContactProviderContract.getContactsUri(context)).withValues(contentValues).build());
        }
    }

    private static void insertPhoneNumberValues(@NonNull ArrayList<ContentProviderOperation> arrayList, @NonNull Contact contact, int i, Context context) {
        List<ContactPhoneNumber> phoneNumbers = contact.getPhoneNumbers();
        if (phoneNumbers != null) {
            if (phoneNumbers.size() == 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.putNull(ContactProviderContract.PhoneNumberEntry.COLUMN_NUMBER_TYPE);
                contentValues.putNull(ContactProviderContract.PhoneNumberEntry.COLUMN_NUMBER_RAW_TYPE);
                contentValues.putNull("number");
                contentValues.put("serverContactId", contact.getId());
                contentValues.put("commsId", "");
                contentValues.putNull(ContactProviderContract.PhoneNumberEntry.COLUMN_HASHED_PHONE_NUMBER);
                contentValues.put(ContactProviderContract.PhoneNumberEntry.COLUMN_IS_COBO_CALLABLE, (Boolean) false);
                arrayList.add(ContentProviderOperation.newInsert(ContactProviderContract.getPhoneNumberUri(context)).withValues(contentValues).build());
                return;
            }
            for (ContactPhoneNumber contactPhoneNumber : phoneNumbers) {
                if (TextUtils.isEmpty(contactPhoneNumber.getPhoneNumber())) {
                    CommsLogger commsLogger = LOG;
                    StringBuilder outline1 = GeneratedOutlineSupport.outline1("Phone number entry missing number for contact:");
                    outline1.append(contact.getId());
                    commsLogger.e(outline1.toString());
                } else {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put(ContactProviderContract.PhoneNumberEntry.COLUMN_NUMBER_TYPE, contactPhoneNumber.getType().name());
                    contentValues2.put(ContactProviderContract.PhoneNumberEntry.COLUMN_NUMBER_RAW_TYPE, contactPhoneNumber.getRawType());
                    contentValues2.put("number", trimToMaxLength(contactPhoneNumber.getPhoneNumber(), i));
                    contentValues2.put("serverContactId", contact.getId());
                    contentValues2.put("commsId", "");
                    contentValues2.put(ContactProviderContract.PhoneNumberEntry.COLUMN_HASHED_PHONE_NUMBER, contactPhoneNumber.getObfuscatedPhoneNumber());
                    contentValues2.put(ContactProviderContract.PhoneNumberEntry.COLUMN_IS_COBO_CALLABLE, Boolean.valueOf(contactPhoneNumber.isCOBOEnabled()));
                    arrayList.add(ContentProviderOperation.newInsert(ContactProviderContract.getPhoneNumberUri(context)).withValues(contentValues2).build());
                }
            }
            return;
        }
        LOG.w("insertPhoneNumberValues: phoneNumberList is null");
    }

    public static boolean isCommsIdUngettable(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            Cursor query = context.getContentResolver().query(ContactProviderContract.getUngettableCommsidsUri(context), new String[]{"commsId"}, "commsId = ?", new String[]{str}, null);
            if (query != null) {
                try {
                    if (query.moveToNext()) {
                        query.close();
                        return true;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            query.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        }
        return false;
    }

    public static boolean isContactChild(@NonNull String str, @NonNull Context context) {
        boolean z = true;
        Cursor query = context.getContentResolver().query(ContactProviderContract.getContactsUri(context), new String[]{ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD}, WHERE_BY_CONTACT_ID, new String[]{str}, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    if (Integer.parseInt(query.getString(0)) != 1) {
                        z = false;
                    }
                    query.close();
                    return z;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
        }
        return false;
    }

    public static boolean isMemberOfMyHg(Context context, String str) {
        boolean z = false;
        Cursor fetchHomeGroupMembers = fetchHomeGroupMembers(context, CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("ContactProviderUtils.isMemberOfMyHg", false));
        if (fetchHomeGroupMembers != null) {
            while (true) {
                try {
                    if (!fetchHomeGroupMembers.moveToNext()) {
                        break;
                    }
                    String string = fetchHomeGroupMembers.getString(fetchHomeGroupMembers.getColumnIndex("commsId"));
                    if (str != null && str.equalsIgnoreCase(string)) {
                        z = true;
                        break;
                    }
                } finally {
                }
            }
        }
        if (fetchHomeGroupMembers != null) {
            fetchHomeGroupMembers.close();
        }
        return z;
    }

    public static void markCommsIdUngettable(Context context, String... strArr) {
        if (strArr != null && strArr.length != 0) {
            ContentValues[] contentValuesArr = new ContentValues[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Marking commsId as ungettable: ");
                outline1.append(LOG.sensitive(strArr[i]));
                commsLogger.d(outline1.toString());
                contentValuesArr[i] = new ContentValues();
                contentValuesArr[i].put("commsId", strArr[i]);
            }
            context.getContentResolver().bulkInsert(ContactProviderContract.getUngettableCommsidsUri(context), contentValuesArr);
            return;
        }
        LOG.d("The list of commsIds to be marked as ungettable is null or empty.");
    }

    public static void removeUngettableCommsIDIfObtained(@NonNull Context context, @NonNull String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        removeUngettableCommsIDIfObtained(strArr, arrayList, context);
        try {
            context.getContentResolver().applyBatch(ContactProviderContract.getAuthorityId(context), arrayList);
        } catch (OperationApplicationException | RemoteException e) {
            LOG.e("Failed deleting commsIds from ungettable commsIds table", e);
        }
    }

    public static void setContactCanDropInOnUser(@NonNull String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME, Integer.valueOf(z ? 1 : 0));
        Context context = CommsDaggerWrapper.getComponent().getContext();
        context.getContentResolver().update(ContactProviderContract.getContactsUri(context), contentValues, WHERE_BY_CONTACT_ID, new String[]{str});
    }

    private static ContactUploadInfo trimContactToMaxCloudLength(@NonNull ContactUploadInfo contactUploadInfo, int i) {
        ContactUploadInfo contactUploadInfo2 = new ContactUploadInfo();
        if (contactUploadInfo.getName() != null) {
            contactUploadInfo2.setContactName(trimToMaxLength(contactUploadInfo.getName().getFirstName(), i), trimToMaxLength(contactUploadInfo.getName().getLastName(), i), trimToMaxLength(contactUploadInfo.getName().getPhoneticFirstName(), i), trimToMaxLength(contactUploadInfo.getName().getPhoneticLastName(), i), trimToMaxLength(contactUploadInfo.getName().getNickName(), i));
        }
        contactUploadInfo2.setCompany(trimToMaxLength(contactUploadInfo.getCompany(), i));
        if (contactUploadInfo.getEmails() != null && contactUploadInfo.getEmails().size() > 0) {
            for (ContactUploadInfo.EmailAddress emailAddress : contactUploadInfo.getEmails()) {
                contactUploadInfo2.addEmailAddress(trimToMaxLength(emailAddress.getEmailAddress(), i), trimToMaxLength(emailAddress.getRawType(), i));
            }
        }
        if (contactUploadInfo.getNumbers() != null && contactUploadInfo.getNumbers().size() > 0) {
            for (ContactUploadInfo.PhoneNumber phoneNumber : contactUploadInfo.getNumbers()) {
                contactUploadInfo2.addPhoneNumber(phoneNumber.getNumber(), trimToMaxLength(phoneNumber.getRawType(), i));
            }
        }
        contactUploadInfo2.setServerContactId(contactUploadInfo.getServerContactId());
        contactUploadInfo2.setDeviceContactId(contactUploadInfo.getDeviceContactId());
        return contactUploadInfo2;
    }

    public static List<ContactUploadInfo> trimContactsToMaxCloudLength(@Nullable List<ContactUploadInfo> list) {
        if (list == null) {
            return null;
        }
        int intValue = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.CONTACTS_CLOUD_MAX_LENGTH).intValue();
        ArrayList arrayList = new ArrayList();
        for (ContactUploadInfo contactUploadInfo : list) {
            arrayList.add(trimContactToMaxCloudLength(contactUploadInfo, intValue));
        }
        return arrayList;
    }

    public static String trimToMaxLength(@Nullable String str, int i) {
        return (str == null || str.length() <= i) ? str : str.substring(0, i - 1);
    }

    public static int updateContactBlockStatus(@NonNull Context context, @NonNull String str, boolean z) {
        String[] strArr = {str};
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED, Boolean.valueOf(z));
        CommsLogger commsLogger = LOG;
        commsLogger.i(String.format("Updating contact block status for %s: %b", commsLogger.sensitive(str), Boolean.valueOf(z)));
        return context.getContentResolver().update(ContactProviderContract.getContactsUri(context), contentValues, WHERE_BY_CONTACT_ID, strArr);
    }

    @Deprecated
    public static int updateContactName(String str, ContactName contactName) {
        if (TextUtils.isEmpty(str)) {
            LOG.w("Unable to update contact name if contact id is empty or null");
            return 0;
        } else if (contactName.getFirstName().isEmpty() && contactName.getLastName().isEmpty() && contactName.getNickName().isEmpty()) {
            LOG.w("If we neither have first or last name or nickname, there's no update to be made");
            return 0;
        } else {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Updating contact name for ");
            outline1.append(LOG.sensitive(str));
            commsLogger.d(outline1.toString());
            String[] strArr = {str};
            ContentValues contentValues = new ContentValues();
            contentValues.put("firstName", contactName.getFirstName());
            contentValues.put("lastName", contactName.getLastName());
            contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, contactName.getNickName());
            contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, contactName.getSourceNickName());
            contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_NAME_EMPTY, (Integer) 0);
            Context context = CommsDaggerWrapper.getComponent().getContext();
            return context.getContentResolver().update(ContactProviderContract.getContactsUri(context), contentValues, WHERE_BY_CONTACT_ID, strArr);
        }
    }

    public static boolean updateContactPhoneNumbers(@NonNull Context context, @NonNull Contact contact) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        insertPhoneNumberValues(arrayList, contact, CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.CONTACTS_PHONE_NUMBER_DB_MAX_LENGTH).intValue(), context);
        try {
            context.getContentResolver().applyBatch(ContactProviderContract.getAuthorityId(context), arrayList);
            LOG.d("updateContactPhoneNumbers: succeeded");
            return true;
        } catch (OperationApplicationException | RemoteException e) {
            LOG.e("Failed while deleting & inserting all contacts", e);
            return false;
        }
    }

    public static int updateContactStringColumn(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            LOG.w("Unable to update contact nickname if contact id is empty or null");
            return 0;
        } else if (TextUtils.isEmpty(str2)) {
            CommsLogger commsLogger = LOG;
            commsLogger.w(str2 + "is empty or null, there's no update to be made");
            return 0;
        } else {
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Updating contact ", str2, " for ");
            outline115.append(LOG.sensitive(str));
            commsLogger2.d(outline115.toString());
            String[] strArr = {str};
            ContentValues contentValues = new ContentValues();
            contentValues.put(str2, str3);
            Context context = CommsDaggerWrapper.getComponent().getContext();
            return context.getContentResolver().update(ContactProviderContract.getContactsUri(context), contentValues, WHERE_BY_CONTACT_ID, strArr);
        }
    }

    public static void updateDropInState(Context context, String str, DropInState dropInState, DropInState dropInState2) {
        CommsLogger commsLogger = LOG;
        commsLogger.i(String.format("Updating drop in state: %s, %s", commsLogger.sensitive(str), dropInState));
        if (str == null) {
            LOG.e("ContactId is null, can't update dropIn State!");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME, Integer.valueOf(dropInState == DropInState.ON ? 1 : 0));
        if (dropInState2 != null) {
            contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM, Integer.valueOf(dropInState2 == DropInState.ON ? 1 : 0));
        }
        context.getContentResolver().update(ContactProviderContract.getContactsUri(context), contentValues, WHERE_BY_CONTACT_ID, new String[]{str});
    }

    public static int updateIsContactEverRefreshed(@NonNull Context context, @NonNull String str, boolean z) {
        String[] strArr = {str};
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_EVER_REFRESHED, Boolean.valueOf(z));
        CommsLogger commsLogger = LOG;
        commsLogger.d(String.format("Updating isEverRefreshed for %s: %b", commsLogger.sensitive(str), Boolean.valueOf(z)));
        return context.getContentResolver().update(ContactProviderContract.getContactsUri(context), contentValues, WHERE_BY_CONTACT_ID, strArr);
    }

    private static void updateParentHomeGroupInfoForContactsOfChild(Context context, ArrayList<ContentProviderOperation> arrayList) {
        ContentValues contentValues = new ContentValues();
        HashSet<String> hashSet = new HashSet();
        Cursor fetchAllHomeGroupIDs = fetchAllHomeGroupIDs(context);
        try {
            if (fetchAllHomeGroupIDs == null) {
                LOG.e("Can't fetch hg Ids from DB, updating parent hg info for contacts of child failed!");
                if (fetchAllHomeGroupIDs == null) {
                    return;
                }
                fetchAllHomeGroupIDs.close();
                return;
            }
            fetchAllHomeGroupIDs.moveToFirst();
            while (!fetchAllHomeGroupIDs.isAfterLast()) {
                String string = fetchAllHomeGroupIDs.getString(fetchAllHomeGroupIDs.getColumnIndex("commsId"));
                if (!Strings.isNullOrEmpty(string)) {
                    hashSet.add(string);
                }
                fetchAllHomeGroupIDs.moveToNext();
            }
            fetchAllHomeGroupIDs.close();
            if (hashSet.size() < 1) {
                LOG.d("no hg id found while updating parent hg info for contacts of child!");
                return;
            }
            for (String str : hashSet) {
                HashSet hashSet2 = new HashSet();
                Cursor fetchHomeGroupMembers = fetchHomeGroupMembers(context, str);
                if (fetchHomeGroupMembers == null) {
                    try {
                        LOG.e("Can't fetch hg members from DB updating parent hg info for contacts of child failed!");
                        if (fetchHomeGroupMembers != null) {
                            fetchHomeGroupMembers.close();
                        }
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            if (fetchHomeGroupMembers != null) {
                                try {
                                    fetchHomeGroupMembers.close();
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                }
                            }
                            throw th2;
                        }
                    }
                } else {
                    fetchHomeGroupMembers.moveToFirst();
                    while (!fetchHomeGroupMembers.isAfterLast()) {
                        String string2 = fetchHomeGroupMembers.getString(fetchHomeGroupMembers.getColumnIndex("commsId"));
                        if (!Strings.isNullOrEmpty(string2)) {
                            hashSet2.add(string2);
                        }
                        fetchHomeGroupMembers.moveToNext();
                    }
                    fetchHomeGroupMembers.close();
                    if (hashSet2.size() < 1) {
                        CommsLogger commsLogger = LOG;
                        commsLogger.d("For given hg ID: " + str + ", CommsIds are missing while updating parent hg info for contacts of child!");
                    } else {
                        StringBuilder outline1 = GeneratedOutlineSupport.outline1("commsId IN (");
                        outline1.append(DatabaseUtils.getInListPlaceholder(hashSet2.size()));
                        outline1.append(")");
                        String sb = outline1.toString();
                        contentValues.put(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP, str);
                        arrayList.add(ContentProviderOperation.newUpdate(ContactProviderContract.getPhoneNumberUri(context)).withSelection(sb, (String[]) hashSet2.toArray(new String[hashSet2.size()])).withValues(contentValues).build());
                    }
                }
            }
        } catch (Throwable th4) {
            try {
                throw th4;
            } catch (Throwable th5) {
                if (fetchAllHomeGroupIDs != null) {
                    try {
                        fetchAllHomeGroupIDs.close();
                    } catch (Throwable th6) {
                        th4.addSuppressed(th6);
                    }
                }
                throw th5;
            }
        }
    }

    private static void updateParentHomeGroupInfoInTable(ArrayList<ContentProviderOperation> arrayList, List<? extends Contact> list, Context context) {
        ContentValues contentValues = new ContentValues();
        for (Contact contact : list) {
            if (contact.isHomeGroup()) {
                List<IdentityRawData> commsIdentities = contact.getCommsIdentities();
                ArrayList arrayList2 = new ArrayList();
                if (commsIdentities != null && !commsIdentities.isEmpty() && contact.getHomeGroupIdentity() != null) {
                    for (IdentityRawData identityRawData : commsIdentities) {
                        arrayList2.add(identityRawData.getId());
                    }
                    StringBuilder outline1 = GeneratedOutlineSupport.outline1("commsId IN (");
                    outline1.append(DatabaseUtils.getInListPlaceholder(arrayList2.size()));
                    outline1.append(")");
                    String sb = outline1.toString();
                    contentValues.put(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP, contact.getHomeGroupIdentity().getId());
                    arrayList.add(ContentProviderOperation.newUpdate(ContactProviderContract.getPhoneNumberUri(context)).withSelection(sb, (String[]) arrayList2.toArray(new String[arrayList2.size()])).withValues(contentValues).build());
                }
            }
        }
    }

    private static void removeUngettableCommsIDIfObtained(@NonNull String[] strArr, @NonNull ArrayList<ContentProviderOperation> arrayList, @NonNull Context context) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        for (int i = 0; i < strArr.length; i += 999) {
            int min = Math.min(999, strArr.length - i);
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("commsId IN (");
            outline1.append(TextUtils.join(", ", Collections.nCopies(min, WebConstants.UriConstants.QUESTIONMARK_KEY)));
            outline1.append(")");
            arrayList.add(ContentProviderOperation.newDelete(ContactProviderContract.getUngettableCommsidsUri(context)).withSelection(outline1.toString(), (String[]) Arrays.copyOfRange(strArr, i, min + i)).build());
        }
    }
}
