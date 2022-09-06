package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Singleton;
/* compiled from: InternalComponentStateProviders.java */
@Singleton
/* loaded from: classes.dex */
public class lEV {
    public final Map<ComponentStateHeader, dRG> zZm = new ConcurrentHashMap();

    public void zZm(dRG drg) {
        this.zZm.put(drg.zZm(), drg);
    }

    public Set<ComponentState> zZm(boolean z) {
        HashSet hashSet = new HashSet();
        for (Map.Entry<ComponentStateHeader, dRG> entry : this.zZm.entrySet()) {
            if (!z) {
                if (!AvsApiConstants.Geolocation.zZm.equals(entry.getKey().BIo())) {
                }
            }
            ComponentState state = entry.getValue().getState();
            if (state != null) {
                hashSet.add(state);
            }
        }
        return hashSet;
    }
}
