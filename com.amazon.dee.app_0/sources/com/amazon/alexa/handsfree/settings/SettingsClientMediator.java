package com.amazon.alexa.handsfree.settings;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager;
import com.amazon.alexa.handsfree.settings.client.AlexaAudioProviderServiceClient;
import com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSettingFactory;
import com.amazon.alexa.handsfree.settings.metrics.MetricType;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class SettingsClientMediator extends WakeWordSettingsManager {
    @VisibleForTesting
    static final String SET_HANDS_FREE_STATE_NAME = "setHandsFreeState";
    private static final String TAG = "SettingsClientMediator";
    private final AlexaAudioProviderServiceClient mAlexaAudioProviderServiceClient;
    private final Context mContext;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final RemoteConfigManager mRemoteConfigManager;
    private final AlexaAudioProviderSettingFactory mSettingFactory;
    private final SettingsChangeRecorder mSettingsChangeRecorder;

    @Inject
    public SettingsClientMediator(@NonNull Context context, @NonNull RemoteConfigManager remoteConfigManager, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this(context, new AlexaAudioProviderSettingFactory(), new AlexaAudioProviderServiceClient(context.getApplicationContext()), remoteConfigManager, metricsBuilderProvider, new SettingsChangeRecorder(context, metricsBuilderProvider));
    }

    private ResultCallback<Void> wrapResponseCallback(@NonNull final ResponseCallback responseCallback) {
        return new ResultCallback<Void>() { // from class: com.amazon.alexa.handsfree.settings.SettingsClientMediator.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                responseCallback.onError(callbackErrorMetadata);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(@Nullable Void r1) {
                responseCallback.onSuccess();
            }
        };
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public void checkHandsFreeState(@NonNull ResultCallback<Boolean> resultCallback) {
        this.mAlexaAudioProviderServiceClient.applySetting(this.mSettingFactory.getWakeWordRecognitionCheckSetting(this.mContext, resultCallback));
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public void checkVoiceAppCurrentLocale(@NonNull ResultCallback<String> resultCallback) {
        this.mAlexaAudioProviderServiceClient.applySetting(this.mSettingFactory.getLocaleCheckSetting(this.mContext, resultCallback));
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public void checkVoiceAppSupportedLocales(@NonNull ResultCallback<List<String>> resultCallback) {
        this.mAlexaAudioProviderServiceClient.applySetting(this.mSettingFactory.getLocaleSupportedSetting(this.mContext, resultCallback));
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public synchronized void setHandsFreeState(boolean z, @NonNull ResponseCallback responseCallback) {
        setHandsFreeState(z, responseCallback, null);
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public synchronized void setVoiceAppLocale(@NonNull String str, @NonNull ResponseCallback responseCallback) {
        this.mAlexaAudioProviderServiceClient.applySetting(this.mSettingFactory.getLocaleUpdateSetting(this.mContext, wrapResponseCallback(responseCallback), str));
    }

    @VisibleForTesting
    boolean shouldHandsFreeBeDisabled(@NonNull String str) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        boolean z = !this.mRemoteConfigManager.isSettingsActive();
        if (z) {
            newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.SETTINGS_NOT_ACTIVE.getValue(), str));
        }
        boolean z2 = !this.mRemoteConfigManager.isDeciderActive();
        if (z2) {
            newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.DECIDER_NOT_ACTIVE.getValue(), str));
        }
        boolean z3 = !this.mRemoteConfigManager.isHandsFreeActive();
        if (z3) {
            newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.HANDSFREE_NOT_ACTIVE.getValue(), str));
        }
        newBuilder.emit(this.mContext);
        return z3 || z2 || z;
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public synchronized void setHandsFreeState(boolean z, @NonNull ResponseCallback responseCallback, String str) {
        if (z) {
            if (shouldHandsFreeBeDisabled(SET_HANDS_FREE_STATE_NAME)) {
                return;
            }
        }
        this.mAlexaAudioProviderServiceClient.applySetting(this.mSettingFactory.getWakeWordRecognitionUpdateSetting(this.mContext, wrapResponseCallback(responseCallback), z));
        if (z) {
            this.mSettingsChangeRecorder.enableWakeWordSharedPreferences(str);
        } else {
            this.mSettingsChangeRecorder.disableWakeWordSharedPreferences(str);
        }
    }

    @VisibleForTesting
    SettingsClientMediator(@NonNull Context context, @NonNull AlexaAudioProviderSettingFactory alexaAudioProviderSettingFactory, @Nullable AlexaAudioProviderServiceClient alexaAudioProviderServiceClient, @NonNull RemoteConfigManager remoteConfigManager, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull SettingsChangeRecorder settingsChangeRecorder) {
        this.mContext = context;
        this.mSettingFactory = alexaAudioProviderSettingFactory;
        this.mRemoteConfigManager = remoteConfigManager;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mAlexaAudioProviderServiceClient = alexaAudioProviderServiceClient;
        this.mSettingsChangeRecorder = settingsChangeRecorder;
    }
}
