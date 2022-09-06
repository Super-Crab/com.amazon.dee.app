package com.sun.mail.imap;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.imap.protocol.BODYSTRUCTURE;
import java.util.Vector;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.MultipartDataSource;
import javax.mail.internet.MimePart;
import javax.mail.internet.MimePartDataSource;
/* loaded from: classes3.dex */
public class IMAPMultipartDataSource extends MimePartDataSource implements MultipartDataSource {
    private Vector parts;

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPMultipartDataSource(MimePart mimePart, BODYSTRUCTURE[] bodystructureArr, String str, IMAPMessage iMAPMessage) {
        super(mimePart);
        String sb;
        this.parts = new Vector(bodystructureArr.length);
        for (int i = 0; i < bodystructureArr.length; i++) {
            Vector vector = this.parts;
            BODYSTRUCTURE bodystructure = bodystructureArr[i];
            if (str == null) {
                sb = Integer.toString(i + 1);
            } else {
                StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ".");
                outline113.append(Integer.toString(i + 1));
                sb = outline113.toString();
            }
            vector.addElement(new IMAPBodyPart(bodystructure, sb, iMAPMessage));
        }
    }

    @Override // javax.mail.MultipartDataSource
    public BodyPart getBodyPart(int i) throws MessagingException {
        return (BodyPart) this.parts.elementAt(i);
    }

    @Override // javax.mail.MultipartDataSource
    public int getCount() {
        return this.parts.size();
    }
}
