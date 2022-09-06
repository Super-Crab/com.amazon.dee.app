package com.amazon.alexa.handsfree.settings.wakewordsettings;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordServiceConnectionProviderContract;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager;
import com.amazon.alexa.handsfree.settings.SettingsChangeRecorder;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.settings.metrics.MetricType;
import com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype.WakeWordSettingsFactory;
import com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype.WakeWordSettingsThreadHelper;
import java.util.List;
import java.util.Locale;
/* loaded from: classes8.dex */
public class WakeWordSettingsMediator extends WakeWordSettingsManager {
    static final String SET_HANDS_FREE_STATE_NAME = "setHandsFreeState";
    private static final String TAG = "WakeWordSettingsMediator";
    private final Context mContext;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final SettingsChangeRecorder mSettingsChangeRecorder;
    private final WakeWordServiceConnectionProviderContract mWakeWordServiceConnectionProviderContract;
    private final WakeWordSettingsFactory mWakeWordSettingsFactory;

    public WakeWordSettingsMediator(@NonNull Context context) {
        this(context, new WakeWordSettingsFactory(), WakeWordSettingsModule.INSTANCE.getWakeWordServiceConnectionProviderContract(), MetricsBuilderProvider.getInstance(context), new SettingsChangeRecorder(context, MetricsBuilderProvider.getInstance(context)));
    }

    private boolean shouldHandsFreeBeDisabled(@NonNull String str) {
        RemoteConfigManager remoteConfigManager = getRemoteConfigManager();
        boolean z = !remoteConfigManager.isSettingsActive();
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (z) {
            newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.SETTINGS_NOT_ACTIVE.getValue(), str));
        }
        boolean z2 = !remoteConfigManager.isDeciderActive();
        if (z2) {
            newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.DECIDER_NOT_ACTIVE.getValue(), str));
        }
        boolean z3 = !remoteConfigManager.isHandsFreeActive();
        if (z3) {
            newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.HANDSFREE_NOT_ACTIVE.getValue(), str));
        }
        newBuilder.emit(this.mContext);
        return z3 || z2 || z;
    }

    private void startServiceConnection(@NonNull final WakeWordSettingsServiceStateCallback wakeWordSettingsServiceStateCallback) {
        WakeWordSettingsThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.handsfree.settings.wakewordsettings.WakeWordSettingsMediator.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection = WakeWordSettingsMediator.this.mWakeWordServiceConnectionProviderContract.getWakeWordSettingsServiceConnection(wakeWordSettingsServiceStateCallback, WakeWordSettingsMediator.this.mContext);
                    Log.d(WakeWordSettingsMediator.TAG, "Starting connection with RemoteService!");
                    wakeWordSettingsServiceConnection.startConnection();
                } catch (Exception e) {
                    Log.e(WakeWordSettingsMediator.TAG, "Exception in startServiceConnection ", e, new Object[0]);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ResultCallback<Void> wrapResponseCallback(@NonNull final ResponseCallback responseCallback) {
        return new ResultCallback<Void>() { // from class: com.amazon.alexa.handsfree.settings.wakewordsettings.WakeWordSettingsMediator.7
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
    public void checkHandsFreeState(@NonNull final ResultCallback<Boolean> resultCallback) {
        startServiceConnection(new WakeWordSettingsServiceStateCallback() { // from class: com.amazon.alexa.handsfree.settings.wakewordsettings.WakeWordSettingsMediator.2
            @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback
            public void onConnected(@NonNull WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
                WakeWordSettingsMediator.this.mWakeWordSettingsFactory.getWakeWordRecognitionCheckSettingV2(resultCallback, wakeWordSettingsServiceConnection).applySetting();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public void checkVoiceAppCurrentLocale(@NonNull final ResultCallback<String> resultCallback) {
        startServiceConnection(new WakeWordSettingsServiceStateCallback() { // from class: com.amazon.alexa.handsfree.settings.wakewordsettings.WakeWordSettingsMediator.5
            @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback
            public void onConnected(@NonNull WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
                WakeWordSettingsMediator.this.mWakeWordSettingsFactory.getLocaleCheckSettingV2(resultCallback, wakeWordSettingsServiceConnection).applySetting();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public void checkVoiceAppSupportedLocales(@NonNull final ResultCallback<List<String>> resultCallback) {
        startServiceConnection(new WakeWordSettingsServiceStateCallback() { // from class: com.amazon.alexa.handsfree.settings.wakewordsettings.WakeWordSettingsMediator.4
            @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback
            public void onConnected(@NonNull WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
                WakeWordSettingsMediator.this.mWakeWordSettingsFactory.getLocaleSupportedSettingV2(resultCallback, wakeWordSettingsServiceConnection).applySetting();
            }
        });
    }

    @VisibleForTesting
    RemoteConfigManager getRemoteConfigManager() {
        return ((FalcoSettingsComponent) AhfComponentsProvider.getComponent(this.mContext, FalcoSettingsComponent.class)).remoteConfigManager();
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public synchronized void setHandsFreeState(boolean z, @NonNull ResponseCallback responseCallback) {
        setHandsFreeState(z, responseCallback, null);
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public synchronized void setVoiceAppLocale(@NonNull final String str, @NonNull final ResponseCallback responseCallback) {
        startServiceConnection(new WakeWordSettingsServiceStateCallback() { // from class: com.amazon.alexa.handsfree.settings.wakewordsettings.WakeWordSettingsMediator.3
            @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback
            public void onConnected(@NonNull WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
                WakeWordSettingsMediator.this.mWakeWordSettingsFactory.getLocaleUpdateSettingV2(str, WakeWordSettingsMediator.this.wrapResponseCallback(responseCallback), wakeWordSettingsServiceConnection).applySetting();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager
    public synchronized void setHandsFreeState(final boolean z, @NonNull final ResponseCallback responseCallback, String str) {
        if (z) {
            if (shouldHandsFreeBeDisabled(SET_HANDS_FREE_STATE_NAME)) {
                return;
            }
        }
        startServiceConnection(new WakeWordSettingsServiceStateCallback() { // from class: com.amazon.alexa.handsfree.settings.wakewordsettings.WakeWordSettingsMediator.1
            @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback
            public void onConnected(@NonNull WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
                WakeWordSettingsMediator.this.mWakeWordSettingsFactory.getWakeWordRecognitionUpdateSettingV2(z, WakeWordSettingsMediator.this.wrapResponseCallback(responseCallback), wakeWordSettingsServiceConnection).applySetting();
            }
        });
        if (z) {
            this.mSettingsChangeRecorder.enableWakeWordSharedPreferences(str);
        } else {
            this.mSettingsChangeRecorder.disableWakeWordSharedPreferences(str);
        }
    }

    @VisibleForTesting
    WakeWordSettingsMediator(@NonNull Context context, @NonNull WakeWordSettingsFactory wakeWordSettingsFactory, @NonNull WakeWordServiceConnectionProviderContract wakeWordServiceConnectionProviderContract, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull SettingsChangeRecorder settingsChangeRecorder) {
        this.mContext = context;
        this.mWakeWordSettingsFactory = wakeWordSettingsFactory;
        this.mWakeWordServiceConnectionProviderContract = wakeWordServiceConnectionProviderContract;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mSettingsChangeRecorder = settingsChangeRecorder;
    }
}
