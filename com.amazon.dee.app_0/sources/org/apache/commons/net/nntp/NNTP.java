package org.apache.commons.net.nntp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import kotlin.text.Typography;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.SocketClient;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class NNTP extends SocketClient {
    public static final int DEFAULT_PORT = 119;
    private static final String __DEFAULT_ENCODING = "ISO-8859-1";
    private StringBuffer __commandBuffer;
    protected ProtocolCommandSupport _commandSupport_;
    boolean _isAllowedToPost;
    protected BufferedReader _reader_;
    int _replyCode;
    String _replyString;
    protected BufferedWriter _writer_;

    public NNTP() {
        setDefaultPort(119);
        this.__commandBuffer = new StringBuffer();
        this._replyString = null;
        this._reader_ = null;
        this._writer_ = null;
        this._isAllowedToPost = false;
        this._commandSupport_ = new ProtocolCommandSupport(this);
    }

    private void __getReply() throws IOException {
        this._replyString = this._reader_.readLine();
        String str = this._replyString;
        if (str != null) {
            if (str.length() >= 3) {
                try {
                    this._replyCode = Integer.parseInt(this._replyString.substring(0, 3));
                    if (this._commandSupport_.getListenerCount() > 0) {
                        ProtocolCommandSupport protocolCommandSupport = this._commandSupport_;
                        int i = this._replyCode;
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(this._replyString);
                        stringBuffer.append("\r\n");
                        protocolCommandSupport.fireReplyReceived(i, stringBuffer.toString());
                    }
                    if (this._replyCode == 400) {
                        throw new NNTPConnectionClosedException("NNTP response 400 received.  Server closed connection.");
                    }
                    return;
                } catch (NumberFormatException unused) {
                    StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Could not parse response code.\nServer Reply: ");
                    outline103.append(this._replyString);
                    throw new MalformedServerReplyException(outline103.toString());
                }
            }
            StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("Truncated server reply: ");
            outline1032.append(this._replyString);
            throw new MalformedServerReplyException(outline1032.toString());
        }
        throw new NNTPConnectionClosedException("Connection closed without indication.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.SocketClient
    public void _connectAction_() throws IOException {
        super._connectAction_();
        this._reader_ = new BufferedReader(new InputStreamReader(this._input_, "ISO-8859-1"));
        this._writer_ = new BufferedWriter(new OutputStreamWriter(this._output_, "ISO-8859-1"));
        __getReply();
        this._isAllowedToPost = this._replyCode == 200;
    }

    public void addProtocolCommandListener(ProtocolCommandListener protocolCommandListener) {
        this._commandSupport_.addProtocolCommandListener(protocolCommandListener);
    }

    public int article(String str) throws IOException {
        return sendCommand(0, str);
    }

    public int authinfoPass(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PASS ");
        stringBuffer.append(str);
        return sendCommand(15, stringBuffer.toString());
    }

    public int authinfoUser(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("USER ");
        stringBuffer.append(str);
        return sendCommand(15, stringBuffer.toString());
    }

    public int body(String str) throws IOException {
        return sendCommand(1, str);
    }

    @Override // org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        this._reader_ = null;
        this._writer_ = null;
        this._replyString = null;
        this._isAllowedToPost = false;
    }

    public int getReply() throws IOException {
        __getReply();
        return this._replyCode;
    }

    public int getReplyCode() {
        return this._replyCode;
    }

    public String getReplyString() {
        return this._replyString;
    }

    public int group(String str) throws IOException {
        return sendCommand(2, str);
    }

    public int head(String str) throws IOException {
        return sendCommand(3, str);
    }

    public int help() throws IOException {
        return sendCommand(4);
    }

    public int ihave(String str) throws IOException {
        return sendCommand(5, str);
    }

    public boolean isAllowedToPost() {
        return this._isAllowedToPost;
    }

    public int last() throws IOException {
        return sendCommand(6);
    }

    public int list() throws IOException {
        return sendCommand(7);
    }

    public int listActive(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer("ACTIVE ");
        stringBuffer.append(str);
        return sendCommand(7, stringBuffer.toString());
    }

    public int newgroups(String str, String str2, boolean z, String str3) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(Chars.SPACE);
        stringBuffer.append(str2);
        if (z) {
            stringBuffer.append(Chars.SPACE);
            stringBuffer.append("GMT");
        }
        if (str3 != null) {
            stringBuffer.append(" <");
            stringBuffer.append(str3);
            stringBuffer.append(Typography.greater);
        }
        return sendCommand(8, stringBuffer.toString());
    }

    public int newnews(String str, String str2, String str3, boolean z, String str4) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(Chars.SPACE);
        stringBuffer.append(str2);
        stringBuffer.append(Chars.SPACE);
        stringBuffer.append(str3);
        if (z) {
            stringBuffer.append(Chars.SPACE);
            stringBuffer.append("GMT");
        }
        if (str4 != null) {
            stringBuffer.append(" <");
            stringBuffer.append(str4);
            stringBuffer.append(Typography.greater);
        }
        return sendCommand(9, stringBuffer.toString());
    }

    public int next() throws IOException {
        return sendCommand(10);
    }

    public int post() throws IOException {
        return sendCommand(11);
    }

    public int quit() throws IOException {
        return sendCommand(12);
    }

    public void removeProtocolCommandListener(ProtocolCommandListener protocolCommandListener) {
        this._commandSupport_.removeProtocolCommandListener(protocolCommandListener);
    }

    public int sendCommand(String str, String str2) throws IOException {
        this.__commandBuffer.setLength(0);
        this.__commandBuffer.append(str);
        if (str2 != null) {
            this.__commandBuffer.append(Chars.SPACE);
            this.__commandBuffer.append(str2);
        }
        this.__commandBuffer.append("\r\n");
        BufferedWriter bufferedWriter = this._writer_;
        String stringBuffer = this.__commandBuffer.toString();
        bufferedWriter.write(stringBuffer);
        this._writer_.flush();
        if (this._commandSupport_.getListenerCount() > 0) {
            this._commandSupport_.fireCommandSent(str, stringBuffer);
        }
        __getReply();
        return this._replyCode;
    }

    public int stat(String str) throws IOException {
        return sendCommand(14, str);
    }

    public int xhdr(String str, String str2) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.append(" ");
        stringBuffer.append(str2);
        return sendCommand(17, stringBuffer.toString());
    }

    public int xover(String str) throws IOException {
        return sendCommand(16, str);
    }

    public int article(int i) throws IOException {
        return sendCommand(0, Integer.toString(i));
    }

    public int body(int i) throws IOException {
        return sendCommand(1, Integer.toString(i));
    }

    public int head(int i) throws IOException {
        return sendCommand(3, Integer.toString(i));
    }

    public int stat(int i) throws IOException {
        return sendCommand(14, Integer.toString(i));
    }

    public int article() throws IOException {
        return sendCommand(0);
    }

    public int body() throws IOException {
        return sendCommand(1);
    }

    public int head() throws IOException {
        return sendCommand(3);
    }

    public int stat() throws IOException {
        return sendCommand(14);
    }

    public int sendCommand(int i, String str) throws IOException {
        return sendCommand(NNTPCommand._commands[i], str);
    }

    public int sendCommand(String str) throws IOException {
        return sendCommand(str, (String) null);
    }

    public int sendCommand(int i) throws IOException {
        return sendCommand(i, (String) null);
    }
}
