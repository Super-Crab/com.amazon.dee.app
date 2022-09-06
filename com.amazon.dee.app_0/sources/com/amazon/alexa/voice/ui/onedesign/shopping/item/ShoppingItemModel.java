package com.amazon.alexa.voice.ui.onedesign.shopping.item;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface ShoppingItemModel {
    CharSequence getLegalText();

    @NonNull
    ProductModel getProduct();
}
