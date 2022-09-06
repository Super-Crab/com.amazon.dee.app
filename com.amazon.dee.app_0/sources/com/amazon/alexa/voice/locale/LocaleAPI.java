package com.amazon.alexa.voice.locale;

import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaLocalesListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.api.compat.AlexaSupportedLocalesListener;
import com.amazon.alexa.api.compat.AlexaSupportedLocalesListenerv2;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class LocaleAPI {
    /* JADX INFO: Access modifiers changed from: package-private */
    public void deregisterLocalesListener(AlexaServicesConnection alexaServicesConnection, AlexaLocalesListener alexaLocalesListener) {
        AlexaServicesApis.Locale.deregisterLocalesListener(alexaServicesConnection, alexaLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deregisterSupportedLocales(AlexaServicesConnection alexaServicesConnection, AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        AlexaServicesApis.Locale.deregisterSupportedLocalesListener(alexaServicesConnection, alexaSupportedLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deregisterSupportedLocalesv2(AlexaServicesConnection alexaServicesConnection, AlexaSupportedLocalesListenerv2 alexaSupportedLocalesListenerv2) {
        AlexaServicesApis.Locale.deregisterSupportedLocalesListener(alexaServicesConnection, alexaSupportedLocalesListenerv2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerLocalesListener(AlexaServicesConnection alexaServicesConnection, AlexaLocalesListener alexaLocalesListener) {
        AlexaServicesApis.Locale.registerLocalesListener(alexaServicesConnection, alexaLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerSupportedLocales(AlexaServicesConnection alexaServicesConnection, AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        AlexaServicesApis.Locale.registerSupportedLocalesListener(alexaServicesConnection, alexaSupportedLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerSupportedLocalesv2(AlexaServicesConnection alexaServicesConnection, AlexaSupportedLocalesListenerv2 alexaSupportedLocalesListenerv2) {
        AlexaServicesApis.Locale.registerSupportedLocalesListener(alexaServicesConnection, alexaSupportedLocalesListenerv2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLocales(AlexaServicesConnection alexaServicesConnection, List<Locale> list, AlexaApiCallbacks alexaApiCallbacks) {
        AlexaServicesApis.Locale.setLocales(alexaServicesConnection, list, alexaApiCallbacks);
    }
}
