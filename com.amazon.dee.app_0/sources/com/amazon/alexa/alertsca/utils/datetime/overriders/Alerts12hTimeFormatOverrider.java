package com.amazon.alexa.alertsca.utils.datetime.overriders;

import androidx.annotation.NonNull;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes6.dex */
public class Alerts12hTimeFormatOverrider implements AlertsDateTimeOverrider<String> {
    public static final Map<Locale, String> TIME_FORMAT_BY_LOCALE = new ImmutableMap.Builder().mo7828put(Locale.JAPAN, "K:mm").mo7826build();

    @Override // com.amazon.alexa.alertsca.utils.datetime.overriders.AlertsDateTimeOverrider
    public String overrideOrDefault(@NonNull Locale locale, String str) {
        return TIME_FORMAT_BY_LOCALE.containsKey(locale) ? TIME_FORMAT_BY_LOCALE.get(locale) : str;
    }
}
