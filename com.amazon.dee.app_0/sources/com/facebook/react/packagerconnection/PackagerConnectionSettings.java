package com.facebook.react.packagerconnection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
/* loaded from: classes2.dex */
public class PackagerConnectionSettings {
    private static final String PREFS_DEBUG_SERVER_HOST_KEY = "debug_http_host";
    private static final String TAG = "PackagerConnectionSettings";
    private final Context mAppContext;
    private final String mPackageName;
    private final SharedPreferences mPreferences;

    public PackagerConnectionSettings(Context context) {
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPackageName = context.getPackageName();
        this.mAppContext = context;
    }

    public String getDebugServerHost() {
        String string = this.mPreferences.getString(PREFS_DEBUG_SERVER_HOST_KEY, null);
        if (!TextUtils.isEmpty(string)) {
            return (String) Assertions.assertNotNull(string);
        }
        String serverHost = AndroidInfoHelpers.getServerHost(this.mAppContext);
        if (serverHost.equals(AndroidInfoHelpers.DEVICE_LOCALHOST)) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("You seem to be running on device. Run '");
            outline107.append(AndroidInfoHelpers.getAdbReverseTcpCommand(this.mAppContext));
            outline107.append("' to forward the debug server's port to the device.");
            FLog.w(str, outline107.toString());
        }
        return serverHost;
    }

    public String getInspectorServerHost() {
        return AndroidInfoHelpers.getInspectorProxyHost(this.mAppContext);
    }

    @Nullable
    public String getPackageName() {
        return this.mPackageName;
    }

    public void setDebugServerHost(String str) {
        this.mPreferences.edit().putString(PREFS_DEBUG_SERVER_HOST_KEY, str).apply();
    }
}
