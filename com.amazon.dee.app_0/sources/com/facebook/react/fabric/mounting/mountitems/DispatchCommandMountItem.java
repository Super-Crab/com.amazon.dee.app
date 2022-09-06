package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.UiThread;
/* loaded from: classes2.dex */
public abstract class DispatchCommandMountItem implements MountItem {
    private int mNumRetries = 0;

    @UiThread
    public int getRetries() {
        return this.mNumRetries;
    }

    @UiThread
    public void incrementRetries() {
        this.mNumRetries++;
    }
}
