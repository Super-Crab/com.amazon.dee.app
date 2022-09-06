package com.facebook.hermes.instrumentation;

import com.facebook.soloader.SoLoader;
/* loaded from: classes2.dex */
public class HermesSamplingProfiler {
    static {
        SoLoader.loadLibrary("jsijniprofiler");
    }

    private HermesSamplingProfiler() {
    }

    public static native void disable();

    public static native void dumpSampledTraceToFile(String str);

    public static native void enable();
}
