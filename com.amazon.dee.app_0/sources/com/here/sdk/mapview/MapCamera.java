package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.imageutils.JfifUtil;
import com.here.NativeBase;
import com.here.sdk.core.GeoBox;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Point2D;
import com.here.sdk.core.Rectangle2D;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class MapCamera extends NativeBase {

    /* loaded from: classes3.dex */
    static class AnimationBuilder extends NativeBase {
        protected AnimationBuilder(long j, Object obj) {
            super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapCamera.AnimationBuilder.1
                @Override // com.here.NativeBase.Disposer
                public void disposeNative(long j2) {
                    AnimationBuilder.disposeNativeHandle(j2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static native void disposeNativeHandle(long j);

        @NonNull
        native Animation build();

        @NonNull
        native AnimationBuilder lookAt(@NonNull GeoBox geoBox, @NonNull OrientationUpdate orientationUpdate, int i, @NonNull EasingFunction easingFunction);

        @NonNull
        native AnimationBuilder lookAt(@NonNull GeoCoordinates geoCoordinates, double d, int i, @NonNull EasingFunction easingFunction);

        @NonNull
        native AnimationBuilder lookAt(@NonNull GeoCoordinates geoCoordinates, int i, @NonNull EasingFunction easingFunction);

        @NonNull
        native AnimationBuilder lookAt(@NonNull GeoCoordinates geoCoordinates, @NonNull OrientationUpdate orientationUpdate, double d, int i, @NonNull EasingFunction easingFunction);

        @NonNull
        native AnimationBuilder zoomBy(double d, @NonNull Point2D point2D, int i, @NonNull EasingFunction easingFunction);
    }

    /* loaded from: classes3.dex */
    public static final class Orientation {
        public double bearing;
        public double tilt;

        public Orientation() {
            this.bearing = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            this.tilt = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }

        public Orientation(double d, double d2) {
            this.bearing = d;
            this.tilt = d2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Orientation)) {
                return false;
            }
            Orientation orientation = (Orientation) obj;
            return Double.compare(this.bearing, orientation.bearing) == 0 && Double.compare(this.tilt, orientation.tilt) == 0;
        }

        public int hashCode() {
            return ((((int) (Double.doubleToLongBits(this.bearing) ^ (Double.doubleToLongBits(this.bearing) >>> 32))) + JfifUtil.MARKER_EOI) * 31) + ((int) (Double.doubleToLongBits(this.tilt) ^ (Double.doubleToLongBits(this.tilt) >>> 32)));
        }
    }

    /* loaded from: classes3.dex */
    public static final class OrientationUpdate {
        @Nullable
        public Double bearing;
        @Nullable
        public Double tilt;

        public OrientationUpdate() {
            this.bearing = null;
            this.tilt = null;
        }

        public OrientationUpdate(@Nullable Double d, @Nullable Double d2) {
            this.bearing = d;
            this.tilt = d2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof OrientationUpdate)) {
                return false;
            }
            OrientationUpdate orientationUpdate = (OrientationUpdate) obj;
            return Objects.equals(this.bearing, orientationUpdate.bearing) && Objects.equals(this.tilt, orientationUpdate.tilt);
        }

        public int hashCode() {
            Double d = this.bearing;
            int i = 0;
            int hashCode = ((d != null ? d.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
            Double d2 = this.tilt;
            if (d2 != null) {
                i = d2.hashCode();
            }
            return hashCode + i;
        }
    }

    /* loaded from: classes3.dex */
    public static final class State {
        public double distanceToTargetInMeters;
        @NonNull
        public GeoCoordinates targetCoordinates;
        @NonNull
        public Orientation targetOrientation;
        public double zoomLevel;

        public State(@NonNull GeoCoordinates geoCoordinates, @NonNull Orientation orientation, double d, double d2) {
            this.targetCoordinates = geoCoordinates;
            this.targetOrientation = orientation;
            this.distanceToTargetInMeters = d;
            this.zoomLevel = d2;
        }
    }

    protected MapCamera(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapCamera.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapCamera.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    public native void addObserver(@NonNull MapCameraObserver mapCameraObserver);

    @NonNull
    native AnimationBuilder animate();

    native void cancelAnimations();

    native void destroy();

    native void flyTo(@NonNull GeoCoordinates geoCoordinates);

    native void flyTo(@NonNull GeoCoordinates geoCoordinates, int i, double d, @Nullable AnimationStateListener animationStateListener);

    native void flyTo(@NonNull GeoCoordinates geoCoordinates, @Nullable AnimationStateListener animationStateListener);

    @Nullable
    public native GeoBox getBoundingBox();

    @NonNull
    public native MapCameraLimits getLimits();

    @NonNull
    public native Point2D getPrincipalPoint();

    @NonNull
    public native State getState();

    public native void lookAt(@NonNull GeoBox geoBox, @NonNull OrientationUpdate orientationUpdate);

    public native void lookAt(@NonNull GeoBox geoBox, @NonNull OrientationUpdate orientationUpdate, @NonNull Rectangle2D rectangle2D);

    public native void lookAt(@NonNull GeoCoordinates geoCoordinates);

    public native void lookAt(@NonNull GeoCoordinates geoCoordinates, double d);

    public native void lookAt(@NonNull GeoCoordinates geoCoordinates, @NonNull OrientationUpdate orientationUpdate, double d);

    public native void orbitBy(@NonNull OrientationUpdate orientationUpdate, @NonNull Point2D point2D);

    public native void removeObserver(@NonNull MapCameraObserver mapCameraObserver);

    public native void setDistanceToTarget(double d);

    native void setHarpMapview(@NonNull HarpMapView harpMapView);

    public native void setPrincipalPoint(@NonNull Point2D point2D);

    public native void setTargetOrientation(@NonNull OrientationUpdate orientationUpdate);

    native void setViewSize(long j, long j2);

    public native void zoomBy(double d, @NonNull Point2D point2D);

    public native void zoomTo(double d);
}
