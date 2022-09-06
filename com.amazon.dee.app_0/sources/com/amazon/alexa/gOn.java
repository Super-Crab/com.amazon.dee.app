package com.amazon.alexa;

import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import javax.inject.Inject;
/* compiled from: AlexaNotificationBuilder.java */
/* loaded from: classes.dex */
public class gOn {
    public static final String zZm = "gOn";
    public final Context BIo;
    @VisibleForTesting
    public Integer CGv;
    public int HvC;
    public PendingIntent JTe;
    public int LPk;
    public int Mlj;
    public PendingIntent NXS;
    public PendingIntent Qgh;
    public PendingIntent Qle;
    public int Tbw;
    public int XWf;
    public boolean dMe;
    public PendingIntent jiA;
    public boolean lOf;
    public int noQ;
    @VisibleForTesting
    public boolean uuO;
    public int uzr;
    public PendingIntent vkx;
    public int wDP;
    public boolean yPL;
    public final liS zQM;
    public String zyO;
    public boolean zzR;

    @Inject
    public gOn(Context context, liS lis) {
        this.BIo = context;
        this.zQM = lis;
    }

    public gOn BIo(int i) {
        this.CGv = Integer.valueOf(i);
        return this;
    }

    public final String zZm(int i) {
        return this.BIo.getString(i);
    }

    @VisibleForTesting
    public boolean zZm() {
        return this.uuO && !(this.zQM.getTitle() == null && this.zQM.JTe() == null);
    }
}
