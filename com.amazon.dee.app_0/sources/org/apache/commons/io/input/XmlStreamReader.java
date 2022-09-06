package org.apache.commons.io.input;

import com.amazonaws.services.s3.util.Mimetypes;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.ByteOrderMark;
/* loaded from: classes4.dex */
public class XmlStreamReader extends Reader {
    private static final int BUFFER_SIZE = 4096;
    private static final String HTTP_EX_1 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], BOM must be NULL";
    private static final String HTTP_EX_2 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], encoding mismatch";
    private static final String HTTP_EX_3 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], Invalid MIME";
    private static final String RAW_EX_1 = "Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch";
    private static final String RAW_EX_2 = "Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] unknown BOM";
    private static final String US_ASCII = "US-ASCII";
    private static final String UTF_16 = "UTF-16";
    private static final String UTF_16BE = "UTF-16BE";
    private static final String UTF_16LE = "UTF-16LE";
    private static final String UTF_8 = "UTF-8";
    private final String defaultEncoding;
    private final String encoding;
    private final Reader reader;
    private static final ByteOrderMark[] BOMS = {ByteOrderMark.UTF_8, ByteOrderMark.UTF_16BE, ByteOrderMark.UTF_16LE};
    private static final String EBCDIC = "CP1047";
    private static final ByteOrderMark[] XML_GUESS_BYTES = {new ByteOrderMark("UTF-8", 60, 63, 120, 109), new ByteOrderMark("UTF-16BE", 0, 60, 0, 63), new ByteOrderMark("UTF-16LE", 60, 0, 63, 0), new ByteOrderMark(EBCDIC, 76, 111, 167, 148)};
    private static final Pattern CHARSET_PATTERN = Pattern.compile("charset=[\"']?([.[^; \"']]*)[\"']?");
    public static final Pattern ENCODING_PATTERN = Pattern.compile("<\\?xml.*encoding[\\s]*=[\\s]*((?:\".[^\"]*\")|(?:'.[^']*'))", 8);

    public XmlStreamReader(File file) throws IOException {
        this(new FileInputStream(file));
    }

    private String doHttpStream(BOMInputStream bOMInputStream, BOMInputStream bOMInputStream2, String str, boolean z) throws IOException {
        String bOMCharsetName = bOMInputStream.getBOMCharsetName();
        String bOMCharsetName2 = bOMInputStream2.getBOMCharsetName();
        try {
            return calculateHttpEncoding(str, bOMCharsetName, bOMCharsetName2, getXmlProlog(bOMInputStream2, bOMCharsetName2), z);
        } catch (XmlStreamReaderException e) {
            if (z) {
                return doLenientDetection(str, e);
            }
            throw e;
        }
    }

    private String doLenientDetection(String str, XmlStreamReaderException xmlStreamReaderException) throws IOException {
        if (str != null && str.startsWith("text/html")) {
            try {
                return calculateHttpEncoding(GeneratedOutlineSupport1.outline72("text/xml", str.substring(9)), xmlStreamReaderException.getBomEncoding(), xmlStreamReaderException.getXmlGuessEncoding(), xmlStreamReaderException.getXmlEncoding(), true);
            } catch (XmlStreamReaderException e) {
                xmlStreamReaderException = e;
            }
        }
        String xmlEncoding = xmlStreamReaderException.getXmlEncoding();
        if (xmlEncoding == null) {
            xmlEncoding = xmlStreamReaderException.getContentTypeEncoding();
        }
        if (xmlEncoding == null) {
            String str2 = this.defaultEncoding;
            return str2 == null ? "UTF-8" : str2;
        }
        return xmlEncoding;
    }

    private String doRawStream(BOMInputStream bOMInputStream, BOMInputStream bOMInputStream2, boolean z) throws IOException {
        String bOMCharsetName = bOMInputStream.getBOMCharsetName();
        String bOMCharsetName2 = bOMInputStream2.getBOMCharsetName();
        try {
            return calculateRawEncoding(bOMCharsetName, bOMCharsetName2, getXmlProlog(bOMInputStream2, bOMCharsetName2));
        } catch (XmlStreamReaderException e) {
            if (z) {
                return doLenientDetection(null, e);
            }
            throw e;
        }
    }

    static String getContentTypeEncoding(String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(";")) <= -1) {
            return null;
        }
        Matcher matcher = CHARSET_PATTERN.matcher(str.substring(indexOf + 1));
        String group = matcher.find() ? matcher.group(1) : null;
        if (group == null) {
            return null;
        }
        return group.toUpperCase(Locale.US);
    }

    static String getContentTypeMime(String str) {
        if (str != null) {
            int indexOf = str.indexOf(";");
            if (indexOf >= 0) {
                str = str.substring(0, indexOf);
            }
            return str.trim();
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002e, code lost:
        if (r4 != (-1)) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
        throw new java.io.IOException("Unexpected end of XML stream");
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0045, code lost:
        throw new java.io.IOException(com.android.tools.r8.GeneratedOutlineSupport1.outline52("XML prolog or ROOT element not found on first ", r7, " bytes"));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getXmlProlog(java.io.InputStream r10, java.lang.String r11) throws java.io.IOException {
        /*
            r0 = 0
            if (r11 == 0) goto L86
            r1 = 4096(0x1000, float:5.74E-42)
            byte[] r2 = new byte[r1]
            r10.mark(r1)
            r3 = 0
            int r4 = r10.read(r2, r3, r1)
            r5 = -1
            r8 = r0
            r9 = r1
            r7 = r3
            r6 = r5
        L14:
            if (r4 == r5) goto L2c
            if (r6 != r5) goto L2c
            if (r7 >= r1) goto L2c
            int r7 = r7 + r4
            int r9 = r9 - r4
            int r4 = r10.read(r2, r7, r9)
            java.lang.String r8 = new java.lang.String
            r8.<init>(r2, r3, r7, r11)
            r6 = 62
            int r6 = r8.indexOf(r6)
            goto L14
        L2c:
            if (r6 != r5) goto L46
            if (r4 != r5) goto L38
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r11 = "Unexpected end of XML stream"
            r10.<init>(r11)
            throw r10
        L38:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r11 = "XML prolog or ROOT element not found on first "
            java.lang.String r0 = " bytes"
            java.lang.String r11 = com.android.tools.r8.GeneratedOutlineSupport1.outline52(r11, r7, r0)
            r10.<init>(r11)
            throw r10
        L46:
            if (r7 <= 0) goto L86
            r10.reset()
            java.io.BufferedReader r10 = new java.io.BufferedReader
            java.io.StringReader r11 = new java.io.StringReader
            r1 = 1
            int r6 = r6 + r1
            java.lang.String r2 = r8.substring(r3, r6)
            r11.<init>(r2)
            r10.<init>(r11)
            java.lang.StringBuffer r11 = new java.lang.StringBuffer
            r11.<init>()
            java.lang.String r2 = r10.readLine()
        L64:
            if (r2 == 0) goto L6e
            r11.append(r2)
            java.lang.String r2 = r10.readLine()
            goto L64
        L6e:
            java.util.regex.Pattern r10 = org.apache.commons.io.input.XmlStreamReader.ENCODING_PATTERN
            java.util.regex.Matcher r10 = r10.matcher(r11)
            boolean r11 = r10.find()
            if (r11 == 0) goto L86
            java.lang.String r10 = r10.group(r1)
            java.lang.String r10 = r10.toUpperCase()
            java.lang.String r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline51(r10, r1, r1)
        L86:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.input.XmlStreamReader.getXmlProlog(java.io.InputStream, java.lang.String):java.lang.String");
    }

    static boolean isAppXml(String str) {
        return str != null && (str.equals(Mimetypes.MIMETYPE_XML) || str.equals("application/xml-dtd") || str.equals("application/xml-external-parsed-entity") || (str.startsWith("application/") && str.endsWith("+xml")));
    }

    static boolean isTextXml(String str) {
        return str != null && (str.equals("text/xml") || str.equals("text/xml-external-parsed-entity") || (str.startsWith("text/") && str.endsWith("+xml")));
    }

    String calculateHttpEncoding(String str, String str2, String str3, String str4, boolean z) throws IOException {
        if (!z || str4 == null) {
            String contentTypeMime = getContentTypeMime(str);
            String contentTypeEncoding = getContentTypeEncoding(str);
            boolean isAppXml = isAppXml(contentTypeMime);
            boolean isTextXml = isTextXml(contentTypeMime);
            if (!isAppXml && !isTextXml) {
                throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_3, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
            }
            if (contentTypeEncoding == null) {
                if (isAppXml) {
                    return calculateRawEncoding(str2, str3, str4);
                }
                String str5 = this.defaultEncoding;
                return str5 == null ? "US-ASCII" : str5;
            } else if (contentTypeEncoding.equals("UTF-16BE") || contentTypeEncoding.equals("UTF-16LE")) {
                if (str2 != null) {
                    throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_1, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
                }
                return contentTypeEncoding;
            } else if (!contentTypeEncoding.equals("UTF-16")) {
                return contentTypeEncoding;
            } else {
                if (str2 != null && str2.startsWith("UTF-16")) {
                    return str2;
                }
                throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_2, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
            }
        }
        return str4;
    }

    String calculateRawEncoding(String str, String str2, String str3) throws IOException {
        if (str == null) {
            if (str2 != null && str3 != null) {
                return (!str3.equals("UTF-16") || (!str2.equals("UTF-16BE") && !str2.equals("UTF-16LE"))) ? str3 : str2;
            }
            String str4 = this.defaultEncoding;
            return str4 == null ? "UTF-8" : str4;
        } else if (str.equals("UTF-8")) {
            if (str2 != null && !str2.equals("UTF-8")) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
            if (str3 != null && !str3.equals("UTF-8")) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
            return str;
        } else if (!str.equals("UTF-16BE") && !str.equals("UTF-16LE")) {
            throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_2, str, str2, str3), str, str2, str3);
        } else {
            if (str2 != null && !str2.equals(str)) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
            if (str3 != null && !str3.equals("UTF-16") && !str3.equals(str)) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
            return str;
        }
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }

    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }

    public String getEncoding() {
        return this.encoding;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        return this.reader.read(cArr, i, i2);
    }

    public XmlStreamReader(InputStream inputStream) throws IOException {
        this(inputStream, true);
    }

    public XmlStreamReader(InputStream inputStream, boolean z) throws IOException {
        this(inputStream, z, (String) null);
    }

    public XmlStreamReader(InputStream inputStream, boolean z, String str) throws IOException {
        this.defaultEncoding = str;
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream(inputStream, 4096), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        this.encoding = doRawStream(bOMInputStream, bOMInputStream2, z);
        this.reader = new InputStreamReader(bOMInputStream2, this.encoding);
    }

    public XmlStreamReader(URL url) throws IOException {
        this(url.openConnection(), (String) null);
    }

    public XmlStreamReader(URLConnection uRLConnection, String str) throws IOException {
        this.defaultEncoding = str;
        String contentType = uRLConnection.getContentType();
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream(uRLConnection.getInputStream(), 4096), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        if (!(uRLConnection instanceof HttpURLConnection) && contentType == null) {
            this.encoding = doRawStream(bOMInputStream, bOMInputStream2, true);
        } else {
            this.encoding = doHttpStream(bOMInputStream, bOMInputStream2, contentType, true);
        }
        this.reader = new InputStreamReader(bOMInputStream2, this.encoding);
    }

    public XmlStreamReader(InputStream inputStream, String str) throws IOException {
        this(inputStream, str, true);
    }

    public XmlStreamReader(InputStream inputStream, String str, boolean z, String str2) throws IOException {
        this.defaultEncoding = str2;
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream(inputStream, 4096), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        this.encoding = doHttpStream(bOMInputStream, bOMInputStream2, str, z);
        this.reader = new InputStreamReader(bOMInputStream2, this.encoding);
    }

    public XmlStreamReader(InputStream inputStream, String str, boolean z) throws IOException {
        this(inputStream, str, z, null);
    }
}
