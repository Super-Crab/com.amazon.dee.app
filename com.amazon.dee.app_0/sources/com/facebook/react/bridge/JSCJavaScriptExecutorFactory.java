package com.facebook.react.bridge;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.common.ReactConstants;
/* loaded from: classes2.dex */
public class JSCJavaScriptExecutorFactory implements JavaScriptExecutorFactory {
    private final String mAppName;
    private final String mDeviceName;

    public JSCJavaScriptExecutorFactory(String str, String str2) {
        this.mAppName = str;
        this.mDeviceName = str2;
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public JavaScriptExecutor create() throws Exception {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("OwnerIdentity", ReactConstants.TAG);
        writableNativeMap.putString("AppIdentity", this.mAppName);
        writableNativeMap.putString("DeviceIdentity", this.mDeviceName);
        return new JSCJavaScriptExecutor(writableNativeMap);
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public void startSamplingProfiler() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Starting sampling profiler not supported on ");
        outline107.append(toString());
        throw new UnsupportedOperationException(outline107.toString());
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public void stopSamplingProfiler(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Stopping sampling profiler not supported on ");
        outline107.append(toString());
        throw new UnsupportedOperationException(outline107.toString());
    }

    public String toString() {
        return "JSCExecutor";
    }
}
