package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.SparseIntArray;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.callback.IGenericIPC;
import com.amazon.identity.auth.device.callback.IRemoteCallback;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.dg;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dn {
    private static dg<IGenericIPC> jI;
    private final ec dr;
    private final ds ia;
    private final String jJ;
    private final String jK;
    private final SparseIntArray jL;
    private final dg<IGenericIPC> jv;
    private final Context mContext;
    public static final Uri jG = Uri.parse("content://com.amazon.map.generic_ipc");
    public static final Uri jH = Uri.parse("content://com.amazon.map.generic_ipc.directboot");
    private static final String[] hZ = {"bundle_value"};
    private static final String TAG = dn.class.getName();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    final class a implements Callback {
        final Callback cA;
        String jN;

        /* synthetic */ a(dn dnVar, Callback callback, byte b) {
            this(callback);
        }

        private synchronized boolean bl(String str) {
            if (this.jN != null) {
                String str2 = dn.TAG;
                io.w(str2, "Duplicate callback detected: " + str + " called after " + this.jN);
                return false;
            }
            this.jN = str;
            return true;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            Callback callback;
            if (bl("onError") && (callback = this.cA) != null) {
                callback.onError(dn.this.B(bundle));
            }
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            Callback callback;
            if (bl("onSuccess") && (callback = this.cA) != null) {
                callback.onSuccess(bundle);
            }
        }

        private a(Callback callback) {
            this.cA = callback;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static final class b extends dg.a<IGenericIPC> implements Callback {
        private final Callback g;
        private final Bundle jO;
        private final Class<? extends IPCCommand> jP;
        private boolean jQ;

        /* synthetic */ b(Callback callback, Bundle bundle, Class cls, dg dgVar, byte b) {
            this(callback, bundle, cls, dgVar);
        }

        @Override // com.amazon.identity.auth.device.dg.a
        /* renamed from: a */
        public void d(IGenericIPC iGenericIPC) throws RemoteException {
            IRemoteCallback remoteCallback = RemoteCallbackWrapper.toRemoteCallback(this);
            this.jv.c(this);
            iGenericIPC.call(this.jP.getName(), this.jO, remoteCallback);
        }

        @Override // com.amazon.identity.auth.device.dg.a
        public void onError() {
            onError(dn.bk("Got an error while calling Generic IPC central store."));
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            synchronized (this) {
                if (this.jQ) {
                    return;
                }
                this.jQ = true;
                this.jv.d(this);
                this.g.onSuccess(bundle);
            }
        }

        private b(Callback callback, Bundle bundle, Class<? extends IPCCommand> cls, dg<IGenericIPC> dgVar) {
            super(dgVar);
            this.g = callback;
            this.jO = bundle;
            this.jP = cls;
            this.jQ = false;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            synchronized (this) {
                if (this.jQ) {
                    return;
                }
                this.jQ = true;
                this.jv.d(this);
                this.g.onError(bundle);
            }
        }
    }

    public dn(Context context, String str, String str2, Integer num) {
        this(context, str, str2, num, new ec(context), D(context), new ds(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle B(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (this.jJ != null && bundle.containsKey("ipc_error_code_key")) {
            int i = bundle.getInt("ipc_error_code_key");
            bundle.putInt(this.jJ, this.jL.get(i, i));
            bundle.remove("ipc_error_code_key");
            bundle.putString(this.jK, bundle.getString("ipc_error_code_message"));
            bundle.remove("ipc_error_code_message");
        }
        return bundle;
    }

    private static synchronized dg<IGenericIPC> D(Context context) {
        synchronized (dn.class) {
            if (jI != null) {
                return jI;
            }
            dg<IGenericIPC> dgVar = new dg<IGenericIPC>(context, "com.amazon.identity.framework.GenericIPCService", ji.rF) { // from class: com.amazon.identity.auth.device.dn.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.amazon.identity.auth.device.dg
                /* renamed from: asInterface */
                public IGenericIPC a(IBinder iBinder) {
                    return IGenericIPC.Stub.asInterface(iBinder);
                }
            };
            if (!jk.gR()) {
                jI = dgVar;
            }
            return dgVar;
        }
    }

    public static Bundle bk(String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("ipc_error_code_key", 500);
        bundle.putString("ipc_error_code_message", str);
        return bundle;
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static final class c implements Callback {
        private final CountDownLatch dH;
        private Bundle gF;

        private c() {
            this.dH = new CountDownLatch(1);
        }

        private void C(Bundle bundle) {
            this.gF = bundle;
            this.dH.countDown();
        }

        public Bundle a(TimeUnit timeUnit) throws InterruptedException, TimeoutException {
            if (this.dH.await(DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS, timeUnit)) {
                return this.gF;
            }
            throw new TimeoutException();
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            C(bundle);
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            C(bundle);
        }

        /* synthetic */ c(byte b) {
            this();
        }
    }

    public void a(Class<? extends IPCCommand> cls, Bundle bundle, Callback callback) {
        in.a(cls, "ipcCommandClass");
        in.a(callback, "callback");
        Callback a2 = mq.a(mq.aE("GenericIPCSender", cls.getSimpleName()), new a(this, callback, (byte) 0));
        dg<IGenericIPC> dgVar = this.jv;
        dgVar.a(new b(a2, bundle, cls, dgVar, (byte) 0));
    }

    public dn(Context context, String str, String str2, Integer num, ec ecVar, dg<IGenericIPC> dgVar, ds dsVar) {
        this.dr = ecVar;
        this.jv = dgVar;
        this.jJ = str;
        this.jK = str2;
        this.jL = new SparseIntArray();
        if (num != null) {
            this.jL.put(500, num.intValue());
        }
        this.ia = dsVar;
        this.mContext = context;
    }

    public Bundle a(Class<? extends IPCCommand> cls, Bundle bundle) {
        in.a(cls, "ipcCommandClass");
        mv aE = mq.aE("GenericIPCSender", cls.getSimpleName());
        Bundle bundle2 = null;
        try {
            c cVar = new c((byte) 0);
            if (this.jv.b(new b(cVar, bundle, cls, this.jv, (byte) 0))) {
                try {
                    bundle2 = cVar.a(TimeUnit.MILLISECONDS);
                    if (this.ia.dA()) {
                        io.i(TAG, String.format("%s try get ipc service in direct mode for %s", this.mContext.getPackageName(), cls.getSimpleName()));
                    } else {
                        String str = TAG;
                        String.format("%s try get ipc service out of direct mode for %s", this.mContext.getPackageName(), cls.getSimpleName());
                        io.dm(str);
                    }
                } catch (InterruptedException e) {
                    io.e(TAG, String.format("MAP api call: %s get InterruptedException, probably due to caller is canceling the call.", cls.getSimpleName()), e);
                    Thread.currentThread().interrupt();
                } catch (TimeoutException e2) {
                    String str2 = TAG;
                    io.e(str2, "TimeoutException while waiting for " + cls.getSimpleName() + "; will retry over content provider", e2);
                }
            }
            if (bundle2 == null) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("command", cls.getName());
                bundle3.putBundle("parameters", bundle);
                if (this.ia.dA()) {
                    io.i(TAG, String.format("%s try get ipc provider in direct mode for %s", this.mContext.getPackageName(), cls.getSimpleName()));
                    bundle2 = a(bundle3, jH);
                } else {
                    String str3 = TAG;
                    String.format("%s try get ipc provider out of direct mode for %s", this.mContext.getPackageName(), cls.getSimpleName());
                    io.dm(str3);
                    bundle2 = a(bundle3, jG);
                }
            }
            Bundle B = B(bundle2);
            if (B.containsKey(this.jK)) {
                String str4 = TAG;
                io.e(str4, cls.getSimpleName() + " returned error " + B.getInt(this.jJ, -1) + " : " + B.getString(this.jK));
            }
            return B;
        } finally {
            aE.stop();
        }
    }

    private Bundle a(Bundle bundle, final Uri uri) {
        final String O = iu.O(bundle);
        try {
            return (Bundle) this.dr.a(uri, new dj<Bundle>() { // from class: com.amazon.identity.auth.device.dn.1
                @Override // com.amazon.identity.auth.device.dj
                /* renamed from: a */
                public Bundle b(ContentProviderClient contentProviderClient) throws RemoteException {
                    Cursor query = contentProviderClient.query(uri, dn.hZ, O, null, null);
                    try {
                        if (query != null) {
                            if (query.moveToFirst()) {
                                Bundle ds = iu.ds(ic.e(query, "bundle_value"));
                                if (ds != null) {
                                    query.close();
                                    return ds;
                                }
                                throw new RuntimeException("Corrupted value returned.");
                            }
                            throw new RuntimeException("Got an empty cursor calling Generic IPC central store.");
                        }
                        throw new RuntimeException("Got a null cursor calling Generic IPC central store.");
                    } catch (Throwable th) {
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            });
        } catch (RemoteMAPException e) {
            io.e(TAG, "Got an error while calling Generic IPC central store. This can happen in certain edge cases around a crash.", e);
            return bk("Got an error while calling Generic IPC central store.");
        }
    }
}
