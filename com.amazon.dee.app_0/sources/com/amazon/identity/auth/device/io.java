package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import org.apache.logging.log4j.util.Chars;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class io {
    private static volatile String rn = "";
    private static volatile String ro = "Unknown";

    public static void a(String str, Object... objArr) {
        String format = String.format(str, objArr);
        int i = 0;
        while (i < format.length()) {
            int i2 = i + 1000;
            format.substring(i, i2 > format.length() ? format.length() : i2);
            i = i2;
        }
    }

    public static void b(String str, Object... objArr) {
        String.format(str, objArr);
    }

    public static void c(String str, String str2, Object... objArr) {
        Log.e(dq(str), String.format(str2, objArr));
    }

    public static void dm(String str) {
        dq(str);
    }

    public static void dn(String str) {
        dq(str);
    }

    /* renamed from: do  reason: not valid java name */
    public static String m4069do(String str) {
        if (TextUtils.isEmpty(str)) {
            return "EmptyData";
        }
        if (str.length() < 5) {
            w("MAPLog", "Not masking data as length to keep is longer than the original data.");
            return str;
        }
        return str.substring(0, 5) + "***";
    }

    public static String dp(String str) {
        if (TextUtils.isEmpty(str)) {
            return "No directedId";
        }
        return "***" + str.substring(str.length() - Math.min(2, str.length()));
    }

    private static String dq(String str) {
        String sb;
        if (TextUtils.isEmpty(str)) {
            sb = ro + rn;
        } else {
            StringBuilder sb2 = new StringBuilder();
            GeneratedOutlineSupport1.outline176(sb2, ro, '/', str);
            sb2.append(rn);
            sb = sb2.toString();
        }
        if (sb.length() > 23) {
            return "..." + sb.substring((sb.length() - 23) + 3);
        }
        return sb;
    }

    public static void e(String str, String str2) {
        Log.e(dq(str), str2);
    }

    public static void gC() {
    }

    public static void gD() {
    }

    public static void gE() {
    }

    public static void i(String str, String str2) {
        Log.i(dq(str), str2);
    }

    public static void setPackageName(String str) {
        ro = str;
    }

    public static void w(String str, String str2) {
        Log.w(dq(str), str2);
    }

    public static void wtf(String str, String str2, Throwable th) {
        Log.wtf(dq(str), str2, th);
    }

    public static void b(String str, String str2, Object... objArr) {
        w(str, String.format(str2, objArr));
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(dq(str), str2, th);
    }

    public static void i(String str, String str2, Throwable th) {
        Log.i(dq(str), str2, th);
    }

    public static void w(String str, String str2, Throwable th) {
        Log.w(dq(str), str2, th);
    }

    public static void a(String str, String str2, Object... objArr) {
        i(str, String.format(str2, objArr));
    }

    public static void a(String str, String str2, Collection<String> collection) {
        StringBuilder sb = new StringBuilder("Account not registered: ");
        if (str2 == null) {
            sb.append("null");
        } else {
            a(sb, str2);
        }
        if (collection != null) {
            sb.append(". Registered accounts are:");
            for (String str3 : collection) {
                sb.append(Chars.SPACE);
                a(sb, str3);
            }
        }
        e(str, sb.toString(), new IllegalArgumentException());
    }

    public static void a(String str, ej ejVar, String str2, String str3, Throwable th) {
        e(str, str2, th);
        if (ejVar != null) {
            ejVar.bA(str3);
        } else {
            mq.b(str3, new String[0]);
        }
    }

    public static void a(String str, ej ejVar, String str2, String str3) {
        e(str, str2);
        if (ejVar != null) {
            ejVar.bA(str3);
        } else {
            mq.b(str3, new String[0]);
        }
    }

    private static void a(StringBuilder sb, String str) {
        sb.append("***");
        sb.append(str.substring(str.length() - Math.min(2, str.length())));
    }
}
