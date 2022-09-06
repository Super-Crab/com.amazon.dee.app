package com.amazon.communication.authentication;

import amazon.communication.MissingCredentialsException;
import amazon.communication.authentication.AccountRequestContext;
import amazon.communication.authentication.RequestContext;
import amazon.communication.authentication.SigningException;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import com.amazon.dp.logger.DPFormattedMessage;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.dee.app.metrics.MetricsConstants;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes12.dex */
public abstract class AbstractDcpRequestSigner implements RequestSigner {
    private static final int TIMEOUT_MS = 1000;
    protected final Context mContext;
    protected final MAPAccountManager mMapAccountManager;
    private static final DPLogger log = new DPLogger("TComm.AbstractDcpRequestSigner");
    protected static final byte[] EMPTY_BODY = new byte[0];

    public AbstractDcpRequestSigner(Context context) {
        this(context, new MAPAccountManager(context));
    }

    protected void callAuthenticationService(HttpRequestBase httpRequestBase, String str, Uri uri, byte[] bArr, RequestContext requestContext) throws SigningException, MissingCredentialsException {
        AccountRequestContext accountRequestContext;
        log.debug("callAuthenticationService", "signing request", new Object[0]);
        if (requestContext == null) {
            accountRequestContext = null;
        } else if (requestContext instanceof AccountRequestContext) {
            accountRequestContext = (AccountRequestContext) requestContext;
        } else {
            throw new SigningException("The RequestContext must be an AccountRequestContext");
        }
        String amazonAccount = getAmazonAccount(accountRequestContext);
        String method = httpRequestBase.getMethod();
        Uri sanitizeUriForDcp = DcpUriSanitizer.sanitizeUriForDcp(uri);
        log.debug("callAuthenticationService", "Signing request", "authType", str, MetricsConstants.NativeFetch.METHOD, method, "body.length", Integer.valueOf(bArr.length));
        try {
            Bundle bundle = new AuthenticationMethodFactory(this.mContext, amazonAccount).newAuthenticationMethod(str).getAuthenticationHeadersForRequest(sanitizeUriForDcp, httpRequestBase.getMethod(), Collections.emptyMap(), bArr, null).get(1000L, TimeUnit.MILLISECONDS).getBundle(AuthenticationMethod.KEY_AUTH_HEADERS);
            for (String str2 : bundle.keySet()) {
                log.debug("signRequest", "transferring header to request", "key", str2, "value", bundle.getString(str2));
                httpRequestBase.setHeader(str2, bundle.getString(str2));
            }
        } catch (MAPCallbackErrorException e) {
            throw new SigningException(DPFormattedMessage.toDPFormat("callAuthenticationService", "MAPError signing request", "authType", str, MetricsConstants.NativeFetch.METHOD, method, "body.length", Integer.valueOf(bArr.length)), e);
        } catch (InterruptedException e2) {
            throw new SigningException(DPFormattedMessage.toDPFormat("callAuthenticationService", "Interrupted getting authentication result", "authType", str, MetricsConstants.NativeFetch.METHOD, method, "body.length", Integer.valueOf(bArr.length), "timeoutInMs", 1000), e2);
        } catch (ExecutionException e3) {
            throw new SigningException(DPFormattedMessage.toDPFormat("callAuthenticationService", "ExecutionException getting authentication result", "authType", str, MetricsConstants.NativeFetch.METHOD, method, "body.length", Integer.valueOf(bArr.length), "timeoutInMs", 1000), e3);
        } catch (TimeoutException e4) {
            throw new SigningException(DPFormattedMessage.toDPFormat("callAuthenticationService", "timed out getting authentication result", "authType", str, MetricsConstants.NativeFetch.METHOD, method, "body.length", Integer.valueOf(bArr.length), "timeoutInMs", 1000), e4);
        }
    }

    protected void checkAndCallAuthenticationService(HttpRequestBase httpRequestBase, String str, Uri uri, byte[] bArr, RequestContext requestContext) throws SigningException, MissingCredentialsException {
        callAuthenticationService(httpRequestBase, str, uri, bArr, requestContext);
    }

    public void close() {
    }

    protected String getAmazonAccount(AccountRequestContext accountRequestContext) throws MissingCredentialsException {
        if (accountRequestContext != null && accountRequestContext.getDirectedId() != null) {
            return accountRequestContext.getDirectedId();
        }
        if (accountRequestContext != null && AccountRequestContext.EMPTY_ACCOUNT.equals(accountRequestContext)) {
            return this.mMapAccountManager.getAccount();
        }
        String account = this.mMapAccountManager.getAccount();
        if (account == null) {
            throw new MissingCredentialsException("No amazon account found");
        }
        return account;
    }

    @Override // amazon.communication.authentication.RequestSigner
    public abstract void signRequest(HttpRequestBase httpRequestBase) throws SigningException, MissingCredentialsException;

    /* JADX INFO: Access modifiers changed from: protected */
    public void signRequest(HttpRequestBase httpRequestBase, String str, Uri uri, byte[] bArr, RequestContext requestContext) throws SigningException, MissingCredentialsException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            callAuthenticationService(httpRequestBase, str, uri, bArr, requestContext);
            return;
        }
        throw new IllegalStateException("This method cannot be called on the main thread");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateRequest(HttpRequestBase httpRequestBase) {
        if (httpRequestBase != null) {
            return;
        }
        throw new IllegalArgumentException("Request cannot be null");
    }

    protected AbstractDcpRequestSigner(Context context, MAPAccountManager mAPAccountManager) {
        this.mContext = context;
        this.mMapAccountManager = mAPAccountManager;
    }
}
