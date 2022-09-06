package com.amazon.alexa.voice.ui.onedesign.weather;

import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface WeatherTodayModel {
    CharSequence getCurrentTemperature();

    CharSequence getCurrentWeatherAlert();

    CharSequence getCurrentWeatherDescription();

    CharSequence getHighTemperature();

    int getIconId();

    CharSequence getLowTemperature();

    CharSequence getRealFeel();

    CharSequence getWeatherDataProvider();
}
