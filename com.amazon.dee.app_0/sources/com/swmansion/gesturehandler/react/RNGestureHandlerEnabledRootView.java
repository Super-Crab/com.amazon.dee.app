package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.Nullable;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
/* loaded from: classes3.dex */
public class RNGestureHandlerEnabledRootView extends ReactRootView {
    @Nullable
    private RNGestureHandlerRootHelper mGestureRootHelper;
    @Nullable
    private ReactInstanceManager mReactInstanceManager;

    public RNGestureHandlerEnabledRootView(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mGestureRootHelper;
        if (rNGestureHandlerRootHelper == null || !rNGestureHandlerRootHelper.dispatchTouchEvent(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    public void initialize() {
        if (this.mGestureRootHelper == null) {
            this.mGestureRootHelper = new RNGestureHandlerRootHelper(this.mReactInstanceManager.getCurrentReactContext(), this);
            return;
        }
        throw new IllegalStateException("GestureHandler already initialized for root view " + this);
    }

    @Override // com.facebook.react.ReactRootView, android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mGestureRootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.requestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // com.facebook.react.ReactRootView
    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, @Nullable Bundle bundle) {
        super.startReactApplication(reactInstanceManager, str, bundle);
        this.mReactInstanceManager = reactInstanceManager;
    }

    public void tearDown() {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mGestureRootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.tearDown();
            this.mGestureRootHelper = null;
        }
    }

    public RNGestureHandlerEnabledRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
