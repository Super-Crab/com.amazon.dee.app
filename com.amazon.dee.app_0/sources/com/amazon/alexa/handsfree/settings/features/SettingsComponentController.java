package com.amazon.alexa.handsfree.settings.features;

import android.content.ComponentName;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsState;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsStateProvider;
import com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaSettingsLauncherActivity;
import com.amazon.alexa.handsfree.settings.quicksettings.AlexaQuickSettingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class SettingsComponentController {
    @VisibleForTesting
    static final String METRIC_NAME_GET_HANDS_FREE_STATE = "SettingsDialerGetPrevHFState";
    @VisibleForTesting
    static final String METRIC_NAME_SET_HANDS_FREE_STATE = "SettingsDialerSetFromPrevHFState";
    @VisibleForTesting
    static final String QUICK_SETTINGS_ACTIVITY_CLASS_NAME = "com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaQuickSettingsLauncherActivity";
    @VisibleForTesting
    static final HandsFreeComponent SETTINGS_COMPONENT = HandsFreeComponent.HANDS_FREE_SETTINGS;
    private static final String TAG = "SettingsComponentController";
    private final Context mContext;
    private final DeviceTypeInformationProvider mDeviceTypeInformationProvider;
    private final HandsFreeSettingsStateProvider mHandsFreeSettingsStateProvider;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final WakeWordSettingsManager mWakeWordSettingsManager;

    public SettingsComponentController(@NonNull Context context) {
        this(context, new HandsFreeSettingsStateProvider(context), WakeWordSettingsManagerProvider.getInstance().get(), MetricsBuilderProvider.getInstance(context), DeviceTypeInformationProvider.getInstance(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportPercentileMetric(boolean z, @NonNull String str) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (z) {
            newBuilder.withPercentileMetricSuccess(TAG, str);
        } else {
            newBuilder.withPercentileMetricFailure(TAG, str);
        }
        newBuilder.emit(this.mContext);
    }

    private void syncHandsFreeState(boolean z) {
        if (z) {
            HandsFreeSettingsState previousSettingsState = this.mHandsFreeSettingsStateProvider.getPreviousSettingsState();
            this.mHandsFreeSettingsStateProvider.setSettingsState(HandsFreeSettingsState.REMOTELY_DISABLED);
            String str = TAG;
            Log.d(str, " syncHandsFreeState " + previousSettingsState);
            updateHandsFreeState(previousSettingsState);
            return;
        }
        storeHandsFreeState();
    }

    @VisibleForTesting
    boolean enableComponent(boolean z, @NonNull ComponentName componentName) {
        if (!shouldComponentSettingBeChanged(z, componentName)) {
            Log.d(TAG, "Component Setting does not need to be changed. Skipping");
            return false;
        }
        this.mContext.getPackageManager().setComponentEnabledSetting(componentName, z ? 1 : 2, 1);
        String str = TAG;
        Log.d(str, componentName.getClassName() + " has been turned on: " + z);
        return true;
    }

    public void setComponentsEnabled(boolean z) {
        String str = TAG;
        Log.d(str, "Dial settings components to : " + z);
        ComponentName componentName = new ComponentName(this.mContext, AlexaSettingsLauncherActivity.class);
        ComponentName componentName2 = new ComponentName(this.mContext, AlexaQuickSettingService.class);
        boolean enableComponent = enableComponent(z, new ComponentName(this.mContext, QUICK_SETTINGS_ACTIVITY_CLASS_NAME));
        boolean enableComponent2 = enableComponent(z, componentName);
        boolean enableComponent3 = enableComponent(z, componentName2);
        if (enableComponent || enableComponent2 || enableComponent3) {
            syncHandsFreeState(z);
        }
    }

    public void setComponentsEnabledNotLaunched(boolean z) {
        String str = TAG;
        Log.d(str, "Dial settings components to : " + z);
        ComponentName componentName = new ComponentName(this.mContext, AlexaSettingsLauncherActivity.class);
        boolean enableComponent = enableComponent(z, new ComponentName(this.mContext, QUICK_SETTINGS_ACTIVITY_CLASS_NAME));
        boolean enableComponent2 = enableComponent(z, componentName);
        if (enableComponent || enableComponent2) {
            syncHandsFreeState(z);
        }
    }

    public void setHandsFreeUserIdentity(boolean z, HandsFreeUserIdentity handsFreeUserIdentity) {
        boolean hasComponent = handsFreeUserIdentity.hasComponent(SETTINGS_COMPONENT);
        if (this.mDeviceTypeInformationProvider.getSupportedDeviceInformation(this.mContext).isDeviceLaunched()) {
            setComponentsEnabled(hasComponent);
        } else {
            setComponentsEnabledNotLaunched(z && hasComponent);
        }
    }

    @VisibleForTesting
    boolean shouldComponentSettingBeChanged(boolean z, @NonNull ComponentName componentName) {
        int componentEnabledSetting = this.mContext.getPackageManager().getComponentEnabledSetting(componentName);
        if (componentEnabledSetting != 0) {
            if (componentEnabledSetting == 1) {
                return !z;
            }
            if (componentEnabledSetting == 2) {
                return z;
            }
            return true;
        }
        return z;
    }

    @VisibleForTesting
    void storeHandsFreeState() {
        this.mWakeWordSettingsManager.checkHandsFreeState(new ResultCallback<Boolean>() { // from class: com.amazon.alexa.handsfree.settings.features.SettingsComponentController.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                String str = SettingsComponentController.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Hands-Free state could not be stored. Error: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.e(str, outline107.toString());
                SettingsComponentController.this.reportPercentileMetric(false, SettingsComponentController.METRIC_NAME_GET_HANDS_FREE_STATE);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(@NonNull Boolean bool) {
                HandsFreeSettingsState handsFreeSettingsState = bool.booleanValue() ? HandsFreeSettingsState.ACTIVE : HandsFreeSettingsState.INACTIVE;
                String str = SettingsComponentController.TAG;
                Log.d(str, "Last Hands-Free state has been stored: " + handsFreeSettingsState);
                SettingsComponentController.this.mHandsFreeSettingsStateProvider.setSettingsState(handsFreeSettingsState);
                SettingsComponentController.this.updateHandsFreeState(HandsFreeSettingsState.INACTIVE);
                SettingsComponentController.this.reportPercentileMetric(true, SettingsComponentController.METRIC_NAME_GET_HANDS_FREE_STATE);
            }
        });
    }

    @VisibleForTesting
    void updateHandsFreeState(@NonNull final HandsFreeSettingsState handsFreeSettingsState) {
        this.mWakeWordSettingsManager.setHandsFreeState(handsFreeSettingsState == HandsFreeSettingsState.ACTIVE, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.settings.features.SettingsComponentController.2
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                String str = SettingsComponentController.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Hands-Free state could not be updated. Error: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.e(str, outline107.toString());
                SettingsComponentController.this.reportPercentileMetric(false, SettingsComponentController.METRIC_NAME_SET_HANDS_FREE_STATE);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                String str = SettingsComponentController.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Last Hands-Free state has been updated: ");
                outline107.append(handsFreeSettingsState);
                Log.d(str, outline107.toString());
                SettingsComponentController.this.reportPercentileMetric(true, SettingsComponentController.METRIC_NAME_SET_HANDS_FREE_STATE);
            }
        }, TAG);
    }

    @VisibleForTesting
    SettingsComponentController(@NonNull Context context, @NonNull HandsFreeSettingsStateProvider handsFreeSettingsStateProvider, @NonNull WakeWordSettingsManager wakeWordSettingsManager, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull DeviceTypeInformationProvider deviceTypeInformationProvider) {
        this.mContext = context;
        this.mHandsFreeSettingsStateProvider = handsFreeSettingsStateProvider;
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mDeviceTypeInformationProvider = deviceTypeInformationProvider;
    }
}
