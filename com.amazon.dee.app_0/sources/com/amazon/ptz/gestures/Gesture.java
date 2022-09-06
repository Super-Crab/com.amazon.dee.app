package com.amazon.ptz.gestures;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.Generated;
/* loaded from: classes13.dex */
public final class Gesture {
    @Nonnull
    private final GestureType gestureType;
    @Nullable
    private final TransformInfo transformInfo;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Gesture(@Nonnull GestureType gestureType, @Nullable TransformInfo transformInfo) {
        if (gestureType != null) {
            this.gestureType = gestureType;
            this.transformInfo = transformInfo;
            return;
        }
        throw new IllegalArgumentException("gestureType is null");
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Gesture)) {
            return false;
        }
        Gesture gesture = (Gesture) obj;
        GestureType gestureType = getGestureType();
        GestureType gestureType2 = gesture.getGestureType();
        if (gestureType != null ? !gestureType.equals(gestureType2) : gestureType2 != null) {
            return false;
        }
        TransformInfo transformInfo = getTransformInfo();
        TransformInfo transformInfo2 = gesture.getTransformInfo();
        return transformInfo != null ? transformInfo.equals(transformInfo2) : transformInfo2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Nonnull
    @Generated
    public GestureType getGestureType() {
        return this.gestureType;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    @Nullable
    public TransformInfo getTransformInfo() {
        return this.transformInfo;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        GestureType gestureType = getGestureType();
        int i = 43;
        int hashCode = gestureType == null ? 43 : gestureType.hashCode();
        TransformInfo transformInfo = getTransformInfo();
        int i2 = (hashCode + 59) * 59;
        if (transformInfo != null) {
            i = transformInfo.hashCode();
        }
        return i2 + i;
    }

    public boolean isZoom() {
        GestureType gestureType = this.gestureType;
        return gestureType == GestureType.ZOOM || gestureType == GestureType.ZOOM_TOGGLE || gestureType == GestureType.ZOOM_IN_CENTER || gestureType == GestureType.ZOOM_OUT_CENTER;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Gesture(gestureType=");
        outline107.append(getGestureType());
        outline107.append(", transformInfo=");
        outline107.append(getTransformInfo());
        outline107.append(")");
        return outline107.toString();
    }
}
