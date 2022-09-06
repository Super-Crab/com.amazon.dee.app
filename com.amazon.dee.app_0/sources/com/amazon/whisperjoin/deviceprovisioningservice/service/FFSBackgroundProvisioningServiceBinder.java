package com.amazon.whisperjoin.deviceprovisioningservice.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface;
import com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback;
import com.amazon.whisperjoin.deviceprovisioningservice.di.DaggerWrapper;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.Locale;
import javax.inject.Inject;
/* loaded from: classes13.dex */
public class FFSBackgroundProvisioningServiceBinder extends Service {
    private static final String PREF_NAME = "FFSBackgroundProvisioningServiceBinder";
    private static final String PREF_SHUTDOWN_BY_CLIENT = "ShutdownByClient";
    private static final String TAG = FFSBackgroundProvisioningServiceBinder.class.getSimpleName();
    private final ClientEventDispatch mEventDispatch = new ClientEventDispatch();
    private FFSProvisioningService mFFSProvisioningService = null;
    @Inject
    MapAuthenticationProvider mMapAuthenticationProvider;
    private ProvisioningServiceConfiguration mProvisioningServiceConfiguration;
    private ServiceInterface mServiceInterface;
    @Inject
    SharedPreferencesProvider mSharedPreferencesProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class ClientEventDispatch extends ProvisioningWorkflowEventCallback.Stub {
        private WeakReference<ProvisioningWorkflowEventCallback> mListener;

        ClientEventDispatch() {
            setListener(null);
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
        public void onComplete() {
            if (this.mListener.get() != null) {
                try {
                    this.mListener.get().onComplete();
                } catch (RemoteException e) {
                    WJLog.e(FFSBackgroundProvisioningServiceBinder.TAG, "RemoteException Occurred", e);
                }
            }
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
        public void onError(String str, String str2, String str3) throws RemoteException {
            if (this.mListener.get() != null) {
                this.mListener.get().onError(str, str2, str3);
            }
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.ProvisioningWorkflowEventCallback
        public void onNext(String str, String str2) throws RemoteException {
            if (this.mListener.get() != null) {
                this.mListener.get().onNext(str, str2);
            }
        }

        public void setListener(ProvisioningWorkflowEventCallback provisioningWorkflowEventCallback) {
            this.mListener = new WeakReference<>(provisioningWorkflowEventCallback);
        }
    }

    /* loaded from: classes13.dex */
    private static class ServiceInterface extends DeviceProvisioningServiceInterface.Stub {
        final WeakReference<FFSBackgroundProvisioningServiceBinder> mBinderReference;
        private final String mLogPrefix = DeviceProvisioningServiceInterface.class.getSimpleName() + " - ";

        public ServiceInterface(FFSBackgroundProvisioningServiceBinder fFSBackgroundProvisioningServiceBinder) {
            this.mBinderReference = new WeakReference<>(fFSBackgroundProvisioningServiceBinder);
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
        public boolean addCallback(ProvisioningWorkflowEventCallback provisioningWorkflowEventCallback) throws RemoteException {
            if (this.mBinderReference.get() == null) {
                return true;
            }
            try {
                String str = FFSBackgroundProvisioningServiceBinder.TAG;
                WJLog.d(str, this.mLogPrefix + "addCallback");
                this.mBinderReference.get().mEventDispatch.setListener(provisioningWorkflowEventCallback);
                return true;
            } catch (Throwable th) {
                this.mBinderReference.get().logAIDLException(th, "addCallback");
                throw th;
            }
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
        public boolean isActive() throws RemoteException {
            if (this.mBinderReference.get() == null) {
                return false;
            }
            try {
                String str = FFSBackgroundProvisioningServiceBinder.TAG;
                WJLog.d(str, this.mLogPrefix + "isServiceRunning");
                if (this.mBinderReference.get().mFFSProvisioningService == null) {
                    return false;
                }
                return this.mBinderReference.get().mFFSProvisioningService.isServiceRunning();
            } catch (Throwable th) {
                this.mBinderReference.get().logAIDLException(th, "isActive");
                throw th;
            }
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
        public boolean removeCallback(ProvisioningWorkflowEventCallback provisioningWorkflowEventCallback) throws RemoteException {
            if (this.mBinderReference.get() == null) {
                return true;
            }
            try {
                String str = FFSBackgroundProvisioningServiceBinder.TAG;
                WJLog.d(str, this.mLogPrefix + "removeCallback");
                this.mBinderReference.get().mEventDispatch.setListener(null);
                return true;
            } catch (Throwable th) {
                this.mBinderReference.get().logAIDLException(th, "removeCallback");
                throw th;
            }
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
        public boolean shutdown() throws RemoteException {
            if (this.mBinderReference.get() == null) {
                return true;
            }
            try {
                String str = FFSBackgroundProvisioningServiceBinder.TAG;
                WJLog.d(str, this.mLogPrefix + "shutdown");
                return this.mBinderReference.get().stopFFSService(true);
            } catch (Throwable th) {
                this.mBinderReference.get().logAIDLException(th, "shutdown");
                throw th;
            }
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface
        public boolean start(ProvisioningServiceConfiguration provisioningServiceConfiguration) throws RemoteException {
            if (this.mBinderReference.get() == null) {
                return true;
            }
            try {
                String str = FFSBackgroundProvisioningServiceBinder.TAG;
                WJLog.d(str, this.mLogPrefix + "start");
                return this.mBinderReference.get().startFFSService(provisioningServiceConfiguration, true);
            } catch (Throwable th) {
                this.mBinderReference.get().logAIDLException(th, "start");
                throw th;
            }
        }
    }

    private void attemptRestartFFS(Intent intent) {
        ProvisioningServiceConfiguration fromSharedPreferences = ProvisioningServiceConfiguration.getFromSharedPreferences(getPreferences());
        boolean z = getPreferences().getBoolean(PREF_SHUTDOWN_BY_CLIENT, false);
        String str = TAG;
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[3];
        boolean z2 = true;
        objArr[0] = Boolean.valueOf(fromSharedPreferences != null);
        objArr[1] = Boolean.valueOf(z);
        if (intent != null) {
            z2 = false;
        }
        objArr[2] = Boolean.valueOf(z2);
        WJLog.d(str, String.format(locale, "attemptRestartFFS - Existing Configuration: %b. Shutdown By Client: %b. Binder Service Restarted After Being Killed: %b.", objArr));
        if (intent == null && fromSharedPreferences != null && !z) {
            WJLog.d(TAG, "Attempting restart of FFS");
            boolean startFFSService = startFFSService(fromSharedPreferences, false);
            WJLog.d(TAG, "FFS successfully restarted after being killed by system: " + startFFSService);
            return;
        }
        WJLog.d(TAG, "Not attempting restart of FFS");
    }

    private SharedPreferences getPreferences() {
        return this.mSharedPreferencesProvider.get(PREF_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAIDLException(Throwable th, String str) {
        WJLog.e(TAG, String.format(Locale.ENGLISH, "An exception occurred while executing method %s. Exception: %s, RootCause: %s", str, th.toString(), WJErrorUtils.getRootCause(th).toString()), th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startFFSService(ProvisioningServiceConfiguration provisioningServiceConfiguration, boolean z) {
        if (provisioningServiceConfiguration != null) {
            this.mProvisioningServiceConfiguration = provisioningServiceConfiguration;
            if (this.mMapAuthenticationProvider.getAccount() != null) {
                WJLog.d(TAG, String.format(Locale.ENGLISH, "startFFSService - startedByClient: %b, configuration: %s", Boolean.valueOf(z), provisioningServiceConfiguration));
                if (this.mFFSProvisioningService == null) {
                    this.mFFSProvisioningService = new FFSProvisioningService(provisioningServiceConfiguration, new RemoteClientProvisioningEventListener(this.mEventDispatch));
                    WJLog.d(TAG, "Starting FFS Provisioning Service");
                    this.mFFSProvisioningService.start();
                    GeneratedOutlineSupport1.outline143(getPreferences(), PREF_SHUTDOWN_BY_CLIENT, false);
                    provisioningServiceConfiguration.writeToSharedPreferences(getPreferences().edit());
                    return true;
                }
                WJLog.d(TAG, "FFS Service not started because its already running");
                return false;
            }
            throw new IllegalStateException("FFS can't be started if a customer is not logged in");
        }
        throw new IllegalArgumentException("provisioningServiceConfiguration can not be null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean stopFFSService(boolean z) {
        WJLog.d(TAG, String.format(Locale.ENGLISH, "stopFFSService - shutdownByClient :%b", Boolean.valueOf(z)));
        if (z) {
            GeneratedOutlineSupport1.outline143(getPreferences(), PREF_SHUTDOWN_BY_CLIENT, true);
        }
        FFSProvisioningService fFSProvisioningService = this.mFFSProvisioningService;
        if (fFSProvisioningService != null) {
            fFSProvisioningService.shutdown();
            this.mFFSProvisioningService = null;
        }
        return true;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String str = TAG;
        WJLog.d(str, "onBind: Intent - " + intent);
        return this.mServiceInterface;
    }

    @Override // android.app.Service
    public void onCreate() {
        DaggerWrapper.initializeBaseComponent(getApplicationContext());
        DaggerWrapper.getBaseDependencyInjector().inject(this);
        super.onCreate();
        this.mServiceInterface = new ServiceInterface(this);
        WJLog.d(TAG, "onCreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        WJLog.d(TAG, "onDestroy");
        stopFFSService(false);
        this.mServiceInterface = null;
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        WJLog.d(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        String str = TAG;
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[3];
        objArr[0] = intent == null ? "null" : intent.toString();
        objArr[1] = Integer.valueOf(i);
        objArr[2] = Integer.valueOf(i2);
        WJLog.d(str, String.format(locale, "onStartCommand - Intent: %s. Flags: %d. StartId: %d.", objArr));
        attemptRestartFFS(intent);
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        String str = TAG;
        WJLog.d(str, "onUnbind - Intent: " + intent);
        return super.onUnbind(intent);
    }
}
