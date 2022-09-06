package com.amazon.alexa.mode;

import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.List;
/* loaded from: classes9.dex */
public interface ModeService {
    void destroy();

    void exitDriveMode(int i);

    Observable<List<DeviceGroup>> getConnectedAutomotiveAccessories();

    String getMode();

    void initialize();

    BehaviorSubject<Boolean> isAutoBluetoothDeviceConnected();

    BehaviorSubject<Boolean> isDriveModeAccessoryDeviceConnected();

    boolean isDriveModeForeground();

    boolean isErrorInWirelessCharging();

    boolean isTtsDeviceJustRegistered();

    void logModeStatus(String str, String str2, String str3, String str4);

    void startDriveMode(int i);
}
