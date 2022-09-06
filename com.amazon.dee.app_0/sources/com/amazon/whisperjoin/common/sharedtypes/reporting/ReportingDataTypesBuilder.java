package com.amazon.whisperjoin.common.sharedtypes.reporting;

import com.amazon.devicesetup.common.v1.ErrorInfo;
import com.amazon.devicesetupservice.v1.CredentialLockerUsageInfo;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.WifiNetworkInfo;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.commons.lang.exception.ExceptionUtils;
/* loaded from: classes13.dex */
public class ReportingDataTypesBuilder {
    private static final String TAG = "ReportingDataTypesBuilder";

    /* renamed from: com.amazon.whisperjoin.common.sharedtypes.reporting.ReportingDataTypesBuilder$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
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
        }
    }

    public static CredentialLockerUsageInfo buildCredentialLockerUsageInfo(Boolean bool, Boolean bool2, Boolean bool3) {
        if (bool == null) {
            WJLog.w(TAG, "Can't build CredentialLockerUsageInfo with null doesUserIntendsToSaveCredToLocker");
            return null;
        } else if (bool2 == null) {
            WJLog.w(TAG, "Can't build CredentialLockerUsageInfo with null isChoosenSSIDCredChanged");
            return null;
        } else if (bool3 == null) {
            WJLog.w(TAG, "Can't build CredentialLockerUsageInfo with null isSSIDFromCredlocker");
            return null;
        } else {
            CredentialLockerUsageInfo credentialLockerUsageInfo = new CredentialLockerUsageInfo();
            credentialLockerUsageInfo.setChosenSSIDCredChanged(bool2.toString());
            credentialLockerUsageInfo.setUserIntendsToSaveCredentialToLocker(bool.toString());
            credentialLockerUsageInfo.setSsidFromCredlocker(bool3.toString());
            return credentialLockerUsageInfo;
        }
    }

    public static ErrorInfo buildErrorInfo(String str, Throwable th, String str2) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setOperation(str);
        errorInfo.setCode(str2);
        if (th != null) {
            errorInfo.setCause(th.getClass().getSimpleName());
            errorInfo.setDetails(ExceptionUtils.getFullStackTrace(th));
        }
        return errorInfo;
    }

    public static ProvisionableInfo buildProvisionableInfo(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, DeviceDetails deviceDetails) {
        if (whisperJoinPeripheralDeviceDetails == null) {
            WJLog.w(TAG, "Can't build provisionable info with null whisperJoinDeviceDetails");
            return null;
        } else if (deviceDetails == null) {
            WJLog.w(TAG, "Can't build provisionable info with null deviceDetails");
            return null;
        } else {
            ProvisionableInfo provisionableInfo = new ProvisionableInfo();
            provisionableInfo.setDeviceName(whisperJoinPeripheralDeviceDetails.getFriendlyName());
            provisionableInfo.setSoftwareVersionIndex(whisperJoinPeripheralDeviceDetails.getSoftwareVersion());
            provisionableInfo.setDeviceModel(deviceDetails.getDeviceModelNumber());
            provisionableInfo.setDeviceSerial(deviceDetails.getDeviceSerialNumber());
            provisionableInfo.setFirmwareVersion(deviceDetails.getDeviceFirmwareRevision());
            provisionableInfo.setHardwareVersion(deviceDetails.getDeviceHardwareRevision());
            provisionableInfo.setManufacturer(deviceDetails.getManufacturer());
            return provisionableInfo;
        }
    }

    public static WifiNetworkInfo buildWifiNetworkInfo(WifiNetwork wifiNetwork) {
        if (wifiNetwork == null) {
            WJLog.w(TAG, "Can't build WifiNetworkInfo with null wifiNetwork");
            return null;
        }
        WifiNetworkInfo wifiNetworkInfo = new WifiNetworkInfo();
        wifiNetworkInfo.setSsid(wifiNetwork.getSsid());
        wifiNetworkInfo.setKeyManagement(getKeyManagementFromWJType(wifiNetwork.getKeyManagement()));
        return wifiNetworkInfo;
    }

    private static String getKeyManagementFromWJType(WifiKeyManagement wifiKeyManagement) {
        int ordinal = wifiKeyManagement.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return "WPAPSK";
            }
            if (ordinal == 2) {
                return "WEP";
            }
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported key management for reporting: ");
            outline107.append(wifiKeyManagement.name());
            WJLog.e(str, outline107.toString());
            return null;
        }
        return "NONE";
    }

    public static WifiNetworkInfo buildWifiNetworkInfo(WifiConnectionDetails wifiConnectionDetails) {
        if (wifiConnectionDetails == null) {
            WJLog.w(TAG, "Can't build WifiNetworkInfo with null wifiConnectionDetails");
            return null;
        }
        WifiNetworkInfo wifiNetworkInfo = new WifiNetworkInfo();
        wifiNetworkInfo.setSsid(wifiConnectionDetails.getSsid());
        wifiNetworkInfo.setKeyManagement(getKeyManagementFromWJType(wifiConnectionDetails.getKeyManagement()));
        wifiNetworkInfo.setNetworkState(wifiConnectionDetails.getConnectionState().name());
        return wifiNetworkInfo;
    }
}
