package com.amazon.alexa.accessory.notificationpublisher.servicerequest;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class CookieHelper {
    private static final String TAG = "CookieHelper";

    private CookieHelper() {
    }

    private static String createSendableCookies(@NonNull String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            String[] split = str.split(";");
            if (split.length >= 1) {
                sb.append(split[0]);
                sb.append(';');
                sb.append(Chars.SPACE);
            } else {
                Log.e(TAG, "Cookie was malformed");
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getRefreshedCookies(String str) throws RxBlockingCallException {
        try {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                MetricsRecorder.getInstance().recordCounterWithDebounce("FocusFilter_blocking_call_run_on_main_thread_getRefreshedCookies");
                Log.w(TAG, "getRefreshedCookies is running on main thread");
            }
            UserIdentity lastOrDefault = DependencyProvider.getIdentityService().refresh(TAG).toBlocking().lastOrDefault(null);
            if (lastOrDefault == null) {
                Log.w(TAG, "Failed to force refresh user identity.");
                return str;
            }
            String[] singleOrDefault = DependencyProvider.getIdentityService().getCookiesFromDirectedId(lastOrDefault.getDirectedId(), DependencyProvider.getEnvironmentService().getAuthWebHost()).toBlocking().singleOrDefault(null);
            return singleOrDefault != null ? createSendableCookies(singleOrDefault) : str;
        } catch (Exception e) {
            Log.e(TAG, "getRefreshedCookies - failed with Exception", e);
            MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FocusFilter_getRefreshedCookie_exception_");
            outline107.append(e.getClass().getSimpleName());
            metricsRecorder.recordCounterWithDebounce(outline107.toString(), MetricsRecorder.customAttributesForException(e));
            throw new RxBlockingCallException("getRefreshedCookies failed with Exception", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getValidCookie() throws IllegalArgumentException, RxBlockingCallException {
        String cookie = DependencyProvider.getCookie(DependencyProvider.getEnvironmentService().getWebEndpoint());
        if (isExpiredCookie(cookie)) {
            Log.i(TAG, "getValidCookie - refresh cookie");
            cookie = getRefreshedCookies(cookie);
        }
        if (!Strings.isNullOrEmpty(cookie)) {
            GeneratedOutlineSupport1.outline165("getValidCookie - cookie: ", cookie, TAG);
            return cookie;
        }
        Log.e(TAG, "getValidCookie - failed because of empty or null cookie");
        throw new IllegalArgumentException("Invalid cookie");
    }

    private static String getValueFromCookie(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        for (String str3 : str.split("; ")) {
            String[] split = str3.split(Config.Compare.EQUAL_TO, 2);
            if (split[0].equals(str2)) {
                return split[1].trim();
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isExpiredCookie(String str) {
        GeneratedOutlineSupport1.outline165("isExpiredCookie -- cookie: ", str, TAG);
        return Strings.isNullOrEmpty(getValueFromCookie(str, "at-main")) && Strings.isNullOrEmpty(getValueFromCookie(str, "at-tacbus"));
    }
}
