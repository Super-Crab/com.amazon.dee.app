package com.amazon.identity.auth.device;

import android.os.Build;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
/* compiled from: DCP */
/* loaded from: classes12.dex */
final class er {
    private static final boolean lL;

    static {
        int i = Build.VERSION.SDK_INT;
        lL = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Socket socket) {
        if (socket instanceof SSLSocket) {
            a((SSLSocket) socket);
            return;
        }
        throw new SecurityException(String.format("SSLSocket is required, %s is not supported", socket.getClass().getCanonicalName()));
    }

    static void a(SSLSocket sSLSocket) {
        if (lL) {
            String[] supportedProtocols = sSLSocket.getSupportedProtocols();
            String[] strArr = new String[supportedProtocols.length];
            int i = 0;
            for (String str : supportedProtocols) {
                if (str.equals("TLSv1") || str.equals("TLSv1.1") || str.equals("TLSv1.2")) {
                    strArr[i] = str;
                    i++;
                }
            }
            if (i != 0) {
                String[] strArr2 = new String[i];
                System.arraycopy(strArr, 0, strArr2, 0, i);
                sSLSocket.setEnabledProtocols(strArr2);
                return;
            }
            throw new SecurityException("Device does not support TLS");
        }
    }
}
