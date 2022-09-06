package com.amazon.alexa;

import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.wakeword.pryon.LocaleProvider;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SettingsLocaleProvider.java */
@Singleton
/* loaded from: classes.dex */
public class mqg implements LocaleProvider {
    public final MBE zZm;

    @Inject
    public mqg(MBE mbe) {
        Preconditions.notNull(mbe, "localeAuthority is null");
        this.zZm = mbe;
    }

    @Override // com.amazon.alexa.wakeword.pryon.LocaleProvider
    public Locale getLocale() {
        return this.zZm.Qle();
    }
}
