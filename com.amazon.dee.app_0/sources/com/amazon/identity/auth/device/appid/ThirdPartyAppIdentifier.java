package com.amazon.identity.auth.device.appid;

import android.content.Context;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class ThirdPartyAppIdentifier extends AbstractAppIdentifier {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.appid.ThirdPartyAppIdentifier";
    private static final String REDIRECT_URI_PREFIX = "amzn://";

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public String[] getAllowedScopes(String str, Context context) {
        String str2 = LOG_TAG;
        MAPLog.i(str2, "getAllowedScopes : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        AppInfo appInfo = getAppInfo(str, context);
        if (appInfo != null) {
            return appInfo.getAllowedScopes();
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.appid.AbstractAppIdentifier, com.amazon.identity.auth.device.appid.AppIdentifier
    public String getAppFamilyId(String str, Context context) {
        String str2 = LOG_TAG;
        MAPLog.i(str2, "getAppFamilyId : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        AppInfo appInfo = getAppInfo(str, context);
        if (appInfo != null) {
            return appInfo.getAppFamilyId();
        }
        return null;
    }

    public AppInfo getAppInfoByApiKey(String str, String str2, Context context) {
        String str3 = LOG_TAG;
        MAPLog.i(str3, "getAppInfo : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        return APIKeyDecoder.doDecode(str, str2, false, context);
    }

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public String[] getAppPermissions(String str, Context context) {
        String str2 = LOG_TAG;
        MAPLog.i(str2, "getAppPermissions : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        AppInfo appInfo = getAppInfo(str, context);
        if (appInfo != null) {
            return appInfo.getGrantedPermissions();
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.appid.AbstractAppIdentifier, com.amazon.identity.auth.device.appid.AppIdentifier
    public String getAppVariantId(String str, Context context) {
        String str2 = LOG_TAG;
        MAPLog.i(str2, "getAppVariantId : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        AppInfo appInfo = getAppInfo(str, context);
        if (appInfo != null) {
            return appInfo.getAppVariantId();
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.appid.AbstractAppIdentifier, com.amazon.identity.auth.device.appid.AppIdentifier
    public String getPackageName(String str, Context context) {
        return null;
    }

    @Override // com.amazon.identity.auth.device.appid.AbstractAppIdentifier, com.amazon.identity.auth.device.appid.AppIdentifier
    public String getPackageNameByVariant(String str, Context context) {
        return null;
    }

    @Override // com.amazon.identity.auth.device.appid.AbstractAppIdentifier, com.amazon.identity.auth.device.appid.AppIdentifier
    public String[] getPackageNames(String str, Context context) {
        return null;
    }

    public String getRedirectUrl(Context context) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(REDIRECT_URI_PREFIX);
        outline107.append(context.getPackageName());
        return outline107.toString();
    }

    public boolean isAPIKeyValidFormat(String str, String str2, Context context) {
        String str3 = LOG_TAG;
        String outline72 = GeneratedOutlineSupport1.outline72("isAPIKeyValid : packageName=", str);
        MAPLog.pii(str3, outline72, "apiKey=" + str2);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return false;
        } else if (str2 != null) {
            return APIKeyDecoder.doDecode(str, str2, false, context) != null;
        } else {
            MAPLog.w(LOG_TAG, "apiKey can't be null!");
            return false;
        }
    }
}
