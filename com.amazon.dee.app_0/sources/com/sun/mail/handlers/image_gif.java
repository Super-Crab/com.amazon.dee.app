package com.sun.mail.handlers;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
/* loaded from: classes3.dex */
public class image_gif implements DataContentHandler {
    private static ActivationDataFlavor myDF = new ActivationDataFlavor(Image.class, "image/gif", "GIF Image");

    public Object getContent(DataSource dataSource) throws IOException {
        InputStream inputStream = dataSource.getInputStream();
        byte[] bArr = new byte[1024];
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr, i, bArr.length - i);
            if (read != -1) {
                i += read;
                if (i >= bArr.length) {
                    int length = bArr.length;
                    byte[] bArr2 = new byte[length < 262144 ? length + length : length + 262144];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    bArr = bArr2;
                }
            } else {
                return Toolkit.getDefaultToolkit().createImage(bArr, 0, i);
            }
        }
    }

    protected ActivationDataFlavor getDF() {
        return myDF;
    }

    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (getDF().equals(dataFlavor)) {
            return getContent(dataSource);
        }
        return null;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{getDF()};
    }

    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        if (!(obj instanceof Image)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline107.append(getDF().getMimeType());
            outline107.append("\" DataContentHandler requires Image object, was given object of type ");
            outline107.append(obj.getClass().toString());
            throw new IOException(outline107.toString());
        }
        throw new IOException(GeneratedOutlineSupport1.outline91(new StringBuilder(), getDF().getMimeType(), " encoding not supported"));
    }
}
