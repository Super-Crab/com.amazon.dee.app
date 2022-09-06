package com.amazon.alexa.drive.smart.device.lock;

import android.graphics.drawable.Drawable;
import com.amazon.alexa.drive.smart.device.data.SmartDevice;
/* loaded from: classes7.dex */
public class Lock extends SmartDevice {
    private String description;
    private Drawable icon;
    private String lastUserAction;
    private String lockState;
    private boolean reachable;
    private String title;

    public Lock() {
        super(2);
    }

    public String getDescription() {
        return this.description;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public String getLastUserAction() {
        return this.lastUserAction;
    }

    public String getLockState() {
        return this.lockState;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isReachable() {
        return this.reachable;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setIcon(Drawable drawable) {
        this.icon = drawable;
    }

    public void setLastUserAction(String str) {
        this.lastUserAction = str;
    }

    public void setLockState(String str) {
        this.lockState = str;
    }

    public void setReachable(boolean z) {
        this.reachable = z;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
