package com.amazon.alexa;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.PJz;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.interactions.ActivityTrackerChannelState;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Name;
import dagger.Lazy;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AVRCPUnnamedMediaPlayer.java */
@Singleton
/* loaded from: classes.dex */
public class EIa implements Blk {
    public static final String zZm = "EIa";
    public Set<rjL> HvC;
    public final Lazy<liS> Mlj;
    public volatile boolean wDP;
    public final Context yPL;
    public final AlexaClientEventBus zzR;
    @VisibleForTesting
    public static final Intent BIo = new Intent("com.amazon.alexa.intent.action.UNKNOWN_MEDIA_PLAYER_MEDIA_PLAY_ACTION");
    @VisibleForTesting
    public static final Intent zQM = new Intent("com.amazon.alexa.intent.action.UNKNOWN_MEDIA_PLAYER_MEDIA_PAUSE_ACTION");
    @VisibleForTesting
    public static final Intent zyO = new Intent("com.amazon.alexa.intent.action.UNKNOWN_MEDIA_PLAYER_MEDIA_PREVIOUS_ACTION");
    @VisibleForTesting
    public static final Intent jiA = new Intent("com.amazon.alexa.intent.action.UNKNOWN_MEDIA_PLAYER_MEDIA_NEXT_ACTION");
    public static final Map<Name, Intent> Qle = new FTQ();
    public static final Map<Name, rjL> JTe = new ZuU();
    public static final dnp LPk = dnp.zZm(AvsApiConstants.Alexa.PlaybackStateReporter.zZm.getValue());
    public NdN vkx = NdN.IDLE;
    public final AtomicBoolean lOf = new AtomicBoolean();
    public final AtomicBoolean dMe = new AtomicBoolean();
    public Map<Name, zZm> uzr = Collections.emptyMap();

    /* compiled from: AVRCPUnnamedMediaPlayer.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    static class BIo implements Runnable {
        public final AtomicBoolean BIo;
        public int jiA = 0;
        public final SKB zQM;
        public final Lazy<liS> zZm;
        public final AtomicBoolean zyO;

        public /* synthetic */ BIo(Lazy lazy, SKB skb, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, FTQ ftq) {
            this.zZm = lazy;
            this.zQM = skb;
            this.BIo = atomicBoolean;
            this.zyO = atomicBoolean2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.zZm.mo358get().BIo() && !this.zZm.mo358get().zZm() && !this.zQM.zZm()) {
                int i = this.jiA + 1;
                this.jiA = i;
                if (i < 3) {
                    return;
                }
                Log.i(EIa.zZm, "An unknown app is playing music");
                this.zyO.set(true);
                this.BIo.set(false);
                return;
            }
            this.jiA = 0;
        }
    }

    /* compiled from: AVRCPUnnamedMediaPlayer.java */
    /* loaded from: classes.dex */
    public static class zZm {
        public static final String zZm = "zZm";
        public final Context BIo;
        public boolean JTe;
        public boolean LPk;
        public final ComponentName Qle;
        public final String jiA;
        public final AlexaClientEventBus zQM;
        public final rjL zyO;

        public zZm(Context context, AlexaClientEventBus alexaClientEventBus, rjL rjl, String str, ComponentName componentName) {
            this.BIo = context;
            this.zyO = rjl;
            this.jiA = str;
            this.Qle = componentName;
            this.zQM = alexaClientEventBus;
        }

        public void BIo() {
            StringBuilder zZm2 = C0179Pya.zZm("MediaActionTarget.");
            zZm2.append(this.jiA);
            zZm2.append(" sendAction: ");
            zZm2.append(this.LPk);
            zZm2.toString();
            if (!this.JTe) {
                this.zQM.zZm(this);
                this.JTe = true;
            }
            if (this.LPk) {
                zZm();
            }
        }

        @Subscribe(sticky = true)
        public void on(Zbv zbv) {
            StringBuilder zZm2 = C0179Pya.zZm("MediaActionTarget.");
            zZm2.append(this.jiA);
            zZm2.append(" gained focus");
            zZm2.toString();
            this.LPk = false;
        }

        public void zQM() {
            this.zQM.BIo(this);
        }

        public final void zZm() {
            StringBuilder zZm2 = C0179Pya.zZm("MediaActionTarget.");
            zZm2.append(this.jiA);
            zZm2.append(" doSendAction: ");
            zZm2.append(this.JTe);
            zZm2.append(" focus: ");
            zZm2.append(this.LPk);
            zZm2.toString();
            this.zQM.BIo(this);
            Intent intent = new Intent(this.jiA);
            intent.setComponent(this.Qle);
            this.BIo.sendOrderedBroadcast(intent, null);
            this.JTe = false;
            this.LPk = false;
        }

        @Subscribe(sticky = true)
        public void on(fxz fxzVar) {
            StringBuilder zZm2 = C0179Pya.zZm("MediaActionTarget.");
            zZm2.append(this.jiA);
            zZm2.append(" lost focus");
            zZm2.toString();
            this.LPk = true;
            if (this.JTe) {
                zZm();
            }
        }
    }

    public EIa(Context context, AlexaClientEventBus alexaClientEventBus, Lazy<liS> lazy, SKB skb, ScheduledExecutorService scheduledExecutorService) {
        this.yPL = context;
        this.Mlj = lazy;
        this.zzR = alexaClientEventBus;
        alexaClientEventBus.zZm(this);
        scheduledExecutorService.scheduleAtFixedRate(new BIo(lazy, skb, this.dMe, this.lOf, null), 1000L, 1000L, TimeUnit.MILLISECONDS);
    }

    public final NdN BIo() {
        if (this.Mlj.mo358get().jiA()) {
            this.vkx = NdN.PLAYING;
        } else if (this.vkx != NdN.IDLE) {
            this.vkx = NdN.PAUSED;
        }
        StringBuilder zZm2 = C0179Pya.zZm("    into: ");
        zZm2.append(this.vkx);
        zZm2.toString();
        return this.vkx;
    }

    @Override // com.amazon.alexa.Blk
    public boolean isAvailable() {
        if (!this.wDP) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<Name, Intent> entry : Qle.entrySet()) {
                Name key = entry.getKey();
                rjL rjl = JTe.get(key);
                Intent value = entry.getValue();
                for (ResolveInfo resolveInfo : this.yPL.getPackageManager().queryBroadcastReceivers(value, 128)) {
                    ActivityInfo activityInfo = resolveInfo.activityInfo;
                    if (activityInfo != null) {
                        String str = "found media action target for: " + key;
                        linkedHashMap.put(key, new zZm(this.yPL, this.zzR, rjl, value.getAction(), new ComponentName(activityInfo.packageName, resolveInfo.activityInfo.name)));
                    }
                }
            }
            this.uzr = Collections.unmodifiableMap(linkedHashMap);
            Map<Name, zZm> map = this.uzr;
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (Map.Entry<Name, zZm> entry2 : map.entrySet()) {
                zZm value2 = entry2.getValue();
                linkedHashSet.add(value2.zyO);
                StringBuilder zZm2 = C0179Pya.zZm("adding supported operation: ");
                zZm2.append(value2.zyO);
                zZm2.toString();
            }
            this.HvC = Collections.unmodifiableSet(linkedHashSet);
            this.wDP = true;
        }
        return !this.uzr.isEmpty();
    }

    @Subscribe
    public void on(Zbv zbv) {
        StringBuilder zZm2 = C0179Pya.zZm("Audio focus was gained. Was EMP playing? ");
        ApR apR = (ApR) zbv;
        zZm2.append(apR.zyO);
        zZm2.append(" Was music playing? ");
        zZm2.append(apR.zQM);
        zZm2.toString();
        this.dMe.set(apR.zyO);
        if (PJz.BIo.PERSISTENT == apR.BIo) {
            this.vkx = NdN.IDLE;
            this.lOf.set(apR.zQM);
        }
    }

    @Override // com.amazon.alexa.Blk
    public void teardown() {
        this.zzR.BIo(this);
        if (this.wDP) {
            for (Map.Entry<Name, zZm> entry : this.uzr.entrySet()) {
                entry.getValue().zQM();
            }
            this.uzr = Collections.emptyMap();
            this.wDP = false;
        }
    }

    @Override // com.amazon.alexa.Blk
    public boolean zZm() {
        return this.dMe.get();
    }

    @Override // com.amazon.alexa.Blk
    public boolean zZm(Header header) {
        zZm zzm;
        StringBuilder zZm2 = C0179Pya.zZm("handle: ");
        zZm2.append(header.getName());
        zZm2.toString();
        Name name = header.getName();
        if (!this.uzr.containsKey(name) || (zzm = this.uzr.get(name)) == null) {
            return false;
        }
        zzm.BIo();
        return true;
    }

    @Override // com.amazon.alexa.Blk
    public Set<ComponentState> zZm(vQe vqe, Set<ComponentState> set) {
        ActivityTrackerChannelState zZm2;
        if (!isAvailable()) {
            Log.w(zZm, "The unnamed player is not available");
            return set;
        } else if (this.Mlj.mo358get().yPL()) {
            Log.i(zZm, "Alexa is playing - no states to correct");
            return set;
        } else if (!vQe.zZm.equals(vqe)) {
            Log.i(zZm, "Player in focus is not unknown - no need to correct component states");
            return set;
        } else if (set.isEmpty()) {
            Log.i(zZm, "No states to correct");
            return set;
        } else {
            HashSet hashSet = new HashSet();
            Log.i(zZm, "Updating EMP state for unnamed player");
            for (ComponentState componentState : set) {
                ComponentStateHeader header = componentState.getHeader();
                if (AvsApiConstants.ExternalMediaPlayer.zZm.equals(header.BIo()) && AvsApiConstants.ExternalMediaPlayer.ComponentStates.ExternalMediaPlayerState.zZm.equals(header.zZm())) {
                    ComponentStatePayload payload = componentState.getPayload();
                    StringBuilder zZm3 = C0179Pya.zZm("correcting ExternalMediaPlayerState from original player in focus: ");
                    DZr dZr = (DZr) payload;
                    zZm3.append(dZr.zQM);
                    zZm3.append(" to ");
                    zZm3.append(vQe.zZm);
                    zZm3.toString();
                    hashSet.add(ComponentState.create(header, BGK.zZm(dZr.zZm, dZr.BIo, vQe.zZm, dZr.zyO)));
                } else if (AvsApiConstants.Alexa.PlaybackStateReporter.zZm.equals(header.BIo()) && AvsApiConstants.Alexa.PlaybackStateReporter.ComponentStates.PlaybackState.zZm.equals(header.zZm())) {
                    ComponentStatePayload payload2 = componentState.getPayload();
                    StringBuilder zZm4 = C0179Pya.zZm("correcting PlaybackStateReporter state from original player in focus: ");
                    xxR xxr = (xxR) payload2;
                    zZm4.append(xxr.zZm);
                    zZm4.append(" to ");
                    zZm4.append(BIo());
                    zZm4.toString();
                    hashSet.add(ComponentState.create(header, GkO.zZm().zZm(xxr.LPk).BIo(this.HvC).zZm(BIo()).zZm(XSR.NOT_REPEATED).zZm(AKJ.NOT_SHUFFLED).zZm(MAh.NOT_RATED).zZm(AbstractC0197ddD.zZm).zZm()));
                } else if (AvsApiConstants.AudioActivityTracker.zZm.equals(header.BIo()) && AvsApiConstants.AudioActivityTracker.ComponentStates.ActivityState.zZm.equals(header.zZm())) {
                    NdN BIo2 = BIo();
                    if (BIo2 == NdN.PLAYING || (BIo2 == NdN.PAUSED && !this.Mlj.mo358get().zZm())) {
                        StringBuilder zZm5 = C0179Pya.zZm("Is anything playing on Alexa? ");
                        zZm5.append(this.Mlj.mo358get().zZm());
                        zZm5.toString();
                        ComponentStatePayload payload3 = componentState.getPayload();
                        StringBuilder zZm6 = C0179Pya.zZm("correcting AudioActivityTrackerState from original player in focus: ");
                        ezo ezoVar = (ezo) payload3;
                        zZm6.append(ezoVar.zyO);
                        zZm6.append(" to ");
                        zZm6.append(LPk);
                        zZm6.toString();
                        long j = BIo() == NdN.PLAYING ? 0L : 10000L;
                        if (!zZm()) {
                            zZm2 = ActivityTrackerChannelState.zZm(LPk, j);
                        } else {
                            Aoi aoi = (Aoi) ezoVar.zyO;
                            zZm2 = ActivityTrackerChannelState.zZm(aoi.zZm, aoi.BIo);
                        }
                        hashSet.add(ComponentState.create(header, plk.zZm().zyO(ezoVar.zZm).BIo(ezoVar.BIo).zZm(ezoVar.zQM).zQM(zZm2).zZm()));
                    }
                } else {
                    hashSet.add(componentState);
                }
            }
            return hashSet;
        }
    }

    @Subscribe
    public void on(fxz fxzVar) {
        if (this.lOf.get()) {
            this.vkx = NdN.PAUSED;
        }
    }

    @Subscribe
    public void on(MwZ mwZ) {
        C0179Pya.zZm(C0179Pya.zZm("Media session playback was started: "), ((cqx) mwZ).BIo, zZm);
        this.dMe.set(true);
        this.lOf.set(false);
    }
}
