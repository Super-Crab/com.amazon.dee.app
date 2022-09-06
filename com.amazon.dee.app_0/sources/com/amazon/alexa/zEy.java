package com.amazon.alexa;

import android.content.ComponentName;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.ExternalCapabilityAgentConnection;
import com.amazon.alexa.api.ExternalCapabilityAgents;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.CapabilityType;
import com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messages.MessageTransformer;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.pPw;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/* compiled from: ExternalCapabilityAgent.java */
/* loaded from: classes.dex */
public class zEy implements CapabilityAgent {
    public static final String zZm = "zEy";
    public final ExternalCapabilityAgentConnection BIo;
    public ScheduledExecutorService JTe;
    public final AlexaClientEventBus Qle;
    public final Set<Capability> jiA;
    public final Map<MessageIdentifier, zZm> zQM = new HashMap();
    public final MessageTransformer zyO;

    /* compiled from: ExternalCapabilityAgent.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public class zZm implements InterfaceC0189bOx {
        public final MessageProcessingCallbacks BIo;
        public FutureTask<?> zQM;
        public final MessageIdentifier zZm;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ExternalCapabilityAgent.java */
        /* renamed from: com.amazon.alexa.zEy$zZm$zZm  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class CallableC0038zZm implements Callable<Void> {
            public CallableC0038zZm() {
            }

            @Override // java.util.concurrent.Callable
            public /* bridge */ /* synthetic */ Void call() throws Exception {
                call2();
                return null;
            }

            @Override // java.util.concurrent.Callable
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Void call2() throws Exception {
                synchronized (zEy.this) {
                    zZm.this.BIo();
                }
                return null;
            }
        }

        public zZm(MessageIdentifier messageIdentifier, MessageProcessingCallbacks messageProcessingCallbacks) {
            this.zZm = messageIdentifier;
            this.BIo = messageProcessingCallbacks;
        }

        public void BIo() {
            this.BIo.onError();
            zZm();
        }

        public final synchronized void zQM() {
            if (this.zQM == null) {
                this.zQM = new FutureTask<>(new CallableC0038zZm());
                zEy.this.JTe.schedule(this.zQM, 500L, TimeUnit.MILLISECONDS);
            }
        }

        public final void zZm() {
            synchronized (this) {
                if (this.zQM != null) {
                    this.zQM.cancel(true);
                    this.zQM = null;
                }
            }
            zEy.this.zQM.remove(this.zZm);
            if (zEy.this.zQM.isEmpty()) {
                zEy.this.BIo.disconnect();
            }
        }
    }

    public zEy(ExternalCapabilityAgentConnection externalCapabilityAgentConnection, MessageTransformer messageTransformer, @Nullable Set<Capability> set, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = externalCapabilityAgentConnection;
        this.zyO = messageTransformer;
        this.jiA = set == null ? Collections.emptySet() : set;
        this.Qle = alexaClientEventBus;
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent
    public void cancel(@NonNull MessageIdentifier messageIdentifier) {
        if (this.BIo.isConnected()) {
            try {
                ExternalCapabilityAgents.zZm(this.BIo, messageIdentifier.getValue());
                this.Qle.zyO(new Rqk(pPw.zQM.CANCEL, ""));
                return;
            } catch (noF e) {
                this.Qle.zyO(pPw.zZm.AbstractC0034zZm.zZm(pPw.zQM.CANCEL, e.zZm));
                zZm(messageIdentifier, e);
                return;
            }
        }
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("External capability agent ");
        zZm2.append(BIo());
        zZm2.append(" is not connected. Failed to cancel message.");
        Log.e(str, zZm2.toString());
        this.Qle.zyO(pPw.zZm.AbstractC0034zZm.zZm(pPw.zQM.CANCEL, pPw.BIo.CONNECTION_FAILURE));
        zZm(messageIdentifier, new RuntimeException());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && zEy.class == obj.getClass()) {
            return Objects.equals(zZm(), ((zEy) obj).zZm());
        }
        return false;
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent
    public Set<Capability> getCapabilities() {
        return this.jiA;
    }

    public int hashCode() {
        return Objects.hash(zZm());
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent
    public void preprocess(@NonNull Message message, @NonNull MessageProcessingCallbacks messageProcessingCallbacks) {
        if (this.JTe == null) {
            StringBuilder zZm2 = C0179Pya.zZm("external-processing-for:");
            zZm2.append(zZm().getPackageName());
            this.JTe = ManagedExecutorFactory.newSingleThreadScheduledExecutor(zZm2.toString());
        }
        StringBuilder zZm3 = C0179Pya.zZm("External preprocess: ");
        zZm3.append(message.getMessageIdentifier());
        zZm3.toString();
        if (!this.BIo.isConnected()) {
            StringBuilder zZm4 = C0179Pya.zZm("Connecting to external service: ");
            zZm4.append(message.getMessageIdentifier());
            zZm4.toString();
            this.BIo.connect();
        }
        String str = message.getHeader().getNamespace().getValue() + ":" + message.getHeader().getName().getValue();
        if (!this.BIo.isConnected()) {
            String str2 = zZm;
            StringBuilder zZm5 = C0179Pya.zZm("External capability agent ");
            zZm5.append(BIo());
            zZm5.append(" is not connected. Failed to preprocess message.");
            Log.e(str2, zZm5.toString());
            this.Qle.zyO(new Ybj(pPw.zQM.PREPROCESS, pPw.BIo.CONNECTION_FAILURE, str));
            messageProcessingCallbacks.onError();
            return;
        }
        zZm zzm = new zZm(message.getMessageIdentifier(), messageProcessingCallbacks);
        this.zQM.put(message.getMessageIdentifier(), zzm);
        try {
            ExternalCapabilityAgents.zZm(this.BIo, this.zyO.convertMessageToAlexaDirective(message), zzm);
            this.Qle.zyO(new Rqk(pPw.zQM.PREPROCESS, str));
        } catch (noF e) {
            this.Qle.zyO(new Ybj(pPw.zQM.PREPROCESS, e.zZm, str));
            zZm(message.getMessageIdentifier(), e);
        }
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent
    public void process(@NonNull MessageIdentifier messageIdentifier) {
        if (this.BIo.isConnected()) {
            try {
                ExternalCapabilityAgents.BIo(this.BIo, messageIdentifier.getValue());
                this.Qle.zyO(new Rqk(pPw.zQM.PROCESS, ""));
                return;
            } catch (noF e) {
                this.Qle.zyO(pPw.zZm.AbstractC0034zZm.zZm(pPw.zQM.PROCESS, e.zZm));
                zZm(messageIdentifier, e);
                return;
            }
        }
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("External capability agent ");
        zZm2.append(BIo());
        zZm2.append(" is not connected. Failed to process message.");
        Log.e(str, zZm2.toString());
        this.Qle.zyO(pPw.zZm.AbstractC0034zZm.zZm(pPw.zQM.PROCESS, pPw.BIo.CONNECTION_FAILURE));
        zZm(messageIdentifier, new RuntimeException());
    }

    public final String BIo() {
        return zZm().getClassName();
    }

    public Set<Namespace> zQM() {
        HashSet hashSet = new HashSet();
        for (Capability capability : this.jiA) {
            if (capability.getType().equals(CapabilityType.createAlexaInterface())) {
                hashSet.add(Namespace.create(capability.getInterface().getValue()));
            }
        }
        return hashSet;
    }

    public ComponentName zZm() {
        return this.BIo.zZm;
    }

    public final void zZm(MessageIdentifier messageIdentifier, Exception exc) {
        String str = zZm;
        StringBuilder sb = new StringBuilder();
        sb.append("Exception handling directive ");
        sb.append(messageIdentifier);
        sb.append(". Message = ");
        GeneratedOutlineSupport1.outline148(exc, sb, str);
        zZm zzm = this.zQM.get(messageIdentifier);
        if (zzm != null) {
            zzm.BIo();
        }
    }
}
