package com.amazon.deecomms.nativemodules.util;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.IdentityValidator;
import com.amazon.deecomms.common.util.Iso8601DateFormatter;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.ContactAddress;
import com.amazon.deecomms.contacts.model.ContactEmailAddress;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.EmailAddressType;
import com.amazon.deecomms.contacts.model.PhoneNumberType;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.provider.MessagingProviderContract;
import com.amazon.deecomms.nativemodules.model.Person;
import com.amazon.deecomms.util.BiConsumer;
import com.amazon.deecomms.util.Consumer;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.internal.Sets;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes12.dex */
public class ContactsDataStoreUtil {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactsDataStoreUtil.class);
    private final Context mApplicationContext;
    private final Iso8601DateFormatter iso8601DateFormatter = new Iso8601DateFormatter();
    private CommsIdentityManager commsIdentityManager = CommsDaggerWrapper.getComponent().getCommsIdentityManager();

    public ContactsDataStoreUtil(Context context) {
        this.mApplicationContext = context;
    }

    private void addContactAddresses(@NonNull String str, @NonNull String[] strArr, @NonNull HashMap<String, Person> hashMap) {
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getContactAddressUri(this.mApplicationContext), null, str, strArr, null);
        try {
            if (query != null) {
                while (query.moveToNext()) {
                    Person person = hashMap.get(query.getString(query.getColumnIndex("serverContactId")));
                    if (person != null) {
                        updatePersonWithContactAddressFromCursor(person, query);
                    } else {
                        LOG.w("Found contact address for a contact that is not in the table.");
                    }
                }
                query.close();
                return;
            }
            throw new IllegalStateException("addContactAddresses: Getting addresses from database failed.");
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

    private void addEmailAddresses(@NonNull String str, @NonNull String[] strArr, @NonNull HashMap<String, Person> hashMap) {
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getEmailAddressUri(this.mApplicationContext), null, str, strArr, null);
        try {
            if (query != null) {
                while (query.moveToNext()) {
                    Person person = hashMap.get(query.getString(query.getColumnIndex("serverContactId")));
                    if (person != null) {
                        updatePersonWithEmailsFromCursor(person, query);
                    } else {
                        LOG.w("Found an email address for a contact that is not in the contacts table.");
                    }
                }
                query.close();
                return;
            }
            throw new IllegalStateException("addEmailAddresses: Getting email addresses from database failed.");
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

    private void addMergedContactIds(@NonNull String str, @NonNull String[] strArr, @NonNull HashMap<String, Person> hashMap) {
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getMergedContactIdsUri(this.mApplicationContext), null, str, strArr, null);
        try {
            if (query != null) {
                while (query.moveToNext()) {
                    Person person = hashMap.get(query.getString(query.getColumnIndex("serverContactId")));
                    if (person != null) {
                        String string = query.getString(query.getColumnIndex("identifier"));
                        if (!Strings.isNullOrEmpty(string)) {
                            person.getMergeContactIdentifiers().add(string);
                        }
                    } else {
                        LOG.w("Found mergedContactId for a contact that is not in the table.");
                    }
                }
                query.close();
                return;
            }
            throw new IllegalStateException("addMergedContactIds: Getting mergedContactIds from database failed.");
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

    @NonNull
    private HashMap<String, Person> addPhoneDetailsAndGetParentHomeGroups(@NonNull String str, @NonNull String[] strArr, @NonNull HashMap<String, Person> hashMap) {
        HashMap<String, Person> hashMap2 = new HashMap<>();
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getPhoneNumberUri(this.mApplicationContext), null, str, strArr, null);
        try {
            if (query != null) {
                while (query.moveToNext()) {
                    Person person = hashMap.get(query.getString(query.getColumnIndex("serverContactId")));
                    if (person != null) {
                        updatePersonWithPhoneDetailsFromCursor(person, query);
                        if (!Strings.isNullOrEmpty(person.getHomeGroupCommsId())) {
                            hashMap2.put(person.getHomeGroupCommsId(), person);
                        }
                    } else {
                        LOG.w("Found a phone number for a contact that is not in the contacts table.");
                    }
                }
                query.close();
                return hashMap2;
            }
            throw new IllegalStateException("addPhoneDetailsAndGetParentHomeGroups: Getting phone numbers from database failed.");
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

    private static Person createPersonFromCursor(@NonNull Cursor cursor) {
        Person person = new Person();
        person.setId(cursor.getString(cursor.getColumnIndex("serverContactId")));
        boolean z = false;
        person.setBlocked(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED)) == 1);
        person.setName(new ContactName(cursor.getString(cursor.getColumnIndex("firstName")), cursor.getString(cursor.getColumnIndex("lastName")), cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME)), cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME)), cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME)), cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME)), cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME)), cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME))));
        person.setSourceDeviceId(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID)));
        person.setDeviceContactId(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID)));
        person.setOwnerCommsId(cursor.getString(cursor.getColumnIndex("ownerCommsId")));
        person.setAlexaEnabled(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED)) == 1);
        person.setBulkImport(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_BULK_IMPORT)) == 1);
        person.setCompanyName(cursor.getString(cursor.getColumnIndex("company")));
        person.setChild(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD)) == 1);
        person.setRelationship(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP)));
        person.setReferenceContactId(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_CONTACT_ID)));
        person.setReferenceCommsId(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_COMMS_ID)));
        person.setLinked(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_LINKED)) == 1);
        person.setContactSourceType(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE)));
        person.setIsMerged(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_MERGED)) == 1);
        person.setShouldMerge(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_MERGE)) == 1);
        if (cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_DISPLAY)) == 1) {
            z = true;
        }
        person.setShouldDisplay(z);
        return person;
    }

    @NonNull
    private HashMap<String, Person> createPersonHashMap(@NonNull String str, @NonNull String[] strArr) {
        HashMap<String, Person> hashMap = new HashMap<>();
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getContactsUri(this.mApplicationContext), null, str, strArr, null);
        try {
            if (query != null) {
                while (query.moveToNext()) {
                    hashMap.put(query.getString(query.getColumnIndex("serverContactId")), createPersonFromCursor(query));
                }
                query.close();
                return hashMap;
            }
            throw new IllegalStateException("createPersonHashMap: Getting contacts from database failed.");
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

    /* JADX INFO: Access modifiers changed from: private */
    public void getBlockContacts(@NonNull Cursor cursor, @NonNull WritableArray writableArray) {
        HashMap hashMap = new HashMap();
        while (cursor.moveToNext()) {
            String string = cursor.getString(cursor.getColumnIndex("serverContactId"));
            WritableMap writableMap = (WritableMap) hashMap.get(string);
            if (writableMap == null) {
                hashMap.put(string, getBlockContactsMap(cursor));
            } else {
                String string2 = cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP));
                if (!Strings.isNullOrEmpty(string2)) {
                    writableMap.putString("homeGroupCommsId", string2);
                }
            }
        }
        for (WritableMap writableMap2 : hashMap.values()) {
            writableArray.pushMap(writableMap2);
        }
    }

    private WritableMap getBlockContactsMap(Cursor cursor) {
        WritableMap createMap = Arguments.createMap();
        boolean z = false;
        boolean z2 = cursor.getInt(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_IS_HOME_GROUP)) == 1;
        createMap.putString(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, z2 ? ReactBridgeSerializer.HOMEGROUP_TYPE_DISCRIMINATOR : ReactBridgeSerializer.PERSON_TYPE_DISCRIMINATOR);
        String string = cursor.getString(cursor.getColumnIndex("commsId"));
        createMap.putString(CamerasRouteParameter.DISPLAY_NAME, ContactUtils.getFullName(getContactName(cursor)));
        createMap.putString("id", cursor.getString(cursor.getColumnIndex("serverContactId")));
        createMap.putString("commsId", string);
        if (cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED)) == 1) {
            z = true;
        }
        createMap.putBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED, z);
        if (!z2) {
            string = cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP));
        }
        createMap.putString("homeGroupCommsId", string);
        return createMap;
    }

    private ReadableMap getContactMap(@Nullable String str, @Nullable String[] strArr, @NonNull BiConsumer<Cursor, WritableMap> biConsumer) {
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getContactJoinPhoneNumberUri(this.mApplicationContext), null, str, strArr, null);
        try {
            if (query == null) {
                LOG.e("getContactMap: Query execution failed!");
                if (query != null) {
                    query.close();
                }
                return null;
            }
            WritableMap createMap = Arguments.createMap();
            biConsumer.accept(query, createMap);
            query.close();
            return createMap;
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

    @NonNull
    private static ContactName getContactName(@NonNull Cursor cursor) {
        return new ContactName(cursor.getString(cursor.getColumnIndex("firstName")), cursor.getString(cursor.getColumnIndex("lastName")), cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME)), cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME)));
    }

    @Nullable
    private ReadableArray getContactsArray(@Nullable String str, @Nullable String[] strArr, @NonNull BiConsumer<Cursor, WritableArray> biConsumer) {
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getContactJoinPhoneNumberUri(this.mApplicationContext), null, str, strArr, null);
        try {
            if (query == null) {
                LOG.e("getContactsArray: Query execution failed!");
                if (query != null) {
                    query.close();
                }
                return null;
            }
            WritableArray createArray = Arguments.createArray();
            biConsumer.accept(query, createArray);
            query.close();
            return createArray;
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

    @Nullable
    private WritableMap getHomeGroupMap(@NonNull Cursor cursor) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, ReactBridgeSerializer.HOMEGROUP_TYPE_DISCRIMINATOR);
        String string = cursor.getString(cursor.getColumnIndex("commsId"));
        createMap.putString("id", cursor.getString(cursor.getColumnIndex("serverContactId")));
        createMap.putString("name", ContactUtils.getFullName(getContactName(cursor)));
        createMap.putString("commsId", string);
        createMap.putString(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR, cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR)));
        boolean z = false;
        createMap.putBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED, cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED)) == 1);
        createMap.putBoolean("canDropIn", cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME)) == 1);
        if (cursor.getInt(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM)) == 1) {
            z = true;
        }
        createMap.putBoolean("canBeDroppedInOn", z);
        createMap.putString(CamerasRouteParameter.DISPLAY_NAME, ContactUtils.getFullName(getContactName(cursor)));
        createMap.putString("homeGroupCommsId", string);
        createMap.putArray("members", Arguments.createArray());
        createMap.putString("timeLastLoaded", this.iso8601DateFormatter.formatDateTime(System.currentTimeMillis()));
        return createMap;
    }

    private static int getInt(@NonNull Cursor cursor, @NonNull String str) {
        return cursor.getInt(cursor.getColumnIndex(str));
    }

    @Nullable
    private Person[] getListOfContactIdsByOwner(@NonNull String... strArr) {
        if (strArr.length == 0) {
            LOG.w("Empty ownerCommsId. This is unexpected.");
            return new Person[0];
        }
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("serverContactId IN (SELECT serverContactId FROM Contacts WHERE ownerCommsId IN (?");
        outline1.append(Strings.repeat(", ?", strArr.length - 1));
        outline1.append("))");
        return getPersonsFromDatabase(outline1.toString(), strArr);
    }

    @Nullable
    private Person[] getPersonsFromDatabase(@NonNull String str, @NonNull String[] strArr) {
        try {
            HashMap<String, Person> createPersonHashMap = createPersonHashMap(str, strArr);
            if (createPersonHashMap.size() == 0) {
                return new Person[0];
            }
            HashMap<String, Person> addPhoneDetailsAndGetParentHomeGroups = addPhoneDetailsAndGetParentHomeGroups(str, strArr, createPersonHashMap);
            addEmailAddresses(str, strArr, createPersonHashMap);
            addContactAddresses(str, strArr, createPersonHashMap);
            addMergedContactIds(str, strArr, createPersonHashMap);
            updateDropInStatus(addPhoneDetailsAndGetParentHomeGroups);
            return (Person[]) createPersonHashMap.values().toArray(new Person[createPersonHashMap.values().size()]);
        } catch (IllegalStateException e) {
            LOG.e(e.getMessage());
            return null;
        }
    }

    private static String getString(@NonNull Cursor cursor, @NonNull String str) {
        return cursor.getString(cursor.getColumnIndex(str));
    }

    private static boolean isContactOwnedByLogInUser(@NonNull String str) {
        return str.equals(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("isContactOwnedByLogInUser", false)) || str.equals(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("isContactOwnedByLogInUser", false));
    }

    private void updateDropInStatus(@NonNull HashMap<String, Person> hashMap) {
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getContactJoinPhoneNumberUri(this.mApplicationContext), new String[]{"commsId", ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM, ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME}, "isHomeGroup = 1", null, null);
        try {
            if (query != null) {
                while (query.moveToNext()) {
                    Person person = hashMap.get(query.getString(query.getColumnIndex("commsId")));
                    if (person != null) {
                        boolean z = false;
                        person.setCanBeDroppedIn(query.getInt(query.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM)) == 1);
                        if (query.getInt(query.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME)) == 1) {
                            z = true;
                        }
                        person.setCanDropIn(z);
                    }
                }
                query.close();
                return;
            }
            throw new IllegalStateException("updateDropInStatus: Getting phone numbers from database failed.");
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

    private void updatePersonWithContactAddressFromCursor(@NonNull Person person, @NonNull Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("value"));
        String string2 = cursor.getString(cursor.getColumnIndex(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE));
        String string3 = cursor.getString(cursor.getColumnIndex("addressType"));
        ContactAddress contactAddress = new ContactAddress();
        contactAddress.setValue(string);
        contactAddress.setRawType(string2);
        contactAddress.setType(string3);
        person.getAddresses().add(contactAddress);
    }

    private static void updatePersonWithEmailsFromCursor(@NonNull Person person, @NonNull Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("email"));
        if (Strings.isNullOrEmpty(string)) {
            return;
        }
        ContactEmailAddress contactEmailAddress = new ContactEmailAddress();
        contactEmailAddress.setEmailAddress(string);
        contactEmailAddress.setType(EmailAddressType.valueOf(cursor.getString(cursor.getColumnIndex(ContactProviderContract.EmailAddressEntry.COLUMN_EMAIL_ADDRESS_TYPE))));
        contactEmailAddress.setRawType(cursor.getString(cursor.getColumnIndex(ContactProviderContract.EmailAddressEntry.COLUMN_EMAIL_ADDRESS_RAW_TYPE)));
        person.getEmails().add(contactEmailAddress);
    }

    private static void updatePersonWithPhoneDetailsFromCursor(@NonNull Person person, @NonNull Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("commsId"));
        if (!Strings.isNullOrEmpty(string)) {
            person.getCommsIds().add(string);
        }
        String string2 = cursor.getString(cursor.getColumnIndex("number"));
        if (!Strings.isNullOrEmpty(string2)) {
            ContactPhoneNumber contactPhoneNumber = new ContactPhoneNumber();
            contactPhoneNumber.setPhoneNumber(string2);
            contactPhoneNumber.setType(PhoneNumberType.valueOf(cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_NUMBER_TYPE))));
            contactPhoneNumber.setRawType(cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_NUMBER_RAW_TYPE)));
            contactPhoneNumber.setObfuscatedPhoneNumber(cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_HASHED_PHONE_NUMBER)));
            contactPhoneNumber.setCoboEnabled(cursor.getInt(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_IS_COBO_CALLABLE)) != 0);
            person.getPhoneNumbers().add(contactPhoneNumber);
        }
        String string3 = cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR));
        if (!Strings.isNullOrEmpty(string3)) {
            person.setAor(string3);
        }
        String string4 = cursor.getString(cursor.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP));
        if (!Strings.isNullOrEmpty(string4)) {
            person.setHomeGroupCommsId(string4);
        }
    }

    public int deleteConversation(String str) {
        return this.mApplicationContext.getContentResolver().delete(MessagingProviderContract.Conversations.getContentUri(this.mApplicationContext), "conversation_id = ?", new String[]{str});
    }

    public ReadableMap getAllHomeGroups() {
        return getContactMap("isHomeGroup = 1 ", null, new BiConsumer() { // from class: com.amazon.deecomms.nativemodules.util.-$$Lambda$ContactsDataStoreUtil$GJ7zUnnQWDgOxQBZpktCkzwURgI
            @Override // com.amazon.deecomms.util.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ContactsDataStoreUtil.this.lambda$getAllHomeGroups$1$ContactsDataStoreUtil((Cursor) obj, (WritableMap) obj2);
            }
        });
    }

    @Nullable
    public Person[] getChildContactsFromDatabase() {
        return getPersonsFromDatabase("serverContactId IN (SELECT serverContactId FROM Contacts WHERE isChild == 1 AND shouldDisplay == 1 AND ownerCommsId == ?)", new String[]{this.commsIdentityManager.getHomeGroupId("getChildContactsFromDatabase", false)});
    }

    @Nullable
    public String getCommsIdFromServerContactId(@NonNull String str) {
        return ContactsProviderUtils.getCommsIdFromServerContactId(this.mApplicationContext, str);
    }

    @VisibleForTesting
    HashMap<String, String> getCommsIdToServerContactIdMap() {
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getContactJoinPhoneNumberUri(this.mApplicationContext), new String[]{ContactProviderContract.PhoneNumberEntry.COLUMN_NAME_WITH_TABLE_NAME_COMMS_ID, ContactProviderContract.PhoneNumberEntry.COLUMN_NAME_WITH_TABLE_NAME_SERVER_CONTACT_ID}, "PhoneNumbers.commsId IS NOT NULL AND LENGTH(PhoneNumbers.commsId) > 0 AND Contacts.ownerCommsId IN (?, ?)", new String[]{this.commsIdentityManager.getCommsId("getCommsIdToServerContactIdMap", false), this.commsIdentityManager.getHomeGroupId("getCommsIdToServerContactIdMap", false)}, null);
        try {
            if (query == null) {
                LOG.e("getServerContactIdsFromCommsIds: Query execution failed!");
                if (query != null) {
                    query.close();
                }
                return null;
            }
            HashMap<String, String> hashMap = new HashMap<>();
            while (query.moveToNext()) {
                hashMap.put(query.getString(query.getColumnIndex("commsId")), query.getString(query.getColumnIndex("serverContactId")));
            }
            query.close();
            return hashMap;
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

    @Nullable
    public ReadableArray getContactsAsSimplePersonFromDatabase() {
        WritableArray createArray = Arguments.createArray();
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getContactsUri(this.mApplicationContext), null, null, null, null);
        try {
            if (query == null) {
                LOG.e("getContactsAsSimplePersonFromDatabase: Query execution failed!");
                if (query != null) {
                    query.close();
                }
                return null;
            } else if (query.moveToFirst()) {
                do {
                    String string = query.getString(query.getColumnIndex("ownerCommsId"));
                    boolean z = true;
                    if (query.getInt(query.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_DISPLAY)) == 1 && !Strings.isNullOrEmpty(string) && isContactOwnedByLogInUser(string)) {
                        WritableMap createMap = Arguments.createMap();
                        String string2 = query.getString(query.getColumnIndex("serverContactId"));
                        createMap.putString("id", string2);
                        createMap.putString("fullName", ContactUtils.getFullName(new ContactName(query.getString(query.getColumnIndex("firstName")), query.getString(query.getColumnIndex("lastName")), query.getString(query.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME)), query.getString(query.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME)))));
                        if (query.getInt(query.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED)) != 1) {
                            z = false;
                        }
                        createMap.putBoolean("isAlexaEnabled", z);
                        createMap.putBoolean("isIdentity", IdentityValidator.isSelf(getCommsIdFromServerContactId(string2)));
                        createArray.pushMap(createMap);
                    }
                } while (query.moveToNext());
                query.close();
                return createArray;
            } else {
                query.close();
                return createArray;
            }
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

    @NonNull
    public Person[] getContactsByIds(@NonNull String[] strArr) {
        if (strArr.length == 0) {
            LOG.w("Empty contactIds arrays. This is unexpected.");
            return new Person[0];
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (int i = 0; i < strArr.length; i += 999) {
            int min = Math.min(999, strArr.length - i);
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("serverContactId IN (?");
            outline1.append(Strings.repeat(", ?", min - 1));
            outline1.append(")");
            Person[] personsFromDatabase = getPersonsFromDatabase(outline1.toString(), (String[]) Arrays.copyOfRange(strArr, i, min + i));
            if (personsFromDatabase == null) {
                LOG.e("getting contacts from database failed.");
                return null;
            }
            Collections.addAll(arrayList, personsFromDatabase);
        }
        return (Person[]) arrayList.toArray(new Person[arrayList.size()]);
    }

    @Nullable
    public Person[] getContactsByOwnerCommsIdFromDatabase(@NonNull String str) {
        if (!isContactOwnedByLogInUser(str)) {
            return getListOfContactIdsByOwner(str);
        }
        return getListOfContactIdsByOwner(this.commsIdentityManager.getCommsId("getContactsByOwnerCommsIdFromDatabase", false), this.commsIdentityManager.getHomeGroupId("getContactsByOwnerCommsIdFromDatabase", false));
    }

    public void getContactsNamesByCommsId(@NonNull Consumer<ReadableMap> consumer, @NonNull List<String> list) {
        Person[] contactsByIds;
        WritableMap createMap = Arguments.createMap();
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!Strings.isNullOrEmpty(str)) {
                String serverContactIdFromCommsId = getServerContactIdFromCommsId(str);
                if (!Strings.isNullOrEmpty(serverContactIdFromCommsId)) {
                    arrayList.add(serverContactIdFromCommsId);
                }
            }
        }
        if (arrayList.size() > 0) {
            String[] strArr = new String[arrayList.size()];
            arrayList.toArray(strArr);
            for (Person person : getContactsByIds(strArr)) {
                if (person != null) {
                    String fullName = ContactUtils.getFullName(person.getName());
                    for (String str2 : person.getCommsIds()) {
                        createMap.putString(str2, fullName);
                    }
                }
            }
        }
        consumer.accept(createMap);
    }

    public ReadableArray getContactsWithBlockStatusArrayFromDatabase() {
        return getContactsArray("alexaEnabled = ? AND ownerCommsId IN (?, ?) AND shouldDisplay = 1", new String[]{"1", this.commsIdentityManager.getCommsId("getContactsWithBlockStatusArrayFromDatabase", false), this.commsIdentityManager.getHomeGroupId("getContactsWithBlockStatusArrayFromDatabase", false)}, new BiConsumer() { // from class: com.amazon.deecomms.nativemodules.util.-$$Lambda$ContactsDataStoreUtil$pIlh6lJjkIe1NlH5vohfGULF9gI
            @Override // com.amazon.deecomms.util.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ContactsDataStoreUtil.this.getBlockContacts((Cursor) obj, (WritableArray) obj2);
            }
        });
    }

    @Nullable
    public ReadableMap getConversationInfoByRecipientCommsId(@NonNull String str) {
        Cursor query = this.mApplicationContext.getContentResolver().query(MessagingProviderContract.Conversations.getConversationContentUri(this.mApplicationContext), new String[]{"conversation_id", "last_msg_id"}, "recipient_id = ?", new String[]{str}, null);
        try {
            if (query == null) {
                LOG.e("getConversationInfoByRecipientCommsId: Query execution failed!");
                if (query != null) {
                    query.close();
                }
                return null;
            } else if (!query.moveToFirst()) {
                query.close();
                return null;
            } else {
                String string = query.getString(query.getColumnIndex("conversation_id"));
                String string2 = query.getString(query.getColumnIndex("last_msg_id"));
                WritableMap createMap = Arguments.createMap();
                createMap.putString("conversationId", string);
                createMap.putString("lastMessageId", string2);
                query.close();
                return createMap;
            }
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

    @Nullable
    public ReadableMap getHomeGroup(@NonNull String str) {
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getContactJoinPhoneNumberUri(this.mApplicationContext), null, "commsId = ? ", new String[]{str}, null);
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    WritableMap homeGroupMap = getHomeGroupMap(query);
                    query.close();
                    return homeGroupMap;
                }
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
        LOG.e("getHomeGroup: Query execution failed or no HomeGroup with given id.");
        if (query != null) {
            query.close();
        }
        return null;
    }

    @Nullable
    public ReadableArray getHomeGroupsThatCanDropIn() {
        return getContactsArray("isHomeGroup = ? AND Contacts.canDropInOnMe = ?", new String[]{"1", "1"}, new BiConsumer() { // from class: com.amazon.deecomms.nativemodules.util.-$$Lambda$ContactsDataStoreUtil$-ypu1zfxb0g9_BykflJg-jwAQxo
            @Override // com.amazon.deecomms.util.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ContactsDataStoreUtil.this.lambda$getHomeGroupsThatCanDropIn$0$ContactsDataStoreUtil((Cursor) obj, (WritableArray) obj2);
            }
        });
    }

    @NonNull
    public Set<String> getMyHomeGroupMembers(@Nullable String str) {
        HashSet newHashSet = Sets.newHashSet();
        if (Strings.isNullOrEmpty(str)) {
            return newHashSet;
        }
        Cursor fetchHomeGroupMembers = ContactsProviderUtils.fetchHomeGroupMembers(this.mApplicationContext, str);
        try {
            if (fetchHomeGroupMembers == null) {
                LOG.e("getMyHomeGroupMembers: Query execution failed!");
                if (fetchHomeGroupMembers != null) {
                    fetchHomeGroupMembers.close();
                }
                return newHashSet;
            }
            fetchHomeGroupMembers.moveToFirst();
            while (!fetchHomeGroupMembers.isAfterLast()) {
                newHashSet.add(fetchHomeGroupMembers.getString(fetchHomeGroupMembers.getColumnIndex("commsId")));
                fetchHomeGroupMembers.moveToNext();
            }
            fetchHomeGroupMembers.close();
            return newHashSet;
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
    }

    @Nullable
    public Person getPersonByServerIdFromDatabase(@NonNull String str) {
        Person[] personsFromDatabase = getPersonsFromDatabase("serverContactId = ?", new String[]{str});
        if (personsFromDatabase == null || personsFromDatabase.length <= 0) {
            return null;
        }
        return personsFromDatabase[0];
    }

    @Nullable
    public String getServerContactIdFromCommsId(@NonNull String str) {
        Cursor query = this.mApplicationContext.getContentResolver().query(ContactProviderContract.getContactJoinPhoneNumberUri(this.mApplicationContext), new String[]{ContactProviderContract.PhoneNumberEntry.COLUMN_NAME_WITH_TABLE_NAME_SERVER_CONTACT_ID}, "PhoneNumbers.commsId = ? AND Contacts.ownerCommsId IN (?, ?)", new String[]{str, this.commsIdentityManager.getCommsId("getServerContactIdFromCommsId", false), this.commsIdentityManager.getHomeGroupId("getServerContactIdFromCommsId", false)}, "Contacts.serverContactId DESC");
        String str2 = null;
        try {
            if (query == null) {
                LOG.e("getServerContactIdFromCommsId: Query execution failed!");
                if (query != null) {
                    query.close();
                }
                return null;
            }
            if (query.moveToFirst()) {
                str2 = query.getString(query.getColumnIndex("serverContactId"));
            } else {
                LOG.w("Could not find serverId for CommsId");
                MetricsHelper.recordOperationalMetricWithSource(MetricKeys.GET_SERVER_ID, "getServerContactIdFromCommsId");
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

    public /* synthetic */ void lambda$getAllHomeGroups$1$ContactsDataStoreUtil(Cursor cursor, WritableMap writableMap) {
        while (cursor.moveToNext()) {
            WritableMap homeGroupMap = getHomeGroupMap(cursor);
            writableMap.putMap(homeGroupMap.getString("commsId"), homeGroupMap);
        }
    }

    public /* synthetic */ void lambda$getHomeGroupsThatCanDropIn$0$ContactsDataStoreUtil(Cursor cursor, WritableArray writableArray) {
        while (cursor.moveToNext()) {
            writableArray.pushMap(getHomeGroupMap(cursor));
        }
    }

    public boolean setHomeGroupCanBeDroppedInOn(@NonNull String str, boolean z) {
        String serverContactIdFromCommsId = getServerContactIdFromCommsId(str);
        if (serverContactIdFromCommsId == null) {
            return !z ? 1 : 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM, Integer.valueOf(z ? 1 : 0));
        this.mApplicationContext.getContentResolver().update(ContactProviderContract.getContactsUri(this.mApplicationContext), contentValues, "serverContactId = ?", new String[]{serverContactIdFromCommsId});
        return true;
    }

    public boolean setHomeGroupCanDropIn(@NonNull String str, boolean z) {
        String serverContactIdFromCommsId = getServerContactIdFromCommsId(str);
        if (serverContactIdFromCommsId == null) {
            return !z ? 1 : 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME, Integer.valueOf(z ? 1 : 0));
        this.mApplicationContext.getContentResolver().update(ContactProviderContract.getContactsUri(this.mApplicationContext), contentValues, "serverContactId = ?", new String[]{serverContactIdFromCommsId});
        return true;
    }

    public void updateContactsBlockStatus(@NonNull ReadableArray readableArray) {
        HashMap<String, String> commsIdToServerContactIdMap = getCommsIdToServerContactIdMap();
        if (commsIdToServerContactIdMap == null) {
            LOG.e("Getting commsId to server contact id mappings failed.");
            return;
        }
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableMap mo6944getMap = readableArray.mo6944getMap(i);
            String str = commsIdToServerContactIdMap.get(mo6944getMap.getString("commsId"));
            boolean z = mo6944getMap.getBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED);
            if (!Strings.isNullOrEmpty(str)) {
                ContactsProviderUtils.addUpdateBlockStatusOperation(str, z, arrayList);
            } else {
                LOG.i("Could not find server contact id for commsId");
            }
        }
        try {
            this.mApplicationContext.getContentResolver().applyBatch(ContactProviderContract.getAuthorityId(this.mApplicationContext), arrayList);
        } catch (OperationApplicationException | RemoteException e) {
            LOG.e("Updating block status failed.", e);
        }
    }
}
