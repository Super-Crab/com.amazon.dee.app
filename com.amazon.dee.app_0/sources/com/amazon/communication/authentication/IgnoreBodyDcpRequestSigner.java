package com.amazon.communication.authentication;

import amazon.communication.authentication.SigningException;
import android.content.Context;
import com.amazon.dp.logger.DPLogger;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes12.dex */
public class IgnoreBodyDcpRequestSigner extends DcpRequestSigner {
    private static final DPLogger log = new DPLogger("TComm.IgnoreBodyDcpRequestSigner");

    public IgnoreBodyDcpRequestSigner(Context context) {
        super(context);
    }

    @Override // com.amazon.communication.authentication.DcpRequestSigner
    protected byte[] extractBody(HttpRequestBase httpRequestBase) throws SigningException {
        return AbstractDcpRequestSigner.EMPTY_BODY;
    }
}
