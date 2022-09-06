package com.facebook.react.fabric.mounting.mountitems;

import android.annotation.TargetApi;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.fabric.mounting.MountingManager;
/* loaded from: classes2.dex */
public class UpdateLayoutMountItem implements MountItem {
    private final int mHeight;
    private final int mLayoutDirection;
    private final int mReactTag;
    private final int mWidth;
    private final int mX;
    private final int mY;

    public UpdateLayoutMountItem(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mReactTag = i;
        this.mX = i2;
        this.mY = i3;
        this.mWidth = i4;
        this.mHeight = i5;
        this.mLayoutDirection = convertLayoutDirection(i6);
    }

    @TargetApi(21)
    private static int convertLayoutDirection(int i) {
        if (i != 0) {
            if (i == 1) {
                return 0;
            }
            if (i != 2) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unsupported layout direction: ", i));
            }
            return 1;
        }
        return 2;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        mountingManager.updateLayout(this.mReactTag, this.mX, this.mY, this.mWidth, this.mHeight);
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getLayoutDirection() {
        return this.mLayoutDirection;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UpdateLayoutMountItem [");
        outline107.append(this.mReactTag);
        outline107.append("] - x: ");
        outline107.append(this.mX);
        outline107.append(" - y: ");
        outline107.append(this.mY);
        outline107.append(" - height: ");
        outline107.append(this.mHeight);
        outline107.append(" - width: ");
        outline107.append(this.mWidth);
        outline107.append(" - layoutDirection: ");
        outline107.append(this.mLayoutDirection);
        return outline107.toString();
    }
}
