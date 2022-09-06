package com.amazon.alexa.handsfree.settings;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricData;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsState;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsStateProvider;
import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import com.amazon.alexa.handsfree.settings.contract.SetupFlow;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.settings.metrics.ClickMetricMetadata;
import com.amazon.alexa.handsfree.settings.metrics.HandsFreeSetupMetricMetadata;
import com.amazon.alexa.handsfree.settings.metrics.MetricType;
import com.amazon.alexa.handsfree.settings.metrics.PageViewMetricMetadata;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.Locale;
/* loaded from: classes8.dex */
public abstract class AlexaHandsFreeManager {
    private static final String TAG = "AlexaHandsFreeManager";
    private final Lazy<AlexaAppSignInContract> mAlexaAppSignInContractLazy;
    private final Context mContext;
    private HandsFreeSettingsState mHandsFreeSettingState;
    private HandsFreeSettingsState.OnStateChangedListener mHandsFreeSettingStateChangedListener;
    private final HandsFreeSettingsStateProvider mHandsFreeSettingsStateProvider;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final RemoteConfigManager mRemoteConfigManager;
    private final SettingsModule mSettingsModule;
    private final SettingsSetupFlowContract mSettingsSetupFlowContract;
    private final WakeWordSettingsManager mWakeWordSettingsManager;

    /* JADX INFO: Access modifiers changed from: protected */
    public AlexaHandsFreeManager(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context), ((FalcoSettingsComponent) AhfComponentsProvider.getComponent(context, FalcoSettingsComponent.class)).remoteConfigManager(), new HandsFreeSettingsStateProvider(context), HandsFreeSettingsState.LOCALLY_DISABLED, WakeWordSettingsManagerProvider.getInstance().get(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).alexaAppSignInContractLazy(), SettingsModule.INSTANCE.getSetupFlowContract(), SettingsModule.INSTANCE);
    }

    private void executePendingStep() {
        Context context = this.mContext;
        startActivity(context, this.mSettingsSetupFlowContract.getPendingSetupIntent(context, SetupFlow.DEFAULT));
        recordHandsFreeSetupMetric();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean canEnableWakeWord() {
        return this.mSettingsSetupFlowContract.canEnableWakeWord(this.mContext);
    }

    public void connectAndCheckSignIn() {
        this.mAlexaAppSignInContractLazy.mo358get().setup(this.mContext, false);
    }

    protected void disableHandsFree(@NonNull HandsFreeSettingsState handsFreeSettingsState) {
        if (!handsFreeSettingsState.isEnabled()) {
            return;
        }
        String str = TAG;
        Log.d(str, "Storing last known Hands-Free settings state: [" + handsFreeSettingsState + "]");
        this.mHandsFreeSettingsStateProvider.setSettingsState(handsFreeSettingsState);
        setHandsFreeState(HandsFreeSettingsState.REMOTELY_DISABLED);
    }

    protected abstract ClickMetricMetadata.Component getActionComponent();

    protected abstract ClickMetricMetadata.PageType getActionPageType();

    protected abstract HandsFreeSetupMetricMetadata.Component getHandsFreeComponent();

    protected abstract HandsFreeSetupMetricMetadata.PageType getHandsFreePageType();

    @NonNull
    public HandsFreeSettingsState getHandsFreeSettingsState() {
        return this.mHandsFreeSettingState;
    }

    protected abstract PageViewMetricMetadata.Component getPageViewComponent();

    protected abstract PageViewMetricMetadata.PageType getPageViewPageType();

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasPendingSteps() {
        return this.mSettingsSetupFlowContract.hasPendingSetup(this.mContext, SetupFlow.DEFAULT);
    }

    public void recordHandsFreeSetupMetric() {
        this.mMetricsBuilderProvider.newBuilder().withHandsFreeSetupMetric(TAG, new HandsFreeSetupMetricData(HandsFreeSetupMetricMetadata.ActionType.INTENTION, HandsFreeSetupMetricMetadata.EventType.CLICK, getHandsFreeComponent(), getHandsFreePageType())).emit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @VisibleForTesting
    public void recordOperationForAlarm(String str, boolean z) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (z) {
            newBuilder.withPercentileMetricSuccess(TAG, str);
        } else {
            newBuilder.withPercentileMetricFailure(TAG, str);
        }
        newBuilder.emit(this.mContext);
    }

    @VisibleForTesting
    void recordOperationForHandsFreeSyncAlarm(boolean z) {
        recordOperationForAlarm(MetricType.HANDSFREE_MANAGER_SYNC_SUCCESS.getValue(), z);
    }

    public synchronized void refreshHandsFree() {
        if (this.mHandsFreeSettingsStateProvider.getPreviousSettingsState().isEnabled()) {
            Log.d(TAG, "Hands-Free is temporarily inactive.");
            syncHandsFree(HandsFreeSettingsState.REMOTELY_DISABLED);
            return;
        }
        this.mWakeWordSettingsManager.checkHandsFreeState(new ResultCallback<Boolean>() { // from class: com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                Log.e(AlexaHandsFreeManager.TAG, "Failed to sync hands-free state.");
                AlexaHandsFreeManager.this.setHandsFreeSettingState(HandsFreeSettingsState.LOCALLY_DISABLED);
                AlexaHandsFreeManager.this.recordOperationForHandsFreeSyncAlarm(false);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(Boolean bool) {
                HandsFreeSettingsState handsFreeSettingsState = bool.booleanValue() ? HandsFreeSettingsState.ACTIVE : HandsFreeSettingsState.INACTIVE;
                AlexaHandsFreeManager.this.syncHandsFree(handsFreeSettingsState);
                if (AlexaHandsFreeManager.this.mHandsFreeSettingStateChangedListener != null) {
                    AlexaHandsFreeManager.this.mHandsFreeSettingStateChangedListener.onStateChanged(handsFreeSettingsState);
                }
                AlexaHandsFreeManager.this.recordOperationForHandsFreeSyncAlarm(true);
            }
        });
    }

    public void releaseCheckSignInThread() {
        this.mAlexaAppSignInContractLazy.mo358get().teardown(this.mContext);
    }

    public void reportClickMetric(@NonNull ClickMetricMetadata.Action action) {
        this.mMetricsBuilderProvider.newBuilder().withClickMetric(TAG, getActionComponent(), getActionPageType(), action).emit(this.mContext);
    }

    public void reportPageViewMetric() {
        this.mMetricsBuilderProvider.newBuilder().withPageViewMetric(TAG, getPageViewComponent(), getPageViewPageType(), PageViewMetricMetadata.Action.VIEW).emit(this.mContext);
    }

    public void reportToggleMetrics(boolean z, boolean z2) {
        MetricsBuilder withClickMetric = this.mMetricsBuilderProvider.newBuilder().withClickMetric(TAG, getActionComponent(), getActionPageType(), z2 ? ClickMetricMetadata.Action.ENABLE : ClickMetricMetadata.Action.DISABLE);
        if (z) {
            withClickMetric.withPercentileMetricSuccess(TAG, MetricType.HANDSFREE_MANAGER_TOGGLE_SUCCESS.getValue());
        } else {
            withClickMetric.withPercentileMetricFailure(TAG, MetricType.HANDSFREE_MANAGER_TOGGLE_SUCCESS.getValue());
        }
        withClickMetric.emit(this.mContext);
    }

    public void setHandsFreeSettingState(@NonNull HandsFreeSettingsState handsFreeSettingsState) {
        this.mHandsFreeSettingState = handsFreeSettingsState;
        updateOnUIThread(handsFreeSettingsState);
    }

    protected void setHandsFreeState(@NonNull final HandsFreeSettingsState handsFreeSettingsState) {
        final boolean z = handsFreeSettingsState == HandsFreeSettingsState.ACTIVE;
        this.mWakeWordSettingsManager.setHandsFreeState(z, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager.2
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                String str = AlexaHandsFreeManager.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to set hands-free state ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.e(str, outline107.toString());
                AlexaHandsFreeManager.this.reportToggleMetrics(false, z);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                String str = AlexaHandsFreeManager.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Success to set hands-free to [");
                outline107.append(handsFreeSettingsState);
                outline107.append("]");
                Log.d(str, outline107.toString());
                if (AlexaHandsFreeManager.this.mHandsFreeSettingStateChangedListener != null) {
                    AlexaHandsFreeManager.this.mHandsFreeSettingStateChangedListener.onStateChanged(handsFreeSettingsState);
                }
                AlexaHandsFreeManager.this.setHandsFreeSettingState(handsFreeSettingsState);
                AlexaHandsFreeManager.this.reportToggleMetrics(true, z);
            }
        }, TAG);
    }

    public void setHandsFreeStateChangedListener(@NonNull HandsFreeSettingsState.OnStateChangedListener onStateChangedListener) {
        this.mHandsFreeSettingStateChangedListener = onStateChangedListener;
    }

    protected boolean shouldHandsFreeSettingsBeDisabled(@NonNull String str) {
        boolean z = !this.mRemoteConfigManager.isSettingsActive();
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (z) {
            newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.SETTINGS_NOT_ACTIVE.getValue(), str));
        }
        boolean z2 = !this.mRemoteConfigManager.isHandsFreeActive();
        if (z2) {
            newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.HANDSFREE_NOT_ACTIVE.getValue(), str));
        }
        newBuilder.emit(this.mContext);
        return z2 || z || !this.mSettingsModule.isComponentsEnabled();
    }

    protected abstract void startActivity(@NonNull Context context, @NonNull Intent intent);

    @VisibleForTesting
    void syncHandsFree(@NonNull HandsFreeSettingsState handsFreeSettingsState) {
        if (shouldHandsFreeSettingsBeDisabled("syncHandsFree")) {
            disableHandsFree(handsFreeSettingsState);
            if (!this.mHandsFreeSettingState.isRemotelyDisabled()) {
                Log.d(TAG, "Syncing up Toggle/Tile to remain disabled when Kill-Switch is on.");
                setHandsFreeSettingState(HandsFreeSettingsState.REMOTELY_DISABLED);
            }
            HandsFreeSettingsState.OnStateChangedListener onStateChangedListener = this.mHandsFreeSettingStateChangedListener;
            if (onStateChangedListener == null) {
                return;
            }
            onStateChangedListener.onStateChanged(HandsFreeSettingsState.REMOTELY_DISABLED);
        } else if (this.mHandsFreeSettingsStateProvider.getPreviousSettingsState().isEnabled()) {
            HandsFreeSettingsState previousSettingsState = this.mHandsFreeSettingsStateProvider.getPreviousSettingsState();
            String str = TAG;
            Log.d(str, "KillSwitch is now off. Re-enabling settings manager instance. Last known state: [" + previousSettingsState + "]");
            setHandsFreeState(previousSettingsState);
            this.mHandsFreeSettingsStateProvider.setSettingsState(HandsFreeSettingsState.REMOTELY_DISABLED);
        } else if (!canEnableWakeWord()) {
            setHandsFreeSettingState(HandsFreeSettingsState.INACTIVE);
        } else if (this.mHandsFreeSettingState == handsFreeSettingsState) {
            Log.d(TAG, "Hands-Free setting is already synced. No update required.");
        } else {
            String str2 = TAG;
            Log.d(str2, "syncHandsFreeState::Successfully sync hands-free setting. Current setting is:[" + handsFreeSettingsState + "]");
            setHandsFreeSettingState(handsFreeSettingsState);
        }
    }

    public boolean updateHandsFreeState() {
        if (shouldHandsFreeSettingsBeDisabled("updateHandsFreeState")) {
            disableHandsFree(this.mHandsFreeSettingState);
            return false;
        }
        HandsFreeSettingsState handsFreeSettingsState = this.mHandsFreeSettingState;
        HandsFreeSettingsState handsFreeSettingsState2 = HandsFreeSettingsState.ACTIVE;
        if (handsFreeSettingsState == handsFreeSettingsState2) {
            handsFreeSettingsState2 = HandsFreeSettingsState.INACTIVE;
        }
        if (handsFreeSettingsState2 == HandsFreeSettingsState.ACTIVE) {
            if (this.mSettingsSetupFlowContract != null) {
                if (hasPendingSteps()) {
                    executePendingStep();
                }
                if (!canEnableWakeWord()) {
                    return false;
                }
            } else {
                Log.w(TAG, "SettingSetupFlowContract is not available. Can't update hands-free state.");
                this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, MetricType.SETTINGS_SETUP_CONTRACT_NOT_AVAILABLE_METRIC.getValue()).emit(this.mContext);
                return false;
            }
        }
        setHandsFreeSettingState(HandsFreeSettingsState.LOCALLY_DISABLED);
        setHandsFreeState(handsFreeSettingsState2);
        return true;
    }

    protected abstract void updateOnUIThread(@Nullable HandsFreeSettingsState handsFreeSettingsState);

    /* JADX INFO: Access modifiers changed from: protected */
    public AlexaHandsFreeManager(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull RemoteConfigManager remoteConfigManager, @NonNull HandsFreeSettingsStateProvider handsFreeSettingsStateProvider, @NonNull HandsFreeSettingsState handsFreeSettingsState, @NonNull WakeWordSettingsManager wakeWordSettingsManager, @NonNull Lazy<AlexaAppSignInContract> lazy, @NonNull SettingsSetupFlowContract settingsSetupFlowContract, @NonNull SettingsModule settingsModule) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mRemoteConfigManager = remoteConfigManager;
        this.mHandsFreeSettingsStateProvider = handsFreeSettingsStateProvider;
        this.mHandsFreeSettingState = handsFreeSettingsState;
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
        this.mAlexaAppSignInContractLazy = lazy;
        this.mSettingsSetupFlowContract = settingsSetupFlowContract;
        this.mSettingsModule = settingsModule;
    }
}
