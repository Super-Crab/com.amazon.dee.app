package com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
/* loaded from: classes8.dex */
public interface VoiceProfileSettingContract {

    /* loaded from: classes8.dex */
    public interface Listener {
        void onProfileDeletedSuccessfully();

        void onProfileDeletedWithError();
    }

    /* loaded from: classes8.dex */
    public interface View {
        void setMandatoryVoiceProfileNotEnrolled();

        void setOptionalVoiceProfileNotEnrolled();

        void setRemotelyDisabled();

        void setUIAddVoiceProfileSettingsAndSetActive();

        void setUIRemoveVoiceProfileSettingsAndSetInactive();

        void setVoiceProfileEnrolled();

        void setupCreateVoiceProfilePreference(@NonNull PreferenceCallback preferenceCallback);

        void setupDeleteVoiceProfilePreference(@NonNull PreferenceCallback preferenceCallback);

        void setupLockScreenCallback(@NonNull PreferenceCallback preferenceCallback);

        void showDeleteFail();

        void showDeleteSuccess();

        void updateViews(boolean z);
    }
}
