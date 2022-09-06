package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class lk {
    private static final String TAG = "com.amazon.identity.auth.device.lk";
    private static final byte[] ud = new byte[0];
    private final kq uf;
    private String ug;
    private String ue = null;
    private boolean uh = false;
    private boolean ui = false;

    public lk(kq kqVar) {
        this.uf = kqVar;
    }

    /* renamed from: if  reason: not valid java name */
    private void m4070if() {
        if (this.ue == null) {
            this.ue = this.uf.hr();
        }
    }

    public String a(String str, md mdVar) {
        String iy;
        byte[] iD;
        byte[] bArr;
        String a;
        String iB = mdVar.iB();
        if (this.ui) {
            iy = mdVar.getUrl();
        } else {
            iy = mdVar.iy();
            if (iy != null && !iy.startsWith("/")) {
                iy = "/".concat(iy);
            }
        }
        if (this.ui) {
            iD = ud;
        } else {
            iD = mdVar.iD();
        }
        String str2 = this.ug;
        if (str2 == null) {
            str2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).format(new Date());
        }
        if (str == null || iB == null || iy == null || str2 == null) {
            bArr = null;
        } else {
            byte[] dF = jg.dF(iB);
            byte[] dF2 = jg.dF(iy);
            byte[] dF3 = jg.dF(str2);
            int length = iD != null ? iD.length : 0;
            byte[] dF4 = jg.dF(str);
            bArr = new byte[dF.length + 1 + dF2.length + 1 + dF3.length + 1 + length + 1 + dF4.length];
            System.arraycopy(dF, 0, bArr, 0, dF.length);
            int length2 = dF.length + 0;
            bArr[length2] = 10;
            int i = length2 + 1;
            System.arraycopy(dF2, 0, bArr, i, dF2.length);
            int length3 = i + dF2.length;
            bArr[length3] = 10;
            int i2 = length3 + 1;
            System.arraycopy(dF3, 0, bArr, i2, dF3.length);
            int length4 = i2 + dF3.length;
            bArr[length4] = 10;
            int i3 = length4 + 1;
            if (iD != null) {
                System.arraycopy(iD, 0, bArr, i3, iD.length);
                i3 += iD.length;
            }
            bArr[i3] = 10;
            System.arraycopy(dF4, 0, bArr, i3 + 1, dF4.length);
        }
        if (bArr == null || (a = a(bArr)) == null) {
            return null;
        }
        return String.format("%s:%s", a, str2);
    }

    public boolean b(md mdVar) {
        kq kqVar = this.uf;
        if (kqVar != null && mdVar != null) {
            String token = kqVar.y().getToken();
            try {
                String a = a(token, mdVar);
                if (a == null) {
                    return false;
                }
                mdVar.setHeader(ii(), a);
                mdVar.setHeader(ih(), token);
                if (ij() == null) {
                    return true;
                }
                mdVar.setHeader(ij(), ie());
                return true;
            } catch (Exception e) {
                io.e(TAG, "Exception in getting ADP signature.", e);
            }
        }
        return false;
    }

    public String ie() {
        if (this.uh) {
            return null;
        }
        m4070if();
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), this.ue, ":1.0");
    }

    public boolean ig() {
        return this.uh;
    }

    public String ih() {
        return this.uh ? "X-ADP-Authentication-Token" : "x-adp-token";
    }

    public String ii() {
        return this.uh ? "X-ADP-Request-Digest" : "x-adp-signature";
    }

    public String ij() {
        if (this.uh) {
            return null;
        }
        return "x-adp-alg";
    }

    public void l(boolean z) {
        this.uh = z;
        if (this.uh) {
            String str = TAG;
            io.i(str, "Try to set useLegacyAuthentication to be true when algorithm is: " + this.ue);
            if (this.uf == null) {
                return;
            }
            m4070if();
            if (this.ue.equalsIgnoreCase("SHA256WithRSA")) {
                return;
            }
            throw new IllegalStateException("LegacyAuthentication is not compatible with algorithm:" + this.ue);
        }
    }

    private byte[] b(byte[] bArr, PrivateKey privateKey) {
        try {
            m4070if();
            Signature signature = Signature.getInstance(this.ue);
            signature.initSign(privateKey);
            signature.update(bArr);
            return signature.sign();
        } catch (InvalidKeyException e) {
            String str = TAG;
            io.e(str, "signWithNewAuth: failed because of InvalidKeyException: " + e.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e2) {
            String str2 = TAG;
            io.e(str2, "signWithNewAuth: failed because of NoSuchAlgorithmException: " + e2.getMessage());
            return null;
        } catch (SignatureException e3) {
            String str3 = TAG;
            io.e(str3, "signWithNewAuth: failed because of SignatureException: " + e3.getMessage());
            return null;
        }
    }

    public String a(byte[] bArr) {
        byte[] b;
        PrivateKey hs = this.uf.hs();
        if (hs == null) {
            return null;
        }
        if (this.uh) {
            m4070if();
            if (!this.ue.equals("SHA256WithRSA")) {
                String str = TAG;
                io.e(str, "Try to use legacy auth when the algorithm is " + this.ue);
            }
            b = a(bArr, hs);
        } else {
            b = b(bArr, hs);
        }
        if (b == null) {
            return null;
        }
        return Base64.encodeToString(b, 2);
    }

    @SuppressLint({"GetInstance"})
    private static byte[] a(byte[] bArr, PrivateKey privateKey) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, privateKey);
            cipher.update(digest);
            return cipher.doFinal();
        } catch (InvalidKeyException e) {
            String str = TAG;
            io.e(str, "signWithOldAuth: failed because of InvalidKeyException: " + e.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e2) {
            String str2 = TAG;
            io.e(str2, "signWithOldAuth: failed because of NoSuchAlgorithmException: " + e2.getMessage());
            return null;
        } catch (BadPaddingException e3) {
            String str3 = TAG;
            io.e(str3, "signWithOldAuth: failed because of BadPaddingException: " + e3.getMessage());
            return null;
        } catch (IllegalBlockSizeException e4) {
            String str4 = TAG;
            io.e(str4, "signWithOldAuth: failed because of IllegalBlockSizeException: " + e4.getMessage());
            return null;
        } catch (NoSuchPaddingException e5) {
            String str5 = TAG;
            io.e(str5, "signWithOldAuth: failed because of NoSuchPaddingException: " + e5.getMessage());
            return null;
        }
    }
}
