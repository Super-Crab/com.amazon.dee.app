package com.amazon.alexa.devices.android;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.devices.Alexa;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.AlexaNotInstalledException;
import com.amazon.alexa.devices.AlexaNotReadyException;
import com.amazon.alexa.devices.AlexaRetryConnectionException;
import com.amazon.alexa.devices.UnrecoverableAlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.android.internal.ComponentRegistry;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public final class AndroidAlexa implements Alexa {
    private static final String API_SERVICE_INTENT_LAUNCH_ACTION = "com.amazon.launch.apiservice";
    private static final int CRASH_PERIOD = 60000;
    private static final boolean DO_RETRY = true;
    private static final int MAX_DEATH_PER_CRASH_PERIOD = 3;
    private static final String TAG = "AndroidAlexa";
    private static final boolean THROW_EXCEPTION = true;
    private static APIGatewayConnectionRetry apiGatewayConnectionRetry;
    private static AndroidAlexa mSingleton;
    private IAlexaApiGateway apiGateway;
    private final WeakReference<Context> mContext;
    private Alexa.UnrecoverableExceptionHandler unrecoverableExceptionHandler;
    private static final Version mVersion = new Version(0, 1, 0);
    private static Boolean mConnectionInFlight = false;
    private final Object lock = new Object();
    private ComponentRegistry registry = new ComponentRegistry(mVersion);
    private List<Callable<IAlexaApiGateway>> callables = new ArrayList();
    private List<WeakReference<BaseProxyClient>> proxyClients = new ArrayList();
    private int connectionTries = 0;
    private long lastConnectionTime = 0;
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.amazon.alexa.devices.android.AndroidAlexa.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            String unused = AndroidAlexa.TAG;
            AndroidAlexa.this.apiGateway = IAlexaApiGateway.Stub.asInterface(iBinder);
            String unused2 = AndroidAlexa.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully connected to API Service API Gateway: ");
            outline107.append(AndroidAlexa.this.apiGateway);
            outline107.toString();
            Boolean unused3 = AndroidAlexa.mConnectionInFlight = false;
            if (AndroidAlexa.apiGatewayConnectionRetry != null) {
                AndroidAlexa.apiGatewayConnectionRetry.quit();
                APIGatewayConnectionRetry unused4 = AndroidAlexa.apiGatewayConnectionRetry = null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - AndroidAlexa.this.lastConnectionTime > 60000) {
                AndroidAlexa.this.lastConnectionTime = currentTimeMillis;
                AndroidAlexa.this.connectionTries = 0;
            }
            synchronized (AndroidAlexa.this.proxyClients) {
                Iterator it2 = AndroidAlexa.this.proxyClients.iterator();
                while (it2.hasNext()) {
                    WeakReference weakReference = (WeakReference) it2.next();
                    if (weakReference == null) {
                        Log.wtf(AndroidAlexa.TAG, "WeakReference to ProxyClient should not be null");
                        if (AndroidAlexa.this.unrecoverableExceptionHandler != null) {
                            AndroidAlexa.this.unrecoverableExceptionHandler.handleException(new UnrecoverableAlexaException("Internal error detected!"));
                        }
                    } else {
                        BaseProxyClient baseProxyClient = (BaseProxyClient) weakReference.get();
                        if (baseProxyClient != null) {
                            try {
                                baseProxyClient.connect(AndroidAlexa.this.apiGateway, AndroidAlexa.mVersion);
                            } catch (RemoteException | AlexaException e) {
                                String str = AndroidAlexa.TAG;
                                Log.e(str, "Error reconnecting to component " + baseProxyClient, e);
                            }
                        } else {
                            it2.remove();
                            weakReference.clear();
                        }
                    }
                }
            }
            if (AndroidAlexa.this.callables.size() > 0) {
                ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(Math.min(AndroidAlexa.this.callables.size(), 5));
                Iterator it3 = AndroidAlexa.this.callables.iterator();
                while (it3.hasNext()) {
                    final Callable callable = (Callable) it3.next();
                    newFixedThreadPool.execute(new Runnable() { // from class: com.amazon.alexa.devices.android.AndroidAlexa.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                callable.call(AndroidAlexa.this.apiGateway);
                            } catch (AlexaException e2) {
                                Log.e(AndroidAlexa.TAG, "Error", e2);
                            }
                        }
                    });
                    it3.remove();
                }
                newFixedThreadPool.shutdown();
            }
            synchronized (AndroidAlexa.this.lock) {
                AndroidAlexa.this.lock.notifyAll();
            }
            try {
                iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: com.amazon.alexa.devices.android.AndroidAlexa.1.2
                    @Override // android.os.IBinder.DeathRecipient
                    public void binderDied() {
                        AndroidAlexa.access$508(AndroidAlexa.this);
                        if (AndroidAlexa.this.connectionTries <= 3) {
                            String unused5 = AndroidAlexa.TAG;
                            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("API Api Binder died, retries: ");
                            outline1072.append(AndroidAlexa.this.connectionTries);
                            outline1072.append("...Reconnecting");
                            outline1072.toString();
                            Boolean unused6 = AndroidAlexa.mConnectionInFlight = false;
                            try {
                                AndroidAlexa.this.apiGateway = null;
                                AndroidAlexa.this.getApiGateway(false, false);
                                return;
                            } catch (AlexaException e2) {
                                Log.e(AndroidAlexa.TAG, "Error reconnecting to API", e2);
                                return;
                            }
                        }
                        UnrecoverableAlexaException unrecoverableAlexaException = new UnrecoverableAlexaException("The Proteus Runtime keeps dying!");
                        if (AndroidAlexa.this.unrecoverableExceptionHandler != null) {
                            AndroidAlexa.this.unrecoverableExceptionHandler.handleException(unrecoverableAlexaException);
                        }
                        Log.e(AndroidAlexa.TAG, "Proteus Gateway died too many times. Stop retrying...", unrecoverableAlexaException);
                    }
                }, 0);
            } catch (RemoteException e2) {
                Log.e(AndroidAlexa.TAG, "linkToDeath error", e2);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            String unused = AndroidAlexa.TAG;
            AndroidAlexa.this.apiGateway = null;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class APIGatewayConnectionRetry extends HandlerThread {
        int attempts;
        AlexaException retryException;

        APIGatewayConnectionRetry(AlexaException alexaException) {
            super("API Gateway Retry Connection");
            this.attempts = 1;
            this.retryException = alexaException;
            start();
            postRetry();
        }

        void postRetry() {
            String str = AndroidAlexa.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("API Gateway Retry Connection. Attempt: ");
            outline107.append(this.attempts);
            Log.i(str, outline107.toString());
            if (AndroidAlexa.this.unrecoverableExceptionHandler != null) {
                AndroidAlexa.this.unrecoverableExceptionHandler.handleException(new AlexaRetryConnectionException(this.attempts));
            }
            long millis = TimeUnit.SECONDS.toMillis(60L);
            long millis2 = TimeUnit.SECONDS.toMillis(1L);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            int i = this.attempts;
            this.attempts = i + 1;
            new Handler(getLooper()).postDelayed(new Runnable() { // from class: com.amazon.alexa.devices.android.AndroidAlexa.APIGatewayConnectionRetry.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AndroidAlexa.this.getApiGateway(true, true);
                    } catch (AlexaException unused) {
                        APIGatewayConnectionRetry.this.postRetry();
                    }
                }
            }, Math.min(millis, timeUnit.toMillis((int) ((Math.pow(2.0d, i) - 1.0d) / 2.0d)) + millis2));
        }

        public void throwRetryException() throws AlexaException {
            throw this.retryException;
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class BaseProxyClient extends BaseProxyClientImpl {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        public BaseProxyClient() throws AlexaException {
            this(true);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @VisibleForTesting
        public BaseProxyClient(Boolean bool) throws AlexaException {
            if (bool.booleanValue()) {
                AndroidAlexa.mSingleton.registerProxyClient(this);
            }
        }
    }

    /* loaded from: classes6.dex */
    private static abstract class Callable<T> {
        private Callable() {
        }

        public abstract void call(T t) throws AlexaException;
    }

    private AndroidAlexa(Context context) throws AlexaNotInstalledException {
        this.mContext = new WeakReference<>(context.getApplicationContext());
        try {
            getApiGateway();
        } catch (AlexaNotInstalledException e) {
            throw e;
        } catch (AlexaException unused) {
        }
    }

    static /* synthetic */ int access$508(AndroidAlexa androidAlexa) {
        int i = androidAlexa.connectionTries;
        androidAlexa.connectionTries = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T callComponentGetter(Class<T> cls) throws AlexaException {
        T t = (T) this.registry.createApiComponent(cls, new Object[0]);
        if (t != null) {
            return t;
        }
        throw new AlexaException(GeneratedOutlineSupport1.outline66("Not a valid Component: ", cls));
    }

    private IAlexaApiGateway getApiGateway() throws AlexaException {
        return getApiGateway(true, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Alexa getInstance(Context context) throws AlexaException {
        if (context.getPackageManager().queryIntentServices(new Intent(API_SERVICE_INTENT_LAUNCH_ACTION), 0).size() != 0) {
            if (mSingleton == null && !mConnectionInFlight.booleanValue()) {
                synchronized (mConnectionInFlight) {
                    if (mSingleton == null) {
                        mSingleton = new AndroidAlexa(context);
                    }
                }
            } else {
                AndroidAlexa androidAlexa = mSingleton;
                if (androidAlexa != null) {
                    androidAlexa.getApiGateway(false, false);
                }
            }
            return mSingleton;
        }
        Log.i(TAG, "API service not registered. Alexa SDK not supported on this device.");
        throw new AlexaNotInstalledException();
    }

    public static final Version getVersion() {
        return mVersion;
    }

    private void setPackageForServiceAction(Context context, Intent intent) throws AlexaNotInstalledException {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 64);
        if (queryIntentServices.size() == 1) {
            ServiceInfo serviceInfo = queryIntentServices.get(0).serviceInfo;
            if (serviceInfo != null && serviceInfo.packageName != null) {
                intent.setPackage(queryIntentServices.get(0).serviceInfo.packageName);
                return;
            }
            throw new AlexaNotInstalledException();
        }
        throw new AlexaNotInstalledException();
    }

    @Override // com.amazon.alexa.devices.Alexa
    public void destroy() {
        mConnectionInFlight = false;
        apiGatewayConnectionRetry = null;
        Context context = this.mContext.get();
        if (context != null) {
            if (this.apiGateway != null) {
                context.unbindService(this.mServiceConnection);
                synchronized (this.proxyClients) {
                    Iterator<WeakReference<BaseProxyClient>> it2 = this.proxyClients.iterator();
                    while (it2.hasNext()) {
                        WeakReference<BaseProxyClient> next = it2.next();
                        BaseProxyClient baseProxyClient = next.get();
                        if (baseProxyClient != null) {
                            baseProxyClient.destroy();
                        }
                        it2.remove();
                        next.clear();
                    }
                }
                this.apiGateway = null;
                return;
            }
            Log.w(TAG, "Ignoring call to destroy because apiGateway is already null. Did you call alexa.destroy() too many times? ");
            return;
        }
        Log.w(TAG, "AndroidAlexa has lost reference to Application Context");
    }

    @Override // com.amazon.alexa.devices.Alexa
    public <T> T getComponent(final Class<T> cls, final Alexa.ComponentCallback<T> componentCallback) {
        try {
            if (getApiGateway(false, false) == null) {
                if (componentCallback != null) {
                    this.callables.add(new Callable<IAlexaApiGateway>() { // from class: com.amazon.alexa.devices.android.AndroidAlexa.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                        }

                        @Override // com.amazon.alexa.devices.android.AndroidAlexa.Callable
                        public void call(IAlexaApiGateway iAlexaApiGateway) throws AlexaException {
                            try {
                                componentCallback.onSuccess(AndroidAlexa.this.callComponentGetter(cls));
                            } catch (AlexaException e) {
                                componentCallback.onFailure(e);
                                throw e;
                            }
                        }
                    });
                } else {
                    Log.w(TAG, "Called getComponent without supplying a callback");
                }
                return null;
            }
            T t = (T) callComponentGetter(cls);
            if (componentCallback != null) {
                componentCallback.onSuccess(t);
            }
            return t;
        } catch (AlexaException e) {
            if (componentCallback != null) {
                componentCallback.onFailure(e);
            } else {
                Log.e(TAG, "Error", e);
            }
            return null;
        }
    }

    public int getVersionLevel() throws AlexaException {
        try {
            Version version = getApiGateway().getVersion();
            Version version2 = mVersion;
            if (version2.getMajor() != version.getMajor()) {
                return version2.getMajor() > version.getMajor() ? 1 : -1;
            } else if (version2.getMinor() != version.getMinor()) {
                return version2.getMinor() > version.getMinor() ? 1 : -1;
            } else if (version2.getBuild() == version.getBuild()) {
                return 0;
            } else {
                return version2.getBuild() > version.getBuild() ? 1 : -1;
            }
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.Alexa
    public boolean isConnected() {
        return this.apiGateway != null;
    }

    protected void registerProxyClient(BaseProxyClient baseProxyClient) throws AlexaException {
        try {
            synchronized (this.proxyClients) {
                baseProxyClient.connect(this.apiGateway, mVersion);
                this.proxyClients.add(new WeakReference<>(baseProxyClient));
            }
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @VisibleForTesting
    protected void resetConnectionTries() {
        this.connectionTries = 0;
    }

    @Override // com.amazon.alexa.devices.Alexa
    public void setExceptionHandler(Alexa.UnrecoverableExceptionHandler unrecoverableExceptionHandler) {
        this.unrecoverableExceptionHandler = unrecoverableExceptionHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IAlexaApiGateway getApiGateway(boolean z, boolean z2) throws AlexaException {
        Intent intent;
        APIGatewayConnectionRetry aPIGatewayConnectionRetry;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Calling getApiGateway. apiGateway: ");
        outline107.append(this.apiGateway);
        outline107.append(" - mConnectionInFlight: ");
        outline107.append(mConnectionInFlight);
        outline107.append(" - mInRetryMode: ");
        outline107.append(apiGatewayConnectionRetry != null);
        outline107.toString();
        if (!z2 && (aPIGatewayConnectionRetry = apiGatewayConnectionRetry) != null) {
            aPIGatewayConnectionRetry.throwRetryException();
        }
        if (this.apiGateway == null && !mConnectionInFlight.booleanValue()) {
            Context context = this.mContext.get();
            if (context != null) {
                try {
                    intent = new Intent(API_SERVICE_INTENT_LAUNCH_ACTION);
                    setPackageForServiceAction(context, intent);
                    mConnectionInFlight = true;
                    int i = Build.VERSION.SDK_INT;
                    context.startForegroundService(intent);
                } catch (Throwable th) {
                    mConnectionInFlight = false;
                    if (apiGatewayConnectionRetry == null) {
                        apiGatewayConnectionRetry = new APIGatewayConnectionRetry(th instanceof AlexaException ? th : new AlexaException(th));
                    }
                    apiGatewayConnectionRetry.throwRetryException();
                }
                if (!context.bindService(intent, this.mServiceConnection, 1)) {
                    throw new AlexaNotInstalledException();
                }
                try {
                    if (Looper.myLooper() != Looper.getMainLooper()) {
                        synchronized (this.lock) {
                            this.lock.wait(1000L);
                        }
                    }
                } catch (InterruptedException e) {
                    Log.e(TAG, "error", e);
                }
            } else {
                throw new AlexaNotReadyException();
            }
        }
        if (this.apiGateway == null && z) {
            throw new AlexaNotReadyException();
        }
        return this.apiGateway;
    }
}
