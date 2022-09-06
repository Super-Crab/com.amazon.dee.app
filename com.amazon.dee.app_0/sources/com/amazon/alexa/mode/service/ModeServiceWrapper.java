package com.amazon.alexa.mode.service;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.mode.ModeName;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.util.LogTag;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.List;
/* loaded from: classes9.dex */
public class ModeServiceWrapper implements ModeService {
    private static final String INITIALIZE_ERROR = "%s should not be called before mode service is initialized";
    private static final String TAG = LogTag.forClass(ModeServiceWrapper.class);
    private final Context mContext;
    @VisibleForTesting
    ModeService mCurrentModeService;
    private final Handler mMainThreadHandler = new Handler();
    private boolean mServiceInitialized;

    public ModeServiceWrapper(@NonNull Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.alexa.mode.ModeService
    public void destroy() {
    }

    @Override // com.amazon.alexa.mode.ModeService
    public void exitDriveMode(int i) {
        ModeService modeService = this.mCurrentModeService;
        if (modeService == null) {
            Log.e(TAG, String.format(INITIALIZE_ERROR, "exitDriveMode"), new Exception());
        } else {
            modeService.exitDriveMode(i);
        }
    }

    @Override // com.amazon.alexa.mode.ModeService
    public Observable<List<DeviceGroup>> getConnectedAutomotiveAccessories() {
        ModeService modeService = this.mCurrentModeService;
        if (modeService == null) {
            Log.e(TAG, String.format(INITIALIZE_ERROR, "getConnectedAutomotiveAccessories"), new Exception());
            return Observable.empty();
        }
        return modeService.getConnectedAutomotiveAccessories();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public String getMode() {
        ModeService modeService = this.mCurrentModeService;
        if (modeService == null) {
            Log.e(TAG, String.format(INITIALIZE_ERROR, "getMode"), new Exception());
            return ModeName.MAIN_MODE;
        }
        return modeService.getMode();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public void initialize() {
        this.mCurrentModeService = new DefaultModeServiceV2(this.mContext);
        this.mCurrentModeService.initialize();
        this.mServiceInitialized = true;
    }

    @Override // com.amazon.alexa.mode.ModeService
    public BehaviorSubject<Boolean> isAutoBluetoothDeviceConnected() {
        ModeService modeService = this.mCurrentModeService;
        if (modeService == null) {
            Log.e(TAG, String.format(INITIALIZE_ERROR, "isAutoBluetoothDeviceConnected"), new Exception());
            return BehaviorSubject.createDefault(false);
        }
        return modeService.isAutoBluetoothDeviceConnected();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public BehaviorSubject<Boolean> isDriveModeAccessoryDeviceConnected() {
        ModeService modeService = this.mCurrentModeService;
        if (modeService == null) {
            Log.e(TAG, String.format(INITIALIZE_ERROR, "isDriveModeAccessoryDeviceConnected"), new Exception());
            return BehaviorSubject.createDefault(false);
        }
        return modeService.isDriveModeAccessoryDeviceConnected();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public boolean isDriveModeForeground() {
        ModeService modeService = this.mCurrentModeService;
        if (modeService == null) {
            Log.e(TAG, String.format(INITIALIZE_ERROR, "isDriveModeForeground"), new Exception());
            return false;
        }
        return modeService.isDriveModeForeground();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public boolean isErrorInWirelessCharging() {
        ModeService modeService = this.mCurrentModeService;
        if (modeService == null) {
            Log.e(TAG, String.format(INITIALIZE_ERROR, "isErrorInWirelessCharging"), new Exception());
            return false;
        }
        return modeService.isErrorInWirelessCharging();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public boolean isTtsDeviceJustRegistered() {
        ModeService modeService = this.mCurrentModeService;
        if (modeService == null) {
            Log.e(TAG, String.format(INITIALIZE_ERROR, "isTtsDeviceJustRegistered"), new Exception());
            return false;
        }
        return modeService.isTtsDeviceJustRegistered();
    }

    @Override // com.amazon.alexa.mode.ModeService
    public void logModeStatus(String str, String str2, String str3, String str4) {
        ModeService modeService = this.mCurrentModeService;
        if (modeService == null) {
            Log.e(TAG, String.format(INITIALIZE_ERROR, "logModeStatus"), new Exception());
        } else {
            modeService.logModeStatus(str, str2, str3, str4);
        }
    }

    @Override // com.amazon.alexa.mode.ModeService
    public void startDriveMode(int i) {
        ModeService modeService = this.mCurrentModeService;
        if (modeService == null) {
            Log.e(TAG, String.format(INITIALIZE_ERROR, "startDriveMode"), new Exception());
        } else {
            modeService.startDriveMode(i);
        }
    }
}
