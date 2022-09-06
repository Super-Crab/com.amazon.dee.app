package com.amazon.identity.auth.device.interactive;

import com.amazon.identity.auth.device.api.workflow.RequestContext;
import java.util.WeakHashMap;
/* loaded from: classes12.dex */
public final class InteractiveRequestMap {
    private static InteractiveRequestMap INSTANCE;
    private final WeakHashMap<Object, RequestContext> sourceContextMap = new WeakHashMap<>();

    InteractiveRequestMap() {
    }

    public static synchronized InteractiveRequestMap getInstance() {
        InteractiveRequestMap interactiveRequestMap;
        synchronized (InteractiveRequestMap.class) {
            if (INSTANCE == null) {
                INSTANCE = new InteractiveRequestMap();
            }
            interactiveRequestMap = INSTANCE;
        }
        return interactiveRequestMap;
    }

    public RequestContext getRequestContextForSource(Object obj) {
        return this.sourceContextMap.get(obj);
    }

    public void putRequestContextForSource(Object obj, RequestContext requestContext) {
        this.sourceContextMap.put(obj, requestContext);
    }
}
