package com.reactcommunity.rndatetimepicker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Locale;
/* loaded from: classes3.dex */
public class RNTimePickerDialogFragment extends DialogFragment {
    @Nullable
    private static DialogInterface.OnClickListener mOnNeutralButtonActionListener;
    private TimePickerDialog instance;
    @Nullable
    private DialogInterface.OnDismissListener mOnDismissListener;
    @Nullable
    private TimePickerDialog.OnTimeSetListener mOnTimeSetListener;

    /* renamed from: com.reactcommunity.rndatetimepicker.RNTimePickerDialogFragment$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$reactcommunity$rndatetimepicker$RNTimePickerDisplay = new int[RNTimePickerDisplay.values().length];

        static {
            try {
                $SwitchMap$com$reactcommunity$rndatetimepicker$RNTimePickerDisplay[RNTimePickerDisplay.CLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$reactcommunity$rndatetimepicker$RNTimePickerDisplay[RNTimePickerDisplay.SPINNER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static TimePickerDialog createDialog(Bundle bundle, Context context, @Nullable TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        TimePickerDialog dialog = getDialog(bundle, context, onTimeSetListener);
        if (bundle != null && bundle.containsKey(RNConstants.ARG_NEUTRAL_BUTTON_LABEL)) {
            dialog.setButton(-3, bundle.getString(RNConstants.ARG_NEUTRAL_BUTTON_LABEL), mOnNeutralButtonActionListener);
        }
        return dialog;
    }

    static TimePickerDialog getDialog(Bundle bundle, Context context, @Nullable TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        RNDate rNDate = new RNDate(bundle);
        int hour = rNDate.hour();
        int minute = rNDate.minute();
        boolean is24HourFormat = DateFormat.is24HourFormat(context);
        int i = (bundle == null || !MinuteIntervalSnappableTimePickerDialog.isValidMinuteInterval(bundle.getInt(RNConstants.ARG_INTERVAL))) ? 1 : bundle.getInt(RNConstants.ARG_INTERVAL);
        RNTimePickerDisplay rNTimePickerDisplay = RNTimePickerDisplay.DEFAULT;
        if (bundle != null && bundle.getString("display", null) != null) {
            rNTimePickerDisplay = RNTimePickerDisplay.valueOf(bundle.getString("display").toUpperCase(Locale.US));
        }
        RNTimePickerDisplay rNTimePickerDisplay2 = rNTimePickerDisplay;
        boolean z = bundle != null ? bundle.getBoolean(RNConstants.ARG_IS24HOUR, DateFormat.is24HourFormat(context)) : is24HourFormat;
        int i2 = Build.VERSION.SDK_INT;
        int ordinal = rNTimePickerDisplay2.ordinal();
        if (ordinal != 0 && ordinal != 1) {
            return new RNDismissableTimePickerDialog(context, onTimeSetListener, hour, minute, i, z, rNTimePickerDisplay2);
        }
        return new RNDismissableTimePickerDialog(context, context.getResources().getIdentifier(rNTimePickerDisplay2 == RNTimePickerDisplay.CLOCK ? "ClockTimePickerDialog" : "SpinnerTimePickerDialog", TtmlNode.TAG_STYLE, context.getPackageName()), onTimeSetListener, hour, minute, i, z, rNTimePickerDisplay2);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        this.instance = createDialog(getArguments(), getActivity(), this.mOnTimeSetListener);
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

    public void setOnDismissListener(@Nullable DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnNeutralButtonActionListener(@Nullable DialogInterface.OnClickListener onClickListener) {
        mOnNeutralButtonActionListener = onClickListener;
    }

    public void setOnTimeSetListener(@Nullable TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        this.mOnTimeSetListener = onTimeSetListener;
    }

    public void update(Bundle bundle) {
        RNDate rNDate = new RNDate(bundle);
        this.instance.updateTime(rNDate.hour(), rNDate.minute());
    }
}
