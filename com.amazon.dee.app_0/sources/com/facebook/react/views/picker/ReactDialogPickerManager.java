package com.facebook.react.views.picker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.AndroidDialogPickerManagerDelegate;
import com.facebook.react.viewmanagers.AndroidDialogPickerManagerInterface;
@ReactModule(name = ReactDialogPickerManager.REACT_CLASS)
/* loaded from: classes2.dex */
public class ReactDialogPickerManager extends ReactPickerManager implements AndroidDialogPickerManagerInterface<ReactPicker> {
    public static final String REACT_CLASS = "AndroidDialogPicker";
    private final ViewManagerDelegate<ReactPicker> mDelegate = new AndroidDialogPickerManagerDelegate(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate<ReactPicker> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.viewmanagers.AndroidDialogPickerManagerInterface
    @ReactProp(customType = "Color", name = "color")
    public /* bridge */ /* synthetic */ void setColor(ReactPicker reactPicker, @Nullable Integer num) {
        super.setColor(reactPicker, num);
    }

    @Override // com.facebook.react.viewmanagers.AndroidDialogPickerManagerInterface
    @ReactProp(defaultBoolean = true, name = "enabled")
    public /* bridge */ /* synthetic */ void setEnabled(ReactPicker reactPicker, boolean z) {
        super.setEnabled(reactPicker, z);
    }

    @Override // com.facebook.react.viewmanagers.AndroidDialogPickerManagerInterface
    @ReactProp(name = EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS)
    public /* bridge */ /* synthetic */ void setItems(ReactPicker reactPicker, @Nullable ReadableArray readableArray) {
        super.setItems(reactPicker, readableArray);
    }

    @Override // com.facebook.react.viewmanagers.AndroidDialogPickerManagerInterface
    @ReactProp(name = "prompt")
    public /* bridge */ /* synthetic */ void setPrompt(ReactPicker reactPicker, @Nullable String str) {
        super.setPrompt(reactPicker, str);
    }

    @Override // com.facebook.react.viewmanagers.AndroidDialogPickerManagerInterface
    @ReactProp(name = "selected")
    public /* bridge */ /* synthetic */ void setSelected(ReactPicker reactPicker, int i) {
        super.setSelected(reactPicker, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactPicker mo12880createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactPicker(themedReactContext, 0);
    }

    @Override // com.facebook.react.viewmanagers.AndroidDialogPickerManagerInterface
    public void setBackgroundColor(@NonNull ReactPicker reactPicker, @Nullable Integer num) {
        reactPicker.setStagedBackgroundColor(num);
    }
}
