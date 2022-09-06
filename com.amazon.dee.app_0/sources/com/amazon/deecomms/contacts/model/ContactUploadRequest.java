package com.amazon.deecomms.contacts.model;

import java.util.List;
/* loaded from: classes12.dex */
public class ContactUploadRequest {
    private List<ContactUploadInfo> contacts;
    private String sourceDeviceId;

    public List<ContactUploadInfo> getContacts() {
        return this.contacts;
    }

    public String getSourceDeviceId() {
        return this.sourceDeviceId;
    }

    public void setContacts(List<ContactUploadInfo> list) {
        this.contacts = list;
    }

    public void setSourceDeviceId(String str) {
        this.sourceDeviceId = str;
    }
}
