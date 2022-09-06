package com.amazon.alexa.voice.ui.onedesign.local;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class LocalCard implements LocalCardModel {
    public static final Parcelable.Creator<LocalCard> CREATOR = new Parcelable.Creator<LocalCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.local.LocalCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public LocalCard mo2704createFromParcel(Parcel parcel) {
            return new LocalCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public LocalCard[] mo2705newArray(int i) {
            return new LocalCard[i];
        }
    };
    private final List<? extends LocalCardModel.BusinessModel> businessList;
    private final String sortKey;
    private final CharSequence title;

    /* loaded from: classes11.dex */
    public static final class Builder {
        List<? extends LocalCardModel.BusinessModel> businessList = new ArrayList();
        String sortKey;
        CharSequence title;

        public LocalCard build() {
            if (this.title != null) {
                if (this.sortKey != null) {
                    if (this.businessList != null) {
                        return new LocalCard(this);
                    }
                    throw new IllegalArgumentException("businessList == null");
                }
                throw new IllegalArgumentException("sortKey == null");
            }
            throw new IllegalArgumentException("title == null");
        }

        public Builder businessList(@NonNull List<? extends LocalCardModel.BusinessModel> list) {
            this.businessList = list;
            return this;
        }

        public Builder sortKey(@NonNull String str) {
            this.sortKey = str;
            return this;
        }

        public Builder title(@NonNull CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }
    }

    LocalCard(Builder builder) {
        this.title = builder.title;
        this.sortKey = builder.sortKey;
        this.businessList = builder.businessList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocalCard.class != obj.getClass()) {
            return false;
        }
        LocalCard localCard = (LocalCard) obj;
        return this.title.equals(localCard.title) && this.sortKey.equals(localCard.sortKey) && this.businessList.equals(localCard.businessList);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel
    @NonNull
    public List<? extends LocalCardModel.BusinessModel> getBusinessList() {
        return this.businessList;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel
    @NonNull
    public String getSortKey() {
        return this.sortKey;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel
    @NonNull
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.businessList.hashCode() + GeneratedOutlineSupport1.outline7(this.sortKey, GeneratedOutlineSupport1.outline5(this.title, JfifUtil.MARKER_EOI, 31), 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocalCard{title=");
        outline107.append((Object) this.title);
        outline107.append(", sortKey=");
        outline107.append(this.sortKey);
        outline107.append(", businessList=");
        return GeneratedOutlineSupport1.outline94(outline107, this.businessList, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        parcel.writeString(this.sortKey);
        parcel.writeTypedList(this.businessList);
    }

    LocalCard(Parcel parcel) {
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.sortKey = parcel.readString();
        this.businessList = parcel.createTypedArrayList(Business.CREATOR);
    }

    /* loaded from: classes11.dex */
    public static final class Business implements LocalCardModel.BusinessModel {
        public static final Parcelable.Creator<Business> CREATOR = new Parcelable.Creator<Business>() { // from class: com.amazon.alexa.voice.ui.onedesign.local.LocalCard.Business.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Business mo2706createFromParcel(Parcel parcel) {
                return new Business(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Business[] mo2707newArray(int i) {
                return new Business[i];
            }
        };
        private final CharSequence address;
        private final List<CharSequence> categories;
        private final String dataSource;
        private final CharSequence distanceUnit;
        private final float distanceValue;
        private final CharSequence geoLocation;
        private final String goToActionText;
        private final String goToActionUrl;
        private final String imageUrl;
        private final String linkUrl;
        private final String mergedProviderInfo;
        private final CharSequence name;
        private final List<CharSequence> openingHours;
        private final CharSequence phoneNumber;
        private final String providerLogoUrl;
        private final float rating;
        private final int reviewCount;
        private final String specialBusinessHoursDisplayMessage;
        private final String specialBusinessHoursDisplayType;
        private final CharSequence spendiness;
        private final String starRatingUrl;

        /* loaded from: classes11.dex */
        public static final class Builder {
            CharSequence address;
            String dataSource;
            CharSequence distanceUnit;
            float distanceValue;
            CharSequence geoLocation;
            String goToActionText;
            String goToActionUrl;
            String imageUrl;
            String linkUrl;
            String mergedProviderInfo;
            CharSequence name;
            CharSequence phoneNumber;
            String providerLogoUrl;
            float rating;
            int reviewCount;
            String specialBusinessHoursDisplayMessage;
            String specialBusinessHoursDisplayType;
            CharSequence spendiness;
            String starRatingUrl;
            List<CharSequence> categories = new ArrayList();
            List<CharSequence> openingHours = new ArrayList();

            public Builder address(@NonNull CharSequence charSequence) {
                this.address = charSequence;
                return this;
            }

            public Business build() {
                if (this.name != null) {
                    if (this.distanceUnit != null) {
                        if (this.imageUrl != null) {
                            if (this.providerLogoUrl != null) {
                                if (this.starRatingUrl != null) {
                                    if (this.categories != null) {
                                        if (this.address != null) {
                                            if (this.openingHours != null) {
                                                return new Business(this);
                                            }
                                            throw new IllegalArgumentException("openingHours == null");
                                        }
                                        throw new IllegalArgumentException("address == null");
                                    }
                                    throw new IllegalArgumentException("categories == null");
                                }
                                throw new IllegalArgumentException("starRatingUrl == null");
                            }
                            throw new IllegalArgumentException("providerLogoUrl == null");
                        }
                        throw new IllegalArgumentException("imageUrl == null");
                    }
                    throw new IllegalArgumentException("distanceUnit == null");
                }
                throw new IllegalArgumentException("name == null");
            }

            public Builder categories(@NonNull List<CharSequence> list) {
                this.categories = list;
                return this;
            }

            public Builder dataSource(String str) {
                this.dataSource = str;
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

            public Builder geoLocation(CharSequence charSequence) {
                this.geoLocation = charSequence;
                return this;
            }

            public Builder goToActionText(String str) {
                this.goToActionText = str;
                return this;
            }

            public Builder goToActionUrl(String str) {
                this.goToActionUrl = str;
                return this;
            }

            public Builder imageUrl(@NonNull String str) {
                this.imageUrl = str;
                return this;
            }

            public Builder linkUrl(String str) {
                this.linkUrl = str;
                return this;
            }

            public Builder mergedProviderInfo(String str) {
                this.mergedProviderInfo = str;
                return this;
            }

            public Builder name(@NonNull CharSequence charSequence) {
                this.name = charSequence;
                return this;
            }

            public Builder openingHours(@NonNull List<CharSequence> list) {
                this.openingHours = list;
                return this;
            }

            public Builder phoneNumber(CharSequence charSequence) {
                this.phoneNumber = charSequence;
                return this;
            }

            public Builder providerLogoUrl(@NonNull String str) {
                this.providerLogoUrl = str;
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

            public Builder specialBusinessHoursDisplayMessage(String str) {
                this.specialBusinessHoursDisplayMessage = str;
                return this;
            }

            public Builder specialBusinessHoursDisplayType(String str) {
                this.specialBusinessHoursDisplayType = str;
                return this;
            }

            public Builder spendiness(CharSequence charSequence) {
                this.spendiness = charSequence;
                return this;
            }

            public Builder starRatingUrl(@NonNull String str) {
                this.starRatingUrl = str;
                return this;
            }
        }

        Business(Builder builder) {
            this.name = builder.name;
            this.dataSource = builder.dataSource;
            this.reviewCount = builder.reviewCount;
            this.distanceValue = builder.distanceValue;
            this.distanceUnit = builder.distanceUnit;
            this.spendiness = builder.spendiness;
            this.imageUrl = builder.imageUrl;
            this.providerLogoUrl = builder.providerLogoUrl;
            this.mergedProviderInfo = builder.mergedProviderInfo;
            this.rating = builder.rating;
            this.starRatingUrl = builder.starRatingUrl;
            this.categories = builder.categories;
            this.address = builder.address;
            this.geoLocation = builder.geoLocation;
            this.phoneNumber = builder.phoneNumber;
            this.openingHours = builder.openingHours;
            this.linkUrl = builder.linkUrl;
            this.goToActionText = builder.goToActionText;
            this.goToActionUrl = builder.goToActionUrl;
            this.specialBusinessHoursDisplayMessage = builder.specialBusinessHoursDisplayMessage;
            this.specialBusinessHoursDisplayType = builder.specialBusinessHoursDisplayType;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Business.class != obj.getClass()) {
                return false;
            }
            Business business = (Business) obj;
            if (!this.name.equals(business.name)) {
                return false;
            }
            String str = this.dataSource;
            if (str == null ? business.dataSource != null : !str.equals(business.dataSource)) {
                return false;
            }
            if (this.reviewCount != business.reviewCount || Float.compare(this.distanceValue, business.distanceValue) != 0 || !this.distanceUnit.equals(business.distanceUnit)) {
                return false;
            }
            CharSequence charSequence = this.spendiness;
            if (charSequence == null ? business.spendiness != null : !charSequence.equals(business.spendiness)) {
                return false;
            }
            if (!this.imageUrl.equals(business.imageUrl) || !this.providerLogoUrl.equals(business.providerLogoUrl)) {
                return false;
            }
            String str2 = this.mergedProviderInfo;
            if (str2 == null ? business.mergedProviderInfo != null : !str2.equals(business.mergedProviderInfo)) {
                return false;
            }
            if (Float.compare(this.rating, business.rating) != 0 || !this.starRatingUrl.equals(business.starRatingUrl) || !this.categories.equals(business.categories) || !this.address.equals(business.address)) {
                return false;
            }
            CharSequence charSequence2 = this.geoLocation;
            if (charSequence2 == null ? business.geoLocation != null : !charSequence2.equals(business.geoLocation)) {
                return false;
            }
            CharSequence charSequence3 = this.phoneNumber;
            if (charSequence3 == null ? business.phoneNumber != null : !charSequence3.equals(business.phoneNumber)) {
                return false;
            }
            if (!this.openingHours.equals(business.openingHours)) {
                return false;
            }
            String str3 = this.linkUrl;
            if (str3 == null ? business.linkUrl != null : !str3.equals(business.linkUrl)) {
                return false;
            }
            String str4 = this.goToActionText;
            if (str4 == null ? business.goToActionText != null : !str4.equals(business.goToActionText)) {
                return false;
            }
            String str5 = this.goToActionUrl;
            if (str5 == null ? business.goToActionUrl != null : !str5.equals(business.goToActionUrl)) {
                return false;
            }
            String str6 = this.specialBusinessHoursDisplayMessage;
            if (str6 == null ? business.specialBusinessHoursDisplayMessage != null : !str6.equals(business.specialBusinessHoursDisplayMessage)) {
                return false;
            }
            String str7 = this.specialBusinessHoursDisplayType;
            String str8 = business.specialBusinessHoursDisplayType;
            return str7 == null ? str8 == null : str7.equals(str8);
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        @NonNull
        public CharSequence getAddress() {
            return this.address;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        @NonNull
        public List<CharSequence> getCategories() {
            return this.categories;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public String getDataSource() {
            return this.dataSource;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        @NonNull
        public CharSequence getDistanceUnit() {
            return this.distanceUnit;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public float getDistanceValue() {
            return this.distanceValue;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public CharSequence getGeoLocation() {
            return this.geoLocation;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        @NonNull
        public String getImageUrl() {
            return this.imageUrl;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public String getLinkUrl() {
            return this.linkUrl;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public String getMergedProviderInfo() {
            return this.mergedProviderInfo;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        @NonNull
        public CharSequence getName() {
            return this.name;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        @NonNull
        public List<CharSequence> getOpeningHours() {
            return this.openingHours;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public CharSequence getPhoneNumber() {
            return this.phoneNumber;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        @NonNull
        public String getProviderLogoUrl() {
            return this.providerLogoUrl;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public float getRating() {
            return this.rating;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public int getReviewCount() {
            return this.reviewCount;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public String getSpecialBusinessHoursDisplayMessage() {
            return this.specialBusinessHoursDisplayMessage;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public String getSpecialBusinessHoursDisplayType() {
            return this.specialBusinessHoursDisplayType;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public CharSequence getSpendiness() {
            return this.spendiness;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        @NonNull
        public String getStarRatingUrl() {
            return this.starRatingUrl;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public String goToActionText() {
            return this.goToActionText;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel.BusinessModel
        public String goToActionUrl() {
            return this.goToActionUrl;
        }

        public int hashCode() {
            int outline5 = GeneratedOutlineSupport1.outline5(this.name, JfifUtil.MARKER_EOI, 31);
            String str = this.dataSource;
            int i = 0;
            int hashCode = (((outline5 + (str != null ? str.hashCode() : 0)) * 31) + this.reviewCount) * 31;
            float f = this.distanceValue;
            int outline52 = GeneratedOutlineSupport1.outline5(this.distanceUnit, (hashCode + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31, 31);
            CharSequence charSequence = this.spendiness;
            int outline7 = GeneratedOutlineSupport1.outline7(this.providerLogoUrl, GeneratedOutlineSupport1.outline7(this.imageUrl, (outline52 + (charSequence != null ? charSequence.hashCode() : 0)) * 31, 31), 31);
            String str2 = this.mergedProviderInfo;
            int hashCode2 = (outline7 + (str2 != null ? str2.hashCode() : 0)) * 31;
            float f2 = this.rating;
            int outline53 = GeneratedOutlineSupport1.outline5(this.address, (this.categories.hashCode() + GeneratedOutlineSupport1.outline7(this.starRatingUrl, (hashCode2 + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31, 31)) * 31, 31);
            CharSequence charSequence2 = this.geoLocation;
            int hashCode3 = (outline53 + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
            CharSequence charSequence3 = this.phoneNumber;
            int hashCode4 = (this.openingHours.hashCode() + ((hashCode3 + (charSequence3 != null ? charSequence3.hashCode() : 0)) * 31)) * 31;
            String str3 = this.linkUrl;
            int hashCode5 = (hashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.goToActionText;
            int hashCode6 = (hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.goToActionUrl;
            int hashCode7 = (hashCode6 + (str5 != null ? str5.hashCode() : 0)) * 31;
            String str6 = this.specialBusinessHoursDisplayMessage;
            int hashCode8 = (hashCode7 + (str6 != null ? str6.hashCode() : 0)) * 31;
            String str7 = this.specialBusinessHoursDisplayType;
            if (str7 != null) {
                i = str7.hashCode();
            }
            return hashCode8 + i;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Business{name=");
            outline107.append((Object) this.name);
            outline107.append(", dataSource=");
            outline107.append(this.dataSource);
            outline107.append(", reviewCount=");
            outline107.append(this.reviewCount);
            outline107.append(", distanceValue=");
            outline107.append(this.distanceValue);
            outline107.append(", distanceUnit=");
            outline107.append((Object) this.distanceUnit);
            outline107.append(", spendiness=");
            outline107.append((Object) this.spendiness);
            outline107.append(", imageUrl=");
            outline107.append(this.imageUrl);
            outline107.append(", providerLogoUrl=");
            outline107.append(this.providerLogoUrl);
            outline107.append(", mergedProviderInfo=");
            outline107.append(this.mergedProviderInfo);
            outline107.append(", rating=");
            outline107.append(this.rating);
            outline107.append(", starRatingUrl=");
            outline107.append(this.starRatingUrl);
            outline107.append(", categories=");
            outline107.append(this.categories);
            outline107.append(", address=");
            outline107.append((Object) this.address);
            outline107.append(", geoLocation=");
            outline107.append((Object) this.geoLocation);
            outline107.append(", phoneNumber=");
            outline107.append((Object) this.phoneNumber);
            outline107.append(", openingHours=");
            outline107.append(this.openingHours);
            outline107.append(", linkUrl=");
            outline107.append(this.linkUrl);
            outline107.append(", goToActionText=");
            outline107.append(this.goToActionText);
            outline107.append(", goToActionUrl=");
            outline107.append(this.goToActionUrl);
            outline107.append(", specialBusinessHoursDisplayMessage=");
            outline107.append(this.specialBusinessHoursDisplayMessage);
            outline107.append(", specialBusinessHoursDisplayType=");
            return GeneratedOutlineSupport1.outline89(outline107, this.specialBusinessHoursDisplayType, JsonReaderKt.END_OBJ);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.name, parcel, i);
            parcel.writeString(this.dataSource);
            parcel.writeInt(this.reviewCount);
            parcel.writeFloat(this.distanceValue);
            TextUtils.writeToParcel(this.distanceUnit, parcel, i);
            TextUtils.writeToParcel(this.spendiness, parcel, i);
            parcel.writeString(this.imageUrl);
            parcel.writeString(this.providerLogoUrl);
            parcel.writeString(this.mergedProviderInfo);
            parcel.writeFloat(this.rating);
            parcel.writeString(this.starRatingUrl);
            List<CharSequence> list = this.categories;
            if (list != null) {
                parcel.writeInt(list.size());
                for (CharSequence charSequence : this.categories) {
                    TextUtils.writeToParcel(charSequence, parcel, i);
                }
            } else {
                parcel.writeInt(0);
            }
            TextUtils.writeToParcel(this.address, parcel, i);
            TextUtils.writeToParcel(this.geoLocation, parcel, i);
            TextUtils.writeToParcel(this.phoneNumber, parcel, i);
            List<CharSequence> list2 = this.openingHours;
            if (list2 != null) {
                parcel.writeInt(list2.size());
                for (CharSequence charSequence2 : this.openingHours) {
                    TextUtils.writeToParcel(charSequence2, parcel, i);
                }
            } else {
                parcel.writeInt(0);
            }
            parcel.writeString(this.linkUrl);
            parcel.writeString(this.goToActionText);
            parcel.writeString(this.goToActionUrl);
            parcel.writeString(this.specialBusinessHoursDisplayMessage);
            parcel.writeString(this.specialBusinessHoursDisplayType);
        }

        /* JADX WARN: Multi-variable type inference failed */
        Business(Parcel parcel) {
            this.name = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.dataSource = parcel.readString();
            this.reviewCount = parcel.readInt();
            this.distanceValue = parcel.readFloat();
            this.distanceUnit = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.spendiness = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.imageUrl = parcel.readString();
            this.providerLogoUrl = parcel.readString();
            this.mergedProviderInfo = parcel.readString();
            this.rating = parcel.readFloat();
            this.starRatingUrl = parcel.readString();
            int readInt = parcel.readInt();
            this.categories = new ArrayList(readInt);
            for (int i = 0; i < readInt; i++) {
                this.categories.add(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
            }
            this.address = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.geoLocation = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.phoneNumber = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            int readInt2 = parcel.readInt();
            this.openingHours = new ArrayList(readInt2);
            for (int i2 = 0; i2 < readInt2; i2++) {
                this.openingHours.add(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
            }
            this.linkUrl = parcel.readString();
            this.goToActionText = parcel.readString();
            this.goToActionUrl = parcel.readString();
            this.specialBusinessHoursDisplayMessage = parcel.readString();
            this.specialBusinessHoursDisplayType = parcel.readString();
        }
    }
}
