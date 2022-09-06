package com.amazon.device.crashmanager.source;

import android.content.SharedPreferences;
import com.amazon.device.crashmanager.source.SettingsStore;
/* loaded from: classes12.dex */
public class SharedPreferencesSettingsStore implements SettingsStore {
    SharedPreferences mSharedPreferences;

    public SharedPreferencesSettingsStore(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            this.mSharedPreferences = sharedPreferences;
            return;
        }
        throw new IllegalArgumentException("SharedPreferences can not be null");
    }

    @Override // com.amazon.device.crashmanager.source.SettingsStore
    public long getLong(SettingsStore.SettingsKey settingsKey, long j) {
        return this.mSharedPreferences.getLong(settingsKey.toString(), j);
    }

    @Override // com.amazon.device.crashmanager.source.SettingsStore
    public String getString(SettingsStore.SettingsKey settingsKey, String str) {
        return this.mSharedPreferences.getString(settingsKey.toString(), str);
    }

    @Override // com.amazon.device.crashmanager.source.SettingsStore
    public void saveLong(SettingsStore.SettingsKey settingsKey, long j) {
        this.mSharedPreferences.edit().putLong(settingsKey.toString(), j).apply();
    }

    @Override // com.amazon.device.crashmanager.source.SettingsStore
    public void saveString(SettingsStore.SettingsKey settingsKey, String str) {
        this.mSharedPreferences.edit().putString(settingsKey.toString(), str).apply();
    }
}
