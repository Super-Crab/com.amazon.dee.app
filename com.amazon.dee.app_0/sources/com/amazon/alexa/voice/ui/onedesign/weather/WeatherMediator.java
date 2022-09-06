package com.amazon.alexa.voice.ui.onedesign.weather;

import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class WeatherMediator implements WeatherMediatorContract {
    private final ViewController controller;

    public WeatherMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherMediatorContract
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherMediatorContract
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }
}
