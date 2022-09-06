package com.amazon.identity.auth.device;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class mp {
    public static boolean a(Context context, mv mvVar) {
        if (jk.gR()) {
            io.w(SSOMetrics.TAG, "Running in unit test. Returning false");
            return false;
        }
        try {
            if (!aL(context)) {
                mvVar.iK();
                return true;
            }
        } catch (SecurityException e) {
            io.e(SSOMetrics.TAG, "We do not have permission to get the network state. Please make sure the permission android.permission.ACCESS_NETWORK_STATE is included in your manifest", e);
        }
        return false;
    }

    public static boolean aL(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean aM(Context context) {
        try {
            return aL(context);
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static String aN(Context context) {
        try {
            return aL(context) ? DriveModeMetrics.ConnectionStatus.CONNECTED : "NotConnected";
        } catch (SecurityException e) {
            io.e(SSOMetrics.TAG, "We do not have permission to get the network state. Please make sure the permission android.permission.ACCESS_NETWORK_STATE is included in your manifest", e);
            return "NetworkStatusUnknown";
        }
    }

    public static String eQ(String str) {
        try {
            String eR = eR(str);
            URL url = new URL(str);
            return eR + ":" + url.getHost();
        } catch (MalformedURLException unused) {
            return "CannotGetURL";
        }
    }

    public static String eR(String str) {
        return str == null ? "CannotGetURL" : str.contains("/ap/signin") ? "/ap/signin" : str.contains("/ap/forgotpassword") ? "/ap/forgotpassword" : str.contains("/ap/register") ? "/ap/register" : str.contains("/ap/challenge") ? "/ap/challenge" : str.contains("/ap/mfa") ? "/ap/mfa" : str.contains("/ap/dcq") ? "/ap/dcq" : "NonMAPURL";
    }

    public static String eS(String str) {
        return str == null ? "CannotGetURL" : str.contains("getNewDeviceCredentials") ? "getNewDeviceCredentials" : str.contains("registerAssociatedDevice") ? "registerAssociatedDevice" : str.contains("registerDeviceWithToken") ? "registerDeviceWithToken" : str.contains("registerDeviceToSecondaryCustomer") ? "registerDeviceToSecondaryCustomer" : str.contains("registerDevice") ? "registerDevice" : str.contains("renameFiona") ? "renameFiona" : str.contains("disownFiona") ? "disownFiona" : str.contains("/auth/register") ? "/auth/register" : str.contains("/auth/signin") ? "/auth/signin" : str.contains("/ap/exchangetoken") ? "/ap/exchangetoken" : "NonMAPURL";
    }

    public static String h(URL url) {
        return url.getPath() + ":" + url.getHost();
    }

    public static String i(URL url) {
        return url.getPath() + ":" + url.getHost() + ":IOException";
    }

    public static String j(URL url) {
        return h(url) + ":Availability";
    }

    public static String k(URL url) {
        return h(url) + ":SuccessAfterRetry";
    }

    public static String a(URL url, int i) {
        return h(url) + ":" + i;
    }

    public static String a(URL url, int i, String str) {
        if (str == null) {
            return a(url, i);
        }
        return a(url, i) + ":" + str;
    }

    public static String a(URL url, IOException iOException, Context context) {
        return i(url) + ":" + iOException.getClass().getSimpleName() + ":" + aN(context);
    }
}
