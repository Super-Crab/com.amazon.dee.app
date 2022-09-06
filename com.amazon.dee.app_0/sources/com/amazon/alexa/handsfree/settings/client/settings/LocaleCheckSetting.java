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
public class LocaleCheckSetting extends AlexaAudioProviderSetting<String> {
    private static final String CLIENT_NAME = LocaleCheckSetting.class.getPackage().getName();
    private static final String METRIC_NAME_GET_LOCALE = "MessageSenderGetLocale";
    private static final String TAG = "LocaleCheckSetting";

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocaleCheckSetting(@NonNull Context context, @NonNull ResultCallback<String> resultCallback) {
        this(context, resultCallback, CLIENT_NAME);
    }

    @VisibleForTesting
    String getLocale(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        try {
            String locale = alexaAudioProviderServiceMessageSender.getLocale(getExtendedClient());
            logPercentileMetricSuccess(METRIC_NAME_GET_LOCALE);
            return locale;
        } catch (RemoteException e) {
            Log.d(TAG, e.getMessage());
            logPercentileMetricFailure(METRIC_NAME_GET_LOCALE);
            logNonFatalError("getLocale", e);
            logPerformanceMetricFailure(METRIC_NAME_GET_LOCALE, e.getMessage());
            throw e;
        }
    }

    private LocaleCheckSetting(@NonNull Context context, @NonNull ResultCallback<String> resultCallback, @NonNull String str) {
        super(context, str, resultCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSetting
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public String mo1543apply(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        return getLocale(alexaAudioProviderServiceMessageSender);
    }

    @VisibleForTesting
    LocaleCheckSetting(@NonNull Context context, @NonNull ResultCallback<String> resultCallback, @NonNull ExtendedClient extendedClient, @NonNull Executor executor, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<CrashReportRecorder> lazy) {
        super(context, extendedClient, resultCallback, executor, metricsBuilderProvider, lazy);
    }
}
