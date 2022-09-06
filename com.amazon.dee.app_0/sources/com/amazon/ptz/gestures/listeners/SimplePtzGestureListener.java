package com.amazon.ptz.gestures.listeners;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.amazon.ptz.gestures.Gesture;
import com.amazon.ptz.gestures.GestureType;
import com.amazon.ptz.gestures.TransformInfo;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.util.LogTag;
import com.amazon.ptz.util.ZoomInput;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes13.dex */
public class SimplePtzGestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final String TAG = LogTag.forClass(SimplePtzGestureListener.class);
    @Nonnull
    private final GestureHandler gestureHandler;
    @Nonnull
    private final MetricRecorder metricRecorder;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public SimplePtzGestureListener(@Nonnull GestureHandler gestureHandler, @Nonnull MetricRecorder metricRecorder) {
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

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        String str = "onDoubleTap, MotionEvent " + motionEvent;
        Gesture gesture = new Gesture(GestureType.ZOOM_TOGGLE, new TransformInfo(0.0f, 0.0f, 0.0f, motionEvent.getX(), motionEvent.getY()));
        this.metricRecorder.recordDigitalZoomRequest(ZoomInput.DOUBLE_TAP);
        this.gestureHandler.handle(gesture);
        return true;
    }
}
