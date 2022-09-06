package com.amazon.alexa.presence.bleconn.identity.model;

import com.amazon.alexa.presence.bleconn.helpers.DateHelper;
import com.amazon.alexa.presence.bleconn.helpers.LoggingHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class BleIdentityPacket {
    private static final int IDENTITY_BEST_BY_HOURS = 72;
    private static final Logger LOG = LoggerFactory.getLogger(BleIdentityPacket.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final SavedData data;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class SavedData {
        public Date dateCreated;
        public BleIdentityCore packet;
        public String recordId;

        SavedData() {
        }

        SavedData(Date date, BleIdentityCore bleIdentityCore) {
            this.recordId = UUID.randomUUID().toString();
            this.dateCreated = date;
            this.packet = bleIdentityCore;
        }
    }

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public BleIdentityPacket() {
        this(new SavedData(new Date(), new BleIdentityCore()));
    }

    public static BleIdentityPacket deserialize(String str) {
        try {
            return new BleIdentityPacket((SavedData) MAPPER.readValue(str, SavedData.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Key> selectCurrentlyValidKeys(List<Key> list) {
        ArrayList arrayList = new ArrayList();
        for (Key key : list) {
            if (ValidTimePeriod.isCurrentlyValid(key.getValidTimePeriod())) {
                arrayList.add(key);
            }
        }
        return arrayList;
    }

    private Key selectMostCurrentlyValidKey(List<Key> list) {
        List<Key> selectCurrentlyValidKeys = selectCurrentlyValidKeys(list);
        if (selectCurrentlyValidKeys.size() <= 0) {
            return null;
        }
        Key key = selectCurrentlyValidKeys.get(0);
        for (Key key2 : selectCurrentlyValidKeys) {
            if (key.getValidTimePeriod().getEnd().compareTo(key2.getValidTimePeriod().getEnd()) < 0) {
                key = key2;
            }
        }
        return key;
    }

    public Date dateLoaded() {
        return this.data.dateCreated;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BleIdentityPacket)) {
            return false;
        }
        return this.data.recordId.equals(((BleIdentityPacket) obj).data.recordId);
    }

    public String getCurrentEncryptionKey() {
        if (isExpired()) {
            LOG.warn("Identity data not usable, expired");
            return null;
        }
        Key selectMostCurrentlyValidKey = selectMostCurrentlyValidKey(this.data.packet.getEncryptionKeys());
        if (selectMostCurrentlyValidKey != null) {
            return selectMostCurrentlyValidKey.getKey();
        }
        return null;
    }

    public String getCurrentToken() {
        for (IdentityToken identityToken : this.data.packet.getTokens()) {
            if (ValidTimePeriod.isCurrentlyValid(identityToken.getValidTimePeriod())) {
                return identityToken.getRollingProximityIdentifierToken();
            }
        }
        LOG.warn("Identity data not usable, expired");
        return null;
    }

    public List<Key> getRelationshipKeys() {
        return selectCurrentlyValidKeys(this.data.packet.getRelationshipVerificationKeys());
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public boolean isExpired() {
        return !isUsable();
    }

    public boolean isStale() {
        return DateHelper.addHoursTo(this.data.dateCreated, 72).compareTo(new Date()) < 0;
    }

    public boolean isUsable() {
        return (getCurrentToken() != null) && (selectCurrentlyValidKeys(this.data.packet.getEncryptionKeys()).size() > 0) && (selectCurrentlyValidKeys(this.data.packet.getRelationshipVerificationKeys()).size() > 0);
    }

    public String serialize() {
        try {
            return MAPPER.writeValueAsString(this.data);
        } catch (JsonProcessingException e) {
            LOG.warn("Failed to serialize data", (Throwable) e);
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return LoggingHelper.dump(this);
    }

    public BleIdentityPacket(BleIdentityCore bleIdentityCore) {
        this(new Date(), bleIdentityCore);
    }

    public BleIdentityPacket(Date date, BleIdentityCore bleIdentityCore) {
        this(new SavedData(date, bleIdentityCore));
    }

    private BleIdentityPacket(SavedData savedData) {
        this.data = savedData;
    }
}
