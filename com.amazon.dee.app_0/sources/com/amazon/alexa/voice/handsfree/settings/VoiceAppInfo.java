package com.amazon.alexa.voice.handsfree.settings;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public enum VoiceAppInfo {
    MOTOROLA("com.motorola.motoalexa", "Moto Voice for Alexa", "Moto", true, true),
    QUALCOMM("com.quicinc.voice.activation", "Qualcomm Voice Assist", "Qualcomm", false, false);
    
    public static final String LAUNCH_HANDSFREE_SETTINGS_INTENT_NAME = "com.amazon.alexa.handsfree.SETTINGS";
    @VisibleForTesting
    static final String LGE_MANUFACTURER_NAME = "LGE";
    @VisibleForTesting
    static final String MOTOROLA_MANUFACTURER_NAME = "motorola";
    static final String SONY_MANUFACTURER_NAME = "Sony";
    private final String mAbbreviatedName;
    private final String mAppName;
    private final boolean mHandlesHandsFreeSetup;
    private final String mPackageName;
    private final boolean mShouldPromptCustomerDownload;
    private static final String TAG = VoiceAppInfo.class.getSimpleName();
    static final Map<String, VoiceAppInfo> MANUFACTURER_TO_VOICE_APP_INFO_MAP = new HashMap();

    static {
        MANUFACTURER_TO_VOICE_APP_INFO_MAP.put(MOTOROLA_MANUFACTURER_NAME, MOTOROLA);
        MANUFACTURER_TO_VOICE_APP_INFO_MAP.put(SONY_MANUFACTURER_NAME, QUALCOMM);
        MANUFACTURER_TO_VOICE_APP_INFO_MAP.put(LGE_MANUFACTURER_NAME, QUALCOMM);
    }

    VoiceAppInfo(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull boolean z, @NonNull boolean z2) {
        this.mAppName = str2;
        this.mPackageName = str;
        this.mAbbreviatedName = str3;
        this.mShouldPromptCustomerDownload = z;
        this.mHandlesHandsFreeSetup = z2;
    }

    @Nullable
    public static VoiceAppInfo getVoiceAppInfo(@NonNull String str) {
        return MANUFACTURER_TO_VOICE_APP_INFO_MAP.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String getAbbreviatedName() {
        return this.mAbbreviatedName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String getAppName() {
        return this.mAppName;
    }

    @NonNull
    public String getPackageName() {
        return this.mPackageName;
    }

    @NonNull
    public boolean handlesHandsFreeSetup() {
        return this.mHandlesHandsFreeSetup;
    }

    public boolean isInstalledAndSupportsHandsFreeSetting(@NonNull PackageManager packageManager) {
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(new Intent(LAUNCH_HANDSFREE_SETTINGS_INTENT_NAME), 0)) {
            if (this.mPackageName.equals(resolveInfo.activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public boolean shouldPromptCustomerDownload() {
        return this.mShouldPromptCustomerDownload;
    }
}
