package com.amazon.identity.auth.device;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLSocketFactory;
/* compiled from: DCP */
/* loaded from: classes12.dex */
class fa extends SSLSocketFactory {
    private static final String TAG = fa.class.getName();
    private final SSLSocketFactory lP;

    /* JADX INFO: Access modifiers changed from: package-private */
    public fa(SSLSocketFactory sSLSocketFactory) {
        this.lP = sSLSocketFactory;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket createSocket = this.lP.createSocket(socket, str, i, z);
        er.a(createSocket);
        return createSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.lP.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.lP.getSupportedCipherSuites();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        Socket createSocket = this.lP.createSocket(str, i);
        er.a(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        Socket createSocket = this.lP.createSocket(inetAddress, i);
        er.a(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        Socket createSocket = this.lP.createSocket(str, i, inetAddress, i2);
        er.a(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        Socket createSocket = this.lP.createSocket(inetAddress, i, inetAddress2, i2);
        er.a(createSocket);
        return createSocket;
    }
}
