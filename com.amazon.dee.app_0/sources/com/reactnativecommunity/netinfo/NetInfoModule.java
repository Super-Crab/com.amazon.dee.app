package com.reactnativecommunity.netinfo;

import android.os.Build;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.reactnativecommunity.netinfo.AmazonFireDeviceConnectivityPoller;
@ReactModule(name = NetInfoModule.NAME)
/* loaded from: classes3.dex */
public class NetInfoModule extends ReactContextBaseJavaModule implements AmazonFireDeviceConnectivityPoller.ConnectivityChangedCallback {
    public static final String NAME = "RNCNetInfo";
    private final AmazonFireDeviceConnectivityPoller mAmazonConnectivityChecker;
    private final ConnectivityReceiver mConnectivityReceiver;
    private int numberOfListeners;

    public NetInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.numberOfListeners = 0;
        int i = Build.VERSION.SDK_INT;
        this.mConnectivityReceiver = new NetworkCallbackConnectivityReceiver(reactApplicationContext);
        this.mAmazonConnectivityChecker = new AmazonFireDeviceConnectivityPoller(reactApplicationContext, this);
    }

    @ReactMethod
    public void addListener(String str) {
        this.numberOfListeners++;
        this.mConnectivityReceiver.hasListener = true;
    }

    @ReactMethod
    public void getCurrentState(String str, Promise promise) {
        this.mConnectivityReceiver.getCurrentState(str, promise);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        this.mConnectivityReceiver.register();
        this.mAmazonConnectivityChecker.register();
    }

    @Override // com.reactnativecommunity.netinfo.AmazonFireDeviceConnectivityPoller.ConnectivityChangedCallback
    public void onAmazonFireDeviceConnectivityChanged(boolean z) {
        this.mConnectivityReceiver.setIsInternetReachableOverride(z);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mAmazonConnectivityChecker.unregister();
        this.mConnectivityReceiver.unregister();
        this.mConnectivityReceiver.hasListener = false;
    }

    @ReactMethod
    public void removeListeners(Integer num) {
        this.numberOfListeners -= num.intValue();
        if (this.numberOfListeners == 0) {
            this.mConnectivityReceiver.hasListener = false;
        }
    }
}
