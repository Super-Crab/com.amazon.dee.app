package com.amazon.alexa.accessory.internal.bluetooth.ble.packet;

import com.amazon.alexa.accessory.internal.util.IOUtils;
/* loaded from: classes.dex */
public final class VendorPacket extends BasePacket {
    private static final int LENGTH = 2;
    private final short vendorId;

    public VendorPacket(byte[] bArr, int i) {
        super(bArr, i, 2);
        this.vendorId = IOUtils.combineTwoBytesIntoShort(getData()[1], getData()[0]);
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && VendorPacket.class == obj.getClass() && this.vendorId == ((VendorPacket) obj).vendorId;
    }

    public short getVendorId() {
        return this.vendorId;
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
    public int hashCode() {
        return this.vendorId;
    }
}
