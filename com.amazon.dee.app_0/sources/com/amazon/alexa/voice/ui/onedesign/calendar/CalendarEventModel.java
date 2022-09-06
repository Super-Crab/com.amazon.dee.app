package com.amazon.alexa.voice.ui.onedesign.calendar;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface CalendarEventModel {
    CharSequence getDayOfMonth();

    CharSequence getDayOfWeek();

    CharSequence getId();

    CharSequence getLocation();

    CharSequence getMonth();

    @NonNull
    CharSequence getName();

    CharSequence getProvider();

    @NonNull
    Object getTag();

    CharSequence getTimes();
}
