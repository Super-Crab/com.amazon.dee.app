package com.amazon.alexa.accessory.internal.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.alexa.accessory.internal.bluetooth.BleAdvertisementData;
import com.amazon.alexa.accessory.internal.util.ByteUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.lang.reflect.InvocationTargetException;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public final class BluetoothUtils {
    private static final int MAC_ADDRESS_LENGTH = 6;
    public static final int TRANSPORT_AUTO = 0;
    public static final int TRANSPORT_BREDR = 1;
    public static final int TRANSPORT_LE = 2;

    private BluetoothUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static byte[] addressToByteArray(String str) {
        Preconditions.notNull(str, "address");
        String[] split = str.split(":");
        Preconditions.precondition(split.length == 6, "Invalid address length (expected 6 bytes)");
        byte[] bArr = new byte[6];
        int length = split.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) Integer.parseInt(split[i], 16);
        }
        return bArr;
    }

    public static String bluetoothDeviceTypeToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "DEVICE_TYPE_UNKNOWN" : "DEVICE_TYPE_DUAL" : "DEVICE_TYPE_LE" : "DEVICE_TYPE_CLASSIC";
    }

    public static String bluetoothProfileToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 7 ? i != 8 ? i != 10 ? "UNKNOWN" : "SAP" : "GATT_SERVER" : "GATT" : "HEALTH" : "A2DP" : "HEADSET";
    }

    public static String byteArrayToAddress(byte[] bArr) {
        Preconditions.notNull(bArr, "bytes");
        Preconditions.precondition(bArr.length == 6, "Invalid address length (expected 6 bytes)");
        return ByteUtils.bytesToHex(bArr, JsonReaderKt.COLON);
    }

    public static boolean cancelBondProcess(BluetoothDevice bluetoothDevice) {
        Preconditions.notNull(bluetoothDevice, "device");
        try {
            return ((Boolean) BluetoothDevice.class.getDeclaredMethod("cancelBondProcess", new Class[0]).invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Invalid environment", e);
        }
    }

    public static BluetoothGatt connectGatt(BluetoothDevice bluetoothDevice, Context context, boolean z, BluetoothGattCallback bluetoothGattCallback, int i) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(bluetoothDevice, "device");
        Preconditions.notNull(bluetoothGattCallback, "callback");
        try {
            return (BluetoothGatt) BluetoothDevice.class.getDeclaredMethod("connectGatt", Context.class, Boolean.TYPE, BluetoothGattCallback.class, Integer.TYPE).invoke(bluetoothDevice, context, Boolean.valueOf(z), bluetoothGattCallback, Integer.valueOf(i));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Invalid environment", e);
        }
    }

    public static boolean createBond(BluetoothDevice bluetoothDevice, int i) {
        Preconditions.notNull(bluetoothDevice, "device");
        try {
            return ((Boolean) BluetoothDevice.class.getDeclaredMethod("createBond", Integer.TYPE).invoke(bluetoothDevice, Integer.valueOf(i))).booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Invalid environment", e);
        }
    }

    public static BluetoothAdapter getBluetoothAdapter(Context context) {
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        if (bluetoothManager == null) {
            return null;
        }
        return bluetoothManager.getAdapter();
    }

    public static boolean isBluetoothOn(Context context) {
        BluetoothAdapter adapter;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        return (bluetoothManager == null || (adapter = bluetoothManager.getAdapter()) == null || !adapter.isEnabled()) ? false : true;
    }

    public static boolean isLeSupported(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && packageManager.hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static BleAdvertisementData.GapProfileMap parseLeAdvertisementData(byte[] bArr) {
        int i;
        BleAdvertisementData.GapProfileMap gapProfileMap = new BleAdvertisementData.GapProfileMap();
        if (bArr == null) {
            return gapProfileMap;
        }
        if (Logger.shouldLog(Logger.Level.VERBOSE)) {
            Logger.v("parseLeAdvertisementData: length=%d  data=%s", Integer.valueOf(bArr.length), ByteUtils.bytesToHex(bArr));
        }
        int i2 = 0;
        while (i2 < bArr.length && (i = bArr[i2]) > 0 && bArr.length > i2 + i) {
            byte b = bArr[i2 + 1];
            byte[] bArr2 = new byte[i - 1];
            System.arraycopy(bArr, i2 + 2, bArr2, 0, bArr2.length);
            try {
                gapProfileMap.put(Byte.valueOf(b), bArr2);
            } catch (IllegalArgumentException e) {
                Logger.e("Invalid BLE AD packet type %02X:  packet=%s  pointer=%d  length=%d\ndata=%s", e, Byte.valueOf(b), ByteUtils.bytesToHex(bArr2), Integer.valueOf(i2), Integer.valueOf(i), ByteUtils.bytesToHex(bArr));
            }
            i2 += i + 1;
        }
        if (Logger.shouldLog(Logger.Level.VERBOSE)) {
            Logger.v("parseLeAdvertisementData: map=%s", gapProfileMap);
        }
        return gapProfileMap;
    }

    public static boolean removeBond(BluetoothDevice bluetoothDevice) {
        Preconditions.notNull(bluetoothDevice, "device");
        try {
            return ((Boolean) BluetoothDevice.class.getDeclaredMethod("removeBond", new Class[0]).invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Invalid environment", e);
        }
    }
}
