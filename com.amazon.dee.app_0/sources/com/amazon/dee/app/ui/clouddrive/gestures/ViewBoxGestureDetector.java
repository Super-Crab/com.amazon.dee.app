package com.amazon.dee.app.ui.clouddrive.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.util.Utils;
/* loaded from: classes12.dex */
public class ViewBoxGestureDetector {
    private static final int INVALID_POINTER_ID = -1;
    private static final String TAG = Utils.safeTag(ViewBoxGestureDetector.class.getSimpleName());
    private int mActivePointerId = -1;
    private int mActivePointerIndex = 0;
    private final ScaleGestureDetector mDetector;
    private boolean mIsDragging;
    private float mLastTouchX;
    private float mLastTouchY;
    private IGestureListener mListener;
    private final float mMinimumVelocity;
    private final float mTouchSlop;
    private VelocityTracker mVelocityTracker;

    public ViewBoxGestureDetector(@NonNull Context context, @NonNull IGestureListener iGestureListener) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mListener = iGestureListener;
        this.mDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() { // from class: com.amazon.dee.app.ui.clouddrive.gestures.ViewBoxGestureDetector.1
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                if (scaleGestureDetector == null) {
                    return false;
                }
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                    return false;
                }
                ViewBoxGestureDetector.this.mListener.onScale(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }
        });
    }

    private float getActiveX(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.mActivePointerIndex);
        } catch (Exception unused) {
            return motionEvent.getX();
        }
    }

    private float getActiveY(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.mActivePointerIndex);
        } catch (Exception unused) {
            return motionEvent.getY();
        }
    }

    public boolean isDragging() {
        return this.mIsDragging;
    }

    public boolean isScaling() {
        return this.mDetector.isInProgress();
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        this.mDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        int i = action & 255;
        if (i == 0) {
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mVelocityTracker = VelocityTracker.obtain();
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            } else {
                Log.w(TAG, "Velocity tracker is null");
            }
            this.mLastTouchX = getActiveX(motionEvent);
            this.mLastTouchY = getActiveY(motionEvent);
            this.mIsDragging = false;
        } else if (i == 1) {
            this.mActivePointerId = -1;
            if (this.mIsDragging && this.mVelocityTracker != null) {
                this.mLastTouchX = getActiveX(motionEvent);
                this.mLastTouchY = getActiveY(motionEvent);
                this.mVelocityTracker.addMovement(motionEvent);
                this.mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = this.mVelocityTracker.getXVelocity();
                float yVelocity = this.mVelocityTracker.getYVelocity();
                if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.mMinimumVelocity) {
                    this.mListener.onFling(this.mLastTouchX, this.mLastTouchY, -xVelocity, -yVelocity);
                }
            }
            VelocityTracker velocityTracker2 = this.mVelocityTracker;
            if (velocityTracker2 != null) {
                velocityTracker2.recycle();
                this.mVelocityTracker = null;
            }
        } else if (i == 2) {
            float activeX = getActiveX(motionEvent);
            float activeY = getActiveY(motionEvent);
            float f = activeX - this.mLastTouchX;
            float f2 = activeY - this.mLastTouchY;
            if (!this.mIsDragging) {
                this.mIsDragging = Math.sqrt((double) ((f2 * f2) + (f * f))) >= ((double) this.mTouchSlop);
            }
            if (this.mIsDragging) {
                this.mListener.onDrag(f, f2);
                this.mLastTouchX = activeX;
                this.mLastTouchY = activeY;
                VelocityTracker velocityTracker3 = this.mVelocityTracker;
                if (velocityTracker3 != null) {
                    velocityTracker3.addMovement(motionEvent);
                }
            }
        } else if (i == 3) {
            VelocityTracker velocityTracker4 = this.mVelocityTracker;
            if (velocityTracker4 != null) {
                velocityTracker4.recycle();
                this.mVelocityTracker = null;
            }
        } else if (i != 6) {
            return false;
        } else {
            int i2 = (action & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            if (motionEvent.getPointerId(i2) == this.mActivePointerId) {
                int i3 = i2 == 0 ? 1 : 0;
                this.mActivePointerId = motionEvent.getPointerId(i3);
                this.mLastTouchX = motionEvent.getX(i3);
                this.mLastTouchY = motionEvent.getY(i3);
            }
        }
        int i4 = this.mActivePointerId;
        if (i4 == -1) {
            i4 = 0;
        }
        this.mActivePointerIndex = motionEvent.findPointerIndex(i4);
        return true;
    }
}
