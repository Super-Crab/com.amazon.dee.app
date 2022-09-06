package com.amazon.alexa.voice.ui.onedesign.shopping.item;

import android.content.Context;
import com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract;
import com.amazon.alexa.voice.ui.onedesign.util.IntentUtils;
/* loaded from: classes11.dex */
public final class ShoppingItemMediator implements ShoppingItemContract.Mediator {
    private Context context;

    public ShoppingItemMediator(Context context) {
        this.context = context;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.Mediator
    public void openLinkUrl(String str) {
        IntentUtils.openLinkUrl(this.context, str);
    }
}
