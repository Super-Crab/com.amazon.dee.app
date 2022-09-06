package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public class ResponseRouterImpl implements ResponseRouter {
    private static final DPLogger log = new DPLogger("TComm.ResponseRouterImpl");
    private final AtomicInteger mLastAllocatedEphemeralChannel = new AtomicInteger(1048576);
    private final Map<Integer, ServiceSideResponseHandler> mResponseHandlerMap = GeneratedOutlineSupport1.outline136();

    @Override // com.amazon.communication.ResponseRouter
    public int registerResponseHandler(ServiceSideResponseHandler serviceSideResponseHandler) {
        this.mLastAllocatedEphemeralChannel.compareAndSet(Integer.MAX_VALUE, 1048576);
        int incrementAndGet = this.mLastAllocatedEphemeralChannel.incrementAndGet();
        log.verbose("registerResponseHandler", "registering response handler", "handler", serviceSideResponseHandler, "channel", Integer.valueOf(incrementAndGet));
        this.mResponseHandlerMap.put(Integer.valueOf(incrementAndGet), serviceSideResponseHandler);
        return incrementAndGet;
    }

    @Override // com.amazon.communication.ResponseRouter
    public void routeResponse(EndpointIdentity endpointIdentity, Message message, int i) {
        ServiceSideResponseHandler serviceSideResponseHandler = this.mResponseHandlerMap.get(Integer.valueOf(i));
        if (serviceSideResponseHandler == null) {
            log.warn("routeResponse", "no response handler is found for channel", "channel", Integer.valueOf(i));
        } else {
            serviceSideResponseHandler.onResponse(endpointIdentity, message, i);
        }
    }

    @Override // com.amazon.communication.ResponseRouter
    public void unregisterResponseHandler(int i) {
        this.mResponseHandlerMap.remove(Integer.valueOf(i));
    }
}
