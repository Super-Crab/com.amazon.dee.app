package org.apache.thrift.orig.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TServerSocket extends TServerTransport {
    private static final Logger LOGGER = LoggerFactory.getLogger(TServerSocket.class.getName());
    private int clientTimeout_;
    private ServerSocket serverSocket_;

    public TServerSocket(ServerSocket serverSocket) {
        this(serverSocket, 0);
    }

    @Override // org.apache.thrift.orig.transport.TServerTransport
    public void close() {
        ServerSocket serverSocket = this.serverSocket_;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                LOGGER.warn("Could not close server socket.", (Throwable) e);
            }
            this.serverSocket_ = null;
        }
    }

    public ServerSocket getServerSocket() {
        return this.serverSocket_;
    }

    @Override // org.apache.thrift.orig.transport.TServerTransport
    public void interrupt() {
        close();
    }

    @Override // org.apache.thrift.orig.transport.TServerTransport
    public void listen() throws TTransportException {
        ServerSocket serverSocket = this.serverSocket_;
        if (serverSocket != null) {
            try {
                serverSocket.setSoTimeout(0);
            } catch (SocketException e) {
                LOGGER.error("Could not set socket timeout.", (Throwable) e);
            }
        }
    }

    public TServerSocket(ServerSocket serverSocket, int i) {
        this.serverSocket_ = null;
        this.clientTimeout_ = 0;
        this.serverSocket_ = serverSocket;
        this.clientTimeout_ = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.thrift.orig.transport.TServerTransport
    /* renamed from: acceptImpl */
    public TSocket mo12848acceptImpl() throws TTransportException {
        ServerSocket serverSocket = this.serverSocket_;
        if (serverSocket != null) {
            try {
                TSocket tSocket = new TSocket(serverSocket.accept());
                tSocket.setTimeout(this.clientTimeout_);
                return tSocket;
            } catch (IOException e) {
                throw new TTransportException(e);
            }
        }
        throw new TTransportException(1, "No underlying server socket.");
    }

    public TServerSocket(int i) throws TTransportException {
        this(i, 0);
    }

    public TServerSocket(int i, int i2) throws TTransportException {
        this(new InetSocketAddress(i), i2);
    }

    public TServerSocket(InetSocketAddress inetSocketAddress) throws TTransportException {
        this(inetSocketAddress, 0);
    }

    public TServerSocket(InetSocketAddress inetSocketAddress, int i) throws TTransportException {
        this.serverSocket_ = null;
        this.clientTimeout_ = 0;
        this.clientTimeout_ = i;
        try {
            this.serverSocket_ = new ServerSocket();
            this.serverSocket_.setReuseAddress(true);
            this.serverSocket_.bind(inetSocketAddress);
        } catch (IOException unused) {
            this.serverSocket_ = null;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not create ServerSocket on address ");
            outline107.append(inetSocketAddress.toString());
            outline107.append(".");
            throw new TTransportException(outline107.toString());
        }
    }
}
