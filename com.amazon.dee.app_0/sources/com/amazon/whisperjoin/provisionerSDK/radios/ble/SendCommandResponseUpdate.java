package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperbridge.ble.BleGattCharacteristic;
import com.amazon.whisperbridge.ble.command.BleReadCharacteristicCommand;
import com.amazon.whisperbridge.ble.command.BleWriteCharacteristicCommand;
import com.amazon.whisperjoin.common.sharedtypes.ble.BleConstants;
import com.amazon.whisperjoin.common.sharedtypes.ble.WhisperJoinBlePacket;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BLETransportOperationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ProtocolBuffersSerializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.reflect.TypeToken;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/* loaded from: classes13.dex */
public class SendCommandResponseUpdate implements GattCharacteristicPendingUpdate {
    private static final String TAG = "com.amazon.whisperjoin.provisionerSDK.radios.ble.SendCommandResponseUpdate";
    private final GattCharacteristicClient mClient;
    private final ByteArrayOutputStream mOutstream;
    private final FutureTask<byte[]> mSendCommandResponseFuture;
    private final Serializer mSerializer = new ProtocolBuffersSerializer();

    public SendCommandResponseUpdate(GattCharacteristicClient gattCharacteristicClient, ByteArrayOutputStream byteArrayOutputStream, FutureTask<byte[]> futureTask) {
        this.mClient = gattCharacteristicClient;
        this.mOutstream = byteArrayOutputStream;
        this.mSendCommandResponseFuture = futureTask;
    }

    private GattCharacteristicPendingStatus readCommandReponse(FutureTask futureTask, ByteArrayOutputStream byteArrayOutputStream, GattCharacteristicPendingUpdate gattCharacteristicPendingUpdate) {
        Future<BleReadCharacteristicCommand.Result> future;
        try {
            future = this.mClient.readCharacteristic(BleConstants.PROVISIONING_COMMAND_RESPONSE_CHARACTERISTIC_UUID);
        } catch (Exception e) {
            e = e;
            future = null;
        }
        try {
            WJLog.v(TAG, "reading provisioning command response");
            int i = future.get().status;
            WJLog.v(TAG, "provisioning command read status : " + i);
            if (i == 0) {
                BleGattCharacteristic bleGattCharacteristic = this.mClient.getBleGattCharacteristic(BleConstants.PROVISIONING_COMMAND_RESPONSE_CHARACTERISTIC_UUID);
                WhisperJoinBlePacket whisperJoinBlePacket = (WhisperJoinBlePacket) this.mSerializer.deserialize(bleGattCharacteristic.getStoredData(), TypeToken.of(WhisperJoinBlePacket.class));
                byte[] payload = whisperJoinBlePacket.getPayload();
                byteArrayOutputStream.write(payload);
                WJLog.byteStruct(TAG, "Received Incoming Packet Payload " + whisperJoinBlePacket.toString(), payload);
                WhisperJoinBlePacket createNextResponsePacket = WhisperJoinBlePacket.createNextResponsePacket(whisperJoinBlePacket);
                WJLog.byteStruct(TAG, "Next Payload Request Packet: " + createNextResponsePacket.toString(), createNextResponsePacket.getPayload());
                byte[] serialize = this.mSerializer.serialize(createNextResponsePacket);
                WJLog.byteStruct(TAG, "Writing Serialized Next Payload Request Packet", serialize);
                bleGattCharacteristic.setStoredData(serialize);
                try {
                    Future<BleWriteCharacteristicCommand.Result> writeCharacteristic = this.mClient.writeCharacteristic(BleConstants.PROVISIONING_COMMAND_RESPONSE_CHARACTERISTIC_UUID);
                    WJLog.v(TAG, "waiting for response packet to be acknowledged");
                    int i2 = writeCharacteristic.get().status;
                    if (i2 == 0) {
                        if (whisperJoinBlePacket.hasAdditionalChunks()) {
                            WJLog.v(TAG, "additional packets pending");
                            return new GattCharacteristicPendingStatus(true, gattCharacteristicPendingUpdate);
                        }
                        WJLog.v(TAG, "all packets response packets processed");
                        futureTask.run();
                        return new GattCharacteristicPendingStatus(true, null);
                    }
                    throw new BLETransportOperationError("Failed to send command, next payload request status was not successful", Integer.valueOf(i2), BLETransportOperationError.Operation.CONFIRM_WRITE_CHARACTERISTIC_REQUEST_ADDITIONAL_COMMAND_RESPONSE_PACKET);
                } catch (Exception e2) {
                    throw new BLETransportOperationError("Error while trying to write request for next packet", e2, BLETransportOperationError.Operation.INITIATE_WRITE_CHARACTERISTIC_REQUEST_ADDITIONAL_COMMAND_RESPONSE_PACKET);
                }
            }
            throw new BLETransportOperationError("Failed to send command, failed to read response characteristic", Integer.valueOf(i), BLETransportOperationError.Operation.READ_CHARACTERISTIC_COMMAND_RESPONSE);
        } catch (Exception e3) {
            e = e3;
            cancelFuture(future);
            WJLog.e(TAG, "error getting reading command response", e);
            throw new RuntimeException("error getting reading command response", e);
        }
    }

    public void cancelFuture(Future<?> future) {
        if (future != null) {
            future.cancel(true);
        }
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.radios.ble.GattCharacteristicPendingUpdate
    public GattCharacteristicPendingStatus notify(BleGattCharacteristic bleGattCharacteristic) {
        if (!bleGattCharacteristic.getUuid().equals(BleConstants.PROVISIONING_COMMAND_RESPONSE_CHARACTERISTIC_UUID)) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("not correct uuid for notify : ");
            outline107.append(bleGattCharacteristic.getUuid());
            outline107.append(" expected : ");
            outline107.append(BleConstants.PROVISIONING_COMMAND_RESPONSE_CHARACTERISTIC_UUID);
            WJLog.v(str, outline107.toString());
            return new GattCharacteristicPendingStatus(false, null);
        }
        return readCommandReponse(this.mSendCommandResponseFuture, this.mOutstream, this);
    }
}
