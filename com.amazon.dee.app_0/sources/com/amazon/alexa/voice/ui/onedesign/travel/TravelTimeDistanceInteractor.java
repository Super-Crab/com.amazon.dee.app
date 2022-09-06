package com.amazon.alexa.voice.ui.onedesign.travel;

import com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract;
/* loaded from: classes11.dex */
public final class TravelTimeDistanceInteractor implements TravelTimeDistanceContract.Interactor {
    private final TravelTimeDistanceModel card;
    private final TravelTimeDistanceContract.Mediator mediator;

    public TravelTimeDistanceInteractor(TravelTimeDistanceModel travelTimeDistanceModel, TravelTimeDistanceContract.Mediator mediator) {
        this.card = travelTimeDistanceModel;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Interactor
    public TravelTimeDistanceModel getCard() {
        return this.card;
    }
}
