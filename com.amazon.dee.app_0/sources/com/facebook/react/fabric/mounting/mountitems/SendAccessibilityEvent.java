package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.fabric.mounting.MountingManager;
/* loaded from: classes2.dex */
public class SendAccessibilityEvent implements MountItem {
    private final String TAG = "Fabric.SendAccessibilityEvent";
    private final int mEventType;
    private final int mReactTag;

    public SendAccessibilityEvent(int i, int i2) {
        this.mReactTag = i;
        this.mEventType = i2;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        try {
            mountingManager.sendAccessibilityEvent(this.mReactTag, this.mEventType);
        } catch (RetryableMountingLayerException e) {
            ReactSoftException.logSoftException("Fabric.SendAccessibilityEvent", e);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SendAccessibilityEvent [");
        outline107.append(this.mReactTag);
        outline107.append("] ");
        outline107.append(this.mEventType);
        return outline107.toString();
    }
}
