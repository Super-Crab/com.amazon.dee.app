package com.amazon.alexa.handsfree.settings.quebec.connection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
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
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.amazon.alexa.handsfree.quebec.QuebecAPIDefaultValues;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.quicinc.voice.activation.IResultCallback;
import com.quicinc.voice.activation.IWakewordSettingsService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
/* loaded from: classes8.dex */
public class QuebecWakeWordSettingsServiceConnection extends WakeWordSettingsServiceConnection {
    private static final String REMOTE_CONNECTION_ERROR_MESSAGE = "Remote connection error.";
    private static final String TAG = QuebecWakeWordSettingsServiceConnection.class.getSimpleName();
    private Context mContext;
    private IWakewordSettingsService mIWakeWordSettings;
    private boolean mIsSettingsServiceBound;
    private MetricsBuilderProvider mMetricsBuilderProvider;
    private WakeWordSettingsServiceStateCallback mWakeWordSettingsServiceStateCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public enum QuebecPercentileMetadata {
        SET_WAKE_WORD_STATE("QuebecConnectionSetWakeWordState"),
        GET_WAKE_WORD_STATE("QuebecConnectionGetWakeWordState"),
        SET_LOCALE("QuebecConnectionSetLocale"),
        SET_CONFIDENCE_THRESHOLD("QuebecConnectionSetWakeWordConfidenceValue"),
        GET_WAKE_WORD_PARAMS("QuebecConnectionGetWakeWordParams");
        
        private final String mMetricName;

        QuebecPercentileMetadata(@NonNull String str) {
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

        @VisibleForTesting
        SettingsSetResultCallback(@Nullable ResultCallback resultCallback) {
            this.mResultCallback = resultCallback;
        }

        @Override // com.quicinc.voice.activation.IResultCallback
        public void onFailure(@NonNull Bundle bundle) throws RemoteException {
            Log.d(QuebecWakeWordSettingsServiceConnection.TAG, "Callback called with Failure state.");
            if (this.mResultCallback == null) {
                return;
            }
            this.mResultCallback.onError(new CallbackErrorMetadata(bundle.getString(QuebecAPIConstants.RESULT_CALLBACK_ERROR_MESSAGE, QuebecAPIDefaultValues.RESULT_CALLBACK_DEFAULT_ERROR_MESSAGE)));
        }

        @Override // com.quicinc.voice.activation.IResultCallback
        public void onSuccess(@NonNull Bundle bundle) throws RemoteException {
            Log.d(QuebecWakeWordSettingsServiceConnection.TAG, "Callback called with Success state.");
            ResultCallback resultCallback = this.mResultCallback;
            if (resultCallback == null) {
                return;
            }
            resultCallback.onResult(null);
        }
    }

    public QuebecWakeWordSettingsServiceConnection(@NonNull WakeWordSettingsServiceStateCallback wakeWordSettingsServiceStateCallback, @NonNull Context context) {
        this.mWakeWordSettingsServiceStateCallback = wakeWordSettingsServiceStateCallback;
        this.mContext = context;
    }

    private void cleanupConnection(@Nullable SettingsSetResultCallback settingsSetResultCallback, @Nullable Exception exc) {
        if (settingsSetResultCallback != null) {
            Bundle bundle = new Bundle();
            bundle.putString(QuebecAPIConstants.RESULT_CALLBACK_ERROR_MESSAGE, exc != null ? exc.getMessage() : REMOTE_CONNECTION_ERROR_MESSAGE);
            try {
                settingsSetResultCallback.onFailure(bundle);
            } catch (Exception e) {
                Log.wtf(TAG, "Exception was thrown while cleaning up connection", e);
            }
        }
        this.mIWakeWordSettings = null;
        this.mIsSettingsServiceBound = false;
    }

    private void connectToQuebecService(@NonNull Class<? extends IInterface> cls) {
        Intent intent = new Intent(cls.getName());
        intent.setPackage(cls.getPackage().getName());
        try {
            this.mContext.bindService(intent, this, 1);
        } catch (SecurityException e) {
            Log.e(TAG, "Connection was not connected to the service.", e, new Object[0]);
        }
    }

    @NonNull
    private List<String> getResult(@NonNull Bundle bundle, @NonNull final String str) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ArrayList arrayList = new ArrayList();
        try {
            this.mIWakeWordSettings.getParams(bundle, new IResultCallback.Stub() { // from class: com.amazon.alexa.handsfree.settings.quebec.connection.QuebecWakeWordSettingsServiceConnection.1
                @Override // com.quicinc.voice.activation.IResultCallback
                public void onFailure(@NonNull Bundle bundle2) {
                    countDownLatch.countDown();
                }

                @Override // com.quicinc.voice.activation.IResultCallback
                public void onSuccess(@NonNull Bundle bundle2) {
                    char c;
                    ArrayList<String> stringArrayList;
                    String str2 = str;
                    int hashCode = str2.hashCode();
                    if (hashCode == -1637600870) {
                        if (str2.equals(QuebecAPIConstants.WAKEWORD_SETTINGS_LOCALE)) {
                            c = 0;
                        }
                        c = 65535;
                    } else if (hashCode != -476867573) {
                        if (hashCode == 1687979457 && str2.equals(QuebecAPIConstants.WAKEWORD_SETTINGS_CONFIDENCE_THRESHOULD)) {
                            c = 1;
                        }
                        c = 65535;
                    } else {
                        if (str2.equals(QuebecAPIConstants.WAKEWORD_SETTINGS_SUPPORTED_LOCALES)) {
                            c = 2;
                        }
                        c = 65535;
                    }
                    if (c == 0) {
                        arrayList.add(bundle2.getString(str));
                    } else if (c == 1) {
                        arrayList.add(String.valueOf(bundle2.getInt(str)));
                    } else if (c == 2 && (stringArrayList = bundle2.getStringArrayList(str)) != null) {
                        arrayList.addAll(stringArrayList);
                    }
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
                recordPercentileMetric(QuebecPercentileMetadata.GET_WAKE_WORD_PARAMS, true);
                return arrayList;
            } catch (InterruptedException e) {
                Log.e(TAG, GeneratedOutlineSupport1.outline72(str, "getResult: InterruptedException "), e, new Object[0]);
                cleanupConnection(null, e);
                recordPercentileMetric(QuebecPercentileMetadata.GET_WAKE_WORD_PARAMS, false);
                return arrayList;
            }
        } catch (RemoteException e2) {
            Log.e(TAG, "RemoteException invoking getParams ", e2, new Object[0]);
            cleanupConnection(null, e2);
            recordPercentileMetric(QuebecPercentileMetadata.GET_WAKE_WORD_PARAMS, false);
            return arrayList;
        }
    }

    private void verifyServiceConnected(@NonNull QuebecPercentileMetadata quebecPercentileMetadata) throws RemoteException {
        if (isServiceConnected()) {
            return;
        }
        Log.e(TAG, "Remote service not setup");
        recordPercentileMetric(quebecPercentileMetadata, false);
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
        verifyServiceConnected(QuebecPercentileMetadata.GET_WAKE_WORD_PARAMS);
        List<String> result = getResult(getRequestBundle(QuebecAPIConstants.WAKEWORD_SETTINGS_REQUEST_STATUS), QuebecAPIConstants.WAKEWORD_SETTINGS_LOCALE);
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @NonNull
    @VisibleForTesting
    Bundle getRequestBundle(@NonNull String str) {
        return GeneratedOutlineSupport1.outline13(str, true);
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    @Nullable
    public List<String> getSupportedLocales() throws RemoteException {
        verifyServiceConnected(QuebecPercentileMetadata.GET_WAKE_WORD_PARAMS);
        List<String> result = getResult(getRequestBundle(QuebecAPIConstants.WAKEWORD_SETTINGS_REQUEST_SUPPORTED_LOCALES), QuebecAPIConstants.WAKEWORD_SETTINGS_SUPPORTED_LOCALES);
        if (result.size() > 0) {
            return result;
        }
        return null;
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    @Nullable
    public Integer getWakeWordConfidenceThreshold() throws RemoteException {
        verifyServiceConnected(QuebecPercentileMetadata.GET_WAKE_WORD_PARAMS);
        List<String> result = getResult(getRequestBundle(QuebecAPIConstants.WAKEWORD_SETTINGS_REQUEST_STATUS), QuebecAPIConstants.WAKEWORD_SETTINGS_CONFIDENCE_THRESHOULD);
        if (result.size() > 0) {
            return Integer.valueOf(result.get(0));
        }
        return null;
    }

    public boolean isServiceConnected() {
        return this.mIsSettingsServiceBound;
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public boolean isWakeWordRecognitionEnabled() throws RemoteException {
        verifyServiceConnected(QuebecPercentileMetadata.GET_WAKE_WORD_STATE);
        try {
            boolean isWakewordEnabled = this.mIWakeWordSettings.isWakewordEnabled();
            recordPercentileMetric(QuebecPercentileMetadata.GET_WAKE_WORD_STATE, true);
            return isWakewordEnabled;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking isWakewordEnabled ", e, new Object[0]);
            recordPercentileMetric(QuebecPercentileMetadata.GET_WAKE_WORD_STATE, false);
            cleanupConnection(null, e);
            return false;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @NonNull IBinder iBinder) {
        Log.d(TAG, "onServiceConnected");
        this.mIWakeWordSettings = IWakewordSettingsService.Stub.asInterface(iBinder);
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
    void recordPercentileMetric(@NonNull QuebecPercentileMetadata quebecPercentileMetadata, boolean z) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (z) {
            newBuilder.withPercentileMetricSuccess(TAG, quebecPercentileMetadata.toString());
        } else {
            newBuilder.withPercentileMetricFailure(TAG, quebecPercentileMetadata.toString());
        }
        newBuilder.emit(this.mContext);
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public void setLocale(@NonNull String str, @NonNull ResultCallback resultCallback) throws RemoteException {
        verifyServiceConnected(QuebecPercentileMetadata.SET_LOCALE);
        SettingsSetResultCallback settingsSetResultCallback = new SettingsSetResultCallback(resultCallback);
        try {
            this.mIWakeWordSettings.setParams(GeneratedOutlineSupport1.outline11(QuebecAPIConstants.WAKEWORD_SETTINGS_LOCALE, str), settingsSetResultCallback);
            recordPercentileMetric(QuebecPercentileMetadata.SET_LOCALE, true);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking setParams ", e, new Object[0]);
            recordPercentileMetric(QuebecPercentileMetadata.SET_LOCALE, false);
            cleanupConnection(settingsSetResultCallback, e);
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public void setWakeWordConfidenceThreshold(@NonNull Integer num, @NonNull ResultCallback resultCallback) throws RemoteException {
        verifyServiceConnected(QuebecPercentileMetadata.SET_CONFIDENCE_THRESHOLD);
        SettingsSetResultCallback settingsSetResultCallback = new SettingsSetResultCallback(resultCallback);
        Bundle bundle = new Bundle();
        bundle.putInt(QuebecAPIConstants.WAKEWORD_SETTINGS_CONFIDENCE_THRESHOULD, num.intValue());
        try {
            this.mIWakeWordSettings.setParams(bundle, settingsSetResultCallback);
            recordPercentileMetric(QuebecPercentileMetadata.SET_CONFIDENCE_THRESHOLD, true);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking setParams ", e, new Object[0]);
            recordPercentileMetric(QuebecPercentileMetadata.SET_CONFIDENCE_THRESHOLD, false);
            cleanupConnection(settingsSetResultCallback, e);
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public void setWakeWordRecognitionEnabled(boolean z, @NonNull ResultCallback resultCallback) throws RemoteException {
        verifyServiceConnected(QuebecPercentileMetadata.SET_WAKE_WORD_STATE);
        SettingsSetResultCallback settingsSetResultCallback = new SettingsSetResultCallback(resultCallback);
        try {
            this.mIWakeWordSettings.setParams(GeneratedOutlineSupport1.outline13(QuebecAPIConstants.WAKEWORD_SETTINGS_ENABLED, z), settingsSetResultCallback);
            recordPercentileMetric(QuebecPercentileMetadata.SET_WAKE_WORD_STATE, true);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException invoking setParams ", e, new Object[0]);
            recordPercentileMetric(QuebecPercentileMetadata.SET_WAKE_WORD_STATE, false);
            cleanupConnection(settingsSetResultCallback, e);
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection
    public void startConnection() {
        Log.d(TAG, "Starting connection with remote service");
        connectToQuebecService(IWakewordSettingsService.class);
    }

    QuebecWakeWordSettingsServiceConnection(@Nullable IWakewordSettingsService iWakewordSettingsService, @NonNull WakeWordSettingsServiceStateCallback wakeWordSettingsServiceStateCallback, @NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this.mWakeWordSettingsServiceStateCallback = wakeWordSettingsServiceStateCallback;
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mIWakeWordSettings = iWakewordSettingsService;
    }
}
