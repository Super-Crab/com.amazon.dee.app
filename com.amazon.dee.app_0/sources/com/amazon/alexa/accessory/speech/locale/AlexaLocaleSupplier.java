package com.amazon.alexa.accessory.speech.locale;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.avsclient.locale.AlexaLocale;
import com.amazon.alexa.accessory.avsclient.locale.LocaleSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import com.amazon.alexa.accessory.speechapi.listeners.SettingsListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes6.dex */
public final class AlexaLocaleSupplier implements LocaleSupplier {
    private final AlexaConnection alexaConnection;
    private final ConnectionListener connectionListener;
    private volatile AlexaLocale lastKnownLocale;
    private final List<LocaleSupplier.Listener> listeners;
    private final LocaleListener localeListener;

    /* loaded from: classes6.dex */
    private final class LocaleListener implements SettingsListener {
        private LocaleListener() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.SettingsListener
        public void onLocaleChanged(@NonNull Locale locale) {
            Logger.d("AlexaLocaleSupplier onLocale. locale=%s", locale);
            AlexaLocaleSupplier.this.cacheAndNotifyListeners(new AlexaLocale(locale.toLanguageTag()));
        }
    }

    /* loaded from: classes6.dex */
    private final class ServiceConnectionListener implements ConnectionListener {
        private ServiceConnectionListener() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onConnected() {
            Logger.d("AlexaLocaleSupplier ConnectionListener onConnected");
            AlexaLocaleSupplier.this.alexaConnection.registerSettingsListener(AlexaLocaleSupplier.this.localeListener);
            Locale locale = AlexaLocaleSupplier.this.alexaConnection.getLocale();
            if (locale != null) {
                AlexaLocaleSupplier.this.cacheAndNotifyListeners(new AlexaLocale(locale.toLanguageTag()));
            } else {
                Logger.e("AlexaLocaleSupplier: Locale received from avsclient is null");
            }
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onConnectingFailed(ConnectionListener.ConnectingFailedReason connectingFailedReason, String str) {
            Logger.d("AlexaLocaleSupplier ConnectionListener onConnectingFailed for reason: %s and message: %s", connectingFailedReason, str);
            AlexaLocaleSupplier.this.alexaConnection.deregisterSettingsListener(AlexaLocaleSupplier.this.localeListener);
            AlexaLocaleSupplier.this.cacheAndNotifyListeners(null);
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onDisconnected() {
            Logger.d("AlexaLocaleSupplier ConnectionListener onDisconnected");
            AlexaLocaleSupplier.this.alexaConnection.deregisterSettingsListener(AlexaLocaleSupplier.this.localeListener);
            AlexaLocaleSupplier.this.cacheAndNotifyListeners(null);
        }
    }

    public AlexaLocaleSupplier(AlexaConnection alexaConnection) {
        Preconditions.notNull(alexaConnection, "alexaConnection");
        this.alexaConnection = alexaConnection;
        this.listeners = new ArrayList();
        this.localeListener = new LocaleListener();
        this.connectionListener = new ServiceConnectionListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheAndNotifyListeners(AlexaLocale alexaLocale) {
        synchronized (this.listeners) {
            if (valueAlreadyCached(this.lastKnownLocale, alexaLocale)) {
                return;
            }
            this.lastKnownLocale = alexaLocale;
            ArrayList arrayList = new ArrayList(this.listeners);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((LocaleSupplier.Listener) arrayList.get(size)).onLocale(alexaLocale);
            }
        }
    }

    private void deregisterListenersInternal() {
        this.alexaConnection.deregisterConnectionListener(this.connectionListener);
        synchronized (this.listeners) {
            this.lastKnownLocale = null;
        }
    }

    private void registerListenersInternal() {
        Logger.d("AlexaLocaleSupplier registerListenersInternal");
        this.alexaConnection.registerConnectionListener(this.connectionListener);
    }

    private boolean valueAlreadyCached(AlexaLocale alexaLocale, AlexaLocale alexaLocale2) {
        if (alexaLocale == null && alexaLocale2 == null) {
            return true;
        }
        if (alexaLocale != null) {
            return alexaLocale.equals(alexaLocale2);
        }
        return false;
    }

    @Override // com.amazon.alexa.accessory.avsclient.locale.LocaleSupplier
    public void deRegisterLocaleListener(LocaleSupplier.Listener listener) {
        Preconditions.notNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.listeners) {
            this.listeners.remove(listener);
            if (!this.listeners.isEmpty()) {
                return;
            }
            deregisterListenersInternal();
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.locale.LocaleSupplier
    public void registerLocaleListener(LocaleSupplier.Listener listener) {
        Preconditions.notNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Logger.d("AlexaLocaleSupplier registerLocaleListener");
        synchronized (this.listeners) {
            this.listeners.add(listener);
            if (this.listeners.size() != 1) {
                if (this.lastKnownLocale != null) {
                    listener.onLocale(this.lastKnownLocale);
                }
                return;
            }
            registerListenersInternal();
        }
    }
}
