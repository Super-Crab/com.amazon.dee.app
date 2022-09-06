package com.amazon.alexa.utils.security;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/* loaded from: classes10.dex */
public class SignatureVerifier {
    static final String DEBUG_FINGERPRINT = "7cac391937981b6134bdce1fd9834c253181f5abf91ded6078210d0f91ace360";
    private static final String ENG_BUILD_TYPE_VALUE = "eng";
    static final String RELEASE_FINGERPRINT = "2f19adeb284eb36f7f07786152b9a1d14b21653203ad0b04ebbf9c73ab6d7625";
    private static final String TAG = "SignatureVerifier";
    private static final String USER_DEBUG_BUILD_TYPE_VALUE = "userdebug";
    @Nullable
    static List<Properties> cachedFingerPrintProperties;
    private volatile Boolean isDebugApp;
    private final PackageManager packageManager;
    private final a packageSignatureUtil;
    private final String selfPackageName;

    /* loaded from: classes10.dex */
    public enum VerifyResult {
        VERIFIED,
        NOT_VERIFIED,
        UNKNOWN_PACKAGE,
        PACKAGENAME_NOT_FOUND,
        SIGNATURE_COUNT_EXCEEDED,
        INTERNAL_ERROR_VERIFYING
    }

    public SignatureVerifier(@NonNull Context context) {
        this(context, new a());
    }

    SignatureVerifier(@NonNull Context context, @NonNull a aVar) {
        this.packageManager = context.getPackageManager();
        this.selfPackageName = context.getPackageName();
        this.packageSignatureUtil = aVar;
    }

    private boolean verifyFingerPrints(String str, String str2) {
        List<Properties> list = cachedFingerPrintProperties;
        if (list == null || list.isEmpty()) {
            cachedFingerPrintProperties = getTrustedFingerPrints();
        }
        for (Properties properties : cachedFingerPrintProperties) {
            if (str.equals(properties.getProperty(str2))) {
                return true;
            }
        }
        return false;
    }

    public void addAdditionalTrustedFingerPrint() {
        List<Properties> list = cachedFingerPrintProperties;
        if (list == null || list.isEmpty()) {
            cachedFingerPrintProperties = getTrustedFingerPrints();
        }
        if (!cachedFingerPrintProperties.contains(b.c)) {
            Log.i(TAG, "Fingerprint added");
            cachedFingerPrintProperties.add(b.c);
        }
    }

    List<Properties> getTrustedFingerPrints() {
        ArrayList arrayList = new ArrayList();
        if (isSystemBuildDebug()) {
            arrayList.add(b.b);
        }
        arrayList.add(b.a);
        return arrayList;
    }

    boolean isSelfAppDebug() throws PackageManager.NameNotFoundException {
        if (this.isDebugApp == null) {
            this.isDebugApp = Boolean.valueOf(this.packageSignatureUtil.a(this.packageManager.getApplicationInfo(this.selfPackageName, 128)));
        }
        return this.isDebugApp.booleanValue();
    }

    boolean isSystemBuildDebug() {
        return USER_DEBUG_BUILD_TYPE_VALUE.equals(Build.TYPE) || ENG_BUILD_TYPE_VALUE.equals(Build.TYPE);
    }

    public boolean verify(int i) {
        return verifyUid(i) == VerifyResult.VERIFIED;
    }

    public boolean verify(@NonNull String str) {
        return verifyPackage(str) == VerifyResult.VERIFIED;
    }

    public VerifyResult verifyPackage(@NonNull String str) {
        GeneratedOutlineSupport1.outline158("Verifying signature of package: ", str);
        try {
            if (str.equals(this.selfPackageName)) {
                return VerifyResult.VERIFIED;
            }
            PackageInfo packageInfo = this.packageManager.getPackageInfo(str, 64);
            if (packageInfo.signatures.length != 1) {
                return VerifyResult.SIGNATURE_COUNT_EXCEEDED;
            }
            String a = this.packageSignatureUtil.a(packageInfo.signatures[0]);
            boolean verifyFingerPrints = verifyFingerPrints(a, str);
            if (!verifyFingerPrints && !(verifyFingerPrints = a.equals(RELEASE_FINGERPRINT)) && isSelfAppDebug()) {
                verifyFingerPrints = a.equals(DEBUG_FINGERPRINT);
            }
            return verifyFingerPrints ? VerifyResult.VERIFIED : VerifyResult.NOT_VERIFIED;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "Failed to find other package info to verify signatures");
            return VerifyResult.PACKAGENAME_NOT_FOUND;
        } catch (IOException e) {
            e = e;
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Couldn't verify signature "), TAG);
            return VerifyResult.INTERNAL_ERROR_VERIFYING;
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Couldn't verify signature "), TAG);
            return VerifyResult.INTERNAL_ERROR_VERIFYING;
        } catch (CertificateException e3) {
            e = e3;
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Couldn't verify signature "), TAG);
            return VerifyResult.INTERNAL_ERROR_VERIFYING;
        }
    }

    public VerifyResult verifyUid(int i) {
        GeneratedOutlineSupport1.outline149("Verifying signature of callingUid: ", i);
        String nameForUid = this.packageManager.getNameForUid(i);
        String str = "Resolved callingUid(" + i + ") to package: " + nameForUid;
        return nameForUid == null ? VerifyResult.UNKNOWN_PACKAGE : verifyPackage(nameForUid);
    }
}
