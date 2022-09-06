package com.amazon.ptz.gestures.listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.MotionEventCompat;
import com.amazon.ptz.gestures.Gesture;
import com.amazon.ptz.gestures.GestureType;
import com.amazon.ptz.gestures.TransformInfo;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.util.LogTag;
import javax.annotation.Nonnull;
/* loaded from: classes13.dex */
public class PtzListener implements View.OnTouchListener {
    private static final int INVALID_POINTER_ID = -1;
    private static final String TAG = LogTag.forClass(PtzListener.class);
    private int activePointerId = -1;
    @Nonnull
    private final GestureDetector gestureDetector;
    @Nonnull
    private final GestureHandler gestureHandler;
    private final int panTiltSlop;
    private float previousX;
    private float previousY;
    @Nonnull
    private final OldScaleGestureDetector scaleGestureDetector;

    public PtzListener(GestureHandler gestureHandler, GestureDetector gestureDetector, OldScaleGestureDetector oldScaleGestureDetector, Context context) {
        this.scaleGestureDetector = oldScaleGestureDetector;
        this.gestureDetector = gestureDetector;
        this.gestureHandler = gestureHandler;
        this.panTiltSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.gestureDetector.onTouchEvent(motionEvent)) {
            return true;
        }
        this.scaleGestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        int actionMasked = motionEvent.getActionMasked() & action;
        int i = 0;
        if (actionMasked == 0) {
            this.previousX = motionEvent.getX();
            this.previousY = motionEvent.getY();
            this.activePointerId = motionEvent.getPointerId(0);
        } else if (actionMasked == 1) {
            if (motionEvent.findPointerIndex(this.activePointerId) != -1) {
                float x = motionEvent.getX() - this.previousX;
                float y = motionEvent.getY() - this.previousY;
                if (Math.hypot(x, y) > this.panTiltSlop) {
                    this.gestureHandler.handle(new Gesture(GestureType.PAN_TILT, new TransformInfo(x, y, 0.0f, 0.0f, 0.0f)));
                }
            }
            this.activePointerId = -1;
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.activePointerId);
            if (findPointerIndex != -1) {
                float x2 = motionEvent.getX(findPointerIndex);
                float y2 = motionEvent.getY(findPointerIndex);
                if (!this.scaleGestureDetector.isInProgress()) {
                    this.gestureHandler.handle(new Gesture(GestureType.PAN_TILT_DRAG, new TransformInfo(x2 - this.previousX, y2 - this.previousY, 0.0f, 0.0f, 0.0f)));
                }
            }
        } else if (actionMasked == 3) {
            this.activePointerId = -1;
        } else if (actionMasked == 6) {
            int i2 = (action & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            if (motionEvent.getPointerId(i2) == this.activePointerId) {
                if (i2 == 0) {
                    i = 1;
                }
                this.previousX = motionEvent.getX(i);
                this.previousY = motionEvent.getY(i);
                this.activePointerId = motionEvent.getPointerId(i);
            }
        }
        return true;
    }
}
