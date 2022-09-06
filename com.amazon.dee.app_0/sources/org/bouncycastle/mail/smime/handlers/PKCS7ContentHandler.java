package org.bouncycastle.mail.smime.handlers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.awt.datatransfer.DataFlavor;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import org.bouncycastle.mail.smime.SMIMEStreamingProcessor;
/* loaded from: classes4.dex */
public class PKCS7ContentHandler implements DataContentHandler {
    private final ActivationDataFlavor _adf;
    private final DataFlavor[] _dfs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PKCS7ContentHandler(ActivationDataFlavor activationDataFlavor, DataFlavor[] dataFlavorArr) {
        this._adf = activationDataFlavor;
        this._dfs = dataFlavorArr;
    }

    public Object getContent(DataSource dataSource) throws IOException {
        return dataSource.getInputStream();
    }

    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (this._adf.equals(dataFlavor)) {
            return getContent(dataSource);
        }
        return null;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return this._dfs;
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
