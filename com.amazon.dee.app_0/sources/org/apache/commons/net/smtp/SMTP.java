package org.apache.commons.net.smtp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.SocketClient;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class SMTP extends SocketClient {
    public static final int DEFAULT_PORT = 25;
    private static final String __DEFAULT_ENCODING = "ISO-8859-1";
    private StringBuffer __commandBuffer;
    protected ProtocolCommandSupport _commandSupport_;
    boolean _newReplyString;
    BufferedReader _reader;
    int _replyCode;
    Vector _replyLines;
    String _replyString;
    BufferedWriter _writer;

    public SMTP() {
        setDefaultPort(25);
        this.__commandBuffer = new StringBuffer();
        this._replyLines = new Vector();
        this._newReplyString = false;
        this._replyString = null;
        this._commandSupport_ = new ProtocolCommandSupport(this);
    }

    private void __getReply() throws IOException {
        this._newReplyString = true;
        this._replyLines.setSize(0);
        String readLine = this._reader.readLine();
        if (readLine != null) {
            int length = readLine.length();
            if (length >= 3) {
                try {
                    this._replyCode = Integer.parseInt(readLine.substring(0, 3));
                    this._replyLines.addElement(readLine);
                    if (length > 3 && readLine.charAt(3) == '-') {
                        while (true) {
                            String readLine2 = this._reader.readLine();
                            if (readLine2 != null) {
                                this._replyLines.addElement(readLine2);
                                if (readLine2.length() >= 4 && readLine2.charAt(3) != '-' && Character.isDigit(readLine2.charAt(0))) {
                                    break;
                                }
                            } else {
                                throw new SMTPConnectionClosedException("Connection closed without indication.");
                            }
                        }
                    }
                    if (this._commandSupport_.getListenerCount() > 0) {
                        this._commandSupport_.fireReplyReceived(this._replyCode, getReplyString());
                    }
                    if (this._replyCode == 421) {
                        throw new SMTPConnectionClosedException("SMTP response 421 received.  Server closed connection.");
                    }
                    return;
                } catch (NumberFormatException unused) {
                    throw new MalformedServerReplyException(GeneratedOutlineSupport1.outline71("Could not parse response code.\nServer Reply: ", readLine));
                }
            }
            throw new MalformedServerReplyException(GeneratedOutlineSupport1.outline71("Truncated server reply: ", readLine));
        }
        throw new SMTPConnectionClosedException("Connection closed without indication.");
    }

    private int __sendCommand(String str, String str2, boolean z) throws IOException {
        this.__commandBuffer.setLength(0);
        this.__commandBuffer.append(str);
        if (str2 != null) {
            if (z) {
                this.__commandBuffer.append(Chars.SPACE);
            }
            this.__commandBuffer.append(str2);
        }
        this.__commandBuffer.append("\r\n");
        BufferedWriter bufferedWriter = this._writer;
        String stringBuffer = this.__commandBuffer.toString();
        bufferedWriter.write(stringBuffer);
        this._writer.flush();
        if (this._commandSupport_.getListenerCount() > 0) {
            this._commandSupport_.fireCommandSent(str, stringBuffer);
        }
        __getReply();
        return this._replyCode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.SocketClient
    public void _connectAction_() throws IOException {
        super._connectAction_();
        this._reader = new BufferedReader(new InputStreamReader(this._input_, "ISO-8859-1"));
        this._writer = new BufferedWriter(new OutputStreamWriter(this._output_, "ISO-8859-1"));
        __getReply();
    }

    public void addProtocolCommandListener(ProtocolCommandListener protocolCommandListener) {
        this._commandSupport_.addProtocolCommandListener(protocolCommandListener);
    }

    public int data() throws IOException {
        return sendCommand(3);
    }

    @Override // org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        this._reader = null;
        this._writer = null;
        this._replyString = null;
        this._replyLines.setSize(0);
        this._newReplyString = false;
    }

    public int expn(String str) throws IOException {
        return sendCommand(9, str);
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

    public int helo(String str) throws IOException {
        return sendCommand(0, str);
    }

    public int help() throws IOException {
        return sendCommand(10);
    }

    public int mail(String str) throws IOException {
        return __sendCommand(1, str, false);
    }

    public int noop() throws IOException {
        return sendCommand(11);
    }

    public int quit() throws IOException {
        return sendCommand(13);
    }

    public int rcpt(String str) throws IOException {
        return __sendCommand(2, str, false);
    }

    public void removeProtocolCommandistener(ProtocolCommandListener protocolCommandListener) {
        this._commandSupport_.removeProtocolCommandListener(protocolCommandListener);
    }

    public int rset() throws IOException {
        return sendCommand(7);
    }

    public int saml(String str) throws IOException {
        return sendCommand(6, str);
    }

    public int send(String str) throws IOException {
        return sendCommand(4, str);
    }

    public int sendCommand(String str, String str2) throws IOException {
        return __sendCommand(str, str2, true);
    }

    public int soml(String str) throws IOException {
        return sendCommand(5, str);
    }

    public int turn() throws IOException {
        return sendCommand(12);
    }

    public int vrfy(String str) throws IOException {
        return sendCommand(8, str);
    }

    public int help(String str) throws IOException {
        return sendCommand(10, str);
    }

    public int sendCommand(int i, String str) throws IOException {
        return sendCommand(SMTPCommand._commands[i], str);
    }

    public int sendCommand(String str) throws IOException {
        return sendCommand(str, (String) null);
    }

    public int sendCommand(int i) throws IOException {
        return sendCommand(i, (String) null);
    }

    private int __sendCommand(int i, String str, boolean z) throws IOException {
        return __sendCommand(SMTPCommand._commands[i], str, z);
    }
}
