package com.amazon.alexa.alertsca.utils.datetime.overriders;

import androidx.annotation.NonNull;
import java.util.Locale;
/* loaded from: classes6.dex */
public interface AlertsDateTimeOverrider<T> {
    T overrideOrDefault(@NonNull Locale locale, T t);
}
