package com.amazon.identity.auth.device.appid;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.utils.ThirdPartyResourceParser;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public abstract class AbstractAppIdentifier implements AppIdentifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String LOG_TAG = "com.amazon.identity.auth.device.appid.AbstractAppIdentifier";

    private String getAPIKey(String str, Context context) {
        String str2 = LOG_TAG;
        MAPLog.i(str2, "Finding API Key for " + str);
        return getResourceParser(context, str).getApiKey();
    }

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public abstract String getAppFamilyId(String str, Context context);

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public AppInfo getAppInfo(String str, Context context) {
        String str2 = LOG_TAG;
        MAPLog.i(str2, "getAppInfo : packageName=" + str);
        return getAppInfoFromAPIKey(str, context);
    }

    public AppInfo getAppInfoFromAPIKey(String str, Context context) {
        String str2 = LOG_TAG;
        MAPLog.i(str2, "getAppInfoFromAPIKey : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        return APIKeyDecoder.decode(str, getAPIKey(str, context), context);
    }

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public String getAppLabel(String str, Context context) {
        String str2 = LOG_TAG;
        MAPLog.i(str2, "getAppLabel : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        try {
            return (String) context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(str, 0));
        } catch (PackageManager.NameNotFoundException e) {
            String str3 = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
            outline107.append(e.getMessage());
            MAPLog.e(str3, outline107.toString(), e);
            return null;
        }
    }

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public abstract String getAppVariantId(String str, Context context);

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public abstract String getPackageName(String str, Context context);

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public abstract String getPackageNameByVariant(String str, Context context);

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public abstract String[] getPackageNames(String str, Context context);

    public ThirdPartyResourceParser getResourceParser(Context context, String str) {
        return new ThirdPartyResourceParser(context, str);
    }

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public boolean isAPIKeyValid(Context context) {
        if (context == null) {
            MAPLog.w(LOG_TAG, "context can't be null!");
            return false;
        }
        return isAPIKeyValid(context.getPackageName(), context);
    }

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public boolean isAPIKeyValid(String str, Context context) {
        String str2 = LOG_TAG;
        MAPLog.i(str2, "isAPIKeyValid : packageName=" + str);
        if (str != null) {
            return getAppInfo(str, context) != null;
        }
        MAPLog.w(LOG_TAG, "packageName can't be null!");
        return false;
    }

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public boolean isAPIKeyValid(String str, String str2, Context context) {
        String str3 = LOG_TAG;
        String outline72 = GeneratedOutlineSupport1.outline72("isAPIKeyValid : packageName=", str);
        MAPLog.pii(str3, outline72, "apiKey=" + str2);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return false;
        } else if (str2 != null) {
            return APIKeyDecoder.decode(str, str2, context) != null;
        } else {
            MAPLog.w(LOG_TAG, "apiKey can't be null!");
            return false;
        }
    }
}
