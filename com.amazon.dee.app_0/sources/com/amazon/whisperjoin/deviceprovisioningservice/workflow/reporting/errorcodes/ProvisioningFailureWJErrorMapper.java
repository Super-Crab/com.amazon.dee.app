package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.deviceprovisioningservice.error.NoAssociatedDeviceCredentialsException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.NoConfiguredWifiNetworksException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableProvisioningDoneFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableRegistrationError;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableWifiNetworkConnectionError;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningVerificationTimeoutException;
/* loaded from: classes13.dex */
public class ProvisioningFailureWJErrorMapper implements WJErrorMapper<ProvisioningFailureException> {

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.ProvisioningFailureWJErrorMapper$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$registration$CBLRegistrationDetails$State = new int[CBLRegistrationDetails.State.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State;

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$registration$CBLRegistrationDetails$State[CBLRegistrationDetails.State.REGISTRATION_FAILED_TOKEN_EXPIRED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$registration$CBLRegistrationDetails$State[CBLRegistrationDetails.State.REGISTRATION_FAILED_TOKEN_INVALID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$registration$CBLRegistrationDetails$State[CBLRegistrationDetails.State.REGISTRATION_FAILED_SERVER_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$registration$CBLRegistrationDetails$State[CBLRegistrationDetails.State.REGISTRATION_FAILED_SERVER_NOT_REACHABLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$registration$CBLRegistrationDetails$State[CBLRegistrationDetails.State.REGISTRATION_FAILED_OTHER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State = new int[WifiConnectionDetails.State.values().length];
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State[WifiConnectionDetails.State.CONNECTION_FAILED_PSK_AUTHENTICATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State[WifiConnectionDetails.State.CONNECTION_FAILED_INTERNAL_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State[WifiConnectionDetails.State.CONNECTED_BEHIND_CAPTIVE_PORTAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State[WifiConnectionDetails.State.CONNECTED_LIMITED_CONNECTIVITY.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State[WifiConnectionDetails.State.CONNECTION_FAILED_AP_NOT_FOUND.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private WJError mapRegistrationError(ProvisionableRegistrationError provisionableRegistrationError) {
        Integer httpCode = provisionableRegistrationError.getHttpCode();
        int ordinal = provisionableRegistrationError.getState().ordinal();
        if (ordinal != 3) {
            if (ordinal == 4) {
                return WJErrorFactory.Provisioning.registrationErrorTokenExpired(httpCode);
            }
            if (ordinal == 5) {
                return WJErrorFactory.Provisioning.registrationErrorServerNotReachable(httpCode);
            }
            if (ordinal == 6) {
                return WJErrorFactory.Provisioning.registrationErrorServerError(httpCode);
            }
            if (ordinal != 7) {
                return WJErrorFactory.Provisioning.registrationUnknownError(httpCode);
            }
            return WJErrorFactory.Provisioning.registrationErrorFailedOther(httpCode);
        }
        return WJErrorFactory.Provisioning.registrationErrorTokenInvalid(httpCode);
    }

    private WJError mapWifiConnectionError(ProvisionableWifiNetworkConnectionError provisionableWifiNetworkConnectionError) {
        switch (provisionableWifiNetworkConnectionError.getState().ordinal()) {
            case 5:
                return WJErrorFactory.Provisioning.wifiConnectionErrorInternalError();
            case 6:
                return WJErrorFactory.Provisioning.wifiConnectionErrorCaptivePortal();
            case 7:
                return WJErrorFactory.Provisioning.wifiConnectionErrorLimitedConnectivity();
            case 8:
                return WJErrorFactory.Provisioning.wifiConnectionErrorBadPsk();
            case 9:
                return WJErrorFactory.Provisioning.wifiConnectionErrorApNotFound();
            default:
                return WJErrorFactory.Provisioning.wifiConnectionUnknownError();
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(ProvisioningFailureException provisioningFailureException) {
        if (provisioningFailureException instanceof NoConfiguredWifiNetworksException) {
            return WJErrorFactory.Provisioning.noConfiguredNetworks();
        }
        if (provisioningFailureException instanceof ProvisionableWifiNetworkConnectionError) {
            return mapWifiConnectionError((ProvisionableWifiNetworkConnectionError) provisioningFailureException);
        }
        if (provisioningFailureException instanceof ProvisionableRegistrationError) {
            return mapRegistrationError((ProvisionableRegistrationError) provisioningFailureException);
        }
        if (provisioningFailureException instanceof ProvisionableProvisioningDoneFailureException) {
            return WJErrorFactory.Provisioning.provisioningDoneFailure(((ProvisionableProvisioningDoneFailureException) provisioningFailureException).getProvisioningFailure().getErrorCode());
        }
        if (provisioningFailureException instanceof ProvisioningVerificationTimeoutException) {
            return WJErrorFactory.Provisioning.provisioningVerificationTimeout();
        }
        if (provisioningFailureException instanceof NoAssociatedDeviceCredentialsException) {
            return WJErrorFactory.Provisioning.noAssociatedDeviceCredentials();
        }
        throw new IllegalStateException("Unhandled ProvisioningFailureException", provisioningFailureException);
    }
}
