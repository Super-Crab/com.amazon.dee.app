package com.amazon.alexa.handsfree.settings.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.compat.audioproviderservice.AlexaAudioProviderServiceMessageSender;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.client.callback.SuccessCallback;
import com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSetting;
import com.amazon.alexa.handsfree.settings.contract.dependencies.FalcoSettingContractComponent;
import com.amazon.alexa.utils.security.SignatureVerifier;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class AlexaAudioServiceConnection implements ServiceConnection {
    private static final String METRIC_CONNECTION_NULL = "ConnectionNull";
    private static final String METRIC_DISABLED_VOICE_APP = "DisabledVoiceApp";
    private static final String METRIC_SERVICE_UNBOUND = "ServiceUnbound";
    private static final String METRIC_SETTING_CONTRACT_NULL = "SettingContractNull";
    private static final String METRIC_UNSUPPORTED = "UnsupportedInSystem";
    @VisibleForTesting
    static final String SETTINGS_CATEGORY = "com.amazon.alexa.CATEGORY_ALEXA_AUDIO_PROVIDER_SERVICE";
    @VisibleForTesting
    static final String SETTINGS_INTENT = "com.amazon.alexa.ACTION_UPDATE_SETTINGS";
    private static final String TAG = "AlexaAudioServiceConctn";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final AlexaAudioProviderServiceSecurityHelper mAlexaAudioProviderServiceSecurityHelper;
    private final AlexaAudioProviderSetting mAlexaAudioProviderSetting;
    private final Context mContext;
    private final MessageReceiversManager mMessageReceiversManager;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioServiceConnection(@NonNull Context context, @NonNull AlexaAudioProviderServiceSecurityHelper alexaAudioProviderServiceSecurityHelper, @NonNull AlexaAudioProviderSetting alexaAudioProviderSetting) {
        this(context, alexaAudioProviderServiceSecurityHelper, alexaAudioProviderSetting, MetricsBuilderProvider.getInstance(context), new MessageReceiversManager(new SignatureVerifier(context)), AMPDInformationProvider.getInstance(context));
    }

    @AnyThread
    public void connect() {
        Intent fetchAlexaAudioProviderServiceIntent = fetchAlexaAudioProviderServiceIntent();
        if (fetchAlexaAudioProviderServiceIntent != null) {
            try {
                this.mContext.bindService(fetchAlexaAudioProviderServiceIntent, this, 1);
                return;
            } catch (IllegalArgumentException unused) {
                Log.e(TAG, "Connection is null");
                recordConnectFailureMetric(METRIC_CONNECTION_NULL);
                return;
            } catch (SecurityException unused2) {
                Log.e(TAG, "Not allowed to bind to service");
                recordConnectFailureMetric(METRIC_SERVICE_UNBOUND);
                return;
            } catch (RuntimeException unused3) {
                Log.e(TAG, "Not supported in system context");
                recordConnectFailureMetric(METRIC_UNSUPPORTED);
                return;
            }
        }
        this.mAlexaAudioProviderSetting.sendRemoteException("Cannot find remote service to connect to.");
    }

    @Nullable
    Intent fetchAlexaAudioProviderServiceIntent() {
        boolean equals = this.mContext.getPackageName() != null ? this.mContext.getPackageName().equals(this.mAMPDInformationProvider.getVoiceAppPackageName()) : false;
        if (this.mAMPDInformationProvider.isTrueTurnKey()) {
            if (((FalcoSettingContractComponent) AhfComponentsProvider.getComponent(this.mContext, FalcoSettingContractComponent.class)).audioProviderServiceIntent() == null) {
                Log.e(TAG, "Settings Contract is null in a True Turn Key Device.");
                recordConnectFailureMetric(METRIC_SETTING_CONTRACT_NULL);
            }
            return ((FalcoSettingContractComponent) AhfComponentsProvider.getComponent(this.mContext, FalcoSettingContractComponent.class)).audioProviderServiceIntent();
        }
        if (this.mAMPDInformationProvider.isAmok() && equals) {
            Intent intent = new Intent();
            intent.setAction(SETTINGS_INTENT);
            intent.addCategory(SETTINGS_CATEGORY);
            ResolveInfo secureResolveInfo = this.mAlexaAudioProviderServiceSecurityHelper.getSecureResolveInfo(intent);
            if (secureResolveInfo != null) {
                intent.setComponent(new ComponentName(secureResolveInfo.serviceInfo.packageName, secureResolveInfo.serviceInfo.name));
                return intent;
            }
        }
        recordConnectFailureMetric(METRIC_DISABLED_VOICE_APP);
        Log.e(TAG, "Cannot fetch secure intent in non-ampd device or missing voice app.");
        return null;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @NonNull IBinder iBinder) {
        Log.d(TAG, "onServiceConnected::Service connected!");
        this.mAlexaAudioProviderSetting.applySetting(new AlexaAudioProviderServiceMessageSender(iBinder, this.mMessageReceiversManager), new SuccessCallback<Void>() { // from class: com.amazon.alexa.handsfree.settings.client.AlexaAudioServiceConnection.1
            @Override // com.amazon.alexa.handsfree.settings.client.callback.SuccessCallback
            public void onSuccess(Void r2) {
                try {
                    AlexaAudioServiceConnection.this.mContext.unbindService(AlexaAudioServiceConnection.this);
                } catch (IllegalArgumentException unused) {
                    Log.w(AlexaAudioServiceConnection.TAG, "Couldn't unbind connection: Service was killed");
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@NonNull ComponentName componentName) {
        this.mAlexaAudioProviderSetting.sendRemoteException("Service disconnected!");
        Log.e(TAG, "onServiceConnected::Service disconnected!");
    }

    @VisibleForTesting
    void recordConnectFailureMetric(@NonNull String str) {
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, str).emit(this.mContext);
    }

    @VisibleForTesting
    AlexaAudioServiceConnection(@NonNull Context context, @NonNull AlexaAudioProviderServiceSecurityHelper alexaAudioProviderServiceSecurityHelper, @NonNull AlexaAudioProviderSetting alexaAudioProviderSetting, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull MessageReceiversManager messageReceiversManager, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        this.mContext = context;
        this.mAlexaAudioProviderServiceSecurityHelper = alexaAudioProviderServiceSecurityHelper;
        this.mAlexaAudioProviderSetting = alexaAudioProviderSetting;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mMessageReceiversManager = messageReceiversManager;
        this.mAMPDInformationProvider = aMPDInformationProvider;
    }
}
