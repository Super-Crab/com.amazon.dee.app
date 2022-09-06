package org.apache.thrift.orig.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TSocket extends TIOStreamTransport {
    private static final Logger LOGGER = LoggerFactory.getLogger(TSocket.class.getName());
    private String host_;
    private int port_;
    private Socket socket_;
    private int timeout_;

    public TSocket(Socket socket) throws TTransportException {
        this.socket_ = null;
        this.host_ = null;
        this.port_ = 0;
        this.timeout_ = 0;
        this.socket_ = socket;
        try {
            this.socket_.setSoLinger(false, 0);
            this.socket_.setTcpNoDelay(true);
        } catch (SocketException e) {
            LOGGER.warn("Could not configure socket.", (Throwable) e);
        }
        if (isOpen()) {
            try {
                this.inputStream_ = new BufferedInputStream(this.socket_.getInputStream(), 1024);
                this.outputStream_ = new BufferedOutputStream(this.socket_.getOutputStream(), 1024);
            } catch (IOException e2) {
                close();
                throw new TTransportException(1, e2);
            }
        }
    }

    private void initSocket() {
        this.socket_ = new Socket();
        try {
            this.socket_.setSoLinger(false, 0);
            this.socket_.setTcpNoDelay(true);
            this.socket_.setSoTimeout(this.timeout_);
        } catch (SocketException e) {
            LOGGER.error("Could not configure socket.", (Throwable) e);
        }
    }

    @Override // org.apache.thrift.orig.transport.TIOStreamTransport, org.apache.thrift.orig.transport.TTransport
    public void close() {
        super.close();
        Socket socket = this.socket_;
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                LOGGER.warn("Could not close socket.", (Throwable) e);
            }
            this.socket_ = null;
        }
    }

    public Socket getSocket() {
        if (this.socket_ == null) {
            initSocket();
        }
        return this.socket_;
    }

    @Override // org.apache.thrift.orig.transport.TIOStreamTransport, org.apache.thrift.orig.transport.TTransport
    public boolean isOpen() {
        Socket socket = this.socket_;
        if (socket == null) {
            return false;
        }
        return socket.isConnected();
    }

    @Override // org.apache.thrift.orig.transport.TIOStreamTransport, org.apache.thrift.orig.transport.TTransport
    public void open() throws TTransportException {
        if (!isOpen()) {
            if (this.host_.length() != 0) {
                if (this.port_ > 0) {
                    if (this.socket_ == null) {
                        initSocket();
                    }
                    try {
                        this.socket_.connect(new InetSocketAddress(this.host_, this.port_), this.timeout_);
                        this.inputStream_ = new BufferedInputStream(this.socket_.getInputStream(), 1024);
                        this.outputStream_ = new BufferedOutputStream(this.socket_.getOutputStream(), 1024);
                        return;
                    } catch (IOException e) {
                        close();
                        throw new TTransportException(1, e);
                    }
                }
                throw new TTransportException(1, "Cannot open without port.");
            }
            throw new TTransportException(1, "Cannot open null host.");
        }
        throw new TTransportException(2, "Socket already connected.");
    }

    public void setTimeout(int i) {
        this.timeout_ = i;
        try {
            this.socket_.setSoTimeout(i);
        } catch (SocketException e) {
            LOGGER.warn("Could not set socket timeout.", (Throwable) e);
        }
    }

    public TSocket(String str, int i) {
        this(str, i, 0);
    }

    public TSocket(String str, int i, int i2) {
        this.socket_ = null;
        this.host_ = null;
        this.port_ = 0;
        this.timeout_ = 0;
        this.host_ = str;
        this.port_ = i;
        this.timeout_ = i2;
        initSocket();
    }
}
