package com.amazon.deecomms.contacts.model.api;

import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.ContactForSync;
import com.amazon.deecomms.contacts.model.SyncedContact;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes12.dex */
public class SyncContacts {

    /* loaded from: classes12.dex */
    public static class Request {
        @JsonProperty("contactsForSyncList")
        private List<ContactForSync> contactsForSyncList;
        @JsonProperty(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID)
        private String sourceDeviceId;

        public Request(String str, List<ContactForSync> list) {
            this.sourceDeviceId = str;
            this.contactsForSyncList = list;
        }
    }

    /* loaded from: classes12.dex */
    public static class Response {
        @JsonProperty("failureList")
        private List<SyncedContact> failureList;
        @JsonProperty("successList")
        private List<SyncedContact> successList;

        public List<SyncedContact> getFailureList() {
            return this.failureList;
        }

        public List<SyncedContact> getSuccessList() {
            return this.successList;
        }

        public void setFailureList(List<SyncedContact> list) {
            this.failureList = list;
        }

        public void setSuccessList(List<SyncedContact> list) {
            this.successList = list;
        }
    }
}
