package javax.mail.internet;

import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.net.HttpHeaders;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.PropUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.mail.Header;
import javax.mail.MessagingException;
/* loaded from: classes3.dex */
public class InternetHeaders {
    private static final boolean ignoreWhitespaceLines = PropUtil.getBooleanSystemProperty("mail.mime.ignorewhitespacelines", false);
    protected List headers = new ArrayList(40);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class MatchEnum implements Enumeration {
        private Iterator e;
        private boolean match;
        private String[] names;
        private InternetHeader next_header = null;
        private boolean want_line;

        MatchEnum(List list, String[] strArr, boolean z, boolean z2) {
            this.e = list.iterator();
            this.names = strArr;
            this.match = z;
            this.want_line = z2;
        }

        private InternetHeader nextMatch() {
            while (this.e.hasNext()) {
                InternetHeader internetHeader = (InternetHeader) this.e.next();
                if (internetHeader.line != null) {
                    if (this.names == null) {
                        if (!this.match) {
                            return internetHeader;
                        }
                        return null;
                    }
                    int i = 0;
                    while (true) {
                        String[] strArr = this.names;
                        if (i < strArr.length) {
                            if (!strArr[i].equalsIgnoreCase(internetHeader.getName())) {
                                i++;
                            } else if (this.match) {
                                return internetHeader;
                            }
                        } else if (!this.match) {
                            return internetHeader;
                        }
                    }
                }
            }
            return null;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            if (this.next_header == null) {
                this.next_header = nextMatch();
            }
            return this.next_header != null;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            if (this.next_header == null) {
                this.next_header = nextMatch();
            }
            InternetHeader internetHeader = this.next_header;
            if (internetHeader != null) {
                this.next_header = null;
                if (this.want_line) {
                    return internetHeader.line;
                }
                return new Header(internetHeader.getName(), internetHeader.getValue());
            }
            throw new NoSuchElementException("No more headers");
        }
    }

    public InternetHeaders() {
        GeneratedOutlineSupport1.outline170("Return-Path", null, this.headers);
        GeneratedOutlineSupport1.outline170("Received", null, this.headers);
        GeneratedOutlineSupport1.outline170("Resent-Date", null, this.headers);
        GeneratedOutlineSupport1.outline170("Resent-From", null, this.headers);
        GeneratedOutlineSupport1.outline170("Resent-Sender", null, this.headers);
        GeneratedOutlineSupport1.outline170("Resent-To", null, this.headers);
        GeneratedOutlineSupport1.outline170("Resent-Cc", null, this.headers);
        GeneratedOutlineSupport1.outline170("Resent-Bcc", null, this.headers);
        GeneratedOutlineSupport1.outline170("Resent-Message-Id", null, this.headers);
        GeneratedOutlineSupport1.outline170("Date", null, this.headers);
        GeneratedOutlineSupport1.outline170(HttpHeaders.FROM, null, this.headers);
        GeneratedOutlineSupport1.outline170("Sender", null, this.headers);
        GeneratedOutlineSupport1.outline170("Reply-To", null, this.headers);
        GeneratedOutlineSupport1.outline170("To", null, this.headers);
        GeneratedOutlineSupport1.outline170("Cc", null, this.headers);
        GeneratedOutlineSupport1.outline170("Bcc", null, this.headers);
        GeneratedOutlineSupport1.outline170("Message-Id", null, this.headers);
        GeneratedOutlineSupport1.outline170("In-Reply-To", null, this.headers);
        GeneratedOutlineSupport1.outline170("References", null, this.headers);
        GeneratedOutlineSupport1.outline170("Subject", null, this.headers);
        GeneratedOutlineSupport1.outline170("Comments", null, this.headers);
        GeneratedOutlineSupport1.outline170("Keywords", null, this.headers);
        GeneratedOutlineSupport1.outline170("Errors-To", null, this.headers);
        GeneratedOutlineSupport1.outline170("MIME-Version", null, this.headers);
        GeneratedOutlineSupport1.outline170("Content-Type", null, this.headers);
        GeneratedOutlineSupport1.outline170("Content-Transfer-Encoding", null, this.headers);
        GeneratedOutlineSupport1.outline170("Content-MD5", null, this.headers);
        GeneratedOutlineSupport1.outline170(":", null, this.headers);
        GeneratedOutlineSupport1.outline170("Content-Length", null, this.headers);
        GeneratedOutlineSupport1.outline170("Status", null, this.headers);
    }

    private static final boolean isEmpty(String str) {
        return str.length() == 0 || (ignoreWhitespaceLines && str.trim().length() == 0);
    }

    public void addHeader(String str, String str2) {
        int size = this.headers.size();
        boolean z = str.equalsIgnoreCase("Received") || str.equalsIgnoreCase("Return-Path");
        if (z) {
            size = 0;
        }
        for (int size2 = this.headers.size() - 1; size2 >= 0; size2--) {
            InternetHeader internetHeader = (InternetHeader) this.headers.get(size2);
            if (str.equalsIgnoreCase(internetHeader.getName())) {
                if (!z) {
                    this.headers.add(size2 + 1, new InternetHeader(str, str2));
                    return;
                }
                size = size2;
            }
            if (!z && internetHeader.getName().equals(":")) {
                size = size2;
            }
        }
        this.headers.add(size, new InternetHeader(str, str2));
    }

    public void addHeaderLine(String str) {
        try {
            char charAt = str.charAt(0);
            if (charAt != ' ' && charAt != '\t') {
                this.headers.add(new InternetHeader(str));
            }
            InternetHeader internetHeader = (InternetHeader) this.headers.get(this.headers.size() - 1);
            internetHeader.line += "\r\n" + str;
        } catch (StringIndexOutOfBoundsException | NoSuchElementException unused) {
        }
    }

    public Enumeration getAllHeaderLines() {
        return getNonMatchingHeaderLines(null);
    }

    public Enumeration getAllHeaders() {
        return new MatchEnum(this.headers, null, false, false);
    }

    public String[] getHeader(String str) {
        ArrayList arrayList = new ArrayList();
        for (InternetHeader internetHeader : this.headers) {
            if (str.equalsIgnoreCase(internetHeader.getName()) && internetHeader.line != null) {
                arrayList.add(internetHeader.getValue());
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public Enumeration getMatchingHeaderLines(String[] strArr) {
        return new MatchEnum(this.headers, strArr, true, true);
    }

    public Enumeration getMatchingHeaders(String[] strArr) {
        return new MatchEnum(this.headers, strArr, true, false);
    }

    public Enumeration getNonMatchingHeaderLines(String[] strArr) {
        return new MatchEnum(this.headers, strArr, false, true);
    }

    public Enumeration getNonMatchingHeaders(String[] strArr) {
        return new MatchEnum(this.headers, strArr, false, false);
    }

    public void load(InputStream inputStream) throws MessagingException {
        String readLine;
        LineInputStream lineInputStream = new LineInputStream(inputStream);
        StringBuffer stringBuffer = new StringBuffer();
        String str = null;
        do {
            try {
                readLine = lineInputStream.readLine();
                if (readLine == null || (!readLine.startsWith(" ") && !readLine.startsWith(DeviceDatabaseUtils.DELIMITER))) {
                    if (str != null) {
                        addHeaderLine(str);
                    } else if (stringBuffer.length() > 0) {
                        addHeaderLine(stringBuffer.toString());
                        stringBuffer.setLength(0);
                    }
                    str = readLine;
                } else {
                    if (str != null) {
                        stringBuffer.append(str);
                        str = null;
                    }
                    stringBuffer.append("\r\n");
                    stringBuffer.append(readLine);
                }
                if (readLine == null) {
                    return;
                }
            } catch (IOException e) {
                throw new MessagingException("Error in input stream", e);
            }
        } while (!isEmpty(readLine));
    }

    public void removeHeader(String str) {
        for (int i = 0; i < this.headers.size(); i++) {
            InternetHeader internetHeader = (InternetHeader) this.headers.get(i);
            if (str.equalsIgnoreCase(internetHeader.getName())) {
                internetHeader.line = null;
            }
        }
    }

    public void setHeader(String str, String str2) {
        int indexOf;
        int i = 0;
        boolean z = false;
        while (i < this.headers.size()) {
            InternetHeader internetHeader = (InternetHeader) this.headers.get(i);
            if (str.equalsIgnoreCase(internetHeader.getName())) {
                if (!z) {
                    String str3 = internetHeader.line;
                    if (str3 != null && (indexOf = str3.indexOf(58)) >= 0) {
                        internetHeader.line = internetHeader.line.substring(0, indexOf + 1) + " " + str2;
                    } else {
                        internetHeader.line = GeneratedOutlineSupport1.outline75(str, RealTimeTextConstants.COLON_SPACE, str2);
                    }
                    z = true;
                } else {
                    this.headers.remove(i);
                    i--;
                }
            }
            i++;
        }
        if (!z) {
            addHeader(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public static final class InternetHeader extends Header {
        String line;

        public InternetHeader(String str) {
            super("", "");
            int indexOf = str.indexOf(58);
            if (indexOf < 0) {
                this.name = str.trim();
            } else {
                this.name = str.substring(0, indexOf).trim();
            }
            this.line = str;
        }

        @Override // javax.mail.Header
        public String getValue() {
            char charAt;
            int indexOf = this.line.indexOf(58);
            if (indexOf < 0) {
                return this.line;
            }
            while (true) {
                indexOf++;
                if (indexOf >= this.line.length() || ((charAt = this.line.charAt(indexOf)) != ' ' && charAt != '\t' && charAt != '\r' && charAt != '\n')) {
                    break;
                }
            }
            return this.line.substring(indexOf);
        }

        public InternetHeader(String str, String str2) {
            super(str, "");
            if (str2 != null) {
                this.line = GeneratedOutlineSupport1.outline75(str, RealTimeTextConstants.COLON_SPACE, str2);
            } else {
                this.line = null;
            }
        }
    }

    public String getHeader(String str, String str2) {
        String[] header = getHeader(str);
        if (header == null) {
            return null;
        }
        if (header.length != 1 && str2 != null) {
            StringBuffer stringBuffer = new StringBuffer(header[0]);
            for (int i = 1; i < header.length; i++) {
                stringBuffer.append(str2);
                stringBuffer.append(header[i]);
            }
            return stringBuffer.toString();
        }
        return header[0];
    }

    public InternetHeaders(InputStream inputStream) throws MessagingException {
        load(inputStream);
    }
}
