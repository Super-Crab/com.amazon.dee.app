package com.amazon.alexa.handsfree.settings.handsfreesettings;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsState;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.settings.handsfreesettings.handsfree.HandsFreeSettingPresenter;
import com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingPresenter;
import com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingPresenter;
import java.util.Objects;
/* loaded from: classes8.dex */
public class PreferencesPresenter {
    private static final String TAG = "PreferencesPresenter";
    private final HandsFreeSettingPresenter mHandsFreeSettingPresenter;
    private final boolean mIsShowOnLockScreenAvailable;
    private final boolean mIsUvrAvailable;
    private final LockScreenSettingPresenter mLockScreenSettingPresenter;
    private final RemoteConfigManager mRemoteConfigManager;
    private final VoiceProfileSettingPresenter mVoiceProfileSettingPresenter;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public static class Builder {
        private Context mContext;
        private HandsFreeSettingPresenter mHandsFreeSettingPresenter;
        private boolean mIsShowOnLockScreenAvailable;
        private boolean mIsUvrAvailable;
        private LockScreenSettingPresenter mLockScreenSettingPresenter;
        private VoiceProfileSettingPresenter mVoiceProfileSettingPresenter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(@NonNull Context context) {
            this.mContext = context;
        }

        public PreferencesPresenter build() {
            return new PreferencesPresenter(this.mHandsFreeSettingPresenter, this.mLockScreenSettingPresenter, this.mVoiceProfileSettingPresenter, ((FalcoSettingsComponent) AhfComponentsProvider.getComponent(this.mContext, FalcoSettingsComponent.class)).remoteConfigManager(), this.mIsUvrAvailable, this.mIsShowOnLockScreenAvailable);
        }

        public Builder withHandsFreeSettingPresenter(@NonNull HandsFreeSettingPresenter handsFreeSettingPresenter) {
            this.mHandsFreeSettingPresenter = handsFreeSettingPresenter;
            return this;
        }

        public Builder withLockScreenSettingPresenter(@NonNull LockScreenSettingPresenter lockScreenSettingPresenter) {
            this.mLockScreenSettingPresenter = lockScreenSettingPresenter;
            return this;
        }

        public Builder withShowOnLockScreenAvailable(boolean z) {
            this.mIsShowOnLockScreenAvailable = z;
            return this;
        }

        public Builder withUvrAvailable(boolean z) {
            this.mIsUvrAvailable = z;
            return this;
        }

        public Builder withVoiceProfileSettingPresenter(@NonNull VoiceProfileSettingPresenter voiceProfileSettingPresenter) {
            this.mVoiceProfileSettingPresenter = voiceProfileSettingPresenter;
            return this;
        }
    }

    @VisibleForTesting
    PreferencesPresenter(@NonNull HandsFreeSettingPresenter handsFreeSettingPresenter, @Nullable LockScreenSettingPresenter lockScreenSettingPresenter, @Nullable VoiceProfileSettingPresenter voiceProfileSettingPresenter, @NonNull RemoteConfigManager remoteConfigManager, boolean z, boolean z2) {
        this.mHandsFreeSettingPresenter = (HandsFreeSettingPresenter) Objects.requireNonNull(handsFreeSettingPresenter, "Hands-free presenter was null!");
        this.mLockScreenSettingPresenter = lockScreenSettingPresenter;
        this.mVoiceProfileSettingPresenter = voiceProfileSettingPresenter;
        this.mRemoteConfigManager = (RemoteConfigManager) Objects.requireNonNull(remoteConfigManager, "RemoteConfigManager was null!");
        this.mIsUvrAvailable = z;
        this.mIsShowOnLockScreenAvailable = z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLockScreenPreference() {
        this.mLockScreenSettingPresenter.onVoiceProfileSettingChange(this.mVoiceProfileSettingPresenter.isVoiceProfileSetup());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cleanup() {
        this.mHandsFreeSettingPresenter.cleanup();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getShowOnLockScreenValue() {
        this.mLockScreenSettingPresenter.getShowOnLockScreenValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void refreshDisplay() {
        this.mHandsFreeSettingPresenter.refreshDisplay();
        if (this.mIsUvrAvailable) {
            this.mVoiceProfileSettingPresenter.refreshDisplay();
            updateLockScreenPreference();
        }
        if (this.mIsShowOnLockScreenAvailable) {
            Log.v(TAG, "Refreshing show on lockscreen value");
            getShowOnLockScreenValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHandsFreeStateChangeForLockScreen() {
        this.mHandsFreeSettingPresenter.setHandsFreeStateChangedListener(new HandsFreeSettingsState.OnStateChangedListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.PreferencesPresenter.1
            @Override // com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsState.OnStateChangedListener
            public void onStateChanged(HandsFreeSettingsState handsFreeSettingsState) {
                if (PreferencesPresenter.this.mIsShowOnLockScreenAvailable) {
                    PreferencesPresenter.this.mLockScreenSettingPresenter.updateState(handsFreeSettingsState, PreferencesPresenter.this.mRemoteConfigManager.isLockScreenActive());
                }
                if (PreferencesPresenter.this.mIsUvrAvailable) {
                    PreferencesPresenter.this.mVoiceProfileSettingPresenter.updateState(handsFreeSettingsState);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupHandsFree() {
        this.mHandsFreeSettingPresenter.setup();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupHandsFreePreference() {
        this.mHandsFreeSettingPresenter.setupHandsFreePreference();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupShowOnLockscreen() {
        this.mLockScreenSettingPresenter.setupShowOnLockScreen(this.mHandsFreeSettingPresenter.isWakeWordActive(), this.mRemoteConfigManager.isLockScreenActive());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupVoiceProfilePreference() {
        this.mVoiceProfileSettingPresenter.setupVoiceProfilePreferences(new PreferenceCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.PreferencesPresenter.2
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback
            public void onPreferenceChanged(boolean z) {
                PreferencesPresenter.this.updateLockScreenPreference();
            }
        });
        updateLockScreenPreference();
        this.mHandsFreeSettingPresenter.refreshDisplay();
    }
}
