package com.amazon.alexa.voice.ui.onedesign.weather;

import com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel;
import com.amazon.alexa.voice.ui.onedesign.weather.WeatherForecast;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.ui.onedesign.weather.-$$Lambda$WeatherPresenter$sJxl8-i1s5yX0gexOEb_-OeXYWw  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$WeatherPresenter$sJxl8i1s5yX0gexOEb_OeXYWw implements Function {
    public static final /* synthetic */ $$Lambda$WeatherPresenter$sJxl8i1s5yX0gexOEb_OeXYWw INSTANCE = new $$Lambda$WeatherPresenter$sJxl8i1s5yX0gexOEb_OeXYWw();

    private /* synthetic */ $$Lambda$WeatherPresenter$sJxl8i1s5yX0gexOEb_OeXYWw() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        WeatherForecast build;
        build = new WeatherForecast.Builder().iconId(r1.getIcon()).highTemperature(r1.getHighTemperature()).lowTemperature(r1.getLowTemperature()).date(((WeatherCardModel.ForecastModel) obj).getDate()).build();
        return build;
    }
}
