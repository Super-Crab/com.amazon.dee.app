package com.amazon.identity.auth.device.appid;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.utils.HashAlgorithm;
import com.amazon.identity.auth.device.utils.JWTDecoder;
import com.amazon.identity.auth.device.utils.PackageSignatureUtil;
import com.amazon.identity.auth.device.utils.ThirdPartyResourceParser;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public final class APIKeyDecoder {
    private static final String EXPECTED_ISSUER = "Amazon";
    private static final String FAILED_TO_DECODE = "Failed to decode: ";
    private static final char HASH_SEPARATOR = ':';
    private static final String HTTPS = "https";
    private static final String KEY_API_KEY_VER = "ver";
    private static final String KEY_APP_FAMILY_ID = "appFamilyId";
    private static final String KEY_APP_ID = "appId";
    private static final String KEY_APP_VARIANT_ID = "appVariantId";
    public static final String KEY_AUTHORIZATION_HOST = "authz";
    private static final String KEY_CLIENT_ID = "clientId";
    public static final String KEY_ENDPOINTS = "endpoints";
    public static final String KEY_EXCHANGE_HOST = "tokenExchange";
    private static final String KEY_ISSUER = "iss";
    private static final String KEY_PACKAGE_NAME = "pkg";
    private static final String KEY_PERMISSIONS = "perm";
    private static final String KEY_SCOPES = "scopes";
    private static final String KEY_SIGNATURE_MD5 = "appsig";
    private static final String KEY_SIGNATURE_SHA256 = "appsigSha256";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.appid.APIKeyDecoder";
    private static final String VER_1 = "1";
    private static final String VER_3 = "3";

    private APIKeyDecoder() throws Exception {
        throw new Exception("This class is not instantiable!");
    }

    public static AppInfo decode(String str, String str2, Context context) {
        return doDecode(str, str2, true, context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AppInfo doDecode(String str, String str2, boolean z, Context context) {
        String str3 = LOG_TAG;
        MAPLog.i(str3, "Begin decoding API Key for packageName=" + str);
        JSONObject decode = new JWTDecoder().decode(str2);
        String str4 = LOG_TAG;
        MAPLog.pii(str4, ThirdPartyResourceParser.KEY_API_KEY, "payload=" + decode);
        if (decode == null) {
            String str5 = LOG_TAG;
            MAPLog.w(str5, "Unable to decode APIKey for pkg=" + str);
            return null;
        }
        if (z) {
            try {
                verifyPayload(str, decode, context);
            } catch (PackageManager.NameNotFoundException e) {
                String str6 = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
                outline107.append(e.getMessage());
                MAPLog.w(str6, outline107.toString());
                String str7 = LOG_TAG;
                MAPLog.w(str7, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (AuthError e2) {
                String str8 = LOG_TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
                outline1072.append(e2.getMessage());
                MAPLog.w(str8, outline1072.toString());
                String str72 = LOG_TAG;
                MAPLog.w(str72, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (IOException e3) {
                String str9 = LOG_TAG;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
                outline1073.append(e3.getMessage());
                MAPLog.w(str9, outline1073.toString());
                String str722 = LOG_TAG;
                MAPLog.w(str722, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (SecurityException e4) {
                String str10 = LOG_TAG;
                StringBuilder outline1074 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
                outline1074.append(e4.getMessage());
                MAPLog.w(str10, outline1074.toString());
                String str7222 = LOG_TAG;
                MAPLog.w(str7222, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (NoSuchAlgorithmException e5) {
                String str11 = LOG_TAG;
                StringBuilder outline1075 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
                outline1075.append(e5.getMessage());
                MAPLog.w(str11, outline1075.toString());
                String str72222 = LOG_TAG;
                MAPLog.w(str72222, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (CertificateException e6) {
                String str12 = LOG_TAG;
                StringBuilder outline1076 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
                outline1076.append(e6.getMessage());
                MAPLog.w(str12, outline1076.toString());
                String str722222 = LOG_TAG;
                MAPLog.w(str722222, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (JSONException e7) {
                String str13 = LOG_TAG;
                StringBuilder outline1077 = GeneratedOutlineSupport1.outline107(FAILED_TO_DECODE);
                outline1077.append(e7.getMessage());
                MAPLog.w(str13, outline1077.toString());
                String str7222222 = LOG_TAG;
                MAPLog.w(str7222222, "Unable to decode APIKey for pkg=" + str);
                return null;
            }
        }
        return extractAppInfo(decode);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(1:3)(1:33)|4|(3:16|17|(2:19|(1:(7:30|7|8|9|10|11|12)(2:28|29))(2:23|24)))|6|7|8|9|10|11|12) */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0090, code lost:
        com.amazon.identity.auth.map.device.utils.MAPLog.w(com.amazon.identity.auth.device.appid.APIKeyDecoder.LOG_TAG, "APIKey does not contain a client id");
        r9 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.amazon.identity.auth.device.dataobject.AppInfo extractAppInfo(org.json.JSONObject r13) throws org.json.JSONException, com.amazon.identity.auth.device.AuthError {
        /*
            java.lang.String r0 = "ver"
            java.lang.String r0 = r13.getString(r0)
            java.lang.String r1 = "1"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L18
            java.lang.String r1 = "appId"
            java.lang.String r1 = r13.getString(r1)
            r4 = r1
            r5 = r4
            goto L26
        L18:
            java.lang.String r1 = "appFamilyId"
            java.lang.String r1 = r13.getString(r1)
            java.lang.String r2 = "appVariantId"
            java.lang.String r2 = r13.getString(r2)
            r4 = r1
            r5 = r2
        L26:
            java.lang.String r1 = "3"
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 == 0) goto L78
            java.lang.String r0 = "endpoints"
            org.json.JSONObject r0 = r13.getJSONObject(r0)     // Catch: org.json.JSONException -> L36
            goto L3e
        L36:
            java.lang.String r0 = com.amazon.identity.auth.device.appid.APIKeyDecoder.LOG_TAG
            java.lang.String r2 = "APIKey does not contain endpoints object"
            com.amazon.identity.auth.map.device.utils.MAPLog.w(r0, r2)
            r0 = r1
        L3e:
            if (r0 == 0) goto L78
            java.lang.String r2 = "authz"
            java.lang.String r2 = r0.getString(r2)
            java.lang.String r3 = "tokenExchange"
            java.lang.String r0 = r0.getString(r3)
            java.lang.String r3 = "https"
            if (r2 == 0) goto L62
            boolean r6 = r2.startsWith(r3)
            if (r6 == 0) goto L58
            goto L62
        L58:
            com.amazon.identity.auth.device.AuthError r13 = new com.amazon.identity.auth.device.AuthError
            com.amazon.identity.auth.device.AuthError$ERROR_TYPE r0 = com.amazon.identity.auth.device.AuthError.ERROR_TYPE.ERROR_BAD_PARAM
            java.lang.String r1 = "Authorization Host in APIKey is invalid"
            r13.<init>(r1, r0)
            throw r13
        L62:
            if (r0 == 0) goto L75
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L6b
            goto L75
        L6b:
            com.amazon.identity.auth.device.AuthError r13 = new com.amazon.identity.auth.device.AuthError
            com.amazon.identity.auth.device.AuthError$ERROR_TYPE r0 = com.amazon.identity.auth.device.AuthError.ERROR_TYPE.ERROR_BAD_PARAM
            java.lang.String r1 = "Exchange Host in APIKey is invalid"
            r13.<init>(r1, r0)
            throw r13
        L75:
            r11 = r0
            r10 = r2
            goto L7a
        L78:
            r10 = r1
            r11 = r10
        L7a:
            java.lang.String r0 = "pkg"
            java.lang.String r6 = r13.getString(r0)
            java.lang.String r0 = "scopes"
            java.lang.String[] r7 = com.amazon.identity.auth.device.utils.JSONUtils.getStringArray(r13, r0)
            java.lang.String r0 = "clientId"
            java.lang.String r0 = r13.getString(r0)     // Catch: org.json.JSONException -> L90
            r9 = r0
            goto L98
        L90:
            java.lang.String r0 = com.amazon.identity.auth.device.appid.APIKeyDecoder.LOG_TAG
            java.lang.String r2 = "APIKey does not contain a client id"
            com.amazon.identity.auth.map.device.utils.MAPLog.w(r0, r2)
            r9 = r1
        L98:
            java.lang.String r0 = "perm"
            java.lang.String[] r8 = com.amazon.identity.auth.device.utils.JSONUtils.getStringArray(r13, r0)
            com.amazon.identity.auth.device.dataobject.AppInfo r0 = new com.amazon.identity.auth.device.dataobject.AppInfo
            r3 = r0
            r12 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.appid.APIKeyDecoder.extractAppInfo(org.json.JSONObject):com.amazon.identity.auth.device.dataobject.AppInfo");
    }

    private static void verifyPayload(String str, JSONObject jSONObject, Context context) throws SecurityException, JSONException, PackageManager.NameNotFoundException, CertificateException, NoSuchAlgorithmException, IOException {
        String str2 = LOG_TAG;
        MAPLog.i(str2, "verifyPayload for packageName=" + str);
        if (jSONObject.getString(KEY_ISSUER).equals("Amazon")) {
            if (str != null && !str.equals(jSONObject.getString(KEY_PACKAGE_NAME))) {
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Decoding fails: package names don't match! - ", str, " != ");
                outline115.append(jSONObject.getString(KEY_PACKAGE_NAME));
                throw new SecurityException(outline115.toString());
            }
            if (jSONObject.has(KEY_SIGNATURE_MD5)) {
                String string = jSONObject.getString(KEY_SIGNATURE_MD5);
                MAPLog.pii(LOG_TAG, "Validating MD5 signature in API key", String.format("pkg = %s and signature %s", str, string));
                verifySignature(string, str, HashAlgorithm.MD5, context);
            }
            if (!jSONObject.has(KEY_SIGNATURE_SHA256)) {
                return;
            }
            String string2 = jSONObject.getString(KEY_SIGNATURE_SHA256);
            MAPLog.pii(LOG_TAG, "Validating SHA256 signature in API key", String.format("pkg = %s and signature %s", str, string2));
            verifySignature(string2, str, HashAlgorithm.SHA_256, context);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Decoding fails: issuer (");
        outline107.append(jSONObject.getString(KEY_ISSUER));
        outline107.append(") is not = ");
        outline107.append("Amazon");
        throw new SecurityException(GeneratedOutlineSupport1.outline91(outline107, " pkg=", str));
    }

    private static void verifySignature(String str, String str2, HashAlgorithm hashAlgorithm, Context context) {
        if (str != null) {
            String replace = str.replace(":", "");
            List<String> allSignaturesFor = PackageSignatureUtil.getAllSignaturesFor(str2, hashAlgorithm, context);
            String str3 = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of signatures = ");
            outline107.append(allSignaturesFor.size());
            MAPLog.i(str3, outline107.toString());
            MAPLog.pii(LOG_TAG, "Fingerprint checking", allSignaturesFor.toString());
            if (!allSignaturesFor.contains(replace.toLowerCase(Locale.US))) {
                throw new SecurityException(GeneratedOutlineSupport1.outline72("Decoding failed: certificate fingerprint can't be verified! pkg=", str2));
            }
            return;
        }
        String str4 = LOG_TAG;
        MAPLog.d(str4, "App Signature is null. pkg=" + str2);
        throw new SecurityException(GeneratedOutlineSupport1.outline72("Decoding failed: certificate fingerprint can't be verified! pkg=", str2));
    }
}
