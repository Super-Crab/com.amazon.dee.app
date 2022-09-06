package com.reactnativecommunity.picker;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
@ReactModule(name = ReactDropdownPickerManager.REACT_CLASS)
/* loaded from: classes3.dex */
public class ReactDropdownPickerManager extends ReactPickerManager {
    public static final String REACT_CLASS = "RNCAndroidDropdownPicker";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactPicker mo12880createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactPicker(themedReactContext, 1);
    }
}
