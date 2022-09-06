package com.amazon.alexa;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.AbstractC0183Xff;
import com.amazon.alexa.AbstractC0238xdr;
import com.amazon.alexa.QYV;
import com.amazon.alexa.TTH;
import com.amazon.alexa.WXj;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaProfile;
import com.amazon.alexa.api.AlexaUserSpeechProviderScope;
import com.amazon.alexa.api.DialogExtras;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.cLd;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_RecognizePayload;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.fuM;
import com.amazon.alexa.kbp;
import com.google.gson.JsonObject;
import dagger.Lazy;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: SpeechRecognizerCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class LuX extends BaseCapabilityAgent implements dRG {
    public static final ComponentStateHeader BIo = ComponentStateHeader.zZm(AvsApiConstants.SpeechRecognizer.zZm, AvsApiConstants.SpeechRecognizer.ComponentStates.RecognizerState.zZm);
    public static final String zZm = "LuX";
    public final Lazy<ClientConfiguration> HvC;
    public final njf JTe;
    public final BOa LPk;
    public final peZ Mlj;
    public final vkx Qle;
    public final yqV dMe;
    public final lhN jiA;
    public final pBR lOf;
    public ScheduledFuture<?> noQ;
    public final ScheduledExecutorService uzr;
    public final vrF vkx;
    public final shl wDP;
    public final vYS yPL;
    public final AlexaClientEventBus zQM;
    public final Wyh zyO;
    public final Msx zzR;

    @Inject
    public LuX(AlexaClientEventBus alexaClientEventBus, Wyh wyh, lhN lhn, vkx vkxVar, njf njfVar, BOa bOa, vYS vys, peZ pez, Msx msx, pBR pbr, yqV yqv, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService, Lazy<ClientConfiguration> lazy, vrF vrf, gSO gso, shl shlVar) {
        super(Capability.create(AvsApiConstants.SpeechRecognizer.BIo, "2.1"));
        this.zQM = alexaClientEventBus;
        this.zyO = wyh;
        this.jiA = lhn;
        this.Qle = vkxVar;
        this.JTe = njfVar;
        this.LPk = bOa;
        this.yPL = vys;
        this.Mlj = pez;
        this.zzR = msx;
        this.lOf = pbr;
        this.uzr = scheduledExecutorService;
        this.dMe = yqv;
        this.HvC = lazy;
        this.vkx = vrf;
        this.wDP = shlVar;
        alexaClientEventBus.zZm(this);
    }

    public final void BIo() {
        synchronized (this.uzr) {
            if (this.noQ != null) {
                this.noQ.cancel(true);
                this.noQ = null;
            }
        }
    }

    @Override // com.amazon.alexa.dRG
    public ComponentState getState() {
        return ComponentState.create(BIo, Bfv.zZm);
    }

    @Subscribe
    public void on(AbstractC0238xdr abstractC0238xdr) {
        int ordinal = ((TWY) abstractC0238xdr).BIo.ordinal();
        if (ordinal == 0) {
            zZm(NTw.CANCEL_USER_INTERACTION);
        } else if (ordinal == 1) {
            zZm(NTw.PAUSE_CONTROL);
        } else if (ordinal == 2) {
            bij bijVar = bij.CANNOT_EXPECT_SPEECH;
            DialogRequestIdentifier LPk = this.zyO.BIo().LPk();
            if (DialogRequestIdentifier.NONE != LPk && LPk != null) {
                this.zQM.zyO(kbp.zQM.zZm(LPk, bijVar));
            }
        } else {
            throw new IllegalArgumentException("Please update this method if LocalStopEvent is updated.");
        }
        this.zyO.zZm();
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onCancel(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        DialogRequestIdentifier dialogRequestIdentifier;
        if (!AvsApiConstants.SpeechRecognizer.Directives.ExpectSpeech.zZm.equals(message.getHeader().getName()) || (dialogRequestIdentifier = message.getDialogRequestIdentifier()) == null || DialogRequestIdentifier.NONE.equals(dialogRequestIdentifier)) {
            return;
        }
        NEe zZm2 = this.zyO.zZm(dialogRequestIdentifier);
        if (zZm2 != null) {
            zZm2.BIo();
        } else {
            Log.w(zZm, "Could not find dialog to cancel expected speech. This likely means the dialog has already been cleaned up.");
        }
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onPreprocess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        DialogRequestIdentifier dialogRequestIdentifier;
        if (!AvsApiConstants.SpeechRecognizer.Directives.ExpectSpeech.zZm.equals(message.getHeader().getName()) || (dialogRequestIdentifier = message.getDialogRequestIdentifier()) == null || DialogRequestIdentifier.NONE.equals(dialogRequestIdentifier)) {
            return;
        }
        NEe zZm2 = this.zyO.zZm(dialogRequestIdentifier);
        if (zZm2 == null) {
            String str = zZm;
            Log.w(str, "Failed to find dialog for " + dialogRequestIdentifier);
            return;
        }
        zZm2.uuO();
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        boolean z;
        Name name = message.getHeader().getName();
        StringBuilder zZm2 = C0179Pya.zZm("onProcess ");
        zZm2.append(name.getValue());
        zZm2.toString();
        if (AvsApiConstants.SpeechRecognizer.Directives.ExpectSpeech.zZm.equals(name)) {
            Payload payload = message.getPayload();
            if (payload instanceof nNv) {
                Bhr bhr = (Bhr) payload;
                JsonObject jsonObject = bhr.BIo;
                long j = bhr.zZm;
                DialogRequestIdentifier dialogRequestIdentifier = message.getDialogRequestIdentifier();
                if (this.zyO.zZm(esV.EXPECT_SPEECH)) {
                    this.Qle.zZm(wSq.PREPARING_TO_LISTEN);
                    UpW upW = new UpW(this.zQM, this.Qle);
                    this.zQM.zyO(mZe.zZm(aVo.DIALOG, upW, this.dMe.zZm(), dialogRequestIdentifier));
                    zZm(j, upW);
                    XWx zQM = this.zyO.zQM();
                    if (zQM != null && jsonObject != null) {
                        this.JTe.BIo.put(zQM, jsonObject);
                    }
                } else {
                    Log.e(zZm, "Unable to trigger a new speech request");
                    this.zQM.zyO(new TWY(AbstractC0238xdr.zZm.CANNOT_EXPECT_SPEECH));
                    this.zQM.zyO(TTH.zZm("Unable to trigger a new speech request", TTH.zZm.INTERNAL_ERROR));
                }
            } else {
                messageProcessingCallbacks.onError();
                return;
            }
        } else if (AvsApiConstants.SpeechRecognizer.Directives.StopCapture.zZm.equals(name)) {
            DialogRequestIdentifier dialogRequestIdentifier2 = message.getDialogRequestIdentifier();
            C0179Pya.zZm("handleStopCapture: ", (Object) dialogRequestIdentifier2);
            if (dialogRequestIdentifier2 != DialogRequestIdentifier.NONE) {
                NEe zZm3 = this.zyO.zZm(dialogRequestIdentifier2);
                if (zZm3 != null) {
                    OGm yPL = zZm3.yPL();
                    if (yPL != null) {
                        yPL.zZm(fuM.zyO.STOP_CAPTURE);
                        this.zQM.zyO(EOM.zZm(yPL.Qle(), YOj.THINKING));
                    } else {
                        String str = zZm;
                        Log.i(str, "Couldn't find a dialog turn within " + dialogRequestIdentifier2);
                    }
                } else {
                    String str2 = zZm;
                    Log.i(str2, "Couldn't find a dialog with " + dialogRequestIdentifier2);
                }
            }
        } else if (AvsApiConstants.SpeechRecognizer.Directives.SetEndOfSpeechOffset.zZm.equals(name)) {
            if (!message.hasDialogRequestIdentifier()) {
                messageProcessingCallbacks.onError();
                z = false;
            } else {
                z = true;
            }
            if (z && (message.getPayload() instanceof NYz)) {
                this.zQM.zyO(new Sdw(((vOm) message.getPayload()).zZm));
            } else {
                String str3 = zZm;
                Log.e(str3, "Invalid Message: " + message);
            }
        } else {
            messageProcessingCallbacks.onError();
            return;
        }
        messageProcessingCallbacks.onFinished();
    }

    public void zQM() {
        this.LPk.zyO();
        this.zQM.BIo(this);
    }

    @Override // com.amazon.alexa.dRG
    public ComponentStateHeader zZm() {
        return BIo;
    }

    public final void zZm(NTw nTw) {
        XWx zQM = this.zyO.zQM();
        if (XWx.zZm == zQM || zQM == null) {
            return;
        }
        this.zQM.zyO(new sbP(zQM, nTw));
    }

    public final void zZm(NEe nEe, mMl mml) {
        this.Qle.BIo(wSq.PREPARING_TO_LISTEN);
        XWx Mlj = nEe.Mlj();
        if (XWx.zZm != Mlj) {
            this.zQM.zyO(kbp.zZm.zZm(Mlj, mml));
        }
        this.zyO.zZm(nEe);
    }

    public final void zZm(QYV.Qle qle) {
        boolean z;
        JsonObject remove;
        esV JTe;
        NEe nEe = ((wmF) qle).BIo;
        AlexaAudioMetadata zyO = nEe.zyO();
        AlexaDialogExtras jiA = nEe.jiA();
        boolean z2 = jiA.isUserVoiceVerified() && this.Mlj.zZm().preferDisplayOverLockscreenWithVerifiedVoice();
        if (!this.zzR.zZm() || z2 || !BOa.zZm(zyO)) {
            z = false;
        } else {
            OGm yPL = nEe.yPL();
            if (yPL == null) {
                Log.w(zZm, "The current dialog turn was null when trying to discard on lockscreen.");
                JTe = esV.UNKNOWN;
            } else {
                JTe = yPL.JTe();
            }
            esV esv = JTe;
            AlexaDialogExtras zZm2 = zZm(esv, jiA);
            this.yPL.zZm(esv, zZm2);
            zZm(nEe.BIo, esv, false, null, zZm2);
            this.LPk.zQM();
            zZm(nEe, mMl.SCREEN_LOCKED);
            z = true;
        }
        if (z) {
            return;
        }
        AlexaAudioMetadata zyO2 = nEe.zyO();
        if (nEe.Tbw()) {
            this.zyO.zQM(nEe);
        } else if (!nEe.XWf()) {
            Log.w(zZm, "multi turn dialog is neither ready to start or started. abandoning");
            zZm(nEe, mMl.OUT_OF_TURN_DIALOG_NOT_STARTED);
            return;
        }
        if (!this.zyO.BIo(nEe)) {
            Log.w(zZm, "not the current dialog. abandoning");
            zZm(nEe, mMl.OUT_OF_TURN_DIALOG_NOT_CURRENT);
            return;
        }
        OGm yPL2 = nEe.yPL();
        esV JTe2 = yPL2.JTe();
        AlexaDialogExtras jiA2 = nEe.jiA();
        AlexaDialogExtras zZm3 = zZm(JTe2, jiA2);
        this.yPL.zZm(JTe2, zZm3);
        if (nEe.HvC()) {
            remove = this.JTe.zZm(zyO2);
        } else {
            remove = this.JTe.BIo.remove(nEe.Mlj());
        }
        JsonObject jsonObject = remove;
        nEe.zOR();
        boolean z3 = (zyO2 == null || zyO2.getAlexaWakeword() == null) ? false : true;
        DialogRequestIdentifier LPk = nEe.LPk();
        ExtendedClient extendedClient = nEe.BIo;
        zZm(extendedClient, JTe2, z3, LPk, zZm3);
        BIo();
        this.zQM.zyO(mZe.zZm(aVo.DIALOG, new LUo(this.zQM, this.dMe, this.Qle, nEe.yPL(), LPk), this.dMe.zZm(), LPk));
        this.zQM.zyO(EOM.zZm(nEe.Mlj(), YOj.LISTENING));
        this.Qle.zZm(wSq.LISTENING);
        this.Qle.BIo(wSq.PREPARING_TO_LISTEN);
        this.Qle.zZm(wSq.THINKING);
        Header build = Header.builder().setDialogRequestIdentifier(LPk).setName(AvsApiConstants.SpeechRecognizer.Events.Recognize.zZm).setNamespace(AvsApiConstants.SpeechRecognizer.zZm).build();
        cIy Qle = nEe.Qle();
        shl shlVar = this.wDP;
        ZVp dataFormat = shlVar.zZm(shlVar.zQM(Qle)).getDataFormat();
        AbstractC0183Xff.zZm zZm4 = AbstractC0183Xff.zZm();
        AlexaProfile alexaProfile = zyO2.getAlexaProfile();
        String alexaProfileOverride = this.HvC.mo358get().getAlexaProfileOverride();
        if (TextUtils.isEmpty(alexaProfileOverride)) {
            alexaProfileOverride = alexaProfile.name();
        }
        cLd.zZm zzm = (cLd.zZm) zZm4;
        if (alexaProfileOverride != null) {
            zzm.zZm = alexaProfileOverride;
            String zZm5 = dataFormat.zZm();
            if (zZm5 != null) {
                zzm.BIo = zZm5;
                zzm.zQM = jsonObject;
                String str = "";
                if (zzm.zZm == null) {
                    str = C0179Pya.zZm(str, " profile");
                }
                if (zzm.BIo == null) {
                    str = C0179Pya.zZm(str, " format");
                }
                if (str.isEmpty()) {
                    Message create = Message.create(build, new AutoValue_RecognizePayload(zzm.zZm, zzm.BIo, zzm.zQM));
                    Ado ado = new Ado(this.zQM, this.vkx, jiA2.getInvocationType(), LPk, yPL2, dataFormat, qle.zZm);
                    Jip jip = new Jip(this.zQM, this.zyO, nEe, nEe.yPL(), ado);
                    String str2 = "Sending recognize with callback: " + jip;
                    AlexaUserSpeechProviderScope alexaUserSpeechProviderScope = ((NND) nEe.lOf()).zQM;
                    pBR pbr = this.lOf;
                    pbr.zQM = extendedClient;
                    pbr.zyO = alexaUserSpeechProviderScope;
                    Set<ComponentState> zZm6 = this.jiA.zZm(true);
                    this.lOf.zQM = null;
                    WXj.zZm zzm2 = (WXj.zZm) JjI.BIo();
                    zzm2.zZm = extendedClient;
                    zzm2.zQM = Qle;
                    zzm2.zyO = ado;
                    WXj.zZm zzm3 = (WXj.zZm) zzm2.zZm(create).zZm(jip);
                    zzm3.jiA = zZm6;
                    if (nEe.uzr()) {
                        zzm3.Qle = nEe.JTe();
                    }
                    if (nEe.HvC()) {
                        zzm3.yPL = ojb.zZm(this.HvC.mo358get(), true);
                    }
                    this.zQM.zyO(zzm3.zZm());
                    return;
                }
                throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
            }
            throw new NullPointerException("Null format");
        }
        throw new NullPointerException("Null profile");
    }

    @Subscribe
    public void on(QYV.JTe jTe) {
        NEe nEe = ((xWg) jTe).BIo;
        if (!XUy.zZm(nEe.zyO())) {
            this.zQM.zyO(TTH.zZm("Turns must use a nonnull AlexaProfile.", TTH.zZm.INTERNAL_ERROR));
            zZm(nEe, mMl.INVALID_AUDIO_METADATA);
            return;
        }
        this.LPk.BIo(nEe);
    }

    @Subscribe
    public void on(QYV.Qle qle) {
        this.uzr.execute(new TPm(this, qle));
    }

    public final void zZm(long j, UpW upW) {
        synchronized (this.uzr) {
            BIo();
            this.noQ = this.uzr.schedule(new CIS(this, upW), j, TimeUnit.MILLISECONDS);
        }
    }

    public final void zZm(ExtendedClient extendedClient, esV esv, boolean z, @Nullable DialogRequestIdentifier dialogRequestIdentifier, AlexaDialogExtras alexaDialogExtras) {
        if (dialogRequestIdentifier != null) {
            this.zQM.zyO(new Don(extendedClient, z ? fuM.zZm.WAKEWORD : fuM.zZm.BUTTON_PRESS, dialogRequestIdentifier, alexaDialogExtras));
        }
    }

    public final AlexaDialogExtras zZm(esV esv, AlexaDialogExtras alexaDialogExtras) {
        AlexaDialogExtras.Builder builder = DialogExtras.getBuilder(alexaDialogExtras);
        if (alexaDialogExtras.getInvocationType() == null) {
            int ordinal = esv.ordinal();
            if (ordinal == 2) {
                builder.setInvocationType(AlexaMetricsConstants.EventConstants.INVOCATION_TYPE_NOTIFICATION);
            } else if (ordinal == 3) {
                builder.setInvocationType(AlexaMetricsConstants.EventConstants.INVOCATION_TYPE_WAKE_WORD);
            } else if (ordinal == 4) {
                builder.setInvocationType(AlexaMetricsConstants.EventConstants.INVOCATION_TYPE_EXPECT_SPEECH);
            }
        }
        return builder.build();
    }
}
