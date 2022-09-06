package com.amazon.alexa.voice.ui.onedesign.shopping.item;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class ShoppingItem implements ShoppingItemModel {
    private final CharSequence legalText;
    private final ProductModel product;

    /* loaded from: classes11.dex */
    public static final class Builder {
        CharSequence legalText;
        ProductModel product;

        public ShoppingItem build() {
            if (this.product != null) {
                return new ShoppingItem(this);
            }
            throw new IllegalArgumentException("product == null");
        }

        public Builder legalText(CharSequence charSequence) {
            this.legalText = charSequence;
            return this;
        }

        public Builder product(@NonNull ProductModel productModel) {
            this.product = productModel;
            return this;
        }
    }

    ShoppingItem(Builder builder) {
        this.product = builder.product;
        this.legalText = builder.legalText;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ShoppingItem.class != obj.getClass()) {
            return false;
        }
        ShoppingItem shoppingItem = (ShoppingItem) obj;
        if (!this.product.equals(shoppingItem.product)) {
            return false;
        }
        CharSequence charSequence = this.legalText;
        CharSequence charSequence2 = shoppingItem.legalText;
        return charSequence == null ? charSequence2 == null : charSequence.equals(charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemModel
    public CharSequence getLegalText() {
        return this.legalText;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemModel
    @NonNull
    public ProductModel getProduct() {
        return this.product;
    }

    public int hashCode() {
        int hashCode = (this.product.hashCode() + JfifUtil.MARKER_EOI) * 31;
        CharSequence charSequence = this.legalText;
        return hashCode + (charSequence != null ? charSequence.hashCode() : 0);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ShoppingItem{product=");
        outline107.append(this.product);
        outline107.append(", legalText=");
        outline107.append((Object) this.legalText);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
