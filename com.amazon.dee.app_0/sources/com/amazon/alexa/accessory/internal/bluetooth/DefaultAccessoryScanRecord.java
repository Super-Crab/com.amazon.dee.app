package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.AccessoryScanRecord;
import com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket;
import com.amazon.alexa.accessory.internal.bluetooth.ble.packet.VendorPacket;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public final class DefaultAccessoryScanRecord implements AccessoryScanRecord {
    private static final int MAXIMUM_LENGTH = 22;
    private static final int MINIMUM_LENGTH = 12;
    private static final int RESERVED_BYTES_LENGTH = 3;
    private static final int START_OFFSET = 2;
    private final byte colorByte;
    private final boolean hasAnUpdateAvailableBoolean;
    private final boolean isDiscoverableOverBTClassicBoolean;
    private final boolean isInOOBEModeBoolean;
    private final boolean prefersRFCOMMConnectionBoolean;
    private final short productIdShort;
    private final byte[] productSpecificDataByteArray;
    private final short vendorIdShort;

    /* loaded from: classes.dex */
    private static final class ColorPacket extends BasePacket {
        private static final int LENGTH = 1;
        private final byte color;

        ColorPacket(byte[] bArr, int i) {
            super(bArr, i, 1);
            this.color = getData()[0];
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && ColorPacket.class == obj.getClass() && this.color == ((ColorPacket) obj).color;
        }

        public byte getColor() {
            return this.color;
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
        public int hashCode() {
            return this.color;
        }
    }

    /* loaded from: classes.dex */
    private static final class ConnectionPreferencePacket extends BasePacket {
        private static final int LENGTH = 1;
        private static final int PREFERS_RFCOMM_CONNECTION_OFFSET = 0;
        private final boolean prefersRFCOMMConnection;

        ConnectionPreferencePacket(byte[] bArr, int i) {
            super(bArr, i, 1);
            this.prefersRFCOMMConnection = IOUtils.isKthBitSet(0, getData()[0]);
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && ConnectionPreferencePacket.class == obj.getClass() && this.prefersRFCOMMConnection == ((ConnectionPreferencePacket) obj).prefersRFCOMMConnection;
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
        public int hashCode() {
            return this.prefersRFCOMMConnection ? 1 : 0;
        }

        boolean prefersRFCOMMConnection() {
            return this.prefersRFCOMMConnection;
        }
    }

    /* loaded from: classes.dex */
    private static final class DeviceStatePacket extends BasePacket {
        static final int HAS_AN_UPDATE_AVAILABLE_OFFSET = 2;
        static final int IN_OOBE_MODE_OFFSET = 0;
        static final int IS_DISCOVERABLE_OVER_BTCLASSIC_OFFSET = 1;
        static final int LENGTH = 1;
        private final boolean hasAnUpdateAvailable;
        private final boolean isDiscoverableOverBTClassic;
        private final boolean isInOOBEMode;

        DeviceStatePacket(byte[] bArr, int i) {
            super(bArr, i, 1);
            this.isInOOBEMode = IOUtils.isKthBitSet(0, getData()[0]);
            this.isDiscoverableOverBTClassic = IOUtils.isKthBitSet(1, getData()[0]);
            this.hasAnUpdateAvailable = IOUtils.isKthBitSet(2, getData()[0]);
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || DeviceStatePacket.class != obj.getClass()) {
                return false;
            }
            DeviceStatePacket deviceStatePacket = (DeviceStatePacket) obj;
            return this.isInOOBEMode == deviceStatePacket.isInOOBEMode && this.isDiscoverableOverBTClassic == deviceStatePacket.isDiscoverableOverBTClassic;
        }

        boolean hasAnUpdateAvailable() {
            return this.hasAnUpdateAvailable;
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
        public int hashCode() {
            return ((((this.isInOOBEMode ? 1 : 0) * 31) + (this.isDiscoverableOverBTClassic ? 1 : 0)) * 31) + (this.hasAnUpdateAvailable ? 1 : 0);
        }

        boolean isDiscoverableOverBTClassic() {
            return this.isDiscoverableOverBTClassic;
        }

        boolean isInOOBEMode() {
            return this.isInOOBEMode;
        }
    }

    /* loaded from: classes.dex */
    private static final class ProductPacket extends BasePacket {
        private static final int LENGTH = 2;
        private final short productId;

        ProductPacket(byte[] bArr, int i) {
            super(bArr, i, 2);
            this.productId = IOUtils.combineTwoBytesIntoShort(getData()[1], getData()[0]);
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && ProductPacket.class == obj.getClass() && this.productId == ((ProductPacket) obj).productId;
        }

        public short getProductId() {
            return this.productId;
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
        public int hashCode() {
            return this.productId;
        }
    }

    /* loaded from: classes.dex */
    private static final class ProductSpecificDataPacket extends BasePacket {
        ProductSpecificDataPacket(byte[] bArr, int i, int i2) {
            super(bArr, i, i2);
        }
    }

    public DefaultAccessoryScanRecord(byte[] bArr, short s, short s2, byte b, boolean z, boolean z2, boolean z3, boolean z4) {
        this.productSpecificDataByteArray = bArr;
        this.vendorIdShort = s;
        this.productIdShort = s2;
        this.colorByte = b;
        this.isInOOBEModeBoolean = z;
        this.isDiscoverableOverBTClassicBoolean = z2;
        this.prefersRFCOMMConnectionBoolean = z3;
        this.hasAnUpdateAvailableBoolean = z4;
    }

    public static AccessoryScanRecord createFrom(BleAdvertisementData bleAdvertisementData) throws IllegalArgumentException {
        byte[] dataForADType = bleAdvertisementData.getDataForADType((byte) 22);
        if (dataForADType != null) {
            if (dataForADType.length >= 12 && 22 >= dataForADType.length) {
                return new DefaultAccessoryScanRecord(dataForADType);
            }
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid service data length. Expected to be in range: [%d-%d] Actual: %d", 12, 22, Integer.valueOf(dataForADType.length)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Invalid advertisement: %s", bleAdvertisementData));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DefaultAccessoryScanRecord.class != obj.getClass()) {
            return false;
        }
        DefaultAccessoryScanRecord defaultAccessoryScanRecord = (DefaultAccessoryScanRecord) obj;
        return this.vendorIdShort == defaultAccessoryScanRecord.vendorIdShort && this.productIdShort == defaultAccessoryScanRecord.productIdShort && this.colorByte == defaultAccessoryScanRecord.colorByte && this.isDiscoverableOverBTClassicBoolean == defaultAccessoryScanRecord.isDiscoverableOverBTClassicBoolean && this.prefersRFCOMMConnectionBoolean == defaultAccessoryScanRecord.prefersRFCOMMConnectionBoolean && this.isInOOBEModeBoolean == defaultAccessoryScanRecord.isInOOBEModeBoolean && this.hasAnUpdateAvailableBoolean == defaultAccessoryScanRecord.hasAnUpdateAvailableBoolean && Arrays.equals(this.productSpecificDataByteArray, defaultAccessoryScanRecord.productSpecificDataByteArray);
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanRecord
    public byte getColor() {
        return this.colorByte;
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanRecord
    public short getProductId() {
        return this.productIdShort;
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanRecord
    public byte[] getProductSpecificData() {
        return this.productSpecificDataByteArray;
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanRecord
    public short getVendorId() {
        return this.vendorIdShort;
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanRecord
    public boolean hasAnUpdateAvailable() {
        return this.hasAnUpdateAvailableBoolean;
    }

    public int hashCode() {
        return Arrays.hashCode(this.productSpecificDataByteArray) + (Objects.hash(Short.valueOf(this.vendorIdShort), Short.valueOf(this.productIdShort), Byte.valueOf(this.colorByte), Boolean.valueOf(this.isDiscoverableOverBTClassicBoolean), Boolean.valueOf(this.prefersRFCOMMConnectionBoolean), Boolean.valueOf(this.isInOOBEModeBoolean), Boolean.valueOf(this.hasAnUpdateAvailableBoolean)) * 31);
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanRecord
    public boolean isDiscoverableOverBTClassic() {
        return this.isDiscoverableOverBTClassicBoolean;
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanRecord
    public boolean isInOOBEMode() {
        return this.isInOOBEModeBoolean;
    }

    @Override // com.amazon.alexa.accessory.AccessoryScanRecord
    public boolean prefersRFCOMMConnection() {
        return this.prefersRFCOMMConnectionBoolean;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DefaultAccessoryScanRecord{productSpecificDataByteArray=");
        outline107.append(Arrays.toString(this.productSpecificDataByteArray));
        outline107.append(", vendorIdShort=");
        outline107.append((int) this.vendorIdShort);
        outline107.append(", productIdShort=");
        outline107.append((int) this.productIdShort);
        outline107.append(", colorByte=");
        outline107.append((int) this.colorByte);
        outline107.append(", isDiscoverableOverBTClassicBoolean=");
        outline107.append(this.isDiscoverableOverBTClassicBoolean);
        outline107.append(", prefersRFCOMMConnectionBoolean=");
        outline107.append(this.prefersRFCOMMConnectionBoolean);
        outline107.append(", isInOOBEModeBoolean=");
        outline107.append(this.isInOOBEModeBoolean);
        outline107.append(", hasAnUpdateAvailableBoolean=");
        outline107.append(this.hasAnUpdateAvailableBoolean);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    private DefaultAccessoryScanRecord(byte[] bArr) {
        VendorPacket vendorPacket = new VendorPacket(bArr, 2);
        int packetLength = vendorPacket.getPacketLength() + 2;
        ProductPacket productPacket = new ProductPacket(bArr, packetLength);
        int i = packetLength + 2;
        ColorPacket colorPacket = new ColorPacket(bArr, i);
        int i2 = i + 1;
        DeviceStatePacket deviceStatePacket = new DeviceStatePacket(bArr, i2);
        int i3 = i2 + 1;
        ConnectionPreferencePacket connectionPreferencePacket = new ConnectionPreferencePacket(bArr, i3);
        int i4 = i3 + 4;
        this.productSpecificDataByteArray = new ProductSpecificDataPacket(bArr, i4, bArr.length - i4).getData();
        this.vendorIdShort = vendorPacket.getVendorId();
        this.productIdShort = productPacket.getProductId();
        this.colorByte = colorPacket.getColor();
        this.isInOOBEModeBoolean = deviceStatePacket.isInOOBEMode();
        this.isDiscoverableOverBTClassicBoolean = deviceStatePacket.isDiscoverableOverBTClassic();
        this.prefersRFCOMMConnectionBoolean = connectionPreferencePacket.prefersRFCOMMConnection();
        this.hasAnUpdateAvailableBoolean = deviceStatePacket.hasAnUpdateAvailable();
    }
}
