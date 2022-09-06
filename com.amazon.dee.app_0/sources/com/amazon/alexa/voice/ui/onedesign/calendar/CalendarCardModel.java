package com.amazon.alexa.voice.ui.onedesign.calendar;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface CalendarCardModel extends Parcelable {

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface EventModel extends Parcelable {
        boolean getAllDay();

        long getEndTime();

        CharSequence getId();

        CharSequence getLocation();

        boolean getMultiDay();

        @NonNull
        CharSequence getName();

        CharSequence getProvider();

        long getStartTime();
    }

    @NonNull
    List<? extends EventModel> getEventList();

    CharSequence getTitle();
}
