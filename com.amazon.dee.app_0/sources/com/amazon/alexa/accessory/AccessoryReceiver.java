package com.amazon.alexa.accessory;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelUuid;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.bluetooth.SdpRecords;
import com.amazon.alexa.accessory.internal.bluetooth.SdpRepository;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class AccessoryReceiver extends BroadcastReceiver {
    @VisibleForTesting
    static final long GO_ASYNC_MAX_MILLIS = 7000;
    private static final String TAG = "AccessoryReceiver:";

    @SuppressLint({"CheckResult"})
    @VisibleForTesting
    static void aclDeviceConnected(final PeripheralDevice peripheralDevice, SdpRepository sdpRepository, UUID uuid, final BroadcastReceiver.PendingResult pendingResult, final long j, final long j2) {
        Preconditions.notNull(peripheralDevice, "peripheralDevice");
        Preconditions.notNull(sdpRepository, "sdpRepository");
        Preconditions.notNull(uuid, "rfcommChannelId");
        Preconditions.notNull(pendingResult, "pendingResult");
        isAccessory(peripheralDevice, sdpRepository, uuid, j).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.-$$Lambda$AccessoryReceiver$oalU8H19yjz_04xA3009eMPi108
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryReceiver.lambda$aclDeviceConnected$0(j2, j, peripheralDevice, pendingResult, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.-$$Lambda$AccessoryReceiver$_nD5NUeTx97Rz5BExW-CZ3h83MQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryReceiver.lambda$aclDeviceConnected$1(PeripheralDevice.this, pendingResult, (Throwable) obj);
            }
        });
    }

    @Nullable
    @VisibleForTesting
    static Thread finishPendingResult(final BroadcastReceiver.PendingResult pendingResult, final long j) {
        Preconditions.notNull(pendingResult, "pendingResult");
        if (j == 0) {
            Logger.d("%s Calling finish on goAsync's PendingResult", TAG);
            pendingResult.finish();
            return null;
        }
        Thread thread = new Thread(new Runnable() { // from class: com.amazon.alexa.accessory.-$$Lambda$AccessoryReceiver$hDa91gJ2HpyTXZm9lNsX4a6Ch2w
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryReceiver.lambda$finishPendingResult$3(j, pendingResult);
            }
        });
        thread.start();
        return thread;
    }

    private static Single<Boolean> isAccessory(final PeripheralDevice peripheralDevice, final SdpRepository sdpRepository, final UUID uuid, long j) {
        Preconditions.notNull(peripheralDevice, "peripheralDevice");
        Preconditions.notNull(sdpRepository, "sdpRepository");
        Preconditions.notNull(uuid, "rfcommChannelId");
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.-$$Lambda$AccessoryReceiver$hFk5OuAk7IR0MaWWCx-94YYzn4c
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                SdpRepository.this.getOrFetch(peripheralDevice, new SdpRepository.FetchListener() { // from class: com.amazon.alexa.accessory.AccessoryReceiver.1
                    @Override // com.amazon.alexa.accessory.internal.bluetooth.SdpRepository.FetchListener
                    public void onError(PeripheralDevice peripheralDevice2, Throwable th) {
                        if (SingleEmitter.this.isDisposed()) {
                            return;
                        }
                        SingleEmitter.this.onError(th);
                    }

                    @Override // com.amazon.alexa.accessory.internal.bluetooth.SdpRepository.FetchListener
                    public void onSuccess(PeripheralDevice peripheralDevice2, SdpRecords sdpRecords) {
                        if (SingleEmitter.this.isDisposed()) {
                            return;
                        }
                        SingleEmitter.this.onSuccess(Boolean.valueOf(sdpRecords.containsUuid(r2)));
                    }
                }, new ParcelUuid(uuid));
            }
        }).timeout(j, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$aclDeviceConnected$0(long j, long j2, PeripheralDevice peripheralDevice, BroadcastReceiver.PendingResult pendingResult, Boolean bool) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (bool.booleanValue()) {
            long j3 = j2 - currentTimeMillis;
            if (j3 <= 0) {
                j3 = 0;
            }
            Logger.d("%s Took %d milliseconds to determine connecting device %s is an accessory, finishing pending result in %d milliseconds", TAG, Long.valueOf(currentTimeMillis), peripheralDevice, Long.valueOf(j3));
            finishPendingResult(pendingResult, j3);
            return;
        }
        Logger.d("%s Took %d milliseconds to determine connecting device %s is not an accessory, finishing pendingResult immediately", TAG, Long.valueOf(currentTimeMillis), peripheralDevice);
        finishPendingResult(pendingResult, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$aclDeviceConnected$1(PeripheralDevice peripheralDevice, BroadcastReceiver.PendingResult pendingResult, Throwable th) throws Throwable {
        Logger.e("%s Error occurred in determining if connecting device %s is an  accessory, finishing pendingResult immediately", th, TAG, peripheralDevice);
        finishPendingResult(pendingResult, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$finishPendingResult$3(long j, BroadcastReceiver.PendingResult pendingResult) {
        try {
            try {
                Thread.sleep(j);
                Logger.d("%s Calling finish on goAsync's PendingResult", TAG);
            } catch (InterruptedException e) {
                Logger.e("%s sleep was interrupted for thread that is responsible for calling finish() on goAsync's PendingResult", e, TAG);
                Logger.d("%s Calling finish on goAsync's PendingResult", TAG);
            }
            pendingResult.finish();
        } catch (Throwable th) {
            Logger.d("%s Calling finish on goAsync's PendingResult", TAG);
            pendingResult.finish();
            throw th;
        }
    }

    @Override // android.content.BroadcastReceiver
    @SuppressLint({"CheckResult"})
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.bluetooth.device.action.ACL_CONNECTED".equals(action)) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice == null) {
                Logger.e("%s device was null in ACL_CONNECTED event, ignoring", TAG);
                return;
            }
            PeripheralDevice peripheralDevice = new PeripheralDevice(bluetoothDevice);
            if (!Accessories.hasSharedInstance()) {
                Logger.e("%s  No Accessories SharedInstance available", TAG);
                return;
            }
            AccessoryExplorer explorer = Accessories.getSharedInstance().getExplorer();
            Logger.d("%s Received intent %s, application is alive, calling goAsync()", TAG, action);
            aclDeviceConnected(peripheralDevice, explorer.getSdpRepository(), explorer.getAccessoryAttributes().rfcommChannelId(), goAsync(), GO_ASYNC_MAX_MILLIS, System.currentTimeMillis());
        }
    }
}
