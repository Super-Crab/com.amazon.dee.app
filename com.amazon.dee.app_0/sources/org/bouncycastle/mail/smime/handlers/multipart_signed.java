package org.bouncycastle.mail.smime.handlers;

import com.amazon.alexa.fitness.utils.FullScreenUpdaterUtilKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.awt.datatransfer.DataFlavor;
import java.io.BufferedInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import org.bouncycastle.mail.smime.SMIMEStreamingProcessor;
import org.bouncycastle.mail.smime.SMIMEUtil;
/* loaded from: classes4.dex */
public class multipart_signed implements DataContentHandler {
    private static final ActivationDataFlavor ADF = new ActivationDataFlavor(MimeMultipart.class, "multipart/signed", "Multipart Signed");
    private static final DataFlavor[] DFS = {ADF};

    /* JADX INFO: Access modifiers changed from: private */
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

    private void outputBodyPart(OutputStream outputStream, Object obj) throws MessagingException, IOException {
        if (obj instanceof Multipart) {
            Multipart multipart = (Multipart) obj;
            ContentType contentType = new ContentType(multipart.getContentType());
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FullScreenUpdaterUtilKt.DEFAULT_DATA);
            outline107.append(contentType.getParameter("boundary"));
            String sb = outline107.toString();
            LineOutputStream lineOutputStream = new LineOutputStream(outputStream);
            for (int i = 0; i < multipart.getCount(); i++) {
                lineOutputStream.writeln(sb);
                outputBodyPart(outputStream, multipart.getBodyPart(i));
                lineOutputStream.writeln();
            }
            lineOutputStream.writeln(GeneratedOutlineSupport1.outline72(sb, FullScreenUpdaterUtilKt.DEFAULT_DATA));
            return;
        }
        MimeBodyPart mimeBodyPart = (MimeBodyPart) obj;
        if (SMIMEUtil.isMultipartContent(mimeBodyPart)) {
            Object content = mimeBodyPart.getContent();
            if (content instanceof Multipart) {
                Multipart multipart2 = (Multipart) content;
                ContentType contentType2 = new ContentType(multipart2.getContentType());
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(FullScreenUpdaterUtilKt.DEFAULT_DATA);
                outline1072.append(contentType2.getParameter("boundary"));
                String sb2 = outline1072.toString();
                LineOutputStream lineOutputStream2 = new LineOutputStream(outputStream);
                Enumeration allHeaderLines = mimeBodyPart.getAllHeaderLines();
                while (allHeaderLines.hasMoreElements()) {
                    lineOutputStream2.writeln((String) allHeaderLines.nextElement());
                }
                lineOutputStream2.writeln();
                outputPreamble(lineOutputStream2, mimeBodyPart, sb2);
                outputBodyPart(outputStream, multipart2);
                return;
            }
        }
        mimeBodyPart.writeTo(outputStream);
    }

    static void outputPreamble(LineOutputStream lineOutputStream, MimeBodyPart mimeBodyPart, String str) throws MessagingException, IOException {
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
        if (read < 0) {
            return null;
        }
        return stringBuffer.toString();
    }

    public Object getContent(DataSource dataSource) throws IOException {
        try {
            return new MimeMultipart(dataSource);
        } catch (MessagingException unused) {
            return null;
        }
    }

    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (ADF.equals(dataFlavor)) {
            return getContent(dataSource);
        }
        return null;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return DFS;
    }

    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        if (obj instanceof MimeMultipart) {
            try {
                outputBodyPart(outputStream, obj);
            } catch (MessagingException e) {
                throw new IOException(e.getMessage());
            }
        } else if (obj instanceof byte[]) {
            outputStream.write((byte[]) obj);
        } else if (!(obj instanceof InputStream)) {
            if (!(obj instanceof SMIMEStreamingProcessor)) {
                throw new IOException(GeneratedOutlineSupport1.outline70("unknown object in writeTo ", obj));
            }
            ((SMIMEStreamingProcessor) obj).write(outputStream);
        } else {
            InputStream inputStream = (InputStream) obj;
            if (!(inputStream instanceof BufferedInputStream)) {
                inputStream = new BufferedInputStream(inputStream);
            }
            while (true) {
                int read = inputStream.read();
                if (read < 0) {
                    inputStream.close();
                    return;
                }
                outputStream.write(read);
            }
        }
    }
}
