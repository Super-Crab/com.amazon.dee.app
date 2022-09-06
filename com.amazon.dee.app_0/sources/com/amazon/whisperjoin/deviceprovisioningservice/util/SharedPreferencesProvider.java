package com.amazon.whisperjoin.deviceprovisioningservice.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class SharedPreferencesProvider {
    private static final String FFS_PROVISIONING = "FFSProvisioningConfiguration";
    private static final String PREF_PREFIX = "com.amazon.whisperjoin.deviceprovisioningservice.preferences.";
    private static final String TAG = "SharedPreferencesProvider";
    private final Context mContext;

    public SharedPreferencesProvider(Context context) {
        if (context != null) {
            this.mContext = context;
            return;
        }
        throw new IllegalArgumentException("context can not be null");
    }

    public SharedPreferences get(String str) {
        if (str != null) {
            String outline72 = GeneratedOutlineSupport1.outline72(PREF_PREFIX, str);
            String str2 = TAG;
            WJLog.v(str2, "providing shared preferences: " + outline72);
            return this.mContext.getSharedPreferences(outline72, 0);
        }
        throw new IllegalArgumentException("preferenceName can not be null");
    }

    public SharedPreferences getFFSConfigurationPreferences() {
        return get(FFS_PROVISIONING);
    }
}
