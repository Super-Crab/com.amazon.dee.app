package com.amazon.alexa.voice.ui.onedesign.weather;
/* loaded from: classes11.dex */
public final class WeatherInteractor implements WeatherInteractorContract {
    private final boolean alwaysFullScreen;
    private final WeatherCard card;
    private final WeatherMediatorContract mediator;
    private final boolean showVoiceIngress;

    public WeatherInteractor(WeatherCard weatherCard, WeatherMediatorContract weatherMediatorContract, boolean z, boolean z2) {
        this.card = weatherCard;
        this.mediator = weatherMediatorContract;
        this.showVoiceIngress = z;
        this.alwaysFullScreen = z2;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherInteractorContract
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherInteractorContract
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherInteractorContract
    public WeatherCard getCard() {
        return this.card;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherInteractorContract
    public boolean isAlwaysFullScreen() {
        return this.alwaysFullScreen;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherInteractorContract
    public boolean isShowVoiceIngress() {
        return this.showVoiceIngress;
    }
}
