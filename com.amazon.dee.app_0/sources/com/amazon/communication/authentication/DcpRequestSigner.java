package com.amazon.communication.authentication;

import amazon.communication.MissingCredentialsException;
import amazon.communication.authentication.RequestContext;
import amazon.communication.authentication.SigningException;
import android.content.Context;
import android.net.Uri;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.AuthenticationType;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
/* loaded from: classes12.dex */
public class DcpRequestSigner extends AbstractDcpRequestSigner {
    private static final DPLogger log = new DPLogger("TComm.DcpRequestSigner");
    protected static final String AUTH_TYPE = AuthenticationType.ADPAuthenticator.toString();

    public DcpRequestSigner(Context context) {
        super(context);
    }

    protected byte[] extractBody(HttpRequestBase httpRequestBase) throws SigningException {
        HttpEntity httpEntity;
        byte[] bArr = null;
        if (httpRequestBase instanceof HttpEntityEnclosingRequest) {
            try {
                try {
                    httpEntity = ((HttpEntityEnclosingRequest) httpRequestBase).getEntity();
                    if (httpEntity != null) {
                        try {
                            InputStream content = httpEntity.getContent();
                            byte[] bArr2 = new byte[content.available()];
                            content.read(bArr2);
                            if (!httpEntity.isRepeatable()) {
                                ((HttpEntityEnclosingRequest) httpRequestBase).setEntity(new ByteArrayEntity(bArr2));
                            }
                            bArr = bArr2;
                        } catch (IOException e) {
                            e = e;
                            throw new SigningException("Error getting content from http entity", e);
                        } catch (Throwable th) {
                            th = th;
                            if (httpEntity != null) {
                                try {
                                    httpEntity.consumeContent();
                                } catch (Exception e2) {
                                    log.warn("extractBody", "Error consuming remainder of entity content", e2);
                                }
                            }
                            throw th;
                        }
                    }
                    if (httpEntity != null) {
                        try {
                            httpEntity.consumeContent();
                        } catch (Exception e3) {
                            log.warn("extractBody", "Error consuming remainder of entity content", e3);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    httpEntity = null;
                }
            } catch (IOException e4) {
                e = e4;
            }
        }
        return bArr != null ? bArr : AbstractDcpRequestSigner.EMPTY_BODY;
    }

    @Override // com.amazon.communication.authentication.AbstractDcpRequestSigner, amazon.communication.authentication.RequestSigner
    public void signRequest(HttpRequestBase httpRequestBase) throws SigningException, MissingCredentialsException {
        signRequest(httpRequestBase, null);
    }

    @Override // amazon.communication.authentication.RequestSigner
    public void signRequest(HttpRequestBase httpRequestBase, RequestContext requestContext) throws SigningException, MissingCredentialsException {
        validateRequest(httpRequestBase);
        signRequest(httpRequestBase, AUTH_TYPE, Uri.parse(httpRequestBase.getURI().toString()), extractBody(httpRequestBase), requestContext);
    }
}
