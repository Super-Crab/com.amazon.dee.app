package com.facebook.react.modules.datepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.sun.mail.imap.IMAPStore;
import java.util.Calendar;
import java.util.Locale;
@SuppressLint({"ValidFragment"})
/* loaded from: classes2.dex */
public class DatePickerDialogFragment extends DialogFragment {
    private static final long DEFAULT_MIN_DATE = -2208988800001L;
    @Nullable
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    @Nullable
    private DialogInterface.OnDismissListener mOnDismissListener;

    /* renamed from: com.facebook.react.modules.datepicker.DatePickerDialogFragment$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode = new int[DatePickerMode.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[DatePickerMode.CALENDAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[DatePickerMode.SPINNER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[DatePickerMode.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static Dialog createDialog(Bundle bundle, Context context, @Nullable DatePickerDialog.OnDateSetListener onDateSetListener) {
        DismissableDatePickerDialog dismissableDatePickerDialog;
        Calendar calendar = Calendar.getInstance();
        if (bundle != null && bundle.containsKey(IMAPStore.ID_DATE)) {
            calendar.setTimeInMillis(bundle.getLong(IMAPStore.ID_DATE));
        }
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        DatePickerMode datePickerMode = DatePickerMode.DEFAULT;
        if (bundle != null && bundle.getString("mode", null) != null) {
            datePickerMode = DatePickerMode.valueOf(bundle.getString("mode").toUpperCase(Locale.US));
        }
        int ordinal = datePickerMode.ordinal();
        if (ordinal == 0) {
            dismissableDatePickerDialog = new DismissableDatePickerDialog(context, context.getResources().getIdentifier("CalendarDatePickerDialog", TtmlNode.TAG_STYLE, context.getPackageName()), onDateSetListener, i, i2, i3);
        } else if (ordinal != 1) {
            dismissableDatePickerDialog = ordinal != 2 ? null : new DismissableDatePickerDialog(context, onDateSetListener, i, i2, i3);
        } else {
            dismissableDatePickerDialog = new DismissableDatePickerDialog(context, 16973939, onDateSetListener, i, i2, i3);
            dismissableDatePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        DatePicker datePicker = dismissableDatePickerDialog.getDatePicker();
        if (bundle != null && bundle.containsKey("minDate")) {
            calendar.setTimeInMillis(bundle.getLong("minDate"));
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            datePicker.setMinDate(calendar.getTimeInMillis());
        } else {
            datePicker.setMinDate(-2208988800001L);
        }
        if (bundle != null && bundle.containsKey("maxDate")) {
            calendar.setTimeInMillis(bundle.getLong("maxDate"));
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
            datePicker.setMaxDate(calendar.getTimeInMillis());
        }
        return dismissableDatePickerDialog;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return createDialog(getArguments(), getActivity(), this.mOnDateSetListener);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnDateSetListener(@Nullable DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.mOnDateSetListener = onDateSetListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnDismissListener(@Nullable DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }
}
