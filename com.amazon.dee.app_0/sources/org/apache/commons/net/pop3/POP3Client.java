package org.apache.commons.net.pop3;

import java.io.IOException;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.net.io.DotTerminatedMessageReader;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class POP3Client extends POP3 {
    private static POP3MessageInfo __parseStatus(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        if (!stringTokenizer.hasMoreElements()) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(stringTokenizer.nextToken());
            if (stringTokenizer.hasMoreElements()) {
                return new POP3MessageInfo(parseInt, Integer.parseInt(stringTokenizer.nextToken()));
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static POP3MessageInfo __parseUID(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        if (!stringTokenizer.hasMoreElements()) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(stringTokenizer.nextToken());
            if (stringTokenizer.hasMoreElements()) {
                return new POP3MessageInfo(parseInt, stringTokenizer.nextToken());
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public boolean deleteMessage(int i) throws IOException {
        return getState() == 1 && sendCommand(6, Integer.toString(i)) == POP3Reply.OK;
    }

    public POP3MessageInfo listMessage(int i) throws IOException {
        if (getState() == 1 && sendCommand(4, Integer.toString(i)) == POP3Reply.OK) {
            return __parseStatus(this._lastReplyLine.substring(3));
        }
        return null;
    }

    public POP3MessageInfo[] listMessages() throws IOException {
        if (getState() == 1 && sendCommand(4) == POP3Reply.OK) {
            getAdditionalReply();
            POP3MessageInfo[] pOP3MessageInfoArr = new POP3MessageInfo[this._replyLines.size() - 2];
            Enumeration elements = this._replyLines.elements();
            elements.nextElement();
            for (int i = 0; i < pOP3MessageInfoArr.length; i++) {
                pOP3MessageInfoArr[i] = __parseStatus((String) elements.nextElement());
            }
            return pOP3MessageInfoArr;
        }
        return null;
    }

    public POP3MessageInfo listUniqueIdentifier(int i) throws IOException {
        if (getState() == 1 && sendCommand(11, Integer.toString(i)) == POP3Reply.OK) {
            return __parseUID(this._lastReplyLine.substring(3));
        }
        return null;
    }

    public POP3MessageInfo[] listUniqueIdentifiers() throws IOException {
        if (getState() == 1 && sendCommand(11) == POP3Reply.OK) {
            getAdditionalReply();
            POP3MessageInfo[] pOP3MessageInfoArr = new POP3MessageInfo[this._replyLines.size() - 2];
            Enumeration elements = this._replyLines.elements();
            elements.nextElement();
            for (int i = 0; i < pOP3MessageInfoArr.length; i++) {
                pOP3MessageInfoArr[i] = __parseUID((String) elements.nextElement());
            }
            return pOP3MessageInfoArr;
        }
        return null;
    }

    public boolean login(String str, String str2) throws IOException {
        if (getState() == 0 && sendCommand(0, str) == POP3Reply.OK && sendCommand(1, str2) == POP3Reply.OK) {
            setState(1);
            return true;
        }
        return false;
    }

    public boolean logout() throws IOException {
        if (getState() == 1) {
            setState(2);
        }
        sendCommand(2);
        return this._replyCode == POP3Reply.OK;
    }

    public boolean noop() throws IOException {
        return getState() == 1 && sendCommand(7) == POP3Reply.OK;
    }

    public boolean reset() throws IOException {
        return getState() == 1 && sendCommand(8) == POP3Reply.OK;
    }

    public Reader retrieveMessage(int i) throws IOException {
        if (getState() == 1 && sendCommand(5, Integer.toString(i)) == POP3Reply.OK) {
            return new DotTerminatedMessageReader(this._reader);
        }
        return null;
    }

    public Reader retrieveMessageTop(int i, int i2) throws IOException {
        if (i2 < 0 || getState() != 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Integer.toString(i));
        stringBuffer.append(" ");
        stringBuffer.append(Integer.toString(i2));
        if (sendCommand(10, stringBuffer.toString()) == POP3Reply.OK) {
            return new DotTerminatedMessageReader(this._reader);
        }
        return null;
    }

    public POP3MessageInfo status() throws IOException {
        if (getState() == 1 && sendCommand(3) == POP3Reply.OK) {
            return __parseStatus(this._lastReplyLine.substring(3));
        }
        return null;
    }

    public boolean login(String str, String str2, String str3) throws IOException, NoSuchAlgorithmException {
        if (getState() != 0) {
            return false;
        }
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append(str3);
        byte[] digest = messageDigest.digest(stringBuffer.toString().getBytes());
        StringBuffer stringBuffer2 = new StringBuffer(128);
        for (byte b : digest) {
            stringBuffer2.append(Integer.toHexString(b & 255));
        }
        StringBuffer stringBuffer3 = new StringBuffer(256);
        stringBuffer3.append(str);
        stringBuffer3.append(Chars.SPACE);
        stringBuffer3.append(stringBuffer2.toString());
        if (sendCommand(9, stringBuffer3.toString()) != POP3Reply.OK) {
            return false;
        }
        setState(1);
        return true;
    }
}
