package com.facebook.react.views.picker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.picker.ReactPicker;
import com.facebook.react.views.picker.events.PickerItemSelectEvent;
/* loaded from: classes2.dex */
public abstract class ReactPickerManager extends SimpleViewManager<ReactPicker> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PickerEventEmitter implements ReactPicker.OnSelectListener {
        private final EventDispatcher mEventDispatcher;
        private final ReactPicker mReactPicker;

        public PickerEventEmitter(ReactPicker reactPicker, EventDispatcher eventDispatcher) {
            this.mReactPicker = reactPicker;
            this.mEventDispatcher = eventDispatcher;
        }

        @Override // com.facebook.react.views.picker.ReactPicker.OnSelectListener
        public void onItemSelected(int i) {
            this.mEventDispatcher.dispatchEvent(new PickerItemSelectEvent(this.mReactPicker.getId(), i));
        }
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactPicker reactPicker, @Nullable Integer num) {
        reactPicker.setStagedPrimaryTextColor(num);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactPicker reactPicker, boolean z) {
        reactPicker.setEnabled(z);
    }

    @ReactProp(name = EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS)
    public void setItems(ReactPicker reactPicker, @Nullable ReadableArray readableArray) {
        reactPicker.setStagedItems(ReactPickerItem.createFromJsArrayMap(readableArray));
    }

    @ReactProp(name = "prompt")
    public void setPrompt(ReactPicker reactPicker, @Nullable String str) {
        reactPicker.setPrompt(str);
    }

    @ReactProp(name = "selected")
    public void setSelected(ReactPicker reactPicker, int i) {
        reactPicker.setStagedSelection(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactPicker reactPicker) {
        reactPicker.setOnSelectListener(new PickerEventEmitter(reactPicker, UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, reactPicker.getId())));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactPicker reactPicker) {
        super.onAfterUpdateTransaction((ReactPickerManager) reactPicker);
        reactPicker.commitStagedData();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(@NonNull ReactPicker reactPicker, String str, @Nullable ReadableArray readableArray) {
        if (((str.hashCode() == -729039331 && str.equals("setNativeSelectedPosition")) ? (char) 0 : (char) 65535) == 0 && readableArray != null) {
            reactPicker.setImmediateSelection(readableArray.getInt(0));
        }
    }
}
