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
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public final class WakeWordRecognitionUpdateSetting extends AlexaAudioProviderSetting<Void> {
    private static final String CLIENT_NAME = WakeWordRecognitionUpdateSetting.class.getPackage().getName();
    private static final String METRIC_NAME_SET_WAKE_WORD_ENABLED = "MessageSenderSetWakeWordEnabled";
    private static final String TAG = "WakeWordUpdateSetting";
    private final boolean mEnabled;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WakeWordRecognitionUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, boolean z) {
        this(context, resultCallback, CLIENT_NAME, z);
    }

    @VisibleForTesting
    void setWakeWordRecognitionEnabled(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        alexaAudioProviderServiceMessageSender.setWakeWordRecognitionEnabled(getExtendedClient(), this.mEnabled, new ResultCallbacks() { // from class: com.amazon.alexa.handsfree.settings.client.settings.WakeWordRecognitionUpdateSetting.1
            @Override // com.amazon.alexa.api.compat.resultcallback.ResultCallbacks
            public void onFailure(@NonNull String str) {
                Log.d(WakeWordRecognitionUpdateSetting.TAG, str);
                WakeWordRecognitionUpdateSetting.this.logPercentileMetricFailure(WakeWordRecognitionUpdateSetting.METRIC_NAME_SET_WAKE_WORD_ENABLED);
                WakeWordRecognitionUpdateSetting.this.logPerformanceMetricFailure(WakeWordRecognitionUpdateSetting.METRIC_NAME_SET_WAKE_WORD_ENABLED, str);
            }

            @Override // com.amazon.alexa.api.compat.resultcallback.ResultCallbacks
            public void onSuccess() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully set wake word recognition enabled to ");
                outline107.append(WakeWordRecognitionUpdateSetting.this.mEnabled);
                Log.d(WakeWordRecognitionUpdateSetting.TAG, outline107.toString());
                WakeWordRecognitionUpdateSetting.this.logPercentileMetricSuccess(WakeWordRecognitionUpdateSetting.METRIC_NAME_SET_WAKE_WORD_ENABLED);
            }
        });
    }

    private WakeWordRecognitionUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, @NonNull String str, boolean z) {
        super(context, str, resultCallback);
        this.mEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSetting
    /* renamed from: apply  reason: collision with other method in class */
    public Void mo1543apply(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        setWakeWordRecognitionEnabled(alexaAudioProviderServiceMessageSender);
        return null;
    }

    @VisibleForTesting
    WakeWordRecognitionUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, @NonNull ExtendedClient extendedClient, @NonNull Executor executor, @NonNull MetricsBuilderProvider metricsBuilderProvider, boolean z, @NonNull Lazy<CrashReportRecorder> lazy) {
        super(context, extendedClient, resultCallback, executor, metricsBuilderProvider, lazy);
        this.mEnabled = z;
    }
}
