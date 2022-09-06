package com.amazon.alexa.voice.ui.onedesign.traffic;

import com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract;
/* loaded from: classes11.dex */
public final class TrafficInteractor implements TrafficContract.Interactor {
    private final TrafficCardModel card;
    private final TrafficContract.Mediator mediator;

    public TrafficInteractor(TrafficCardModel trafficCardModel, TrafficContract.Mediator mediator) {
        this.card = trafficCardModel;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Interactor
    public TrafficCardModel getCard() {
        return this.card;
    }
}
