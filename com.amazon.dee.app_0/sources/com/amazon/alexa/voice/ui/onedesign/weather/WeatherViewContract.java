package com.amazon.alexa.voice.ui.onedesign.weather;
/* loaded from: classes11.dex */
public interface WeatherViewContract {
    void setBackground(int i);

    void setTitleAndSubTitle(CharSequence charSequence, CharSequence charSequence2);

    void setVoiceIngressVisibility(int i);

    void setWeatherTodayModel(WeatherTodayModel weatherTodayModel);

    void showFullScreen();

    void updateDailyForecast(WeatherForecastModel weatherForecastModel);

    void updateHourlyForecast(HourlyForecastModel hourlyForecastModel);
}
