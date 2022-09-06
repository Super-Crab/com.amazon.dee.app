package org.apache.commons.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
/* loaded from: classes4.dex */
public class DefaultSocketFactory implements SocketFactory {
    @Override // org.apache.commons.net.SocketFactory
    public ServerSocket createServerSocket(int i) throws IOException {
        return new ServerSocket(i);
    }

    @Override // org.apache.commons.net.SocketFactory
    public Socket createSocket(String str, int i) throws UnknownHostException, IOException {
        return new Socket(str, i);
    }

    @Override // org.apache.commons.net.SocketFactory
    public ServerSocket createServerSocket(int i, int i2) throws IOException {
        return new ServerSocket(i, i2);
    }

    @Override // org.apache.commons.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return new Socket(inetAddress, i);
    }

    @Override // org.apache.commons.net.SocketFactory
    public ServerSocket createServerSocket(int i, int i2, InetAddress inetAddress) throws IOException {
        return new ServerSocket(i, i2, inetAddress);
    }

    @Override // org.apache.commons.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws UnknownHostException, IOException {
        return new Socket(str, i, inetAddress, i2);
    }

    @Override // org.apache.commons.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return new Socket(inetAddress, i, inetAddress2, i2);
    }
}
