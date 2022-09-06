package com.amazon.alexa.voice.ui.onedesign.shopping;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class Product implements ProductModel {
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() { // from class: com.amazon.alexa.voice.ui.onedesign.shopping.Product.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Product mo2726createFromParcel(Parcel parcel) {
            return new Product(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Product[] mo2727newArray(int i) {
            return new Product[i];
        }
    };
    private final long availableDate;
    private final String imageUrl;
    private final String linkUrl;
    private final CharSequence name;
    private final CharSequence price;
    private final boolean primeAvailable;
    private final float rating;
    private final int reviewCount;
    private final CharSequence shippedAndSoldBy;
    private final CharSequence unitPriceText;

    /* loaded from: classes11.dex */
    public static final class Builder {
        long availableDate;
        String imageUrl;
        String linkUrl;
        CharSequence name;
        CharSequence price;
        boolean primeAvailable;
        float rating;
        int reviewCount;
        CharSequence shippedAndSoldBy;
        CharSequence unitPriceText;

        public Builder availableDate(long j) {
            this.availableDate = j;
            return this;
        }

        public Product build() {
            if (this.imageUrl != null) {
                if (this.name != null) {
                    if (this.linkUrl != null) {
                        return new Product(this);
                    }
                    throw new IllegalArgumentException("linkUrl == null");
                }
                throw new IllegalArgumentException("name == null");
            }
            throw new IllegalArgumentException("imageUrl == null");
        }

        public Builder imageUrl(@NonNull String str) {
            this.imageUrl = str;
            return this;
        }

        public Builder linkUrl(@NonNull String str) {
            this.linkUrl = str;
            return this;
        }

        public Builder name(@NonNull CharSequence charSequence) {
            this.name = charSequence;
            return this;
        }

        public Builder price(CharSequence charSequence) {
            this.price = charSequence;
            return this;
        }

        public Builder primeAvailable(boolean z) {
            this.primeAvailable = z;
            return this;
        }

        public Builder rating(float f) {
            this.rating = f;
            return this;
        }

        public Builder reviewCount(int i) {
            this.reviewCount = i;
            return this;
        }

        public Builder shippedAndSoldBy(CharSequence charSequence) {
            this.shippedAndSoldBy = charSequence;
            return this;
        }

        public Builder unitPriceText(CharSequence charSequence) {
            this.unitPriceText = charSequence;
            return this;
        }
    }

    Product(Builder builder) {
        this.imageUrl = builder.imageUrl;
        this.name = builder.name;
        this.rating = builder.rating;
        this.reviewCount = builder.reviewCount;
        this.price = builder.price;
        this.unitPriceText = builder.unitPriceText;
        this.shippedAndSoldBy = builder.shippedAndSoldBy;
        this.primeAvailable = builder.primeAvailable;
        this.availableDate = builder.availableDate;
        this.linkUrl = builder.linkUrl;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Product.class != obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        if (!this.imageUrl.equals(product.imageUrl) || !this.name.equals(product.name) || Float.compare(this.rating, product.rating) != 0 || this.reviewCount != product.reviewCount) {
            return false;
        }
        CharSequence charSequence = this.price;
        if (charSequence == null ? product.price != null : !charSequence.equals(product.price)) {
            return false;
        }
        CharSequence charSequence2 = this.unitPriceText;
        if (charSequence2 == null ? product.unitPriceText != null : !charSequence2.equals(product.unitPriceText)) {
            return false;
        }
        CharSequence charSequence3 = this.shippedAndSoldBy;
        if (charSequence3 == null ? product.shippedAndSoldBy != null : !charSequence3.equals(product.shippedAndSoldBy)) {
            return false;
        }
        return this.primeAvailable == product.primeAvailable && this.availableDate == product.availableDate && this.linkUrl.equals(product.linkUrl);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel
    public long getAvailableDate() {
        return this.availableDate;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel
    @NonNull
    public String getImageUrl() {
        return this.imageUrl;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel
    @NonNull
    public String getLinkUrl() {
        return this.linkUrl;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel
    @NonNull
    public CharSequence getName() {
        return this.name;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel
    public CharSequence getPrice() {
        return this.price;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel
    public boolean getPrimeAvailable() {
        return this.primeAvailable;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel
    public float getRating() {
        return this.rating;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel
    public int getReviewCount() {
        return this.reviewCount;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel
    @Nullable
    public CharSequence getShippedAndSoldBy() {
        return this.shippedAndSoldBy;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel
    @Nullable
    public CharSequence getUnitPriceText() {
        return this.unitPriceText;
    }

    public int hashCode() {
        int outline5 = GeneratedOutlineSupport1.outline5(this.name, GeneratedOutlineSupport1.outline7(this.imageUrl, JfifUtil.MARKER_EOI, 31), 31);
        float f = this.rating;
        int i = 0;
        int floatToIntBits = (((outline5 + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31) + this.reviewCount) * 31;
        CharSequence charSequence = this.price;
        int hashCode = (floatToIntBits + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.unitPriceText;
        int hashCode2 = (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
        CharSequence charSequence3 = this.shippedAndSoldBy;
        if (charSequence3 != null) {
            i = charSequence3.hashCode();
        }
        long j = this.availableDate;
        return this.linkUrl.hashCode() + ((((((hashCode2 + i) * 31) + (this.primeAvailable ? 1 : 0)) * 31) + ((int) (j ^ (j >>> 32)))) * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Product{imageUrl=");
        outline107.append(this.imageUrl);
        outline107.append(", name=");
        outline107.append((Object) this.name);
        outline107.append(", rating=");
        outline107.append(this.rating);
        outline107.append(", reviewCount=");
        outline107.append(this.reviewCount);
        outline107.append(", price=");
        outline107.append((Object) this.price);
        outline107.append(", unitPriceText=");
        outline107.append((Object) this.unitPriceText);
        outline107.append(", shippedAndSoldBy=");
        outline107.append((Object) this.shippedAndSoldBy);
        outline107.append(", primeAvailable=");
        outline107.append(this.primeAvailable);
        outline107.append(", availableDate=");
        outline107.append(this.availableDate);
        outline107.append(", linkUrl=");
        return GeneratedOutlineSupport1.outline89(outline107, this.linkUrl, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.imageUrl);
        TextUtils.writeToParcel(this.name, parcel, i);
        parcel.writeFloat(this.rating);
        parcel.writeInt(this.reviewCount);
        TextUtils.writeToParcel(this.price, parcel, i);
        TextUtils.writeToParcel(this.unitPriceText, parcel, i);
        TextUtils.writeToParcel(this.shippedAndSoldBy, parcel, i);
        parcel.writeByte(this.primeAvailable ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.availableDate);
        parcel.writeString(this.linkUrl);
    }

    Product(Parcel parcel) {
        this.imageUrl = parcel.readString();
        this.name = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.rating = parcel.readFloat();
        this.reviewCount = parcel.readInt();
        this.price = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.unitPriceText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.shippedAndSoldBy = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.primeAvailable = parcel.readByte() != 0;
        this.availableDate = parcel.readLong();
        this.linkUrl = parcel.readString();
    }
}
