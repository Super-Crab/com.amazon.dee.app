package com.amazon.alexa.biloba.view.cards;

import android.view.View;
/* loaded from: classes6.dex */
public class CardButton {
    private final String buttonText;
    private final View.OnClickListener clickListener;

    public CardButton(String str, View.OnClickListener onClickListener) {
        this.buttonText = str;
        this.clickListener = onClickListener;
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public View.OnClickListener getClickListener() {
        return this.clickListener;
    }
}
