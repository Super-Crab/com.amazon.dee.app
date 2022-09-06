package org.apache.commons.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
/* loaded from: classes4.dex */
public abstract class SocketClient {
    public static final String NETASCII_EOL = "\r\n";
    private static final SocketFactory __DEFAULT_SOCKET_FACTORY = new DefaultSocketFactory();
    protected Socket _socket_ = null;
    protected InputStream _input_ = null;
    protected OutputStream _output_ = null;
    protected int _timeout_ = 0;
    protected int _defaultPort_ = 0;
    protected boolean _isConnected_ = false;
    protected SocketFactory _socketFactory_ = __DEFAULT_SOCKET_FACTORY;

    /* JADX INFO: Access modifiers changed from: protected */
    public void _connectAction_() throws IOException {
        this._socket_.setSoTimeout(this._timeout_);
        this._input_ = this._socket_.getInputStream();
        this._output_ = this._socket_.getOutputStream();
        this._isConnected_ = true;
    }

    public void connect(InetAddress inetAddress, int i) throws SocketException, IOException {
        this._socket_ = this._socketFactory_.createSocket(inetAddress, i);
        _connectAction_();
    }

    public void disconnect() throws IOException {
        this._socket_.close();
        this._input_.close();
        this._output_.close();
        this._socket_ = null;
        this._input_ = null;
        this._output_ = null;
        this._isConnected_ = false;
    }

    public int getDefaultPort() {
        return this._defaultPort_;
    }

    public int getDefaultTimeout() {
        return this._timeout_;
    }

    public InetAddress getLocalAddress() {
        return this._socket_.getLocalAddress();
    }

    public int getLocalPort() {
        return this._socket_.getLocalPort();
    }

    public InetAddress getRemoteAddress() {
        return this._socket_.getInetAddress();
    }

    public int getRemotePort() {
        return this._socket_.getPort();
    }

    public int getSoLinger() throws SocketException {
        return this._socket_.getSoLinger();
    }

    public int getSoTimeout() throws SocketException {
        return this._socket_.getSoTimeout();
    }

    public boolean getTcpNoDelay() throws SocketException {
        return this._socket_.getTcpNoDelay();
    }

    public boolean isConnected() {
        return this._isConnected_;
    }

    public void setDefaultPort(int i) {
        this._defaultPort_ = i;
    }

    public void setDefaultTimeout(int i) {
        this._timeout_ = i;
    }

    public void setSoLinger(boolean z, int i) throws SocketException {
        this._socket_.setSoLinger(z, i);
    }

    public void setSoTimeout(int i) throws SocketException {
        this._socket_.setSoTimeout(i);
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        if (socketFactory == null) {
            this._socketFactory_ = __DEFAULT_SOCKET_FACTORY;
        } else {
            this._socketFactory_ = socketFactory;
        }
    }

    public void setTcpNoDelay(boolean z) throws SocketException {
        this._socket_.setTcpNoDelay(z);
    }

    public boolean verifyRemote(Socket socket) {
        return socket.getInetAddress().equals(getRemoteAddress());
    }

    public void connect(String str, int i) throws SocketException, IOException {
        this._socket_ = this._socketFactory_.createSocket(str, i);
        _connectAction_();
    }

    public void connect(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws SocketException, IOException {
        this._socket_ = this._socketFactory_.createSocket(inetAddress, i, inetAddress2, i2);
        _connectAction_();
    }

    public void connect(String str, int i, InetAddress inetAddress, int i2) throws SocketException, IOException {
        this._socket_ = this._socketFactory_.createSocket(str, i, inetAddress, i2);
        _connectAction_();
    }

    public void connect(InetAddress inetAddress) throws SocketException, IOException {
        connect(inetAddress, this._defaultPort_);
    }

    public void connect(String str) throws SocketException, IOException {
        connect(str, this._defaultPort_);
    }
}
