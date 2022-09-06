package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperbridge.constants.Command;
import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BLETransportOperationError;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.UnsuccessfulProvisioningCommandError;
import com.amazon.whisperjoin.provisionerSDK.radios.error.ProvisionerCommandError;
/* loaded from: classes13.dex */
public class ProvisionerBLECommandErrorMapper implements WJErrorMapper<ProvisionerCommandError> {
    private final BLETransportOperationErrorDetailsProvider mBLETransportOperationErrorDetailsProvider;

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.ProvisionerBLECommandErrorMapper$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperbridge$constants$Command = new int[Command.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.EXCHANGE_ECDHE_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.EXCHANGE_AUTHENTICATED_ECDHE_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.JPAKE_ROUND1.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.JPAKE_ROUND2.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.JPAKE_ROUND3.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.JPAKE_CERT_VALIDATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_VISIBLE_NETWORKS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_DEVICE_DETAILS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.SAVE_NETWORK.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_CONNECTION_STATUS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.SET_CONFIGURATION.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.SET_REGISTRATION_CONFIGURATION.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_REGISTRATION_INFORMATION.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.COMPLETE_PROVISIONING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$whisperbridge$constants$Command[Command.GET_SUPPORTED_NOTIFICATIONS.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public ProvisionerBLECommandErrorMapper(BLETransportOperationErrorDetailsProvider bLETransportOperationErrorDetailsProvider) {
        this.mBLETransportOperationErrorDetailsProvider = bLETransportOperationErrorDetailsProvider;
    }

    private int getCommandErrorDetails(Throwable th) {
        if (th instanceof BLETransportOperationError) {
            return this.mBLETransportOperationErrorDetailsProvider.getDetails((BLETransportOperationError) th);
        }
        if (th instanceof UnsuccessfulProvisioningCommandError) {
            return getProvisioningCommandErrorDetails((UnsuccessfulProvisioningCommandError) th);
        }
        return CommonErrorDetailsProvider.getCommonErrorDetails(th);
    }

    private int getProvisioningCommandErrorDetails(UnsuccessfulProvisioningCommandError unsuccessfulProvisioningCommandError) {
        return unsuccessfulProvisioningCommandError.getStatusCode() + 300000;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(ProvisionerCommandError provisionerCommandError) {
        int commandErrorDetails = getCommandErrorDetails(provisionerCommandError.getFailureCause());
        int ordinal = provisionerCommandError.getCommand().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return WJErrorFactory.BLECommand.exchangeAuthEcdheKey(commandErrorDetails);
            }
            if (ordinal == 2) {
                return WJErrorFactory.BLECommand.getVisibleNetworks(commandErrorDetails);
            }
            if (ordinal == 5) {
                return WJErrorFactory.BLECommand.getDeviceDetails(commandErrorDetails);
            }
            if (ordinal == 8) {
                return WJErrorFactory.BLECommand.saveNetwork(commandErrorDetails);
            }
            if (ordinal == 11) {
                return WJErrorFactory.BLECommand.getConnectionStatus(commandErrorDetails);
            }
            if (ordinal == 12) {
                return WJErrorFactory.BLECommand.setConfiguration(commandErrorDetails);
            }
            if (ordinal == 16) {
                return WJErrorFactory.BLECommand.setRegistrationToken(commandErrorDetails);
            }
            if (ordinal == 17) {
                return WJErrorFactory.BLECommand.getRegistrationStatus(commandErrorDetails);
            }
            if (ordinal == 23) {
                return WJErrorFactory.BLECommand.completeProvisioning(commandErrorDetails);
            }
            if (ordinal != 24) {
                switch (ordinal) {
                    case 26:
                        return WJErrorFactory.BLECommand.jpakeRound1(commandErrorDetails);
                    case 27:
                        return WJErrorFactory.BLECommand.jpakeRound2(commandErrorDetails);
                    case 28:
                        return WJErrorFactory.BLECommand.jpakeRound3(commandErrorDetails);
                    case 29:
                        return WJErrorFactory.BLECommand.jpakeRoundCertValidation(commandErrorDetails);
                    default:
                        return WJErrorFactory.BLECommand.unknown(0);
                }
            }
            return WJErrorFactory.BLECommand.getSupportedNotifications(commandErrorDetails);
        }
        return WJErrorFactory.BLECommand.exchangeEcdheKey(commandErrorDetails);
    }
}
