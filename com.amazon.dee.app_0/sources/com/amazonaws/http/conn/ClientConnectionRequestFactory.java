package com.amazonaws.http.conn;

import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.metrics.AwsSdkMetrics;
import com.amazonaws.metrics.ServiceLatencyProvider;
import com.amazonaws.util.AWSServiceMetrics;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.http.conn.ClientConnectionRequest;
/* loaded from: classes13.dex */
class ClientConnectionRequestFactory {
    private static final Log log = LogFactory.getLog(ClientConnectionRequestFactory.class);
    private static final Class<?>[] INTERFACES = {ClientConnectionRequest.class, Wrapped.class};

    /* loaded from: classes13.dex */
    private static class Handler implements InvocationHandler {
        private final ClientConnectionRequest orig;

        Handler(ClientConnectionRequest clientConnectionRequest) {
            this.orig = clientConnectionRequest;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                if ("getConnection".equals(method.getName())) {
                    ServiceLatencyProvider serviceLatencyProvider = new ServiceLatencyProvider(AWSServiceMetrics.HttpClientGetConnectionTime);
                    Object invoke = method.invoke(this.orig, objArr);
                    AwsSdkMetrics.getServiceMetricCollector().collectLatency(serviceLatencyProvider.endTiming());
                    return invoke;
                }
                return method.invoke(this.orig, objArr);
            } catch (InvocationTargetException e) {
                ClientConnectionRequestFactory.log.debug("", e);
                throw e.getCause();
            }
        }
    }

    ClientConnectionRequestFactory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClientConnectionRequest wrap(ClientConnectionRequest clientConnectionRequest) {
        if (!(clientConnectionRequest instanceof Wrapped)) {
            return (ClientConnectionRequest) Proxy.newProxyInstance(ClientConnectionRequestFactory.class.getClassLoader(), INTERFACES, new Handler(clientConnectionRequest));
        }
        throw new IllegalArgumentException();
    }
}
