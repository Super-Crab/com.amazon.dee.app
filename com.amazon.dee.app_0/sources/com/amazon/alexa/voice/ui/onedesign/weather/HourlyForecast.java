package com.amazon.alexa.voice.ui.onedesign.weather;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class HourlyForecast implements HourlyForecastModel {
    public static final Parcelable.Creator<HourlyForecast> CREATOR = new Parcelable.Creator<HourlyForecast>() { // from class: com.amazon.alexa.voice.ui.onedesign.weather.HourlyForecast.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public HourlyForecast mo2774createFromParcel(Parcel parcel) {
            return new HourlyForecast(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public HourlyForecast[] mo2775newArray(int i) {
            return new HourlyForecast[i];
        }
    };
    private final CharSequence hourlyDescriptiveText;
    private final CharSequence hourlyTemperature;
    private final int iconId;

    /* loaded from: classes11.dex */
    public static final class Builder {
        CharSequence hourlyDescriptiveText;
        CharSequence hourlyTemperature;
        int iconId;

        public HourlyForecast build() {
            return new HourlyForecast(this);
        }

        public Builder hourlyDescriptiveText(CharSequence charSequence) {
            this.hourlyDescriptiveText = charSequence;
            return this;
        }

        public Builder hourlyTemperature(CharSequence charSequence) {
            this.hourlyTemperature = charSequence;
            return this;
        }

        public Builder iconId(int i) {
            this.iconId = i;
            return this;
        }
    }

    HourlyForecast(Builder builder) {
        this.iconId = builder.iconId;
        this.hourlyTemperature = builder.hourlyTemperature;
        this.hourlyDescriptiveText = builder.hourlyDescriptiveText;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || HourlyForecast.class != obj.getClass()) {
            return false;
        }
        HourlyForecast hourlyForecast = (HourlyForecast) obj;
        if (this.iconId != hourlyForecast.iconId) {
            return false;
        }
        CharSequence charSequence = this.hourlyTemperature;
        if (charSequence == null ? hourlyForecast.hourlyTemperature != null : !charSequence.equals(hourlyForecast.hourlyTemperature)) {
            return false;
        }
        CharSequence charSequence2 = this.hourlyDescriptiveText;
        CharSequence charSequence3 = hourlyForecast.hourlyDescriptiveText;
        return charSequence2 == null ? charSequence3 == null : charSequence2.equals(charSequence3);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.HourlyForecastModel
    public CharSequence getHourlyDescriptiveText() {
        return this.hourlyDescriptiveText;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.HourlyForecastModel
    public CharSequence getHourlyTemperature() {
        return this.hourlyTemperature;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.HourlyForecastModel
    public int getIconId() {
        return this.iconId;
    }

    public int hashCode() {
        int i = (JfifUtil.MARKER_EOI + this.iconId) * 31;
        CharSequence charSequence = this.hourlyTemperature;
        int i2 = 0;
        int hashCode = (i + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.hourlyDescriptiveText;
        if (charSequence2 != null) {
            i2 = charSequence2.hashCode();
        }
        return hashCode + i2;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HourlyForecast{iconId=");
        outline107.append(this.iconId);
        outline107.append(", hourlyTemperature=");
        outline107.append((Object) this.hourlyTemperature);
        outline107.append(", hourlyDescriptiveText=");
        outline107.append((Object) this.hourlyDescriptiveText);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.iconId);
        TextUtils.writeToParcel(this.hourlyTemperature, parcel, i);
        TextUtils.writeToParcel(this.hourlyDescriptiveText, parcel, i);
    }

    HourlyForecast(Parcel parcel) {
        this.iconId = parcel.readInt();
        this.hourlyTemperature = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.hourlyDescriptiveText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }
}
