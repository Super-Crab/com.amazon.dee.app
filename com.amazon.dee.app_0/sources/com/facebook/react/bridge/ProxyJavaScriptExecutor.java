package com.facebook.react.bridge;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaJSExecutor;
/* loaded from: classes2.dex */
public class ProxyJavaScriptExecutor extends JavaScriptExecutor {
    @Nullable
    private JavaJSExecutor mJavaJSExecutor;

    /* loaded from: classes2.dex */
    public static class Factory implements JavaScriptExecutorFactory {
        private final JavaJSExecutor.Factory mJavaJSExecutorFactory;

        public Factory(JavaJSExecutor.Factory factory) {
            this.mJavaJSExecutorFactory = factory;
        }

        @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
        public JavaScriptExecutor create() throws Exception {
            return new ProxyJavaScriptExecutor(this.mJavaJSExecutorFactory.create());
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
    }

    static {
        ReactBridge.staticInit();
    }

    public ProxyJavaScriptExecutor(JavaJSExecutor javaJSExecutor) {
        super(initHybrid(javaJSExecutor));
        this.mJavaJSExecutor = javaJSExecutor;
    }

    private static native HybridData initHybrid(JavaJSExecutor javaJSExecutor);

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public void close() {
        JavaJSExecutor javaJSExecutor = this.mJavaJSExecutor;
        if (javaJSExecutor != null) {
            javaJSExecutor.close();
            this.mJavaJSExecutor = null;
        }
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public String getName() {
        return "ProxyJavaScriptExecutor";
    }
}
