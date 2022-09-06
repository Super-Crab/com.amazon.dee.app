package com.facebook.react.modules.datepicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import java.lang.reflect.Field;
/* loaded from: classes2.dex */
public class DismissableDatePickerDialog extends DatePickerDialog {
    public DismissableDatePickerDialog(Context context, @Nullable DatePickerDialog.OnDateSetListener onDateSetListener, int i, int i2, int i3) {
        super(context, onDateSetListener, i, i2, i3);
        fixSpinner(context, i, i2, i3);
    }

    private static Field findField(Class cls, Class cls2, String str) {
        Field[] declaredFields;
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (NoSuchFieldException unused) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.getType() == cls2) {
                    field.setAccessible(true);
                    return field;
                }
            }
            return null;
        }
    }

    private void fixSpinner(Context context, int i, int i2, int i3) {
        int i4 = Build.VERSION.SDK_INT;
    }

    public DismissableDatePickerDialog(Context context, int i, @Nullable DatePickerDialog.OnDateSetListener onDateSetListener, int i2, int i3, int i4) {
        super(context, i, onDateSetListener, i2, i3, i4);
        fixSpinner(context, i2, i3, i4);
    }
}
