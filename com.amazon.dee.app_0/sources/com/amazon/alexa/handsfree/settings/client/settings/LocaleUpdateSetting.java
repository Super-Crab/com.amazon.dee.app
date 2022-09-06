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
public class LocaleUpdateSetting extends AlexaAudioProviderSetting<Void> {
    private static final String CLIENT_NAME = LocaleUpdateSetting.class.getPackage().getName();
    private static final String METRIC_NAME_SET_LOCALE = "MessageSenderSetLocale";
    private static final String TAG = "LocaleUpdateSetting";
    private final String mLocale;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocaleUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, @NonNull String str) {
        super(context, CLIENT_NAME, resultCallback);
        this.mLocale = str;
    }

    @VisibleForTesting
    void recordSupportedLocaleChangeMetric(@NonNull String str) {
        getMetricsBuilder().withSupportedLocaleChangeMetric(TAG, str).emit(getContext());
    }

    @VisibleForTesting
    void setLocale(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        alexaAudioProviderServiceMessageSender.setLocale(getExtendedClient(), this.mLocale, new ResultCallbacks() { // from class: com.amazon.alexa.handsfree.settings.client.settings.LocaleUpdateSetting.1
            @Override // com.amazon.alexa.api.compat.resultcallback.ResultCallbacks
            public void onFailure(@NonNull String str) {
                GeneratedOutlineSupport1.outline167("setLocale onFailure: ", str, LocaleUpdateSetting.TAG);
                LocaleUpdateSetting.this.logPercentileMetricFailure(LocaleUpdateSetting.METRIC_NAME_SET_LOCALE);
                LocaleUpdateSetting.this.logPerformanceMetricFailure(LocaleUpdateSetting.METRIC_NAME_SET_LOCALE, str);
            }

            @Override // com.amazon.alexa.api.compat.resultcallback.ResultCallbacks
            public void onSuccess() {
                String str = LocaleUpdateSetting.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setLocale onSuccess with locale: ");
                outline107.append(LocaleUpdateSetting.this.mLocale);
                Log.d(str, outline107.toString());
                LocaleUpdateSetting.this.logPercentileMetricSuccess(LocaleUpdateSetting.METRIC_NAME_SET_LOCALE);
                LocaleUpdateSetting localeUpdateSetting = LocaleUpdateSetting.this;
                localeUpdateSetting.recordSupportedLocaleChangeMetric(localeUpdateSetting.mLocale);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSetting
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public Void mo1543apply(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException {
        setLocale(alexaAudioProviderServiceMessageSender);
        return null;
    }

    @VisibleForTesting
    LocaleUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, @NonNull ExtendedClient extendedClient, @NonNull Executor executor, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull String str, @NonNull Lazy<CrashReportRecorder> lazy) {
        super(context, extendedClient, resultCallback, executor, metricsBuilderProvider, lazy);
        this.mLocale = str;
    }
}
