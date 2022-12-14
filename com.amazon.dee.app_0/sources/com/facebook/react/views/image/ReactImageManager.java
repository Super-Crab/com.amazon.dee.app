package com.facebook.react.views.image;

import android.graphics.PorterDuff;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaConstants;
import java.util.Map;
@ReactModule(name = ReactImageManager.REACT_CLASS)
/* loaded from: classes2.dex */
public class ReactImageManager extends SimpleViewManager<ReactImageView> {
    public static final String REACT_CLASS = "RCTImageView";
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private final ReactCallerContextFactory mCallerContextFactory;
    @Nullable
    private AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    @Nullable
    private GlobalImageLoadListener mGlobalImageLoadListener;

    @Deprecated
    public ReactImageManager(@Nullable AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable Object obj) {
        this(abstractDraweeControllerBuilder, (GlobalImageLoadListener) null, obj);
    }

    @Deprecated
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return this.mDraweeControllerBuilder;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(ImageLoadEvent.eventNameForType(4), MapBuilder.of("registrationName", "onLoadStart"), ImageLoadEvent.eventNameForType(5), MapBuilder.of("registrationName", "onProgress"), ImageLoadEvent.eventNameForType(2), MapBuilder.of("registrationName", "onLoad"), ImageLoadEvent.eventNameForType(1), MapBuilder.of("registrationName", "onError"), ImageLoadEvent.eventNameForType(3), MapBuilder.of("registrationName", "onLoadEnd"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "blurRadius")
    public void setBlurRadius(ReactImageView reactImageView, float f) {
        reactImageView.setBlurRadius(f);
    }

    @ReactProp(customType = "Color", name = ViewProps.BORDER_COLOR)
    public void setBorderColor(ReactImageView reactImageView, @Nullable Integer num) {
        if (num == null) {
            reactImageView.setBorderColor(0);
        } else {
            reactImageView.setBorderColor(num.intValue());
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {ViewProps.BORDER_RADIUS, ViewProps.BORDER_TOP_LEFT_RADIUS, ViewProps.BORDER_TOP_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_LEFT_RADIUS})
    public void setBorderRadius(ReactImageView reactImageView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactImageView.setBorderRadius(f);
        } else {
            reactImageView.setBorderRadius(f, i - 1);
        }
    }

    @ReactProp(name = ViewProps.BORDER_WIDTH)
    public void setBorderWidth(ReactImageView reactImageView, float f) {
        reactImageView.setBorderWidth(f);
    }

    @ReactProp(name = "defaultSrc")
    public void setDefaultSource(ReactImageView reactImageView, @Nullable String str) {
        reactImageView.setDefaultSource(str);
    }

    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(ReactImageView reactImageView, int i) {
        reactImageView.setFadeDuration(i);
    }

    @ReactProp(name = HttpClientModule.ElementsRequestKey.HEADERS)
    public void setHeaders(ReactImageView reactImageView, ReadableMap readableMap) {
        reactImageView.setHeaders(readableMap);
    }

    @ReactProp(name = "internal_analyticTag")
    public void setInternal_AnalyticsTag(ReactImageView reactImageView, @Nullable String str) {
        ReactCallerContextFactory reactCallerContextFactory = this.mCallerContextFactory;
        if (reactCallerContextFactory != null) {
            reactImageView.updateCallerContext(reactCallerContextFactory.getOrCreateCallerContext(((ThemedReactContext) reactImageView.getContext()).getSurfaceID(), str));
        }
    }

    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(ReactImageView reactImageView, boolean z) {
        reactImageView.setShouldNotifyLoadEvents(z);
    }

    @ReactProp(name = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(ReactImageView reactImageView, @Nullable String str) {
        reactImageView.setLoadingIndicatorSource(str);
    }

    @ReactProp(customType = "Color", name = "overlayColor")
    public void setOverlayColor(ReactImageView reactImageView, @Nullable Integer num) {
        if (num == null) {
            reactImageView.setOverlayColor(0);
        } else {
            reactImageView.setOverlayColor(num.intValue());
        }
    }

    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(ReactImageView reactImageView, boolean z) {
        reactImageView.setProgressiveRenderingEnabled(z);
    }

    @ReactProp(name = ViewProps.RESIZE_METHOD)
    public void setResizeMethod(ReactImageView reactImageView, @Nullable String str) {
        if (str != null && !"auto".equals(str)) {
            if ("resize".equals(str)) {
                reactImageView.setResizeMethod(ImageResizeMethod.RESIZE);
                return;
            } else if (ModelTransformer.KEY_BATTERY_SCALE.equals(str)) {
                reactImageView.setResizeMethod(ImageResizeMethod.SCALE);
                return;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline75("Invalid resize method: '", str, "'"));
            }
        }
        reactImageView.setResizeMethod(ImageResizeMethod.AUTO);
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(ReactImageView reactImageView, @Nullable String str) {
        reactImageView.setScaleType(ImageResizeMode.toScaleType(str));
        reactImageView.setTileMode(ImageResizeMode.toTileMode(str));
    }

    @ReactProp(name = "src")
    public void setSource(ReactImageView reactImageView, @Nullable ReadableArray readableArray) {
        reactImageView.setSource(readableArray);
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public void setTintColor(ReactImageView reactImageView, @Nullable Integer num) {
        if (num == null) {
            reactImageView.clearColorFilter();
        } else {
            reactImageView.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @Deprecated
    public ReactImageManager(@Nullable AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable GlobalImageLoadListener globalImageLoadListener, @Nullable Object obj) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mGlobalImageLoadListener = globalImageLoadListener;
        this.mCallerContext = obj;
        this.mCallerContextFactory = null;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactImageView mo12880createViewInstance(ThemedReactContext themedReactContext) {
        Object callerContext;
        ReactCallerContextFactory reactCallerContextFactory = this.mCallerContextFactory;
        if (reactCallerContextFactory != null) {
            callerContext = reactCallerContextFactory.getOrCreateCallerContext(themedReactContext.getSurfaceID(), null);
        } else {
            callerContext = getCallerContext();
        }
        return new ReactImageView(themedReactContext, getDraweeControllerBuilder(), this.mGlobalImageLoadListener, callerContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactImageView reactImageView) {
        super.onAfterUpdateTransaction((ReactImageManager) reactImageView);
        reactImageView.maybeUpdateView();
    }

    public ReactImageManager(@Nullable AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable ReactCallerContextFactory reactCallerContextFactory) {
        this(abstractDraweeControllerBuilder, (GlobalImageLoadListener) null, reactCallerContextFactory);
    }

    public ReactImageManager(@Nullable AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable GlobalImageLoadListener globalImageLoadListener, @Nullable ReactCallerContextFactory reactCallerContextFactory) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mGlobalImageLoadListener = globalImageLoadListener;
        this.mCallerContextFactory = reactCallerContextFactory;
        this.mCallerContext = null;
    }

    public ReactImageManager() {
        this.mDraweeControllerBuilder = null;
        this.mCallerContext = null;
        this.mCallerContextFactory = null;
    }
}
