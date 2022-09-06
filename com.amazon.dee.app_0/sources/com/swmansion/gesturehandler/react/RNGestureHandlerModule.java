package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.gesturehandler.FlingGestureHandler;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.LongPressGestureHandler;
import com.swmansion.gesturehandler.NativeViewGestureHandler;
import com.swmansion.gesturehandler.OnTouchEventListener;
import com.swmansion.gesturehandler.PanGestureHandler;
import com.swmansion.gesturehandler.PinchGestureHandler;
import com.swmansion.gesturehandler.RotationGestureHandler;
import com.swmansion.gesturehandler.TapGestureHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@ReactModule(name = RNGestureHandlerModule.MODULE_NAME)
/* loaded from: classes3.dex */
public class RNGestureHandlerModule extends ReactContextBaseJavaModule {
    private static final String KEY_DIRECTION = "direction";
    private static final String KEY_ENABLED = "enabled";
    private static final String KEY_HIT_SLOP = "hitSlop";
    private static final String KEY_HIT_SLOP_BOTTOM = "bottom";
    private static final String KEY_HIT_SLOP_HEIGHT = "height";
    private static final String KEY_HIT_SLOP_HORIZONTAL = "horizontal";
    private static final String KEY_HIT_SLOP_LEFT = "left";
    private static final String KEY_HIT_SLOP_RIGHT = "right";
    private static final String KEY_HIT_SLOP_TOP = "top";
    private static final String KEY_HIT_SLOP_VERTICAL = "vertical";
    private static final String KEY_HIT_SLOP_WIDTH = "width";
    private static final String KEY_LONG_PRESS_MAX_DIST = "maxDist";
    private static final String KEY_LONG_PRESS_MIN_DURATION_MS = "minDurationMs";
    private static final String KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION = "disallowInterruption";
    private static final String KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START = "shouldActivateOnStart";
    private static final String KEY_NUMBER_OF_POINTERS = "numberOfPointers";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_END = "activeOffsetXEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_START = "activeOffsetXStart";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_END = "activeOffsetYEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_START = "activeOffsetYStart";
    private static final String KEY_PAN_AVG_TOUCHES = "avgTouches";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_END = "failOffsetXEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_START = "failOffsetXStart";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_END = "failOffsetYEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_START = "failOffsetYStart";
    private static final String KEY_PAN_MAX_POINTERS = "maxPointers";
    private static final String KEY_PAN_MIN_DIST = "minDist";
    private static final String KEY_PAN_MIN_POINTERS = "minPointers";
    private static final String KEY_PAN_MIN_VELOCITY = "minVelocity";
    private static final String KEY_PAN_MIN_VELOCITY_X = "minVelocityX";
    private static final String KEY_PAN_MIN_VELOCITY_Y = "minVelocityY";
    private static final String KEY_SHOULD_CANCEL_WHEN_OUTSIDE = "shouldCancelWhenOutside";
    private static final String KEY_TAP_MAX_DELAY_MS = "maxDelayMs";
    private static final String KEY_TAP_MAX_DELTA_X = "maxDeltaX";
    private static final String KEY_TAP_MAX_DELTA_Y = "maxDeltaY";
    private static final String KEY_TAP_MAX_DIST = "maxDist";
    private static final String KEY_TAP_MAX_DURATION_MS = "maxDurationMs";
    private static final String KEY_TAP_MIN_POINTERS = "minPointers";
    private static final String KEY_TAP_NUMBER_OF_TAPS = "numberOfTaps";
    public static final String MODULE_NAME = "RNGestureHandlerModule";
    private List<Integer> mEnqueuedRootViewInit;
    private OnTouchEventListener mEventListener;
    private HandlerFactory[] mHandlerFactories;
    private RNGestureHandlerInteractionManager mInteractionManager;
    private final RNGestureHandlerRegistry mRegistry;
    private List<RNGestureHandlerRootHelper> mRoots;

    /* loaded from: classes3.dex */
    private static class FlingGestureHandlerFactory extends HandlerFactory<FlingGestureHandler> {
        private FlingGestureHandlerFactory() {
            super();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "FlingGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<FlingGestureHandler> getType() {
            return FlingGestureHandler.class;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(FlingGestureHandler flingGestureHandler, ReadableMap readableMap) {
            super.configure((FlingGestureHandlerFactory) flingGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS)) {
                flingGestureHandler.setNumberOfPointersRequired(readableMap.getInt(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS));
            }
            if (readableMap.hasKey("direction")) {
                flingGestureHandler.setDirection(readableMap.getInt("direction"));
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        /* renamed from: create */
        public FlingGestureHandler mo10148create(Context context) {
            return new FlingGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(FlingGestureHandler flingGestureHandler, WritableMap writableMap) {
            super.extractEventData((FlingGestureHandlerFactory) flingGestureHandler, writableMap);
            writableMap.putDouble(ReactProperties.HereMapMarker.X, PixelUtil.toDIPFromPixel(flingGestureHandler.getLastRelativePositionX()));
            writableMap.putDouble(ReactProperties.HereMapMarker.Y, PixelUtil.toDIPFromPixel(flingGestureHandler.getLastRelativePositionY()));
            writableMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(flingGestureHandler.getLastAbsolutePositionX()));
            writableMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(flingGestureHandler.getLastAbsolutePositionY()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class HandlerFactory<T extends GestureHandler> implements RNGestureHandlerEventDataExtractor<T> {
        private HandlerFactory() {
        }

        public void configure(T t, ReadableMap readableMap) {
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE)) {
                t.setShouldCancelWhenOutside(readableMap.getBoolean(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE));
            }
            if (readableMap.hasKey("enabled")) {
                t.setEnabled(readableMap.getBoolean("enabled"));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP)) {
                RNGestureHandlerModule.handleHitSlopProperty(t, readableMap);
            }
        }

        /* renamed from: create */
        public abstract T mo10148create(Context context);

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(T t, WritableMap writableMap) {
            writableMap.putDouble(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS, t.getNumberOfPointers());
        }

        public abstract String getName();

        public abstract Class<T> getType();
    }

    /* loaded from: classes3.dex */
    private static class LongPressGestureHandlerFactory extends HandlerFactory<LongPressGestureHandler> {
        private LongPressGestureHandlerFactory() {
            super();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "LongPressGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<LongPressGestureHandler> getType() {
            return LongPressGestureHandler.class;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(LongPressGestureHandler longPressGestureHandler, ReadableMap readableMap) {
            super.configure((LongPressGestureHandlerFactory) longPressGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS)) {
                longPressGestureHandler.setMinDurationMs(readableMap.getInt(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS));
            }
            if (readableMap.hasKey("maxDist")) {
                longPressGestureHandler.setMaxDist(PixelUtil.toPixelFromDIP(readableMap.getDouble("maxDist")));
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        /* renamed from: create  reason: collision with other method in class */
        public LongPressGestureHandler mo10148create(Context context) {
            return new LongPressGestureHandler(context);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(LongPressGestureHandler longPressGestureHandler, WritableMap writableMap) {
            super.extractEventData((LongPressGestureHandlerFactory) longPressGestureHandler, writableMap);
            writableMap.putDouble(ReactProperties.HereMapMarker.X, PixelUtil.toDIPFromPixel(longPressGestureHandler.getLastRelativePositionX()));
            writableMap.putDouble(ReactProperties.HereMapMarker.Y, PixelUtil.toDIPFromPixel(longPressGestureHandler.getLastRelativePositionY()));
            writableMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(longPressGestureHandler.getLastAbsolutePositionX()));
            writableMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(longPressGestureHandler.getLastAbsolutePositionY()));
        }
    }

    /* loaded from: classes3.dex */
    private static class NativeViewGestureHandlerFactory extends HandlerFactory<NativeViewGestureHandler> {
        private NativeViewGestureHandlerFactory() {
            super();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "NativeViewGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<NativeViewGestureHandler> getType() {
            return NativeViewGestureHandler.class;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(NativeViewGestureHandler nativeViewGestureHandler, ReadableMap readableMap) {
            super.configure((NativeViewGestureHandlerFactory) nativeViewGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START)) {
                nativeViewGestureHandler.setShouldActivateOnStart(readableMap.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION)) {
                nativeViewGestureHandler.setDisallowInterruption(readableMap.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION));
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        /* renamed from: create  reason: collision with other method in class */
        public NativeViewGestureHandler mo10148create(Context context) {
            return new NativeViewGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(NativeViewGestureHandler nativeViewGestureHandler, WritableMap writableMap) {
            super.extractEventData((NativeViewGestureHandlerFactory) nativeViewGestureHandler, writableMap);
            writableMap.putBoolean("pointerInside", nativeViewGestureHandler.isWithinBounds());
        }
    }

    /* loaded from: classes3.dex */
    private static class PanGestureHandlerFactory extends HandlerFactory<PanGestureHandler> {
        private PanGestureHandlerFactory() {
            super();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "PanGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<PanGestureHandler> getType() {
            return PanGestureHandler.class;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(PanGestureHandler panGestureHandler, ReadableMap readableMap) {
            boolean z;
            super.configure((PanGestureHandlerFactory) panGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)) {
                panGestureHandler.setActiveOffsetXStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)));
                z = true;
            } else {
                z = false;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)) {
                panGestureHandler.setActiveOffsetXEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)) {
                panGestureHandler.setFailOffsetXStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)) {
                panGestureHandler.setFailOffsetXEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)) {
                panGestureHandler.setActiveOffsetYStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)) {
                panGestureHandler.setActiveOffsetYEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)) {
                panGestureHandler.setFailOffsetYStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)) {
                panGestureHandler.setFailOffsetYEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)) {
                panGestureHandler.setMinVelocity(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)) {
                panGestureHandler.setMinVelocityX(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)) {
                panGestureHandler.setMinVelocityY(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_DIST)) {
                panGestureHandler.setMinDist(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_DIST)));
            } else if (z) {
                panGestureHandler.setMinDist(Float.MAX_VALUE);
            }
            if (readableMap.hasKey("minPointers")) {
                panGestureHandler.setMinPointers(readableMap.getInt("minPointers"));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS)) {
                panGestureHandler.setMaxPointers(readableMap.getInt(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES)) {
                panGestureHandler.setAverageTouches(readableMap.getBoolean(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES));
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        /* renamed from: create  reason: collision with other method in class */
        public PanGestureHandler mo10148create(Context context) {
            return new PanGestureHandler(context);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(PanGestureHandler panGestureHandler, WritableMap writableMap) {
            super.extractEventData((PanGestureHandlerFactory) panGestureHandler, writableMap);
            writableMap.putDouble(ReactProperties.HereMapMarker.X, PixelUtil.toDIPFromPixel(panGestureHandler.getLastRelativePositionX()));
            writableMap.putDouble(ReactProperties.HereMapMarker.Y, PixelUtil.toDIPFromPixel(panGestureHandler.getLastRelativePositionY()));
            writableMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(panGestureHandler.getLastAbsolutePositionX()));
            writableMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(panGestureHandler.getLastAbsolutePositionY()));
            writableMap.putDouble("translationX", PixelUtil.toDIPFromPixel(panGestureHandler.getTranslationX()));
            writableMap.putDouble("translationY", PixelUtil.toDIPFromPixel(panGestureHandler.getTranslationY()));
            writableMap.putDouble("velocityX", PixelUtil.toDIPFromPixel(panGestureHandler.getVelocityX()));
            writableMap.putDouble("velocityY", PixelUtil.toDIPFromPixel(panGestureHandler.getVelocityY()));
        }
    }

    /* loaded from: classes3.dex */
    private static class PinchGestureHandlerFactory extends HandlerFactory<PinchGestureHandler> {
        private PinchGestureHandlerFactory() {
            super();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "PinchGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<PinchGestureHandler> getType() {
            return PinchGestureHandler.class;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        /* renamed from: create  reason: collision with other method in class */
        public PinchGestureHandler mo10148create(Context context) {
            return new PinchGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(PinchGestureHandler pinchGestureHandler, WritableMap writableMap) {
            super.extractEventData((PinchGestureHandlerFactory) pinchGestureHandler, writableMap);
            writableMap.putDouble(ModelTransformer.KEY_BATTERY_SCALE, pinchGestureHandler.getScale());
            writableMap.putDouble("focalX", PixelUtil.toDIPFromPixel(pinchGestureHandler.getFocalPointX()));
            writableMap.putDouble("focalY", PixelUtil.toDIPFromPixel(pinchGestureHandler.getFocalPointY()));
            writableMap.putDouble("velocity", pinchGestureHandler.getVelocity());
        }
    }

    /* loaded from: classes3.dex */
    private static class RotationGestureHandlerFactory extends HandlerFactory<RotationGestureHandler> {
        private RotationGestureHandlerFactory() {
            super();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "RotationGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<RotationGestureHandler> getType() {
            return RotationGestureHandler.class;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        /* renamed from: create  reason: collision with other method in class */
        public RotationGestureHandler mo10148create(Context context) {
            return new RotationGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(RotationGestureHandler rotationGestureHandler, WritableMap writableMap) {
            super.extractEventData((RotationGestureHandlerFactory) rotationGestureHandler, writableMap);
            writableMap.putDouble(ViewProps.ROTATION, rotationGestureHandler.getRotation());
            writableMap.putDouble("anchorX", PixelUtil.toDIPFromPixel(rotationGestureHandler.getAnchorX()));
            writableMap.putDouble("anchorY", PixelUtil.toDIPFromPixel(rotationGestureHandler.getAnchorY()));
            writableMap.putDouble("velocity", rotationGestureHandler.getVelocity());
        }
    }

    /* loaded from: classes3.dex */
    private static class TapGestureHandlerFactory extends HandlerFactory<TapGestureHandler> {
        private TapGestureHandlerFactory() {
            super();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "TapGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<TapGestureHandler> getType() {
            return TapGestureHandler.class;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(TapGestureHandler tapGestureHandler, ReadableMap readableMap) {
            super.configure((TapGestureHandlerFactory) tapGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS)) {
                tapGestureHandler.setNumberOfTaps(readableMap.getInt(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS)) {
                tapGestureHandler.setMaxDurationMs(readableMap.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS)) {
                tapGestureHandler.setMaxDelayMs(readableMap.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)) {
                tapGestureHandler.setMaxDx(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)) {
                tapGestureHandler.setMaxDy(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)));
            }
            if (readableMap.hasKey("maxDist")) {
                tapGestureHandler.setMaxDist(PixelUtil.toPixelFromDIP(readableMap.getDouble("maxDist")));
            }
            if (readableMap.hasKey("minPointers")) {
                tapGestureHandler.setMinNumberOfPointers(readableMap.getInt("minPointers"));
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        /* renamed from: create  reason: collision with other method in class */
        public TapGestureHandler mo10148create(Context context) {
            return new TapGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(TapGestureHandler tapGestureHandler, WritableMap writableMap) {
            super.extractEventData((TapGestureHandlerFactory) tapGestureHandler, writableMap);
            writableMap.putDouble(ReactProperties.HereMapMarker.X, PixelUtil.toDIPFromPixel(tapGestureHandler.getLastRelativePositionX()));
            writableMap.putDouble(ReactProperties.HereMapMarker.Y, PixelUtil.toDIPFromPixel(tapGestureHandler.getLastRelativePositionY()));
            writableMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(tapGestureHandler.getLastAbsolutePositionX()));
            writableMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(tapGestureHandler.getLastAbsolutePositionY()));
        }
    }

    public RNGestureHandlerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mEventListener = new OnTouchEventListener() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerModule.1
            @Override // com.swmansion.gesturehandler.OnTouchEventListener
            public void onStateChange(GestureHandler gestureHandler, int i, int i2) {
                RNGestureHandlerModule.this.onStateChange(gestureHandler, i, i2);
            }

            @Override // com.swmansion.gesturehandler.OnTouchEventListener
            public void onTouchEvent(GestureHandler gestureHandler, MotionEvent motionEvent) {
                RNGestureHandlerModule.this.onTouchEvent(gestureHandler, motionEvent);
            }
        };
        this.mHandlerFactories = new HandlerFactory[]{new NativeViewGestureHandlerFactory(), new TapGestureHandlerFactory(), new LongPressGestureHandlerFactory(), new PanGestureHandlerFactory(), new PinchGestureHandlerFactory(), new RotationGestureHandlerFactory(), new FlingGestureHandlerFactory()};
        this.mRegistry = new RNGestureHandlerRegistry();
        this.mInteractionManager = new RNGestureHandlerInteractionManager();
        this.mRoots = new ArrayList();
        this.mEnqueuedRootViewInit = new ArrayList();
    }

    @Nullable
    private HandlerFactory findFactoryForHandler(GestureHandler gestureHandler) {
        int i = 0;
        while (true) {
            HandlerFactory[] handlerFactoryArr = this.mHandlerFactories;
            if (i < handlerFactoryArr.length) {
                HandlerFactory handlerFactory = handlerFactoryArr[i];
                if (handlerFactory.getType().equals(gestureHandler.getClass())) {
                    return handlerFactory;
                }
                i++;
            } else {
                return null;
            }
        }
    }

    @Nullable
    private RNGestureHandlerRootHelper findRootHelperForViewAncestor(int i) {
        int resolveRootTagFromReactTag = ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).resolveRootTagFromReactTag(i);
        if (resolveRootTagFromReactTag < 1) {
            return null;
        }
        synchronized (this.mRoots) {
            for (int i2 = 0; i2 < this.mRoots.size(); i2++) {
                RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mRoots.get(i2);
                ViewGroup rootView = rNGestureHandlerRootHelper.getRootView();
                if ((rootView instanceof ReactRootView) && ((ReactRootView) rootView).getRootViewTag() == resolveRootTagFromReactTag) {
                    return rNGestureHandlerRootHelper;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleHitSlopProperty(GestureHandler gestureHandler, ReadableMap readableMap) {
        if (readableMap.getType(KEY_HIT_SLOP) == ReadableType.Number) {
            float pixelFromDIP = PixelUtil.toPixelFromDIP(readableMap.getDouble(KEY_HIT_SLOP));
            gestureHandler.setHitSlop(pixelFromDIP, pixelFromDIP, pixelFromDIP, pixelFromDIP, Float.NaN, Float.NaN);
            return;
        }
        ReadableMap mo6945getMap = readableMap.mo6945getMap(KEY_HIT_SLOP);
        float f = Float.NaN;
        float pixelFromDIP2 = mo6945getMap.hasKey(KEY_HIT_SLOP_HORIZONTAL) ? PixelUtil.toPixelFromDIP(mo6945getMap.getDouble(KEY_HIT_SLOP_HORIZONTAL)) : Float.NaN;
        float f2 = pixelFromDIP2;
        float pixelFromDIP3 = mo6945getMap.hasKey(KEY_HIT_SLOP_VERTICAL) ? PixelUtil.toPixelFromDIP(mo6945getMap.getDouble(KEY_HIT_SLOP_VERTICAL)) : Float.NaN;
        float f3 = pixelFromDIP3;
        if (mo6945getMap.hasKey("left")) {
            pixelFromDIP2 = PixelUtil.toPixelFromDIP(mo6945getMap.getDouble("left"));
        }
        float f4 = pixelFromDIP2;
        if (mo6945getMap.hasKey("top")) {
            f3 = PixelUtil.toPixelFromDIP(mo6945getMap.getDouble("top"));
        }
        float f5 = f3;
        if (mo6945getMap.hasKey("right")) {
            f2 = PixelUtil.toPixelFromDIP(mo6945getMap.getDouble("right"));
        }
        float f6 = f2;
        if (mo6945getMap.hasKey("bottom")) {
            pixelFromDIP3 = PixelUtil.toPixelFromDIP(mo6945getMap.getDouble("bottom"));
        }
        float f7 = pixelFromDIP3;
        float pixelFromDIP4 = mo6945getMap.hasKey("width") ? PixelUtil.toPixelFromDIP(mo6945getMap.getDouble("width")) : Float.NaN;
        if (mo6945getMap.hasKey("height")) {
            f = PixelUtil.toPixelFromDIP(mo6945getMap.getDouble("height"));
        }
        gestureHandler.setHitSlop(f4, f5, f6, f7, pixelFromDIP4, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStateChange(GestureHandler gestureHandler, int i, int i2) {
        if (gestureHandler.getTag() < 0) {
            return;
        }
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher().dispatchEvent(RNGestureHandlerStateChangeEvent.obtain(gestureHandler, i, i2, findFactoryForHandler(gestureHandler)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTouchEvent(GestureHandler gestureHandler, MotionEvent motionEvent) {
        if (gestureHandler.getTag() >= 0 && gestureHandler.getState() == 4) {
            ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher().dispatchEvent(RNGestureHandlerEvent.obtain(gestureHandler, findFactoryForHandler(gestureHandler)));
        }
    }

    private void tryInitializeHandlerForReactRootView(int i) {
        UIManagerModule uIManagerModule = (UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class);
        final int resolveRootTagFromReactTag = uIManagerModule.resolveRootTagFromReactTag(i);
        if (resolveRootTagFromReactTag >= 1) {
            synchronized (this.mRoots) {
                for (int i2 = 0; i2 < this.mRoots.size(); i2++) {
                    ViewGroup rootView = this.mRoots.get(i2).getRootView();
                    if ((rootView instanceof ReactRootView) && ((ReactRootView) rootView).getRootViewTag() == resolveRootTagFromReactTag) {
                        return;
                    }
                }
                synchronized (this.mEnqueuedRootViewInit) {
                    if (this.mEnqueuedRootViewInit.contains(Integer.valueOf(resolveRootTagFromReactTag))) {
                        return;
                    }
                    this.mEnqueuedRootViewInit.add(Integer.valueOf(resolveRootTagFromReactTag));
                    uIManagerModule.addUIBlock(new UIBlock() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerModule.2
                        @Override // com.facebook.react.uimanager.UIBlock
                        public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                            View resolveView = nativeViewHierarchyManager.resolveView(resolveRootTagFromReactTag);
                            if (resolveView instanceof RNGestureHandlerEnabledRootView) {
                                ((RNGestureHandlerEnabledRootView) resolveView).initialize();
                            }
                            synchronized (RNGestureHandlerModule.this.mEnqueuedRootViewInit) {
                                RNGestureHandlerModule.this.mEnqueuedRootViewInit.remove(new Integer(resolveRootTagFromReactTag));
                            }
                        }
                    });
                    return;
                }
            }
        }
        throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline49("Could find root view for a given ancestor with tag ", i));
    }

    @ReactMethod
    public void attachGestureHandler(int i, int i2) {
        tryInitializeHandlerForReactRootView(i2);
        if (this.mRegistry.attachHandlerToView(i, i2)) {
            return;
        }
        throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline52("Handler with tag ", i, " does not exists"));
    }

    @ReactMethod
    public void createGestureHandler(String str, int i, ReadableMap readableMap) {
        int i2 = 0;
        while (true) {
            HandlerFactory[] handlerFactoryArr = this.mHandlerFactories;
            if (i2 < handlerFactoryArr.length) {
                HandlerFactory handlerFactory = handlerFactoryArr[i2];
                if (handlerFactory.getName().equals(str)) {
                    GestureHandler mo10148create = handlerFactory.mo10148create(getReactApplicationContext());
                    mo10148create.setTag(i);
                    mo10148create.setOnTouchEventListener(this.mEventListener);
                    this.mRegistry.registerHandler(mo10148create);
                    this.mInteractionManager.configureInteractions(mo10148create, readableMap);
                    handlerFactory.configure(mo10148create, readableMap);
                    return;
                }
                i2++;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid handler name ", str));
            }
        }
    }

    @ReactMethod
    public void dropGestureHandler(int i) {
        this.mInteractionManager.dropRelationsForHandlerWithTag(i);
        this.mRegistry.dropHandler(i);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map getConstants() {
        return MapBuilder.of("State", MapBuilder.of("UNDETERMINED", 0, "BEGAN", 2, "ACTIVE", 4, "CANCELLED", 3, "FAILED", 1, "END", 5), "Direction", MapBuilder.of("RIGHT", 1, "LEFT", 2, "UP", 4, "DOWN", 8));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    public RNGestureHandlerRegistry getRegistry() {
        return this.mRegistry;
    }

    @ReactMethod
    public void handleClearJSResponder() {
    }

    @ReactMethod
    public void handleSetJSResponder(int i, boolean z) {
        RNGestureHandlerRootHelper findRootHelperForViewAncestor;
        if (this.mRegistry == null || (findRootHelperForViewAncestor = findRootHelperForViewAncestor(i)) == null) {
            return;
        }
        findRootHelperForViewAncestor.handleSetJSResponder(i, z);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mRegistry.dropAllHandlers();
        this.mInteractionManager.reset();
        synchronized (this.mRoots) {
            while (!this.mRoots.isEmpty()) {
                int size = this.mRoots.size();
                RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mRoots.get(0);
                ViewGroup rootView = rNGestureHandlerRootHelper.getRootView();
                if (rootView instanceof RNGestureHandlerEnabledRootView) {
                    ((RNGestureHandlerEnabledRootView) rootView).tearDown();
                } else {
                    rNGestureHandlerRootHelper.tearDown();
                }
                if (this.mRoots.size() >= size) {
                    throw new IllegalStateException("Expected root helper to get unregistered while tearing down");
                }
            }
        }
        super.onCatalystInstanceDestroy();
    }

    public void registerRootHelper(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        synchronized (this.mRoots) {
            if (!this.mRoots.contains(rNGestureHandlerRootHelper)) {
                this.mRoots.add(rNGestureHandlerRootHelper);
            } else {
                throw new IllegalStateException("Root helper" + rNGestureHandlerRootHelper + " already registered");
            }
        }
    }

    public void unregisterRootHelper(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        synchronized (this.mRoots) {
            this.mRoots.remove(rNGestureHandlerRootHelper);
        }
    }

    @ReactMethod
    public void updateGestureHandler(int i, ReadableMap readableMap) {
        HandlerFactory findFactoryForHandler;
        GestureHandler handler = this.mRegistry.getHandler(i);
        if (handler == null || (findFactoryForHandler = findFactoryForHandler(handler)) == null) {
            return;
        }
        this.mInteractionManager.dropRelationsForHandlerWithTag(i);
        this.mInteractionManager.configureInteractions(handler, readableMap);
        findFactoryForHandler.configure(handler, readableMap);
    }
}
