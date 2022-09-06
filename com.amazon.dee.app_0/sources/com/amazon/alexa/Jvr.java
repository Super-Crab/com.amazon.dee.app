package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.NEv;
import com.amazon.alexa.QYV;
import com.amazon.alexa.WXj;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.TextResponse;
import com.amazon.alexa.api.TextResponseMetadata;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.tAW;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: TextCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class Jvr extends BaseCapabilityAgent {
    public static final String zZm = "Jvr";
    public final AlexaClientEventBus BIo;
    public final tAW JTe;
    public final ScheduledExecutorService Qle;
    public final lhN jiA;
    public final Wyh zQM;
    public final vkx zyO;

    @Inject
    public Jvr(AlexaClientEventBus alexaClientEventBus, Wyh wyh, vkx vkxVar, lhN lhn, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService, yjS yjs, tAW taw) {
        super(Capability.create(AvsApiConstants.Input.Text.BIo, "1.0"));
        this.BIo = alexaClientEventBus;
        this.BIo.zZm(this);
        this.zQM = wyh;
        this.zyO = vkxVar;
        this.jiA = lhn;
        this.Qle = scheduledExecutorService;
        this.JTe = taw;
    }

    @Subscribe
    public void on(QYV.jiA jia) {
        this.Qle.execute(new BbQ(this, jia));
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onCancel(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        MessageIdentifier messageIdentifier = message.getMessageIdentifier();
        C0179Pya.zZm("Cancelling message: ", (Object) messageIdentifier);
        tAW taw = this.JTe;
        taw.jiA.execute(new tAW.zZm(messageIdentifier, null));
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        this.zyO.BIo(wSq.THINKING);
        if (AvsApiConstants.Input.Text.Directives.TextMessage.zZm.equals(message.getHeader().getName())) {
            tAW taw = this.JTe;
            taw.jiA.execute(new tAW.BIo(message, messageProcessingCallbacks, null));
            DEe dEe = (DEe) message.getPayload();
            this.BIo.zyO(new fru(TextResponse.builder().setTitle(((UqQ) dEe).zyO).setMetadata(zZm(dEe)).build()));
        } else if (AvsApiConstants.Input.Text.Directives.ExpectText.zZm.equals(message.getHeader().getName())) {
            this.BIo.zyO(new sBz());
            messageProcessingCallbacks.onFinished();
        } else {
            messageProcessingCallbacks.onFinished();
        }
    }

    @Subscribe
    public void on(QYV.zQM zqm) {
        DEe dEe = ((eZz) zqm).BIo;
        this.BIo.zyO(new fru(TextResponse.builder().setTitle(((UqQ) dEe).zyO).setMetadata(zZm(dEe)).build()));
    }

    public final void zZm() {
        tAW taw = this.JTe;
        taw.jiA.execute(new tAW.zyO(null));
    }

    public final void zZm(QYV.jiA jia) {
        Arb arb = (Arb) jia;
        NEe nEe = arb.BIo;
        if (nEe.Tbw()) {
            this.zQM.zQM(nEe);
        } else if (!nEe.XWf()) {
            Log.w(zZm, "multi turn dialog is neither ready to start or started. abandoning");
            this.zQM.zZm(nEe);
            eOP eop = arb.zQM;
            ApiCallFailure.InternalFailure create = ApiCallFailure.InternalFailure.create("abandon dialog since it is not ready to start or started");
            if (eop == null || create == null) {
                return;
            }
            this.BIo.zyO(NEv.zZm.zZm(eop, create));
            return;
        }
        OGm yPL = nEe.yPL();
        if (yPL != null) {
            String Qle = yPL.jiA().Qle();
            nEe.zOR();
            ExtendedClient extendedClient = nEe.BIo;
            Message zZm2 = Qjn.zZm(Qle, nEe.LPk());
            IjO ijO = new IjO(this.BIo, this.zQM, nEe, yPL);
            Set<ComponentState> zZm3 = this.jiA.zZm(true);
            WXj.zZm zzm = (WXj.zZm) JjI.BIo();
            zzm.zZm = extendedClient;
            WXj.zZm zzm2 = (WXj.zZm) zzm.zZm(zZm2).zZm(ijO);
            zzm2.jiA = zZm3;
            this.zyO.zZm(wSq.THINKING);
            this.BIo.zyO(zzm2.zZm());
            eOP eop2 = arb.zQM;
            if (eop2 == null) {
                return;
            }
            this.BIo.zyO(NEv.zQM.zZm(eop2));
            return;
        }
        this.zQM.zZm(nEe);
        eOP eop3 = arb.zQM;
        ApiCallFailure.InternalFailure create2 = ApiCallFailure.InternalFailure.create("current dialog does not have a current active turn");
        if (eop3 == null || create2 == null) {
            return;
        }
        this.BIo.zyO(NEv.zZm.zZm(eop3, create2));
    }

    @Subscribe
    public void on(ISQ isq) {
        this.zyO.BIo(wSq.THINKING);
    }

    @Subscribe
    public void on(kOA koa) {
        this.zyO.BIo(wSq.THINKING);
    }

    @Subscribe
    public void on(QYV.zyO zyo) {
        zZm();
    }

    @Subscribe
    public void on(QYV.BIo bIo) {
        zZm();
    }

    @VisibleForTesting
    public TextResponseMetadata zZm(DEe dEe) {
        TextResponseMetadata.Builder builder = TextResponseMetadata.builder();
        UqQ uqQ = (UqQ) dEe;
        builder.setToken(uqQ.BIo.zZm);
        String str = uqQ.jiA;
        if (str != null) {
            Matcher matcher = Pattern.compile("<promptId>(.*)</promptId>").matcher(str);
            String str2 = "";
            TextResponseMetadata.Builder promptId = builder.setPromptId(matcher.find() ? matcher.group(1) : str2);
            Matcher matcher2 = Pattern.compile("<namespace>(.*)</namespace>").matcher(str);
            TextResponseMetadata.Builder namespace = promptId.setNamespace(matcher2.find() ? matcher2.group(1) : str2);
            Matcher matcher3 = Pattern.compile("<variant>(.*)</variant>").matcher(str);
            if (matcher3.find()) {
                str2 = matcher3.group(1);
            }
            namespace.setVariant(str2);
        }
        return builder.build();
    }
}
