package com.amazon.comms.calling.service;
/* loaded from: classes11.dex */
public class PlatformVoIPSelection {
    private final boolean enableDolbyDap;
    private final boolean enableLibasp;

    public PlatformVoIPSelection(boolean z, boolean z2) {
        this.enableLibasp = z;
        this.enableDolbyDap = z2;
    }

    public boolean isEnableDolbyDap() {
        return this.enableDolbyDap;
    }

    public boolean isEnableLibasp() {
        return this.enableLibasp;
    }
}
