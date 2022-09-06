package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.co;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.fp;
import com.amazon.identity.auth.device.hw;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.jp;
import com.amazon.identity.auth.device.js;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mv;
import com.amazon.identity.auth.request.NoCredentialsException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class AuthenticationMethod {
    @FireOsSdk
    public static final String KEY_AUTH_HEADERS = "auth.headers";
    private static final String TAG = "com.amazon.identity.auth.device.api.AuthenticationMethod";
    final co ax;
    final String bP;
    final MAPAccountManager dY;
    final String fJ;
    final ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class GetAuthenticationHeadersForRequestError {
        @FireOsSdk
        public static final int INVALID_ARGUMENT = 3;
        @FireOsSdk
        public static final int IPC_ERROR = 1;
        @FireOsSdk
        public static final String KEY_ERROR_CODE = "error_code_key";
        @FireOsSdk
        public static final String KEY_ERROR_MESSAGE = "error_message_key";
        @FireOsSdk
        public static final int NOT_SUPPORTED = 5;
        @FireOsSdk
        public static final int NO_CREDENTIALS = 2;
        @FireOsSdk
        public static final int PACKAGE_NOT_WHITE_LIST = 4;
        @FireOsSdk
        public static final int UNRECOGNIZED = 6;

        private GetAuthenticationHeadersForRequestError() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthenticationMethod(Context context, String str, AuthenticationType authenticationType) {
        this(context, str, authenticationType.getValue());
    }

    private void be() throws NoCredentialsException {
        String str = this.bP;
        if (str == null) {
            if (!AuthenticationType.OAuth.getValue().equals(this.fJ)) {
                return;
            }
            io.e(TAG, "OAuth does not support anonymous credentials");
            throw new NoCredentialsException("OAuth does not support anonymous credentials");
        } else if (this.dY.isAccountRegistered(str)) {
        } else {
            io.e(TAG, "The account that this AuthenticationMethod with is no longer registered");
            io.a(TAG, this.bP, this.dY.getAccounts());
            throw new NoCredentialsException("The account that this AuthenticationMethod with is no longer registered");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(js jsVar) throws IOException {
        be();
        mv aE = mq.aE(getClass().getSimpleName(), "getAuthenticationBundle");
        Bundle b = b(jsVar);
        aE.stop();
        if (b != null) {
            Map<String, String> P = jp.P(b);
            if (P.size() == 0) {
                io.dm(TAG);
                return;
            }
            for (Map.Entry<String, String> entry : P.entrySet()) {
                if (jsVar.getHeader(entry.getKey()) != null) {
                    String str = TAG;
                    String.format("Overridding header %s because it is needed for authentication", entry.getKey());
                    io.dm(str);
                }
                jsVar.setHeader(entry.getKey(), entry.getValue());
            }
            return;
        }
        throw new AuthenticatedURLConnection.AuthenticationFailureIOException("Cannot authenticate because we received no token, the getToken call failed");
    }

    Bundle b(js jsVar) throws IOException {
        Map<String, List<String>> map;
        byte[] bArr;
        fp.eD();
        if (jsVar != null) {
            Uri uri = jsVar.getUri();
            String httpVerb = jsVar.getHttpVerb();
            if (!AuthenticationType.OAuth.getValue().equals(this.fJ)) {
                Map<String, List<String>> headers = jsVar.getHeaders();
                bArr = jsVar.getBody();
                map = headers;
            } else {
                map = Collections.EMPTY_MAP;
                bArr = new byte[0];
            }
            try {
                MAPFuture<Bundle> authenticationBundle = getAuthenticationBundle(uri, httpVerb, map, bArr, new bl(null));
                if (authenticationBundle != null) {
                    return authenticationBundle.get();
                }
                throw new AuthenticatedURLConnection.AuthenticationFailureIOException("The future result is null!");
            } catch (MAPCallbackErrorException e) {
                Bundle errorBundle = e.getErrorBundle();
                Bundle bundle = errorBundle.getBundle("com.amazon.identity.mobi.account.recover.context");
                if (bundle != null) {
                    io.e(TAG, "Error happened when try to get authentication bundle. Account needs to be recovered.");
                    throw new AuthenticatedURLConnection.AccountNeedsRecoveryException("Error happened when try to get authentication bundle", bundle);
                }
                String str = TAG;
                io.e(str, "Error happened when try to get authentication bundle, the error message is: " + hw.M(errorBundle));
                throw new AuthenticatedURLConnection.AuthenticationFailureIOException("Error happened when try to get authentication bundle");
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                io.e(TAG, "InterruptedException happened when try to get authentication bundle result", e2);
                throw new AuthenticatedURLConnection.AuthenticationFailureIOException("InterruptedException happened when try to get authentication bundle. " + e2.getMessage());
            } catch (ExecutionException e3) {
                io.e(TAG, "ExecutionException happened when try to get authentication bundle result", e3);
                throw new AuthenticatedURLConnection.AuthenticationFailureIOException("ExecutionException happened when try to get authentication bundle. " + e3.getMessage());
            }
        }
        throw new AuthenticatedURLConnection.AuthenticationFailureIOException("The request cannot be null!");
    }

    abstract MAPFuture<Bundle> getAuthenticationBundle(Uri uri, String str, Map map, byte[] bArr, bl blVar) throws IOException;

    @FireOsSdk
    public MAPFuture<Bundle> getAuthenticationHeadersForRequest(Uri uri, String str, Map map, byte[] bArr, Callback callback) {
        bl blVar = new bl(callback);
        if (!a(uri, str, blVar)) {
            return blVar;
        }
        mv aE = mq.aE(getClass().getSimpleName(), "getAuthenticationBundle");
        try {
            try {
                getAuthenticationBundle(uri, str, map, bArr, blVar);
            } catch (IOException unused) {
                io.e(TAG, "Error happened when trying to sign the request components and get the http headers back!");
            }
            return blVar;
        } finally {
            aE.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthenticationMethod(Context context, String str, String str2) {
        if (str2 != null) {
            MAPInit.getInstance(context).initialize();
            this.bP = str;
            this.fJ = str2;
            this.o = ed.M(context);
            this.dY = new MAPAccountManager(this.o);
            this.ax = this.o.dW();
            return;
        }
        throw new IllegalArgumentException("Must Specify an Authentication Type");
    }

    private boolean a(Uri uri, String str, bl blVar) {
        try {
            be();
            if (uri == null) {
                a(blVar, 3, "The serviceUri cannot be null, please check your parameters!", null);
                return false;
            } else if (TextUtils.isEmpty(str)) {
                a(blVar, 3, "The verb of the request cannot be null, please check your parameters!", null);
                return false;
            } else if (TextUtils.isEmpty(this.fJ)) {
                a(blVar, 3, "Please specify an authentication type!", null);
                return false;
            } else {
                AuthenticationType parse = AuthenticationType.parse(this.fJ);
                if (AuthenticationType.ADPAuthenticator.equals(parse) || AuthenticationType.OAuth.equals(parse)) {
                    return true;
                }
                a(blVar, 5, "Currently MAP just support ADP and OAuh authentication type for this API. Please check your authentication type.", null);
                return false;
            }
        } catch (NoCredentialsException unused) {
            a(blVar, 2, "The given directedId does not exist!", null);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(bl blVar, int i, String str, Bundle bundle) {
        if (blVar == null) {
            return;
        }
        io.e(TAG, str);
        blVar.onError(a(i, str, bundle));
    }

    private static Bundle a(int i, String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("error_code_key", i);
        bundle2.putString("error_message_key", str);
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        return bundle2;
    }
}
