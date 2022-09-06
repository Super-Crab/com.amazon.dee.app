package org.bouncycastle.mail.smime;

import java.io.IOException;
import java.io.OutputStream;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
/* loaded from: classes4.dex */
public class CMSProcessableBodyPart implements CMSProcessable {
    private BodyPart bodyPart;

    public CMSProcessableBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public Object getContent() {
        return this.bodyPart;
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public void write(OutputStream outputStream) throws IOException, CMSException {
        try {
            this.bodyPart.writeTo(outputStream);
        } catch (MessagingException e) {
            throw new CMSException("can't write BodyPart to stream.", e);
        }
    }
}
