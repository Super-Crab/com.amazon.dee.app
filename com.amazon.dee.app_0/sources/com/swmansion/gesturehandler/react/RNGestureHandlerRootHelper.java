package com.swmansion.gesturehandler.react;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.views.modal.RNGHModalUtils;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.GestureHandlerOrchestrator;
/* loaded from: classes3.dex */
public class RNGestureHandlerRootHelper {
    private static final float MIN_ALPHA_FOR_TOUCH = 0.1f;
    private final ReactContext mContext;
    private final GestureHandler mJSGestureHandler;
    private final GestureHandlerOrchestrator mOrchestrator;
    private final ViewGroup mRootView;
    private boolean mShouldIntercept = false;
    private boolean mPassingTouch = false;

    /* loaded from: classes3.dex */
    private class RootViewGestureHandler extends GestureHandler {
        private RootViewGestureHandler() {
        }

        @Override // com.swmansion.gesturehandler.GestureHandler
        protected void onCancel() {
            RNGestureHandlerRootHelper.this.mShouldIntercept = true;
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            obtain.setAction(3);
            if (RNGestureHandlerRootHelper.this.mRootView instanceof ReactRootView) {
                ((ReactRootView) RNGestureHandlerRootHelper.this.mRootView).onChildStartedNativeGesture(obtain);
            } else {
                RNGHModalUtils.dialogRootViewGroupOnChildStartedNativeGesture(RNGestureHandlerRootHelper.this.mRootView, obtain);
            }
        }

        @Override // com.swmansion.gesturehandler.GestureHandler
        protected void onHandle(MotionEvent motionEvent) {
            if (getState() == 0) {
                begin();
                RNGestureHandlerRootHelper.this.mShouldIntercept = false;
            }
            if (motionEvent.getActionMasked() == 1) {
                end();
            }
        }
    }

    public RNGestureHandlerRootHelper(ReactContext reactContext, ViewGroup viewGroup) {
        UiThreadUtil.assertOnUiThread();
        int id = viewGroup.getId();
        if (id >= 1) {
            RNGestureHandlerModule rNGestureHandlerModule = (RNGestureHandlerModule) reactContext.getNativeModule(RNGestureHandlerModule.class);
            RNGestureHandlerRegistry registry = rNGestureHandlerModule.getRegistry();
            this.mRootView = findRootViewTag(viewGroup);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[GESTURE HANDLER] Initialize gesture handler for root view ");
            outline107.append(this.mRootView);
            Log.i(ReactConstants.TAG, outline107.toString());
            this.mContext = reactContext;
            this.mOrchestrator = new GestureHandlerOrchestrator(viewGroup, registry, new RNViewConfigurationHelper());
            this.mOrchestrator.setMinimumAlphaForTraversal(0.1f);
            this.mJSGestureHandler = new RootViewGestureHandler();
            this.mJSGestureHandler.setTag(-id);
            registry.registerHandler(this.mJSGestureHandler);
            registry.attachHandlerToView(this.mJSGestureHandler.getTag(), id);
            rNGestureHandlerModule.registerRootHelper(this);
            return;
        }
        throw new IllegalStateException("Expect view tag to be set for " + viewGroup);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0019, code lost:
        return (android.view.ViewGroup) r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.view.ViewGroup findRootViewTag(android.view.ViewGroup r3) {
        /*
            com.facebook.react.bridge.UiThreadUtil.assertOnUiThread()
            r0 = r3
        L4:
            if (r0 == 0) goto L15
            boolean r1 = r0 instanceof com.facebook.react.ReactRootView
            if (r1 != 0) goto L15
            boolean r1 = com.facebook.react.views.modal.RNGHModalUtils.isDialogRootViewGroup(r0)
            if (r1 != 0) goto L15
            android.view.ViewParent r0 = r0.getParent()
            goto L4
        L15:
            if (r0 == 0) goto L1a
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            return r0
        L1a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "View "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = " has not been mounted under ReactRootView"
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper.findRootViewTag(android.view.ViewGroup):android.view.ViewGroup");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryCancelAllHandlers() {
        GestureHandler gestureHandler = this.mJSGestureHandler;
        if (gestureHandler == null || gestureHandler.getState() != 2) {
            return;
        }
        this.mJSGestureHandler.activate();
        this.mJSGestureHandler.end();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.mPassingTouch = true;
        this.mOrchestrator.onTouchEvent(motionEvent);
        this.mPassingTouch = false;
        return this.mShouldIntercept;
    }

    public ViewGroup getRootView() {
        return this.mRootView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleSetJSResponder(int i, boolean z) {
        if (z) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    RNGestureHandlerRootHelper.this.tryCancelAllHandlers();
                }
            });
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (this.mOrchestrator == null || this.mPassingTouch) {
            return;
        }
        tryCancelAllHandlers();
    }

    public void tearDown() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[GESTURE HANDLER] Tearing down gesture handler registered for root view ");
        outline107.append(this.mRootView);
        Log.i(ReactConstants.TAG, outline107.toString());
        RNGestureHandlerModule rNGestureHandlerModule = (RNGestureHandlerModule) this.mContext.getNativeModule(RNGestureHandlerModule.class);
        rNGestureHandlerModule.getRegistry().dropHandler(this.mJSGestureHandler.getTag());
        rNGestureHandlerModule.unregisterRootHelper(this);
    }
}
