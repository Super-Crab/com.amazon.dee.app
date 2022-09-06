package com.amazon.alexa.voice.ui.onedesign.weather;

import android.text.TextUtils;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel;
import com.amazon.alexa.voice.ui.onedesign.weather.WeatherToday;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes11.dex */
public final class WeatherPresenter implements WeatherPresenterContract {
    private final WeatherInteractorContract interactor;
    private final CardMetricsInteractor metricsInteractor;
    private final WeatherViewContract view;
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private final CompositeDisposable disposables = new CompositeDisposable();

    public WeatherPresenter(WeatherViewContract weatherViewContract, WeatherInteractorContract weatherInteractorContract, CardMetricsInteractor cardMetricsInteractor) {
        this.view = weatherViewContract;
        this.interactor = weatherInteractorContract;
        this.metricsInteractor = cardMetricsInteractor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$start$0(WeatherCardModel.ForecastModel forecastModel) throws Throwable {
        return !TextUtils.isEmpty(forecastModel.getDate());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$start$2(HourlyForecastModel hourlyForecastModel) throws Throwable {
        return !TextUtils.isEmpty(hourlyForecastModel.getHourlyDescriptiveText());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherPresenterContract
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherPresenterContract
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherPresenterContract
    public void end() {
        if (!this.disposables.isDisposed()) {
            this.disposables.clear();
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherPresenterContract
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherPresenterContract
    public void start() {
        WeatherCard card = this.interactor.getCard();
        this.view.setBackground(card.getIcon());
        this.view.setVoiceIngressVisibility(this.interactor.isShowVoiceIngress() ? 0 : 8);
        if (this.interactor.isAlwaysFullScreen()) {
            this.view.showFullScreen();
        }
        this.view.setTitleAndSubTitle(card.getShortDescriptionDate(), card.getShortTitle());
        this.view.setWeatherTodayModel(new WeatherToday.Builder().iconId(card.getIcon()).currentTemperature(card.getCurrentTemperature()).lowTemperature(card.getLowTemperature()).highTemperature(card.getHighTemperature()).currentWeatherDescription(card.getCurrentWeatherDescription()).weatherDataProvider(card.getWeatherDataProvider()).currentWeatherAlert(card.getCurrentWeatherAlert()).build());
        CompositeDisposable compositeDisposable = this.disposables;
        Observable map = Observable.fromIterable(card.getDailyForecasts()).filter($$Lambda$WeatherPresenter$Di7uPE9NWKgPPlaCtO3OW9Pzro.INSTANCE).map($$Lambda$WeatherPresenter$sJxl8i1s5yX0gexOEb_OeXYWw.INSTANCE);
        final WeatherViewContract weatherViewContract = this.view;
        weatherViewContract.getClass();
        compositeDisposable.add(map.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.weather.-$$Lambda$FJOgElZ68taxFRRSKOWS4s2DiEo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                WeatherViewContract.this.updateDailyForecast((WeatherForecast) obj);
            }
        }));
        CompositeDisposable compositeDisposable2 = this.disposables;
        Observable map2 = Observable.fromIterable(card.getHourlyForecasts()).filter($$Lambda$WeatherPresenter$CuFmF5dkxMDlXsJQsYbCJkkGuHU.INSTANCE).map($$Lambda$WeatherPresenter$nTvbfVRRfk1OETyaINGX6bD0J5A.INSTANCE);
        final WeatherViewContract weatherViewContract2 = this.view;
        weatherViewContract2.getClass();
        compositeDisposable2.add(map2.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.weather.-$$Lambda$8wLILJ7NDybmh1SjfSsDcXweTSU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                WeatherViewContract.this.updateHourlyForecast((HourlyForecast) obj);
            }
        }));
        this.metricsInteractor.reportCardShown(WeatherCard.class.getSimpleName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherPresenterContract
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(this.interactor.getCard().getClass().getSimpleName(), this.interactionTracker.wasInteracted());
    }
}
