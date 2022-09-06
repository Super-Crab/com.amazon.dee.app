package com.amazon.mobile.heremapsexplore;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.amazon.mobile.heremapsexplore.Utilities.UIUtils;
import java.lang.ref.WeakReference;
/* loaded from: classes13.dex */
public class GeofenceHandleView extends View {
    private static final int PULSE_ANIMATION_DURATION = 1000;
    private final float deadZoneRadius;
    private GeofenceView geofence;
    private boolean isExploreByTouchEnabled;
    private int mActivePointerId;
    private GeofenceDrawable mDrawable;
    private float mInitTouchX;
    private float mLastTouchX;
    private final Rect pressedVisualBounds;
    private final int pressedVisualRadius;
    private final ValueAnimator pulseAnimation;
    private final float pulseEndStrokeWidth;
    private final float pulseStartStrokeWidth;
    private final Rect releasedVisualBounds;
    private final int releasedVisualRadius;
    private final Rect touchableBounds;
    private final int touchableRadius;
    private WeakReference<HereMapExploreView> weakHereMapView;
    private static final int FILL_COLOR = UITheme.DarkTheme.getControlActive();
    private static final int STROKE_COLOR = UITheme.DarkTheme.getTextPrimaryInverse();

    public GeofenceHandleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.releasedVisualRadius = (int) UIUtils.dpToPx(this, 9);
        this.pressedVisualRadius = (int) UIUtils.dpToPx(this, 12);
        this.touchableRadius = this.pressedVisualRadius * 2;
        this.pulseStartStrokeWidth = UIUtils.dpToPx(this, 4);
        this.pulseEndStrokeWidth = UIUtils.dpToPx(this, 2);
        this.releasedVisualBounds = new Rect();
        this.pressedVisualBounds = new Rect();
        this.touchableBounds = new Rect();
        this.mActivePointerId = -1;
        this.deadZoneRadius = UIUtils.dpToPx(this, 2);
        this.isExploreByTouchEnabled = false;
        this.mDrawable = new GeofenceDrawable(FILL_COLOR, STROKE_COLOR);
        this.mDrawable.setShowBorder(true);
        this.pulseAnimation = ValueAnimator.ofFloat(this.pulseStartStrokeWidth, this.pulseEndStrokeWidth);
        this.pulseAnimation.setDuration(1000L);
        this.pulseAnimation.setRepeatMode(2);
        this.pulseAnimation.setRepeatCount(-1);
        this.pulseAnimation.start();
        this.pulseAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.amazon.mobile.heremapsexplore.GeofenceHandleView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                GeofenceHandleView.this.mDrawable.setStrokeWidth(((Float) valueAnimator.getAnimatedValue()).floatValue());
                GeofenceHandleView.this.invalidate();
            }
        });
        setContentDescription("Geo fence radius adjust button");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Rect bounds = this.geofence.getBounds();
        if (this.isExploreByTouchEnabled) {
            Rect rect = this.touchableBounds;
            int i = bounds.right - (this.touchableRadius * 2);
            int centerY = bounds.centerY();
            int i2 = this.touchableRadius;
            rect.set(i, centerY - (i2 * 2), (i2 * 2) + bounds.right, (this.touchableRadius * 2) + bounds.centerY());
            Rect rect2 = this.touchableBounds;
            layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
            Rect rect3 = this.releasedVisualBounds;
            int i3 = this.touchableRadius;
            int i4 = this.releasedVisualRadius;
            rect3.set((i3 * 2) - i4, (i3 * 2) - i4, (i3 * 2) + i4, (i3 * 2) + i4);
            Rect rect4 = this.pressedVisualBounds;
            int i5 = this.touchableRadius;
            int i6 = this.pressedVisualRadius;
            rect4.set((i5 * 2) - i6, (i5 * 2) - i6, (i5 * 2) + i6, (i5 * 2) + i6);
        } else {
            Rect rect5 = this.touchableBounds;
            int i7 = bounds.right - this.touchableRadius;
            int centerY2 = bounds.centerY();
            int i8 = this.touchableRadius;
            rect5.set(i7, centerY2 - i8, bounds.right + i8, bounds.centerY() + this.touchableRadius);
            Rect rect6 = this.releasedVisualBounds;
            int i9 = bounds.right - this.releasedVisualRadius;
            int centerY3 = bounds.centerY();
            int i10 = this.releasedVisualRadius;
            rect6.set(i9, centerY3 - i10, bounds.right + i10, bounds.centerY() + this.releasedVisualRadius);
            Rect rect7 = this.pressedVisualBounds;
            int i11 = bounds.right - this.pressedVisualRadius;
            int centerY4 = bounds.centerY();
            int i12 = this.pressedVisualRadius;
            rect7.set(i11, centerY4 - i12, bounds.right + i12, bounds.centerY() + this.pressedVisualRadius);
        }
        this.mDrawable.setBounds(this.mActivePointerId == -1 ? this.releasedVisualBounds : this.pressedVisualBounds);
        this.mDrawable.draw(canvas);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int i = 0;
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                this.mActivePointerId = -1;
                this.geofence.onHandleReleased();
                this.pulseAnimation.start();
                invalidate();
            } else if (actionMasked == 2) {
                float x = motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId));
                float f = x - this.mLastTouchX;
                this.mLastTouchX = x;
                if (Math.abs(x - this.mInitTouchX) >= this.deadZoneRadius) {
                    this.geofence.onHandleDragging((int) f);
                    invalidate();
                }
            } else if (actionMasked == 3) {
                this.mActivePointerId = -1;
                this.geofence.onHandleReleased();
                this.pulseAnimation.start();
                invalidate();
            } else if (actionMasked == 6) {
                int actionIndex = motionEvent.getActionIndex();
                if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
                    if (actionIndex == 0) {
                        i = 1;
                    }
                    this.mLastTouchX = motionEvent.getX(i);
                    this.mActivePointerId = motionEvent.getPointerId(i);
                }
            }
        } else if (!this.isExploreByTouchEnabled && (!this.touchableBounds.contains((int) motionEvent.getX(), (int) motionEvent.getY()) || (this.weakHereMapView.get() != null && !this.weakHereMapView.get().isMapIdle()))) {
            return false;
        } else {
            this.mLastTouchX = motionEvent.getX(motionEvent.getActionIndex());
            this.mInitTouchX = this.mLastTouchX;
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.pulseAnimation.cancel();
            this.mDrawable.setStrokeWidth(this.pulseEndStrokeWidth);
            this.geofence.onHandlePressed();
            invalidate();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setExploreByTouchEnabled(boolean z) {
        this.isExploreByTouchEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGeofence(GeofenceView geofenceView) {
        this.geofence = geofenceView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHereMapView(HereMapExploreView hereMapExploreView) {
        this.weakHereMapView = new WeakReference<>(hereMapExploreView);
    }
}
