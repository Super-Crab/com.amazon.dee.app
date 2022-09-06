package com.amazon.alexa.handsfree.settings.metro.alexaservice;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioProviderService;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.metro.connection.MetroConnection;
import com.amazon.alexa.handsfree.settings.metro.connection.exception.RemoteServiceNotSetUpException;
import com.magiear.handsfree.util.Common;
import com.magiear.handsfree.util.IResultCallback;
import com.magiear.handsfree.util.ParamDefinition;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes8.dex */
public class MetroAlexaAudioProviderService extends AlexaAudioProviderService {
    private static final int ANDROID_Q_VERSION_CODE = 29;
    @VisibleForTesting
    static final String GENERIC_ERROR = "Unidentified error";
    private boolean mIsConnected;
    private MetroConnection mMetroConnection;
    private static final String TAG = MetroAlexaAudioProviderService.class.getSimpleName();
    @VisibleForTesting
    static final List<Locale> DEFAULT_LIST = Arrays.asList(Locale.forLanguageTag("en-US"), Locale.forLanguageTag("de-DE"), Locale.forLanguageTag("en-GB"), Locale.forLanguageTag("ja-JP"), Locale.forLanguageTag("en-IN"), Locale.forLanguageTag("hi-IN"));

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public class SettingsSetResultCallback extends IResultCallback.Stub {
        private final AlexaAudioProviderService.ResultCallbacks mResultCallbacks;

        SettingsSetResultCallback(@Nullable AlexaAudioProviderService.ResultCallbacks resultCallbacks) {
            this.mResultCallbacks = resultCallbacks;
        }

        @Override // com.magiear.handsfree.util.IResultCallback
        public void onResult(@NonNull Bundle bundle) {
            if (this.mResultCallbacks != null) {
                String string = bundle.getString(ParamDefinition.KEY_CALLBACK_RESULT);
                if (string != null) {
                    char c = 65535;
                    int hashCode = string.hashCode();
                    if (hashCode != -1867169789) {
                        if (hashCode == 3135262 && string.equals("fail")) {
                            c = 1;
                        }
                    } else if (string.equals("success")) {
                        c = 0;
                    }
                    if (c == 0) {
                        this.mResultCallbacks.onSuccess();
                        return;
                    } else if (c != 1) {
                        this.mResultCallbacks.onFailure(MetroAlexaAudioProviderService.GENERIC_ERROR);
                        return;
                    } else {
                        this.mResultCallbacks.onFailure(bundle.getString(ParamDefinition.KEY_CALLBACK_ERROR_MSG));
                        return;
                    }
                }
                this.mResultCallbacks.onFailure(MetroAlexaAudioProviderService.GENERIC_ERROR);
            }
        }
    }

    public MetroAlexaAudioProviderService() {
    }

    private void connectToMetroService() {
        Intent intent = new Intent(Common.INTENT_ACTION_WAKEWORD_SETTINGS);
        intent.setPackage(Common.HANDSFREE_ASSISTANT_PACKAGE_NAME);
        this.mMetroConnection = new MetroConnection(this);
        try {
            this.mIsConnected = bindService(intent, this.mMetroConnection, 1);
            Log.e(TAG, String.format("Connection bound to service = %b.", Boolean.valueOf(this.mIsConnected)));
        } catch (SecurityException e) {
            Log.e(TAG, "Connection was not made to the service.", e, new Object[0]);
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void doBind() {
        if (this.mIsConnected) {
            return;
        }
        connectToMetroService();
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void doUnbind() {
        if (this.mIsConnected) {
            unbindService(this.mMetroConnection);
        } else {
            Log.d(TAG, "Context was not connected, nothing to disconnect.");
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    @Nullable
    public Locale getLocale() {
        try {
            String locale = this.mMetroConnection.getLocale(this);
            if (locale == null) {
                return null;
            }
            return Locale.forLanguageTag(locale);
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "getLocale: RemoteServiceNotSetupException", e, new Object[0]);
            return null;
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    @NonNull
    public Set<Locale> getSupportedLocales() {
        String[] stringArray;
        try {
            Bundle wakeWordParam = this.mMetroConnection.getWakeWordParam(this);
            if (wakeWordParam != null && (stringArray = wakeWordParam.getStringArray(ParamDefinition.KEY_SETTING_PARAM_SUPPORTED_LOCALES)) != null) {
                HashSet hashSet = new HashSet();
                for (String str : stringArray) {
                    hashSet.add(Locale.forLanguageTag(str));
                }
                return hashSet;
            }
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "getSupportedLocales: RemoteException", e, new Object[0]);
        }
        return new HashSet(DEFAULT_LIST);
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    @Nullable
    public Integer getWakeWordConfidenceThreshold() {
        try {
            Bundle wakeWordParam = this.mMetroConnection.getWakeWordParam(this);
            if (wakeWordParam == null) {
                return null;
            }
            return Integer.valueOf(wakeWordParam.getInt(ParamDefinition.KEY_SETTING_PARAM_USER_CONF_LEVEL));
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "getWakeWordConfidenceThreshold: RemoteException", e, new Object[0]);
            return null;
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public boolean isWakeWordRecognitionEnabled() {
        try {
            return this.mMetroConnection.isWakeWordRecognitionEnabled(this);
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "isWakeWordRecognitionEnabled: RemoteServiceNotSetupException", e, new Object[0]);
            return false;
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void setLocale(@NonNull Locale locale) {
        setLocale(locale, null);
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void setWakeWordConfidenceThreshold(@NonNull Integer num, @NonNull AlexaAudioProviderService.ResultCallbacks resultCallbacks) {
        Bundle bundle = new Bundle();
        bundle.putInt(ParamDefinition.KEY_SETTING_PARAM_USER_CONF_LEVEL, num.intValue());
        try {
            this.mMetroConnection.setWakeWordParam(this, bundle, new SettingsSetResultCallback(resultCallbacks));
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "setWakeWordConfidenceThreshold: RemoteException", e, new Object[0]);
            resultCallbacks.onFailure("setWakeWordConfidenceThreshold failed: RemoteServiceNotSetUpException");
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void setWakeWordRecognitionEnabled(boolean z) {
        setWakeWordRecognitionEnabled(z, null);
    }

    @VisibleForTesting
    MetroAlexaAudioProviderService(@NonNull MetroConnection metroConnection) {
        this.mMetroConnection = metroConnection;
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void setLocale(@NonNull Locale locale, @Nullable AlexaAudioProviderService.ResultCallbacks resultCallbacks) {
        if (Build.VERSION.SDK_INT < 29) {
            if (resultCallbacks == null) {
                return;
            }
            resultCallbacks.onSuccess();
            return;
        }
        try {
            this.mMetroConnection.setLocale(this, locale.toString(), new SettingsSetResultCallback(resultCallbacks));
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "setLocale: RemoteServiceNotSetupException", e, new Object[0]);
            if (resultCallbacks == null) {
                return;
            }
            resultCallbacks.onFailure("setLocale failed: RemoteServiceNotSetupException");
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderService
    public void setWakeWordRecognitionEnabled(boolean z, @Nullable AlexaAudioProviderService.ResultCallbacks resultCallbacks) {
        try {
            this.mMetroConnection.setWakeWordRecognitionEnabled(this, z, new SettingsSetResultCallback(resultCallbacks));
        } catch (RemoteServiceNotSetUpException e) {
            Log.e(TAG, "setWakeWordRecognitionEnabled: RemoteServiceNotSetUpException", e, new Object[0]);
            if (resultCallbacks == null) {
                return;
            }
            resultCallbacks.onFailure("setWakeWordRecognitionEnabled failed: RemoteServiceNotSetUpException");
        }
    }
}
