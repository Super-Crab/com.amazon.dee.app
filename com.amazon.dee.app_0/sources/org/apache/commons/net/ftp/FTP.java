package org.apache.commons.net.ftp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Vector;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class FTP extends TelnetClient {
    public static final int ASCII_FILE_TYPE = 0;
    public static final int BINARY_FILE_TYPE = 2;
    public static final int BLOCK_TRANSFER_MODE = 11;
    public static final int CARRIAGE_CONTROL_TEXT_FORMAT = 6;
    public static final int COMPRESSED_TRANSFER_MODE = 12;
    public static final String DEFAULT_CONTROL_ENCODING = "ISO-8859-1";
    public static final int DEFAULT_DATA_PORT = 20;
    public static final int DEFAULT_PORT = 21;
    public static final int EBCDIC_FILE_TYPE = 1;
    public static final int FILE_STRUCTURE = 7;
    public static final int IMAGE_FILE_TYPE = 2;
    public static final int LOCAL_FILE_TYPE = 3;
    public static final int NON_PRINT_TEXT_FORMAT = 4;
    public static final int PAGE_STRUCTURE = 9;
    public static final int RECORD_STRUCTURE = 8;
    public static final int STREAM_TRANSFER_MODE = 10;
    public static final int TELNET_TEXT_FORMAT = 5;
    private static final String __modes = "ABILNTCFRPSBC";
    private StringBuffer __commandBuffer;
    protected ProtocolCommandSupport _commandSupport_;
    String _controlEncoding;
    BufferedReader _controlInput;
    BufferedWriter _controlOutput;
    boolean _newReplyString;
    int _replyCode;
    Vector _replyLines;
    String _replyString;

    public FTP() {
        setDefaultPort(21);
        this.__commandBuffer = new StringBuffer();
        this._replyLines = new Vector();
        this._newReplyString = false;
        this._replyString = null;
        this._commandSupport_ = new ProtocolCommandSupport(this);
        this._controlEncoding = "ISO-8859-1";
    }

    private void __getReply() throws IOException {
        this._newReplyString = true;
        this._replyLines.setSize(0);
        String readLine = this._controlInput.readLine();
        if (readLine != null) {
            int length = readLine.length();
            if (length >= 3) {
                try {
                    this._replyCode = Integer.parseInt(readLine.substring(0, 3));
                    this._replyLines.addElement(readLine);
                    if (length > 3 && readLine.charAt(3) == '-') {
                        while (true) {
                            String readLine2 = this._controlInput.readLine();
                            if (readLine2 != null) {
                                this._replyLines.addElement(readLine2);
                                if (readLine2.length() >= 4 && readLine2.charAt(3) != '-' && Character.isDigit(readLine2.charAt(0))) {
                                    break;
                                }
                            } else {
                                throw new FTPConnectionClosedException("Connection closed without indication.");
                            }
                        }
                    }
                    if (this._commandSupport_.getListenerCount() > 0) {
                        this._commandSupport_.fireReplyReceived(this._replyCode, getReplyString());
                    }
                    if (this._replyCode == 421) {
                        throw new FTPConnectionClosedException("FTP response 421 received.  Server closed connection.");
                    }
                    return;
                } catch (NumberFormatException unused) {
                    throw new MalformedServerReplyException(GeneratedOutlineSupport1.outline71("Could not parse response code.\nServer Reply: ", readLine));
                }
            }
            throw new MalformedServerReplyException(GeneratedOutlineSupport1.outline71("Truncated server reply: ", readLine));
        }
        throw new FTPConnectionClosedException("Connection closed without indication.");
    }

    private boolean socketIsConnected(Socket socket) {
        if (socket == null) {
            return false;
        }
        try {
            return ((Boolean) socket.getClass().getMethod("isConnected", null).invoke(socket, null)).booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.telnet.TelnetClient, org.apache.commons.net.telnet.Telnet, org.apache.commons.net.SocketClient
    public void _connectAction_() throws IOException {
        super._connectAction_();
        this._controlInput = new BufferedReader(new InputStreamReader(getInputStream(), getControlEncoding()));
        this._controlOutput = new BufferedWriter(new OutputStreamWriter(getOutputStream(), getControlEncoding()));
        __getReply();
        if (FTPReply.isPositivePreliminary(this._replyCode)) {
            __getReply();
        }
    }

    public int abor() throws IOException {
        return sendCommand(21);
    }

    public int acct(String str) throws IOException {
        return sendCommand(2, str);
    }

    public void addProtocolCommandListener(ProtocolCommandListener protocolCommandListener) {
        this._commandSupport_.addProtocolCommandListener(protocolCommandListener);
    }

    public int allo(int i) throws IOException {
        return sendCommand(17, Integer.toString(i));
    }

    public int appe(String str) throws IOException {
        return sendCommand(16, str);
    }

    public int cdup() throws IOException {
        return sendCommand(4);
    }

    public int cwd(String str) throws IOException {
        return sendCommand(3, str);
    }

    public int dele(String str) throws IOException {
        return sendCommand(22, str);
    }

    @Override // org.apache.commons.net.telnet.TelnetClient, org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        this._controlInput = null;
        this._controlOutput = null;
        this._replyLines.setSize(0);
        this._newReplyString = false;
        this._replyString = null;
    }

    public String getControlEncoding() {
        return this._controlEncoding;
    }

    public int getReply() throws IOException {
        __getReply();
        return this._replyCode;
    }

    public int getReplyCode() {
        return this._replyCode;
    }

    public String getReplyString() {
        if (!this._newReplyString) {
            return this._replyString;
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        Enumeration elements = this._replyLines.elements();
        while (elements.hasMoreElements()) {
            stringBuffer.append((String) elements.nextElement());
            stringBuffer.append("\r\n");
        }
        this._newReplyString = false;
        String stringBuffer2 = stringBuffer.toString();
        this._replyString = stringBuffer2;
        return stringBuffer2;
    }

    public String[] getReplyStrings() {
        String[] strArr = new String[this._replyLines.size()];
        this._replyLines.copyInto(strArr);
        return strArr;
    }

    public int help() throws IOException {
        return sendCommand(31);
    }

    public int list() throws IOException {
        return sendCommand(26);
    }

    public int mkd(String str) throws IOException {
        return sendCommand(24, str);
    }

    public int mode(int i) throws IOException {
        return sendCommand(12, __modes.substring(i, i + 1));
    }

    public int nlst() throws IOException {
        return sendCommand(27);
    }

    public int noop() throws IOException {
        return sendCommand(32);
    }

    public int pass(String str) throws IOException {
        return sendCommand(1, str);
    }

    public int pasv() throws IOException {
        return sendCommand(9);
    }

    public int port(InetAddress inetAddress, int i) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(24);
        stringBuffer.append(inetAddress.getHostAddress().replace('.', JsonReaderKt.COMMA));
        stringBuffer.append(JsonReaderKt.COMMA);
        stringBuffer.append(i >>> 8);
        stringBuffer.append(JsonReaderKt.COMMA);
        stringBuffer.append(i & 255);
        return sendCommand(8, stringBuffer.toString());
    }

    public int pwd() throws IOException {
        return sendCommand(25);
    }

    public int quit() throws IOException {
        return sendCommand(7);
    }

    public int rein() throws IOException {
        return sendCommand(6);
    }

    public void removeProtocolCommandListener(ProtocolCommandListener protocolCommandListener) {
        this._commandSupport_.removeProtocolCommandListener(protocolCommandListener);
    }

    public int rest(String str) throws IOException {
        return sendCommand(18, str);
    }

    public int retr(String str) throws IOException {
        return sendCommand(13, str);
    }

    public int rmd(String str) throws IOException {
        return sendCommand(23, str);
    }

    public int rnfr(String str) throws IOException {
        return sendCommand(19, str);
    }

    public int rnto(String str) throws IOException {
        return sendCommand(20, str);
    }

    public int sendCommand(String str, String str2) throws IOException {
        this.__commandBuffer.setLength(0);
        this.__commandBuffer.append(str);
        if (str2 != null) {
            this.__commandBuffer.append(Chars.SPACE);
            this.__commandBuffer.append(str2);
        }
        this.__commandBuffer.append("\r\n");
        try {
            BufferedWriter bufferedWriter = this._controlOutput;
            String stringBuffer = this.__commandBuffer.toString();
            bufferedWriter.write(stringBuffer);
            this._controlOutput.flush();
            if (this._commandSupport_.getListenerCount() > 0) {
                this._commandSupport_.fireCommandSent(str, stringBuffer);
            }
            __getReply();
            return this._replyCode;
        } catch (SocketException e) {
            if (isConnected() && socketIsConnected(this._socket_)) {
                throw e;
            }
            throw new FTPConnectionClosedException("Connection unexpectedly closed.");
        }
    }

    public void setControlEncoding(String str) {
        this._controlEncoding = str;
    }

    public int site(String str) throws IOException {
        return sendCommand(28, str);
    }

    public int smnt(String str) throws IOException {
        return sendCommand(5, str);
    }

    public int stat() throws IOException {
        return sendCommand(30);
    }

    public int stor(String str) throws IOException {
        return sendCommand(14, str);
    }

    public int stou() throws IOException {
        return sendCommand(15);
    }

    public int stru(int i) throws IOException {
        return sendCommand(11, __modes.substring(i, i + 1));
    }

    public int syst() throws IOException {
        return sendCommand(29);
    }

    public int type(int i, int i2) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(__modes.charAt(i));
        stringBuffer.append(Chars.SPACE);
        if (i == 3) {
            stringBuffer.append(i2);
        } else {
            stringBuffer.append(__modes.charAt(i2));
        }
        return sendCommand(10, stringBuffer.toString());
    }

    public int user(String str) throws IOException {
        return sendCommand(0, str);
    }

    public int allo(int i, int i2) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Integer.toString(i));
        stringBuffer.append(" R ");
        stringBuffer.append(Integer.toString(i2));
        return sendCommand(17, stringBuffer.toString());
    }

    public int help(String str) throws IOException {
        return sendCommand(31, str);
    }

    public int list(String str) throws IOException {
        return sendCommand(26, str);
    }

    public int nlst(String str) throws IOException {
        return sendCommand(27, str);
    }

    public int stat(String str) throws IOException {
        return sendCommand(30, str);
    }

    public int stou(String str) throws IOException {
        return sendCommand(15, str);
    }

    public int type(int i) throws IOException {
        return sendCommand(10, __modes.substring(i, i + 1));
    }

    public int sendCommand(int i, String str) throws IOException {
        return sendCommand(FTPCommand._commands[i], str);
    }

    public int sendCommand(String str) throws IOException {
        return sendCommand(str, (String) null);
    }

    public int sendCommand(int i) throws IOException {
        return sendCommand(i, (String) null);
    }
}
