package com.amazon.alexa;

import com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent;
import com.amazon.alexa.client.core.messages.Namespace;
import java.util.HashSet;
import javax.inject.Singleton;
/* compiled from: InternalCapabilityAgentRegistry.java */
@Singleton
/* renamed from: com.amazon.alexa.ubm  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0234ubm extends xQl<CapabilityAgent> {
    public void zZm(Namespace namespace, CapabilityAgent capabilityAgent) {
        if (!this.zZm.containsKey(namespace)) {
            HashSet hashSet = new HashSet();
            hashSet.add(capabilityAgent);
            this.zZm.put(namespace, hashSet);
            return;
        }
        throw new IllegalStateException("Namespace has already been registered for.");
    }
}
