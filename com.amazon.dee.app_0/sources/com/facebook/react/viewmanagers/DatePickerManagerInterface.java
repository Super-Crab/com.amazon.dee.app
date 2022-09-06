package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface DatePickerManagerInterface<T extends View> {
    void setDate(T t, float f);

    void setInitialDate(T t, float f);

    void setLocale(T t, @Nullable String str);

    void setMaximumDate(T t, float f);

    void setMinimumDate(T t, float f);

    void setMinuteInterval(T t, @Nullable Integer num);

    void setMode(T t, @Nullable String str);

    void setNativeDate(T t, float f);

    void setTimeZoneOffsetInMinutes(T t, float f);
}
