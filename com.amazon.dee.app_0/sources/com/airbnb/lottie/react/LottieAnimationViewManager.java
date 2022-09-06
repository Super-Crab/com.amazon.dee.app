package com.airbnb.lottie.react;

import android.animation.Animator;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
class LottieAnimationViewManager extends SimpleViewManager<LottieAnimationView> {
    private static final int COMMAND_PLAY = 1;
    private static final int COMMAND_RESET = 2;
    private static final String REACT_CLASS = "LottieAnimationView";
    private static final String TAG = "LottieAnimationViewManager";
    private static final int VERSION = 1;
    private Map<LottieAnimationView, LottieAnimationViewPropertyManager> propManagersMap = new WeakHashMap();

    private LottieAnimationViewPropertyManager getOrCreatePropertyManager(LottieAnimationView lottieAnimationView) {
        LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager = this.propManagersMap.get(lottieAnimationView);
        if (lottieAnimationViewPropertyManager == null) {
            LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager2 = new LottieAnimationViewPropertyManager(lottieAnimationView);
            this.propManagersMap.put(lottieAnimationView, lottieAnimationViewPropertyManager2);
            return lottieAnimationViewPropertyManager2;
        }
        return lottieAnimationViewPropertyManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOnAnimationFinishEvent(LottieAnimationView lottieAnimationView, boolean z) {
        ReactContext reactContext;
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("isCancelled", z);
        Context context = lottieAnimationView.getContext();
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                reactContext = null;
                break;
            } else if (context instanceof ReactContext) {
                reactContext = (ReactContext) context;
                break;
            } else {
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        if (reactContext != null) {
            ((RCTEventEmitter) reactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(lottieAnimationView.getId(), "animationFinish", createMap);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(VoiceBridgePayloadUtil.PayloadCommand.PLAY, 1, "reset", 2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put("animationFinish", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onAnimationFinish"))).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.builder().put("VERSION", 1).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "colorFilters")
    public void setColorFilters(LottieAnimationView lottieAnimationView, ReadableArray readableArray) {
        getOrCreatePropertyManager(lottieAnimationView).setColorFilters(readableArray);
    }

    @ReactProp(name = "enableMergePathsAndroidForKitKatAndAbove")
    public void setEnableMergePaths(LottieAnimationView lottieAnimationView, boolean z) {
        getOrCreatePropertyManager(lottieAnimationView).setEnableMergePaths(z);
    }

    @ReactProp(name = "imageAssetsFolder")
    public void setImageAssetsFolder(LottieAnimationView lottieAnimationView, String str) {
        getOrCreatePropertyManager(lottieAnimationView).setImageAssetsFolder(str);
    }

    @ReactProp(name = "loop")
    public void setLoop(LottieAnimationView lottieAnimationView, boolean z) {
        getOrCreatePropertyManager(lottieAnimationView).setLoop(z);
    }

    @ReactProp(name = "progress")
    public void setProgress(LottieAnimationView lottieAnimationView, float f) {
        getOrCreatePropertyManager(lottieAnimationView).setProgress(Float.valueOf(f));
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(LottieAnimationView lottieAnimationView, String str) {
        ImageView.ScaleType scaleType;
        if ("cover".equals(str)) {
            scaleType = ImageView.ScaleType.CENTER_CROP;
        } else if ("contain".equals(str)) {
            scaleType = ImageView.ScaleType.CENTER_INSIDE;
        } else {
            scaleType = "center".equals(str) ? ImageView.ScaleType.CENTER : null;
        }
        getOrCreatePropertyManager(lottieAnimationView).setScaleType(scaleType);
    }

    @ReactProp(name = "sourceJson")
    public void setSourceJson(LottieAnimationView lottieAnimationView, String str) {
        getOrCreatePropertyManager(lottieAnimationView).setAnimationJson(str);
    }

    @ReactProp(name = "sourceName")
    public void setSourceName(LottieAnimationView lottieAnimationView, String str) {
        if (!str.contains(".")) {
            str = GeneratedOutlineSupport1.outline72(str, ".json");
        }
        getOrCreatePropertyManager(lottieAnimationView).setAnimationName(str);
    }

    @ReactProp(name = "speed")
    public void setSpeed(LottieAnimationView lottieAnimationView, double d) {
        getOrCreatePropertyManager(lottieAnimationView).setSpeed((float) d);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public LottieAnimationView mo12880createViewInstance(ThemedReactContext themedReactContext) {
        final LottieAnimationView lottieAnimationView = new LottieAnimationView(themedReactContext);
        lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() { // from class: com.airbnb.lottie.react.LottieAnimationViewManager.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                LottieAnimationViewManager.this.sendOnAnimationFinishEvent(lottieAnimationView, true);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LottieAnimationViewManager.this.sendOnAnimationFinishEvent(lottieAnimationView, false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        return lottieAnimationView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(LottieAnimationView lottieAnimationView) {
        super.onAfterUpdateTransaction((LottieAnimationViewManager) lottieAnimationView);
        getOrCreatePropertyManager(lottieAnimationView).commitChanges();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(final LottieAnimationView lottieAnimationView, int i, final ReadableArray readableArray) {
        if (i == 1) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.lottie.react.LottieAnimationViewManager.2
                @Override // java.lang.Runnable
                public void run() {
                    int i2 = readableArray.getInt(0);
                    int i3 = readableArray.getInt(1);
                    if (i2 != -1 && i3 != -1) {
                        if (i2 > i3) {
                            lottieAnimationView.setMinAndMaxFrame(i3, i2);
                            lottieAnimationView.reverseAnimationSpeed();
                        } else {
                            lottieAnimationView.setMinAndMaxFrame(i2, i3);
                        }
                    }
                    if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
                        lottieAnimationView.setProgress(0.0f);
                        lottieAnimationView.playAnimation();
                        return;
                    }
                    lottieAnimationView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.airbnb.lottie.react.LottieAnimationViewManager.2.1
                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewAttachedToWindow(View view) {
                            LottieAnimationView lottieAnimationView2 = (LottieAnimationView) view;
                            lottieAnimationView2.setProgress(0.0f);
                            lottieAnimationView2.playAnimation();
                            lottieAnimationView2.removeOnAttachStateChangeListener(this);
                        }

                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewDetachedFromWindow(View view) {
                            lottieAnimationView.removeOnAttachStateChangeListener(this);
                        }
                    });
                }
            });
        } else if (i != 2) {
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.lottie.react.LottieAnimationViewManager.3
                @Override // java.lang.Runnable
                public void run() {
                    if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
                        lottieAnimationView.cancelAnimation();
                        lottieAnimationView.setProgress(0.0f);
                    }
                }
            });
        }
    }
}
