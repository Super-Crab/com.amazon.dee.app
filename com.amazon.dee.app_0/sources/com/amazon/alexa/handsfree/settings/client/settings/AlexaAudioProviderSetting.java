package com.amazon.alexa.handsfree.settings.client.settings;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.compat.audioproviderservice.AlexaAudioProviderServiceMessageSender;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.settings.client.callback.SuccessCallback;
import dagger.Lazy;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: classes8.dex */
public abstract class AlexaAudioProviderSetting<T> {
    private static final String SEPARATOR = ":";
    private static final String TAG = "AlexaAudioProviderSetting";
    private final ResultCallback<T> mCallback;
    private final Context mContext;
    private final Lazy<CrashReportRecorder> mCrashReportRecorderLazy;
    private final Executor mExecutor;
    private final ExtendedClient mExtendedClient;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioProviderSetting(@NonNull Context context, @NonNull String str, @NonNull ResultCallback<T> resultCallback) {
        this(context, new ExtendedClient(str), resultCallback, Executors.newSingleThreadExecutor(), MetricsBuilderProvider.getInstance(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).crashReportRecorderLazy());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: apply */
    public abstract T mo1543apply(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) throws RemoteException;

    @VisibleForTesting
    void applyInBackground(@NonNull AlexaAudioProviderSettingRunnable alexaAudioProviderSettingRunnable) {
        this.mExecutor.execute(alexaAudioProviderSettingRunnable);
    }

    public void applySetting(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender, @NonNull SuccessCallback<Void> successCallback) {
        applyInBackground(new AlexaAudioProviderSettingRunnable(alexaAudioProviderServiceMessageSender, successCallback, this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultCallback<T> getCallback() {
        return this.mCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtendedClient getExtendedClient() {
        return this.mExtendedClient;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MetricsBuilder getMetricsBuilder() {
        return this.mMetricsBuilderProvider.newBuilder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logNonFatalError(@NonNull String str, @NonNull Throwable th) {
        this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(this.mContext, str, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logPercentileMetricFailure(@NonNull String str) {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, str).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logPercentileMetricSuccess(@NonNull String str) {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, str).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logPerformanceMetricFailure(@NonNull String str, @NonNull String str2) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        String str3 = TAG;
        newBuilder.withPerformanceMetric(str3, str + ":" + str2).emit(this.mContext);
    }

    public void sendRemoteException(@NonNull String str) {
        this.mCallback.onError(new CallbackErrorMetadata(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public AlexaAudioProviderSetting(@NonNull Context context, @NonNull ExtendedClient extendedClient, @NonNull ResultCallback<T> resultCallback, @NonNull Executor executor, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<CrashReportRecorder> lazy) {
        this.mContext = context;
        this.mExtendedClient = extendedClient;
        this.mCallback = resultCallback;
        this.mExecutor = executor;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mCrashReportRecorderLazy = lazy;
    }
}
