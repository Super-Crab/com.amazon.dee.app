package com.amazon.alexa;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.ExternalMediaPlayerBroadcastReceiver;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: ExternalMediaPlayerCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class DJw extends BaseCapabilityAgent {
    public static final String zZm = "DJw";
    public final AlexaClientEventBus BIo;
    public final Map<String, ScheduledFuture<?>> JTe;
    public final ScheduledExecutorService Qle;
    public final lhN jiA;
    public final zmg zQM;
    public final uFX zyO;

    @Inject
    public DJw(AlexaClientEventBus alexaClientEventBus, lhN lhn, zmg zmgVar, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService) {
        super(Capability.create(AvsApiConstants.ExternalMediaPlayer.zQM, "1.1"), Capability.create(AvsApiConstants.Alexa.PlaybackController.BIo, "1.0"), Capability.create(AvsApiConstants.Alexa.PlaybackStateReporter.BIo, "1.0"), Capability.create(AvsApiConstants.Alexa.PlaylistController.BIo, "1.0"), Capability.create(AvsApiConstants.Alexa.FavoritesController.BIo, "1.0"), Capability.create(AvsApiConstants.Alexa.SeekController.BIo, "1.0"));
        this.BIo = alexaClientEventBus;
        this.zQM = zmgVar;
        this.zyO = new uFX();
        this.jiA = lhn;
        this.Qle = scheduledExecutorService;
        this.JTe = new HashMap();
        alexaClientEventBus.zZm(this);
    }

    public void BIo() {
        this.zQM.LPk();
    }

    @Subscribe
    public synchronized void on(MwZ mwZ) {
        JYe zZm2 = zZm(((cqx) mwZ).BIo);
        if (zZm2 != null) {
            uFX ufx = this.zyO;
            cqx cqxVar = (cqx) mwZ;
            if (ufx.zZm.contains(cqxVar.BIo)) {
                zZm2.noQ();
            }
            uFX ufx2 = this.zyO;
            if (ufx2.BIo.contains(cqxVar.BIo)) {
                zZm2.CGv();
            }
            this.zyO.zZm(cqxVar.BIo);
        }
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public synchronized void onPreprocess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0078 A[Catch: all -> 0x042e, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0020, B:7:0x0049, B:9:0x0059, B:12:0x0078, B:14:0x0090, B:16:0x0098, B:18:0x00b8, B:143:0x0425, B:144:0x0429, B:19:0x00c4, B:21:0x00cc, B:23:0x00dc, B:24:0x00e1, B:26:0x00e9, B:28:0x00f9, B:29:0x00fe, B:31:0x0106, B:33:0x0129, B:34:0x0137, B:36:0x013f, B:38:0x014f, B:40:0x0155, B:42:0x015b, B:43:0x0168, B:44:0x016d, B:46:0x0175, B:48:0x0185, B:50:0x018b, B:52:0x0191, B:53:0x019e, B:54:0x01a3, B:56:0x01ab, B:58:0x01bb, B:59:0x01c9, B:61:0x01d1, B:63:0x01e1, B:64:0x01f2, B:66:0x01fa, B:68:0x020a, B:69:0x0218, B:71:0x0220, B:73:0x0230, B:74:0x023e, B:76:0x0246, B:78:0x0256, B:79:0x0264, B:81:0x026c, B:83:0x027c, B:84:0x0281, B:86:0x0289, B:88:0x0299, B:89:0x029e, B:91:0x02a6, B:93:0x02b6, B:94:0x02bb, B:96:0x02c3, B:98:0x02d3, B:99:0x02d8, B:101:0x02e0, B:103:0x02f0, B:104:0x02f5, B:106:0x02fd, B:108:0x030e, B:109:0x031a, B:111:0x0322, B:113:0x0333, B:114:0x033f, B:116:0x0347, B:117:0x035d, B:119:0x0363, B:121:0x036f, B:122:0x0385, B:123:0x0393, B:125:0x03a3, B:126:0x03a9, B:128:0x03ad, B:129:0x03b3, B:131:0x03b9, B:132:0x03f6, B:133:0x0401, B:134:0x0402, B:136:0x040a, B:139:0x0413), top: B:150:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0425 A[Catch: all -> 0x042e, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0020, B:7:0x0049, B:9:0x0059, B:12:0x0078, B:14:0x0090, B:16:0x0098, B:18:0x00b8, B:143:0x0425, B:144:0x0429, B:19:0x00c4, B:21:0x00cc, B:23:0x00dc, B:24:0x00e1, B:26:0x00e9, B:28:0x00f9, B:29:0x00fe, B:31:0x0106, B:33:0x0129, B:34:0x0137, B:36:0x013f, B:38:0x014f, B:40:0x0155, B:42:0x015b, B:43:0x0168, B:44:0x016d, B:46:0x0175, B:48:0x0185, B:50:0x018b, B:52:0x0191, B:53:0x019e, B:54:0x01a3, B:56:0x01ab, B:58:0x01bb, B:59:0x01c9, B:61:0x01d1, B:63:0x01e1, B:64:0x01f2, B:66:0x01fa, B:68:0x020a, B:69:0x0218, B:71:0x0220, B:73:0x0230, B:74:0x023e, B:76:0x0246, B:78:0x0256, B:79:0x0264, B:81:0x026c, B:83:0x027c, B:84:0x0281, B:86:0x0289, B:88:0x0299, B:89:0x029e, B:91:0x02a6, B:93:0x02b6, B:94:0x02bb, B:96:0x02c3, B:98:0x02d3, B:99:0x02d8, B:101:0x02e0, B:103:0x02f0, B:104:0x02f5, B:106:0x02fd, B:108:0x030e, B:109:0x031a, B:111:0x0322, B:113:0x0333, B:114:0x033f, B:116:0x0347, B:117:0x035d, B:119:0x0363, B:121:0x036f, B:122:0x0385, B:123:0x0393, B:125:0x03a3, B:126:0x03a9, B:128:0x03ad, B:129:0x03b3, B:131:0x03b9, B:132:0x03f6, B:133:0x0401, B:134:0x0402, B:136:0x040a, B:139:0x0413), top: B:150:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0429 A[Catch: all -> 0x042e, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0020, B:7:0x0049, B:9:0x0059, B:12:0x0078, B:14:0x0090, B:16:0x0098, B:18:0x00b8, B:143:0x0425, B:144:0x0429, B:19:0x00c4, B:21:0x00cc, B:23:0x00dc, B:24:0x00e1, B:26:0x00e9, B:28:0x00f9, B:29:0x00fe, B:31:0x0106, B:33:0x0129, B:34:0x0137, B:36:0x013f, B:38:0x014f, B:40:0x0155, B:42:0x015b, B:43:0x0168, B:44:0x016d, B:46:0x0175, B:48:0x0185, B:50:0x018b, B:52:0x0191, B:53:0x019e, B:54:0x01a3, B:56:0x01ab, B:58:0x01bb, B:59:0x01c9, B:61:0x01d1, B:63:0x01e1, B:64:0x01f2, B:66:0x01fa, B:68:0x020a, B:69:0x0218, B:71:0x0220, B:73:0x0230, B:74:0x023e, B:76:0x0246, B:78:0x0256, B:79:0x0264, B:81:0x026c, B:83:0x027c, B:84:0x0281, B:86:0x0289, B:88:0x0299, B:89:0x029e, B:91:0x02a6, B:93:0x02b6, B:94:0x02bb, B:96:0x02c3, B:98:0x02d3, B:99:0x02d8, B:101:0x02e0, B:103:0x02f0, B:104:0x02f5, B:106:0x02fd, B:108:0x030e, B:109:0x031a, B:111:0x0322, B:113:0x0333, B:114:0x033f, B:116:0x0347, B:117:0x035d, B:119:0x0363, B:121:0x036f, B:122:0x0385, B:123:0x0393, B:125:0x03a3, B:126:0x03a9, B:128:0x03ad, B:129:0x03b3, B:131:0x03b9, B:132:0x03f6, B:133:0x0401, B:134:0x0402, B:136:0x040a, B:139:0x0413), top: B:150:0x0001 }] */
    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void onProcess(com.amazon.alexa.client.core.messages.Message r7, com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks r8) {
        /*
            Method dump skipped, instructions count: 1073
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.DJw.onProcess(com.amazon.alexa.client.core.messages.Message, com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks):void");
    }

    public synchronized void zZm() {
        this.zQM.JTe();
    }

    @VisibleForTesting
    public void zZm(String str, String str2) {
        if (this.JTe.containsKey(str)) {
            ScheduledFuture<?> scheduledFuture = this.JTe.get(str);
            if (scheduledFuture != null && !scheduledFuture.isDone()) {
                scheduledFuture.cancel(false);
            }
            this.JTe.remove(str);
            String.format("Cancelled the timeout task for key = %s, reason = %s", str, str2);
        }
    }

    public final String zZm(vQe vqe, vat vatVar) {
        return vqe.getValue() + "_" + vatVar.zZm();
    }

    public final void zZm(vQe vqe, String str, String str2) {
        zZm(str, "received new directive");
        ScheduledFuture<?> schedule = this.Qle.schedule(new RSe(this, str, str2, vqe), 10000L, TimeUnit.MILLISECONDS);
        if (schedule != null) {
            this.JTe.put(str, schedule);
            String.format("Scheduled the timeout task for key = %s, directive = %s ", str, str2);
        }
    }

    public final JYe zZm(vQe vqe) {
        JYe zQM = this.zQM.zQM(vqe);
        if (zQM == null) {
            String str = zZm;
            Log.w(str, "Unknown Player: " + vqe);
            this.BIo.zyO(FXk.zZm(vqe, FGE.zZm));
        }
        return zQM;
    }

    @Subscribe
    public synchronized void on(AbstractC0201dvl abstractC0201dvl) {
        this.zQM.zZm(((DNr) abstractC0201dvl).BIo);
    }

    @Subscribe
    public synchronized void on(FXk fXk) {
        vQe vqe = ((YLU) fXk).BIo;
        pfe pfeVar = ((YLU) fXk).zQM;
        for (String str : this.JTe.keySet()) {
            if (str.startsWith(vqe.getValue())) {
                zZm(str, "PlayerError event");
            }
        }
        if (pfeVar != null) {
            PUa pUa = (PUa) pfeVar;
            Log.i(zZm, String.format("ErrorName: %s, fatal: %b, shouldCleanupSession: %b.", pUa.zZm, Boolean.valueOf(pUa.zQM), Boolean.valueOf(pUa.zyO)));
            if (pUa.zQM && pUa.zyO) {
                Log.w(zZm, String.format("ErrorName: %s, is unrecoverable, cleaning up the session.", pUa.zZm));
                this.zQM.zZm(vqe);
            }
        }
    }

    @Subscribe
    public synchronized void on(Ehk ehk) {
        Intent intent = ((xSe) ehk).BIo;
        if ("com.amazon.alexa.externalmediaplayer.ACTION_START_SERVICE_FOR_MSP".equals(intent.getAction())) {
            ComponentName componentName = (ComponentName) intent.getParcelableExtra(ExternalMediaPlayerBroadcastReceiver.zZm.zZm);
            if (componentName == null) {
                Log.w(zZm, "Received connection initiated by MSP broadcast with invalid component name");
                return;
            }
            vQe zZm2 = this.zQM.zZm(componentName.getPackageName());
            if (zZm2 != null) {
                JYe zZm3 = zZm(zZm2);
                if (zZm3 != null && zZm3.BIo()) {
                    this.BIo.zyO(ddC.zZm(zZm2, vat.PLAYBACK_SESSION_STARTED));
                }
            } else {
                Log.w(zZm, "Received connection initiated by MSP broadcast with unknown component name");
            }
        }
    }

    @Subscribe
    public synchronized void on(ddC ddc) {
        zZm(zZm(((SLO) ddc).BIo, ((SLO) ddc).zQM), "event subscriber processed");
    }
}
