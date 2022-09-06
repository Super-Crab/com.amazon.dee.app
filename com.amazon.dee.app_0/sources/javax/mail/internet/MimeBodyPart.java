package javax.mail.internet;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.FolderClosedIOException;
import com.sun.mail.util.LineOutputStream;
import com.sun.mail.util.MessageRemovedIOException;
import com.sun.mail.util.MimeUtil;
import com.sun.mail.util.PropUtil;
import io.ktor.http.ContentDisposition;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.EncodingAware;
import javax.mail.FolderClosedException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.HeaderTokenizer;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes3.dex */
public class MimeBodyPart extends BodyPart implements MimePart {
    protected Object cachedContent;
    protected byte[] content;
    protected InputStream contentStream;
    protected DataHandler dh;
    protected InternetHeaders headers;
    private static final boolean setDefaultTextCharset = PropUtil.getBooleanSystemProperty("mail.mime.setdefaulttextcharset", true);
    private static final boolean setContentTypeFileName = PropUtil.getBooleanSystemProperty("mail.mime.setcontenttypefilename", true);
    private static final boolean encodeFileName = PropUtil.getBooleanSystemProperty("mail.mime.encodefilename", false);
    private static final boolean decodeFileName = PropUtil.getBooleanSystemProperty("mail.mime.decodefilename", false);
    private static final boolean ignoreMultipartEncoding = PropUtil.getBooleanSystemProperty("mail.mime.ignoremultipartencoding", true);
    static final boolean cacheMultipart = PropUtil.getBooleanSystemProperty("mail.mime.cachemultipart", true);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class EncodedFileDataSource extends FileDataSource implements EncodingAware {
        private String contentType;
        private String encoding;

        public EncodedFileDataSource(File file, String str, String str2) {
            super(file);
            this.contentType = str;
            this.encoding = str2;
        }

        public String getContentType() {
            String str = this.contentType;
            return str != null ? str : super.getContentType();
        }

        @Override // javax.mail.EncodingAware
        public String getEncoding() {
            return this.encoding;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class MimePartDataHandler extends DataHandler {
        MimePart part;

        public MimePartDataHandler(MimePart mimePart) {
            super(new MimePartDataSource(mimePart));
            this.part = mimePart;
        }

        InputStream getContentStream() throws MessagingException {
            MimePart mimePart = this.part;
            if (mimePart instanceof MimeBodyPart) {
                return ((MimeBodyPart) mimePart).getContentStream();
            }
            if (!(mimePart instanceof MimeMessage)) {
                return null;
            }
            return ((MimeMessage) mimePart).getContentStream();
        }

        MimePart getPart() {
            return this.part;
        }
    }

    public MimeBodyPart() {
        this.headers = new InternetHeaders();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invalidateContentHeaders(MimePart mimePart) throws MessagingException {
        mimePart.removeHeader("Content-Type");
        mimePart.removeHeader("Content-Transfer-Encoding");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String restrictEncoding(MimePart mimePart, String str) throws MessagingException {
        String contentType;
        ContentType contentType2;
        if (!ignoreMultipartEncoding || str == null || str.equalsIgnoreCase("7bit") || str.equalsIgnoreCase("8bit") || str.equalsIgnoreCase("binary") || (contentType = mimePart.getContentType()) == null) {
            return str;
        }
        try {
            contentType2 = new ContentType(contentType);
        } catch (ParseException unused) {
        }
        if (contentType2.match("multipart/*")) {
            return null;
        }
        if (contentType2.match("message/*")) {
            if (!PropUtil.getBooleanSystemProperty("mail.mime.allowencodedmessages", false)) {
                return null;
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setEncoding(MimePart mimePart, String str) throws MessagingException {
        mimePart.setHeader("Content-Transfer-Encoding", str);
    }

    @Override // javax.mail.Part
    public void addHeader(String str, String str2) throws MessagingException {
        this.headers.addHeader(str, str2);
    }

    public void addHeaderLine(String str) throws MessagingException {
        this.headers.addHeaderLine(str);
    }

    public void attachFile(File file) throws IOException, MessagingException {
        FileDataSource fileDataSource = new FileDataSource(file);
        setDataHandler(new DataHandler(fileDataSource));
        setFileName(fileDataSource.getName());
        setDisposition("attachment");
    }

    public Enumeration getAllHeaderLines() throws MessagingException {
        return this.headers.getAllHeaderLines();
    }

    @Override // javax.mail.Part
    public Enumeration getAllHeaders() throws MessagingException {
        return this.headers.getAllHeaders();
    }

    @Override // javax.mail.Part
    public Object getContent() throws IOException, MessagingException {
        Object obj = this.cachedContent;
        if (obj != null) {
            return obj;
        }
        try {
            Object content = getDataHandler().getContent();
            if (cacheMultipart && (((content instanceof Multipart) || (content instanceof Message)) && (this.content != null || this.contentStream != null))) {
                this.cachedContent = content;
                if (content instanceof MimeMultipart) {
                    ((MimeMultipart) content).parse();
                }
            }
            return content;
        } catch (FolderClosedIOException e) {
            throw new FolderClosedException(e.getFolder(), e.getMessage());
        } catch (MessageRemovedIOException e2) {
            throw new MessageRemovedException(e2.getMessage());
        }
    }

    public String getContentID() throws MessagingException {
        return getHeader("Content-Id", null);
    }

    @Override // javax.mail.internet.MimePart
    public String[] getContentLanguage() throws MessagingException {
        return getContentLanguage(this);
    }

    public String getContentMD5() throws MessagingException {
        return getHeader("Content-MD5", null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public InputStream getContentStream() throws MessagingException {
        InputStream inputStream = this.contentStream;
        if (inputStream != null) {
            return ((SharedInputStream) inputStream).newStream(0L, -1L);
        }
        byte[] bArr = this.content;
        if (bArr != null) {
            return new ByteArrayInputStream(bArr);
        }
        throw new MessagingException("No MimeBodyPart content");
    }

    @Override // javax.mail.Part
    public String getContentType() throws MessagingException {
        String cleanContentType = MimeUtil.cleanContentType(this, getHeader("Content-Type", null));
        return cleanContentType == null ? Constants.TEXT_PLAIN_MEDIA_TYPE : cleanContentType;
    }

    @Override // javax.mail.Part
    public DataHandler getDataHandler() throws MessagingException {
        if (this.dh == null) {
            this.dh = new MimePartDataHandler(this);
        }
        return this.dh;
    }

    @Override // javax.mail.Part
    public String getDescription() throws MessagingException {
        return getDescription(this);
    }

    @Override // javax.mail.Part
    public String getDisposition() throws MessagingException {
        return getDisposition(this);
    }

    public String getEncoding() throws MessagingException {
        return getEncoding(this);
    }

    @Override // javax.mail.Part
    public String getFileName() throws MessagingException {
        return getFileName(this);
    }

    @Override // javax.mail.Part
    public String[] getHeader(String str) throws MessagingException {
        return this.headers.getHeader(str);
    }

    @Override // javax.mail.Part
    public InputStream getInputStream() throws IOException, MessagingException {
        return getDataHandler().getInputStream();
    }

    @Override // javax.mail.Part
    public int getLineCount() throws MessagingException {
        return -1;
    }

    public Enumeration getMatchingHeaderLines(String[] strArr) throws MessagingException {
        return this.headers.getMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.Part
    public Enumeration getMatchingHeaders(String[] strArr) throws MessagingException {
        return this.headers.getMatchingHeaders(strArr);
    }

    public Enumeration getNonMatchingHeaderLines(String[] strArr) throws MessagingException {
        return this.headers.getNonMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.Part
    public Enumeration getNonMatchingHeaders(String[] strArr) throws MessagingException {
        return this.headers.getNonMatchingHeaders(strArr);
    }

    public InputStream getRawInputStream() throws MessagingException {
        return getContentStream();
    }

    @Override // javax.mail.Part
    public int getSize() throws MessagingException {
        byte[] bArr = this.content;
        if (bArr != null) {
            return bArr.length;
        }
        InputStream inputStream = this.contentStream;
        if (inputStream == null) {
            return -1;
        }
        try {
            int available = inputStream.available();
            if (available <= 0) {
                return -1;
            }
            return available;
        } catch (IOException unused) {
            return -1;
        }
    }

    @Override // javax.mail.Part
    public boolean isMimeType(String str) throws MessagingException {
        return isMimeType(this, str);
    }

    @Override // javax.mail.Part
    public void removeHeader(String str) throws MessagingException {
        this.headers.removeHeader(str);
    }

    public void saveFile(File file) throws IOException, MessagingException {
        BufferedOutputStream bufferedOutputStream;
        InputStream inputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                inputStream = getInputStream();
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        bufferedOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException unused) {
                        }
                    }
                }
                inputStream.close();
                try {
                    bufferedOutputStream.close();
                } catch (IOException unused2) {
                }
            } catch (Throwable th) {
                th = th;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream = null;
        }
    }

    @Override // javax.mail.Part
    public void setContent(Object obj, String str) throws MessagingException {
        if (obj instanceof Multipart) {
            setContent((Multipart) obj);
        } else {
            setDataHandler(new DataHandler(obj, str));
        }
    }

    public void setContentID(String str) throws MessagingException {
        if (str == null) {
            removeHeader("Content-ID");
        } else {
            setHeader("Content-ID", str);
        }
    }

    @Override // javax.mail.internet.MimePart
    public void setContentLanguage(String[] strArr) throws MessagingException {
        setContentLanguage(this, strArr);
    }

    public void setContentMD5(String str) throws MessagingException {
        setHeader("Content-MD5", str);
    }

    @Override // javax.mail.Part
    public void setDataHandler(DataHandler dataHandler) throws MessagingException {
        this.dh = dataHandler;
        this.cachedContent = null;
        invalidateContentHeaders(this);
    }

    @Override // javax.mail.Part
    public void setDescription(String str) throws MessagingException {
        setDescription(str, null);
    }

    @Override // javax.mail.Part
    public void setDisposition(String str) throws MessagingException {
        setDisposition(this, str);
    }

    @Override // javax.mail.Part
    public void setFileName(String str) throws MessagingException {
        setFileName(this, str);
    }

    @Override // javax.mail.Part
    public void setHeader(String str, String str2) throws MessagingException {
        this.headers.setHeader(str, str2);
    }

    @Override // javax.mail.Part, javax.mail.internet.MimePart
    public void setText(String str) throws MessagingException {
        setText(str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateHeaders() throws MessagingException {
        updateHeaders(this);
        Object obj = this.cachedContent;
        if (obj != null) {
            this.dh = new DataHandler(obj, getContentType());
            this.cachedContent = null;
            this.content = null;
            InputStream inputStream = this.contentStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
            this.contentStream = null;
        }
    }

    @Override // javax.mail.Part
    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        writeTo(this, outputStream, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] getContentLanguage(MimePart mimePart) throws MessagingException {
        String header = mimePart.getHeader("Content-Language", null);
        if (header == null) {
            return null;
        }
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(header, HeaderTokenizer.MIME);
        Vector vector = new Vector();
        while (true) {
            HeaderTokenizer.Token next = headerTokenizer.next();
            int type = next.getType();
            if (type == -4) {
                break;
            } else if (type == -1) {
                vector.addElement(next.getValue());
            }
        }
        if (vector.size() == 0) {
            return null;
        }
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDescription(MimePart mimePart) throws MessagingException {
        String header = mimePart.getHeader("Content-Description", null);
        if (header == null) {
            return null;
        }
        try {
            return MimeUtility.decodeText(MimeUtility.unfold(header));
        } catch (UnsupportedEncodingException unused) {
            return header;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDisposition(MimePart mimePart) throws MessagingException {
        String header = mimePart.getHeader("Content-Disposition", null);
        if (header == null) {
            return null;
        }
        return new ContentDisposition(header).getDisposition();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getEncoding(MimePart mimePart) throws MessagingException {
        HeaderTokenizer.Token next;
        int type;
        String header = mimePart.getHeader("Content-Transfer-Encoding", null);
        if (header == null) {
            return null;
        }
        String trim = header.trim();
        if (trim.equalsIgnoreCase("7bit") || trim.equalsIgnoreCase("8bit") || trim.equalsIgnoreCase("quoted-printable") || trim.equalsIgnoreCase("binary") || trim.equalsIgnoreCase("base64")) {
            return trim;
        }
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(trim, HeaderTokenizer.MIME);
        do {
            next = headerTokenizer.next();
            type = next.getType();
            if (type == -4) {
                return trim;
            }
        } while (type != -1);
        return next.getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getFileName(MimePart mimePart) throws MessagingException {
        String cleanContentType;
        String header = mimePart.getHeader("Content-Disposition", null);
        String parameter = header != null ? new ContentDisposition(header).getParameter(ContentDisposition.Parameters.FileName) : null;
        if (parameter == null && (cleanContentType = MimeUtil.cleanContentType(mimePart, mimePart.getHeader("Content-Type", null))) != null) {
            try {
                parameter = new ContentType(cleanContentType).getParameter("name");
            } catch (ParseException unused) {
            }
        }
        if (!decodeFileName || parameter == null) {
            return parameter;
        }
        try {
            return MimeUtility.decodeText(parameter);
        } catch (UnsupportedEncodingException e) {
            throw new MessagingException("Can't decode filename", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isMimeType(MimePart mimePart, String str) throws MessagingException {
        try {
            return new ContentType(mimePart.getContentType()).match(str);
        } catch (ParseException unused) {
            return mimePart.getContentType().equalsIgnoreCase(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setContentLanguage(MimePart mimePart, String[] strArr) throws MessagingException {
        StringBuffer stringBuffer = new StringBuffer(strArr[0]);
        int length = strArr[0].length() + 18;
        for (int i = 1; i < strArr.length; i++) {
            stringBuffer.append(JsonReaderKt.COMMA);
            int i2 = length + 1;
            if (i2 > 76) {
                stringBuffer.append("\r\n\t");
                i2 = 8;
            }
            stringBuffer.append(strArr[i]);
            length = i2 + strArr[i].length();
        }
        mimePart.setHeader("Content-Language", stringBuffer.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setDisposition(MimePart mimePart, String str) throws MessagingException {
        if (str == null) {
            mimePart.removeHeader("Content-Disposition");
            return;
        }
        String header = mimePart.getHeader("Content-Disposition", null);
        if (header != null) {
            ContentDisposition contentDisposition = new ContentDisposition(header);
            contentDisposition.setDisposition(str);
            str = contentDisposition.toString();
        }
        mimePart.setHeader("Content-Disposition", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setFileName(MimePart mimePart, String str) throws MessagingException {
        String cleanContentType;
        if (encodeFileName && str != null) {
            try {
                str = MimeUtility.encodeText(str);
            } catch (UnsupportedEncodingException e) {
                throw new MessagingException("Can't encode filename", e);
            }
        }
        String header = mimePart.getHeader("Content-Disposition", null);
        if (header == null) {
            header = "attachment";
        }
        ContentDisposition contentDisposition = new ContentDisposition(header);
        String defaultMIMECharset = MimeUtility.getDefaultMIMECharset();
        ParameterList parameterList = contentDisposition.getParameterList();
        if (parameterList == null) {
            parameterList = new ParameterList();
            contentDisposition.setParameterList(parameterList);
        }
        parameterList.set(ContentDisposition.Parameters.FileName, str, defaultMIMECharset);
        mimePart.setHeader("Content-Disposition", contentDisposition.toString());
        if (!setContentTypeFileName || (cleanContentType = MimeUtil.cleanContentType(mimePart, mimePart.getHeader("Content-Type", null))) == null) {
            return;
        }
        try {
            ContentType contentType = new ContentType(cleanContentType);
            ParameterList parameterList2 = contentType.getParameterList();
            if (parameterList2 == null) {
                parameterList2 = new ParameterList();
                contentType.setParameterList(parameterList2);
            }
            parameterList2.set("name", str, defaultMIMECharset);
            mimePart.setHeader("Content-Type", contentType.toString());
        } catch (ParseException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeTo(MimePart mimePart, OutputStream outputStream, String[] strArr) throws IOException, MessagingException {
        LineOutputStream lineOutputStream;
        if (outputStream instanceof LineOutputStream) {
            lineOutputStream = (LineOutputStream) outputStream;
        } else {
            lineOutputStream = new LineOutputStream(outputStream);
        }
        Enumeration nonMatchingHeaderLines = mimePart.getNonMatchingHeaderLines(strArr);
        while (nonMatchingHeaderLines.hasMoreElements()) {
            lineOutputStream.writeln((String) nonMatchingHeaderLines.nextElement());
        }
        lineOutputStream.writeln();
        InputStream inputStream = null;
        try {
            DataHandler dataHandler = mimePart.getDataHandler();
            if (dataHandler instanceof MimePartDataHandler) {
                MimePartDataHandler mimePartDataHandler = (MimePartDataHandler) dataHandler;
                if (mimePartDataHandler.getPart().getEncoding() != null) {
                    inputStream = mimePartDataHandler.getContentStream();
                }
            }
            if (inputStream != null) {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                }
            } else {
                outputStream = MimeUtility.encode(outputStream, restrictEncoding(mimePart, mimePart.getEncoding()));
                mimePart.getDataHandler().writeTo(outputStream);
            }
            outputStream.flush();
        } finally {
            if (0 != 0) {
                inputStream.close();
            }
        }
    }

    @Override // javax.mail.internet.MimePart
    public String getHeader(String str, String str2) throws MessagingException {
        return this.headers.getHeader(str, str2);
    }

    public void setDescription(String str, String str2) throws MessagingException {
        setDescription(this, str, str2);
    }

    @Override // javax.mail.internet.MimePart
    public void setText(String str, String str2) throws MessagingException {
        setText(this, str, str2, "plain");
    }

    public MimeBodyPart(InputStream inputStream) throws MessagingException {
        if (!(inputStream instanceof ByteArrayInputStream) && !(inputStream instanceof BufferedInputStream) && !(inputStream instanceof SharedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        this.headers = new InternetHeaders(inputStream);
        if (inputStream instanceof SharedInputStream) {
            SharedInputStream sharedInputStream = (SharedInputStream) inputStream;
            this.contentStream = sharedInputStream.newStream(sharedInputStream.getPosition(), -1L);
            return;
        }
        try {
            this.content = ASCIIUtility.getBytes(inputStream);
        } catch (IOException e) {
            throw new MessagingException("Error reading input stream", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setDescription(MimePart mimePart, String str, String str2) throws MessagingException {
        if (str == null) {
            mimePart.removeHeader("Content-Description");
            return;
        }
        try {
            mimePart.setHeader("Content-Description", MimeUtility.fold(21, MimeUtility.encodeText(str, str2, null)));
        } catch (UnsupportedEncodingException e) {
            throw new MessagingException("Encoding error", e);
        }
    }

    @Override // javax.mail.internet.MimePart
    public void setText(String str, String str2, String str3) throws MessagingException {
        setText(this, str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setText(MimePart mimePart, String str, String str2, String str3) throws MessagingException {
        if (str2 == null) {
            str2 = MimeUtility.checkAscii(str) != 1 ? MimeUtility.getDefaultMIMECharset() : "us-ascii";
        }
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("text/", str3, "; charset=");
        outline115.append(MimeUtility.quote(str2, HeaderTokenizer.MIME));
        mimePart.setContent(str, outline115.toString());
    }

    @Override // javax.mail.Part
    public void setContent(Multipart multipart) throws MessagingException {
        setDataHandler(new DataHandler(multipart, multipart.getContentType()));
        multipart.setParent(this);
    }

    public void attachFile(String str) throws IOException, MessagingException {
        attachFile(new File(str));
    }

    public void attachFile(File file, String str, String str2) throws IOException, MessagingException {
        EncodedFileDataSource encodedFileDataSource = new EncodedFileDataSource(file, str, str2);
        setDataHandler(new DataHandler(encodedFileDataSource));
        setFileName(encodedFileDataSource.getName());
        setDisposition("attachment");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateHeaders(MimePart mimePart) throws MessagingException {
        String header;
        String parameter;
        Object content;
        DataHandler dataHandler = mimePart.getDataHandler();
        if (dataHandler == null) {
            return;
        }
        try {
            String contentType = dataHandler.getContentType();
            boolean z = true;
            boolean z2 = mimePart.getHeader("Content-Type") == null;
            ContentType contentType2 = new ContentType(contentType);
            if (contentType2.match("multipart/*")) {
                if (mimePart instanceof MimeBodyPart) {
                    MimeBodyPart mimeBodyPart = (MimeBodyPart) mimePart;
                    content = mimeBodyPart.cachedContent != null ? mimeBodyPart.cachedContent : dataHandler.getContent();
                } else if (mimePart instanceof MimeMessage) {
                    MimeMessage mimeMessage = (MimeMessage) mimePart;
                    content = mimeMessage.cachedContent != null ? mimeMessage.cachedContent : dataHandler.getContent();
                } else {
                    content = dataHandler.getContent();
                }
                if (content instanceof MimeMultipart) {
                    ((MimeMultipart) content).updateHeaders();
                } else {
                    throw new MessagingException("MIME part of type \"" + contentType + "\" contains object of type " + content.getClass().getName() + " instead of MimeMultipart");
                }
            } else if (!contentType2.match("message/rfc822")) {
                z = false;
            }
            if (dataHandler instanceof MimePartDataHandler) {
                MimePart part = ((MimePartDataHandler) dataHandler).getPart();
                if (part == mimePart) {
                    return;
                }
                if (z2) {
                    mimePart.setHeader("Content-Type", part.getContentType());
                }
                String encoding = part.getEncoding();
                if (encoding != null) {
                    setEncoding(mimePart, encoding);
                    return;
                }
            }
            if (!z) {
                if (mimePart.getHeader("Content-Transfer-Encoding") == null) {
                    setEncoding(mimePart, MimeUtility.getEncoding(dataHandler));
                }
                if (z2 && setDefaultTextCharset && contentType2.match("text/*") && contentType2.getParameter(HttpAuthHeader.Parameters.Charset) == null) {
                    String encoding2 = mimePart.getEncoding();
                    contentType2.setParameter(HttpAuthHeader.Parameters.Charset, (encoding2 == null || !encoding2.equalsIgnoreCase("7bit")) ? MimeUtility.getDefaultMIMECharset() : "us-ascii");
                    contentType = contentType2.toString();
                }
            }
            if (!z2) {
                return;
            }
            if (setContentTypeFileName && (header = mimePart.getHeader("Content-Disposition", null)) != null && (parameter = new ContentDisposition(header).getParameter(ContentDisposition.Parameters.FileName)) != null) {
                ParameterList parameterList = contentType2.getParameterList();
                if (parameterList == null) {
                    parameterList = new ParameterList();
                    contentType2.setParameterList(parameterList);
                }
                parameterList.set("name", parameter, MimeUtility.getDefaultMIMECharset());
                contentType = contentType2.toString();
            }
            mimePart.setHeader("Content-Type", contentType);
        } catch (IOException e) {
            throw new MessagingException("IOException updating headers", e);
        }
    }

    public void saveFile(String str) throws IOException, MessagingException {
        saveFile(new File(str));
    }

    public void attachFile(String str, String str2, String str3) throws IOException, MessagingException {
        attachFile(new File(str), str2, str3);
    }

    public MimeBodyPart(InternetHeaders internetHeaders, byte[] bArr) throws MessagingException {
        this.headers = internetHeaders;
        this.content = bArr;
    }
}
