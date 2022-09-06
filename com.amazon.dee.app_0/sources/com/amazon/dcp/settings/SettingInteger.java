package com.amazon.dcp.settings;
/* loaded from: classes12.dex */
public class SettingInteger {
    private String mName;
    private SettingsNamespace mNamespace;
    private int mValue;

    public SettingInteger(SettingsNamespace settingsNamespace, String str, int i) {
        this.mNamespace = settingsNamespace;
        this.mName = str;
        this.mValue = i;
    }

    public int getValue() {
        return SettingsCache.getInstance().getIntValue(this.mNamespace, this.mName, this.mValue);
    }

    public SettingInteger(String str, int i) {
        this(SettingsNamespace.Default, str, i);
    }
}
