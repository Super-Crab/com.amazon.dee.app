package com.amazon.identity.auth.device.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes12.dex */
public class ThirdPartyResourceParser {
    private static final String API_KEY_FILE = "api_key.txt";
    public static final String KEY_API_KEY = "APIKey";
    public static final String KEY_API_KEY_OLD = "AmazonAPIKey";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.utils.ThirdPartyResourceParser";
    public static final String UTF8_BYTE_ORDER_MARK = "\ufeff";
    private static final String UTF_8 = "UTF-8";
    private final String _apiKey = parseApiKey();
    private final Context _context;
    private final String _packageName;

    public ThirdPartyResourceParser(Context context, String str) {
        this._packageName = str;
        this._context = context;
    }

    private String getStringValueFromMetaData(String str) {
        if (this._context != null) {
            MAPLog.i(LOG_TAG, "Attempting to parse API Key from meta data in Android manifest");
            try {
                ApplicationInfo applicationInfo = this._context.getPackageManager().getApplicationInfo(this._packageName, 128);
                if (applicationInfo.metaData == null) {
                    return null;
                }
                return applicationInfo.metaData.getString(str);
            } catch (PackageManager.NameNotFoundException e) {
                String str2 = LOG_TAG;
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("(key=", str, ") ");
                outline115.append(e.getMessage());
                MAPLog.w(str2, outline115.toString());
                return null;
            }
        }
        return null;
    }

    private String parseApiKey() {
        Throwable th;
        InputStream inputStream;
        Context context = this._context;
        try {
            if (context != null) {
                try {
                    inputStream = context.getPackageManager().getResourcesForApplication(getPackageName()).getAssets().open(getApiKeyFile());
                    try {
                        MAPLog.i(LOG_TAG, "Attempting to parse API Key from assets directory");
                        String readString = readString(inputStream);
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        return readString;
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = null;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to get api key asset document: ");
            outline107.append(e.getMessage());
            MAPLog.i(str, outline107.toString());
        } catch (IOException e2) {
            String str2 = LOG_TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unable to get api key asset document: ");
            outline1072.append(e2.getMessage());
            MAPLog.i(str2, outline1072.toString());
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0093 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.String readString(java.io.InputStream r9) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.utils.ThirdPartyResourceParser.readString(java.io.InputStream):java.lang.String");
    }

    public String getApiKey() {
        if (!isApiKeyInAssest()) {
            MAPLog.w(LOG_TAG, "Unable to get API Key from Assests");
            String stringValueFromMetaData = getStringValueFromMetaData(KEY_API_KEY);
            return stringValueFromMetaData != null ? stringValueFromMetaData : getStringValueFromMetaData(KEY_API_KEY_OLD);
        }
        return this._apiKey;
    }

    protected String getApiKeyFile() {
        return API_KEY_FILE;
    }

    protected String getPackageName() {
        return this._packageName;
    }

    public boolean isApiKeyInAssest() {
        return this._apiKey != null;
    }
}
