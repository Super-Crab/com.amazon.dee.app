package com.amazon.alexa.handsfree.settings.locale;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.devices.locales.CertifiedLocaleVoiceAppProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes8.dex */
public class LocaleChangeUpdater {
    private static final String METRIC_CERTIFIED_LOCALE_ACCEPTED = "LocaleChangeUpdaterCertifiedLocaleAccepted";
    private static final String METRIC_CERTIFIED_LOCALE_DEFAULT = "LocaleChangeUpdaterDefaultCertifiedLocale";
    private static final String METRIC_NAME_GET_LOCALE = "LocaleChangeUpdaterGetLocale";
    private static final String METRIC_NAME_SET_LOCALE = "LocaleChangeUpdaterSetLocale";
    private static final String TAG = "LocaleChangeUpdater";
    private final Context mContext;
    private CompletableFuture<String> mLocaleCompletableFuture;
    private final LocalePreferencesManager mLocalePreferencesManager;
    private final MetricsBuilderProvider mMetricBuilderProvider;
    private final CertifiedLocaleVoiceAppProvider mVoiceAppCertifiedLocalesProvider;
    private final VoiceAppLocalesProvider mVoiceAppLocalesProvider;
    private final WakeWordSettingsManager mWakeWordSettingsManager;

    public LocaleChangeUpdater(@NonNull Context context) {
        this(context, WakeWordSettingsManagerProvider.getInstance().get(), MetricsBuilderProvider.getInstance(context), DeviceTypeInformationProvider.getInstance(context).getDeviceInformationForCurrentDevice(context).getCertifiedLocaleVoiceAppProvider(), ((FalcoSettingsComponent) AhfComponentsProvider.getComponent(context, FalcoSettingsComponent.class)).voiceAppLocalesProvider(), new LocalePreferencesManager(context), new CompletableFuture());
    }

    @VisibleForTesting
    String getAmazonCertifiedLocale(@NonNull String str) {
        if (isVoiceAppSupportedLocale(str) && this.mVoiceAppCertifiedLocalesProvider.isLocaleCertified(this.mContext, str)) {
            Log.i(TAG, String.format("Certified Locale accepted (%s).", str));
            recordPerformanceMetric(METRIC_CERTIFIED_LOCALE_ACCEPTED);
            return str;
        }
        Locale defaultLocale = this.mVoiceAppCertifiedLocalesProvider.getDefaultLocale(this.mContext);
        if (defaultLocale == null) {
            Log.w(TAG, String.format("Default certified locale not specified. Sending current locale %s for universal model.", str));
            return str;
        }
        int i = Build.VERSION.SDK_INT;
        String languageTag = defaultLocale.toLanguageTag();
        Log.i(TAG, String.format("Non-certified Locale in use (%s). Defaulting to: %s", str, languageTag));
        recordPerformanceMetric(METRIC_CERTIFIED_LOCALE_DEFAULT);
        return languageTag;
    }

    @VisibleForTesting
    boolean isVoiceAppSupportedLocale(@NonNull String str) {
        return this.mVoiceAppLocalesProvider.getVoiceAppSupportedLocales().contains(str);
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
    void recordPerformanceMetric(@NonNull String str) {
        MetricsBuilder newBuilder = this.mMetricBuilderProvider.newBuilder();
        newBuilder.withPerformanceMetric(TAG, str);
        newBuilder.emit(this.mContext);
    }

    @VisibleForTesting
    void recordUnsupportedLocaleInUseMetric(@NonNull String str, @NonNull Context context) {
        Resources resources = context.getResources();
        if (resources == null) {
            Log.i(TAG, "recordUnsupportedLocaleInUseMetric resources is null.");
            return;
        }
        Configuration configuration = resources.getConfiguration();
        if (configuration == null) {
            Log.i(TAG, "recordUnsupportedLocaleInUseMetric configuration is null.");
            return;
        }
        MetricsBuilder newBuilder = this.mMetricBuilderProvider.newBuilder();
        int i = Build.VERSION.SDK_INT;
        LocaleList locales = configuration.getLocales();
        if (locales != null && !locales.isEmpty()) {
            if (locales.get(0).toLanguageTag().equals(str)) {
                return;
            }
            newBuilder.withUnsupportedLocaleInUseMetric(TAG, locales.get(0).toLanguageTag()).emit(context);
            return;
        }
        Log.i(TAG, "recordUnsupportedLocaleInUseMetric localeList is null or empty.");
    }

    @VisibleForTesting
    boolean requiresUpdateVoiceAppLocale(@NonNull String str) {
        this.mWakeWordSettingsManager.checkVoiceAppCurrentLocale(new ResultCallback<String>() { // from class: com.amazon.alexa.handsfree.settings.locale.LocaleChangeUpdater.2
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                String str2 = LocaleChangeUpdater.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getVoiceAppLocale onError: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.d(str2, outline107.toString());
                LocaleChangeUpdater.this.recordPercentileMetric(LocaleChangeUpdater.METRIC_NAME_GET_LOCALE, false);
                LocaleChangeUpdater.this.mLocaleCompletableFuture.completeExceptionally(new Throwable(callbackErrorMetadata.getErrorMessage()));
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(@NonNull String str2) {
                GeneratedOutlineSupport1.outline167("getVoiceAppLocale onSuccess: ", str2, LocaleChangeUpdater.TAG);
                LocaleChangeUpdater.this.recordPercentileMetric(LocaleChangeUpdater.METRIC_NAME_GET_LOCALE, true);
                LocaleChangeUpdater.this.mLocaleCompletableFuture.complete(str2);
            }
        });
        try {
            return !str.equals(this.mLocaleCompletableFuture.get(2L, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            Log.d(TAG, "Exception while retrieving voice app locale, will change");
            return true;
        } finally {
            this.mLocaleCompletableFuture = new CompletableFuture<>();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setVoiceAppLocale(@NonNull String[] strArr) {
        final String str = strArr[0];
        String amazonCertifiedLocale = getAmazonCertifiedLocale(str);
        this.mLocalePreferencesManager.settingLastUpdatedLocaleWasCertifiedAndSupported(str.equalsIgnoreCase(amazonCertifiedLocale));
        if (!requiresUpdateVoiceAppLocale(amazonCertifiedLocale)) {
            Log.d(TAG, "New locale is the same as the previous one, will not update");
            return;
        }
        this.mWakeWordSettingsManager.setVoiceAppLocale(amazonCertifiedLocale, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.settings.locale.LocaleChangeUpdater.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                String str2 = LocaleChangeUpdater.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setVoiceAppLocale onError: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.d(str2, outline107.toString());
                LocaleChangeUpdater.this.recordPercentileMetric(LocaleChangeUpdater.METRIC_NAME_SET_LOCALE, false);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                Log.d(LocaleChangeUpdater.TAG, "setVoiceAppLocale onSuccess");
                LocaleChangeUpdater localeChangeUpdater = LocaleChangeUpdater.this;
                localeChangeUpdater.recordUnsupportedLocaleInUseMetric(str, localeChangeUpdater.mContext);
                LocaleChangeUpdater.this.recordPercentileMetric(LocaleChangeUpdater.METRIC_NAME_SET_LOCALE, true);
            }
        });
    }

    @VisibleForTesting
    LocaleChangeUpdater(@NonNull Context context, @NonNull WakeWordSettingsManager wakeWordSettingsManager, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull CertifiedLocaleVoiceAppProvider certifiedLocaleVoiceAppProvider, @NonNull VoiceAppLocalesProvider voiceAppLocalesProvider, @NonNull LocalePreferencesManager localePreferencesManager, @NonNull CompletableFuture<String> completableFuture) {
        this.mContext = context;
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
        this.mMetricBuilderProvider = metricsBuilderProvider;
        this.mVoiceAppCertifiedLocalesProvider = certifiedLocaleVoiceAppProvider;
        this.mVoiceAppLocalesProvider = voiceAppLocalesProvider;
        this.mLocalePreferencesManager = localePreferencesManager;
        this.mLocaleCompletableFuture = completableFuture;
    }
}
