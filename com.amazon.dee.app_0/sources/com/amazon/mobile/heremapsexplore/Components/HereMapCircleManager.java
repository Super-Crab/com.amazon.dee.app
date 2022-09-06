package com.amazon.mobile.heremapsexplore.Components;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nonnull;
/* loaded from: classes13.dex */
public class HereMapCircleManager extends ViewGroupManager<HereMapCircle> {
    private static final String REACT_CLASS_NAME = "HereMapCircle";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return REACT_CLASS_NAME;
    }

    @ReactProp(name = "center")
    public void setCenter(HereMapCircle hereMapCircle, ReadableMap readableMap) {
        hereMapCircle.setCenter(readableMap);
    }

    @ReactProp(name = "radius")
    public void setRadius(HereMapCircle hereMapCircle, double d) {
        hereMapCircle.setRadius(d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public HereMapCircle mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        return new HereMapCircle(themedReactContext);
    }
}
