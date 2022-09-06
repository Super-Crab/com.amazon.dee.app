package com.amazon.alexa.voice.ui.onedesign.weather;

import com.amazon.alexa.voice.ui.onedesign.weather.HourlyForecast;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.ui.onedesign.weather.-$$Lambda$WeatherPresenter$nTvbfVRRfk1OETyaINGX6bD0J5A  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$WeatherPresenter$nTvbfVRRfk1OETyaINGX6bD0J5A implements Function {
    public static final /* synthetic */ $$Lambda$WeatherPresenter$nTvbfVRRfk1OETyaINGX6bD0J5A INSTANCE = new $$Lambda$WeatherPresenter$nTvbfVRRfk1OETyaINGX6bD0J5A();

    private /* synthetic */ $$Lambda$WeatherPresenter$nTvbfVRRfk1OETyaINGX6bD0J5A() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        HourlyForecast build;
        build = new HourlyForecast.Builder().iconId(r1.getIconId()).hourlyTemperature(r1.getHourlyTemperature()).hourlyDescriptiveText(((HourlyForecastModel) obj).getHourlyDescriptiveText()).build();
        return build;
    }
}
