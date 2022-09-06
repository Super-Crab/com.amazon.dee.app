package com.amazon.alexa.handsfree.settings.client.settings;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.compat.audioproviderservice.AlexaAudioProviderServiceMessageSender;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import dagger.Lazy;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public final class WakeWordRecognitionCheckSetting extends AlexaAudioProviderSetting<Boolean> {
    private static final String CLIENT_NAME = WakeWordRecognitionCheckSetting.class.getPackage().getName();
    private static final String METRIC_NAME_IS_WAKE_WORD_ENABLED = "MessageSenderIsWakeWordEnabled";
    private static final String TAG = "WakeWordCheckSetting";

    /* JADX INFO: Access modifiers changed from: package-private */
    public WakeWordRecognitionCheckSetting(@NonNull Context context, @NonNull ResultCallback<Boolean> resultCallback) {
        this(context, resultCallback, CLIENT_NAME);
    }

    @VisibleForTesting
    Boolean isWakeWordRecognitionEnabled(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        try {
            boolean isWakeWordRecognitionEnabled = alexaAudioProviderServiceMessageSender.isWakeWordRecognitionEnabled(getExtendedClient());
            logPercentileMetricSuccess(METRIC_NAME_IS_WAKE_WORD_ENABLED);
            return Boolean.valueOf(isWakeWordRecognitionEnabled);
        } catch (RemoteException e) {
            Log.d(TAG, e.getMessage());
            logPercentileMetricFailure(METRIC_NAME_IS_WAKE_WORD_ENABLED);
            logNonFatalError("isWakeWordRecognitionEnabled", e);
            logPerformanceMetricFailure(METRIC_NAME_IS_WAKE_WORD_ENABLED, e.getMessage());
            throw e;
        }
    }

    private WakeWordRecognitionCheckSetting(@NonNull Context context, @NonNull ResultCallback<Boolean> resultCallback, @NonNull String str) {
        super(context, str, resultCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSetting
    /* renamed from: apply */
    public Boolean mo1543apply(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        return isWakeWordRecognitionEnabled(alexaAudioProviderServiceMessageSender);
    }

    @VisibleForTesting
    WakeWordRecognitionCheckSetting(@NonNull Context context, @NonNull ResultCallback<Boolean> resultCallback, @NonNull ExtendedClient extendedClient, @NonNull Executor executor, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<CrashReportRecorder> lazy) {
        super(context, extendedClient, resultCallback, executor, metricsBuilderProvider, lazy);
    }
}
