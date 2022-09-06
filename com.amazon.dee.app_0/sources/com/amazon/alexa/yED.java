package com.amazon.alexa;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.AgQ;
import com.amazon.alexa.TTH;
import com.amazon.alexa.WXj;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_ExceptionEncounteredPayload;
import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_ExceptionEncounteredPayload_Error;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.system.UserInactivityAuthority;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: SystemCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class yED extends BaseCapabilityAgent {
    public static final String zZm = "yED";
    public final AlexaClientEventBus BIo;
    public final MBE JTe;
    public final Box LPk;
    public final Lazy<ClientConfiguration> Mlj;
    public final zDj Qle;
    public final lhN jiA;
    public final ScheduledExecutorService yPL;
    public final Lazy<xUA> zQM;
    public final UserInactivityAuthority zyO;
    public final Lazy<gSO> zzR;

    /* compiled from: SystemCapabilityAgent.java */
    /* loaded from: classes.dex */
    private class BIo extends UcG {
        public /* synthetic */ BIo(UQn uQn) {
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
            yED.this.BIo.zyO(new MKA(false, num, exc));
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onSuccess(RrI rrI, Collection<Message> collection) {
        }
    }

    /* compiled from: SystemCapabilityAgent.java */
    /* loaded from: classes.dex */
    private class zZm implements Runnable {
        public /* synthetic */ zZm(UQn uQn) {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (yED.BIo(yED.this)) {
                yED.this.zQM();
            }
        }
    }

    @Inject
    public yED(AlexaClientEventBus alexaClientEventBus, Lazy<xUA> lazy, UserInactivityAuthority userInactivityAuthority, lhN lhn, zDj zdj, Box box, MBE mbe, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService, Lazy<ClientConfiguration> lazy2, Lazy<gSO> lazy3) {
        super(new Capability[0]);
        this.zQM = lazy;
        this.zyO = userInactivityAuthority;
        this.jiA = lhn;
        this.Qle = zdj;
        this.LPk = box;
        this.JTe = mbe;
        this.yPL = scheduledExecutorService;
        this.Mlj = lazy2;
        this.zzR = lazy3;
        this.BIo = alexaClientEventBus;
        alexaClientEventBus.zZm(this);
    }

    public final Message BIo() {
        return Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.SoftwareInfo.zZm).build(), wOp.zZm(zZm("2.4.1609.0")));
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent, com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent
    public Set<Capability> getCapabilities() {
        return Collections.singleton(Capability.create(AvsApiConstants.System.BIo, this.zzR.mo358get().zZm(Feature.ALEXA_VOX_ANDROID_DLS) ? "1.4" : "1.0"));
    }

    @Subscribe
    public void on(Voz voz) {
        zZm(new BIo(null));
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onCancel(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onPreprocess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        Ufm ufm;
        Name name = message.getHeader().getName();
        if (AvsApiConstants.System.Directives.SetEndpoint.zZm.equals(name)) {
            Payload payload = message.getPayload();
            if (!Boolean.FALSE.equals(this.Mlj.mo358get().getSupportsPfmChanged())) {
                this.zQM.mo358get().zZm(((IGY) payload).zZm);
            }
        } else if (AvsApiConstants.System.Directives.ResetUserInactivity.zZm.equals(name)) {
            this.zyO.zQM();
        } else if (AvsApiConstants.System.Directives.RevokeAuthorization.zZm.equals(name)) {
            String unparsedMessage = message.getMessageMetadata().getUnparsedMessage();
            Log.e(zZm, "Received an unsupported RevokeAuthorization directive");
            zZm(TTH.zZm.UNEXPECTED_INFORMATION_RECEIVED, "This directive is not supported", unparsedMessage);
        } else if (AvsApiConstants.System.Directives.ReportSoftwareInfo.zZm.equals(name)) {
            zQM();
        } else if (AvsApiConstants.System.Directives.SetTimeZone.zZm.equals(name)) {
            this.Qle.BIo();
        } else if (AvsApiConstants.System.Directives.SetLocales.zZm.equals(name)) {
            this.JTe.Mlj();
        } else if (AvsApiConstants.System.Directives.ReportState.zZm.equals(name)) {
            this.BIo.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.StateReport.zZm).build(), rff.zZm(zZm()))).zZm());
        } else if (AvsApiConstants.System.Exception.zZm.equals(name)) {
            Log.e(zZm, message.getPayload().toString());
            Payload payload2 = message.getPayload();
            AlexaClientEventBus alexaClientEventBus = this.BIo;
            ZGM zgm = (ZGM) payload2;
            String str = zgm.zZm;
            Ufm[] values = Ufm.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    ufm = values[i];
                    if (ufm.toString().equalsIgnoreCase(str)) {
                        break;
                    }
                    i++;
                } else {
                    ufm = Ufm.UNDEFINED;
                    break;
                }
            }
            alexaClientEventBus.zyO(new xyS(ufm, zgm.BIo));
        } else {
            messageProcessingCallbacks.onError();
            return;
        }
        messageProcessingCallbacks.onFinished();
    }

    @Subscribe
    public void on(TTH tth) {
        nAN nan = (nAN) tth;
        if (!nan.jiA) {
            String str = nan.zQM;
            if (str == null) {
                str = "";
            }
            zZm(nan.BIo, str, nan.zyO);
        }
    }

    public final void zQM() {
        this.BIo.zyO(JjI.BIo().zZm(BIo()).zZm());
    }

    public void zZm(TtM ttM) {
        WXj.zZm zzm = (WXj.zZm) JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.SynchronizeState.zZm).build()));
        zzm.jiA = this.jiA.zZm();
        zzm.zZm(ttM);
        this.BIo.zyO(zzm.zZm());
    }

    public static /* synthetic */ boolean BIo(yED yed) {
        if (!TextUtils.equals(yed.LPk.JTe(), "2.4.1609.0")) {
            yed.LPk.zZm("2.4.1609.0");
            return true;
        }
        return false;
    }

    @Subscribe
    public void on(MyZ myZ) {
        this.yPL.execute(new zZm(null));
    }

    public final void zZm(TTH.zZm zzm, String str, String str2) {
        AgQ.zZm zzm2 = (AgQ.zZm) LWv.zZm();
        zzm2.zZm = new AutoValue_ExceptionEncounteredPayload_Error(zzm, str);
        zzm2.BIo = str2;
        String str3 = "";
        if (zzm2.zZm == null) {
            str3 = C0179Pya.zZm(str3, " error");
        }
        if (str3.isEmpty()) {
            Message create = Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.ExceptionEncountered.zZm).build(), new AutoValue_ExceptionEncounteredPayload(zzm2.zZm, zzm2.BIo));
            AlexaClientEventBus alexaClientEventBus = this.BIo;
            WXj.zZm zzm3 = (WXj.zZm) JjI.BIo();
            zzm3.jiA = this.jiA.zZm();
            alexaClientEventBus.zyO(zzm3.zZm(create).zZm());
            return;
        }
        throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str3));
    }

    @VisibleForTesting
    public List<Message> zZm() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.Qle.zZm());
        arrayList.add(this.JTe.jiA());
        arrayList.add(this.zyO.BIo());
        arrayList.add(BIo());
        return arrayList;
    }

    @VisibleForTesting
    public int zZm(String str) throws NumberFormatException {
        try {
            return Integer.valueOf(str.replaceAll(ArcusConfig.PATH_SEPARATOR, "")).intValue();
        } catch (NumberFormatException unused) {
            GeneratedOutlineSupport1.outline162("Invalid characters in version name: ", str, zZm);
            return 1;
        }
    }
}
