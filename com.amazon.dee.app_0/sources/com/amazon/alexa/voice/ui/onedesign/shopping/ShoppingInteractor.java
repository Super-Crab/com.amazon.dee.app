package com.amazon.alexa.voice.ui.onedesign.shopping;

import com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract;
/* loaded from: classes11.dex */
public final class ShoppingInteractor implements ShoppingContract.Interactor {
    private final ShoppingCard card;
    private final String marketplace;
    private final ShoppingContract.Mediator mediator;

    public ShoppingInteractor(ShoppingCard shoppingCard, String str, ShoppingContract.Mediator mediator) {
        this.card = shoppingCard;
        this.marketplace = str;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Interactor
    public ShoppingCard getCard() {
        return this.card;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Interactor
    public String getUserMarketplace() {
        return this.marketplace;
    }
}
