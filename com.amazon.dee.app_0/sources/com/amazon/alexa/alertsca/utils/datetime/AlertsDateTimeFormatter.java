package com.amazon.alexa.alertsca.utils.datetime;

import android.content.Context;
import android.text.format.DateFormat;
import androidx.core.os.ConfigurationCompat;
import com.amazon.alexa.alertsca.utils.datetime.overriders.Alerts12hTimeFormatOverrider;
import com.amazon.alexa.alertsca.utils.datetime.overriders.AlertsDateFormatOverrider;
import com.amazon.alexa.alertsca.utils.datetime.overriders.AlertsTimeOrderOverrider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes6.dex */
public class AlertsDateTimeFormatter {
    private static final String ALARM_DISPLAY_DATE_DEFAULT_FORMATTER = "EEE, MMM d";
    private static final String ALARM_DISPLAY_TIME_12HOUR_FORMATTER = "h:mm";
    private static final String ALARM_DISPLAY_TIME_24HOUR_FORMATTER = "HH:mm";
    private static final String ALARM_DISPLAY_TIME_AM_PM_FORMATTER = "a";
    private static final String TAG = "AlertsDateTimeFormatter";
    private static final String TIME_OF_DAY_FORMAT = "%s %s";
    private Alerts12hTimeFormatOverrider alerts12hTimeFormatOverrider;
    private AlertsDateFormatOverrider alertsDateFormatOverrider;
    private AlertsTimeOrderOverrider alertsTimeOrderOverrider;
    private final Locale currentLocale;
    private final boolean use24HourFormatPreference;

    public AlertsDateTimeFormatter(Context context) {
        this.currentLocale = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).get(0);
        this.use24HourFormatPreference = DateFormat.is24HourFormat(context);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Constructor: currentLocale = ");
        outline107.append(this.currentLocale);
        outline107.append(", use24HourFormatPreference = ");
        outline107.append(this.use24HourFormatPreference);
        outline107.toString();
        initOverriders();
    }

    private String formatAmPmOfDay(Date date, Locale locale) {
        return new SimpleDateFormat(ALARM_DISPLAY_TIME_AM_PM_FORMATTER, locale).format(date);
    }

    private String formatHourMin(Date date, Locale locale) {
        return new SimpleDateFormat(getHourMinFormatter(locale), locale).format(date);
    }

    private String getDateFormatter(Locale locale) {
        return this.alertsDateFormatOverrider.overrideOrDefault(locale, ALARM_DISPLAY_DATE_DEFAULT_FORMATTER);
    }

    private String getHourMinFormatter(Locale locale) {
        return this.use24HourFormatPreference ? ALARM_DISPLAY_TIME_24HOUR_FORMATTER : this.alerts12hTimeFormatOverrider.overrideOrDefault(locale, ALARM_DISPLAY_TIME_12HOUR_FORMATTER);
    }

    private void initOverriders() {
        this.alerts12hTimeFormatOverrider = new Alerts12hTimeFormatOverrider();
        this.alertsDateFormatOverrider = new AlertsDateFormatOverrider();
        this.alertsTimeOrderOverrider = new AlertsTimeOrderOverrider();
    }

    public String formatDate(Date date) {
        return formatDate(date, this.currentLocale);
    }

    public String formatTime(Date date) {
        return formatTime(date, this.currentLocale);
    }

    public String formatDate(Date date, Locale locale) {
        return new SimpleDateFormat(getDateFormatter(locale), locale).format(date);
    }

    public String formatTime(Date date, Locale locale) {
        String formatHourMin = formatHourMin(date, locale);
        if (this.use24HourFormatPreference) {
            return formatHourMin;
        }
        String formatAmPmOfDay = formatAmPmOfDay(date, locale);
        if (this.alertsTimeOrderOverrider.overrideOrDefault(locale, (Boolean) false).booleanValue()) {
            return String.format(TIME_OF_DAY_FORMAT, formatAmPmOfDay, formatHourMin);
        }
        return String.format(TIME_OF_DAY_FORMAT, formatHourMin, formatAmPmOfDay);
    }

    AlertsDateTimeFormatter(Locale locale, boolean z) {
        this.currentLocale = locale;
        this.use24HourFormatPreference = z;
        initOverriders();
    }
}
