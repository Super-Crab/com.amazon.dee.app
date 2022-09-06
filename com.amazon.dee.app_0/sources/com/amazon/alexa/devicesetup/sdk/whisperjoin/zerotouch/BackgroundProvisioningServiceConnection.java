package com.amazon.alexa.devicesetup.sdk.whisperjoin.zerotouch;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.whisperjoin.deviceprovisioningservice.DeviceProvisioningServiceInterface;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/* loaded from: classes7.dex */
public class BackgroundProvisioningServiceConnection implements ServiceConnection {
    private ComponentName componentName;
    private DeviceProvisioningServiceInterface provisioningService;
    private ProvisioningServiceConfiguration serviceConfiguration;
    private Future<?> startProvisioningServiceFuture;
    private ProvisioningEventCallback provisioningEventCallback = new ProvisioningEventCallback();
    private volatile boolean isServiceActive = false;
    private volatile boolean isProvisioningStartRequested = false;

    public BackgroundProvisioningServiceConnection(ProvisioningServiceConfiguration provisioningServiceConfiguration) {
        this.serviceConfiguration = provisioningServiceConfiguration;
    }

    public ComponentName getComponentName() {
        return this.componentName;
    }

    public DeviceProvisioningServiceInterface getProvisioningService() {
        return this.provisioningService;
    }

    public boolean isServiceActive() {
        return this.isServiceActive;
    }

    public /* synthetic */ void lambda$onServiceConnected$0$BackgroundProvisioningServiceConnection(ExecutorService executorService) {
        try {
            this.isServiceActive = this.provisioningService.isActive();
            this.provisioningService.addCallback(this.provisioningEventCallback);
            if (this.isProvisioningStartRequested) {
                startService();
                this.isProvisioningStartRequested = false;
            }
        } catch (RemoteException unused) {
        } catch (Throwable th) {
            executorService.shutdown();
            throw th;
        }
        executorService.shutdown();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.provisioningService = DeviceProvisioningServiceInterface.Stub.asInterface(iBinder);
        this.componentName = componentName;
        final ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.startProvisioningServiceFuture = newSingleThreadExecutor.submit(new Runnable() { // from class: com.amazon.alexa.devicesetup.sdk.whisperjoin.zerotouch.-$$Lambda$BackgroundProvisioningServiceConnection$V1dWukAwbQpR7-eXHIufhylkcmQ
            @Override // java.lang.Runnable
            public final void run() {
                BackgroundProvisioningServiceConnection.this.lambda$onServiceConnected$0$BackgroundProvisioningServiceConnection(newSingleThreadExecutor);
            }
        });
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.isServiceActive = false;
        this.provisioningService = null;
    }

    public void startService() {
        try {
            if (this.provisioningService != null) {
                if (this.provisioningService.isActive()) {
                    return;
                }
                try {
                    this.provisioningService.start(this.serviceConfiguration);
                    this.isServiceActive = true;
                } catch (IllegalStateException unused) {
                }
                this.isProvisioningStartRequested = false;
                return;
            }
            this.isProvisioningStartRequested = true;
        } catch (RemoteException unused2) {
        }
    }

    public void stopService() {
        try {
            if (this.provisioningService != null && this.provisioningService.isActive()) {
                this.provisioningService.shutdown();
            }
            this.isServiceActive = false;
            this.isProvisioningStartRequested = false;
        } catch (RemoteException unused) {
        }
    }
}
