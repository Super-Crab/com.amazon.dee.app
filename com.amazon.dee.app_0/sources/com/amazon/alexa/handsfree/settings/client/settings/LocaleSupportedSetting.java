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
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public class LocaleSupportedSetting extends AlexaAudioProviderSetting<List<String>> {
    private static final String CLIENT_NAME = LocaleSupportedSetting.class.getPackage().getName();
    private static final String METRIC_NAME_GET_SUPPORTED_LOCALES = "MessageSenderGetSupportedLocales";
    private static final String TAG = "LocaleSupportedSetting";

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocaleSupportedSetting(@NonNull Context context, @NonNull ResultCallback<List<String>> resultCallback) {
        this(context, resultCallback, CLIENT_NAME);
    }

    @VisibleForTesting
    List<String> getSupportedLocales(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        try {
            List<String> supportedLocales = alexaAudioProviderServiceMessageSender.getSupportedLocales(getExtendedClient());
            logPercentileMetricSuccess(METRIC_NAME_GET_SUPPORTED_LOCALES);
            return supportedLocales;
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            logPercentileMetricFailure(METRIC_NAME_GET_SUPPORTED_LOCALES);
            logNonFatalError("getSupportedLocales", e);
            logPerformanceMetricFailure(METRIC_NAME_GET_SUPPORTED_LOCALES, e.getMessage());
            throw e;
        }
    }

    private LocaleSupportedSetting(@NonNull Context context, @NonNull ResultCallback<List<String>> resultCallback, @NonNull String str) {
        super(context, str, resultCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSetting
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public List<String> mo1543apply(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        return getSupportedLocales(alexaAudioProviderServiceMessageSender);
    }

    @VisibleForTesting
    LocaleSupportedSetting(@NonNull Context context, @NonNull ResultCallback<List<String>> resultCallback, @NonNull ExtendedClient extendedClient, @NonNull Executor executor, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<CrashReportRecorder> lazy) {
        super(context, extendedClient, resultCallback, executor, metricsBuilderProvider, lazy);
    }
}
