package com.amazon.alexa;

import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.api.AlexaCardExtras;
import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.api.AlexaCardRendererListenerProxy;
import com.amazon.alexa.api.AlexaPlayerInfoCardListenerProxy;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.qgo;
import com.amazon.alexa.uTP;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: CardRendererCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class yYy extends BaseCapabilityAgent {
    public static final long BIo = TimeUnit.MILLISECONDS.convert(2, TimeUnit.HOURS);
    public static final String zZm = "yYy";
    public final AlexaClientEventBus JTe;
    public final Gson LPk;
    public final IWd<xNT, String> Mlj;
    public final uTP Qle;
    public final zZm jiA;
    public final AtomicReference<xNT> yPL;
    public final CDz<AlexaPlayerInfoCardListenerProxy> zQM;
    public final Shr<GvA> zyO;
    public final Provider<AlexaCardExtras> zzR;

    @Inject
    public yYy(AlexaClientEventBus alexaClientEventBus, Gson gson, UCV ucv, Provider<AlexaCardExtras> provider) {
        super(Capability.create(AvsApiConstants.CardRenderer.BIo, "0.1"));
        this.zyO = new Shr<>();
        this.zQM = new CDz<>();
        this.JTe = alexaClientEventBus;
        this.LPk = gson;
        this.zzR = provider;
        this.Qle = ucv.zZm("card_renderer", BIo);
        this.jiA = new zZm(null);
        this.Mlj = new IWd<>();
        this.yPL = new AtomicReference<>(xNT.zZm);
        this.Qle.zZm((uTP.zZm) this.jiA);
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public synchronized void on(xZV xzv) {
        this.zyO.BIo(((uyC) xzv).BIo);
        this.zQM.BIo(((uyC) xzv).BIo);
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public synchronized void onCancel(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public synchronized void onPreprocess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        String str;
        Name name = message.getHeader().getName();
        if (AvsApiConstants.CardRenderer.Directives.RenderCard.zZm.equals(name)) {
            String value = ((RawStringPayload) message.getPayload()).getValue();
            DialogRequestIdentifier dialogRequestIdentifier = message.getDialogRequestIdentifier();
            boolean z = false;
            AlexaCardExtras mo10268get = this.zzR.mo10268get();
            Iterator<GvA> it2 = this.zyO.iterator();
            while (it2.hasNext()) {
                GvA next = it2.next();
                try {
                    AlexaCardRendererListenerProxy alexaCardRendererListenerProxy = next.BIo;
                    if (alexaCardRendererListenerProxy != null) {
                        alexaCardRendererListenerProxy.onReceivedRenderCard(value);
                    } else {
                        AlexaCardListener alexaCardListener = next.zQM;
                        if (alexaCardListener != null) {
                            alexaCardListener.onReceivedRenderCard(value, mo10268get);
                        } else {
                            Log.e(GvA.zZm, "Unable to send render card - listener is null");
                        }
                    }
                    z = true;
                } catch (RemoteException e) {
                    Log.e(zZm, e.getMessage(), e);
                    this.JTe.zyO(xZV.zZm(this.zyO.BIo((Shr<GvA>) next)));
                }
            }
            if (z) {
                AlexaClientEventBus alexaClientEventBus = this.JTe;
                try {
                    str = new JSONObject(value).getString("cardType");
                } catch (JSONException unused) {
                    String str2 = zZm;
                    Log.e(str2, "Unable to parse JSON: " + value);
                    str = null;
                }
                alexaClientEventBus.zyO(new wFY(dialogRequestIdentifier, str));
            }
            messageProcessingCallbacks.onFinished();
        } else if (AvsApiConstants.CardRenderer.Directives.PlayerInfo.zZm.equals(name)) {
            String value2 = ((RawStringPayload) message.getPayload()).getValue();
            boolean hasDialogRequestIdentifier = message.hasDialogRequestIdentifier();
            this.Qle.BIo();
            Iterator<T> it3 = this.zQM.iterator();
            while (it3.hasNext()) {
                AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy = (AlexaPlayerInfoCardListenerProxy) it3.next();
                try {
                    alexaPlayerInfoCardListenerProxy.onReceivedPlayerInfoCard(value2, hasDialogRequestIdentifier);
                } catch (RemoteException e2) {
                    Log.e(zZm, e2.getMessage(), e2);
                    this.JTe.zyO(xZV.zZm(this.zQM.BIo((CDz<AlexaPlayerInfoCardListenerProxy>) alexaPlayerInfoCardListenerProxy)));
                }
            }
            zZm(value2);
            this.Qle.zZm((uTP.BIo) this.jiA);
            messageProcessingCallbacks.onFinished();
        } else {
            messageProcessingCallbacks.onError();
        }
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public synchronized void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
    }

    public synchronized void zZm() {
        this.Mlj.zZm();
        this.Qle.zZm();
        this.yPL.set(xNT.zZm);
    }

    /* compiled from: CardRendererCapabilityAgent.java */
    /* loaded from: classes.dex */
    private class zZm implements uTP.zZm, uTP.BIo {
        public /* synthetic */ zZm(msg msgVar) {
        }

        @Override // com.amazon.alexa.uTP.zZm
        public void zZm(PersistentStorage persistentStorage, Gson gson) {
            synchronized (yYy.this) {
                String string = persistentStorage.getString("lastPlayerInfoCard", "");
                if (!TextUtils.isEmpty(string)) {
                    yYy.this.zZm(string);
                }
            }
        }

        @Override // com.amazon.alexa.uTP.BIo
        public void zZm(PersistentStorage.Transaction transaction, Gson gson) {
            synchronized (yYy.this) {
                transaction.set("lastPlayerInfoCard", (String) yYy.this.Mlj.BIo(yYy.this.yPL.get()));
            }
        }
    }

    public synchronized void zZm(ExtendedClient extendedClient, GvA gvA) {
        this.zyO.zZm(extendedClient, gvA);
    }

    @Subscribe
    public synchronized void on(CKO cko) {
        this.yPL.set(((IyB) cko).zQM);
        this.Qle.zZm((uTP.BIo) this.jiA);
        xNT xnt = ((IyB) cko).zQM;
        if (xnt != null && !xnt.equals(xNT.BIo)) {
            Iterator<T> it2 = this.zQM.iterator();
            while (it2.hasNext()) {
                AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy = (AlexaPlayerInfoCardListenerProxy) it2.next();
                try {
                    alexaPlayerInfoCardListenerProxy.onAudioItemStateChanged(((IyB) cko).BIo, ((IyB) cko).zQM.getValue(), ((IyB) cko).zyO);
                } catch (RemoteException e) {
                    Log.e(zZm, e.getMessage(), e);
                    this.JTe.zyO(xZV.zZm(this.zQM.BIo((CDz<AlexaPlayerInfoCardListenerProxy>) alexaPlayerInfoCardListenerProxy)));
                }
            }
        }
    }

    public synchronized void zZm(GvA gvA) {
        this.zyO.remove(gvA);
    }

    public synchronized void zZm(ExtendedClient extendedClient, AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy) {
        String value;
        this.Qle.BIo();
        this.zQM.zZm(extendedClient, (ExtendedClient) alexaPlayerInfoCardListenerProxy);
        xNT xnt = this.yPL.get();
        if (!xNT.zZm.equals(xnt)) {
            value = this.Mlj.BIo(xnt);
        } else {
            Map.Entry<xNT, String> BIo2 = this.Mlj.BIo();
            value = BIo2 != null ? BIo2.getValue() : "";
        }
        if (!TextUtils.isEmpty(value)) {
            try {
                alexaPlayerInfoCardListenerProxy.onReceivedPlayerInfoCard(value, false);
            } catch (RemoteException e) {
                Log.e(zZm, e.getMessage(), e);
                this.JTe.zyO(xZV.zZm(this.zQM.BIo((CDz<AlexaPlayerInfoCardListenerProxy>) alexaPlayerInfoCardListenerProxy)));
            }
        }
        this.JTe.zyO(new oAY());
    }

    public synchronized void zZm(AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy) {
        this.zQM.remove(alexaPlayerInfoCardListenerProxy);
    }

    public final void zZm(String str) {
        try {
            qgo qgoVar = (qgo) this.LPk.fromJson(str, (Class<Object>) qgo.class);
            this.Mlj.zZm(((zXp) qgoVar).zZm, str);
            zZm(qgoVar);
        } catch (JsonSyntaxException e) {
            String str2 = zZm;
            Log.e(str2, "PlayerInfo was incorrectly formatted: " + str, e);
        }
    }

    @Subscribe
    public synchronized void on(AgS agS) {
        if (((UuG) agS).BIo) {
            this.Qle.zZm();
            this.yPL.set(xNT.zZm);
        }
    }

    public final void zZm(qgo qgoVar) {
        String str;
        String str2;
        String str3;
        Uri uri;
        Uri uri2;
        Long l;
        zXp zxp = (zXp) qgoVar;
        xNT xnt = zxp.zZm;
        if (xnt != null) {
            qgo.zZm zzm = zxp.BIo;
            if (zzm != null) {
                Knu knu = (Knu) zzm;
                str = knu.zZm;
                str3 = knu.zQM;
                str2 = knu.BIo;
            } else {
                str = null;
                str2 = null;
                str3 = null;
            }
            qgo.zQM zqm = zxp.zQM;
            if (zqm != null) {
                srS srs = (srS) zqm;
                uri = srs.zZm;
                lsL lsl = srs.BIo;
                uri2 = lsl != null ? ((puf) lsl).zZm : null;
            } else {
                uri = null;
                uri2 = null;
            }
            qgo.BIo bIo = zxp.zyO;
            if (bIo == null || (l = ((zfK) bIo).zZm) == null) {
                l = null;
            }
            AlexaClientEventBus alexaClientEventBus = this.JTe;
            if ("".isEmpty()) {
                alexaClientEventBus.zyO(new MuN(xnt, str, str2, str3, uri, uri2, l, null));
                return;
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", ""));
        }
        throw new NullPointerException("Null audioItemIdentifier");
    }
}
