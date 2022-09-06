package com.reactnativecommunity.slider;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.View;
import android.widget.SeekBar;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
import com.reactnativecommunity.slider.ReactSliderManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public class ReactSliderManager extends SimpleViewManager<ReactSlider> {
    private static final SeekBar.OnSeekBarChangeListener ON_CHANGE_LISTENER = new AnonymousClass1();
    public static final String REACT_CLASS = "RNCSlider";
    private static final int STYLE = 16842875;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.reactnativecommunity.slider.ReactSliderManager$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static class AnonymousClass1 implements SeekBar.OnSeekBarChangeListener {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onStopTrackingTouch$0(SeekBar seekBar) {
            ((ReactSlider) seekBar).setDragInProgress(false);
            seekBar.postInvalidate();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            ((UIManagerModule) ((ReactContext) seekBar.getContext()).getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher().dispatchEvent(new ReactSliderEvent(seekBar.getId(), ((ReactSlider) seekBar).toRealProgress(i), z));
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            ReactSlider reactSlider = (ReactSlider) seekBar;
            ((UIManagerModule) ((ReactContext) seekBar.getContext()).getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher().dispatchEvent(new ReactSlidingStartEvent(seekBar.getId(), reactSlider.toRealProgress(seekBar.getProgress())));
            reactSlider.setDragInProgress(true);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(final SeekBar seekBar) {
            ((UIManagerModule) ((ReactContext) seekBar.getContext()).getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher().dispatchEvent(new ReactSlidingCompleteEvent(seekBar.getId(), ((ReactSlider) seekBar).toRealProgress(seekBar.getProgress())));
            this.scheduler.schedule(new Runnable() { // from class: com.reactnativecommunity.slider.-$$Lambda$ReactSliderManager$1$UT9Y5Cac6wYHKmK78Ely472wU9A
                @Override // java.lang.Runnable
                public final void run() {
                    ReactSliderManager.AnonymousClass1.lambda$onStopTrackingTouch$0(seekBar);
                }
            }, 1500L, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ReactSliderShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
        private int mHeight;
        private boolean mMeasured;
        private int mWidth;

        /* synthetic */ ReactSliderShadowNode(AnonymousClass1 anonymousClass1) {
            this();
        }

        private void initMeasureFunction() {
            setMeasureFunction(this);
        }

        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            if (!this.mMeasured) {
                ReactSlider reactSlider = new ReactSlider(getThemedContext(), null, ReactSliderManager.STYLE);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
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

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("topSlidingComplete", MapBuilder.of("registrationName", "onRNCSliderSlidingComplete"), ReactSlidingStartEvent.EVENT_NAME, MapBuilder.of("registrationName", "onRNCSliderSlidingStart"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.SimpleViewManager, com.facebook.react.uimanager.ViewManager
    public Class getShadowNodeClass() {
        return ReactSliderShadowNode.class;
    }

    @ReactProp(name = "accessibilityIncrements")
    public void setAccessibilityIncrements(ReactSlider reactSlider, ReadableArray readableArray) {
        ArrayList<Object> arrayList = readableArray.toArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<Object> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add((String) it2.next());
        }
        reactSlider.setAccessibilityIncrements(arrayList2);
    }

    @ReactProp(name = "accessibilityUnits")
    public void setAccessibilityUnits(ReactSlider reactSlider, String str) {
        reactSlider.setAccessibilityUnits(str);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSlider reactSlider, boolean z) {
        reactSlider.setEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "inverted")
    public void setInverted(ReactSlider reactSlider, boolean z) {
        if (z) {
            reactSlider.setScaleX(-1.0f);
        } else {
            reactSlider.setScaleX(1.0f);
        }
    }

    @ReactProp(customType = "Color", name = "labelColor")
    public void setLabelColor(ReactSlider reactSlider, @Nullable Integer num) {
        if (num == null) {
            reactSlider.setLabelColor(-1);
        } else {
            reactSlider.setLabelColor(num);
        }
    }

    @ReactProp(name = "labelType")
    public void setLabelType(ReactSlider reactSlider, @Nullable String str) {
        if (str == null) {
            return;
        }
        reactSlider.setLabelType(str);
    }

    @ReactProp(customType = "Color", name = "maximumTrackTintColor")
    public void setMaximumTrackTintColor(ReactSlider reactSlider, Integer num) {
        Drawable findDrawableByLayerId = ((LayerDrawable) reactSlider.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908288);
        if (num == null) {
            findDrawableByLayerId.clearColorFilter();
        } else {
            findDrawableByLayerId.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @ReactProp(defaultDouble = 1.0d, name = "maximumValue")
    public void setMaximumValue(ReactSlider reactSlider, double d) {
        reactSlider.setMaxValue(d);
    }

    @ReactProp(customType = "Color", name = "minimumTrackTintColor")
    public void setMinimumTrackTintColor(ReactSlider reactSlider, Integer num) {
        Drawable findDrawableByLayerId = ((LayerDrawable) reactSlider.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908301);
        if (num == null) {
            findDrawableByLayerId.clearColorFilter();
        } else {
            findDrawableByLayerId.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @ReactProp(defaultDouble = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, name = "minimumValue")
    public void setMinimumValue(ReactSlider reactSlider, double d) {
        reactSlider.setMinValue(d);
    }

    @ReactProp(defaultDouble = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, name = "step")
    public void setStep(ReactSlider reactSlider, double d) {
        reactSlider.setStep(d);
    }

    @ReactProp(name = "thumbImage")
    public void setThumbImage(ReactSlider reactSlider, @Nullable ReadableMap readableMap) {
        reactSlider.setThumbImage(readableMap != null ? readableMap.getString("uri") : null);
    }

    @ReactProp(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(ReactSlider reactSlider, Integer num) {
        if (reactSlider.getThumb() != null) {
            if (num == null) {
                reactSlider.getThumb().clearColorFilter();
            } else {
                reactSlider.getThumb().setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    @ReactProp(defaultDouble = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, name = "value")
    public void setValue(ReactSlider reactSlider, double d) {
        reactSlider.setOnSeekBarChangeListener(null);
        reactSlider.setValue(d);
        reactSlider.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactSlider reactSlider) {
        reactSlider.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    @Override // com.facebook.react.uimanager.SimpleViewManager, com.facebook.react.uimanager.ViewManager
    /* renamed from: createShadowNodeInstance */
    public LayoutShadowNode mo10136createShadowNodeInstance() {
        return new ReactSliderShadowNode(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactSlider mo12880createViewInstance(ThemedReactContext themedReactContext) {
        ReactSlider reactSlider = new ReactSlider(themedReactContext, null, STYLE);
        int i = Build.VERSION.SDK_INT;
        reactSlider.setSplitTrack(false);
        return reactSlider;
    }
}
