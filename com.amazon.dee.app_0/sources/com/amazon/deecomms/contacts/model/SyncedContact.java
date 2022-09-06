package com.amazon.deecomms.contacts.model;

import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.enums.SyncErrorCode;
import com.amazon.deecomms.contacts.model.enums.SyncOperationType;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class SyncedContact {
    @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID)
    private String deviceContactId;
    @JsonProperty("syncErrorCode")
    private SyncErrorCode syncErrorCode;
    @JsonProperty("syncOperationType")
    private SyncOperationType syncOperationType;

    public String getDeviceContactId() {
        return this.deviceContactId;
    }

    public SyncErrorCode getSyncErrorCode() {
        return this.syncErrorCode;
    }

    public SyncOperationType getSyncOperationType() {
        return this.syncOperationType;
    }

    public void setDeviceContactId(String str) {
        this.deviceContactId = str;
    }

    public void setSyncErrorCode(SyncErrorCode syncErrorCode) {
        this.syncErrorCode = syncErrorCode;
    }

    public void setSyncOperationType(SyncOperationType syncOperationType) {
        this.syncOperationType = syncOperationType;
    }
}
