package com.sun.mail.handlers;

import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessageAware;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
/* loaded from: classes3.dex */
public class message_rfc822 implements DataContentHandler {
    ActivationDataFlavor ourDataFlavor = new ActivationDataFlavor(Message.class, "message/rfc822", AlexaMetricsConstants.EventConstants.MESSAGE);

    public Object getContent(DataSource dataSource) throws IOException {
        Session defaultInstance;
        try {
            if (dataSource instanceof MessageAware) {
                defaultInstance = ((MessageAware) dataSource).getMessageContext().getSession();
            } else {
                defaultInstance = Session.getDefaultInstance(new Properties(), null);
            }
            return new MimeMessage(defaultInstance, dataSource.getInputStream());
        } catch (MessagingException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception creating MimeMessage in message/rfc822 DataContentHandler: ");
            outline107.append(e.toString());
            throw new IOException(outline107.toString());
        }
    }

    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (this.ourDataFlavor.equals(dataFlavor)) {
            return getContent(dataSource);
        }
        return null;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{this.ourDataFlavor};
    }

    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        if (obj instanceof Message) {
            try {
                ((Message) obj).writeTo(outputStream);
                return;
            } catch (MessagingException e) {
                throw new IOException(e.toString());
            }
        }
        throw new IOException("unsupported object");
    }
}
