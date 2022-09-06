package com.amazon.alexa;

import com.amazon.alexa.WXj;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Name;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: PlaybackController.java */
@Singleton
/* loaded from: classes.dex */
public class fla {
    public final lhN BIo;
    public final AlexaClientEventBus zZm;

    @Inject
    public fla(AlexaClientEventBus alexaClientEventBus, lhN lhn) {
        this.BIo = lhn;
        this.zZm = alexaClientEventBus;
    }

    public void BIo() {
        zZm(AvsApiConstants.PlaybackController.Events.PlayCommandIssued.zZm);
    }

    public void zQM() {
        zZm(AvsApiConstants.PlaybackController.Events.PreviousCommandIssued.zZm);
    }

    public void zZm() {
        zZm(AvsApiConstants.PlaybackController.Events.NextCommandIssued.zZm);
    }

    public final void zZm(Name name) {
        Message create = Message.create(Header.builder().setName(name).setNamespace(AvsApiConstants.PlaybackController.zZm).build());
        AlexaClientEventBus alexaClientEventBus = this.zZm;
        WXj.zZm zzm = (WXj.zZm) JjI.BIo();
        zzm.jiA = this.BIo.BIo(false);
        alexaClientEventBus.zyO(zzm.zZm(create).zZm());
    }
}
