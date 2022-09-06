package com.amazon.alexa;

import android.os.IBinder;
import android.os.IInterface;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* compiled from: ClientProxyListenerContainer.java */
/* loaded from: classes.dex */
public class CDz<T extends IInterface> extends Shr<T> {
    public final Map<IBinder, T> zQM = new HashMap();
    public final Map<IBinder, ExtendedClient> zyO = new HashMap();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.alexa.Shr
    public /* bridge */ /* synthetic */ ExtendedClient BIo(Object obj) {
        return BIo((CDz<T>) ((IInterface) obj));
    }

    @Override // com.amazon.alexa.Shr
    public void clear() {
        synchronized (this.zZm) {
            super.clear();
            this.zyO.clear();
            this.zQM.clear();
        }
    }

    @Override // com.amazon.alexa.Shr
    /* renamed from: zQM */
    public ExtendedClient remove(T t) {
        Preconditions.notNull(t, "listener cannot be null");
        synchronized (this.zZm) {
            IBinder asBinder = t.asBinder();
            T remove = this.zQM.remove(asBinder);
            this.zyO.remove(asBinder);
            if (remove != null) {
                return super.remove(remove);
            }
            return super.remove(t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.alexa.Shr
    public /* bridge */ /* synthetic */ void zZm(ExtendedClient extendedClient, Object obj) {
        zZm(extendedClient, (ExtendedClient) ((IInterface) obj));
    }

    public ExtendedClient BIo(T t) {
        ExtendedClient extendedClient;
        Preconditions.notNull(t, "listener cannot be null");
        synchronized (this.zZm) {
            extendedClient = this.zyO.get(t.asBinder());
        }
        return extendedClient;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.alexa.Shr
    public /* bridge */ /* synthetic */ boolean zZm(Object obj) {
        return zZm((CDz<T>) ((IInterface) obj));
    }

    public void zZm(ExtendedClient extendedClient, T t) {
        Preconditions.notNull(extendedClient, "client cannot be null");
        Preconditions.notNull(t, "listener cannot be null");
        synchronized (this.zZm) {
            super.zZm(extendedClient, (ExtendedClient) t);
            IBinder asBinder = t.asBinder();
            this.zQM.put(asBinder, t);
            this.zyO.put(asBinder, extendedClient);
        }
    }

    @Override // com.amazon.alexa.Shr
    public Set<T> BIo(ExtendedClient extendedClient) {
        Set<T> BIo;
        Preconditions.notNull(extendedClient, "client cannot be null");
        synchronized (this.zZm) {
            BIo = super.BIo(extendedClient);
            for (T t : BIo) {
                IBinder asBinder = t.asBinder();
                this.zQM.remove(asBinder);
                this.zyO.remove(asBinder);
            }
        }
        return BIo;
    }

    public boolean zZm(T t) {
        boolean containsKey;
        Preconditions.notNull(t, "listener cannot be null");
        synchronized (this.zZm) {
            containsKey = this.zQM.containsKey(t.asBinder());
        }
        return containsKey;
    }
}
