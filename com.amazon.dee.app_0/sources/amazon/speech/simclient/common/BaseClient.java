package amazon.speech.simclient.common;

import amazon.speech.simclient.common.ServiceConnectionManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public abstract class BaseClient<T extends IInterface> {
    private static final String TAG = GeneratedOutlineSupport1.outline39(BaseClient.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private BaseClient<T>.BaseClientServiceConnection mConnection;
    private T mProxy;
    private volatile T mService;
    private final Class<T> mServiceClass;
    private final ServiceConnectionManager mServiceConnectionManager;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public abstract class BaseClientServiceConnection implements ServiceConnection, InvocationHandler {
        private final ArrayList<PendingInvocation> mPendingInvocations = new ArrayList<>();

        /* JADX INFO: Access modifiers changed from: protected */
        public BaseClientServiceConnection() {
        }

        private Object invokeOnService(Object obj, Method method, Object[] objArr) throws RemoteException {
            try {
                return method.invoke(obj, objArr);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to proxy pending invocation " + method, e);
            } catch (InvocationTargetException e2) {
                Throwable targetException = e2.getTargetException();
                if (targetException instanceof RemoteException) {
                    throw ((RemoteException) targetException);
                }
                throw new RuntimeException("Failed to proxy pending invocation " + method, e2);
            }
        }

        /* renamed from: convertIBinderToService */
        protected abstract T mo49convertIBinderToService(IBinder iBinder);

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            synchronized (BaseClient.this) {
                if (!BaseClient.this.isTornDown()) {
                    if (BaseClient.this.mService != null) {
                        return invokeOnService(BaseClient.this.mService, method, objArr);
                    }
                    Class<?> returnType = method.getReturnType();
                    if (!Void.TYPE.equals(returnType) && !Void.TYPE.equals(returnType)) {
                        throw new ReturnValueMethodInvocationException();
                    }
                    this.mPendingInvocations.add(new PendingInvocation(method, objArr));
                    return null;
                }
                throw new IllegalStateException("Cannot invoke on a torn down client");
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (BaseClient.this) {
                BaseClient.this.mService = mo49convertIBinderToService(iBinder);
                Iterator<PendingInvocation> it2 = this.mPendingInvocations.iterator();
                while (it2.hasNext()) {
                    PendingInvocation next = it2.next();
                    try {
                        invokeOnService(BaseClient.this.mService, next.mMethod, next.mArgs);
                    } catch (RemoteException e) {
                        Log.e(BaseClient.TAG, "RemoteException invoking pending method", e);
                    }
                }
                this.mPendingInvocations.clear();
                BaseClient.this.mServiceConnectionManager.onServiceConnected();
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            BaseClient.this.mService = null;
            BaseClient.this.mServiceConnectionManager.onServiceDisconnected();
        }
    }

    /* loaded from: classes.dex */
    private static class PendingInvocation {
        public final Object[] mArgs;
        public final Method mMethod;

        public PendingInvocation(Method method, Object[] objArr) {
            this.mMethod = method;
            this.mArgs = objArr;
        }
    }

    /* loaded from: classes.dex */
    public static class ReturnValueMethodInvocationException extends RuntimeException {
        public ReturnValueMethodInvocationException() {
            super("Method which returns a value called on the proxy service!");
        }
    }

    public BaseClient(Class<T> cls, Context context, Intent intent, String str) {
        this(cls, context, intent, str, new SyncServiceConnectionManager());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean isTornDown() {
        return this.mConnection == null;
    }

    public static boolean serviceExists(Context context, Intent intent, String str) {
        return ServiceResolver.resolveBindIntent(context, intent, str) != null;
    }

    protected abstract BaseClient<T>.BaseClientServiceConnection createServiceConnection();

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized T getService() {
        if (this.mConnection != null) {
            if (this.mProxy == null) {
                this.mProxy = (T) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{this.mServiceClass}, this.mConnection);
            }
        } else {
            Log.e(TAG, "Cannot retrieve service, already torn down");
            throw new RuntimeException("Client already torn down!");
        }
        return this.mProxy;
    }

    public ServiceConnectionManager getServiceConnectionManager() {
        return this.mServiceConnectionManager;
    }

    public boolean isRealServiceConnected() {
        return this.mService != null;
    }

    public synchronized void teardown() {
        if (this.mConnection == null) {
            Log.e(TAG, "Already torn down, ignoring");
            return;
        }
        this.mServiceConnectionManager.unbindService();
        this.mConnection = null;
    }

    public BaseClient(Class<T> cls, Context context, Intent intent, String str, ServiceConnectionManager serviceConnectionManager) {
        this(cls, context, intent, null, str, serviceConnectionManager);
    }

    public BaseClient(Class<T> cls, final Context context, Intent intent, final Intent intent2, final String str, ServiceConnectionManager serviceConnectionManager) {
        this.mServiceClass = cls;
        this.mServiceConnectionManager = serviceConnectionManager;
        this.mConnection = createServiceConnection();
        this.mServiceConnectionManager.addCallback(new ServiceConnectionManager.Callback() { // from class: amazon.speech.simclient.common.BaseClient.1
            @Override // amazon.speech.simclient.common.ServiceConnectionManager.Callback
            public void onBind(boolean z) {
                BaseClient.this.mServiceConnectionManager.removeCallback(this);
                if (!z) {
                    if (intent2 != null) {
                        Log.i(BaseClient.TAG, "Binding to preferred intent failed. Now trying to bind to backup intent");
                        BaseClient.this.mServiceConnectionManager.addCallback(new ServiceConnectionManager.Callback() { // from class: amazon.speech.simclient.common.BaseClient.1.1
                            @Override // amazon.speech.simclient.common.ServiceConnectionManager.Callback
                            public void onBind(boolean z2) {
                                BaseClient.this.mServiceConnectionManager.removeCallback(this);
                                if (!z2) {
                                    BaseClient.this.teardown();
                                    Log.wtf(BaseClient.TAG, "Binding to backup intent failed. Tearing down the client.");
                                }
                            }
                        });
                        BaseClient.this.mServiceConnectionManager.bindService(context, BaseClient.this.mConnection, intent2, str);
                        return;
                    }
                    BaseClient.this.teardown();
                    Log.wtf(BaseClient.TAG, String.format("Binding for (%s) in package (%s) was unsuccessful. This probably means the service does not exist, or the client attempting to bind does not have the correct permissions", BaseClient.this.getClass().getSimpleName(), context.getPackageName()));
                }
            }
        });
        this.mServiceConnectionManager.bindService(context, this.mConnection, intent, str);
    }
}
