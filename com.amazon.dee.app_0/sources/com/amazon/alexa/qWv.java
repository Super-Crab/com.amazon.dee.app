package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.settings.AutoValue_Setting;
import com.google.auto.value.AutoValue;
import java.util.Locale;
import java.util.TimeZone;
/* compiled from: Setting.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class qWv {
    public static qWv zZm(Locale locale) {
        return new AutoValue_Setting("locale", locale.toLanguageTag());
    }

    public static qWv zZm(TimeZone timeZone) {
        return new AutoValue_Setting("timezoneid", timeZone.getID());
    }
}
