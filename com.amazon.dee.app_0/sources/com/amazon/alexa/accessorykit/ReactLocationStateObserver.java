package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.monitor.LocationStateMonitor;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
/* loaded from: classes6.dex */
public class ReactLocationStateObserver implements LocationStateMonitor.Observer {
    private static final String ACCESSORY_LOCATION_STATE_EVENT_NAME = "ama-location-state-event";
    private static final String LOCATION_STATE_EVENT_ENABLED_KEY = "enabled";
    private final ContainerProvider containerProvider;
    private final ReactApplicationContext reactApplicationContext;

    public ReactLocationStateObserver(ReactApplicationContext reactApplicationContext, ContainerProvider containerProvider) {
        this.reactApplicationContext = reactApplicationContext;
        this.containerProvider = containerProvider;
    }

    private WritableMap getLocationStateEvent(boolean z) {
        WritableMap map = this.containerProvider.getMap();
        map.putBoolean("enabled", z);
        return map;
    }

    @Override // com.amazon.alexa.accessory.monitor.LocationStateMonitor.Observer
    public void onLocationDisabled() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ACCESSORY_LOCATION_STATE_EVENT_NAME, getLocationStateEvent(false));
    }

    @Override // com.amazon.alexa.accessory.monitor.LocationStateMonitor.Observer
    public void onLocationEnabled() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ACCESSORY_LOCATION_STATE_EVENT_NAME, getLocationStateEvent(true));
    }
}
