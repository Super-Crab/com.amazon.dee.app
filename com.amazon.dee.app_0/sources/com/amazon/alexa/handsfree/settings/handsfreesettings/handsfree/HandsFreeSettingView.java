package com.amazon.alexa.handsfree.settings.handsfreesettings.handsfree;

import android.content.Context;
import android.preference.Preference;
import android.preference.SwitchPreference;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.settings.R;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract;
/* loaded from: classes8.dex */
public class HandsFreeSettingView implements HandsFreeSettingContract.View {
    private final Context mContext;
    private final SwitchPreference mHandsFreePreference;

    public HandsFreeSettingView(@NonNull SwitchPreference switchPreference, @NonNull Context context) {
        this.mHandsFreePreference = switchPreference;
        this.mContext = context;
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.View
    public void setActive() {
        this.mHandsFreePreference.setEnabled(true);
        this.mHandsFreePreference.setChecked(true);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.View
    public void setInactive() {
        this.mHandsFreePreference.setEnabled(true);
        this.mHandsFreePreference.setChecked(false);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.View
    public void setLocallyDisabled() {
        this.mHandsFreePreference.setEnabled(false);
        this.mHandsFreePreference.setChecked(false);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.View
    public void setRemotelyDisabled() {
        this.mHandsFreePreference.setEnabled(false);
        this.mHandsFreePreference.setChecked(false);
        this.mHandsFreePreference.setSelectable(false);
        this.mHandsFreePreference.setSummary(this.mContext.getString(R.string.kill_switch_setting_text));
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract.View
    public void setupListener(@NonNull final HandsFreeSettingContract.ViewListener viewListener) {
        this.mHandsFreePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.handsfree.HandsFreeSettingView.1
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(@NonNull Preference preference, @NonNull Object obj) {
                return viewListener.onPreferenceChanged();
            }
        });
    }
}
