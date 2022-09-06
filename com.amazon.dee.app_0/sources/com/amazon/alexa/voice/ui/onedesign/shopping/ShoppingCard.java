package com.amazon.alexa.voice.ui.onedesign.shopping;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class ShoppingCard implements ShoppingCardModel {
    public static final Parcelable.Creator<ShoppingCard> CREATOR = new Parcelable.Creator<ShoppingCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ShoppingCard mo2729createFromParcel(Parcel parcel) {
            return new ShoppingCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ShoppingCard[] mo2730newArray(int i) {
            return new ShoppingCard[i];
        }
    };
    private final List<? extends ProductModel> productList;

    /* loaded from: classes11.dex */
    public static final class Builder {
        List<? extends ProductModel> productList = new ArrayList();

        public ShoppingCard build() {
            if (this.productList != null) {
                return new ShoppingCard(this);
            }
            throw new IllegalArgumentException("productList == null");
        }

        public Builder productList(@NonNull List<? extends ProductModel> list) {
            this.productList = list;
            return this;
        }
    }

    ShoppingCard(Builder builder) {
        this.productList = builder.productList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && ShoppingCard.class == obj.getClass() && this.productList.equals(((ShoppingCard) obj).productList);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingCardModel
    @NonNull
    public List<? extends ProductModel> getProductList() {
        return this.productList;
    }

    public int hashCode() {
        return this.productList.hashCode() + JfifUtil.MARKER_EOI;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline94(GeneratedOutlineSupport1.outline107("ShoppingCard{productList="), this.productList, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.productList);
    }

    ShoppingCard(Parcel parcel) {
        this.productList = parcel.createTypedArrayList(Product.CREATOR);
    }
}
