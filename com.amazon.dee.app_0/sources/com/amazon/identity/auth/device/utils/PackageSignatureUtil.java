package com.amazon.identity.auth.device.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class PackageSignatureUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String LOG_TAG = "com.amazon.identity.auth.device.utils.PackageSignatureUtil";

    public static List<String> getAllSignaturesFor(String str, HashAlgorithm hashAlgorithm, Context context) {
        ArrayList arrayList = new ArrayList();
        Signature[] androidSignaturesFor = getAndroidSignaturesFor(str, context);
        if (androidSignaturesFor == null) {
            MAPLog.d(LOG_TAG, " appSignature is null. pkg=" + str);
            return arrayList;
        }
        String str2 = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("num sigs = ");
        outline107.append(androidSignaturesFor.length);
        MAPLog.i(str2, outline107.toString());
        for (Signature signature : androidSignaturesFor) {
            String str3 = null;
            try {
                str3 = getSignatureFingerprint(signature, hashAlgorithm);
                arrayList.add(str3.toLowerCase(Locale.US));
            } catch (Exception e) {
                MAPLog.e(LOG_TAG, "Encountered error while finding signatures for " + str, e);
            }
            MAPLog.pii(LOG_TAG, "Fingerprint checking", "fingerprint = " + str3);
        }
        return arrayList;
    }

    private static Signature[] getAndroidSignaturesFor(String str, Context context) {
        PackageInfo packageInfo;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            MAPLog.d(LOG_TAG, "Can't find app signatures as pkgMgr is null ");
            return null;
        }
        try {
            packageInfo = packageManager.getPackageInfo(str, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            String str2 = LOG_TAG;
            MAPLog.d(str2, "packageName not found for package " + str);
            packageInfo = null;
        }
        if (packageInfo == null) {
            MAPLog.d(LOG_TAG, "Can't find app signatures as pkgMgr is null ");
            return null;
        }
        return packageInfo.signatures;
    }

    private static byte[] getFingerprint(HashAlgorithm hashAlgorithm, byte[] bArr) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(hashAlgorithm.getAlgorithmName()).digest(bArr);
    }

    public static String getSignatureFingerprint(Signature signature, HashAlgorithm hashAlgorithm) throws IOException, CertificateException, NoSuchAlgorithmException {
        return MAPUtils.toHexString(getFingerprint(hashAlgorithm, SignatureUtil.getCertificateFromByteArray(signature.toByteArray()).getEncoded()));
    }
}
