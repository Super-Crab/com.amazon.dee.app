package com.amazon.dcp.settings;
/* loaded from: classes12.dex */
public enum SettingsNamespace {
    AppLocal,
    Default,
    DeviceGlobal;

    public static SettingsNamespace fromOrdinal(int i) {
        return values()[i];
    }
}
