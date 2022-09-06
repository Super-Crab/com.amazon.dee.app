package org.apache.thrift.orig.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TNonblockingServerSocket extends TNonblockingServerTransport {
    private static final Logger LOGGER = LoggerFactory.getLogger(TNonblockingServerTransport.class.getName());
    private int clientTimeout_;
    private ServerSocketChannel serverSocketChannel;
    private ServerSocket serverSocket_;

    public TNonblockingServerSocket(int i) throws TTransportException {
        this(i, 0);
    }

    @Override // org.apache.thrift.orig.transport.TServerTransport
    public void close() {
        ServerSocket serverSocket = this.serverSocket_;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                Logger logger = LOGGER;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WARNING: Could not close server socket: ");
                outline107.append(e.getMessage());
                logger.warn(outline107.toString());
            }
            this.serverSocket_ = null;
        }
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
                e.printStackTrace();
            }
        }
    }

    @Override // org.apache.thrift.orig.transport.TNonblockingServerTransport
    public void registerSelector(Selector selector) {
        try {
            this.serverSocketChannel.register(selector, 16);
        } catch (ClosedChannelException unused) {
        }
    }

    public TNonblockingServerSocket(int i, int i2) throws TTransportException {
        this(new InetSocketAddress(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.thrift.orig.transport.TServerTransport
    /* renamed from: acceptImpl */
    public TNonblockingSocket mo12848acceptImpl() throws TTransportException {
        if (this.serverSocket_ != null) {
            try {
                SocketChannel accept = this.serverSocketChannel.accept();
                if (accept == null) {
                    return null;
                }
                TNonblockingSocket tNonblockingSocket = new TNonblockingSocket(accept);
                tNonblockingSocket.setTimeout(this.clientTimeout_);
                return tNonblockingSocket;
            } catch (IOException e) {
                throw new TTransportException(e);
            }
        }
        throw new TTransportException(1, "No underlying server socket.");
    }

    public TNonblockingServerSocket(InetSocketAddress inetSocketAddress) throws TTransportException {
        this(inetSocketAddress, 0);
    }

    public TNonblockingServerSocket(InetSocketAddress inetSocketAddress, int i) throws TTransportException {
        this.serverSocketChannel = null;
        this.serverSocket_ = null;
        this.clientTimeout_ = 0;
        this.clientTimeout_ = i;
        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocket_ = this.serverSocketChannel.socket();
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
