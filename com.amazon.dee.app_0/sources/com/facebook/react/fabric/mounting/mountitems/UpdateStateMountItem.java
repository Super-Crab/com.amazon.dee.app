package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.uimanager.StateWrapper;
/* loaded from: classes2.dex */
public class UpdateStateMountItem implements MountItem {
    private final int mReactTag;
    @Nullable
    private final StateWrapper mStateWrapper;

    public UpdateStateMountItem(int i, @Nullable StateWrapper stateWrapper) {
        this.mReactTag = i;
        this.mStateWrapper = stateWrapper;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        mountingManager.updateState(this.mReactTag, this.mStateWrapper);
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(new StringBuilder("UpdateStateMountItem ["), this.mReactTag, "]");
    }
}
