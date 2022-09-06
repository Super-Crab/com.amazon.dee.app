package com.amazon.alexa.voice.ui.onedesign.local.search;

import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract;
/* loaded from: classes11.dex */
public final class LocalSearchInteractor implements LocalSearchContract.Interactor {
    private final LocalCard card;
    private final LocalDelegate delegate;
    private final LocalSearchContract.Mediator mediator;

    public LocalSearchInteractor(LocalCard localCard, LocalSearchContract.Mediator mediator, LocalDelegate localDelegate) {
        this.card = localCard;
        this.mediator = mediator;
        this.delegate = localDelegate;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.Interactor
    public void close() {
        this.delegate.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.Interactor
    public LocalCard getCard() {
        return this.card;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.Interactor
    public void showBusiness(LocalCardModel.BusinessModel businessModel) {
        this.mediator.openBusiness(businessModel);
    }
}
