package com.sun.mail.imap.protocol;

import java.util.List;
import javax.mail.Flags;
/* loaded from: classes3.dex */
public class MailboxInfo {
    public Flags availableFlags;
    public int first;
    public long highestmodseq;
    public int mode;
    public Flags permanentFlags;
    public int recent;
    public List<IMAPResponse> responses;
    public int total;
    public long uidnext;
    public long uidvalidity;

    /* JADX WARN: Removed duplicated region for block: B:56:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x010d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MailboxInfo(com.sun.mail.iap.Response[] r7) throws com.sun.mail.iap.ParsingException {
        /*
            Method dump skipped, instructions count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.protocol.MailboxInfo.<init>(com.sun.mail.iap.Response[]):void");
    }
}
