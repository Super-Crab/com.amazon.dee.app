package com.amazon.alexa.voice.ui.onedesign.weather;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface WeatherCardModel extends Parcelable {

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface ForecastModel extends Parcelable {
        CharSequence getDate();

        CharSequence getHighTemperature();

        int getIcon();

        CharSequence getLowTemperature();
    }

    @NonNull
    CharSequence getCurrentTemperature();

    CharSequence getCurrentWeatherAlert();

    CharSequence getCurrentWeatherDescription();

    List<? extends ForecastModel> getDailyForecasts();

    CharSequence getHighTemperature();

    List<? extends HourlyForecastModel> getHourlyForecasts();

    int getIcon();

    CharSequence getLowTemperature();

    CharSequence getShortDescriptionDate();

    CharSequence getShortTitle();

    @NonNull
    CharSequence getSubTitle();

    @NonNull
    CharSequence getTitle();

    CharSequence getWeatherDataProvider();
}
