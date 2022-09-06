package com.amazon.alexa;

import android.net.Network;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.AbstractC0173MQv;
import com.amazon.alexa.NEv;
import com.amazon.alexa.TtM;
import com.amazon.alexa.WnL;
import com.amazon.alexa.XFF;
import com.amazon.alexa.ZAZ;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.alexaservice.networking.ComposedMessage;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.fuM;
import com.amazon.alexa.gmj;
import com.amazon.alexa.kbp;
import com.amazon.alexa.pZY;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.vHh;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import dagger.Lazy;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: RequestComposer.java */
/* loaded from: classes.dex */
public class aew {
    public static final MediaType BIo = MediaType.parse("application/json");
    public static final String zZm = "aew";
    public final CrashReporter HvC;
    public final qxC JTe;
    public final tol LPk;
    public final shl Mlj;
    public final Lazy<xUA> Qle;
    public final DYu dMe;
    public final OkHttpClient jiA;
    public final ExecutorService lOf;
    public volatile boolean noQ;
    public final ZPU uzr;
    public final WnL vkx;
    public volatile boolean wDP;
    public final Gson yPL;
    public final AlexaClientEventBus zQM;
    public final Lazy<ClientConfiguration> zyO;
    public final lSb zzR;

    /* compiled from: RequestComposer.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class BIo extends Exception {
        public BIo(String str) {
            super(str);
        }
    }

    /* compiled from: RequestComposer.java */
    /* loaded from: classes.dex */
    private class jiA implements Runnable {
        public /* synthetic */ jiA(Qfq qfq) {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator<mDr> it2 = ((VBC) aew.this.uzr).zZm().iterator();
            while (it2.hasNext()) {
                C0195dTB c0195dTB = (C0195dTB) it2.next();
                if (!aew.this.dMe.zZm(c0195dTB.zZm)) {
                    C0179Pya.zZm(C0179Pya.zZm("Queuing persisted alexa event: "), c0195dTB.zZm, aew.zZm);
                    aew.this.zZm(c0195dTB.zZm, c0195dTB.BIo);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RequestComposer.java */
    /* loaded from: classes.dex */
    public static class zQM extends zZm {
        public zQM(@Nullable LFH lfh, TtM ttM, RrI rrI, DYu dYu, AlexaClientEventBus alexaClientEventBus) {
            super(lfh, ttM, rrI, dYu, false, alexaClientEventBus);
        }

        @Override // com.amazon.alexa.aew.zZm
        public void zZm() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RequestComposer.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class zyO implements Runnable {
        public final aew BIo;
        public final RrI zQM;
        public final JjI zZm;

        @VisibleForTesting
        public zyO(JjI jjI, aew aewVar, RrI rrI) {
            this.zZm = jjI;
            this.BIo = aewVar;
            this.zQM = rrI;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.BIo.zZm(this.zQM, this.zZm, TtM.zZm.TIMEOUT, bij.AVS_CONNECTION_TIMEOUT, null, null);
        }
    }

    public aew(Lazy<ClientConfiguration> lazy, dAN dan, Gson gson, AlexaClientEventBus alexaClientEventBus, shl shlVar, qxC qxc, Lazy<xUA> lazy2, DYu dYu, lSb lsb, tol tolVar, CrashReporter crashReporter, WnL wnL, ZPU zpu) {
        ExecutorService newSingleThreadExecutor = ExecutorFactory.newSingleThreadExecutor("request-composer");
        this.zyO = lazy;
        this.jiA = dan.BIo();
        this.yPL = gson;
        this.zQM = alexaClientEventBus;
        this.Mlj = shlVar;
        this.JTe = qxc;
        this.Qle = lazy2;
        this.LPk = tolVar;
        this.dMe = dYu;
        this.zzR = lsb;
        this.lOf = newSingleThreadExecutor;
        this.HvC = crashReporter;
        this.vkx = wnL;
        this.uzr = zpu;
        alexaClientEventBus.zZm(this);
    }

    public void BIo() throws IOException {
    }

    @Subscribe
    public void on(MyZ myZ) {
        this.lOf.submit(new jiA(null));
    }

    public boolean zQM(JjI jjI) {
        if (this.noQ) {
            WXj wXj = (WXj) jjI;
            if (AvsApiConstants.System.zZm.equals(wXj.zQM.getHeader().getNamespace()) && AvsApiConstants.System.Events.SynchronizeState.zZm.equals(wXj.zQM.getHeader().getName())) {
                return false;
            }
        }
        if (AvsApiConstants.Settings.zZm.equals(((WXj) jjI).zQM.getHeader().getNamespace())) {
            return false;
        }
        return !this.wDP;
    }

    public void zZm(JjI jjI) throws IOException {
    }

    public void zyO() {
        this.zQM.BIo(this);
        this.lOf.shutdown();
        ((VBC) this.uzr).BIo();
    }

    @Subscribe
    public void on(fEt fet) {
        mDr zZm2;
        YAN yan = (YAN) fet;
        if (DialogRequestIdentifier.NONE.equals(yan.BIo) || (zZm2 = this.zzR.zZm(yan.BIo)) == null) {
            return;
        }
        C0195dTB c0195dTB = (C0195dTB) zZm2;
        zZm(c0195dTB.zZm, c0195dTB.BIo, TtM.zZm.CANCELLED, null);
    }

    public final void zZm(RrI rrI, JjI jjI) {
        this.dMe.zZm(rrI, jjI);
        ((WXj) jjI).yPL.onRequestQueued(rrI);
        BIo(rrI, jjI);
    }

    public final void BIo(RrI rrI, JjI jjI) {
        Network BIo2 = this.JTe.BIo();
        if (this.JTe.zQM()) {
            if (!zQM(jjI)) {
                this.lOf.submit(new wLE(this, rrI, jjI));
                return;
            }
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Downchannel unavailable or event isn't SynchronizeState. Enqueuing event to send later: ");
            zZm2.append(((WXj) jjI).zQM.getHeader().getName());
            Log.i(str, zZm2.toString());
            this.zzR.zZm(jjI, rrI, new zyO(jjI, this, rrI), 10L, TimeUnit.SECONDS);
            return;
        }
        Log.i(zZm, "Tried to schedule sending a message without connectivity");
        zZm(rrI, jjI, TtM.zZm.NETWORK, bij.NETWORK_UNAVAILABLE, null, BIo2);
    }

    public static /* synthetic */ void zZm(aew aewVar, RrI rrI, JjI jjI) {
        Network BIo2 = aewVar.JTe.BIo();
        if (!aewVar.zZm()) {
            Log.i(zZm, "Tried to send a message without connectivity.");
            aewVar.zZm(rrI, jjI, TtM.zZm.NETWORK, bij.NETWORK_UNAVAILABLE, BIo2);
        } else if (!aewVar.zQM()) {
            Log.w(zZm, "Tried to send a message without being authenticated.");
            aewVar.zQM.zyO(jLK.BIo());
            aewVar.zZm(rrI, jjI, TtM.zZm.AUTHORIZATION, bij.AUTHORIZATION_ERROR, null, null);
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            try {
                aewVar.zZm(builder, jjI);
                aewVar.BIo(builder, jjI, rrI);
                aewVar.zZm(builder, jjI, rrI);
                Request build = new Request.Builder().url(aewVar.Qle.mo358get().zZm().newBuilder().addPathSegment("v20160207").addPathSegment(DefaultDeliveryClient.EVENTS_DIRECTORY).build()).tag(rrI).post(builder.build()).build();
                ((WXj) jjI).yPL.onRequestStarted(rrI);
                Response execute = aewVar.jiA.newCall(build).execute();
                aewVar.zZm(jjI);
                AlexaClientEventBus alexaClientEventBus = aewVar.zQM;
                eOP eop = ((WXj) jjI).zzR;
                boolean z = ((WXj) jjI).lOf;
                alexaClientEventBus.zyO(ZAZ.zZm(execute, ZAZ.zZm.zZm(false, z), eop, ((WXj) jjI).yPL));
            } catch (BIo e) {
                Log.e(zZm, e.getMessage(), e);
                aewVar.zZm(rrI, jjI, TtM.zZm.CANCELLED, bij.CANCELLED, null, null);
                if (!((WXj) jjI).zQM.hasDialogRequestIdentifier()) {
                    return;
                }
                aewVar.HvC.notifyHandledException(e);
            } catch (IOException e2) {
                Log.e(zZm, e2.getMessage(), e2);
                aewVar.zZm(rrI, jjI, TtM.zZm.ERROR, bij.NETWORK_REQUEST_ERROR, e2, BIo2);
                if (!((WXj) jjI).zQM.hasDialogRequestIdentifier()) {
                    return;
                }
                aewVar.HvC.notifyHandledException(e2, 0.05d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RequestComposer.java */
    /* loaded from: classes.dex */
    public static class zZm implements LFH {
        public final TtM BIo;
        public final boolean Qle;
        public final AlexaClientEventBus jiA;
        public final DYu zQM;
        public final LFH zZm;
        public final RrI zyO;

        public zZm(@Nullable LFH lfh, TtM ttM, RrI rrI, DYu dYu, boolean z, AlexaClientEventBus alexaClientEventBus) {
            this.zZm = lfh;
            this.BIo = ttM;
            this.zQM = dYu;
            this.zyO = rrI;
            this.Qle = z;
            this.jiA = alexaClientEventBus;
        }

        @Override // com.amazon.alexa.LFH
        public void BIo(int i, long j) {
            zZm();
            this.jiA.zyO(new SFM(i, this.Qle ? XFF.zZm.OPUSCOMPRESSED : XFF.zZm.GENERIC));
            LFH lfh = this.zZm;
            if (lfh != null) {
                lfh.BIo(i, j);
            }
        }

        @Override // com.amazon.alexa.LFH
        public void onError(Exception exc) {
            Log.e(aew.zZm, "Attachment write error", exc);
            if (exc instanceof gmj.zZm) {
                if (exc.getCause() != null && (exc.getCause() instanceof SocketTimeoutException)) {
                    this.jiA.zyO(new aOP(AbstractC0173MQv.zZm.SOCKET_TIMEOUT));
                }
            } else {
                this.jiA.zyO(new aOP(AbstractC0173MQv.zZm.UNKNOWN));
            }
            this.zQM.zZm(this.zyO, false, (Integer) null, exc);
            LFH lfh = this.zZm;
            if (lfh != null) {
                lfh.onError(exc);
            }
        }

        @Override // com.amazon.alexa.LFH
        public void zZm(int i, long j) {
            Log.w(aew.zZm, "Attachment write timed out");
            this.jiA.zyO(fuM.zQM.zZm(fuM.zyO.OTHER));
            zZm();
            this.jiA.zyO(new aOP(AbstractC0173MQv.zZm.CLIENT_TIMEOUT));
            LFH lfh = this.zZm;
            if (lfh != null) {
                lfh.zZm(i, j);
            }
        }

        public void zZm() {
            this.BIo.onRequestFinished(this.zyO);
        }
    }

    @Subscribe
    public void on(yHQ yhq) {
        this.noQ = ((dpf) yhq).BIo;
    }

    public boolean zQM() {
        return this.LPk.zyO();
    }

    @Subscribe(sticky = true)
    public void on(Bob bob) {
        this.wDP = bob.BIo();
        if (this.wDP) {
            this.lOf.submit(new jiA(null));
            synchronized (this.zzR) {
                if (!this.zzR.zZm()) {
                    this.zQM.zyO(new MUe());
                    while (!this.zzR.zZm()) {
                        mDr BIo2 = this.zzR.BIo();
                        BIo(((C0195dTB) BIo2).zZm, ((C0195dTB) BIo2).BIo);
                    }
                }
            }
        }
    }

    public final ojb BIo(JjI jjI) {
        ojb ojbVar = ((WXj) jjI).Mlj;
        return ojbVar == null ? ojb.zZm(this.zyO.mo358get(), false) : ojbVar;
    }

    public final MultipartBody.Builder BIo(MultipartBody.Builder builder, JjI jjI, RrI rrI) throws IOException {
        WXj wXj = (WXj) jjI;
        cIy ciy = wXj.JTe;
        if (ciy != null) {
            Aml zQM2 = this.Mlj.zQM(ciy);
            if (zQM2 != null) {
                builder.addFormDataPart(VTK.WAKEWORD_ENGINE_METADATA.zZm(), null, new gmj(BIo(jjI), new zQM(wXj.LPk, wXj.yPL, rrI, this.dMe, this.zQM), zQM2));
            } else {
                throw new IOException(C0179Pya.BIo(C0179Pya.zZm("Attachment for data "), wXj.JTe, " was null"));
            }
        }
        return builder;
    }

    @Subscribe
    public void on(NEv.jiA jia) {
        zLp zlp = (zLp) jia;
        mDr zZm2 = this.zzR.zZm(zlp.BIo);
        if (zZm2 != null) {
            this.zQM.zyO(NEv.zZm.zZm(zlp.BIo, ApiCallFailure.TimeoutFailure.create("Timeout exceeded before event was sent")));
            C0195dTB c0195dTB = (C0195dTB) zZm2;
            zZm(c0195dTB.zZm, c0195dTB.BIo, TtM.zZm.TIMEOUT, bij.INTERNAL_CLIENT_ERROR_CLIENT_TIMEOUT_EXCEEDED);
        }
    }

    public boolean BIo(Response response) {
        return response.isSuccessful();
    }

    @Subscribe
    public void on(rjK rjk) {
        if (!this.JTe.zQM()) {
            Log.i(zZm, "Tried to update capabilities without connectivity");
            ((pZY.zZm) ((MXm) rjk).zQM).zZm(vHh.zZm.zZm);
            return;
        }
        this.lOf.submit(new Qfq(this, rjk));
    }

    @Subscribe
    public void on(JjI jjI) {
        RrI zZm2;
        WXj wXj = (WXj) jjI;
        Message message = wXj.zQM;
        if (message.hasDialogRequestIdentifier()) {
            zZm2 = RrI.zZm(message.getDialogRequestIdentifier());
        } else {
            zZm2 = RrI.zZm();
        }
        if (wXj.lOf) {
            ((VBC) this.uzr).BIo(zZm2, jjI);
        }
        zZm(zZm2, jjI);
    }

    public final MultipartBody.Builder zZm(MultipartBody.Builder builder, JjI jjI) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Serializing event: ");
        WXj wXj = (WXj) jjI;
        zZm2.append(wXj.zQM.getHeader());
        Log.i(str, zZm2.toString());
        ComposedMessage.Builder builder2 = ComposedMessage.builder();
        builder2.zZm(wXj.zQM);
        Set<ComponentState> set = wXj.Qle;
        if (set != null && !set.isEmpty()) {
            builder2.zZm(wXj.Qle);
        }
        String json = this.yPL.toJson(builder2.zZm());
        GeneratedOutlineSupport1.outline158("Request JSON: ", json);
        builder.addFormDataPart(VTK.METADATA.zZm(), json);
        return builder;
    }

    public final MultipartBody.Builder zZm(MultipartBody.Builder builder, JjI jjI, RrI rrI) throws BIo {
        WXj wXj = (WXj) jjI;
        cIy ciy = wXj.zyO;
        if (ciy != null) {
            Aml zQM2 = this.Mlj.zQM(ciy);
            if (zQM2 != null) {
                builder.addFormDataPart(VTK.AUDIO.zZm(), null, new gmj(BIo(jjI), new zZm(wXj.jiA, wXj.yPL, rrI, this.dMe, zQM2.getDataFormat().Qle(), this.zQM), zQM2));
            } else {
                throw new BIo(C0179Pya.BIo(C0179Pya.zZm("Attachment for audio "), wXj.zyO, " was null"));
            }
        }
        return builder;
    }

    public static /* synthetic */ void zZm(aew aewVar, rjK rjk) {
        HttpUrl zZm2;
        if (!aewVar.zZm()) {
            Log.e(zZm, "Tried to send capabilities update without network connectivity.");
            ((pZY.zZm) ((MXm) rjk).zQM).zZm(vHh.zZm.zZm);
        } else if (!aewVar.zQM()) {
            Log.w(zZm, "Tried to send capabilities update without being authenticated.");
            aewVar.zQM.zyO(jLK.BIo());
            ((pZY.zZm) ((MXm) rjk).zQM).zZm(vHh.zZm.BIo);
        } else {
            RequestBody create = RequestBody.create(BIo, aewVar.yPL.toJson(rjk.BIo()));
            Request.Builder builder = new Request.Builder();
            xUA mo358get = aewVar.Qle.mo358get();
            if (mo358get.JTe.LPk()) {
                zZm2 = mo358get.zZm(mo358get.JTe.BIo(), xUA.zQM);
            } else {
                zZm2 = mo358get.zZm(mo358get.jiA.mo358get().getCapabilitiesEndpoint(), xUA.zQM);
            }
            HttpUrl.Builder newBuilder = zZm2.newBuilder();
            for (String str : xUA.BIo) {
                newBuilder.addPathSegment(str);
            }
            Request build = builder.url(newBuilder.build()).put(create).build();
            Network BIo2 = aewVar.JTe.BIo();
            try {
                Response execute = aewVar.jiA.newCall(build).execute();
                int zZm3 = aewVar.zZm(execute);
                boolean BIo3 = aewVar.BIo(execute);
                aewVar.BIo();
                if (BIo3) {
                    ((pZY.zZm) ((MXm) rjk).zQM).zZm();
                } else {
                    ((pZY.zZm) ((MXm) rjk).zQM).zZm(new vHh.zZm(vHh.zZm.EnumC0036zZm.AVS_REQUEST_FAILED, Integer.valueOf(zZm3), null));
                }
                String str2 = zZm;
                StringBuilder sb = new StringBuilder();
                sb.append("Response from Capabilities publish request: ");
                sb.append(zZm3);
                sb.append(" ");
                sb.append(execute.body().string());
                Log.i(str2, sb.toString());
            } catch (IOException e) {
                Log.e(zZm, e.getMessage(), e);
                ((pZY.zZm) ((MXm) rjk).zQM).zZm(new vHh.zZm(vHh.zZm.EnumC0036zZm.IO_EXCEPTION, null, e));
                aewVar.JTe.zZm(false, DialogRequestIdentifier.NONE, BIo2);
                aewVar.HvC.notifyHandledException(e, 0.015d);
            }
        }
    }

    public boolean zZm() {
        return this.JTe.zQM();
    }

    public int zZm(Response response) {
        return response.code();
    }

    public void zZm(bij bijVar) {
        synchronized (this.zzR) {
            while (!this.zzR.zZm()) {
                mDr BIo2 = this.zzR.BIo();
                zZm(((C0195dTB) BIo2).zZm, ((C0195dTB) BIo2).BIo, TtM.zZm.TEARDOWN, bijVar);
            }
        }
    }

    public final void zZm(RrI rrI, JjI jjI, TtM.zZm zzm, @Nullable bij bijVar) {
        zZm(rrI, jjI, zzm, bijVar, null, null);
    }

    public final void zZm(RrI rrI, JjI jjI, TtM.zZm zzm, @Nullable bij bijVar, Network network) {
        zZm(rrI, jjI, zzm, bijVar, null, network);
    }

    public final void zZm(RrI rrI, JjI jjI, TtM.zZm zzm, @Nullable bij bijVar, @Nullable Exception exc, Network network) {
        ApiCallFailure.FailureType failureType;
        ApiCallFailure create;
        WXj wXj = (WXj) jjI;
        Message message = wXj.zQM;
        Log.i(zZm, String.format("Dropping send message event id %s (%s)", ((Fkl) rrI).zZm, zzm.name()));
        C0179Pya.zZm("Dropping message: ", (Object) message);
        wXj.yPL.onRequestDropped(rrI, zzm);
        this.Mlj.BIo(wXj.zyO);
        this.Mlj.BIo(wXj.JTe);
        boolean zQM2 = this.dMe.zQM(rrI);
        if (message.hasDialogRequestIdentifier() && bijVar != null) {
            DialogRequestIdentifier dialogRequestIdentifier = message.getDialogRequestIdentifier();
            if (qxC.zZm(exc)) {
                this.zQM.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.NETWORK_UNAVAILABLE, zQM2));
            } else if (bij.AVS_CONNECTION_TIMEOUT.equals(bijVar)) {
                WnL.zQM zZm2 = this.vkx.zZm();
                int ordinal = zZm2.yPL.ordinal();
                if (ordinal == 0) {
                    this.zQM.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.AVS_CONNECTION_TIMEOUT_UNINITIALIZED, zQM2));
                    Log.wtf(zZm, "The service is uninitialized while dropping");
                } else if (ordinal != 1) {
                    switch (ordinal) {
                        case 4:
                            this.zQM.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.AVS_CONNECTION_TIMEOUT_NETWORK, zQM2));
                            break;
                        case 5:
                            this.zQM.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_ESTABLISHED, zZm("establishing downchannel", zZm2), zQM2));
                            break;
                        case 6:
                            this.zQM.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_CONNECTED, zQM2));
                            break;
                        case 7:
                            this.zQM.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.AVS_CONNECTION_TIMEOUT_CAPABILITY_PUBLISH, zZm("publishing capabilities", zZm2), zQM2));
                            break;
                        case 8:
                            this.zQM.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.AVS_CONNECTION_TIMEOUT_SYNCHRONIZE_STATE, zZm("synchronizing state", zZm2), zQM2));
                            break;
                        case 9:
                            this.zQM.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.AVS_CONNECTION_TIMEOUT_DOWNCHANNEL_AVAILABLE, zQM2));
                            break;
                        default:
                            this.zQM.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.AVS_CONNECTION_TIMEOUT, zQM2));
                            break;
                    }
                } else {
                    this.zQM.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.AVS_CONNECTION_TIMEOUT_UNAUTHORIZED, zQM2));
                }
            } else {
                AlexaClientEventBus alexaClientEventBus = this.zQM;
                bijVar.zZm(zQM2);
                alexaClientEventBus.zyO(new gMf(dialogRequestIdentifier, bijVar, null, zQM2));
            }
        }
        if ((zzm == TtM.zZm.CANCELLED || zzm == TtM.zZm.TEARDOWN) ? false : true) {
            this.JTe.zZm(message.hasDialogRequestIdentifier(), message.getDialogRequestIdentifier(), network);
        }
        eOP eop = wXj.zzR;
        if (eOP.zZm != eop) {
            if (bijVar == null) {
                failureType = ApiCallFailure.FailureType.INTERNAL;
            } else {
                int ordinal2 = bijVar.ordinal();
                if (ordinal2 != 10 && ordinal2 != 20) {
                    switch (ordinal2) {
                        default:
                            switch (ordinal2) {
                                case 24:
                                    break;
                                case 25:
                                    failureType = ApiCallFailure.FailureType.AUTHORIZATION;
                                    break;
                                case 26:
                                    failureType = ApiCallFailure.FailureType.TIMEOUT;
                                    break;
                                default:
                                    failureType = ApiCallFailure.FailureType.INTERNAL;
                                    break;
                            }
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            failureType = ApiCallFailure.FailureType.NETWORK;
                            break;
                    }
                }
                failureType = ApiCallFailure.FailureType.NETWORK;
            }
            String message2 = exc == null ? null : exc.getMessage();
            int i = Dei.BIo[failureType.ordinal()];
            if (i == 1) {
                create = ApiCallFailure.NetworkFailure.create(message2, exc);
            } else if (i == 2) {
                create = ApiCallFailure.AuthorizationFailure.create(message2);
            } else if (i != 3) {
                create = ApiCallFailure.InternalFailure.create(message2, exc);
            } else {
                create = ApiCallFailure.TimeoutFailure.create(message2);
            }
            this.zQM.zyO(NEv.zZm.zZm(eop, create));
        }
        if (!this.dMe.zZm(jjI, false, (Integer) null, exc)) {
            wXj.yPL.onFailure(rrI, null, exc);
        }
    }

    public final Map<String, String> zZm(String str, WnL.zQM zqm) {
        if (zqm.zyO()) {
            HashMap hashMap = new HashMap();
            int i = zqm.zZm().Mlj;
            String str2 = zZm;
            Log.w(str2, "AVS Connection Timeout due to HTTP status code response to " + str + RealTimeTextConstants.COLON_SPACE + i);
            hashMap.put(ClG.STATUS_CODE.name(), Integer.toString(i));
            return hashMap;
        } else if (!zqm.zQM()) {
            return null;
        } else {
            Exception exc = zqm.BIo().Mlj;
            this.HvC.notifyHandledException(new ZSB(exc), 0.05d);
            String str3 = zZm;
            Log.w(str3, "AVS Connection Timeout due to exception while " + str, exc);
            HashMap hashMap2 = new HashMap();
            hashMap2.put(ClG.EXCEPTION_MESSAGE.name(), exc.getMessage());
            hashMap2.put(ClG.EXCEPTION_TYPE.name(), exc.getClass().getSimpleName());
            return hashMap2;
        }
    }
}
