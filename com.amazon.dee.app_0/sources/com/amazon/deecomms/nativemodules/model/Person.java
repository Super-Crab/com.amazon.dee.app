package com.amazon.deecomms.nativemodules.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.ContactAddress;
import com.amazon.deecomms.contacts.model.ContactEmailAddress;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.IdentityRawData;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public final class Person {
    private String aor;
    private boolean canBeDroppedIn;
    private boolean canDropIn;
    private String companyName;
    private String contactSourceType;
    private String deviceContactId;
    private String homeGroupCommsId;
    private String id;
    private boolean isAlexaEnabled;
    private boolean isBlocked;
    private boolean isBulkImport;
    private boolean isChild;
    private boolean isLinked;
    private boolean isMerged;
    private ContactName name;
    private String ownerCommsId;
    private String referenceCommsId;
    private String referenceContactId;
    private String relationship;
    private boolean shouldDisplay;
    private boolean shouldMerge;
    private String sourceDeviceId;
    @NonNull
    private List<String> commsIds = new ArrayList();
    @NonNull
    private List<ContactPhoneNumber> phoneNumbers = new ArrayList();
    @NonNull
    private List<ContactEmailAddress> emails = new ArrayList();
    @NonNull
    private List<ContactAddress> addresses = new ArrayList();
    @NonNull
    private List<String> mergeContactIdentifiers = new ArrayList();

    public Person() {
    }

    @NonNull
    public List<ContactAddress> getAddresses() {
        return this.addresses;
    }

    public String getAor() {
        return this.aor;
    }

    public boolean getCanBeDroppedIn() {
        return this.canBeDroppedIn;
    }

    public boolean getCanDropIn() {
        return this.canDropIn;
    }

    @NonNull
    public List<String> getCommsIds() {
        return this.commsIds;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getContactSourceType() {
        return this.contactSourceType;
    }

    public String getDeviceContactId() {
        return this.deviceContactId;
    }

    @NonNull
    public List<ContactEmailAddress> getEmails() {
        return this.emails;
    }

    public String getHomeGroupCommsId() {
        return this.homeGroupCommsId;
    }

    public String getId() {
        return this.id;
    }

    @NonNull
    public List<String> getMergeContactIdentifiers() {
        return this.mergeContactIdentifiers;
    }

    public ContactName getName() {
        return this.name;
    }

    public String getOwnerCommsId() {
        return this.ownerCommsId;
    }

    @NonNull
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

    public boolean isAlexaEnabled() {
        return this.isAlexaEnabled;
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

    public boolean isLinked() {
        return this.isLinked;
    }

    public boolean isMerged() {
        return this.isMerged;
    }

    public void setAddresses(@NonNull List<ContactAddress> list) {
        this.addresses = list;
    }

    public void setAlexaEnabled(boolean z) {
        this.isAlexaEnabled = z;
    }

    public void setAor(String str) {
        this.aor = str;
    }

    public void setBlocked(boolean z) {
        this.isBlocked = z;
    }

    public void setBulkImport(boolean z) {
        this.isBulkImport = z;
    }

    public void setCanBeDroppedIn(boolean z) {
        this.canBeDroppedIn = z;
    }

    public void setCanDropIn(boolean z) {
        this.canDropIn = z;
    }

    public void setChild(boolean z) {
        this.isChild = z;
    }

    public void setCommsIds(@NonNull List<String> list) {
        this.commsIds = list;
    }

    public void setCompanyName(String str) {
        this.companyName = str;
    }

    public void setContactSourceType(String str) {
        this.contactSourceType = str;
    }

    public void setDeviceContactId(String str) {
        this.deviceContactId = str;
    }

    public void setEmails(@NonNull List<ContactEmailAddress> list) {
        this.emails = list;
    }

    public void setHomeGroupCommsId(String str) {
        this.homeGroupCommsId = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setIsMerged(boolean z) {
        this.isMerged = z;
    }

    public void setLinked(boolean z) {
        this.isLinked = z;
    }

    public void setMergeContactIdentifiers(@NonNull List<String> list) {
        this.mergeContactIdentifiers = list;
    }

    public void setName(ContactName contactName) {
        this.name = contactName;
    }

    public void setOwnerCommsId(String str) {
        this.ownerCommsId = str;
    }

    public void setPhoneNumbers(@NonNull List<ContactPhoneNumber> list) {
        this.phoneNumbers = list;
    }

    public void setReferenceCommsId(@Nullable String str) {
        this.referenceCommsId = str;
    }

    public void setReferenceContactId(@Nullable String str) {
        this.referenceContactId = str;
    }

    public void setRelationship(@Nullable String str) {
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

    public boolean shouldMerge() {
        return this.shouldMerge;
    }

    public Person(@NonNull Contact contact) {
        setId(contact.getId());
        List<String> commsIds = contact.getCommsIds();
        if (commsIds != null && commsIds.size() > 0) {
            setCommsIds(commsIds);
        }
        List<IdentityRawData> commsIdentities = contact.getCommsIdentities();
        boolean z = false;
        if (commsIdentities != null && commsIdentities.size() > 0) {
            setAor(contact.getCommsIdentities().get(0).getAor());
        }
        setSourceDeviceId(contact.getSourceDeviceId());
        setDeviceContactId(contact.getDeviceContactId());
        setOwnerCommsId(contact.getOwnerCommsId());
        if (contact.getHomeGroupIdentity() != null) {
            setHomeGroupCommsId(contact.getHomeGroupIdentity().getId());
        }
        setBlocked(contact.isBlocked());
        setAlexaEnabled(contact.isAlexaEnabled());
        setBulkImport(contact.isBulkImport());
        setCanDropIn(contact.canDropInOnMe() == 1);
        setCanBeDroppedIn(contact.canIDropInOnHim() == 1 ? true : z);
        setCompanyName(contact.getCompany());
        if (contact.getContactName() != null) {
            setName(contact.getContactName());
        }
        if (contact.getPhoneNumbers() != null) {
            setPhoneNumbers(contact.getPhoneNumbers());
        }
        if (contact.getEmails() != null) {
            setEmails(contact.getEmails());
        }
        if (contact.getAddresses() != null) {
            setAddresses(contact.getAddresses());
        }
        setChild(contact.isChild());
        setRelationship(contact.getRelationship());
        setReferenceContactId(contact.getReferenceContactId());
        setReferenceCommsId(contact.getReferenceCommsId());
        setLinked(contact.isLinked());
        setContactSourceType(contact.getContactSourceType());
        setIsMerged(contact.isMerged());
        setShouldMerge(contact.isShouldMerge());
        setMergeContactIdentifiers(contact.getMergedContactIdentifiers());
    }
}
