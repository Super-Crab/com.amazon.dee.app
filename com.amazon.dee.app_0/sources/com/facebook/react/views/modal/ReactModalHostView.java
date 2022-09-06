package com.facebook.react.views.modal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.common.ContextUtils;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class ReactModalHostView extends ViewGroup implements LifecycleEventListener, FabricViewStateManager.HasFabricViewStateManager {
    private String mAnimationType;
    @Nullable
    private Dialog mDialog;
    private boolean mHardwareAccelerated;
    private DialogRootViewGroup mHostView;
    @Nullable
    private OnRequestCloseListener mOnRequestCloseListener;
    @Nullable
    private DialogInterface.OnShowListener mOnShowListener;
    private boolean mPropertyRequiresNewDialog;
    private boolean mStatusBarTranslucent;
    private boolean mTransparent;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class DialogRootViewGroup extends ReactViewGroup implements RootView, FabricViewStateManager.HasFabricViewStateManager {
        private boolean hasAdjustedSize;
        private final FabricViewStateManager mFabricViewStateManager;
        private final JSTouchDispatcher mJSTouchDispatcher;
        private int viewHeight;
        private int viewWidth;

        public DialogRootViewGroup(Context context) {
            super(context);
            this.hasAdjustedSize = false;
            this.mFabricViewStateManager = new FabricViewStateManager();
            this.mJSTouchDispatcher = new JSTouchDispatcher(this);
        }

        private EventDispatcher getEventDispatcher() {
            return ((UIManagerModule) getReactContext().getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ReactContext getReactContext() {
            return (ReactContext) getContext();
        }

        private void updateFirstChildView() {
            if (getChildCount() > 0) {
                this.hasAdjustedSize = false;
                final int id = getChildAt(0).getId();
                if (this.mFabricViewStateManager.hasStateWrapper()) {
                    updateState(this.viewWidth, this.viewHeight);
                    return;
                }
                ReactContext reactContext = getReactContext();
                reactContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactContext) { // from class: com.facebook.react.views.modal.ReactModalHostView.DialogRootViewGroup.1
                    @Override // com.facebook.react.bridge.GuardedRunnable
                    public void runGuarded() {
                        UIManagerModule uIManagerModule = (UIManagerModule) DialogRootViewGroup.this.getReactContext().getNativeModule(UIManagerModule.class);
                        if (uIManagerModule == null) {
                            return;
                        }
                        uIManagerModule.updateNodeSize(id, DialogRootViewGroup.this.viewWidth, DialogRootViewGroup.this.viewHeight);
                    }
                });
                return;
            }
            this.hasAdjustedSize = true;
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
            super.addView(view, i, layoutParams);
            if (this.hasAdjustedSize) {
                updateFirstChildView();
            }
        }

        @Override // com.facebook.react.uimanager.FabricViewStateManager.HasFabricViewStateManager
        public FabricViewStateManager getFabricViewStateManager() {
            return this.mFabricViewStateManager;
        }

        @Override // com.facebook.react.uimanager.RootView
        public void handleException(Throwable th) {
            getReactContext().handleException(new RuntimeException(th));
        }

        @Override // com.facebook.react.uimanager.RootView
        public void onChildStartedNativeGesture(MotionEvent motionEvent) {
            this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, getEventDispatcher());
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, getEventDispatcher());
            return super.onInterceptTouchEvent(motionEvent);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        public void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            this.viewWidth = i;
            this.viewHeight = i2;
            updateFirstChildView();
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, getEventDispatcher());
            super.onTouchEvent(motionEvent);
            return true;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean z) {
        }

        @UiThread
        public void updateState(int i, int i2) {
            final float dIPFromPixel = PixelUtil.toDIPFromPixel(i);
            final float dIPFromPixel2 = PixelUtil.toDIPFromPixel(i2);
            ReadableMap state = getFabricViewStateManager().getState();
            if (state != null) {
                float f = 0.0f;
                float f2 = state.hasKey("screenHeight") ? (float) state.getDouble("screenHeight") : 0.0f;
                if (state.hasKey(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH)) {
                    f = (float) state.getDouble(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH);
                }
                if (Math.abs(f - dIPFromPixel) < 0.9f && Math.abs(f2 - dIPFromPixel2) < 0.9f) {
                    return;
                }
            }
            this.mFabricViewStateManager.setState(new FabricViewStateManager.StateUpdateCallback() { // from class: com.facebook.react.views.modal.ReactModalHostView.DialogRootViewGroup.2
                @Override // com.facebook.react.uimanager.FabricViewStateManager.StateUpdateCallback
                public WritableMap getStateUpdate() {
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH, dIPFromPixel);
                    writableNativeMap.putDouble("screenHeight", dIPFromPixel2);
                    return writableNativeMap;
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    public interface OnRequestCloseListener {
        void onRequestClose(DialogInterface dialogInterface);
    }

    public ReactModalHostView(Context context) {
        super(context);
        ((ReactContext) context).addLifecycleEventListener(this);
        this.mHostView = new DialogRootViewGroup(context);
    }

    private void dismiss() {
        Activity activity;
        UiThreadUtil.assertOnUiThread();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            if (dialog.isShowing() && ((activity = (Activity) ContextUtils.findContextOfType(this.mDialog.getContext(), Activity.class)) == null || !activity.isFinishing())) {
                this.mDialog.dismiss();
            }
            this.mDialog = null;
            ((ViewGroup) this.mHostView.getParent()).removeViewAt(0);
        }
    }

    private View getContentView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.mHostView);
        if (this.mStatusBarTranslucent) {
            frameLayout.setSystemUiVisibility(1024);
        } else {
            frameLayout.setFitsSystemWindows(true);
        }
        return frameLayout;
    }

    @Nullable
    private Activity getCurrentActivity() {
        return ((ReactContext) getContext()).getCurrentActivity();
    }

    private void updateProperties() {
        Assertions.assertNotNull(this.mDialog, "mDialog must exist when we call updateProperties");
        Activity currentActivity = getCurrentActivity();
        Window window = this.mDialog.getWindow();
        if (currentActivity == null || currentActivity.isFinishing() || !window.isActive()) {
            return;
        }
        if ((currentActivity.getWindow().getAttributes().flags & 1024) != 0) {
            window.addFlags(1024);
        } else {
            window.clearFlags(1024);
        }
        if (this.mTransparent) {
            window.clearFlags(2);
            return;
        }
        window.setDimAmount(0.5f);
        window.setFlags(2, 2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.addView(view, i);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(23)
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        this.mHostView.dispatchProvideStructure(viewStructure);
    }

    @Override // android.view.ViewGroup
    public View getChildAt(int i) {
        return this.mHostView.getChildAt(i);
    }

    @Override // android.view.ViewGroup
    public int getChildCount() {
        return this.mHostView.getChildCount();
    }

    @Nullable
    @VisibleForTesting
    public Dialog getDialog() {
        return this.mDialog;
    }

    @Override // com.facebook.react.uimanager.FabricViewStateManager.HasFabricViewStateManager
    public FabricViewStateManager getFabricViewStateManager() {
        return this.mHostView.getFabricViewStateManager();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismiss();
    }

    public void onDropInstance() {
        ((ReactContext) getContext()).removeLifecycleEventListener(this);
        dismiss();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        onDropInstance();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        showOrUpdate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.removeView(getChildAt(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAnimationType(String str) {
        this.mAnimationType = str;
        this.mPropertyRequiresNewDialog = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setHardwareAccelerated(boolean z) {
        this.mHardwareAccelerated = z;
        this.mPropertyRequiresNewDialog = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnRequestCloseListener(OnRequestCloseListener onRequestCloseListener) {
        this.mOnRequestCloseListener = onRequestCloseListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.mOnShowListener = onShowListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStatusBarTranslucent(boolean z) {
        this.mStatusBarTranslucent = z;
        this.mPropertyRequiresNewDialog = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTransparent(boolean z) {
        this.mTransparent = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showOrUpdate() {
        UiThreadUtil.assertOnUiThread();
        if (this.mDialog != null) {
            if (this.mPropertyRequiresNewDialog) {
                dismiss();
            } else {
                updateProperties();
                return;
            }
        }
        this.mPropertyRequiresNewDialog = false;
        int i = R.style.Theme_FullScreenDialog;
        if (this.mAnimationType.equals(EventBusConstants.FADE_KEY)) {
            i = R.style.Theme_FullScreenDialogAnimatedFade;
        } else if (this.mAnimationType.equals("slide")) {
            i = R.style.Theme_FullScreenDialogAnimatedSlide;
        }
        Activity currentActivity = getCurrentActivity();
        Context context = currentActivity == null ? getContext() : currentActivity;
        this.mDialog = new Dialog(context, i);
        this.mDialog.getWindow().setFlags(8, 8);
        this.mDialog.setContentView(getContentView());
        updateProperties();
        this.mDialog.setOnShowListener(this.mOnShowListener);
        this.mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.facebook.react.views.modal.ReactModalHostView.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 1) {
                    if (i2 == 4) {
                        Assertions.assertNotNull(ReactModalHostView.this.mOnRequestCloseListener, "setOnRequestCloseListener must be called by the manager");
                        ReactModalHostView.this.mOnRequestCloseListener.onRequestClose(dialogInterface);
                        return true;
                    }
                    Activity currentActivity2 = ((ReactContext) ReactModalHostView.this.getContext()).getCurrentActivity();
                    if (currentActivity2 == null) {
                        return false;
                    }
                    return currentActivity2.onKeyUp(i2, keyEvent);
                }
                return false;
            }
        });
        this.mDialog.getWindow().setSoftInputMode(16);
        if (this.mHardwareAccelerated) {
            this.mDialog.getWindow().addFlags(16777216);
        }
        if (currentActivity == null || currentActivity.isFinishing()) {
            return;
        }
        this.mDialog.show();
        if (context instanceof Activity) {
            this.mDialog.getWindow().getDecorView().setSystemUiVisibility(((Activity) context).getWindow().getDecorView().getSystemUiVisibility());
        }
        this.mDialog.getWindow().clearFlags(8);
    }

    public void updateState(int i, int i2) {
        this.mHostView.updateState(i, i2);
    }
}
