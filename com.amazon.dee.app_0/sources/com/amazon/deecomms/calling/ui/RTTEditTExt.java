package com.amazon.deecomms.calling.ui;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import com.amazon.deecomms.common.ui.CommsEditText;
/* loaded from: classes12.dex */
public class RTTEditTExt extends CommsEditText {
    public RTTEditTExt(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.TextView
    public void onSelectionChanged(int i, int i2) {
        Editable text = getText();
        if (text != null && (i != text.length() || i2 != text.length())) {
            setSelection(text.length(), text.length());
        } else {
            super.onSelectionChanged(i, i2);
        }
    }
}
