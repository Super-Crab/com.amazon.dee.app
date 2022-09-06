package com.amazon.communication.authentication;

import amazon.communication.MissingCredentialsException;
import amazon.communication.authentication.RequestContext;
import amazon.communication.authentication.SigningException;
import android.content.Context;
import android.net.Uri;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.AuthenticationType;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes12.dex */
public class DcpOAuthRequestSigner extends AbstractDcpRequestSigner {
    private static final DPLogger log = new DPLogger("TComm.DcpOAuthRequestSigner");
    protected static final String AUTH_TYPE = AuthenticationType.OAuth.toString();

    public DcpOAuthRequestSigner(Context context) {
        super(context);
    }

    @Override // com.amazon.communication.authentication.AbstractDcpRequestSigner, amazon.communication.authentication.RequestSigner
    public void signRequest(HttpRequestBase httpRequestBase) throws SigningException, MissingCredentialsException {
        validateRequest(httpRequestBase);
        signRequest(httpRequestBase, AUTH_TYPE, Uri.parse(httpRequestBase.getURI().toString()), AbstractDcpRequestSigner.EMPTY_BODY, null);
    }

    @Override // amazon.communication.authentication.RequestSigner
    public void signRequest(HttpRequestBase httpRequestBase, RequestContext requestContext) throws SigningException, MissingCredentialsException {
        if (requestContext == null) {
            signRequest(httpRequestBase);
            return;
        }
        throw new UnsupportedOperationException("DcpOAuthRequestSigner does not support client-provided RequestContext");
    }
}
