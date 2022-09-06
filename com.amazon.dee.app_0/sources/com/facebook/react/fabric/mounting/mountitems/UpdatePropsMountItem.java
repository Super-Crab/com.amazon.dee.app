package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.mounting.MountingManager;
/* loaded from: classes2.dex */
public class UpdatePropsMountItem implements MountItem {
    private final int mReactTag;
    @NonNull
    private final ReadableMap mUpdatedProps;

    public UpdatePropsMountItem(int i, @NonNull ReadableMap readableMap) {
        this.mReactTag = i;
        this.mUpdatedProps = readableMap;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        mountingManager.updateProps(this.mReactTag, this.mUpdatedProps);
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(new StringBuilder("UpdatePropsMountItem ["), this.mReactTag, "]");
    }
}
