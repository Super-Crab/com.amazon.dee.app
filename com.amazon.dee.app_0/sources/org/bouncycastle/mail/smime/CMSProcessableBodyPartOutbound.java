package org.bouncycastle.mail.smime;

import java.io.IOException;
import java.io.OutputStream;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.mail.smime.util.CRLFOutputStream;
/* loaded from: classes4.dex */
public class CMSProcessableBodyPartOutbound implements CMSProcessable {
    private BodyPart bodyPart;
    private String defaultContentTransferEncoding;

    public CMSProcessableBodyPartOutbound(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public CMSProcessableBodyPartOutbound(BodyPart bodyPart, String str) {
        this.bodyPart = bodyPart;
        this.defaultContentTransferEncoding = str;
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public Object getContent() {
        return this.bodyPart;
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public void write(OutputStream outputStream) throws IOException, CMSException {
        try {
            if (SMIMEUtil.isCanonicalisationRequired((MimeBodyPart) this.bodyPart, this.defaultContentTransferEncoding)) {
                outputStream = new CRLFOutputStream(outputStream);
            }
            this.bodyPart.writeTo(outputStream);
        } catch (MessagingException e) {
            throw new CMSException("can't write BodyPart to stream.", e);
        }
    }
}
