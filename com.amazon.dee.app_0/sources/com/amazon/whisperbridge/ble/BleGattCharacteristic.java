package com.amazon.whisperbridge.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.amazon.whisperbridge.ble.BleGattDescriptor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes13.dex */
public class BleGattCharacteristic {
    protected static final int MAX_STORED_DATA_SIZE = 512;
    protected static final String TAG = "BleGattCharacteristic";
    protected final Map<UUID, BleGattDescriptor> mBleDescriptors = new HashMap();
    protected final BluetoothGattCharacteristic mBluetoothGattCharacteristic;
    protected final EnumSet<Properties> mProperties;
    protected final UUID mUuid;

    /* loaded from: classes13.dex */
    public enum Properties {
        READ,
        WRITE,
        NOTIFY,
        INDICATE
    }

    protected BleGattCharacteristic(UUID uuid, EnumSet<Properties> enumSet) {
        this.mUuid = uuid;
        this.mProperties = enumSet;
        int i = 16;
        this.mBluetoothGattCharacteristic = new BluetoothGattCharacteristic(this.mUuid, (enumSet.contains(Properties.READ) ? 2 : 0) | 0 | (enumSet.contains(Properties.WRITE) ? 8 : 0) | (enumSet.contains(Properties.NOTIFY) ? 16 : 0) | (enumSet.contains(Properties.INDICATE) ? 32 : 0), (enumSet.contains(Properties.READ) ? 1 : 0) | 0 | (!enumSet.contains(Properties.WRITE) ? 0 : i));
        if (this.mProperties.contains(Properties.NOTIFY) || this.mProperties.contains(Properties.INDICATE)) {
            byte[] bArr = {0, 0};
            if (this.mProperties.contains(Properties.NOTIFY)) {
                byte b = bArr[0];
                byte[] bArr2 = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE;
                bArr[0] = (byte) (b | bArr2[0]);
                bArr[1] = (byte) (bArr[1] | bArr2[1]);
            }
            if (this.mProperties.contains(Properties.INDICATE)) {
                byte b2 = bArr[0];
                byte[] bArr3 = BluetoothGattDescriptor.ENABLE_INDICATION_VALUE;
                bArr[0] = (byte) (b2 | bArr3[0]);
                bArr[1] = (byte) (bArr[1] | bArr3[1]);
            }
            BleGattDescriptor createDescriptor = BleGattDescriptor.createDescriptor(BleConstants.CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR_UUID, EnumSet.of(BleGattDescriptor.Properties.READ, BleGattDescriptor.Properties.WRITE));
            createDescriptor.setStoredData(bArr);
            addBleDescriptor(createDescriptor);
        }
    }

    public static BleGattCharacteristic createCharacteristic(UUID uuid, EnumSet<Properties> enumSet) {
        if (uuid != null) {
            if (enumSet != null) {
                if (!enumSet.isEmpty()) {
                    return new BleGattCharacteristic(uuid, enumSet);
                }
                throw new IllegalArgumentException("properties cannot be an empty set.");
            }
            throw new IllegalArgumentException("properties cannot be null.");
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public boolean addBleDescriptor(BleGattDescriptor bleGattDescriptor) {
        if (bleGattDescriptor != null) {
            if (!this.mBleDescriptors.containsKey(bleGattDescriptor.getUuid())) {
                boolean addDescriptor = this.mBluetoothGattCharacteristic.addDescriptor(bleGattDescriptor.getBluetoothGattDescriptor());
                if (addDescriptor) {
                    this.mBleDescriptors.put(bleGattDescriptor.getUuid(), bleGattDescriptor);
                }
                return addDescriptor;
            }
            throw new IllegalArgumentException("descriptor already associated with characteristic.");
        }
        throw new IllegalArgumentException("descriptor cannot be null.");
    }

    public boolean containsBleDescriptor(UUID uuid) {
        if (uuid != null) {
            return this.mBleDescriptors.containsKey(uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public BleGattDescriptor getBleDescriptor(UUID uuid) {
        if (uuid != null) {
            if (this.mBleDescriptors.containsKey(uuid)) {
                return this.mBleDescriptors.get(uuid);
            }
            throw new IllegalArgumentException("No descriptor with uuid=" + uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public BluetoothGattCharacteristic getBluetoothGattCharacteristic() {
        return this.mBluetoothGattCharacteristic;
    }

    public synchronized byte[] getStoredData() {
        return this.mBluetoothGattCharacteristic.getValue();
    }

    public UUID getUuid() {
        return this.mUuid;
    }

    public boolean isIndicatable() {
        return this.mProperties.contains(Properties.INDICATE);
    }

    public boolean isNotifiable() {
        return this.mProperties.contains(Properties.NOTIFY);
    }

    public boolean isReadable() {
        return this.mProperties.contains(Properties.READ);
    }

    public boolean isValidReadDescriptor(UUID uuid) {
        if (uuid != null) {
            if (this.mBleDescriptors.containsKey(uuid)) {
                return this.mBleDescriptors.get(uuid).isReadable();
            }
            throw new IllegalArgumentException("No descriptor with uuid=" + uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public boolean isValidWriteDescriptor(UUID uuid) {
        if (uuid != null) {
            if (this.mBleDescriptors.containsKey(uuid)) {
                return this.mBleDescriptors.get(uuid).isWritable();
            }
            throw new IllegalArgumentException("No descriptor with uuid=" + uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public boolean isWritable() {
        return this.mProperties.contains(Properties.WRITE);
    }

    public void setDescriptorData(UUID uuid, byte[] bArr) {
        if (uuid != null) {
            if (this.mBleDescriptors.containsKey(uuid)) {
                this.mBleDescriptors.get(uuid).setStoredData(bArr);
                return;
            }
            throw new IllegalArgumentException("No descriptor with uuid=" + uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public synchronized void setStoredData(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length > 512) {
                throw new IllegalArgumentException("Stored data in characteristic is limited to 512 bytes.");
            }
        }
        this.mBluetoothGattCharacteristic.setValue(bArr);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleGattCharacteristic [uuid=");
        outline107.append(this.mUuid);
        outline107.append("]");
        return outline107.toString();
    }

    public static BleGattCharacteristic createCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            return new BleGattCharacteristic(bluetoothGattCharacteristic);
        }
        throw new IllegalArgumentException("characteristic cannot be null.");
    }

    protected BleGattCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.mUuid = bluetoothGattCharacteristic.getUuid();
        this.mBluetoothGattCharacteristic = bluetoothGattCharacteristic;
        int properties = bluetoothGattCharacteristic.getProperties();
        this.mProperties = EnumSet.noneOf(Properties.class);
        if ((properties & 2) != 0) {
            this.mProperties.add(Properties.READ);
        }
        if ((properties & 8) != 0) {
            this.mProperties.add(Properties.WRITE);
        }
        if ((properties & 16) != 0) {
            this.mProperties.add(Properties.NOTIFY);
        }
        if ((properties & 32) != 0) {
            this.mProperties.add(Properties.INDICATE);
        }
        for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
            BleGattDescriptor createDescriptor = BleGattDescriptor.createDescriptor(bluetoothGattDescriptor);
            this.mBleDescriptors.put(createDescriptor.getUuid(), createDescriptor);
        }
    }
}
