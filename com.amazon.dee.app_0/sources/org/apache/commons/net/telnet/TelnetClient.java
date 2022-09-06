package org.apache.commons.net.telnet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.io.FromNetASCIIInputStream;
import org.apache.commons.net.io.ToNetASCIIOutputStream;
/* loaded from: classes4.dex */
public class TelnetClient extends Telnet {
    private InputStream __input;
    private OutputStream __output;
    protected boolean readerThread;

    public TelnetClient() {
        super("VT100");
        this.readerThread = true;
        this.__input = null;
        this.__output = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _closeOutputStream() throws IOException {
        this._output_.close();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.telnet.Telnet, org.apache.commons.net.SocketClient
    public void _connectAction_() throws IOException {
        InputStream inputStream;
        super._connectAction_();
        if (FromNetASCIIInputStream.isConversionRequired()) {
            inputStream = new FromNetASCIIInputStream(this._input_);
        } else {
            inputStream = this._input_;
        }
        TelnetInputStream telnetInputStream = new TelnetInputStream(inputStream, this, this.readerThread);
        if (this.readerThread) {
            telnetInputStream._start();
        }
        this.__input = new BufferedInputStream(telnetInputStream);
        this.__output = new ToNetASCIIOutputStream(new TelnetOutputStream(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _flushOutputStream() throws IOException {
        this._output_.flush();
    }

    @Override // org.apache.commons.net.telnet.Telnet
    public void addOptionHandler(TelnetOptionHandler telnetOptionHandler) throws InvalidTelnetOptionException {
        super.addOptionHandler(telnetOptionHandler);
    }

    @Override // org.apache.commons.net.telnet.Telnet
    public void deleteOptionHandler(int i) throws InvalidTelnetOptionException {
        super.deleteOptionHandler(i);
    }

    @Override // org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        this.__input.close();
        this.__output.close();
        super.disconnect();
    }

    public InputStream getInputStream() {
        return this.__input;
    }

    public boolean getLocalOptionState(int i) {
        return _stateIsWill(i) && _requestedWill(i);
    }

    public OutputStream getOutputStream() {
        return this.__output;
    }

    public boolean getReaderThread() {
        return this.readerThread;
    }

    public boolean getRemoteOptionState(int i) {
        return _stateIsDo(i) && _requestedDo(i);
    }

    @Override // org.apache.commons.net.telnet.Telnet
    public void registerNotifHandler(TelnetNotificationHandler telnetNotificationHandler) {
        super.registerNotifHandler(telnetNotificationHandler);
    }

    public void registerSpyStream(OutputStream outputStream) {
        super._registerSpyStream(outputStream);
    }

    public boolean sendAYT(long j) throws IOException, IllegalArgumentException, InterruptedException {
        return _sendAYT(j);
    }

    public void setReaderThread(boolean z) {
        this.readerThread = z;
    }

    public void stopSpyStream() {
        super._stopSpyStream();
    }

    @Override // org.apache.commons.net.telnet.Telnet
    public void unregisterNotifHandler() {
        super.unregisterNotifHandler();
    }

    public TelnetClient(String str) {
        super(str);
        this.readerThread = true;
        this.__input = null;
        this.__output = null;
    }
}
