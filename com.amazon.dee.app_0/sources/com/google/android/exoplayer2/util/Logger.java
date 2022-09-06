package com.google.android.exoplayer2.util;

import java.util.Arrays;
/* loaded from: classes2.dex */
public class Logger {
    private static final int[] enabledModules = new int[Module.All.ordinal()];
    private int mModule;
    private String mTag;

    /* loaded from: classes2.dex */
    public enum Module {
        Unknown,
        AudioVideoCommon,
        Audio,
        Video,
        AudioVideo,
        Text,
        Source,
        Manifest,
        Player,
        All
    }

    static {
        Arrays.fill(enabledModules, 4);
    }

    public Logger(Module module, String str) {
        this.mTag = "UNKNOWN";
        this.mModule = Module.Unknown.ordinal();
        if (str != null) {
            this.mTag = str;
            this.mModule = module.ordinal();
            return;
        }
        throw new IllegalArgumentException("Null Tag");
    }

    public static void setLogLevel(Module module, int i) {
        if (module.compareTo(Module.All) == 0) {
            Arrays.fill(enabledModules, i);
        } else {
            enabledModules[module.ordinal()] = i;
        }
        if (module.compareTo(Module.Audio) >= 0 && module.compareTo(Module.AudioVideo) <= 0) {
            enabledModules[Module.AudioVideoCommon.ordinal()] = i;
        }
        if (module.compareTo(Module.AudioVideo) == 0) {
            enabledModules[Module.Audio.ordinal()] = i;
            enabledModules[Module.Video.ordinal()] = i;
        }
    }

    public boolean allowDebug() {
        return enabledModules[this.mModule] <= 3;
    }

    public boolean allowVerbose() {
        return enabledModules[this.mModule] == 2;
    }

    public void d(String str) {
        int i = enabledModules[this.mModule];
    }

    public void e(String str) {
        android.util.Log.e(this.mTag, str);
    }

    public void i(String str) {
        android.util.Log.i(this.mTag, str);
    }

    public void setModule(Module module) {
        this.mModule = module.ordinal();
    }

    public void setTAG(String str) {
        if (str != null) {
            this.mTag = str;
            return;
        }
        throw new IllegalArgumentException("Null Tag");
    }

    public void v(String str) {
        int i = enabledModules[this.mModule];
    }

    public void w(String str) {
        android.util.Log.w(this.mTag, str);
    }

    public void e(String str, Throwable th) {
        android.util.Log.e(this.mTag, str, th);
    }
}
