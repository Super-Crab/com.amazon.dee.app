package com.amazon.alexa.handsfree.ui.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;
/* loaded from: classes8.dex */
public class AlertDialogBuilderFactory {
    private final Context mContext;
    private final int mTheme;

    public AlertDialogBuilderFactory(@NonNull Context context, @StyleRes int i) {
        this.mContext = context;
        this.mTheme = i;
    }

    public AlertDialog.Builder create() {
        return new AlertDialog.Builder(this.mContext, this.mTheme);
    }
}
