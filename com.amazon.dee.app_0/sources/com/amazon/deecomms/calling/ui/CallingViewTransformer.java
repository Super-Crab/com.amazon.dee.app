package com.amazon.deecomms.calling.ui;

import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public class CallingViewTransformer {
    private boolean mIsInDriveMode;

    public CallingViewTransformer(boolean z) {
        this.mIsInDriveMode = z;
    }

    public void adjustVisibility(@NonNull TextView textView) {
        if (textView != null) {
            textView.setVisibility(this.mIsInDriveMode ? 0 : 8);
        }
    }

    public void setEnabled(TextView textView, boolean z, @ColorInt int i) {
        if (!this.mIsInDriveMode) {
            return;
        }
        textView.setEnabled(z);
        textView.setTextColor(i);
    }
}
