package com.amazon.identity.auth.device.api;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.amazon.dcp.sso.IRequestAuthenticationMethod;
import com.amazon.dcp.sso.ReturnValueOrError;
import com.amazon.identity.auth.device.api.ADPCorpusSigningAuthenticationMethod;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.df;
import com.amazon.identity.auth.device.ef;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.fp;
import com.amazon.identity.auth.device.hp;
import com.amazon.identity.auth.device.hu;
import com.amazon.identity.auth.device.il;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.jp;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mv;
import com.amazon.identity.auth.device.o;
import com.amazon.identity.auth.request.NoCredentialsException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class CentralDcpAuthenticationMethod extends AuthenticationMethod implements ADPCorpusSigningAuthenticationMethod {
    static final String TAG = "com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod";
    static final String fL = "CentralDcpAuthenticationMethod";

    /* JADX INFO: Access modifiers changed from: package-private */
    public CentralDcpAuthenticationMethod(Context context, String str, AuthenticationType authenticationType) {
        super(context, str, authenticationType);
    }

    private MAPFuture<Bundle> a(byte[] bArr, String str, Bundle bundle, Callback callback) {
        aE("You cannot authenticate this corpus on the main thread!");
        bl blVar = new bl(callback);
        if (bArr == null) {
            hp.b(blVar, TAG);
            return blVar;
        }
        String str2 = this.fJ;
        if (!AuthenticationType.ADPAuthenticator.getValue().equals(str2)) {
            String format = String.format("Only support authentication type: %s. Cannot sign the corpus with invalid authentication type: %s", AuthenticationType.ADPAuthenticator.getValue(), str2);
            io.e(TAG, format);
            hp.a(blVar, 3, format);
            return blVar;
        }
        return a(bArr, str, bundle, blVar, callback);
    }

    private void aE(String str) {
        if (Thread.currentThread().getId() != Looper.getMainLooper().getThread().getId()) {
            return;
        }
        throw new IllegalStateException(str);
    }

    @Override // com.amazon.identity.auth.device.api.AuthenticationMethod
    protected MAPFuture<Bundle> getAuthenticationBundle(final Uri uri, final String str, final Map map, final byte[] bArr, bl blVar) throws IOException {
        Bundle bundle;
        aE("You cannot authenticate this request on the main thread!");
        o d = o.d(this.o);
        if (d != null) {
            final AtomicReference atomicReference = new AtomicReference();
            final mv aE = mq.aE(fL, "bind");
            new ef(this.o, d.s()) { // from class: com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod.1
                @Override // com.amazon.identity.auth.device.ef
                public void useService(IBinder iBinder) {
                    ReturnValueOrError returnValueOrError;
                    aE.stop();
                    mv aE2 = mq.aE(CentralDcpAuthenticationMethod.fL, "getAuthenticationParams");
                    try {
                        try {
                            Uri uri2 = uri;
                            String str2 = str;
                            Map map2 = map;
                            byte[] bArr2 = bArr;
                            IRequestAuthenticationMethod asInterface = IRequestAuthenticationMethod.Stub.asInterface(iBinder);
                            String str3 = CentralDcpAuthenticationMethod.this.fJ;
                            String str4 = CentralDcpAuthenticationMethod.this.bP;
                            Map map3 = Collections.EMPTY_MAP;
                            byte[] bArr3 = new byte[0];
                            if (!AuthenticationType.OAuth.getValue().equals(str3)) {
                                map3 = map2;
                                bArr3 = bArr2;
                            }
                            if (CentralDcpAuthenticationMethod.this.ax.a(Feature.DirectedIdSupported)) {
                                returnValueOrError = asInterface.getAuthenticationParametersForRequestByDirectedId(str3, str4, uri2, str2, map3, bArr3);
                            } else {
                                Account o = hu.o(CentralDcpAuthenticationMethod.this.o, str4);
                                returnValueOrError = asInterface.getAuthenticationParametersForRequest(str3, o != null ? o.type : null, o != null ? o.name : null, uri2, str2, map3, bArr3);
                            }
                            io.dm(CentralDcpAuthenticationMethod.TAG);
                        } catch (RemoteException e) {
                            io.e(CentralDcpAuthenticationMethod.TAG, "Could not receive request authentication from dcp!", e);
                            returnValueOrError = new ReturnValueOrError(6, "Connection to DCP has been lost");
                        } catch (RuntimeException e2) {
                            io.e(CentralDcpAuthenticationMethod.TAG, "Unknown error during getAuthenticationBundle execution.", e2);
                            atomicReference.set(new ReturnValueOrError(5, e2.toString()));
                        }
                        atomicReference.set(returnValueOrError);
                        aE2.stop();
                        doneUsingService();
                    } catch (Throwable th) {
                        atomicReference.set(null);
                        aE2.stop();
                        doneUsingService();
                        throw th;
                    }
                }
            }.run(Long.valueOf(jp.rM), TimeUnit.MILLISECONDS, "GetAuthenticationParameters");
            ReturnValueOrError returnValueOrError = (ReturnValueOrError) atomicReference.get();
            fp.eD();
            if (returnValueOrError != null) {
                if (!returnValueOrError.isError()) {
                    Bundle response = returnValueOrError.getResponse();
                    if (blVar != null && response != null) {
                        blVar.onSuccess(response);
                    }
                    return blVar;
                }
                String responseMessage = returnValueOrError.getResponseMessage();
                int responseCode = returnValueOrError.getResponseCode();
                if (responseCode == 2) {
                    Bundle response2 = returnValueOrError.getResponse();
                    if (response2 != null && (bundle = response2.getBundle("com.amazon.identity.mobi.account.recover.context")) != null) {
                        AuthenticationMethod.a(blVar, 2, responseMessage, fp.E(bundle).eC());
                        io.e(TAG, "Account is in bad state, throwing AccountNeedsRecoveryException to help account recovery");
                        throw new AuthenticatedURLConnection.AccountNeedsRecoveryException(responseMessage, bundle);
                    }
                    il.u(this.o, responseMessage);
                    AuthenticationMethod.a(blVar, 2, responseMessage, null);
                    throw new NoCredentialsException(responseMessage);
                } else if (responseCode == 3) {
                    AuthenticationMethod.a(blVar, 4, responseMessage, null);
                    throw new IllegalArgumentException(responseMessage);
                } else if (responseCode == 5) {
                    AuthenticationMethod.a(blVar, 6, responseMessage, null);
                    throw new RuntimeException(responseMessage);
                } else if (responseCode != 6) {
                    AuthenticationMethod.a(blVar, 6, responseMessage, null);
                    throw new IllegalArgumentException(responseMessage);
                } else {
                    AuthenticationMethod.a(blVar, 1, responseMessage, null);
                    throw new AuthenticatedURLConnection.AuthenticationFailureIOException(responseMessage);
                }
            }
            AuthenticationMethod.a(blVar, 1, "Connection to SSO timeout", null);
            throw new AuthenticatedURLConnection.AuthenticationFailureIOException("Connection to SSO timeout");
        }
        AuthenticationMethod.a(blVar, 1, "A Central device credential location cannot be found", null);
        throw new AuthenticatedURLConnection.AuthenticationFailureIOException("A Central device credential location cannot be found");
    }

    @Override // com.amazon.identity.auth.device.api.ADPCorpusSigningAuthenticationMethod
    public MAPFuture<Bundle> signCorpus(byte[] bArr, Bundle bundle, Callback callback) {
        return a(bArr, this.bP, bundle, callback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CentralDcpAuthenticationMethod(Context context, String str, String str2) {
        super(context, str, str2);
    }

    private MAPFuture<Bundle> a(final byte[] bArr, final String str, final Bundle bundle, final bl blVar, final Callback callback) {
        o d = o.d(this.o);
        if (d == null) {
            io.e(TAG, "A Central device credential location cannot be found.");
            hp.a(blVar, 1, "A Central device credential location cannot be found.");
            return blVar;
        }
        final mv aE = mq.aE(fL, "bind");
        if (!new df(this.o, d.s()) { // from class: com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod.2
            @Override // com.amazon.identity.auth.device.df
            public void serviceDisconnected() {
                io.e(CentralDcpAuthenticationMethod.TAG, "AuthenticatedRequestService is disconnected. It's probably crashed.");
                hp.a(blVar, 1, "AuthenticatedRequestService is disconnected. It's probably crashed.");
            }

            @Override // com.amazon.identity.auth.device.df
            public void useService(IBinder iBinder) {
                ReturnValueOrError signCorpus;
                aE.stop();
                mv aE2 = mq.aE(CentralDcpAuthenticationMethod.fL, "signCorpusByDirectedIdWrapper");
                try {
                    try {
                        byte[] bArr2 = bArr;
                        IRequestAuthenticationMethod asInterface = IRequestAuthenticationMethod.Stub.asInterface(iBinder);
                        if (CentralDcpAuthenticationMethod.this.ax.a(Feature.DirectedIdSupported)) {
                            signCorpus = asInterface.signCorpusByDirectedId("SHA256WithRSA", str, bArr2);
                        } else {
                            Account o = hu.o(CentralDcpAuthenticationMethod.this.o, str);
                            String str2 = null;
                            String str3 = o != null ? o.type : null;
                            if (o != null) {
                                str2 = o.name;
                            }
                            signCorpus = asInterface.signCorpus("SHA256WithRSA", str3, str2, bArr2);
                        }
                        io.dm(CentralDcpAuthenticationMethod.TAG);
                        CentralDcpAuthenticationMethod.this.a(signCorpus, blVar, bArr, bundle);
                    } catch (RemoteException e) {
                        io.e(CentralDcpAuthenticationMethod.TAG, "Cannot receive corpus authentication from dcp! Connection to DCP has been lost.", e);
                        hp.a(blVar, 1, "Cannot receive corpus authentication from dcp! Connection to DCP has been lost.");
                    } catch (RuntimeException e2) {
                        io.e(CentralDcpAuthenticationMethod.TAG, "Unknown error during signCorpus execution.", e2);
                        hp.a(blVar, 5, e2.toString());
                    }
                } finally {
                    aE2.stop();
                    unbind();
                }
            }
        }.call()) {
            io.e(TAG, "Failed to bind to AuthenticatedRequestService.");
            hp.a(blVar, 1, "Failed to bind to AuthenticatedRequestService.");
        }
        return blVar;
    }

    void a(ReturnValueOrError returnValueOrError, final bl blVar, byte[] bArr, Bundle bundle) {
        if (returnValueOrError == null) {
            io.e(TAG, "Connection to SSO timeout.");
            hp.a(blVar, 1, "Connection to SSO timeout.");
        } else if (!returnValueOrError.isError()) {
            Bundle response = returnValueOrError.getResponse();
            String string = response.getString("token");
            String string2 = response.getString("digest");
            Bundle bundle2 = new Bundle();
            bundle2.putString("adp_token", string);
            bundle2.putString(ADPCorpusSigningAuthenticationMethod.KEY_ADP_AUTH_SIGNATURE, string2);
            blVar.onSuccess(bundle2);
        } else {
            String responseMessage = returnValueOrError.getResponseMessage();
            int responseCode = returnValueOrError.getResponseCode();
            if (responseCode == 2) {
                if (bundle != null && bundle.getBoolean(ADPCorpusSigningAuthenticationMethod.SignCorpusOption.USE_FALL_BACK_CREDENTIALS, false)) {
                    a(bArr, this.dY.getAccount(), (Bundle) null, new Callback() { // from class: com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod.3
                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onError(Bundle bundle3) {
                            blVar.onError(bundle3);
                        }

                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onSuccess(Bundle bundle3) {
                            blVar.onSuccess(bundle3);
                        }
                    });
                } else {
                    hp.a(blVar, 2, responseMessage);
                }
            } else if (responseCode == 3 || responseCode == 4) {
                hp.a(blVar, 3, responseMessage);
            } else if (responseCode != 6) {
                hp.a(blVar, 5, responseMessage);
            } else {
                hp.a(blVar, 1, responseMessage);
            }
        }
    }
}
