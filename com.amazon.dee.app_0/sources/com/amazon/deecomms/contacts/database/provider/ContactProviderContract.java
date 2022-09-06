package com.amazon.deecomms.contacts.database.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public final class ContactProviderContract {
    public static final String COMMS_IDS_TO_CONTACTS_JOIN_TABLE = "PhoneNumbers LEFT JOIN Contacts ON Contacts.serverContactId = PhoneNumbers.serverContactId";
    public static final String COMMS_ID_TO_CONTACTS_MSG_PATH = "contact/commsIDJoinContactsMsg";
    public static final String COMMS_ID_TO_CONTACTS_PATH = "contact/commsIDJoinContacts";
    public static final String COMMS_ID_TO_CONTACTS_TO_EMAIL_ADDRESS_PATH = "contact/commsIDJoinContactsJoinEmailAddress";
    public static final String CONTACTS_JOIN_MERGED_JOIN_PHONE_NUMBER_PATH = "contact/joinMergedJoinPhoneNumber";
    public static final String CONTACTS_JOIN_PHONE_NUMBER_PATH = "contact/joinCommsId";
    public static final String CONTACT_ADDRESS_PATH = "contactAddress";
    public static final String CONTACT_PATH = "contact";
    public static final String EMAIL_ADDRESS_PATH = "emailAddress";
    public static final String MERGED_CONTACT_IDS_PATH = "mergedContactIds";
    public static final String PHONE_NUMBER_PATH = "phoneNumber";
    public static final String UNGETTABLE_COMMSIDS_PATH = "ungettableCommsIds";

    /* loaded from: classes12.dex */
    public static class AddressEntry implements BaseColumns {
        private static final String ALIAS_TABLE_NAME = "AddressesAlias";
        public static final String ALIAS_TABLE_NAME_PREFIX = "AddressesAlias.";
        public static final String COLUMN_ADDRESS_RAW_TYPE = "rawType";
        public static final String COLUMN_ADDRESS_TYPE = "addressType";
        public static final String COLUMN_ADDRESS_VALUE = "value";
        public static final String COLUMN_NAME_SERVER_CONTACT_ID = "serverContactId";
        public static final String INDEX_NAME = "address_unique";
        public static final String TABLE_NAME = "Addresses";
        public static final String TABLE_NAME_PREFIX = "Addresses.";
    }

    /* loaded from: classes12.dex */
    public static class ContactDatabaseEntry implements BaseColumns {
        public static final String ALIAS_TABLE_NAME = "ContactsAlias";
        public static final String ALIAS_TABLE_NAME_PREFIX = "ContactsAlias.";
        public static final String COLUMN_CAN_DROP_IN_ON_ME = "canDropInOnMe";
        public static final String COLUMN_CAN_I_DROP_IN_ON_HIM = "canIDropInOnHim";
        public static final String COLUMN_NAME_ALEXA_ENABLED = "alexaEnabled";
        public static final String COLUMN_NAME_BULK_IMPORT = "bulkImport";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_CONTACT_SOURCE_TYPE = "contactSourceType";
        public static final String COLUMN_NAME_DEVICE_CONTACT_ID = "deviceContactId";
        public static final String COLUMN_NAME_FIRST_NAME = "firstName";
        public static final String COLUMN_NAME_IS_BLOCKED = "isBlocked";
        public static final String COLUMN_NAME_IS_CHILD = "isChild";
        public static final String COLUMN_NAME_IS_EVER_REFRESHED = "isEverRefreshed";
        public static final String COLUMN_NAME_IS_LINKED = "isLinked";
        public static final String COLUMN_NAME_IS_MERGED = "isMerged";
        public static final String COLUMN_NAME_IS_NAME_EMPTY = "isNameEmpty";
        public static final String COLUMN_NAME_LAST_NAME = "lastName";
        public static final String COLUMN_NAME_NICK_NAME = "nickName";
        public static final String COLUMN_NAME_OWNER_COMMS_ID = "ownerCommsId";
        public static final String COLUMN_NAME_PHONETIC_FIRST_NAME = "phoneticFirstName";
        public static final String COLUMN_NAME_PHONETIC_FIRST_NAME_SORT_FORMAT = "phoneticFirstNameSortFormat";
        public static final String COLUMN_NAME_PHONETIC_LAST_NAME = "phoneticLastName";
        public static final String COLUMN_NAME_PHONETIC_LAST_NAME_SORT_FORMAT = "phoneticLastNameSortFormat";
        public static final String COLUMN_NAME_PRONUNCIATION_FIRST_NAME = "pronunciationFirstName";
        public static final String COLUMN_NAME_PRONUNCIATION_LAST_NAME = "pronunciationLastName";
        public static final String COLUMN_NAME_REFERENCE_COMMS_ID = "referenceCommsId";
        public static final String COLUMN_NAME_REFERENCE_CONTACT_ID = "referenceContactId";
        public static final String COLUMN_NAME_RELATIONSHIP = "relationship";
        public static final String COLUMN_NAME_SERVER_CONTACT_ID = "serverContactId";
        public static final String COLUMN_NAME_SHOULD_DISPLAY = "shouldDisplay";
        public static final String COLUMN_NAME_SHOULD_MERGE = "shouldMerge";
        public static final String COLUMN_NAME_SOURCE_DEVICE_ID = "sourceDeviceId";
        public static final String COLUMN_NAME_SOURCE_NICK_NAME = "sourceNickName";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_SERVER_CONTACT_ID = "Contacts.serverContactId";
        public static final String TABLE_NAME = "Contacts";
        public static final String TABLE_NAME_PREFIX = "Contacts.";
    }

    /* loaded from: classes12.dex */
    public static class EmailAddressEntry implements BaseColumns {
        public static final String ALIAS_TABLE_NAME = "EmailAddressesAlias";
        public static final String ALIAS_TABLE_NAME_PREFIX = "EmailAddressesAlias.";
        public static final String COLUMN_EMAIL_ADDRESS = "email";
        public static final String COLUMN_EMAIL_ADDRESS_RAW_TYPE = "emailRawType";
        public static final String COLUMN_EMAIL_ADDRESS_TYPE = "emailType";
        public static final String COLUMN_NAME_SERVER_CONTACT_ID = "serverContactId";
        public static final String INDEX_NAME = "email_address_commsId_unique";
        public static final String TABLE_NAME = "EmailAddresses";
        public static final String TABLE_NAME_PREFIX = "EmailAddresses.";
    }

    /* loaded from: classes12.dex */
    public static class MergedContactIdentifier implements BaseColumns {
        private static final String ALIAS_TABLE_NAME = "MergedContactIdsAlias";
        public static final String ALIAS_TABLE_NAME_PREFIX = "MergedContactIdsAlias.";
        public static final String COLUMN_NAME_IDENTIFIER = "identifier";
        public static final String COLUMN_NAME_SERVER_CONTACT_ID = "serverContactId";
        public static final String INDEX_NAME = "merged_contact_unique";
        public static final String TABLE_NAME = "MergedContactIds";
        public static final String TABLE_NAME_PREFIX = "MergedContactIds.";
    }

    /* loaded from: classes12.dex */
    public static class PhoneNumberEntry implements BaseColumns {
        public static final String ALIAS_TABLE_NAME = "PhoneNumbersAlias";
        public static final String ALIAS_TABLE_NAME_PREFIX = "PhoneNumbersAlias.";
        public static final String COLUMN_COMMS_ID = "commsId";
        public static final String COLUMN_HASHED_PHONE_NUMBER = "hashedPhoneNumber";
        public static final String COLUMN_IS_COBO_CALLABLE = "isCoboCallable";
        public static final String COLUMN_NAME_SERVER_CONTACT_ID = "serverContactId";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_COMMS_ID = "PhoneNumbers.commsId";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_SERVER_CONTACT_ID = "PhoneNumbers.serverContactId";
        public static final String COLUMN_NUMBER = "number";
        public static final String INDEX_NAME = "phonenumber_commsId_unique";
        public static final String TABLE_NAME = "PhoneNumbers";
        public static final String TABLE_NAME_PREFIX = "PhoneNumbers.";
        public static final String COLUMN_NUMBER_TYPE = "numberType";
        public static final String COLUMN_NUMBER_RAW_TYPE = "numberRawType";
        public static final String COLUMN_AOR = "aor";
        public static final String COLUMN_IS_HOME_GROUP = "isHomeGroup";
        public static final String COLUMN_PARENT_HOME_GROUP = "parentHomeGroup";
        public static final String[] ALL_COLUMNS = {"_id", "serverContactId", "number", COLUMN_NUMBER_TYPE, COLUMN_NUMBER_RAW_TYPE, "commsId", COLUMN_AOR, COLUMN_IS_HOME_GROUP, COLUMN_PARENT_HOME_GROUP};
    }

    /* loaded from: classes12.dex */
    public static class UngettableCommsIds {
        public static final String COLUMN_COMMS_ID = "commsId";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_CONVERSATION_KEY_ID = "UngettableCommsIds.commsId";
        public static final String TABLE_NAME = "UngettableCommsIds";
    }

    private ContactProviderContract() {
    }

    public static String getAuthorityId(@NonNull Context context) {
        return context.getString(R.string.contactsProviderAuthoritiesId);
    }

    public static Uri getCommsIdToContactsMsgUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append(COMMS_ID_TO_CONTACTS_MSG_PATH);
        return Uri.parse(outline1.toString());
    }

    public static Uri getCommsIdToContactsToEmailAddressUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append(COMMS_ID_TO_CONTACTS_TO_EMAIL_ADDRESS_PATH);
        return Uri.parse(outline1.toString());
    }

    public static Uri getCommsIdToContactsUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append(COMMS_ID_TO_CONTACTS_PATH);
        return Uri.parse(outline1.toString());
    }

    public static Uri getContactAddressUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append(CONTACT_ADDRESS_PATH);
        return Uri.parse(outline1.toString());
    }

    public static Uri getContactJoinMergedJoinPhoneNumberUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append(CONTACTS_JOIN_MERGED_JOIN_PHONE_NUMBER_PATH);
        return Uri.parse(outline1.toString());
    }

    public static Uri getContactJoinPhoneNumberUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append(CONTACTS_JOIN_PHONE_NUMBER_PATH);
        return Uri.parse(outline1.toString());
    }

    public static Uri getContactsUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append(CONTACT_PATH);
        return Uri.parse(outline1.toString());
    }

    public static Uri getEmailAddressUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append(EMAIL_ADDRESS_PATH);
        return Uri.parse(outline1.toString());
    }

    public static Uri getMergedContactIdsUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append(MERGED_CONTACT_IDS_PATH);
        return Uri.parse(outline1.toString());
    }

    public static Uri getPhoneNumberUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append("phoneNumber");
        return Uri.parse(outline1.toString());
    }

    public static Uri getUngettableCommsidsUri(@NonNull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
        outline1.append(getAuthorityId(context));
        outline1.append("/");
        outline1.append(UNGETTABLE_COMMSIDS_PATH);
        return Uri.parse(outline1.toString());
    }
}
