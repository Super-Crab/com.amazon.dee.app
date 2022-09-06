package org.bouncycastle.mail.smime.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
/* loaded from: classes4.dex */
public class FileBackedMimeBodyPart extends MimeBodyPart {
    private static final int BUF_SIZE = 32760;
    private final File _file;

    public FileBackedMimeBodyPart(File file) throws MessagingException, IOException {
        super(new SharedFileInputStream(file));
        this._file = file;
    }

    public FileBackedMimeBodyPart(InputStream inputStream, File file) throws MessagingException, IOException {
        this(saveStreamToFile(inputStream, file));
    }

    public FileBackedMimeBodyPart(InternetHeaders internetHeaders, InputStream inputStream, File file) throws MessagingException, IOException {
        this(saveStreamToFile(internetHeaders, inputStream, file));
    }

    private static void saveContentToStream(OutputStream outputStream, InputStream inputStream) throws IOException {
        byte[] bArr = new byte[BUF_SIZE];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read <= 0) {
                outputStream.close();
                inputStream.close();
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    private static File saveStreamToFile(InputStream inputStream, File file) throws IOException {
        saveContentToStream(new FileOutputStream(file), inputStream);
        return file;
    }

    private static File saveStreamToFile(InternetHeaders internetHeaders, InputStream inputStream, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Enumeration allHeaderLines = internetHeaders.getAllHeaderLines();
        while (allHeaderLines.hasMoreElements()) {
            writeHeader(fileOutputStream, (String) allHeaderLines.nextElement());
        }
        writeSeperator(fileOutputStream);
        saveContentToStream(fileOutputStream, inputStream);
        return file;
    }

    private static void writeHeader(OutputStream outputStream, String str) throws IOException {
        for (int i = 0; i != str.length(); i++) {
            outputStream.write(str.charAt(i));
        }
        writeSeperator(outputStream);
    }

    private static void writeSeperator(OutputStream outputStream) throws IOException {
        outputStream.write(13);
        outputStream.write(10);
    }

    public void dispose() throws IOException {
        ((SharedFileInputStream) this.contentStream).getRoot().dispose();
        if (!this._file.exists() || this._file.delete()) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("deletion of underlying file <");
        outline107.append(this._file.getCanonicalPath());
        outline107.append("> failed.");
        throw new IOException(outline107.toString());
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        if (this._file.exists()) {
            super.writeTo(outputStream);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("file ");
        outline107.append(this._file.getCanonicalPath());
        outline107.append(" no longer exists.");
        throw new IOException(outline107.toString());
    }
}
