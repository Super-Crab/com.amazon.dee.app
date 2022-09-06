package com.amazon.identity.auth.device.utils;

import android.util.Base64;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class JWTDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CHAR_SET = "UTF-8";
    private static final String FAILED_TO_DECODE = "Failed to decode: ";
    private static final String JWT_SPLITTER = "[.]";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.utils.JWTDecoder";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum JWT_SECTION {
        HEADER,
        PAYLOAD,
        SIGNATURE
    }

    private byte[] decodeBase64ToBytes(String str) throws UnsupportedEncodingException {
        return Base64.decode(str.trim().getBytes("UTF-8"), 0);
    }

    private String decodeBase64ToString(String str) throws UnsupportedEncodingException {
        return new String(decodeBase64ToBytes(str), "UTF-8");
    }

    private String[] getTokenParts(String str) {
        if ($assertionsDisabled || str != null) {
            String[] split = str.split(JWT_SPLITTER);
            if (split.length != 3) {
                throw new IllegalArgumentException("Invalid JWT format");
            }
            return split;
        }
        throw new AssertionError();
    }

    private void verifySignature(String[] strArr) throws InvalidKeyException, NoSuchProviderException, SignatureException, NoSuchAlgorithmException, CertificateException, IOException {
        if (verifySignatureWithRsaSha256(decodeBase64ToBytes(strArr[JWT_SECTION.SIGNATURE.ordinal()]), (strArr[JWT_SECTION.HEADER.ordinal()].trim() + "." + strArr[JWT_SECTION.PAYLOAD.ordinal()].trim()).getBytes("UTF-8"), SignatureUtil.getAmazonPublicCertificate())) {
            return;
        }
        throw new SecurityException("Decoding fails: signature mismatch!");
    }

    private boolean verifySignatureWithRsaSha256(byte[] bArr, byte[] bArr2, Certificate certificate) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA", BouncyCastleProvider.PROVIDER_NAME);
        signature.initVerify(certificate);
        signature.update(bArr2);
        return signature.verify(bArr);
    }

    public JSONObject decode(String str) {
        if (str == null) {
            return null;
        }
        try {
            String[] tokenParts = getTokenParts(str.trim());
            verifySignature(tokenParts);
            return new JSONObject(decodeBase64ToString(tokenParts[JWT_SECTION.PAYLOAD.ordinal()]));
        } catch (UnsupportedEncodingException e) {
            String str2 = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
            outline107.append(e.getMessage());
            MAPLog.w(str2, outline107.toString());
            return null;
        } catch (IOException e2) {
            String str3 = LOG_TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
            outline1072.append(e2.getMessage());
            MAPLog.w(str3, outline1072.toString());
            return null;
        } catch (IllegalArgumentException e3) {
            String str4 = LOG_TAG;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
            outline1073.append(e3.getMessage());
            MAPLog.w(str4, outline1073.toString());
            return null;
        } catch (SecurityException e4) {
            String str5 = LOG_TAG;
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
            outline1074.append(e4.getMessage());
            MAPLog.w(str5, outline1074.toString());
            return null;
        } catch (InvalidKeyException e5) {
            String str6 = LOG_TAG;
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
            outline1075.append(e5.getMessage());
            MAPLog.w(str6, outline1075.toString());
            return null;
        } catch (NoSuchAlgorithmException e6) {
            String str7 = LOG_TAG;
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
            outline1076.append(e6.getMessage());
            MAPLog.w(str7, outline1076.toString());
            return null;
        } catch (NoSuchProviderException e7) {
            String str8 = LOG_TAG;
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
            outline1077.append(e7.getMessage());
            MAPLog.w(str8, outline1077.toString());
            return null;
        } catch (SignatureException e8) {
            String str9 = LOG_TAG;
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
            outline1078.append(e8.getMessage());
            MAPLog.w(str9, outline1078.toString());
            return null;
        } catch (CertificateException e9) {
            String str10 = LOG_TAG;
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
            outline1079.append(e9.getMessage());
            MAPLog.w(str10, outline1079.toString());
            return null;
        } catch (JSONException e10) {
            String str11 = LOG_TAG;
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
            outline10710.append(e10.getMessage());
            MAPLog.w(str11, outline10710.toString());
            return null;
        }
    }
}
