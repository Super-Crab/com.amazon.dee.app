package com.amazon.alexa.biloba.databinding;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
/* loaded from: classes6.dex */
public final class TextViewWithLinks {
    private TextViewWithLinks() {
    }

    @BindingAdapter({"textViewHasLinks"})
    public static void setTextViewHasLinks(TextView textView, Boolean bool) {
        if (bool.booleanValue()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
