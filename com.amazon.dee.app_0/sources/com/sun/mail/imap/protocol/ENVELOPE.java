package com.sun.mail.imap.protocol;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Response;
import com.sun.mail.util.PropUtil;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MailDateFormat;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
public class ENVELOPE implements Item {
    public InternetAddress[] bcc;
    public InternetAddress[] cc;
    public Date date;
    public InternetAddress[] from;
    public String inReplyTo;
    public String messageId;
    public int msgno;
    public InternetAddress[] replyTo;
    public InternetAddress[] sender;
    public String subject;
    public InternetAddress[] to;
    static final char[] name = {'E', 'N', 'V', 'E', Matrix.MATRIX_TYPE_RANDOM_LT, 'O', 'P', 'E'};
    private static MailDateFormat mailDateFormat = new MailDateFormat();
    private static final boolean parseDebug = PropUtil.getBooleanSystemProperty("mail.imap.parse.debug", false);

    public ENVELOPE(FetchResponse fetchResponse) throws ParsingException {
        this.date = null;
        if (parseDebug) {
            System.out.println("parse ENVELOPE");
        }
        this.msgno = fetchResponse.getNumber();
        fetchResponse.skipSpaces();
        if (fetchResponse.readByte() == 40) {
            String readString = fetchResponse.readString();
            if (readString != null) {
                try {
                    this.date = mailDateFormat.parse(readString);
                } catch (RuntimeException | ParseException unused) {
                }
            }
            if (parseDebug) {
                PrintStream printStream = System.out;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("  Date: ");
                outline107.append(this.date);
                printStream.println(outline107.toString());
            }
            this.subject = fetchResponse.readString();
            if (parseDebug) {
                GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("  Subject: "), this.subject, System.out);
            }
            if (parseDebug) {
                System.out.println("  From addresses:");
            }
            this.from = parseAddressList(fetchResponse);
            if (parseDebug) {
                System.out.println("  Sender addresses:");
            }
            this.sender = parseAddressList(fetchResponse);
            if (parseDebug) {
                System.out.println("  Reply-To addresses:");
            }
            this.replyTo = parseAddressList(fetchResponse);
            if (parseDebug) {
                System.out.println("  To addresses:");
            }
            this.to = parseAddressList(fetchResponse);
            if (parseDebug) {
                System.out.println("  Cc addresses:");
            }
            this.cc = parseAddressList(fetchResponse);
            if (parseDebug) {
                System.out.println("  Bcc addresses:");
            }
            this.bcc = parseAddressList(fetchResponse);
            this.inReplyTo = fetchResponse.readString();
            if (parseDebug) {
                GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("  In-Reply-To: "), this.inReplyTo, System.out);
            }
            this.messageId = fetchResponse.readString();
            if (parseDebug) {
                GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("  Message-ID: "), this.messageId, System.out);
            }
            if (fetchResponse.readByte() != 41) {
                throw new ParsingException("ENVELOPE parse error");
            }
            return;
        }
        throw new ParsingException("ENVELOPE parse error");
    }

    private InternetAddress[] parseAddressList(Response response) throws ParsingException {
        response.skipSpaces();
        byte readByte = response.readByte();
        if (readByte != 40) {
            if (readByte != 78 && readByte != 110) {
                throw new ParsingException("ADDRESS parse error");
            }
            response.skip(2);
            return null;
        } else if (response.peekByte() == 41) {
            response.skip(1);
            return null;
        } else {
            ArrayList arrayList = new ArrayList();
            do {
                IMAPAddress iMAPAddress = new IMAPAddress(response);
                if (parseDebug) {
                    PrintStream printStream = System.out;
                    printStream.println("    Address: " + iMAPAddress);
                }
                if (!iMAPAddress.isEndOfGroup()) {
                    arrayList.add(iMAPAddress);
                }
            } while (response.peekByte() != 41);
            response.skip(1);
            return (InternetAddress[]) arrayList.toArray(new InternetAddress[arrayList.size()]);
        }
    }
}
