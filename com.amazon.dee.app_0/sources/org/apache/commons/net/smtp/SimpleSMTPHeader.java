package org.apache.commons.net.smtp;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
/* loaded from: classes4.dex */
public class SimpleSMTPHeader {
    private String __from;
    private String __subject;
    private String __to;
    private StringBuffer __headerFields = new StringBuffer();
    private StringBuffer __cc = null;

    public SimpleSMTPHeader(String str, String str2, String str3) {
        this.__to = str2;
        this.__from = str;
        this.__subject = str3;
    }

    public void addCC(String str) {
        StringBuffer stringBuffer = this.__cc;
        if (stringBuffer == null) {
            this.__cc = new StringBuffer();
        } else {
            stringBuffer.append(", ");
        }
        this.__cc.append(str);
    }

    public void addHeaderField(String str, String str2) {
        this.__headerFields.append(str);
        this.__headerFields.append(RealTimeTextConstants.COLON_SPACE);
        this.__headerFields.append(str2);
        this.__headerFields.append('\n');
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.__headerFields.length() > 0) {
            stringBuffer.append(this.__headerFields.toString());
        }
        stringBuffer.append("From: ");
        stringBuffer.append(this.__from);
        stringBuffer.append("\nTo: ");
        stringBuffer.append(this.__to);
        if (this.__cc != null) {
            stringBuffer.append("\nCc: ");
            stringBuffer.append(this.__cc.toString());
        }
        if (this.__subject != null) {
            stringBuffer.append("\nSubject: ");
            stringBuffer.append(this.__subject);
        }
        stringBuffer.append('\n');
        stringBuffer.append('\n');
        return stringBuffer.toString();
    }
}
