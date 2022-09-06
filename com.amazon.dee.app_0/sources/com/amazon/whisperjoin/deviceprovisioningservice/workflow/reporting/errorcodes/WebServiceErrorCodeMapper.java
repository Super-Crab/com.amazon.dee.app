package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSOperation;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSClientError;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSServiceError;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.GetAccessTokenError;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.NoCustomerAccountFoundError;
/* loaded from: classes13.dex */
public class WebServiceErrorCodeMapper implements WJErrorMapper<DSSClientError> {

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WebServiceErrorCodeMapper$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation = new int[DSSOperation.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation[DSSOperation.DISCOVERED_PROVISIONABLE_DEVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation[DSSOperation.DISCOVERED_PROVISIONEE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation[DSSOperation.START_ECDHE_AUTHENTICATION_SESSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation[DSSOperation.FINALIZE_ECDHE_AUTHENTICATION_SESSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation[DSSOperation.COMPUTE_CONFIGURATION_DATA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation[DSSOperation.REPORT_EVENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation[DSSOperation.GET_FFS_WHITELIST_POLICY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation[DSSOperation.GET_DEVICE_REGISTRATION_STATUS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation[DSSOperation.GET_CUSTOMER_PROVISIONEES_SETUP_STATUS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$devicesetupserviceandroidclient$DSSOperation[DSSOperation.VALIDATE_WIFI_SYNC_AUTH_TOKEN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private int getErrorDetails(Throwable th) {
        int i;
        int errorCode;
        if (th instanceof DSSServiceError) {
            i = 100000;
            errorCode = ((DSSServiceError) th).getErrorCode();
        } else if (th instanceof GetAccessTokenError) {
            i = WebServiceErrorDetails.GET_ACCESS_TOKEN_ERROR_OFFSET;
            errorCode = ((GetAccessTokenError) th).getErrorCode();
        } else if (!(th instanceof NoCustomerAccountFoundError)) {
            return CommonErrorDetailsProvider.getCommonErrorDetails(th);
        } else {
            return 300000;
        }
        return errorCode + i;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(DSSClientError dSSClientError) {
        DSSOperation operation = dSSClientError.getOperation();
        int errorDetails = getErrorDetails(dSSClientError.getFailureCause());
        int ordinal = operation.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return WJErrorFactory.WebService.discoveredProvisioneeDevice(errorDetails);
            }
            if (ordinal == 2) {
                return WJErrorFactory.WebService.startEcdheAuthenticationSession(errorDetails);
            }
            if (ordinal == 3) {
                return WJErrorFactory.WebService.finalizeEcdheAuthenticationSession(errorDetails);
            }
            if (ordinal == 5) {
                return WJErrorFactory.WebService.reportEvent(errorDetails);
            }
            if (ordinal == 6) {
                return WJErrorFactory.WebService.getWhiteListPolicy(errorDetails);
            }
            if (ordinal == 8) {
                return WJErrorFactory.WebService.computeConfigurationData(errorDetails);
            }
            if (ordinal == 9) {
                return WJErrorFactory.WebService.getDeviceRegistrationStatus(errorDetails);
            }
            if (ordinal == 16) {
                return WJErrorFactory.WebService.getCustomerProvisioneesSetupStatus(errorDetails);
            }
            if (ordinal != 18) {
                return WJErrorFactory.WebService.unknown(errorDetails);
            }
            return WJErrorFactory.WebService.validateWifiSyncAuthToken(errorDetails);
        }
        return WJErrorFactory.WebService.discoveredProvisionableDevice(errorDetails);
    }
}
