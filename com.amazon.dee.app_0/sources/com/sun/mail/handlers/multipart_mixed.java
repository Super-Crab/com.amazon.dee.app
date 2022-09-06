package com.sun.mail.handlers;

import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.OutputStream;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.fileupload.FileUploadBase;
/* loaded from: classes3.dex */
public class multipart_mixed implements DataContentHandler {
    private ActivationDataFlavor myDF = new ActivationDataFlavor(MimeMultipart.class, FileUploadBase.MULTIPART_MIXED, "Multipart");

    public Object getContent(DataSource dataSource) throws IOException {
        try {
            return new MimeMultipart(dataSource);
        } catch (MessagingException e) {
            IOException iOException = new IOException("Exception while constructing MimeMultipart");
            iOException.initCause(e);
            throw iOException;
        }
    }

    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (this.myDF.equals(dataFlavor)) {
            return getContent(dataSource);
        }
        return null;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{this.myDF};
    }

    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        if (obj instanceof MimeMultipart) {
            try {
                ((MimeMultipart) obj).writeTo(outputStream);
            } catch (MessagingException e) {
                throw new IOException(e.toString());
            }
        }
    }
}
