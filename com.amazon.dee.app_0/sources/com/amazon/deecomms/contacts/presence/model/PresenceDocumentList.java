package com.amazon.deecomms.contacts.presence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class PresenceDocumentList {
    @JsonProperty("contactsPresenceCount")
    private Integer documentCount;
    @JsonProperty("contactsPresences")
    private PresenceDocument[] documents;

    public Integer getDocumentCount() {
        return this.documentCount;
    }

    public PresenceDocument[] getDocuments() {
        return this.documents;
    }

    public void setDocumentCount(Integer num) {
        this.documentCount = num;
    }

    public void setDocuments(PresenceDocument[] presenceDocumentArr) {
        this.documents = presenceDocumentArr;
    }
}
