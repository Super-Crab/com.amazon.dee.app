package com.amazon.communication;

import amazon.communication.ServiceConnectedHandler;
import amazon.communication.ServiceConnectivityListener;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.dp.logger.DPLogger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes12.dex */
public class AndroidTCommServiceConnection implements ServiceConnection {
    protected static final int BIND_SERVICE_WAIT_MS = 10000;
    public static final String TCOMM_INTENT_ACTION = "com.amazon.communication.TCOMM";
    private static final String TCOMM_PROCESS_NAME = "com.amazon.tcomm";
    public static final String TCOMM_SERVICE_CLASS = "com.amazon.communication.AndroidTCommService";
    public static final String TCOMM_SERVICE_PACKAGE = "com.amazon.tcomm";
    private static final DPLogger log = new DPLogger("TComm.AndroidTCommServiceConnection");
    protected final Context mContext;
    protected final Intent mTCommIntent;
    protected IBinder mTCommService;
    protected State mState = State.UNBOUND;
    protected final Lock mServiceConnectedLock = new ReentrantLock();
    protected final Condition mServiceConnectedCondition = this.mServiceConnectedLock.newCondition();
    protected final List<ServiceConnectedHandler> mServiceConnectedHandlers = new CopyOnWriteArrayList();
    protected final List<ServiceConnectivityListener> mServiceConnectivityListeners = new CopyOnWriteArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public enum State {
        UNBOUND,
        BINDING,
        BOUND
    }

    public AndroidTCommServiceConnection(Context context) {
        this.mContext = context;
        this.mTCommIntent = getTCommServiceIntent(this.mContext);
    }

    private static Intent getTCommServiceIntent(Context context) {
        Intent intent = new Intent(TCOMM_INTENT_ACTION);
        intent.setClassName(context, TCOMM_SERVICE_CLASS);
        if (context.getPackageManager().queryIntentServices(intent, 0).isEmpty()) {
            intent.setClassName("com.amazon.tcomm", TCOMM_SERVICE_CLASS);
        }
        return intent;
    }

    private void notifyServiceConnected() {
        log.debug("notifyServiceConnected", "notifying listeners", "mServiceConnectivityListeners.size()", Integer.valueOf(this.mServiceConnectivityListeners.size()));
        for (ServiceConnectivityListener serviceConnectivityListener : this.mServiceConnectivityListeners) {
            serviceConnectivityListener.onServiceConnected();
        }
    }

    private void notifyServiceConnectedHandlers() {
        log.debug("notifyServiceConnectedHandlers", "notifying handlers", "mServiceConnectedHandlers.size()", Integer.valueOf(this.mServiceConnectedHandlers.size()));
        for (ServiceConnectedHandler serviceConnectedHandler : this.mServiceConnectedHandlers) {
            serviceConnectedHandler.onServiceConnected();
        }
    }

    private void notifyServiceDisconnected() {
        log.debug("notifyServiceDisconnected", "notifying listeners", "mServiceConnectivityListeners.size()", Integer.valueOf(this.mServiceConnectivityListeners.size()));
        for (ServiceConnectivityListener serviceConnectivityListener : this.mServiceConnectivityListeners) {
            serviceConnectivityListener.onServiceDisconnected();
        }
    }

    public void bindTCommService() {
        this.mServiceConnectedLock.lock();
        try {
            if (this.mState != State.UNBOUND) {
                log.info("bindTCommService", "state is not UNBOUND, doing nothing", "current state", this.mState);
                return;
            }
            this.mContext.startService(this.mTCommIntent);
            boolean bindService = this.mContext.bindService(this.mTCommIntent, this, 4);
            if (bindService) {
                this.mState = State.BINDING;
            }
            log.debug("bindTCommService", "bound service", "bindResult", Boolean.valueOf(bindService));
        } finally {
            this.mServiceConnectedLock.unlock();
        }
    }

    public void deregisterServiceConnectivityListener(ServiceConnectivityListener serviceConnectivityListener) {
        if (serviceConnectivityListener != null) {
            this.mServiceConnectedLock.lock();
            try {
                this.mServiceConnectivityListeners.remove(serviceConnectivityListener);
                return;
            } finally {
                this.mServiceConnectedLock.unlock();
            }
        }
        throw new IllegalArgumentException("listener must not be null");
    }

    public IBinder getBinder() throws amazon.communication.TCommServiceDownException {
        this.mServiceConnectedLock.lock();
        try {
            try {
                log.debug("getService", "getting service binder", "state", this.mState);
                if (this.mState == State.UNBOUND) {
                    bindTCommService();
                }
                if (this.mState == State.BINDING) {
                    log.info("getService", "state is BINDING, waiting", new Object[0]);
                    long currentTimeMillis = GlobalTimeSource.INSTANCE.currentTimeMillis();
                    if (!this.mServiceConnectedCondition.await(10000L, TimeUnit.MILLISECONDS)) {
                        log.debug("getService", "timed out waiting for service binder", "BIND_SERVICE_WAIT_MS", 10000);
                        if (!"user".equals(Build.TYPE)) {
                            new StackDumper(this.mContext).dumpStack("com.amazon.tcomm");
                        }
                        throw new amazon.communication.TCommServiceDownException("Timed out waiting for service binder after 10000 (ms)");
                    }
                    log.debug("getService", "finished waiting for service to bind", "elapsed", Long.valueOf(GlobalTimeSource.INSTANCE.currentTimeMillis() - currentTimeMillis));
                }
                if (this.mState == State.BOUND) {
                    return this.mTCommService;
                }
                throw new IllegalStateException("TComm service is not bound. It's possible the service is shut down");
            } catch (InterruptedException e) {
                log.debug("getService", "interrupted waiting for service binder", new Object[0]);
                throw new amazon.communication.TCommServiceDownException("Interrupted waiting for service binder", e);
            }
        } finally {
            this.mServiceConnectedLock.unlock();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mServiceConnectedLock.lock();
        try {
            log.debug("onServiceConnected", "service connected", "componentName", componentName, "state", this.mState);
            if (this.mState != State.BINDING && this.mState != State.UNBOUND) {
                log.debug("onServiceConnected", "state is not BINDING or UNBOUND, doing nothing", "state", this.mState);
            }
            this.mTCommService = iBinder;
            this.mState = State.BOUND;
            notifyServiceConnectedHandlers();
            notifyServiceConnected();
            this.mServiceConnectedCondition.signalAll();
        } finally {
            this.mServiceConnectedLock.unlock();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.mServiceConnectedLock.lock();
        try {
            log.debug(MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED, "service disconnected", "componentName", componentName, "state", this.mState);
            if (this.mState != State.UNBOUND && this.mState != State.BINDING) {
                this.mTCommService = null;
                this.mState = State.BINDING;
                notifyServiceDisconnected();
            } else {
                log.debug(MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED, "state is BINDING or UNBOUND, doing nothing", new Object[0]);
            }
        } finally {
            this.mServiceConnectedLock.unlock();
        }
    }

    @Deprecated
    public void registerServiceConnectedHandler(ServiceConnectedHandler serviceConnectedHandler) {
        if (serviceConnectedHandler != null) {
            this.mServiceConnectedLock.lock();
            try {
                if (!this.mServiceConnectedHandlers.contains(serviceConnectedHandler)) {
                    this.mServiceConnectedHandlers.add(serviceConnectedHandler);
                }
                if (this.mState == State.BOUND) {
                    serviceConnectedHandler.onServiceConnected();
                }
                return;
            } finally {
                this.mServiceConnectedLock.unlock();
            }
        }
        throw new IllegalArgumentException("handler must not be null");
    }

    public void registerServiceConnectivityListener(ServiceConnectivityListener serviceConnectivityListener) {
        if (serviceConnectivityListener != null) {
            this.mServiceConnectedLock.lock();
            try {
                this.mServiceConnectivityListeners.add(serviceConnectivityListener);
                if (this.mState == State.BOUND) {
                    serviceConnectivityListener.onServiceConnected();
                }
                return;
            } finally {
                this.mServiceConnectedLock.unlock();
            }
        }
        throw new IllegalArgumentException("listener must not be null");
    }

    public void unbindTCommService() {
        this.mServiceConnectedLock.lock();
        try {
            try {
                if (this.mState != State.UNBOUND) {
                    this.mContext.unbindService(this);
                    log.debug("unbindTCommService", "unbound service", new Object[0]);
                    this.mState = State.UNBOUND;
                    this.mTCommService = null;
                } else {
                    log.info("unbindTCommService", "state is not BOUND, doing nothing", "current state", this.mState);
                }
            } catch (IllegalArgumentException e) {
                log.warn("unbindTCommService", "TCommService is not bound, so calling unbindService is illegal", ADMRegistrationConstants.CALL_EXCEPTION, e);
            }
            this.mServiceConnectedLock.unlock();
            this.mServiceConnectedHandlers.clear();
        } catch (Throwable th) {
            this.mServiceConnectedLock.unlock();
            throw th;
        }
    }
}
