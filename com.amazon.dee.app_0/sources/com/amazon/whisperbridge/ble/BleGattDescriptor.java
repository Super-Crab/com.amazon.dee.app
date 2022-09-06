package com.amazon.whisperbridge.ble;

import android.bluetooth.BluetoothGattDescriptor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.EnumSet;
import java.util.UUID;
/* loaded from: classes13.dex */
public class BleGattDescriptor {
    private static final int MAX_STORED_DATA_SIZE = 512;
    private static final String TAG = "BleGattDescriptor";
    protected final BluetoothGattDescriptor mBluetoothGattDescriptor;
    protected final EnumSet<Properties> mProperties;
    protected final UUID mUuid;

    /* loaded from: classes13.dex */
    public enum Properties {
        READ,
        WRITE
    }

    protected BleGattDescriptor(UUID uuid, EnumSet<Properties> enumSet) {
        this.mUuid = uuid;
        this.mProperties = enumSet;
        this.mBluetoothGattDescriptor = new BluetoothGattDescriptor(this.mUuid, (enumSet.contains(Properties.READ) ? 1 : 0) | 0 | (enumSet.contains(Properties.WRITE) ? 16 : 0));
    }

    public static BleGattDescriptor createDescriptor(UUID uuid, EnumSet<Properties> enumSet) {
        if (uuid != null) {
            if (enumSet != null) {
                if (!enumSet.isEmpty()) {
                    return new BleGattDescriptor(uuid, enumSet);
                }
                throw new IllegalArgumentException("properties cannot be an empty set.");
            }
            throw new IllegalArgumentException("properties cannot be null.");
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public BluetoothGattDescriptor getBluetoothGattDescriptor() {
        return this.mBluetoothGattDescriptor;
    }

    public synchronized byte[] getStoredData() {
        return this.mBluetoothGattDescriptor.getValue();
    }

    public UUID getUuid() {
        return this.mUuid;
    }

    public boolean isReadable() {
        return this.mProperties.contains(Properties.READ);
    }

    public boolean isWritable() {
        return this.mProperties.contains(Properties.WRITE);
    }

    public synchronized void setStoredData(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length > 512) {
                throw new IllegalArgumentException("Stored data in descriptor is limited to 512 bytes.");
            }
        }
        this.mBluetoothGattDescriptor.setValue(bArr);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BluetoothGattDescriptor [uuid=");
        outline107.append(this.mUuid);
        outline107.append("]");
        return outline107.toString();
    }

    public static BleGattDescriptor createDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (bluetoothGattDescriptor != null) {
            return new BleGattDescriptor(bluetoothGattDescriptor);
        }
        throw new IllegalArgumentException("characteristic cannot be null.");
    }

    protected BleGattDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        this.mUuid = bluetoothGattDescriptor.getUuid();
        this.mBluetoothGattDescriptor = bluetoothGattDescriptor;
        int permissions = bluetoothGattDescriptor.getPermissions();
        this.mProperties = EnumSet.noneOf(Properties.class);
        if ((permissions & 1) != 0) {
            this.mProperties.add(Properties.READ);
        }
        if ((permissions & 16) != 0) {
            this.mProperties.add(Properties.WRITE);
        }
    }
}
