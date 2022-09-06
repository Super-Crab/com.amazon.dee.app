package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import com.amazon.alexa.voice.ui.onedesign.util.image.ImageType;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
public final class zzw implements ServiceConnection {
    @GuardedBy("this")
    int zza;
    final Messenger zzb;
    zzaf zzc;
    @GuardedBy("this")
    final Queue<zzah<?>> zzd;
    @GuardedBy("this")
    final SparseArray<zzah<?>> zze;
    final /* synthetic */ zzv zzf;

    private zzw(zzv zzvVar) {
        this.zzf = zzvVar;
        this.zza = 0;
        this.zzb = new Messenger(new com.google.android.gms.internal.firebase_messaging.zze(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.firebase.iid.zzz
            private final zzw zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zza.zza(message);
            }
        }));
        this.zzd = new ArrayDeque();
        this.zze = new SparseArray<>();
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        ScheduledExecutorService scheduledExecutorService;
        Log.isLoggable("MessengerIpcClient", 2);
        scheduledExecutorService = this.zzf.zzc;
        scheduledExecutorService.execute(new Runnable(this, iBinder) { // from class: com.google.firebase.iid.zzab
            private final zzw zza;
            private final IBinder zzb;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
                this.zzb = iBinder;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzw zzwVar = this.zza;
                IBinder iBinder2 = this.zzb;
                synchronized (zzwVar) {
                    try {
                        if (iBinder2 == null) {
                            zzwVar.zza(0, "Null service connection");
                            return;
                        }
                        try {
                            zzwVar.zzc = new zzaf(iBinder2);
                            zzwVar.zza = 2;
                            zzwVar.zza();
                        } catch (RemoteException e) {
                            zzwVar.zza(0, e.getMessage());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        ScheduledExecutorService scheduledExecutorService;
        Log.isLoggable("MessengerIpcClient", 2);
        scheduledExecutorService = this.zzf.zzc;
        scheduledExecutorService.execute(new Runnable(this) { // from class: com.google.firebase.iid.zzad
            private final zzw zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(2, "Service disconnected");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zza(zzah<?> zzahVar) {
        Context context;
        ScheduledExecutorService scheduledExecutorService;
        int i = this.zza;
        if (i == 0) {
            this.zzd.add(zzahVar);
            Preconditions.checkState(this.zza == 0);
            Log.isLoggable("MessengerIpcClient", 2);
            this.zza = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
            context = this.zzf.zzb;
            if (connectionTracker.bindService(context, intent, this, 1)) {
                scheduledExecutorService = this.zzf.zzc;
                scheduledExecutorService.schedule(new Runnable(this) { // from class: com.google.firebase.iid.zzy
                    private final zzw zza;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zza = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zza.zzc();
                    }
                }, 30L, TimeUnit.SECONDS);
            } else {
                zza(0, "Unable to bind to service");
            }
            return true;
        } else if (i == 1) {
            this.zzd.add(zzahVar);
            return true;
        } else if (i == 2) {
            this.zzd.add(zzahVar);
            zza();
            return true;
        } else {
            if (i != 3 && i != 4) {
                int i2 = this.zza;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i2);
                throw new IllegalStateException(sb.toString());
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzb() {
        Context context;
        if (this.zza == 2 && this.zzd.isEmpty() && this.zze.size() == 0) {
            Log.isLoggable("MessengerIpcClient", 2);
            this.zza = 3;
            ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
            context = this.zzf.zzb;
            connectionTracker.unbindService(context, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzc() {
        if (this.zza == 1) {
            zza(1, "Timed out while binding");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Received response to request: ");
            sb.append(i);
            sb.toString();
        }
        synchronized (this) {
            zzah<?> zzahVar = this.zze.get(i);
            if (zzahVar == null) {
                StringBuilder sb2 = new StringBuilder(50);
                sb2.append("Received response for unknown request: ");
                sb2.append(i);
                Log.w("MessengerIpcClient", sb2.toString());
                return true;
            }
            this.zze.remove(i);
            zzb();
            Bundle data = message.getData();
            if (data.getBoolean(ImageType.UNSUPPORTED, false)) {
                zzahVar.zza(new zzag(4, "Not supported by GmsCore"));
            } else {
                zzahVar.zza(data);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza() {
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = this.zzf.zzc;
        scheduledExecutorService.execute(new Runnable(this) { // from class: com.google.firebase.iid.zzaa
            private final zzw zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final zzah<?> poll;
                ScheduledExecutorService scheduledExecutorService2;
                Context context;
                final zzw zzwVar = this.zza;
                while (true) {
                    synchronized (zzwVar) {
                        if (zzwVar.zza != 2) {
                            return;
                        }
                        if (zzwVar.zzd.isEmpty()) {
                            zzwVar.zzb();
                            return;
                        }
                        poll = zzwVar.zzd.poll();
                        zzwVar.zze.put(poll.zza, poll);
                        scheduledExecutorService2 = zzwVar.zzf.zzc;
                        scheduledExecutorService2.schedule(new Runnable(zzwVar, poll) { // from class: com.google.firebase.iid.zzac
                            private final zzw zza;
                            private final zzah zzb;

                            /* JADX INFO: Access modifiers changed from: package-private */
                            {
                                this.zza = zzwVar;
                                this.zzb = poll;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zza.zza(this.zzb.zza);
                            }
                        }, 30L, TimeUnit.SECONDS);
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String valueOf = String.valueOf(poll);
                        StringBuilder sb = new StringBuilder(valueOf.length() + 8);
                        sb.append("Sending ");
                        sb.append(valueOf);
                        sb.toString();
                    }
                    context = zzwVar.zzf.zzb;
                    Messenger messenger = zzwVar.zzb;
                    Message obtain = Message.obtain();
                    obtain.what = poll.zzc;
                    obtain.arg1 = poll.zza;
                    obtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", poll.zza());
                    bundle.putString("pkg", context.getPackageName());
                    bundle.putBundle("data", poll.zzd);
                    obtain.setData(bundle);
                    try {
                        zzwVar.zzc.zza(obtain);
                    } catch (RemoteException e) {
                        zzwVar.zza(2, e.getMessage());
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i, String str) {
        Context context;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
                "Disconnected: ".concat(valueOf);
            } else {
                new String("Disconnected: ");
            }
        }
        int i2 = this.zza;
        if (i2 != 0) {
            if (i2 != 1 && i2 != 2) {
                if (i2 == 3) {
                    this.zza = 4;
                    return;
                } else if (i2 == 4) {
                    return;
                } else {
                    int i3 = this.zza;
                    StringBuilder sb = new StringBuilder(26);
                    sb.append("Unknown state: ");
                    sb.append(i3);
                    throw new IllegalStateException(sb.toString());
                }
            }
            Log.isLoggable("MessengerIpcClient", 2);
            this.zza = 4;
            ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
            context = this.zzf.zzb;
            connectionTracker.unbindService(context, this);
            zzag zzagVar = new zzag(i, str);
            for (zzah<?> zzahVar : this.zzd) {
                zzahVar.zza(zzagVar);
            }
            this.zzd.clear();
            for (int i4 = 0; i4 < this.zze.size(); i4++) {
                this.zze.valueAt(i4).zza(zzagVar);
            }
            this.zze.clear();
            return;
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i) {
        zzah<?> zzahVar = this.zze.get(i);
        if (zzahVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.zze.remove(i);
            zzahVar.zza(new zzag(3, "Timed out waiting for response"));
            zzb();
        }
    }
}
