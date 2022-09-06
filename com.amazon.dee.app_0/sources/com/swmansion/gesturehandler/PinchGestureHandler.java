package com.swmansion.gesturehandler;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes3.dex */
public class PinchGestureHandler extends GestureHandler<PinchGestureHandler> {
    private ScaleGestureDetector.OnScaleGestureListener mGestureListener = new ScaleGestureDetector.OnScaleGestureListener() { // from class: com.swmansion.gesturehandler.PinchGestureHandler.1
        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            double d = PinchGestureHandler.this.mLastScaleFactor;
            PinchGestureHandler.this.mLastScaleFactor *= scaleGestureDetector.getScaleFactor();
            long timeDelta = scaleGestureDetector.getTimeDelta();
            if (timeDelta > 0) {
                PinchGestureHandler pinchGestureHandler = PinchGestureHandler.this;
                pinchGestureHandler.mLastVelocity = (pinchGestureHandler.mLastScaleFactor - d) / timeDelta;
            }
            if (Math.abs(PinchGestureHandler.this.mStartingSpan - scaleGestureDetector.getCurrentSpan()) < PinchGestureHandler.this.mSpanSlop || PinchGestureHandler.this.getState() != 2) {
                return true;
            }
            PinchGestureHandler.this.activate();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            PinchGestureHandler.this.mStartingSpan = scaleGestureDetector.getCurrentSpan();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }
    };
    private double mLastScaleFactor;
    private double mLastVelocity;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mSpanSlop;
    private float mStartingSpan;

    public PinchGestureHandler() {
        setShouldCancelWhenOutside(false);
    }

    public float getFocalPointX() {
        ScaleGestureDetector scaleGestureDetector = this.mScaleGestureDetector;
        if (scaleGestureDetector == null) {
            return Float.NaN;
        }
        return scaleGestureDetector.getFocusX();
    }

    public float getFocalPointY() {
        ScaleGestureDetector scaleGestureDetector = this.mScaleGestureDetector;
        if (scaleGestureDetector == null) {
            return Float.NaN;
        }
        return scaleGestureDetector.getFocusY();
    }

    public double getScale() {
        return this.mLastScaleFactor;
    }

    public double getVelocity() {
        return this.mLastVelocity;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onHandle(MotionEvent motionEvent) {
        if (getState() == 0) {
            Context context = getView().getContext();
            this.mLastVelocity = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            this.mLastScaleFactor = 1.0d;
            this.mScaleGestureDetector = new ScaleGestureDetector(context, this.mGestureListener);
            this.mSpanSlop = ViewConfiguration.get(context).getScaledTouchSlop();
            begin();
        }
        ScaleGestureDetector scaleGestureDetector = this.mScaleGestureDetector;
        if (scaleGestureDetector != null) {
            scaleGestureDetector.onTouchEvent(motionEvent);
        }
        int pointerCount = motionEvent.getPointerCount();
        if (motionEvent.getActionMasked() == 6) {
            pointerCount--;
        }
        if (getState() == 4 && pointerCount < 2) {
            end();
        } else if (motionEvent.getActionMasked() != 1) {
        } else {
            fail();
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onReset() {
        this.mScaleGestureDetector = null;
        this.mLastVelocity = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mLastScaleFactor = 1.0d;
    }
}
