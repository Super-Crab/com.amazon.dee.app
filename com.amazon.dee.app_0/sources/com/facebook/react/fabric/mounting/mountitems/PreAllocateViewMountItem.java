package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
/* loaded from: classes2.dex */
public class PreAllocateViewMountItem implements MountItem {
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

    public PreAllocateViewMountItem(@Nullable ThemedReactContext themedReactContext, int i, int i2, @NonNull String str, @Nullable ReadableMap readableMap, @NonNull StateWrapper stateWrapper, boolean z) {
        this.mContext = themedReactContext;
        this.mComponent = str;
        this.mRootTag = i;
        this.mProps = readableMap;
        this.mStateWrapper = stateWrapper;
        this.mReactTag = i2;
        this.mIsLayoutable = z;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        if (FabricUIManager.ENABLE_FABRIC_LOGS) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Executing pre-allocation of: ");
            outline107.append(toString());
            FLog.d(FabricUIManager.TAG, outline107.toString());
        }
        ThemedReactContext themedReactContext = this.mContext;
        if (themedReactContext != null) {
            mountingManager.preallocateView(themedReactContext, this.mComponent, this.mReactTag, this.mProps, this.mStateWrapper, this.mIsLayoutable);
            return;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Cannot execute PreAllocateViewMountItem without Context for ReactTag: ");
        outline1072.append(this.mReactTag);
        outline1072.append(" and rootTag: ");
        outline1072.append(this.mRootTag);
        throw new IllegalStateException(outline1072.toString());
    }

    public int getRootTag() {
        return this.mRootTag;
    }

    public String toString() {
        return "PreAllocateViewMountItem [" + this.mReactTag + "] - component: " + this.mComponent + " rootTag: " + this.mRootTag + " isLayoutable: " + this.mIsLayoutable;
    }
}
