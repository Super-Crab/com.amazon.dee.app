package com.amazon.whisperjoin.devicesetupserviceandroidclient.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class SharedPreferencesProvider {
    private static final String PREF_PREFIX = "com.amazon.whisperjoin.devicesetupserviceandroidclient.";
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
            return this.mContext.getSharedPreferences(GeneratedOutlineSupport1.outline72(PREF_PREFIX, str), 0);
        }
        throw new IllegalArgumentException("preferenceName can not be null");
    }
}
