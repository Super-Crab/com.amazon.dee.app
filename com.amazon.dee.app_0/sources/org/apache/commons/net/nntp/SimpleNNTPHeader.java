package org.apache.commons.net.nntp;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes4.dex */
public class SimpleNNTPHeader {
    private String __from;
    private String __subject;
    private StringBuffer __newsgroups = new StringBuffer();
    private StringBuffer __headerFields = new StringBuffer();
    private int __newsgroupCount = 0;

    public SimpleNNTPHeader(String str, String str2) {
        this.__from = str;
        this.__subject = str2;
    }

    public void addHeaderField(String str, String str2) {
        this.__headerFields.append(str);
        this.__headerFields.append(RealTimeTextConstants.COLON_SPACE);
        this.__headerFields.append(str2);
        this.__headerFields.append('\n');
    }

    public void addNewsgroup(String str) {
        int i = this.__newsgroupCount;
        this.__newsgroupCount = i + 1;
        if (i > 0) {
            this.__newsgroups.append(JsonReaderKt.COMMA);
        }
        this.__newsgroups.append(str);
    }

    public String getFromAddress() {
        return this.__from;
    }

    public String getNewsgroups() {
        return this.__newsgroups.toString();
    }

    public String getSubject() {
        return this.__subject;
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("From: ");
        outline103.append(this.__from);
        outline103.append("\nNewsgroups: ");
        outline103.append(this.__newsgroups.toString());
        outline103.append("\nSubject: ");
        outline103.append(this.__subject);
        outline103.append('\n');
        if (this.__headerFields.length() > 0) {
            outline103.append(this.__headerFields.toString());
        }
        outline103.append('\n');
        return outline103.toString();
    }
}
