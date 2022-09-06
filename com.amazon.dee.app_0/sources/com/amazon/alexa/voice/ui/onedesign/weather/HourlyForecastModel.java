package com.amazon.alexa.voice.ui.onedesign.weather;

import android.os.Parcelable;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface HourlyForecastModel extends Parcelable {
    CharSequence getHourlyDescriptiveText();

    CharSequence getHourlyTemperature();

    int getIconId();
}
