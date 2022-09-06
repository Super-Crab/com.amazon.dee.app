package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.PJz;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.EmptyPayload;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.xik;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AudioPlayerCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class HVk extends BaseCapabilityAgent {
    @VisibleForTesting
    public static final PJz BIo = PJz.zZm(PJz.zQM.MUSIC, PJz.BIo.PERSISTENT, PJz.zyO.MEDIA, PJz.zZm.MUSIC);
    @VisibleForTesting
    public static final PJz zQM = new PNy(PJz.zQM.MUSIC, PJz.BIo.PERSISTENT, PJz.zyO.MEDIA, PJz.zZm.MUSIC, false);
    public static final String zZm = "HVk";
    public final Map<MessageIdentifier, kQf> JTe;
    public final oGE LPk;
    public final Set<MessageIdentifier> Mlj;
    public final chR Qle;
    public BXc dMe;
    public final AlexaClientEventBus jiA;
    public boolean lOf;
    public Bha yPL;
    public final TLe zyO;
    public final BcO zzR;

    @Inject
    public HVk(TLe tLe, AlexaClientEventBus alexaClientEventBus, oGE oge, chR chr) {
        super(Capability.create(AvsApiConstants.AudioPlayer.BIo, "1.3"));
        this.jiA = alexaClientEventBus;
        this.zyO = tLe;
        this.Qle = chr;
        this.LPk = oge;
        this.zzR = new BcO();
        this.Mlj = new HashSet();
        this.JTe = new LinkedHashMap();
    }

    public final Puy BIo() {
        return this.LPk.zQM();
    }

    public synchronized void jiA() {
        zyO();
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public synchronized void onCancel(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        Bha bha;
        C0179Pya.zZm("Cancelling message: ", (Object) message);
        if (AvsApiConstants.AudioPlayer.Directives.Play.zZm.equals(message.getHeader().getName())) {
            zZm();
            kQf remove = this.JTe.remove(message.getMessageIdentifier());
            if (remove != null) {
                if (this.yPL != null && this.Mlj.contains(message.getMessageIdentifier())) {
                    this.yPL.lOf(remove);
                    this.Mlj.remove(message.getMessageIdentifier());
                } else if (!this.zzR.BIo()) {
                    BcO bcO = this.zzR;
                    MessageIdentifier messageIdentifier = message.getMessageIdentifier();
                    Iterator<Map.Entry<Bha, Set<MessageIdentifier>>> it2 = bcO.zZm.entrySet().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            bha = null;
                            break;
                        }
                        Map.Entry<Bha, Set<MessageIdentifier>> next = it2.next();
                        if (next.getValue().remove(messageIdentifier)) {
                            bha = next.getKey();
                            break;
                        }
                    }
                    if (bha != null) {
                        bha.lOf(remove);
                        BcO bcO2 = this.zzR;
                        if (bcO2.zZm.containsKey(bha) && bcO2.zZm.get(bha).isEmpty()) {
                            BcO bcO3 = this.zzR;
                            bcO3.BIo.remove(bha);
                            bcO3.zZm.remove(bha);
                            bha.zZm(false);
                            this.lOf = false;
                        } else {
                            this.lOf = true;
                        }
                    }
                }
                this.jiA.zyO(CKO.zZm(AlexaPlayerInfoState.CANCELLED, remove.BIo, 0L));
            } else {
                String str = zZm;
                Log.e(str, "Could not cancel message: " + message);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00ad A[Catch: all -> 0x018c, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0021, B:27:0x00ad, B:29:0x00d2, B:31:0x00d8, B:35:0x00df, B:44:0x0168, B:36:0x0111, B:38:0x0115, B:39:0x0145, B:41:0x014d, B:43:0x0151, B:8:0x003e, B:9:0x004a, B:11:0x0050, B:13:0x0059, B:14:0x0063, B:18:0x006c, B:21:0x0078, B:23:0x008c, B:25:0x0092, B:45:0x0177, B:47:0x017f, B:49:0x0187), top: B:55:0x0001 }] */
    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void onPreprocess(com.amazon.alexa.client.core.messages.Message r12, com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks r13) {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.HVk.onPreprocess(com.amazon.alexa.client.core.messages.Message, com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks):void");
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public synchronized void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        boolean z;
        boolean z2;
        Bha bha;
        kQf remove;
        int ordinal;
        String str = "Processing message: " + message;
        Name name = message.getHeader().getName();
        if (AvsApiConstants.AudioPlayer.Directives.Play.zZm.equals(name)) {
            xik.zZm zzm = ((UJB) message.getPayload()).zZm;
            if (!this.zzR.BIo() && this.zzR.zZm().contains(message.getMessageIdentifier())) {
                Bha bha2 = this.yPL;
                if (bha2 != null) {
                    bha2.zZm(true);
                    this.Mlj.clear();
                }
                Set<MessageIdentifier> zZm2 = this.zzR.zZm();
                if (!zZm2.contains(message.getMessageIdentifier())) {
                    Log.e(zZm, "Upcoming Interaction doesn't contain expected message");
                    z2 = false;
                    if (z2 && (bha = this.yPL) != null) {
                        this.LPk.zZm(bha);
                        remove = this.JTe.remove(message.getMessageIdentifier());
                        boolean z3 = !this.yPL.lOf();
                        ordinal = zzm.ordinal();
                        if (ordinal == 0 && ordinal != 1) {
                            z = this.yPL.noQ(remove);
                        } else {
                            z = this.yPL.dMe(remove);
                        }
                        if (z && z3) {
                            Bha bha3 = this.yPL;
                            DialogRequestIdentifier originatingDialogRequestIdentifier = message.getOriginatingDialogRequestIdentifier();
                            this.lOf = false;
                            this.jiA.zyO(mZe.zZm(aVo.CONTENT, bha3, BIo, originatingDialogRequestIdentifier));
                        }
                        zZm();
                    }
                    z = false;
                } else {
                    this.yPL = this.zzR.BIo.getFirst();
                    this.Mlj.addAll(zZm2);
                    this.zzR.zZm(this.yPL);
                }
            }
            z2 = true;
            if (z2) {
                this.LPk.zZm(bha);
                remove = this.JTe.remove(message.getMessageIdentifier());
                boolean z32 = !this.yPL.lOf();
                ordinal = zzm.ordinal();
                if (ordinal == 0) {
                }
                z = this.yPL.dMe(remove);
                if (z) {
                    Bha bha32 = this.yPL;
                    DialogRequestIdentifier originatingDialogRequestIdentifier2 = message.getOriginatingDialogRequestIdentifier();
                    this.lOf = false;
                    this.jiA.zyO(mZe.zZm(aVo.CONTENT, bha32, BIo, originatingDialogRequestIdentifier2));
                }
                zZm();
            }
            z = false;
        } else {
            if (AvsApiConstants.AudioPlayer.Directives.Stop.zZm.equals(name)) {
                Bha bha4 = this.yPL;
                if (bha4 != null && bha4.lOf()) {
                    zyO();
                }
                if (!((BkS) this.LPk.BIo()).zQM.equals(AUQ.IDLE)) {
                    this.LPk.zZm(null, AUQ.STOPPED, -1L);
                }
            } else {
                if (AvsApiConstants.AudioPlayer.Directives.ClearQueue.zZm.equals(name)) {
                    Payload payload = message.getPayload();
                    Bha bha5 = this.yPL;
                    if (bha5 != null && bha5.lOf() && !this.lOf) {
                        if (((kXG) payload).zZm.ordinal() != 1) {
                            this.yPL.yPL();
                        } else {
                            zyO();
                        }
                    }
                    this.jiA.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.AudioPlayer.zZm).setName(AvsApiConstants.AudioPlayer.Events.PlaybackQueueCleared.zZm).build(), EmptyPayload.create(), message.getMessageMetadata())).zZm());
                }
                z = false;
            }
            z = true;
        }
        if (z) {
            messageProcessingCallbacks.onFinished();
        } else {
            messageProcessingCallbacks.onError();
        }
    }

    public synchronized void zQM() {
        zyO();
        this.LPk.zyO();
    }

    public final void zZm() {
        BXc bXc = this.dMe;
        if (bXc != null) {
            this.jiA.zyO(LBB.zZm(bXc.zZm));
        }
    }

    public final void zyO() {
        Bha bha = this.yPL;
        if (bha != null) {
            bha.zZm(true);
        }
        this.yPL = null;
        this.LPk.zZm((Bha) null);
        this.Mlj.clear();
    }
}
