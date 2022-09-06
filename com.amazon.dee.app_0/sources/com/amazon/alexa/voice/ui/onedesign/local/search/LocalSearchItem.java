package com.amazon.alexa.voice.ui.onedesign.local.search;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class LocalSearchItem implements LocalSearchItemModel {
    private final CharSequence address;
    private final List<CharSequence> categories;
    private final String dataSource;
    private final boolean displayProviderInfo;
    private final CharSequence distanceUnit;
    private final float distanceValue;
    private final String imageUrl;
    private final float rating;
    private final int reviewCount;
    private final CharSequence spendiness;
    private final String starRatingUrl;
    private final Object tag;
    private final CharSequence title;

    /* loaded from: classes11.dex */
    public static final class Builder {
        CharSequence address;
        List<CharSequence> categories = new ArrayList();
        String dataSource;
        boolean displayProviderInfo;
        CharSequence distanceUnit;
        float distanceValue;
        String imageUrl;
        float rating;
        int reviewCount;
        CharSequence spendiness;
        String starRatingUrl;
        Object tag;
        CharSequence title;

        public Builder address(CharSequence charSequence) {
            this.address = charSequence;
            return this;
        }

        public LocalSearchItem build() {
            if (this.tag != null) {
                if (this.title != null) {
                    if (this.distanceUnit != null) {
                        if (this.categories != null) {
                            return new LocalSearchItem(this);
                        }
                        throw new IllegalArgumentException("categories == null");
                    }
                    throw new IllegalArgumentException("distanceUnit == null");
                }
                throw new IllegalArgumentException("title == null");
            }
            throw new IllegalArgumentException("tag == null");
        }

        public Builder categories(@NonNull List<CharSequence> list) {
            this.categories = list;
            return this;
        }

        public Builder dataSource(String str) {
            this.dataSource = str;
            return this;
        }

        public Builder displayProviderInfo(boolean z) {
            this.displayProviderInfo = z;
            return this;
        }

        public Builder distanceUnit(@NonNull CharSequence charSequence) {
            this.distanceUnit = charSequence;
            return this;
        }

        public Builder distanceValue(float f) {
            this.distanceValue = f;
            return this;
        }

        public Builder imageUrl(String str) {
            this.imageUrl = str;
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

        public Builder spendiness(CharSequence charSequence) {
            this.spendiness = charSequence;
            return this;
        }

        public Builder starRatingUrl(String str) {
            this.starRatingUrl = str;
            return this;
        }

        public Builder tag(@NonNull Object obj) {
            this.tag = obj;
            return this;
        }

        public Builder title(@NonNull CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }
    }

    LocalSearchItem(Builder builder) {
        this.tag = builder.tag;
        this.title = builder.title;
        this.imageUrl = builder.imageUrl;
        this.dataSource = builder.dataSource;
        this.rating = builder.rating;
        this.starRatingUrl = builder.starRatingUrl;
        this.reviewCount = builder.reviewCount;
        this.spendiness = builder.spendiness;
        this.distanceValue = builder.distanceValue;
        this.distanceUnit = builder.distanceUnit;
        this.categories = builder.categories;
        this.address = builder.address;
        this.displayProviderInfo = builder.displayProviderInfo;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    public boolean displayProviderInfo() {
        return this.displayProviderInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocalSearchItem.class != obj.getClass()) {
            return false;
        }
        LocalSearchItem localSearchItem = (LocalSearchItem) obj;
        if (!this.tag.equals(localSearchItem.tag) || !this.title.equals(localSearchItem.title)) {
            return false;
        }
        String str = this.imageUrl;
        if (str == null ? localSearchItem.imageUrl != null : !str.equals(localSearchItem.imageUrl)) {
            return false;
        }
        String str2 = this.dataSource;
        if (str2 == null ? localSearchItem.dataSource != null : !str2.equals(localSearchItem.dataSource)) {
            return false;
        }
        if (Float.compare(this.rating, localSearchItem.rating) != 0) {
            return false;
        }
        String str3 = this.starRatingUrl;
        if (str3 == null ? localSearchItem.starRatingUrl != null : !str3.equals(localSearchItem.starRatingUrl)) {
            return false;
        }
        if (this.reviewCount != localSearchItem.reviewCount) {
            return false;
        }
        CharSequence charSequence = this.spendiness;
        if (charSequence == null ? localSearchItem.spendiness != null : !charSequence.equals(localSearchItem.spendiness)) {
            return false;
        }
        if (Float.compare(this.distanceValue, localSearchItem.distanceValue) != 0 || !this.distanceUnit.equals(localSearchItem.distanceUnit) || !this.categories.equals(localSearchItem.categories)) {
            return false;
        }
        CharSequence charSequence2 = this.address;
        if (charSequence2 == null ? localSearchItem.address != null : !charSequence2.equals(localSearchItem.address)) {
            return false;
        }
        return this.displayProviderInfo == localSearchItem.displayProviderInfo;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    public CharSequence getAddress() {
        return this.address;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    @NonNull
    public List<CharSequence> getCategories() {
        return this.categories;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    public String getDataSource() {
        return this.dataSource;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    @NonNull
    public CharSequence getDistanceUnit() {
        return this.distanceUnit;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    public float getDistanceValue() {
        return this.distanceValue;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    public String getImageUrl() {
        return this.imageUrl;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    public float getRating() {
        return this.rating;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    public int getReviewCount() {
        return this.reviewCount;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    public CharSequence getSpendiness() {
        return this.spendiness;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    public String getStarRatingUrl() {
        return this.starRatingUrl;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    @NonNull
    public Object getTag() {
        return this.tag;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel
    @NonNull
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        int outline5 = GeneratedOutlineSupport1.outline5(this.title, (this.tag.hashCode() + JfifUtil.MARKER_EOI) * 31, 31);
        String str = this.imageUrl;
        int i = 0;
        int hashCode = (outline5 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.dataSource;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        float f = this.rating;
        int floatToIntBits = (hashCode2 + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31;
        String str3 = this.starRatingUrl;
        int hashCode3 = (((floatToIntBits + (str3 != null ? str3.hashCode() : 0)) * 31) + this.reviewCount) * 31;
        CharSequence charSequence = this.spendiness;
        int hashCode4 = (hashCode3 + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        float f2 = this.distanceValue;
        int hashCode5 = (this.categories.hashCode() + GeneratedOutlineSupport1.outline5(this.distanceUnit, (hashCode4 + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31, 31)) * 31;
        CharSequence charSequence2 = this.address;
        if (charSequence2 != null) {
            i = charSequence2.hashCode();
        }
        return ((hashCode5 + i) * 31) + (this.displayProviderInfo ? 1 : 0);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocalSearchItem{tag=");
        outline107.append(this.tag);
        outline107.append(", title=");
        outline107.append((Object) this.title);
        outline107.append(", imageUrl=");
        outline107.append(this.imageUrl);
        outline107.append(", dataSource=");
        outline107.append(this.dataSource);
        outline107.append(", rating=");
        outline107.append(this.rating);
        outline107.append(", starRatingUrl=");
        outline107.append(this.starRatingUrl);
        outline107.append(", reviewCount=");
        outline107.append(this.reviewCount);
        outline107.append(", spendiness=");
        outline107.append((Object) this.spendiness);
        outline107.append(", distanceValue=");
        outline107.append(this.distanceValue);
        outline107.append(", distanceUnit=");
        outline107.append((Object) this.distanceUnit);
        outline107.append(", categories=");
        outline107.append(this.categories);
        outline107.append(", address=");
        outline107.append((Object) this.address);
        outline107.append(", displayProviderInfo=");
        outline107.append(this.displayProviderInfo);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
