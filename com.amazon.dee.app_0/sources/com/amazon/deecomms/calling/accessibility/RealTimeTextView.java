package com.amazon.deecomms.calling.accessibility;

import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.ui.OutgoingRealTimeTextWatcher;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.AnimUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public class RealTimeTextView implements RealTimeTextViewContract {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, RealTimeTextView.class);
    private String calleeContactName;
    private TextView farSideTextBox;
    private InputMethodManager inputMethodManager;
    private EditText nearSideEditText;
    private TextWatcher nearSideTextWatcher;
    private RealTimeTextViewContract parentActiveVideoCallViewContract;
    private View parentView;
    private RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    private RealTimeTextPresenter realTimeTextPresenter;
    private String remoteSideText = "";
    private String localSideText = "";
    private String contactNamePrefixStringToInsert = "";

    public RealTimeTextView(@NonNull RealTimeTextPresenter realTimeTextPresenter, @NonNull String str, @NonNull RealTimeTextViewContract realTimeTextViewContract, @NonNull RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        this.calleeContactName = "";
        this.realTimeTextPresenter = realTimeTextPresenter;
        this.calleeContactName = str;
        this.parentActiveVideoCallViewContract = realTimeTextViewContract;
        this.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
    }

    private void animateFarSideTextViewVisibilityIfNeeded(int i) {
        if (this.farSideTextBox.getVisibility() != i) {
            AnimUtils.fadingAnimation(this.parentView.getContext(), this.farSideTextBox, 300, i == 0);
            this.farSideTextBox.setVisibility(i);
        }
    }

    private void appendCalleeContactNameIfNeeded() {
        TextView textView = this.farSideTextBox;
        if (textView == null || textView.getText().length() != 0) {
            return;
        }
        this.contactNamePrefixStringToInsert = this.realTimeTextPresenter.getInitialsForContactName(this.calleeContactName) + RealTimeTextConstants.COLON_SPACE;
        this.farSideTextBox.getEditableText().insert(0, this.contactNamePrefixStringToInsert);
    }

    private void bindUI(View view) {
        this.farSideTextBox = (TextView) view.findViewById(R.id.far_side_text_box);
        this.nearSideEditText = (EditText) view.findViewById(R.id.near_side_edit_text);
        this.parentView = view;
        this.nearSideTextWatcher = new OutgoingRealTimeTextWatcher(this.realTimeTextPresenter, this, this.nearSideEditText);
    }

    private boolean handleClearButtonPressIfSelected(@NonNull MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            Drawable drawable = this.nearSideEditText.getCompoundDrawables()[2];
            int right = this.nearSideEditText.getRight();
            if (drawable == null) {
                drawable = this.nearSideEditText.getCompoundDrawables()[0];
                right = this.nearSideEditText.getLeft();
            }
            if (drawable != null && motionEvent.getRawX() >= right - drawable.getBounds().width()) {
                this.nearSideEditText.getEditableText().clear();
                return true;
            }
        }
        return false;
    }

    private void hideFarSideTextViewIfNeeded() {
        TextView textView = this.farSideTextBox;
        if (textView == null || textView.getText() == null) {
            return;
        }
        String charSequence = this.farSideTextBox.getText().toString();
        if (charSequence.length() != 0 && !charSequence.trim().equals(this.contactNamePrefixStringToInsert.trim())) {
            return;
        }
        this.farSideTextBox.postDelayed(new Runnable() { // from class: com.amazon.deecomms.calling.accessibility.-$$Lambda$RealTimeTextView$7FSM9z7DmhSTGKj45tNw6sjlezU
            @Override // java.lang.Runnable
            public final void run() {
                RealTimeTextView.this.lambda$hideFarSideTextViewIfNeeded$1$RealTimeTextView();
            }
        }, 2000L);
    }

    private void processMessageReceived(@NonNull String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\b') {
                int length = this.farSideTextBox.getText().length();
                if (length > 0) {
                    this.farSideTextBox.getEditableText().delete(length - 1, length);
                }
            } else {
                TextView textView = this.farSideTextBox;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("");
                outline1.append(str.charAt(i));
                textView.append(outline1.toString());
            }
        }
    }

    private void setInitialStrings() {
        this.inputMethodManager = (InputMethodManager) this.parentView.getContext().getSystemService("input_method");
        this.farSideTextBox.setText("", TextView.BufferType.EDITABLE);
        appendCalleeContactNameIfNeeded();
        this.farSideTextBox.getEditableText().append((CharSequence) this.remoteSideText);
        this.nearSideEditText.setText(this.localSideText);
        EditText editText = this.nearSideEditText;
        editText.setSelection(editText.getText().length());
    }

    private void setListeners() {
        EditText editText = this.nearSideEditText;
        if (editText != null) {
            editText.addTextChangedListener(this.nearSideTextWatcher);
            this.nearSideEditText.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.deecomms.calling.accessibility.-$$Lambda$RealTimeTextView$HwCN2JFJkoiEJLcAMjmw3Qxhg0Y
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return RealTimeTextView.this.lambda$setListeners$2$RealTimeTextView(view, motionEvent);
                }
            });
            this.nearSideEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.amazon.deecomms.calling.accessibility.-$$Lambda$RealTimeTextView$HwYs9JOyVSa6lKkZ13S3ZCJIXYI
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z) {
                    RealTimeTextView.this.lambda$setListeners$3$RealTimeTextView(view, z);
                }
            });
            this.nearSideEditText.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.accessibility.-$$Lambda$RealTimeTextView$6hwCxXq2-N0QbiTfnJpAWJONGjM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RealTimeTextView.this.lambda$setListeners$4$RealTimeTextView(view);
                }
            });
        }
    }

    private void setRemoteTextState() {
        String charSequence = this.farSideTextBox.getText().toString();
        int length = this.contactNamePrefixStringToInsert.length();
        if (charSequence.length() >= length) {
            this.remoteSideText = charSequence.substring(length);
        }
    }

    private void showKeyboard() {
        EditText editText = this.nearSideEditText;
        if (editText == null || this.parentView == null) {
            return;
        }
        editText.postDelayed(new Runnable() { // from class: com.amazon.deecomms.calling.accessibility.-$$Lambda$RealTimeTextView$KZ1Qs6mfr9vJNxz9D000rsO3qT4
            @Override // java.lang.Runnable
            public final void run() {
                RealTimeTextView.this.lambda$showKeyboard$0$RealTimeTextView();
            }
        }, 500L);
    }

    private void toggleViewVisibility() {
        String str = this.remoteSideText;
        if (str == null || this.nearSideEditText == null) {
            return;
        }
        if (str.length() > this.contactNamePrefixStringToInsert.length()) {
            AnimUtils.fadingAnimation(this.parentView.getContext(), this.farSideTextBox, 300, true);
            this.farSideTextBox.setVisibility(0);
        }
        this.nearSideEditText.setVisibility(0);
    }

    public void addClearButtonToEditText() {
        if (this.nearSideEditText.getText().length() == 0) {
            this.nearSideEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            this.nearSideEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_cancel_18dp, 0);
        }
    }

    public void bind(@NonNull View view) {
        bindUI(view);
        setInitialStrings();
        toggleViewVisibility();
        setListeners();
        hideCallControls();
        showKeyboard();
        hideFarSideTextViewIfNeeded();
    }

    public void enforceTwoLineLimit(@Nullable TextView textView, boolean z) {
        if (textView == null || textView.getLayout() == null) {
            return;
        }
        int lineCount = textView.getLayout().getLineCount();
        int i = 0;
        if (z) {
            i = 0 + this.contactNamePrefixStringToInsert.length();
        }
        while (lineCount > 2) {
            if (textView.getEditableText().length() > i) {
                textView.getEditableText().delete(i, i + 1);
            }
            lineCount = textView.getLayout().getLineCount();
        }
    }

    public void handleScreenTap() {
        hideKeyboard();
        toggleViewVisibility();
    }

    @Override // com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract
    public void hideCallControls() {
        this.parentActiveVideoCallViewContract.hideCallControls();
    }

    public void hideKeyboard() {
        EditText editText = this.nearSideEditText;
        if (editText == null || this.parentView == null) {
            return;
        }
        this.inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public /* synthetic */ void lambda$hideFarSideTextViewIfNeeded$1$RealTimeTextView() {
        animateFarSideTextViewVisibilityIfNeeded(4);
    }

    public /* synthetic */ boolean lambda$setListeners$2$RealTimeTextView(View view, MotionEvent motionEvent) {
        return handleClearButtonPressIfSelected(motionEvent);
    }

    public /* synthetic */ void lambda$setListeners$3$RealTimeTextView(View view, boolean z) {
        if (!z) {
            hideKeyboard();
        }
    }

    public /* synthetic */ void lambda$setListeners$4$RealTimeTextView(View view) {
        hideCallControls();
        showKeyboard();
    }

    public /* synthetic */ void lambda$showKeyboard$0$RealTimeTextView() {
        this.nearSideEditText.requestFocus();
        if (!this.inputMethodManager.showSoftInput(this.nearSideEditText, 1)) {
            this.inputMethodManager.showSoftInput(this.nearSideEditText, 2);
        }
    }

    public void setLocalTextState(@NonNull String str) {
        this.localSideText = str;
    }

    @Override // com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract
    public void showIncomingMessage(@NonNull String str) {
        if (this.farSideTextBox != null) {
            animateFarSideTextViewVisibilityIfNeeded(0);
            appendCalleeContactNameIfNeeded();
            processMessageReceived(str);
            enforceTwoLineLimit(this.farSideTextBox, true);
            hideFarSideTextViewIfNeeded();
            setRemoteTextState();
        }
    }

    public void unbind() {
        if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            this.nearSideEditText.removeTextChangedListener(this.nearSideTextWatcher);
            this.nearSideEditText.setOnClickListener(null);
            this.parentView = null;
            this.nearSideEditText = null;
            this.farSideTextBox = null;
            this.nearSideTextWatcher = null;
        }
    }

    @VisibleForTesting
    public RealTimeTextView(@NonNull TextView textView, @NonNull EditText editText, @NonNull InputMethodManager inputMethodManager, @NonNull RealTimeTextPresenter realTimeTextPresenter, @NonNull String str, @NonNull TextWatcher textWatcher, @NonNull RealTimeTextViewContract realTimeTextViewContract, @NonNull RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        this.calleeContactName = "";
        this.farSideTextBox = textView;
        this.nearSideEditText = editText;
        this.inputMethodManager = inputMethodManager;
        this.realTimeTextPresenter = realTimeTextPresenter;
        this.calleeContactName = str;
        this.nearSideTextWatcher = textWatcher;
        this.parentActiveVideoCallViewContract = realTimeTextViewContract;
        this.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
    }
}
