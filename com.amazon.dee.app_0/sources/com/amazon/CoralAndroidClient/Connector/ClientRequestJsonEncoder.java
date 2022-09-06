package com.amazon.CoralAndroidClient.Connector;

import com.amazon.CoralAndroidClient.ClientBase.ClientRequest;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.nio.charset.Charset;
/* loaded from: classes.dex */
public final class ClientRequestJsonEncoder implements ClientRequestEncoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Charset mCharset;

    public ClientRequestJsonEncoder(Charset charset) throws NativeException {
        if (charset != null) {
            this.mCharset = charset;
            return;
        }
        throw new NativeException("charset is null.");
    }

    @Override // com.amazon.CoralAndroidClient.Connector.ClientRequestEncoder
    public byte[] encode(ClientRequest clientRequest) throws NativeException {
        if (clientRequest != null) {
            String json = clientRequest.toJson();
            if (json != null) {
                return json.getBytes(this.mCharset);
            }
            throw new NativeException("content body is null");
        }
        throw new NativeException("ClientRequest is null");
    }

    public ClientRequestJsonEncoder(String str) throws NativeException {
        if (!Helper.isStringNullOrEmpty(str)) {
            try {
                this.mCharset = Charset.forName(str);
                return;
            } catch (IllegalArgumentException e) {
                throw new NativeException(e);
            }
        }
        throw new NativeException("charset name is null or empty.");
    }
}
