package com.amazon.alexa.growth.coachmark;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
/* loaded from: classes8.dex */
public class WrappingTextView extends AppCompatTextView {
    /* JADX INFO: Access modifiers changed from: package-private */
    public WrappingTextView(Context context) {
        super(context);
    }

    private float getMaxLineWidth(Layout layout) {
        int lineCount = layout.getLineCount();
        float f = 0.0f;
        for (int i = 0; i < lineCount; i++) {
            if (layout.getLineWidth(i) > f) {
                f = layout.getLineWidth(i);
            }
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        Layout layout;
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == 1073741824 || (layout = getLayout()) == null) {
            return;
        }
        int compoundPaddingRight = getCompoundPaddingRight() + getCompoundPaddingLeft() + ((int) Math.ceil(getMaxLineWidth(layout)));
        if (compoundPaddingRight >= getMeasuredWidth()) {
            return;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(compoundPaddingRight, Integer.MIN_VALUE), i2);
    }
}
