package com.amazon.dcp.settings;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class SettingsCache {
    private static SettingsCache INSTANCE = new SettingsCache();
    private final HashMap<String, String> mValues = new HashMap<>();

    /* loaded from: classes12.dex */
    public interface IListener {
        void onCacheUpdated();
    }

    protected SettingsCache() {
    }

    public static SettingsCache getInstance() {
        return INSTANCE;
    }

    public static void setContext(Context context) {
    }

    public static void waitForInitialSync() {
    }

    public void addListener(IListener iListener) {
    }

    public boolean getBooleanValue(String str, boolean z) {
        return Boolean.parseBoolean(getValue(str, Boolean.toString(z)));
    }

    public int getIntValue(String str, int i) {
        return Integer.parseInt(getValue(str, Integer.toString(i)));
    }

    public long getLongValue(String str, long j) {
        return Long.parseLong(getValue(str, Long.toString(j)));
    }

    public Map<String, String> getSettings(SettingsNamespace settingsNamespace) {
        return this.mValues;
    }

    public String getValue(String str, String str2) {
        return getValue(SettingsNamespace.Default, str, str2);
    }

    public void removeListener(IListener iListener) {
    }

    public boolean getBooleanValue(SettingsNamespace settingsNamespace, String str, boolean z) {
        return Boolean.parseBoolean(getValue(settingsNamespace, str, Boolean.toString(z)));
    }

    public int getIntValue(SettingsNamespace settingsNamespace, String str, int i) {
        return Integer.parseInt(getValue(settingsNamespace, str, Integer.toString(i)));
    }

    public long getLongValue(SettingsNamespace settingsNamespace, String str, long j) {
        return Long.parseLong(getValue(settingsNamespace, str, Long.toString(j)));
    }

    public String getValue(SettingsNamespace settingsNamespace, String str, String str2) {
        return this.mValues.containsKey(str) ? this.mValues.get(str) : str2;
    }
}
