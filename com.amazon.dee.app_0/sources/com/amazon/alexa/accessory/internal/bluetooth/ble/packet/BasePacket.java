package com.amazon.alexa.accessory.internal.bluetooth.ble.packet;

import com.amazon.alexa.accessory.internal.util.ByteUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Arrays;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public abstract class BasePacket {
    private byte[] data;
    private int packetLength;

    /* JADX INFO: Access modifiers changed from: protected */
    public BasePacket(byte[] bArr, int i, int i2) {
        Preconditions.notNull(bArr, "packetData");
        Preconditions.notNegative(i, "offset");
        Preconditions.notNegative(i2, "packetLength");
        this.data = new byte[i2];
        System.arraycopy(bArr, i, this.data, 0, i2);
        this.packetLength = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return Arrays.equals(this.data, ((BasePacket) obj).data);
        }
        return false;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getPacketLength() {
        return this.packetLength;
    }

    public int hashCode() {
        return Arrays.hashCode(this.data);
    }

    public String toString() {
        return getClass().getSimpleName() + "{data=[" + ByteUtils.bytesToHex(this.data) + "]" + JsonReaderKt.END_OBJ;
    }
}
