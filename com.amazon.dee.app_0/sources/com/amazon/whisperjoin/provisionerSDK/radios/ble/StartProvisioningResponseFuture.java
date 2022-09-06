package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperjoin.common.sharedtypes.ble.BleConstants;
import com.amazon.whisperjoin.common.sharedtypes.ble.StartProvisioningResponse;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ProtocolBuffersSerializer;
import com.google.common.reflect.TypeToken;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/* loaded from: classes13.dex */
public class StartProvisioningResponseFuture extends FutureTask<StartProvisioningResponse> {
    public StartProvisioningResponseFuture(final GattCharacteristicClient gattCharacteristicClient) {
        super(new Callable() { // from class: com.amazon.whisperjoin.provisionerSDK.radios.ble.StartProvisioningResponseFuture.1
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public StartProvisioningResponse mo6643call() throws Exception {
                return (StartProvisioningResponse) new ProtocolBuffersSerializer().deserialize(GattCharacteristicClient.this.getBleGattCharacteristic(BleConstants.START_PROVISIONING_RESPONSE_CHARACTERISTIC_UUID).getStoredData(), TypeToken.of(StartProvisioningResponse.class));
            }
        });
    }
}
