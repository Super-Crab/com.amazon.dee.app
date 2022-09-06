package com.facebook.react.uimanager;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.JSIModule;
import com.facebook.react.bridge.JSIModuleType;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
/* loaded from: classes2.dex */
public class ThemedReactContext extends ReactContext {
    private final ReactApplicationContext mReactApplicationContext;
    @Nullable
    private final String mSurfaceID;

    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context context) {
        this(reactApplicationContext, context, null);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void addLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.mReactApplicationContext.addLifecycleEventListener(lifecycleEventListener);
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Nullable
    public Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public JSIModule getJSIModule(JSIModuleType jSIModuleType) {
        if (isBridgeless()) {
            return this.mReactApplicationContext.getJSIModule(jSIModuleType);
        }
        return super.getJSIModule(jSIModuleType);
    }

    public ReactApplicationContext getReactApplicationContext() {
        return this.mReactApplicationContext;
    }

    @Nullable
    public String getSurfaceID() {
        return this.mSurfaceID;
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean hasCurrentActivity() {
        return this.mReactApplicationContext.hasCurrentActivity();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean isBridgeless() {
        return this.mReactApplicationContext.isBridgeless();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void removeLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.mReactApplicationContext.removeLifecycleEventListener(lifecycleEventListener);
    }

    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context context, @Nullable String str) {
        super(context);
        if (reactApplicationContext.hasCatalystInstance()) {
            initializeWithInstance(reactApplicationContext.getCatalystInstance());
        }
        this.mReactApplicationContext = reactApplicationContext;
        this.mSurfaceID = str;
    }
}
