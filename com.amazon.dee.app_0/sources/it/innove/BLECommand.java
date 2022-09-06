package it.innove;

import java.util.UUID;
/* loaded from: classes3.dex */
class BLECommand {
    public static int READ = 10000;
    public static int REGISTER_NOTIFY = 10001;
    public static int REMOVE_NOTIFY = 10002;
    private UUID characteristicUUID;
    private byte[] data;
    private UUID serviceUUID;
    private int type;

    public BLECommand(UUID uuid, UUID uuid2, int i) {
        this.serviceUUID = uuid;
        this.characteristicUUID = uuid2;
        this.type = i;
    }

    public UUID getCharacteristicUUID() {
        return this.characteristicUUID;
    }

    public byte[] getData() {
        return this.data;
    }

    public UUID getServiceUUID() {
        return this.serviceUUID;
    }

    public int getType() {
        return this.type;
    }

    public BLECommand(UUID uuid, UUID uuid2, byte[] bArr, int i) {
        this.serviceUUID = uuid;
        this.characteristicUUID = uuid2;
        this.data = bArr;
        this.type = i;
    }
}
