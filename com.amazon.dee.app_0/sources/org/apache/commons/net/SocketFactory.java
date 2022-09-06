package org.apache.commons.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
/* loaded from: classes4.dex */
public interface SocketFactory {
    ServerSocket createServerSocket(int i) throws IOException;

    ServerSocket createServerSocket(int i, int i2) throws IOException;

    ServerSocket createServerSocket(int i, int i2, InetAddress inetAddress) throws IOException;

    Socket createSocket(String str, int i) throws UnknownHostException, IOException;

    Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws UnknownHostException, IOException;

    Socket createSocket(InetAddress inetAddress, int i) throws IOException;

    Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException;
}
