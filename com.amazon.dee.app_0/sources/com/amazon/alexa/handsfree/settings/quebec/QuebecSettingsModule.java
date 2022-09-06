package com.amazon.alexa.handsfree.settings.quebec;

import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
/* loaded from: classes8.dex */
public enum QuebecSettingsModule {
    INSTANCE;
    
    private final SettingsContract mSettingsContract = new SettingsContract(new QuebecPreferenceSetting());

    QuebecSettingsModule() {
    }

    public SettingsContract getSettingsContract() {
        return this.mSettingsContract;
    }
}
