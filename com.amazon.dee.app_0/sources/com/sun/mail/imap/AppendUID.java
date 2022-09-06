package com.sun.mail.imap;
/* loaded from: classes3.dex */
public class AppendUID {
    public long uid;
    public long uidvalidity;

    public AppendUID(long j, long j2) {
        this.uidvalidity = -1L;
        this.uid = -1L;
        this.uidvalidity = j;
        this.uid = j2;
    }
}
