package com.amazon.dcp.settings;
/* loaded from: classes12.dex */
public class SettingLong {
    private String mName;
    private SettingsNamespace mNamespace;
    private long mValue;

    public SettingLong(SettingsNamespace settingsNamespace, String str, long j) {
        this.mNamespace = settingsNamespace;
        this.mName = str;
        this.mValue = j;
    }

    public long getValue() {
        return SettingsCache.getInstance().getLongValue(this.mNamespace, this.mName, this.mValue);
    }

    public SettingLong(String str, long j) {
        this(SettingsNamespace.Default, str, j);
    }
}
