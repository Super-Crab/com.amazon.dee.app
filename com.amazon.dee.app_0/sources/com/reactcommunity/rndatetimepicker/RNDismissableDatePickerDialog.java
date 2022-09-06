package com.reactcommunity.rndatetimepicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
/* loaded from: classes3.dex */
public class RNDismissableDatePickerDialog extends DatePickerDialog {
    public RNDismissableDatePickerDialog(Context context, @Nullable DatePickerDialog.OnDateSetListener onDateSetListener, int i, int i2, int i3, RNDatePickerDisplay rNDatePickerDisplay) {
        super(context, onDateSetListener, i, i2, i3);
        fixSpinner(context, i, i2, i3, rNDatePickerDisplay);
    }

    private void fixSpinner(Context context, int i, int i2, int i3, RNDatePickerDisplay rNDatePickerDisplay) {
        int i4 = Build.VERSION.SDK_INT;
    }

    @Override // android.app.Dialog
    protected void onStop() {
        int i = Build.VERSION.SDK_INT;
        super.onStop();
    }

    public RNDismissableDatePickerDialog(Context context, int i, @Nullable DatePickerDialog.OnDateSetListener onDateSetListener, int i2, int i3, int i4, RNDatePickerDisplay rNDatePickerDisplay) {
        super(context, i, onDateSetListener, i2, i3, i4);
        fixSpinner(context, i2, i3, i4, rNDatePickerDisplay);
    }
}
