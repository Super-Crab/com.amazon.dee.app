package org.bouncycastle.mail.smime;

import com.amazon.alexa.fitness.utils.FullScreenUpdaterUtilKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.mail.smime.util.CRLFOutputStream;
import org.bouncycastle.mail.smime.util.FileBackedMimeBodyPart;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class SMIMEUtil {
    private static final int BUF_SIZE = 32760;
    private static final String MULTIPART = "multipart";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class Base64CRLFOutputStream extends FilterOutputStream {
        protected static byte[] newline = new byte[2];
        private boolean isCrlfStream;
        protected int lastb;

        static {
            byte[] bArr = newline;
            bArr[0] = 13;
            bArr[1] = 10;
        }

        public Base64CRLFOutputStream(OutputStream outputStream) {
            super(outputStream);
            this.lastb = -1;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            if (i == 13) {
                ((FilterOutputStream) this).out.write(newline);
            } else if (i == 10) {
                int i2 = this.lastb;
                if (i2 == 13) {
                    this.isCrlfStream = true;
                } else if (!this.isCrlfStream || i2 != 10) {
                    ((FilterOutputStream) this).out.write(newline);
                }
            } else {
                ((FilterOutputStream) this).out.write(i);
            }
            this.lastb = i;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            for (int i3 = i; i3 != i + i2; i3++) {
                write(bArr[i3]);
            }
        }

        public void writeln() throws IOException {
            ((FilterOutputStream) this).out.write(newline);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class LineOutputStream extends FilterOutputStream {
        private static byte[] newline = new byte[2];

        static {
            byte[] bArr = newline;
            bArr[0] = 13;
            bArr[1] = 10;
        }

        public LineOutputStream(OutputStream outputStream) {
            super(outputStream);
        }

        private static byte[] getBytes(String str) {
            char[] charArray = str.toCharArray();
            int length = charArray.length;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) charArray[i];
            }
            return bArr;
        }

        public void writeln() throws MessagingException {
            try {
                ((FilterOutputStream) this).out.write(newline);
            } catch (Exception e) {
                throw new MessagingException("IOException", e);
            }
        }

        public void writeln(String str) throws MessagingException {
            try {
                ((FilterOutputStream) this).out.write(getBytes(str));
                ((FilterOutputStream) this).out.write(newline);
            } catch (Exception e) {
                throw new MessagingException("IOException", e);
            }
        }
    }

    /* loaded from: classes4.dex */
    private static class WriteOnceFileBackedMimeBodyPart extends FileBackedMimeBodyPart {
        public WriteOnceFileBackedMimeBodyPart(InputStream inputStream, File file) throws MessagingException, IOException {
            super(inputStream, file);
        }

        @Override // org.bouncycastle.mail.smime.util.FileBackedMimeBodyPart, javax.mail.internet.MimeBodyPart, javax.mail.Part
        public void writeTo(OutputStream outputStream) throws MessagingException, IOException {
            super.writeTo(outputStream);
            dispose();
        }
    }

    public static IssuerAndSerialNumber createIssuerAndSerialNumberFor(X509Certificate x509Certificate) throws CertificateParsingException {
        try {
            return new IssuerAndSerialNumber(new JcaX509CertificateHolder(x509Certificate).getIssuer(), x509Certificate.getSerialNumber());
        } catch (Exception e) {
            throw new CertificateParsingException(GeneratedOutlineSupport1.outline68("exception extracting issuer and serial number: ", e));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isCanonicalisationRequired(MimeBodyPart mimeBodyPart, String str) throws MessagingException {
        String[] header = mimeBodyPart.getHeader("Content-Transfer-Encoding");
        if (header != null) {
            str = header[0];
        }
        return !str.equalsIgnoreCase("binary");
    }

    public static boolean isMultipartContent(Part part) throws MessagingException {
        return Strings.toLowerCase(part.getContentType()).startsWith(MULTIPART);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void outputBodyPart(OutputStream outputStream, boolean z, BodyPart bodyPart, String str) throws MessagingException, IOException {
        if (!(bodyPart instanceof MimeBodyPart)) {
            if (!str.equalsIgnoreCase("binary")) {
                outputStream = new CRLFOutputStream(outputStream);
            }
            bodyPart.writeTo(outputStream);
            outputStream.flush();
            return;
        }
        MimeBodyPart mimeBodyPart = (MimeBodyPart) bodyPart;
        String[] header = mimeBodyPart.getHeader("Content-Transfer-Encoding");
        if (isMultipartContent(mimeBodyPart)) {
            Object content = bodyPart.getContent();
            Multipart mimeMultipart = content instanceof Multipart ? (Multipart) content : new MimeMultipart(bodyPart.getDataHandler().getDataSource());
            ContentType contentType = new ContentType(mimeMultipart.getContentType());
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FullScreenUpdaterUtilKt.DEFAULT_DATA);
            outline107.append(contentType.getParameter("boundary"));
            String sb = outline107.toString();
            LineOutputStream lineOutputStream = new LineOutputStream(outputStream);
            Enumeration allHeaderLines = mimeBodyPart.getAllHeaderLines();
            while (allHeaderLines.hasMoreElements()) {
                lineOutputStream.writeln((String) allHeaderLines.nextElement());
            }
            lineOutputStream.writeln();
            outputPreamble(lineOutputStream, mimeBodyPart, sb);
            for (int i = 0; i < mimeMultipart.getCount(); i++) {
                lineOutputStream.writeln(sb);
                BodyPart bodyPart2 = mimeMultipart.getBodyPart(i);
                outputBodyPart(outputStream, false, bodyPart2, str);
                if (!isMultipartContent(bodyPart2)) {
                    lineOutputStream.writeln();
                } else {
                    outputPostamble(lineOutputStream, mimeBodyPart, sb, bodyPart2);
                }
            }
            lineOutputStream.writeln(GeneratedOutlineSupport1.outline72(sb, FullScreenUpdaterUtilKt.DEFAULT_DATA));
            if (!z) {
                return;
            }
            outputPostamble(lineOutputStream, mimeBodyPart, mimeMultipart.getCount(), sb);
            return;
        }
        if (header != null) {
            str = header[0];
        }
        if (!str.equalsIgnoreCase("base64") && !str.equalsIgnoreCase("quoted-printable")) {
            if (!str.equalsIgnoreCase("binary")) {
                outputStream = new CRLFOutputStream(outputStream);
            }
            bodyPart.writeTo(outputStream);
            outputStream.flush();
            return;
        }
        boolean equalsIgnoreCase = str.equalsIgnoreCase("base64");
        try {
            InputStream rawInputStream = mimeBodyPart.getRawInputStream();
            LineOutputStream lineOutputStream2 = new LineOutputStream(outputStream);
            Enumeration allHeaderLines2 = mimeBodyPart.getAllHeaderLines();
            while (allHeaderLines2.hasMoreElements()) {
                lineOutputStream2.writeln((String) allHeaderLines2.nextElement());
            }
            lineOutputStream2.writeln();
            lineOutputStream2.flush();
            OutputStream base64CRLFOutputStream = equalsIgnoreCase ? new Base64CRLFOutputStream(outputStream) : new CRLFOutputStream(outputStream);
            byte[] bArr = new byte[BUF_SIZE];
            while (true) {
                int read = rawInputStream.read(bArr, 0, bArr.length);
                if (read <= 0) {
                    rawInputStream.close();
                    base64CRLFOutputStream.flush();
                    return;
                }
                base64CRLFOutputStream.write(bArr, 0, read);
            }
        } catch (MessagingException unused) {
            CRLFOutputStream cRLFOutputStream = new CRLFOutputStream(outputStream);
            bodyPart.writeTo(cRLFOutputStream);
            cRLFOutputStream.flush();
        }
    }

    static void outputPostamble(LineOutputStream lineOutputStream, BodyPart bodyPart, String str, BodyPart bodyPart2) throws MessagingException, IOException {
        try {
            InputStream rawInputStream = ((MimeBodyPart) bodyPart).getRawInputStream();
            MimeMultipart mimeMultipart = (MimeMultipart) bodyPart2.getContent();
            ContentType contentType = new ContentType(mimeMultipart.getContentType());
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FullScreenUpdaterUtilKt.DEFAULT_DATA);
            outline107.append(contentType.getParameter("boundary"));
            String sb = outline107.toString();
            int count = mimeMultipart.getCount() + 1;
            while (count != 0) {
                String readLine = readLine(rawInputStream);
                if (readLine == null) {
                    break;
                } else if (readLine.startsWith(sb)) {
                    count--;
                }
            }
            while (true) {
                String readLine2 = readLine(rawInputStream);
                if (readLine2 == null || readLine2.startsWith(str)) {
                    break;
                }
                lineOutputStream.writeln(readLine2);
            }
            rawInputStream.close();
        } catch (MessagingException unused) {
        }
    }

    static void outputPostamble(LineOutputStream lineOutputStream, MimeBodyPart mimeBodyPart, int i, String str) throws MessagingException, IOException {
        try {
            InputStream rawInputStream = mimeBodyPart.getRawInputStream();
            int i2 = i + 1;
            while (true) {
                String readLine = readLine(rawInputStream);
                if (readLine == null || (readLine.startsWith(str) && i2 - 1 == 0)) {
                    break;
                }
            }
            while (true) {
                String readLine2 = readLine(rawInputStream);
                if (readLine2 == null) {
                    break;
                }
                lineOutputStream.writeln(readLine2);
            }
            rawInputStream.close();
            if (i2 != 0) {
                throw new MessagingException(GeneratedOutlineSupport1.outline72("all boundaries not found for: ", str));
            }
        } catch (MessagingException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void outputPreamble(LineOutputStream lineOutputStream, MimeBodyPart mimeBodyPart, String str) throws MessagingException, IOException {
        String readLine;
        try {
            InputStream rawInputStream = mimeBodyPart.getRawInputStream();
            while (true) {
                readLine = readLine(rawInputStream);
                if (readLine == null || readLine.equals(str)) {
                    break;
                }
                lineOutputStream.writeln(readLine);
            }
            rawInputStream.close();
            if (readLine == null) {
                throw new MessagingException("no boundary found");
            }
        } catch (MessagingException unused) {
        }
    }

    private static String readLine(InputStream inputStream) throws IOException {
        int read;
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            read = inputStream.read();
            if (read < 0 || read == 10) {
                break;
            } else if (read != 13) {
                stringBuffer.append((char) read);
            }
        }
        if (read >= 0 || stringBuffer.length() != 0) {
            return stringBuffer.toString();
        }
        return null;
    }

    public static MimeBodyPart toMimeBodyPart(InputStream inputStream) throws SMIMEException {
        try {
            return new MimeBodyPart(inputStream);
        } catch (MessagingException e) {
            throw new SMIMEException("exception creating body part.", e);
        }
    }

    public static MimeBodyPart toMimeBodyPart(byte[] bArr) throws SMIMEException {
        return toMimeBodyPart(new ByteArrayInputStream(bArr));
    }

    public static FileBackedMimeBodyPart toMimeBodyPart(CMSTypedStream cMSTypedStream) throws SMIMEException {
        try {
            return toMimeBodyPart(cMSTypedStream, File.createTempFile("bcMail", ".mime"));
        } catch (IOException e) {
            throw new SMIMEException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("IOException creating tmp file:")), e);
        }
    }

    public static FileBackedMimeBodyPart toMimeBodyPart(CMSTypedStream cMSTypedStream, File file) throws SMIMEException {
        try {
            return new FileBackedMimeBodyPart(cMSTypedStream.getContentStream(), file);
        } catch (IOException e) {
            throw new SMIMEException(GeneratedOutlineSupport1.outline65("can't save content to file: ", e), e);
        } catch (MessagingException e2) {
            throw new SMIMEException("can't create part: " + e2, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FileBackedMimeBodyPart toWriteOnceBodyPart(CMSTypedStream cMSTypedStream) throws SMIMEException {
        try {
            return new WriteOnceFileBackedMimeBodyPart(cMSTypedStream.getContentStream(), File.createTempFile("bcMail", ".mime"));
        } catch (IOException e) {
            throw new SMIMEException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("IOException creating tmp file:")), e);
        } catch (MessagingException e2) {
            throw new SMIMEException("can't create part: " + e2, e2);
        }
    }
}
