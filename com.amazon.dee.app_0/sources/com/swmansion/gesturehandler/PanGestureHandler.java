package com.swmansion.gesturehandler;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
/* loaded from: classes3.dex */
public class PanGestureHandler extends GestureHandler<PanGestureHandler> {
    private static int DEFAULT_MAX_POINTERS = 10;
    private static int DEFAULT_MIN_POINTERS = 1;
    private static float MAX_VALUE_IGNORE = Float.MIN_VALUE;
    private static float MIN_VALUE_IGNORE = Float.MAX_VALUE;
    private float mActiveOffsetXEnd;
    private float mActiveOffsetXStart;
    private float mActiveOffsetYEnd;
    private float mActiveOffsetYStart;
    private boolean mAverageTouches;
    private float mFailOffsetXEnd;
    private float mFailOffsetXStart;
    private float mFailOffsetYEnd;
    private float mFailOffsetYStart;
    private float mLastVelocityX;
    private float mLastVelocityY;
    private float mLastX;
    private float mLastY;
    private int mMaxPointers;
    private float mMinDistSq;
    private int mMinPointers;
    private float mMinVelocitySq;
    private float mMinVelocityX;
    private float mMinVelocityY;
    private float mOffsetX;
    private float mOffsetY;
    private float mStartX;
    private float mStartY;
    private VelocityTracker mVelocityTracker;

    public PanGestureHandler(Context context) {
        float f = MAX_VALUE_IGNORE;
        this.mMinDistSq = f;
        float f2 = MIN_VALUE_IGNORE;
        this.mActiveOffsetXStart = f2;
        this.mActiveOffsetXEnd = f;
        this.mFailOffsetXStart = f;
        this.mFailOffsetXEnd = f2;
        this.mActiveOffsetYStart = f2;
        this.mActiveOffsetYEnd = f;
        this.mFailOffsetYStart = f;
        this.mFailOffsetYEnd = f2;
        this.mMinVelocityX = f2;
        this.mMinVelocityY = f2;
        this.mMinVelocitySq = f2;
        this.mMinPointers = DEFAULT_MIN_POINTERS;
        this.mMaxPointers = DEFAULT_MAX_POINTERS;
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMinDistSq = scaledTouchSlop * scaledTouchSlop;
    }

    private static void addVelocityMovement(VelocityTracker velocityTracker, MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        velocityTracker.addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0070, code lost:
        if (r0 >= r1) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x008d, code lost:
        if (r0 >= r2) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean shouldActivate() {
        /*
            r6 = this;
            float r0 = r6.mLastX
            float r1 = r6.mStartX
            float r0 = r0 - r1
            float r1 = r6.mOffsetX
            float r0 = r0 + r1
            float r1 = r6.mActiveOffsetXStart
            float r2 = com.swmansion.gesturehandler.PanGestureHandler.MIN_VALUE_IGNORE
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 1
            if (r2 == 0) goto L16
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 >= 0) goto L16
            return r3
        L16:
            float r1 = r6.mActiveOffsetXEnd
            float r2 = com.swmansion.gesturehandler.PanGestureHandler.MAX_VALUE_IGNORE
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 == 0) goto L23
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 <= 0) goto L23
            return r3
        L23:
            float r1 = r6.mLastY
            float r2 = r6.mStartY
            float r1 = r1 - r2
            float r2 = r6.mOffsetY
            float r1 = r1 + r2
            float r2 = r6.mActiveOffsetYStart
            float r4 = com.swmansion.gesturehandler.PanGestureHandler.MIN_VALUE_IGNORE
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L38
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 >= 0) goto L38
            return r3
        L38:
            float r2 = r6.mActiveOffsetYEnd
            float r4 = com.swmansion.gesturehandler.PanGestureHandler.MAX_VALUE_IGNORE
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L45
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 <= 0) goto L45
            return r3
        L45:
            float r0 = r0 * r0
            float r1 = r1 * r1
            float r1 = r1 + r0
            float r0 = r6.mMinDistSq
            float r2 = com.swmansion.gesturehandler.PanGestureHandler.MIN_VALUE_IGNORE
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L55
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 < 0) goto L55
            return r3
        L55:
            float r0 = r6.mLastVelocityX
            float r1 = r6.mMinVelocityX
            float r2 = com.swmansion.gesturehandler.PanGestureHandler.MIN_VALUE_IGNORE
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r4 = 0
            if (r2 == 0) goto L73
            int r2 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r2 >= 0) goto L68
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 <= 0) goto L72
        L68:
            float r1 = r6.mMinVelocityX
            int r2 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r2 < 0) goto L73
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 < 0) goto L73
        L72:
            return r3
        L73:
            float r1 = r6.mLastVelocityY
            float r2 = r6.mMinVelocityY
            float r5 = com.swmansion.gesturehandler.PanGestureHandler.MIN_VALUE_IGNORE
            int r5 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r5 == 0) goto L90
            int r5 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r5 >= 0) goto L85
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L8f
        L85:
            float r2 = r6.mMinVelocityY
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 < 0) goto L90
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L90
        L8f:
            return r3
        L90:
            float r0 = r0 * r0
            float r1 = r1 * r1
            float r1 = r1 + r0
            float r0 = r6.mMinVelocitySq
            float r2 = com.swmansion.gesturehandler.PanGestureHandler.MIN_VALUE_IGNORE
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto La0
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 < 0) goto La0
            return r3
        La0:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.PanGestureHandler.shouldActivate():boolean");
    }

    private boolean shouldFail() {
        float f = (this.mLastX - this.mStartX) + this.mOffsetX;
        float f2 = this.mFailOffsetXStart;
        if (f2 == MAX_VALUE_IGNORE || f >= f2) {
            float f3 = this.mFailOffsetXEnd;
            if (f3 != MIN_VALUE_IGNORE && f > f3) {
                return true;
            }
            float f4 = (this.mLastY - this.mStartY) + this.mOffsetY;
            float f5 = this.mFailOffsetYStart;
            if (f5 != MAX_VALUE_IGNORE && f4 < f5) {
                return true;
            }
            float f6 = this.mFailOffsetYEnd;
            return f6 != MIN_VALUE_IGNORE && f4 > f6;
        }
        return true;
    }

    public float getTranslationX() {
        return (this.mLastX - this.mStartX) + this.mOffsetX;
    }

    public float getTranslationY() {
        return (this.mLastY - this.mStartY) + this.mOffsetY;
    }

    public float getVelocityX() {
        return this.mLastVelocityX;
    }

    public float getVelocityY() {
        return this.mLastVelocityY;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onHandle(MotionEvent motionEvent) {
        int state = getState();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 6 && actionMasked != 5) {
            this.mLastX = GestureUtils.getLastPointerX(motionEvent, this.mAverageTouches);
            this.mLastY = GestureUtils.getLastPointerY(motionEvent, this.mAverageTouches);
        } else {
            this.mOffsetX = (this.mLastX - this.mStartX) + this.mOffsetX;
            this.mOffsetY = (this.mLastY - this.mStartY) + this.mOffsetY;
            this.mLastX = GestureUtils.getLastPointerX(motionEvent, this.mAverageTouches);
            this.mLastY = GestureUtils.getLastPointerY(motionEvent, this.mAverageTouches);
            this.mStartX = this.mLastX;
            this.mStartY = this.mLastY;
        }
        if (state == 0 && motionEvent.getPointerCount() >= this.mMinPointers) {
            this.mStartX = this.mLastX;
            this.mStartY = this.mLastY;
            this.mOffsetX = 0.0f;
            this.mOffsetY = 0.0f;
            this.mVelocityTracker = VelocityTracker.obtain();
            addVelocityMovement(this.mVelocityTracker, motionEvent);
            begin();
        } else {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                addVelocityMovement(velocityTracker, motionEvent);
                this.mVelocityTracker.computeCurrentVelocity(1000);
                this.mLastVelocityX = this.mVelocityTracker.getXVelocity();
                this.mLastVelocityY = this.mVelocityTracker.getYVelocity();
            }
        }
        if (actionMasked == 1) {
            if (state != 4 && state != 2) {
                fail();
            } else {
                end();
            }
        } else if (actionMasked == 5 && motionEvent.getPointerCount() > this.mMaxPointers) {
            if (state == 4) {
                cancel();
            } else {
                fail();
            }
        } else if (actionMasked == 6 && state == 4 && motionEvent.getPointerCount() < this.mMinPointers) {
            fail();
        } else if (state != 2) {
        } else {
            if (shouldFail()) {
                fail();
            } else if (!shouldActivate()) {
            } else {
                this.mStartX = this.mLastX;
                this.mStartY = this.mLastY;
                activate();
            }
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onReset() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public PanGestureHandler setActiveOffsetXEnd(float f) {
        this.mActiveOffsetXEnd = f;
        return this;
    }

    public PanGestureHandler setActiveOffsetXStart(float f) {
        this.mActiveOffsetXStart = f;
        return this;
    }

    public PanGestureHandler setActiveOffsetYEnd(float f) {
        this.mActiveOffsetYEnd = f;
        return this;
    }

    public PanGestureHandler setActiveOffsetYStart(float f) {
        this.mActiveOffsetYStart = f;
        return this;
    }

    public PanGestureHandler setAverageTouches(boolean z) {
        this.mAverageTouches = z;
        return this;
    }

    public PanGestureHandler setFailOffsetXEnd(float f) {
        this.mFailOffsetXEnd = f;
        return this;
    }

    public PanGestureHandler setFailOffsetXStart(float f) {
        this.mFailOffsetXStart = f;
        return this;
    }

    public PanGestureHandler setFailOffsetYEnd(float f) {
        this.mFailOffsetYEnd = f;
        return this;
    }

    public PanGestureHandler setFailOffsetYStart(float f) {
        this.mFailOffsetYStart = f;
        return this;
    }

    public PanGestureHandler setMaxPointers(int i) {
        this.mMaxPointers = i;
        return this;
    }

    public PanGestureHandler setMinDist(float f) {
        this.mMinDistSq = f * f;
        return this;
    }

    public PanGestureHandler setMinPointers(int i) {
        this.mMinPointers = i;
        return this;
    }

    public PanGestureHandler setMinVelocity(float f) {
        this.mMinVelocitySq = f * f;
        return this;
    }

    public PanGestureHandler setMinVelocityX(float f) {
        this.mMinVelocityX = f;
        return this;
    }

    public PanGestureHandler setMinVelocityY(float f) {
        this.mMinVelocityY = f;
        return this;
    }
}
