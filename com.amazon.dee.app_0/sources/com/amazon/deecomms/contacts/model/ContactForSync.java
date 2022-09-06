package com.amazon.deecomms.contacts.model;

import com.amazon.deecomms.contacts.model.enums.SyncOperationType;
/* loaded from: classes12.dex */
public class ContactForSync {
    private ContactUploadInfo contact;
    private SyncOperationType syncOperationType;

    public ContactForSync(ContactUploadInfo contactUploadInfo, SyncOperationType syncOperationType) {
        this.contact = contactUploadInfo;
        this.syncOperationType = syncOperationType;
    }

    public ContactUploadInfo getContact() {
        return this.contact;
    }

    public SyncOperationType getSyncOperationType() {
        return this.syncOperationType;
    }
}
