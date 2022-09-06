package com.facebook.react.views.slider;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.SliderManagerDelegate;
import com.facebook.react.viewmanagers.SliderManagerInterface;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
import java.util.Map;
/* loaded from: classes2.dex */
public class ReactSliderManager extends SimpleViewManager<ReactSlider> implements SliderManagerInterface<ReactSlider> {
    public static final String REACT_CLASS = "RCTSlider";
    private static final int STYLE = 16842875;
    private final ViewManagerDelegate<ReactSlider> mDelegate = new SliderManagerDelegate(this);
    private static final SeekBar.OnSeekBarChangeListener ON_CHANGE_LISTENER = new SeekBar.OnSeekBarChangeListener() { // from class: com.facebook.react.views.slider.ReactSliderManager.1
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            UIManagerModule uIManagerModule = (UIManagerModule) ((ReactContext) seekBar.getContext()).getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.mo6949getEventDispatcher().dispatchEvent(new ReactSliderEvent(seekBar.getId(), ((ReactSlider) seekBar).toRealProgress(i), z));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            UIManagerModule uIManagerModule = (UIManagerModule) ((ReactContext) seekBar.getContext()).getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.mo6949getEventDispatcher().dispatchEvent(new ReactSlidingCompleteEvent(seekBar.getId(), ((ReactSlider) seekBar).toRealProgress(seekBar.getProgress())));
            }
        }
    };
    protected static ReactSliderAccessibilityDelegate sAccessibilityDelegate = new ReactSliderAccessibilityDelegate();

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static class ReactSliderAccessibilityDelegate extends AccessibilityDelegateCompat {
        protected ReactSliderAccessibilityDelegate() {
        }

        private static boolean isSliderAction(int i) {
            return i == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId() || i == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId() || i == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SET_PROGRESS.getId();
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (isSliderAction(i)) {
                ReactSliderManager.ON_CHANGE_LISTENER.onStartTrackingTouch((SeekBar) view);
            }
            boolean performAccessibilityAction = super.performAccessibilityAction(view, i, bundle);
            if (isSliderAction(i)) {
                ReactSliderManager.ON_CHANGE_LISTENER.onStopTrackingTouch((SeekBar) view);
            }
            return performAccessibilityAction;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class ReactSliderShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
        private int mHeight;
        private boolean mMeasured;
        private int mWidth;

        private void initMeasureFunction() {
            setMeasureFunction(this);
        }

        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            if (!this.mMeasured) {
                ReactSlider reactSlider = new ReactSlider(getThemedContext(), null, ReactSliderManager.STYLE);
                reactSlider.disableStateListAnimatorIfNeeded();
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(-2, 0);
                reactSlider.measure(makeMeasureSpec, makeMeasureSpec);
                this.mWidth = reactSlider.getMeasuredWidth();
                this.mHeight = reactSlider.getMeasuredHeight();
                this.mMeasured = true;
            }
            return YogaMeasureOutput.make(this.mWidth, this.mHeight);
        }

        private ReactSliderShadowNode() {
            initMeasureFunction();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate<ReactSlider> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("topSlidingComplete", MapBuilder.of("registrationName", "onSlidingComplete"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.SimpleViewManager, com.facebook.react.uimanager.ViewManager
    public Class getShadowNodeClass() {
        return ReactSliderShadowNode.class;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, @Nullable float[] fArr) {
        ReactSlider reactSlider = new ReactSlider(context, null, STYLE);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(-2, 0);
        reactSlider.measure(makeMeasureSpec, makeMeasureSpec);
        return YogaMeasureOutput.make(PixelUtil.toDIPFromPixel(reactSlider.getMeasuredWidth()), PixelUtil.toDIPFromPixel(reactSlider.getMeasuredHeight()));
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    public void setDisabled(ReactSlider reactSlider, boolean z) {
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    public void setMaximumTrackImage(ReactSlider reactSlider, @Nullable ReadableMap readableMap) {
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    public void setMinimumTrackImage(ReactSlider reactSlider, @Nullable ReadableMap readableMap) {
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    public void setThumbImage(ReactSlider reactSlider, @Nullable ReadableMap readableMap) {
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    public void setTrackImage(ReactSlider reactSlider, @Nullable ReadableMap readableMap) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactSlider reactSlider) {
        reactSlider.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    @Override // com.facebook.react.uimanager.SimpleViewManager, com.facebook.react.uimanager.ViewManager
    /* renamed from: createShadowNodeInstance */
    public LayoutShadowNode mo10136createShadowNodeInstance() {
        return new ReactSliderShadowNode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactSlider mo12880createViewInstance(ThemedReactContext themedReactContext) {
        ReactSlider reactSlider = new ReactSlider(themedReactContext, null, STYLE);
        ViewCompat.setAccessibilityDelegate(reactSlider, sAccessibilityDelegate);
        return reactSlider;
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSlider reactSlider, boolean z) {
        reactSlider.setEnabled(z);
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(customType = "Color", name = "maximumTrackTintColor")
    public void setMaximumTrackTintColor(ReactSlider reactSlider, Integer num) {
        Drawable findDrawableByLayerId = ((LayerDrawable) reactSlider.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908288);
        if (num == null) {
            findDrawableByLayerId.clearColorFilter();
        } else {
            findDrawableByLayerId.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(defaultDouble = 1.0d, name = "maximumValue")
    public void setMaximumValue(ReactSlider reactSlider, double d) {
        reactSlider.setMaxValue(d);
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(customType = "Color", name = "minimumTrackTintColor")
    public void setMinimumTrackTintColor(ReactSlider reactSlider, Integer num) {
        Drawable findDrawableByLayerId = ((LayerDrawable) reactSlider.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908301);
        if (num == null) {
            findDrawableByLayerId.clearColorFilter();
        } else {
            findDrawableByLayerId.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(defaultDouble = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, name = "minimumValue")
    public void setMinimumValue(ReactSlider reactSlider, double d) {
        reactSlider.setMinValue(d);
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(defaultDouble = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, name = "step")
    public void setStep(ReactSlider reactSlider, double d) {
        reactSlider.setStep(d);
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    public void setTestID(ReactSlider reactSlider, @Nullable String str) {
        super.setTestId(reactSlider, str);
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(ReactSlider reactSlider, Integer num) {
        if (num == null) {
            reactSlider.getThumb().clearColorFilter();
        } else {
            reactSlider.getThumb().setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(defaultDouble = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, name = "value")
    public void setValue(ReactSlider reactSlider, double d) {
        reactSlider.setOnSeekBarChangeListener(null);
        reactSlider.setValue(d);
        reactSlider.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }
}
