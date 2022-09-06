package com.amazon.identity.auth.device.token;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.eh;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.fs;
import com.amazon.identity.auth.device.gp;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.jg;
import com.amazon.identity.auth.device.jj;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.storage.KeystoreProvider;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MobileAuthEncryptionKeyManager {
    private final eh F;
    private final ed o;
    private static final long pt = jj.b(180, TimeUnit.MILLISECONDS);
    private static final long pn = jj.c(1, TimeUnit.MILLISECONDS);
    private static final String TAG = MobileAuthEncryptionKeyManager.class.getName();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class a {
        final String nf;
        final String pu;
        final long pv;
        final long pw;

        public a(String str, String str2, long j, long j2) {
            this.pu = str;
            this.nf = str2;
            this.pv = j;
            this.pw = j2;
        }
    }

    public MobileAuthEncryptionKeyManager(Context context) {
        this.o = ed.M(context);
        this.F = (eh) this.o.getSystemService("dcp_system");
    }

    private String t(String str, String str2, String str3) {
        return String.format("%s%s%s", str, str3, str2);
    }

    public boolean c(String str, String str2, ej ejVar) throws MobileAuthEncryptionKeyManagerException {
        int i;
        int i2;
        int i3;
        KeystoreProvider keystoreProvider;
        gp l;
        JSONObject jSONObject;
        int i4 = Build.VERSION.SDK_INT;
        try {
            keystoreProvider = new KeystoreProvider(t("mobile_auth_storage", str, "_"));
            l = gp.l(this.o, "mobile_auth_storage");
            SecretKey fm = keystoreProvider.fm();
            String cs = l.cs(t(AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_IDENTIFIER, str, "."));
            if (fm != null && !jg.dE(cs)) {
                if (!((l.cv(String.format("%s.%s", AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_CREATION_TIME, str)) + pt) + pn <= this.F.currentTimeMillis())) {
                    return false;
                }
            }
            jSONObject = new fs(this.o, str, str2, cs).c(ejVar).na;
        } catch (KeystoreProvider.KeystoreProviderException e) {
            e = e;
            i3 = 1;
        } catch (JSONException e2) {
            e = e2;
            i2 = 1;
        } catch (Exception e3) {
            e = e3;
            i = 1;
        }
        try {
            a aVar = new a(jSONObject.getString("encryptionKey"), jSONObject.getString("keyIdentifier"), Long.parseLong(jSONObject.getJSONObject("keyMetadata").getString("keyVersion")), Long.parseLong(jSONObject.getJSONObject("keyMetadata").getString("creationTime")));
            byte[] decode = Base64.decode(aVar.pu, 0);
            keystoreProvider.a(new SecretKeySpec(decode, 0, decode.length, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM));
            l.U(t(AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_IDENTIFIER, str, "."), aVar.nf);
            l.a(t(AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_VERSION, str, "."), aVar.pv);
            l.a(t(AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_CREATION_TIME, str, "."), aVar.pw);
            ejVar.bA("MOBILE_AUTH_UPSERT_ENCRYPTION_KEY");
            return true;
        } catch (KeystoreProvider.KeystoreProviderException e4) {
            e = e4;
            i3 = 1;
            Object[] objArr = new Object[i3];
            objArr[0] = e.getErrorMessage();
            String format = String.format("KeystoreProviderException encountered while creating or updating encryption key. %s", objArr);
            io.e(TAG, format, e);
            mq.incrementCounterAndRecord("MOBILE_AUTH_UPSERT_ENCRYPTION_KEY:KeystoreProviderException", new String[0]);
            throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INTERNAL_ERROR, format, e);
        } catch (JSONException e5) {
            e = e5;
            i2 = 1;
            Object[] objArr2 = new Object[i2];
            objArr2[0] = e.getMessage();
            String format2 = String.format("JSONException encountered while parsing MobileAuthEncryptionKey response. %s", objArr2);
            io.e(TAG, format2, e);
            mq.incrementCounterAndRecord("MOBILE_AUTH_UPSERT_ENCRYPTION_KEY:JSONException", new String[0]);
            throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INVALID_RESPONSE, format2, e);
        } catch (Exception e6) {
            e = e6;
            i = 1;
            Object[] objArr3 = new Object[i];
            objArr3[0] = e.getMessage();
            String format3 = String.format("Exception encountered while creating or updating encryption key. %s", objArr3);
            io.e(TAG, format3, e);
            mq.incrementCounterAndRecord("MOBILE_AUTH_UPSERT_ENCRYPTION_KEY:Exception", new String[0]);
            throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INTERNAL_ERROR, format3, e);
        }
    }

    public Bundle e(String str, ej ejVar) throws MobileAuthEncryptionKeyManagerException {
        int i = Build.VERSION.SDK_INT;
        try {
            KeystoreProvider keystoreProvider = new KeystoreProvider(t("mobile_auth_storage", str, "_"));
            gp l = gp.l(this.o, "mobile_auth_storage");
            SecretKey fm = keystoreProvider.fm();
            String cs = l.cs(t(AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_IDENTIFIER, str, "."));
            if (fm != null && !jg.dE(cs)) {
                ejVar.bA("MOBILE_AUTH_GET_ENCRYPTION_KEY");
                Bundle bundle = new Bundle();
                bundle.putSerializable("value_key", fm);
                bundle.putString(TokenKeys.Options.KEY_MOBILE_AUTH_ENCRYPTION_KEY_ID, cs);
                return bundle;
            }
            if (this.o.dV() != null) {
                if (this.o.dV().D(str)) {
                    io.e(TAG, "Null/Invalid encryption key or key identifier received.");
                    mq.incrementCounterAndRecord("MOBILE_AUTH_GET_ENCRYPTION_KEY:KeyNotFoundException", new String[0]);
                    throw new MobileAuthEncryptionKeyManagerException(MAPError.AccountError.ACCOUNT_ENCRYPTION_KEY_NOT_FOUND, "Null/Invalid encryption key or key identifier received.");
                }
                io.e(TAG, "Account already deregistered. So, no encryption key or key identifier received.");
                mq.incrementCounterAndRecord("MOBILE_AUTH_GET_ENCRYPTION_KEY:AccountDeregistered", new String[0]);
                throw new MobileAuthEncryptionKeyManagerException(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, "Account already deregistered. So, no encryption key or key identifier received.");
            }
            io.e(TAG, "MAP data storage is null/invalid.");
            mq.incrementCounterAndRecord("MOBILE_AUTH_GET_ENCRYPTION_KEY:InvalidMAPDataStorage", new String[0]);
            throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INTERNAL_ERROR, "MAP data storage is null/invalid.");
        } catch (KeystoreProvider.KeystoreProviderException e) {
            String format = String.format("KeystoreProviderException encountered while fetching encryption key. %s", e.getErrorMessage());
            io.e(TAG, format, e);
            mq.incrementCounterAndRecord("MOBILE_AUTH_GET_ENCRYPTION_KEY:KeystoreProviderException", new String[0]);
            throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INTERNAL_ERROR, format, e);
        } catch (MobileAuthEncryptionKeyManagerException e2) {
            throw e2;
        } catch (Exception e3) {
            String format2 = String.format("Exception encountered while fetching encryption key. %s", e3.getMessage());
            io.e(TAG, format2, e3);
            mq.incrementCounterAndRecord("MOBILE_AUTH_GET_ENCRYPTION_KEY:Exception", new String[0]);
            throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INTERNAL_ERROR, format2, e3);
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class MobileAuthEncryptionKeyManagerException extends Exception {
        private static final long serialVersionUID = -7354549861193710767L;
        private final MAPError mError;
        private final String mErrorMessage;

        public MobileAuthEncryptionKeyManagerException(MAPError mAPError, String str) {
            super(str);
            this.mError = mAPError;
            this.mErrorMessage = str;
        }

        public MAPError getError() {
            return this.mError;
        }

        public String getErrorMessage() {
            return this.mErrorMessage;
        }

        public MobileAuthEncryptionKeyManagerException(MAPError mAPError, String str, Throwable th) {
            super(th.getMessage(), th);
            this.mError = mAPError;
            this.mErrorMessage = str;
        }
    }
}
