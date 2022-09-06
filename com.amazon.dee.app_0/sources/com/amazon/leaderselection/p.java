package com.amazon.leaderselection;

import android.os.Handler;
import android.os.Message;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class p {
    static final Message d = Message.obtain((Handler) null, EnumC0247r.ERROR.ordinal());
    private final Candidate a;
    private final s b;
    private final i c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(Candidate candidate, s sVar, i iVar) {
        this.a = candidate;
        this.b = sVar;
        this.c = iVar;
    }

    private Candidate a() {
        return this.b.d(this.a) ? this.a : this.b.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Message message) {
        if (message != null && EnumC0247r.a(message.what)) {
            if (EnumC0247r.b(message.what) != EnumC0247r.ERROR) {
                return o.a(EnumC0247r.b(message.what), message.getData());
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Message a(Candidate candidate) {
        Message obtain = Message.obtain((Handler) null, EnumC0247r.LEADERSHIP_USURPED.ordinal());
        obtain.setData(new o(this.a, candidate, a(), candidate).b());
        return obtain;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Message a(EnumC0247r enumC0247r) {
        Message obtain = Message.obtain((Handler) null, enumC0247r.ordinal());
        obtain.setData(new o(this.a, this.c.a(), a()).b());
        return obtain;
    }
}
