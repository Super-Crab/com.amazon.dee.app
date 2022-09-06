package com.amazon.leaderselection;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class q {
    private static final String d = "q";
    private final Context a;
    private final b b;
    private final p c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(Context context, b bVar, p pVar) {
        this.a = context;
        this.b = bVar;
        this.c = pVar;
    }

    private Message a(Message message, Candidate candidate) {
        l b;
        a a = this.b.a(this.a, candidate.getComponentName());
        if (a != null && (b = a.b()) != null) {
            try {
                return b.a(message);
            } catch (RemoteException unused) {
                String str = d;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not connected to candidateService: ");
                outline107.append(candidate.getPackageName());
                Log.e(str, outline107.toString());
                this.b.a(a);
            }
        }
        String str2 = d;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Failed to send message to candidate. Message: ");
        outline1072.append(EnumC0247r.b(message.what));
        outline1072.append(", Candidate: ");
        outline1072.append(candidate.getPackageName());
        Log.e(str2, outline1072.toString());
        return p.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Message a(Candidate candidate, Candidate candidate2) {
        String str = this.a.getPackageName() + " sending: " + EnumC0247r.USURP_LEADERSHIP + " to " + candidate2;
        return a(this.c.a(candidate), candidate2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Message a(EnumC0247r enumC0247r, Candidate candidate) {
        String str = this.a.getPackageName() + " sending: " + enumC0247r + " to " + candidate;
        return a(this.c.a(enumC0247r), candidate);
    }
}
