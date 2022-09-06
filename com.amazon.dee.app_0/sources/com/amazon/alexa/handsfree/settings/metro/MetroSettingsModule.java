package com.amazon.alexa.handsfree.settings.metro;

import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
/* loaded from: classes8.dex */
public enum MetroSettingsModule {
    INSTANCE;
    
    private final SettingsContract mSettingsContract = new SettingsContract(new MetroPreferenceSetting());

    MetroSettingsModule() {
    }

    public SettingsContract getSettingsContract() {
        return this.mSettingsContract;
    }
}
