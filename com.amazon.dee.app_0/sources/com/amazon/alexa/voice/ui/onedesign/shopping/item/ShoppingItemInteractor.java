package com.amazon.alexa.voice.ui.onedesign.shopping.item;

import com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract;
/* loaded from: classes11.dex */
public final class ShoppingItemInteractor implements ShoppingItemContract.Interactor {
    private final ShoppingItemModel item;
    private final ShoppingItemContract.Mediator mediator;

    public ShoppingItemInteractor(ShoppingItemModel shoppingItemModel, ShoppingItemContract.Mediator mediator) {
        this.item = shoppingItemModel;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.Interactor
    public ShoppingItemModel getShoppingItem() {
        return this.item;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.Interactor
    public void openLink() {
        this.mediator.openLinkUrl(this.item.getProduct().getLinkUrl());
    }
}
