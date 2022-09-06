package com.facebook.hermes.reactexecutor;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.soloader.SoLoader;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class HermesExecutor extends JavaScriptExecutor {
    private static String mode_;

    static {
        SoLoader.loadLibrary("hermes");
        try {
            SoLoader.loadLibrary("hermes-executor-debug");
            mode_ = "Debug";
        } catch (UnsatisfiedLinkError unused) {
            SoLoader.loadLibrary("hermes-executor-release");
            mode_ = "Release";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HermesExecutor(@Nullable RuntimeConfig runtimeConfig) {
        super(runtimeConfig == null ? initHybridDefaultConfig() : initHybrid(runtimeConfig.heapSizeMB));
    }

    public static native boolean canLoadFile(String str);

    private static native HybridData initHybrid(long j);

    private static native HybridData initHybridDefaultConfig();

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public String getName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HermesExecutor");
        outline107.append(mode_);
        return outline107.toString();
    }
}
