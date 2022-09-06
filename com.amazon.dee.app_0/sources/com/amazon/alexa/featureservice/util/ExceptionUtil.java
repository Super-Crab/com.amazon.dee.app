package com.amazon.alexa.featureservice.util;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.StreamResetException;
/* loaded from: classes7.dex */
public final class ExceptionUtil {
    private ExceptionUtil() {
    }

    private static boolean isInstanceOf(Throwable th, @NonNull Class cls) {
        return th.toString().contains(cls.getCanonicalName());
    }

    @VisibleForTesting
    public static boolean isNonActionableHTTPError(Exception exc) {
        if (exc.getSuppressed() == null || exc.getSuppressed().length <= 0) {
            return false;
        }
        Throwable th = exc.getSuppressed()[0];
        return isInstanceOf(th, SocketTimeoutException.class) || isInstanceOf(th, SSLHandshakeException.class) || isInstanceOf(th, SSLException.class) || isInstanceOf(th, ConnectException.class) || isInstanceOf(th, SSLPeerUnverifiedException.class) || isInstanceOf(th, IOException.class) || isInstanceOf(th, UnknownHostException.class) || isInstanceOf(th, SSLProtocolException.class) || isInstanceOf(th, SocketException.class) || isInstanceOf(th, NoRouteToHostException.class) || isInstanceOf(th, BindException.class) || isInstanceOf(th, ProtocolException.class) || isInstanceOf(th, ConnectionShutdownException.class) || isInstanceOf(th, StreamResetException.class);
    }
}
