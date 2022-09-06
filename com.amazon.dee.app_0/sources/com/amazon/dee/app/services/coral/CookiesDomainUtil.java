package com.amazon.dee.app.services.coral;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nullable;
/* loaded from: classes12.dex */
public final class CookiesDomainUtil {
    private static final String DOMAIN_PATTERN = "\\.amazon\\..+";
    private static final String TAG = "CookiesDomainUtil";

    private CookiesDomainUtil() {
    }

    @Nullable
    public static String getCookiesDomain(@NonNull String str) {
        String str2 = null;
        try {
            String host = new URL(str).getHost();
            String substring = (TextUtils.isEmpty(host) || !host.contains(".")) ? null : host.substring(host.indexOf("."));
            if (substring != null) {
                try {
                    if (!substring.matches(DOMAIN_PATTERN)) {
                        String str3 = TAG;
                        Log.i(str3, substring + " isn't a valid cookie domain, returning null.");
                        return null;
                    }
                } catch (MalformedURLException unused) {
                    str2 = substring;
                    GeneratedOutlineSupport1.outline158("MalformedURLException: ", str);
                    return str2;
                }
            }
            return substring;
        } catch (MalformedURLException unused2) {
        }
    }
}
