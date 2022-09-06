package com.amazon.alexa.mode.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.util.LogTag;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class AutoBluetoothObserver extends BroadcastReceiver {
    private static final String TAG = LogTag.forClass(AutoBluetoothObserver.class);
    private String headunitType;
    private final AutoBluetoothDetector mAutoBluetoothDetector;
    private final ArrayList<BluetoothDevice> mCarBluetoothDevices = new ArrayList<>();
    private final BehaviorSubject<Boolean> mCarBluetoothObservable = BehaviorSubject.createDefault(false);
    private final Context mContext;
    private final Lazy<DriveModeMetrics> mDriveModeMetrics;

    public AutoBluetoothObserver(Context context, Lazy<DriveModeMetrics> lazy) {
        this.mContext = context;
        this.mDriveModeMetrics = lazy;
        this.mAutoBluetoothDetector = new AutoBluetoothDetector(context);
    }

    @SuppressLint({"MissingPermission"})
    private void disconnectedToAutoBluetooth(@NonNull BluetoothDevice bluetoothDevice) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("disconnectedToAutoBluetooth | device: ");
        outline107.append(bluetoothDevice.getName());
        Log.i(str, outline107.toString());
        this.mCarBluetoothDevices.remove(bluetoothDevice);
        if (this.mCarBluetoothDevices.isEmpty()) {
            this.mCarBluetoothObservable.onNext(false);
        }
    }

    @SuppressLint({"MissingPermission"})
    private void examineConnectedDevices() {
        BluetoothAdapter adapter;
        BluetoothManager bluetoothManager = (BluetoothManager) this.mContext.getSystemService("bluetooth");
        if (bluetoothManager == null || (adapter = bluetoothManager.getAdapter()) == null || !adapter.isEnabled()) {
            return;
        }
        adapter.getProfileProxy(this.mContext, instantiateBluetoothListener(adapter), 1);
        adapter.getProfileProxy(this.mContext, instantiateBluetoothListener(adapter), 2);
    }

    @SuppressLint({"MissingPermission"})
    private BluetoothProfile.ServiceListener instantiateBluetoothListener(final BluetoothAdapter bluetoothAdapter) {
        return new BluetoothProfile.ServiceListener() { // from class: com.amazon.alexa.mode.bluetooth.AutoBluetoothObserver.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                List<BluetoothDevice> connectedDevices;
                if ((i == 1 || i == 2) && (connectedDevices = bluetoothProfile.getConnectedDevices()) != null && connectedDevices.size() > 0) {
                    for (BluetoothDevice bluetoothDevice : connectedDevices) {
                        if (AutoBluetoothObserver.this.mAutoBluetoothDetector.isSupportedAutoBluetooth(bluetoothDevice)) {
                            AutoBluetoothObserver.this.connectedToAutoBluetooth(bluetoothDevice);
                        }
                    }
                }
                bluetoothAdapter.closeProfileProxy(i, bluetoothProfile);
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
            }
        };
    }

    private void registerForBluetoothDeviceUpdates() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        this.mContext.registerReceiver(this, intentFilter);
    }

    private void unregisterForBluetoothDeviceUpdates() {
        try {
            this.mContext.unregisterReceiver(this);
        } catch (Exception unused) {
            Log.e(TAG, "Issue unregistering receiver");
        }
    }

    @SuppressLint({"MissingPermission"})
    void connectedToAutoBluetooth(@NonNull BluetoothDevice bluetoothDevice) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("connectedToAutoBluetooth | device: ");
        outline107.append(bluetoothDevice.getName());
        Log.i(str, outline107.toString());
        if (!this.mCarBluetoothDevices.contains(bluetoothDevice)) {
            this.mCarBluetoothDevices.add(bluetoothDevice);
            this.headunitType = this.mAutoBluetoothDetector.getAutoBluetoothType(bluetoothDevice);
            if (this.headunitType != null) {
                this.mDriveModeMetrics.mo358get().logAutoBluetoothConnected(this.headunitType, String.valueOf(bluetoothDevice.getBluetoothClass() == null ? -1 : bluetoothDevice.getBluetoothClass().getDeviceClass()));
                this.mDriveModeMetrics.mo358get().logAutoBluetoothConnected();
            }
            if (this.mCarBluetoothDevices.size() != 1) {
                return;
            }
            this.mCarBluetoothObservable.onNext(true);
        }
    }

    public String getHeadUnitType() {
        return isConnectedToAutoBluetooth().getValue().booleanValue() ? this.headunitType : "";
    }

    public void initialize() {
        Log.i(TAG, "initialize");
        this.mAutoBluetoothDetector.init();
        examineConnectedDevices();
        registerForBluetoothDeviceUpdates();
    }

    public BehaviorSubject<Boolean> isConnectedToAutoBluetooth() {
        return this.mCarBluetoothObservable;
    }

    @Override // android.content.BroadcastReceiver
    @SuppressLint({"MissingPermission"})
    public void onReceive(Context context, Intent intent) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if ("android.bluetooth.device.action.ACL_CONNECTED".equals(intent.getAction())) {
            Log.i(TAG, "onReceive | bluetooth auto device connected");
            if (bluetoothDevice != null && bluetoothDevice.getBluetoothClass() != null) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class of Device for ");
                outline107.append(bluetoothDevice.getName());
                outline107.append(" is ");
                outline107.append(bluetoothDevice.getBluetoothClass().getDeviceClass());
                Log.i(str, outline107.toString());
            }
            if (!this.mAutoBluetoothDetector.isSupportedAutoBluetooth(bluetoothDevice)) {
                return;
            }
            connectedToAutoBluetooth(bluetoothDevice);
        } else if (!"android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) || !this.mAutoBluetoothDetector.isSupportedAutoBluetooth(bluetoothDevice)) {
        } else {
            Log.i(TAG, "onReceive | bluetooth auto device disconnected");
            disconnectedToAutoBluetooth(bluetoothDevice);
        }
    }

    public void uninitialize() {
        unregisterForBluetoothDeviceUpdates();
        this.mAutoBluetoothDetector.deInit();
    }
}
