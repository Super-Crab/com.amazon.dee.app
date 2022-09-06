package com.amazon.ptz.gestures.listeners;

import android.view.KeyEvent;
import com.amazon.ptz.digital.DigitalMagnitudes;
import com.amazon.ptz.digital.DigitalZoomState;
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
public class PtzKeyEventListener {
    private static final int KEYEVENT_INTERVAL_MILLIS = 400;
    private static final String TAG = LogTag.forClass(PtzKeyEventListener.class);
    private static final float ZOOM_IN_SCALE = DigitalMagnitudes.DIGITAL_ZOOM_IN.getMagnitude();
    private static final float ZOOM_OUT_SCALE = DigitalMagnitudes.DIGITAL_ZOOM_OUT.getMagnitude();
    @Nonnull
    private final GestureHandler gestureHandler;
    private long keyDownTimeMillis;
    @Nonnull
    private final MetricRecorder metricRecorder;
    @Nonnull
    private final DigitalZoomState zoomState;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PtzKeyEventListener(@Nonnull GestureHandler gestureHandler, @Nonnull MetricRecorder metricRecorder, @Nonnull DigitalZoomState digitalZoomState) {
        if (gestureHandler != null) {
            if (metricRecorder == null) {
                throw new IllegalArgumentException("metricRecorder is null");
            }
            if (digitalZoomState == null) {
                throw new IllegalArgumentException("zoomState is null");
            }
            this.gestureHandler = gestureHandler;
            this.metricRecorder = metricRecorder;
            this.zoomState = digitalZoomState;
            return;
        }
        throw new IllegalArgumentException("gestureHandler is null");
    }

    private void handleKeyEvent(float f, float f2, float f3, GestureType gestureType) {
        if (!gestureType.equals(GestureType.PAN_TILT) || sendKeyEvents()) {
            this.gestureHandler.handle(new Gesture(gestureType, new TransformInfo(f, f2, f3, 0.0f, 0.0f)));
        }
    }

    private boolean sendKeyEvents() {
        if (!this.zoomState.isZoomedOut()) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.keyDownTimeMillis <= 400) {
            return false;
        }
        this.keyDownTimeMillis = currentTimeMillis;
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        float magnitude = (this.zoomState.isZoomedOut() ? DigitalMagnitudes.PHYSICAL_PAN_STEP : DigitalMagnitudes.DIGITAL_PAN_STEP).getMagnitude();
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 4) {
            resetCameraView();
            return false;
        } else if (keyCode == 89) {
            handleKeyEvent(0.0f, 0.0f, ZOOM_OUT_SCALE, GestureType.ZOOM_OUT_CENTER);
            return true;
        } else if (keyCode != 90) {
            switch (keyCode) {
                case 19:
                    handleKeyEvent(0.0f, magnitude, 0.0f, GestureType.PAN_TILT);
                    return true;
                case 20:
                    handleKeyEvent(0.0f, -magnitude, 0.0f, GestureType.PAN_TILT);
                    return true;
                case 21:
                    handleKeyEvent(magnitude, 0.0f, 0.0f, GestureType.PAN_TILT);
                    return true;
                case 22:
                    handleKeyEvent(-magnitude, 0.0f, 0.0f, GestureType.PAN_TILT);
                    return true;
                default:
                    return false;
            }
        } else {
            this.metricRecorder.recordDigitalZoomRequest(ZoomInput.BUTTON_PRESS);
            handleKeyEvent(0.0f, 0.0f, ZOOM_IN_SCALE, GestureType.ZOOM_IN_CENTER);
            return true;
        }
    }

    public void resetCameraView() {
        handleKeyEvent(0.0f, 0.0f, 0.0f, GestureType.ZOOM_OUT_CENTER);
    }
}
