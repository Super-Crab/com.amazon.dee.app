package com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback;
/* loaded from: classes8.dex */
public interface LockScreenSettingContract {

    /* loaded from: classes8.dex */
    public interface View {
        boolean isBlockSensitiveRequest();

        void onVoiceProfileSettingChange(boolean z);

        void setActive(String str);

        void setActive(boolean z);

        void setEnabled(boolean z);

        void setRemotelyDisabled();

        void setupListener(@NonNull PreferenceCallback preferenceCallback);

        void setupListener(@NonNull ResponsePreferenceCallback responsePreferenceCallback);
    }
}
