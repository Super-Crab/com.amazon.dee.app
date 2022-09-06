package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageMetadata;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: RequestEventAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class DYu {
    public static final String zZm = "DYu";
    public final AlexaClientEventBus BIo;
    public final Map<RrI, JjI> zQM = new HashMap();
    public final Map<RrI, List<Message>> zyO = new HashMap();
    public final Map<JjI, RrI> jiA = new HashMap();

    @Inject
    public DYu(AlexaClientEventBus alexaClientEventBus) {
        this.BIo = alexaClientEventBus;
    }

    @Nullable
    public synchronized String BIo(RrI rrI) {
        if (this.zQM.containsKey(rrI)) {
            return ((WXj) this.zQM.get(rrI)).zQM.getHeader().getName().getValue();
        }
        return null;
    }

    public synchronized boolean zQM(RrI rrI) {
        if (rrI == null) {
            return false;
        }
        if (!this.zQM.containsKey(rrI)) {
            return false;
        }
        return AvsApiConstants.Input.Text.zZm.equals(((WXj) this.zQM.get(rrI)).zQM.getHeader().getNamespace());
    }

    public synchronized void zZm(RrI rrI, JjI jjI) {
        String.format("Starting event. ReqId=%s, Event=%s", rrI, jjI);
        this.zQM.put(rrI, jjI);
        this.zyO.put(rrI, new LinkedList());
        this.jiA.put(jjI, rrI);
        this.BIo.zyO(new aeu(rrI));
    }

    public synchronized boolean zyO(RrI rrI) {
        List<Message> list = this.zyO.get(rrI);
        if (list == null) {
            return false;
        }
        for (Message message : list) {
            if (AvsApiConstants.zZm(AvsApiConstants.SpeechSynthesizer.zZm, AvsApiConstants.SpeechSynthesizer.Directives.Speak.zZm, message)) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean zZm(RrI rrI) {
        return this.zQM.containsKey(rrI);
    }

    public synchronized void zZm(RrI rrI, Message message, @Nullable String str) {
        DialogRequestIdentifier originatingDialogRequestIdentifier;
        String.format("Adding response to request. ReqId=%s, Response=%s", rrI, message);
        List<Message> list = this.zyO.get(rrI);
        JjI jjI = this.zQM.get(rrI);
        if (jjI != null) {
            WXj wXj = (WXj) jjI;
            Message message2 = wXj.zQM;
            if (message2.hasDialogRequestIdentifier()) {
                originatingDialogRequestIdentifier = message2.getDialogRequestIdentifier();
            } else {
                originatingDialogRequestIdentifier = message2.getMessageMetadata().getOriginatingDialogRequestIdentifier();
            }
            message.setMessageMetadata(MessageMetadata.create(str, originatingDialogRequestIdentifier));
            wXj.yPL.onMessageReceived(rrI, message);
        } else {
            message.setMessageMetadata(MessageMetadata.create(str, DialogRequestIdentifier.NONE));
        }
        if (list != null) {
            list.add(message);
        }
    }

    public synchronized boolean zZm(RrI rrI, boolean z, @Nullable Integer num, @Nullable Exception exc) {
        String.format("Finishing event. ReqId=%s, Success=%b", rrI, Boolean.valueOf(z));
        List<Message> remove = this.zyO.remove(rrI);
        JjI remove2 = this.zQM.remove(rrI);
        this.jiA.remove(remove2);
        if (remove2 == null) {
            return false;
        }
        TtM ttM = ((WXj) remove2).yPL;
        DialogRequestIdentifier dialogRequestIdentifier = ((Fkl) rrI).BIo;
        if (dialogRequestIdentifier != null && remove != null) {
            Log.i(zZm, String.format("DialogRequestId %s had %d directive(s).", dialogRequestIdentifier.getValue(), Integer.valueOf(remove.size())));
        }
        if (z) {
            if (remove == null) {
                remove = Collections.emptyList();
            }
            ttM.onSuccess(rrI, remove);
        } else {
            ttM.onFailure(rrI, num, exc);
        }
        this.BIo.zyO(new Kzp(rrI));
        return true;
    }

    public synchronized boolean zZm(JjI jjI, boolean z, @Nullable Integer num, @Nullable Exception exc) {
        boolean z2;
        z2 = false;
        String.format("Finishing event. Event=%s, Success=%b", jjI, Boolean.valueOf(z));
        RrI rrI = this.jiA.get(jjI);
        if (rrI != null) {
            if (zZm(rrI, z, num, exc)) {
                z2 = true;
            }
        }
        return z2;
    }
}
