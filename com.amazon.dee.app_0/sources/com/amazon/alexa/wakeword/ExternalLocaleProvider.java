package com.amazon.alexa.wakeword;

import com.amazon.alexa.api.AlexaAudioProviderConnection;
import com.amazon.alexa.wakeword.pryon.LocaleProvider;
import java.util.Locale;
/* loaded from: classes11.dex */
public class ExternalLocaleProvider implements LocaleProvider {
    private final AlexaAudioProviderConnection audioProviderConnection;

    public ExternalLocaleProvider(AlexaAudioProviderConnection alexaAudioProviderConnection) {
        this.audioProviderConnection = alexaAudioProviderConnection;
    }

    @Override // com.amazon.alexa.wakeword.pryon.LocaleProvider
    public Locale getLocale() {
        return Locale.US;
    }
}
