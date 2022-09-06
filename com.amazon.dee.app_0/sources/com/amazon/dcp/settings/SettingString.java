package com.amazon.dcp.settings;
/* loaded from: classes12.dex */
public class SettingString {
    private String mName;
    private SettingsNamespace mNamespace;
    private String mValue;

    public SettingString(SettingsNamespace settingsNamespace, String str, String str2) {
        this.mNamespace = settingsNamespace;
        this.mName = str;
        this.mValue = str2;
    }

    public String getValue() {
        return SettingsCache.getInstance().getValue(this.mNamespace, this.mName, this.mValue);
    }

    public SettingString(String str, String str2) {
        this(SettingsNamespace.Default, str, str2);
    }
}
