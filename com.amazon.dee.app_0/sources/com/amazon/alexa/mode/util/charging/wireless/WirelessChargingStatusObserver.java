package com.amazon.alexa.mode.util.charging.wireless;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessory.repositories.state.StateRepository;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.mode.util.AutomotiveAccessoryConnectivityObserver;
import com.amazon.alexa.mode.util.LogTag;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class WirelessChargingStatusObserver {
    private static final int NO_UNUSUAL_ACTIVITY_STATUS = 0;
    private static final String TAG = LogTag.forClass(WirelessChargingStatusObserver.class);
    private Disposable mAccessoryDisposable;
    private final AutomotiveAccessoryConnectivityObserver mAutomotiveDeviceObserver;
    private final Context mContext;
    private Map<String, Integer> chargingStatuses = new HashMap();
    PublishSubject<Boolean> wirelessChargingErrorStatus = PublishSubject.create();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public enum WirelessChargingStatus {
        WirelessChargingError,
        WirelessChargingNoError
    }

    public WirelessChargingStatusObserver(@NonNull Context context, AutomotiveAccessoryConnectivityObserver automotiveAccessoryConnectivityObserver) {
        this.mContext = context;
        this.mAutomotiveDeviceObserver = automotiveAccessoryConnectivityObserver;
    }

    private WirelessChargingStatus getCurrentWirelessChargingStatus() {
        if (this.chargingStatuses.isEmpty()) {
            return WirelessChargingStatus.WirelessChargingNoError;
        }
        for (Map.Entry<String, Integer> entry : this.chargingStatuses.entrySet()) {
            if (isChargingSuccessful(entry.getValue().intValue()).booleanValue()) {
                return WirelessChargingStatus.WirelessChargingNoError;
            }
        }
        return WirelessChargingStatus.WirelessChargingError;
    }

    private Boolean isChargingSuccessful(int i) {
        byte[] bArr = new byte[4];
        ByteBuffer.wrap(bArr).putInt(i);
        byte b = bArr[3];
        byte b2 = bArr[2];
        boolean z = true;
        byte b3 = bArr[1];
        if (b2 != 0) {
            z = false;
        }
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("chargingStatusIndicator : ", b, " unusualActivityIndicator : ", b2, " efficiencyIndicator : ");
        outline110.append((int) b3);
        outline110.append(" isWirelessChargingSuccessful : ");
        outline110.append(z);
        outline110.toString();
        return Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: observeWirelessChargingStatusChanges */
    public void lambda$startObservingForDeviceConnectionChanges$0$WirelessChargingStatusObserver(List<DeviceGroup> list) {
        if (list.size() <= 0) {
            this.chargingStatuses.clear();
            publishWirelessChargingStatus(WirelessChargingStatus.WirelessChargingNoError);
            return;
        }
        for (final DeviceGroup deviceGroup : list) {
            getStateRepository(deviceGroup).query(StateFeature.CHARGING_STATUS).subscribeOn(Schedulers.computation()).subscribe(new Consumer<StateOuterClass.State>() { // from class: com.amazon.alexa.mode.util.charging.wireless.WirelessChargingStatusObserver.1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public void accept(StateOuterClass.State state) {
                    WirelessChargingStatusObserver.this.chargingStatuses.put(deviceGroup.getIdentifier(), Integer.valueOf(state.getInteger()));
                    WirelessChargingStatusObserver.this.publishCurrentWirelessChargingStatus();
                }
            }, new Consumer<Throwable>() { // from class: com.amazon.alexa.mode.util.charging.wireless.WirelessChargingStatusObserver.2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public void accept(Throwable th) {
                    WirelessChargingStatusObserver.this.chargingStatuses.remove(deviceGroup.getIdentifier());
                    WirelessChargingStatusObserver.this.publishCurrentWirelessChargingStatus();
                }
            }, new Action() { // from class: com.amazon.alexa.mode.util.charging.wireless.WirelessChargingStatusObserver.3
                @Override // io.reactivex.rxjava3.functions.Action
                public void run() {
                    WirelessChargingStatusObserver.this.chargingStatuses.remove(deviceGroup.getIdentifier());
                    WirelessChargingStatusObserver.this.publishCurrentWirelessChargingStatus();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishCurrentWirelessChargingStatus() {
        publishWirelessChargingStatus(getCurrentWirelessChargingStatus());
    }

    private void publishWirelessChargingStatus(WirelessChargingStatus wirelessChargingStatus) {
        this.wirelessChargingErrorStatus.onNext(Boolean.valueOf(wirelessChargingStatus != WirelessChargingStatus.WirelessChargingNoError));
    }

    StateRepository getStateRepository(DeviceGroup deviceGroup) {
        return Accessories.Shared.INSTANCE.get(this.mContext).getAccessorySession(deviceGroup.getIdentifier()).getStateRepository();
    }

    public Observable<Boolean> getWirelessChargingErrorStatusObservable() {
        return this.wirelessChargingErrorStatus.distinctUntilChanged();
    }

    public boolean isErrorInWirelessCharging() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getCurrentWirelessChargingStatus ");
        outline107.append(getCurrentWirelessChargingStatus());
        outline107.toString();
        return getCurrentWirelessChargingStatus() == WirelessChargingStatus.WirelessChargingError;
    }

    public void startObservingForDeviceConnectionChanges() {
        if (this.mAccessoryDisposable != null) {
            Log.w(TAG, "startObservingForDeviceConnectionChanges | Not re-subscribing again");
        } else {
            this.mAccessoryDisposable = this.mAutomotiveDeviceObserver.connectedAutomotiveDeviceGroups().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.util.charging.wireless.-$$Lambda$WirelessChargingStatusObserver$wvpNqiribM16PvmRBV7EEV7kROc
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WirelessChargingStatusObserver.this.lambda$startObservingForDeviceConnectionChanges$0$WirelessChargingStatusObserver((List) obj);
                }
            }, $$Lambda$WirelessChargingStatusObserver$qU6WtHAj3uJpS0QAdKc3XqzVLgc.INSTANCE);
        }
    }

    public void stopObservingForDeviceConnectionChanges() {
        Disposable disposable = this.mAccessoryDisposable;
        if (disposable == null) {
            Log.e(TAG, "stopObservingForDeviceConnectionChanges |  nothing to unsubscribe from");
            return;
        }
        disposable.dispose();
        this.mAccessoryDisposable = null;
        this.chargingStatuses.clear();
    }
}
