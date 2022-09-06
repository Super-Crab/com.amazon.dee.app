package com.amazon.alexa.handsfree.settings.client.settings;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.compat.audioproviderservice.AlexaAudioProviderServiceMessageSender;
import com.amazon.alexa.api.compat.resultcallback.ResultCallbacks;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
final class ConfidenceLevelUpdateSetting extends AlexaAudioProviderSetting<Void> {
    private static final String CLIENT_NAME = ConfidenceLevelUpdateSetting.class.getPackage().getName();
    private static final String METRIC_NAME_SET_WAKE_WORD_CONFIDENCE_THRESHOLD = "MessageSenderSetWakeWordConfidenceThreshold";
    private static final String TAG = "ConfLevelUpdateSetting";
    private final int mConfidenceLevel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfidenceLevelUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, int i) {
        this(context, resultCallback, CLIENT_NAME, i);
    }

    @VisibleForTesting
    void setConfidenceLevel(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        alexaAudioProviderServiceMessageSender.setWakeWordConfidenceThreshold(getExtendedClient(), this.mConfidenceLevel, new ResultCallbacks() { // from class: com.amazon.alexa.handsfree.settings.client.settings.ConfidenceLevelUpdateSetting.1
            @Override // com.amazon.alexa.api.compat.resultcallback.ResultCallbacks
            public void onFailure(@NonNull String str) {
                Log.d(ConfidenceLevelUpdateSetting.TAG, str);
                ConfidenceLevelUpdateSetting.this.logPercentileMetricFailure(ConfidenceLevelUpdateSetting.METRIC_NAME_SET_WAKE_WORD_CONFIDENCE_THRESHOLD);
                ConfidenceLevelUpdateSetting.this.logPerformanceMetricFailure(ConfidenceLevelUpdateSetting.METRIC_NAME_SET_WAKE_WORD_CONFIDENCE_THRESHOLD, str);
            }

            @Override // com.amazon.alexa.api.compat.resultcallback.ResultCallbacks
            public void onSuccess() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully set wake word confidence level to ");
                outline107.append(ConfidenceLevelUpdateSetting.this.mConfidenceLevel);
                Log.d(ConfidenceLevelUpdateSetting.TAG, outline107.toString());
                ConfidenceLevelUpdateSetting.this.logPercentileMetricSuccess(ConfidenceLevelUpdateSetting.METRIC_NAME_SET_WAKE_WORD_CONFIDENCE_THRESHOLD);
            }
        });
    }

    private ConfidenceLevelUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, @NonNull String str, int i) {
        super(context, str, resultCallback);
        this.mConfidenceLevel = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSetting
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public Void mo1543apply(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        setConfidenceLevel(alexaAudioProviderServiceMessageSender);
        return null;
    }

    @VisibleForTesting
    ConfidenceLevelUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, @NonNull ExtendedClient extendedClient, @NonNull Executor executor, @NonNull MetricsBuilderProvider metricsBuilderProvider, int i, @NonNull Lazy<CrashReportRecorder> lazy) {
        super(context, extendedClient, resultCallback, executor, metricsBuilderProvider, lazy);
        this.mConfidenceLevel = i;
    }
}
