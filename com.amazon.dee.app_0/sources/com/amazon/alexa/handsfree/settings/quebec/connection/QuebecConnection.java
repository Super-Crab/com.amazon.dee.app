package com.amazon.alexa.handsfree.settings.quebec.connection;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.amazon.alexa.handsfree.settings.quebec.connection.exception.RemoteServiceNotSetUpException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.quicinc.voice.activation.IResultCallback;
import com.quicinc.voice.activation.IWakewordSettingsService;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes8.dex */
public class QuebecConnection implements ServiceConnection {
    private static final String GET_SUPPORTED_LOCALES_ERROR_METRIC = "QuebecVoiceAppGetSupportedLocalesError";
    private static final String REMOTE_CONNECTION_ERROR_MESSAGE = "Remote connection error.";
    private static final int RETRIEVAL_TIME_OUT_MILLISECONDS = 1500;
    private static final String TAG = QuebecConnection.class.getSimpleName();
    private static final String WAKE_WORD_SETTING_GET_PARAMS_METRIC = "QuebecWakewordSettingsServiceGetParams";
    private Context mContext;
    private Lazy<CrashReportRecorder> mCrashReportRecorderLazy;
    private IWakewordSettingsService mIWakeWordSettings;
    private boolean mIsSettingsServiceBound;
    private MetricsBuilderProvider mMetricsBuilderProvider;

    public QuebecConnection(@NonNull Context context) {
        this.mContext = context;
    }

    private void cleanupConnection(@Nullable IResultCallback iResultCallback, @Nullable Exception exc) {
        if (iResultCallback != null) {
            Bundle bundle = new Bundle();
            bundle.putString(QuebecAPIConstants.RESULT_CALLBACK_ERROR_MESSAGE, exc != null ? exc.getMessage() : REMOTE_CONNECTION_ERROR_MESSAGE);
            try {
                iResultCallback.onFailure(bundle);
            } catch (RemoteException e) {
                Log.wtf(TAG, "RemoteException was thrown for a local object.", e);
            }
        }
        this.mIWakeWordSettings = null;
        this.mIsSettingsServiceBound = false;
    }

    @NonNull
    private List<String> getResult(@NonNull final Context context, @NonNull Bundle bundle, @NonNull final String str) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ArrayList arrayList = new ArrayList();
        try {
            this.mIWakeWordSettings.getParams(bundle, new IResultCallback.Stub() { // from class: com.amazon.alexa.handsfree.settings.quebec.connection.QuebecConnection.1
                @Override // com.quicinc.voice.activation.IResultCallback
                public void onFailure(@NonNull Bundle bundle2) {
                    countDownLatch.countDown();
                }

                @Override // com.quicinc.voice.activation.IResultCallback
                public void onSuccess(@NonNull Bundle bundle2) {
                    char c;
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
                    } else if (c == 2) {
                        ArrayList<String> stringArrayList = bundle2.getStringArrayList(str);
                        if (stringArrayList == null) {
                            QuebecConnection.this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(QuebecConnection.TAG, QuebecConnection.GET_SUPPORTED_LOCALES_ERROR_METRIC).emit(context);
                        } else {
                            arrayList.addAll(stringArrayList);
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            try {
                if (countDownLatch.await(1500L, TimeUnit.MILLISECONDS)) {
                    recordPercentileMetric(context, WAKE_WORD_SETTING_GET_PARAMS_METRIC, true);
                } else {
                    String str2 = TAG;
                    Log.e(str2, str + " getResult: Timed-out");
                    this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "getResultTimeoutException", new TimeoutException("getResult"));
                    recordPercentileMetric(context, WAKE_WORD_SETTING_GET_PARAMS_METRIC, false);
                }
                return arrayList;
            } catch (InterruptedException e) {
                Log.e(TAG, GeneratedOutlineSupport1.outline72(str, " getResult: InterruptedException "), e, new Object[0]);
                cleanupConnection(null, e);
                this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "getResultInterruptedException", e);
                recordPercentileMetric(context, WAKE_WORD_SETTING_GET_PARAMS_METRIC, false);
                return arrayList;
            }
        } catch (RemoteException e2) {
            Log.e(TAG, GeneratedOutlineSupport1.outline72(str, " getResult: RemoteException "), e2, new Object[0]);
            cleanupConnection(null, e2);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "getResultRemoteException", e2);
            recordPercentileMetric(context, WAKE_WORD_SETTING_GET_PARAMS_METRIC, false);
            return arrayList;
        }
    }

    private void verifyServiceConnected() throws RemoteServiceNotSetUpException {
        if (isServiceConnected()) {
            return;
        }
        Log.e(TAG, "Remote service not setup");
        throw new RemoteServiceNotSetUpException();
    }

    @Nullable
    public String getLocale(@NonNull Context context) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        List<String> result = getResult(context, getRequestBundle(QuebecAPIConstants.WAKEWORD_SETTINGS_REQUEST_STATUS), QuebecAPIConstants.WAKEWORD_SETTINGS_LOCALE);
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

    @Nullable
    public List<String> getSupportedLocales(@NonNull Context context) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        List<String> result = getResult(context, getRequestBundle(QuebecAPIConstants.WAKEWORD_SETTINGS_REQUEST_SUPPORTED_LOCALES), QuebecAPIConstants.WAKEWORD_SETTINGS_SUPPORTED_LOCALES);
        if (result.size() > 0) {
            return result;
        }
        return null;
    }

    @Nullable
    public Integer getWakeWordConfidenceThreshold(@NonNull Context context) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        List<String> result = getResult(context, getRequestBundle(QuebecAPIConstants.WAKEWORD_SETTINGS_REQUEST_STATUS), QuebecAPIConstants.WAKEWORD_SETTINGS_CONFIDENCE_THRESHOULD);
        if (result.size() > 0) {
            return Integer.valueOf(result.get(0));
        }
        return null;
    }

    public boolean isServiceConnected() {
        return this.mIsSettingsServiceBound;
    }

    public boolean isWakeWordRecognitionEnabled(@NonNull Context context) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        try {
            return this.mIWakeWordSettings.isWakewordEnabled();
        } catch (RemoteException e) {
            Log.e(TAG, "isWakeWordRecognitionEnabled: RemoteException ", e, new Object[0]);
            cleanupConnection(null, e);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "isWakeWordRecognitionEnabled", e);
            return false;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @NonNull IBinder iBinder) {
        Log.d(TAG, "onServiceConnected");
        this.mIWakeWordSettings = IWakewordSettingsService.Stub.asInterface(iBinder);
        this.mIsSettingsServiceBound = true;
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this.mContext);
        this.mCrashReportRecorderLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(this.mContext, FalcoProtocolComponent.class)).crashReportRecorderLazy();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@NonNull ComponentName componentName) {
        Log.d(TAG, MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED);
        cleanupConnection(null, null);
    }

    @VisibleForTesting
    void recordPercentileMetric(@NonNull Context context, @NonNull String str, boolean z) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (z) {
            newBuilder.withPercentileMetricSuccess(TAG, str);
        } else {
            newBuilder.withPercentileMetricFailure(TAG, str);
        }
        newBuilder.emit(context);
    }

    public void setLocale(@NonNull Context context, @NonNull String str, @NonNull IResultCallback iResultCallback) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        try {
            this.mIWakeWordSettings.setParams(GeneratedOutlineSupport1.outline11(QuebecAPIConstants.WAKEWORD_SETTINGS_LOCALE, str), iResultCallback);
        } catch (RemoteException e) {
            Log.e(TAG, "setLocale: RemoteException ", e, new Object[0]);
            cleanupConnection(iResultCallback, e);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "setLocale", e);
        }
    }

    public void setWakeWordConfidenceThreshold(@NonNull Context context, @NonNull Integer num, @NonNull IResultCallback iResultCallback) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        Bundle bundle = new Bundle();
        bundle.putInt(QuebecAPIConstants.WAKEWORD_SETTINGS_CONFIDENCE_THRESHOULD, num.intValue());
        try {
            this.mIWakeWordSettings.setParams(bundle, iResultCallback);
        } catch (RemoteException e) {
            Log.e(TAG, "setWakeWordConfidenceThreshold: RemoteException ", e, new Object[0]);
            cleanupConnection(iResultCallback, e);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "setWakeWordConfidenceThreshold", e);
        }
    }

    public void setWakeWordRecognitionEnabled(@NonNull Context context, boolean z, @NonNull IResultCallback iResultCallback) throws RemoteServiceNotSetUpException {
        verifyServiceConnected();
        try {
            this.mIWakeWordSettings.setParams(GeneratedOutlineSupport1.outline13(QuebecAPIConstants.WAKEWORD_SETTINGS_ENABLED, z), iResultCallback);
        } catch (RemoteException e) {
            Log.e(TAG, "setWakeWordRecognitionEnabled: RemoteException ", e, new Object[0]);
            cleanupConnection(iResultCallback, e);
            this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(context, "setWakeWordRecognitionEnabled", e);
        }
    }

    @VisibleForTesting
    QuebecConnection(@NonNull Context context, @Nullable IWakewordSettingsService iWakewordSettingsService, boolean z, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<CrashReportRecorder> lazy) {
        this.mContext = context;
        this.mIWakeWordSettings = iWakewordSettingsService;
        this.mIsSettingsServiceBound = z;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mCrashReportRecorderLazy = lazy;
    }
}
