package com.amazon.alexa.voice.ui.onedesign.weather;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class WeatherForecast implements WeatherForecastModel {
    private final CharSequence date;
    private final CharSequence highTemperature;
    private final int iconId;
    private final CharSequence lowTemperature;

    /* loaded from: classes11.dex */
    public static final class Builder {
        CharSequence date;
        CharSequence highTemperature;
        int iconId;
        CharSequence lowTemperature;

        public WeatherForecast build() {
            return new WeatherForecast(this);
        }

        public Builder date(CharSequence charSequence) {
            this.date = charSequence;
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
    }

    WeatherForecast(Builder builder) {
        this.iconId = builder.iconId;
        this.highTemperature = builder.highTemperature;
        this.lowTemperature = builder.lowTemperature;
        this.date = builder.date;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WeatherForecast.class != obj.getClass()) {
            return false;
        }
        WeatherForecast weatherForecast = (WeatherForecast) obj;
        if (this.iconId != weatherForecast.iconId) {
            return false;
        }
        CharSequence charSequence = this.highTemperature;
        if (charSequence == null ? weatherForecast.highTemperature != null : !charSequence.equals(weatherForecast.highTemperature)) {
            return false;
        }
        CharSequence charSequence2 = this.lowTemperature;
        if (charSequence2 == null ? weatherForecast.lowTemperature != null : !charSequence2.equals(weatherForecast.lowTemperature)) {
            return false;
        }
        CharSequence charSequence3 = this.date;
        CharSequence charSequence4 = weatherForecast.date;
        return charSequence3 == null ? charSequence4 == null : charSequence3.equals(charSequence4);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherForecastModel
    public CharSequence getDate() {
        return this.date;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherForecastModel
    public CharSequence getHighTemperature() {
        return this.highTemperature;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherForecastModel
    public int getIconId() {
        return this.iconId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherForecastModel
    public CharSequence getLowTemperature() {
        return this.lowTemperature;
    }

    public int hashCode() {
        int i = (JfifUtil.MARKER_EOI + this.iconId) * 31;
        CharSequence charSequence = this.highTemperature;
        int i2 = 0;
        int hashCode = (i + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.lowTemperature;
        int hashCode2 = (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
        CharSequence charSequence3 = this.date;
        if (charSequence3 != null) {
            i2 = charSequence3.hashCode();
        }
        return hashCode2 + i2;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WeatherForecast{iconId=");
        outline107.append(this.iconId);
        outline107.append(", highTemperature=");
        outline107.append((Object) this.highTemperature);
        outline107.append(", lowTemperature=");
        outline107.append((Object) this.lowTemperature);
        outline107.append(", date=");
        outline107.append((Object) this.date);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
