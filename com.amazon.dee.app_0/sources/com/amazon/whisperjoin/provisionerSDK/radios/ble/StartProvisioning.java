package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperbridge.ProvisioningStatus;
import com.amazon.whisperbridge.ble.BleGattCharacteristic;
import com.amazon.whisperbridge.ble.command.BleWriteCharacteristicCommand;
import com.amazon.whisperjoin.common.sharedtypes.ble.BleConstants;
import com.amazon.whisperjoin.common.sharedtypes.ble.StartProvisioningRequest;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BLETransportOperationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ProtocolBuffersSerializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
class StartProvisioning implements Callable<ProvisioningStatus> {
    final GattCharacteristicClient mClient;
    final Serializer mSerializer = new ProtocolBuffersSerializer();
    final StartProvisioningResponseFuture mStartProvisioningResponseFuture;
    final StartProvisioningResponseUpdate mStartProvisioningResponseUpdate;

    public StartProvisioning(GattCharacteristicClient gattCharacteristicClient) {
        if (gattCharacteristicClient != null) {
            this.mClient = gattCharacteristicClient;
            this.mStartProvisioningResponseFuture = new StartProvisioningResponseFuture(this.mClient);
            this.mStartProvisioningResponseUpdate = new StartProvisioningResponseUpdate(this.mStartProvisioningResponseFuture);
            this.mClient.addPendingUpdate(this.mStartProvisioningResponseUpdate);
            return;
        }
        throw new IllegalArgumentException("client can not be null");
    }

    private void cancelFuture(Future<?> future) {
        if (future != null) {
            future.cancel(true);
        }
    }

    private void cleanupFuture(Future<BleWriteCharacteristicCommand.Result> future) {
        cancelFuture(future);
        cancel();
    }

    public void cancel() {
        this.mClient.markUpdated(this.mStartProvisioningResponseUpdate);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public ProvisioningStatus mo6642call() throws Exception {
        BleGattCharacteristic bleGattCharacteristic = this.mClient.getBleGattCharacteristic(BleConstants.START_PROVISIONING_REQUEST_CHARACTERISTIC_UUID);
        bleGattCharacteristic.setStoredData(this.mSerializer.serialize(StartProvisioningRequest.createRequest()));
        try {
            Future<BleWriteCharacteristicCommand.Result> writeCharacteristic = this.mClient.writeCharacteristic(bleGattCharacteristic.getUuid());
            int i = writeCharacteristic.get().status;
            if (i == 0) {
                try {
                    if (this.mStartProvisioningResponseFuture.get().getStatus() == 0) {
                        this.mClient.enableProvisioningCommandResponseNotifications();
                        return new ProvisioningStatus(true);
                    }
                    return new ProvisioningStatus(false);
                } catch (Exception e) {
                    throw new BLETransportOperationError(e, BLETransportOperationError.Operation.READ_CHARACTERISTIC_START_PROVISIONING_REQUEST_RESPONSE);
                }
            }
            cleanupFuture(writeCharacteristic);
            throw new BLETransportOperationError(Integer.valueOf(i), BLETransportOperationError.Operation.CONFIRM_WRITE_CHARACTERISTIC_START_PROVISIONING_REQUEST);
        } catch (Exception e2) {
            cleanupFuture(null);
            throw new BLETransportOperationError(e2, BLETransportOperationError.Operation.INITIATE_WRITE_CHARACTERISTIC_START_PROVISIONING_REQUEST);
        }
    }

    public StartProvisioning(GattCharacteristicClient gattCharacteristicClient, StartProvisioningResponseFuture startProvisioningResponseFuture, StartProvisioningResponseUpdate startProvisioningResponseUpdate) {
        if (gattCharacteristicClient != null) {
            this.mClient = gattCharacteristicClient;
            this.mStartProvisioningResponseFuture = startProvisioningResponseFuture;
            this.mStartProvisioningResponseUpdate = startProvisioningResponseUpdate;
            this.mClient.addPendingUpdate(this.mStartProvisioningResponseUpdate);
            return;
        }
        throw new IllegalArgumentException("client can not be null");
    }
}
