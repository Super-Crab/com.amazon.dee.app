package com.amazon.alexa.devicesetup.bridge;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.devicesetup.impl.DefaultDeviceProvisioningCoordinator;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.DeviceSetupPresenterFactory;
import com.amazon.alexa.devicesetup.service.FFSProvisioningInvalidPskException;
import com.amazon.alexa.devicesetup.service.FFSProvisioningInvalidWepKeyException;
import com.amazon.alexa.devicesetup.service.NullFFSProvisioningConfigurationException;
import com.amazon.alexa.devicesetup.utils.EventBusUtil;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BarcodeFormatException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.PublicKeyDecompressionException;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevice;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ClientProvisioningDataModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes7.dex */
public class FFSProvisioningBridge extends ReactContextBaseJavaModule {
    public static final String DEVICE_LANGUAGE_LOCALE_CONFIGURATION = "LocaleConfiguration.LanguageLocale";
    private static final String MODULE_NAME = "FFSProvisioningService";
    private static final String TAG = "FFSProvisioningBridge";
    private static final List<Integer> WEP_ASCII_KEY_LENGTHS = Arrays.asList(5, 13, 16, 29);
    private static final List<Integer> WEP_HEX_KEY_LENGTHS = Arrays.asList(10, 26, 32, 58);
    private AutoDiscoveryPresenterContract.Action autoDiscoveryPresenter;
    EventBusUtil eventBus;
    DeviceSetupPresenterFactory factory;
    private ControlledSetupPresenter presenter;
    private final ReactApplicationContext reactApplicationContext;

    public FFSProvisioningBridge(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactApplicationContext = reactApplicationContext;
        this.eventBus = getEventBus();
        this.factory = getPresenterFactory();
    }

    private String addQuotes(String str) {
        return GeneratedOutlineSupport1.outline75(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, str, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    private AutoDiscoveryPresenterContract.Action getAutoDiscoveryPresenter() {
        AutoDiscoveryPresenterContract.Action action = this.autoDiscoveryPresenter;
        if (action == null) {
            action = null;
            try {
                this.autoDiscoveryPresenter = DeviceSetupPresenterFactory.createAutoDiscoverySetupPresenter(this.reactApplicationContext, DefaultDeviceProvisioningCoordinator.getffs().getProvisioningServiceConfiguration());
                return this.autoDiscoveryPresenter;
            } catch (NullFFSProvisioningConfigurationException e) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to get config during for Autodiscovery presenter: ");
                outline107.append(e.getMessage());
                Log.e(str, outline107.toString());
            } catch (Throwable th) {
                String str2 = TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Failed on creating auto discovery presenter: ");
                outline1072.append(th.getMessage());
                Log.e(str2, outline1072.toString());
                sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::createAutoDiscoveryPresenter");
                return null;
            }
        }
        return action;
    }

    @ReactMethod
    public void chooseDevice(String str, String str2) {
        if (this.presenter == null) {
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::chooseDevice");
            return;
        }
        try {
            this.presenter.chooseDevice(new DiscoveredDevice(str, str2));
        } catch (Throwable th) {
            String str3 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to chooseDevice: ");
            outline107.append(th.getMessage());
            Log.e(str3, outline107.toString());
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::chooseDevice");
        }
    }

    @VisibleForTesting
    void cleanAutoDiscoveryPresenter() {
        this.autoDiscoveryPresenter = null;
    }

    @ReactMethod
    public void discoverDevices() {
        try {
            if (this.presenter == null) {
                this.presenter = DeviceSetupPresenterFactory.createSmartHomePresenter(this.reactApplicationContext.getCurrentActivity(), DefaultDeviceProvisioningCoordinator.getffs().getProvisioningServiceConfiguration());
            }
            this.presenter.discoverDevices();
        } catch (Throwable th) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to discoverDevices: ");
            outline107.append(th.getMessage());
            Log.e(str, outline107.toString());
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::discoverDevices");
        }
    }

    public WifiConfiguration generateWifiConfiguration(String str, String str2, String str3) throws FFSProvisioningInvalidPskException {
        if (str3.equals(WifiKeyManagement.WPA_PSK.toString())) {
            if (InputValidator.isValidPsk(addQuotes(str2))) {
                return WifiConfiguration.createWpaWifiConfiguration(addQuotes(str), addQuotes(str2));
            }
            throw new FFSProvisioningInvalidPskException("WPA PSK is invalid.");
        } else if (str3.equals(WifiKeyManagement.WEP.toString())) {
            if (WEP_ASCII_KEY_LENGTHS.contains(Integer.valueOf(str2.length()))) {
                str2 = addQuotes(str2);
            }
            if (InputValidator.isValidWepKey(str2)) {
                return WifiConfiguration.createWepWifiConfiguration(addQuotes(str), str2);
            }
            throw new FFSProvisioningInvalidWepKeyException("WEP Key is invalid");
        } else {
            return WifiConfiguration.createOpenWifiConfiguration(addQuotes(str));
        }
    }

    @ReactMethod
    public void getCustomerProvisioneesSetupStatus() {
        AutoDiscoveryPresenterContract.Action autoDiscoveryPresenter = getAutoDiscoveryPresenter();
        if (autoDiscoveryPresenter == null) {
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::getCustomerProvisioneesSetupStatus");
            return;
        }
        try {
            autoDiscoveryPresenter.getCustomerProvisioneesSetupStatus();
        } catch (Throwable th) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to getCustomerProvisioneesSetupStatus: ");
            outline107.append(th.getMessage());
            Log.e(str, outline107.toString());
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::getCustomerProvisioneesSetupStatus");
        }
    }

    @VisibleForTesting
    protected EventBusUtil getEventBus() {
        return EventBusUtil.instance();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @VisibleForTesting
    protected DeviceSetupPresenterFactory getPresenterFactory() {
        return DeviceSetupPresenterFactory.instance();
    }

    @VisibleForTesting
    boolean isValidProvisioningReadableMap(ReadableMap readableMap) {
        return readableMap.hasKey("ssid") && readableMap.getString("ssid") != null && readableMap.hasKey("keyManagement") && readableMap.getString("keyManagement") != null && readableMap.hasKey("saveToWifiLocker") && readableMap.hasKey("overrideLocale");
    }

    @ReactMethod
    public void provisionDevice(String str, String str2, String str3, boolean z) {
        if (this.presenter == null) {
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::provisionDevice");
            return;
        }
        try {
            WifiConfiguration generateWifiConfiguration = generateWifiConfiguration(str, str2, str3);
            HashMap hashMap = new HashMap();
            hashMap.put("LocaleConfiguration.LanguageLocale", DeviceSetupPresenterFactory.getLanguageLocale());
            this.presenter.provisionDevice(new ClientProvisioningDataModel(generateWifiConfiguration, z, hashMap));
        } catch (Exception e) {
            String str4 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to provisionDevice: ");
            outline107.append(e.getMessage());
            Log.e(str4, outline107.toString());
            sendUnexpectedErrorMessageToEventBus(e.toString());
        }
    }

    @ReactMethod
    public void provisionDeviceV2(ReadableMap readableMap) {
        String languageLocale;
        if (this.presenter == null) {
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::provisionDevice");
        } else if (!isValidProvisioningReadableMap(readableMap)) {
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::provisionDevice");
        } else {
            com.amazon.alexa.devicesetup.sdk.whisperjoin.bridge.ProvisionDeviceConfiguration fromReadableMap = com.amazon.alexa.devicesetup.sdk.whisperjoin.bridge.ProvisionDeviceConfiguration.fromReadableMap(readableMap);
            try {
                WifiConfiguration generateWifiConfiguration = generateWifiConfiguration(fromReadableMap.getSsid(), fromReadableMap.getKey(), fromReadableMap.getKeyManagement());
                HashMap hashMap = new HashMap();
                if (fromReadableMap.isOverrideLocale()) {
                    if (fromReadableMap.getDeviceLocale() != null) {
                        languageLocale = fromReadableMap.getDeviceLocale();
                    } else {
                        languageLocale = DeviceSetupPresenterFactory.getLanguageLocale();
                    }
                    hashMap.put("LocaleConfiguration.LanguageLocale", languageLocale);
                    Log.i("Provisioning language: ", languageLocale);
                }
                this.presenter.provisionDevice(new ClientProvisioningDataModel(generateWifiConfiguration, fromReadableMap.isSaveToWifiLocker(), hashMap));
            } catch (IllegalArgumentException e) {
                if (e.getClass() == FFSProvisioningInvalidPskException.class) {
                    sendInvalidInputMessageToEventBus("invalidPsk");
                } else if (e.getClass() != FFSProvisioningInvalidWepKeyException.class) {
                } else {
                    sendInvalidInputMessageToEventBus("invalidWepKey");
                }
            } catch (Exception e2) {
                GeneratedOutlineSupport1.outline148(e2, GeneratedOutlineSupport1.outline107("Unable to provisionDeviceV2: "), TAG);
                sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::provisionDevice");
            }
        }
    }

    @ReactMethod
    public void refreshAvailableNetworks() {
        ControlledSetupPresenter controlledSetupPresenter = this.presenter;
        if (controlledSetupPresenter == null) {
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::refreshAvailableNetworks");
            return;
        }
        try {
            controlledSetupPresenter.refreshAvailableNetworks();
        } catch (Throwable th) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to refreshAvailableNetworks: ");
            outline107.append(th.getMessage());
            Log.e(str, outline107.toString());
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::refreshAvailableNetworks");
        }
    }

    @VisibleForTesting
    void sendInvalidInputMessageToEventBus(String str) {
        EventBusUtil eventBusUtil = this.eventBus;
        eventBusUtil.sendMessageToEventBus("ffs::InvalidInput::" + str);
    }

    @VisibleForTesting
    void sendUnexpectedErrorMessageToEventBus(String str) {
        EventBusUtil eventBusUtil = this.eventBus;
        eventBusUtil.sendMessageToEventBus("error::" + str);
    }

    @ReactMethod
    public void setBarcodeData(String str, Promise promise) {
        try {
            this.presenter = DeviceSetupPresenterFactory.createSmartHomePresenterWithBarcode(this.reactApplicationContext.getCurrentActivity(), DefaultDeviceProvisioningCoordinator.getffs().getProvisioningServiceConfiguration(), str);
            promise.resolve("Barcode data accepted.");
        } catch (NullFFSProvisioningConfigurationException e) {
            promise.reject("ProvisioningServiceConfiguration hasn't been initialized yet.", e);
        } catch (BarcodeFormatException e2) {
            promise.reject("Barcode data is invalid.", e2);
        } catch (PublicKeyDecompressionException e3) {
            promise.reject("Barcode data is invalid.", e3);
        } catch (Throwable th) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to setBarcodeData: ");
            outline107.append(th.getMessage());
            Log.e(str2, outline107.toString());
            promise.reject("Barcode data is invalid.", th);
        }
    }

    @ReactMethod
    public void setDeviceFilter(String str, Promise promise) {
        try {
            this.presenter = DeviceSetupPresenterFactory.createSmartHomePresenterWithDeviceId(this.reactApplicationContext.getCurrentActivity(), DefaultDeviceProvisioningCoordinator.getffs().getProvisioningServiceConfiguration(), str);
            promise.resolve("DeviceID accepted.");
        } catch (NullFFSProvisioningConfigurationException e) {
            promise.reject("ProvisioningServiceConfiguration hasn't been initialized yet.", e);
        } catch (Throwable th) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to setDeviceFilter: ");
            outline107.append(th.getMessage());
            Log.e(str2, outline107.toString());
            promise.reject("DeviceID is invalid.", th);
        }
    }

    @ReactMethod
    public void setProductFilter(String str, Promise promise) {
        try {
            this.presenter = DeviceSetupPresenterFactory.createSmartHomePresenterWithProductId(this.reactApplicationContext.getCurrentActivity(), DefaultDeviceProvisioningCoordinator.getffs().getProvisioningServiceConfiguration(), str);
            promise.resolve("ProductID accepted.");
        } catch (NullFFSProvisioningConfigurationException e) {
            promise.reject("ProvisioningServiceConfiguration hasn't been initialized yet.", e);
        } catch (Throwable th) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to setProductFilter: ");
            outline107.append(th.getMessage());
            Log.e(str2, outline107.toString());
            promise.reject("ProductID is invalid.", th);
        }
    }

    @ReactMethod
    public void startAutoDiscovery() {
        AutoDiscoveryPresenterContract.Action autoDiscoveryPresenter = getAutoDiscoveryPresenter();
        if (autoDiscoveryPresenter == null) {
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::startAutoDiscovery");
            return;
        }
        try {
            autoDiscoveryPresenter.startDiscovery();
        } catch (Throwable th) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to startAutoDiscovery: ");
            outline107.append(th.getMessage());
            Log.e(str, outline107.toString());
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::startAutoDiscovery");
        }
    }

    @ReactMethod
    public void stopAutoDiscovery() {
        AutoDiscoveryPresenterContract.Action action = this.autoDiscoveryPresenter;
        if (action == null) {
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::stopAutoDiscovery");
            return;
        }
        try {
            action.terminate();
        } finally {
            try {
            } finally {
            }
        }
    }

    @ReactMethod
    public void terminateSetup() {
        ControlledSetupPresenter controlledSetupPresenter = this.presenter;
        if (controlledSetupPresenter == null) {
            sendUnexpectedErrorMessageToEventBus("FFSProvisioningBridge::terminateSetup");
            return;
        }
        try {
            controlledSetupPresenter.terminateSetup();
        } finally {
            try {
            } finally {
            }
        }
    }
}
