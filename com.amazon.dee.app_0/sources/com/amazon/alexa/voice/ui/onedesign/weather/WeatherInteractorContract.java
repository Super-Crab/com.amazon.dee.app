package com.amazon.alexa.voice.ui.onedesign.weather;
/* loaded from: classes11.dex */
public interface WeatherInteractorContract {
    void close();

    void dismiss();

    WeatherCard getCard();

    boolean isAlwaysFullScreen();

    boolean isShowVoiceIngress();
}
