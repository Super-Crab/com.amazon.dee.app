package com.amazon.alexa.voice.ui.onedesign.standard;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class StandardCard implements StandardCardModel {
    public static final Parcelable.Creator<StandardCard> CREATOR = new Parcelable.Creator<StandardCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.standard.StandardCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public StandardCard mo2748createFromParcel(Parcel parcel) {
            return new StandardCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public StandardCard[] mo2749newArray(int i) {
            return new StandardCard[i];
        }
    };
    private final CharSequence cardType;
    private final CharSequence content;
    private final CharSequence contentImageAttribution;
    private final CharSequence contentImageUrl;
    private final List<CharSequence> domains;
    private final CharSequence linkText;
    private final CharSequence linkUrl;
    private final CharSequence subTitle;
    private final CharSequence title;

    /* loaded from: classes11.dex */
    public static final class Builder {
        CharSequence cardType;
        CharSequence content;
        CharSequence contentImageAttribution;
        CharSequence contentImageUrl;
        List<CharSequence> domains;
        CharSequence linkText;
        CharSequence linkUrl;
        CharSequence subTitle;
        CharSequence title;

        public StandardCard build() {
            return new StandardCard(this);
        }

        public Builder cardType(CharSequence charSequence) {
            this.cardType = charSequence;
            return this;
        }

        public Builder content(CharSequence charSequence) {
            this.content = charSequence;
            return this;
        }

        public Builder contentImageAttribution(CharSequence charSequence) {
            this.contentImageAttribution = charSequence;
            return this;
        }

        public Builder contentImageUrl(CharSequence charSequence) {
            this.contentImageUrl = charSequence;
            return this;
        }

        public Builder domains(List<CharSequence> list) {
            this.domains = list;
            return this;
        }

        public Builder linkText(CharSequence charSequence) {
            this.linkText = charSequence;
            return this;
        }

        public Builder linkUrl(CharSequence charSequence) {
            this.linkUrl = charSequence;
            return this;
        }

        public Builder subTitle(CharSequence charSequence) {
            this.subTitle = charSequence;
            return this;
        }

        public Builder title(CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }
    }

    StandardCard(Builder builder) {
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.content = builder.content;
        this.contentImageUrl = builder.contentImageUrl;
        this.contentImageAttribution = builder.contentImageAttribution;
        this.linkUrl = builder.linkUrl;
        this.linkText = builder.linkText;
        this.domains = builder.domains;
        this.cardType = builder.cardType;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StandardCard.class != obj.getClass()) {
            return false;
        }
        StandardCard standardCard = (StandardCard) obj;
        CharSequence charSequence = this.title;
        if (charSequence == null ? standardCard.title != null : !charSequence.equals(standardCard.title)) {
            return false;
        }
        CharSequence charSequence2 = this.subTitle;
        if (charSequence2 == null ? standardCard.subTitle != null : !charSequence2.equals(standardCard.subTitle)) {
            return false;
        }
        CharSequence charSequence3 = this.content;
        if (charSequence3 == null ? standardCard.content != null : !charSequence3.equals(standardCard.content)) {
            return false;
        }
        CharSequence charSequence4 = this.contentImageUrl;
        if (charSequence4 == null ? standardCard.contentImageUrl != null : !charSequence4.equals(standardCard.contentImageUrl)) {
            return false;
        }
        CharSequence charSequence5 = this.contentImageAttribution;
        if (charSequence5 == null ? standardCard.contentImageAttribution != null : !charSequence5.equals(standardCard.contentImageAttribution)) {
            return false;
        }
        CharSequence charSequence6 = this.linkUrl;
        if (charSequence6 == null ? standardCard.linkUrl != null : !charSequence6.equals(standardCard.linkUrl)) {
            return false;
        }
        CharSequence charSequence7 = this.linkText;
        if (charSequence7 == null ? standardCard.linkText != null : !charSequence7.equals(standardCard.linkText)) {
            return false;
        }
        List<CharSequence> list = this.domains;
        if (list == null ? standardCard.domains != null : !list.equals(standardCard.domains)) {
            return false;
        }
        CharSequence charSequence8 = this.cardType;
        CharSequence charSequence9 = standardCard.cardType;
        return charSequence8 == null ? charSequence9 == null : charSequence8.equals(charSequence9);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardCardModel
    public CharSequence getCardType() {
        return this.cardType;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardCardModel
    public CharSequence getContent() {
        return this.content;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardCardModel
    public CharSequence getContentImageAttribution() {
        return this.contentImageAttribution;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardCardModel
    public CharSequence getContentImageUrl() {
        return this.contentImageUrl;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardCardModel
    public List<CharSequence> getDomains() {
        return this.domains;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardCardModel
    public CharSequence getLinkText() {
        return this.linkText;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardCardModel
    public CharSequence getLinkUrl() {
        return this.linkUrl;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardCardModel
    public CharSequence getSubTitle() {
        return this.subTitle;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardCardModel
    @Nullable
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        CharSequence charSequence = this.title;
        int i = 0;
        int hashCode = (JfifUtil.MARKER_EOI + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.subTitle;
        int hashCode2 = (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
        CharSequence charSequence3 = this.content;
        int hashCode3 = (hashCode2 + (charSequence3 != null ? charSequence3.hashCode() : 0)) * 31;
        CharSequence charSequence4 = this.contentImageUrl;
        int hashCode4 = (hashCode3 + (charSequence4 != null ? charSequence4.hashCode() : 0)) * 31;
        CharSequence charSequence5 = this.contentImageAttribution;
        int hashCode5 = (hashCode4 + (charSequence5 != null ? charSequence5.hashCode() : 0)) * 31;
        CharSequence charSequence6 = this.linkUrl;
        int hashCode6 = (hashCode5 + (charSequence6 != null ? charSequence6.hashCode() : 0)) * 31;
        CharSequence charSequence7 = this.linkText;
        int hashCode7 = (hashCode6 + (charSequence7 != null ? charSequence7.hashCode() : 0)) * 31;
        List<CharSequence> list = this.domains;
        int hashCode8 = (hashCode7 + (list != null ? list.hashCode() : 0)) * 31;
        CharSequence charSequence8 = this.cardType;
        if (charSequence8 != null) {
            i = charSequence8.hashCode();
        }
        return hashCode8 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StandardCard{title=");
        outline107.append((Object) this.title);
        outline107.append(", subTitle=");
        outline107.append((Object) this.subTitle);
        outline107.append(", content=");
        outline107.append((Object) this.content);
        outline107.append(", contentImageUrl=");
        outline107.append((Object) this.contentImageUrl);
        outline107.append(", contentImageAttribution=");
        outline107.append((Object) this.contentImageAttribution);
        outline107.append(", linkUrl=");
        outline107.append((Object) this.linkUrl);
        outline107.append(", linkText=");
        outline107.append((Object) this.linkText);
        outline107.append(", domains=");
        outline107.append(this.domains);
        outline107.append(", cardType=");
        outline107.append((Object) this.cardType);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        TextUtils.writeToParcel(this.subTitle, parcel, i);
        TextUtils.writeToParcel(this.content, parcel, i);
        TextUtils.writeToParcel(this.contentImageUrl, parcel, i);
        TextUtils.writeToParcel(this.contentImageAttribution, parcel, i);
        TextUtils.writeToParcel(this.linkUrl, parcel, i);
        TextUtils.writeToParcel(this.linkText, parcel, i);
        List<CharSequence> list = this.domains;
        if (list != null) {
            parcel.writeInt(list.size());
            for (CharSequence charSequence : this.domains) {
                TextUtils.writeToParcel(charSequence, parcel, i);
            }
        } else {
            parcel.writeInt(0);
        }
        TextUtils.writeToParcel(this.cardType, parcel, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    StandardCard(Parcel parcel) {
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.subTitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.content = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.contentImageUrl = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.contentImageAttribution = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.linkUrl = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.linkText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        int readInt = parcel.readInt();
        this.domains = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            this.domains.add(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
        }
        this.cardType = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }
}
