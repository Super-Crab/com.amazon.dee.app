package com.amazon.alexa.accessory.notificationpublisher.transcriber;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.BluetoothHeadsetProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.google.common.base.Strings;
/* loaded from: classes.dex */
final class BluetoothAudioRecorder {
    private static final String TAG = "BluetoothAudioRecorder";
    private BluetoothDevice btDevice;
    private BluetoothHeadset btHeadset;

    @Nullable
    private BluetoothHeadsetProvider.BluetoothHeadsetModel getBluetoothHeadsetModel() {
        return BluetoothHeadsetProvider.getProvider().getBluetoothHeadsetModel();
    }

    private BluetoothDevice getBondedZionDevice(BluetoothAdapter bluetoothAdapter, String str) {
        if (Strings.isNullOrEmpty(str)) {
            Log.w(TAG, "getBondedZionDevice - MAC Address null or empty");
            return null;
        } else if (!bluetoothAdapter.isEnabled()) {
            Log.w(TAG, "getBondedZionDevice - BT Adapter is not enabled");
            return null;
        } else {
            for (BluetoothDevice bluetoothDevice : bluetoothAdapter.getBondedDevices()) {
                if (bluetoothDevice.getAddress().equalsIgnoreCase(str)) {
                    Log.i(TAG, "getBondedZionDevice - Found bonded Zion device");
                    return bluetoothDevice;
                }
            }
            return null;
        }
    }

    public boolean closeMicrophone() {
        Log.i(TAG, "closeMicrophone");
        if (getBluetoothHeadsetModel() == null) {
            Log.w(TAG, "closeMicrophone - Zion device BT Headset profile not connected");
            return false;
        }
        BluetoothDevice bluetoothDevice = this.btDevice;
        if (bluetoothDevice == null) {
            Log.w(TAG, "closeMicrophone - No bonded accessory");
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.btHeadset;
        if (bluetoothHeadset == null) {
            Log.w(TAG, "closeMicrophone - BT Headset service not connected");
            return false;
        }
        return bluetoothHeadset.stopVoiceRecognition(bluetoothDevice);
    }

    public boolean openMicrophone() {
        BluetoothHeadset bluetoothHeadset;
        Log.i(TAG, "openMicrophone");
        BluetoothHeadsetProvider.BluetoothHeadsetModel bluetoothHeadsetModel = getBluetoothHeadsetModel();
        if (bluetoothHeadsetModel == null) {
            Log.w(TAG, "openMicrophone - Zion device BT Headset profile not connected");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TRANSCRIBER_NO_HFP);
            return false;
        }
        String bluetoothAddress = bluetoothHeadsetModel.getBluetoothAddress();
        BluetoothDevice bondedZionDevice = getBondedZionDevice(bluetoothHeadsetModel.getBluetoothAdapter(), bluetoothAddress);
        this.btHeadset = bluetoothHeadsetModel.getBluetoothHeadset();
        if (bondedZionDevice != null && (bluetoothHeadset = this.btHeadset) != null && bluetoothHeadset.startVoiceRecognition(bondedZionDevice)) {
            Log.i(TAG, "openMicrophone - Success");
            this.btDevice = bondedZionDevice;
            return true;
        }
        String str = TAG;
        Log.w(str, "openMicrophone - Unable to open microphone for " + bluetoothAddress);
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.TRANSCRIBER_MICROPHONE_ERROR);
        this.btDevice = null;
        return false;
    }
}
