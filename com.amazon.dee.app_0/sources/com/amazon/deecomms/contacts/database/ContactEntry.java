package com.amazon.deecomms.contacts.database;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.DropInState;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class ContactEntry implements Parcelable {
    public static final Parcelable.Creator<ContactEntry> CREATOR = new Parcelable.Creator<ContactEntry>() { // from class: com.amazon.deecomms.contacts.database.ContactEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ContactEntry mo3642createFromParcel(Parcel parcel) {
            return new ContactEntry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ContactEntry[] mo3643newArray(int i) {
            return new ContactEntry[i];
        }
    };
    private boolean alexaEnabled;
    private boolean canIDropInOnHim;
    private String company;
    private String contactSourceType;
    private String deviceContactId;
    private DropInState dropInState;
    private FullContactName fullContactName;
    private String id;
    private boolean isBlocked;
    private boolean isBulkImport;
    private boolean isChild;
    private boolean isEverRefreshed;
    private boolean isMerged;
    private boolean isNameEmpty;
    private ContactName name;
    private String ownerCommsId;
    private String relationship;
    private boolean shouldDisplay;
    private boolean shouldMerge;
    private String sourceDeviceId;

    public ContactEntry() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ContactEntry.class != obj.getClass()) {
            return false;
        }
        ContactEntry contactEntry = (ContactEntry) obj;
        return Objects.equal(this.id, contactEntry.id) && Objects.equal(Boolean.valueOf(this.alexaEnabled), Boolean.valueOf(contactEntry.alexaEnabled)) && Objects.equal(this.deviceContactId, contactEntry.deviceContactId) && Objects.equal(this.name, contactEntry.name) && Objects.equal(this.fullContactName, contactEntry.fullContactName) && Objects.equal(this.sourceDeviceId, contactEntry.sourceDeviceId) && Objects.equal(Boolean.valueOf(this.isBlocked), Boolean.valueOf(contactEntry.isBlocked)) && Objects.equal(this.company, contactEntry.company) && Objects.equal(Boolean.valueOf(this.isNameEmpty), Boolean.valueOf(contactEntry.isNameEmpty)) && Objects.equal(Boolean.valueOf(this.canIDropInOnHim), Boolean.valueOf(contactEntry.canIDropInOnHim)) && Objects.equal(this.dropInState, contactEntry.dropInState) && Objects.equal(Boolean.valueOf(this.isBulkImport), Boolean.valueOf(contactEntry.isBulkImport)) && Objects.equal(Boolean.valueOf(this.shouldDisplay), Boolean.valueOf(contactEntry.shouldDisplay)) && Objects.equal(this.ownerCommsId, contactEntry.ownerCommsId) && Objects.equal(Boolean.valueOf(this.isEverRefreshed), Boolean.valueOf(contactEntry.isEverRefreshed)) && Objects.equal(Boolean.valueOf(this.isChild), Boolean.valueOf(contactEntry.isChild)) && Objects.equal(this.relationship, contactEntry.relationship) && Objects.equal(this.contactSourceType, contactEntry.contactSourceType) && Objects.equal(Boolean.valueOf(this.isMerged), Boolean.valueOf(contactEntry.isMerged)) && Objects.equal(Boolean.valueOf(this.shouldMerge), Boolean.valueOf(contactEntry.shouldMerge));
    }

    public boolean getCanIDropInOnHim() {
        return this.canIDropInOnHim;
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

    public DropInState getDropInState() {
        return this.dropInState;
    }

    public FullContactName getFullContactName() {
        return this.fullContactName;
    }

    public String getId() {
        return this.id;
    }

    public boolean getIsNameEmpty() {
        return this.isNameEmpty;
    }

    public String getOwnerCommsId() {
        return this.ownerCommsId;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public String getSourceDeviceId() {
        return this.sourceDeviceId;
    }

    public int hashCode() {
        return Objects.hashCode(this.id, Boolean.valueOf(this.alexaEnabled), this.deviceContactId, this.name, this.sourceDeviceId, Boolean.valueOf(this.isBlocked), this.company, Boolean.valueOf(this.isNameEmpty), Boolean.valueOf(this.canIDropInOnHim), this.dropInState, Boolean.valueOf(this.isBulkImport), Boolean.valueOf(this.shouldDisplay), this.ownerCommsId, Boolean.valueOf(this.isEverRefreshed), Boolean.valueOf(this.isChild), this.relationship, this.contactSourceType, Boolean.valueOf(this.isMerged), Boolean.valueOf(this.shouldMerge));
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

    public boolean isMerged() {
        return this.isMerged;
    }

    public boolean isShouldMerge() {
        return this.shouldMerge;
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

    public void setCanIDropInOnHim(boolean z) {
        this.canIDropInOnHim = z;
    }

    public void setChild(boolean z) {
        this.isChild = z;
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

    public void setDropInState(DropInState dropInState) {
        this.dropInState = dropInState;
    }

    public void setEverRefreshed(boolean z) {
        this.isEverRefreshed = z;
    }

    public void setFullContactName(FullContactName fullContactName) {
        this.fullContactName = fullContactName;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setIsMerged(boolean z) {
        this.isMerged = z;
    }

    public void setIsNameEmpty(boolean z) {
        this.isNameEmpty = z;
    }

    public void setOwnerCommsId(String str) {
        this.ownerCommsId = str;
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

    public void toContact(Contact contact) {
        contact.setSourceDeviceId(getSourceDeviceId());
        contact.setAlexaEnabled(isAlexaEnabled());
        contact.setContactName(getContactName());
        contact.setDeviceContactId(getDeviceContactId());
        contact.setId(getId());
        contact.setCompany(getCompany());
        contact.setBlocked(isBlocked());
        contact.setShouldDisplay(shouldDisplay());
        contact.setOwnerCommsId(getOwnerCommsId());
        contact.setBulkImport(isBulkImport());
        contact.setEverRefreshed(isEverRefreshed());
        contact.setIsChild(isChild());
        contact.setRelationship(getRelationship());
        contact.setContactSourceType(getContactSourceType());
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED, this.alexaEnabled).add(SettingsStorageModule.FILTER_SETTINGS_CONTACT_NAME_KEY, this.name).add("fullContactName", this.fullContactName).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID, this.sourceDeviceId).add("id", this.id).add("company", this.company).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID, this.deviceContactId).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED, this.isBlocked).add("dropInState", this.dropInState).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM, this.canIDropInOnHim).add("isBulkImport", this.isBulkImport).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_DISPLAY, this.shouldDisplay).add("ownerCommsId", this.ownerCommsId).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_EVER_REFRESHED, this.isEverRefreshed).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD, this.isChild).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP, this.relationship).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE, this.contactSourceType).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_MERGED, this.isMerged).add(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_MERGE, this.shouldMerge).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.alexaEnabled ? 1 : 0);
        parcel.writeParcelable(this.name, i);
        parcel.writeParcelable(this.fullContactName, i);
        parcel.writeString(this.sourceDeviceId);
        parcel.writeString(this.id);
        parcel.writeString(this.company);
        parcel.writeString(this.deviceContactId);
        parcel.writeByte(this.isNameEmpty ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.isBlocked ? 1 : 0);
        parcel.writeInt(this.isBulkImport ? 1 : 0);
        parcel.writeInt(this.shouldDisplay ? 1 : 0);
        parcel.writeString(this.ownerCommsId);
        parcel.writeByte((byte) (this.dropInState == DropInState.ON ? 1 : 0));
        parcel.writeByte(this.canIDropInOnHim ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.isEverRefreshed ? 1 : 0);
        parcel.writeInt(this.isChild ? 1 : 0);
        parcel.writeString(this.relationship);
        parcel.writeString(this.contactSourceType);
        parcel.writeInt(this.isMerged ? 1 : 0);
        parcel.writeInt(this.shouldMerge ? 1 : 0);
    }

    public ContactEntry(Contact contact) {
        this.name = contact.getContactName();
        this.fullContactName = new FullContactName(contact.getContactName(), contact.getCompany());
        this.sourceDeviceId = contact.getSourceDeviceId();
        this.alexaEnabled = contact.isAlexaEnabled();
        this.isBulkImport = contact.isBulkImport();
        this.id = contact.getId();
        this.company = contact.getCompany();
        this.deviceContactId = contact.getDeviceContactId();
        this.isBlocked = contact.isBlocked();
        this.shouldDisplay = contact.shouldDisplay();
        this.isBulkImport = contact.isBulkImport();
        this.ownerCommsId = contact.getOwnerCommsId();
        this.isEverRefreshed = contact.isEverRefreshed();
        this.isChild = contact.isChild();
        this.relationship = contact.getRelationship();
        this.contactSourceType = contact.getContactSourceType();
        this.isMerged = contact.isMerged();
        this.shouldMerge = contact.isShouldMerge();
    }

    public ContactEntry(Parcel parcel) {
        boolean z = true;
        this.alexaEnabled = parcel.readInt() != 0;
        this.name = (ContactName) parcel.readParcelable(ContactName.class.getClassLoader());
        this.fullContactName = (FullContactName) parcel.readParcelable(FullContactName.class.getClassLoader());
        this.sourceDeviceId = parcel.readString();
        this.id = parcel.readString();
        this.company = parcel.readString();
        this.deviceContactId = parcel.readString();
        this.isNameEmpty = parcel.readByte() != 0;
        this.isBlocked = parcel.readInt() != 0;
        this.isBulkImport = parcel.readInt() != 0;
        this.shouldDisplay = parcel.readInt() != 0;
        this.ownerCommsId = parcel.readString();
        this.dropInState = parcel.readByte() != 0 ? DropInState.ON : DropInState.OFF;
        this.canIDropInOnHim = parcel.readByte() != 0;
        this.isEverRefreshed = parcel.readInt() != 0;
        this.isChild = parcel.readInt() != 0;
        this.relationship = parcel.readString();
        this.contactSourceType = parcel.readString();
        this.isMerged = parcel.readInt() != 0;
        this.shouldMerge = parcel.readInt() == 0 ? false : z;
    }
}
