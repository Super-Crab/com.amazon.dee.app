package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.monitor.BluetoothStateMonitor;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
/* loaded from: classes6.dex */
public class ReactBluetoothStateObserver implements BluetoothStateMonitor.Observer {
    public static final String ACCESSORY_BLUETOOTH_STATE_EVENT_NAME = "ama-bluetooth-state-event";
    public static final String BLUETOOTH_STATE_EVENT_KEY_BLUETOOTH_ENABLED = "enabled";
    private final ContainerProvider containerProvider;
    private final ReactApplicationContext reactApplicationContext;

    public ReactBluetoothStateObserver(ReactApplicationContext reactApplicationContext, ContainerProvider containerProvider) {
        this.reactApplicationContext = reactApplicationContext;
        this.containerProvider = containerProvider;
    }

    private WritableMap getBluetoothStateEvent(boolean z) {
        WritableMap map = this.containerProvider.getMap();
        map.putBoolean("enabled", z);
        return map;
    }

    @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor.Observer
    public void onBluetoothDisabled() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ACCESSORY_BLUETOOTH_STATE_EVENT_NAME, getBluetoothStateEvent(false));
    }

    @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor.Observer
    public void onBluetoothEnabled() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ACCESSORY_BLUETOOTH_STATE_EVENT_NAME, getBluetoothStateEvent(true));
    }
}
