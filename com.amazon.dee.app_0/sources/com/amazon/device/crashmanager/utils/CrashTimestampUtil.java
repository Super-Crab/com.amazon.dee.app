package com.amazon.device.crashmanager.utils;

import amazon.speech.simclient.settings.Settings;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.device.crashmanager.source.SettingsStore;
import com.amazon.dp.logger.DPLogger;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class CrashTimestampUtil {
    private static final long DEFAULT_LAST_GLOBAL_DROPBOX_TIME_INDEX = 0;
    private static final String DEFAULT_TIMESTAMP_JSON_STRING = "{}";
    private static CrashTimestampUtil sCrashTimestampUtil;
    private JSONObject mLatestDedupeTimestampsJson;
    private JSONObject mLatestUploadTimestampsJson;
    private final SettingsStore mSettingsStore;
    private static final DPLogger log = new DPLogger("CrashManager.CrashTimestampUtil");
    private static final SettingsStore.SettingsKey KEY_LATEST_UPLOAD_TIMESTAMPS_JSON = SettingsStore.SettingsKey.UPLOAD_TIMESTAMPS_JSON;
    private static final SettingsStore.SettingsKey KEY_LATEST_DEDUPE_TIMESTAMPS_JSON = SettingsStore.SettingsKey.DEDUPE_TIMESTAMPS_JSON;

    private CrashTimestampUtil(SettingsStore settingsStore) {
        if (settingsStore != null) {
            this.mSettingsStore = settingsStore;
            this.mLatestUploadTimestampsJson = getSavedTimestampJson(KEY_LATEST_UPLOAD_TIMESTAMPS_JSON, DEFAULT_TIMESTAMP_JSON_STRING);
            this.mLatestDedupeTimestampsJson = getSavedTimestampJson(KEY_LATEST_DEDUPE_TIMESTAMPS_JSON, this.mLatestUploadTimestampsJson.toString());
            return;
        }
        throw new IllegalArgumentException("SettingsStore cannot be null");
    }

    public static synchronized CrashTimestampUtil getInstance(SettingsStore settingsStore) {
        CrashTimestampUtil crashTimestampUtil;
        synchronized (CrashTimestampUtil.class) {
            if (sCrashTimestampUtil == null) {
                sCrashTimestampUtil = new CrashTimestampUtil(settingsStore);
            }
            crashTimestampUtil = sCrashTimestampUtil;
        }
        return crashTimestampUtil;
    }

    private JSONObject getSavedTimestampJson(SettingsStore.SettingsKey settingsKey, String str) {
        String string = this.mSettingsStore.getString(settingsKey, str);
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            DPLogger dPLogger = log;
            dPLogger.warn("getSavedTimestampJson", "Illegal JSON stored in key " + settingsKey + RealTimeTextConstants.COLON_SPACE + string + ". Ignoring.", e);
            return new JSONObject();
        }
    }

    public long getLastTimestamp(String str, String str2) {
        if (str != null) {
            if (CrashManagerActions.BUILD_MAP.equals(str2)) {
                return this.mLatestDedupeTimestampsJson.optLong(str, 0L);
            }
            return this.mLatestUploadTimestampsJson.optLong(str, 0L);
        }
        throw new NullPointerException("The tag may not be null");
    }

    public void saveBuildMapIndex() {
        this.mSettingsStore.saveString(KEY_LATEST_DEDUPE_TIMESTAMPS_JSON, this.mLatestDedupeTimestampsJson.toString());
    }

    public void saveCurrentIndex() {
        this.mSettingsStore.saveString(KEY_LATEST_UPLOAD_TIMESTAMPS_JSON, this.mLatestUploadTimestampsJson.toString());
    }

    public void updateLastTimestamp(String str, String str2, long j) {
        if (str != null) {
            try {
                if (CrashManagerActions.BUILD_MAP.equals(str2)) {
                    this.mLatestDedupeTimestampsJson.put(str, j);
                } else {
                    this.mLatestUploadTimestampsJson.put(str, j);
                }
                return;
            } catch (JSONException e) {
                log.fatal("updateLastTimestamp", "Unable to update timestamp!", "tag", str, Settings.ListeningSettings.EXTRA_REASON, str2, e);
                return;
            }
        }
        throw new NullPointerException("The tag may not be null");
    }
}
