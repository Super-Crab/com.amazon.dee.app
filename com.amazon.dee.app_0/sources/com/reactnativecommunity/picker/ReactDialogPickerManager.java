package com.reactnativecommunity.picker;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
@ReactModule(name = ReactDialogPickerManager.REACT_CLASS)
/* loaded from: classes3.dex */
public class ReactDialogPickerManager extends ReactPickerManager {
    public static final String REACT_CLASS = "RNCAndroidDialogPicker";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactPicker mo12880createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactPicker(themedReactContext, 0);
    }
}
