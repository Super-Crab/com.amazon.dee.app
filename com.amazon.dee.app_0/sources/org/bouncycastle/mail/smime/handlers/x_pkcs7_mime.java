package org.bouncycastle.mail.smime.handlers;

import java.awt.datatransfer.DataFlavor;
import javax.activation.ActivationDataFlavor;
import javax.mail.internet.MimeBodyPart;
/* loaded from: classes4.dex */
public class x_pkcs7_mime extends PKCS7ContentHandler {
    private static final ActivationDataFlavor ADF = new ActivationDataFlavor(MimeBodyPart.class, "application/x-pkcs7-mime", "Encrypted Data");
    private static final DataFlavor[] DFS = {ADF};

    public x_pkcs7_mime() {
        super(ADF, DFS);
    }
}
