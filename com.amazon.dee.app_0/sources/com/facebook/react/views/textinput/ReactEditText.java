package com.facebook.react.views.textinput;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.QwertyKeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.text.CustomLetterSpacingSpan;
import com.facebook.react.views.text.CustomLineHeightSpan;
import com.facebook.react.views.text.CustomStyleSpan;
import com.facebook.react.views.text.ReactAbsoluteSizeSpan;
import com.facebook.react.views.text.ReactSpan;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.ReactTypefaceUtils;
import com.facebook.react.views.text.TextAttributes;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.react.views.text.TextLayoutManager;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes2.dex */
public class ReactEditText extends AppCompatEditText implements FabricViewStateManager.HasFabricViewStateManager {
    public static final boolean DEBUG_MODE = false;
    private static final int UNSET = -1;
    private static final KeyListener sKeyListener = QwertyKeyListener.getInstanceForFullKeyboard();
    private final String TAG;
    private boolean mAutoFocus;
    @Nullable
    private Boolean mBlurOnSubmit;
    protected boolean mContainsImages;
    @Nullable
    private ContentSizeWatcher mContentSizeWatcher;
    private int mDefaultGravityHorizontal;
    private int mDefaultGravityVertical;
    private boolean mDetectScrollMovement;
    private boolean mDidAttachToWindow;
    private boolean mDisableFullscreen;
    protected boolean mDisableTextDiffing;
    private final FabricViewStateManager mFabricViewStateManager;
    @Nullable
    private String mFontFamily;
    private int mFontStyle;
    private int mFontWeight;
    private final InputMethodManager mInputMethodManager;
    protected boolean mIsSettingTextFromCacheUpdate;
    protected boolean mIsSettingTextFromJS;
    protected boolean mIsSettingTextFromState;
    private final InternalKeyListener mKeyListener;
    @Nullable
    private ArrayList<TextWatcher> mListeners;
    protected int mNativeEventCount;
    private boolean mOnKeyPress;
    private ReactViewBackgroundManager mReactBackgroundManager;
    @Nullable
    private String mReturnKeyType;
    @Nullable
    private ScrollWatcher mScrollWatcher;
    @Nullable
    private SelectionWatcher mSelectionWatcher;
    private int mStagedInputType;
    private TextAttributes mTextAttributes;
    @Nullable
    private TextWatcherDelegator mTextWatcherDelegator;
    private boolean mTypefaceDirty;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class InternalKeyListener implements KeyListener {
        private int mInputType = 0;

        @Override // android.text.method.KeyListener
        public void clearMetaKeyState(View view, Editable editable, int i) {
            ReactEditText.sKeyListener.clearMetaKeyState(view, editable, i);
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return this.mInputType;
        }

        @Override // android.text.method.KeyListener
        public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyDown(view, editable, i, keyEvent);
        }

        @Override // android.text.method.KeyListener
        public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyOther(view, editable, keyEvent);
        }

        @Override // android.text.method.KeyListener
        public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyUp(view, editable, i, keyEvent);
        }

        public void setInputType(int i) {
            this.mInputType = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class TextWatcherDelegator implements TextWatcher {
        private TextWatcherDelegator() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            ReactEditText reactEditText = ReactEditText.this;
            if (reactEditText.mIsSettingTextFromCacheUpdate || reactEditText.mIsSettingTextFromJS || reactEditText.mListeners == null) {
                return;
            }
            Iterator it2 = ReactEditText.this.mListeners.iterator();
            while (it2.hasNext()) {
                ((TextWatcher) it2.next()).afterTextChanged(editable);
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ReactEditText reactEditText = ReactEditText.this;
            if (reactEditText.mIsSettingTextFromCacheUpdate || reactEditText.mIsSettingTextFromJS || reactEditText.mListeners == null) {
                return;
            }
            Iterator it2 = ReactEditText.this.mListeners.iterator();
            while (it2.hasNext()) {
                ((TextWatcher) it2.next()).beforeTextChanged(charSequence, i, i2, i3);
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ReactEditText reactEditText = ReactEditText.this;
            if (!reactEditText.mIsSettingTextFromCacheUpdate) {
                if (!reactEditText.mIsSettingTextFromJS && reactEditText.mListeners != null) {
                    Iterator it2 = ReactEditText.this.mListeners.iterator();
                    while (it2.hasNext()) {
                        ((TextWatcher) it2.next()).onTextChanged(charSequence, i, i2, i3);
                    }
                }
                ReactEditText reactEditText2 = ReactEditText.this;
                reactEditText2.updateCachedSpannable(!reactEditText2.mIsSettingTextFromJS && !reactEditText2.mIsSettingTextFromState && i == 0 && i2 == 0);
            }
            ReactEditText.this.onContentSizeChange();
        }
    }

    public ReactEditText(Context context) {
        super(context);
        this.TAG = ReactEditText.class.getSimpleName();
        this.mIsSettingTextFromCacheUpdate = false;
        this.mDetectScrollMovement = false;
        this.mOnKeyPress = false;
        this.mTypefaceDirty = false;
        this.mFontFamily = null;
        this.mFontWeight = -1;
        this.mFontStyle = -1;
        this.mAutoFocus = false;
        this.mDidAttachToWindow = false;
        this.mFabricViewStateManager = new FabricViewStateManager();
        this.mDisableTextDiffing = false;
        this.mIsSettingTextFromState = false;
        setFocusableInTouchMode(false);
        this.mReactBackgroundManager = new ReactViewBackgroundManager(this);
        this.mInputMethodManager = (InputMethodManager) Assertions.assertNotNull(context.getSystemService("input_method"));
        this.mDefaultGravityHorizontal = getGravity() & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        this.mDefaultGravityVertical = getGravity() & 112;
        this.mNativeEventCount = 0;
        this.mIsSettingTextFromJS = false;
        this.mBlurOnSubmit = null;
        this.mDisableFullscreen = false;
        this.mListeners = null;
        this.mTextWatcherDelegator = null;
        this.mStagedInputType = getInputType();
        this.mKeyListener = new InternalKeyListener();
        this.mScrollWatcher = null;
        this.mTextAttributes = new TextAttributes();
        applyTextAttributes();
        if (Build.VERSION.SDK_INT <= 27) {
            setLayerType(1, null);
        }
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: com.facebook.react.views.textinput.ReactEditText.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i == 16) {
                    int length = ReactEditText.this.mo99getText().length();
                    if (length > 0) {
                        ReactEditText.this.setSelection(length);
                    }
                    return ReactEditText.this.requestFocusInternal();
                }
                return super.performAccessibilityAction(view, i, bundle);
            }
        });
    }

    private void addSpansForMeasurement(Spannable spannable) {
        Object[] spans;
        if (!this.mFabricViewStateManager.hasStateWrapper()) {
            return;
        }
        boolean z = this.mDisableTextDiffing;
        this.mDisableTextDiffing = true;
        int length = spannable.length();
        int i = 0;
        for (Object obj : spannable.getSpans(0, length(), Object.class)) {
            int spanFlags = spannable.getSpanFlags(obj);
            if (((spanFlags & 18) == 18 || (spanFlags & 17) == 17) && (obj instanceof ReactSpan) && spannable.getSpanStart(obj) == 0 && spannable.getSpanEnd(obj) == length) {
                spannable.removeSpan(obj);
            }
        }
        ArrayList<TextLayoutManager.SetSpanOperation> arrayList = new ArrayList();
        int i2 = Build.VERSION.SDK_INT;
        if (!Float.isNaN(this.mTextAttributes.getLetterSpacing())) {
            arrayList.add(new TextLayoutManager.SetSpanOperation(0, length, new CustomLetterSpacingSpan(this.mTextAttributes.getLetterSpacing())));
        }
        arrayList.add(new TextLayoutManager.SetSpanOperation(0, length, new ReactAbsoluteSizeSpan(this.mTextAttributes.getEffectiveFontSize())));
        if (this.mFontStyle != -1 || this.mFontWeight != -1 || this.mFontFamily != null) {
            arrayList.add(new TextLayoutManager.SetSpanOperation(0, length, new CustomStyleSpan(this.mFontStyle, this.mFontWeight, null, this.mFontFamily, UIManagerHelper.getReactContext(this).getAssets())));
        }
        if (!Float.isNaN(this.mTextAttributes.getEffectiveLineHeight())) {
            arrayList.add(new TextLayoutManager.SetSpanOperation(0, length, new CustomLineHeightSpan(this.mTextAttributes.getEffectiveLineHeight())));
        }
        for (TextLayoutManager.SetSpanOperation setSpanOperation : arrayList) {
            setSpanOperation.execute(spannable, i);
            i++;
        }
        this.mDisableTextDiffing = z;
    }

    private TextWatcherDelegator getTextWatcherDelegator() {
        if (this.mTextWatcherDelegator == null) {
            this.mTextWatcherDelegator = new TextWatcherDelegator();
        }
        return this.mTextWatcherDelegator;
    }

    private boolean isSecureText() {
        return (getInputType() & 144) != 0;
    }

    private void manageSpans(SpannableStringBuilder spannableStringBuilder, boolean z) {
        Object[] spans;
        for (Object obj : mo99getText().getSpans(0, length(), Object.class)) {
            int spanFlags = mo99getText().getSpanFlags(obj);
            boolean z2 = (spanFlags & 33) == 33;
            if (obj instanceof ReactSpan) {
                mo99getText().removeSpan(obj);
            }
            if (z2) {
                int spanStart = mo99getText().getSpanStart(obj);
                int spanEnd = mo99getText().getSpanEnd(obj);
                mo99getText().removeSpan(obj);
                if (sameTextForSpan(mo99getText(), spannableStringBuilder, spanStart, spanEnd)) {
                    spannableStringBuilder.setSpan(obj, spanStart, spanEnd, spanFlags);
                }
            }
        }
        if (!z) {
            addSpansForMeasurement(mo99getText());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onContentSizeChange() {
        ContentSizeWatcher contentSizeWatcher = this.mContentSizeWatcher;
        if (contentSizeWatcher != null) {
            contentSizeWatcher.onLayout();
        }
        setIntrinsicContentSize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean requestFocusInternal() {
        setFocusableInTouchMode(true);
        boolean requestFocus = super.requestFocus(130, null);
        if (getShowSoftInputOnFocus()) {
            showSoftKeyboard();
        }
        return requestFocus;
    }

    private static boolean sameTextForSpan(Editable editable, SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        if (i > spannableStringBuilder.length() || i2 > spannableStringBuilder.length()) {
            return false;
        }
        while (i < i2) {
            if (editable.charAt(i) != spannableStringBuilder.charAt(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    private void setIntrinsicContentSize() {
        if (!this.mFabricViewStateManager.hasStateWrapper()) {
            ReactContext reactContext = UIManagerHelper.getReactContext(this);
            ReactTextInputLocalData reactTextInputLocalData = new ReactTextInputLocalData(this);
            UIManagerModule uIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
            if (uIManagerModule == null) {
                return;
            }
            uIManagerModule.setViewLocalData(getId(), reactTextInputLocalData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCachedSpannable(boolean z) {
        if (this.mFabricViewStateManager.hasStateWrapper() && getId() != -1) {
            boolean z2 = true;
            if (z) {
                this.mIsSettingTextFromCacheUpdate = true;
                addSpansForMeasurement(mo99getText());
                this.mIsSettingTextFromCacheUpdate = false;
            }
            Editable mo99getText = mo99getText();
            if (mo99getText == null || mo99getText.length() <= 0) {
                z2 = false;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (z2) {
                try {
                    spannableStringBuilder.append(mo99getText.subSequence(0, mo99getText.length()));
                } catch (IndexOutOfBoundsException e) {
                    ReactSoftException.logSoftException(this.TAG, e);
                }
            }
            if (!z2) {
                if (getHint() != null && getHint().length() > 0) {
                    spannableStringBuilder.append(getHint());
                } else {
                    spannableStringBuilder.append((CharSequence) "I");
                }
                addSpansForMeasurement(spannableStringBuilder);
            }
            TextLayoutManager.setCachedSpannabledForTag(getId(), spannableStringBuilder);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void updateImeOptions() {
        /*
            r9 = this;
            java.lang.String r0 = r9.mReturnKeyType
            r1 = 4
            r2 = 3
            r3 = 1
            r4 = 5
            r5 = 2
            r6 = 6
            if (r0 == 0) goto L6b
            r7 = -1
            int r8 = r0.hashCode()
            switch(r8) {
                case -1273775369: goto L51;
                case -906336856: goto L46;
                case 3304: goto L3c;
                case 3089282: goto L32;
                case 3377907: goto L28;
                case 3387192: goto L1e;
                case 3526536: goto L13;
                default: goto L12;
            }
        L12:
            goto L5c
        L13:
            java.lang.String r8 = "send"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L5c
            r0 = r4
            goto L5d
        L1e:
            java.lang.String r8 = "none"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L5c
            r0 = r5
            goto L5d
        L28:
            java.lang.String r8 = "next"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L5c
            r0 = r3
            goto L5d
        L32:
            java.lang.String r8 = "done"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L5c
            r0 = r6
            goto L5d
        L3c:
            java.lang.String r8 = "go"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L5c
            r0 = 0
            goto L5d
        L46:
            java.lang.String r8 = "search"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L5c
            r0 = r1
            goto L5d
        L51:
            java.lang.String r8 = "previous"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L5c
            r0 = r2
            goto L5d
        L5c:
            r0 = r7
        L5d:
            switch(r0) {
                case 0: goto L69;
                case 1: goto L67;
                case 2: goto L65;
                case 3: goto L63;
                case 4: goto L61;
                case 5: goto L6c;
                case 6: goto L6b;
                default: goto L60;
            }
        L60:
            goto L6b
        L61:
            r1 = r2
            goto L6c
        L63:
            r1 = 7
            goto L6c
        L65:
            r1 = r3
            goto L6c
        L67:
            r1 = r4
            goto L6c
        L69:
            r1 = r5
            goto L6c
        L6b:
            r1 = r6
        L6c:
            boolean r0 = r9.mDisableFullscreen
            if (r0 == 0) goto L77
            r0 = 33554432(0x2000000, float:9.403955E-38)
            r0 = r0 | r1
            r9.setImeOptions(r0)
            goto L7a
        L77:
            r9.setImeOptions(r1)
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.ReactEditText.updateImeOptions():void");
    }

    @Override // android.widget.TextView
    public void addTextChangedListener(TextWatcher textWatcher) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
            super.addTextChangedListener(getTextWatcherDelegator());
        }
        this.mListeners.add(textWatcher);
    }

    protected void applyTextAttributes() {
        setTextSize(0, this.mTextAttributes.getEffectiveFontSize());
        int i = Build.VERSION.SDK_INT;
        float effectiveLetterSpacing = this.mTextAttributes.getEffectiveLetterSpacing();
        if (!Float.isNaN(effectiveLetterSpacing)) {
            setLetterSpacing(effectiveLetterSpacing);
        }
    }

    public boolean canUpdateWithEventCount(int i) {
        return i >= this.mNativeEventCount;
    }

    @Override // android.view.View
    public void clearFocus() {
        setFocusableInTouchMode(false);
        super.clearFocus();
        hideSoftKeyboard();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearFocusFromJS() {
        clearFocus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void commitStagedInputType() {
        if (getInputType() != this.mStagedInputType) {
            int selectionStart = getSelectionStart();
            int selectionEnd = getSelectionEnd();
            setInputType(this.mStagedInputType);
            setSelection(selectionStart, selectionEnd);
        }
    }

    protected void finalize() {
        TextLayoutManager.deleteCachedSpannableForTag(getId());
    }

    public boolean getBlurOnSubmit() {
        Boolean bool = this.mBlurOnSubmit;
        if (bool == null) {
            return !isMultiline();
        }
        return bool.booleanValue();
    }

    public boolean getDisableFullscreenUI() {
        return this.mDisableFullscreen;
    }

    @Override // com.facebook.react.uimanager.FabricViewStateManager.HasFabricViewStateManager
    public FabricViewStateManager getFabricViewStateManager() {
        return this.mFabricViewStateManager;
    }

    public String getReturnKeyType() {
        return this.mReturnKeyType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStagedInputType() {
        return this.mStagedInputType;
    }

    protected void hideSoftKeyboard() {
        this.mInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    public int incrementAndGetEventCounter() {
        int i = this.mNativeEventCount + 1;
        this.mNativeEventCount = i;
        return i;
    }

    @Override // android.widget.TextView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (this.mContainsImages) {
            Editable mo99getText = mo99getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) mo99getText.getSpans(0, mo99getText.length(), TextInlineImageSpan.class)) {
                if (textInlineImageSpan.getDrawable() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    @Override // android.view.View
    public boolean isLayoutRequested() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isMultiline() {
        return (getInputType() & 131072) != 0;
    }

    public void maybeSetSelection(int i, int i2, int i3) {
        if (!canUpdateWithEventCount(i) || i2 == -1 || i3 == -1) {
            return;
        }
        setSelection(i2, i3);
    }

    public void maybeSetText(ReactTextUpdate reactTextUpdate) {
        if ((!isSecureText() || !TextUtils.equals(mo99getText(), reactTextUpdate.getText())) && canUpdateWithEventCount(reactTextUpdate.getJsEventCounter())) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(reactTextUpdate.getText());
            manageSpans(spannableStringBuilder, reactTextUpdate.mContainsMultipleFragments);
            this.mContainsImages = reactTextUpdate.containsImages();
            this.mDisableTextDiffing = true;
            if (reactTextUpdate.getText().length() == 0) {
                setText((CharSequence) null);
            } else {
                mo99getText().replace(0, length(), spannableStringBuilder);
            }
            this.mDisableTextDiffing = false;
            int i = Build.VERSION.SDK_INT;
            if (getBreakStrategy() != reactTextUpdate.getTextBreakStrategy()) {
                setBreakStrategy(reactTextUpdate.getTextBreakStrategy());
            }
            updateCachedSpannable(false);
        }
    }

    public void maybeSetTextFromJS(ReactTextUpdate reactTextUpdate) {
        this.mIsSettingTextFromJS = true;
        maybeSetText(reactTextUpdate);
        this.mIsSettingTextFromJS = false;
    }

    public void maybeSetTextFromState(ReactTextUpdate reactTextUpdate) {
        this.mIsSettingTextFromState = true;
        maybeSetText(reactTextUpdate);
        this.mIsSettingTextFromState = false;
    }

    public void maybeUpdateTypeface() {
        if (!this.mTypefaceDirty) {
            return;
        }
        this.mTypefaceDirty = false;
        setTypeface(ReactTypefaceUtils.applyStyles(getTypeface(), this.mFontStyle, this.mFontWeight, this.mFontFamily, getContext().getAssets()));
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        super.setTextIsSelectable(true);
        if (this.mContainsImages) {
            Editable mo99getText = mo99getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) mo99getText.getSpans(0, mo99getText.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onAttachedToWindow();
            }
        }
        if (this.mAutoFocus && !this.mDidAttachToWindow) {
            requestFocusInternal();
        }
        this.mDidAttachToWindow = true;
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        ReactContext reactContext = UIManagerHelper.getReactContext(this);
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (onCreateInputConnection != null && this.mOnKeyPress) {
            onCreateInputConnection = new ReactEditTextInputConnectionWrapper(onCreateInputConnection, reactContext, this);
        }
        if (isMultiline() && getBlurOnSubmit()) {
            editorInfo.imeOptions &= -1073741825;
        }
        return onCreateInputConnection;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mContainsImages) {
            Editable mo99getText = mo99getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) mo99getText.getSpans(0, mo99getText.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onDetachedFromWindow();
            }
        }
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.mContainsImages) {
            Editable mo99getText = mo99getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) mo99getText.getSpans(0, mo99getText.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onFinishTemporaryDetach();
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        SelectionWatcher selectionWatcher;
        super.onFocusChanged(z, i, rect);
        if (!z || (selectionWatcher = this.mSelectionWatcher) == null) {
            return;
        }
        selectionWatcher.onSelectionChanged(getSelectionStart(), getSelectionEnd());
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 66 && !isMultiline()) {
            hideSoftKeyboard();
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        onContentSizeChange();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        ScrollWatcher scrollWatcher = this.mScrollWatcher;
        if (scrollWatcher != null) {
            scrollWatcher.onScrollChanged(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    protected void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        if (this.mIsSettingTextFromCacheUpdate || this.mSelectionWatcher == null || !hasFocus()) {
            return;
        }
        this.mSelectionWatcher.onSelectionChanged(i, i2);
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.mContainsImages) {
            Editable mo99getText = mo99getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) mo99getText.getSpans(0, mo99getText.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onStartTemporaryDetach();
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 2 && this.mDetectScrollMovement) {
                if (!canScrollVertically(-1) && !canScrollVertically(1) && !canScrollHorizontally(-1) && !canScrollHorizontally(1)) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                this.mDetectScrollMovement = false;
            }
        } else {
            this.mDetectScrollMovement = true;
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.TextView
    public void removeTextChangedListener(TextWatcher textWatcher) {
        ArrayList<TextWatcher> arrayList = this.mListeners;
        if (arrayList != null) {
            arrayList.remove(textWatcher);
            if (!this.mListeners.isEmpty()) {
                return;
            }
            this.mListeners = null;
            super.removeTextChangedListener(getTextWatcherDelegator());
        }
    }

    @Override // android.view.View
    public boolean requestFocus(int i, Rect rect) {
        return isFocused();
    }

    public void requestFocusFromJS() {
        requestFocusInternal();
    }

    public void setAllowFontScaling(boolean z) {
        if (this.mTextAttributes.getAllowFontScaling() != z) {
            this.mTextAttributes.setAllowFontScaling(z);
            applyTextAttributes();
        }
    }

    public void setAutoFocus(boolean z) {
        this.mAutoFocus = z;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mReactBackgroundManager.setBackgroundColor(i);
    }

    public void setBlurOnSubmit(@Nullable Boolean bool) {
        this.mBlurOnSubmit = bool;
    }

    public void setBorderColor(int i, float f, float f2) {
        this.mReactBackgroundManager.setBorderColor(i, f, f2);
    }

    public void setBorderRadius(float f) {
        this.mReactBackgroundManager.setBorderRadius(f);
    }

    public void setBorderStyle(@Nullable String str) {
        this.mReactBackgroundManager.setBorderStyle(str);
    }

    public void setBorderWidth(int i, float f) {
        this.mReactBackgroundManager.setBorderWidth(i, f);
    }

    public void setContentSizeWatcher(ContentSizeWatcher contentSizeWatcher) {
        this.mContentSizeWatcher = contentSizeWatcher;
    }

    public void setDisableFullscreenUI(boolean z) {
        this.mDisableFullscreen = z;
        updateImeOptions();
    }

    public void setFontFamily(String str) {
        this.mFontFamily = str;
        this.mTypefaceDirty = true;
    }

    public void setFontSize(float f) {
        this.mTextAttributes.setFontSize(f);
        applyTextAttributes();
    }

    public void setFontStyle(String str) {
        int parseFontStyle = ReactTypefaceUtils.parseFontStyle(str);
        if (parseFontStyle != this.mFontStyle) {
            this.mFontStyle = parseFontStyle;
            this.mTypefaceDirty = true;
        }
    }

    public void setFontWeight(String str) {
        int parseFontWeight = ReactTypefaceUtils.parseFontWeight(str);
        if (parseFontWeight != this.mFontWeight) {
            this.mFontWeight = parseFontWeight;
            this.mTypefaceDirty = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGravityHorizontal(int i) {
        if (i == 0) {
            i = this.mDefaultGravityHorizontal;
        }
        setGravity(i | (getGravity() & (-8) & (-8388616)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGravityVertical(int i) {
        if (i == 0) {
            i = this.mDefaultGravityVertical;
        }
        setGravity(i | (getGravity() & (-113)));
    }

    @Override // android.widget.TextView
    public void setInputType(int i) {
        Typeface typeface = super.getTypeface();
        super.setInputType(i);
        this.mStagedInputType = i;
        super.setTypeface(typeface);
        if (isMultiline()) {
            setSingleLine(false);
        }
        this.mKeyListener.setInputType(i);
        setKeyListener(this.mKeyListener);
    }

    public void setLetterSpacingPt(float f) {
        this.mTextAttributes.setLetterSpacing(f);
        applyTextAttributes();
    }

    public void setMaxFontSizeMultiplier(float f) {
        if (f != this.mTextAttributes.getMaxFontSizeMultiplier()) {
            this.mTextAttributes.setMaxFontSizeMultiplier(f);
            applyTextAttributes();
        }
    }

    public void setOnKeyPress(boolean z) {
        this.mOnKeyPress = z;
    }

    public void setReturnKeyType(String str) {
        this.mReturnKeyType = str;
        updateImeOptions();
    }

    public void setScrollWatcher(ScrollWatcher scrollWatcher) {
        this.mScrollWatcher = scrollWatcher;
    }

    @Override // android.widget.EditText
    public void setSelection(int i, int i2) {
        super.setSelection(i, i2);
    }

    public void setSelectionWatcher(SelectionWatcher selectionWatcher) {
        this.mSelectionWatcher = selectionWatcher;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStagedInputType(int i) {
        this.mStagedInputType = i;
    }

    protected boolean showSoftKeyboard() {
        return this.mInputMethodManager.showSoftInput(this, 0);
    }

    @Override // android.widget.TextView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        if (this.mContainsImages) {
            Editable mo99getText = mo99getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) mo99getText.getSpans(0, mo99getText.length(), TextInlineImageSpan.class)) {
                if (textInlineImageSpan.getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void setBorderRadius(float f, int i) {
        this.mReactBackgroundManager.setBorderRadius(f, i);
    }
}
