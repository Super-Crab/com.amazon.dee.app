package org.bouncycastle.mail.smime;

import java.io.IOException;
import java.io.OutputStream;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
/* loaded from: classes4.dex */
public class CMSProcessableBodyPartInbound implements CMSProcessable {
    private final BodyPart bodyPart;
    private final String defaultContentTransferEncoding;

    public CMSProcessableBodyPartInbound(BodyPart bodyPart) {
        this(bodyPart, "7bit");
    }

    public CMSProcessableBodyPartInbound(BodyPart bodyPart, String str) {
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
            SMIMEUtil.outputBodyPart(outputStream, true, this.bodyPart, this.defaultContentTransferEncoding);
        } catch (MessagingException e) {
            throw new CMSException("can't write BodyPart to stream: " + e, e);
        }
    }
}
