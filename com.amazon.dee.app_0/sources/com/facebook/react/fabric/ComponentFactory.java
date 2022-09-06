package com.facebook.react.fabric;

import androidx.annotation.NonNull;
import com.facebook.jni.HybridData;
/* loaded from: classes2.dex */
public class ComponentFactory {
    @NonNull
    private final HybridData mHybridData = initHybrid();

    static {
        FabricSoLoader.staticInit();
    }

    private static native HybridData initHybrid();
}
