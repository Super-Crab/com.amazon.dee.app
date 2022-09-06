package com.amazon.alexa.alertsca.utils.datetime.overriders;

import androidx.annotation.NonNull;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes6.dex */
public class AlertsDateFormatOverrider implements AlertsDateTimeOverrider<String> {
    public static final Map<String, String> DATE_FORMAT_BY_LOCALE = new ImmutableMap.Builder().mo7828put("en_AU", "EEE, d MMM").mo7828put("en_CA", "EEE, MMM d").mo7828put("en_GB", "EEE, d MMM").mo7828put("en_US", "EEE, MMM d").mo7828put("de_DE", "EEE., d. MMM.").mo7828put("en_IN", "EEE, d MMM").mo7828put("es_ES", "EEE., d MMM.").mo7828put("es_MX", "EEE. d 'de' MMM").mo7828put("es_US", "EEE., d MMM.").mo7828put("fr_CA", "EEE d MMM").mo7828put("fr_FR", "EEE d MMM").mo7828put("hi_IN", "EEE, d MMM").mo7828put("it_IT", "EEE d MMM").mo7828put("pt_BR", "EEE, d 'de' MMM").mo7828put("ja_JP", "MMMMd'日'(EEE)").mo7826build();
    public static final Map<String, String> DATE_FORMAT_BY_LANGUAGE = new ImmutableMap.Builder().mo7828put("ar", "EEEE, d MMMM").mo7826build();

    @Override // com.amazon.alexa.alertsca.utils.datetime.overriders.AlertsDateTimeOverrider
    public String overrideOrDefault(@NonNull Locale locale, String str) {
        if (DATE_FORMAT_BY_LOCALE.containsKey(locale.toString())) {
            return DATE_FORMAT_BY_LOCALE.get(locale.toString());
        }
        return DATE_FORMAT_BY_LANGUAGE.containsKey(locale.getLanguage()) ? DATE_FORMAT_BY_LANGUAGE.get(locale.getLanguage()) : str;
    }
}
