package com.amazon.alexa;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import java.util.EnumSet;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: FailedInteractionTracker.java */
@Singleton
/* loaded from: classes.dex */
public class hlu {
    @VisibleForTesting
    public static final EnumSet<bij> zZm = EnumSet.of(bij.NETWORK_UNAVAILABLE, bij.AVS_CONNECTION_TIMEOUT, bij.NETWORK_REQUEST_ERROR, bij.AVS_UNAVAILABLE_DOWNCHANNEL);
    public final AlexaClientEventBus BIo;
    public zZm Qle;
    public final TimeProvider zQM;
    public final IWd<XWx, String> zyO = new IWd<>();
    public final IWd<DialogRequestIdentifier, XWx> jiA = new IWd<>();

    /* compiled from: FailedInteractionTracker.java */
    /* loaded from: classes.dex */
    private static class zZm {
        public final String BIo;
        public boolean jiA;
        public final long zQM;
        public final bij zZm;
        public boolean zyO;

        public zZm(bij bijVar, String str, long j) {
            this.zZm = bijVar;
            this.BIo = str;
            this.zQM = j;
        }
    }

    @Inject
    public hlu(AlexaClientEventBus alexaClientEventBus, TimeProvider timeProvider) {
        this.BIo = alexaClientEventBus;
        this.zQM = timeProvider;
        this.BIo.zZm(this);
    }

    @Subscribe
    public void on(GUc gUc) {
        ghu ghuVar = (ghu) gUc;
        DialogRequestIdentifier dialogRequestIdentifier = ghuVar.zQM;
        if (dialogRequestIdentifier != null) {
            this.jiA.zZm(dialogRequestIdentifier, ghuVar.BIo);
            return;
        }
        String str = ghuVar.zyO;
        if (str == null) {
            return;
        }
        this.zyO.zZm(ghuVar.BIo, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x003e  */
    @org.greenrobot.eventbus.Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void on(com.amazon.alexa.kbp.zQM r5) {
        /*
            r4 = this;
            com.amazon.alexa.IWd<com.amazon.alexa.client.core.messages.DialogRequestIdentifier, com.amazon.alexa.XWx> r0 = r4.jiA
            r1 = r5
            com.amazon.alexa.gMf r1 = (com.amazon.alexa.gMf) r1
            com.amazon.alexa.client.core.messages.DialogRequestIdentifier r2 = r1.BIo
            boolean r0 = r0.zZm(r2)
            if (r0 == 0) goto L28
            com.amazon.alexa.IWd<com.amazon.alexa.client.core.messages.DialogRequestIdentifier, com.amazon.alexa.XWx> r0 = r4.jiA
            com.amazon.alexa.client.core.messages.DialogRequestIdentifier r2 = r1.BIo
            java.lang.Object r0 = r0.BIo(r2)
            com.amazon.alexa.XWx r0 = (com.amazon.alexa.XWx) r0
            com.amazon.alexa.IWd<com.amazon.alexa.XWx, java.lang.String> r2 = r4.zyO
            boolean r2 = r2.zZm(r0)
            if (r2 == 0) goto L28
            com.amazon.alexa.IWd<com.amazon.alexa.XWx, java.lang.String> r2 = r4.zyO
            java.lang.Object r0 = r2.BIo(r0)
            java.lang.String r0 = (java.lang.String) r0
            goto L2a
        L28:
            java.lang.String r0 = "UNKNOWN"
        L2a:
            com.amazon.alexa.bij r1 = r1.zQM
            long r2 = r5.zZm
            java.util.EnumSet<com.amazon.alexa.bij> r5 = com.amazon.alexa.hlu.zZm
            boolean r5 = r5.contains(r1)
            if (r5 == 0) goto L3e
            com.amazon.alexa.hlu$zZm r5 = new com.amazon.alexa.hlu$zZm
            r5.<init>(r1, r0, r2)
            r4.Qle = r5
            goto L41
        L3e:
            r5 = 0
            r4.Qle = r5
        L41:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.hlu.on(com.amazon.alexa.kbp$zQM):void");
    }

    @Subscribe
    public void on(yHQ yhq) {
        zZm zzm;
        if (!((dpf) yhq).BIo || (zzm = this.Qle) == null || zzm.zyO) {
            return;
        }
        long elapsedRealTime = this.zQM.elapsedRealTime();
        zZm zzm2 = this.Qle;
        long j = zzm2.zQM;
        if (elapsedRealTime > j) {
            this.BIo.zyO(new whK(zzm2.zZm, zzm2.BIo, elapsedRealTime - j));
        }
        this.Qle.zyO = true;
    }

    @Subscribe
    public void on(Bob bob) {
        zZm zzm;
        if (!bob.BIo() || (zzm = this.Qle) == null || zzm.jiA) {
            return;
        }
        long elapsedRealTime = this.zQM.elapsedRealTime();
        zZm zzm2 = this.Qle;
        long j = zzm2.zQM;
        if (elapsedRealTime > j) {
            this.BIo.zyO(new spO(zzm2.zZm, zzm2.BIo, elapsedRealTime - j));
        }
        this.Qle.jiA = true;
    }

    @Subscribe
    public void on(GSR gsr) {
        this.Qle = null;
    }
}
