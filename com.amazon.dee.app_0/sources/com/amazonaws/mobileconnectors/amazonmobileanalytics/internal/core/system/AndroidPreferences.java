package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system;

import android.content.Context;
import android.content.SharedPreferences;
@Deprecated
/* loaded from: classes13.dex */
public class AndroidPreferences implements Preferences {
    private final SharedPreferences preferences;

    public AndroidPreferences(Context context, String str) {
        this.preferences = context.getSharedPreferences(str, 0);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences
    public boolean getBoolean(String str, boolean z) {
        return this.preferences.getBoolean(str, z);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences
    public float getFloat(String str, float f) {
        return this.preferences.getFloat(str, f);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences
    public int getInt(String str, int i) {
        return this.preferences.getInt(str, i);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences
    public long getLong(String str, long j) {
        return this.preferences.getLong(str, j);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences
    public String getString(String str, String str2) {
        return this.preferences.getString(str, str2);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences
    public void putBoolean(String str, boolean z) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences
    public void putFloat(String str, float f) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putFloat(str, f);
        edit.commit();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences
    public void putInt(String str, int i) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences
    public void putLong(String str, long j) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences
    public void putString(String str, String str2) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putString(str, str2);
        edit.commit();
    }
}
