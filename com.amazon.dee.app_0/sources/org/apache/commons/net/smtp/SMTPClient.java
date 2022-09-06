package org.apache.commons.net.smtp;

import com.amazon.alexa.mobilytics.configuration.Config;
import java.io.IOException;
import java.io.Writer;
import org.apache.commons.net.io.DotTerminatedMessageWriter;
/* loaded from: classes4.dex */
public class SMTPClient extends SMTP {
    public boolean addRecipient(RelayPath relayPath) throws IOException {
        return SMTPReply.isPositiveCompletion(rcpt(relayPath.toString()));
    }

    public boolean completePendingCommand() throws IOException {
        return SMTPReply.isPositiveCompletion(getReply());
    }

    public String listHelp() throws IOException {
        if (SMTPReply.isPositiveCompletion(help())) {
            return getReplyString();
        }
        return null;
    }

    public boolean login(String str) throws IOException {
        return SMTPReply.isPositiveCompletion(helo(str));
    }

    public boolean logout() throws IOException {
        return SMTPReply.isPositiveCompletion(quit());
    }

    public boolean reset() throws IOException {
        return SMTPReply.isPositiveCompletion(rset());
    }

    public Writer sendMessageData() throws IOException {
        if (!SMTPReply.isPositiveIntermediate(data())) {
            return null;
        }
        return new DotTerminatedMessageWriter(this._writer);
    }

    public boolean sendNoOp() throws IOException {
        return SMTPReply.isPositiveCompletion(noop());
    }

    public boolean sendShortMessageData(String str) throws IOException {
        Writer sendMessageData = sendMessageData();
        if (sendMessageData == null) {
            return false;
        }
        sendMessageData.write(str);
        sendMessageData.close();
        return completePendingCommand();
    }

    public boolean sendSimpleMessage(String str, String str2, String str3) throws IOException {
        if (setSender(str) && addRecipient(str2)) {
            return sendShortMessageData(str3);
        }
        return false;
    }

    public boolean setSender(RelayPath relayPath) throws IOException {
        return SMTPReply.isPositiveCompletion(mail(relayPath.toString()));
    }

    public boolean verify(String str) throws IOException {
        int vrfy = vrfy(str);
        return vrfy == 250 || vrfy == 251;
    }

    public boolean addRecipient(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Config.Compare.LESS_THAN);
        stringBuffer.append(str);
        stringBuffer.append(Config.Compare.GREATER_THAN);
        return SMTPReply.isPositiveCompletion(rcpt(stringBuffer.toString()));
    }

    public boolean login() throws IOException {
        String hostName = getLocalAddress().getHostName();
        if (hostName == null) {
            return false;
        }
        return SMTPReply.isPositiveCompletion(helo(hostName));
    }

    public boolean setSender(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Config.Compare.LESS_THAN);
        stringBuffer.append(str);
        stringBuffer.append(Config.Compare.GREATER_THAN);
        return SMTPReply.isPositiveCompletion(mail(stringBuffer.toString()));
    }

    public String listHelp(String str) throws IOException {
        if (SMTPReply.isPositiveCompletion(help(str))) {
            return getReplyString();
        }
        return null;
    }

    public boolean sendSimpleMessage(String str, String[] strArr, String str2) throws IOException {
        if (!setSender(str)) {
            return false;
        }
        boolean z = false;
        for (String str3 : strArr) {
            if (addRecipient(str3)) {
                z = true;
            }
        }
        if (z) {
            return sendShortMessageData(str2);
        }
        return false;
    }
}
