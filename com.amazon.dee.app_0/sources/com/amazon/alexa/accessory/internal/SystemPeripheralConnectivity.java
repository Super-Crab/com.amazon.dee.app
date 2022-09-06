package com.amazon.alexa.accessory.internal;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.PeripheralConnectivity;
import com.amazon.alexa.accessory.internal.repositories.PeripheralDeviceRepository;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Set;
/* loaded from: classes.dex */
public final class SystemPeripheralConnectivity implements PeripheralConnectivity {
    private static final long RFCOMM_DELAY_MS = 1000;
    private static final String TAG = "SystemPeripheralConnectivity:";
    private final Handler handler;
    private final PeripheralDeviceRepository peripheralRepository;

    /* renamed from: com.amazon.alexa.accessory.internal.SystemPeripheralConnectivity$2  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$AccessoryTransport$Type = new int[AccessoryTransport.Type.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$AccessoryTransport$Type[AccessoryTransport.Type.GATT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$AccessoryTransport$Type[AccessoryTransport.Type.RFCOMM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public SystemPeripheralConnectivity(PeripheralDeviceRepository peripheralDeviceRepository) {
        Preconditions.notNull(peripheralDeviceRepository, "peripheralRepository");
        Preconditions.mainThread();
        this.peripheralRepository = peripheralDeviceRepository;
        this.handler = new Handler(Looper.myLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getRfcommConnectionStatus */
    public void lambda$getRfcommConnectionStatusAfterDelay$0$SystemPeripheralConnectivity(final Accessory accessory, final PeripheralConnectivity.Callback callback) {
        Logger.d("%s Querying accessory for RFCOMM connection status: %s", TAG, accessory);
        this.peripheralRepository.queryConnectedBluetoothDevices(new PeripheralDeviceRepository.Callback() { // from class: com.amazon.alexa.accessory.internal.SystemPeripheralConnectivity.1
            @Override // com.amazon.alexa.accessory.internal.repositories.PeripheralDeviceRepository.Callback
            public void onConnectedBluetoothDevicesFound(Set<PeripheralDevice> set) {
                Logger.d("%s A set of connected devices was found %s", SystemPeripheralConnectivity.TAG, set);
                if (set.contains(new PeripheralDevice(accessory))) {
                    callback.onConnectionStatus(PeripheralConnectivity.ConnectionStatus.CONNECTED);
                } else {
                    callback.onConnectionStatus(PeripheralConnectivity.ConnectionStatus.DISCONNECTED);
                }
            }

            @Override // com.amazon.alexa.accessory.internal.repositories.PeripheralDeviceRepository.Callback
            public void onQueryFailed(Throwable th) {
                Logger.e("%s Query for connection RFCOMM connection status of accessory %s returned an error", th, SystemPeripheralConnectivity.TAG, accessory);
                callback.onConnectionStatus(PeripheralConnectivity.ConnectionStatus.UNKNOWN);
            }
        });
    }

    private void getRfcommConnectionStatusAfterDelay(final Accessory accessory, final PeripheralConnectivity.Callback callback) {
        Logger.d("%s accessory %s is RFCOMM. Requesting connected devices after a delay of %dms...", TAG, accessory, 1000L);
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$SystemPeripheralConnectivity$tIG-EytLSLXFYSuYqkAORmbQqpM
            @Override // java.lang.Runnable
            public final void run() {
                SystemPeripheralConnectivity.this.lambda$getRfcommConnectionStatusAfterDelay$0$SystemPeripheralConnectivity(accessory, callback);
            }
        }, 1000L);
    }

    @Override // com.amazon.alexa.accessory.internal.PeripheralConnectivity
    public void getConnectionStatus(Accessory accessory, PeripheralConnectivity.Callback callback) {
        Logger.d("%s getting connection status for %s", TAG, accessory);
        int ordinal = accessory.getTransport().ordinal();
        if (ordinal == 0) {
            Logger.d("PeripheralConnectivity: accessory %s is LE. Connection status is UNKNOWN", accessory);
            callback.onConnectionStatus(PeripheralConnectivity.ConnectionStatus.UNKNOWN);
        } else if (ordinal != 1) {
            callback.onConnectionStatus(PeripheralConnectivity.ConnectionStatus.UNKNOWN);
        } else {
            getRfcommConnectionStatusAfterDelay(accessory, callback);
        }
    }
}
