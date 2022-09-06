package com.amazon.identity.auth.device.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.fp;
import com.amazon.identity.auth.device.hp;
import com.amazon.identity.auth.device.hw;
import com.amazon.identity.auth.device.il;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.jj;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class InProcessAdpAuthenticationMethod extends AuthenticationMethod implements ADPCorpusSigningAuthenticationMethod {
    static final String TAG = "com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod";
    private static final long as = jj.d(2, TimeUnit.MILLISECONDS);
    private static final byte[] gc = "\n".getBytes();
    private final TokenManagement au;
    private final String bm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InProcessAdpAuthenticationMethod(Context context, String str, String str2, AuthenticationType authenticationType) {
        super(context, str, authenticationType);
        this.au = (TokenManagement) this.o.getSystemService("dcp_token_mangement");
        this.bm = str2;
    }

    private byte[] b(byte[] bArr, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initSign(privateKey);
            signature.update(bArr);
            return signature.sign();
        } catch (InvalidKeyException e) {
            io.e(TAG, "Request signing failed because it was given an invalid key", e);
            return null;
        } catch (NoSuchAlgorithmException e2) {
            io.e(TAG, "Request signing failed because of No such algorithm found.", e2);
            return null;
        } catch (SignatureException e3) {
            io.e(TAG, "Request signing failed because there was a problem with the signature", e3);
            return null;
        }
    }

    private boolean bh() {
        return AuthenticationType.DeviceAuthenticator.getValue().equals(this.fJ);
    }

    private Bundle getOptions() {
        return GeneratedOutlineSupport1.outline13("ignore.platform.restrictions", true);
    }

    private PrivateKey getPrivateKey() throws BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException {
        try {
            return il.getPrivateKey(this.au.getValue(this.bP, TokenKeys.getPrivateKeyKeyForPackage(this.bm), getOptions(), as));
        } catch (MAPCallbackErrorException e) {
            Bundle errorBundle = e.getErrorBundle();
            Bundle bundle = errorBundle.getBundle("com.amazon.identity.mobi.account.recover.context");
            if (bundle == null) {
                String str = TAG;
                io.e(str, "Getting private key failed because of callback error. Error Bundle: " + hw.M(errorBundle));
                return null;
            }
            io.e(TAG, "Getting private key failed because of BackwardsCompatibleDataStorageException");
            throw new BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException(fp.E(bundle));
        } catch (InterruptedException e2) {
            String str2 = TAG;
            io.e(str2, "Getting private key failed because of InterruptedException: " + e2.getMessage());
            return null;
        } catch (InvalidKeySpecException e3) {
            io.e(TAG, "Could not sign request because there was an invalid key", e3);
            return null;
        } catch (ExecutionException e4) {
            String str3 = TAG;
            io.e(str3, "Getting private key failed because of ExecutionException: " + e4.getMessage());
            return null;
        } catch (TimeoutException e5) {
            String str4 = TAG;
            io.e(str4, "Getting private key failed because of TimeoutException: " + e5.getMessage());
            return null;
        }
    }

    String a(byte[] bArr) throws BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException {
        byte[] b;
        PrivateKey privateKey = getPrivateKey();
        if (privateKey == null) {
            return null;
        }
        if (bh()) {
            b = a(bArr, privateKey);
        } else {
            b = b(bArr, privateKey);
        }
        if (b == null) {
            return null;
        }
        return new String(Base64.encode(b, 2));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005b A[Catch: BackwardsCompatibleDataStorageException -> 0x00ac, TimeoutException -> 0x00cc, ExecutionException -> 0x00f9, InterruptedException -> 0x0125, MAPCallbackErrorException -> 0x0158, TryCatch #2 {MAPCallbackErrorException -> 0x0158, BackwardsCompatibleDataStorageException -> 0x00ac, InterruptedException -> 0x0125, ExecutionException -> 0x00f9, TimeoutException -> 0x00cc, blocks: (B:3:0x0007, B:5:0x001e, B:7:0x0027, B:11:0x003b, B:18:0x005b, B:20:0x0069, B:24:0x007a, B:28:0x0089, B:33:0x0099, B:37:0x00a3, B:39:0x00a8, B:14:0x0042, B:15:0x0051), top: B:59:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0069 A[Catch: BackwardsCompatibleDataStorageException -> 0x00ac, TimeoutException -> 0x00cc, ExecutionException -> 0x00f9, InterruptedException -> 0x0125, MAPCallbackErrorException -> 0x0158, TryCatch #2 {MAPCallbackErrorException -> 0x0158, BackwardsCompatibleDataStorageException -> 0x00ac, InterruptedException -> 0x0125, ExecutionException -> 0x00f9, TimeoutException -> 0x00cc, blocks: (B:3:0x0007, B:5:0x001e, B:7:0x0027, B:11:0x003b, B:18:0x005b, B:20:0x0069, B:24:0x007a, B:28:0x0089, B:33:0x0099, B:37:0x00a3, B:39:0x00a8, B:14:0x0042, B:15:0x0051), top: B:59:0x0007 }] */
    @Override // com.amazon.identity.auth.device.api.AuthenticationMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.amazon.identity.auth.device.api.MAPFuture<android.os.Bundle> getAuthenticationBundle(android.net.Uri r14, java.lang.String r15, java.util.Map r16, byte[] r17, com.amazon.identity.auth.device.bl r18) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod.getAuthenticationBundle(android.net.Uri, java.lang.String, java.util.Map, byte[], com.amazon.identity.auth.device.bl):com.amazon.identity.auth.device.api.MAPFuture");
    }

    protected String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).format(new Date());
    }

    @Override // com.amazon.identity.auth.device.api.ADPCorpusSigningAuthenticationMethod
    public MAPFuture<Bundle> signCorpus(final byte[] bArr, Bundle bundle, Callback callback) {
        final bl blVar = new bl(callback);
        if (bArr == null) {
            hp.b(blVar, TAG);
            return blVar;
        } else if (!TextUtils.isEmpty(this.bP) && this.dY.isAccountRegistered(this.bP)) {
            this.au.getToken(this.bP, TokenKeys.getAdpTokenKeyForPackage(this.bm), getOptions(), new Callback() { // from class: com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod.1
                @Override // com.amazon.identity.auth.device.api.Callback
                public void onError(Bundle bundle2) {
                    io.e(InProcessAdpAuthenticationMethod.TAG, "Unknown error during signCorpus execution.");
                    hp.a(blVar, "Unknown error during signCorpus execution.", bundle2);
                }

                @Override // com.amazon.identity.auth.device.api.Callback
                public void onSuccess(Bundle bundle2) {
                    String str;
                    String string = bundle2.getString("value_key");
                    try {
                        str = InProcessAdpAuthenticationMethod.this.a(bArr);
                    } catch (BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException e) {
                        fp eE = e.eE();
                        if (eE != null) {
                            hp.a(blVar, "The account db is corrupted", eE.eC());
                            return;
                        }
                        str = null;
                    }
                    if (string != null && str != null) {
                        blVar.onSuccess(GeneratedOutlineSupport1.outline12(ADPCorpusSigningAuthenticationMethod.KEY_ADP_AUTH_SIGNATURE, str, "adp_token", string));
                        return;
                    }
                    hp.a(blVar, 2, "The account is not valid or no longer registered.");
                }
            });
            return blVar;
        } else {
            hp.a(blVar, 2, "The account is not valid or no longer registered.");
            return blVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InProcessAdpAuthenticationMethod(Context context, String str, String str2, String str3) {
        super(context, str, str3);
        this.au = (TokenManagement) this.o.getSystemService("dcp_token_mangement");
        this.bm = str2;
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
            io.e(str, "Signing request with old auth failed because of InvalidKeyException: " + e.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e2) {
            String str2 = TAG;
            io.e(str2, "Signing request with old auth failed because of NoSuchAlgorithmException: " + e2.getMessage());
            return null;
        } catch (BadPaddingException e3) {
            String str3 = TAG;
            io.e(str3, "Signing request with old auth failed because of BadPaddingException: " + e3.getMessage());
            return null;
        } catch (IllegalBlockSizeException e4) {
            String str4 = TAG;
            io.e(str4, "Signing request with old auth failed because of IllegalBlockSizeException: " + e4.getMessage());
            return null;
        } catch (NoSuchPaddingException e5) {
            String str5 = TAG;
            io.e(str5, "Signing request with old auth failed because of NoSuchPaddingException: " + e5.getMessage());
            return null;
        }
    }

    private byte[] a(Uri uri, String str, byte[] bArr, String str2, String str3) {
        String str4;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (str == null) {
                io.e(TAG, "No verb specified. Cannot create corpus");
                return null;
            }
            if (uri == null) {
                str4 = null;
            } else if ("BustedIdentityADPAuthenticator".equals(this.fJ)) {
                str4 = uri.toString();
            } else {
                String encodedPath = uri.getEncodedPath();
                if (encodedPath == null) {
                    encodedPath = "";
                }
                if (!encodedPath.startsWith("/")) {
                    encodedPath = "/".concat(encodedPath);
                }
                String encodedQuery = uri.getEncodedQuery();
                if (!TextUtils.isEmpty(encodedQuery)) {
                    str4 = encodedPath + WebConstants.UriConstants.QUESTIONMARK_KEY + encodedQuery;
                } else {
                    str4 = encodedPath;
                }
            }
            if (str4 == null) {
                io.e(TAG, "No path specified. Cannot create corpus");
                return null;
            }
            if ("BustedIdentityADPAuthenticator".equals(this.fJ)) {
                bArr = new byte[0];
            } else if (bArr == null) {
                bArr = new byte[0];
            }
            if (str3 == null) {
                io.e(TAG, "Unable to retrieve ADP token for given account. Cannot generate corpus.");
                return null;
            }
            byteArrayOutputStream.write(str.getBytes());
            byteArrayOutputStream.write(gc);
            byteArrayOutputStream.write(str4.getBytes());
            byteArrayOutputStream.write(gc);
            byteArrayOutputStream.write(str2.getBytes());
            byteArrayOutputStream.write(gc);
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(gc);
            byteArrayOutputStream.write(str3.getBytes());
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            io.e(TAG, "Could not construct a corpus because an IOException occured", e);
            return null;
        }
    }
}
