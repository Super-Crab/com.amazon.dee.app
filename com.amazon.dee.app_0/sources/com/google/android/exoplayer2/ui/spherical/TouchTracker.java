package com.google.android.exoplayer2.ui.spherical;

import android.content.Context;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.ui.spherical.OrientationListener;
/* loaded from: classes2.dex */
class TouchTracker extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener, OrientationListener.Listener {
    static final float MAX_PITCH_DEGREES = 45.0f;
    private final GestureDetector gestureDetector;
    private final Listener listener;
    private final float pxPerDegrees;
    @Nullable
    private SingleTapListener singleTapListener;
    private final PointF previousTouchPointPx = new PointF();
    private final PointF accumulatedTouchOffsetDegrees = new PointF();
    private volatile float roll = 3.1415927f;

    /* loaded from: classes2.dex */
    interface Listener {
        void onScrollChange(PointF pointF);
    }

    public TouchTracker(Context context, Listener listener, float f) {
        this.listener = listener;
        this.pxPerDegrees = f;
        this.gestureDetector = new GestureDetector(context, this);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        this.previousTouchPointPx.set(motionEvent.getX(), motionEvent.getY());
        return true;
    }

    @Override // com.google.android.exoplayer2.ui.spherical.OrientationListener.Listener
    @BinderThread
    public void onOrientationChange(float[] fArr, float f) {
        this.roll = -f;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float x = (motionEvent2.getX() - this.previousTouchPointPx.x) / this.pxPerDegrees;
        float y = motionEvent2.getY();
        PointF pointF = this.previousTouchPointPx;
        float f3 = (y - pointF.y) / this.pxPerDegrees;
        pointF.set(motionEvent2.getX(), motionEvent2.getY());
        double d = this.roll;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        PointF pointF2 = this.accumulatedTouchOffsetDegrees;
        pointF2.x -= (cos * x) - (sin * f3);
        pointF2.y = (cos * f3) + (sin * x) + pointF2.y;
        pointF2.y = Math.max(-45.0f, Math.min((float) MAX_PITCH_DEGREES, pointF2.y));
        this.listener.onScrollChange(this.accumulatedTouchOffsetDegrees);
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        SingleTapListener singleTapListener = this.singleTapListener;
        if (singleTapListener != null) {
            return singleTapListener.onSingleTapUp(motionEvent);
        }
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.gestureDetector.onTouchEvent(motionEvent);
    }

    public void setSingleTapListener(@Nullable SingleTapListener singleTapListener) {
        this.singleTapListener = singleTapListener;
    }
}
