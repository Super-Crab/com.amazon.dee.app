package org.apache.thrift.orig.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TNonblockingSocket extends TNonblockingTransport {
    private static final Logger LOGGER = LoggerFactory.getLogger(TNonblockingSocket.class.getName());
    private final SocketAddress socketAddress_;
    private final SocketChannel socketChannel_;

    public TNonblockingSocket(String str, int i) throws IOException {
        this(str, i, 0);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
        try {
            this.socketChannel_.close();
        } catch (IOException e) {
            LOGGER.warn("Could not close socket.", (Throwable) e);
        }
    }

    @Override // org.apache.thrift.orig.transport.TNonblockingTransport
    public boolean finishConnect() throws IOException {
        return this.socketChannel_.finishConnect();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void flush() throws TTransportException {
    }

    public SocketChannel getSocketChannel() {
        return this.socketChannel_;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public boolean isOpen() {
        return this.socketChannel_.isOpen() && this.socketChannel_.isConnected();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void open() throws TTransportException {
        throw new RuntimeException("open() is not implemented for TNonblockingSocket");
    }

    @Override // org.apache.thrift.orig.transport.TNonblockingTransport
    public int read(ByteBuffer byteBuffer) throws IOException {
        return this.socketChannel_.read(byteBuffer);
    }

    @Override // org.apache.thrift.orig.transport.TNonblockingTransport
    public SelectionKey registerSelector(Selector selector, int i) throws IOException {
        return this.socketChannel_.register(selector, i);
    }

    public void setTimeout(int i) {
        try {
            this.socketChannel_.socket().setSoTimeout(i);
        } catch (SocketException e) {
            LOGGER.warn("Could not set socket timeout.", (Throwable) e);
        }
    }

    @Override // org.apache.thrift.orig.transport.TNonblockingTransport
    public boolean startConnect() throws IOException {
        return this.socketChannel_.connect(this.socketAddress_);
    }

    @Override // org.apache.thrift.orig.transport.TNonblockingTransport
    public int write(ByteBuffer byteBuffer) throws IOException {
        return this.socketChannel_.write(byteBuffer);
    }

    public TNonblockingSocket(String str, int i, int i2) throws IOException {
        this(SocketChannel.open(), i2, new InetSocketAddress(str, i));
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int read(byte[] bArr, int i, int i2) throws TTransportException {
        if ((this.socketChannel_.validOps() & 1) == 1) {
            try {
                return this.socketChannel_.read(ByteBuffer.wrap(bArr, i, i2));
            } catch (IOException e) {
                throw new TTransportException(0, e);
            }
        }
        throw new TTransportException(1, "Cannot read from write-only socket channel");
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) throws TTransportException {
        if ((this.socketChannel_.validOps() & 4) == 4) {
            try {
                this.socketChannel_.write(ByteBuffer.wrap(bArr, i, i2));
                return;
            } catch (IOException e) {
                throw new TTransportException(0, e);
            }
        }
        throw new TTransportException(1, "Cannot write to write-only socket channel");
    }

    public TNonblockingSocket(SocketChannel socketChannel) throws IOException {
        this(socketChannel, 0, (SocketAddress) null);
        if (socketChannel.isConnected()) {
            return;
        }
        throw new IOException("Socket must already be connected");
    }

    private TNonblockingSocket(SocketChannel socketChannel, int i, SocketAddress socketAddress) throws IOException {
        this.socketChannel_ = socketChannel;
        this.socketAddress_ = socketAddress;
        socketChannel.configureBlocking(false);
        Socket socket = socketChannel.socket();
        socket.setSoLinger(false, 0);
        socket.setTcpNoDelay(true);
        setTimeout(i);
    }
}
