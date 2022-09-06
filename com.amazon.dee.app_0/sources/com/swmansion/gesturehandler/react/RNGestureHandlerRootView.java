package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.views.view.ReactViewGroup;
/* loaded from: classes3.dex */
public class RNGestureHandlerRootView extends ReactViewGroup {
    private boolean mEnabled;
    @Nullable
    private RNGestureHandlerRootHelper mRootHelper;

    public RNGestureHandlerRootView(Context context) {
        super(context);
    }

    private static boolean hasGestureHandlerEnabledRootView(ViewGroup viewGroup) {
        UiThreadUtil.assertOnUiThread();
        for (ViewParent parent = viewGroup.getParent(); parent != null; parent = parent.getParent()) {
            if ((parent instanceof RNGestureHandlerEnabledRootView) || (parent instanceof RNGestureHandlerRootView)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.mEnabled || !((RNGestureHandlerRootHelper) Assertions.assertNotNull(this.mRootHelper)).dispatchTouchEvent(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mEnabled = !hasGestureHandlerEnabledRootView(this);
        if (!this.mEnabled) {
            Log.i(ReactConstants.TAG, "[GESTURE HANDLER] Gesture handler is already enabled for a parent view");
        }
        if (!this.mEnabled || this.mRootHelper != null) {
            return;
        }
        this.mRootHelper = new RNGestureHandlerRootHelper((ReactContext) getContext(), this);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (this.mEnabled) {
            ((RNGestureHandlerRootHelper) Assertions.assertNotNull(this.mRootHelper)).requestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void tearDown() {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mRootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.tearDown();
        }
    }
}
