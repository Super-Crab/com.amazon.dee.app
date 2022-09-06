package com.amazon.dee.app.services.security;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
public final class DefaultCertificateReaderService implements CertificateReaderService {
    private static final String APPIUM_FINGERPRINT = "61:ED:37:7E:85:D3:86:A8:DF:EE:6B:86:4B:D8:5B:0B:FA:A5:AF:81";
    private static final String DEBUG_FINGERPRINT = "B6:0B:17:79:56:B8:1C:1D:63:53:33:E4:68:8F:02:77:1C:D9:EB:B3";
    private static final String DEVICE_FARM_FINGERPRINT = "8D:2E:22:6C:30:4C:DD:85:05:5D:10:67:08:88:75:8A:A7:97:05:78";
    private static final String TAG = "com.amazon.dee.app.services.security.DefaultCertificateReaderService";
    private final Context appContext;
    private Set<String> debugBuildCerts;
    private Boolean isDebugSigned = null;
    private Boolean isPerformanceProfilingBuild = null;
    private Set<String> performanceProfilingBuildCerts = new HashSet();

    public DefaultCertificateReaderService(Context context) {
        this.appContext = context;
        this.performanceProfilingBuildCerts.add(DEBUG_FINGERPRINT);
        this.performanceProfilingBuildCerts.add(APPIUM_FINGERPRINT);
        this.performanceProfilingBuildCerts.add(DEVICE_FARM_FINGERPRINT);
        this.debugBuildCerts = new HashSet();
        this.debugBuildCerts.add(DEBUG_FINGERPRINT);
    }

    private String byte2HexFormatted(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = GeneratedOutlineSupport1.outline72("0", hexString);
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(JsonReaderKt.COLON);
            }
        }
        return sb.toString();
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private List<String> getCertificateSHA1Fingerprint(Context context) throws GeneralSecurityException, PackageManager.NameNotFoundException {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
            arrayList.add(byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signature.toByteArray()))).getEncoded())));
        }
        return arrayList;
    }

    private boolean hasFingerprint(Set<String> set) {
        try {
            List<String> certificateSHA1Fingerprint = getCertificateSHA1Fingerprint(this.appContext);
            if (certificateSHA1Fingerprint.size() == 0) {
                return false;
            }
            for (String str : certificateSHA1Fingerprint) {
                if (!set.contains(str)) {
                    return false;
                }
            }
            return true;
        } catch (PackageManager.NameNotFoundException | GeneralSecurityException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.security.CertificateReaderService
    public boolean isDebugSigned() {
        Boolean bool = this.isDebugSigned;
        if (bool != null) {
            return bool.booleanValue();
        }
        this.isDebugSigned = Boolean.valueOf(hasFingerprint(this.debugBuildCerts));
        return this.isDebugSigned.booleanValue();
    }

    @Override // com.amazon.dee.app.services.security.CertificateReaderService
    public boolean isPerformanceProfilingBuild() {
        Boolean bool = this.isPerformanceProfilingBuild;
        if (bool != null) {
            return bool.booleanValue();
        }
        this.isPerformanceProfilingBuild = Boolean.valueOf(hasFingerprint(this.performanceProfilingBuildCerts));
        return this.isPerformanceProfilingBuild.booleanValue();
    }
}
