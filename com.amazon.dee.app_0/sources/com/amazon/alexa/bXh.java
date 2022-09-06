package com.amazon.alexa;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: CapabilityAgentManager.java */
@Singleton
/* loaded from: classes.dex */
public class bXh {
    public static final String zZm = "bXh";
    public final C0234ubm BIo;
    public final AlexaClientEventBus JTe;
    public final Context LPk;
    public final ExecutorService Qle;
    public final VqX jiA;
    public final LrI zQM;
    public final Map<CapabilityAgent, ExecutorService> zyO;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CapabilityAgentManager.java */
    /* loaded from: classes.dex */
    public static abstract class BIo implements Runnable {
        public final Message BIo;
        public final MessageProcessingCallbacks zQM;
        public final CapabilityAgent zZm;

        public /* synthetic */ BIo(CapabilityAgent capabilityAgent, Message message, MessageProcessingCallbacks messageProcessingCallbacks, gSQ gsq) {
            this.zZm = capabilityAgent;
            this.BIo = message;
            this.zQM = messageProcessingCallbacks;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                zZm(this.zZm, this.BIo, this.zQM);
            } catch (Exception e) {
                Log.e(bXh.zZm, "Failed to execute task: ", e);
                this.zQM.onError();
            }
        }

        public abstract void zZm(CapabilityAgent capabilityAgent, Message message, MessageProcessingCallbacks messageProcessingCallbacks);
    }

    /* compiled from: CapabilityAgentManager.java */
    /* loaded from: classes.dex */
    private static class zQM extends BIo {
        public /* synthetic */ zQM(CapabilityAgent capabilityAgent, Message message, MessageProcessingCallbacks messageProcessingCallbacks, gSQ gsq) {
            super(capabilityAgent, message, messageProcessingCallbacks, null);
        }

        @Override // com.amazon.alexa.bXh.BIo
        public void zZm(CapabilityAgent capabilityAgent, Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
            capabilityAgent.preprocess(message, messageProcessingCallbacks);
        }
    }

    /* compiled from: CapabilityAgentManager.java */
    /* loaded from: classes.dex */
    private static class zZm extends BIo {
        public /* synthetic */ zZm(CapabilityAgent capabilityAgent, Message message, MessageProcessingCallbacks messageProcessingCallbacks, gSQ gsq) {
            super(capabilityAgent, message, messageProcessingCallbacks, null);
        }

        @Override // com.amazon.alexa.bXh.BIo
        public void zZm(CapabilityAgent capabilityAgent, Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
            capabilityAgent.cancel(message.getMessageIdentifier());
        }
    }

    /* compiled from: CapabilityAgentManager.java */
    /* loaded from: classes.dex */
    private static class zyO extends BIo {
        public /* synthetic */ zyO(CapabilityAgent capabilityAgent, Message message, MessageProcessingCallbacks messageProcessingCallbacks, gSQ gsq) {
            super(capabilityAgent, message, messageProcessingCallbacks, null);
        }

        @Override // com.amazon.alexa.bXh.BIo
        public void zZm(CapabilityAgent capabilityAgent, Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
            capabilityAgent.process(message.getMessageIdentifier());
        }
    }

    @Inject
    public bXh(AlexaClientEventBus alexaClientEventBus, C0234ubm c0234ubm, LrI lrI, VqX vqX, Context context) {
        ExecutorService newSingleThreadCachedThreadPool = ExecutorFactory.newSingleThreadCachedThreadPool("external-capability-agent-refresh-thread");
        this.BIo = c0234ubm;
        this.zQM = lrI;
        this.jiA = vqX;
        this.Qle = newSingleThreadCachedThreadPool;
        this.zyO = new HashMap();
        this.JTe = alexaClientEventBus;
        this.LPk = context;
        this.zyO.put(vqX, zZm(vqX));
        alexaClientEventBus.zZm(this);
    }

    public final ExecutorService BIo(CapabilityAgent capabilityAgent) {
        if (!this.zyO.containsKey(capabilityAgent)) {
            this.zyO.put(capabilityAgent, zZm(capabilityAgent));
        }
        return this.zyO.get(capabilityAgent);
    }

    @Subscribe
    public void on(Vba vba) {
        liQ liq = (liQ) vba;
        for (CapabilityAgent capabilityAgent : zZm(liq.BIo)) {
            BIo(capabilityAgent).submit(new zQM(capabilityAgent, liq.BIo, liq.zQM, null));
        }
    }

    public void zZm() {
        this.Qle.submit(new gSQ(this));
    }

    public final Set<CapabilityAgent> zZm(Message message) {
        Set set;
        Namespace namespace = message.getHeader().getNamespace();
        HashSet hashSet = new HashSet();
        if (this.BIo.zZm.containsKey(namespace)) {
            hashSet.addAll((Set) this.BIo.zZm.get(namespace));
        } else if (this.zQM.zZm(namespace)) {
            LrI lrI = this.zQM;
            if (lrI.zQM()) {
                Log.i(LrI.BIo, "get external capability agents from v2 for: " + namespace);
                set = new HashSet();
                Iterator<KHc> it2 = ((wdQ) lrI.zzR).BIo().iterator();
                while (it2.hasNext()) {
                    Qrg qrg = (Qrg) it2.next();
                    Iterator<Capability> it3 = qrg.zyO.iterator();
                    while (it3.hasNext()) {
                        if (it3.next().getInterface().getValue().equals(namespace.getValue())) {
                            set.add(new zEy(lrI.yPL.zZm(lrI.zyO, qrg.zZm, lrI.Mlj), lrI.jiA, lrI.LPk, lrI.JTe));
                        }
                    }
                }
            } else {
                set = (Set) lrI.zZm.get(namespace);
            }
            hashSet.addAll(set);
            boolean z = true;
            if (hashSet.size() > 1) {
                if ((this.LPk.getApplicationInfo().flags & 2) == 0) {
                    z = false;
                }
                String str = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("MULTIPLE CAPABILITY AGENTS REGISTERED FOR MESSAGE: ");
                zZm2.append(message.getHeader());
                Log.e(str, zZm2.toString());
                Log.e(zZm, "THIS CAUSES UNDEFINED BEHAVIOR. DO NOT DO THIS!");
                if (z) {
                    final String str2 = message.getHeader() + " already exists ! Multiple capability agents are not allowed to be registered! DO NOT DO THIS AS IT CAUSES UNDEFINED BEHAVIOR!";
                    ContextCompat.getMainExecutor(this.LPk).execute(new Runnable() { // from class: com.amazon.alexa.-$$Lambda$bXh$e1V6QSI9mP2zVRzd_LdcXa2KP6c
                        @Override // java.lang.Runnable
                        public final void run() {
                            bXh.this.zZm(str2);
                        }
                    });
                    throw new IllegalStateException("Multiple capability agents are not allowed to be registered");
                } else if (namespace != null) {
                    if ("".isEmpty()) {
                        this.JTe.zyO(new jLE(namespace, null));
                    } else {
                        throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", ""));
                    }
                } else {
                    throw new NullPointerException("Null namespace");
                }
            }
        }
        if (hashSet.isEmpty()) {
            hashSet.add(this.jiA);
        }
        return hashSet;
    }

    public void BIo() {
        HashSet hashSet = new HashSet();
        for (CapabilityAgent capabilityAgent : this.BIo.zZm()) {
            hashSet.addAll(capabilityAgent.getCapabilities());
        }
        this.JTe.zyO(new Hvd(true, true, hashSet, -1L, 1, 1));
    }

    @Subscribe
    public void on(wVP wvp) {
        Cuq cuq = (Cuq) wvp;
        for (CapabilityAgent capabilityAgent : zZm(cuq.BIo)) {
            BIo(capabilityAgent).submit(new zyO(capabilityAgent, cuq.BIo, cuq.zQM, null));
        }
    }

    @Subscribe
    public void on(SQt sQt) {
        NBU nbu = (NBU) sQt;
        for (CapabilityAgent capabilityAgent : zZm(nbu.BIo)) {
            BIo(capabilityAgent).submit(new zZm(capabilityAgent, nbu.BIo, nbu.zQM, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void zZm(String str) {
        Toast.makeText(this.LPk, str, 1).show();
    }

    @VisibleForTesting
    public ExecutorService zZm(CapabilityAgent capabilityAgent) {
        StringBuilder zZm2 = C0179Pya.zZm("proc-");
        zZm2.append(capabilityAgent.getClass().getSimpleName());
        return ManagedExecutorFactory.newSingleThreadCachedThreadPool(zZm2.toString());
    }
}
