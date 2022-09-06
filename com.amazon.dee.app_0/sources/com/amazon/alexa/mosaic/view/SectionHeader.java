package com.amazon.alexa.mosaic.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.utils.LogUtils;
/* loaded from: classes9.dex */
public class SectionHeader extends RelativeLayout {
    private static final String TAG = SectionHeader.class.getSimpleName();
    private final String dividerText;
    private final boolean showBackground;

    public SectionHeader(Context context) {
        this(context, null);
    }

    private void init() {
        RelativeLayout.inflate(getContext(), R.layout.mosaic_list_divider, this);
        ((TextView) findViewById(R.id.dividerTextView)).setText(this.dividerText);
        if (!this.showBackground) {
            LogUtils.v(TAG, "setting the background color to be transparent");
            setBackground(new ColorDrawable(getContext().getResources().getColor(R.color.transparent)));
            return;
        }
        LogUtils.v(TAG, "setting the background color to match the theme");
    }

    public void setText(String str) {
        ((TextView) findViewById(R.id.dividerTextView)).setText(str);
    }

    public SectionHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SectionHeader, 0, 0);
        try {
            this.dividerText = obtainStyledAttributes.getString(R.styleable.SectionHeader_text);
            this.showBackground = obtainStyledAttributes.getBoolean(R.styleable.SectionHeader_showBackground, true);
            obtainStyledAttributes.recycle();
            init();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
