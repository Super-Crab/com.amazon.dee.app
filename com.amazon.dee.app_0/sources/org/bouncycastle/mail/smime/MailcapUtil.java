package org.bouncycastle.mail.smime;

import javax.activation.CommandInfo;
import javax.activation.MailcapCommandMap;
/* loaded from: classes4.dex */
class MailcapUtil {
    MailcapUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MailcapCommandMap addCommands(MailcapCommandMap mailcapCommandMap) {
        CommandInfo[] allCommands = mailcapCommandMap.getAllCommands("application/pkcs7-signature");
        boolean z = false;
        int i = 0;
        while (true) {
            if (i == allCommands.length) {
                break;
            } else if ("org.bouncycastle.mail.smime.handlers.pkcs7_signature".equals(allCommands[i].getCommandClass())) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            mailcapCommandMap.addMailcap("application/pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_signature");
            mailcapCommandMap.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_mime");
            mailcapCommandMap.addMailcap("application/x-pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_signature");
            mailcapCommandMap.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_mime");
            mailcapCommandMap.addMailcap("multipart/signed;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.multipart_signed");
        }
        return mailcapCommandMap;
    }
}
