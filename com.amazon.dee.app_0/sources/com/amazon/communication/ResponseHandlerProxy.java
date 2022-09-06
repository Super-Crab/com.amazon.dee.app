package com.amazon.communication;

import amazon.communication.ResponseHandlerBase;
import com.amazon.communication.IResponseHandler;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class ResponseHandlerProxy extends IResponseHandler.Stub {
    private static final DPLogger log = new DPLogger("TComm.ResponseHandlerProxy");
    private final HttpRequestResponseConverter mHttpRequestResponseConverter;
    private ResponseHandlerBase mResponseHandlerBase;

    public ResponseHandlerProxy(ResponseHandlerBase responseHandlerBase, HttpRequestResponseConverter httpRequestResponseConverter) {
        this.mResponseHandlerBase = responseHandlerBase;
        this.mHttpRequestResponseConverter = httpRequestResponseConverter;
    }

    public ResponseHandlerBase getClientHandler() {
        return this.mResponseHandlerBase;
    }

    @Override // com.amazon.communication.IResponseHandler
    public void onResponse(MessageEnvelope messageEnvelope) {
        try {
            this.mResponseHandlerBase.onResponse(null, this.mHttpRequestResponseConverter.convertMessageToResponse(messageEnvelope.toMessage()), 0);
        } catch (Exception e) {
            log.warn("onResponse", "Caught exception for client on-response callback!", e);
        }
    }
}
