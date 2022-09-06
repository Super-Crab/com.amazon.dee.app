package com.amazon.alexa.voice.ui.onedesign.weather;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class WeatherToday implements WeatherTodayModel {
    private final CharSequence currentTemperature;
    private final CharSequence currentWeatherAlert;
    private final CharSequence currentWeatherDescription;
    private final CharSequence highTemperature;
    private final int iconId;
    private final CharSequence lowTemperature;
    private final CharSequence realFeel;
    private final CharSequence weatherDataProvider;

    /* loaded from: classes11.dex */
    public static final class Builder {
        CharSequence currentTemperature;
        CharSequence currentWeatherAlert;
        CharSequence currentWeatherDescription;
        CharSequence highTemperature;
        int iconId;
        CharSequence lowTemperature;
        CharSequence realFeel;
        CharSequence weatherDataProvider;

        public WeatherToday build() {
            return new WeatherToday(this);
        }

        public Builder currentTemperature(CharSequence charSequence) {
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

        public Builder highTemperature(CharSequence charSequence) {
            this.highTemperature = charSequence;
            return this;
        }

        public Builder iconId(int i) {
            this.iconId = i;
            return this;
        }

        public Builder lowTemperature(CharSequence charSequence) {
            this.lowTemperature = charSequence;
            return this;
        }

        public Builder realFeel(CharSequence charSequence) {
            this.realFeel = charSequence;
            return this;
        }

        public Builder weatherDataProvider(CharSequence charSequence) {
            this.weatherDataProvider = charSequence;
            return this;
        }
    }

    WeatherToday(Builder builder) {
        this.iconId = builder.iconId;
        this.currentTemperature = builder.currentTemperature;
        this.highTemperature = builder.highTemperature;
        this.lowTemperature = builder.lowTemperature;
        this.realFeel = builder.realFeel;
        this.currentWeatherAlert = builder.currentWeatherAlert;
        this.currentWeatherDescription = builder.currentWeatherDescription;
        this.weatherDataProvider = builder.weatherDataProvider;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WeatherToday.class != obj.getClass()) {
            return false;
        }
        WeatherToday weatherToday = (WeatherToday) obj;
        if (this.iconId != weatherToday.iconId) {
            return false;
        }
        CharSequence charSequence = this.currentTemperature;
        if (charSequence == null ? weatherToday.currentTemperature != null : !charSequence.equals(weatherToday.currentTemperature)) {
            return false;
        }
        CharSequence charSequence2 = this.highTemperature;
        if (charSequence2 == null ? weatherToday.highTemperature != null : !charSequence2.equals(weatherToday.highTemperature)) {
            return false;
        }
        CharSequence charSequence3 = this.lowTemperature;
        if (charSequence3 == null ? weatherToday.lowTemperature != null : !charSequence3.equals(weatherToday.lowTemperature)) {
            return false;
        }
        CharSequence charSequence4 = this.realFeel;
        if (charSequence4 == null ? weatherToday.realFeel != null : !charSequence4.equals(weatherToday.realFeel)) {
            return false;
        }
        CharSequence charSequence5 = this.currentWeatherAlert;
        if (charSequence5 == null ? weatherToday.currentWeatherAlert != null : !charSequence5.equals(weatherToday.currentWeatherAlert)) {
            return false;
        }
        CharSequence charSequence6 = this.currentWeatherDescription;
        if (charSequence6 == null ? weatherToday.currentWeatherDescription != null : !charSequence6.equals(weatherToday.currentWeatherDescription)) {
            return false;
        }
        CharSequence charSequence7 = this.weatherDataProvider;
        CharSequence charSequence8 = weatherToday.weatherDataProvider;
        return charSequence7 == null ? charSequence8 == null : charSequence7.equals(charSequence8);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherTodayModel
    public CharSequence getCurrentTemperature() {
        return this.currentTemperature;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherTodayModel
    public CharSequence getCurrentWeatherAlert() {
        return this.currentWeatherAlert;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherTodayModel
    public CharSequence getCurrentWeatherDescription() {
        return this.currentWeatherDescription;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherTodayModel
    public CharSequence getHighTemperature() {
        return this.highTemperature;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherTodayModel
    public int getIconId() {
        return this.iconId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherTodayModel
    public CharSequence getLowTemperature() {
        return this.lowTemperature;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherTodayModel
    public CharSequence getRealFeel() {
        return this.realFeel;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherTodayModel
    public CharSequence getWeatherDataProvider() {
        return this.weatherDataProvider;
    }

    public int hashCode() {
        int i = (JfifUtil.MARKER_EOI + this.iconId) * 31;
        CharSequence charSequence = this.currentTemperature;
        int i2 = 0;
        int hashCode = (i + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.highTemperature;
        int hashCode2 = (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
        CharSequence charSequence3 = this.lowTemperature;
        int hashCode3 = (hashCode2 + (charSequence3 != null ? charSequence3.hashCode() : 0)) * 31;
        CharSequence charSequence4 = this.realFeel;
        int hashCode4 = (hashCode3 + (charSequence4 != null ? charSequence4.hashCode() : 0)) * 31;
        CharSequence charSequence5 = this.currentWeatherAlert;
        int hashCode5 = (hashCode4 + (charSequence5 != null ? charSequence5.hashCode() : 0)) * 31;
        CharSequence charSequence6 = this.currentWeatherDescription;
        int hashCode6 = (hashCode5 + (charSequence6 != null ? charSequence6.hashCode() : 0)) * 31;
        CharSequence charSequence7 = this.weatherDataProvider;
        if (charSequence7 != null) {
            i2 = charSequence7.hashCode();
        }
        return hashCode6 + i2;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WeatherToday{iconId=");
        outline107.append(this.iconId);
        outline107.append(", currentTemperature=");
        outline107.append((Object) this.currentTemperature);
        outline107.append(", highTemperature=");
        outline107.append((Object) this.highTemperature);
        outline107.append(", lowTemperature=");
        outline107.append((Object) this.lowTemperature);
        outline107.append(", realFeel=");
        outline107.append((Object) this.realFeel);
        outline107.append(", currentWeatherAlert=");
        outline107.append((Object) this.currentWeatherAlert);
        outline107.append(", currentWeatherDescription=");
        outline107.append((Object) this.currentWeatherDescription);
        outline107.append(", weatherDataProvider=");
        outline107.append((Object) this.weatherDataProvider);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
