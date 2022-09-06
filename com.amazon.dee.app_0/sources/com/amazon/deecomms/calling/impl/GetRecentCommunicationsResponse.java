package com.amazon.deecomms.calling.impl;

import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes12.dex */
public class GetRecentCommunicationsResponse {
    public String addressId;
    public String addressValue;
    public List<ConnectedEntityEventData> connectedEntityEventData;
    public String contactId;
    public String dsn;
    public String entityType;

    /* loaded from: classes12.dex */
    public static class ConnectedEntityEventData {
        public String eventType;
        public long lastEventTimeStamp;

        public ConnectedEntityEventData() {
        }

        public String getEventType() {
            return this.eventType;
        }

        public long getLastEventTimeStamp() {
            return this.lastEventTimeStamp;
        }

        public void setEventType(@NonNull String str) {
            this.eventType = str;
        }

        public void setLastEventTimeStamp(long j) {
            this.lastEventTimeStamp = j;
        }

        public ConnectedEntityEventData(@NonNull String str, long j) {
            this.eventType = str;
            this.lastEventTimeStamp = j;
        }
    }

    public GetRecentCommunicationsResponse() {
    }

    public String getAddressId() {
        return this.addressId;
    }

    public String getAddressValue() {
        return this.addressValue;
    }

    public List<ConnectedEntityEventData> getConnectedEntityEventData() {
        return this.connectedEntityEventData;
    }

    public String getContactId() {
        return this.contactId;
    }

    public String getDsn() {
        return this.dsn;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public void setAddressId(@NonNull String str) {
        this.addressId = str;
    }

    public void setAddressValue(@NonNull String str) {
        this.addressValue = str;
    }

    public void setConnectedEntityEventData(@NonNull List<ConnectedEntityEventData> list) {
        this.connectedEntityEventData = list;
    }

    public void setContactId(@NonNull String str) {
        this.contactId = str;
    }

    public void setDsn(@NonNull String str) {
        this.dsn = str;
    }

    public void setEntityType(@NonNull String str) {
        this.entityType = str;
    }

    public GetRecentCommunicationsResponse(@NonNull String str, @NonNull List<ConnectedEntityEventData> list, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        this.entityType = str;
        this.connectedEntityEventData = list;
        this.contactId = str2;
        this.addressId = str3;
        this.addressValue = str4;
        this.dsn = str5;
    }
}
