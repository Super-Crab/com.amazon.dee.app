package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperbridge.ble.BleGattCharacteristic;
import com.amazon.whisperbridge.ble.BleGattServiceClient;
import com.amazon.whisperbridge.ble.command.BleUpdateNotificationsCommand;
import com.amazon.whisperjoin.common.sharedtypes.ble.BleConstants;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BLETransportOperationError;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
class GattCharacteristicClient extends BleGattServiceClient {
    private static final String TAG = "com.amazon.whisperjoin.provisionerSDK.radios.ble.GattCharacteristicClient";
    ArrayList<GattCharacteristicPendingUpdate> mPendingUpdates;

    public GattCharacteristicClient() {
        super(BleConstants.WHISPER_JOIN_UUID);
        this.mPendingUpdates = new ArrayList<>();
    }

    private void enableNotification(UUID uuid, BLETransportOperationError.Operation operation) throws BLETransportOperationError {
        if (uuid != null) {
            BleGattCharacteristic bleGattCharacteristic = getBleGattCharacteristic(uuid);
            if (bleGattCharacteristic.isNotifiable()) {
                Future<BleUpdateNotificationsCommand.Result> updateCharacteristicNotifiability = updateCharacteristicNotifiability(bleGattCharacteristic.getUuid(), true, false);
                try {
                    int i = updateCharacteristicNotifiability.get().status;
                    if (i == 0) {
                        return;
                    }
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Enable notifications was not successful for UUID: ");
                    outline107.append(uuid.toString());
                    throw new BLETransportOperationError(outline107.toString(), Integer.valueOf(i), operation);
                } catch (Exception e) {
                    updateCharacteristicNotifiability.cancel(true);
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Error enabling notifications for UUID: ");
                    outline1072.append(uuid.toString());
                    throw new BLETransportOperationError(outline1072.toString(), e, operation);
                }
            }
            throw new IllegalArgumentException("Characteristic does not support notifications.");
        }
        throw new IllegalArgumentException("characteristicUuid cannot be null.");
    }

    public void addPendingUpdate(GattCharacteristicPendingUpdate gattCharacteristicPendingUpdate) {
        if (gattCharacteristicPendingUpdate == null) {
            return;
        }
        synchronized (this.mPendingUpdates) {
            this.mPendingUpdates.add(gattCharacteristicPendingUpdate);
        }
    }

    public void enableProvisionableEventNotifications() throws BLETransportOperationError {
        enableNotification(BleConstants.PROVISIONING_NOTIFICATION_CHARACTERISTIC_UUID, BLETransportOperationError.Operation.ENABLE_NOTIFICATION_PROVISIONING_EVENTS);
    }

    public void enableProvisioningCommandResponseNotifications() throws BLETransportOperationError {
        enableNotification(BleConstants.PROVISIONING_COMMAND_RESPONSE_CHARACTERISTIC_UUID, BLETransportOperationError.Operation.ENABLE_NOTIFICATION_PROVISIONING_COMMAND_RESPONSE);
    }

    public void enablePublicNotfications() throws BLETransportOperationError {
        enableNotification(BleConstants.PROVISIONING_STATUS_CHARACTERISTIC_UUID, BLETransportOperationError.Operation.ENABLE_NOTIFICATION_PROVISIONING_STATUS);
        enableNotification(BleConstants.START_PROVISIONING_RESPONSE_CHARACTERISTIC_UUID, BLETransportOperationError.Operation.ENABLE_NOTIFICATION_START_PROVISIONING_REQUEST_RESPONSE);
    }

    public void markUpdated(GattCharacteristicPendingUpdate gattCharacteristicPendingUpdate) {
        if (gattCharacteristicPendingUpdate == null) {
            return;
        }
        synchronized (this.mPendingUpdates) {
            this.mPendingUpdates.remove(gattCharacteristicPendingUpdate);
        }
    }

    @Override // com.amazon.whisperbridge.ble.BleGattServiceClient
    protected void onCharacteristicUpdated(BleGattCharacteristic bleGattCharacteristic) {
        synchronized (this.mPendingUpdates) {
            GattCharacteristicPendingUpdate gattCharacteristicPendingUpdate = null;
            try {
                String str = TAG;
                WJLog.v(str, "pending onCharacteristicUpdated : " + bleGattCharacteristic.getUuid());
                Iterator<GattCharacteristicPendingUpdate> it2 = this.mPendingUpdates.iterator();
                GattCharacteristicPendingStatus gattCharacteristicPendingStatus = null;
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    GattCharacteristicPendingUpdate next = it2.next();
                    GattCharacteristicPendingStatus notify = next.notify(bleGattCharacteristic);
                    if (notify.handled()) {
                        String str2 = TAG;
                        WJLog.v(str2, "handled onCharacteristicUpdated : " + bleGattCharacteristic.getUuid());
                        gattCharacteristicPendingUpdate = next;
                        gattCharacteristicPendingStatus = notify;
                        break;
                    }
                    gattCharacteristicPendingStatus = notify;
                }
                if (gattCharacteristicPendingUpdate == null) {
                    String str3 = TAG;
                    WJLog.v(str3, "not handled onCharacteristicUpdated : " + bleGattCharacteristic.getUuid());
                }
                markUpdated(gattCharacteristicPendingUpdate);
                if (gattCharacteristicPendingStatus != null) {
                    addPendingUpdate(gattCharacteristicPendingStatus.additionalUpdates());
                }
            } catch (Exception e) {
                String str4 = TAG;
                WJLog.e(str4, "issue while processing onCharacteristicUpdated : " + bleGattCharacteristic.getUuid(), e);
                markUpdated(gattCharacteristicPendingUpdate);
            }
        }
    }
}
