package chip.devicecontroller;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.util.Log;
import chip.devicecontroller.GetConnectedDeviceCallbackJni;
import chip.devicecontroller.mdns.ChipMdnsCallback;
import chip.devicecontroller.mdns.ServiceResolver;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class ChipDeviceController {
    private static final String TAG = "ChipDeviceController";
    private BluetoothGatt bleGatt;
    private CompletionListener completionListener;
    private int connectionId;
    private long deviceControllerPtr;

    /* loaded from: classes.dex */
    public interface CompletionListener {
        void onCloseBleComplete();

        void onCommissioningComplete(long j, int i);

        void onConnectDeviceComplete();

        void onError(Throwable th);

        void onNotifyChipConnectionClosed();

        void onOpCSRGenerationComplete(byte[] bArr);

        void onPairingComplete(int i);

        void onPairingDeleted(int i);

        void onSendMessageComplete(String str);

        void onStatusUpdate(int i);
    }

    static {
        System.loadLibrary("CHIPController");
    }

    public ChipDeviceController(KeyValueStoreManager keyValueStoreManager, ServiceResolver serviceResolver, ChipMdnsCallback chipMdnsCallback) {
        this.deviceControllerPtr = newDeviceController(keyValueStoreManager, serviceResolver, chipMdnsCallback);
    }

    private native void deleteDeviceController(long j);

    private native boolean disconnectDevice(long j, long j2);

    private native void getConnectedDevicePointer(long j, long j2, long j3);

    private native long getDevicePointer(long j, long j2);

    private native long newDeviceController(KeyValueStoreManager keyValueStoreManager, ServiceResolver serviceResolver, ChipMdnsCallback chipMdnsCallback);

    private native void pairDevice(long j, long j2, long j3, int i, long j4, byte[] bArr);

    private boolean releaseBluetoothGatt(int i) {
        if (this.connectionId == 0) {
            return false;
        }
        GeneratedOutlineSupport1.outline149("Closing GATT and removing connection for ", i);
        this.bleGatt.close();
        AndroidChipStack.getInstance().removeConnection(i);
        this.connectionId = 0;
        this.bleGatt = null;
        return true;
    }

    public void close() {
        releaseBluetoothGatt(this.connectionId);
    }

    public boolean disconnectDevice(long j) {
        return disconnectDevice(this.deviceControllerPtr, j);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        long j = this.deviceControllerPtr;
        if (j != 0) {
            deleteDeviceController(j);
            this.deviceControllerPtr = 0L;
        }
    }

    public BluetoothGatt getBluetoothGatt() {
        return this.bleGatt;
    }

    public BluetoothGattCallback getCallback() {
        return AndroidChipStack.getInstance().getCallback();
    }

    public void getConnectedDevicePointer(long j, GetConnectedDeviceCallbackJni.GetConnectedDeviceCallback getConnectedDeviceCallback) {
        getConnectedDevicePointer(this.deviceControllerPtr, j, new GetConnectedDeviceCallbackJni(getConnectedDeviceCallback).getCallbackHandle());
    }

    public long getDevicePointer(long j) {
        return getDevicePointer(this.deviceControllerPtr, j);
    }

    public void onCloseBleComplete(int i) {
        if (releaseBluetoothGatt(i)) {
            this.completionListener.onCloseBleComplete();
        }
    }

    public void onCommissioningComplete(long j, int i) {
        CompletionListener completionListener = this.completionListener;
        if (completionListener != null) {
            completionListener.onCommissioningComplete(j, i);
        }
    }

    public void onConnectDeviceComplete() {
        this.completionListener.onConnectDeviceComplete();
    }

    public void onError(Throwable th) {
        this.completionListener.onError(th);
    }

    public void onNotifyChipConnectionClosed(int i) {
        AndroidChipStack.getInstance().removeConnection(i);
        this.connectionId = 0;
        this.bleGatt = null;
        this.completionListener.onNotifyChipConnectionClosed();
    }

    public void onOpCSRGenerationComplete(byte[] bArr) {
        CompletionListener completionListener = this.completionListener;
        if (completionListener != null) {
            completionListener.onOpCSRGenerationComplete(bArr);
        }
    }

    public void onPairingComplete(int i) {
        CompletionListener completionListener = this.completionListener;
        if (completionListener != null) {
            completionListener.onPairingComplete(i);
        }
    }

    public void onPairingDeleted(int i) {
        CompletionListener completionListener = this.completionListener;
        if (completionListener != null) {
            completionListener.onPairingDeleted(i);
        }
    }

    public void onSendMessageComplete(String str) {
        this.completionListener.onSendMessageComplete(str);
    }

    public void onStatusUpdate(int i) {
        CompletionListener completionListener = this.completionListener;
        if (completionListener != null) {
            completionListener.onStatusUpdate(i);
        }
    }

    public void pairDevice(BluetoothGatt bluetoothGatt, long j, long j2, long j3) {
        pairDevice(bluetoothGatt, j, j2, j3, null);
    }

    public void setCompletionListener(CompletionListener completionListener) {
        this.completionListener = completionListener;
    }

    public void pairDevice(BluetoothGatt bluetoothGatt, long j, long j2, long j3, byte[] bArr) {
        if (this.connectionId == 0) {
            this.bleGatt = bluetoothGatt;
            this.connectionId = AndroidChipStack.getInstance().addConnection(this);
            if (this.connectionId == 0) {
                Log.e(TAG, "Failed to add Bluetooth connection.");
                this.completionListener.onError(new Exception("Failed to add Bluetooth connection."));
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bluetooth connection added with ID: ");
            outline107.append(this.connectionId);
            outline107.toString();
            String str = "Pairing device with fabricId: " + j + ", deviceId:" + j2;
            pairDevice(this.deviceControllerPtr, j, j2, this.connectionId, j3, bArr);
            return;
        }
        Log.e(TAG, "Bluetooth connection already in use.");
        this.completionListener.onError(new Exception("Bluetooth connection already in use."));
    }
}
