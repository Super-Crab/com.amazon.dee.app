package com.amazon.deecomms.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CommsSharedPreferences {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsSharedPreferences.class);
    private static final String PREFS_FILE_NAME = "COMMS_SHARED_PREFS";
    private Context mContext;

    @Inject
    public CommsSharedPreferences(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private SharedPreferences getSharedPreferences() {
        return this.mContext.getSharedPreferences(PREFS_FILE_NAME, 0);
    }

    public synchronized void clearAll() {
        LOG.i("Caesar: clearAll");
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences == null) {
            LOG.e("Unable to fetch shared prefs.");
        } else {
            sharedPreferences.edit().clear().apply();
        }
    }

    public synchronized boolean containsKey(@NonNull String str) {
        boolean z;
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences != null) {
            if (sharedPreferences.contains(str)) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized String getString(@NonNull String str, String str2) {
        GeneratedOutlineSupport.outline4("Caesar: getString: key: ", str, LOG);
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences == null) {
            LOG.e("Unable to fetch secured shared preference from context");
            return str2;
        }
        return sharedPreferences.getString(str, str2);
    }

    public void putNonEmptyString(@Nullable String str, @Nullable String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            putString(str, str2);
        } else {
            LOG.i("Caesar: Empty key/value");
        }
    }

    public synchronized void putString(@NonNull String str, @NonNull String str2) {
        GeneratedOutlineSupport.outline4("Caesar: putString: key: ", str, LOG);
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences == null) {
            LOG.e("Unable to fetch secured shared preference from context");
        } else {
            sharedPreferences.edit().putString(str, str2).apply();
        }
    }
}
