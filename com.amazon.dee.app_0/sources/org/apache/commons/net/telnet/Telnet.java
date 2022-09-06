package org.apache.commons.net.telnet;

import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.commons.net.SocketClient;
/* loaded from: classes4.dex */
class Telnet extends SocketClient {
    static final int DEFAULT_PORT = 23;
    protected static final int TERMINAL_TYPE = 24;
    protected static final int TERMINAL_TYPE_IS = 0;
    protected static final int TERMINAL_TYPE_SEND = 1;
    static final int _DO_MASK = 2;
    static final int _REQUESTED_DO_MASK = 8;
    static final int _REQUESTED_WILL_MASK = 4;
    static final int _WILL_MASK = 1;
    static final boolean debug = false;
    static final boolean debugoptions = false;
    private TelnetNotificationHandler __notifhand;
    int[] _doResponse;
    int[] _options;
    int[] _willResponse;
    private boolean aytFlag;
    private Object aytMonitor;
    private TelnetOptionHandler[] optionHandlers;
    private OutputStream spyStream;
    private String terminalType;
    static final byte[] _COMMAND_DO = {-1, -3};
    static final byte[] _COMMAND_DONT = {-1, -2};
    static final byte[] _COMMAND_WILL = {-1, -5};
    static final byte[] _COMMAND_WONT = {-1, -4};
    static final byte[] _COMMAND_SB = {-1, -6};
    static final byte[] _COMMAND_SE = {-1, RequesterRelationshipsPacketV1.HEADER_MASK_VERSION};
    static final byte[] _COMMAND_IS = {24, 0};
    static final byte[] _COMMAND_AYT = {-1, -10};

    Telnet() {
        this.terminalType = null;
        this.aytMonitor = new Object();
        this.aytFlag = true;
        this.spyStream = null;
        this.__notifhand = null;
        setDefaultPort(23);
        this._doResponse = new int[256];
        this._willResponse = new int[256];
        this._options = new int[256];
        this.optionHandlers = new TelnetOptionHandler[256];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.SocketClient
    public void _connectAction_() throws IOException {
        for (int i = 0; i < 256; i++) {
            this._doResponse[i] = 0;
            this._willResponse[i] = 0;
            this._options[i] = 0;
            TelnetOptionHandler[] telnetOptionHandlerArr = this.optionHandlers;
            if (telnetOptionHandlerArr[i] != null) {
                telnetOptionHandlerArr[i].setDo(false);
                this.optionHandlers[i].setWill(false);
            }
        }
        super._connectAction_();
        this._input_ = new BufferedInputStream(this._input_);
        this._output_ = new BufferedOutputStream(this._output_);
        for (int i2 = 0; i2 < 256; i2++) {
            TelnetOptionHandler[] telnetOptionHandlerArr2 = this.optionHandlers;
            if (telnetOptionHandlerArr2[i2] != null) {
                if (telnetOptionHandlerArr2[i2].getInitLocal()) {
                    try {
                        _requestWill(this.optionHandlers[i2].getOptionCode());
                    } catch (IOException e) {
                        PrintStream printStream = System.err;
                        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Exception while initializing option: ");
                        outline103.append(e.getMessage());
                        printStream.println(outline103.toString());
                    }
                }
                if (this.optionHandlers[i2].getInitRemote()) {
                    try {
                        _requestDo(this.optionHandlers[i2].getOptionCode());
                    } catch (IOException e2) {
                        PrintStream printStream2 = System.err;
                        StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("Exception while initializing option: ");
                        outline1032.append(e2.getMessage());
                        printStream2.println(outline1032.toString());
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void _processAYTResponse() {
        if (!this.aytFlag) {
            synchronized (this.aytMonitor) {
                this.aytFlag = true;
                try {
                    this.aytMonitor.notifyAll();
                } catch (Exception e) {
                    PrintStream printStream = System.err;
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Exception notifying:");
                    stringBuffer.append(e.getMessage());
                    printStream.println(stringBuffer.toString());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _processDo(int i) throws IOException {
        String str;
        TelnetNotificationHandler telnetNotificationHandler = this.__notifhand;
        if (telnetNotificationHandler != null) {
            telnetNotificationHandler.receivedNegotiation(1, i);
        }
        boolean z = false;
        TelnetOptionHandler[] telnetOptionHandlerArr = this.optionHandlers;
        if (telnetOptionHandlerArr[i] != null) {
            z = telnetOptionHandlerArr[i].getAcceptLocal();
        } else if (i == 24 && (str = this.terminalType) != null && str.length() > 0) {
            z = true;
        }
        int[] iArr = this._willResponse;
        if (iArr[i] > 0) {
            iArr[i] = iArr[i] - 1;
            if (iArr[i] > 0 && _stateIsWill(i)) {
                int[] iArr2 = this._willResponse;
                iArr2[i] = iArr2[i] - 1;
            }
        }
        if (this._willResponse[i] == 0 && _requestedWont(i)) {
            if (z) {
                _setWantWill(i);
                _sendWill(i);
            } else {
                int[] iArr3 = this._willResponse;
                iArr3[i] = iArr3[i] + 1;
                _sendWont(i);
            }
        }
        _setWill(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _processDont(int i) throws IOException {
        TelnetNotificationHandler telnetNotificationHandler = this.__notifhand;
        if (telnetNotificationHandler != null) {
            telnetNotificationHandler.receivedNegotiation(2, i);
        }
        int[] iArr = this._willResponse;
        if (iArr[i] > 0) {
            iArr[i] = iArr[i] - 1;
            if (iArr[i] > 0 && _stateIsWont(i)) {
                int[] iArr2 = this._willResponse;
                iArr2[i] = iArr2[i] - 1;
            }
        }
        if (this._willResponse[i] == 0 && _requestedWill(i)) {
            if (_stateIsWill(i) || _requestedWill(i)) {
                _sendWont(i);
            }
            _setWantWont(i);
        }
        _setWont(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _processSuboption(int[] iArr, int i) throws IOException {
        if (i > 0) {
            TelnetOptionHandler[] telnetOptionHandlerArr = this.optionHandlers;
            if (telnetOptionHandlerArr[iArr[0]] != null) {
                _sendSubnegotiation(telnetOptionHandlerArr[iArr[0]].answerSubnegotiation(iArr, i));
            } else if (i <= 1 || iArr[0] != 24 || iArr[1] != 1) {
            } else {
                _sendTerminalType();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _processWill(int i) throws IOException {
        TelnetNotificationHandler telnetNotificationHandler = this.__notifhand;
        if (telnetNotificationHandler != null) {
            telnetNotificationHandler.receivedNegotiation(3, i);
        }
        boolean z = false;
        TelnetOptionHandler[] telnetOptionHandlerArr = this.optionHandlers;
        if (telnetOptionHandlerArr[i] != null) {
            z = telnetOptionHandlerArr[i].getAcceptRemote();
        }
        int[] iArr = this._doResponse;
        if (iArr[i] > 0) {
            iArr[i] = iArr[i] - 1;
            if (iArr[i] > 0 && _stateIsDo(i)) {
                int[] iArr2 = this._doResponse;
                iArr2[i] = iArr2[i] - 1;
            }
        }
        if (this._doResponse[i] == 0 && _requestedDont(i)) {
            if (z) {
                _setWantDo(i);
                _sendDo(i);
            } else {
                int[] iArr3 = this._doResponse;
                iArr3[i] = iArr3[i] + 1;
                _sendDont(i);
            }
        }
        _setDo(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _processWont(int i) throws IOException {
        TelnetNotificationHandler telnetNotificationHandler = this.__notifhand;
        if (telnetNotificationHandler != null) {
            telnetNotificationHandler.receivedNegotiation(4, i);
        }
        int[] iArr = this._doResponse;
        if (iArr[i] > 0) {
            iArr[i] = iArr[i] - 1;
            if (iArr[i] > 0 && _stateIsDont(i)) {
                int[] iArr2 = this._doResponse;
                iArr2[i] = iArr2[i] - 1;
            }
        }
        if (this._doResponse[i] == 0 && _requestedDo(i)) {
            if (_stateIsDo(i) || _requestedDo(i)) {
                _sendDont(i);
            }
            _setWantDont(i);
        }
        _setDont(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _registerSpyStream(OutputStream outputStream) {
        this.spyStream = outputStream;
    }

    final synchronized void _requestDo(int i) throws IOException {
        if ((this._doResponse[i] != 0 || !_stateIsDo(i)) && !_requestedDo(i)) {
            _setWantDo(i);
            int[] iArr = this._doResponse;
            iArr[i] = iArr[i] + 1;
            _sendDo(i);
        }
    }

    final synchronized void _requestDont(int i) throws IOException {
        if ((this._doResponse[i] != 0 || !_stateIsDont(i)) && !_requestedDont(i)) {
            _setWantDont(i);
            int[] iArr = this._doResponse;
            iArr[i] = iArr[i] + 1;
            _sendDont(i);
        }
    }

    final synchronized void _requestWill(int i) throws IOException {
        if ((this._willResponse[i] != 0 || !_stateIsWill(i)) && !_requestedWill(i)) {
            _setWantWill(i);
            int[] iArr = this._doResponse;
            iArr[i] = iArr[i] + 1;
            _sendWill(i);
        }
    }

    final synchronized void _requestWont(int i) throws IOException {
        if ((this._willResponse[i] != 0 || !_stateIsWont(i)) && !_requestedWont(i)) {
            _setWantWont(i);
            int[] iArr = this._doResponse;
            iArr[i] = iArr[i] + 1;
            _sendWont(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean _requestedDo(int i) {
        return (this._options[i] & 8) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean _requestedDont(int i) {
        return !_requestedDo(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean _requestedWill(int i) {
        return (this._options[i] & 4) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean _requestedWont(int i) {
        return !_requestedWill(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean _sendAYT(long j) throws IOException, IllegalArgumentException, InterruptedException {
        boolean z;
        synchronized (this.aytMonitor) {
            synchronized (this) {
                z = false;
                this.aytFlag = false;
                this._output_.write(_COMMAND_AYT);
                this._output_.flush();
            }
            try {
                this.aytMonitor.wait(j);
                if (!this.aytFlag) {
                    this.aytFlag = true;
                } else {
                    z = true;
                }
            } catch (IllegalMonitorStateException e) {
                PrintStream printStream = System.err;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Exception processing AYT:");
                stringBuffer.append(e.getMessage());
                printStream.println(stringBuffer.toString());
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void _sendByte(int i) throws IOException {
        this._output_.write(i);
        _spyWrite(i);
    }

    final synchronized void _sendDo(int i) throws IOException {
        this._output_.write(_COMMAND_DO);
        this._output_.write(i);
        this._output_.flush();
    }

    final synchronized void _sendDont(int i) throws IOException {
        this._output_.write(_COMMAND_DONT);
        this._output_.write(i);
        this._output_.flush();
    }

    final synchronized void _sendSubnegotiation(int[] iArr) throws IOException {
        if (iArr != null) {
            byte[] bArr = new byte[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                bArr[i] = (byte) iArr[i];
            }
            this._output_.write(_COMMAND_SB);
            this._output_.write(bArr);
            this._output_.write(_COMMAND_SE);
            this._output_.flush();
        }
    }

    final synchronized void _sendTerminalType() throws IOException {
        if (this.terminalType != null) {
            this._output_.write(_COMMAND_SB);
            this._output_.write(_COMMAND_IS);
            this._output_.write(this.terminalType.getBytes());
            this._output_.write(_COMMAND_SE);
            this._output_.flush();
        }
    }

    final synchronized void _sendWill(int i) throws IOException {
        this._output_.write(_COMMAND_WILL);
        this._output_.write(i);
        this._output_.flush();
    }

    final synchronized void _sendWont(int i) throws IOException {
        this._output_.write(_COMMAND_WONT);
        this._output_.write(i);
        this._output_.flush();
    }

    void _setDo(int i) {
        int[] iArr = this._options;
        iArr[i] = iArr[i] | 2;
        if (_requestedDo(i)) {
            TelnetOptionHandler[] telnetOptionHandlerArr = this.optionHandlers;
            if (telnetOptionHandlerArr[i] == null) {
                return;
            }
            telnetOptionHandlerArr[i].setDo(true);
            int[] startSubnegotiationRemote = this.optionHandlers[i].startSubnegotiationRemote();
            if (startSubnegotiationRemote == null) {
                return;
            }
            try {
                _sendSubnegotiation(startSubnegotiationRemote);
            } catch (Exception e) {
                PrintStream printStream = System.err;
                StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Exception in option subnegotiation");
                outline103.append(e.getMessage());
                printStream.println(outline103.toString());
            }
        }
    }

    void _setDont(int i) {
        int[] iArr = this._options;
        iArr[i] = iArr[i] & (-3);
        TelnetOptionHandler[] telnetOptionHandlerArr = this.optionHandlers;
        if (telnetOptionHandlerArr[i] != null) {
            telnetOptionHandlerArr[i].setDo(false);
        }
    }

    void _setWantDo(int i) {
        int[] iArr = this._options;
        iArr[i] = iArr[i] | 8;
    }

    void _setWantDont(int i) {
        int[] iArr = this._options;
        iArr[i] = iArr[i] & (-9);
    }

    void _setWantWill(int i) {
        int[] iArr = this._options;
        iArr[i] = iArr[i] | 4;
    }

    void _setWantWont(int i) {
        int[] iArr = this._options;
        iArr[i] = iArr[i] & (-5);
    }

    void _setWill(int i) {
        int[] iArr = this._options;
        iArr[i] = iArr[i] | 1;
        if (_requestedWill(i)) {
            TelnetOptionHandler[] telnetOptionHandlerArr = this.optionHandlers;
            if (telnetOptionHandlerArr[i] == null) {
                return;
            }
            telnetOptionHandlerArr[i].setWill(true);
            int[] startSubnegotiationLocal = this.optionHandlers[i].startSubnegotiationLocal();
            if (startSubnegotiationLocal == null) {
                return;
            }
            try {
                _sendSubnegotiation(startSubnegotiationLocal);
            } catch (Exception e) {
                PrintStream printStream = System.err;
                StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Exception in option subnegotiation");
                outline103.append(e.getMessage());
                printStream.println(outline103.toString());
            }
        }
    }

    void _setWont(int i) {
        int[] iArr = this._options;
        iArr[i] = iArr[i] & (-2);
        TelnetOptionHandler[] telnetOptionHandlerArr = this.optionHandlers;
        if (telnetOptionHandlerArr[i] != null) {
            telnetOptionHandlerArr[i].setWill(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _spyRead(int i) {
        OutputStream outputStream = this.spyStream;
        if (outputStream == null || i == 13) {
            return;
        }
        try {
            outputStream.write(i);
            if (i == 10) {
                this.spyStream.write(13);
            }
            this.spyStream.flush();
        } catch (Exception unused) {
            this.spyStream = null;
        }
    }

    void _spyWrite(int i) {
        OutputStream outputStream;
        if ((!_stateIsDo(TelnetOption.ECHO) || !_requestedDo(TelnetOption.ECHO)) && (outputStream = this.spyStream) != null) {
            try {
                outputStream.write(i);
                this.spyStream.flush();
            } catch (Exception unused) {
                this.spyStream = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean _stateIsDo(int i) {
        return (this._options[i] & 2) != 0;
    }

    boolean _stateIsDont(int i) {
        return !_stateIsDo(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean _stateIsWill(int i) {
        return (this._options[i] & 1) != 0;
    }

    boolean _stateIsWont(int i) {
        return !_stateIsWill(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _stopSpyStream() {
        this.spyStream = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addOptionHandler(TelnetOptionHandler telnetOptionHandler) throws InvalidTelnetOptionException {
        int optionCode = telnetOptionHandler.getOptionCode();
        if (TelnetOption.isValidOption(optionCode)) {
            TelnetOptionHandler[] telnetOptionHandlerArr = this.optionHandlers;
            if (telnetOptionHandlerArr[optionCode] == null) {
                telnetOptionHandlerArr[optionCode] = telnetOptionHandler;
                if (!isConnected()) {
                    return;
                }
                if (telnetOptionHandler.getInitLocal()) {
                    try {
                        _requestWill(optionCode);
                    } catch (IOException e) {
                        PrintStream printStream = System.err;
                        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Exception while initializing option: ");
                        outline103.append(e.getMessage());
                        printStream.println(outline103.toString());
                    }
                }
                if (!telnetOptionHandler.getInitRemote()) {
                    return;
                }
                try {
                    _requestDo(optionCode);
                    return;
                } catch (IOException e2) {
                    PrintStream printStream2 = System.err;
                    StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("Exception while initializing option: ");
                    outline1032.append(e2.getMessage());
                    printStream2.println(outline1032.toString());
                    return;
                }
            }
            throw new InvalidTelnetOptionException("Already registered option", optionCode);
        }
        throw new InvalidTelnetOptionException("Invalid Option Code", optionCode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deleteOptionHandler(int i) throws InvalidTelnetOptionException {
        if (TelnetOption.isValidOption(i)) {
            TelnetOptionHandler[] telnetOptionHandlerArr = this.optionHandlers;
            if (telnetOptionHandlerArr[i] != null) {
                TelnetOptionHandler telnetOptionHandler = telnetOptionHandlerArr[i];
                telnetOptionHandlerArr[i] = null;
                if (telnetOptionHandler.getWill()) {
                    try {
                        _requestWont(i);
                    } catch (IOException e) {
                        PrintStream printStream = System.err;
                        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Exception while turning off option: ");
                        outline103.append(e.getMessage());
                        printStream.println(outline103.toString());
                    }
                }
                if (!telnetOptionHandler.getDo()) {
                    return;
                }
                try {
                    _requestDont(i);
                    return;
                } catch (IOException e2) {
                    PrintStream printStream2 = System.err;
                    StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("Exception while turning off option: ");
                    outline1032.append(e2.getMessage());
                    printStream2.println(outline1032.toString());
                    return;
                }
            }
            throw new InvalidTelnetOptionException("Unregistered option", i);
        }
        throw new InvalidTelnetOptionException("Invalid Option Code", i);
    }

    public void registerNotifHandler(TelnetNotificationHandler telnetNotificationHandler) {
        this.__notifhand = telnetNotificationHandler;
    }

    public void unregisterNotifHandler() {
        this.__notifhand = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Telnet(String str) {
        this.terminalType = null;
        this.aytMonitor = new Object();
        this.aytFlag = true;
        this.spyStream = null;
        this.__notifhand = null;
        setDefaultPort(23);
        this._doResponse = new int[256];
        this._willResponse = new int[256];
        this._options = new int[256];
        this.terminalType = str;
        this.optionHandlers = new TelnetOptionHandler[256];
    }
}
