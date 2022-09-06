package com.amazon.alexa.voice.handsfree.settings.locale;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaArtifactDownloadListener;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaLocalesListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.ArtifactDownloadListenerFailure;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.AlexaLocaleStore;
import com.amazon.alexa.handsfree.protocols.utils.ArtifactDownloadStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.locale.LocaleChangeService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(24)
/* loaded from: classes11.dex */
public class AlexaResponsesLocaleInteractor implements AlexaServicesConnection.ConnectionListener {
    private static final long MAX_AWAIT_TIME_SECS = 10;
    private static final String TAG = "AlexaResponsesLocaleInteractor";
    private final AlexaLocaleStore mAlexaLocaleStore;
    private final AlexaServicesConnection mAlexaServicesConnection;
    private final AlexaServicesLocaleAPI mAlexaServicesLocaleAPI;
    private final ArtifactDownloadStateProvider mArtifactDownloadStateProvider;
    private final Context mContext;
    private Locale mCurrentLocale;
    private List<Locale> mCurrentLocales;
    private final HandsFreeUserIdentityProvider mHandsFreeUserIdentityProvider;
    private final LocaleChangeApiCallbacks mLocaleChangeApiCallbacks;
    private final LocaleChangeListener mLocaleChangeListener;
    private CompletableFuture<Locale> mLocaleCompletableFuture;
    private CompletableFuture<List<Locale>> mLocalesCompletableFuture;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final ModelDownloadListener mModelDownloadListener;

    @VisibleForTesting
    /* loaded from: classes11.dex */
    static class LocaleChangeApiCallbacks extends AlexaApiCallbacks {
        LocaleChangeApiCallbacks() {
        }

        @Override // com.amazon.alexa.api.AlexaApiCallbacks
        public void onFailure(@NonNull ApiCallFailure apiCallFailure) {
            super.onFailure(apiCallFailure);
            Log.d(AlexaResponsesLocaleInteractor.TAG, "Locale change failed");
        }

        @Override // com.amazon.alexa.api.AlexaApiCallbacks
        public void onSuccess() {
            super.onSuccess();
            Log.d(AlexaResponsesLocaleInteractor.TAG, "Locale change successful");
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class LocaleChangeListener implements AlexaLocalesListener {
        LocaleChangeListener() {
        }

        @VisibleForTesting
        void enqueueLocaleChange(@NonNull Intent intent) {
            LocaleChangeService.enqueueWork(AlexaResponsesLocaleInteractor.this.mContext, intent);
        }

        @Override // com.amazon.alexa.api.AlexaLocalesListener
        public void onLocales(@NonNull List<Locale> list) {
            if (list.isEmpty()) {
                Log.d(AlexaResponsesLocaleInteractor.TAG, "List of locales is empty, no need to change locale");
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Locale locale : list) {
                arrayList.add(locale.toLanguageTag());
            }
            Log.d(AlexaResponsesLocaleInteractor.TAG, String.format("onLocaleChanged: list of locales received: %s", arrayList));
            if (AlexaResponsesLocaleInteractor.this.isDLSWeblabEnabled()) {
                AlexaResponsesLocaleInteractor.this.mCurrentLocales = list;
                AlexaResponsesLocaleInteractor.this.mLocalesCompletableFuture.complete(list);
            } else {
                Locale forLanguageTag = Locale.forLanguageTag((String) arrayList.get(0));
                AlexaResponsesLocaleInteractor.this.mCurrentLocale = forLanguageTag;
                AlexaResponsesLocaleInteractor.this.mLocaleCompletableFuture.complete(forLanguageTag);
            }
            AlexaResponsesLocaleInteractor.this.mAlexaLocaleStore.setLocales(list);
            enqueueLocaleChange(new Intent().putExtra(LocaleChangeService.EXTRA_LOCALE, (String[]) arrayList.toArray(new String[0])));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public enum MetricType {
        METRIC_NAME_LOCALE_CONNECTION("AlexaResponsesLocaleConnection"),
        AVS_CONNECTION_TIMEOUT("AlexaResponsesLocaleConnectionTimeout");
        
        private final String mValue;

        MetricType(@NonNull String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class ModelDownloadListener implements AlexaArtifactDownloadListener {
        ModelDownloadListener() {
        }

        @Override // com.amazon.alexa.api.AlexaArtifactDownloadListener
        public void onArtifactAlreadyUpToDate(@NonNull Locale locale) {
            String str = AlexaResponsesLocaleInteractor.TAG;
            Log.d(str, "Called already up to date for locale: " + locale);
            AlexaResponsesLocaleInteractor.this.mArtifactDownloadStateProvider.reportArtifactUpToDate(ArtifactDownloadStateProvider.ArtifactType.WAKE_WORD_MODEL);
        }

        @Override // com.amazon.alexa.api.AlexaArtifactDownloadListener
        public void onArtifactDownloadFailure(@NonNull ArtifactDownloadListenerFailure artifactDownloadListenerFailure) {
            String str = AlexaResponsesLocaleInteractor.TAG;
            Log.d(str, "Called failure type: " + artifactDownloadListenerFailure);
            AlexaResponsesLocaleInteractor.this.mArtifactDownloadStateProvider.updateDownloadState(ArtifactDownloadStateProvider.ArtifactType.WAKE_WORD_MODEL, ArtifactDownloadStateProvider.DownloadState.FAILURE);
        }

        @Override // com.amazon.alexa.api.AlexaArtifactDownloadListener
        public void onArtifactDownloadSuccess(@NonNull Locale locale) {
            String str = AlexaResponsesLocaleInteractor.TAG;
            Log.d(str, "Called success for locale: " + locale);
            AlexaResponsesLocaleInteractor.this.mArtifactDownloadStateProvider.updateDownloadState(ArtifactDownloadStateProvider.ArtifactType.WAKE_WORD_MODEL, ArtifactDownloadStateProvider.DownloadState.SUCCESS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaResponsesLocaleInteractor(@NonNull Context context) {
        this(context, new AlexaServicesConnection(context), new CompletableFuture(), new CompletableFuture(), new AlexaServicesLocaleAPI(), MetricsBuilderProvider.getInstance(context), new AlexaLocaleStore(context), new ArtifactDownloadStateProvider(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).handsFreeUserIdentityProvider());
    }

    private void recordPerformanceMetric(@NonNull String str) {
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, str).emit(this.mContext);
    }

    public boolean connect(boolean z) {
        CompletableFuture<Locale> completableFuture;
        if (!this.mAlexaServicesConnection.isConnected()) {
            this.mAlexaServicesConnection.registerListener(this);
            this.mAlexaServicesConnection.connect();
            try {
                if (z) {
                    try {
                        if (isDLSWeblabEnabled()) {
                            this.mCurrentLocales = this.mLocalesCompletableFuture.get(10L, TimeUnit.SECONDS);
                        } else {
                            this.mCurrentLocale = this.mLocaleCompletableFuture.get(10L, TimeUnit.SECONDS);
                        }
                        this.mLocalesCompletableFuture = new CompletableFuture<>();
                        completableFuture = new CompletableFuture<>();
                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
                        Log.w(TAG, "Timeout connecting", e);
                        recordPerformanceMetric(MetricType.AVS_CONNECTION_TIMEOUT.getValue());
                        this.mLocalesCompletableFuture = new CompletableFuture<>();
                        completableFuture = new CompletableFuture<>();
                    }
                    this.mLocaleCompletableFuture = completableFuture;
                }
            } catch (Throwable th) {
                this.mLocalesCompletableFuture = new CompletableFuture<>();
                this.mLocaleCompletableFuture = new CompletableFuture<>();
                throw th;
            }
        }
        return this.mAlexaServicesConnection.isConnected();
    }

    public boolean ensureInitialized() {
        return connect(true);
    }

    public Locale getCurrentLocale() {
        Log.d(TAG, String.format("the locale is %s", this.mCurrentLocale));
        return this.mCurrentLocale;
    }

    public boolean isDLSWeblabEnabled() {
        return this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity() != null && this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity().hasComponent(HandsFreeComponent.ALEXA_HANDS_FREE_DYNAMIC_LANGUAGE_SWITCHING);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        Log.d(TAG, "onConnected");
        recordPercentileMetric(true);
        this.mAlexaServicesLocaleAPI.registerLocalesListener(this.mAlexaServicesConnection, this.mLocaleChangeListener);
        this.mAlexaServicesLocaleAPI.registerArtifactDownloadListener(this.mAlexaServicesConnection, this.mModelDownloadListener);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(@NonNull AlexaConnectingFailedReason alexaConnectingFailedReason, @NonNull String str) {
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onConnectingFailed ");
        outline107.append(alexaConnectingFailedReason.toString());
        Log.e(str2, outline107.toString());
        recordPercentileMetric(false);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        Log.d(TAG, "onDisconnected");
        this.mAlexaServicesLocaleAPI.deregisterLocalesListener(this.mAlexaServicesConnection, this.mLocaleChangeListener);
        this.mAlexaServicesLocaleAPI.deregisterArtifactDownloadListener(this.mAlexaServicesConnection, this.mModelDownloadListener);
    }

    @VisibleForTesting
    void recordPercentileMetric(boolean z) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (z) {
            newBuilder.withPercentileMetricSuccess(TAG, MetricType.METRIC_NAME_LOCALE_CONNECTION.getValue());
        } else {
            newBuilder.withPercentileMetricFailure(TAG, MetricType.METRIC_NAME_LOCALE_CONNECTION.getValue());
        }
        newBuilder.emit(this.mContext);
    }

    public void updateLocales(@NonNull List<Locale> list, @NonNull ArtifactDownloadStateProvider.DownloadStarter downloadStarter) {
        String str = TAG;
        Log.d(str, "Request to update locales : " + list);
        this.mArtifactDownloadStateProvider.reportDownloadStarted(ArtifactDownloadStateProvider.ArtifactType.WAKE_WORD_MODEL, downloadStarter);
        this.mAlexaServicesLocaleAPI.setLocales(this.mAlexaServicesConnection, list, this.mLocaleChangeApiCallbacks);
    }

    @VisibleForTesting
    AlexaResponsesLocaleInteractor(@NonNull Context context, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull CompletableFuture<Locale> completableFuture, @NonNull CompletableFuture<List<Locale>> completableFuture2, @NonNull AlexaServicesLocaleAPI alexaServicesLocaleAPI, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull AlexaLocaleStore alexaLocaleStore, @NonNull ArtifactDownloadStateProvider artifactDownloadStateProvider, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider) {
        this.mContext = context;
        this.mAlexaServicesConnection = alexaServicesConnection;
        this.mLocaleChangeListener = new LocaleChangeListener();
        this.mLocaleChangeApiCallbacks = new LocaleChangeApiCallbacks();
        this.mModelDownloadListener = new ModelDownloadListener();
        this.mLocaleCompletableFuture = completableFuture;
        this.mLocalesCompletableFuture = completableFuture2;
        this.mAlexaServicesLocaleAPI = alexaServicesLocaleAPI;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mAlexaLocaleStore = alexaLocaleStore;
        this.mArtifactDownloadStateProvider = artifactDownloadStateProvider;
        this.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
    }
}
