package com.amazon.device.crashmanager.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class CrashDescriptor3rdPartyDedupeUtil implements CrashDescriptorDedupeUtil {
    private static final String KEY_SEPARATOR = "#";
    private final SharedPreferences mSharedPreferences;

    public CrashDescriptor3rdPartyDedupeUtil(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public void clearAll() {
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public boolean contains(String str, String str2) {
        return this.mSharedPreferences.contains(GeneratedOutlineSupport1.outline75(str, "#", str2));
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public int getCount(String str, String str2) {
        return this.mSharedPreferences.getInt(GeneratedOutlineSupport1.outline75(str, "#", str2), 0);
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public void increaseCounter(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String outline75 = GeneratedOutlineSupport1.outline75(str, "#", str2);
        if (this.mSharedPreferences.contains(outline75)) {
            this.mSharedPreferences.edit().putInt(outline75, this.mSharedPreferences.getInt(outline75, 0) + 1).commit();
            return;
        }
        this.mSharedPreferences.edit().putInt(outline75, 1).commit();
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public void persistCrashDescriptors() {
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public void prune(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String outline75 = GeneratedOutlineSupport1.outline75(str, "#", str2);
        if (!this.mSharedPreferences.contains(outline75)) {
            return;
        }
        int i2 = this.mSharedPreferences.getInt(outline75, 0);
        if (i >= i2) {
            this.mSharedPreferences.edit().remove(outline75).commit();
            return;
        }
        this.mSharedPreferences.edit().putInt(outline75, i2 - i).commit();
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public void prune(String str, String str2) {
        this.mSharedPreferences.edit().remove(GeneratedOutlineSupport1.outline75(str, "#", str2)).commit();
    }
}
