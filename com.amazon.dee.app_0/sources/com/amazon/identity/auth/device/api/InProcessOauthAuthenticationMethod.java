package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.fp;
import com.amazon.identity.auth.device.hw;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.jj;
import com.amazon.identity.auth.device.jp;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class InProcessOauthAuthenticationMethod extends AuthenticationMethod {
    private static final String TAG = "com.amazon.identity.auth.device.api.InProcessOauthAuthenticationMethod";
    private static final long ge = jj.d(2, TimeUnit.MILLISECONDS);
    private final TokenManagement au;
    private final String bm;

    public InProcessOauthAuthenticationMethod(Context context, String str, String str2, AuthenticationType authenticationType) {
        super(context, str, authenticationType);
        this.au = (TokenManagement) this.o.getSystemService("dcp_token_mangement");
        this.bm = str2;
    }

    @Override // com.amazon.identity.auth.device.api.AuthenticationMethod
    protected MAPFuture<Bundle> getAuthenticationBundle(Uri uri, String str, Map map, byte[] bArr, bl blVar) throws IOException {
        Bundle bundle = null;
        try {
            String scheme = uri.getScheme();
            if (scheme != null && !"https".equals(scheme.toLowerCase(Locale.US))) {
                AuthenticationMethod.a(blVar, 3, "OAuth authentication has to be over https", null);
                io.w(TAG, "OAuth authentication has to be over https");
                return blVar;
            }
            String value = this.au.getValue(this.bP, TokenKeys.getAccessTokenKeyForPackage(this.bm), null, ge);
            if (value == null) {
                AuthenticationMethod.a(blVar, 2, "Could not authenticate request because we could not get an access token", null);
                io.e(TAG, "Could not authenticate request because we could not get an access token");
                return blVar;
            }
            Bundle bundle2 = new Bundle();
            jp.b(bundle2, "x-amz-access-token", value);
            if (blVar != null) {
                blVar.onSuccess(bundle2);
            }
            return blVar;
        } catch (MAPCallbackErrorException e) {
            Bundle errorBundle = e.getErrorBundle();
            if (errorBundle != null) {
                Bundle bundle3 = errorBundle.getBundle("com.amazon.identity.mobi.account.recover.context");
                if (bundle3 != null) {
                    bundle = fp.E(bundle3).eC();
                    io.c(TAG, "Received an error when calling getAtzAccessToken. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(errorBundle.getInt("com.amazon.dcp.sso.ErrorCode", -1)), errorBundle.getString("com.amazon.dcp.sso.ErrorMessage"));
                } else {
                    String str2 = TAG;
                    io.e(str2, "Getting Access Token failed because of callback error. Error Bundle: " + hw.M(errorBundle));
                }
            } else {
                io.e(TAG, "Getting Access Token failed because of callback error. No error bundle");
            }
            AuthenticationMethod.a(blVar, 6, "Getting Access Token failed because of callback error. Error Bundle: " + hw.M(errorBundle), bundle);
            return blVar;
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            AuthenticationMethod.a(blVar, 6, "Getting Access Token failed because of InterruptedException. This can happen if the caller kills the thread or asnc task that is calling MAP's api. Exception message: " + e2.getMessage(), null);
            String str3 = TAG;
            io.e(str3, "Getting Access Token failed because of InterruptedException. This can happen if the caller kills the thread or asnc task that is calling MAP's api. Exception message: " + e2.getMessage(), e2);
            return blVar;
        } catch (ExecutionException e3) {
            AuthenticationMethod.a(blVar, 6, "Getting Access Token failed failed because of ExecutionException. This can happen when the thread or task was aborted. Exception message: " + e3.getMessage(), null);
            String str4 = TAG;
            io.e(str4, "Getting Access Token failed failed because of ExecutionException. This can happen when the thread or task was aborted. Exception message: " + e3.getMessage(), e3);
            return blVar;
        } catch (TimeoutException e4) {
            AuthenticationMethod.a(blVar, 6, "Getting Access Token failed because of TimeoutException. This happens when the timeout passed into the future object occurs. Exception message: " + e4.getMessage(), null);
            String str5 = TAG;
            io.e(str5, "Getting Access Token failed because of TimeoutException. This happens when the timeout passed into the future object occurs. Exception message: " + e4.getMessage(), e4);
            return blVar;
        }
    }
}
