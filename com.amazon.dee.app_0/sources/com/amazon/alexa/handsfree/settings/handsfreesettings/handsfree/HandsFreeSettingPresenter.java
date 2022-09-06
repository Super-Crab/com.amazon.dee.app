package com.amazon.alexa.handsfree.settings.handsfreesettings.handsfree;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsState;
import com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract;
import com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaHandsFreeSettingsManager;
/* loaded from: classes8.dex */
public class HandsFreeSettingPresenter implements HandsFreeSettingContract.Listener, HandsFreeSettingContract.WakeWordManager {
    private AlexaHandsFreeManager mAlexaHandsFreeManager;
    private final HandsFreeSettingContract.View mHandsFreeView;

    public HandsFreeSettingPresenter(@NonNull HandsFreeSettingContract.View view, @NonNull Context context) {
        this.mHandsFreeView = view;
        this.mAlexaHandsFreeManager = new AlexaHandsFreeSettingsManager(context, this);
    }

    public void cleanup() {
        this.mAlexaHandsFreeManager.releaseCheckSignInThread();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.WakeWordManager
    public boolean isWakeWordActive() {
        return this.mAlexaHandsFreeManager.getHandsFreeSettingsState() == HandsFreeSettingsState.ACTIVE;
    }

    public void refreshDisplay() {
        this.mAlexaHandsFreeManager.refreshHandsFree();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.Listener
    public void setActive() {
        this.mHandsFreeView.setActive();
    }

    public void setHandsFreeStateChangedListener(@NonNull HandsFreeSettingsState.OnStateChangedListener onStateChangedListener) {
        this.mAlexaHandsFreeManager.setHandsFreeStateChangedListener(onStateChangedListener);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.Listener
    public void setInactive() {
        this.mHandsFreeView.setInactive();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.Listener
    public void setLocallyDisabled() {
        this.mHandsFreeView.setLocallyDisabled();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.Listener
    public void setRemotelyDisabled() {
        this.mHandsFreeView.setRemotelyDisabled();
    }

    public void setup() {
        this.mAlexaHandsFreeManager.connectAndCheckSignIn();
        this.mAlexaHandsFreeManager.reportPageViewMetric();
    }

    public void setupHandsFreePreference() {
        this.mHandsFreeView.setupListener(new HandsFreeSettingContract.ViewListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.handsfree.HandsFreeSettingPresenter.1
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.ViewListener
            public boolean onPreferenceChanged() {
                return HandsFreeSettingPresenter.this.mAlexaHandsFreeManager.updateHandsFreeState();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.WakeWordManager
    public void toggleWakeWordDetection() {
        this.mAlexaHandsFreeManager.updateHandsFreeState();
    }

    @VisibleForTesting
    HandsFreeSettingPresenter(@NonNull HandsFreeSettingContract.View view, @NonNull AlexaHandsFreeManager alexaHandsFreeManager) {
        this.mHandsFreeView = view;
        this.mAlexaHandsFreeManager = alexaHandsFreeManager;
    }
}
