package com.amazon.alexa.devicesetup.impl;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.FFSConfigurationProvider;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.zerotouch.BackgroundProvisioningService;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.zerotouch.BackgroundProvisioningServiceFactory;
import com.amazon.alexa.devicesetup.service.DeviceProvisioningCoordinator;
import com.amazon.alexa.devicesetup.service.NullFFSProvisioningConfigurationException;
import com.amazon.alexa.devicesetup.service.NullIoTProvisioningConfigurationException;
import com.amazon.alexa.devicesetup.service.NullIoTSoftAPResponseCallbackException;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.dee.sdk.iotsoftap.IoTSoftApConfigurationProvider;
import com.amazon.dee.sdk.iotsoftap.IoTSoftApResponseCallback;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes7.dex */
public class DefaultDeviceProvisioningCoordinator implements DeviceProvisioningCoordinator {
    private static final String TAG = "DefaultDeviceProvisioningCoordinator";
    private static DefaultDeviceProvisioningCoordinator instance;
    private BackgroundProvisioningService backgroundProvisioningService;
    private final Context context;
    private final LazyComponent<EnvironmentService> environmentService;
    private final LazyComponent<EventBus> eventbus;
    private FFSConfigurationProvider ffsConfigurationProvider;
    private final LazyComponent<IdentityService> identityService;
    private IoTSoftApConfigurationProvider ioTSoftApConfigurationProvider;
    private boolean backgroundProvisioningActive = false;
    private IoTSoftApResponseCallback ioTSoftApResponseCallback = new IoTSoftAPResponseCallbackImpl();

    public DefaultDeviceProvisioningCoordinator(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        this.context = context;
        this.environmentService = componentGetter.get(EnvironmentService.class);
        this.identityService = componentGetter.get(IdentityService.class);
        this.eventbus = componentGetter.get(EventBus.class);
        this.ffsConfigurationProvider = new FFSConfigurationProviderImpl(this.environmentService, this.identityService);
        this.ioTSoftApConfigurationProvider = new IoTSoftAPConfigurationProviderImpl(this.environmentService, this.identityService);
        instance = this;
        ((MainActivityLifecycleObserverRegistrar) GeneratedOutlineSupport1.outline20(MainActivityLifecycleObserverRegistrar.class)).addObserver(getMainActivityLifecycleObserver());
    }

    public static IoTSoftApConfigurationProvider getIoTSoftAPProvider() throws NullIoTProvisioningConfigurationException {
        DefaultDeviceProvisioningCoordinator defaultDeviceProvisioningCoordinator = instance;
        if (defaultDeviceProvisioningCoordinator != null) {
            return defaultDeviceProvisioningCoordinator.ioTSoftApConfigurationProvider;
        }
        throw new NullIoTProvisioningConfigurationException("Configuration Provider has yet to be initialized");
    }

    public static IoTSoftApResponseCallback getIoTSoftApResponseCallback() throws NullIoTSoftAPResponseCallbackException {
        DefaultDeviceProvisioningCoordinator defaultDeviceProvisioningCoordinator = instance;
        if (defaultDeviceProvisioningCoordinator != null) {
            return defaultDeviceProvisioningCoordinator.ioTSoftApResponseCallback;
        }
        throw new NullIoTSoftAPResponseCallbackException("Configuration Provider has yet to be initialized");
    }

    private MainActivityLifecycleObserver getMainActivityLifecycleObserver() {
        return new MainActivityLifecycleObserver() { // from class: com.amazon.alexa.devicesetup.impl.DefaultDeviceProvisioningCoordinator.2
            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityCreated() {
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityDestroy() {
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityPause() {
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityResume() {
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityStart() {
                DefaultDeviceProvisioningCoordinator.this.onMainActivityStarted();
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityStop() {
            }
        };
    }

    public static FFSConfigurationProvider getffs() throws NullFFSProvisioningConfigurationException {
        DefaultDeviceProvisioningCoordinator defaultDeviceProvisioningCoordinator = instance;
        if (defaultDeviceProvisioningCoordinator != null) {
            return defaultDeviceProvisioningCoordinator.ffsConfigurationProvider;
        }
        throw new NullFFSProvisioningConfigurationException("Configuration Provider has yet to be initialized");
    }

    private void initializeAndStartBackgroundProvisioning() {
        if (this.backgroundProvisioningActive) {
            return;
        }
        int i = Build.VERSION.SDK_INT;
        if (!isAppOnForeground(this.context)) {
            return;
        }
        getBackgroundProvisioningService();
        BackgroundProvisioningService backgroundProvisioningService = this.backgroundProvisioningService;
        if (backgroundProvisioningService != null) {
            backgroundProvisioningService.startProvisioningService();
        }
        this.backgroundProvisioningActive = true;
    }

    private boolean isAppOnForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserChangedOrNull(UserIdentity userIdentity) {
        if (userIdentity != null) {
            initializeAndStartBackgroundProvisioning();
        } else {
            stopBackgroundProvisioning();
        }
    }

    private void stopBackgroundProvisioning() {
        BackgroundProvisioningService backgroundProvisioningService = this.backgroundProvisioningService;
        if (backgroundProvisioningService != null) {
            backgroundProvisioningService.stopProvisioningService();
            this.backgroundProvisioningService = null;
            this.backgroundProvisioningActive = false;
        }
    }

    public BackgroundProvisioningService getBackgroundProvisioningService() {
        if (this.backgroundProvisioningService == null) {
            this.backgroundProvisioningService = BackgroundProvisioningServiceFactory.getBackgroundProvisioning(this.context, this.ffsConfigurationProvider);
            this.backgroundProvisioningService.start();
        }
        return this.backgroundProvisioningService;
    }

    @Override // com.amazon.alexa.devicesetup.service.DeviceProvisioningCoordinator
    public void initialize() {
        this.eventbus.mo10268get().getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.devicesetup.impl.DefaultDeviceProvisioningCoordinator.1
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public void handle(@NonNull Message message) {
                DefaultDeviceProvisioningCoordinator defaultDeviceProvisioningCoordinator = DefaultDeviceProvisioningCoordinator.this;
                defaultDeviceProvisioningCoordinator.onUserChangedOrNull(((IdentityService) defaultDeviceProvisioningCoordinator.identityService.mo10268get()).getUser(DefaultDeviceProvisioningCoordinator.TAG));
            }
        });
    }

    @VisibleForTesting
    void onMainActivityStarted() {
        if (this.identityService.mo10268get().getUser(TAG) == null) {
            stopBackgroundProvisioning();
        } else {
            initializeAndStartBackgroundProvisioning();
        }
    }
}
