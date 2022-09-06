package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.fabric.mounting.MountingManager;
/* loaded from: classes2.dex */
public class UpdatePaddingMountItem implements MountItem {
    private final int mBottom;
    private final int mLeft;
    private final int mReactTag;
    private final int mRight;
    private final int mTop;

    public UpdatePaddingMountItem(int i, int i2, int i3, int i4, int i5) {
        this.mReactTag = i;
        this.mLeft = i2;
        this.mTop = i3;
        this.mRight = i4;
        this.mBottom = i5;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        mountingManager.updatePadding(this.mReactTag, this.mLeft, this.mTop, this.mRight, this.mBottom);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UpdatePaddingMountItem [");
        outline107.append(this.mReactTag);
        outline107.append("] - left: ");
        outline107.append(this.mLeft);
        outline107.append(" - top: ");
        outline107.append(this.mTop);
        outline107.append(" - right: ");
        outline107.append(this.mRight);
        outline107.append(" - bottom: ");
        outline107.append(this.mBottom);
        return outline107.toString();
    }
}
