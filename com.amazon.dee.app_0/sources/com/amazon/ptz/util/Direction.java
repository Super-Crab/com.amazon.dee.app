package com.amazon.ptz.util;

import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
/* loaded from: classes13.dex */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    /* renamed from: com.amazon.ptz.util.Direction$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ptz$util$Direction = new int[Direction.values().length];

        static {
            try {
                $SwitchMap$com$amazon$ptz$util$Direction[Direction.DOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ptz$util$Direction[Direction.UP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ptz$util$Direction[Direction.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ptz$util$Direction[Direction.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static Direction getDirection(float f, float f2) {
        return (Math.abs(f) > Math.abs(f2) ? 1 : (Math.abs(f) == Math.abs(f2) ? 0 : -1)) > 0 ? f > 0.0f ? RIGHT : LEFT : f2 > 0.0f ? DOWN : UP;
    }

    public static CameraPtzInstance mapToPtzInstance(Direction direction) {
        int ordinal = direction.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return CameraPtzInstance.CAMERA_TILT;
        }
        if (ordinal != 2 && ordinal != 3) {
            throw new IllegalArgumentException("Unexpected direction encountered in mapToPtzCapability");
        }
        return CameraPtzInstance.CAMERA_PAN;
    }
}
