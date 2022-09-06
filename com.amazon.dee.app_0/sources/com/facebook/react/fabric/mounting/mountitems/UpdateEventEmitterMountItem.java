package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
/* loaded from: classes2.dex */
public class UpdateEventEmitterMountItem implements MountItem {
    @NonNull
    private final EventEmitterWrapper mEventHandler;
    private final int mReactTag;

    public UpdateEventEmitterMountItem(int i, @NonNull EventEmitterWrapper eventEmitterWrapper) {
        this.mReactTag = i;
        this.mEventHandler = eventEmitterWrapper;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        mountingManager.updateEventEmitter(this.mReactTag, this.mEventHandler);
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("UpdateEventEmitterMountItem ["), this.mReactTag, "]");
    }
}
