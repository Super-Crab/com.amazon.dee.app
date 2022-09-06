package org.bouncycastle.mail.smime.handlers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
/* loaded from: classes4.dex */
public class x_pkcs7_signature implements DataContentHandler {
    private static final ActivationDataFlavor ADF = new ActivationDataFlavor(MimeBodyPart.class, "application/x-pkcs7-signature", "Signature");
    private static final DataFlavor[] ADFs = {ADF};

    public Object getContent(DataSource dataSource) throws IOException {
        return dataSource.getInputStream();
    }

    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (ADF.equals(dataFlavor)) {
            return getContent(dataSource);
        }
        return null;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return ADFs;
    }

    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        if (obj instanceof MimeBodyPart) {
            try {
                ((MimeBodyPart) obj).writeTo(outputStream);
            } catch (MessagingException e) {
                throw new IOException(e.getMessage());
            }
        } else if (obj instanceof byte[]) {
            outputStream.write((byte[]) obj);
        } else if (!(obj instanceof InputStream)) {
            throw new IOException(GeneratedOutlineSupport1.outline70("unknown object in writeTo ", obj));
        } else {
            InputStream inputStream = (InputStream) obj;
            while (true) {
                int read = inputStream.read();
                if (read < 0) {
                    return;
                }
                outputStream.write(read);
            }
        }
    }
}
