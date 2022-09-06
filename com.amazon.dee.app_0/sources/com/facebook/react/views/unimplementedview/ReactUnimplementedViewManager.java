package com.facebook.react.views.unimplementedview;

import androidx.annotation.Nullable;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
@ReactModule(name = ReactUnimplementedViewManager.REACT_CLASS)
/* loaded from: classes2.dex */
public class ReactUnimplementedViewManager extends ViewGroupManager<ReactUnimplementedView> {
    public static final String REACT_CLASS = "UnimplementedNativeView";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "name")
    public void setName(ReactUnimplementedView reactUnimplementedView, @Nullable String str) {
        reactUnimplementedView.setName(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactUnimplementedView mo12880createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactUnimplementedView(themedReactContext);
    }
}
