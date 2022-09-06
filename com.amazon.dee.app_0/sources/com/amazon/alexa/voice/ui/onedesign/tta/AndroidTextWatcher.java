package com.amazon.alexa.voice.ui.onedesign.tta;

import android.text.Editable;
import android.text.TextWatcher;
/* loaded from: classes11.dex */
public class AndroidTextWatcher implements TextWatcher {
    private final UserInputWatcher userInputWatcher;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AndroidTextWatcher(UserInputWatcher userInputWatcher) {
        this.userInputWatcher = userInputWatcher;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.userInputWatcher.onTextChanged(charSequence);
    }
}
