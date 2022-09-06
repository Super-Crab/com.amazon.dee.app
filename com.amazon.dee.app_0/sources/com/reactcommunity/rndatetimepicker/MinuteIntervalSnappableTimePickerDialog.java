package com.reactcommunity.rndatetimepicker;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import java.util.ArrayList;
/* loaded from: classes3.dex */
class MinuteIntervalSnappableTimePickerDialog extends TimePickerDialog {
    private Handler handler;
    private Context mContext;
    private RNTimePickerDisplay mDisplay;
    private TimePicker mTimePicker;
    private int mTimePickerInterval;
    private final TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private Runnable runnable;

    public MinuteIntervalSnappableTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener, int i, int i2, int i3, boolean z, RNTimePickerDisplay rNTimePickerDisplay) {
        super(context, onTimeSetListener, i, i2, z);
        this.handler = new Handler();
        this.mTimePickerInterval = i3;
        this.mTimeSetListener = onTimeSetListener;
        this.mDisplay = rNTimePickerDisplay;
        this.mContext = context;
    }

    private void assertNotSpinner(String str) {
        if (!isSpinner()) {
            return;
        }
        throw new RuntimeException(str);
    }

    private void correctEnteredMinutes(final TimePicker timePicker, final int i, final int i2) {
        assertNotSpinner("spinner never needs to be corrected because wrong values are not offered to user (both in scrolling and textInput mode)!");
        final EditText editText = (EditText) timePicker.findFocus();
        this.runnable = new Runnable() { // from class: com.reactcommunity.rndatetimepicker.MinuteIntervalSnappableTimePickerDialog.1
            @Override // java.lang.Runnable
            public void run() {
                if (MinuteIntervalSnappableTimePickerDialog.this.pickerIsInTextInputMode()) {
                    timePicker.setCurrentHour(Integer.valueOf(i));
                    timePicker.setCurrentMinute(Integer.valueOf(i2));
                    EditText editText2 = editText;
                    editText2.setSelection(editText2.getText().length());
                    return;
                }
                timePicker.setCurrentHour(Integer.valueOf(i));
                timePicker.setCurrentMinute(0);
                timePicker.setCurrentMinute(Integer.valueOf(i2));
            }
        };
        this.handler.postDelayed(this.runnable, 500L);
    }

    private int getRealMinutes(int i) {
        return this.mDisplay == RNTimePickerDisplay.SPINNER ? i * this.mTimePickerInterval : i;
    }

    private boolean isSpinner() {
        return this.mDisplay == RNTimePickerDisplay.SPINNER;
    }

    public static boolean isValidMinuteInterval(int i) {
        return i >= 1 && i <= 30 && 60 % i == 0;
    }

    private boolean minutesNeedCorrection(int i) {
        assertNotSpinner("minutesNeedCorrection is not intended to be used with spinner, spinner won't allow picking invalid values");
        return timePickerHasCustomMinuteInterval() && i != snapRealMinutesToInterval(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean pickerIsInTextInputMode() {
        View findViewById = findViewById(this.mContext.getResources().getIdentifier("input_mode", "id", "android"));
        return findViewById != null && findViewById.hasFocus();
    }

    @SuppressLint({"DefaultLocale"})
    private void setSpinnerDisplayedValues() {
        NumberPicker numberPicker = (NumberPicker) findViewById(this.mContext.getResources().getIdentifier("minute", "id", "android"));
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue((60 / this.mTimePickerInterval) - 1);
        ArrayList arrayList = new ArrayList(60 / this.mTimePickerInterval);
        int i = 0;
        while (i < 60) {
            arrayList.add(String.format("%02d", Integer.valueOf(i)));
            i += this.mTimePickerInterval;
        }
        numberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[0]));
    }

    private void setupPickerDialog() {
        this.mTimePicker = (TimePicker) findViewById(this.mContext.getResources().getIdentifier("timePicker", "id", "android"));
        int intValue = this.mTimePicker.getCurrentMinute().intValue();
        if (isSpinner()) {
            setSpinnerDisplayedValues();
            this.mTimePicker.setCurrentMinute(Integer.valueOf(snapRealMinutesToInterval(intValue) / this.mTimePickerInterval));
            return;
        }
        this.mTimePicker.setCurrentMinute(Integer.valueOf(snapRealMinutesToInterval(intValue)));
    }

    private int snapRealMinutesToInterval(int i) {
        int round = Math.round(i / this.mTimePickerInterval);
        int i2 = this.mTimePickerInterval;
        int i3 = round * i2;
        return i3 == 60 ? i3 - i2 : i3;
    }

    private boolean timePickerHasCustomMinuteInterval() {
        return this.mTimePickerInterval != 1;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (timePickerHasCustomMinuteInterval()) {
            setupPickerDialog();
        }
    }

    @Override // android.app.TimePickerDialog, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.mTimePicker != null && i == -1 && timePickerHasCustomMinuteInterval()) {
            int intValue = this.mTimePicker.getCurrentHour().intValue();
            int realMinutes = getRealMinutes();
            if (!isSpinner()) {
                realMinutes = snapRealMinutesToInterval(realMinutes);
            }
            TimePickerDialog.OnTimeSetListener onTimeSetListener = this.mTimeSetListener;
            if (onTimeSetListener == null) {
                return;
            }
            onTimeSetListener.onTimeSet(this.mTimePicker, intValue, realMinutes);
            return;
        }
        super.onClick(dialogInterface, i);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.handler.removeCallbacks(this.runnable);
        super.onDetachedFromWindow();
    }

    @Override // android.app.TimePickerDialog, android.widget.TimePicker.OnTimeChangedListener
    public void onTimeChanged(TimePicker timePicker, int i, int i2) {
        int realMinutes = getRealMinutes(i2);
        this.handler.removeCallbacks(this.runnable);
        if (!isSpinner() && minutesNeedCorrection(realMinutes)) {
            correctEnteredMinutes(timePicker, i, snapRealMinutesToInterval(realMinutes));
        } else {
            super.onTimeChanged(timePicker, i, i2);
        }
    }

    @Override // android.app.TimePickerDialog
    public void updateTime(int i, int i2) {
        if (timePickerHasCustomMinuteInterval()) {
            if (isSpinner()) {
                super.updateTime(i, snapRealMinutesToInterval(getRealMinutes()) / this.mTimePickerInterval);
                return;
            } else {
                super.updateTime(i, snapRealMinutesToInterval(i2));
                return;
            }
        }
        super.updateTime(i, i2);
    }

    private int getRealMinutes() {
        return getRealMinutes(this.mTimePicker.getCurrentMinute().intValue());
    }

    public MinuteIntervalSnappableTimePickerDialog(Context context, int i, TimePickerDialog.OnTimeSetListener onTimeSetListener, int i2, int i3, int i4, boolean z, RNTimePickerDisplay rNTimePickerDisplay) {
        super(context, i, onTimeSetListener, i2, i3, z);
        this.handler = new Handler();
        this.mTimePickerInterval = i4;
        this.mTimeSetListener = onTimeSetListener;
        this.mDisplay = rNTimePickerDisplay;
        this.mContext = context;
    }
}
