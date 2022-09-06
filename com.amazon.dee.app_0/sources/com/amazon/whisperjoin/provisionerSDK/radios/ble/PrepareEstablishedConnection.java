package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import android.content.Context;
import com.amazon.whisperbridge.ble.BleGattClient;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BLETransportOperationError;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.util.FireOSUtil;
import java.util.concurrent.Callable;
/* loaded from: classes13.dex */
class PrepareEstablishedConnection implements Callable<Void> {
    private static final int MAX_MTU = 512;
    private static final String TAG = "PrepareEstablishedConnection";
    private FireOSUtil mFireOSUtil;
    BleGattClient mGattClient;
    GattCharacteristicClient mServiceClient;

    public PrepareEstablishedConnection(Context context) {
        this.mFireOSUtil = new FireOSUtil(context);
    }

    private void bindServiceClient() throws BLETransportOperationError {
        if (this.mGattClient.bindServiceClient(this.mServiceClient)) {
            return;
        }
        throw new BLETransportOperationError(BLETransportOperationError.Operation.BIND_SERVICE_CLIENT);
    }

    private void discoverServices() throws BLETransportOperationError {
        try {
            if (this.mGattClient.enqueueDiscoverServices().get().status != 0) {
                throw new BLETransportOperationError(BLETransportOperationError.Operation.DISCOVER_GATT_SERVICES);
            }
        } catch (Exception e) {
            throw new BLETransportOperationError(e, BLETransportOperationError.Operation.DISCOVER_GATT_SERVICES);
        }
    }

    public void setBleClients(BleGattClient bleGattClient, GattCharacteristicClient gattCharacteristicClient) {
        if (bleGattClient != null) {
            if (gattCharacteristicClient != null) {
                this.mGattClient = bleGattClient;
                this.mServiceClient = gattCharacteristicClient;
                return;
            }
            throw new IllegalArgumentException("serviceClient can not be null");
        }
        throw new IllegalArgumentException("gattClient can not be null");
    }

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        if (this.mGattClient != null) {
            if (this.mServiceClient != null) {
                discoverServices();
                bindServiceClient();
                this.mServiceClient.enablePublicNotfications();
                if (this.mFireOSUtil.isFireTvDevice()) {
                    WJLog.d(TAG, "Setting connection priority to: 0 for FireTV");
                    this.mGattClient.enqueueRequestConnectionPriorityChange(0);
                } else {
                    WJLog.d(TAG, "Setting connection priority to: 1");
                    this.mGattClient.enqueueRequestConnectionPriorityChange(1);
                }
                this.mGattClient.enqueueRequestMtuChange(512);
                return null;
            }
            throw new RuntimeException("mServiceClient can not be null");
        }
        throw new RuntimeException("mGattClient can not be null");
    }
}
