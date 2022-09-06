package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class dg<T> implements ServiceConnection {
    private final String jp;
    private T jr;
    private List<a> js;
    private boolean jt;
    private ComponentName mComponentName;
    private final Context mContext;
    private final Executor mExecutor;
    static final long cK = TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS);
    private static final String TAG = dg.class.getName();
    private final List<a<T>> jq = new LinkedList();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static abstract class a<T> implements Runnable {
        protected final dg<T> jv;

        public a(dg<T> dgVar) {
            this.jv = dgVar;
        }

        public abstract void d(T t) throws RemoteException;

        public abstract void onError();

        @Override // java.lang.Runnable
        public void run() {
            this.jv.e(this);
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class b implements Runnable {
        private final a<?> jw;

        b(a<?> aVar) {
            this.jw = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.jw.onError();
        }
    }

    public dg(Context context, String str, Executor executor) {
        this.mContext = context.getApplicationContext();
        this.jp = str;
        this.mExecutor = executor;
    }

    private synchronized void f(a<T> aVar) {
        if (this.jr == null) {
            if (this.js == null) {
                this.js = new ArrayList();
                this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.identity.auth.device.dg.1
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (dg.this) {
                            if (dg.this.jr != null) {
                                return;
                            }
                            String str = dg.TAG;
                            io.e(str, "Application timed out trying to bind to " + dg.this.mComponentName);
                            List<a> list = dg.this.js;
                            dg.this.js = null;
                            if (list == null) {
                                return;
                            }
                            mq.b("BindTimeout", new String[0]);
                            for (a aVar2 : list) {
                                dg.this.mExecutor.execute(new b(aVar2));
                            }
                        }
                    }
                }, cK);
            }
            this.js.add(aVar);
            return;
        }
        this.mExecutor.execute(aVar);
    }

    private synchronized ComponentName getComponentName() {
        if (this.mComponentName != null) {
            return this.mComponentName;
        }
        this.mComponentName = eb.a(this.mContext, this.jp, eb.lg);
        if (this.mComponentName == null) {
            String str = TAG;
            io.e(str, "Couldn't find " + this.jp);
        } else {
            String str2 = TAG;
            new StringBuilder("Found service ").append(this.mComponentName);
            io.dm(str2);
        }
        return this.mComponentName;
    }

    protected abstract T a(IBinder iBinder);

    public synchronized boolean cL() {
        if (this.jr != null) {
            String str = TAG;
            new StringBuilder("already bound: ").append(this.mComponentName);
            io.dm(str);
            return true;
        } else if (this.jt) {
            String str2 = TAG;
            new StringBuilder("bind already initiated: ").append(this.mComponentName);
            io.dm(str2);
            return true;
        } else {
            ComponentName componentName = getComponentName();
            if (componentName == null) {
                return false;
            }
            Intent intent = new Intent();
            intent.setComponent(componentName);
            try {
                if (this.mContext.bindService(intent, this, 21)) {
                    String str3 = TAG;
                    new StringBuilder("binding: ").append(this.mComponentName);
                    io.dm(str3);
                    this.jt = true;
                    return true;
                }
                mq.b("BindFailed", new String[0]);
                String str4 = TAG;
                io.w(str4, "bind failed: " + this.mComponentName);
                return false;
            } catch (SecurityException e) {
                mq.b("BindFailed", new String[0]);
                String str5 = TAG;
                io.w(str5, "bind failed: " + this.mComponentName, e);
                return false;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        List<a> list;
        synchronized (this) {
            String str = TAG;
            new StringBuilder("onServiceConnected: ").append(this.mComponentName);
            io.dm(str);
            this.jr = a(iBinder);
            list = this.js;
            this.js = null;
        }
        if (list != null) {
            for (a aVar : list) {
                this.mExecutor.execute(aVar);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public synchronized void onServiceDisconnected(ComponentName componentName) {
        String str = TAG;
        new StringBuilder("onServiceDisconnected: ").append(this.mComponentName);
        io.dm(str);
        this.jr = null;
        for (a<T> aVar : this.jq) {
            this.mExecutor.execute(new b(aVar));
        }
        this.jq.clear();
    }

    public synchronized void a(a<T> aVar) {
        if (!cL()) {
            aVar.onError();
        } else {
            f(aVar);
        }
    }

    public synchronized boolean b(a<T> aVar) {
        if (this.jr == null) {
            return false;
        }
        f(aVar);
        return true;
    }

    public synchronized void c(a<T> aVar) {
        this.jq.add(aVar);
    }

    public synchronized void d(a<T> aVar) {
        this.jq.remove(aVar);
    }

    public void e(a<T> aVar) {
        T t;
        synchronized (this) {
            t = this.jr;
        }
        if (t == null) {
            io.w(TAG, "Service was disconnected before task could execute; re-enqueuing task to run after service re-connects.");
            f(aVar);
            return;
        }
        try {
            aVar.d(t);
        } catch (RemoteException unused) {
            aVar.onError();
        }
    }
}
