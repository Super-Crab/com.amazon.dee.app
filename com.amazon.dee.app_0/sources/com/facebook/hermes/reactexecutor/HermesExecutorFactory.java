package com.facebook.hermes.reactexecutor;

import com.facebook.hermes.instrumentation.HermesSamplingProfiler;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
/* loaded from: classes2.dex */
public class HermesExecutorFactory implements JavaScriptExecutorFactory {
    private static final String TAG = "Hermes";
    private final RuntimeConfig mConfig;

    public HermesExecutorFactory() {
        this(new RuntimeConfig(1024L));
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public JavaScriptExecutor create() {
        return new HermesExecutor(this.mConfig);
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public void startSamplingProfiler() {
        HermesSamplingProfiler.enable();
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public void stopSamplingProfiler(String str) {
        HermesSamplingProfiler.dumpSampledTraceToFile(str);
        HermesSamplingProfiler.disable();
    }

    public String toString() {
        return "JSIExecutor+HermesRuntime";
    }

    public HermesExecutorFactory(RuntimeConfig runtimeConfig) {
        this.mConfig = runtimeConfig;
    }
}
