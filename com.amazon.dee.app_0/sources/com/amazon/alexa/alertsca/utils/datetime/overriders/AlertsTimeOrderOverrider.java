package com.amazon.alexa.alertsca.utils.datetime.overriders;

import androidx.annotation.NonNull;
import com.google.common.collect.Sets;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes6.dex */
public class AlertsTimeOrderOverrider implements AlertsDateTimeOverrider<Boolean> {
    private static final Set<Locale> LOCALES_WITH_REVERSED_TIME_ORDER = Sets.newHashSet(Locale.JAPAN);

    @Override // com.amazon.alexa.alertsca.utils.datetime.overriders.AlertsDateTimeOverrider
    public Boolean overrideOrDefault(@NonNull Locale locale, Boolean bool) {
        if (LOCALES_WITH_REVERSED_TIME_ORDER.contains(locale)) {
            return true;
        }
        return bool;
    }
}
