package com.amazon.identity.auth.device;

import java.io.UnsupportedEncodingException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jg {
    public static boolean dD(String str) {
        return str != null && str.length() == 0;
    }

    public static boolean dE(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static byte[] dF(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
