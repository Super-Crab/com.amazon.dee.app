package com.facebook.react.modules.core;

import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeExceptionsManagerSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.util.ExceptionDataHelper;
import com.facebook.react.util.JSStackTrace;
@ReactModule(name = ExceptionsManagerModule.NAME)
/* loaded from: classes2.dex */
public class ExceptionsManagerModule extends NativeExceptionsManagerSpec {
    public static final String NAME = "ExceptionsManager";
    private final DevSupportManager mDevSupportManager;

    public ExceptionsManagerModule(DevSupportManager devSupportManager) {
        super(null);
        this.mDevSupportManager = devSupportManager;
    }

    @Override // com.facebook.fbreact.specs.NativeExceptionsManagerSpec
    public void dismissRedbox() {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.hideRedboxDialog();
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeExceptionsManagerSpec
    public void reportException(ReadableMap readableMap) {
        String string = readableMap.hasKey("message") ? readableMap.getString("message") : "";
        ReadableArray array = readableMap.hasKey(HttpClientModule.EventEmitterObserver.ElementsErrorKey.STACK) ? readableMap.getArray(HttpClientModule.EventEmitterObserver.ElementsErrorKey.STACK) : Arguments.createArray();
        int i = readableMap.hasKey("id") ? readableMap.getInt("id") : -1;
        boolean z = false;
        boolean z2 = readableMap.hasKey("isFatal") ? readableMap.getBoolean("isFatal") : false;
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            if (readableMap.mo6945getMap(ReactProperties.HereMapMarker.EXTRA_DATA) != null && readableMap.mo6945getMap(ReactProperties.HereMapMarker.EXTRA_DATA).hasKey("suppressRedBox")) {
                z = readableMap.mo6945getMap(ReactProperties.HereMapMarker.EXTRA_DATA).getBoolean("suppressRedBox");
            }
            if (z) {
                return;
            }
            this.mDevSupportManager.showNewJSError(string, array, i);
            return;
        }
        String extraDataAsJson = ExceptionDataHelper.getExtraDataAsJson(readableMap);
        if (!z2) {
            FLog.e(ReactConstants.TAG, JSStackTrace.format(string, array));
            if (extraDataAsJson == null) {
                return;
            }
            FLog.d(ReactConstants.TAG, "extraData: %s", extraDataAsJson);
            return;
        }
        throw new JavascriptException(JSStackTrace.format(string, array)).setExtraDataAsJson(extraDataAsJson);
    }

    @Override // com.facebook.fbreact.specs.NativeExceptionsManagerSpec
    public void reportFatalException(String str, ReadableArray readableArray, double d) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.putString("message", str);
        javaOnlyMap.putArray(HttpClientModule.EventEmitterObserver.ElementsErrorKey.STACK, readableArray);
        javaOnlyMap.putInt("id", (int) d);
        javaOnlyMap.putBoolean("isFatal", true);
        reportException(javaOnlyMap);
    }

    @Override // com.facebook.fbreact.specs.NativeExceptionsManagerSpec
    public void reportSoftException(String str, ReadableArray readableArray, double d) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.putString("message", str);
        javaOnlyMap.putArray(HttpClientModule.EventEmitterObserver.ElementsErrorKey.STACK, readableArray);
        javaOnlyMap.putInt("id", (int) d);
        javaOnlyMap.putBoolean("isFatal", false);
        reportException(javaOnlyMap);
    }

    @Override // com.facebook.fbreact.specs.NativeExceptionsManagerSpec
    public void updateExceptionMessage(String str, ReadableArray readableArray, double d) {
        int i = (int) d;
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.updateJSError(str, readableArray, i);
        }
    }
}
