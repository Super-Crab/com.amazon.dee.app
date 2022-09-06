package com.amazon.dee.app.services.messaging;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.amazon.dee.app.BuildConfig;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.messaging.MobilePushNotificationsRequests;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.util.Observables;
import com.amazon.dee.app.util.Utils;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricsService;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonObject;
import dagger.Lazy;
import java.io.IOException;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class FirebaseCloudMessagingService implements MessagingService {
    private static final String TAG = Log.tag(FirebaseCloudMessagingService.class);
    CommsDeviceSupport commsDeviceSupport;
    CommsManager commsManager;
    Context context;
    CoralService coralService;
    DeviceInformation deviceInformation;
    EventBus eventBus;
    IdentityService identityService;
    FirebaseInstanceId instanceID;
    Lazy<MessagingHandler> lazyMessagingHandler;
    MAPAccountManager mapAccountManager;
    MessageCrypto messageCrypto;
    MessagingSettings messagingSettings;
    MetricsService metricsService;
    NetworkService networkService;
    PersonIdProvider personIdProvider;
    Subscription registrationSubscription;

    public FirebaseCloudMessagingService(Context context, FirebaseInstanceId firebaseInstanceId, CoralService coralService, IdentityService identityService, Lazy<MessagingHandler> lazy, MessagingSettings messagingSettings, CommsManager commsManager, CommsDeviceSupport commsDeviceSupport, MetricsService metricsService, NetworkService networkService, DeviceInformation deviceInformation, MessageCrypto messageCrypto, PersonIdProvider personIdProvider, EventBus eventBus) {
        this.mapAccountManager = new MAPAccountManager(context);
        this.instanceID = firebaseInstanceId;
        this.coralService = coralService;
        this.identityService = identityService;
        this.lazyMessagingHandler = lazy;
        this.messagingSettings = messagingSettings;
        this.context = context;
        this.commsManager = commsManager;
        this.commsDeviceSupport = commsDeviceSupport;
        this.metricsService = metricsService;
        this.networkService = networkService;
        this.deviceInformation = deviceInformation;
        this.messageCrypto = messageCrypto;
        this.personIdProvider = personIdProvider;
        this.eventBus = eventBus;
    }

    private String getToken() throws IOException {
        return this.instanceID.getToken(BuildConfig.FCM_SENDER_ID, FirebaseMessaging.INSTANCE_ID_SCOPE);
    }

    private String getUserId() {
        UserIdentity user = this.identityService.getUser(TAG);
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean isSupported(Context context) {
        int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        boolean z = isGooglePlayServicesAvailable == 0 || isGooglePlayServicesAvailable == 2 || isGooglePlayServicesAvailable == 18;
        Object[] objArr = new Object[2];
        objArr[0] = z ? "yes" : "no";
        objArr[1] = Integer.valueOf(isGooglePlayServicesAvailable);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$unregister$9(Void r0) {
    }

    private boolean shouldAlwaysHandleMessageInILAW() {
        return !BuildConfig.IS_PROD_ENVIRONMENT;
    }

    public void handleMessage(@NonNull Bundle bundle) {
        if (this.messagingSettings.isFcmEnabled()) {
            String userId = getUserId();
            if (userId == null) {
                Log.w(TAG, "There is no current user");
                return;
            }
            String personId = this.personIdProvider.getPersonId();
            boolean shouldAlwaysHandleMessageInILAW = shouldAlwaysHandleMessageInILAW();
            if (!this.messagingSettings.isFcmRegistered(userId, personId) && !shouldAlwaysHandleMessageInILAW) {
                unregister();
                return;
            } else {
                this.lazyMessagingHandler.mo358get().handleMessage(bundle);
                return;
            }
        }
        Log.w(TAG, "Received an incoming message, but FCM is disabled");
    }

    public void initialize() {
        observeUserChanges();
        observeNetworkChanges();
        register(false);
    }

    void invalidateToken() {
        try {
            this.instanceID.deleteToken(BuildConfig.FCM_SENDER_ID, FirebaseMessaging.INSTANCE_ID_SCOPE);
        } catch (IOException e) {
            this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.FCM_TOKEN_INVALIDATION_ERROR, e.getMessage(), "PushNotifications", Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
            Log.e(TAG, e, "Unexpected exception deleting Firebase InstanceID token.", new Object[0]);
        }
    }

    public boolean isRegistered() {
        String userId = getUserId();
        if (userId != null) {
            return this.messagingSettings.isFcmRegistered(userId, this.personIdProvider.getPersonId());
        }
        return false;
    }

    public /* synthetic */ void lambda$observeNetworkChanges$1$FirebaseCloudMessagingService(Boolean bool) {
        if (bool.booleanValue()) {
            Log.i(TAG, "Network available, attempting registration");
            register(false);
            return;
        }
        Log.i(TAG, "Network unavailable, will not attempt registration");
    }

    public /* synthetic */ void lambda$observeUserChanges$0$FirebaseCloudMessagingService(Message message) {
        userChanged(this.identityService.getUser(TAG));
    }

    public /* synthetic */ Observable lambda$register$2$FirebaseCloudMessagingService(String str) {
        Log.i(TAG, "Attempting to register with FCM");
        try {
            String token = getToken();
            Object[] objArr = {token, str};
            this.metricsService.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.FCM_REGISTRATION_SUCCESS, "PushNotifications", true, Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
            try {
                return Observable.just(MobilePushNotificationsRequests.getRegistrationData(this.deviceInformation.getDeviceSerialNumber(), MobilePushNotificationsRequests.PushNotificationPlatform.FIREBASE, token, this.messageCrypto.getPublicKey(), str));
            } catch (DeviceInformationException e) {
                return Observable.error(e);
            }
        } catch (IOException | IllegalArgumentException e2) {
            this.metricsService.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.FCM_REGISTRATION_SUCCESS, "PushNotifications", false, Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
            this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.FCM_REGISTRATION_ERROR, e2.getMessage(), "PushNotifications", Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
            return Observable.error(e2);
        }
    }

    public /* synthetic */ Observable lambda$register$3$FirebaseCloudMessagingService(JsonObject jsonObject) {
        if (!this.deviceInformation.isFireOS() && this.commsDeviceSupport.check()) {
            this.commsManager.registerForPush("AlexaCompanionApp", jsonObject.get("deviceToken").getAsString());
        }
        return this.coralService.request("/api/mobilepushnotifications/registerWithDMPS?sif_profile=dmps").post(jsonObject).as(Void.class).toObservable();
    }

    public /* synthetic */ Observable lambda$register$4$FirebaseCloudMessagingService(Throwable th) {
        if (shouldAlwaysHandleMessageInILAW()) {
            return Observable.just(null);
        }
        return Observable.error(th);
    }

    public /* synthetic */ void lambda$register$5$FirebaseCloudMessagingService(String str, String str2, Void r7) {
        Log.i(TAG, "\n*********************************************************************\nRegistration with FCM successful!\n*********************************************************************");
        this.metricsService.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.DMPS_REGISTRATION_SUCCESS, "PushNotifications", true, Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        this.messagingSettings.registerFcm(str, str2);
    }

    public /* synthetic */ void lambda$register$6$FirebaseCloudMessagingService(Throwable th) {
        Log.e(TAG, th, "FCM registration failed\n", new Object[0]);
        this.metricsService.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.DMPS_REGISTRATION_SUCCESS, "PushNotifications", false, Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.DMPS_REGISTRATION_ERROR, th.getMessage(), "PushNotifications", Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        if (!shouldAlwaysHandleMessageInILAW()) {
            this.messagingSettings.setFcmEnabled(false);
        }
    }

    public /* synthetic */ void lambda$unregister$10$FirebaseCloudMessagingService(Throwable th) {
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.FCM_DEREGISTRATION_ERROR, th.getMessage(), "PushNotifications", Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        Log.e(TAG, th, "Deregistration through DWCS failed", new Object[0]);
        invalidateToken();
    }

    public /* synthetic */ Observable lambda$unregister$7$FirebaseCloudMessagingService() {
        try {
            return Observable.just(MobilePushNotificationsRequests.getUnregistrationData(this.deviceInformation.getDeviceSerialNumber(), MobilePushNotificationsRequests.PushNotificationPlatform.FIREBASE));
        } catch (DeviceInformationException | IllegalArgumentException e) {
            Log.e(TAG, e, "Deregistration attempt failed\n", new Object[0]);
            this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.FCM_DEREGISTRATION_ERROR, e.getMessage(), "PushNotifications", Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
            return Observable.error(e);
        }
    }

    public /* synthetic */ Observable lambda$unregister$8$FirebaseCloudMessagingService(JsonObject jsonObject) {
        return this.coralService.request("/api/mobilepushnotifications/unregisterWithDMPS").post(jsonObject).as(Void.class).toObservable();
    }

    void observeNetworkChanges() {
        this.networkService.onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$FirebaseCloudMessagingService$UQtNpWop0JQuXVmkjmS4ldAhL3E
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FirebaseCloudMessagingService.this.lambda$observeNetworkChanges$1$FirebaseCloudMessagingService((Boolean) obj);
            }
        });
    }

    void observeUserChanges() {
        this.eventBus.getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$FirebaseCloudMessagingService$h4Xdqm2Ed-W3oeg_1zOM3q1ls5I
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                FirebaseCloudMessagingService.this.lambda$observeUserChanges$0$FirebaseCloudMessagingService(message);
            }
        });
    }

    public void register(boolean z) {
        new Object[1][0] = Boolean.valueOf(z);
        final String userId = getUserId();
        if (userId == null) {
            Log.w(TAG, "There is no current user");
            return;
        }
        final String personId = this.personIdProvider.getPersonId();
        if (!this.networkService.isConnected()) {
            Log.i(TAG, "No network available to do FCM registration");
        } else if (!z && this.messagingSettings.isFcmRegistered(userId, personId)) {
            Log.i(TAG, "We are already registered");
        } else {
            Subscription subscription = this.registrationSubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            this.messagingSettings.setFcmEnabled(false);
            this.registrationSubscription = Observable.defer(new Func0() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$FirebaseCloudMessagingService$CmnHeViOQQmLC6GuwZgoDtjs0sw
                @Override // rx.functions.Func0, java.util.concurrent.Callable
                /* renamed from: call */
                public final Object mo13098call() {
                    return FirebaseCloudMessagingService.this.lambda$register$2$FirebaseCloudMessagingService(personId);
                }
            }).flatMap(new Func1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$FirebaseCloudMessagingService$JbjtUogb91OiP7Z3GTuMe7FveEo
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return FirebaseCloudMessagingService.this.lambda$register$3$FirebaseCloudMessagingService((JsonObject) obj);
                }
            }).subscribeOn(Schedulers.io()).retryWhen(Observables.exponentialBackoff(20)).onErrorResumeNext(new Func1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$FirebaseCloudMessagingService$tNyl2W5hY27AcW1P3CKQzFbJ92I
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return FirebaseCloudMessagingService.this.lambda$register$4$FirebaseCloudMessagingService((Throwable) obj);
                }
            }).subscribe(new Action1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$FirebaseCloudMessagingService$xBpCwQ7K4s__C_MqrmM-kRg0Tek
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    FirebaseCloudMessagingService.this.lambda$register$5$FirebaseCloudMessagingService(userId, personId, (Void) obj);
                }
            }, new Action1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$FirebaseCloudMessagingService$HDaJacoAzUOn0JI3NsG4Tk9i1L0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    FirebaseCloudMessagingService.this.lambda$register$6$FirebaseCloudMessagingService((Throwable) obj);
                }
            });
        }
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingService
    public void registerReceiver(@NonNull MessagingReceiver messagingReceiver) {
        this.lazyMessagingHandler.mo358get().registerReceiver(messagingReceiver);
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingService
    public void registerReceiverWithHigherPriority(@NonNull MessagingReceiver messagingReceiver) {
        this.lazyMessagingHandler.mo358get().registerReceiverWithHigherPriority(messagingReceiver);
    }

    public void unregister() {
        Subscription subscription = this.registrationSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.messagingSettings.unregisterFcm();
        this.messageCrypto.expireKeyPair();
        if (getUserId() == null) {
            Log.w(TAG, "There is no current user");
            invalidateToken();
            return;
        }
        this.registrationSubscription = Observable.defer(new Func0() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$FirebaseCloudMessagingService$WmLOOO4TKwFoSvHK-aRxMu-HEkQ
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return FirebaseCloudMessagingService.this.lambda$unregister$7$FirebaseCloudMessagingService();
            }
        }).switchMap(new Func1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$FirebaseCloudMessagingService$0uB721HYzDqcq2ZPxM5b9XGzBWE
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return FirebaseCloudMessagingService.this.lambda$unregister$8$FirebaseCloudMessagingService((JsonObject) obj);
            }
        }).retryWhen(Observables.exponentialBackoff(20)).subscribeOn(Schedulers.io()).subscribe($$Lambda$FirebaseCloudMessagingService$2VjeQfhepIgos_A9kNrNGbIWhmM.INSTANCE, new Action1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$FirebaseCloudMessagingService$xzmRROi7miTLDoiQ92FyiqtGFgk
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FirebaseCloudMessagingService.this.lambda$unregister$10$FirebaseCloudMessagingService((Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingService
    public void unregisterReceiver(@NonNull MessagingReceiver messagingReceiver) {
        this.lazyMessagingHandler.mo358get().unregisterReceiver(messagingReceiver);
    }

    @VisibleForTesting
    void userChanged(UserIdentity userIdentity) {
        if (userIdentity != null) {
            register(true);
        } else {
            unregister();
        }
    }
}
