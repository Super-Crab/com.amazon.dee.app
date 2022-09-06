package com.amazon.dcp.settings;
/* loaded from: classes12.dex */
public class SettingBoolean {
    private String mName;
    private SettingsNamespace mNamespace;
    private boolean mValue;

    public SettingBoolean(SettingsNamespace settingsNamespace, String str, boolean z) {
        this.mNamespace = settingsNamespace;
        this.mName = str;
        this.mValue = z;
    }

    public boolean getValue() {
        return SettingsCache.getInstance().getBooleanValue(this.mNamespace, this.mName, this.mValue);
    }

    public SettingBoolean(String str, boolean z) {
        this(SettingsNamespace.Default, str, z);
    }
}
