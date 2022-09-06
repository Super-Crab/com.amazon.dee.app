package com.facebook.react.views.textinput;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.amazon.alexa.fitness.utils.MetricName;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewDefaults;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.react.views.text.DefaultStyleValuesUtil;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.ReactTextViewManagerCallback;
import com.facebook.react.views.text.TextAttributeProps;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.react.views.text.TextLayoutManager;
import com.facebook.react.views.text.TextTransform;
import com.facebook.yoga.YogaConstants;
import com.reactnativecommunity.picker.PickerBlurEvent;
import com.reactnativecommunity.picker.PickerFocusEvent;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;
@ReactModule(name = ReactTextInputManager.REACT_CLASS)
/* loaded from: classes2.dex */
public class ReactTextInputManager extends BaseViewManager<ReactEditText, LayoutShadowNode> {
    private static final int AUTOCAPITALIZE_FLAGS = 28672;
    private static final int BLUR_TEXT_INPUT = 2;
    private static final int FOCUS_TEXT_INPUT = 1;
    private static final int IME_ACTION_ID = 1648;
    private static final int INPUT_TYPE_KEYBOARD_DECIMAL_PAD = 8194;
    private static final int INPUT_TYPE_KEYBOARD_NUMBERED = 12290;
    private static final int INPUT_TYPE_KEYBOARD_NUMBER_PAD = 2;
    private static final String KEYBOARD_TYPE_DECIMAL_PAD = "decimal-pad";
    private static final String KEYBOARD_TYPE_EMAIL_ADDRESS = "email-address";
    private static final String KEYBOARD_TYPE_NUMBER_PAD = "number-pad";
    private static final String KEYBOARD_TYPE_NUMERIC = "numeric";
    private static final String KEYBOARD_TYPE_PHONE_PAD = "phone-pad";
    private static final String KEYBOARD_TYPE_VISIBLE_PASSWORD = "visible-password";
    private static final int PASSWORD_VISIBILITY_FLAG = 16;
    public static final String REACT_CLASS = "AndroidTextInput";
    private static final int SET_MOST_RECENT_EVENT_COUNT = 3;
    private static final int SET_TEXT_AND_SELECTION = 4;
    public static final String TAG = "ReactTextInputManager";
    private static final int UNSET = -1;
    @Nullable
    protected ReactTextViewManagerCallback mReactTextViewManagerCallback;
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private static final InputFilter[] EMPTY_FILTERS = new InputFilter[0];

    /* loaded from: classes2.dex */
    private static class ReactContentSizeWatcher implements ContentSizeWatcher {
        private ReactEditText mEditText;
        @Nullable
        private EventDispatcher mEventDispatcher;
        private int mPreviousContentWidth = 0;
        private int mPreviousContentHeight = 0;

        public ReactContentSizeWatcher(ReactEditText reactEditText) {
            this.mEditText = reactEditText;
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(UIManagerHelper.getReactContext(reactEditText), reactEditText);
        }

        @Override // com.facebook.react.views.textinput.ContentSizeWatcher
        public void onLayout() {
            if (this.mEventDispatcher == null) {
                return;
            }
            int width = this.mEditText.getWidth();
            int height = this.mEditText.getHeight();
            if (this.mEditText.getLayout() != null) {
                width = this.mEditText.getCompoundPaddingRight() + this.mEditText.getLayout().getWidth() + this.mEditText.getCompoundPaddingLeft();
                height = this.mEditText.getCompoundPaddingBottom() + this.mEditText.getLayout().getHeight() + this.mEditText.getCompoundPaddingTop();
            }
            if (width == this.mPreviousContentWidth && height == this.mPreviousContentHeight) {
                return;
            }
            this.mPreviousContentHeight = height;
            this.mPreviousContentWidth = width;
            this.mEventDispatcher.dispatchEvent(new ReactContentSizeChangedEvent(this.mEditText.getId(), PixelUtil.toDIPFromPixel(width), PixelUtil.toDIPFromPixel(height)));
        }
    }

    /* loaded from: classes2.dex */
    private static class ReactScrollWatcher implements ScrollWatcher {
        private EventDispatcher mEventDispatcher;
        private int mPreviousHoriz;
        private int mPreviousVert;
        private ReactEditText mReactEditText;

        public ReactScrollWatcher(ReactEditText reactEditText) {
            this.mReactEditText = reactEditText;
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(UIManagerHelper.getReactContext(reactEditText), reactEditText);
        }

        @Override // com.facebook.react.views.textinput.ScrollWatcher
        public void onScrollChanged(int i, int i2, int i3, int i4) {
            if (this.mPreviousHoriz == i && this.mPreviousVert == i2) {
                return;
            }
            this.mEventDispatcher.dispatchEvent(ScrollEvent.obtain(this.mReactEditText.getId(), ScrollEventType.SCROLL, i, i2, 0.0f, 0.0f, 0, 0, this.mReactEditText.getWidth(), this.mReactEditText.getHeight()));
            this.mPreviousHoriz = i;
            this.mPreviousVert = i2;
        }
    }

    /* loaded from: classes2.dex */
    private class ReactSelectionWatcher implements SelectionWatcher {
        private EventDispatcher mEventDispatcher;
        private int mPreviousSelectionEnd;
        private int mPreviousSelectionStart;
        private ReactEditText mReactEditText;

        public ReactSelectionWatcher(ReactEditText reactEditText) {
            this.mReactEditText = reactEditText;
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(UIManagerHelper.getReactContext(reactEditText), reactEditText);
        }

        @Override // com.facebook.react.views.textinput.SelectionWatcher
        public void onSelectionChanged(int i, int i2) {
            int min = Math.min(i, i2);
            int max = Math.max(i, i2);
            if (this.mPreviousSelectionStart == min && this.mPreviousSelectionEnd == max) {
                return;
            }
            this.mEventDispatcher.dispatchEvent(new ReactTextInputSelectionEvent(this.mReactEditText.getId(), min, max));
            this.mPreviousSelectionStart = min;
            this.mPreviousSelectionEnd = max;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ReactTextInputTextWatcher implements TextWatcher {
        private ReactEditText mEditText;
        private EventDispatcher mEventDispatcher;
        private String mPreviousText = null;

        public ReactTextInputTextWatcher(ReactContext reactContext, ReactEditText reactEditText) {
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(reactContext, reactEditText);
            this.mEditText = reactEditText;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.mPreviousText = charSequence.toString();
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.mEditText.mDisableTextDiffing) {
                return;
            }
            if (i3 == 0 && i2 == 0) {
                return;
            }
            Assertions.assertNotNull(this.mPreviousText);
            String substring = charSequence.toString().substring(i, i + i3);
            int i4 = i + i2;
            String substring2 = this.mPreviousText.substring(i, i4);
            if (i3 == i2 && substring.equals(substring2)) {
                return;
            }
            if (this.mEditText.getFabricViewStateManager().hasStateWrapper()) {
                this.mEditText.getFabricViewStateManager().setState(new FabricViewStateManager.StateUpdateCallback() { // from class: com.facebook.react.views.textinput.ReactTextInputManager.ReactTextInputTextWatcher.1
                    @Override // com.facebook.react.uimanager.FabricViewStateManager.StateUpdateCallback
                    public WritableMap getStateUpdate() {
                        WritableNativeMap writableNativeMap = new WritableNativeMap();
                        writableNativeMap.putInt("mostRecentEventCount", ReactTextInputTextWatcher.this.mEditText.incrementAndGetEventCounter());
                        writableNativeMap.putInt("opaqueCacheId", ReactTextInputTextWatcher.this.mEditText.getId());
                        return writableNativeMap;
                    }
                });
            }
            this.mEventDispatcher.dispatchEvent(new ReactTextChangedEvent(this.mEditText.getId(), charSequence.toString(), this.mEditText.incrementAndGetEventCounter()));
            this.mEventDispatcher.dispatchEvent(new ReactTextInputEvent(this.mEditText.getId(), substring, substring2, i, i4));
        }
    }

    private static void checkPasswordType(ReactEditText reactEditText) {
        if ((reactEditText.getStagedInputType() & 12290) == 0 || (reactEditText.getStagedInputType() & 128) == 0) {
            return;
        }
        updateStagedInputTypeFlag(reactEditText, 128, 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EventDispatcher getEventDispatcher(ReactContext reactContext, ReactEditText reactEditText) {
        return UIManagerHelper.getEventDispatcherForReactTag(reactContext, reactEditText.getId());
    }

    private ReactTextUpdate getReactTextUpdate(String str, int i, int i2, int i3) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) TextTransform.apply(str, TextTransform.UNSET));
        return new ReactTextUpdate(spannableStringBuilder, i, false, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, i2, i3);
    }

    private void setAutofillHints(ReactEditText reactEditText, String... strArr) {
        int i = Build.VERSION.SDK_INT;
        reactEditText.setAutofillHints(strArr);
    }

    private static boolean shouldHideCursorForEmailTextInput() {
        return Build.VERSION.SDK_INT == 29 && Build.MANUFACTURER.toLowerCase().contains("xiaomi");
    }

    private static void updateStagedInputTypeFlag(ReactEditText reactEditText, int i, int i2) {
        reactEditText.setStagedInputType(((~i) & reactEditText.getStagedInputType()) | i2);
    }

    protected EditText createInternalEditText(ThemedReactContext themedReactContext) {
        return new EditText(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("focusTextInput", 1, "blurTextInput", 2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put("topSubmitEditing", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onSubmitEditing", "captured", "onSubmitEditingCapture"))).put("topEndEditing", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onEndEditing", "captured", "onEndEditingCapture"))).put(ReactTextInputEvent.EVENT_NAME, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onTextInput", "captured", "onTextInputCapture"))).put(PickerFocusEvent.EVENT_NAME, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onFocus", "captured", "onFocusCapture"))).put(PickerBlurEvent.EVENT_NAME, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onBlur", "captured", "onBlurCapture"))).put(ReactTextInputKeyPressEvent.EVENT_NAME, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onKeyPress", "captured", "onKeyPressCapture"))).build();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), MapBuilder.of("registrationName", "onScroll")).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedViewConstants() {
        return MapBuilder.of("AutoCapitalizationType", MapBuilder.of("none", 0, "characters", 4096, "words", 8192, "sentences", 16384));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ReactTextInputShadowNode.class;
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.ALLOW_FONT_SCALING)
    public void setAllowFontScaling(ReactEditText reactEditText, boolean z) {
        reactEditText.setAllowFontScaling(z);
    }

    @ReactProp(name = "autoCapitalize")
    public void setAutoCapitalize(ReactEditText reactEditText, Dynamic dynamic) {
        int i = 16384;
        if (dynamic.getType() == ReadableType.Number) {
            i = dynamic.asInt();
        } else if (dynamic.getType() == ReadableType.String) {
            String asString = dynamic.asString();
            if (asString.equals("none")) {
                i = 0;
            } else if (asString.equals("characters")) {
                i = 4096;
            } else if (asString.equals("words")) {
                i = 8192;
            } else {
                asString.equals("sentences");
            }
        }
        updateStagedInputTypeFlag(reactEditText, AUTOCAPITALIZE_FLAGS, i);
    }

    @ReactProp(name = "autoCorrect")
    public void setAutoCorrect(ReactEditText reactEditText, @Nullable Boolean bool) {
        int i;
        if (bool != null) {
            i = bool.booleanValue() ? 32768 : 524288;
        } else {
            i = 0;
        }
        updateStagedInputTypeFlag(reactEditText, 557056, i);
    }

    @ReactProp(defaultBoolean = false, name = "autoFocus")
    public void setAutoFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setAutoFocus(z);
    }

    @ReactProp(name = "blurOnSubmit")
    public void setBlurOnSubmit(ReactEditText reactEditText, @Nullable Boolean bool) {
        reactEditText.setBlurOnSubmit(bool);
    }

    @ReactPropGroup(customType = "Color", names = {ViewProps.BORDER_COLOR, ViewProps.BORDER_LEFT_COLOR, ViewProps.BORDER_RIGHT_COLOR, ViewProps.BORDER_TOP_COLOR, ViewProps.BORDER_BOTTOM_COLOR})
    public void setBorderColor(ReactEditText reactEditText, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : num.intValue() & ViewCompat.MEASURED_SIZE_MASK;
        if (num != null) {
            f = num.intValue() >>> 24;
        }
        reactEditText.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {ViewProps.BORDER_RADIUS, ViewProps.BORDER_TOP_LEFT_RADIUS, ViewProps.BORDER_TOP_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_LEFT_RADIUS})
    public void setBorderRadius(ReactEditText reactEditText, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactEditText.setBorderRadius(f);
        } else {
            reactEditText.setBorderRadius(f, i - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactEditText reactEditText, @Nullable String str) {
        reactEditText.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {ViewProps.BORDER_WIDTH, ViewProps.BORDER_LEFT_WIDTH, ViewProps.BORDER_RIGHT_WIDTH, ViewProps.BORDER_TOP_WIDTH, ViewProps.BORDER_BOTTOM_WIDTH})
    public void setBorderWidth(ReactEditText reactEditText, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        reactEditText.setBorderWidth(SPACING_TYPES[i], f);
    }

    @ReactProp(defaultBoolean = false, name = "caretHidden")
    public void setCaretHidden(ReactEditText reactEditText, boolean z) {
        if (reactEditText.getStagedInputType() != 32 || !shouldHideCursorForEmailTextInput()) {
            reactEditText.setCursorVisible(!z);
        }
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactEditText reactEditText, @Nullable Integer num) {
        if (num == null) {
            ColorStateList defaultTextColor = DefaultStyleValuesUtil.getDefaultTextColor(reactEditText.getContext());
            if (defaultTextColor != null) {
                reactEditText.setTextColor(defaultTextColor);
                return;
            }
            Context context = reactEditText.getContext();
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not get default text color from View Context: ");
            outline107.append(context != null ? context.getClass().getCanonicalName() : "null");
            ReactSoftException.logSoftException(str, new IllegalStateException(outline107.toString()));
            return;
        }
        reactEditText.setTextColor(num.intValue());
    }

    @ReactProp(defaultBoolean = false, name = "contextMenuHidden")
    public void setContextMenuHidden(ReactEditText reactEditText, final boolean z) {
        reactEditText.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.facebook.react.views.textinput.ReactTextInputManager.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return z;
            }
        });
    }

    @ReactProp(customType = "Color", name = "cursorColor")
    public void setCursorColor(ReactEditText reactEditText, @Nullable Integer num) {
        if (num == null) {
            return;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            Drawable textCursorDrawable = reactEditText.getTextCursorDrawable();
            if (textCursorDrawable == null) {
                return;
            }
            textCursorDrawable.setColorFilter(new BlendModeColorFilter(num.intValue(), BlendMode.SRC_IN));
            reactEditText.setTextCursorDrawable(textCursorDrawable);
        } else if (i == 28) {
        } else {
            try {
                Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
                declaredField.setAccessible(true);
                int i2 = declaredField.getInt(reactEditText);
                if (i2 == 0) {
                    return;
                }
                Drawable drawable = ContextCompat.getDrawable(reactEditText.getContext(), i2);
                drawable.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
                Drawable[] drawableArr = {drawable, drawable};
                Field declaredField2 = TextView.class.getDeclaredField("mEditor");
                declaredField2.setAccessible(true);
                Object obj = declaredField2.get(reactEditText);
                Field declaredField3 = obj.getClass().getDeclaredField("mCursorDrawable");
                declaredField3.setAccessible(true);
                declaredField3.set(obj, drawableArr);
            } catch (IllegalAccessException | NoSuchFieldException unused) {
            }
        }
    }

    @ReactProp(defaultBoolean = false, name = "disableFullscreenUI")
    public void setDisableFullscreenUI(ReactEditText reactEditText, boolean z) {
        reactEditText.setDisableFullscreenUI(z);
    }

    @ReactProp(defaultBoolean = true, name = "editable")
    public void setEditable(ReactEditText reactEditText, boolean z) {
        reactEditText.setEnabled(z);
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(ReactEditText reactEditText, String str) {
        reactEditText.setFontFamily(str);
    }

    @ReactProp(defaultFloat = ViewDefaults.FONT_SIZE_SP, name = "fontSize")
    public void setFontSize(ReactEditText reactEditText, float f) {
        reactEditText.setFontSize(f);
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(ReactEditText reactEditText, @Nullable String str) {
        reactEditText.setFontStyle(str);
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(ReactEditText reactEditText, @Nullable String str) {
        reactEditText.setFontWeight(str);
    }

    @ReactProp(name = "importantForAutofill")
    public void setImportantForAutofill(ReactEditText reactEditText, @Nullable String str) {
        int i;
        if ("no".equals(str)) {
            i = 2;
        } else if ("noExcludeDescendants".equals(str)) {
            i = 8;
        } else if ("yes".equals(str)) {
            i = 1;
        } else {
            i = "yesExcludeDescendants".equals(str) ? 4 : 0;
        }
        setImportantForAutofill(reactEditText, i);
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.INCLUDE_FONT_PADDING)
    public void setIncludeFontPadding(ReactEditText reactEditText, boolean z) {
        reactEditText.setIncludeFontPadding(z);
    }

    @ReactProp(name = "inlineImageLeft")
    public void setInlineImageLeft(ReactEditText reactEditText, @Nullable String str) {
        reactEditText.setCompoundDrawablesWithIntrinsicBounds(ResourceDrawableIdHelper.getInstance().getResourceDrawableId(reactEditText.getContext(), str), 0, 0, 0);
    }

    @ReactProp(name = "inlineImagePadding")
    public void setInlineImagePadding(ReactEditText reactEditText, int i) {
        reactEditText.setCompoundDrawablePadding(i);
    }

    @ReactProp(name = "keyboardType")
    public void setKeyboardType(ReactEditText reactEditText, @Nullable String str) {
        int i;
        if (KEYBOARD_TYPE_NUMERIC.equalsIgnoreCase(str)) {
            i = 12290;
        } else if (KEYBOARD_TYPE_NUMBER_PAD.equalsIgnoreCase(str)) {
            i = 2;
        } else if (KEYBOARD_TYPE_DECIMAL_PAD.equalsIgnoreCase(str)) {
            i = 8194;
        } else if (KEYBOARD_TYPE_EMAIL_ADDRESS.equalsIgnoreCase(str)) {
            i = 33;
            if (shouldHideCursorForEmailTextInput()) {
                reactEditText.setCursorVisible(false);
            }
        } else if (KEYBOARD_TYPE_PHONE_PAD.equalsIgnoreCase(str)) {
            i = 3;
        } else {
            i = KEYBOARD_TYPE_VISIBLE_PASSWORD.equalsIgnoreCase(str) ? 144 : 1;
        }
        updateStagedInputTypeFlag(reactEditText, 15, i);
        checkPasswordType(reactEditText);
    }

    @ReactProp(defaultFloat = 0.0f, name = ViewProps.LETTER_SPACING)
    public void setLetterSpacing(ReactEditText reactEditText, float f) {
        reactEditText.setLetterSpacingPt(f);
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.MAX_FONT_SIZE_MULTIPLIER)
    public void setMaxFontSizeMultiplier(ReactEditText reactEditText, float f) {
        reactEditText.setMaxFontSizeMultiplier(f);
    }

    @ReactProp(name = "maxLength")
    public void setMaxLength(ReactEditText reactEditText, @Nullable Integer num) {
        InputFilter[] filters = reactEditText.getFilters();
        InputFilter[] inputFilterArr = EMPTY_FILTERS;
        if (num == null) {
            if (filters.length > 0) {
                LinkedList linkedList = new LinkedList();
                for (int i = 0; i < filters.length; i++) {
                    if (!(filters[i] instanceof InputFilter.LengthFilter)) {
                        linkedList.add(filters[i]);
                    }
                }
                if (!linkedList.isEmpty()) {
                    inputFilterArr = (InputFilter[]) linkedList.toArray(new InputFilter[linkedList.size()]);
                }
            }
        } else if (filters.length > 0) {
            boolean z = false;
            for (int i2 = 0; i2 < filters.length; i2++) {
                if (filters[i2] instanceof InputFilter.LengthFilter) {
                    filters[i2] = new InputFilter.LengthFilter(num.intValue());
                    z = true;
                }
            }
            if (!z) {
                inputFilterArr = new InputFilter[filters.length + 1];
                System.arraycopy(filters, 0, inputFilterArr, 0, filters.length);
                filters[filters.length] = new InputFilter.LengthFilter(num.intValue());
            } else {
                inputFilterArr = filters;
            }
        } else {
            inputFilterArr = new InputFilter[]{new InputFilter.LengthFilter(num.intValue())};
        }
        reactEditText.setFilters(inputFilterArr);
    }

    @ReactProp(defaultBoolean = false, name = "multiline")
    public void setMultiline(ReactEditText reactEditText, boolean z) {
        int i = 0;
        int i2 = z ? 0 : 131072;
        if (z) {
            i = 131072;
        }
        updateStagedInputTypeFlag(reactEditText, i2, i);
    }

    @ReactProp(defaultInt = 1, name = ViewProps.NUMBER_OF_LINES)
    public void setNumLines(ReactEditText reactEditText, int i) {
        reactEditText.setLines(i);
    }

    @ReactProp(defaultBoolean = false, name = "onContentSizeChange")
    public void setOnContentSizeChange(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setContentSizeWatcher(new ReactContentSizeWatcher(reactEditText));
        } else {
            reactEditText.setContentSizeWatcher(null);
        }
    }

    @ReactProp(defaultBoolean = false, name = "onKeyPress")
    public void setOnKeyPress(ReactEditText reactEditText, boolean z) {
        reactEditText.setOnKeyPress(z);
    }

    @ReactProp(defaultBoolean = false, name = "onScroll")
    public void setOnScroll(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setScrollWatcher(new ReactScrollWatcher(reactEditText));
        } else {
            reactEditText.setScrollWatcher(null);
        }
    }

    @ReactProp(defaultBoolean = false, name = "onSelectionChange")
    public void setOnSelectionChange(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setSelectionWatcher(new ReactSelectionWatcher(reactEditText));
        } else {
            reactEditText.setSelectionWatcher(null);
        }
    }

    @ReactProp(name = ReactTextInputShadowNode.PROP_PLACEHOLDER)
    public void setPlaceholder(ReactEditText reactEditText, @Nullable String str) {
        reactEditText.setHint(str);
    }

    @ReactProp(customType = "Color", name = "placeholderTextColor")
    public void setPlaceholderTextColor(ReactEditText reactEditText, @Nullable Integer num) {
        if (num == null) {
            reactEditText.setHintTextColor(DefaultStyleValuesUtil.getDefaultTextColorHint(reactEditText.getContext()));
        } else {
            reactEditText.setHintTextColor(num.intValue());
        }
    }

    @ReactProp(name = "returnKeyLabel")
    public void setReturnKeyLabel(ReactEditText reactEditText, String str) {
        reactEditText.setImeActionLabel(str, IME_ACTION_ID);
    }

    @ReactProp(name = "returnKeyType")
    public void setReturnKeyType(ReactEditText reactEditText, String str) {
        reactEditText.setReturnKeyType(str);
    }

    @ReactProp(defaultBoolean = false, name = "secureTextEntry")
    public void setSecureTextEntry(ReactEditText reactEditText, boolean z) {
        updateStagedInputTypeFlag(reactEditText, 144, z ? 128 : 0);
        checkPasswordType(reactEditText);
    }

    @ReactProp(defaultBoolean = false, name = "selectTextOnFocus")
    public void setSelectTextOnFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setSelectAllOnFocus(z);
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactEditText reactEditText, @Nullable Integer num) {
        if (num == null) {
            reactEditText.setHighlightColor(DefaultStyleValuesUtil.getDefaultTextColorHighlight(reactEditText.getContext()));
        } else {
            reactEditText.setHighlightColor(num.intValue());
        }
        setCursorColor(reactEditText, num);
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(ReactEditText reactEditText, @Nullable String str) {
        if ("justify".equals(str)) {
            int i = Build.VERSION.SDK_INT;
            reactEditText.setJustificationMode(1);
            reactEditText.setGravityHorizontal(3);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        reactEditText.setJustificationMode(0);
        if (str != null && !"auto".equals(str)) {
            if ("left".equals(str)) {
                reactEditText.setGravityHorizontal(3);
                return;
            } else if ("right".equals(str)) {
                reactEditText.setGravityHorizontal(5);
                return;
            } else if ("center".equals(str)) {
                reactEditText.setGravityHorizontal(1);
                return;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid textAlign: ", str));
            }
        }
        reactEditText.setGravityHorizontal(0);
    }

    @ReactProp(name = ViewProps.TEXT_ALIGN_VERTICAL)
    public void setTextAlignVertical(ReactEditText reactEditText, @Nullable String str) {
        if (str != null && !"auto".equals(str)) {
            if (ViewProps.TOP.equals(str)) {
                reactEditText.setGravityVertical(48);
                return;
            } else if (ViewProps.BOTTOM.equals(str)) {
                reactEditText.setGravityVertical(80);
                return;
            } else if ("center".equals(str)) {
                reactEditText.setGravityVertical(16);
                return;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid textAlignVertical: ", str));
            }
        }
        reactEditText.setGravityVertical(0);
    }

    @ReactProp(name = "autoCompleteType")
    public void setTextContentType(ReactEditText reactEditText, @Nullable String str) {
        if (str == null) {
            setImportantForAutofill(reactEditText, 2);
        } else if ("username".equals(str)) {
            setAutofillHints(reactEditText, "username");
        } else if (MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD.equals(str)) {
            setAutofillHints(reactEditText, MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD);
        } else if ("email".equals(str)) {
            setAutofillHints(reactEditText, ContactProviderContract.EMAIL_ADDRESS_PATH);
        } else if ("name".equals(str)) {
            setAutofillHints(reactEditText, "name");
        } else if ("tel".equals(str)) {
            setAutofillHints(reactEditText, "phone");
        } else if ("street-address".equals(str)) {
            setAutofillHints(reactEditText, "postalAddress");
        } else if ("postal-code".equals(str)) {
            setAutofillHints(reactEditText, "postalCode");
        } else if ("cc-number".equals(str)) {
            setAutofillHints(reactEditText, "creditCardNumber");
        } else if ("cc-csc".equals(str)) {
            setAutofillHints(reactEditText, "creditCardSecurityCode");
        } else if ("cc-exp".equals(str)) {
            setAutofillHints(reactEditText, "creditCardExpirationDate");
        } else if ("cc-exp-month".equals(str)) {
            setAutofillHints(reactEditText, "creditCardExpirationMonth");
        } else if ("cc-exp-year".equals(str)) {
            setAutofillHints(reactEditText, "creditCardExpirationYear");
        } else if ("off".equals(str)) {
            setImportantForAutofill(reactEditText, 2);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid autoCompleteType: ", str));
        }
    }

    @ReactProp(customType = "Color", name = "underlineColorAndroid")
    public void setUnderlineColor(ReactEditText reactEditText, @Nullable Integer num) {
        Drawable background = reactEditText.getBackground();
        if (background.getConstantState() != null) {
            try {
                background = background.mutate();
            } catch (NullPointerException e) {
                FLog.e(TAG, "NullPointerException when setting underlineColorAndroid for TextInput", e);
            }
        }
        if (num == null) {
            background.clearColorFilter();
        } else {
            background.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @ReactProp(defaultBoolean = true, name = "showSoftInputOnFocus")
    public void showKeyboardOnFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setShowSoftInputOnFocus(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(final ThemedReactContext themedReactContext, final ReactEditText reactEditText) {
        reactEditText.addTextChangedListener(new ReactTextInputTextWatcher(themedReactContext, reactEditText));
        reactEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.facebook.react.views.textinput.ReactTextInputManager.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                EventDispatcher eventDispatcher = ReactTextInputManager.getEventDispatcher(themedReactContext, reactEditText);
                if (z) {
                    eventDispatcher.dispatchEvent(new ReactTextInputFocusEvent(reactEditText.getId()));
                    return;
                }
                eventDispatcher.dispatchEvent(new ReactTextInputBlurEvent(reactEditText.getId()));
                eventDispatcher.dispatchEvent(new ReactTextInputEndEditingEvent(reactEditText.getId(), reactEditText.mo99getText().toString()));
            }
        });
        reactEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.facebook.react.views.textinput.ReactTextInputManager.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i & 255) != 0 || i == 0) {
                    boolean blurOnSubmit = reactEditText.getBlurOnSubmit();
                    boolean isMultiline = reactEditText.isMultiline();
                    ReactTextInputManager.getEventDispatcher(themedReactContext, reactEditText).dispatchEvent(new ReactTextInputSubmitEditingEvent(reactEditText.getId(), reactEditText.mo99getText().toString()));
                    if (blurOnSubmit) {
                        reactEditText.clearFocus();
                    }
                    return blurOnSubmit || !isMultiline || i == 5 || i == 7;
                }
                return true;
            }
        });
    }

    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createShadowNodeInstance  reason: collision with other method in class */
    public ReactBaseTextShadowNode mo10136createShadowNodeInstance() {
        return new ReactTextInputShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactEditText mo12880createViewInstance(ThemedReactContext themedReactContext) {
        ReactEditText reactEditText = new ReactEditText(themedReactContext);
        reactEditText.setInputType(reactEditText.getInputType() & (-131073));
        reactEditText.setReturnKeyType(MetricName.DONE);
        return reactEditText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactEditText reactEditText) {
        super.onAfterUpdateTransaction((ReactTextInputManager) reactEditText);
        reactEditText.maybeUpdateTypeface();
        reactEditText.commitStagedInputType();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void setPadding(ReactEditText reactEditText, int i, int i2, int i3, int i4) {
        reactEditText.setPadding(i, i2, i3, i4);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(ReactEditText reactEditText, Object obj) {
        if (obj instanceof ReactTextUpdate) {
            ReactTextUpdate reactTextUpdate = (ReactTextUpdate) obj;
            int paddingLeft = (int) reactTextUpdate.getPaddingLeft();
            int paddingTop = (int) reactTextUpdate.getPaddingTop();
            int paddingRight = (int) reactTextUpdate.getPaddingRight();
            int paddingBottom = (int) reactTextUpdate.getPaddingBottom();
            if (paddingLeft != -1 || paddingTop != -1 || paddingRight != -1 || paddingBottom != -1) {
                if (paddingLeft == -1) {
                    paddingLeft = reactEditText.getPaddingLeft();
                }
                if (paddingTop == -1) {
                    paddingTop = reactEditText.getPaddingTop();
                }
                if (paddingRight == -1) {
                    paddingRight = reactEditText.getPaddingRight();
                }
                if (paddingBottom == -1) {
                    paddingBottom = reactEditText.getPaddingBottom();
                }
                reactEditText.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
            if (reactTextUpdate.containsImages()) {
                TextInlineImageSpan.possiblyUpdateInlineImageSpans(reactTextUpdate.getText(), reactEditText);
            }
            reactEditText.maybeSetTextFromState(reactTextUpdate);
            reactEditText.maybeSetSelection(reactTextUpdate.getJsEventCounter(), reactTextUpdate.getSelectionStart(), reactTextUpdate.getSelectionEnd());
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Object updateState(ReactEditText reactEditText, ReactStylesDiffMap reactStylesDiffMap, @Nullable StateWrapper stateWrapper) {
        reactEditText.getFabricViewStateManager().setStateWrapper(stateWrapper);
        ReadableNativeMap state = stateWrapper.getState();
        if (!state.hasKey("attributedString")) {
            return null;
        }
        ReadableNativeMap mo6945getMap = state.mo6945getMap("attributedString");
        ReadableNativeMap mo6945getMap2 = state.mo6945getMap("paragraphAttributes");
        if (mo6945getMap != null && mo6945getMap2 != null) {
            return ReactTextUpdate.buildReactTextUpdateFromState(TextLayoutManager.getOrCreateSpannableForText(reactEditText.getContext(), mo6945getMap, this.mReactTextViewManagerCallback), state.getInt("mostRecentEventCount"), TextAttributeProps.getTextAlignment(reactStylesDiffMap, TextLayoutManager.isRTL(mo6945getMap)), TextAttributeProps.getTextBreakStrategy(mo6945getMap2.getString(ViewProps.TEXT_BREAK_STRATEGY)), TextAttributeProps.getJustificationMode(reactStylesDiffMap), mo6945getMap.getArray("fragments").toArrayList().size() > 1);
        }
        throw new IllegalArgumentException("Invalid TextInput State was received as a parameters");
    }

    public ReactBaseTextShadowNode createShadowNodeInstance(@Nullable ReactTextViewManagerCallback reactTextViewManagerCallback) {
        return new ReactTextInputShadowNode(reactTextViewManagerCallback);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactEditText reactEditText, int i, @Nullable ReadableArray readableArray) {
        if (i == 1) {
            receiveCommand(reactEditText, "focus", readableArray);
        } else if (i == 2) {
            receiveCommand(reactEditText, "blur", readableArray);
        } else if (i == 3 || i != 4) {
        } else {
            receiveCommand(reactEditText, "setTextAndSelection", readableArray);
        }
    }

    private void setImportantForAutofill(ReactEditText reactEditText, int i) {
        int i2 = Build.VERSION.SDK_INT;
        reactEditText.setImportantForAutofill(i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactEditText reactEditText, String str, @Nullable ReadableArray readableArray) {
        boolean z;
        int i;
        switch (str.hashCode()) {
            case -1699362314:
                if (str.equals("blurTextInput")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 3027047:
                if (str.equals("blur")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 97604824:
                if (str.equals("focus")) {
                    z = false;
                    break;
                }
                z = true;
                break;
            case 1427010500:
                if (str.equals("setTextAndSelection")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1690703013:
                if (str.equals("focusTextInput")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        if (!z || z) {
            reactEditText.requestFocusFromJS();
        } else if (z || z) {
            reactEditText.clearFocusFromJS();
        } else if (!z || (i = readableArray.getInt(0)) == -1) {
        } else {
            String string = readableArray.getString(1);
            int i2 = readableArray.getInt(2);
            int i3 = readableArray.getInt(3);
            if (i3 == -1) {
                i3 = i2;
            }
            reactEditText.maybeSetTextFromJS(getReactTextUpdate(string, i, i2, i3));
            reactEditText.maybeSetSelection(i, i2, i3);
        }
    }
}
