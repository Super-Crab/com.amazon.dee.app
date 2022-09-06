package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
public final class zax implements zabs {
    private final Looper zabj;
    private final GoogleApiManager zabm;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api<?>, Boolean> zaew;
    private final zaaw zaex;
    private final GoogleApiAvailabilityLight zaey;
    private final Condition zaez;
    private final boolean zafa;
    private final boolean zafb;
    @GuardedBy("mLock")
    private boolean zafd;
    @GuardedBy("mLock")
    private Map<zai<?>, ConnectionResult> zafe;
    @GuardedBy("mLock")
    private Map<zai<?>, ConnectionResult> zaff;
    @GuardedBy("mLock")
    private zaaa zafg;
    @GuardedBy("mLock")
    private ConnectionResult zafh;
    private final Map<Api.AnyClientKey<?>, zaw<?>> zaeu = new HashMap();
    private final Map<Api.AnyClientKey<?>, zaw<?>> zaev = new HashMap();
    private final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafc = new LinkedList();

    public zax(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList, zaaw zaawVar, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zaeo = lock;
        this.zabj = looper;
        this.zaez = lock.newCondition();
        this.zaey = googleApiAvailabilityLight;
        this.zaex = zaawVar;
        this.zaew = map2;
        this.zaet = clientSettings;
        this.zafa = z;
        HashMap hashMap = new HashMap();
        for (Api<?> api : map2.keySet()) {
            hashMap.put(api.getClientKey(), api);
        }
        HashMap hashMap2 = new HashMap();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            zaq zaqVar = arrayList.get(i);
            i++;
            zaq zaqVar2 = zaqVar;
            hashMap2.put(zaqVar2.mApi, zaqVar2);
        }
        boolean z5 = true;
        boolean z6 = true;
        boolean z7 = false;
        boolean z8 = false;
        for (Map.Entry<Api.AnyClientKey<?>, Api.Client> entry : map.entrySet()) {
            Api api2 = (Api) hashMap.get(entry.getKey());
            Api.Client value = entry.getValue();
            if (value.requiresGooglePlayServices()) {
                z4 = z6;
                if (!this.zaew.get(api2).booleanValue()) {
                    z3 = true;
                    z2 = true;
                } else {
                    z3 = z8;
                    z2 = true;
                }
            } else {
                z2 = z7;
                z3 = z8;
                z4 = false;
            }
            zaw<?> zawVar = new zaw<>(context, api2, looper, value, (zaq) hashMap2.get(api2), clientSettings, abstractClientBuilder);
            this.zaeu.put(entry.getKey(), zawVar);
            if (value.requiresSignIn()) {
                this.zaev.put(entry.getKey(), zawVar);
            }
            z8 = z3;
            z6 = z4;
            z7 = z2;
        }
        this.zafb = (!z7 || z6 || z8) ? false : z5;
        this.zabm = GoogleApiManager.zabc();
    }

    @Nullable
    private final ConnectionResult zaa(@NonNull Api.AnyClientKey<?> anyClientKey) {
        this.zaeo.lock();
        try {
            zaw<?> zawVar = this.zaeu.get(anyClientKey);
            if (this.zafe != null && zawVar != null) {
                return this.zafe.get(zawVar.zak());
            }
            this.zaeo.unlock();
            return null;
        } finally {
            this.zaeo.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[Catch: all -> 0x0044, TryCatch #0 {all -> 0x0044, blocks: (B:3:0x0005, B:5:0x000a, B:8:0x000f, B:9:0x0019, B:11:0x001f, B:13:0x002b), top: B:24:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean zaac() {
        /*
            r3 = this;
            java.util.concurrent.locks.Lock r0 = r3.zaeo
            r0.lock()
            boolean r0 = r3.zafd     // Catch: java.lang.Throwable -> L44
            r1 = 0
            if (r0 == 0) goto L3e
            boolean r0 = r3.zafa     // Catch: java.lang.Throwable -> L44
            if (r0 != 0) goto Lf
            goto L3e
        Lf:
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.api.internal.zaw<?>> r0 = r3.zaev     // Catch: java.lang.Throwable -> L44
            java.util.Set r0 = r0.keySet()     // Catch: java.lang.Throwable -> L44
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L44
        L19:
            boolean r2 = r0.hasNext()     // Catch: java.lang.Throwable -> L44
            if (r2 == 0) goto L37
            java.lang.Object r2 = r0.next()     // Catch: java.lang.Throwable -> L44
            com.google.android.gms.common.api.Api$AnyClientKey r2 = (com.google.android.gms.common.api.Api.AnyClientKey) r2     // Catch: java.lang.Throwable -> L44
            com.google.android.gms.common.ConnectionResult r2 = r3.zaa(r2)     // Catch: java.lang.Throwable -> L44
            if (r2 == 0) goto L31
            boolean r2 = r2.isSuccess()     // Catch: java.lang.Throwable -> L44
            if (r2 != 0) goto L19
        L31:
            java.util.concurrent.locks.Lock r0 = r3.zaeo
            r0.unlock()
            return r1
        L37:
            java.util.concurrent.locks.Lock r0 = r3.zaeo
            r0.unlock()
            r0 = 1
            return r0
        L3e:
            java.util.concurrent.locks.Lock r0 = r3.zaeo
            r0.unlock()
            return r1
        L44:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r3.zaeo
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zax.zaac():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaad() {
        ClientSettings clientSettings = this.zaet;
        if (clientSettings == null) {
            this.zaex.zaha = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(clientSettings.getRequiredScopes());
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zaet.getOptionalApiSettings();
        for (Api<?> api : optionalApiSettings.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(optionalApiSettings.get(api).mScopes);
            }
        }
        this.zaex.zaha = hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaae() {
        while (!this.zafc.isEmpty()) {
            execute(this.zafc.remove());
        }
        this.zaex.zab((Bundle) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    @GuardedBy("mLock")
    public final ConnectionResult zaaf() {
        ConnectionResult connectionResult = null;
        int i = 0;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        for (zaw<?> zawVar : this.zaeu.values()) {
            Api<?> api = zawVar.getApi();
            ConnectionResult connectionResult3 = this.zafe.get(zawVar.zak());
            if (!connectionResult3.isSuccess() && (!this.zaew.get(api).booleanValue() || connectionResult3.hasResolution() || this.zaey.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() == 4 && this.zafa) {
                    int priority = api.zah().getPriority();
                    if (connectionResult2 == null || i2 > priority) {
                        connectionResult2 = connectionResult3;
                        i2 = priority;
                    }
                } else {
                    int priority2 = api.zah().getPriority();
                    if (connectionResult == null || i > priority2) {
                        connectionResult = connectionResult3;
                        i = priority2;
                    }
                }
            }
        }
        return (connectionResult == null || connectionResult2 == null || i <= i2) ? connectionResult : connectionResult2;
    }

    private final <T extends BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zab(@NonNull T t) {
        Api.AnyClientKey<?> clientKey = t.getClientKey();
        ConnectionResult zaa = zaa(clientKey);
        if (zaa == null || zaa.getErrorCode() != 4) {
            return false;
        }
        t.setFailedResult(new Status(4, null, this.zabm.zaa(this.zaeu.get(clientKey).zak(), System.identityHashCode(this.zaex))));
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zaez.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafh;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void connect() {
        this.zaeo.lock();
        try {
            if (this.zafd) {
                return;
            }
            this.zafd = true;
            this.zafe = null;
            this.zaff = null;
            this.zafg = null;
            this.zafh = null;
            this.zabm.zao();
            this.zabm.zaa(this.zaeu.values()).addOnCompleteListener(new HandlerExecutor(this.zabj), new zaz(this));
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void disconnect() {
        this.zaeo.lock();
        try {
            this.zafd = false;
            this.zafe = null;
            this.zaff = null;
            if (this.zafg != null) {
                this.zafg.cancel();
                this.zafg = null;
            }
            this.zafh = null;
            while (!this.zafc.isEmpty()) {
                BaseImplementation.ApiMethodImpl<?, ?> remove = this.zafc.remove();
                remove.zaa((zacs) null);
                remove.cancel();
            }
            this.zaez.signalAll();
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        if (!this.zafa || !zab((zax) t)) {
            if (!isConnected()) {
                this.zafc.add(t);
                return t;
            }
            this.zaex.zahf.zab(t);
            return (T) this.zaeu.get(t.getClientKey()).doRead((zaw<?>) t);
        }
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        Api.AnyClientKey<A> clientKey = t.getClientKey();
        if (!this.zafa || !zab((zax) t)) {
            this.zaex.zahf.zab(t);
            return (T) this.zaeu.get(clientKey).doWrite((zaw<?>) t);
        }
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zaa(api.getClientKey());
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final boolean isConnected() {
        boolean z;
        this.zaeo.lock();
        try {
            if (this.zafe != null) {
                if (this.zafh == null) {
                    z = true;
                    return z;
                }
            }
            z = false;
            return z;
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final boolean isConnecting() {
        boolean z;
        this.zaeo.lock();
        try {
            if (this.zafe == null) {
                if (this.zafd) {
                    z = true;
                    return z;
                }
            }
            z = false;
            return z;
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        this.zaeo.lock();
        try {
            if (this.zafd && !zaac()) {
                this.zabm.zao();
                this.zafg = new zaaa(this, signInConnectionListener);
                this.zabm.zaa(this.zaev.values()).addOnCompleteListener(new HandlerExecutor(this.zabj), this.zafg);
                this.zaeo.unlock();
                return true;
            }
            this.zaeo.unlock();
            return false;
        } catch (Throwable th) {
            this.zaeo.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void maybeSignOut() {
        this.zaeo.lock();
        try {
            this.zabm.maybeSignOut();
            if (this.zafg != null) {
                this.zafg.cancel();
                this.zafg = null;
            }
            if (this.zaff == null) {
                this.zaff = new ArrayMap(this.zaev.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zaw<?> zawVar : this.zaev.values()) {
                this.zaff.put(zawVar.zak(), connectionResult);
            }
            if (this.zafe != null) {
                this.zafe.putAll(this.zaff);
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zaw() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(zaw<?> zawVar, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zaew.get(zawVar.getApi()).booleanValue() && zawVar.zaab().requiresGooglePlayServices() && this.zaey.isUserResolvableError(connectionResult.getErrorCode());
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                disconnect();
                return new ConnectionResult(14, null);
            }
            try {
                nanos = this.zaez.awaitNanos(nanos);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, null);
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafh;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, null);
    }
}
