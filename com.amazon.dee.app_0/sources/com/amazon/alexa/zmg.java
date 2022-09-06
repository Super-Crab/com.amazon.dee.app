package com.amazon.alexa;

import android.os.ConditionVariable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.JYe;
import com.amazon.alexa.LTt;
import com.amazon.alexa.Qds;
import com.amazon.alexa.WXj;
import com.amazon.alexa.bXd;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_PlayerErrorPayload;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_PlayerEventPayload;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_ReportDiscoveredPlayersPayload;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_ReportDiscoveredPlayersPayload_Player;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.plk;
import com.amazon.alexa.qQM;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.alexa.vST;
import com.amazon.alexa.yfS;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: MediaPlayersAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class zmg {
    public static final AbstractC0188bKf BIo = AbstractC0188bKf.zZm("1.0");
    public static final String zZm = "zmg";
    public final Lazy<C0245zoo> HvC;
    public final yWS JTe;
    public final qKe LPk;
    public final aim Mlj;
    public int NXS;
    public final AtomicReference<vQe> Qgh;
    public final Map<vQe, JYe> Qle;
    public final vkx Tbw;
    public final gSO XWf;
    public final Lazy<HTC> dMe;
    public final Blk jiA;
    public final Lazy<Boy> lOf;
    public final ConditionVariable noQ;
    public final Lazy<FQX> uzr;
    public final ExecutorService vkx;
    public final ConditionVariable wDP;
    public final OWw yPL;
    public final AlexaClientEventBus zQM;
    public final OQS zyO;
    public final XTJ zzR;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MediaPlayersAuthority.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public class BIo implements JYe.zZm {
        public final vQe zZm;

        public BIo(vQe vqe) {
            this.zZm = vqe;
        }
    }

    /* compiled from: MediaPlayersAuthority.java */
    /* loaded from: classes.dex */
    private class zQM extends UcG implements Runnable {
        public final Set<yfS> BIo;
        public final AlexaClientEventBus zQM;
        public final Set<ComponentState> zZm;

        public zQM(Set<ComponentState> set, Set<yfS> set2, AlexaClientEventBus alexaClientEventBus) {
            this.zZm = set;
            this.BIo = set2;
            this.zQM = alexaClientEventBus;
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
            Log.w(zmg.zZm, "Failed to reported external media players");
            if (zmg.this.NXS < 3) {
                this.zQM.zyO(new xie());
                zmg.yPL(zmg.this);
            }
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onSuccess(RrI rrI, Collection<Message> collection) {
            Log.i(zmg.zZm, "Successfully reported external media players");
            synchronized (zmg.this.yPL) {
                zmg.zZm(zmg.this.JTe, this.BIo);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!zmg.this.wDP.block(10000L)) {
                return;
            }
            HashSet hashSet = new HashSet();
            for (yfS yfs : this.BIo) {
                bve bveVar = (bve) yfs;
                C0179Pya.zZm(C0179Pya.zZm("reporting: "), bveVar.zQM, zmg.zZm);
                hashSet.add(new AutoValue_ReportDiscoveredPlayersPayload_Player(bveVar.zQM, bveVar.jiA, bveVar.yPL, vST.BIo.zZm.SIGNING_CERTIFICATE));
            }
            LTt.zZm zzm = (LTt.zZm) vST.zZm();
            zzm.zZm = "JR410H5Y27";
            zzm.BIo = hashSet;
            String str = "";
            if (zzm.zZm == null) {
                str = C0179Pya.zZm(str, " agent");
            }
            if (zzm.BIo == null) {
                str = C0179Pya.zZm(str, " players");
            }
            if (str.isEmpty()) {
                Message create = Message.create(Header.builder().setNamespace(AvsApiConstants.ExternalMediaPlayer.zZm).setName(AvsApiConstants.ExternalMediaPlayer.Events.ReportDiscoveredPlayers.zZm).build(), new AutoValue_ReportDiscoveredPlayersPayload(zzm.zZm, zzm.BIo));
                AlexaClientEventBus alexaClientEventBus = this.zQM;
                WXj.zZm zzm2 = (WXj.zZm) JjI.BIo().zZm(create);
                zzm2.jiA = this.zZm;
                alexaClientEventBus.zyO(zzm2.zZm(this).zZm());
                return;
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }

    /* compiled from: MediaPlayersAuthority.java */
    /* loaded from: classes.dex */
    private class zZm implements Runnable {
        public /* synthetic */ zZm(BSz bSz) {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Map<FHI, yfS> BIo = zmg.this.JTe.BIo();
                String str = zmg.zZm;
                StringBuilder sb = new StringBuilder();
                sb.append("Cached media player count: ");
                sb.append(BIo.size());
                Log.i(str, sb.toString());
                for (yfS yfs : BIo.values()) {
                    if (yfS.zZm.AUTHORIZED == ((bve) yfs).LPk) {
                        vQe vqe = ((bve) yfs).BIo;
                        JYe zZm = zmg.this.zZm(yfs, zmg.this.zyO.zZm(yfs, zmg.this.XWf, zmg.this.Tbw));
                        String str2 = zmg.zZm;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Loaded External Media Player: ");
                        sb2.append(vqe);
                        Log.i(str2, sb2.toString());
                        zmg.this.zZm(vqe, zZm);
                    }
                }
            } finally {
                zmg.this.noQ.open();
            }
        }
    }

    @Inject
    public zmg(AlexaClientEventBus alexaClientEventBus, Blk blk, OQS oqs, yWS yws, OWw oWw, aim aimVar, XTJ xtj, Lazy<Boy> lazy, Lazy<HTC> lazy2, Lazy<FQX> lazy3, Lazy<C0245zoo> lazy4, qKe qke, vkx vkxVar, gSO gso) {
        ExecutorService newSingleThreadCachedThreadPool = ManagedExecutorFactory.newSingleThreadCachedThreadPool("media-player-authority", ManagedExecutorFactory.Group.INITIALIZATION);
        this.zQM = alexaClientEventBus;
        this.zyO = oqs;
        this.jiA = blk;
        this.Qle = GeneratedOutlineSupport1.outline136();
        this.JTe = yws;
        this.yPL = oWw;
        this.LPk = qke;
        this.Mlj = aimVar;
        this.zzR = xtj;
        this.lOf = lazy;
        this.dMe = lazy2;
        this.uzr = lazy3;
        this.HvC = lazy4;
        this.vkx = newSingleThreadCachedThreadPool;
        this.wDP = new ConditionVariable();
        this.noQ = new ConditionVariable();
        this.Qgh = new AtomicReference<>(vQe.zZm);
        this.zQM.zZm(this);
        this.vkx.submit(new zZm(null));
        this.NXS = 0;
        this.Tbw = vkxVar;
        this.XWf = gso;
    }

    public static /* synthetic */ int yPL(zmg zmgVar) {
        int i = zmgVar.NXS;
        zmgVar.NXS = i + 1;
        return i;
    }

    @Subscribe(sticky = true)
    public void on(Bob bob) {
        if (bob.BIo()) {
            this.wDP.open();
        }
    }

    @VisibleForTesting
    public static Set<yfS> zZm(Map<FHI, yfS> map, Set<yfS> set) {
        Set<String> set2;
        HashSet hashSet = new HashSet();
        for (yfS yfs : set) {
            bve bveVar = (bve) yfs;
            if (map.containsKey(bveVar.zQM)) {
                yfS yfs2 = map.get(bveVar.zQM);
                bve bveVar2 = (bve) yfs2;
                if (yfS.zZm.UNKNOWN == bveVar2.LPk) {
                    hashSet.add(yfs);
                } else if (!(Objects.equals(bveVar.zyO, bveVar2.zyO) && Objects.equals(bveVar.jiA, bveVar2.jiA) && Objects.equals(bveVar.Qle, bveVar2.Qle) && Objects.equals(bveVar.JTe, bveVar2.JTe) && (set2 = bveVar2.yPL) != null && bveVar.yPL.containsAll(set2) && bveVar2.yPL.containsAll(bveVar.yPL))) {
                    String.format("Changing registration for %s changed. Before: %s. After: %s", bveVar.zQM, yfs2, yfs);
                    hashSet.add(yfs);
                }
            } else {
                hashSet.add(yfs);
            }
        }
        return hashSet;
    }

    public final Set<ComponentState> BIo() {
        HashSet hashSet = new HashSet();
        hashSet.add(this.lOf.mo358get().getState());
        hashSet.add(this.dMe.mo358get().getState());
        FQX mo358get = this.uzr.mo358get();
        ComponentStateHeader componentStateHeader = mo358get.zZm;
        Map<aVo, aNh> JTe = mo358get.zQM.JTe();
        plk.zZm zZm2 = plk.zZm();
        if (mo358get.zZm(aVo.DIALOG, JTe)) {
            zZm2.zyO(mo358get.zZm(JTe.get(aVo.DIALOG)));
        }
        if (mo358get.zZm(aVo.COMMUNICATIONS, JTe)) {
            zZm2.BIo(mo358get.zZm(JTe.get(aVo.COMMUNICATIONS)));
        }
        if (mo358get.zZm(aVo.ALERTS, JTe)) {
            zZm2.zZm(mo358get.zZm(JTe.get(aVo.ALERTS)));
        }
        if (mo358get.zZm(aVo.CONTENT, JTe)) {
            zZm2.zQM(mo358get.zZm(JTe.get(aVo.CONTENT)));
        }
        hashSet.add(ComponentState.create(componentStateHeader, zZm2.zZm()));
        return Qle() ? zQM(hashSet) : hashSet;
    }

    public void JTe() {
        this.JTe.zZm();
        this.yPL.zZm();
        this.Mlj.zZm();
        this.zzR.zZm();
    }

    public void LPk() {
        for (JYe jYe : this.Qle.values()) {
            jYe.zOR();
        }
        this.jiA.teardown();
        this.zQM.BIo(this);
        ManagedExecutorFactory.shutdown("media-player-authority");
    }

    public final boolean Qle() {
        if (this.jiA.isAvailable()) {
            C0245zoo mo358get = this.HvC.mo358get();
            StringBuilder zZm2 = C0179Pya.zZm("isAccessory Available? ");
            zZm2.append(mo358get.zZm());
            zZm2.toString();
            if (mo358get.zZm()) {
                return true;
            }
        }
        return false;
    }

    public Blk jiA() {
        return this.jiA;
    }

    @VisibleForTesting
    public vQe zQM() {
        return this.Qgh.get();
    }

    public final String zyO() {
        vQe vqe = this.Qgh.get();
        return vQe.zZm.equals(vqe) ? "unknown" : vqe.getValue();
    }

    @Subscribe
    public void on(FXk fXk) {
        YLU ylu = (YLU) fXk;
        zZm(ylu.BIo, ylu.zQM);
    }

    public Set<ComponentState> zQM(Set<ComponentState> set) {
        if (!Qle()) {
            return set;
        }
        vQe vqe = vQe.zZm;
        if (this.jiA.zZm()) {
            StringBuilder zZm2 = C0179Pya.zZm("Last External Media Player in focus: ");
            zZm2.append(zyO());
            zZm2.toString();
            for (Map.Entry<vQe, JYe> entry : zZm().entrySet()) {
                vQe key = entry.getKey();
                if (entry.getValue().HvC()) {
                    vqe = key;
                }
            }
        } else {
            Log.i(zZm, "No external media player was playing music");
        }
        String str = zZm;
        StringBuilder zZm3 = C0179Pya.zZm("correctExternalMediaPlayerStates, playerInFocus: ");
        zZm3.append(zyO());
        Log.i(str, zZm3.toString());
        return this.jiA.zZm(vqe, set);
    }

    public void zyO(Set<yfS> set) {
        synchronized (this.JTe) {
            StringBuilder sb = new StringBuilder();
            sb.append("updateAvailablePlayerRegistrations: ");
            sb.append(set.size());
            sb.toString();
            Map<FHI, yfS> BIo2 = this.JTe.BIo();
            Set<yfS> zZm2 = zZm(BIo2, set);
            Set<vQe> zZm3 = zZm(this.JTe, BIo2, set);
            BIo(zZm3);
            zZm(zZm3);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<yfS> it2 = zZm2.iterator();
            while (it2.hasNext()) {
                linkedHashSet.add(((bve) it2.next()).BIo);
            }
            BIo(linkedHashSet);
            zZm(linkedHashSet);
            if (!zZm2.isEmpty()) {
                String str = zZm;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Found ");
                sb2.append(zZm2.size());
                sb2.append(" new external media players");
                Log.i(str, sb2.toString());
                this.vkx.submit(new zQM(BIo(), zZm2, this.zQM));
            }
        }
    }

    @Subscribe
    public void on(ddC ddc) {
        SLO slo = (SLO) ddc;
        zZm(slo.BIo, slo.zQM, slo.zyO);
        vQe vqe = slo.BIo;
        if (!slo.zQM.BIo() || vqe.equals(this.Qgh.getAndSet(vqe))) {
            return;
        }
        String str = zZm;
        Log.i(str, "New External Media Player in focus: " + vqe);
    }

    @Nullable
    public JYe zQM(vQe vqe) {
        return this.Qle.get(vqe);
    }

    @VisibleForTesting
    public void BIo(Set<vQe> set) {
        if (set.isEmpty()) {
            return;
        }
        for (vQe vqe : set) {
            if (this.Qle.containsKey(vqe)) {
                this.Qle.remove(vqe).zOR();
            }
        }
    }

    @VisibleForTesting
    public static void zZm(yWS yws, Set<yfS> set) {
        for (yfS yfs : set) {
            yws.BIo(((bve) yfs).zQM, yfs);
        }
    }

    @VisibleForTesting
    public BIo BIo(vQe vqe) {
        return new BIo(vqe);
    }

    @VisibleForTesting
    public static Set<vQe> zZm(yWS yws, Map<FHI, yfS> map, Set<yfS> set) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Iterator<yfS> it2 = set.iterator();
        while (it2.hasNext()) {
            hashSet2.add(((bve) it2.next()).zQM);
        }
        for (Map.Entry<FHI, yfS> entry : map.entrySet()) {
            FHI key = entry.getKey();
            yfS value = entry.getValue();
            if (!hashSet2.contains(key)) {
                yws.BIo((yWS) key);
                hashSet.add(((bve) value).BIo);
            }
        }
        return hashSet;
    }

    @VisibleForTesting
    public void zZm(vQe vqe, vat vatVar, GWl gWl) {
        Hir hir;
        if (vatVar == null) {
            return;
        }
        HkJ zyO = this.yPL.zyO(vqe);
        if (vatVar == vat.PLAYBACK_SESSION_ENDED || vatVar == vat.PLAYBACK_SESSION_STARTED) {
            if (GWl.zZm.equals(gWl) || TextUtils.isEmpty(gWl.getValue())) {
                return;
            }
        } else if (zyO != null) {
            gWl = ((Roh) zyO).lOf;
        } else {
            gWl = GWl.zZm;
        }
        Qds.zZm zZm2 = Qds.zZm();
        String zZm3 = vatVar.zZm();
        bXd.zZm zzm = (bXd.zZm) zZm2;
        if (zZm3 != null) {
            zzm.zZm = zZm3;
            if (vqe != null) {
                zzm.BIo = vqe;
                if (zyO != null) {
                    hir = ((Roh) zyO).zzR;
                } else {
                    hir = Hir.zZm;
                }
                if (hir != null) {
                    zzm.zQM = hir;
                    zzm.zyO = gWl;
                    String str = "";
                    if (zzm.zZm == null) {
                        str = C0179Pya.zZm(str, " eventName");
                    }
                    if (zzm.BIo == null) {
                        str = C0179Pya.zZm(str, " playerId");
                    }
                    if (zzm.zQM == null) {
                        str = C0179Pya.zZm(str, " skillToken");
                    }
                    if (str.isEmpty()) {
                        AutoValue_PlayerEventPayload autoValue_PlayerEventPayload = new AutoValue_PlayerEventPayload(zzm.zZm, zzm.BIo, zzm.zQM, zzm.zyO);
                        String.format("sendPlayerEvent: %s [playbackSessionId: %s]", vatVar.zZm(), gWl.getValue());
                        zZm(AvsApiConstants.ExternalMediaPlayer.Events.PlayerEvent.zZm, autoValue_PlayerEventPayload);
                        return;
                    }
                    throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
                }
                throw new NullPointerException("Null skillToken");
            }
            throw new NullPointerException("Null playerId");
        }
        throw new NullPointerException("Null eventName");
    }

    @VisibleForTesting
    public void zZm(vQe vqe, pfe pfeVar) {
        Hir hir;
        GWl gWl;
        if (pfeVar == null) {
            return;
        }
        HkJ zyO = this.yPL.zyO(vqe);
        qQM.zZm zzm = (qQM.zZm) Rgi.zZm();
        zzm.jiA = vqe;
        PUa pUa = (PUa) pfeVar;
        Iof iof = pUa.zZm;
        if (iof != null) {
            zzm.zZm = iof;
            Long valueOf = Long.valueOf(pUa.BIo);
            if (valueOf != null) {
                zzm.BIo = valueOf;
                String str = pUa.jiA;
                if (str != null) {
                    zzm.zQM = str;
                    Boolean valueOf2 = Boolean.valueOf(pUa.zQM);
                    if (valueOf2 != null) {
                        zzm.zyO = valueOf2;
                        if (zyO != null) {
                            hir = ((Roh) zyO).zzR;
                        } else {
                            hir = Hir.zZm;
                        }
                        zzm.Qle = hir;
                        if (zyO != null) {
                            gWl = ((Roh) zyO).lOf;
                        } else {
                            gWl = GWl.zZm;
                        }
                        zzm.JTe = gWl;
                        String str2 = "";
                        if (zzm.zZm == null) {
                            str2 = C0179Pya.zZm(str2, " errorName");
                        }
                        if (zzm.BIo == null) {
                            str2 = C0179Pya.zZm(str2, " code");
                        }
                        if (zzm.zQM == null) {
                            str2 = C0179Pya.zZm(str2, " description");
                        }
                        if (zzm.zyO == null) {
                            str2 = C0179Pya.zZm(str2, " fatal");
                        }
                        if (str2.isEmpty()) {
                            zZm(AvsApiConstants.ExternalMediaPlayer.Events.PlayerError.zZm, new AutoValue_PlayerErrorPayload(zzm.zZm, zzm.BIo, zzm.zQM, zzm.zyO, zzm.jiA, zzm.Qle, zzm.JTe));
                            return;
                        }
                        throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str2));
                    }
                    throw new NullPointerException("Null fatal");
                }
                throw new NullPointerException("Null description");
            }
            throw new NullPointerException("Null code");
        }
        throw new NullPointerException("Null errorName");
    }

    public final void zZm(Name name, Payload payload) {
        Message create = Message.create(Header.builder().setNamespace(AvsApiConstants.ExternalMediaPlayer.zZm).setName(name).build(), payload);
        Set<ComponentState> BIo2 = BIo();
        AlexaClientEventBus alexaClientEventBus = this.zQM;
        WXj.zZm zzm = (WXj.zZm) JjI.BIo().zZm(create);
        zzm.jiA = BIo2;
        alexaClientEventBus.zyO(zzm.zZm());
    }

    @VisibleForTesting
    public void zZm(Set<vQe> set) {
        vQe zyO = this.zzR.zyO(XjE.PLAYER_IN_FOCUS);
        if (zyO == null || !set.contains(zyO)) {
            return;
        }
        this.zzR.zQM(XjE.PLAYER_IN_FOCUS, vQe.zZm);
    }

    public Map<vQe, JYe> zZm() {
        this.noQ.block(10000L);
        return this.Qle;
    }

    public void zZm(FHI fhi, vQe vqe, Hir hir) {
        synchronized (this.JTe) {
            yfS zyO = this.JTe.zyO(fhi);
            if (zyO != null) {
                yfS zZm2 = yfS.zZm(vqe, zyO);
                this.JTe.BIo(fhi, zZm2);
                JYe zZm3 = JYe.zZm(this.zQM, zZm2, this.zyO.zZm(zZm2, this.XWf, this.Tbw), hir, this.yPL, BIo(vqe), this.LPk, this.XWf);
                String str = zZm;
                StringBuilder sb = new StringBuilder();
                sb.append("Found External Media Player: ");
                sb.append(vqe);
                Log.i(str, sb.toString());
                zZm(vqe, zZm3);
            } else {
                Log.w(zZm, "Trying to authorize a player which is not registered.");
            }
        }
    }

    public void zZm(FHI fhi) {
        synchronized (this.JTe) {
            yfS zyO = this.JTe.zyO(fhi);
            if (zyO != null) {
                this.JTe.BIo(fhi, yfS.zZm(zyO));
            }
        }
    }

    public void zZm(vQe vqe) {
        JYe jYe = this.Qle.get(vqe);
        if (jYe != null) {
            jYe.zZm();
        }
    }

    public vQe zZm(String str) {
        for (Map.Entry<FHI, yfS> entry : this.JTe.BIo().entrySet()) {
            bve bveVar = (bve) entry.getValue();
            if (str.equals(bveVar.zyO.getPackageName())) {
                if (bveVar.LPk == yfS.zZm.AUTHORIZED) {
                    return bveVar.BIo;
                }
            }
        }
        GeneratedOutlineSupport1.outline164("Could not find matched registration by broadcastComponentName for package: ", str, zZm);
        return null;
    }

    @VisibleForTesting
    public void zZm(vQe vqe, JYe jYe) {
        this.Qle.put(vqe, jYe);
    }

    @VisibleForTesting
    public JYe zZm(yfS yfs, bYx byx) {
        bve bveVar = (bve) yfs;
        vQe vqe = bveVar.BIo;
        AlexaClientEventBus alexaClientEventBus = this.zQM;
        OWw oWw = this.yPL;
        BIo BIo2 = BIo(vqe);
        qKe qke = this.LPk;
        gSO gso = this.XWf;
        vQe vqe2 = bveVar.BIo;
        JYe jYe = new JYe(alexaClientEventBus, yfs, byx, oWw, BIo2, qke, gso);
        oWw.zZm((OWw) vqe2);
        return jYe;
    }
}
