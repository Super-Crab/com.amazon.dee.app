package org.bouncycastle.jsse.provider;

import java.lang.reflect.Method;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSSLEngine;
import org.bouncycastle.jsse.BCSSLParameters;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class SSLEngineUtil {
    private static final Method getHandshakeSession;
    private static final Method getSSLParameters;

    static {
        Method[] methods = ReflectionUtil.getMethods("javax.net.ssl.SSLEngine");
        getHandshakeSession = ReflectionUtil.findMethod(methods, "getHandshakeSession");
        getSSLParameters = ReflectionUtil.findMethod(methods, "getSSLParameters");
    }

    SSLEngineUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SSLEngine create(ContextData contextData) {
        return new ProvSSLEngine(contextData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SSLEngine create(ContextData contextData, String str, int i) {
        return new ProvSSLEngine(contextData, str, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCExtendedSSLSession importHandshakeSession(SSLEngine sSLEngine) {
        Method method;
        SSLSession sSLSession;
        if (sSLEngine instanceof BCSSLEngine) {
            return ((BCSSLEngine) sSLEngine).getBCHandshakeSession();
        }
        if (sSLEngine != null && (method = getHandshakeSession) != null && (sSLSession = (SSLSession) ReflectionUtil.invokeGetter(sSLEngine, method)) != null) {
            return SSLSessionUtil.importSSLSession(sSLSession);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCSSLParameters importSSLParameters(SSLEngine sSLEngine) {
        Method method;
        if (sSLEngine instanceof BCSSLEngine) {
            return ((BCSSLEngine) sSLEngine).getParameters();
        }
        if (sSLEngine == null || (method = getSSLParameters) == null) {
            return null;
        }
        SSLParameters sSLParameters = (SSLParameters) ReflectionUtil.invokeGetter(sSLEngine, method);
        if (sSLParameters == null) {
            throw new RuntimeException("SSLEngine.getSSLParameters returned null");
        }
        return SSLParametersUtil.importSSLParameters(sSLParameters);
    }
}
