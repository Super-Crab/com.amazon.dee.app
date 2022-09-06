package com.amazon.identity.auth.device;

import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Base64;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class fl {
    public static String a(PackageInfo packageInfo) {
        ArrayList<String> b = b(packageInfo);
        if (b.isEmpty()) {
            return null;
        }
        return b.get(0);
    }

    private static ArrayList<String> b(PackageInfo packageInfo) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            for (Signature signature : packageInfo.signatures) {
                String t = t(packageInfo.packageName, signature.toCharsString());
                if (t != null) {
                    arrayList.add(String.format("%s", t));
                }
            }
        } catch (Exception e) {
            io.e("AppSmsSignatureHelper", "Unable to calculate hash.", e);
        }
        return arrayList;
    }

    private static String t(String str, String str2) {
        String outline75 = GeneratedOutlineSupport1.outline75(str, " ", str2);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(outline75.getBytes(Charset.forName("UTF-8")));
            String substring = Base64.encodeToString(Arrays.copyOfRange(messageDigest.digest(), 0, 9), 3).substring(0, 11);
            String.format("pkg: %s -- hash: %s", str, substring);
            io.dm("AppSmsSignatureHelper");
            return substring;
        } catch (NoSuchAlgorithmException e) {
            io.e("AppSmsSignatureHelper", "hash:NoSuchAlgorithm", e);
            return null;
        }
    }
}
