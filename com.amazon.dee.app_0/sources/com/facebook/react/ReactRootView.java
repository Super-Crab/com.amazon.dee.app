package com.facebook.react;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.ThreadConfined;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.systrace.Systrace;
/* loaded from: classes2.dex */
public class ReactRootView extends FrameLayout implements RootView, ReactRoot {
    private static final String TAG = "ReactRootView";
    private final ReactAndroidHWInputDeviceHelper mAndroidHWInputDeviceHelper;
    @Nullable
    private Bundle mAppProperties;
    @Nullable
    private CustomGlobalLayoutListener mCustomGlobalLayoutListener;
    private int mHeightMeasureSpec;
    @Nullable
    private String mInitialUITemplate;
    private boolean mIsAttachedToInstance;
    @Nullable
    private String mJSModuleName;
    @Nullable
    private JSTouchDispatcher mJSTouchDispatcher;
    private int mLastHeight;
    private int mLastOffsetX;
    private int mLastOffsetY;
    private int mLastWidth;
    @Nullable
    private ReactInstanceManager mReactInstanceManager;
    @Nullable
    private ReactRootViewEventListener mRootViewEventListener;
    private int mRootViewTag;
    private boolean mShouldLogContentAppeared;
    private int mUIManagerType;
    private boolean mWasMeasured;
    private int mWidthMeasureSpec;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class CustomGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private final int mMinKeyboardHeightDetected;
        private final Rect mVisibleViewArea;
        private int mKeyboardHeight = 0;
        private int mDeviceRotation = 0;

        CustomGlobalLayoutListener() {
            DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(ReactRootView.this.getContext().getApplicationContext());
            this.mVisibleViewArea = new Rect();
            this.mMinKeyboardHeightDetected = (int) PixelUtil.toPixelFromDIP(60.0f);
        }

        private void checkForDeviceDimensionsChanges() {
            emitUpdateDimensionsEvent();
        }

        private void checkForDeviceOrientationChanges() {
            int rotation = ((WindowManager) ReactRootView.this.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
            if (this.mDeviceRotation == rotation) {
                return;
            }
            this.mDeviceRotation = rotation;
            DisplayMetricsHolder.initDisplayMetrics(ReactRootView.this.getContext().getApplicationContext());
            emitOrientationChanged(rotation);
        }

        private void checkForKeyboardEvents() {
            ReactRootView reactRootView;
            ReactRootView.this.getRootView().getWindowVisibleDisplayFrame(this.mVisibleViewArea);
            int i = DisplayMetricsHolder.getWindowDisplayMetrics().heightPixels - this.mVisibleViewArea.bottom;
            boolean z = true;
            if (this.mKeyboardHeight != i && i > this.mMinKeyboardHeightDetected) {
                this.mKeyboardHeight = i;
                ReactRootView.this.sendEvent("keyboardDidShow", createKeyboardEventPayload(PixelUtil.toDIPFromPixel(this.mVisibleViewArea.bottom), PixelUtil.toDIPFromPixel(this.mVisibleViewArea.left), PixelUtil.toDIPFromPixel(this.mVisibleViewArea.width()), PixelUtil.toDIPFromPixel(this.mKeyboardHeight)));
                return;
            }
            if (this.mKeyboardHeight == 0 || i > this.mMinKeyboardHeightDetected) {
                z = false;
            }
            if (!z) {
                return;
            }
            this.mKeyboardHeight = 0;
            ReactRootView.this.sendEvent("keyboardDidHide", createKeyboardEventPayload(PixelUtil.toDIPFromPixel(reactRootView.mLastHeight), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, PixelUtil.toDIPFromPixel(this.mVisibleViewArea.width()), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR));
        }

        private WritableMap createKeyboardEventPayload(double d, double d2, double d3, double d4) {
            WritableMap createMap = Arguments.createMap();
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putDouble("height", d4);
            createMap2.putDouble("screenX", d2);
            createMap2.putDouble("width", d3);
            createMap2.putDouble("screenY", d);
            createMap.putMap("endCoordinates", createMap2);
            createMap.putString("easing", "keyboard");
            createMap.putDouble("duration", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
            return createMap;
        }

        private void emitOrientationChanged(int i) {
            double d;
            String str;
            double d2;
            boolean z = true;
            if (i != 0) {
                if (i == 1) {
                    d2 = -90.0d;
                    str = "landscape-primary";
                } else if (i == 2) {
                    d = 180.0d;
                    str = "portrait-secondary";
                } else if (i != 3) {
                    return;
                } else {
                    d2 = 90.0d;
                    str = "landscape-secondary";
                }
                WritableMap createMap = Arguments.createMap();
                createMap.putString("name", str);
                createMap.putDouble("rotationDegrees", d2);
                createMap.putBoolean("isLandscape", z);
                ReactRootView.this.sendEvent("namedOrientationDidChange", createMap);
            }
            d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            str = "portrait-primary";
            z = false;
            d2 = d;
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putString("name", str);
            createMap2.putDouble("rotationDegrees", d2);
            createMap2.putBoolean("isLandscape", z);
            ReactRootView.this.sendEvent("namedOrientationDidChange", createMap2);
        }

        private void emitUpdateDimensionsEvent() {
            DeviceInfoModule deviceInfoModule = (DeviceInfoModule) ReactRootView.this.mReactInstanceManager.getCurrentReactContext().getNativeModule(DeviceInfoModule.class);
            if (deviceInfoModule != null) {
                deviceInfoModule.emitUpdateDimensionsEvent();
            }
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (ReactRootView.this.mReactInstanceManager == null || !ReactRootView.this.mIsAttachedToInstance || ReactRootView.this.mReactInstanceManager.getCurrentReactContext() == null) {
                return;
            }
            checkForKeyboardEvents();
            checkForDeviceOrientationChanges();
            checkForDeviceDimensionsChanges();
        }
    }

    /* loaded from: classes2.dex */
    public interface ReactRootViewEventListener {
        void onAttachedToReactInstance(ReactRootView reactRootView);
    }

    public ReactRootView(Context context) {
        super(context);
        this.mAndroidHWInputDeviceHelper = new ReactAndroidHWInputDeviceHelper(this);
        this.mWasMeasured = false;
        this.mWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mLastWidth = 0;
        this.mLastHeight = 0;
        this.mLastOffsetX = Integer.MIN_VALUE;
        this.mLastOffsetY = Integer.MIN_VALUE;
        this.mUIManagerType = 1;
        init();
    }

    private void attachToReactInstanceManager() {
        Systrace.beginSection(0L, "attachToReactInstanceManager");
        if (this.mIsAttachedToInstance) {
            return;
        }
        try {
            this.mIsAttachedToInstance = true;
            ((ReactInstanceManager) Assertions.assertNotNull(this.mReactInstanceManager)).attachRootView(this);
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        } finally {
            Systrace.endSection(0L);
        }
    }

    private void dispatchJSTouchEvent(MotionEvent motionEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
            if (this.mJSTouchDispatcher == null) {
                FLog.w(TAG, "Unable to dispatch touch to JS before the dispatcher is available");
                return;
            }
            UIManagerModule uIManagerModule = (UIManagerModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class);
            if (uIManagerModule == null) {
                return;
            }
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, uIManagerModule.mo6949getEventDispatcher());
            return;
        }
        FLog.w(TAG, "Unable to dispatch touch to JS as the catalyst instance has not been attached");
    }

    private CustomGlobalLayoutListener getCustomGlobalLayoutListener() {
        if (this.mCustomGlobalLayoutListener == null) {
            this.mCustomGlobalLayoutListener = new CustomGlobalLayoutListener();
        }
        return this.mCustomGlobalLayoutListener;
    }

    public static Point getViewportOffset(View view) {
        view.getLocationInWindow(r0);
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int[] iArr = {iArr[0] - rect.left, iArr[1] - rect.top};
        return new Point(iArr[0], iArr[1]);
    }

    private void init() {
        setClipChildren(false);
    }

    private void removeOnGlobalLayoutListener() {
        getViewTreeObserver().removeOnGlobalLayoutListener(getCustomGlobalLayoutListener());
    }

    private void updateRootLayoutSpecs(boolean z, int i, int i2) {
        UIManager uIManager;
        int i3;
        int i4;
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null) {
            FLog.w(TAG, "Unable to update root layout specs for uninitialized ReactInstanceManager");
            return;
        }
        ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
        if (currentReactContext == null || (uIManager = UIManagerHelper.getUIManager(currentReactContext, getUIManagerType())) == null) {
            return;
        }
        if (getUIManagerType() == 2) {
            Point viewportOffset = getViewportOffset(this);
            i4 = viewportOffset.x;
            i3 = viewportOffset.y;
        } else {
            i3 = 0;
            i4 = 0;
        }
        if (z || i4 != this.mLastOffsetX || i3 != this.mLastOffsetY) {
            uIManager.updateRootLayoutSpecs(getRootViewTag(), i, i2, i4, i3);
        }
        this.mLastOffsetX = i4;
        this.mLastOffsetY = i3;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (StackOverflowError e) {
            handleException(e);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
            this.mAndroidHWInputDeviceHelper.handleKeyEvent(keyEvent);
            return super.dispatchKeyEvent(keyEvent);
        }
        FLog.w(TAG, "Unable to handle key event as the catalyst instance has not been attached");
        return super.dispatchKeyEvent(keyEvent);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        Assertions.assertCondition(!this.mIsAttachedToInstance, "The application this ReactRootView was rendering was not unmounted before the ReactRootView was garbage collected. This usually means that your application is leaking large amounts of memory. To solve this, make sure to call ReactRootView#unmountReactApplication in the onDestroy() of your hosting Activity or in the onDestroyView() of your hosting Fragment.");
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    @Nullable
    public Bundle getAppProperties() {
        return this.mAppProperties;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getHeightMeasureSpec() {
        return this.mHeightMeasureSpec;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    @Nullable
    public String getInitialUITemplate() {
        return this.mInitialUITemplate;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public String getJSModuleName() {
        return (String) Assertions.assertNotNull(this.mJSModuleName);
    }

    @Nullable
    public ReactInstanceManager getReactInstanceManager() {
        return this.mReactInstanceManager;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public ViewGroup getRootViewGroup() {
        return this;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getRootViewTag() {
        return this.mRootViewTag;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    @Nullable
    public String getSurfaceID() {
        Bundle appProperties = getAppProperties();
        if (appProperties != null) {
            return appProperties.getString("surfaceID");
        }
        return null;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getUIManagerType() {
        return this.mUIManagerType;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getWidthMeasureSpec() {
        return this.mWidthMeasureSpec;
    }

    @Override // com.facebook.react.uimanager.RootView
    public void handleException(Throwable th) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && reactInstanceManager.getCurrentReactContext() != null) {
            this.mReactInstanceManager.getCurrentReactContext().handleException(new IllegalViewOperationException(th.getMessage(), this, th));
            return;
        }
        throw new RuntimeException(th);
    }

    public void onAttachedToReactInstance() {
        this.mJSTouchDispatcher = new JSTouchDispatcher(this);
        ReactRootViewEventListener reactRootViewEventListener = this.mRootViewEventListener;
        if (reactRootViewEventListener != null) {
            reactRootViewEventListener.onAttachedToReactInstance(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsAttachedToInstance) {
            removeOnGlobalLayoutListener();
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    @Override // com.facebook.react.uimanager.RootView
    public void onChildStartedNativeGesture(MotionEvent motionEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
            if (this.mJSTouchDispatcher == null) {
                FLog.w(TAG, "Unable to dispatch touch to JS before the dispatcher is available");
                return;
            }
            UIManagerModule uIManagerModule = (UIManagerModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class);
            if (uIManagerModule == null) {
                return;
            }
            this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, uIManagerModule.mo6949getEventDispatcher());
            return;
        }
        FLog.w(TAG, "Unable to dispatch touch to JS as the catalyst instance has not been attached");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIsAttachedToInstance) {
            removeOnGlobalLayoutListener();
        }
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
            this.mAndroidHWInputDeviceHelper.clearFocus();
            super.onFocusChanged(z, i, rect);
            return;
        }
        FLog.w(TAG, "Unable to handle focus changed event as the catalyst instance has not been attached");
        super.onFocusChanged(z, i, rect);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        dispatchJSTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!this.mWasMeasured || getUIManagerType() != 2) {
            return;
        }
        updateRootLayoutSpecs(false, this.mWidthMeasureSpec, this.mHeightMeasureSpec);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0031 A[Catch: all -> 0x00ad, LOOP:1: B:16:0x002b->B:18:0x0031, LOOP_END, TryCatch #0 {all -> 0x00ad, blocks: (B:3:0x0007, B:5:0x000d, B:10:0x0015, B:14:0x0024, B:20:0x0050, B:24:0x0059, B:29:0x0083, B:31:0x008c, B:33:0x0090, B:40:0x00a5, B:35:0x0096, B:37:0x009a, B:39:0x009e, B:26:0x005f, B:28:0x0065, B:16:0x002b, B:18:0x0031), top: B:46:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0056 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0065 A[Catch: all -> 0x00ad, LOOP:0: B:26:0x005f->B:28:0x0065, LOOP_END, TryCatch #0 {all -> 0x00ad, blocks: (B:3:0x0007, B:5:0x000d, B:10:0x0015, B:14:0x0024, B:20:0x0050, B:24:0x0059, B:29:0x0083, B:31:0x008c, B:33:0x0090, B:40:0x00a5, B:35:0x0096, B:37:0x009a, B:39:0x009e, B:26:0x005f, B:28:0x0065, B:16:0x002b, B:18:0x0031), top: B:46:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008c A[Catch: all -> 0x00ad, TryCatch #0 {all -> 0x00ad, blocks: (B:3:0x0007, B:5:0x000d, B:10:0x0015, B:14:0x0024, B:20:0x0050, B:24:0x0059, B:29:0x0083, B:31:0x008c, B:33:0x0090, B:40:0x00a5, B:35:0x0096, B:37:0x009a, B:39:0x009e, B:26:0x005f, B:28:0x0065, B:16:0x002b, B:18:0x0031), top: B:46:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0096 A[Catch: all -> 0x00ad, TryCatch #0 {all -> 0x00ad, blocks: (B:3:0x0007, B:5:0x000d, B:10:0x0015, B:14:0x0024, B:20:0x0050, B:24:0x0059, B:29:0x0083, B:31:0x008c, B:33:0x0090, B:40:0x00a5, B:35:0x0096, B:37:0x009a, B:39:0x009e, B:26:0x005f, B:28:0x0065, B:16:0x002b, B:18:0x0031), top: B:46:0x0007 }] */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r11, int r12) {
        /*
            r10 = this;
            r0 = 0
            java.lang.String r2 = "ReactRootView.onMeasure"
            com.facebook.systrace.Systrace.beginSection(r0, r2)
            int r2 = r10.mWidthMeasureSpec     // Catch: java.lang.Throwable -> Lad
            r3 = 0
            r4 = 1
            if (r11 != r2) goto L14
            int r2 = r10.mHeightMeasureSpec     // Catch: java.lang.Throwable -> Lad
            if (r12 == r2) goto L12
            goto L14
        L12:
            r2 = r3
            goto L15
        L14:
            r2 = r4
        L15:
            r10.mWidthMeasureSpec = r11     // Catch: java.lang.Throwable -> Lad
            r10.mHeightMeasureSpec = r12     // Catch: java.lang.Throwable -> Lad
            int r5 = android.view.View.MeasureSpec.getMode(r11)     // Catch: java.lang.Throwable -> Lad
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r5 == r6) goto L29
            if (r5 != 0) goto L24
            goto L29
        L24:
            int r11 = android.view.View.MeasureSpec.getSize(r11)     // Catch: java.lang.Throwable -> Lad
            goto L50
        L29:
            r11 = r3
            r5 = r11
        L2b:
            int r7 = r10.getChildCount()     // Catch: java.lang.Throwable -> Lad
            if (r11 >= r7) goto L4f
            android.view.View r7 = r10.getChildAt(r11)     // Catch: java.lang.Throwable -> Lad
            int r8 = r7.getLeft()     // Catch: java.lang.Throwable -> Lad
            int r9 = r7.getMeasuredWidth()     // Catch: java.lang.Throwable -> Lad
            int r8 = r8 + r9
            int r9 = r7.getPaddingLeft()     // Catch: java.lang.Throwable -> Lad
            int r8 = r8 + r9
            int r7 = r7.getPaddingRight()     // Catch: java.lang.Throwable -> Lad
            int r8 = r8 + r7
            int r5 = java.lang.Math.max(r5, r8)     // Catch: java.lang.Throwable -> Lad
            int r11 = r11 + 1
            goto L2b
        L4f:
            r11 = r5
        L50:
            int r5 = android.view.View.MeasureSpec.getMode(r12)     // Catch: java.lang.Throwable -> Lad
            if (r5 == r6) goto L5e
            if (r5 != 0) goto L59
            goto L5e
        L59:
            int r12 = android.view.View.MeasureSpec.getSize(r12)     // Catch: java.lang.Throwable -> Lad
            goto L83
        L5e:
            r12 = r3
        L5f:
            int r5 = r10.getChildCount()     // Catch: java.lang.Throwable -> Lad
            if (r3 >= r5) goto L83
            android.view.View r5 = r10.getChildAt(r3)     // Catch: java.lang.Throwable -> Lad
            int r6 = r5.getTop()     // Catch: java.lang.Throwable -> Lad
            int r7 = r5.getMeasuredHeight()     // Catch: java.lang.Throwable -> Lad
            int r6 = r6 + r7
            int r7 = r5.getPaddingTop()     // Catch: java.lang.Throwable -> Lad
            int r6 = r6 + r7
            int r5 = r5.getPaddingBottom()     // Catch: java.lang.Throwable -> Lad
            int r6 = r6 + r5
            int r12 = java.lang.Math.max(r12, r6)     // Catch: java.lang.Throwable -> Lad
            int r3 = r3 + 1
            goto L5f
        L83:
            r10.setMeasuredDimension(r11, r12)     // Catch: java.lang.Throwable -> Lad
            r10.mWasMeasured = r4     // Catch: java.lang.Throwable -> Lad
            com.facebook.react.ReactInstanceManager r3 = r10.mReactInstanceManager     // Catch: java.lang.Throwable -> Lad
            if (r3 == 0) goto L94
            boolean r3 = r10.mIsAttachedToInstance     // Catch: java.lang.Throwable -> Lad
            if (r3 != 0) goto L94
            r10.attachToReactInstanceManager()     // Catch: java.lang.Throwable -> Lad
            goto La5
        L94:
            if (r2 != 0) goto L9e
            int r2 = r10.mLastWidth     // Catch: java.lang.Throwable -> Lad
            if (r2 != r11) goto L9e
            int r2 = r10.mLastHeight     // Catch: java.lang.Throwable -> Lad
            if (r2 == r12) goto La5
        L9e:
            int r2 = r10.mWidthMeasureSpec     // Catch: java.lang.Throwable -> Lad
            int r3 = r10.mHeightMeasureSpec     // Catch: java.lang.Throwable -> Lad
            r10.updateRootLayoutSpecs(r4, r2, r3)     // Catch: java.lang.Throwable -> Lad
        La5:
            r10.mLastWidth = r11     // Catch: java.lang.Throwable -> Lad
            r10.mLastHeight = r12     // Catch: java.lang.Throwable -> Lad
            com.facebook.systrace.Systrace.endSection(r0)
            return
        Lad:
            r11 = move-exception
            com.facebook.systrace.Systrace.endSection(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactRootView.onMeasure(int, int):void");
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void onStage(int i) {
        if (i != 101) {
            return;
        }
        onAttachedToReactInstance();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        dispatchJSTouchEvent(motionEvent);
        super.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (this.mShouldLogContentAppeared) {
            this.mShouldLogContentAppeared = false;
            String str = this.mJSModuleName;
            if (str == null) {
                return;
            }
            ReactMarker.logMarker(ReactMarkerConstants.CONTENT_APPEARED, str, this.mRootViewTag);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
            this.mAndroidHWInputDeviceHelper.onFocusChanged(view2);
            super.requestChildFocus(view, view2);
            return;
        }
        FLog.w(TAG, "Unable to handle child focus changed event as the catalyst instance has not been attached");
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void runApplication() {
        Systrace.beginSection(0L, "ReactRootView.runApplication");
        try {
            if (this.mReactInstanceManager != null && this.mIsAttachedToInstance) {
                ReactContext currentReactContext = this.mReactInstanceManager.getCurrentReactContext();
                if (currentReactContext == null) {
                    return;
                }
                CatalystInstance catalystInstance = currentReactContext.getCatalystInstance();
                String jSModuleName = getJSModuleName();
                if (this.mWasMeasured) {
                    updateRootLayoutSpecs(true, this.mWidthMeasureSpec, this.mHeightMeasureSpec);
                }
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putDouble("rootTag", getRootViewTag());
                Bundle appProperties = getAppProperties();
                if (appProperties != null) {
                    writableNativeMap.putMap("initialProps", Arguments.fromBundle(appProperties));
                }
                this.mShouldLogContentAppeared = true;
                ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).runApplication(jSModuleName, writableNativeMap);
            }
        } finally {
            Systrace.endSection(0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactInstanceManager.getCurrentReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void setAppProperties(@Nullable Bundle bundle) {
        UiThreadUtil.assertOnUiThread();
        this.mAppProperties = bundle;
        if (getRootViewTag() != 0) {
            runApplication();
        }
    }

    public void setEventListener(ReactRootViewEventListener reactRootViewEventListener) {
        this.mRootViewEventListener = reactRootViewEventListener;
    }

    public void setIsFabric(boolean z) {
        this.mUIManagerType = z ? 2 : 1;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void setRootViewTag(int i) {
        this.mRootViewTag = i;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void setShouldLogContentAppeared(boolean z) {
        this.mShouldLogContentAppeared = z;
    }

    @VisibleForTesting
    void simulateAttachForTesting() {
        this.mIsAttachedToInstance = true;
        this.mJSTouchDispatcher = new JSTouchDispatcher(this);
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str) {
        startReactApplication(reactInstanceManager, str, null);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void unmountReactApplication() {
        ReactContext currentReactContext;
        UIManager uIManager;
        UiThreadUtil.assertOnUiThread();
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && ReactFeatureFlags.enableStopSurfaceOnRootViewUnmount && (currentReactContext = reactInstanceManager.getCurrentReactContext()) != null && getUIManagerType() == 2 && (uIManager = UIManagerHelper.getUIManager(currentReactContext, getUIManagerType())) != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("stopSurface for surfaceId: ");
            outline107.append(getId());
            FLog.e(TAG, outline107.toString(), new RuntimeException("unmountReactApplication"));
            if (getId() == -1) {
                ReactSoftException.logSoftException(TAG, new RuntimeException("unmountReactApplication called on ReactRootView with invalid id"));
            } else {
                uIManager.stopSurface(getId());
            }
        }
        ReactInstanceManager reactInstanceManager2 = this.mReactInstanceManager;
        if (reactInstanceManager2 != null && this.mIsAttachedToInstance) {
            reactInstanceManager2.detachRootView(this);
            this.mIsAttachedToInstance = false;
        }
        this.mReactInstanceManager = null;
        this.mShouldLogContentAppeared = false;
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, @Nullable Bundle bundle) {
        startReactApplication(reactInstanceManager, str, bundle, null);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, @Nullable Bundle bundle, @Nullable String str2) {
        Systrace.beginSection(0L, "startReactApplication");
        try {
            UiThreadUtil.assertOnUiThread();
            Assertions.assertCondition(this.mReactInstanceManager == null, "This root view has already been attached to a catalyst instance manager");
            this.mReactInstanceManager = reactInstanceManager;
            this.mJSModuleName = str;
            this.mAppProperties = bundle;
            this.mInitialUITemplate = str2;
            this.mReactInstanceManager.createReactContextInBackground();
            attachToReactInstanceManager();
        } finally {
            Systrace.endSection(0L);
        }
    }

    public ReactRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAndroidHWInputDeviceHelper = new ReactAndroidHWInputDeviceHelper(this);
        this.mWasMeasured = false;
        this.mWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mLastWidth = 0;
        this.mLastHeight = 0;
        this.mLastOffsetX = Integer.MIN_VALUE;
        this.mLastOffsetY = Integer.MIN_VALUE;
        this.mUIManagerType = 1;
        init();
    }

    public ReactRootView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAndroidHWInputDeviceHelper = new ReactAndroidHWInputDeviceHelper(this);
        this.mWasMeasured = false;
        this.mWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mLastWidth = 0;
        this.mLastHeight = 0;
        this.mLastOffsetX = Integer.MIN_VALUE;
        this.mLastOffsetY = Integer.MIN_VALUE;
        this.mUIManagerType = 1;
        init();
    }
}
