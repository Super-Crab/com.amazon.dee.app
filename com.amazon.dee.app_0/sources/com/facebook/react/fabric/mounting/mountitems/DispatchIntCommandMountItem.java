package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.fabric.mounting.MountingManager;
/* loaded from: classes2.dex */
public class DispatchIntCommandMountItem extends DispatchCommandMountItem {
    @Nullable
    private final ReadableArray mCommandArgs;
    private final int mCommandId;
    private final int mReactTag;

    public DispatchIntCommandMountItem(int i, int i2, @Nullable ReadableArray readableArray) {
        this.mReactTag = i;
        this.mCommandId = i2;
        this.mCommandArgs = readableArray;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        mountingManager.receiveCommand(this.mReactTag, this.mCommandId, this.mCommandArgs);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DispatchIntCommandMountItem [");
        outline107.append(this.mReactTag);
        outline107.append("] ");
        outline107.append(this.mCommandId);
        return outline107.toString();
    }
}
