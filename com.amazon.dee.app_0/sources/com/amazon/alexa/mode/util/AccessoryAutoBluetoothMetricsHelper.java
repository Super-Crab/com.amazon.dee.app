package com.amazon.alexa.mode.util;

import android.util.Log;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.mode.bluetooth.AutoBluetoothObserver;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class AccessoryAutoBluetoothMetricsHelper {
    private static final String TAG = LogTag.forClass(AccessoryAutoBluetoothMetricsHelper.class);
    private Single<Map<String, String>> deviceTypes;
    private Disposable mAccessoryDisposable;
    private AutomotiveAccessoryConnectivityObserver mAccessoryObserver;
    private Disposable mAutoBluetoothDisposable;
    private AutoBluetoothObserver mAutoBluetoothObserver;
    private String mCurrentDeviceType;
    private Map<String, String> mDeviceTypeManufactures;
    private final Lazy<DriveModeMetrics> mDriveModeMetrics;

    public AccessoryAutoBluetoothMetricsHelper(AutoBluetoothObserver autoBluetoothObserver, AutomotiveAccessoryConnectivityObserver automotiveAccessoryConnectivityObserver, Lazy<DriveModeMetrics> lazy, Single<Map<String, String>> single) {
        this.mAutoBluetoothObserver = autoBluetoothObserver;
        this.mAccessoryObserver = automotiveAccessoryConnectivityObserver;
        this.mDriveModeMetrics = lazy;
        this.deviceTypes = single;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$init$2(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "AutoBluetoothObserver - Unexpected error: " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$init$4(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "AccessoryObserver - Unexpected error: " + th);
    }

    private void logAccessoryMetric(List<DeviceGroup> list) {
        if (getAutoBluetoothDisposable() != null ? this.mAutoBluetoothObserver.isConnectedToAutoBluetooth().getValue().booleanValue() : false) {
            this.mDriveModeMetrics.mo358get().logAccessoryConnectedWithAutoBluetoothConnectedWithTimers();
            String currentDeviceType = getCurrentDeviceType();
            if (currentDeviceType == null) {
                return;
            }
            this.mDriveModeMetrics.mo358get().logAccessoryConnectedWithAutoBluetoothConnected(currentDeviceType);
            return;
        }
        this.mDriveModeMetrics.mo358get().logAccessoryTimerEndedWithTimers();
        this.mDriveModeMetrics.mo358get().logAccessoryConnectedWithAutoBluetoothDisconnectedWithTimers();
        String currentDeviceType2 = getCurrentDeviceType();
        if (currentDeviceType2 == null) {
            return;
        }
        this.mDriveModeMetrics.mo358get().logAccessoryConnectedWithAutoBluetoothDisconnected(currentDeviceType2);
    }

    private void logAutoBluetoothMetric() {
        if (getAccessoryDisposable() != null ? this.mAccessoryObserver.isAutomotiveAccessoryConnected().getValue().booleanValue() : false) {
            this.mDriveModeMetrics.mo358get().logAutoBluetoothConnectedWithAccessoryConnectedWithTimers();
            String currentDeviceType = getCurrentDeviceType();
            if (currentDeviceType == null) {
                return;
            }
            this.mDriveModeMetrics.mo358get().logAutoBluetoothConnectedWithAccessoryConnected(currentDeviceType);
            return;
        }
        this.mDriveModeMetrics.mo358get().logAutoBluetoothConnectedWithAccessoryDisconnectedWithTimers();
    }

    public void destroy() {
        Disposable disposable = this.mAutoBluetoothDisposable;
        if (disposable != null) {
            disposable.dispose();
            this.mAutoBluetoothDisposable = null;
        }
        Disposable disposable2 = this.mAccessoryDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
            this.mAccessoryDisposable = null;
        }
    }

    Disposable getAccessoryDisposable() {
        return this.mAccessoryDisposable;
    }

    Disposable getAutoBluetoothDisposable() {
        return this.mAutoBluetoothDisposable;
    }

    String getCurrentDeviceType() {
        String str;
        if (getDeviceTypeManufacturesMap() == null || (str = getDeviceTypeManufacturesMap().get(this.mCurrentDeviceType)) == null) {
            return null;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1212377694:
                if (str.equals(DriveModeMetrics.DeviceType.IOTTIE)) {
                    c = 1;
                    break;
                }
                break;
            case 69498:
                if (str.equals("G&Y")) {
                    c = 3;
                    break;
                }
                break;
            case 384370744:
                if (str.equals("JVC Kenwood")) {
                    c = 4;
                    break;
                }
                break;
            case 1489454180:
                if (str.equals(DriveModeMetrics.DeviceType.NEXTBASE)) {
                    c = 2;
                    break;
                }
                break;
            case 1964569124:
                if (str.equals("Amazon")) {
                    c = 0;
                    break;
                }
                break;
        }
        if (c == 0) {
            return DriveModeMetrics.DeviceType.MUFFIN;
        }
        if (c == 1) {
            return DriveModeMetrics.DeviceType.IOTTIE;
        }
        if (c == 2) {
            return DriveModeMetrics.DeviceType.NEXTBASE;
        }
        if (c == 3) {
            return DriveModeMetrics.DeviceType.GYCOM;
        }
        if (c == 4) {
            return DriveModeMetrics.DeviceType.JVC;
        }
        return null;
    }

    Map<String, String> getDeviceTypeManufacturesMap() {
        return this.mDeviceTypeManufactures;
    }

    public void init() {
        Single<Map<String, String>> single = this.deviceTypes;
        if (single != null) {
            single.subscribe(new Consumer() { // from class: com.amazon.alexa.mode.util.-$$Lambda$AccessoryAutoBluetoothMetricsHelper$jhjp1i2r60WROKw_3rRvFN_OHnk
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AccessoryAutoBluetoothMetricsHelper.this.lambda$init$0$AccessoryAutoBluetoothMetricsHelper((Map) obj);
                }
            });
        }
        AutoBluetoothObserver autoBluetoothObserver = this.mAutoBluetoothObserver;
        if (autoBluetoothObserver != null && autoBluetoothObserver.isConnectedToAutoBluetooth() != null) {
            this.mAutoBluetoothDisposable = this.mAutoBluetoothObserver.isConnectedToAutoBluetooth().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.util.-$$Lambda$AccessoryAutoBluetoothMetricsHelper$MHPI5-bV0KBKhuoGCz55p6xXe9M
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AccessoryAutoBluetoothMetricsHelper.this.lambda$init$1$AccessoryAutoBluetoothMetricsHelper((Boolean) obj);
                }
            }, $$Lambda$AccessoryAutoBluetoothMetricsHelper$H4LD2clqlI5pQcKebBSUd1pi1vU.INSTANCE);
        }
        AutomotiveAccessoryConnectivityObserver automotiveAccessoryConnectivityObserver = this.mAccessoryObserver;
        if (automotiveAccessoryConnectivityObserver == null || automotiveAccessoryConnectivityObserver.connectedAutomotiveDeviceGroups() == null) {
            return;
        }
        this.mAccessoryDisposable = this.mAccessoryObserver.connectedAutomotiveDeviceGroups().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.util.-$$Lambda$AccessoryAutoBluetoothMetricsHelper$7kyCF2Ers4A9PDBH_HqgWcFVb-w
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryAutoBluetoothMetricsHelper.this.lambda$init$3$AccessoryAutoBluetoothMetricsHelper((List) obj);
            }
        }, $$Lambda$AccessoryAutoBluetoothMetricsHelper$1evmpP9n73ZbO0YzsnY1MWXeFvM.INSTANCE);
    }

    public /* synthetic */ void lambda$init$0$AccessoryAutoBluetoothMetricsHelper(Map map) throws Throwable {
        this.mDeviceTypeManufactures = map;
    }

    public /* synthetic */ void lambda$init$1$AccessoryAutoBluetoothMetricsHelper(Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            logAutoBluetoothMetric();
        }
    }

    public /* synthetic */ void lambda$init$3$AccessoryAutoBluetoothMetricsHelper(List list) throws Throwable {
        Device device;
        if (list.size() <= 0 || (device = ((DeviceGroup) list.get(0)).getDevice()) == null) {
            return;
        }
        this.mCurrentDeviceType = device.getType();
        logAccessoryMetric(list);
    }
}
