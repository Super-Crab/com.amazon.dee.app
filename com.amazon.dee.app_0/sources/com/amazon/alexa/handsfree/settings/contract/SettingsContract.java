package com.amazon.alexa.handsfree.settings.contract;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.CustomPreferenceSetting;
/* loaded from: classes8.dex */
public class SettingsContract {
    private final CustomPreferenceSetting mCustomPreferenceSetting;

    public SettingsContract(@NonNull CustomPreferenceSetting customPreferenceSetting) {
        this.mCustomPreferenceSetting = customPreferenceSetting;
    }

    @NonNull
    public CustomPreferenceSetting getCustomPreferenceSetting() {
        return this.mCustomPreferenceSetting;
    }
}
