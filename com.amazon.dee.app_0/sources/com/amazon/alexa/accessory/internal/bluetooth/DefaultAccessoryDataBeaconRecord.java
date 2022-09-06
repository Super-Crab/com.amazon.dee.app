package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.AccessoryDataBeaconRecord;
import com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket;
import com.amazon.alexa.accessory.internal.bluetooth.ble.packet.VendorPacket;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public class DefaultAccessoryDataBeaconRecord implements AccessoryDataBeaconRecord {
    private static final int MAXIMUM_LENGTH = 25;
    private static final int MINIMUM_LENGTH = 3;
    private static final int START_OFFSET = 0;
    private final short applicationIdShort;
    private final byte[] applicationSpecificDataByteArray;
    private final short vendorIdShort;

    /* loaded from: classes.dex */
    private static final class ApplicationPacket extends BasePacket {
        private static final int LENGTH = 1;
        private final short applicationId;

        ApplicationPacket(byte[] bArr, int i) {
            super(bArr, i, 1);
            this.applicationId = getData()[0];
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && ApplicationPacket.class == obj.getClass() && this.applicationId == ((ApplicationPacket) obj).applicationId;
        }

        public short getApplicationId() {
            return this.applicationId;
        }

        @Override // com.amazon.alexa.accessory.internal.bluetooth.ble.packet.BasePacket
        public int hashCode() {
            return this.applicationId;
        }
    }

    /* loaded from: classes.dex */
    private static final class ApplicationSpecificDataPacket extends BasePacket {
        ApplicationSpecificDataPacket(byte[] bArr, int i, int i2) {
            super(bArr, i, i2);
        }
    }

    public DefaultAccessoryDataBeaconRecord(byte[] bArr, short s, short s2) {
        this.vendorIdShort = s;
        this.applicationIdShort = s2;
        this.applicationSpecificDataByteArray = bArr;
    }

    public static AccessoryDataBeaconRecord createFrom(BleAdvertisementData bleAdvertisementData) throws IllegalArgumentException {
        byte[] dataForADType = bleAdvertisementData.getDataForADType((byte) -1);
        if (dataForADType != null) {
            if (dataForADType.length >= 3 && 25 >= dataForADType.length) {
                return new DefaultAccessoryDataBeaconRecord(dataForADType);
            }
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid manufacturer specific data length from beacon. Expected to be in range:[%d-%d] Actual: %d", 3, 25, Integer.valueOf(dataForADType.length)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Invalid advertisement from beacon: %s", bleAdvertisementData));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DefaultAccessoryDataBeaconRecord.class != obj.getClass()) {
            return false;
        }
        DefaultAccessoryDataBeaconRecord defaultAccessoryDataBeaconRecord = (DefaultAccessoryDataBeaconRecord) obj;
        return this.vendorIdShort == defaultAccessoryDataBeaconRecord.vendorIdShort && this.applicationIdShort == defaultAccessoryDataBeaconRecord.applicationIdShort && Arrays.equals(this.applicationSpecificDataByteArray, defaultAccessoryDataBeaconRecord.applicationSpecificDataByteArray);
    }

    @Override // com.amazon.alexa.accessory.AccessoryDataBeaconRecord
    public short getApplicationId() {
        return this.applicationIdShort;
    }

    @Override // com.amazon.alexa.accessory.AccessoryDataBeaconRecord
    public byte[] getApplicationSpecificData() {
        return this.applicationSpecificDataByteArray;
    }

    @Override // com.amazon.alexa.accessory.AccessoryDataBeaconRecord
    public short getVendorId() {
        return this.vendorIdShort;
    }

    public int hashCode() {
        return Arrays.hashCode(this.applicationSpecificDataByteArray) + (Objects.hash(Short.valueOf(this.vendorIdShort), Short.valueOf(this.applicationIdShort)) * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DefaultAccessoryDataBeaconRecord{applicationSpecificDataByteArray=");
        outline107.append(Arrays.toString(this.applicationSpecificDataByteArray));
        outline107.append(", vendorIdShort=");
        outline107.append((int) this.vendorIdShort);
        outline107.append(", applicationIdShort=");
        return GeneratedOutlineSupport1.outline85(outline107, this.applicationIdShort, JsonReaderKt.END_OBJ);
    }

    private DefaultAccessoryDataBeaconRecord(byte[] bArr) {
        VendorPacket vendorPacket = new VendorPacket(bArr, 0);
        int packetLength = vendorPacket.getPacketLength() + 0;
        ApplicationPacket applicationPacket = new ApplicationPacket(bArr, packetLength);
        int i = packetLength + 1;
        ApplicationSpecificDataPacket applicationSpecificDataPacket = new ApplicationSpecificDataPacket(bArr, i, bArr.length - i);
        this.vendorIdShort = vendorPacket.getVendorId();
        this.applicationIdShort = applicationPacket.getApplicationId();
        this.applicationSpecificDataByteArray = applicationSpecificDataPacket.getData();
    }
}
