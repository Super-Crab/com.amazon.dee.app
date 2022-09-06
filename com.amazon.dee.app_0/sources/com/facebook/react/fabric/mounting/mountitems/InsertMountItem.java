package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.fabric.mounting.MountingManager;
/* loaded from: classes2.dex */
public class InsertMountItem implements MountItem {
    private int mIndex;
    private int mParentReactTag;
    private int mReactTag;

    public InsertMountItem(int i, int i2, int i3) {
        this.mReactTag = i;
        this.mParentReactTag = i2;
        this.mIndex = i3;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        mountingManager.addViewAt(this.mParentReactTag, this.mReactTag, this.mIndex);
    }

    public int getIndex() {
        return this.mIndex;
    }

    public int getParentReactTag() {
        return this.mParentReactTag;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InsertMountItem [");
        outline107.append(this.mReactTag);
        outline107.append("] - parentTag: [");
        outline107.append(this.mParentReactTag);
        outline107.append("] - index: ");
        outline107.append(this.mIndex);
        return outline107.toString();
    }
}
