package chip.devicecontroller;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Build;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/* loaded from: classes.dex */
public final class AndroidChipStack {
    private static String CLIENT_CHARACTERISTIC_CONFIG = null;
    public static final int INITIAL_CONNECTIONS = 4;
    private static final String TAG = "AndroidChipStack";
    private static final AndroidChipStack sInstance = new AndroidChipStack();
    private final List<ChipDeviceController> mConnections = new ArrayList(4);
    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() { // from class: chip.devicecontroller.AndroidChipStack.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            byte[] convertUUIDToBytes = AndroidChipStack.convertUUIDToBytes(bluetoothGattCharacteristic.getService().getUuid());
            byte[] convertUUIDToBytes2 = AndroidChipStack.convertUUIDToBytes(bluetoothGattCharacteristic.getUuid());
            int connId = AndroidChipStack.this.getConnId(bluetoothGatt);
            if (connId > 0) {
                AndroidChipStack.this.handleIndicationReceived(connId, convertUUIDToBytes, convertUUIDToBytes2, bluetoothGattCharacteristic.getValue());
            } else {
                Log.e(AndroidChipStack.TAG, "onCharacteristicChanged no active connection");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            byte[] convertUUIDToBytes = AndroidChipStack.convertUUIDToBytes(bluetoothGattCharacteristic.getService().getUuid());
            byte[] convertUUIDToBytes2 = AndroidChipStack.convertUUIDToBytes(bluetoothGattCharacteristic.getUuid());
            if (i != 0) {
                String str = AndroidChipStack.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCharacteristicWrite for ");
                outline107.append(bluetoothGattCharacteristic.getUuid().toString());
                outline107.append(" failed with status: ");
                outline107.append(i);
                Log.e(str, outline107.toString());
                return;
            }
            int connId = AndroidChipStack.this.getConnId(bluetoothGatt);
            if (connId > 0) {
                AndroidChipStack.this.handleWriteConfirmation(connId, convertUUIDToBytes, convertUUIDToBytes2, i == 0);
            } else {
                Log.e(AndroidChipStack.TAG, "onCharacteristicWrite no active connection");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 0) {
                int connId = AndroidChipStack.this.getConnId(bluetoothGatt);
                if (connId > 0) {
                    String unused = AndroidChipStack.TAG;
                    AndroidChipStack.this.handleConnectionError(connId);
                    return;
                }
                Log.e(AndroidChipStack.TAG, "onConnectionStateChange disconnected: no active connection");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
            byte[] convertUUIDToBytes = AndroidChipStack.convertUUIDToBytes(characteristic.getService().getUuid());
            byte[] convertUUIDToBytes2 = AndroidChipStack.convertUUIDToBytes(characteristic.getUuid());
            if (i != 0) {
                String str = AndroidChipStack.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDescriptorWrite for ");
                outline107.append(bluetoothGattDescriptor.getUuid().toString());
                outline107.append(" failed with status: ");
                outline107.append(i);
                Log.e(str, outline107.toString());
            }
            int connId = AndroidChipStack.this.getConnId(bluetoothGatt);
            if (connId == 0) {
                Log.e(AndroidChipStack.TAG, "onDescriptorWrite no active connection");
                return;
            }
            boolean z = true;
            if (bluetoothGattDescriptor.getValue() == BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE) {
                AndroidChipStack androidChipStack = AndroidChipStack.this;
                if (i != 0) {
                    z = false;
                }
                androidChipStack.handleSubscribeComplete(connId, convertUUIDToBytes, convertUUIDToBytes2, z);
            } else if (bluetoothGattDescriptor.getValue() != BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE) {
                String unused = AndroidChipStack.TAG;
            } else {
                AndroidChipStack androidChipStack2 = AndroidChipStack.this;
                if (i != 0) {
                    z = false;
                }
                androidChipStack2.handleUnsubscribeComplete(connId, convertUUIDToBytes, convertUUIDToBytes2, z);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        }
    };

    /* loaded from: classes.dex */
    private static class BleMtuDenylist {
        static final boolean BLE_MTU_DENYLISTED;
        static final int BLE_MTU_FALLBACK = 23;

        static {
            if ("OnePlus".equals(Build.MANUFACTURER)) {
                BLE_MTU_DENYLISTED = "ONE A2005".equals(Build.MODEL);
                return;
            }
            boolean z = false;
            if ("motorola".equals(Build.MANUFACTURER)) {
                if ("XT1575".equals(Build.MODEL) || "XT1585".equals(Build.MODEL)) {
                    z = true;
                }
                BLE_MTU_DENYLISTED = z;
                return;
            }
            BLE_MTU_DENYLISTED = false;
        }

        private BleMtuDenylist() {
        }
    }

    static {
        System.loadLibrary("CHIPController");
        CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    }

    private AndroidChipStack() {
    }

    private static UUID convertBytesToUUID(byte[] bArr) {
        long j;
        long j2 = 0;
        if (bArr.length == 16) {
            long j3 = 0;
            for (int i = 0; i < 8; i++) {
                j3 = (j3 << 8) | (bArr[i] & 255);
            }
            for (int i2 = 0; i2 < 8; i2++) {
                j2 = (j2 << 8) | (bArr[i2 + 8] & 255);
            }
            long j4 = j2;
            j2 = j3;
            j = j4;
        } else {
            j = 0;
        }
        return new UUID(j2, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] convertUUIDToBytes(UUID uuid) {
        byte[] bArr = new byte[16];
        long leastSignificantBits = uuid.getLeastSignificantBits();
        for (int i = 0; i < 8; i++) {
            bArr[15 - i] = (byte) (255 & leastSignificantBits);
            leastSignificantBits >>= 8;
        }
        long mostSignificantBits = uuid.getMostSignificantBits();
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[7 - i2] = (byte) (mostSignificantBits & 255);
            mostSignificantBits >>= 8;
        }
        return bArr;
    }

    public static AndroidChipStack getInstance() {
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void handleConnectionError(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void handleIndicationReceived(int i, byte[] bArr, byte[] bArr2, byte[] bArr3);

    /* JADX INFO: Access modifiers changed from: private */
    public native void handleSubscribeComplete(int i, byte[] bArr, byte[] bArr2, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void handleUnsubscribeComplete(int i, byte[] bArr, byte[] bArr2, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void handleWriteConfirmation(int i, byte[] bArr, byte[] bArr2, boolean z);

    public static boolean onCloseConnection(int i) {
        ChipDeviceController connection = getInstance().getConnection(i);
        if (connection != null) {
            connection.onCloseBleComplete(i);
            return true;
        }
        Log.i(TAG, "Tried to close BLE connection, but connection was not found.");
        return true;
    }

    public static int onGetMTU(int i) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Android Manufacturer: (");
        outline107.append(Build.MANUFACTURER);
        outline107.append(")");
        outline107.toString();
        String str = "Android Model: (" + Build.MODEL + ")";
        if (BleMtuDenylist.BLE_MTU_DENYLISTED) {
            Log.e(TAG, "Detected Manufacturer/Model with MTU incompatibiility. Reporting MTU: 23");
            return 23;
        }
        return 0;
    }

    public static void onNotifyChipConnectionClosed(int i) {
        ChipDeviceController connection = getInstance().getConnection(i);
        if (connection != null) {
            connection.onNotifyChipConnectionClosed(i);
        } else {
            Log.i(TAG, "Tried to notify connection closed, but BLE connection was not found.");
        }
    }

    public static boolean onSendCharacteristic(int i, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        ChipDeviceController connection = getInstance().getConnection(i);
        if (connection == null) {
            Log.i(TAG, "Tried to send characteristic, but BLE connection was not found.");
            return false;
        }
        BluetoothGatt bluetoothGatt = connection.getBluetoothGatt();
        if (bluetoothGatt == null) {
            return false;
        }
        BluetoothGattService service = bluetoothGatt.getService(convertBytesToUUID(bArr));
        if (service == null) {
            Log.e(TAG, "Bad service");
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(convertBytesToUUID(bArr2));
        if (!characteristic.setValue(bArr3)) {
            Log.e(TAG, "Failed to set characteristic");
            return false;
        }
        characteristic.setWriteType(2);
        if (bluetoothGatt.writeCharacteristic(characteristic)) {
            return true;
        }
        Log.e(TAG, "Failed writing char");
        return false;
    }

    public static boolean onSubscribeCharacteristic(int i, byte[] bArr, byte[] bArr2) {
        ChipDeviceController connection = getInstance().getConnection(i);
        if (connection == null) {
            Log.i(TAG, "Tried to send characteristic, but BLE connection was not found.");
            return false;
        }
        BluetoothGatt bluetoothGatt = connection.getBluetoothGatt();
        if (bluetoothGatt == null) {
            return false;
        }
        BluetoothGattService service = bluetoothGatt.getService(convertBytesToUUID(bArr));
        if (service == null) {
            Log.e(TAG, "Bad service");
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(convertBytesToUUID(bArr2));
        if (characteristic == null) {
            Log.e(TAG, "Bad characteristic");
            return false;
        } else if (!bluetoothGatt.setCharacteristicNotification(characteristic, true)) {
            Log.e(TAG, "Failed to subscribe to characteristic.");
            return false;
        } else {
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(CLIENT_CHARACTERISTIC_CONFIG));
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            if (bluetoothGatt.writeDescriptor(descriptor)) {
                return true;
            }
            Log.e(TAG, "writeDescriptor failed");
            return false;
        }
    }

    public static boolean onUnsubscribeCharacteristic(int i, byte[] bArr, byte[] bArr2) {
        ChipDeviceController connection = getInstance().getConnection(i);
        if (connection == null) {
            Log.i(TAG, "Tried to unsubscribe characteristic, but BLE connection was not found.");
            return false;
        }
        BluetoothGatt bluetoothGatt = connection.getBluetoothGatt();
        if (bluetoothGatt == null) {
            return false;
        }
        BluetoothGattService service = bluetoothGatt.getService(convertBytesToUUID(bArr));
        if (service == null) {
            Log.e(TAG, "Bad service");
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(convertBytesToUUID(bArr2));
        if (characteristic == null) {
            Log.e(TAG, "Bad characteristic");
            return false;
        } else if (!bluetoothGatt.setCharacteristicNotification(characteristic, false)) {
            Log.e(TAG, "Failed to unsubscribe to characteristic.");
            return false;
        } else {
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(CLIENT_CHARACTERISTIC_CONFIG));
            descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            if (bluetoothGatt.writeDescriptor(descriptor)) {
                return true;
            }
            Log.e(TAG, "writeDescriptor failed");
            return false;
        }
    }

    public synchronized int addConnection(ChipDeviceController chipDeviceController) {
        int i = 0;
        while (i < this.mConnections.size()) {
            if (this.mConnections.get(i) == null) {
                this.mConnections.set(i, chipDeviceController);
                return i + 1;
            }
            i++;
        }
        this.mConnections.add(i, chipDeviceController);
        return i + 1;
    }

    public BluetoothGattCallback getCallback() {
        return this.mGattCallback;
    }

    public synchronized int getConnId(BluetoothGatt bluetoothGatt) {
        for (int i = 0; i < this.mConnections.size(); i++) {
            ChipDeviceController chipDeviceController = this.mConnections.get(i);
            if (chipDeviceController != null && bluetoothGatt == chipDeviceController.getBluetoothGatt()) {
                return i + 1;
            }
        }
        return 0;
    }

    public synchronized ChipDeviceController getConnection(int i) {
        int i2 = i - 1;
        if (i2 >= 0) {
            if (i2 < this.mConnections.size()) {
                return this.mConnections.get(i2);
            }
        }
        Log.e(TAG, "Unknown connId " + i);
        return null;
    }

    public synchronized ChipDeviceController removeConnection(int i) {
        int i2 = i - 1;
        if (i2 >= 0) {
            if (i2 < this.mConnections.size()) {
                return this.mConnections.set(i2, null);
            }
        }
        Log.e(TAG, "Trying to remove unknown connId " + i);
        return null;
    }
}
