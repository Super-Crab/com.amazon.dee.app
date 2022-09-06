package com.facebook.react.bridge;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import java.util.Collection;
import java.util.List;
/* loaded from: classes2.dex */
public interface CatalystInstance extends MemoryPressureListener, JSInstance, JSBundleLoaderDelegate {
    void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener);

    void addJSIModules(List<JSIModuleSpec> list);

    void callFunction(String str, String str2, NativeArray nativeArray);

    void destroy();

    void extendNativeModules(NativeModuleRegistry nativeModuleRegistry);

    /* renamed from: getJSCallInvokerHolder */
    CallInvokerHolder mo6930getJSCallInvokerHolder();

    JSIModule getJSIModule(JSIModuleType jSIModuleType);

    <T extends JavaScriptModule> T getJSModule(Class<T> cls);

    @Deprecated
    JavaScriptContextHolder getJavaScriptContextHolder();

    /* renamed from: getNativeCallInvokerHolder */
    CallInvokerHolder mo6931getNativeCallInvokerHolder();

    @Nullable
    <T extends NativeModule> T getNativeModule(Class<T> cls);

    @Nullable
    NativeModule getNativeModule(String str);

    Collection<NativeModule> getNativeModules();

    ReactQueueConfiguration getReactQueueConfiguration();

    RuntimeExecutor getRuntimeExecutor();

    @Nullable
    String getSourceURL();

    <T extends NativeModule> boolean hasNativeModule(Class<T> cls);

    boolean hasRunJSBundle();

    @VisibleForTesting
    void initialize();

    @Override // com.facebook.react.bridge.JSInstance
    void invokeCallback(int i, NativeArrayInterface nativeArrayInterface);

    boolean isDestroyed();

    void registerSegment(int i, String str);

    void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener);

    void runJSBundle();

    @VisibleForTesting
    void setGlobalVariable(String str, String str2);

    void setTurboModuleManager(JSIModule jSIModule);
}
