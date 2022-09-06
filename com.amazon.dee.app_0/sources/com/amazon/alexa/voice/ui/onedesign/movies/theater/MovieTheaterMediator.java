package com.amazon.alexa.voice.ui.onedesign.movies.theater;

import com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract;
import com.amazon.alexa.voice.ui.onedesign.util.IntentUtils;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class MovieTheaterMediator implements MovieTheaterContract.Mediator {
    private final ViewController controller;

    public MovieTheaterMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Mediator
    public void openLocation(String str) {
        ViewController viewController = this.controller;
        IntentUtils.openLinkUrl(viewController, "geo:0,0?q=" + str);
    }
}
