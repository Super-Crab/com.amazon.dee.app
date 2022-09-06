package com.amazon.alexa.redesign.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes10.dex */
public class CarouselTouchHandler {
    @VisibleForTesting
    final int touchSlop;
    private float scrollStartY = 0.0f;
    private float scrollEndY = 0.0f;

    public CarouselTouchHandler(Context context) {
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean handleTouchEvent(MotionEvent motionEvent, ViewParent viewParent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.scrollStartY = motionEvent.getY();
        } else if (actionMasked == 2) {
            this.scrollEndY = motionEvent.getY();
            if (Math.abs(this.scrollEndY - this.scrollStartY) > this.touchSlop) {
                viewParent.requestDisallowInterceptTouchEvent(false);
                return false;
            }
        }
        viewParent.requestDisallowInterceptTouchEvent(true);
        return false;
    }
}
