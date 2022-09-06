package com.amazon.alexa.handsfree.settings.locale;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class VoiceAppLocalesProvider {
    private static final long MAX_AWAIT_TIME_SECS = 2;
    private static final String METRIC_NAME_GET_SUPPORTED_LOCALES = "LocaleChangeUpdaterGetSupportedLocales";
    private static final String TAG = LocaleChangeUpdater.class.getSimpleName();
    private final Context mContext;
    private final MetricsBuilderProvider mMetricBuilderProvider;
    private CompletableFuture<List<String>> mRetrievedLocalesFuture;
    private List<String> mVoiceAppSupportedLocales;
    private final WakeWordSettingsManager mWakeWordSettingsManager;

    @Inject
    public VoiceAppLocalesProvider(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this.mContext = context;
        this.mVoiceAppSupportedLocales = new ArrayList();
        this.mRetrievedLocalesFuture = new CompletableFuture<>();
        this.mMetricBuilderProvider = metricsBuilderProvider;
        this.mWakeWordSettingsManager = WakeWordSettingsManagerProvider.getInstance().get();
    }

    public boolean ensureInitialized() {
        return retrieveVoiceAppSupportedLocalesList(true);
    }

    public List<String> getVoiceAppSupportedLocales() {
        retrieveVoiceAppSupportedLocalesList(true);
        return this.mVoiceAppSupportedLocales;
    }

    public void initialize() {
        retrieveVoiceAppSupportedLocalesList(false);
    }

    @VisibleForTesting
    void recordPercentileMetric(@NonNull String str, boolean z) {
        MetricsBuilder newBuilder = this.mMetricBuilderProvider.newBuilder();
        if (z) {
            newBuilder.withPercentileMetricSuccess(TAG, str);
        } else {
            newBuilder.withPercentileMetricFailure(TAG, str);
        }
        newBuilder.emit(this.mContext);
    }

    @VisibleForTesting
    boolean retrieveVoiceAppSupportedLocalesList(boolean z) {
        if (!this.mVoiceAppSupportedLocales.isEmpty()) {
            Log.d(TAG, "retrieveVoiceAppSupportedLocalesList: List of supported locales synced up.");
            return true;
        }
        this.mWakeWordSettingsManager.checkVoiceAppSupportedLocales(new ResultCallback<List<String>>() { // from class: com.amazon.alexa.handsfree.settings.locale.VoiceAppLocalesProvider.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                String str = VoiceAppLocalesProvider.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("retrieveVoiceAppSupportedLocalesList onError: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.e(str, outline107.toString());
                VoiceAppLocalesProvider.this.recordPercentileMetric(VoiceAppLocalesProvider.METRIC_NAME_GET_SUPPORTED_LOCALES, false);
                VoiceAppLocalesProvider.this.mRetrievedLocalesFuture.completeExceptionally(new Throwable(callbackErrorMetadata.getErrorMessage()));
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(@NonNull List<String> list) {
                String str = VoiceAppLocalesProvider.TAG;
                Log.i(str, "retrieveVoiceAppSupportedLocalesList onSuccess: " + list);
                VoiceAppLocalesProvider.this.recordPercentileMetric(VoiceAppLocalesProvider.METRIC_NAME_GET_SUPPORTED_LOCALES, true);
                VoiceAppLocalesProvider.this.mRetrievedLocalesFuture.complete(list);
            }
        });
        try {
            if (!z) {
                return false;
            }
            try {
                this.mVoiceAppSupportedLocales = this.mRetrievedLocalesFuture.get(2L, TimeUnit.SECONDS);
                return true;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.w(TAG, "Unable to retrieve locales", e);
                return false;
            }
        } finally {
            this.mRetrievedLocalesFuture = new CompletableFuture<>();
        }
    }

    VoiceAppLocalesProvider(@NonNull Context context, @NonNull List<String> list, @NonNull WakeWordSettingsManager wakeWordSettingsManager, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull CompletableFuture<List<String>> completableFuture) {
        this.mContext = context.getApplicationContext();
        this.mVoiceAppSupportedLocales = list;
        this.mRetrievedLocalesFuture = completableFuture;
        this.mMetricBuilderProvider = metricsBuilderProvider;
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
    }
}
