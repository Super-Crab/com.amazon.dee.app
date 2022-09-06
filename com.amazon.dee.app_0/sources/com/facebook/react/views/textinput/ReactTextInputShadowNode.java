package com.facebook.react.views.textinput;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.room.FtsOptions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.ReactTextViewManagerCallback;
import com.facebook.react.views.view.MeasureUtil;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
@TargetApi(23)
@VisibleForTesting
/* loaded from: classes2.dex */
public class ReactTextInputShadowNode extends ReactBaseTextShadowNode implements YogaMeasureFunction {
    @VisibleForTesting
    public static final String PROP_PLACEHOLDER = "placeholder";
    @VisibleForTesting
    public static final String PROP_SELECTION = "selection";
    @VisibleForTesting
    public static final String PROP_TEXT = "text";
    @Nullable
    private EditText mInternalEditText;
    @Nullable
    private ReactTextInputLocalData mLocalData;
    private int mMostRecentEventCount;
    @Nullable
    private String mPlaceholder;
    private int mSelectionEnd;
    private int mSelectionStart;
    @Nullable
    private String mText;

    public ReactTextInputShadowNode(@Nullable ReactTextViewManagerCallback reactTextViewManagerCallback) {
        super(reactTextViewManagerCallback);
        this.mMostRecentEventCount = -1;
        this.mText = null;
        this.mPlaceholder = null;
        this.mSelectionStart = -1;
        this.mSelectionEnd = -1;
        int i = Build.VERSION.SDK_INT;
        this.mTextBreakStrategy = 1;
        initMeasureFunction();
    }

    private void initMeasureFunction() {
        setMeasureFunction(this);
    }

    protected EditText createInternalEditText() {
        return new EditText(getThemedContext());
    }

    @Nullable
    public String getPlaceholder() {
        return this.mPlaceholder;
    }

    @Nullable
    public String getText() {
        return this.mText;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtualAnchor() {
        return true;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isYogaLeafNode() {
        return true;
    }

    @Override // com.facebook.yoga.YogaMeasureFunction
    public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        EditText editText = (EditText) Assertions.assertNotNull(this.mInternalEditText);
        ReactTextInputLocalData reactTextInputLocalData = this.mLocalData;
        if (reactTextInputLocalData != null) {
            reactTextInputLocalData.apply(editText);
        } else {
            editText.setTextSize(0, this.mTextAttributes.getEffectiveFontSize());
            int i = this.mNumberOfLines;
            if (i != -1) {
                editText.setLines(i);
            }
            int i2 = Build.VERSION.SDK_INT;
            int breakStrategy = editText.getBreakStrategy();
            int i3 = this.mTextBreakStrategy;
            if (breakStrategy != i3) {
                editText.setBreakStrategy(i3);
            }
        }
        editText.setHint(getPlaceholder());
        editText.measure(MeasureUtil.getMeasureSpec(f, yogaMeasureMode), MeasureUtil.getMeasureSpec(f2, yogaMeasureMode2));
        return YogaMeasureOutput.make(editText.getMeasuredWidth(), editText.getMeasuredHeight());
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onCollectExtraUpdates(UIViewOperationQueue uIViewOperationQueue) {
        super.onCollectExtraUpdates(uIViewOperationQueue);
        if (this.mMostRecentEventCount != -1) {
            uIViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(spannedFromShadowNode(this, getText(), false, null), this.mMostRecentEventCount, this.mContainsImages, getPadding(0), getPadding(1), getPadding(2), getPadding(3), this.mTextAlign, this.mTextBreakStrategy, this.mJustificationMode, this.mSelectionStart, this.mSelectionEnd));
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setLocalData(Object obj) {
        Assertions.assertCondition(obj instanceof ReactTextInputLocalData);
        this.mLocalData = (ReactTextInputLocalData) obj;
        dirty();
    }

    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(int i) {
        this.mMostRecentEventCount = i;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setPadding(int i, float f) {
        super.setPadding(i, f);
        markUpdated();
    }

    @ReactProp(name = PROP_PLACEHOLDER)
    public void setPlaceholder(@Nullable String str) {
        this.mPlaceholder = str;
        markUpdated();
    }

    @ReactProp(name = PROP_SELECTION)
    public void setSelection(@Nullable ReadableMap readableMap) {
        this.mSelectionEnd = -1;
        this.mSelectionStart = -1;
        if (readableMap != null && readableMap.hasKey("start") && readableMap.hasKey("end")) {
            this.mSelectionStart = readableMap.getInt("start");
            this.mSelectionEnd = readableMap.getInt("end");
            markUpdated();
        }
    }

    @ReactProp(name = "text")
    public void setText(@Nullable String str) {
        this.mText = str;
        if (str != null) {
            if (this.mSelectionStart > str.length()) {
                this.mSelectionStart = str.length();
            }
            if (this.mSelectionEnd > str.length()) {
                this.mSelectionEnd = str.length();
            }
        } else {
            this.mSelectionStart = -1;
            this.mSelectionEnd = -1;
        }
        markUpdated();
    }

    @Override // com.facebook.react.views.text.ReactBaseTextShadowNode
    public void setTextBreakStrategy(@Nullable String str) {
        int i = Build.VERSION.SDK_INT;
        if (str != null && !FtsOptions.TOKENIZER_SIMPLE.equals(str)) {
            if ("highQuality".equals(str)) {
                this.mTextBreakStrategy = 1;
                return;
            } else if ("balanced".equals(str)) {
                this.mTextBreakStrategy = 2;
                return;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid textBreakStrategy: ", str));
            }
        }
        this.mTextBreakStrategy = 0;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setThemedContext(ThemedReactContext themedReactContext) {
        super.setThemedContext(themedReactContext);
        EditText createInternalEditText = createInternalEditText();
        setDefaultPadding(4, ViewCompat.getPaddingStart(createInternalEditText));
        setDefaultPadding(1, createInternalEditText.getPaddingTop());
        setDefaultPadding(5, ViewCompat.getPaddingEnd(createInternalEditText));
        setDefaultPadding(3, createInternalEditText.getPaddingBottom());
        this.mInternalEditText = createInternalEditText;
        this.mInternalEditText.setPadding(0, 0, 0, 0);
        this.mInternalEditText.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    }

    public ReactTextInputShadowNode() {
        this(null);
    }
}
