package com.amazon.alexa;

import android.content.ComponentName;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: MessageReceiverAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class KvZ {
    public static final String zZm = "KvZ";
    public final SignatureVerifier BIo;
    public final Map<ComponentName, MessageReceiversManager> Qle;
    public final Map<ExtendedClient, MessageReceiversManager> jiA;
    public final UYN zQM;
    public final MessageReceiversManager zyO;

    @Inject
    public KvZ(SignatureVerifier signatureVerifier, UYN uyn) {
        MessageReceiversManager messageReceiversManager = new MessageReceiversManager(signatureVerifier, uyn);
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Preconditions.notNull(signatureVerifier, "signature verifier is null");
        Preconditions.notNull(uyn, "error reporter is null");
        Preconditions.notNull(messageReceiversManager, "alexaServiceMessageReceiverManager map is null");
        Preconditions.notNull(hashMap, "clientMessengerManagers map is null");
        Preconditions.notNull(hashMap2, "capabilityAgentMessengerManagers map is null");
        this.BIo = signatureVerifier;
        this.zQM = uyn;
        this.zyO = messageReceiversManager;
        this.jiA = hashMap;
        this.Qle = hashMap2;
    }

    public synchronized void BIo(ExtendedClient extendedClient) {
        Preconditions.notNull(extendedClient, "client is null");
        C0179Pya.zZm("clearing the client ", (Object) extendedClient);
        MessageReceiversManager remove = this.jiA.remove(extendedClient);
        if (remove != null) {
            remove.clear();
        }
    }

    public synchronized MessageReceiversManager zZm() {
        return this.zyO;
    }

    public synchronized MessageReceiversManager zZm(ExtendedClient extendedClient) {
        if (!this.jiA.containsKey(extendedClient)) {
            C0179Pya.zZm("creating the manager for the client ", (Object) extendedClient);
            this.jiA.put(extendedClient, new MessageReceiversManager(this.BIo, this.zQM));
        }
        C0179Pya.zZm("getting the manager for the client ", (Object) extendedClient);
        return this.jiA.get(extendedClient);
    }

    public synchronized void BIo(ComponentName componentName) {
        Preconditions.notNull(componentName, "componentName is null");
        C0179Pya.zZm("clearing the capability agent ", (Object) componentName);
        MessageReceiversManager remove = this.Qle.remove(componentName);
        if (remove != null) {
            remove.clear();
        }
    }

    public synchronized MessageReceiversManager zZm(ComponentName componentName) {
        if (!this.Qle.containsKey(componentName)) {
            C0179Pya.zZm("creating the manager for the capability agent ", (Object) componentName);
            this.Qle.put(componentName, new MessageReceiversManager(this.BIo, this.zQM));
        }
        C0179Pya.zZm("getting the manager for the capability agent ", (Object) componentName);
        return this.Qle.get(componentName);
    }

    public synchronized void BIo() {
        this.zyO.clear();
        for (MessageReceiversManager messageReceiversManager : this.jiA.values()) {
            messageReceiversManager.clear();
        }
        this.jiA.clear();
        for (MessageReceiversManager messageReceiversManager2 : this.Qle.values()) {
            messageReceiversManager2.clear();
        }
        this.Qle.clear();
    }
}
