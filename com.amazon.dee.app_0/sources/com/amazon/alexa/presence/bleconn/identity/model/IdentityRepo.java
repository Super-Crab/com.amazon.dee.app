package com.amazon.alexa.presence.bleconn.identity.model;

import java.util.Map;
/* loaded from: classes9.dex */
public class IdentityRepo {
    private static final String IDENTITY_DATA_ID = "IdentityDataSet";
    private final Map<String, String> storage;

    public IdentityRepo(Map<String, String> map) {
        this.storage = map;
    }

    public BleIdentityPacket loadWithV2Identity(BleIdentityCore bleIdentityCore) {
        BleIdentityPacket bleIdentityPacket = new BleIdentityPacket(bleIdentityCore);
        this.storage.put(IDENTITY_DATA_ID, bleIdentityPacket.serialize());
        return bleIdentityPacket;
    }

    public void resetIdentityData() {
        this.storage.remove(IDENTITY_DATA_ID);
    }

    public BleIdentityPacket retrieveIdentityData() {
        String str = this.storage.get(IDENTITY_DATA_ID);
        if (str == null) {
            return new BleIdentityPacket();
        }
        return BleIdentityPacket.deserialize(str);
    }

    public String retrieveRawData() {
        BleIdentityPacket retrieveIdentityData = retrieveIdentityData();
        if (retrieveIdentityData == null) {
            return null;
        }
        return retrieveIdentityData.serialize();
    }

    public void store(BleIdentityPacket bleIdentityPacket) {
        this.storage.put(IDENTITY_DATA_ID, bleIdentityPacket.serialize());
    }
}
