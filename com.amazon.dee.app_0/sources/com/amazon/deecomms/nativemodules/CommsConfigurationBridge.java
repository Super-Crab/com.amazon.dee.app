package com.amazon.deecomms.nativemodules;

import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
/* loaded from: classes12.dex */
public class CommsConfigurationBridge extends ReactContextBaseJavaModule {
    private final ArcusConfig mArcusConfig;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommsConfigurationBridge(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        ArcusConfig arcusConfig = CommsDaggerWrapper.getComponent().getArcusConfig();
        this.mArcusConfig = arcusConfig;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsConfiguration";
    }

    @ReactMethod
    public void getValueForPath(String str, Promise promise) {
        promise.resolve(this.mArcusConfig.getConfigString(str));
    }

    public CommsConfigurationBridge(ReactApplicationContext reactApplicationContext, ArcusConfig arcusConfig) {
        super(reactApplicationContext);
        this.mArcusConfig = arcusConfig;
    }
}
