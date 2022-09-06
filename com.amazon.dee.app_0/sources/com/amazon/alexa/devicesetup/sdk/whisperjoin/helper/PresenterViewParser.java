package com.amazon.alexa.devicesetup.sdk.whisperjoin.helper;

import com.amazon.alexa.devicesetup.utils.EventBusUtil;
import com.amazon.alexa.devicesetup.utils.JsonUtil;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetails;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevice;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.DiscoveredDevicesViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.FailureViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.IdleViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.InProgressViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ProvisioningDetailsViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.RecentlySetupDevicesViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupCompleteViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupFailureViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.WifiConnectionErrorViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetwork;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetworks;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioneeSetupStatus;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public final class PresenterViewParser {
    private static EventBusUtil eventBusUtil = EventBusUtil.instance();
    private static JsonUtil json = JsonUtil.instance();

    private PresenterViewParser() {
    }

    public static String parseDiscoveredDevices(DiscoveredDevicesViewModel discoveredDevicesViewModel, String str) {
        try {
            JSONArray jSONArray = new JSONArray();
            discoveredDevicesViewModel.getDiscoveredDevices().getDevices().toString();
            for (DiscoveredDevice discoveredDevice : discoveredDevicesViewModel.getDiscoveredDevices().getDevices()) {
                discoveredDevice.getAdvertisedName();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("productIndex", discoveredDevice.getProductIndex());
                jSONObject.put("deviceIdentity", discoveredDevice.getDeviceIdentity());
                jSONObject.put("advertisedName", discoveredDevice.getAdvertisedName());
                jSONObject.put(ProvisioningConstants.DEVICE_DISTRESSED, discoveredDevice.isDistressed());
                jSONObject.put(ProvisioningConstants.DEVICE_DISTRESSED_ERROR, discoveredDevice.getDistressError());
                jSONArray.put(jSONObject);
            }
            return new JSONObject().put("discoveredDevices", jSONArray).toString();
        } catch (Exception unused) {
            sendUnexpectedErrorMessageToEventBus(GeneratedOutlineSupport1.outline72("SetupPresenter::", str));
            return null;
        }
    }

    public static String parseFailure(FailureViewModel failureViewModel) {
        if (failureViewModel == null) {
            sendUnexpectedErrorMessageToEventBus("SetupPresenter::showAutoDiscoveryFailure");
            return null;
        }
        return json.toJson(failureViewModel);
    }

    public static String parseIdle(IdleViewModel idleViewModel) {
        try {
            return new JSONObject().put("progressState", idleViewModel.getState().toString()).toString();
        } catch (Exception unused) {
            sendUnexpectedErrorMessageToEventBus("SetupPresenter::showIdleState");
            return null;
        }
    }

    public static String parseInProgress(InProgressViewModel inProgressViewModel) {
        try {
            return new JSONObject().put("progressState", inProgressViewModel.getState().toString()).toString();
        } catch (JSONException unused) {
            sendUnexpectedErrorMessageToEventBus("SetupPresenter::showInProgress");
            return null;
        }
    }

    public static String parseProvisioningDetails(ProvisioningDetailsViewModel provisioningDetailsViewModel) {
        try {
            JSONArray jSONArray = new JSONArray();
            AvailableWifiNetworks availableWifiNetworks = provisioningDetailsViewModel.getProvisioningDetails().getAvailableWifiNetworks();
            for (AvailableWifiNetwork availableWifiNetwork : availableWifiNetworks.getConfiguredNetworks()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ssid", removeQuotes(availableWifiNetwork.getScanResult().getSsid()));
                jSONObject.put("keyManagement", availableWifiNetwork.getScanResult().getKeyManagement().toString());
                jSONObject.put("signalStrength", availableWifiNetwork.getScanResult().getSignalStrength());
                jSONObject.put("frequencyBand", availableWifiNetwork.getScanResult().getFrequencyBand());
                jSONObject.put("hasConfiguration", availableWifiNetwork.hasConfiguration());
                if (availableWifiNetwork.hasConfiguration()) {
                    jSONObject.put("wepKey", removeQuotes(availableWifiNetwork.getWifiConfiguration().getWepKey()));
                    jSONObject.put("pskKey", removeQuotes(availableWifiNetwork.getWifiConfiguration().getPsk()));
                    jSONObject.put("priority", availableWifiNetwork.getWifiConfiguration().getNetworkPriority());
                    jSONObject.put("isHidden", availableWifiNetwork.getWifiConfiguration().isHiddenNetwork());
                }
                jSONArray.put(jSONObject);
            }
            JSONArray jSONArray2 = new JSONArray();
            for (AvailableWifiNetwork availableWifiNetwork2 : availableWifiNetworks.getUnrecognizedNetworks()) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("ssid", removeQuotes(availableWifiNetwork2.getScanResult().getSsid()));
                jSONObject2.put("keyManagement", availableWifiNetwork2.getScanResult().getKeyManagement().toString());
                jSONObject2.put("signalStrength", availableWifiNetwork2.getScanResult().getSignalStrength());
                jSONObject2.put("frequencyBand", availableWifiNetwork2.getScanResult().getFrequencyBand());
                jSONObject2.put("hasConfiguration", availableWifiNetwork2.hasConfiguration());
                jSONArray2.put(jSONObject2);
            }
            JSONObject jSONObject3 = new JSONObject();
            DeviceDetails deviceDetails = provisioningDetailsViewModel.getProvisioningDetails().getDeviceDetails();
            jSONObject3.put("manufacturer", deviceDetails.getManufacturer());
            jSONObject3.put("deviceModelNumber", deviceDetails.getDeviceModelNumber());
            jSONObject3.put("deviceSerialNumber", deviceDetails.getDeviceSerialNumber());
            jSONObject3.put("deviceHardwareRevision", deviceDetails.getDeviceHardwareRevision());
            jSONObject3.put("deviceFirmwareRevision", deviceDetails.getDeviceFirmwareRevision());
            jSONObject3.put(ProvisioningConstants.PROVISIONABLE_DEVICE_DETAILS_HAS_LAST_CONNECTED_WIFI_DETAILS, deviceDetails.hasLastConnectionWifiDetails());
            if (deviceDetails.hasLastConnectionWifiDetails()) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("ssid", deviceDetails.getLastConnectionWifiDetails().getSsid());
                jSONObject4.put(ProvisioningConstants.PROVISIONABLE_DEVICE_DETAILS_WIFI_KEY_MANAGEMENT, deviceDetails.getLastConnectionWifiDetails().getKeyManagement());
                jSONObject4.put("state", deviceDetails.getLastConnectionWifiDetails().getConnectionState());
                jSONObject3.put(ProvisioningConstants.PROVISIONABLE_DEVICE_DETAILS_LAST_CONNECTED_WIFI_DETAILS, jSONObject4);
            }
            return new JSONObject().put("configuredNetworks", jSONArray).put("unrecognizedNetworks", jSONArray2).put("deviceDetails", jSONObject3).toString();
        } catch (Exception unused) {
            sendUnexpectedErrorMessageToEventBus("SetupPresenter::showAvailableNetworks");
            return null;
        }
    }

    public static String parseRecentlySetupDevices(RecentlySetupDevicesViewModel recentlySetupDevicesViewModel) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (ProvisioneeSetupStatus provisioneeSetupStatus : recentlySetupDevicesViewModel.getCustomerProvisioneesSetupStatusResponse().getProvisioneeSetupStatuses()) {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("deviceType", provisioneeSetupStatus.getProvisionerInformation().getDeviceType());
                jSONObject2.put("dsn", provisioneeSetupStatus.getProvisionerInformation().getDsn());
                jSONObject.put(ProvisioningConstants.DEVICE_SETUP_PROVISIONER_INFORMATION, jSONObject2);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("productIndex", provisioneeSetupStatus.getAuthMaterialIdentifier().getProductIndex());
                jSONObject3.put(ProvisioningConstants.DEVICE_SETUP_AUTH_MATERIAL_INDEX, provisioneeSetupStatus.getAuthMaterialIdentifier().getAuthMaterialIndex());
                jSONObject.put(ProvisioningConstants.DEVICE_SETUP_AUTH_MATERIAL_IDENTIFIER, jSONObject3);
                jSONObject.put(ProvisioningConstants.DEVICE_SETUP_PROVISIONING_METHOD, provisioneeSetupStatus.getProvisioningMethod());
                jSONObject.put(ProvisioningConstants.DEVICE_SETUP_PROVISIONING_STATE, provisioneeSetupStatus.getProvisioningState());
                jSONObject.put(ProvisioningConstants.DEVICE_SETUP_PROVISIONING_STATUS, provisioneeSetupStatus.getProvisioningStatus());
                jSONArray.put(jSONObject);
            }
            return new JSONObject().put(ProvisioningConstants.DEVICE_SETUP_INFO, jSONArray).toString();
        } catch (Exception unused) {
            sendUnexpectedErrorMessageToEventBus("SetupPresenter::showRecentlySetupDevices");
            return null;
        }
    }

    public static String parseSetupComplete(SetupCompleteViewModel setupCompleteViewModel) {
        try {
            return new JSONObject().put("progressState", setupCompleteViewModel.getState().toString()).toString();
        } catch (Exception unused) {
            sendUnexpectedErrorMessageToEventBus("SetupPresenter::showSetupComplete");
            return null;
        }
    }

    public static String parseSetupFailure(SetupFailureViewModel setupFailureViewModel) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("progressState", setupFailureViewModel.getFailureState().toString());
            jSONObject.put("wjErrorDomain", setupFailureViewModel.getWJError().getDomain());
            jSONObject.put("wjErrorType", setupFailureViewModel.getWJError().getErrorType());
            jSONObject.put("wjErrorDetails", setupFailureViewModel.getWJError().getErrorDetails());
            jSONObject.put("wjErrorResolution", setupFailureViewModel.getWJError().getResolution());
            jSONObject.put("error", setupFailureViewModel.getFailureName());
            return jSONObject.toString();
        } catch (Exception unused) {
            sendUnexpectedErrorMessageToEventBus("SetupPresenter::showSetupFailure");
            return null;
        }
    }

    public static String parseWifiConnectionError(WifiConnectionErrorViewModel wifiConnectionErrorViewModel) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("errorType", wifiConnectionErrorViewModel.getWifiConnectionError().getState().toString());
            jSONObject.put("wjErrorDomain", wifiConnectionErrorViewModel.getWJError().getDomain());
            jSONObject.put("wjErrorType", wifiConnectionErrorViewModel.getWJError().getErrorType());
            jSONObject.put("wjErrorDetails", wifiConnectionErrorViewModel.getWJError().getErrorDetails());
            jSONObject.put("wjErrorResolution", wifiConnectionErrorViewModel.getWJError().getResolution());
            jSONObject.put("error", wifiConnectionErrorViewModel.getWifiConnectionError().getState().getDescription());
            return jSONObject.toString();
        } catch (Exception unused) {
            sendUnexpectedErrorMessageToEventBus("SetupPresenter::showWifiConnectionError");
            return null;
        }
    }

    private static String removeQuotes(String str) {
        return (str == null || !str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) || !str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED)) ? str : GeneratedOutlineSupport1.outline51(str, 1, 1);
    }

    private static void sendUnexpectedErrorMessageToEventBus(String str) {
        EventBusUtil eventBusUtil2 = eventBusUtil;
        eventBusUtil2.sendMessageToEventBus("error::" + str);
    }
}
