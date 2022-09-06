package com.swmansion.gesturehandler.react;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.exoplayer2.offline.DownloadService;
/* loaded from: classes3.dex */
public class RNGestureHandlerButtonViewManager extends ViewGroupManager<ButtonViewGroup> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ButtonViewGroup extends ViewGroup {
        public static final String SELECTABLE_ITEM_BACKGROUND = "selectableItemBackground";
        public static final String SELECTABLE_ITEM_BACKGROUND_BORDERLESS = "selectableItemBackgroundBorderless";
        static ButtonViewGroup sResponder;
        int mBackgroundColor;
        float mBorderRadius;
        long mLastEventTime;
        boolean mNeedBackgroundUpdate;
        Integer mRippleColor;
        Integer mRippleRadius;
        boolean mUseBorderless;
        boolean mUseForeground;
        static TypedValue sResolveOutValue = new TypedValue();
        static View.OnClickListener sDummyClickListener = new View.OnClickListener() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        };

        public ButtonViewGroup(Context context) {
            super(context);
            this.mBackgroundColor = 0;
            this.mUseForeground = false;
            this.mUseBorderless = false;
            this.mBorderRadius = 0.0f;
            this.mNeedBackgroundUpdate = false;
            this.mLastEventTime = 0L;
            setOnClickListener(sDummyClickListener);
            setClickable(true);
            setFocusable(true);
            this.mNeedBackgroundUpdate = true;
        }

        private Drawable applyRippleEffectWhenNeeded(Drawable drawable) {
            Integer num = this.mRippleColor;
            if (num != null) {
                int i = Build.VERSION.SDK_INT;
                if (drawable instanceof RippleDrawable) {
                    ((RippleDrawable) drawable).setColor(new ColorStateList(new int[][]{new int[]{16842910}}, new int[]{num.intValue()}));
                }
            }
            int i2 = Build.VERSION.SDK_INT;
            Integer num2 = this.mRippleRadius;
            if (num2 != null && (drawable instanceof RippleDrawable)) {
                ((RippleDrawable) drawable).setRadius((int) PixelUtil.toPixelFromDIP(num2.intValue()));
            }
            return drawable;
        }

        private Drawable createSelectableDrawable() {
            int i = Build.VERSION.SDK_INT;
            getContext().getTheme().resolveAttribute(getAttrId(getContext(), this.mUseBorderless ? SELECTABLE_ITEM_BACKGROUND_BORDERLESS : SELECTABLE_ITEM_BACKGROUND), sResolveOutValue, true);
            return getResources().getDrawable(sResolveOutValue.resourceId, getContext().getTheme());
        }

        @TargetApi(21)
        private static int getAttrId(Context context, String str) {
            SoftAssertions.assertNotNull(str);
            if (SELECTABLE_ITEM_BACKGROUND.equals(str)) {
                return 16843534;
            }
            if (!SELECTABLE_ITEM_BACKGROUND_BORDERLESS.equals(str)) {
                return context.getResources().getIdentifier(str, "attr", "android");
            }
            return 16843868;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateBackground() {
            if (!this.mNeedBackgroundUpdate) {
                return;
            }
            this.mNeedBackgroundUpdate = false;
            if (this.mBackgroundColor == 0) {
                setBackground(null);
            }
            int i = Build.VERSION.SDK_INT;
            setForeground(null);
            if (this.mUseForeground) {
                int i2 = Build.VERSION.SDK_INT;
                setForeground(applyRippleEffectWhenNeeded(createSelectableDrawable()));
                int i3 = this.mBackgroundColor;
                if (i3 == 0) {
                    return;
                }
                setBackgroundColor(i3);
            } else if (this.mBackgroundColor == 0 && this.mRippleColor == null) {
                setBackground(createSelectableDrawable());
            } else {
                PaintDrawable paintDrawable = new PaintDrawable(this.mBackgroundColor);
                Drawable createSelectableDrawable = createSelectableDrawable();
                float f = this.mBorderRadius;
                if (f != 0.0f) {
                    paintDrawable.setCornerRadius(f);
                    int i4 = Build.VERSION.SDK_INT;
                    if (createSelectableDrawable instanceof RippleDrawable) {
                        PaintDrawable paintDrawable2 = new PaintDrawable(-1);
                        paintDrawable2.setCornerRadius(this.mBorderRadius);
                        ((RippleDrawable) createSelectableDrawable).setDrawableByLayerId(16908334, paintDrawable2);
                    }
                }
                applyRippleEffectWhenNeeded(createSelectableDrawable);
                setBackground(new LayerDrawable(new Drawable[]{paintDrawable, createSelectableDrawable}));
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDrawableHotspotChanged(float f, float f2) {
        }

        @Override // android.view.View
        public void drawableHotspotChanged(float f, float f2) {
            ButtonViewGroup buttonViewGroup = sResponder;
            if (buttonViewGroup == null || buttonViewGroup == this) {
                super.drawableHotspotChanged(f, f2);
            }
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (super.onInterceptTouchEvent(motionEvent)) {
                return true;
            }
            onTouchEvent(motionEvent);
            return isPressed();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent motionEvent) {
            long eventTime = motionEvent.getEventTime();
            long j = this.mLastEventTime;
            if (j != eventTime || j == 0) {
                this.mLastEventTime = eventTime;
                return super.onTouchEvent(motionEvent);
            }
            return false;
        }

        @Override // android.view.View
        public void setBackgroundColor(int i) {
            this.mBackgroundColor = i;
            this.mNeedBackgroundUpdate = true;
        }

        public void setBorderRadius(float f) {
            this.mBorderRadius = f * getResources().getDisplayMetrics().density;
            this.mNeedBackgroundUpdate = true;
        }

        @Override // android.view.View
        public void setPressed(boolean z) {
            if (z && sResponder == null) {
                sResponder = this;
            }
            if (!z || sResponder == this) {
                super.setPressed(z);
            }
            if (z || sResponder != this) {
                return;
            }
            sResponder = null;
        }

        public void setRippleColor(Integer num) {
            this.mRippleColor = num;
            this.mNeedBackgroundUpdate = true;
        }

        public void setRippleRadius(Integer num) {
            this.mRippleRadius = num;
            this.mNeedBackgroundUpdate = true;
        }

        public void setUseBorderlessDrawable(boolean z) {
            this.mUseBorderless = z;
        }

        public void setUseDrawableOnForeground(boolean z) {
            this.mUseForeground = z;
            this.mNeedBackgroundUpdate = true;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNGestureHandlerButton";
    }

    @ReactProp(name = "borderless")
    public void setBorderless(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.setUseBorderlessDrawable(z);
    }

    @ReactProp(name = "enabled")
    public void setEnabled(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.setEnabled(z);
    }

    @ReactProp(name = DownloadService.KEY_FOREGROUND)
    @TargetApi(23)
    public void setForeground(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.setUseDrawableOnForeground(z);
    }

    @ReactProp(name = "rippleColor")
    public void setRippleColor(ButtonViewGroup buttonViewGroup, Integer num) {
        buttonViewGroup.setRippleColor(num);
    }

    @ReactProp(name = "rippleRadius")
    public void setRippleRadius(ButtonViewGroup buttonViewGroup, Integer num) {
        buttonViewGroup.setRippleRadius(num);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ButtonViewGroup mo12880createViewInstance(ThemedReactContext themedReactContext) {
        return new ButtonViewGroup(themedReactContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ButtonViewGroup buttonViewGroup) {
        buttonViewGroup.updateBackground();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.BORDER_RADIUS)
    public void setBorderRadius(ButtonViewGroup buttonViewGroup, float f) {
        buttonViewGroup.setBorderRadius(f);
    }
}
