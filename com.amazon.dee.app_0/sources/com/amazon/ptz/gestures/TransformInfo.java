package com.amazon.ptz.gestures;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes13.dex */
public final class TransformInfo {
    private final float deltaScale;
    private final float deltaX;
    private final float deltaY;
    private final float pivotX;
    private final float pivotY;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public TransformInfo(float f, float f2, float f3, float f4, float f5) {
        this.deltaX = f;
        this.deltaY = f2;
        this.deltaScale = f3;
        this.pivotX = f4;
        this.pivotY = f5;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TransformInfo)) {
            return false;
        }
        TransformInfo transformInfo = (TransformInfo) obj;
        return Float.compare(getDeltaX(), transformInfo.getDeltaX()) == 0 && Float.compare(getDeltaY(), transformInfo.getDeltaY()) == 0 && Float.compare(getDeltaScale(), transformInfo.getDeltaScale()) == 0 && Float.compare(getPivotX(), transformInfo.getPivotX()) == 0 && Float.compare(getPivotY(), transformInfo.getPivotY()) == 0;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public float getDeltaScale() {
        return this.deltaScale;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public float getDeltaX() {
        return this.deltaX;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public float getDeltaY() {
        return this.deltaY;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public float getPivotX() {
        return this.pivotX;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public float getPivotY() {
        return this.pivotY;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        int floatToIntBits = Float.floatToIntBits(getDeltaY());
        int floatToIntBits2 = Float.floatToIntBits(getDeltaScale());
        int floatToIntBits3 = Float.floatToIntBits(getPivotX());
        return Float.floatToIntBits(getPivotY()) + ((floatToIntBits3 + ((floatToIntBits2 + ((floatToIntBits + ((Float.floatToIntBits(getDeltaX()) + 59) * 59)) * 59)) * 59)) * 59);
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TransformInfo(deltaX=");
        outline107.append(getDeltaX());
        outline107.append(", deltaY=");
        outline107.append(getDeltaY());
        outline107.append(", deltaScale=");
        outline107.append(getDeltaScale());
        outline107.append(", pivotX=");
        outline107.append(getPivotX());
        outline107.append(", pivotY=");
        outline107.append(getPivotY());
        outline107.append(")");
        return outline107.toString();
    }
}
