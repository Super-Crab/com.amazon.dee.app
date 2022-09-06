package com.facebook.react.modules.datepicker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.clouddrive.cdasdk.cds.common.TimeGroupBy;
import com.facebook.fbreact.specs.NativeDatePickerAndroidSpec;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = DatePickerDialogModule.FRAGMENT_TAG)
/* loaded from: classes2.dex */
public class DatePickerDialogModule extends NativeDatePickerAndroidSpec {
    static final String ACTION_DATE_SET = "dateSetAction";
    static final String ACTION_DISMISSED = "dismissedAction";
    static final String ARG_DATE = "date";
    static final String ARG_MAXDATE = "maxDate";
    static final String ARG_MINDATE = "minDate";
    static final String ARG_MODE = "mode";
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    @VisibleForTesting
    public static final String FRAGMENT_TAG = "DatePickerAndroid";

    /* loaded from: classes2.dex */
    private class DatePickerDialogListener implements DatePickerDialog.OnDateSetListener, DialogInterface.OnDismissListener {
        private final Promise mPromise;
        private boolean mPromiseResolved = false;

        public DatePickerDialogListener(Promise promise) {
            this.mPromise = promise;
        }

        @Override // android.app.DatePickerDialog.OnDateSetListener
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            if (this.mPromiseResolved || !DatePickerDialogModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                return;
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("action", "dateSetAction");
            writableNativeMap.putInt(TimeGroupBy.YEAR, i);
            writableNativeMap.putInt(TimeGroupBy.MONTH, i2);
            writableNativeMap.putInt(TimeGroupBy.DAY, i3);
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (this.mPromiseResolved || !DatePickerDialogModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                return;
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("action", "dismissedAction");
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }
    }

    public DatePickerDialogModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private Bundle createFragmentArguments(ReadableMap readableMap) {
        Bundle bundle = new Bundle();
        if (readableMap.hasKey("date") && !readableMap.isNull("date")) {
            bundle.putLong("date", (long) readableMap.getDouble("date"));
        }
        if (readableMap.hasKey(ARG_MINDATE) && !readableMap.isNull(ARG_MINDATE)) {
            bundle.putLong(ARG_MINDATE, (long) readableMap.getDouble(ARG_MINDATE));
        }
        if (readableMap.hasKey(ARG_MAXDATE) && !readableMap.isNull(ARG_MAXDATE)) {
            bundle.putLong(ARG_MAXDATE, (long) readableMap.getDouble(ARG_MAXDATE));
        }
        if (readableMap.hasKey(ARG_MODE) && !readableMap.isNull(ARG_MODE)) {
            bundle.putString(ARG_MODE, readableMap.getString(ARG_MODE));
        }
        return bundle;
    }

    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public String getName() {
        return FRAGMENT_TAG;
    }

    @Override // com.facebook.fbreact.specs.NativeDatePickerAndroidSpec
    public void open(@Nullable ReadableMap readableMap, Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null && (currentActivity instanceof FragmentActivity)) {
            FragmentManager supportFragmentManager = ((FragmentActivity) currentActivity).getSupportFragmentManager();
            DialogFragment dialogFragment = (DialogFragment) supportFragmentManager.findFragmentByTag(FRAGMENT_TAG);
            if (dialogFragment != null) {
                dialogFragment.dismiss();
            }
            DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
            if (readableMap != null) {
                datePickerDialogFragment.setArguments(createFragmentArguments(readableMap));
            }
            DatePickerDialogListener datePickerDialogListener = new DatePickerDialogListener(promise);
            datePickerDialogFragment.setOnDismissListener(datePickerDialogListener);
            datePickerDialogFragment.setOnDateSetListener(datePickerDialogListener);
            datePickerDialogFragment.show(supportFragmentManager, FRAGMENT_TAG);
            return;
        }
        promise.reject("E_NO_ACTIVITY", "Tried to open a DatePicker dialog while not attached to a FragmentActivity");
    }
}
