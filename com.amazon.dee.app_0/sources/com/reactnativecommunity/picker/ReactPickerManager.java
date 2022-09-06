package com.reactnativecommunity.picker;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.reactnativecommunity.picker.ReactPicker;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public abstract class ReactPickerManager extends BaseViewManager<ReactPicker, ReactPickerShadowNode> {
    private static final int BLUR_PICKER = 2;
    private static final ReadableArray EMPTY_ARRAY = Arguments.createArray();
    private static final int FOCUS_PICKER = 1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class PickerEventEmitter implements ReactPicker.OnSelectListener, ReactPicker.OnFocusListener {
        private final EventDispatcher mEventDispatcher;
        private final ReactPicker mReactPicker;

        public PickerEventEmitter(ReactPicker reactPicker, EventDispatcher eventDispatcher) {
            this.mReactPicker = reactPicker;
            this.mEventDispatcher = eventDispatcher;
        }

        @Override // com.reactnativecommunity.picker.ReactPicker.OnSelectListener
        public void onItemSelected(int i) {
            this.mEventDispatcher.dispatchEvent(new PickerItemSelectEvent(this.mReactPicker.getId(), i));
        }

        @Override // com.reactnativecommunity.picker.ReactPicker.OnFocusListener
        public void onPickerBlur() {
            this.mEventDispatcher.dispatchEvent(new PickerBlurEvent(this.mReactPicker.getId()));
        }

        @Override // com.reactnativecommunity.picker.ReactPicker.OnFocusListener
        public void onPickerFocus() {
            this.mEventDispatcher.dispatchEvent(new PickerFocusEvent(this.mReactPicker.getId()));
        }
    }

    /* loaded from: classes3.dex */
    private static class ReactPickerAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        @Nullable
        private ReadableArray mItems;
        private int mNumberOfLines = 1;
        @Nullable
        private Integer mPrimaryTextColor;

        public ReactPickerAdapter(Context context, @Nullable ReadableArray readableArray) {
            this.mItems = readableArray;
            this.mInflater = (LayoutInflater) Assertions.assertNotNull(context.getSystemService("layout_inflater"));
        }

        @Override // android.widget.Adapter
        public int getCount() {
            ReadableArray readableArray = this.mItems;
            if (readableArray == null) {
                return 0;
            }
            return readableArray.size();
        }

        @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return getView(i, view, viewGroup, true);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return getView(i, view, viewGroup, false);
        }

        public void setItems(@Nullable ReadableArray readableArray) {
            this.mItems = readableArray;
            notifyDataSetChanged();
        }

        public void setNumberOfLines(int i) {
            this.mNumberOfLines = i;
            notifyDataSetChanged();
        }

        public void setPrimaryTextColor(@Nullable Integer num) {
            this.mPrimaryTextColor = num;
            notifyDataSetChanged();
        }

        private View getView(int i, View view, ViewGroup viewGroup, boolean z) {
            Integer num;
            ReadableMap mo10134getItem = mo10134getItem(i);
            ReadableMap mo6945getMap = mo10134getItem.hasKey(TtmlNode.TAG_STYLE) ? mo10134getItem.mo6945getMap(TtmlNode.TAG_STYLE) : null;
            if (view == null) {
                view = this.mInflater.inflate(z ? R.layout.simple_spinner_dropdown_item : R.layout.simple_spinner_item, viewGroup, false);
            }
            boolean z2 = mo10134getItem.hasKey("enabled") ? mo10134getItem.getBoolean("enabled") : true;
            view.setEnabled(z2);
            view.setClickable(!z2);
            TextView textView = (TextView) view;
            textView.setText(mo10134getItem.getString("label"));
            textView.setMaxLines(this.mNumberOfLines);
            if (mo6945getMap != null) {
                if (mo6945getMap.hasKey("backgroundColor") && !mo6945getMap.isNull("backgroundColor")) {
                    view.setBackgroundColor(mo6945getMap.getInt("backgroundColor"));
                } else {
                    view.setBackgroundColor(0);
                }
                if (mo6945getMap.hasKey("color") && !mo6945getMap.isNull("color")) {
                    textView.setTextColor(mo6945getMap.getInt("color"));
                }
                if (mo6945getMap.hasKey("fontSize") && !mo6945getMap.isNull("fontSize")) {
                    textView.setTextSize((float) mo6945getMap.getDouble("fontSize"));
                }
                if (mo6945getMap.hasKey("fontFamily") && !mo6945getMap.isNull("fontFamily")) {
                    textView.setTypeface(Typeface.create(mo6945getMap.getString("fontFamily"), 0));
                }
            }
            if (!z && (num = this.mPrimaryTextColor) != null) {
                textView.setTextColor(num.intValue());
            } else if (mo10134getItem.hasKey("color") && !mo10134getItem.isNull("color")) {
                textView.setTextColor(mo10134getItem.getInt("color"));
            }
            if (mo10134getItem.hasKey("fontFamily") && !mo10134getItem.isNull("fontFamily")) {
                textView.setTypeface(Typeface.create(mo10134getItem.getString("fontFamily"), 0));
            }
            if (I18nUtil.getInstance().isRTL(view.getContext())) {
                view.setLayoutDirection(1);
                view.setTextDirection(4);
            } else {
                view.setLayoutDirection(0);
                view.setTextDirection(3);
            }
            return view;
        }

        @Override // android.widget.Adapter
        /* renamed from: getItem */
        public ReadableMap mo10134getItem(int i) {
            ReadableArray readableArray = this.mItems;
            if (readableArray == null) {
                return null;
            }
            return readableArray.mo6944getMap(i);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("focus", 1, "blur", 2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put("topSelect", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onSelect", "captured", "onSelectCapture"))).put(PickerFocusEvent.EVENT_NAME, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onFocus", "captured", "onFocusCapture"))).put(PickerBlurEvent.EVENT_NAME, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onBlur", "captured", "onBlurCapture"))).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends ReactPickerShadowNode> getShadowNodeClass() {
        return ReactPickerShadowNode.class;
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactPicker reactPicker, @Nullable Integer num) {
        reactPicker.setPrimaryColor(num);
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) reactPicker.getAdapter();
        if (reactPickerAdapter != null) {
            reactPickerAdapter.setPrimaryTextColor(num);
        }
    }

    @ReactProp(name = "dropdownIconColor")
    public void setDropdownIconColor(ReactPicker reactPicker, @Nullable int i) {
        reactPicker.setDropdownIconColor(i);
    }

    @ReactProp(name = "dropdownIconRippleColor")
    public void setDropdownIconRippleColor(ReactPicker reactPicker, @Nullable int i) {
        reactPicker.setDropdownIconRippleColor(i);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactPicker reactPicker, boolean z) {
        reactPicker.setEnabled(z);
    }

    @ReactProp(name = EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS)
    public void setItems(ReactPicker reactPicker, @Nullable ReadableArray readableArray) {
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) reactPicker.getAdapter();
        if (reactPickerAdapter == null) {
            ReactPickerAdapter reactPickerAdapter2 = new ReactPickerAdapter(reactPicker.getContext(), readableArray);
            reactPickerAdapter2.setPrimaryTextColor(reactPicker.getPrimaryColor());
            reactPicker.setAdapter((SpinnerAdapter) reactPickerAdapter2);
            return;
        }
        reactPickerAdapter.setItems(readableArray);
    }

    @ReactProp(defaultInt = 1, name = ViewProps.NUMBER_OF_LINES)
    public void setNumberOfLines(ReactPicker reactPicker, int i) {
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) reactPicker.getAdapter();
        if (reactPickerAdapter == null) {
            ReactPickerAdapter reactPickerAdapter2 = new ReactPickerAdapter(reactPicker.getContext(), EMPTY_ARRAY);
            reactPickerAdapter2.setPrimaryTextColor(reactPicker.getPrimaryColor());
            reactPickerAdapter2.setNumberOfLines(i);
            reactPicker.setAdapter((SpinnerAdapter) reactPickerAdapter2);
            return;
        }
        reactPickerAdapter.setNumberOfLines(i);
    }

    @ReactProp(name = "prompt")
    public void setPrompt(ReactPicker reactPicker, @Nullable String str) {
        reactPicker.setPrompt(str);
    }

    @ReactProp(name = "selected")
    public void setSelected(ReactPicker reactPicker, int i) {
        reactPicker.setStagedSelection(i);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(ReactPicker reactPicker, Object obj) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactPicker reactPicker) {
        PickerEventEmitter pickerEventEmitter = new PickerEventEmitter(reactPicker, ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher());
        reactPicker.setOnSelectListener(pickerEventEmitter);
        reactPicker.setOnFocusListener(pickerEventEmitter);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createShadowNodeInstance  reason: collision with other method in class */
    public ReactPickerShadowNode mo10136createShadowNodeInstance() {
        return new ReactPickerShadowNode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactPicker reactPicker) {
        super.onAfterUpdateTransaction((ReactPickerManager) reactPicker);
        reactPicker.updateStagedSelection();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(ReactPicker reactPicker, @Nullable int i) {
        reactPicker.setBackgroundColor(i);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(@NonNull ReactPicker reactPicker, int i, @androidx.annotation.Nullable ReadableArray readableArray) {
        if (i == 1) {
            reactPicker.performClick();
        } else if (i != 2) {
        } else {
            reactPicker.clearFocus();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(@NonNull ReactPicker reactPicker, String str, @androidx.annotation.Nullable ReadableArray readableArray) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != 3027047) {
            if (hashCode == 97604824 && str.equals("focus")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals("blur")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            reactPicker.performClick();
        } else if (c != 1) {
        } else {
            reactPicker.clearFocus();
        }
    }
}
