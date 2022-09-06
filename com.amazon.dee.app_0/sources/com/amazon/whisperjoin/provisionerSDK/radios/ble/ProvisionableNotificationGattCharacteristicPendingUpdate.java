package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperbridge.Transport;
import com.amazon.whisperbridge.ble.BleGattCharacteristic;
import com.amazon.whisperjoin.common.sharedtypes.ble.BleConstants;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventNotification;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ProtocolBuffersSerializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.google.common.reflect.TypeToken;
/* loaded from: classes13.dex */
class ProvisionableNotificationGattCharacteristicPendingUpdate implements GattCharacteristicPendingUpdate {
    private final Transport.ProvisionableEventNotificationCallback mCallback;
    private Serializer mSerializer = new ProtocolBuffersSerializer();

    public ProvisionableNotificationGattCharacteristicPendingUpdate(Transport.ProvisionableEventNotificationCallback provisionableEventNotificationCallback) {
        this.mCallback = provisionableEventNotificationCallback;
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.radios.ble.GattCharacteristicPendingUpdate
    public GattCharacteristicPendingStatus notify(BleGattCharacteristic bleGattCharacteristic) {
        if (!BleConstants.PROVISIONING_NOTIFICATION_CHARACTERISTIC_UUID.equals(bleGattCharacteristic.getUuid())) {
            return new GattCharacteristicPendingStatus(false, null);
        }
        this.mCallback.onProvisionableEventNotification((ProvisionableEventNotification) this.mSerializer.deserialize(bleGattCharacteristic.getStoredData(), TypeToken.of(ProvisionableEventNotification.class)));
        return new GattCharacteristicPendingStatus(true, this);
    }
}
