package com.amazon.device.crashmanager.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class DropboxCrashDescriptorDedupeUtil implements CrashDescriptorDedupeUtil {
    private static final String KEY_DESCRIPTORS = "CrashDescriptors";
    private static final String KEY_SEPARATOR = "#";
    private static final DPLogger log = new DPLogger("CrashManager1stParty.DedupeUtil");
    private final Map<String, Integer> mDescriptorMap = new HashMap();
    private final SharedPreferences mSharedPreferences;

    public DropboxCrashDescriptorDedupeUtil(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
        loadFromSharedPreference();
    }

    private void loadFromSharedPreference() {
        String[] split;
        String string = this.mSharedPreferences.getString(KEY_DESCRIPTORS, null);
        if (!TextUtils.isEmpty(string)) {
            for (String str : string.split(" ")) {
                try {
                    int i = this.mSharedPreferences.getInt(str, 0);
                    if (i > 0) {
                        this.mDescriptorMap.put(str, Integer.valueOf(i));
                    }
                } catch (ClassCastException e) {
                    log.warn("loadFromSharedPreferences", "Error retrieving descriptor.", e);
                }
            }
        }
        DPLogger dPLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("finish loading, map size = ");
        outline107.append(this.mDescriptorMap.size());
        dPLogger.debug("loadFromSharedPreference", outline107.toString(), new Object[0]);
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public void clearAll() {
        this.mSharedPreferences.edit().clear().commit();
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public boolean contains(String str, String str2) {
        return getCount(str, str2) > 0;
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
        Map<String, Integer> map = this.mDescriptorMap;
        int i = 1;
        if (map.get(outline75) != null) {
            i = 1 + this.mDescriptorMap.get(outline75).intValue();
        }
        map.put(outline75, Integer.valueOf(i));
        DPLogger dPLogger = log;
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("increaseCounter, descriptor=", outline75, " counter=");
        outline115.append(this.mDescriptorMap.get(outline75));
        dPLogger.debug("increaseCounter", outline115.toString(), new Object[0]);
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public void persistCrashDescriptors() {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        edit.clear();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : this.mDescriptorMap.entrySet()) {
            log.debug("saveToSharedPreferences", "Save Key =", entry.getKey());
            sb.append(entry.getKey());
            sb.append(" ");
            if (entry.getValue() != null) {
                edit.putInt(entry.getKey(), entry.getValue().intValue());
            }
        }
        edit.putString(KEY_DESCRIPTORS, sb.toString());
        edit.commit();
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public void prune(String str, String str2, int i) {
        prune(str, str2);
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil
    public void prune(String str, String str2) {
        this.mSharedPreferences.edit().remove(GeneratedOutlineSupport1.outline75(str, "#", str2)).commit();
    }
}
