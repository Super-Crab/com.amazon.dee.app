package com.amazon.ptz.digital;

import android.util.Log;
import android.view.View;
import com.amazon.ptz.gestures.Gesture;
import com.amazon.ptz.gestures.GestureType;
import com.amazon.ptz.gestures.TransformInfo;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.util.LogTag;
import com.google.common.annotations.VisibleForTesting;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes13.dex */
public class DigitalPtzGestureHandler implements GestureHandler {
    @Nonnull
    private final MetricRecorder metricRecorder;
    @Nonnull
    private final View view;
    @Nonnull
    private final DigitalZoomState zoomState;
    private static final String TAG = LogTag.forClass(DigitalPtzGestureHandler.class);
    private static final float MINIMUM_SCALE = DigitalMagnitudes.FULL_ZOOM_OUT.getMagnitude();
    private static final float MAXIMUM_SCALE = DigitalMagnitudes.DIGITAL_MAX_ZOOM.getMagnitude();
    private static final float TOGGLE_SCALE = DigitalMagnitudes.TOGGLE_MAX_ZOOM.getMagnitude();
    private static final List<GestureType> DIGITAL_GESTURES = Collections.unmodifiableList(Arrays.asList(GestureType.PAN_TILT, GestureType.PAN_TILT_DRAG, GestureType.ZOOM, GestureType.ZOOM_TOGGLE, GestureType.ZOOM_IN_CENTER, GestureType.ZOOM_OUT_CENTER));

    /* renamed from: com.amazon.ptz.digital.DigitalPtzGestureHandler$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ptz$gestures$GestureType = new int[GestureType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$ptz$gestures$GestureType[GestureType.ZOOM_TOGGLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ptz$gestures$GestureType[GestureType.ZOOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ptz$gestures$GestureType[GestureType.PAN_TILT_DRAG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ptz$gestures$GestureType[GestureType.PAN_TILT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ptz$gestures$GestureType[GestureType.ZOOM_IN_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ptz$gestures$GestureType[GestureType.ZOOM_OUT_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public DigitalPtzGestureHandler(@Nonnull View view, @Nonnull DigitalZoomState digitalZoomState, @Nonnull MetricRecorder metricRecorder) {
        if (view != null) {
            if (digitalZoomState == null) {
                throw new IllegalArgumentException("zoomState is null");
            }
            if (metricRecorder == null) {
                throw new IllegalArgumentException("metricRecorder is null");
            }
            this.view = view;
            this.zoomState = digitalZoomState;
            this.metricRecorder = metricRecorder;
            return;
        }
        throw new IllegalArgumentException("view is null");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0072  */
    @com.google.common.annotations.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean adjustTranslation(float r6, float r7) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Adjusting translation to deltaX: "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = " and deltaY: "
            r0.append(r1)
            r0.append(r7)
            r0.toString()
            r0 = 2
            float[] r0 = new float[r0]
            r1 = 0
            r0[r1] = r6
            r6 = 1
            r0[r6] = r7
            android.view.View r7 = r5.view
            android.graphics.Matrix r7 = r7.getMatrix()
            r7.mapVectors(r0)
            android.view.View r7 = r5.view
            float r7 = r7.getScaleY()
            r2 = 1065353216(0x3f800000, float:1.0)
            float r7 = r7 - r2
            android.view.View r3 = r5.view
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r7 = r7 * r3
            r3 = 1073741824(0x40000000, float:2.0)
            float r7 = r7 / r3
            android.view.View r4 = r5.view
            float r4 = r4.getScaleX()
            float r4 = r4 - r2
            android.view.View r2 = r5.view
            int r2 = r2.getWidth()
            float r2 = (float) r2
            float r4 = r4 * r2
            float r4 = r4 / r3
            android.view.View r2 = r5.view
            float r2 = r2.getTranslationX()
            r3 = r0[r1]
            float r2 = r2 + r3
            int r3 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r3 <= 0) goto L5d
        L5b:
            r1 = r6
            goto L64
        L5d:
            float r4 = -r4
            int r3 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r3 >= 0) goto L63
            goto L5b
        L63:
            r4 = r2
        L64:
            android.view.View r2 = r5.view
            float r2 = r2.getTranslationY()
            r0 = r0[r6]
            float r0 = r0 + r2
            int r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r2 <= 0) goto L72
            goto L7a
        L72:
            float r7 = -r7
            int r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r2 >= 0) goto L78
            goto L7a
        L78:
            r7 = r0
            r6 = r1
        L7a:
            android.view.View r0 = r5.view
            r0.setTranslationX(r4)
            android.view.View r0 = r5.view
            r0.setTranslationY(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ptz.digital.DigitalPtzGestureHandler.adjustTranslation(float, float):boolean");
    }

    @Override // com.amazon.ptz.gestures.handlers.GestureHandler
    public boolean canHandle(Gesture gesture) {
        return DIGITAL_GESTURES.contains(gesture.getGestureType()) && (!this.zoomState.isZoomedOut() || gesture.isZoom());
    }

    @Override // com.amazon.ptz.gestures.handlers.GestureHandler
    public void handle(Gesture gesture) {
        GestureType gestureType = gesture.getGestureType();
        TransformInfo transformInfo = gesture.getTransformInfo();
        if (!canHandle(gesture)) {
            Log.e(TAG, "Attempted to route an unsupported gesture to the digital route. This gesture type is not supported: " + gestureType);
            return;
        }
        String str = "Handling a gesture of type: " + gestureType;
        int ordinal = gestureType.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            handlePan(transformInfo);
        } else if (ordinal == 2) {
            handleZoom(transformInfo);
        } else if (ordinal == 3) {
            handleZoomToggle(transformInfo);
        } else if (ordinal == 4) {
            handleZoomInCenter(transformInfo);
        } else if (ordinal != 5) {
        } else {
            handleZoomOutCenter(transformInfo);
        }
    }

    @VisibleForTesting
    void handlePan(TransformInfo transformInfo) {
        String str = "Panning with transform info: " + transformInfo;
        if (adjustTranslation(transformInfo.getDeltaX(), transformInfo.getDeltaY())) {
            this.metricRecorder.recordDigitalPanToBorder();
        }
    }

    @VisibleForTesting
    void handleZoom(TransformInfo transformInfo) {
        String str = "Zooming with transform info: " + transformInfo;
        adjustTranslation(transformInfo.getDeltaX(), transformInfo.getDeltaY());
        setScale(transformInfo.getDeltaScale() * this.view.getScaleX());
    }

    @VisibleForTesting
    void handleZoomInCenter(TransformInfo transformInfo) {
        String str = "Centre zoom with transform info: " + transformInfo;
        setScale(transformInfo.getDeltaScale() * this.view.getScaleX());
    }

    @VisibleForTesting
    void handleZoomOutCenter(TransformInfo transformInfo) {
        String str = "Zoom out the centre with transform info: " + transformInfo;
        setScale(transformInfo.getDeltaScale() * this.view.getScaleX());
        this.view.setTranslationX(0.0f);
        this.view.setTranslationY(0.0f);
    }

    @VisibleForTesting
    void handleZoomToggle(TransformInfo transformInfo) {
        r0 = "Toggling zoom with transform info: " + transformInfo;
        float scaleX = this.view.getScaleX();
        float f = TOGGLE_SCALE;
        if (scaleX < f) {
            setScale(f);
            adjustTranslation((this.view.getWidth() / 2.0f) - transformInfo.getPivotX(), (this.view.getHeight() / 2.0f) - transformInfo.getPivotY());
            return;
        }
        setScale(MINIMUM_SCALE);
        this.view.setTranslationX(0.0f);
        this.view.setTranslationY(0.0f);
    }

    @VisibleForTesting
    void setScale(float f) {
        float max = Math.max(MINIMUM_SCALE, Math.min(MAXIMUM_SCALE, f));
        String str = "Setting zoom scale to " + max;
        this.view.setScaleX(max);
        this.view.setScaleY(max);
    }
}
