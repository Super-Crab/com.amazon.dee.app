package com.amazon.alexa.handsfree.settings.metro.connection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.magiear.handsfree.util.Common;
import com.magiear.handsfree.util.IResultCallback;
import com.magiear.handsfree.util.IWakeWordSettings;
import com.magiear.handsfree.util.ParamDefinition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes8.dex */
public class MetroWakeWordSettingsServiceConnection extends WakeWordSettingsServiceConnection {
    private static final String ERROR_CONNECTION = "Error on the connection.";
    private static final String GENERIC_ERROR = "Unidentified error";
    private Context mContext;
    private IWakeWordSettings mIWakeWordSettings;
    private boolean mIsSettingsServiceBound;
    private MetricsBuilderProvider mMetricsBuilderProvider;
    private WakeWordSettingsServiceStateCallback mWakeWordSettingsServiceStateCallback;
    @VisibleForTesting
    static final List<String> DEFAULT_LIST = Arrays.asList("en-US", "de-DE", "en-GB", "ja-JP", "en-IN", "hi-IN");
    private static final String TAG = MetroWakeWordSettingsServiceConnection.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public enum MetroPercentileMetadata {
        SET_WAKE_WORD_STATE("MetroConnectionSetWakeWordState"),
        GET_WAKE_WORD_STATE("MetroConnectionGetWakeWordState"),
        SET_LOCALE("MetroConnectionSetLocale"),
        GET_LOCALE("MetroConnectionGetLocale"),
        SET_CONFIDENCE_THRESHOLD("MetroConnectionSetWakeWordConfidenceValue"),
        GET_WAKE_WORD_PARAMS("MetroConnectionGetWakeWordParams");
        
        private final String mMetricName;

        MetroPercentileMetadata(@NonNull String str) {
            this.mMetricName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mMetricName;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public class SettingsSetResultCallback extends IResultCallback.Stub {
        private final ResultCallback mResultCallback;

        SettingsSetResultCallback(@Nullable ResultCallback resultCallback) {
            this.mResultCallback = resultCallback;
        }

        @Override // com.magiear.handsfree.util.IResultCallback
        public void onResult(@NonNull Bundle bundle) {
            if (this.mResultCallback != null) {
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
                        this.mResultCallback.onResult(null);
                        return;
                    } else if (c != 1) {
                        this.mResultCallback.onError(new CallbackErrorMetadata(MetroWakeWordSettingsServiceConnection.GENERIC_ERROR));
                        return;
                    } else {
                        this.mResultCallback.onError(new CallbackErrorMetadata(bundle.getString(ParamDefinition.KEY_CALLBACK_ERROR_MSG)));
                        return;
                    }
                }
                this.mResultCallback.onError(new CallbackErrorMetadata(MetroWakeWordSettingsServiceConnection.GENERIC_ERROR));
            }
        }
    }

    public MetroWakeWordSettingsServiceConnection(@NonNull WakeWordSettingsServiceStateCallback wakeWordSettingsServiceStateCallback, @NonNull Context context) {
        this.mWakeWordSettingsServiceStateCallback = wakeWordSettingsServiceStateCallback;
        this.mContext = context;
    }

    private void cleanupConnection(@Nullable SettingsSetResultCallback settingsSetResultCallback, @Nullable Exception exc) {
        if (settingsSetResultCallback != null) {
            Bundle outline11 = GeneratedOutlineSupport1.outline11(ParamDefinition.KEY_CALLBACK_RESULT, "fail");
            outline11.putString(ParamDefinition.KEY_CALLBACK_ERROR_MSG, exc != null ? exc.getMessage() : ERROR_CONNECTION);
            try {
                settingsSetResultCallback.onResult(outline11);
            } catch (Exception e) {
                Log.wtf(TAG, "Exception was thrown while cleaning up connection", e);
            }
        }
        this.mIWakeWordSettings = null;
        this.mIsSettingsServiceBound = false;
    }

    private void connectToMetroService() {
        Intent intent = new Intent(Common.INTENT_ACTION_WAKEWORD_SETTINGS);
        intent.setPackage(Common.HANDSFREE_ASSISTANT_PACKAGE_NAME);
        try {
            this.mContext.bindService(intent, this, 1);
        } catch (SecurityException e) {
            Log.e(TAG, "Connection was not connected to the service.", e, new Object[0]);
        }
    }

    private void verifyServiceConnected(@NonNull MetroPercentileMetadata metroPercentileMetadata) throws RemoteException {
        if (isServiceConnected()) {
            return;
        }
        Log.e(TAG, "Remote service not setup");
        recordPercentileMetric(metroPercentileMetadata, false);
        throw new RemoteException();
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public void endConnection() {
        Log.d(TAG, "Ending connection with remote service");
        if (this.mIsSettingsServiceBound) {
            this.mContext.unbindService(this);
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    @Nullable
    public String getLocale() throws RemoteException {
        verifyServiceConnected(MetroPercentileMetadata.GET_LOCALE);
        try {
            String locale = this.mIWakeWordSettings.getLocale();
            recordPercentileMetric(MetroPercentileMetadata.GET_LOCALE, true);
            return locale;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking getLocale", e, new Object[0]);
            recordPercentileMetric(MetroPercentileMetadata.GET_LOCALE, false);
            cleanupConnection(null, e);
            return null;
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    @Nullable
    public List<String> getSupportedLocales() throws RemoteException {
        String[] stringArray;
        verifyServiceConnected(MetroPercentileMetadata.GET_WAKE_WORD_PARAMS);
        try {
            Bundle wakeWordParam = this.mIWakeWordSettings.getWakeWordParam();
            if (wakeWordParam != null && (stringArray = wakeWordParam.getStringArray(ParamDefinition.KEY_SETTING_PARAM_SUPPORTED_LOCALES)) != null) {
                HashSet hashSet = new HashSet();
                for (String str : stringArray) {
                    hashSet.add(str);
                }
                recordPercentileMetric(MetroPercentileMetadata.GET_WAKE_WORD_PARAMS, true);
                return new ArrayList(hashSet);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking getWakeWordParam", e, new Object[0]);
            recordPercentileMetric(MetroPercentileMetadata.GET_WAKE_WORD_PARAMS, false);
            cleanupConnection(null, e);
        }
        return new ArrayList(DEFAULT_LIST);
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    @Nullable
    public Integer getWakeWordConfidenceThreshold() throws RemoteException {
        verifyServiceConnected(MetroPercentileMetadata.GET_WAKE_WORD_PARAMS);
        try {
            Bundle wakeWordParam = this.mIWakeWordSettings.getWakeWordParam();
            if (wakeWordParam != null) {
                recordPercentileMetric(MetroPercentileMetadata.GET_WAKE_WORD_PARAMS, true);
                return Integer.valueOf(wakeWordParam.getInt(ParamDefinition.KEY_SETTING_PARAM_USER_CONF_LEVEL));
            }
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking getWakeWordParam", e, new Object[0]);
            recordPercentileMetric(MetroPercentileMetadata.GET_WAKE_WORD_PARAMS, false);
            cleanupConnection(null, e);
        }
        return null;
    }

    public boolean isServiceConnected() {
        return this.mIsSettingsServiceBound;
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public boolean isWakeWordRecognitionEnabled() throws RemoteException {
        verifyServiceConnected(MetroPercentileMetadata.GET_WAKE_WORD_STATE);
        try {
            boolean isWakeWordRecognitionEnabled = this.mIWakeWordSettings.isWakeWordRecognitionEnabled();
            recordPercentileMetric(MetroPercentileMetadata.GET_WAKE_WORD_STATE, true);
            return isWakeWordRecognitionEnabled;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking isWakeWordRecognitionEnabled", e, new Object[0]);
            recordPercentileMetric(MetroPercentileMetadata.GET_WAKE_WORD_STATE, false);
            cleanupConnection(null, e);
            return false;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @NonNull IBinder iBinder) {
        Log.d(TAG, "onServiceConnected");
        this.mIWakeWordSettings = IWakeWordSettings.Stub.asInterface(iBinder);
        this.mIsSettingsServiceBound = true;
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this.mContext);
        this.mWakeWordSettingsServiceStateCallback.onConnected(this);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@NonNull ComponentName componentName) {
        Log.d(TAG, MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED);
        cleanupConnection(null, null);
    }

    @VisibleForTesting
    void recordPercentileMetric(@NonNull MetroPercentileMetadata metroPercentileMetadata, boolean z) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (z) {
            newBuilder.withPercentileMetricSuccess(TAG, metroPercentileMetadata.toString());
        } else {
            newBuilder.withPercentileMetricFailure(TAG, metroPercentileMetadata.toString());
        }
        newBuilder.emit(this.mContext);
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public void setLocale(@NonNull String str, @NonNull ResultCallback resultCallback) throws RemoteException {
        verifyServiceConnected(MetroPercentileMetadata.SET_LOCALE);
        SettingsSetResultCallback settingsSetResultCallback = new SettingsSetResultCallback(resultCallback);
        try {
            this.mIWakeWordSettings.setLocale(str, settingsSetResultCallback);
            recordPercentileMetric(MetroPercentileMetadata.SET_LOCALE, true);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking setLocale", e, new Object[0]);
            recordPercentileMetric(MetroPercentileMetadata.SET_LOCALE, false);
            cleanupConnection(settingsSetResultCallback, e);
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public void setWakeWordConfidenceThreshold(@NonNull Integer num, @NonNull ResultCallback resultCallback) throws RemoteException {
        verifyServiceConnected(MetroPercentileMetadata.SET_CONFIDENCE_THRESHOLD);
        SettingsSetResultCallback settingsSetResultCallback = new SettingsSetResultCallback(resultCallback);
        Bundle bundle = new Bundle();
        bundle.putInt(ParamDefinition.KEY_SETTING_PARAM_USER_CONF_LEVEL, num.intValue());
        try {
            this.mIWakeWordSettings.setWakeWordParam(bundle, settingsSetResultCallback);
            recordPercentileMetric(MetroPercentileMetadata.SET_CONFIDENCE_THRESHOLD, true);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking setWakeWordParam", e, new Object[0]);
            recordPercentileMetric(MetroPercentileMetadata.SET_CONFIDENCE_THRESHOLD, false);
            cleanupConnection(settingsSetResultCallback, e);
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public void setWakeWordRecognitionEnabled(boolean z, @NonNull ResultCallback resultCallback) throws RemoteException {
        verifyServiceConnected(MetroPercentileMetadata.SET_WAKE_WORD_STATE);
        SettingsSetResultCallback settingsSetResultCallback = new SettingsSetResultCallback(resultCallback);
        try {
            this.mIWakeWordSettings.setWakeWordRecognitionEnabled(z, settingsSetResultCallback);
            recordPercentileMetric(MetroPercentileMetadata.SET_WAKE_WORD_STATE, true);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking setWakeWordRecognitionEnabled", e, new Object[0]);
            recordPercentileMetric(MetroPercentileMetadata.SET_WAKE_WORD_STATE, false);
            cleanupConnection(settingsSetResultCallback, e);
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public void startConnection() {
        Log.d(TAG, "Starting connection with remote service");
        connectToMetroService();
    }

    MetroWakeWordSettingsServiceConnection(@Nullable IWakeWordSettings iWakeWordSettings, @NonNull WakeWordSettingsServiceStateCallback wakeWordSettingsServiceStateCallback, @NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this.mWakeWordSettingsServiceStateCallback = wakeWordSettingsServiceStateCallback;
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mIWakeWordSettings = iWakeWordSettings;
    }
}
