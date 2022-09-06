package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.CommsBridgeError;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
/* loaded from: classes12.dex */
class RequestDispatcherImpl implements RequestDispatcher {
    private static final CommsLogger LOG = CommsLogger.getLogger(RequestDispatcherImpl.class);
    private final RequestRegistry mRegistry;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestDispatcherImpl(@NonNull RequestRegistry requestRegistry) {
        this.mRegistry = requestRegistry;
    }

    @Override // com.amazon.commscore.commsbridge.RequestDispatcher
    public void dispatch(@NonNull RequestMessage requestMessage, @NonNull ResponseResolver responseResolver) {
        String name = requestMessage.getName();
        String obj = requestMessage.getPayload() != null ? requestMessage.getPayload().toString() : null;
        RequestHandler<String> requestHandlerByName = this.mRegistry.getRequestHandlerByName(name);
        if (requestHandlerByName == null) {
            String format = String.format("[comms-bridge] No registered handler for request named %s", name);
            LOG.e(format);
            responseResolver.reject(new CommsBridgeError(format));
            return;
        }
        requestHandlerByName.handleRequest(obj, responseResolver);
    }
}
