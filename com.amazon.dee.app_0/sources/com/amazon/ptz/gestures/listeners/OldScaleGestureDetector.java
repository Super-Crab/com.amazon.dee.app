package com.amazon.ptz.gestures.listeners;

import android.content.Context;
import android.view.MotionEvent;
import com.amazon.ptz.util.LogTag;
/* loaded from: classes13.dex */
public class OldScaleGestureDetector {
    private static final String TAG = LogTag.forClass(OldScaleGestureDetector.class);
    private final Context mContext;
    private float mCurrSpan;
    private float mCurrSpanX;
    private float mCurrSpanY;
    private long mCurrTime;
    private float mFocusX;
    private float mFocusY;
    private boolean mInProgress;
    private final OnScaleGestureListener mListener;
    private float mPrevSpan;
    private float mPrevSpanX;
    private float mPrevSpanY;
    private long mPrevTime;

    /* loaded from: classes13.dex */
    public interface OnScaleGestureListener {
        boolean onScale(OldScaleGestureDetector oldScaleGestureDetector);

        boolean onScaleBegin(OldScaleGestureDetector oldScaleGestureDetector);

        void onScaleEnd(OldScaleGestureDetector oldScaleGestureDetector);
    }

    /* loaded from: classes13.dex */
    public static class SimpleOnScaleGestureListener implements OnScaleGestureListener {
        @Override // com.amazon.ptz.gestures.listeners.OldScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(OldScaleGestureDetector oldScaleGestureDetector) {
            return false;
        }

        @Override // com.amazon.ptz.gestures.listeners.OldScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(OldScaleGestureDetector oldScaleGestureDetector) {
            return true;
        }

        @Override // com.amazon.ptz.gestures.listeners.OldScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(OldScaleGestureDetector oldScaleGestureDetector) {
        }
    }

    public OldScaleGestureDetector(Context context, OnScaleGestureListener onScaleGestureListener) {
        this.mContext = context;
        this.mListener = onScaleGestureListener;
    }

    public float getCurrentSpan() {
        return this.mCurrSpan;
    }

    public float getCurrentSpanX() {
        return this.mCurrSpanX;
    }

    public float getCurrentSpanY() {
        return this.mCurrSpanY;
    }

    public long getEventTime() {
        return this.mCurrTime;
    }

    public float getFocusX() {
        return this.mFocusX;
    }

    public float getFocusY() {
        return this.mFocusY;
    }

    public float getPreviousSpan() {
        return this.mPrevSpan;
    }

    public float getPreviousSpanX() {
        return this.mPrevSpanX;
    }

    public float getPreviousSpanY() {
        return this.mPrevSpanY;
    }

    public float getScaleFactor() {
        float f = this.mPrevSpan;
        if (f > 0.0f) {
            return this.mCurrSpan / f;
        }
        return 1.0f;
    }

    public long getTimeDelta() {
        return this.mCurrTime - this.mPrevTime;
    }

    public boolean isInProgress() {
        return this.mInProgress;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        boolean z = actionMasked == 1 || actionMasked == 3;
        if (actionMasked == 0 || z) {
            if (this.mInProgress) {
                this.mListener.onScaleEnd(this);
                this.mInProgress = false;
            }
            if (z) {
                return true;
            }
        }
        boolean z2 = actionMasked == 6 || actionMasked == 5;
        boolean z3 = actionMasked == 6;
        int actionIndex = z3 ? motionEvent.getActionIndex() : -1;
        int pointerCount = motionEvent.getPointerCount();
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            if (actionIndex != i) {
                float x = motionEvent.getX(i) + f;
                f2 = motionEvent.getY(i) + f2;
                f = x;
            }
        }
        float f3 = z3 ? pointerCount - 1 : pointerCount;
        float f4 = f / f3;
        float f5 = f2 / f3;
        float f6 = 0.0f;
        float f7 = 0.0f;
        for (int i2 = 0; i2 < pointerCount; i2++) {
            if (actionIndex != i2) {
                float abs = Math.abs(motionEvent.getX(i2) - f4) + f6;
                f7 = Math.abs(motionEvent.getY(i2) - f5) + f7;
                f6 = abs;
            }
        }
        float f8 = (f6 / f3) * 2.0f;
        float f9 = (f7 / f3) * 2.0f;
        float hypot = (float) Math.hypot(f8, f9);
        if (this.mInProgress && (hypot == 0.0f || z2)) {
            this.mListener.onScaleEnd(this);
            this.mInProgress = false;
        }
        if (z2) {
            this.mCurrSpanX = f8;
            this.mPrevSpanX = f8;
            this.mCurrSpanY = f9;
            this.mPrevSpanY = f9;
            this.mCurrSpan = hypot;
            this.mPrevSpan = hypot;
        }
        if (!this.mInProgress && hypot != 0.0f) {
            this.mFocusX = f4;
            this.mFocusY = f5;
            this.mInProgress = this.mListener.onScaleBegin(this);
        }
        if (actionMasked == 2) {
            this.mCurrSpanX = f8;
            this.mCurrSpanY = f9;
            this.mCurrSpan = hypot;
            this.mFocusX = f4;
            this.mFocusY = f5;
            if (!(this.mInProgress ? this.mListener.onScale(this) : true)) {
                return true;
            }
            this.mPrevSpanX = this.mCurrSpanX;
            this.mPrevSpanY = this.mCurrSpanY;
            this.mPrevSpan = this.mCurrSpan;
            return true;
        }
        return true;
    }
}
