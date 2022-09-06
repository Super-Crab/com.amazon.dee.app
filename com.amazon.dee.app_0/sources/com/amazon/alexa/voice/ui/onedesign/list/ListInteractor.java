package com.amazon.alexa.voice.ui.onedesign.list;

import com.amazon.alexa.voice.ui.onedesign.list.ListContract;
/* loaded from: classes11.dex */
public final class ListInteractor implements ListContract.Interactor {
    private final ListCard card;
    private final ListContract.Mediator mediator;

    public ListInteractor(ListCard listCard, ListContract.Mediator mediator) {
        this.card = listCard;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Interactor
    public ListCard getCard() {
        return this.card;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Interactor
    public void openLists() {
        this.mediator.openSpecialList(ListType.from(this.card.getListType()));
    }
}
