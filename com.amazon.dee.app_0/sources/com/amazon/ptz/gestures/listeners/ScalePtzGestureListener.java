package com.amazon.ptz.gestures.listeners;

import com.amazon.ptz.gestures.Gesture;
import com.amazon.ptz.gestures.GestureType;
import com.amazon.ptz.gestures.TransformInfo;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.OldScaleGestureDetector;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.util.LogTag;
import com.amazon.ptz.util.ZoomInput;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes13.dex */
public class ScalePtzGestureListener extends OldScaleGestureDetector.SimpleOnScaleGestureListener {
    private static final String TAG = LogTag.forClass(ScalePtzGestureListener.class);
    @Nonnull
    private final GestureHandler gestureHandler;
    @Nonnull
    private final MetricRecorder metricRecorder;
    private float pivotX;
    private float pivotY;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ScalePtzGestureListener(@Nonnull GestureHandler gestureHandler, @Nonnull MetricRecorder metricRecorder) {
        if (gestureHandler != null) {
            if (metricRecorder == null) {
                throw new IllegalArgumentException("metricRecorder is null");
            }
            this.gestureHandler = gestureHandler;
            this.metricRecorder = metricRecorder;
            return;
        }
        throw new IllegalArgumentException("gestureHandler is null");
    }

    @Override // com.amazon.ptz.gestures.listeners.OldScaleGestureDetector.SimpleOnScaleGestureListener, com.amazon.ptz.gestures.listeners.OldScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(OldScaleGestureDetector oldScaleGestureDetector) {
        float focusX = oldScaleGestureDetector.getFocusX() - this.pivotX;
        float focusY = oldScaleGestureDetector.getFocusY() - this.pivotY;
        float scaleFactor = oldScaleGestureDetector.getScaleFactor();
        this.metricRecorder.recordDigitalZoomRequest(ZoomInput.PINCH);
        this.gestureHandler.handle(new Gesture(GestureType.ZOOM, new TransformInfo(focusX, focusY, scaleFactor, this.pivotX, this.pivotY)));
        return false;
    }

    @Override // com.amazon.ptz.gestures.listeners.OldScaleGestureDetector.SimpleOnScaleGestureListener, com.amazon.ptz.gestures.listeners.OldScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(OldScaleGestureDetector oldScaleGestureDetector) {
        this.pivotX = oldScaleGestureDetector.getFocusX();
        this.pivotY = oldScaleGestureDetector.getFocusY();
        return true;
    }
}
