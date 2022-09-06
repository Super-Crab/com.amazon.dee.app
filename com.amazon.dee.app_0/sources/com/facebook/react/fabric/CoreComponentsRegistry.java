package com.facebook.react.fabric;

import com.facebook.jni.HybridData;
import com.facebook.soloader.SoLoader;
/* loaded from: classes2.dex */
public class CoreComponentsRegistry {
    private final HybridData mHybridData;

    static {
        SoLoader.loadLibrary("fabricjni");
    }

    private CoreComponentsRegistry(ComponentFactory componentFactory) {
        this.mHybridData = initHybrid(componentFactory);
    }

    private native HybridData initHybrid(ComponentFactory componentFactory);

    public static CoreComponentsRegistry register(ComponentFactory componentFactory) {
        return new CoreComponentsRegistry(componentFactory);
    }
}
