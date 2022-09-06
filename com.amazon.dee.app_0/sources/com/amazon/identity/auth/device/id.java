package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.amazon.identity.platform.setting.PlatformSettings;
import java.security.MessageDigest;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class id {
    private static final ConcurrentHashMap<String, Boolean> rd = new ConcurrentHashMap<>();
    private static volatile boolean re = false;

    public static void at(Context context) {
        re = PlatformSettings.aU(context).f("enable.debugging.logs", false).booleanValue();
    }

    public static void df(String str) {
        if (!re) {
            return;
        }
        String dg = dg(Log.getStackTraceString(new Throwable()));
        Boolean put = rd.put(dg, Boolean.TRUE);
        StringBuilder sb = new StringBuilder("IMP is calling ");
        sb.append(str);
        sb.append(" with trace with hash ");
        sb.append(dg);
        io.dm("MAP_OUT_BINDER");
        if (put != null && put.booleanValue()) {
            return;
        }
        "Stack trace hash with ".concat(String.valueOf(dg));
        io.dn("HASH_STACK_TRACE");
    }

    private static String dg(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(str.getBytes());
            return Base64.encodeToString(messageDigest.digest(), 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
