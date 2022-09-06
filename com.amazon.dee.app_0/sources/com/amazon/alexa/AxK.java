package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.QYV;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaClient;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.fuM;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AttentionSystemLatencyProcessor.java */
@Singleton
/* loaded from: classes.dex */
public class AxK {
    public static final String zZm = "AxK";
    public final AlexaClientEventBus BIo;
    public zZm Qle;
    public wSq jiA;
    public final vrF zQM;
    public final String zyO;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AttentionSystemLatencyProcessor.java */
    /* loaded from: classes.dex */
    public static class zZm {
        public String BIo;
        public boolean JTe;
        public boolean LPk;
        public long Mlj;
        public long Qle;
        public long jiA;
        public boolean yPL;
        public String zQM;
        public final String zZm;
        public String zyO;

        public /* synthetic */ zZm(String str, peA pea) {
            this.zZm = str;
        }

        public boolean BIo() {
            return zQM() && AlexaMetricsConstants.EventConstants.INVOCATION_TYPE_EXPECT_SPEECH.equals(this.zyO);
        }

        public final boolean zQM() {
            return this.zZm.equals(this.BIo) || AlexaClient.CLIENT.getPackageName().equals(this.BIo);
        }

        public boolean zZm() {
            return zQM() && AlexaMetricsConstants.EventConstants.TAP_TO_TALK.equals(this.zQM) && this.Qle > 0;
        }
    }

    @Inject
    public AxK(Context context, vrF vrf, AlexaClientEventBus alexaClientEventBus) {
        String packageName = context.getPackageName();
        this.zyO = packageName;
        this.zQM = vrf;
        this.BIo = alexaClientEventBus;
        this.Qle = new zZm(packageName, null);
        alexaClientEventBus.zZm(this);
    }

    public void BIo() {
        this.BIo.BIo(this);
    }

    @Subscribe
    public void on(fuM.BIo bIo) {
        fuM.zZm zzm = ((Don) bIo).zQM;
        long j = bIo.zZm;
        Don don = (Don) bIo;
        String invocationType = don.jiA.getInvocationType();
        ExtendedClient extendedClient = don.BIo;
        zZm zzm2 = this.Qle;
        String packageName = extendedClient.getActiveSubClient().getPackageName();
        boolean equals = fuM.zZm.BUTTON_PRESS.equals(zzm);
        String str = AlexaMetricsConstants.EventConstants.TAP_TO_TALK;
        if (!equals && fuM.zZm.WAKEWORD.equals(zzm)) {
            str = AlexaMetricsConstants.EventConstants.WAKE_WORD;
        }
        zzm2.jiA = j;
        zzm2.BIo = packageName;
        zzm2.zyO = invocationType;
        zzm2.zQM = str;
    }

    public final void zZm() {
        this.zQM.zyO = 0L;
        zZm zzm = this.Qle;
        if (zzm.JTe || zzm.Qle > 0 || zzm.Mlj > 0 || zzm.jiA > 0) {
            this.Qle = new zZm(this.zyO, null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0023, code lost:
        if ((r8.zQM.zyO != 0) == false) goto L46;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zZm(com.amazon.alexa.AxK.zZm r9) {
        /*
            r8 = this;
            boolean r0 = r9.JTe
            r1 = 0
            r2 = 0
            r4 = 1
            if (r0 == 0) goto L11
            long r5 = r9.jiA
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 > 0) goto Lf
            goto L11
        Lf:
            r0 = r1
            goto L12
        L11:
            r0 = r4
        L12:
            if (r0 != 0) goto Laf
            boolean r0 = r9.yPL
            if (r0 == 0) goto L27
            com.amazon.alexa.vrF r0 = r8.zQM
            long r5 = r0.zyO
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 == 0) goto L22
            r0 = r4
            goto L23
        L22:
            r0 = r1
        L23:
            if (r0 != 0) goto L27
            goto Laf
        L27:
            boolean r0 = r9.LPk
            if (r0 == 0) goto L3c
            boolean r0 = r9.zZm()
            if (r0 != 0) goto L3b
            boolean r0 = r9.BIo()
            if (r0 != 0) goto L3b
            boolean r0 = r9.yPL
            if (r0 == 0) goto L3c
        L3b:
            r1 = r4
        L3c:
            if (r1 != 0) goto L3f
            return r4
        L3f:
            boolean r0 = r9.zZm()
            boolean r1 = r9.BIo()
            boolean r5 = r9.yPL
            if (r0 != 0) goto L50
            if (r5 != 0) goto L50
            if (r1 != 0) goto L50
            goto L85
        L50:
            if (r1 == 0) goto L55
            long r0 = r9.jiA
            goto L5e
        L55:
            if (r5 == 0) goto L5c
            com.amazon.alexa.vrF r0 = r8.zQM
            long r0 = r0.zyO
            goto L5e
        L5c:
            long r0 = r9.Qle
        L5e:
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 <= 0) goto L85
            long r5 = r9.Mlj
            long r5 = r5 - r0
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 <= 0) goto L85
            java.lang.String r0 = com.amazon.alexa.AxK.zZm
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r7 = "Voice chrome latency: "
            r1.append(r7)
            r1.append(r5)
            java.lang.String r7 = " ms"
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r0, r1)
            goto L87
        L85:
            r5 = -1
        L87:
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 <= 0) goto L98
            com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus r0 = r8.BIo
            java.lang.String r9 = r9.zyO
            com.amazon.alexa.AWj r1 = new com.amazon.alexa.AWj
            r1.<init>(r9, r5)
            r0.zyO(r1)
            goto Lae
        L98:
            java.lang.String r0 = com.amazon.alexa.AxK.zZm
            java.lang.String r1 = "Calculated attention system latency value of "
            java.lang.String r2 = " for "
            java.lang.StringBuilder r1 = com.android.tools.r8.GeneratedOutlineSupport1.outline111(r1, r5, r2)
            java.lang.String r9 = r9.zyO
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            android.util.Log.w(r0, r9)
        Lae:
            return r4
        Laf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.AxK.zZm(com.amazon.alexa.AxK$zZm):boolean");
    }

    @Subscribe
    public void on(AQg aQg) {
        C0236vPD c0236vPD = (C0236vPD) aQg;
        if (UiEventName.CARD_INGRESS_TAPPED.equals(c0236vPD.BIo)) {
            this.Qle.Qle = c0236vPD.zQM.getLong(AlexaMetadataBundleKey.EVENT_REALTIME_MS.name(), -1L);
        } else if (UiEventName.ALEXA_UI_SHOWN.equals(c0236vPD.BIo)) {
            long j = c0236vPD.zQM.getLong(AlexaMetadataBundleKey.EVENT_REALTIME_MS.name(), -1L);
            zZm zzm = this.Qle;
            zzm.LPk = true;
            zzm.Mlj = j;
        } else if (!UiEventName.ALEXA_UI_DISMISSED.equals(c0236vPD.BIo)) {
        } else {
            zZm(this.Qle);
            zZm();
        }
    }

    @Subscribe
    public void on(QYV.Qle qle) {
        AlexaAudioMetadata zyO = ((wmF) qle).BIo.zyO();
        this.Qle.yPL = (zyO == null || zyO.getAlexaWakeword() == null) ? false : true;
    }

    @Subscribe(sticky = true)
    public void on(HDT hdt) {
        this.jiA = ((RUl) hdt).BIo;
        int ordinal = this.jiA.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            this.Qle.JTe = true;
        } else if (ordinal != 2) {
            if (ordinal != 7 || !this.Qle.JTe) {
                return;
            }
            zZm();
        } else if (!zZm(this.Qle)) {
        } else {
            zZm();
        }
    }
}
