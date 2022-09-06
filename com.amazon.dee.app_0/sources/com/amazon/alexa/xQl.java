package com.amazon.alexa;

import com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent;
import com.amazon.alexa.client.core.messages.Namespace;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: CapabilityAgentRegistry.java */
/* loaded from: classes.dex */
public abstract class xQl<T extends CapabilityAgent> {
    public final Map<Namespace, Set<T>> zZm = new ConcurrentHashMap();

    public synchronized Set<Namespace> BIo() {
        return this.zZm.keySet();
    }

    public Set<T> zZm() {
        HashSet hashSet = new HashSet();
        for (Set<T> set : this.zZm.values()) {
            hashSet.addAll(set);
        }
        return hashSet;
    }
}
