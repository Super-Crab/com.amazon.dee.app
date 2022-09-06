package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ClientConnectionsAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class IYJ {
    public static final String zZm = "IYJ";
    public final AlexaClientEventBus Qle;
    public final Map<ExtendedClient, zZm> BIo = new HashMap();
    public final Map<ExtendedClient, Set<Class<?>>> zQM = new HashMap();
    public final Set<ExtendedClient> zyO = new HashSet();
    public final Set<ExtendedClient> jiA = new HashSet();

    /* compiled from: ClientConnectionsAuthority.java */
    /* loaded from: classes.dex */
    public enum zZm {
        CONNECTING,
        LACKING_REQUIRED_LISTENERS,
        CONNECTED
    }

    static {
        Collections.unmodifiableSet(new HashSet());
    }

    @Inject
    public IYJ(AlexaClientEventBus alexaClientEventBus) {
        this.Qle = alexaClientEventBus;
    }

    public synchronized void BIo(ExtendedClient extendedClient, boolean z) {
        StringBuilder zZm2 = C0179Pya.zZm("Client ");
        zZm2.append(extendedClient.getId());
        zZm2.append(" keep alive is set to ");
        zZm2.append(z);
        zZm2.toString();
        if (z) {
            this.zyO.add(extendedClient);
        }
    }

    public synchronized boolean Qle() {
        return !this.BIo.isEmpty();
    }

    public synchronized boolean jiA() {
        boolean z;
        Iterator<ExtendedClient> it2 = this.jiA.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            if (this.BIo.containsKey(it2.next())) {
                z = true;
                break;
            }
        }
        return z;
    }

    public synchronized Set<ExtendedClient> zQM() {
        HashSet hashSet;
        hashSet = new HashSet();
        for (Map.Entry<ExtendedClient, zZm> entry : this.BIo.entrySet()) {
            if (zZm.CONNECTED.equals(entry.getValue())) {
                hashSet.add(entry.getKey());
            }
        }
        return hashSet;
    }

    public synchronized void zZm(ExtendedClient extendedClient) {
        StringBuilder zZm2 = C0179Pya.zZm("Client ");
        zZm2.append(extendedClient.getId());
        zZm2.append(" is connecting");
        zZm2.toString();
        this.BIo.put(extendedClient, zZm.CONNECTING);
        this.zQM.put(extendedClient, new HashSet());
        this.Qle.zyO(new gsu(this.BIo.size(), extendedClient));
    }

    public synchronized boolean zyO() {
        boolean z;
        Iterator<ExtendedClient> it2 = this.zyO.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            if (this.BIo.containsKey(it2.next())) {
                z = true;
                break;
            }
        }
        return z;
    }

    public synchronized void BIo(@Nullable ExtendedClient extendedClient) {
        if (extendedClient == null) {
            return;
        }
        StringBuilder zZm2 = C0179Pya.zZm("Client ");
        zZm2.append(extendedClient.getId());
        zZm2.append(" disconnected");
        zZm2.toString();
        this.zQM.remove(extendedClient);
        this.zyO.remove(extendedClient);
        this.jiA.remove(extendedClient);
        if (this.BIo.containsKey(extendedClient)) {
            this.BIo.remove(extendedClient);
            this.Qle.zyO(new hZD(zQM().size()));
        }
    }

    public synchronized void zZm(ExtendedClient extendedClient, boolean z) {
        StringBuilder zZm2 = C0179Pya.zZm("Client ");
        zZm2.append(extendedClient.getId());
        zZm2.append(" allows background activity starts? ");
        zZm2.append(z);
        zZm2.toString();
        if (z) {
            this.jiA.add(extendedClient);
        }
    }

    public synchronized Set<ExtendedClient> zZm() {
        HashSet hashSet;
        this.zQM.clear();
        hashSet = new HashSet();
        hashSet.addAll(this.BIo.keySet());
        this.BIo.clear();
        this.zyO.clear();
        this.jiA.clear();
        this.Qle.zyO(new hZD(zQM().size()));
        return hashSet;
    }

    public synchronized Set<ExtendedClient> BIo() {
        HashSet hashSet;
        hashSet = new HashSet();
        for (ExtendedClient extendedClient : this.zyO) {
            if (this.BIo.containsKey(extendedClient)) {
                hashSet.add(extendedClient);
            }
        }
        return hashSet;
    }
}
