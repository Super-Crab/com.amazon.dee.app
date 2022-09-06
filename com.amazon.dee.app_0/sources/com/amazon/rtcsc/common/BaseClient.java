package com.amazon.rtcsc.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.rtcsc.common.ServiceConnectionManager;
import com.amazon.rtcsc.utils.RtcscLogger;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes13.dex */
public abstract class BaseClient<T> {
    private T mProxy;
    private volatile T mService;
    private final Class<T> mServiceClass;
    private final ServiceConnectionManager mServiceConnectionManager;
    private RtcscLogger mLog = RtcscLogger.getLogger(BaseClient.class);
    private BaseClient<T>.BaseClientServiceConnection mConnection = createServiceConnection();

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes13.dex */
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
        protected abstract T mo4497convertIBinderToService(IBinder iBinder);

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            synchronized (BaseClient.this) {
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
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (BaseClient.this) {
                BaseClient.this.mService = mo4497convertIBinderToService(iBinder);
                int intValue = BaseClient.this.queryAPIVersion(BaseClient.this.mService).intValue();
                Iterator<PendingInvocation> it2 = this.mPendingInvocations.iterator();
                while (it2.hasNext()) {
                    PendingInvocation next = it2.next();
                    try {
                        invokeOnService(BaseClient.this.mService, next.mMethod, next.mArgs);
                    } catch (RemoteException unused) {
                        BaseClient.this.mLog.e("RemoteException invoking pending method");
                    }
                }
                this.mPendingInvocations.clear();
                BaseClient.this.mServiceConnectionManager.onServiceConnected(intValue);
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            BaseClient.this.mService = null;
            BaseClient.this.mServiceConnectionManager.onServiceDisconnected();
        }
    }

    /* loaded from: classes13.dex */
    private static class PendingInvocation {
        public final Object[] mArgs;
        public final Method mMethod;

        public PendingInvocation(Method method, Object[] objArr) {
            this.mMethod = method;
            this.mArgs = objArr;
        }
    }

    /* loaded from: classes13.dex */
    public static class ReturnValueMethodInvocationException extends RuntimeException {
        public ReturnValueMethodInvocationException() {
            super("Method which returns a value called on the proxy service!");
        }
    }

    public BaseClient(Class<T> cls, final Context context, Intent intent, String str, ServiceConnectionManager serviceConnectionManager) {
        this.mServiceClass = cls;
        this.mServiceConnectionManager = serviceConnectionManager;
        this.mServiceConnectionManager.addCallback(new ServiceConnectionManager.Callback() { // from class: com.amazon.rtcsc.common.BaseClient.1
            @Override // com.amazon.rtcsc.common.ServiceConnectionManager.Callback
            public void onBind(boolean z) {
                BaseClient.this.mServiceConnectionManager.removeCallback(this);
                if (z) {
                    return;
                }
                BaseClient.this.teardown();
                throw new IllegalStateException(String.format("Binding for (%s) in package (%s) was unsuccessful. This probably means the service does not exist, or the client attempting to bind does not have the correct permissions", BaseClient.this.getClass().getSimpleName(), context.getPackageName()));
            }
        });
        this.mServiceConnectionManager.bindService(context, this.mConnection, intent, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer queryAPIVersion(T t) {
        Class<?> cls = t.getClass();
        String simpleName = cls.getSimpleName();
        try {
            try {
                int intValue = ((Integer) cls.getMethod("getAPIVersion", new Class[0]).invoke(t, new Object[0])).intValue();
                this.mLog.i(String.format("Service (%s) version (%s)", simpleName, Integer.valueOf(intValue)));
                return Integer.valueOf(intValue);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Unable to pull version from (%s)", simpleName), e);
            }
        } catch (NoSuchMethodException unused) {
            this.mLog.w(String.format("Service (%s) does not implement (%s)", simpleName, "getAPIVersion"));
            return 1;
        }
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
            this.mLog.e("Cannot retrieve service, already torn down");
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
            this.mLog.e("Already torn down, ignoring");
            return;
        }
        this.mServiceConnectionManager.unbindService();
        this.mConnection = null;
        this.mLog.i("Client torn down successfully.");
    }
}
