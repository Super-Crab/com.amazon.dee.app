package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperbridge.ble.BleGattCharacteristic;
import com.amazon.whisperbridge.ble.command.BleWriteCharacteristicCommand;
import com.amazon.whisperbridge.constants.Command;
import com.amazon.whisperjoin.common.sharedtypes.ble.BleConstants;
import com.amazon.whisperjoin.common.sharedtypes.ble.WhisperJoinBlePacket;
import com.amazon.whisperjoin.common.sharedtypes.ble.commands.ProvisioningCommand;
import com.amazon.whisperjoin.common.sharedtypes.ble.commands.ProvisioningCommands;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BLETransportOperationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ProtocolBuffersSerializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.EncodingHelpers;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.provisionerSDK.radios.ble.WJPacketSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
class SendCommand implements Callable<byte[]> {
    private static final String TAG = "com.amazon.whisperjoin.provisionerSDK.radios.ble.SendCommand";
    final Command mCommand;
    private final EncodingHelpers mEncodingHelpers;
    final GattCharacteristicClient mGattCharacteristicClient;
    private final ByteArrayOutputStream mOutstream = new ByteArrayOutputStream();
    final byte[] mPayload;
    private final ResponseFutureProvider mResponseFutureProvider;
    private final Serializer mSerializer;
    private final WJPacketSerializer mWJPacketSerializer;

    /* renamed from: com.amazon.whisperjoin.provisionerSDK.radios.ble.SendCommand$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperbridge$constants$Command = new int[Command.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_VISIBLE_NETWORKS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.CONNECT_TO_PROVIDED_NETWORK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.START_NETWORK_SCAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.DELETE_NETWORK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.DELETE_ALL_NETWORKS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_CONNECTION_STATUS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.SAVE_NETWORK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.COMPLETE_PROVISIONING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.STOP_PROVISIONING.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_SUPPORTED_NOTIFICATIONS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_NOTIFICATION_EVENT_DATA.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.EXCHANGE_ECDHE_KEY.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.EXCHANGE_AUTHENTICATED_ECDHE_KEY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.SET_CONFIGURATION.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_CONFIGURATION.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.DELETE_CONFIGURATION.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.DELETE_CONFIGURATION_SET.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_DEVICE_DETAILS.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.PING.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.SET_REGISTRATION_CONFIGURATION.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_REGISTRATION_INFORMATION.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.JPAKE_ROUND1.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.JPAKE_ROUND2.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.JPAKE_ROUND3.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_PROVISIONING_FAILURE_CAUSE.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.DELETE_REGISTRATION_CONFIGURATION.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.DELETE_REGISTRATION_CONFIGURATIONS.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.WILL_SEND_EVENTS.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.CONNECT_AND_SAVE_PROVIDED_NETWORK.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
        }
    }

    public SendCommand(GattCharacteristicClient gattCharacteristicClient, byte[] bArr, Command command, EncodingHelpers encodingHelpers) {
        if (gattCharacteristicClient != null) {
            if (command == null) {
                throw new IllegalArgumentException("command can not be null");
            }
            if (encodingHelpers != null) {
                this.mGattCharacteristicClient = gattCharacteristicClient;
                this.mCommand = command;
                this.mPayload = bArr;
                this.mEncodingHelpers = encodingHelpers;
                this.mSerializer = new ProtocolBuffersSerializer();
                this.mWJPacketSerializer = new WJPacketSerializer.DefaultWJPacketSerializer();
                this.mResponseFutureProvider = new ResponseFutureProvider();
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("created command : ");
                outline107.append(this.mCommand);
                WJLog.v(str, outline107.toString());
                return;
            }
            throw new IllegalArgumentException("encoding helpers can't be null");
        }
        throw new IllegalArgumentException("client can not be null");
    }

    private UUID getCommandUUID(Command command) {
        switch (command.ordinal()) {
            case 0:
                return ProvisioningCommands.UNAUTHENTICATED_ECDHE_COMMAND_UUID;
            case 1:
                return ProvisioningCommands.AUTHENTICATED_ECDHE_COMMAND_UUID;
            case 2:
                return ProvisioningCommands.GET_VISIBLE_NETWORKS_COMMAND_UUID;
            case 3:
                return ProvisioningCommands.CONNECT_TO_CONFIGURED_NETWORK_COMMAND_UUID;
            case 4:
            case 18:
            case 19:
            case 20:
            case 21:
            case 29:
            default:
                throw new UnsupportedOperationException("command : " + command + " is not supported on this device/transport");
            case 5:
                return ProvisioningCommands.GET_DEVICE_DETAILS_UUID;
            case 6:
                throw new UnsupportedOperationException("command : " + command + " is not supported on this device/transport");
            case 7:
                return ProvisioningCommands.INITIATE_VISIBLE_NETWORK_SCAN_COMMAND_UUID;
            case 8:
                return ProvisioningCommands.SAVE_CONFIGURED_NETWORK_COMMAND_UUID;
            case 9:
                return ProvisioningCommands.DELETE_CONFIGURED_NETWORK_COMMAND_UUID;
            case 10:
                return ProvisioningCommands.DELETE_ALL_CONFIGURED_NETWORKS_COMMAND_UUID;
            case 11:
                return ProvisioningCommands.REFRESH_WIFI_CONNECTION_DETAILS_UUID;
            case 12:
                return ProvisioningCommands.SET_CONFIGURATION_UUID;
            case 13:
                return ProvisioningCommands.GET_CONFIGURATION_UUID;
            case 14:
                return ProvisioningCommands.DELETE_CONFIGURATION_UUID;
            case 15:
                return ProvisioningCommands.DELETE_CONFIGURATION_SET_UUID;
            case 16:
                return ProvisioningCommands.REGISTER_WITH_CODE_BASED_LINKING;
            case 17:
                return ProvisioningCommands.REFRESH_CBL_REGISTRATION_DETAILS_UUID;
            case 22:
                return ProvisioningCommands.STOP_PROVISIONING_COMMAND_UUID;
            case 23:
                return ProvisioningCommands.PROVISIONING_COMPLETE_UUID;
            case 24:
                return ProvisioningCommands.RETRIEVE_SUPPORTED_EVENTS_UUID;
            case 25:
                return ProvisioningCommands.RETRIEVE_EVENT_OBJECT_UUID;
            case 26:
                return ProvisioningCommands.JPAKE_ROUND1_COMMAND_UUID;
            case 27:
                return ProvisioningCommands.JPAKE_ROUND2_COMMAND_UUID;
            case 28:
                return ProvisioningCommands.JPAKE_ROUND3_COMMAND_UUID;
            case 30:
                return ProvisioningCommands.GET_PROVISIONING_FAILURE_CAUSE_UUID;
        }
    }

    private void writeCommand(SendCommandResponseUpdate sendCommandResponseUpdate) throws Exception {
        byte[] serialize;
        this.mGattCharacteristicClient.addPendingUpdate(sendCommandResponseUpdate);
        BleGattCharacteristic bleGattCharacteristic = this.mGattCharacteristicClient.getBleGattCharacteristic(BleConstants.PROVISIONING_COMMAND_CHARACTERISTIC_UUID);
        if (Command.DATA.equals(this.mCommand)) {
            EncodingHelpers.CommandInformation decodeCommand = this.mEncodingHelpers.decodeCommand(this.mPayload);
            serialize = this.mSerializer.serialize(new ProvisioningCommand(UUID.fromString(decodeCommand.getCommand()), decodeCommand.getPayload()));
        } else {
            UUID commandUUID = getCommandUUID(this.mCommand);
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WriteCommand UUID: ");
            outline107.append(commandUUID.toString());
            WJLog.d(str, outline107.toString());
            serialize = this.mSerializer.serialize(new ProvisioningCommand(commandUUID, this.mPayload));
        }
        WJLog.byteStruct(TAG, "Provisioning Command Bytes", serialize);
        for (WhisperJoinBlePacket whisperJoinBlePacket : this.mWJPacketSerializer.serialize(serialize)) {
            byte[] serialize2 = this.mSerializer.serialize(whisperJoinBlePacket);
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Serialized Packet ");
            outline1072.append(whisperJoinBlePacket.toString());
            WJLog.byteStruct(str2, outline1072.toString(), serialize2);
            bleGattCharacteristic.setStoredData(serialize2);
            try {
                Future<BleWriteCharacteristicCommand.Result> writeCharacteristic = this.mGattCharacteristicClient.writeCharacteristic(BleConstants.PROVISIONING_COMMAND_CHARACTERISTIC_UUID);
                WJLog.v(TAG, "sending command packet");
                int i = writeCharacteristic.get().status;
                if (i != 0) {
                    WJLog.e(TAG, "failed to send command");
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Failed to send command: ");
                    outline1073.append(this.mCommand);
                    throw new BLETransportOperationError(outline1073.toString(), Integer.valueOf(i), BLETransportOperationError.Operation.CONFIRM_WRITE_CHARACTERISTIC_PROVISIONING_COMMAND);
                }
            } catch (Exception e) {
                WJLog.e(TAG, "error in writeCharacteristic", e);
                sendCommandResponseUpdate.cancelFuture(null);
                this.mGattCharacteristicClient.markUpdated(sendCommandResponseUpdate);
                throw new BLETransportOperationError(e, BLETransportOperationError.Operation.INITIATE_WRITE_CHARACTERISTIC_PROVISIONING_COMMAND);
            }
        }
        WJLog.d(TAG, "Command sent");
    }

    @Override // java.util.concurrent.Callable
    public byte[] call() throws Exception {
        SendCommandResponseFuture newSendCommandResponseFuture = this.mResponseFutureProvider.newSendCommandResponseFuture(this.mOutstream);
        writeCommand(this.mResponseFutureProvider.newSendCommandResponseUpdate(this.mGattCharacteristicClient, this.mOutstream, newSendCommandResponseFuture));
        return newSendCommandResponseFuture.get();
    }

    SendCommand(GattCharacteristicClient gattCharacteristicClient, byte[] bArr, Command command, EncodingHelpers encodingHelpers, ResponseFutureProvider responseFutureProvider, Serializer serializer, WJPacketSerializer wJPacketSerializer) {
        if (gattCharacteristicClient != null) {
            if (command == null) {
                throw new IllegalArgumentException("command can not be null");
            }
            if (encodingHelpers != null) {
                this.mGattCharacteristicClient = gattCharacteristicClient;
                this.mCommand = command;
                this.mPayload = bArr;
                this.mEncodingHelpers = encodingHelpers;
                this.mSerializer = serializer;
                this.mWJPacketSerializer = wJPacketSerializer;
                this.mResponseFutureProvider = responseFutureProvider;
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("created command : ");
                outline107.append(this.mCommand);
                WJLog.v(str, outline107.toString());
                return;
            }
            throw new IllegalArgumentException("encoding helpers can't be null");
        }
        throw new IllegalArgumentException("gattCharacteristicClient can not be null");
    }
}
