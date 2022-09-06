package com.amazon.alexa.accessorykit.internal;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
/* loaded from: classes6.dex */
public final class RNEventEmitter implements DeviceEventManagerModule.RCTDeviceEventEmitter {
    private static RNEventEmitter instance;
    private static final Object lock = new Object();
    private DeviceEventManagerModule.RCTDeviceEventEmitter emitter;

    private RNEventEmitter() {
    }

    public static RNEventEmitter getInstance() {
        RNEventEmitter rNEventEmitter;
        synchronized (lock) {
            if (instance == null) {
                instance = new RNEventEmitter();
            }
            rNEventEmitter = instance;
        }
        return rNEventEmitter;
    }

    @Override // com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter
    public void emit(String str, @Nullable Object obj) {
        synchronized (lock) {
            if (this.emitter != null) {
                this.emitter.emit(str, obj);
            }
        }
    }

    public void initialize(ReactApplicationContext reactApplicationContext) {
        synchronized (lock) {
            this.emitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
    }
}
