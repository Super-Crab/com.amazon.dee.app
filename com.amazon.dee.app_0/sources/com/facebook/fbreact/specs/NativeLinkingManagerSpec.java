package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes2.dex */
public abstract class NativeLinkingManagerSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeLinkingManagerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void addListener(String str);

    @ReactMethod
    public abstract void canOpenURL(String str, Promise promise);

    @ReactMethod
    public abstract void getInitialURL(Promise promise);

    @ReactMethod
    public abstract void openSettings(Promise promise);

    @ReactMethod
    public abstract void openURL(String str, Promise promise);

    @ReactMethod
    public abstract void removeListeners(double d);
}
