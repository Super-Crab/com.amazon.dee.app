package javax.mail.internet;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.fitness.utils.FullScreenUpdaterUtilKt;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.BEncoderStream;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.PropUtil;
import com.sun.mail.util.QDecoderStream;
import com.sun.mail.util.QEncoderStream;
import com.sun.mail.util.QPDecoderStream;
import com.sun.mail.util.QPEncoderStream;
import com.sun.mail.util.UUDecoderStream;
import com.sun.mail.util.UUEncoderStream;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.EncodingAware;
import javax.mail.MessagingException;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public class MimeUtility {
    public static final int ALL = -1;
    static final int ALL_ASCII = 1;
    static final int MOSTLY_ASCII = 2;
    static final int MOSTLY_NONASCII = 3;
    private static String defaultJavaCharset;
    private static String defaultMIMECharset;
    private static final Map nonAsciiCharsetMap = new HashMap();
    private static final boolean decodeStrict = PropUtil.getBooleanSystemProperty("mail.mime.decodetext.strict", true);
    private static final boolean encodeEolStrict = PropUtil.getBooleanSystemProperty("mail.mime.encodeeol.strict", false);
    private static final boolean ignoreUnknownEncoding = PropUtil.getBooleanSystemProperty("mail.mime.ignoreunknownencoding", false);
    private static final boolean foldEncodedWords = PropUtil.getBooleanSystemProperty("mail.mime.foldencodedwords", false);
    private static final boolean foldText = PropUtil.getBooleanSystemProperty("mail.mime.foldtext", true);
    private static Hashtable java2mime = new Hashtable(40);
    private static Hashtable mime2java = new Hashtable(10);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v15, types: [com.sun.mail.util.LineInputStream, java.io.InputStream] */
    static {
        ?? r1;
        Throwable th;
        try {
            InputStream resourceAsStream = MimeUtility.class.getResourceAsStream("/META-INF/javamail.charset.map");
            if (resourceAsStream != null) {
                try {
                    r1 = new LineInputStream(resourceAsStream);
                    try {
                        loadMappings(r1, java2mime);
                        loadMappings(r1, mime2java);
                        r1.close();
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            r1.close();
                        } catch (Exception unused) {
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    r1 = resourceAsStream;
                    th = th3;
                }
            }
        } catch (Exception unused2) {
        }
        if (java2mime.isEmpty()) {
            java2mime.put("8859_1", "ISO-8859-1");
            java2mime.put("iso8859_1", "ISO-8859-1");
            java2mime.put("iso8859-1", "ISO-8859-1");
            java2mime.put("8859_2", "ISO-8859-2");
            java2mime.put("iso8859_2", "ISO-8859-2");
            java2mime.put("iso8859-2", "ISO-8859-2");
            java2mime.put("8859_3", "ISO-8859-3");
            java2mime.put("iso8859_3", "ISO-8859-3");
            java2mime.put("iso8859-3", "ISO-8859-3");
            java2mime.put("8859_4", "ISO-8859-4");
            java2mime.put("iso8859_4", "ISO-8859-4");
            java2mime.put("iso8859-4", "ISO-8859-4");
            java2mime.put("8859_5", "ISO-8859-5");
            java2mime.put("iso8859_5", "ISO-8859-5");
            java2mime.put("iso8859-5", "ISO-8859-5");
            java2mime.put("8859_6", "ISO-8859-6");
            java2mime.put("iso8859_6", "ISO-8859-6");
            java2mime.put("iso8859-6", "ISO-8859-6");
            java2mime.put("8859_7", "ISO-8859-7");
            java2mime.put("iso8859_7", "ISO-8859-7");
            java2mime.put("iso8859-7", "ISO-8859-7");
            java2mime.put("8859_8", "ISO-8859-8");
            java2mime.put("iso8859_8", "ISO-8859-8");
            java2mime.put("iso8859-8", "ISO-8859-8");
            java2mime.put("8859_9", "ISO-8859-9");
            java2mime.put("iso8859_9", "ISO-8859-9");
            java2mime.put("iso8859-9", "ISO-8859-9");
            java2mime.put("sjis", "Shift_JIS");
            java2mime.put("jis", "ISO-2022-JP");
            java2mime.put("iso2022jp", "ISO-2022-JP");
            java2mime.put("euc_jp", "euc-jp");
            java2mime.put("koi8_r", "koi8-r");
            java2mime.put("euc_cn", "euc-cn");
            java2mime.put("euc_tw", "euc-tw");
            java2mime.put("euc_kr", "euc-kr");
        }
        if (mime2java.isEmpty()) {
            mime2java.put("iso-2022-cn", "ISO2022CN");
            mime2java.put("iso-2022-kr", "ISO2022KR");
            mime2java.put("utf-8", "UTF8");
            mime2java.put("utf8", "UTF8");
            mime2java.put("ja_jp.iso2022-7", "ISO2022JP");
            mime2java.put("ja_jp.eucjp", "EUCJIS");
            mime2java.put("euc-kr", "KSC5601");
            mime2java.put("euckr", "KSC5601");
            mime2java.put("us-ascii", "ISO-8859-1");
            mime2java.put("x-us-ascii", "ISO-8859-1");
        }
    }

    private MimeUtility() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int checkAscii(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (nonascii(str.charAt(i3))) {
                i++;
            } else {
                i2++;
            }
        }
        if (i == 0) {
            return 1;
        }
        return i2 > i ? 2 : 3;
    }

    public static InputStream decode(InputStream inputStream, String str) throws MessagingException {
        if (str.equalsIgnoreCase("base64")) {
            return new BASE64DecoderStream(inputStream);
        }
        if (str.equalsIgnoreCase("quoted-printable")) {
            return new QPDecoderStream(inputStream);
        }
        if (!str.equalsIgnoreCase("uuencode") && !str.equalsIgnoreCase("x-uuencode") && !str.equalsIgnoreCase("x-uue")) {
            if (!str.equalsIgnoreCase("binary") && !str.equalsIgnoreCase("7bit") && !str.equalsIgnoreCase("8bit") && !ignoreUnknownEncoding) {
                throw new MessagingException(GeneratedOutlineSupport1.outline72("Unknown encoding: ", str));
            }
            return inputStream;
        }
        return new UUDecoderStream(inputStream);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0044, code lost:
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String decodeInnerWords(java.lang.String r5) throws java.io.UnsupportedEncodingException {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
        L6:
            java.lang.String r2 = "=?"
            int r2 = r5.indexOf(r2, r1)
            if (r2 < 0) goto L42
            java.lang.String r3 = r5.substring(r1, r2)
            r0.append(r3)
            int r3 = r2 + 2
            r4 = 63
            int r3 = r5.indexOf(r4, r3)
            if (r3 >= 0) goto L20
            goto L42
        L20:
            int r3 = r3 + 1
            int r3 = r5.indexOf(r4, r3)
            if (r3 >= 0) goto L29
            goto L42
        L29:
            int r3 = r3 + 1
            java.lang.String r4 = "?="
            int r3 = r5.indexOf(r4, r3)
            if (r3 >= 0) goto L34
            goto L42
        L34:
            int r1 = r3 + 2
            java.lang.String r2 = r5.substring(r2, r1)
            java.lang.String r2 = decodeWord(r2)     // Catch: javax.mail.internet.ParseException -> L3e
        L3e:
            r0.append(r2)
            goto L6
        L42:
            if (r1 != 0) goto L45
            return r5
        L45:
            int r2 = r5.length()
            if (r1 >= r2) goto L52
            java.lang.String r5 = r5.substring(r1)
            r0.append(r5)
        L52:
            java.lang.String r5 = r0.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeUtility.decodeInnerWords(java.lang.String):java.lang.String");
    }

    public static String decodeText(String str) throws UnsupportedEncodingException {
        String decodeInnerWords;
        if (str.indexOf("=?") == -1) {
            return str;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, " \t\n\r", true);
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        boolean z = false;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            char charAt = nextToken.charAt(0);
            if (charAt != ' ' && charAt != '\t' && charAt != '\r' && charAt != '\n') {
                try {
                    decodeInnerWords = decodeWord(nextToken);
                    if (!z && stringBuffer2.length() > 0) {
                        stringBuffer.append(stringBuffer2);
                    }
                    z = true;
                } catch (ParseException unused) {
                    if (!decodeStrict) {
                        decodeInnerWords = decodeInnerWords(nextToken);
                        if (decodeInnerWords != nextToken) {
                            if ((!z || !nextToken.startsWith("=?")) && stringBuffer2.length() > 0) {
                                stringBuffer.append(stringBuffer2);
                            }
                            z = nextToken.endsWith("?=");
                        } else if (stringBuffer2.length() > 0) {
                            stringBuffer.append(stringBuffer2);
                        }
                    } else if (stringBuffer2.length() > 0) {
                        stringBuffer.append(stringBuffer2);
                    }
                    z = false;
                }
                nextToken = decodeInnerWords;
                stringBuffer.append(nextToken);
                stringBuffer2.setLength(0);
            } else {
                stringBuffer2.append(charAt);
            }
        }
        stringBuffer.append(stringBuffer2);
        return stringBuffer.toString();
    }

    public static String decodeWord(String str) throws ParseException, UnsupportedEncodingException {
        InputStream qDecoderStream;
        if (str.startsWith("=?")) {
            int indexOf = str.indexOf(63, 2);
            if (indexOf != -1) {
                String substring = str.substring(2, indexOf);
                int indexOf2 = substring.indexOf(42);
                if (indexOf2 >= 0) {
                    substring = substring.substring(0, indexOf2);
                }
                String javaCharset = javaCharset(substring);
                int i = indexOf + 1;
                int indexOf3 = str.indexOf(63, i);
                if (indexOf3 != -1) {
                    String substring2 = str.substring(i, indexOf3);
                    int i2 = indexOf3 + 1;
                    int indexOf4 = str.indexOf("?=", i2);
                    if (indexOf4 != -1) {
                        String substring3 = str.substring(i2, indexOf4);
                        try {
                            String str2 = "";
                            if (substring3.length() > 0) {
                                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ASCIIUtility.getBytes(substring3));
                                if (substring2.equalsIgnoreCase("B")) {
                                    qDecoderStream = new BASE64DecoderStream(byteArrayInputStream);
                                } else if (substring2.equalsIgnoreCase("Q")) {
                                    qDecoderStream = new QDecoderStream(byteArrayInputStream);
                                } else {
                                    throw new UnsupportedEncodingException("unknown encoding: " + substring2);
                                }
                                int available = byteArrayInputStream.available();
                                byte[] bArr = new byte[available];
                                int read = qDecoderStream.read(bArr, 0, available);
                                if (read > 0) {
                                    str2 = new String(bArr, 0, read, javaCharset);
                                }
                            }
                            int i3 = indexOf4 + 2;
                            if (i3 >= str.length()) {
                                return str2;
                            }
                            String substring4 = str.substring(i3);
                            if (!decodeStrict) {
                                substring4 = decodeInnerWords(substring4);
                            }
                            return str2 + substring4;
                        } catch (UnsupportedEncodingException e) {
                            throw e;
                        } catch (IOException e2) {
                            throw new ParseException(e2.toString());
                        } catch (IllegalArgumentException unused) {
                            throw new UnsupportedEncodingException(javaCharset);
                        }
                    }
                    throw new ParseException(GeneratedOutlineSupport1.outline72("encoded word does not end with \"?=\": ", str));
                }
                throw new ParseException(GeneratedOutlineSupport1.outline72("encoded word does not include encoding: ", str));
            }
            throw new ParseException(GeneratedOutlineSupport1.outline72("encoded word does not include charset: ", str));
        }
        throw new ParseException(GeneratedOutlineSupport1.outline72("encoded word does not start with \"=?\": ", str));
    }

    private static void doEncode(String str, boolean z, String str2, int i, String str3, boolean z2, boolean z3, StringBuffer stringBuffer) throws UnsupportedEncodingException {
        int encodedLength;
        OutputStream qEncoderStream;
        int length;
        byte[] bytes = str.getBytes(str2);
        if (z) {
            encodedLength = BEncoderStream.encodedLength(bytes);
        } else {
            encodedLength = QEncoderStream.encodedLength(bytes, z3);
        }
        if (encodedLength > i && (length = str.length()) > 1) {
            int i2 = length / 2;
            if (Character.isHighSurrogate(str.charAt(i2 - 1))) {
                i2--;
            }
            int i3 = i2;
            if (i3 > 0) {
                doEncode(str.substring(0, i3), z, str2, i, str3, z2, z3, stringBuffer);
            }
            doEncode(str.substring(i3, length), z, str2, i, str3, false, z3, stringBuffer);
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (z) {
            qEncoderStream = new BEncoderStream(byteArrayOutputStream);
        } else {
            qEncoderStream = new QEncoderStream(byteArrayOutputStream, z3);
        }
        try {
            qEncoderStream.write(bytes);
            qEncoderStream.close();
        } catch (IOException unused) {
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (!z2) {
            if (foldEncodedWords) {
                stringBuffer.append("\r\n ");
            } else {
                stringBuffer.append(" ");
            }
        }
        stringBuffer.append(str3);
        for (byte b : byteArray) {
            stringBuffer.append((char) b);
        }
        stringBuffer.append("?=");
    }

    public static OutputStream encode(OutputStream outputStream, String str) throws MessagingException {
        if (str == null) {
            return outputStream;
        }
        if (str.equalsIgnoreCase("base64")) {
            return new BASE64EncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("quoted-printable")) {
            return new QPEncoderStream(outputStream);
        }
        if (!str.equalsIgnoreCase("uuencode") && !str.equalsIgnoreCase("x-uuencode") && !str.equalsIgnoreCase("x-uue")) {
            if (!str.equalsIgnoreCase("binary") && !str.equalsIgnoreCase("7bit") && !str.equalsIgnoreCase("8bit")) {
                throw new MessagingException(GeneratedOutlineSupport1.outline72("Unknown encoding: ", str));
            }
            return outputStream;
        }
        return new UUEncoderStream(outputStream);
    }

    public static String encodeText(String str) throws UnsupportedEncodingException {
        return encodeText(str, null, null);
    }

    public static String encodeWord(String str) throws UnsupportedEncodingException {
        return encodeWord(str, null, null);
    }

    public static String fold(int i, String str) {
        char charAt;
        if (!foldText) {
            return str;
        }
        int length = str.length() - 1;
        while (length >= 0 && ((charAt = str.charAt(length)) == ' ' || charAt == '\t' || charAt == '\r' || charAt == '\n')) {
            length--;
        }
        if (length != str.length() - 1) {
            str = str.substring(0, length + 1);
        }
        if (str.length() + i <= 76) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 4);
        char c = 0;
        while (true) {
            if (str.length() + i <= 76) {
                break;
            }
            char c2 = c;
            int i2 = -1;
            int i3 = 0;
            while (i3 < str.length() && (i2 == -1 || i + i3 <= 76)) {
                char charAt2 = str.charAt(i3);
                if ((charAt2 == ' ' || charAt2 == '\t') && c2 != ' ' && c2 != '\t') {
                    i2 = i3;
                }
                i3++;
                c2 = charAt2;
            }
            if (i2 == -1) {
                stringBuffer.append(str);
                str = "";
                break;
            }
            stringBuffer.append(str.substring(0, i2));
            stringBuffer.append("\r\n");
            c = str.charAt(i2);
            stringBuffer.append(c);
            str = str.substring(i2 + 1);
            i = 1;
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static String getDefaultJavaCharset() {
        if (defaultJavaCharset == null) {
            String str = null;
            try {
                str = System.getProperty("mail.mime.charset");
            } catch (SecurityException unused) {
            }
            if (str != null && str.length() > 0) {
                defaultJavaCharset = javaCharset(str);
                return defaultJavaCharset;
            }
            try {
                defaultJavaCharset = System.getProperty("file.encoding", "8859_1");
            } catch (SecurityException unused2) {
                defaultJavaCharset = new InputStreamReader(new InputStream() { // from class: javax.mail.internet.MimeUtility.1NullInputStream
                    @Override // java.io.InputStream
                    public int read() {
                        return 0;
                    }
                }).getEncoding();
                if (defaultJavaCharset == null) {
                    defaultJavaCharset = "8859_1";
                }
            }
        }
        return defaultJavaCharset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDefaultMIMECharset() {
        if (defaultMIMECharset == null) {
            try {
                defaultMIMECharset = System.getProperty("mail.mime.charset");
            } catch (SecurityException unused) {
            }
        }
        if (defaultMIMECharset == null) {
            defaultMIMECharset = mimeCharset(getDefaultJavaCharset());
        }
        return defaultMIMECharset;
    }

    public static String getEncoding(DataSource dataSource) {
        String encoding;
        String str = "base64";
        if (!(dataSource instanceof EncodingAware) || (encoding = ((EncodingAware) dataSource).getEncoding()) == null) {
            InputStream inputStream = null;
            try {
                ContentType contentType = new ContentType(dataSource.getContentType());
                inputStream = dataSource.getInputStream();
                boolean match = contentType.match("text/*");
                int checkAscii = checkAscii(inputStream, -1, !match);
                if (checkAscii == 1) {
                    str = "7bit";
                } else if (checkAscii == 2) {
                    if (match) {
                        if (nonAsciiCharset(contentType)) {
                        }
                    }
                    str = "quoted-printable";
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                return str;
            } catch (Exception unused2) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return str;
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        }
        return encoding;
    }

    private static int indexOfAny(String str, String str2) {
        return indexOfAny(str, str2, 0);
    }

    public static String javaCharset(String str) {
        String str2;
        Hashtable hashtable = mime2java;
        return (hashtable == null || str == null || (str2 = (String) hashtable.get(str.toLowerCase(Locale.ENGLISH))) == null) ? str : str2;
    }

    private static void loadMappings(LineInputStream lineInputStream, Hashtable hashtable) {
        while (true) {
            try {
                String readLine = lineInputStream.readLine();
                if (readLine == null) {
                    return;
                }
                if (readLine.startsWith(FullScreenUpdaterUtilKt.DEFAULT_DATA) && readLine.endsWith(FullScreenUpdaterUtilKt.DEFAULT_DATA)) {
                    return;
                }
                if (readLine.trim().length() != 0 && !readLine.startsWith(MqttTopic.MULTI_LEVEL_WILDCARD)) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine, " \t");
                    try {
                        String nextToken = stringTokenizer.nextToken();
                        hashtable.put(nextToken.toLowerCase(Locale.ENGLISH), stringTokenizer.nextToken());
                    } catch (NoSuchElementException unused) {
                    }
                }
            } catch (IOException unused2) {
                return;
            }
        }
    }

    public static String mimeCharset(String str) {
        String str2;
        Hashtable hashtable = java2mime;
        return (hashtable == null || str == null || (str2 = (String) hashtable.get(str.toLowerCase(Locale.ENGLISH))) == null) ? str : str2;
    }

    private static boolean nonAsciiCharset(ContentType contentType) {
        Boolean bool;
        Boolean bool2;
        String parameter = contentType.getParameter(HttpAuthHeader.Parameters.Charset);
        boolean z = false;
        if (parameter == null) {
            return false;
        }
        String lowerCase = parameter.toLowerCase(Locale.ENGLISH);
        synchronized (nonAsciiCharsetMap) {
            bool = (Boolean) nonAsciiCharsetMap.get(lowerCase);
        }
        if (bool == null) {
            try {
                byte[] bytes = "\r\n".getBytes(lowerCase);
                if (bytes.length != 2 || bytes[0] != 13 || bytes[1] != 10) {
                    z = true;
                }
                bool2 = Boolean.valueOf(z);
            } catch (UnsupportedEncodingException unused) {
                bool2 = Boolean.FALSE;
            } catch (RuntimeException unused2) {
                bool2 = Boolean.TRUE;
            }
            bool = bool2;
            synchronized (nonAsciiCharsetMap) {
                nonAsciiCharsetMap.put(lowerCase, bool);
            }
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean nonascii(int i) {
        return i >= 127 || !(i >= 32 || i == 13 || i == 10 || i == 9);
    }

    public static String quote(String str, String str2) {
        char c = 0;
        int length = str == null ? 0 : str.length();
        if (length == 0) {
            return "\"\"";
        }
        int i = 0;
        boolean z = false;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != '\"' && charAt != '\\' && charAt != '\r' && charAt != '\n') {
                if (charAt < ' ' || charAt >= 127 || str2.indexOf(charAt) >= 0) {
                    z = true;
                }
                i++;
            } else {
                StringBuffer stringBuffer = new StringBuffer(length + 3);
                stringBuffer.append('\"');
                stringBuffer.append(str.substring(0, i));
                while (i < length) {
                    char charAt2 = str.charAt(i);
                    if ((charAt2 == '\"' || charAt2 == '\\' || charAt2 == '\r' || charAt2 == '\n') && (charAt2 != '\n' || c != '\r')) {
                        stringBuffer.append('\\');
                    }
                    stringBuffer.append(charAt2);
                    i++;
                    c = charAt2;
                }
                stringBuffer.append('\"');
                return stringBuffer.toString();
            }
        }
        if (!z) {
            return str;
        }
        StringBuffer stringBuffer2 = new StringBuffer(length + 2);
        stringBuffer2.append('\"');
        stringBuffer2.append(str);
        stringBuffer2.append('\"');
        return stringBuffer2.toString();
    }

    public static String unfold(String str) {
        char charAt;
        char charAt2;
        if (!foldText) {
            return str;
        }
        StringBuffer stringBuffer = null;
        while (true) {
            int indexOfAny = indexOfAny(str, "\r\n");
            if (indexOfAny < 0) {
                break;
            }
            int length = str.length();
            int i = indexOfAny + 1;
            if (i < length && str.charAt(i - 1) == '\r' && str.charAt(i) == '\n') {
                i++;
            }
            if (indexOfAny != 0) {
                int i2 = indexOfAny - 1;
                if (str.charAt(i2) == '\\') {
                    if (stringBuffer == null) {
                        stringBuffer = new StringBuffer(str.length());
                    }
                    stringBuffer.append(str.substring(0, i2));
                    stringBuffer.append(str.substring(indexOfAny, i));
                    str = str.substring(i);
                }
            }
            if (i < length && ((charAt = str.charAt(i)) == ' ' || charAt == '\t')) {
                while (true) {
                    i++;
                    if (i >= length || ((charAt2 = str.charAt(i)) != ' ' && charAt2 != '\t')) {
                        break;
                    }
                }
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(str.length());
                }
                if (indexOfAny != 0) {
                    stringBuffer.append(str.substring(0, indexOfAny));
                    stringBuffer.append(Chars.SPACE);
                }
                str = str.substring(i);
            } else {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(str.length());
                }
                stringBuffer.append(str.substring(0, i));
                str = str.substring(i);
            }
        }
        if (stringBuffer == null) {
            return str;
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static String encodeText(String str, String str2, String str3) throws UnsupportedEncodingException {
        return encodeWord(str, str2, str3, false);
    }

    public static String encodeWord(String str, String str2, String str3) throws UnsupportedEncodingException {
        return encodeWord(str, str2, str3, true);
    }

    private static int indexOfAny(String str, String str2, int i) {
        try {
            int length = str.length();
            while (i < length) {
                if (str2.indexOf(str.charAt(i)) >= 0) {
                    return i;
                }
                i++;
            }
        } catch (StringIndexOutOfBoundsException unused) {
        }
        return -1;
    }

    static int checkAscii(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        for (byte b : bArr) {
            if (nonascii(b & 255)) {
                i++;
            } else {
                i2++;
            }
        }
        if (i == 0) {
            return 1;
        }
        return i2 > i ? 2 : 3;
    }

    private static String encodeWord(String str, String str2, String str3, boolean z) throws UnsupportedEncodingException {
        String javaCharset;
        int checkAscii = checkAscii(str);
        boolean z2 = true;
        if (checkAscii == 1) {
            return str;
        }
        if (str2 == null) {
            javaCharset = getDefaultJavaCharset();
            str2 = getDefaultMIMECharset();
        } else {
            javaCharset = javaCharset(str2);
        }
        if (str3 == null) {
            str3 = checkAscii != 3 ? "Q" : "B";
        }
        if (!str3.equalsIgnoreCase("B")) {
            if (!str3.equalsIgnoreCase("Q")) {
                throw new UnsupportedEncodingException(GeneratedOutlineSupport1.outline72("Unknown transfer encoding: ", str3));
            }
            z2 = false;
        }
        boolean z3 = z2;
        StringBuffer stringBuffer = new StringBuffer();
        doEncode(str, z3, javaCharset, 68 - str2.length(), GeneratedOutlineSupport1.outline77("=?", str2, WebConstants.UriConstants.QUESTIONMARK_KEY, str3, WebConstants.UriConstants.QUESTIONMARK_KEY), true, z, stringBuffer);
        return stringBuffer.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static int checkAscii(java.io.InputStream r17, int r18, boolean r19) {
        /*
            r0 = r18
            boolean r1 = javax.mail.internet.MimeUtility.encodeEolStrict
            r3 = 0
            if (r1 == 0) goto Lb
            if (r19 == 0) goto Lb
            r1 = 1
            goto Lc
        Lb:
            r1 = r3
        Lc:
            r4 = 0
            r5 = -1
            r6 = 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L1c
            if (r0 != r5) goto L15
            goto L1a
        L15:
            int r4 = java.lang.Math.min(r0, r6)
            r6 = r4
        L1a:
            byte[] r4 = new byte[r6]
        L1c:
            r7 = r3
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
        L21:
            r12 = 3
            if (r0 == 0) goto L7f
            r13 = r17
            int r14 = r13.read(r4, r3, r6)     // Catch: java.io.IOException -> L7f
            if (r14 != r5) goto L2e
            goto L7f
        L2e:
            r15 = r3
            r16 = r11
            r11 = r9
            r9 = r8
            r8 = r7
            r7 = r15
        L35:
            if (r7 >= r14) goto L73
            r3 = r4[r7]     // Catch: java.io.IOException -> L6f
            r3 = r3 & 255(0xff, float:3.57E-43)
            r2 = 10
            r5 = 13
            if (r1 == 0) goto L4a
            if (r15 != r5) goto L45
            if (r3 != r2) goto L49
        L45:
            if (r15 == r5) goto L4a
            if (r3 != r2) goto L4a
        L49:
            r11 = 1
        L4a:
            if (r3 == r5) goto L59
            if (r3 != r2) goto L4f
            goto L59
        L4f:
            int r2 = r16 + 1
            r5 = 998(0x3e6, float:1.398E-42)
            r16 = r2
            if (r2 <= r5) goto L5b
            r10 = 1
            goto L5b
        L59:
            r16 = 0
        L5b:
            boolean r2 = nonascii(r3)     // Catch: java.io.IOException -> L6f
            if (r2 == 0) goto L67
            if (r19 == 0) goto L64
            return r12
        L64:
            int r9 = r9 + 1
            goto L69
        L67:
            int r8 = r8 + 1
        L69:
            int r7 = r7 + 1
            r15 = r3
            r3 = 0
            r5 = -1
            goto L35
        L6f:
            r7 = r8
            r8 = r9
            r9 = r11
            goto L7f
        L73:
            r2 = r5
            if (r0 == r2) goto L77
            int r0 = r0 - r14
        L77:
            r5 = r2
            r7 = r8
            r8 = r9
            r9 = r11
            r11 = r16
            r3 = 0
            goto L21
        L7f:
            if (r0 != 0) goto L84
            if (r19 == 0) goto L84
            return r12
        L84:
            r0 = 2
            if (r8 != 0) goto L8f
            if (r9 == 0) goto L8a
            return r12
        L8a:
            if (r10 == 0) goto L8d
            return r0
        L8d:
            r0 = 1
            return r0
        L8f:
            if (r7 <= r8) goto L92
            return r0
        L92:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeUtility.checkAscii(java.io.InputStream, int, boolean):int");
    }

    public static String getEncoding(DataHandler dataHandler) {
        if (dataHandler.getName() != null) {
            return getEncoding(dataHandler.getDataSource());
        }
        try {
            if (new ContentType(dataHandler.getContentType()).match("text/*")) {
                AsciiOutputStream asciiOutputStream = new AsciiOutputStream(false, false);
                try {
                    dataHandler.writeTo(asciiOutputStream);
                } catch (IOException unused) {
                }
                int ascii = asciiOutputStream.getAscii();
                if (ascii != 1) {
                    return ascii != 2 ? "base64" : "quoted-printable";
                }
            } else {
                AsciiOutputStream asciiOutputStream2 = new AsciiOutputStream(true, encodeEolStrict);
                try {
                    dataHandler.writeTo(asciiOutputStream2);
                } catch (IOException unused2) {
                }
                if (asciiOutputStream2.getAscii() != 1) {
                    return "base64";
                }
            }
            return "7bit";
        } catch (Exception unused3) {
            return "base64";
        }
    }

    public static OutputStream encode(OutputStream outputStream, String str, String str2) throws MessagingException {
        if (str == null) {
            return outputStream;
        }
        if (str.equalsIgnoreCase("base64")) {
            return new BASE64EncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("quoted-printable")) {
            return new QPEncoderStream(outputStream);
        }
        if (!str.equalsIgnoreCase("uuencode") && !str.equalsIgnoreCase("x-uuencode") && !str.equalsIgnoreCase("x-uue")) {
            if (!str.equalsIgnoreCase("binary") && !str.equalsIgnoreCase("7bit") && !str.equalsIgnoreCase("8bit")) {
                throw new MessagingException(GeneratedOutlineSupport1.outline72("Unknown encoding: ", str));
            }
            return outputStream;
        }
        return new UUEncoderStream(outputStream, str2);
    }
}
