package com.amazon.alexa.handsfree.metrics;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.metrics.FirstStartupMetricJobService;
import com.amazon.alexa.handsfree.metrics.amok.VoiceAppMetricsInitializer;
import com.amazon.alexa.handsfree.metrics.utils.AttributionTagProvider;
import com.amazon.alexa.handsfree.metrics.utils.IdentityServiceManager;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsRecordMode;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.storage.initialization.AppInitializationStatusStore;
import com.amazon.alexa.handsfree.storage.initialization.DspApkVersionCodeStore;
import com.amazon.alexa.handsfree.storage.initialization.SdkVersionCodeStore;
import com.amazon.alexa.handsfree.storage.metrics.MetricsEnabledStatusStore;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.preload.attribution.AttributionTagListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class MetricsModule {
    static final int MAX_FRO_EMISSION_COUNT = 15;
    static final long MIN_MILLIS_BETWEEN_FROS = 86400000;
    private static final String TAG = "MetricsModule";
    private final AppInitializationStatusStore mAppInitializationStatusStore;
    private final Lazy<ApplicationInformationProvider> mApplicationInformationProviderLazy;
    private final AttributionTagProvider mAttributionTagProvider;
    private final DeviceTypeInformationProvider mDeviceTypeInformationProvider;
    private final DspApkVersionCodeStore mDspApkVersionCodeStore;
    private final FirstStartupMetricJobService.Helper mFirstStartupMetricJobServiceHelper;
    private final IdentityServiceManager mIdentityServiceManager;
    private boolean mIsInitialized;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final MetricsEnabledStatusStore mMetricsEnabledStatusStore;
    private final SdkVersionCodeStore mSdkVersionCodeStore;
    private final VoiceAppMetricsInitializer mVoiceAppMetricsInitializer;
    private final WakeWordSettingsManager mWakeWordSettingsManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public MetricsModule(@NonNull AppInitializationStatusStore appInitializationStatusStore, @NonNull MetricsEnabledStatusStore metricsEnabledStatusStore, @NonNull DspApkVersionCodeStore dspApkVersionCodeStore, @NonNull SdkVersionCodeStore sdkVersionCodeStore, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull IdentityServiceManager identityServiceManager, @NonNull AttributionTagProvider attributionTagProvider, @NonNull FirstStartupMetricJobService.Helper helper, @NonNull VoiceAppMetricsInitializer voiceAppMetricsInitializer, @NonNull DeviceTypeInformationProvider deviceTypeInformationProvider, @NonNull Lazy<ApplicationInformationProvider> lazy) {
        this.mIsInitialized = false;
        this.mAppInitializationStatusStore = appInitializationStatusStore;
        this.mMetricsEnabledStatusStore = metricsEnabledStatusStore;
        this.mDspApkVersionCodeStore = dspApkVersionCodeStore;
        this.mSdkVersionCodeStore = sdkVersionCodeStore;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mIdentityServiceManager = identityServiceManager;
        this.mAttributionTagProvider = attributionTagProvider;
        this.mFirstStartupMetricJobServiceHelper = helper;
        this.mVoiceAppMetricsInitializer = voiceAppMetricsInitializer;
        this.mDeviceTypeInformationProvider = deviceTypeInformationProvider;
        this.mWakeWordSettingsManager = WakeWordSettingsManagerProvider.getInstance().get();
        this.mApplicationInformationProviderLazy = lazy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableMetrics(@NonNull Context context) {
        this.mMetricsEnabledStatusStore.setEnabled(context, true);
        if (this.mAttributionTagProvider.hasComputedAttributionTag()) {
            this.mMetricsEnabledStatusStore.emitMetricsInCache(context);
        }
    }

    @VisibleForTesting
    void addUserSignInListener(@NonNull final Context context) {
        this.mIdentityServiceManager.addUserChangeListener(new IdentityServiceManager.UserChangeListener() { // from class: com.amazon.alexa.handsfree.metrics.MetricsModule.5
            @Override // com.amazon.alexa.handsfree.metrics.utils.IdentityServiceManager.UserChangeListener
            public void onUserChanged(@Nullable UserIdentity userIdentity) {
                if (userIdentity == null || !userIdentity.hasAcceptedEula()) {
                    return;
                }
                MetricsModule.this.enableMetrics(context);
            }
        });
    }

    @VisibleForTesting
    void enableMetricsIfSignedIn(@NonNull Context context) {
        if (this.mIdentityServiceManager.hasAcceptedEula()) {
            Log.d(TAG, "enableMetricsIfSignedIn: User signed in, enabling metrics.");
            enableMetrics(context);
            return;
        }
        addUserSignInListener(context);
    }

    @VisibleForTesting
    int getDspApkVersionCode() {
        Integer dspAppBuildCode = this.mApplicationInformationProviderLazy.mo358get().getDspAppBuildCode();
        if (dspAppBuildCode == null) {
            return 1;
        }
        return dspAppBuildCode.intValue();
    }

    @VisibleForTesting
    int getSdkVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    @VisibleForTesting
    void handleFirstInitialization(@NonNull Context context, boolean z) {
        this.mMetricsEnabledStatusStore.setEnabled(context, z);
        recordSupportedLanguageInUseMetric(context);
        this.mAppInitializationStatusStore.setInitialized(context, true);
    }

    public void initialize(@NonNull Context context) {
        if (!this.mIsInitialized) {
            initializeDevice(context, this.mDeviceTypeInformationProvider.getDeviceInformationForCurrentDevice(context).hasMetricsEnabled(), getDspApkVersionCode(), getSdkVersionCode());
            initializeAttributionTag(context);
            Log.i(TAG, "initialize: Initializing content provider");
            this.mVoiceAppMetricsInitializer.initialize(context);
            this.mIsInitialized = true;
        }
    }

    @VisibleForTesting
    void initializeAttributionTag(@NonNull final Context context) {
        this.mAttributionTagProvider.initialize(context);
        this.mAttributionTagProvider.addListener(new AttributionTagListener() { // from class: com.amazon.alexa.handsfree.metrics.MetricsModule.6
            @Override // com.amazon.alexa.preload.attribution.AttributionTagListener
            public void onAttributionTagComputed() {
                if (MetricsModule.this.mMetricsEnabledStatusStore.canEmitMetrics(context)) {
                    MetricsModule.this.mMetricsEnabledStatusStore.emitMetricsInCache(context);
                }
            }
        });
    }

    @VisibleForTesting
    void initializeDevice(@NonNull Context context, boolean z, int i, int i2) {
        int sdkVersionCode = this.mSdkVersionCodeStore.getSdkVersionCode(context);
        if (this.mAppInitializationStatusStore.isInitialized(context)) {
            Log.d(TAG, "AppInitializationStatusStore is already initialized, checking if this in a DSP APK update");
            Log.d(TAG, String.format("Last DSP APK version code: %d, new DSP APK version code: %d", Integer.valueOf(this.mDspApkVersionCodeStore.getDspApkVersionCode(context)), Integer.valueOf(i)));
            if (i > this.mDspApkVersionCodeStore.getDspApkVersionCode(context)) {
                Log.d(TAG, "DSP APK update detected, recording update metric");
                recordUpdateMetric(context);
            }
            Log.d(TAG, String.format("Previous SDK version is: %d, current SDK version is: %d", Integer.valueOf(sdkVersionCode), Integer.valueOf(i2)));
            if (sdkVersionCode != 0 && i2 > sdkVersionCode) {
                Log.d(TAG, "SDK update detected, recording SDK update metric");
                recordSdkUpdateMetric(context);
            }
        } else {
            Log.d(TAG, "initializeDevice: isInitialized is false. Emitting initial metrics.");
            handleFirstInitialization(context, z);
        }
        Log.d(TAG, "initializeDevice: scheduleFirstStartupMetric");
        scheduleFirstStartupMetric(context);
        String str = TAG;
        Log.d(str, "Storing current DSP APK version code as " + i);
        this.mDspApkVersionCodeStore.setDspApkVersionCode(context, i);
        if (i2 > sdkVersionCode) {
            String str2 = TAG;
            Log.d(str2, "Storing current SDK version code as " + i2);
            this.mSdkVersionCodeStore.setSdkVersionCode(context, i2);
        }
        enableMetricsIfSignedIn(context);
    }

    @VisibleForTesting
    void recordSdkUpdateMetric(@NonNull final Context context) {
        this.mAttributionTagProvider.addListener(new AttributionTagListener() { // from class: com.amazon.alexa.handsfree.metrics.MetricsModule.2
            @Override // com.amazon.alexa.preload.attribution.AttributionTagListener
            public void onAttributionTagComputed() {
                Log.d(MetricsModule.TAG, "in onAttributionTagComputed");
                MetricsModule.this.mMetricsBuilderProvider.newBuilder().withSdkUpdateMetric(MetricsModule.TAG).emit(MetricsRecordMode.CHECK_BEFORE_RECORD_IGNORE_METRICS_ENABLED, context);
            }
        });
    }

    @VisibleForTesting
    void recordSupportedLanguageInUseMetric(@NonNull final Context context) {
        this.mWakeWordSettingsManager.checkVoiceAppCurrentLocale(new ResultCallback<String>() { // from class: com.amazon.alexa.handsfree.metrics.MetricsModule.4
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                Log.e(MetricsModule.TAG, "recordSupportedLanguageInUseMetric: Supported locale in use by the Alexa App couldn't be retrieved");
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(@NonNull String str) {
                GeneratedOutlineSupport1.outline167("recordSupportedLanguageInUseMetric: Supported locale in use by the Alexa App: ", str, MetricsModule.TAG);
                MetricsModule.this.mMetricsBuilderProvider.newBuilder().withSupportedLocaleInUseAtStartUpMetric(MetricsModule.TAG, str).emit(context);
            }
        });
    }

    @VisibleForTesting
    void recordUpdateMetric(@NonNull final Context context) {
        this.mAttributionTagProvider.addListener(new AttributionTagListener() { // from class: com.amazon.alexa.handsfree.metrics.MetricsModule.1
            @Override // com.amazon.alexa.preload.attribution.AttributionTagListener
            public void onAttributionTagComputed() {
                MetricsModule.this.mMetricsBuilderProvider.newBuilder().withDspApkUpdateMetric(MetricsModule.TAG).emit(MetricsRecordMode.CHECK_BEFORE_RECORD_IGNORE_METRICS_ENABLED, context);
            }
        });
    }

    @VisibleForTesting
    void scheduleFirstStartupMetric(@NonNull final Context context) {
        int appVersionCode = this.mAppInitializationStatusStore.getAppVersionCode(context);
        Integer voxAppBuildCode = this.mApplicationInformationProviderLazy.mo358get().getVoxAppBuildCode();
        if (voxAppBuildCode != null && appVersionCode != voxAppBuildCode.intValue()) {
            this.mAppInitializationStatusStore.setFirstStartupMetricCount(context, 0);
            this.mAppInitializationStatusStore.setFirstStartupMetricEmissionTime(context, 0L);
            this.mAppInitializationStatusStore.setAppVersionCode(context, voxAppBuildCode.intValue());
        }
        final int firstStartupMetricCount = this.mAppInitializationStatusStore.getFirstStartupMetricCount(context);
        if (firstStartupMetricCount < 15) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mAppInitializationStatusStore.getFirstStartupMetricEmissionTime(context) <= 86400000) {
                return;
            }
            this.mAttributionTagProvider.addListener(new AttributionTagListener() { // from class: com.amazon.alexa.handsfree.metrics.MetricsModule.3
                @Override // com.amazon.alexa.preload.attribution.AttributionTagListener
                public void onAttributionTagComputed() {
                    Log.d(MetricsModule.TAG, "Attribution tag calculated, emitting FRO to Mobilytics.");
                    MetricsModule.this.mMetricsBuilderProvider.newBuilder().withFirstStartupMetric(MetricsModule.TAG).emit(MetricsRecordMode.CHECK_BEFORE_RECORD_IGNORE_METRICS_ENABLED, context);
                    MetricsModule.this.mFirstStartupMetricJobServiceHelper.scheduleJob(context);
                    MetricsModule.this.mAppInitializationStatusStore.setFirstStartupMetricEmissionTime(context, currentTimeMillis);
                    MetricsModule.this.mAppInitializationStatusStore.setFirstStartupMetricCount(context, firstStartupMetricCount + 1);
                }
            });
        }
    }

    MetricsModule(@NonNull AppInitializationStatusStore appInitializationStatusStore, @NonNull MetricsEnabledStatusStore metricsEnabledStatusStore, @NonNull DspApkVersionCodeStore dspApkVersionCodeStore, @NonNull SdkVersionCodeStore sdkVersionCodeStore, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull IdentityServiceManager identityServiceManager, @NonNull AttributionTagProvider attributionTagProvider, @NonNull FirstStartupMetricJobService.Helper helper, @NonNull VoiceAppMetricsInitializer voiceAppMetricsInitializer, @NonNull DeviceTypeInformationProvider deviceTypeInformationProvider, @NonNull WakeWordSettingsManager wakeWordSettingsManager, @NonNull Lazy<ApplicationInformationProvider> lazy) {
        this.mIsInitialized = false;
        this.mAppInitializationStatusStore = appInitializationStatusStore;
        this.mMetricsEnabledStatusStore = metricsEnabledStatusStore;
        this.mDspApkVersionCodeStore = dspApkVersionCodeStore;
        this.mSdkVersionCodeStore = sdkVersionCodeStore;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mIdentityServiceManager = identityServiceManager;
        this.mAttributionTagProvider = attributionTagProvider;
        this.mFirstStartupMetricJobServiceHelper = helper;
        this.mVoiceAppMetricsInitializer = voiceAppMetricsInitializer;
        this.mDeviceTypeInformationProvider = deviceTypeInformationProvider;
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
        this.mApplicationInformationProviderLazy = lazy;
    }
}
