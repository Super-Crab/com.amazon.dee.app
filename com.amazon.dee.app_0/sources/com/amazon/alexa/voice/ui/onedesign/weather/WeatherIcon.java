package com.amazon.alexa.voice.ui.onedesign.weather;

import androidx.annotation.DrawableRes;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public enum WeatherIcon {
    UNKNOWN(R.drawable.ic_od_sunny_36dp, R.drawable.ic_od_clear_hero),
    SUNNY(R.drawable.ic_od_sunny_36dp, R.drawable.ic_od_clear_hero, 1, 5, 30),
    CLEAR_NIGHT(R.drawable.ic_od_clear_night_36dp, R.drawable.ic_od_clear_night_hero, 33),
    CLOUDY(R.drawable.ic_od_cloudy_36dp, R.drawable.ic_od_cloudy_hero, 7, 8, 11),
    CLOUD_RAINY_NIGHT(R.drawable.ic_od_partly_cloudy_rain_night_36dp, R.drawable.ic_od_partly_cloudy_rain_night_hero, 39, 40),
    LIGHTNING(R.drawable.ic_od_lightning_36dp, R.drawable.ic_od_lightning_hero, 15, 16, 17, 41, 42),
    PARTLY_CLOUDY(R.drawable.ic_od_partly_cloudy_36dp, R.drawable.ic_od_partly_cloudy_hero, 2, 3, 4, 6),
    PARTLY_CLOUDY_NIGHT(R.drawable.ic_od_partly_cloudy_night_36dp, R.drawable.ic_od_partly_cloudy_night_hero, 34, 35, 36, 37, 38),
    PARTLY_CLOUDY_RAIN(R.drawable.ic_od_partly_cloudy_rain_36dp, R.drawable.ic_od_partly_cloudy_rain_hero, 13, 14),
    RAINY(R.drawable.ic_od_rainy_36dp, R.drawable.ic_od_rainy_hero, 12, 18),
    SNOW(R.drawable.ic_od_snow_36dp, R.drawable.ic_od_snowy_hero, 19, 20, 21, 22, 23, 24, 25, 26, 29, 31, 43, 44),
    WINDY(R.drawable.ic_od_wind_36dp, R.drawable.ic_od_windy_hero, 32);
    
    private final int iconLargeId;
    private final int iconSmallId;
    private final int[] indices;

    /* renamed from: com.amazon.alexa.voice.ui.onedesign.weather.WeatherIcon$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$voice$ui$onedesign$weather$WeatherIcon = new int[WeatherIcon.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$weather$WeatherIcon[WeatherIcon.CLEAR_NIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$weather$WeatherIcon[WeatherIcon.CLOUD_RAINY_NIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$weather$WeatherIcon[WeatherIcon.PARTLY_CLOUDY_NIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$weather$WeatherIcon[WeatherIcon.SUNNY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    WeatherIcon(@DrawableRes int i, @DrawableRes int i2) {
        this.iconSmallId = i;
        this.iconLargeId = i2;
        this.indices = new int[0];
    }

    private boolean is(int i) {
        for (int i2 : this.indices) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIconForNight(WeatherIcon weatherIcon) {
        int ordinal = weatherIcon.ordinal();
        return ordinal == 2 || ordinal == 4 || ordinal == 7;
    }

    public static boolean isIconForSunny(WeatherIcon weatherIcon) {
        return weatherIcon.ordinal() == 1;
    }

    public static WeatherIcon of(int i) {
        WeatherIcon[] values;
        for (WeatherIcon weatherIcon : values()) {
            if (weatherIcon.is(i)) {
                return weatherIcon;
            }
        }
        return UNKNOWN;
    }

    public int getIconLargeId() {
        return this.iconLargeId;
    }

    public int getIconSmallId() {
        return this.iconSmallId;
    }

    WeatherIcon(@DrawableRes int i, @DrawableRes int i2, int... iArr) {
        this.iconSmallId = i;
        this.iconLargeId = i2;
        this.indices = iArr;
    }
}
