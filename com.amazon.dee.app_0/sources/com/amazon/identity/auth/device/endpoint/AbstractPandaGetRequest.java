package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.endpoint.PandaResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes12.dex */
public abstract class AbstractPandaGetRequest<T extends PandaResponse> extends AbstractPandaRequest<T> {
    public AbstractPandaGetRequest(Context context, AppInfo appInfo) {
        super(context, appInfo);
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected void consumeEntity() throws IOException {
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected HttpRequestBase initializeRequest(String str) {
        return new HttpGet(str);
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected void setEntity() throws UnsupportedEncodingException {
    }
}
