package com.amazon.alexa;

import com.amazon.alexa.WlV;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.alexaservice.speaker.payload.AutoValue_VolumeStatePayload;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SpeakerCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class DJb extends BaseCapabilityAgent implements dRG {
    public static final ComponentStateHeader zZm = ComponentStateHeader.zZm(AvsApiConstants.Speaker.zZm, AvsApiConstants.Speaker.ComponentStates.VolumeState.zZm);
    public final AlexaClientEventBus BIo;
    public final QMz zQM;
    public final vVi zyO;

    @Inject
    public DJb(AlexaClientEventBus alexaClientEventBus, QMz qMz, vVi vvi) {
        super(Capability.create(AvsApiConstants.Speaker.BIo, "1.0"));
        this.BIo = alexaClientEventBus;
        this.zQM = qMz;
        this.zyO = vvi;
    }

    @Override // com.amazon.alexa.dRG
    public ComponentState getState() {
        WlV.zZm zzm = (WlV.zZm) vRe.zZm();
        zzm.BIo = Boolean.valueOf(this.zQM.jiA);
        QMz qMz = this.zQM;
        zzm.zZm = Long.valueOf(qMz.BIo(qMz.zyO()));
        String str = "";
        if (zzm.zZm == null) {
            str = C0179Pya.zZm(str, " volume");
        }
        if (zzm.BIo == null) {
            str = C0179Pya.zZm(str, " muted");
        }
        if (str.isEmpty()) {
            return ComponentState.create(zZm, new AutoValue_VolumeStatePayload(zzm.zZm.longValue(), zzm.BIo.booleanValue()));
        }
        throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onCancel(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onPreprocess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        this.zQM.Qle = this.zyO.zZm(message.getMessageMetadata().getOriginatingDialogRequestIdentifier());
        Name name = message.getHeader().getName();
        if (AvsApiConstants.Speaker.Directives.SetMute.zZm.equals(name)) {
            boolean z = ((jrX) message.getPayload()).zZm;
            this.zQM.zZm(z);
            this.BIo.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.Speaker.zZm).setName(AvsApiConstants.Speaker.Events.MuteChanged.zZm).build(), VCD.zZm().zZm(z).zZm(this.zQM.zZm()).zZm())).zZm());
        } else if (AvsApiConstants.Speaker.Directives.SetVolume.zZm.equals(name)) {
            Payload payload = message.getPayload();
            QMz qMz = this.zQM;
            qMz.zZm(qMz.zZm(((gHX) payload).zZm));
        } else if (AvsApiConstants.Speaker.Directives.AdjustVolume.zZm.equals(name)) {
            Payload payload2 = message.getPayload();
            QMz qMz2 = this.zQM;
            qMz2.zZm(Math.max(Math.min(qMz2.zyO() + qMz2.zZm(((jEt) payload2).zZm), qMz2.BIo.getStreamMaxVolume(qMz2.BIo())), 0));
        }
        messageProcessingCallbacks.onFinished();
    }

    @Override // com.amazon.alexa.dRG
    public ComponentStateHeader zZm() {
        return zZm;
    }
}
