package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class Language implements LanguageModel {
    public static final Parcelable.Creator<Language> CREATOR = new Parcelable.Creator<Language>() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.language.Language.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Language mo2693createFromParcel(Parcel parcel) {
            return new Language(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Language[] mo2694newArray(int i) {
            return new Language[i];
        }
    };
    private final String country;
    private final CharSequence displayCountry;
    private final CharSequence displayLanguage;
    private final String language;

    /* loaded from: classes11.dex */
    public static final class Builder {
        String country;
        CharSequence displayCountry;
        CharSequence displayLanguage;
        String language;

        public Language build() {
            if (this.displayLanguage != null) {
                if (this.displayCountry != null) {
                    if (this.language != null) {
                        if (this.country != null) {
                            return new Language(this);
                        }
                        throw new IllegalArgumentException("country == null");
                    }
                    throw new IllegalArgumentException("language == null");
                }
                throw new IllegalArgumentException("displayCountry == null");
            }
            throw new IllegalArgumentException("displayLanguage == null");
        }

        public Builder country(@NonNull String str) {
            this.country = str;
            return this;
        }

        public Builder displayCountry(@NonNull CharSequence charSequence) {
            this.displayCountry = charSequence;
            return this;
        }

        public Builder displayLanguage(@NonNull CharSequence charSequence) {
            this.displayLanguage = charSequence;
            return this;
        }

        public Builder language(@NonNull String str) {
            this.language = str;
            return this;
        }
    }

    Language(Builder builder) {
        this.displayLanguage = builder.displayLanguage;
        this.displayCountry = builder.displayCountry;
        this.language = builder.language;
        this.country = builder.country;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Language.class != obj.getClass()) {
            return false;
        }
        Language language = (Language) obj;
        return this.displayLanguage.equals(language.displayLanguage) && this.displayCountry.equals(language.displayCountry) && this.language.equals(language.language) && this.country.equals(language.country);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguageModel
    @NonNull
    public String getCountry() {
        return this.country;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguageModel
    @NonNull
    public CharSequence getDisplayCountry() {
        return this.displayCountry;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguageModel
    @NonNull
    public CharSequence getDisplayLanguage() {
        return this.displayLanguage;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguageModel
    @NonNull
    public String getLanguage() {
        return this.language;
    }

    public int hashCode() {
        return this.country.hashCode() + GeneratedOutlineSupport1.outline7(this.language, GeneratedOutlineSupport1.outline5(this.displayCountry, GeneratedOutlineSupport1.outline5(this.displayLanguage, JfifUtil.MARKER_EOI, 31), 31), 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Language{displayLanguage=");
        outline107.append((Object) this.displayLanguage);
        outline107.append(", displayCountry=");
        outline107.append((Object) this.displayCountry);
        outline107.append(", language=");
        outline107.append(this.language);
        outline107.append(", country=");
        return GeneratedOutlineSupport1.outline89(outline107, this.country, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.displayLanguage, parcel, i);
        TextUtils.writeToParcel(this.displayCountry, parcel, i);
        parcel.writeString(this.language);
        parcel.writeString(this.country);
    }

    Language(Parcel parcel) {
        this.displayLanguage = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.displayCountry = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.language = parcel.readString();
        this.country = parcel.readString();
    }
}
