package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.swmansion.gesturehandler.RotationGestureDetector;
/* loaded from: classes3.dex */
public class RotationGestureHandler extends GestureHandler<RotationGestureHandler> {
    private static final double ROTATION_RECOGNITION_THRESHOLD = 0.08726646259971647d;
    private RotationGestureDetector.OnRotationGestureListener mGestureListener = new RotationGestureDetector.OnRotationGestureListener() { // from class: com.swmansion.gesturehandler.RotationGestureHandler.1
        @Override // com.swmansion.gesturehandler.RotationGestureDetector.OnRotationGestureListener
        public boolean onRotation(RotationGestureDetector rotationGestureDetector) {
            double d = RotationGestureHandler.this.mLastRotation;
            RotationGestureHandler rotationGestureHandler = RotationGestureHandler.this;
            rotationGestureHandler.mLastRotation = rotationGestureDetector.getRotation() + rotationGestureHandler.mLastRotation;
            long timeDelta = rotationGestureDetector.getTimeDelta();
            if (timeDelta > 0) {
                RotationGestureHandler rotationGestureHandler2 = RotationGestureHandler.this;
                rotationGestureHandler2.mLastVelocity = (rotationGestureHandler2.mLastRotation - d) / timeDelta;
            }
            if (Math.abs(RotationGestureHandler.this.mLastRotation) < RotationGestureHandler.ROTATION_RECOGNITION_THRESHOLD || RotationGestureHandler.this.getState() != 2) {
                return true;
            }
            RotationGestureHandler.this.activate();
            return true;
        }

        @Override // com.swmansion.gesturehandler.RotationGestureDetector.OnRotationGestureListener
        public boolean onRotationBegin(RotationGestureDetector rotationGestureDetector) {
            return true;
        }

        @Override // com.swmansion.gesturehandler.RotationGestureDetector.OnRotationGestureListener
        public void onRotationEnd(RotationGestureDetector rotationGestureDetector) {
            RotationGestureHandler.this.end();
        }
    };
    private double mLastRotation;
    private double mLastVelocity;
    private RotationGestureDetector mRotationGestureDetector;

    public RotationGestureHandler() {
        setShouldCancelWhenOutside(false);
    }

    public float getAnchorX() {
        RotationGestureDetector rotationGestureDetector = this.mRotationGestureDetector;
        if (rotationGestureDetector == null) {
            return Float.NaN;
        }
        return rotationGestureDetector.getAnchorX();
    }

    public float getAnchorY() {
        RotationGestureDetector rotationGestureDetector = this.mRotationGestureDetector;
        if (rotationGestureDetector == null) {
            return Float.NaN;
        }
        return rotationGestureDetector.getAnchorY();
    }

    public double getRotation() {
        return this.mLastRotation;
    }

    public double getVelocity() {
        return this.mLastVelocity;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onHandle(MotionEvent motionEvent) {
        int state = getState();
        if (state == 0) {
            this.mLastVelocity = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            this.mLastRotation = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            this.mRotationGestureDetector = new RotationGestureDetector(this.mGestureListener);
            begin();
        }
        RotationGestureDetector rotationGestureDetector = this.mRotationGestureDetector;
        if (rotationGestureDetector != null) {
            rotationGestureDetector.onTouchEvent(motionEvent);
        }
        if (motionEvent.getActionMasked() == 1) {
            if (state == 4) {
                end();
            } else {
                fail();
            }
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onReset() {
        this.mRotationGestureDetector = null;
        this.mLastVelocity = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mLastRotation = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }
}
