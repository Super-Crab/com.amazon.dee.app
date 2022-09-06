package com.facebook.react.views.drawer;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.events.NativeGestureUtil;
/* loaded from: classes2.dex */
class ReactDrawerLayout extends DrawerLayout {
    public static final int DEFAULT_DRAWER_WIDTH = -1;
    private int mDrawerPosition;
    private int mDrawerWidth;

    public ReactDrawerLayout(ReactContext reactContext) {
        super(reactContext);
        this.mDrawerPosition = GravityCompat.START;
        this.mDrawerWidth = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeDrawer() {
        closeDrawer(this.mDrawerPosition);
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (!super.onInterceptTouchEvent(motionEvent)) {
                return false;
            }
            NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
            return true;
        } catch (IllegalArgumentException e) {
            FLog.w(ReactConstants.TAG, "Error intercepting touch event.", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void openDrawer() {
        openDrawer(this.mDrawerPosition);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDrawerPosition(int i) {
        this.mDrawerPosition = i;
        setDrawerProperties();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDrawerProperties() {
        if (getChildCount() == 2) {
            View childAt = getChildAt(1);
            DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) childAt.getLayoutParams();
            layoutParams.gravity = this.mDrawerPosition;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = this.mDrawerWidth;
            childAt.setLayoutParams(layoutParams);
            childAt.setClickable(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDrawerWidth(int i) {
        this.mDrawerWidth = i;
        setDrawerProperties();
    }
}
