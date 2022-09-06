package com.amazon.alexa.handsfree.settings.quebec.alexaservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioProviderService;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.amazon.alexa.handsfree.quebec.QuebecAPIDefaultValues;
import com.amazon.alexa.handsfree.settings.quebec.connection.QuebecConnection;
import com.amazon.alexa.handsfree.settings.quebec.connection.exception.RemoteServiceNotSetUpException;
import com.quicinc.voice.activation.IResultCallback;
import com.quicinc.voice.activation.IWakewordSettingsService;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes8.dex */
public class QuebecAlexaAudioProviderService extends AlexaAudioProviderService {
    private static final String TAG = QuebecAlexaAudioProviderService.class.getSimpleName();
    private Initializer mInitializer;
    private boolean mIsConnected;
    private QuebecConnection mQuebecConnection;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public class SettingsSetResultCallback extends IResultCallback.Stub {
        private final AlexaAudioProviderService.ResultCallbacks mResultCallbacks;

        @VisibleForTesting
        SettingsSetResultCallback(@Nullable AlexaAudioProviderService.ResultCallbacks resultCallbacks) {
            this.mResultCallbacks = resultCallbacks;
        }

        @Override // com.quicinc.voice.activation.IResultCallback
        public void onFailure(@NonNull Bundle bundle) throws RemoteException {
            Log.d(QuebecAlexaAudioProviderService.TAG, "Callback called with Failure state.");
            if (this.mResultCallbacks == null) {
                return;
            }
            this.mResultCallbacks.onFailure(bundle.getString(QuebecAPIConstants.RESULT_CALLBACK_ERROR_MESSAGE, QuebecAPIDefaultValues.RESULT_CALLBACK_DEFAULT_ERROR_MESSAGE));
        }

        @Override // com.quicinc.voice.activation.IResultCallback
        public void onSuccess(@NonNull Bundle bundle) throws RemoteException {
            Log.d(QuebecAlexaAudioProviderService.TAG, "Callback called with Success state.");
            AlexaAudioProviderService.ResultCallbacks resultCallbacks = this.mResultCallbacks;
            if (resultCallbacks == null) {
                return;
            }
            resultCallbacks.onSuccess();
        }
    }

    public QuebecAlexaAudioProviderService() {
        this.mInitializer = InitializerProvider.getInitializer();
    }

    private void connectToQuebecService(@NonNull Class<? extends IInterface> cls) {
        Intent intent = new Intent(cls.getName());
        intent.setPackage(cls.getPackage().getName());
        this.mQuebecConnection = new QuebecConnection(this);
        try {
            if (bindService(intent, this.mQuebecConnection, 1)) {
                this.mIsConnected = true;
            } else {
                Log.e(TAG, "Connection was not connected to the service.");
            }
        } catch (SecurityException e) {
            Log.e(TAG, "Connection was not connected to the service.", e, new Object[0]);
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void doBind() {
        this.mInitializer.initialize(this);
        if (this.mIsConnected) {
            return;
        }
        connectToQuebecService(IWakewordSettingsService.class);
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void doUnbind() {
        if (this.mIsConnected) {
            unbindService(this.mQuebecConnection);
        } else {
            Log.d(TAG, "Context was not connected, nothing to unbind.");
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    @Nullable
    public Locale getLocale() {
        try {
            String locale = this.mQuebecConnection.getLocale(this);
            if (locale == null) {
                return null;
            }
            return Locale.forLanguageTag(locale);
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "getLocale: RemoteServiceNotSetUpException", e, new Object[0]);
            return null;
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    @NonNull
    public Set<Locale> getSupportedLocales() {
        try {
            List<String> supportedLocales = this.mQuebecConnection.getSupportedLocales(this);
            HashSet hashSet = new HashSet();
            if (supportedLocales != null) {
                for (String str : supportedLocales) {
                    hashSet.add(Locale.forLanguageTag(str));
                }
                return hashSet;
            }
            Log.w(TAG, "getSupportedLocales: Supported locales list from partner app is null.");
            return hashSet;
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "getSupportedLocales: RemoteServiceNotSetUpException", e, new Object[0]);
            return new HashSet();
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    @Nullable
    public Integer getWakeWordConfidenceThreshold() {
        try {
            return this.mQuebecConnection.getWakeWordConfidenceThreshold(this);
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "getWakeWordConfidenceThreshold: RemoteServiceNotSetUpException", e, new Object[0]);
            return null;
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public boolean isWakeWordRecognitionEnabled() {
        try {
            return this.mQuebecConnection.isWakeWordRecognitionEnabled(this);
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "isWakeWordRecognitionEnabled: RemoteServiceNotSetUpException", e, new Object[0]);
            return false;
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void setLocale(@NonNull Locale locale) {
        setLocale(locale, null);
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void setWakeWordConfidenceThreshold(@NonNull Integer num, @NonNull AlexaAudioProviderService.ResultCallbacks resultCallbacks) {
        try {
            this.mQuebecConnection.setWakeWordConfidenceThreshold(this, num, new SettingsSetResultCallback(resultCallbacks));
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "setWakeWordConfidenceThreshold: RemoteServiceNotSetUpException", e, new Object[0]);
            resultCallbacks.onFailure("setWakeWordConfidenceThreshold: RemoteServiceNotSetUpException");
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void setWakeWordRecognitionEnabled(boolean z) {
        setWakeWordRecognitionEnabled(z, null);
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void setLocale(@NonNull Locale locale, @Nullable AlexaAudioProviderService.ResultCallbacks resultCallbacks) {
        try {
            this.mQuebecConnection.setLocale(this, locale.toLanguageTag(), new SettingsSetResultCallback(resultCallbacks));
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "setLocale: RemoteServiceNotSetUpException", e, new Object[0]);
            if (resultCallbacks == null) {
                return;
            }
            resultCallbacks.onFailure("setLocale failed: RemoteServiceNotSetUpException");
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void setWakeWordRecognitionEnabled(boolean z, @Nullable AlexaAudioProviderService.ResultCallbacks resultCallbacks) {
        try {
            this.mQuebecConnection.setWakeWordRecognitionEnabled(this, z, new SettingsSetResultCallback(resultCallbacks));
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "setWakeWordRecognitionEnabled: RemoteServiceNotSetUpException", e, new Object[0]);
            if (resultCallbacks == null) {
                return;
            }
            resultCallbacks.onFailure("setWakeWordRecognitionEnabled failed: RemoteServiceNotSetUpException");
        }
    }

    @VisibleForTesting
    QuebecAlexaAudioProviderService(@NonNull QuebecConnection quebecConnection, boolean z, @NonNull Initializer initializer) {
        this.mQuebecConnection = quebecConnection;
        this.mIsConnected = z;
        this.mInitializer = initializer;
    }
}
