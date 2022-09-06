package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.token.MAPCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ia {
    private static final String TAG = "com.amazon.identity.auth.device.ia";

    private ia() {
    }

    public static MAPCookie an(String str, String str2) {
        String substring;
        StringTokenizer stringTokenizer = new StringTokenizer(str.trim(), ";");
        boolean z = false;
        boolean z2 = false;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            int indexOf = trim.indexOf(Config.Compare.EQUAL_TO);
            if (indexOf == -1) {
                substring = null;
            } else {
                String substring2 = trim.substring(0, indexOf);
                substring = trim.substring(indexOf + 1);
                trim = substring2;
            }
            if (trim.equalsIgnoreCase("domain")) {
                str5 = substring;
            } else if (trim.equalsIgnoreCase("Expires")) {
                str6 = substring;
            } else if (trim.equalsIgnoreCase(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_PATH)) {
                str7 = substring;
            } else if (trim.equalsIgnoreCase(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_SECURE)) {
                z = true;
            } else if (trim.equalsIgnoreCase(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_HTTP_ONLY)) {
                z2 = true;
            } else {
                str3 = trim;
                str4 = substring;
            }
        }
        return new MAPCookie(str3, str4, str5, str6, str7, str2, z, z2);
    }

    public static List<MAPCookie> ao(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        if (str.startsWith("[")) {
            return aq(str, str2);
        }
        return ap(str, str2);
    }

    @Deprecated
    public static List<MAPCookie> ap(String str, String str2) {
        String[] strArr;
        ArrayList arrayList = new ArrayList();
        Bundle ds = iu.ds(str);
        if (ds == null) {
            return arrayList;
        }
        try {
            strArr = ds.getStringArray(CookieKeys.KEY_COOKIES);
        } catch (Exception e) {
            io.w(TAG, "Failed to deserialize parcel", e);
            strArr = null;
        }
        if (strArr == null) {
            return arrayList;
        }
        for (String str3 : strArr) {
            arrayList.add(an(str3, str2));
        }
        return arrayList;
    }

    public static List<MAPCookie> aq(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(an(jSONArray.getString(i), str2));
            }
            return arrayList;
        } catch (JSONException e) {
            io.w(TAG, "Failed to parse cookies", e);
            return arrayList;
        }
    }

    public static String dd(String str) throws MalformedURLException {
        String host = new URL(str).getHost();
        if (!TextUtils.isEmpty(host)) {
            return "." + host.trim();
        }
        throw new MalformedURLException("Host of the url is null");
    }

    public static String de(String str) throws MalformedURLException {
        return hr.bb(new URL(str).getHost());
    }

    public static List<MAPCookie> e(@NonNull Bundle bundle, String str) {
        ArrayList arrayList = new ArrayList();
        if (bundle.containsKey(CookieKeys.KEY_COOKIES)) {
            try {
                String[] stringArray = bundle.getStringArray(CookieKeys.KEY_COOKIES);
                if (stringArray == null) {
                    return arrayList;
                }
                for (String str2 : stringArray) {
                    arrayList.add(an(str2, str));
                }
            } catch (Exception e) {
                io.w(TAG, "Failed to deserialize parcel", e);
            }
        }
        return arrayList;
    }

    public static SimpleDateFormat gy() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy kk:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    public static String gz() {
        Calendar calendar = Calendar.getInstance();
        Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(1, 30);
        return gy().format(calendar.getTime());
    }

    @Deprecated
    public static String h(List<MAPCookie> list) {
        return iu.O(i(list));
    }

    public static Bundle i(List<MAPCookie> list) {
        Bundle bundle = new Bundle();
        bundle.putStringArray(CookieKeys.KEY_COOKIES, k(list));
        return bundle;
    }

    public static String j(List<MAPCookie> list) {
        JSONArray jSONArray = new JSONArray();
        String[] k = k(list);
        if (k != null) {
            for (String str : k) {
                jSONArray.put(str);
            }
        }
        return jSONArray.toString();
    }

    public static String[] k(List<MAPCookie> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (MAPCookie mAPCookie : list) {
                arrayList.add(mAPCookie.fO());
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        return new String[0];
    }
}
