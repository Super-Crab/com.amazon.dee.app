package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.whispercloak.KeyUtils;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.concurrent.ExecutionException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class il {
    private static final String[] rl = {"-----BEGIN RSA PRIVATE KEY-----", "-----END RSA PRIVATE KEY-----", "-----BEGIN EC PRIVATE KEY-----", "-----END EC PRIVATE KEY-----", "-----BEGIN PRIVATE KEY-----", "-----END PRIVATE KEY-----"};
    private static final String TAG = il.class.getName();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        protected KeyFactory getInstance(String str, String str2) throws NoSuchProviderException, NoSuchAlgorithmException {
            return KeyFactory.getInstance(str, str2);
        }

        protected KeyFactory getInstance(String str) throws NoSuchAlgorithmException {
            return KeyFactory.getInstance(str);
        }
    }

    private il() {
    }

    static KeyFactory a(String str, a aVar) {
        if (!TextUtils.isEmpty(str)) {
            if (Build.VERSION.SDK_INT >= 28) {
                String str2 = TAG;
                io.i(str2, "KeyFactory at Android version " + Build.VERSION.SDK_INT);
                try {
                    return aVar.getInstance(str);
                } catch (Exception e) {
                    String str3 = TAG;
                    io.e(str3, "getKeyFactory: Could not get private key because there was no " + str + " algorithm", e);
                    mq.b("MAPKeyFactoryGenerationError:DefaultProviderNotSupportAlgorithm:Algorithm:" + str + ":SystemVersion:" + Build.VERSION.SDK_INT, new String[0]);
                    return null;
                }
            }
            try {
                return aVar.getInstance(str, BouncyCastleProvider.PROVIDER_NAME);
            } catch (NoSuchAlgorithmException e2) {
                String str4 = TAG;
                io.e(str4, "getKeyFactory: Could not get private key because there was no " + str + " algorithm", e2);
                mq.b("MAPKeyFactoryGenerationError:BouncyCastleMissing:Algorithm:" + str + ":SystemVersion:" + Build.VERSION.SDK_INT, new String[0]);
                try {
                    return aVar.getInstance(str);
                } catch (NoSuchAlgorithmException e3) {
                    io.e(TAG, "getKeyFactory: Could not get private key because there was no RSA algorithm", e3);
                    mq.b("MAPKeyFactoryGenerationError:DefaultProviderNotSupportAlgorithm:Algorithm:" + str + ":SystemVersion:" + Build.VERSION.SDK_INT, new String[0]);
                    return null;
                }
            } catch (NoSuchProviderException e4) {
                io.e(TAG, "The device doesn't contain BouncyCastle Provider, try using the default.", e4);
                mq.b("MAPKeyFactoryGenerationError:MissingBouncyCastle:Algorithm:" + str + ":SystemVersion:" + Build.VERSION.SDK_INT, new String[0]);
                try {
                    return aVar.getInstance(str);
                } catch (NoSuchAlgorithmException e5) {
                    io.e(TAG, "getKeyFactory: Could not get private key because there was no RSA algorithm", e5);
                    mq.b("MAPKeyFactoryGenerationError:DefaultProviderNotSupportAlgorithm:Algorithm:" + str + ":SystemVersion:" + Build.VERSION.SDK_INT, new String[0]);
                    return null;
                }
            }
        }
        io.e(TAG, "The algorithm cannot be null");
        throw new IllegalArgumentException("The algorithm cannot be null");
    }

    public static KeyFactory dj(String str) {
        return a(str, new a());
    }

    public static String dk(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : rl) {
            str = str.replace(str2, "");
        }
        return str.trim();
    }

    public static PrivateKey getPrivateKey(String str) throws InvalidKeySpecException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        KeyFactory dj = dj(KeyUtils.ALGORITHM_RSA);
        if (dj == null) {
            io.w(TAG, "Failed to create keyFactory for the input key");
            return null;
        }
        String str2 = TAG;
        new StringBuilder("Key Factory created using the algorithm as ").append(dj.getAlgorithm());
        io.dm(str2);
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(dk(str).getBytes(), 0));
        try {
            return dj.generatePrivate(pKCS8EncodedKeySpec);
        } catch (InvalidKeySpecException e) {
            io.dm(TAG);
            try {
                return dj(KeyUtils.ALGORITHM_EC).generatePrivate(pKCS8EncodedKeySpec);
            } catch (InvalidKeySpecException e2) {
                String str3 = TAG;
                io.e(str3, "Failed to create private key using the original algo: " + dj.getAlgorithm(), e);
                io.e(TAG, "Failed to create private key when retried using algo: EC", e2);
                throw e;
            }
        }
    }

    public static void u(Context context, String str) {
        if (!TextUtils.equals(str, "SSO Currently does not have credentials") || mz.aY(context)) {
            return;
        }
        String c = iw.c(context, DeviceAttribute.CentralDeviceType);
        io.e(TAG, "Central DMS token or DMS private key get corrupted, MAP is going to deregister device to clean the state");
        mq.b("DMSCredentialCorrupted:DeviceType:" + c + ":SYSTEM_VERSION:" + Build.VERSION.SDK_INT, new String[0]);
        try {
            new MAPAccountManager(context).deregisterDevice(null).get();
        } catch (MAPCallbackErrorException e) {
            Bundle errorBundle = e.getErrorBundle();
            String str2 = TAG;
            io.e(str2, "Error deregister the device when DMS private-key/DMS token got corrupted or missing. Error code:" + errorBundle.getInt("com.amazon.dcp.sso.ErrorCode") + " Error message is:" + errorBundle.getString("com.amazon.dcp.sso.ErrorMessage"));
        } catch (InterruptedException e2) {
            io.e(TAG, "InterruptedException! Error deregister the device when DMS private-key/DMS token got corrupted or missing. ", e2);
        } catch (ExecutionException e3) {
            io.e(TAG, "ExecutionException! Error deregister the device when DMS private-key/DMS token got corrupted or missing.", e3);
        }
        io.i(TAG, "Successfully deregister the device when DMS private-key/DMS token got corrupted or missing.");
    }
}
