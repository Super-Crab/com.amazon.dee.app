package com.amazon.alexa.voice.ui.onedesign.weather;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class WeatherCard implements WeatherCardModel {
    public static final Parcelable.Creator<WeatherCard> CREATOR = new Parcelable.Creator<WeatherCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.weather.WeatherCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WeatherCard mo2776createFromParcel(Parcel parcel) {
            return new WeatherCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WeatherCard[] mo2777newArray(int i) {
            return new WeatherCard[i];
        }
    };
    private final CharSequence currentTemperature;
    private final CharSequence currentWeatherAlert;
    private final CharSequence currentWeatherDescription;
    private final List<? extends WeatherCardModel.ForecastModel> dailyForecasts;
    private final CharSequence highTemperature;
    private final List<? extends HourlyForecastModel> hourlyForecasts;
    private final int icon;
    private final CharSequence lowTemperature;
    private final CharSequence shortDescriptionDate;
    private final CharSequence shortTitle;
    private final CharSequence subTitle;
    private final CharSequence title;
    private final CharSequence weatherDataProvider;

    /* loaded from: classes11.dex */
    public static final class Builder {
        CharSequence currentTemperature;
        CharSequence currentWeatherAlert;
        CharSequence currentWeatherDescription;
        List<? extends WeatherCardModel.ForecastModel> dailyForecasts;
        CharSequence highTemperature;
        List<? extends HourlyForecastModel> hourlyForecasts;
        int icon;
        CharSequence lowTemperature;
        CharSequence shortDescriptionDate;
        CharSequence shortTitle;
        CharSequence subTitle;
        CharSequence title;
        CharSequence weatherDataProvider;

        public WeatherCard build() {
            if (this.title != null) {
                if (this.subTitle != null) {
                    if (this.currentTemperature != null) {
                        return new WeatherCard(this);
                    }
                    throw new IllegalArgumentException("currentTemperature == null");
                }
                throw new IllegalArgumentException("subTitle == null");
            }
            throw new IllegalArgumentException("title == null");
        }

        public Builder currentTemperature(@NonNull CharSequence charSequence) {
            this.currentTemperature = charSequence;
            return this;
        }

        public Builder currentWeatherAlert(CharSequence charSequence) {
            this.currentWeatherAlert = charSequence;
            return this;
        }

        public Builder currentWeatherDescription(CharSequence charSequence) {
            this.currentWeatherDescription = charSequence;
            return this;
        }

        public Builder dailyForecasts(List<? extends WeatherCardModel.ForecastModel> list) {
            this.dailyForecasts = list;
            return this;
        }

        public Builder highTemperature(CharSequence charSequence) {
            this.highTemperature = charSequence;
            return this;
        }

        public Builder hourlyForecasts(List<? extends HourlyForecastModel> list) {
            this.hourlyForecasts = list;
            return this;
        }

        public Builder icon(int i) {
            this.icon = i;
            return this;
        }

        public Builder lowTemperature(CharSequence charSequence) {
            this.lowTemperature = charSequence;
            return this;
        }

        public Builder shortDescriptionDate(CharSequence charSequence) {
            this.shortDescriptionDate = charSequence;
            return this;
        }

        public Builder shortTitle(CharSequence charSequence) {
            this.shortTitle = charSequence;
            return this;
        }

        public Builder subTitle(@NonNull CharSequence charSequence) {
            this.subTitle = charSequence;
            return this;
        }

        public Builder title(@NonNull CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }

        public Builder weatherDataProvider(CharSequence charSequence) {
            this.weatherDataProvider = charSequence;
            return this;
        }
    }

    WeatherCard(Builder builder) {
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.shortDescriptionDate = builder.shortDescriptionDate;
        this.shortTitle = builder.shortTitle;
        this.currentTemperature = builder.currentTemperature;
        this.lowTemperature = builder.lowTemperature;
        this.highTemperature = builder.highTemperature;
        this.icon = builder.icon;
        this.currentWeatherDescription = builder.currentWeatherDescription;
        this.weatherDataProvider = builder.weatherDataProvider;
        this.currentWeatherAlert = builder.currentWeatherAlert;
        this.dailyForecasts = builder.dailyForecasts;
        this.hourlyForecasts = builder.hourlyForecasts;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WeatherCard.class != obj.getClass()) {
            return false;
        }
        WeatherCard weatherCard = (WeatherCard) obj;
        if (!this.title.equals(weatherCard.title) || !this.subTitle.equals(weatherCard.subTitle)) {
            return false;
        }
        CharSequence charSequence = this.shortDescriptionDate;
        if (charSequence == null ? weatherCard.shortDescriptionDate != null : !charSequence.equals(weatherCard.shortDescriptionDate)) {
            return false;
        }
        CharSequence charSequence2 = this.shortTitle;
        if (charSequence2 == null ? weatherCard.shortTitle != null : !charSequence2.equals(weatherCard.shortTitle)) {
            return false;
        }
        if (!this.currentTemperature.equals(weatherCard.currentTemperature)) {
            return false;
        }
        CharSequence charSequence3 = this.lowTemperature;
        if (charSequence3 == null ? weatherCard.lowTemperature != null : !charSequence3.equals(weatherCard.lowTemperature)) {
            return false;
        }
        CharSequence charSequence4 = this.highTemperature;
        if (charSequence4 == null ? weatherCard.highTemperature != null : !charSequence4.equals(weatherCard.highTemperature)) {
            return false;
        }
        if (this.icon != weatherCard.icon) {
            return false;
        }
        CharSequence charSequence5 = this.currentWeatherDescription;
        if (charSequence5 == null ? weatherCard.currentWeatherDescription != null : !charSequence5.equals(weatherCard.currentWeatherDescription)) {
            return false;
        }
        CharSequence charSequence6 = this.weatherDataProvider;
        if (charSequence6 == null ? weatherCard.weatherDataProvider != null : !charSequence6.equals(weatherCard.weatherDataProvider)) {
            return false;
        }
        CharSequence charSequence7 = this.currentWeatherAlert;
        if (charSequence7 == null ? weatherCard.currentWeatherAlert != null : !charSequence7.equals(weatherCard.currentWeatherAlert)) {
            return false;
        }
        List<? extends WeatherCardModel.ForecastModel> list = this.dailyForecasts;
        if (list == null ? weatherCard.dailyForecasts != null : !list.equals(weatherCard.dailyForecasts)) {
            return false;
        }
        List<? extends HourlyForecastModel> list2 = this.hourlyForecasts;
        List<? extends HourlyForecastModel> list3 = weatherCard.hourlyForecasts;
        return list2 == null ? list3 == null : list2.equals(list3);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    @NonNull
    public CharSequence getCurrentTemperature() {
        return this.currentTemperature;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    public CharSequence getCurrentWeatherAlert() {
        return this.currentWeatherAlert;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    public CharSequence getCurrentWeatherDescription() {
        return this.currentWeatherDescription;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    public List<? extends WeatherCardModel.ForecastModel> getDailyForecasts() {
        return this.dailyForecasts;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    public CharSequence getHighTemperature() {
        return this.highTemperature;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    public List<? extends HourlyForecastModel> getHourlyForecasts() {
        return this.hourlyForecasts;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    public int getIcon() {
        return this.icon;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    public CharSequence getLowTemperature() {
        return this.lowTemperature;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    public CharSequence getShortDescriptionDate() {
        return this.shortDescriptionDate;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    public CharSequence getShortTitle() {
        return this.shortTitle;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    @NonNull
    public CharSequence getSubTitle() {
        return this.subTitle;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    @NonNull
    public CharSequence getTitle() {
        return this.title;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel
    public CharSequence getWeatherDataProvider() {
        return this.weatherDataProvider;
    }

    public int hashCode() {
        int outline5 = GeneratedOutlineSupport1.outline5(this.subTitle, GeneratedOutlineSupport1.outline5(this.title, JfifUtil.MARKER_EOI, 31), 31);
        CharSequence charSequence = this.shortDescriptionDate;
        int i = 0;
        int hashCode = (outline5 + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.shortTitle;
        int outline52 = GeneratedOutlineSupport1.outline5(this.currentTemperature, (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31, 31);
        CharSequence charSequence3 = this.lowTemperature;
        int hashCode2 = (outline52 + (charSequence3 != null ? charSequence3.hashCode() : 0)) * 31;
        CharSequence charSequence4 = this.highTemperature;
        int hashCode3 = (((hashCode2 + (charSequence4 != null ? charSequence4.hashCode() : 0)) * 31) + this.icon) * 31;
        CharSequence charSequence5 = this.currentWeatherDescription;
        int hashCode4 = (hashCode3 + (charSequence5 != null ? charSequence5.hashCode() : 0)) * 31;
        CharSequence charSequence6 = this.weatherDataProvider;
        int hashCode5 = (hashCode4 + (charSequence6 != null ? charSequence6.hashCode() : 0)) * 31;
        CharSequence charSequence7 = this.currentWeatherAlert;
        int hashCode6 = (hashCode5 + (charSequence7 != null ? charSequence7.hashCode() : 0)) * 31;
        List<? extends WeatherCardModel.ForecastModel> list = this.dailyForecasts;
        int hashCode7 = (hashCode6 + (list != null ? list.hashCode() : 0)) * 31;
        List<? extends HourlyForecastModel> list2 = this.hourlyForecasts;
        if (list2 != null) {
            i = list2.hashCode();
        }
        return hashCode7 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WeatherCard{title=");
        outline107.append((Object) this.title);
        outline107.append(", subTitle=");
        outline107.append((Object) this.subTitle);
        outline107.append(", shortDescriptionDate=");
        outline107.append((Object) this.shortDescriptionDate);
        outline107.append(", shortTitle=");
        outline107.append((Object) this.shortTitle);
        outline107.append(", currentTemperature=");
        outline107.append((Object) this.currentTemperature);
        outline107.append(", lowTemperature=");
        outline107.append((Object) this.lowTemperature);
        outline107.append(", highTemperature=");
        outline107.append((Object) this.highTemperature);
        outline107.append(", icon=");
        outline107.append(this.icon);
        outline107.append(", currentWeatherDescription=");
        outline107.append((Object) this.currentWeatherDescription);
        outline107.append(", weatherDataProvider=");
        outline107.append((Object) this.weatherDataProvider);
        outline107.append(", currentWeatherAlert=");
        outline107.append((Object) this.currentWeatherAlert);
        outline107.append(", dailyForecasts=");
        outline107.append(this.dailyForecasts);
        outline107.append(", hourlyForecasts=");
        return GeneratedOutlineSupport1.outline94(outline107, this.hourlyForecasts, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        TextUtils.writeToParcel(this.subTitle, parcel, i);
        TextUtils.writeToParcel(this.shortDescriptionDate, parcel, i);
        TextUtils.writeToParcel(this.shortTitle, parcel, i);
        TextUtils.writeToParcel(this.currentTemperature, parcel, i);
        TextUtils.writeToParcel(this.lowTemperature, parcel, i);
        TextUtils.writeToParcel(this.highTemperature, parcel, i);
        parcel.writeInt(this.icon);
        TextUtils.writeToParcel(this.currentWeatherDescription, parcel, i);
        TextUtils.writeToParcel(this.weatherDataProvider, parcel, i);
        TextUtils.writeToParcel(this.currentWeatherAlert, parcel, i);
        parcel.writeTypedList(this.dailyForecasts);
        parcel.writeTypedList(this.hourlyForecasts);
    }

    /* loaded from: classes11.dex */
    public static final class Forecast implements WeatherCardModel.ForecastModel {
        public static final Parcelable.Creator<Forecast> CREATOR = new Parcelable.Creator<Forecast>() { // from class: com.amazon.alexa.voice.ui.onedesign.weather.WeatherCard.Forecast.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Forecast mo2778createFromParcel(Parcel parcel) {
                return new Forecast(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Forecast[] mo2779newArray(int i) {
                return new Forecast[i];
            }
        };
        private final CharSequence date;
        private final CharSequence highTemperature;
        private final int icon;
        private final CharSequence lowTemperature;

        /* loaded from: classes11.dex */
        public static final class Builder {
            CharSequence date;
            CharSequence highTemperature;
            int icon;
            CharSequence lowTemperature;

            public Forecast build() {
                return new Forecast(this);
            }

            public Builder date(CharSequence charSequence) {
                this.date = charSequence;
                return this;
            }

            public Builder highTemperature(CharSequence charSequence) {
                this.highTemperature = charSequence;
                return this;
            }

            public Builder icon(int i) {
                this.icon = i;
                return this;
            }

            public Builder lowTemperature(CharSequence charSequence) {
                this.lowTemperature = charSequence;
                return this;
            }
        }

        Forecast(Builder builder) {
            this.date = builder.date;
            this.lowTemperature = builder.lowTemperature;
            this.highTemperature = builder.highTemperature;
            this.icon = builder.icon;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Forecast.class != obj.getClass()) {
                return false;
            }
            Forecast forecast = (Forecast) obj;
            CharSequence charSequence = this.date;
            if (charSequence == null ? forecast.date != null : !charSequence.equals(forecast.date)) {
                return false;
            }
            CharSequence charSequence2 = this.lowTemperature;
            if (charSequence2 == null ? forecast.lowTemperature != null : !charSequence2.equals(forecast.lowTemperature)) {
                return false;
            }
            CharSequence charSequence3 = this.highTemperature;
            if (charSequence3 == null ? forecast.highTemperature != null : !charSequence3.equals(forecast.highTemperature)) {
                return false;
            }
            return this.icon == forecast.icon;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel.ForecastModel
        public CharSequence getDate() {
            return this.date;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel.ForecastModel
        public CharSequence getHighTemperature() {
            return this.highTemperature;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel.ForecastModel
        public int getIcon() {
            return this.icon;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherCardModel.ForecastModel
        public CharSequence getLowTemperature() {
            return this.lowTemperature;
        }

        public int hashCode() {
            CharSequence charSequence = this.date;
            int i = 0;
            int hashCode = (JfifUtil.MARKER_EOI + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
            CharSequence charSequence2 = this.lowTemperature;
            int hashCode2 = (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
            CharSequence charSequence3 = this.highTemperature;
            if (charSequence3 != null) {
                i = charSequence3.hashCode();
            }
            return ((hashCode2 + i) * 31) + this.icon;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Forecast{date=");
            outline107.append((Object) this.date);
            outline107.append(", lowTemperature=");
            outline107.append((Object) this.lowTemperature);
            outline107.append(", highTemperature=");
            outline107.append((Object) this.highTemperature);
            outline107.append(", icon=");
            return GeneratedOutlineSupport1.outline85(outline107, this.icon, JsonReaderKt.END_OBJ);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.date, parcel, i);
            TextUtils.writeToParcel(this.lowTemperature, parcel, i);
            TextUtils.writeToParcel(this.highTemperature, parcel, i);
            parcel.writeInt(this.icon);
        }

        Forecast(Parcel parcel) {
            this.date = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.lowTemperature = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.highTemperature = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.icon = parcel.readInt();
        }
    }

    WeatherCard(Parcel parcel) {
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.subTitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.shortDescriptionDate = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.shortTitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.currentTemperature = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.lowTemperature = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.highTemperature = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.icon = parcel.readInt();
        this.currentWeatherDescription = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.weatherDataProvider = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.currentWeatherAlert = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.dailyForecasts = parcel.createTypedArrayList(Forecast.CREATOR);
        this.hourlyForecasts = parcel.createTypedArrayList(HourlyForecast.CREATOR);
    }
}
