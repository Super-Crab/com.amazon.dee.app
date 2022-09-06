package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import amazon.communication.connection.Channels;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BLETransportOperationError;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.firebase.FirebaseError;
/* loaded from: classes13.dex */
public class BLETransportOperationErrorDetailsProvider implements ErrorDetailsProvider<BLETransportOperationError> {

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.BLETransportOperationErrorDetailsProvider$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation = new int[BLETransportOperationError.Operation.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.DISCOVER_GATT_SERVICES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.BIND_SERVICE_CLIENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.ENABLE_NOTIFICATION_PROVISIONING_COMMAND_RESPONSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.ENABLE_NOTIFICATION_PROVISIONING_EVENTS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.ENABLE_NOTIFICATION_PROVISIONING_STATUS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.ENABLE_NOTIFICATION_START_PROVISIONING_REQUEST_RESPONSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.INITIATE_WRITE_CHARACTERISTIC_START_PROVISIONING_REQUEST.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.CONFIRM_WRITE_CHARACTERISTIC_START_PROVISIONING_REQUEST.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.READ_CHARACTERISTIC_START_PROVISIONING_REQUEST_RESPONSE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.INITIATE_WRITE_CHARACTERISTIC_PROVISIONING_COMMAND.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.CONFIRM_WRITE_CHARACTERISTIC_PROVISIONING_COMMAND.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.READ_CHARACTERISTIC_COMMAND_RESPONSE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.INITIATE_WRITE_CHARACTERISTIC_REQUEST_ADDITIONAL_COMMAND_RESPONSE_PACKET.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.CONFIRM_WRITE_CHARACTERISTIC_REQUEST_ADDITIONAL_COMMAND_RESPONSE_PACKET.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.INITIATE_READ_CHARACTERISTIC.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.CONFIRM_READ_CHARACTERISTIC.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.INITIATE_WRITE_CHARACTERISTIC.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.CONFIRM_WRITE_CHARACTERISTIC.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$exceptions$BLETransportOperationError$Operation[BLETransportOperationError.Operation.ENABLE_NOTIFICATIONS.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    private int getTransportErrorOperationOffset(BLETransportOperationError.Operation operation) {
        switch (operation.ordinal()) {
            case 0:
                return 10000;
            case 1:
                return Channels.WP_RESERVED_FIRST_CHANNEL;
            case 2:
                return DeviceConfigConstants.WEBRTC_STATS_LOGGING_FREQUENCY_14000_MS;
            case 3:
                return 15000;
            case 4:
                return 12000;
            case 5:
                return 13000;
            case 6:
                return 16000;
            case 7:
                return FirebaseError.ERROR_INVALID_CUSTOM_TOKEN;
            case 8:
                return 18000;
            case 9:
                return 19000;
            case 10:
                return 20000;
            case 11:
                return 21000;
            case 12:
                return 22000;
            case 13:
                return 23000;
            case 14:
                return 24000;
            case 15:
                return 25000;
            case 16:
                return 26000;
            case 17:
                return 27000;
            case 18:
                return 28000;
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unhandled Operation ");
                outline107.append(operation.name());
                throw new IllegalStateException(outline107.toString());
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.ErrorDetailsProvider
    public int getDetails(BLETransportOperationError bLETransportOperationError) {
        return getTransportErrorOperationOffset(bLETransportOperationError.getOperation()) + 100000 + (bLETransportOperationError.getGattStatus() != null ? bLETransportOperationError.getGattStatus().intValue() : 0);
    }
}
