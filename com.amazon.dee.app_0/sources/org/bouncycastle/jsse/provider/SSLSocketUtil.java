package org.bouncycastle.jsse.provider;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class SSLSocketUtil {
    private static final Method getHandshakeSession;
    private static final Method getSSLParameters;
    private static AtomicInteger threadNumber = new AtomicInteger();

    static {
        Method[] methods = ReflectionUtil.getMethods("javax.net.ssl.SSLSocket");
        getHandshakeSession = ReflectionUtil.findMethod(methods, "getHandshakeSession");
        getSSLParameters = ReflectionUtil.findMethod(methods, "getSSLParameters");
    }

    SSLSocketUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect create(ContextData contextData) {
        return new ProvSSLSocketDirect(contextData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect create(ContextData contextData, String str, int i) throws IOException, UnknownHostException {
        return new ProvSSLSocketDirect(contextData, str, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect create(ContextData contextData, String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        return new ProvSSLSocketDirect(contextData, str, i, inetAddress, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect create(ContextData contextData, InetAddress inetAddress, int i) throws IOException {
        return new ProvSSLSocketDirect(contextData, inetAddress, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect create(ContextData contextData, InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return new ProvSSLSocketDirect(contextData, inetAddress, i, inetAddress2, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect create(ContextData contextData, boolean z, boolean z2, ProvSSLParameters provSSLParameters) {
        return new ProvSSLSocketDirect(contextData, z, z2, provSSLParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketWrap create(ContextData contextData, Socket socket, InputStream inputStream, boolean z) throws IOException {
        return new ProvSSLSocketWrap(contextData, socket, inputStream, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketWrap create(ContextData contextData, Socket socket, String str, int i, boolean z) throws IOException {
        return new ProvSSLSocketWrap(contextData, socket, str, i, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void handshakeCompleted(Runnable runnable) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BCJSSE-HandshakeCompleted-");
        outline107.append(threadNumber.getAndIncrement() & Integer.MAX_VALUE);
        new Thread(runnable, outline107.toString()).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCExtendedSSLSession importHandshakeSession(SSLSocket sSLSocket) {
        Method method;
        SSLSession sSLSession;
        if (sSLSocket instanceof BCSSLSocket) {
            return ((BCSSLSocket) sSLSocket).getBCHandshakeSession();
        }
        if (sSLSocket != null && (method = getHandshakeSession) != null && (sSLSession = (SSLSession) ReflectionUtil.invokeGetter(sSLSocket, method)) != null) {
            return SSLSessionUtil.importSSLSession(sSLSession);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCSSLParameters importSSLParameters(SSLSocket sSLSocket) {
        Method method;
        if (sSLSocket instanceof BCSSLSocket) {
            return ((BCSSLSocket) sSLSocket).getParameters();
        }
        if (sSLSocket == null || (method = getSSLParameters) == null) {
            return null;
        }
        SSLParameters sSLParameters = (SSLParameters) ReflectionUtil.invokeGetter(sSLSocket, method);
        if (sSLParameters == null) {
            throw new RuntimeException("SSLSocket.getSSLParameters returned null");
        }
        return SSLParametersUtil.importSSLParameters(sSLParameters);
    }
}
