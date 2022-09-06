package com.amazon.alexa.accessory.notificationpublisher.providers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.google.common.base.Strings;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public final class BluetoothHeadsetProvider {
    private static final String TAG = "BluetoothHeadsetProvider";
    private static final IntentFilter btHeadsetConnectionChangeIntentFilter = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
    private static BluetoothHeadsetProvider provider;
    private BluetoothAdapter btAdapter;
    private BluetoothHeadset btHeadset;
    private BluetoothHeadsetModel btHeadsetModel;
    private BluetoothProfile.ServiceListener btProfileListener;
    private String zionMacAddress = "";
    private final BluetoothHeadsetConnectionChangedReceiver btHeadsetConnectionChangeReceiver = new BluetoothHeadsetConnectionChangedReceiver();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class BluetoothHeadsetConnectionChangedReceiver extends BroadcastReceiver {
        BluetoothHeadsetConnectionChangedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public synchronized void onReceive(Context context, Intent intent) {
            Log.d(BluetoothHeadsetProvider.TAG, "BluetoothHeadsetConnectionChangedReceiver - onReceive");
            Bundle extras = intent.getExtras();
            if (extras != null) {
                try {
                    String address = ((BluetoothDevice) extras.getParcelable("android.bluetooth.device.extra.DEVICE")).getAddress();
                    if (Strings.isNullOrEmpty(address)) {
                        Log.w(BluetoothHeadsetProvider.TAG, "BluetoothHeadsetConnectionChangedReceiver - Device address is null or empty.");
                    } else if (!address.equalsIgnoreCase(BluetoothHeadsetProvider.this.zionMacAddress)) {
                        Log.i(BluetoothHeadsetProvider.TAG, "BluetoothHeadsetConnectionChangedReceiver - Ignore non-Zion device");
                    } else {
                        int i = extras.getInt("android.bluetooth.profile.extra.STATE");
                        Log.d(BluetoothHeadsetProvider.TAG, String.format(Locale.US, "BluetoothHeadsetConnectionChangedReceiver - Device: %s, Connection State: %d", address, Integer.valueOf(i)));
                        try {
                            if (BluetoothHeadsetProvider.this.btHeadset == null) {
                                Log.i(BluetoothHeadsetProvider.TAG, "BluetoothHeadsetConnectionChangedReceiver - BT Headset is null, return");
                                return;
                            }
                            List<BluetoothDevice> connectedDevices = BluetoothHeadsetProvider.this.btHeadset.getConnectedDevices();
                            if (connectedDevices != null && connectedDevices.size() != 0) {
                                if (i == 0) {
                                    Log.i(BluetoothHeadsetProvider.TAG, "BluetoothHeadsetConnectionChangedReceiver - Zion device - BT Headset disconnected");
                                    BluetoothHeadsetProvider.this.btHeadsetModel = null;
                                } else if (i == 2) {
                                    Log.i(BluetoothHeadsetProvider.TAG, "BluetoothHeadsetConnectionChangedReceiver - Zion device - BT Headset connected");
                                    BluetoothHeadsetProvider.this.btHeadsetModel = new BluetoothHeadsetModel(BluetoothHeadsetProvider.this.btAdapter, BluetoothHeadsetProvider.this.btHeadset, address);
                                }
                            }
                            Log.i(BluetoothHeadsetProvider.TAG, "BluetoothHeadsetConnectionChangedReceiver - BT Device list is null, return");
                        } catch (Exception e) {
                            String str = BluetoothHeadsetProvider.TAG;
                            Log.e(str, "BluetoothHeadsetConnectionChangedReceiver - Exception: " + e);
                        }
                    }
                } catch (Exception e2) {
                    String str2 = BluetoothHeadsetProvider.TAG;
                    Log.w(str2, "BluetoothHeadsetConnectionChangedReceiver - Exception getting device address" + e2);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class BluetoothHeadsetModel {
        private BluetoothAdapter btAdapter;
        private String btAddress;
        private BluetoothHeadset btHeadset;

        BluetoothHeadsetModel(BluetoothAdapter bluetoothAdapter, BluetoothHeadset bluetoothHeadset, String str) {
            this.btAdapter = bluetoothAdapter;
            this.btHeadset = bluetoothHeadset;
            this.btAddress = str;
        }

        public BluetoothAdapter getBluetoothAdapter() {
            return this.btAdapter;
        }

        public String getBluetoothAddress() {
            return this.btAddress;
        }

        public BluetoothHeadset getBluetoothHeadset() {
            return this.btHeadset;
        }
    }

    private BluetoothHeadsetProvider() {
    }

    public static synchronized BluetoothHeadsetProvider getProvider() {
        BluetoothHeadsetProvider bluetoothHeadsetProvider;
        synchronized (BluetoothHeadsetProvider.class) {
            if (provider == null) {
                provider = new BluetoothHeadsetProvider();
            }
            bluetoothHeadsetProvider = provider;
        }
        return bluetoothHeadsetProvider;
    }

    public static synchronized void releaseProvider() {
        synchronized (BluetoothHeadsetProvider.class) {
            if (provider != null) {
                provider.resetState();
            }
            provider = null;
        }
    }

    private synchronized void requestBluetoothHeadsetSetup() {
        Log.i(TAG, "requestBluetoothHeadsetSetup");
        final Context context = DependencyProvider.getContext();
        if (context == null) {
            Log.w(TAG, "requestBluetoothHeadsetSetup - Cannot request because context is null");
            return;
        }
        this.btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (this.btAdapter != null && this.btAdapter.isEnabled()) {
            if (this.btProfileListener == null) {
                this.btProfileListener = new BluetoothProfile.ServiceListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.BluetoothHeadsetProvider.1
                    @Override // android.bluetooth.BluetoothProfile.ServiceListener
                    public synchronized void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                        if (i == 1) {
                            Log.i(BluetoothHeadsetProvider.TAG, "requestBluetoothHeadsetSetup - Bluetooth headset profile connected");
                            BluetoothHeadsetProvider.this.btHeadset = (BluetoothHeadset) bluetoothProfile;
                            BluetoothHeadsetProvider.this.btHeadsetModel = new BluetoothHeadsetModel(BluetoothHeadsetProvider.this.btAdapter, BluetoothHeadsetProvider.this.btHeadset, BluetoothHeadsetProvider.this.zionMacAddress);
                            context.registerReceiver(BluetoothHeadsetProvider.this.btHeadsetConnectionChangeReceiver, BluetoothHeadsetProvider.btHeadsetConnectionChangeIntentFilter);
                        }
                    }

                    @Override // android.bluetooth.BluetoothProfile.ServiceListener
                    public synchronized void onServiceDisconnected(int i) {
                        Log.i(BluetoothHeadsetProvider.TAG, "onServiceDisconnected - Bluetooth headset profile disconnected");
                        BluetoothHeadsetProvider.this.resetState();
                    }
                };
            }
            this.btAdapter.getProfileProxy(context, this.btProfileListener, 1);
            return;
        }
        Log.w(TAG, "requestBluetoothHeadsetSetup - Cannot request because Bluetooth adapter is null or is disabled");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void resetState() {
        Log.i(TAG, "resetState");
        try {
            DependencyProvider.getContext().unregisterReceiver(this.btHeadsetConnectionChangeReceiver);
        } catch (Exception e) {
            String str = TAG;
            Log.w(str, "resetState - Exception when unregistering connection state receiver - " + e);
        }
        try {
            if (this.btAdapter != null && this.btHeadset != null) {
                this.btAdapter.closeProfileProxy(1, this.btHeadset);
            }
        } catch (Exception e2) {
            String str2 = TAG;
            Log.w(str2, "resetState - Exception when closing Headset proxy - " + e2);
        }
        this.btProfileListener = null;
        this.btHeadset = null;
        this.btAdapter = null;
        this.zionMacAddress = "";
    }

    @Nullable
    public synchronized BluetoothHeadsetModel getBluetoothHeadsetModel() {
        return this.btHeadsetModel;
    }

    public synchronized void onZionDeviceConnectionStateChanged(boolean z, String str) {
        String str2 = TAG;
        Log.i(str2, "onZionDeviceConnectionStateChanged - connected: " + z);
        if (!z) {
            resetState();
        } else {
            this.zionMacAddress = str;
            requestBluetoothHeadsetSetup();
        }
    }

    /* renamed from: clone */
    public BluetoothHeadsetProvider m351clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
