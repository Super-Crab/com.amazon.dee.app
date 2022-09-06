package com.amazon.alexa.auth.map;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.auth.AbstractAccountManager;
import com.amazon.alexa.auth.AccessToken;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.auth.map.AuthorizationServiceProxy;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes6.dex */
public class MAPAccountManager extends AbstractAccountManager implements ServiceConnection {
    private static final String TAG = MAPAccountManager.class.getSimpleName();
    private AuthorizationServiceProxy authorizationServiceProxy;
    private long connectToServiceTime;
    private final Context context;
    private volatile boolean isConnected;
    private volatile boolean isConnecting;
    private volatile boolean isTornDown;
    private final Queue<Runnable> runnableQueue;
    private final ComponentName serviceComponentName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MAPAccountManager(Context context) {
        super(context);
        this.context = context.getApplicationContext();
        this.serviceComponentName = new ComponentName(context, MAPProxyService.class);
        this.runnableQueue = new ConcurrentLinkedQueue();
        connectToService();
    }

    private void connectToService() {
        if (this.isConnected || this.isConnecting) {
            return;
        }
        this.isConnecting = true;
        Intent intent = new Intent();
        intent.setClass(this.context, MAPProxyService.class);
        this.connectToServiceTime = SystemClock.elapsedRealtime();
        if (this.context.bindService(intent, this, 65)) {
            return;
        }
        Log.e(TAG, "Error connecting to authorization service. Unbinding");
        this.context.unbindService(this);
        this.isConnecting = false;
    }

    private void disconnectFromService() {
        if (this.isConnected || this.isConnecting) {
            this.context.unbindService(this);
            this.isConnected = false;
            this.isConnecting = false;
        }
    }

    private void executePendingTasks() {
        while (true) {
            Runnable poll = this.runnableQueue.poll();
            if (poll != null) {
                poll.run();
            } else {
                return;
            }
        }
    }

    private String getDirectedIDInternal() {
        try {
            return this.authorizationServiceProxy.getDirectedID();
        } catch (RemoteException unused) {
            return null;
        }
    }

    private String getMarketplaceInternal() {
        try {
            return this.authorizationServiceProxy.getMarketplace();
        } catch (RemoteException unused) {
            return null;
        }
    }

    private String getTokenInternal() {
        try {
            return this.authorizationServiceProxy.getToken();
        } catch (RemoteException unused) {
            return null;
        }
    }

    private void handleServiceDisappeared(ComponentName componentName) {
        synchronized (this) {
            disconnectFromService();
            this.isConnected = false;
            this.isConnecting = false;
            this.authorizationServiceProxy = null;
            if (!this.isTornDown) {
                connectToService();
            }
        }
    }

    @Override // com.amazon.alexa.auth.AccountManager
    public synchronized void clearCache() {
        if (!this.isConnected) {
            return;
        }
        this.isConnected = false;
        try {
            this.authorizationServiceProxy.killService();
        } catch (RemoteException e) {
            Log.e(TAG, "Remote exception", e);
        }
    }

    @VisibleForTesting
    AuthorizationServiceProxy createProxy(IBinder iBinder) {
        return AuthorizationServiceProxy.Stub.asInterface(iBinder);
    }

    @Override // com.amazon.alexa.auth.AccountManager
    public synchronized void getDirectedID(@NonNull final AccountManager.ResultCallback<String> resultCallback) {
        if (this.isConnected) {
            String directedIDInternal = getDirectedIDInternal();
            if (directedIDInternal == null) {
                clearCache();
            } else {
                resultCallback.onResult(directedIDInternal);
                return;
            }
        } else {
            connectToService();
        }
        this.runnableQueue.add(new Runnable() { // from class: com.amazon.alexa.auth.map.MAPAccountManager.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    resultCallback.onResult(MAPAccountManager.this.authorizationServiceProxy.getDirectedID());
                } catch (RemoteException e) {
                    resultCallback.onError(e);
                }
            }
        });
    }

    @Override // com.amazon.alexa.auth.AccountManager
    public synchronized void getMarketplace(@NonNull final AccountManager.ResultCallback<String> resultCallback) {
        if (this.isConnected) {
            String marketplaceInternal = getMarketplaceInternal();
            if (marketplaceInternal == null) {
                clearCache();
            } else {
                resultCallback.onResult(marketplaceInternal);
                return;
            }
        } else {
            connectToService();
        }
        this.runnableQueue.add(new Runnable() { // from class: com.amazon.alexa.auth.map.MAPAccountManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    resultCallback.onResult(MAPAccountManager.this.authorizationServiceProxy.getMarketplace());
                } catch (RemoteException e) {
                    resultCallback.onError(e);
                }
            }
        });
    }

    @Override // com.amazon.alexa.auth.AccountManager
    public synchronized void getToken(@NonNull final AccountManager.ResultCallback<AccessToken> resultCallback) {
        if (this.isConnected) {
            String tokenInternal = getTokenInternal();
            if (tokenInternal == null) {
                clearCache();
            } else {
                resultCallback.onResult(AccessToken.create(tokenInternal));
                return;
            }
        } else {
            connectToService();
        }
        this.runnableQueue.add(new Runnable() { // from class: com.amazon.alexa.auth.map.MAPAccountManager.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    resultCallback.onResult(AccessToken.create(MAPAccountManager.this.authorizationServiceProxy.getToken()));
                } catch (RemoteException e) {
                    resultCallback.onError(e);
                }
            }
        });
    }

    @Override // com.amazon.alexa.auth.AccountManager
    public synchronized void isLoggedIn(@NonNull final AccountManager.ResultCallback<Boolean> resultCallback) {
        if (this.isConnected) {
            try {
                if (!this.authorizationServiceProxy.isLoggedIn()) {
                    clearCache();
                } else {
                    resultCallback.onResult(true);
                    return;
                }
            } catch (RemoteException unused) {
                clearCache();
            }
        } else {
            connectToService();
        }
        this.runnableQueue.add(new Runnable() { // from class: com.amazon.alexa.auth.map.MAPAccountManager.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    resultCallback.onResult(Boolean.valueOf(MAPAccountManager.this.authorizationServiceProxy.isLoggedIn()));
                } catch (RemoteException e) {
                    resultCallback.onError(e);
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName componentName) {
        if (this.serviceComponentName.equals(componentName)) {
            Log.w(TAG, "AuthorizationService binding died");
            handleServiceDisappeared(componentName);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (this.serviceComponentName.equals(componentName)) {
            synchronized (this) {
                this.isConnected = true;
                this.isConnecting = false;
                this.authorizationServiceProxy = createProxy(iBinder);
                String str = "Authorization Service connected in " + (SystemClock.elapsedRealtime() - this.connectToServiceTime) + "ms";
                if (this.isTornDown) {
                    disconnectFromService();
                } else {
                    executePendingTasks();
                }
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        if (this.serviceComponentName.equals(componentName)) {
            Log.w(TAG, "AuthorizationService disconnected");
            handleServiceDisappeared(componentName);
        }
    }

    @Override // com.amazon.alexa.auth.AbstractAccountManager, com.amazon.alexa.auth.AccountManager
    public synchronized void teardown() {
        super.teardown();
        this.isTornDown = true;
        this.runnableQueue.clear();
        disconnectFromService();
    }
}
