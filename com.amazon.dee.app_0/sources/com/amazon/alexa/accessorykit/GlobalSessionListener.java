package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.Accessory;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
/* loaded from: classes6.dex */
public class GlobalSessionListener {
    public static final String ACCESSORY_CONNECTION_EVENT_NAME = "ama-accessory-connection";
    public static final String CONNECTION_EVENT_KEY_CONNECTED = "connected";
    public static final String CONNECTION_EVENT_KEY_IDENTIFIER = "id";
    public static final String CONNECTION_EVENT_KEY_NAME = "name";
    public static final String CONNECTION_EVENT_KEY_TRANSPORT = "transport";
    private final ContainerProvider mContainerProvider;
    private final ReactApplicationContext mReactContext;

    public GlobalSessionListener(ReactApplicationContext reactApplicationContext, ContainerProvider containerProvider) {
        this.mReactContext = reactApplicationContext;
        this.mContainerProvider = containerProvider;
    }

    private WritableMap getConnectionEvent(Accessory accessory, boolean z) {
        WritableMap map = this.mContainerProvider.getMap();
        map.putString("name", accessory.getName());
        map.putString("id", accessory.getAddress());
        map.putInt("transport", accessory.getTransport().ordinal());
        map.putBoolean("connected", z);
        return map;
    }

    public void onAccessorySessionConnected(Accessory accessory) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ACCESSORY_CONNECTION_EVENT_NAME, getConnectionEvent(accessory, true));
    }

    public void onAccessorySessionReleased(Accessory accessory) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ACCESSORY_CONNECTION_EVENT_NAME, getConnectionEvent(accessory, false));
    }
}
