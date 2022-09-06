package com.amazon.whisperbridge.ble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
@TargetApi(21)
/* loaded from: classes13.dex */
public class BleAdvertiser extends AdvertiseCallback {
    private static final String TAG = BleAdvertiser.class.getSimpleName();
    BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    private final Callback mCallback;

    /* loaded from: classes13.dex */
    public interface Callback {
        void onAdvertisingStateChanged(State state);
    }

    /* loaded from: classes13.dex */
    public enum State implements Parcelable {
        SUCCESS(0, "Success"),
        ADVERTISE_FAILED_ALREADY_STARTED(3, "Advertise failed, already started"),
        ADVERTISE_FAILED_DATA_TOO_LARGE(1, "Advertise failed, data too large"),
        ADVERTISE_FAILED_FEATURE_UNSUPPORTED(5, "Advertise failed, feature unsupported"),
        ADVERTISE_FAILED_INTERNAL_ERROR(4, "Advertise failed, internal error"),
        ADVERTISE_FAILED_TOO_MANY_ADVERTISERS(2, "Advertise failed, too many advertisers");
        
        public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() { // from class: com.amazon.whisperbridge.ble.BleAdvertiser.State.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public State mo5058createFromParcel(Parcel parcel) {
                return State.fromInt(parcel.readInt());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public State[] mo5059newArray(int i) {
                return new State[i];
            }
        };
        private String mString;
        private int mValue;

        State(int i, String str) {
            this.mValue = i;
            this.mString = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static State fromInt(int i) {
            if (i != 0) {
                if (i == 1) {
                    return ADVERTISE_FAILED_DATA_TOO_LARGE;
                }
                if (i == 2) {
                    return ADVERTISE_FAILED_TOO_MANY_ADVERTISERS;
                }
                if (i == 3) {
                    return ADVERTISE_FAILED_ALREADY_STARTED;
                }
                if (i == 4) {
                    return ADVERTISE_FAILED_INTERNAL_ERROR;
                }
                if (i == 5) {
                    return ADVERTISE_FAILED_FEATURE_UNSUPPORTED;
                }
                throw new IllegalArgumentException("Unknown BleAdvertiser.State encountered.");
            }
            return SUCCESS;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getString() {
            return this.mString;
        }

        public int getValue() {
            return this.mValue;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mString;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    private BleAdvertiser(Callback callback) {
        this.mCallback = callback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(21)
    public static BleAdvertiser startAdvertising(BluetoothAdapter bluetoothAdapter, Callback callback, AdvertiseSettings advertiseSettings, AdvertiseData advertiseData, AdvertiseData advertiseData2) throws BleException {
        if (bluetoothAdapter != null) {
            if (callback == null) {
                throw new IllegalArgumentException("callback unexpectedly null.");
            }
            if (advertiseSettings == null) {
                throw new IllegalArgumentException("settings unexpectedly null.");
            }
            if (advertiseData != null) {
                int i = Build.VERSION.SDK_INT;
                BleAdvertiser bleAdvertiser = new BleAdvertiser(callback);
                bleAdvertiser.mBluetoothLeAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
                if (bleAdvertiser.mBluetoothLeAdvertiser != null) {
                    WJLog.i(TAG, "Starting Bluetooth LE advertisement.");
                    if (advertiseData2 == null) {
                        bleAdvertiser.mBluetoothLeAdvertiser.startAdvertising(advertiseSettings, advertiseData, bleAdvertiser);
                    } else {
                        bleAdvertiser.mBluetoothLeAdvertiser.startAdvertising(advertiseSettings, advertiseData, advertiseData2, bleAdvertiser);
                    }
                    return bleAdvertiser;
                }
                throw new BleException("Failed to get Bluetooth LE Advertiser. Bluetooth may be disabled or Bluetooth LE advertising is not supported.");
            }
            throw new IllegalArgumentException("advertisingData unexpectedly null.");
        }
        throw new IllegalArgumentException("btAdapter unexpectedly null.");
    }

    @Override // android.bluetooth.le.AdvertiseCallback
    public synchronized void onStartFailure(int i) {
        String str = TAG;
        WJLog.e(str, "onStartFailure: errorCode=" + i);
        this.mCallback.onAdvertisingStateChanged(State.fromInt(i));
    }

    @Override // android.bluetooth.le.AdvertiseCallback
    public synchronized void onStartSuccess(AdvertiseSettings advertiseSettings) {
        String str = TAG;
        WJLog.d(str, "onStartSuccess: settings=" + advertiseSettings);
        this.mCallback.onAdvertisingStateChanged(State.SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void stopAdvertising() {
        if (this.mBluetoothLeAdvertiser != null) {
            WJLog.i(TAG, "Stopping Bluetooth LE advertisement.");
            this.mBluetoothLeAdvertiser.stopAdvertising(this);
            this.mBluetoothLeAdvertiser = null;
        } else {
            throw new IllegalStateException("Attempting to stop advertising while not advertising.");
        }
    }
}
