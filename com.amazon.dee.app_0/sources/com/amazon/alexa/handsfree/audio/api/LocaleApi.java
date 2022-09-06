package com.amazon.alexa.handsfree.audio.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaLocalesListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes8.dex */
public class LocaleApi {
    private static final String TAG = "LocaleApi";
    private static volatile Locale sCurrentAlexaLocale;
    private final LocaleChangeListener mAlexaLocalesListener = new LocaleChangeListener();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public class LocaleChangeListener implements AlexaLocalesListener {
        private LocaleChangeListener() {
        }

        @Override // com.amazon.alexa.api.AlexaLocalesListener
        public void onLocales(@NonNull List<Locale> list) {
            if (list.isEmpty()) {
                Log.i(LocaleApi.TAG, "List of locales is empty, no need to change locale");
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Locale locale : list) {
                arrayList.add(locale.toLanguageTag());
            }
            Log.i(LocaleApi.TAG, String.format("onLocaleChanged: list of locales received: %s", arrayList));
            Locale unused = LocaleApi.sCurrentAlexaLocale = Locale.forLanguageTag((String) arrayList.get(0));
        }
    }

    private void registerLocaleListener(AlexaServicesConnection alexaServicesConnection) {
        Log.d(TAG, "Registering locale listener");
        AlexaServicesApis.Locale.registerLocalesListener(alexaServicesConnection, this.mAlexaLocalesListener);
    }

    @Nullable
    public Locale getCurrentAlexaLocale() {
        return sCurrentAlexaLocale;
    }

    @Nullable
    public Locale getCurrentDeviceLocale() {
        return Locale.getDefault();
    }

    public void onConnected(AlexaServicesConnection alexaServicesConnection) {
        registerLocaleListener(alexaServicesConnection);
    }
}
