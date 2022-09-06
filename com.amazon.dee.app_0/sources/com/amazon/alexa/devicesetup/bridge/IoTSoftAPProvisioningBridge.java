package com.amazon.alexa.devicesetup.bridge;

import com.amazon.alexa.devicesetup.impl.DefaultDeviceProvisioningCoordinator;
import com.amazon.alexa.devicesetup.service.NullIoTProvisioningConfigurationException;
import com.amazon.alexa.devicesetup.service.NullIoTSoftAPResponseCallbackException;
import com.amazon.alexa.devicesetup.util.EventBusUtil;
import com.amazon.dee.sdk.iotsoftap.IoTSoftApPresenter;
import com.amazon.dee.sdk.iotsoftap.IoTSoftApPresenterFactory;
import com.amazon.whisperjoin.softap.pojos.WifiConfiguration;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
/* loaded from: classes7.dex */
public class IoTSoftAPProvisioningBridge extends ReactContextBaseJavaModule {
    private static final String CONNECT = "IoTSoftApProvisioningBridge::connect";
    private static final String DISCONNECT = "IoTSoftApProvisioningBridge::disconnect";
    private static final String MODULE_NAME = "IoTSoftApProvisioningService";
    private static final String PROVISION_DEVICE = "IoTSoftApProvisioningBridge::provisionDevice";
    private final EventBusUtil eventBus;
    private IoTSoftApPresenter presenter;
    private final ReactApplicationContext reactApplicationContext;

    public IoTSoftAPProvisioningBridge(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.eventBus = EventBusUtil.instance();
        this.reactApplicationContext = reactApplicationContext;
    }

    @ReactMethod
    public void connect(String str) {
        if (this.presenter == null) {
            try {
                this.presenter = IoTSoftApPresenterFactory.getIoTSoftApPresenter(this.reactApplicationContext, DefaultDeviceProvisioningCoordinator.getIoTSoftAPProvider(), DefaultDeviceProvisioningCoordinator.getIoTSoftApResponseCallback());
                if (this.presenter == null) {
                    this.eventBus.sendMessageToEventBus("IoTSoftapError::IoTSoftApProvisioningBridge::connect");
                    return;
                }
            } catch (NullIoTProvisioningConfigurationException | NullIoTSoftAPResponseCallbackException unused) {
                this.eventBus.sendMessageToEventBus("IoTSoftapError::IoTSoftApProvisioningBridge::connect");
                return;
            }
        }
        this.presenter.connect(str);
    }

    @ReactMethod
    public void disconnect() {
        IoTSoftApPresenter ioTSoftApPresenter = this.presenter;
        if (ioTSoftApPresenter != null) {
            ioTSoftApPresenter.disconnect();
            this.presenter = null;
            return;
        }
        this.eventBus.sendMessageToEventBus("IoTSoftapError::IoTSoftApProvisioningBridge::disconnect");
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void provisionDevice(String str, String str2, String str3) {
        if (this.presenter == null) {
            this.eventBus.sendMessageToEventBus("IoTSoftapError::IoTSoftApProvisioningBridge::provisionDevice");
            return;
        }
        this.presenter.provisionDevice(WifiConfiguration.builder().ssid(str).keyMgmt(WifiConfiguration.WifiKeyManagement.valueOf(str2)).key(str3).build());
    }
}
