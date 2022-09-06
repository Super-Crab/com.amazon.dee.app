package com.amazon.deecomms.contacts.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public class Contact implements Parcelable {
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() { // from class: com.amazon.deecomms.contacts.model.Contact.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Contact mo3644createFromParcel(Parcel parcel) {
            return new Contact(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Contact[] mo3645newArray(int i) {
            return new Contact[i];
        }
    };
    @JsonProperty("addresses")
    private List<ContactAddress> addresses;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED)
    private boolean alexaEnabled;
    @JsonProperty("commsIds")
    private List<IdentityRawData> commsIdentities;
    @JsonProperty("commsId")
    private List<String> commsIds;
    @JsonProperty("commsIdsPreferences")
    private Map<String, Map<ContactPreferenceType, Map<String, String>>> commsIdsPreferences;
    @JsonProperty("company")
    private String company;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE)
    private String contactSourceType;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID)
    private String deviceContactId;
    @JsonProperty("emails")
    private List<ContactEmailAddress> emails;
    @JsonProperty("homeGroupId")
    private IdentityRawData homeGroupIdentity;
    @JsonProperty("id")
    private String id;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED)
    private boolean isBlocked;
    @JsonProperty("isBulkImport")
    private boolean isBulkImport;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD)
    private boolean isChild;
    private boolean isEverRefreshed;
    @JsonProperty(ContactProviderContract.PhoneNumberEntry.COLUMN_IS_HOME_GROUP)
    private boolean isHomeGroup;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_LINKED)
    private boolean isLinked;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_MERGED)
    private boolean isMerged;
    @JsonProperty("mergedContactIdentifiers")
    private List<String> mergedContactIdentifiers;
    @JsonProperty("name")
    private ContactName name;
    @JsonProperty("ownerCommsId")
    private String ownerCommsId;
    @JsonProperty("numbers")
    private List<ContactPhoneNumber> phoneNumbers;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_COMMS_ID)
    private String referenceCommsId;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_CONTACT_ID)
    private String referenceContactId;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP)
    private String relationship;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_DISPLAY)
    private boolean shouldDisplay;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_MERGE)
    private boolean shouldMerge;
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID)
    private String sourceDeviceId;

    public Contact() {
        this.isBlocked = false;
        this.isEverRefreshed = false;
    }

    public int canDropInOnMe() {
        int i = 0;
        for (String str : this.commsIdsPreferences.keySet()) {
            i |= DropInState.fromValue(this.commsIdsPreferences.get(str).get(ContactPreferenceType.preferenceGrantedToContactByUser).get(Constants.CONTACT_PREF_DROP_IN)) == DropInState.ON ? 1 : 0;
        }
        return i;
    }

    public int canIDropInOnHim() {
        int i = 0;
        for (String str : this.commsIdsPreferences.keySet()) {
            i |= DropInState.fromValue(this.commsIdsPreferences.get(str).get(ContactPreferenceType.preferenceGrantedToUserByContact).get(Constants.CONTACT_PREF_DROP_IN)) == DropInState.ON ? 1 : 0;
        }
        return i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Contact contact = (Contact) obj;
        return Objects.equal(this.id, contact.id) && Objects.equal(this.commsIds, contact.commsIds) && Objects.equal(this.name, contact.name) && Objects.equal(this.phoneNumbers, contact.phoneNumbers) && Objects.equal(this.emails, contact.emails) && Objects.equal(Boolean.valueOf(this.isBlocked), Boolean.valueOf(contact.isBlocked)) && Objects.equal(this.company, contact.company) && Objects.equal(Boolean.valueOf(this.isBulkImport), Boolean.valueOf(contact.isBulkImport)) && Objects.equal(Boolean.valueOf(this.shouldDisplay), Boolean.valueOf(contact.shouldDisplay)) && Objects.equal(Boolean.valueOf(this.isEverRefreshed), Boolean.valueOf(contact.isEverRefreshed)) && Objects.equal(Boolean.valueOf(this.alexaEnabled), Boolean.valueOf(contact.alexaEnabled)) && Objects.equal(this.deviceContactId, contact.deviceContactId) && Objects.equal(this.sourceDeviceId, contact.sourceDeviceId) && Objects.equal(Boolean.valueOf(this.isHomeGroup), Boolean.valueOf(contact.isHomeGroup)) && Objects.equal(this.commsIdentities, contact.commsIdentities) && Objects.equal(this.homeGroupIdentity, contact.homeGroupIdentity) && Objects.equal(this.ownerCommsId, contact.ownerCommsId) && Objects.equal(Boolean.valueOf(this.isChild), Boolean.valueOf(contact.isChild)) && Objects.equal(this.relationship, contact.relationship) && Objects.equal(this.referenceCommsId, contact.referenceCommsId) && Objects.equal(this.referenceContactId, contact.referenceContactId) && Objects.equal(Boolean.valueOf(this.isLinked), Boolean.valueOf(contact.isLinked)) && Objects.equal(this.contactSourceType, contact.contactSourceType) && Objects.equal(Boolean.valueOf(this.isMerged), Boolean.valueOf(contact.isMerged)) && Objects.equal(Boolean.valueOf(this.shouldMerge), Boolean.valueOf(contact.shouldMerge)) && Objects.equal(this.addresses, contact.addresses) && Objects.equal(this.mergedContactIdentifiers, contact.mergedContactIdentifiers);
    }

    public List<ContactAddress> getAddresses() {
        return this.addresses;
    }

    public List<IdentityRawData> getCommsIdentities() {
        return this.commsIdentities;
    }

    public List<String> getCommsIds() {
        return this.commsIds;
    }

    public String getCompany() {
        return this.company;
    }

    public ContactName getContactName() {
        return this.name;
    }

    public String getContactSourceType() {
        return this.contactSourceType;
    }

    public String getDeviceContactId() {
        return this.deviceContactId;
    }

    public List<ContactEmailAddress> getEmails() {
        return this.emails;
    }

    public IdentityRawData getHomeGroupIdentity() {
        return this.homeGroupIdentity;
    }

    public String getId() {
        return this.id;
    }

    public List<String> getMergedContactIdentifiers() {
        return this.mergedContactIdentifiers;
    }

    public String getOwnerCommsId() {
        return this.ownerCommsId;
    }

    public List<ContactPhoneNumber> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public String getReferenceCommsId() {
        return this.referenceCommsId;
    }

    public String getReferenceContactId() {
        return this.referenceContactId;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public String getSourceDeviceId() {
        return this.sourceDeviceId;
    }

    public int hashCode() {
        return Objects.hashCode(this.id, Boolean.valueOf(this.alexaEnabled), this.commsIds, this.company, Boolean.valueOf(this.isHomeGroup), this.deviceContactId, this.name, this.phoneNumbers, this.emails, this.sourceDeviceId, Boolean.valueOf(this.isHomeGroup), this.commsIdentities, this.homeGroupIdentity, Boolean.valueOf(this.isBlocked), this.company, Boolean.valueOf(this.isBulkImport), Boolean.valueOf(this.shouldDisplay), this.ownerCommsId, Boolean.valueOf(this.isEverRefreshed), Boolean.valueOf(this.isChild), this.relationship, this.referenceCommsId, this.referenceContactId, Boolean.valueOf(this.isLinked), this.contactSourceType, Boolean.valueOf(this.isMerged), Boolean.valueOf(this.shouldMerge), this.addresses, this.mergedContactIdentifiers);
    }

    public boolean isAlexaEnabled() {
        return this.alexaEnabled;
    }

    public boolean isBlocked() {
        return this.isBlocked;
    }

    public boolean isBulkImport() {
        return this.isBulkImport;
    }

    public boolean isChild() {
        return this.isChild;
    }

    public boolean isEverRefreshed() {
        return this.isEverRefreshed;
    }

    public boolean isHomeGroup() {
        return this.isHomeGroup;
    }

    public boolean isLinked() {
        return this.isLinked;
    }

    public boolean isMerged() {
        return this.isMerged;
    }

    public boolean isShouldMerge() {
        return this.shouldMerge;
    }

    public void setAddresses(List<ContactAddress> list) {
        this.addresses = list;
    }

    public void setAlexaEnabled(boolean z) {
        this.alexaEnabled = z;
    }

    public void setBlocked(boolean z) {
        this.isBlocked = z;
    }

    public void setBulkImport(boolean z) {
        this.isBulkImport = z;
    }

    public void setCommsIdentities(List<IdentityRawData> list) {
        this.commsIdentities = list;
    }

    public void setCommsIds(List<String> list) {
        this.commsIds = list;
    }

    public void setCompany(String str) {
        this.company = str;
    }

    public void setContactName(ContactName contactName) {
        this.name = contactName;
    }

    public void setContactSourceType(String str) {
        this.contactSourceType = str;
    }

    public void setDeviceContactId(String str) {
        this.deviceContactId = str;
    }

    public void setEmails(List<ContactEmailAddress> list) {
        this.emails = list;
    }

    public void setEverRefreshed(boolean z) {
        this.isEverRefreshed = z;
    }

    public void setHomeGroup(boolean z) {
        this.isHomeGroup = z;
    }

    public void setHomeGroupIdentity(IdentityRawData identityRawData) {
        this.homeGroupIdentity = identityRawData;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setIsChild(boolean z) {
        this.isChild = z;
    }

    public void setIsLinked(boolean z) {
        this.isLinked = z;
    }

    public void setIsMerged(boolean z) {
        this.isMerged = z;
    }

    public void setMergedContactIdentifiers(List<String> list) {
        this.mergedContactIdentifiers = list;
    }

    public void setOwnerCommsId(String str) {
        this.ownerCommsId = str;
    }

    public void setPhoneNumbers(List<ContactPhoneNumber> list) {
        this.phoneNumbers = list;
    }

    public void setReferenceCommsId(String str) {
        this.referenceCommsId = str;
    }

    public void setReferenceContactId(String str) {
        this.referenceContactId = str;
    }

    public void setRelationship(String str) {
        this.relationship = str;
    }

    public void setShouldDisplay(boolean z) {
        this.shouldDisplay = z;
    }

    public void setShouldMerge(boolean z) {
        this.shouldMerge = z;
    }

    public void setSourceDeviceId(String str) {
        this.sourceDeviceId = str;
    }

    public boolean shouldDisplay() {
        return this.shouldDisplay;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED, this.alexaEnabled).add(SettingsStorageModule.FILTER_SETTINGS_CONTACT_NAME_KEY, this.name).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID, this.sourceDeviceId).add(ContactsModuleConstants.CONTACT_PHONE_NUMBERS, this.phoneNumbers).add("emails", this.emails).add("id", this.id).add("company", this.company).add("commsId", this.commsIds).add("commsIdentities", this.commsIdentities).add("homeGroupIdentity", this.homeGroupIdentity).add(ContactProviderContract.PhoneNumberEntry.COLUMN_IS_HOME_GROUP, this.isHomeGroup).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID, this.deviceContactId).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED, this.isBlocked).add("isBulkImport", this.isBulkImport).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_DISPLAY, this.shouldDisplay).add("ownerCommsId", this.ownerCommsId).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_EVER_REFRESHED, this.isEverRefreshed).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD, this.isChild).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP, this.relationship).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_COMMS_ID, this.referenceCommsId).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_CONTACT_ID, this.referenceContactId).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_LINKED, this.isLinked).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE, this.contactSourceType).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_MERGED, this.isMerged).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_MERGE, this.shouldMerge).add("addresses", this.addresses).add("mergedContactIdentifiers", this.mergedContactIdentifiers).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.alexaEnabled ? 1 : 0);
        parcel.writeParcelable(this.name, i);
        parcel.writeString(this.sourceDeviceId);
        parcel.writeTypedList(this.phoneNumbers);
        parcel.writeTypedList(this.emails);
        parcel.writeString(this.id);
        parcel.writeString(this.company);
        parcel.writeString(this.ownerCommsId);
        parcel.writeStringList(this.commsIds);
        parcel.writeInt(this.isHomeGroup ? 1 : 0);
        parcel.writeInt(this.isBulkImport ? 1 : 0);
        parcel.writeTypedList(this.commsIdentities);
        parcel.writeParcelable(this.homeGroupIdentity, i);
        parcel.writeString(this.deviceContactId);
        parcel.writeInt(this.isBlocked ? 1 : 0);
        parcel.writeInt(this.isBulkImport ? 1 : 0);
        parcel.writeInt(this.shouldDisplay ? 1 : 0);
        parcel.writeString(this.ownerCommsId);
        parcel.writeInt(this.isEverRefreshed ? 1 : 0);
        parcel.writeInt(this.isChild ? 1 : 0);
        parcel.writeString(this.relationship);
        parcel.writeString(this.referenceCommsId);
        parcel.writeString(this.referenceContactId);
        parcel.writeInt(this.isLinked ? 1 : 0);
        parcel.writeString(this.contactSourceType);
        parcel.writeInt(this.isMerged ? 1 : 0);
        parcel.writeInt(this.shouldMerge ? 1 : 0);
        parcel.writeTypedList(this.addresses);
        parcel.writeStringList(this.mergedContactIdentifiers);
    }

    public Contact(Parcel parcel) {
        boolean z = false;
        this.isBlocked = false;
        this.isEverRefreshed = false;
        this.alexaEnabled = parcel.readInt() != 0;
        this.name = (ContactName) parcel.readParcelable(ContactName.class.getClassLoader());
        this.sourceDeviceId = parcel.readString();
        this.phoneNumbers = parcel.createTypedArrayList(ContactPhoneNumber.CREATOR);
        this.emails = parcel.createTypedArrayList(ContactEmailAddress.CREATOR);
        this.id = parcel.readString();
        this.company = parcel.readString();
        this.ownerCommsId = parcel.readString();
        this.commsIds = parcel.createStringArrayList();
        this.isHomeGroup = parcel.readInt() != 0;
        this.isBulkImport = parcel.readInt() != 0;
        this.commsIdentities = parcel.createTypedArrayList(IdentityRawData.CREATOR);
        this.homeGroupIdentity = (IdentityRawData) parcel.readParcelable(IdentityRawData.class.getClassLoader());
        this.deviceContactId = parcel.readString();
        this.isBlocked = parcel.readInt() != 0;
        this.isBulkImport = parcel.readInt() != 0;
        this.shouldDisplay = parcel.readInt() != 0;
        this.isEverRefreshed = parcel.readInt() != 0;
        this.ownerCommsId = parcel.readString();
        this.isChild = parcel.readInt() != 0;
        this.relationship = parcel.readString();
        this.referenceCommsId = parcel.readString();
        this.referenceContactId = parcel.readString();
        this.isLinked = parcel.readInt() != 0;
        this.contactSourceType = parcel.readString();
        this.isMerged = parcel.readInt() != 0;
        this.shouldMerge = parcel.readInt() != 0 ? true : z;
        this.addresses = parcel.createTypedArrayList(ContactAddress.CREATOR);
        this.mergedContactIdentifiers = parcel.createStringArrayList();
    }
}
