package com.amazon.dee.app.services.messaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.messaging.MessagingService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.util.Observables;
import com.amazon.dee.app.util.Utils;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.device.messaging.ADM;
import com.amazon.device.messaging.ADMMessageReceiver;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricsService;
import com.google.gson.JsonObject;
import dagger.Lazy;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class AmazonDeviceMessagingService implements MessagingService {
    static final String TAG = "AmazonDeviceMessagingService";
    ADM amazonDeviceMessaging;
    CommsDeviceSupport commsDeviceSupport;
    CommsManager commsManager;
    Context context;
    CoralService coralService;
    DeviceInformation deviceInformation;
    EventBus eventBus;
    IdentityService identityService;
    Lazy<MessagingHandler> lazyMessagingHandler;
    MessagingSettings messagingSettings;
    MetricsService metricsService;
    NetworkService networkService;
    PersonIdProvider personIdProvider;
    Subscription registrationSubscription;

    public AmazonDeviceMessagingService(Context context, ADM adm, DeviceInformation deviceInformation, CoralService coralService, IdentityService identityService, Lazy<MessagingHandler> lazy, MessagingSettings messagingSettings, CommsManager commsManager, CommsDeviceSupport commsDeviceSupport, MetricsService metricsService, NetworkService networkService, PersonIdProvider personIdProvider, EventBus eventBus) {
        this.amazonDeviceMessaging = adm;
        this.coralService = coralService;
        this.identityService = identityService;
        this.lazyMessagingHandler = lazy;
        this.messagingSettings = messagingSettings;
        this.metricsService = metricsService;
        this.context = context;
        this.networkService = networkService;
        this.commsManager = commsManager;
        this.commsDeviceSupport = commsDeviceSupport;
        this.personIdProvider = personIdProvider;
        this.deviceInformation = deviceInformation;
        this.eventBus = eventBus;
    }

    private String getUserId() {
        UserIdentity user = this.identityService.getUser(TAG);
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public static boolean isSupported(Context context) {
        boolean z;
        try {
            Class.forName("com.amazon.device.messaging.ADM");
            z = new ADM(context).isSupported();
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        new Object[1][0] = z ? "yes" : "no";
        return z;
    }

    @VisibleForTesting
    boolean canUseADMJobIntentService() {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                Class.forName("com.amazon.device.messaging.ADMMessageHandlerJobBase");
                ADMMessageReceiver.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                return true;
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
            }
        }
        return false;
    }

    void enableComponents(Context context) {
        ComponentName componentName = new ComponentName(context, AmazonMessageReceiver.class);
        ComponentName componentName2 = new ComponentName(context, AmazonMessageHandler.class);
        PackageManager packageManager = context.getPackageManager();
        packageManager.setComponentEnabledSetting(componentName, 1, 1);
        packageManager.setComponentEnabledSetting(componentName2, 1, 1);
    }

    public void handleMessage(@NonNull Bundle bundle) {
        if (this.messagingSettings.isDmpsEnabled()) {
            String userId = getUserId();
            if (userId == null) {
                Log.w(TAG, "There is no current user");
                return;
            }
            if (this.messagingSettings.isDmpsRegistered(userId, this.personIdProvider.getPersonId())) {
                this.lazyMessagingHandler.mo358get().handleMessage(bundle);
                return;
            } else {
                unregister();
                return;
            }
        }
        Log.w(TAG, "Received an incoming message, but DMPS is disabled");
    }

    public void initialize() {
        enableComponents(this.context);
        observeUserChanges();
    }

    public /* synthetic */ void lambda$observeUserChanges$0$AmazonDeviceMessagingService(Message message) {
        userChanged(this.identityService.getUser(TAG));
    }

    public /* synthetic */ Observable lambda$requestDeregistration$5$AmazonDeviceMessagingService() {
        try {
            return Observable.just(MobilePushNotificationsRequests.getUnregistrationData(this.deviceInformation.getDeviceSerialNumber(), "ADM"));
        } catch (DeviceInformationException e) {
            Log.e(TAG, e, "Deregistration attempted failed", new Object[0]);
            this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.DMPS_DEREGISTRATION_ERROR, e.getMessage(), "PushNotifications", Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
            return Observable.error(e);
        }
    }

    public /* synthetic */ Observable lambda$requestDeregistration$6$AmazonDeviceMessagingService(JsonObject jsonObject) {
        return this.coralService.request("/api/mobilepushnotifications/unregisterWithDMPS").post(jsonObject).as(Void.class).toObservable();
    }

    public /* synthetic */ void lambda$requestDeregistration$7$AmazonDeviceMessagingService(Void r5) {
        this.metricsService.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.DMPS_REGISTRATION_SUCCESS, "PushNotifications", true, Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
    }

    public /* synthetic */ void lambda$requestDeregistration$8$AmazonDeviceMessagingService(Throwable th) {
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.DMPS_DEREGISTRATION_ERROR, th.getMessage(), "PushNotifications", Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        this.metricsService.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.DMPS_REGISTRATION_SUCCESS, "PushNotifications", false, Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        Log.e(TAG, th, "Deregistration through DWCS failed", new Object[0]);
    }

    public /* synthetic */ Observable lambda$requestRegistration$1$AmazonDeviceMessagingService(String str, String str2) {
        try {
            String deviceSerialNumber = this.deviceInformation.getDeviceSerialNumber();
            Object[] objArr = {deviceSerialNumber, str, str2};
            return Observable.just(MobilePushNotificationsRequests.getRegistrationData(deviceSerialNumber, "ADM", str, null, str2));
        } catch (DeviceInformationException e) {
            Log.e(TAG, e, "Registration attempted failed", new Object[0]);
            this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.DMPS_REGISTRATION_ERROR, e.getMessage(), "PushNotifications", Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
            return Observable.error(e);
        }
    }

    public /* synthetic */ Observable lambda$requestRegistration$2$AmazonDeviceMessagingService(JsonObject jsonObject) {
        if (this.commsDeviceSupport.check()) {
            this.commsManager.registerForPush("CommsMobileApplication", jsonObject.get("deviceToken").getAsString());
        }
        return this.coralService.request("/api/mobilepushnotifications/registerWithDMPS").post(jsonObject).as(Void.class).toObservable();
    }

    public /* synthetic */ void lambda$requestRegistration$3$AmazonDeviceMessagingService(String str, String str2, Void r7) {
        Log.i(TAG, "\n*********************************************************************\nRegistration with ADM successful!\n*********************************************************************");
        this.metricsService.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.DMPS_REGISTRATION_SUCCESS, "PushNotifications", true, Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        this.messagingSettings.registerDmps(str, str2);
    }

    public /* synthetic */ void lambda$requestRegistration$4$AmazonDeviceMessagingService(Throwable th) {
        Log.e(TAG, th, "Registration failed", new Object[0]);
        this.metricsService.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.DMPS_REGISTRATION_SUCCESS, "PushNotifications", false, Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.DMPS_REGISTRATION_ERROR, th.getMessage(), "PushNotifications", Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        this.messagingSettings.setDmpsEnabled(false);
    }

    void observeUserChanges() {
        this.eventBus.getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$AmazonDeviceMessagingService$VyF_gg2nwvul5uqCiqrev7Jb5gQ
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AmazonDeviceMessagingService.this.lambda$observeUserChanges$0$AmazonDeviceMessagingService(message);
            }
        });
        if (getUserId() != null) {
            register();
        }
    }

    void register() {
        String registrationId = this.amazonDeviceMessaging.getRegistrationId();
        if (registrationId == null) {
            Log.e(TAG, "registrationId for ADM was null");
            this.amazonDeviceMessaging.startRegister();
            return;
        }
        new Object[1][0] = registrationId;
        requestRegistration(registrationId);
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingService
    public void registerReceiver(@NonNull MessagingReceiver messagingReceiver) {
        this.lazyMessagingHandler.mo358get().registerReceiver(messagingReceiver);
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingService
    public void registerReceiverWithHigherPriority(@NonNull MessagingReceiver messagingReceiver) {
        this.lazyMessagingHandler.mo358get().registerReceiverWithHigherPriority(messagingReceiver);
    }

    public void requestDeregistration() {
        Subscription subscription = this.registrationSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.messagingSettings.unregisterDmps();
        if (getUserId() == null) {
            Log.w(TAG, "There is no current user");
        } else {
            this.registrationSubscription = Observable.defer(new Func0() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$AmazonDeviceMessagingService$Mrde6o6WuPsrp9W5jZKq12d29GI
                @Override // rx.functions.Func0, java.util.concurrent.Callable
                /* renamed from: call */
                public final Object mo13098call() {
                    return AmazonDeviceMessagingService.this.lambda$requestDeregistration$5$AmazonDeviceMessagingService();
                }
            }).switchMap(new Func1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$AmazonDeviceMessagingService$bYarszMmtQFcKrSZ8Lz8MKwXS_c
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return AmazonDeviceMessagingService.this.lambda$requestDeregistration$6$AmazonDeviceMessagingService((JsonObject) obj);
                }
            }).retryWhen(Observables.exponentialBackoff(20)).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$AmazonDeviceMessagingService$iYVrrmPFtjZQ7Xm_zzN7cRJjX-g
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AmazonDeviceMessagingService.this.lambda$requestDeregistration$7$AmazonDeviceMessagingService((Void) obj);
                }
            }, new Action1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$AmazonDeviceMessagingService$FTkJSaJEfY-7oRtUlBeMq712K3g
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AmazonDeviceMessagingService.this.lambda$requestDeregistration$8$AmazonDeviceMessagingService((Throwable) obj);
                }
            });
        }
    }

    public void requestRegistration(@NonNull final String str) {
        final String userId = getUserId();
        if (userId == null) {
            Log.w(TAG, "There is no current user");
            return;
        }
        final String personId = this.personIdProvider.getPersonId();
        if (!this.networkService.isConnected()) {
            Log.i(TAG, "No network available to do ADM registration");
        } else if (this.messagingSettings.isDmpsRegistered(userId, personId)) {
            Log.i(TAG, "We are already registered");
        } else {
            Subscription subscription = this.registrationSubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            this.messagingSettings.setDmpsEnabled(false);
            this.registrationSubscription = Observable.defer(new Func0() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$AmazonDeviceMessagingService$GSaIrcMdp1nPMYUeSVHAYsNOTOc
                @Override // rx.functions.Func0, java.util.concurrent.Callable
                /* renamed from: call */
                public final Object mo13098call() {
                    return AmazonDeviceMessagingService.this.lambda$requestRegistration$1$AmazonDeviceMessagingService(str, personId);
                }
            }).switchMap(new Func1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$AmazonDeviceMessagingService$3NOfr8xDNUdcj3IxnazNtkjDA6Y
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return AmazonDeviceMessagingService.this.lambda$requestRegistration$2$AmazonDeviceMessagingService((JsonObject) obj);
                }
            }).subscribeOn(Schedulers.io()).retryWhen(Observables.exponentialBackoff(20)).subscribe(new Action1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$AmazonDeviceMessagingService$AdcljJzn0sUJeSkC7vvB_SoXMvQ
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AmazonDeviceMessagingService.this.lambda$requestRegistration$3$AmazonDeviceMessagingService(userId, personId, (Void) obj);
                }
            }, new Action1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$AmazonDeviceMessagingService$rwTUop3F8FTHAe-b5h4FBUbdzmw
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AmazonDeviceMessagingService.this.lambda$requestRegistration$4$AmazonDeviceMessagingService((Throwable) obj);
                }
            });
        }
    }

    void unregister() {
        this.amazonDeviceMessaging.startUnregister();
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingService
    public void unregisterReceiver(@NonNull MessagingReceiver messagingReceiver) {
        this.lazyMessagingHandler.mo358get().unregisterReceiver(messagingReceiver);
    }

    @VisibleForTesting
    void userChanged(UserIdentity userIdentity) {
        if (userIdentity != null) {
            register();
        } else {
            unregister();
        }
    }
}
