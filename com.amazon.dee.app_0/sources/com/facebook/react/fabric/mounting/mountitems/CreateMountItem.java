package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
/* loaded from: classes2.dex */
public class CreateMountItem implements MountItem {
    @NonNull
    private final String mComponent;
    @NonNull
    private final ThemedReactContext mContext;
    private final boolean mIsLayoutable;
    @Nullable
    private final ReadableMap mProps;
    private final int mReactTag;
    private final int mRootTag;
    @Nullable
    private final StateWrapper mStateWrapper;

    public CreateMountItem(@Nullable ThemedReactContext themedReactContext, int i, int i2, @NonNull String str, @Nullable ReadableMap readableMap, @NonNull StateWrapper stateWrapper, boolean z) {
        this.mContext = themedReactContext;
        this.mComponent = str;
        this.mRootTag = i;
        this.mReactTag = i2;
        this.mProps = readableMap;
        this.mStateWrapper = stateWrapper;
        this.mIsLayoutable = z;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        ThemedReactContext themedReactContext = this.mContext;
        if (themedReactContext != null) {
            mountingManager.createView(themedReactContext, this.mComponent, this.mReactTag, this.mProps, this.mStateWrapper, this.mIsLayoutable);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot execute PreAllocateViewMountItem without Context for ReactTag: ");
        outline107.append(this.mReactTag);
        outline107.append(" and rootTag: ");
        outline107.append(this.mRootTag);
        throw new IllegalStateException(outline107.toString());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateMountItem [");
        outline107.append(this.mReactTag);
        outline107.append("] - component: ");
        outline107.append(this.mComponent);
        outline107.append(" - rootTag: ");
        outline107.append(this.mRootTag);
        outline107.append(" - isLayoutable: ");
        outline107.append(this.mIsLayoutable);
        return outline107.toString();
    }
}
