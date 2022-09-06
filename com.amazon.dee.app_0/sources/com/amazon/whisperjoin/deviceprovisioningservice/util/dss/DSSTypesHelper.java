package com.amazon.whisperjoin.deviceprovisioningservice.util.dss;

import com.amazon.devicesetup.common.v1.WifiCredentials;
import com.amazon.devicesetup.common.v1.WifiScanData;
import com.amazon.devicesetupservice.reporting.KeyExchangeMethod;
import com.amazon.devicesetupservice.v1.CredentialConfiguration;
import com.amazon.devicesetupservice.v1.WepKeyConfiguration;
import com.amazon.devicesetupservice.v1.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResult;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResultCollection;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AuthMaterialIdentifier;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BarcodeIdentifier;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.GetCustomerProvisioneesSetupStatusResponse;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.LegacyIdentifier;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioneeSetupStatus;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.LegacyIdentifierData;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ProvisioneeSetupStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
/* loaded from: classes13.dex */
public class DSSTypesHelper {

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.util.dss.DSSTypesHelper$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$cryptography$TrustProvider$TrustState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement = new int[WifiKeyManagement.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.WPA_PSK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.WEP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.OTHER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$cryptography$TrustProvider$TrustState = new int[TrustProvider.TrustState.values().length];
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$cryptography$TrustProvider$TrustState[TrustProvider.TrustState.TRUSTED_ENCRYPTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$cryptography$TrustProvider$TrustState[TrustProvider.TrustState.UNTRUSTED_ENCRYPTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$cryptography$TrustProvider$TrustState[TrustProvider.TrustState.UNTRUSTED_PIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static AuthMaterialIdentifier convertAuthMaterialIdentifier(com.amazon.whisperjoin.devicesetupserviceandroidclient.data.AuthMaterialIdentifier authMaterialIdentifier) {
        if (authMaterialIdentifier == null) {
            return null;
        }
        return new AuthMaterialIdentifier.Builder().setAuthMaterialIndex(authMaterialIdentifier.getAuthMaterialIndex()).setProductIndex(authMaterialIdentifier.getProductIndex()).build();
    }

    private static BarcodeIdentifier convertBarcodeIdentifier(com.amazon.whisperjoin.devicesetupserviceandroidclient.data.BarcodeIdentifier barcodeIdentifier) {
        if (barcodeIdentifier == null) {
            return null;
        }
        return new BarcodeIdentifier.Builder().setBarcodeData(barcodeIdentifier.getBarcodeData()).build();
    }

    public static GetCustomerProvisioneesSetupStatusResponse convertGetCustomerProvisioneesSetupStatusResponse(com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse) {
        GetCustomerProvisioneesSetupStatusResponse.Builder builder = new GetCustomerProvisioneesSetupStatusResponse.Builder();
        for (ProvisioneeSetupStatus provisioneeSetupStatus : getCustomerProvisioneesSetupStatusResponse.getProvisioneeSetupStatuses()) {
            builder.addProvisioneeSetupStatus(convertProvisioneeSetupStatus(provisioneeSetupStatus));
        }
        builder.setSearchIntervalUsed(getCustomerProvisioneesSetupStatusResponse.getSearchIntervalUsed());
        return builder.createResponse();
    }

    private static LegacyIdentifier convertLegacyIdentifier(LegacyIdentifierData legacyIdentifierData) {
        if (legacyIdentifierData == null) {
            return null;
        }
        return new LegacyIdentifier.Builder().setDeviceType(legacyIdentifierData.getDeviceType()).setDsn(legacyIdentifierData.getDsn()).build();
    }

    private static com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioneeSetupStatus convertProvisioneeSetupStatus(ProvisioneeSetupStatus provisioneeSetupStatus) {
        if (provisioneeSetupStatus == null) {
            return null;
        }
        return new ProvisioneeSetupStatus.Builder().setAuthMaterialIdentifier(convertAuthMaterialIdentifier(provisioneeSetupStatus.getAuthMaterialIdentifier())).setProvisionerInformation(convertLegacyIdentifier(provisioneeSetupStatus.getProvisionerInformation())).setProvisioneeAuthMaterialIdentifier(convertAuthMaterialIdentifier(provisioneeSetupStatus.getProvisioneeAuthMaterialIdentifier())).setProvisioneeBarcodeIdentifier(convertBarcodeIdentifier(provisioneeSetupStatus.getProvisioneeBarcodeIdentifier())).setErrorCode(provisioneeSetupStatus.getErrorCode()).setLastUpdatedTime(provisioneeSetupStatus.getLastUpdatedTime()).setProvisioningMethod(provisioneeSetupStatus.getProvisioningMethod()).setProvisioningState(provisioneeSetupStatus.getProvisioningState()).setProvisioningStatus(provisioneeSetupStatus.getProvisioningStatus()).build();
    }

    public static WifiConfiguration convertToDssWifiConfiguration(com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration wifiConfiguration) {
        WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
        wifiConfiguration2.setSsid(wifiConfiguration.getSsid());
        wifiConfiguration2.setPriority(wifiConfiguration.getNetworkPriority());
        CredentialConfiguration credentialConfiguration = new CredentialConfiguration();
        if (StringUtils.isNotBlank(wifiConfiguration.getPsk())) {
            credentialConfiguration.setPrivateSharedKey(wifiConfiguration.getPsk());
        }
        WepKeyConfiguration wepKeyConfiguration = new WepKeyConfiguration();
        int ordinal = wifiConfiguration.getKeyManagement().ordinal();
        if (ordinal == 0) {
            wifiConfiguration2.setKeyManagement("NONE");
            return wifiConfiguration2;
        } else if (ordinal == 1) {
            wifiConfiguration2.setKeyManagement("WPAPSK");
            wifiConfiguration2.setCredentialConfiguration(credentialConfiguration);
            return wifiConfiguration2;
        } else if (ordinal != 2) {
            return null;
        } else {
            wifiConfiguration2.setKeyManagement("WEP");
            wepKeyConfiguration.setWepKeyList(Collections.singletonList(wifiConfiguration.getWepKey()));
            credentialConfiguration.setWepKeyConfiguration(wepKeyConfiguration);
            wifiConfiguration2.setCredentialConfiguration(credentialConfiguration);
            return wifiConfiguration2;
        }
    }

    public static WifiCredentials createWifiCredentialsFromWifiConfiguration(com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return null;
        }
        WifiCredentials wifiCredentials = new WifiCredentials();
        wifiCredentials.setSsid(wifiConfiguration.getSsid());
        wifiCredentials.setSecurityProtocol(getSecurityProtocolFromKeyManagement(wifiConfiguration.getKeyManagement()));
        wifiCredentials.setKey(wifiConfiguration.getPsk());
        wifiCredentials.setPriority(wifiConfiguration.getNetworkPriority());
        return wifiCredentials;
    }

    public static WifiScanData createWifiScanDataFromWifiConnectionDetails(WifiConnectionDetails wifiConnectionDetails) {
        if (wifiConnectionDetails == null) {
            return null;
        }
        WifiScanData wifiScanData = new WifiScanData();
        wifiScanData.setSsid(wifiConnectionDetails.getSsid());
        wifiScanData.setSecurityProtocol(getSecurityProtocolFromKeyManagement(wifiConnectionDetails.getKeyManagement()));
        return wifiScanData;
    }

    public static WifiScanData createWifiScanDataFromWifiNetwork(WifiNetwork wifiNetwork) {
        if (wifiNetwork == null) {
            return null;
        }
        WifiScanData wifiScanData = new WifiScanData();
        wifiScanData.setSsid(wifiNetwork.getSsid());
        wifiScanData.setSecurityProtocol(getSecurityProtocolFromKeyManagement(wifiNetwork.getKeyManagement()));
        return wifiScanData;
    }

    public static WifiScanData createWifiScanDataFromWifiScanResult(WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            return null;
        }
        WifiScanData wifiScanData = new WifiScanData();
        wifiScanData.setSsid(wifiScanResult.getSsid());
        wifiScanData.setSecurityProtocol(getSecurityProtocolFromKeyManagement(wifiScanResult.getKeyManagement()));
        wifiScanData.setFrequency(wifiScanResult.getFrequencyBand());
        wifiScanData.setRssi(wifiScanResult.getSignalStrength());
        return wifiScanData;
    }

    public static List<WifiScanData> createWifiScanDataListFromWifiScanResults(WifiScanResultCollection wifiScanResultCollection) {
        ArrayList arrayList = new ArrayList(wifiScanResultCollection.size());
        for (WifiScanResult wifiScanResult : wifiScanResultCollection.getSetCollection()) {
            arrayList.add(createWifiScanDataFromWifiScanResult(wifiScanResult));
        }
        return arrayList;
    }

    public static String getKeyExchangeMethodFromTrustState(TrustProvider.TrustState trustState) {
        int ordinal = trustState.ordinal();
        if (ordinal == 1 || ordinal == 2) {
            return KeyExchangeMethod.ECDHE;
        }
        if (ordinal == 3) {
            return KeyExchangeMethod.JPAKE;
        }
        throw new IllegalStateException("Unexpected TrustState " + trustState);
    }

    public static String getSecurityProtocolFromKeyManagement(WifiKeyManagement wifiKeyManagement) {
        return wifiKeyManagement.equals(WifiKeyManagement.NONE) ? "OPEN" : wifiKeyManagement.toString();
    }

    public static String getTrustMethodFromTrustState(TrustProvider.TrustState trustState) {
        int ordinal = trustState.ordinal();
        if (ordinal != 1) {
            if (ordinal == 2) {
                return "AUTHENTICATED";
            }
            if (ordinal == 3) {
                return "UNAUTHENTICATED";
            }
            throw new IllegalStateException("Unexpected TrustState " + trustState);
        }
        return "UNAUTHENTICATED";
    }
}
