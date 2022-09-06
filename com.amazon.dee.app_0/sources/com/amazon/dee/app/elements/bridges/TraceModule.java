package com.amazon.dee.app.elements.bridges;

import android.annotation.SuppressLint;
import android.os.Trace;
import com.amazon.dee.app.services.logging.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = TraceModule.MODULE_NAME)
/* loaded from: classes12.dex */
public class TraceModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "Trace";
    private static final String SET_APP_TRACING_METHOD_NAME = "setAppTracingAllowed";
    private static final String TAG = "TraceModule";

    public TraceModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @SuppressLint({"DiscouragedPrivateApi"})
    public static boolean enableApplicationTracing(boolean z) {
        Log.w(TAG, "Application tracing isn't supported on '%s' builds.", "prod");
        return true;
    }

    @ReactMethod
    public void beginSection(String str) {
        new Object[1][0] = str;
        Trace.beginSection(str);
    }

    @ReactMethod
    public void endSection() {
        Trace.endSection();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }
}
