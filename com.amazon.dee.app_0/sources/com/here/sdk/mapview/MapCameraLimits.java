package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.AngleRange;
import com.here.sdk.core.GeoBox;
/* loaded from: classes3.dex */
public final class MapCameraLimits extends NativeBase {
    public static final double MAX_TILT = 70.0d;
    public static final double MAX_ZOOM_LEVEL = 22.0d;
    public static final double MIN_TILT = 0.0d;
    public static final double MIN_ZOOM_LEVEL = 0.0d;

    /* loaded from: classes3.dex */
    public enum ErrorCode {
        VALUE_ABOVE_ABSOLUTE_MAXIMUM(1),
        VALUE_BELOW_ABSOLUTE_MINIMUM(2),
        MINIMUM_ABOVE_MAXIMUM(3),
        MAXIMUM_BELOW_MINIMUM(4);
        
        public final int value;

        ErrorCode(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes3.dex */
    public static final class MapCameraLimitsException extends Exception {
        public final ErrorCode error;

        public MapCameraLimitsException(ErrorCode errorCode) {
            super(errorCode.toString());
            this.error = errorCode;
        }
    }

    protected MapCameraLimits(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapCameraLimits.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapCameraLimits.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @NonNull
    public native AngleRange getBearingRange();

    @Nullable
    public native GeoBox getTargetArea();

    public native void setBearingRange(@NonNull AngleRange angleRange);

    native void setHarpMapview(@NonNull HarpMapView harpMapView);

    public native void setMaxTilt(double d) throws MapCameraLimitsException;

    public native void setMaxZoomLevel(double d) throws MapCameraLimitsException;

    public native void setMinTilt(double d) throws MapCameraLimitsException;

    public native void setMinZoomLevel(double d) throws MapCameraLimitsException;

    public native void setTargetArea(@Nullable GeoBox geoBox);
}
