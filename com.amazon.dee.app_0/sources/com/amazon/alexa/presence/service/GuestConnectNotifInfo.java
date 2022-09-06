package com.amazon.alexa.presence.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Date;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class GuestConnectNotifInfo {
    private static final Logger LOG = LoggerFactory.getLogger(GuestConnectNotifInfo.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final SavedData data;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class SavedData {
        public long dateLastNotified;
        public boolean hasOpened;
        public int numSent;
        public long openOrDismissDate;
        public String recordId;

        SavedData() {
        }

        SavedData(int i, long j, long j2, boolean z) {
            this.numSent = i;
            this.dateLastNotified = j;
            this.recordId = UUID.randomUUID().toString();
            this.hasOpened = z;
            this.openOrDismissDate = j2;
        }
    }

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public GuestConnectNotifInfo() {
        this(new SavedData(0, new Date().getTime(), new Date().getTime(), false));
    }

    public static GuestConnectNotifInfo deserialize(String str) {
        try {
            return new GuestConnectNotifInfo((SavedData) MAPPER.readValue(str, SavedData.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public long getDateLastNotified() {
        return this.data.dateLastNotified;
    }

    public String getID() {
        return this.data.recordId;
    }

    public int getNumSent() {
        return this.data.numSent;
    }

    public long getOpenOrDismissDate() {
        return this.data.openOrDismissDate;
    }

    public boolean hasOpened() {
        return this.data.hasOpened;
    }

    public String serialize() {
        try {
            return MAPPER.writeValueAsString(this.data);
        } catch (JsonProcessingException e) {
            LOG.warn("Failed to serialize data", (Throwable) e);
            throw new RuntimeException(e);
        }
    }

    public GuestConnectNotifInfo(int i, long j, long j2, boolean z) {
        this(new SavedData(i, j, j2, z));
    }

    public GuestConnectNotifInfo(SavedData savedData) {
        this.data = savedData;
    }
}
