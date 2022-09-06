package com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsState;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.LockScreenSettingState;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.LockScreenSettingStateProvider;
import com.amazon.alexa.handsfree.settings.R;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract;
import com.amazon.alexa.handsfree.settings.metrics.ClickMetricMetadata;
import com.amazon.alexa.handsfree.settings.voxsettings.ShowOnLockscreenResultReceiver;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsEnqueuer;
/* loaded from: classes8.dex */
public class LockScreenSettingPresenter {
    static final String DISABLE_SHOW_ON_LOCK_SCREEN = "disableShowOnLockscreen";
    private static final String TAG = "LockScreenSettingPresenter";
    private final Context mContext;
    private LockScreenSettingStateProvider mLockScreenSettingStateProvider;
    private final LockScreenSettingContract.View mLockScreenView;
    private MetricsBuilderProvider mMetricsBuilderProvider;
    private VoxSettingsEnqueuer mVoxSettingsEnqueuer;

    public LockScreenSettingPresenter(@NonNull LockScreenSettingContract.View view, @NonNull Context context) {
        this(view, context, new VoxSettingsEnqueuer(), MetricsBuilderProvider.getInstance(context), new LockScreenSettingStateProvider(context));
    }

    public void disableShowOnLockScreen() {
        ShowOnLockscreenResultReceiver showOnLockscreenResultReceiver;
        if (this.mLockScreenView.isBlockSensitiveRequest()) {
            showOnLockscreenResultReceiver = new ShowOnLockscreenResultReceiver(new ResponsePreferenceCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingPresenter.5
                @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback
                public void onPreferenceChanged(String str) {
                    LockScreenSettingPresenter.this.mLockScreenSettingStateProvider.setLockScreenSettingState(str.equals(LockScreenSettingPresenter.this.mContext.getString(R.string.alexa_respond_while_locked_block_all)) ? LockScreenSettingState.INACTIVE : LockScreenSettingState.ACTIVE);
                }
            });
        } else {
            showOnLockscreenResultReceiver = new ShowOnLockscreenResultReceiver(new PreferenceCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingPresenter.6
                @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback
                public void onPreferenceChanged(boolean z) {
                    LockScreenSettingPresenter.this.mLockScreenSettingStateProvider.setLockScreenSettingState(z ? LockScreenSettingState.ACTIVE : LockScreenSettingState.INACTIVE);
                }
            });
        }
        this.mVoxSettingsEnqueuer.getShowOnLockscreen(this.mContext, showOnLockscreenResultReceiver);
        this.mLockScreenView.setRemotelyDisabled();
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, DISABLE_SHOW_ON_LOCK_SCREEN).emit(this.mContext);
    }

    public void getShowOnLockScreenValue() {
        if (this.mLockScreenView.isBlockSensitiveRequest()) {
            getShowOnLockScreenValue((ResponsePreferenceCallback) null);
        } else {
            getShowOnLockScreenValue((PreferenceCallback) null);
        }
    }

    public void onVoiceProfileSettingChange(boolean z) {
        this.mLockScreenView.onVoiceProfileSettingChange(z);
    }

    public void setupShowOnLockScreen(boolean z, boolean z2, @Nullable final ResponsePreferenceCallback responsePreferenceCallback) {
        this.mLockScreenView.setupListener(new ResponsePreferenceCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingPresenter.3
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback
            public void onPreferenceChanged(String str) {
                LockScreenSettingPresenter.this.mVoxSettingsEnqueuer.updateShowOnLockscreenPref(LockScreenSettingPresenter.this.mContext, str);
                ResponsePreferenceCallback responsePreferenceCallback2 = responsePreferenceCallback;
                if (responsePreferenceCallback2 != null) {
                    responsePreferenceCallback2.onPreferenceChanged(str);
                }
            }
        });
        this.mLockScreenView.setEnabled(z);
        if (!z2) {
            disableShowOnLockScreen();
        }
    }

    public void updateState(@NonNull HandsFreeSettingsState handsFreeSettingsState, boolean z) {
        LockScreenSettingState previousLockScreenSettingState = this.mLockScreenSettingStateProvider.getPreviousLockScreenSettingState();
        if (!handsFreeSettingsState.isRemotelyDisabled() && z) {
            boolean z2 = true;
            if (previousLockScreenSettingState.isEnabled()) {
                Log.d(TAG, "The lock screen was disabled, re-enable and re-assign the previous state.");
                if (this.mLockScreenView.isBlockSensitiveRequest()) {
                    VoxSettingsEnqueuer voxSettingsEnqueuer = this.mVoxSettingsEnqueuer;
                    Context context = this.mContext;
                    voxSettingsEnqueuer.updateShowOnLockscreenPref(context, context.getString(R.string.alexa_respond_while_locked_block_personal));
                } else {
                    this.mVoxSettingsEnqueuer.updateShowOnLockscreenPref(this.mContext, previousLockScreenSettingState == LockScreenSettingState.ACTIVE);
                }
                this.mLockScreenSettingStateProvider.setLockScreenSettingState(LockScreenSettingState.REMOTELY_DISABLED);
            }
            if (handsFreeSettingsState != HandsFreeSettingsState.ACTIVE) {
                z2 = false;
            }
            this.mLockScreenView.setEnabled(z2);
        } else if (previousLockScreenSettingState.isEnabled()) {
            Log.d(TAG, "The lock screen has already been disabled.");
        } else {
            disableShowOnLockScreen();
        }
    }

    @VisibleForTesting
    LockScreenSettingPresenter(@NonNull LockScreenSettingContract.View view, @NonNull Context context, @NonNull VoxSettingsEnqueuer voxSettingsEnqueuer, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull LockScreenSettingStateProvider lockScreenSettingStateProvider) {
        this.mLockScreenView = view;
        this.mContext = context;
        this.mVoxSettingsEnqueuer = voxSettingsEnqueuer;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mLockScreenSettingStateProvider = lockScreenSettingStateProvider;
    }

    public void getShowOnLockScreenValue(@Nullable final ResponsePreferenceCallback responsePreferenceCallback) {
        ShowOnLockscreenResultReceiver showOnLockscreenResultReceiver = new ShowOnLockscreenResultReceiver(new ResponsePreferenceCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingPresenter.1
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback
            public void onPreferenceChanged(String str) {
                LockScreenSettingPresenter.this.mLockScreenView.setActive(str);
                ResponsePreferenceCallback responsePreferenceCallback2 = responsePreferenceCallback;
                if (responsePreferenceCallback2 != null) {
                    responsePreferenceCallback2.onPreferenceChanged(str);
                }
            }
        });
        Log.i(TAG, "Called getShowOnLockScreenValue");
        this.mVoxSettingsEnqueuer.getShowOnLockscreen(this.mContext, showOnLockscreenResultReceiver);
    }

    public void setupShowOnLockScreen(boolean z, boolean z2, @Nullable final PreferenceCallback preferenceCallback) {
        this.mLockScreenView.setupListener(new PreferenceCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingPresenter.4
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback
            public void onPreferenceChanged(boolean z3) {
                LockScreenSettingPresenter.this.mMetricsBuilderProvider.newBuilder().withClickMetric(LockScreenSettingPresenter.TAG, ClickMetricMetadata.Component.SETTINGS_MENU, ClickMetricMetadata.PageType.RESPOND_ON_LOCK_SCREEN, z3 ? ClickMetricMetadata.Action.ENABLE : ClickMetricMetadata.Action.DISABLE).emit(LockScreenSettingPresenter.this.mContext);
                LockScreenSettingPresenter.this.mVoxSettingsEnqueuer.updateShowOnLockscreenPref(LockScreenSettingPresenter.this.mContext, z3);
                PreferenceCallback preferenceCallback2 = preferenceCallback;
                if (preferenceCallback2 != null) {
                    preferenceCallback2.onPreferenceChanged(z3);
                }
            }
        });
        this.mLockScreenView.setEnabled(z);
        if (!z2) {
            disableShowOnLockScreen();
        }
    }

    public void getShowOnLockScreenValue(@Nullable final PreferenceCallback preferenceCallback) {
        ShowOnLockscreenResultReceiver showOnLockscreenResultReceiver = new ShowOnLockscreenResultReceiver(new PreferenceCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingPresenter.2
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback
            public void onPreferenceChanged(boolean z) {
                LockScreenSettingPresenter.this.mLockScreenView.setActive(z);
                PreferenceCallback preferenceCallback2 = preferenceCallback;
                if (preferenceCallback2 != null) {
                    preferenceCallback2.onPreferenceChanged(z);
                }
            }
        });
        Log.i(TAG, "Called getShowOnLockScreenValue");
        this.mVoxSettingsEnqueuer.getShowOnLockscreen(this.mContext, showOnLockscreenResultReceiver);
    }

    public void setupShowOnLockScreen(boolean z, boolean z2) {
        if (this.mLockScreenView.isBlockSensitiveRequest()) {
            setupShowOnLockScreen(z, z2, (ResponsePreferenceCallback) null);
        } else {
            setupShowOnLockScreen(z, z2, (PreferenceCallback) null);
        }
    }
}
