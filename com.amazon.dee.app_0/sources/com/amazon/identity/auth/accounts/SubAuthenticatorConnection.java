package com.amazon.identity.auth.accounts;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.amazon.dcp.sso.ISubAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
import com.amazon.identity.auth.device.aj;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.utils.ResourceHelper;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class SubAuthenticatorConnection {
    private static final String TAG = "com.amazon.identity.auth.accounts.SubAuthenticatorConnection";
    private static final long cK = TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS);
    private final aj cL;
    private final Context cM;
    private CurrentState cO;
    private b cP;
    private ISubAuthenticator cQ;
    private boolean cR;
    private ServiceConnection cN = new ServiceConnection() { // from class: com.amazon.identity.auth.accounts.SubAuthenticatorConnection.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            b bVar;
            SubAuthenticatorConnection.this.cR = true;
            synchronized (SubAuthenticatorConnection.this.cS) {
                SubAuthenticatorConnection.this.cO = CurrentState.Bound;
                SubAuthenticatorConnection.this.cQ = ISubAuthenticator.Stub.asInterface(iBinder);
                bVar = SubAuthenticatorConnection.this.cP;
                String str = SubAuthenticatorConnection.TAG;
                String.format("Connected to SubAuthenticator in package %s.", SubAuthenticatorConnection.this.cL.packageName);
                io.dm(str);
            }
            if (bVar != null) {
                bVar.L();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            b bVar;
            SubAuthenticatorConnection.this.cN = null;
            synchronized (SubAuthenticatorConnection.this.cS) {
                SubAuthenticatorConnection.this.cO = CurrentState.Unbound;
                bVar = SubAuthenticatorConnection.this.cP;
                SubAuthenticatorConnection.this.cQ = null;
                String str = SubAuthenticatorConnection.TAG;
                String.format("Disconnected from SubAuthenticator in package %s.", SubAuthenticatorConnection.this.cL.packageName);
                io.dm(str);
            }
            if (bVar != null) {
                bVar.a(SubAuthenticatorConnection.this);
            }
        }
    };
    private Object cS = new Object[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum CurrentState {
        Unbound,
        Binding,
        Bound
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        void M();

        void b(int i, String str);
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface b {
        void K();

        void L();

        void a(SubAuthenticatorConnection subAuthenticatorConnection);
    }

    public SubAuthenticatorConnection(aj ajVar, Context context) {
        if (ajVar != null) {
            if (context != null) {
                this.cL = ajVar;
                this.cM = context;
                this.cO = CurrentState.Unbound;
                this.cR = false;
                return;
            }
            throw new IllegalArgumentException("Context cannot be null");
        }
        throw new IllegalArgumentException("SubAuthenticatorDescription cannot be null");
    }

    public void closeConnection() {
        synchronized (this.cS) {
            if (this.cO != CurrentState.Bound) {
                io.e(TAG, "Cannot close the connection because it was not connected");
                return;
            }
            if (this.cN != null) {
                try {
                    this.cM.unbindService(this.cN);
                } catch (IllegalArgumentException unused) {
                    io.w(TAG, String.format("IllegalArgumentException is received during unbinding from Subauthenticator package, Ignored.", new Object[0]));
                }
                this.cN = null;
            }
            this.cO = CurrentState.Unbound;
        }
    }

    public String getPackageName() {
        return this.cL.packageName;
    }

    public boolean a(b bVar) {
        synchronized (this.cS) {
            if (this.cO == CurrentState.Unbound) {
                if (this.cN != null) {
                    this.cO = CurrentState.Binding;
                    this.cP = bVar;
                    Intent intent = new Intent("com.amazon.dcp.sso.AccountSubAuthenticator");
                    intent.setComponent(this.cL.getComponentName());
                    boolean a2 = a(intent, this.cN);
                    if (!a2) {
                        this.cO = CurrentState.Unbound;
                        io.e(TAG, String.format("Application tried to bind to SubAuthenticator Service and failed.", new Object[0]));
                        return false;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.identity.auth.accounts.SubAuthenticatorConnection.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (SubAuthenticatorConnection.this.cR) {
                                return;
                            }
                            io.e(SubAuthenticatorConnection.TAG, String.format("Application tried to bind to SubAuthenticator but Service timed out.", new Object[0]));
                            SubAuthenticatorConnection.this.cP.K();
                            SubAuthenticatorConnection.this.closeConnection();
                        }
                    }, cK);
                    return a2;
                }
                throw new IllegalStateException("Attempted to reuse a SubAuthenticatorConnection.  openConnection can only be executed once.");
            }
            throw new IllegalStateException("Cannot open a connection to the service because we are currently connecting or have already connected to the service.");
        }
    }

    private boolean a(Intent intent, ServiceConnection serviceConnection) {
        try {
            return this.cM.bindService(intent, serviceConnection, 5);
        } catch (SecurityException e) {
            io.e(TAG, String.format("Unable to talk to package because of SecurityException : %s", e.getMessage()));
            return false;
        }
    }

    public void a(Account account, final a aVar) {
        CurrentState currentState;
        synchronized (this.cS) {
            currentState = this.cO;
        }
        if (currentState != CurrentState.Bound) {
            io.e(TAG, "Cannot deregister the Sub Authenticator until the connection has been opened");
            aVar.b(8, "In bad state. Cannot deregister");
            return;
        }
        ISubAuthenticatorResponse.Stub stub = new ISubAuthenticatorResponse.Stub() { // from class: com.amazon.identity.auth.accounts.SubAuthenticatorConnection.3
            @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
            public void onError(int i, String str) {
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.b(i, str);
                }
            }

            @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
            public void onResult(Bundle bundle) {
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.M();
                }
            }
        };
        try {
            String str = TAG;
            StringBuilder sb = new StringBuilder("Calling ");
            sb.append(this.cL.packageName);
            sb.append(" to start deregistration");
            io.dm(str);
            this.cQ.getAccountRemovalAllowed(stub, account.type, account.name);
        } catch (RemoteException unused) {
            a(aVar);
        } catch (RuntimeException e) {
            io.e(TAG, String.format("SubAuthenticator package caused run time exception in it's getAccountRemovalAllowed implementation. Error Message: %s", e.getMessage()));
            a(aVar);
        }
    }

    private void a(a aVar) {
        if (aVar == null) {
            return;
        }
        aVar.b(-1, String.format(ResourceHelper.y(this.cM, "ErrorConnectingToSubAuth"), this.cL.packageName));
    }
}
