package com.reactcommunity.rndatetimepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Calendar;
import java.util.Locale;
@SuppressLint({"ValidFragment"})
/* loaded from: classes3.dex */
public class RNDatePickerDialogFragment extends DialogFragment {
    @Nullable
    private static DialogInterface.OnClickListener mOnNeutralButtonActionListener;
    private DatePickerDialog instance;
    @Nullable
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    @Nullable
    private DialogInterface.OnDismissListener mOnDismissListener;

    /* renamed from: com.reactcommunity.rndatetimepicker.RNDatePickerDialogFragment$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$reactcommunity$rndatetimepicker$RNDatePickerDisplay = new int[RNDatePickerDisplay.values().length];

        static {
            try {
                $SwitchMap$com$reactcommunity$rndatetimepicker$RNDatePickerDisplay[RNDatePickerDisplay.CALENDAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$reactcommunity$rndatetimepicker$RNDatePickerDisplay[RNDatePickerDisplay.SPINNER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static DatePickerDialog createDialog(Bundle bundle, Context context, @Nullable DatePickerDialog.OnDateSetListener onDateSetListener) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = getDialog(bundle, context, onDateSetListener);
        if (bundle != null && bundle.containsKey(RNConstants.ARG_NEUTRAL_BUTTON_LABEL)) {
            dialog.setButton(-3, bundle.getString(RNConstants.ARG_NEUTRAL_BUTTON_LABEL), mOnNeutralButtonActionListener);
        }
        DatePicker datePicker = dialog.getDatePicker();
        if (bundle != null && bundle.containsKey(RNConstants.ARG_MINDATE)) {
            calendar.setTimeInMillis(bundle.getLong(RNConstants.ARG_MINDATE));
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            datePicker.setMinDate(calendar.getTimeInMillis());
        } else {
            datePicker.setMinDate(RNConstants.DEFAULT_MIN_DATE);
        }
        if (bundle != null && bundle.containsKey(RNConstants.ARG_MAXDATE)) {
            calendar.setTimeInMillis(bundle.getLong(RNConstants.ARG_MAXDATE));
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
            datePicker.setMaxDate(calendar.getTimeInMillis());
        }
        return dialog;
    }

    @NonNull
    static DatePickerDialog getDialog(Bundle bundle, Context context, @Nullable DatePickerDialog.OnDateSetListener onDateSetListener) {
        RNDate rNDate = new RNDate(bundle);
        int year = rNDate.year();
        int month = rNDate.month();
        int day = rNDate.day();
        RNDatePickerDisplay valueOf = (bundle == null || bundle.getString("display", null) == null) ? RNDatePickerDisplay.DEFAULT : RNDatePickerDisplay.valueOf(bundle.getString("display").toUpperCase(Locale.US));
        int i = Build.VERSION.SDK_INT;
        int ordinal = valueOf.ordinal();
        if (ordinal != 0 && ordinal != 1) {
            return new RNDismissableDatePickerDialog(context, onDateSetListener, year, month, day, valueOf);
        }
        return new RNDismissableDatePickerDialog(context, context.getResources().getIdentifier(valueOf == RNDatePickerDisplay.CALENDAR ? "CalendarDatePickerDialog" : "SpinnerDatePickerDialog", TtmlNode.TAG_STYLE, context.getPackageName()), onDateSetListener, year, month, day, valueOf);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        this.instance = createDialog(getArguments(), getActivity(), this.mOnDateSetListener);
        return this.instance;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnNeutralButtonActionListener(@Nullable DialogInterface.OnClickListener onClickListener) {
        mOnNeutralButtonActionListener = onClickListener;
    }

    public void update(Bundle bundle) {
        RNDate rNDate = new RNDate(bundle);
        this.instance.updateDate(rNDate.year(), rNDate.month(), rNDate.day());
    }
}
