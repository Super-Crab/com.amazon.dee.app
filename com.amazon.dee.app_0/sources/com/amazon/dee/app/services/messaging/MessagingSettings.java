package com.amazon.dee.app.services.messaging;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.tarazed.dmps.DMPSHandlerDefault;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.amazon.dee.app.services.logging.Log;
import com.dee.app.http.CoralService;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class MessagingSettings {
    private static final String ANDROID_OS_NOTIFICATIONS_ENABLED = "service.android_os_notifications_enabled";
    private static final String DMPS_MESSAGING = "service.dmps_messaging";
    private static final String DMPS_SERVICE_ENABLED = "service.dmps_enabled";
    private static final String FCM_MESSAGING = "service.fcmce.fcm_enabled";
    private static final String PERSON_ID = "person.id";
    private static final String TAG = Log.tag(MessagingSettings.class);
    private static final String USER_ID = "user.id";
    Context context;
    CoralService coralService;
    DeviceInformation deviceInformation;
    EventBus eventBus;
    IdentityService identityService;
    MessagingSettingsMetricsHandler messagingSettingsMetricsHandler;
    NetworkService networkService;
    Subscription networkServiceSubscription;
    PersistentStorage persistentDmpsStorage;
    PersistentStorage persistentFcmStorage;

    public MessagingSettings(PersistentStorage.Factory factory, MessagingSettingsMetricsHandler messagingSettingsMetricsHandler, DeviceInformation deviceInformation, CoralService coralService, EventBus eventBus, final IdentityService identityService, NetworkService networkService, final Context context) {
        this.messagingSettingsMetricsHandler = messagingSettingsMetricsHandler;
        this.persistentFcmStorage = factory.create(FCM_MESSAGING);
        this.persistentDmpsStorage = factory.create(DMPS_MESSAGING);
        this.deviceInformation = deviceInformation;
        this.coralService = coralService;
        this.eventBus = eventBus;
        this.identityService = identityService;
        this.networkService = networkService;
        this.context = context;
        eventBus.getNewSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$MessagingSettings$Hs-0FcGjx9zXLLDP3vCHWNnQ_-s
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MessagingSettings.this.lambda$new$0$MessagingSettings(identityService, context, message);
            }
        });
        eventBus.getNewSubscriber().subscribeFilter(new EventTypeMessageFilter(LifecycleEvent.APPLICATION_DID_FOREGROUND.name), new MessageHandler() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$MessagingSettings$KtP0asi3_JsXZKNVOh8MdlSNGJE
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MessagingSettings.this.lambda$new$1$MessagingSettings(identityService, context, message);
            }
        });
        this.networkServiceSubscription = networkService.onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$MessagingSettings$jAf_Jul78ok4UjVsu0yPlbukeAs
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MessagingSettings.this.onNetworkChanged(((Boolean) obj).booleanValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNetworkChanged(boolean z) {
        if (!z || !this.identityService.isAuthenticated(TAG)) {
            return;
        }
        setAndroidOSNotificationsEnabled(NotificationManagerCompat.from(this.context).areNotificationsEnabled());
    }

    private void setAndroidOSNotificationsEnabled(final boolean z) {
        new Object[1][0] = z ? "enabled" : FeatureState.DISABLED;
        if (shouldRecordOSNotificationEnabledMetric(z)) {
            this.messagingSettingsMetricsHandler.recordAndroidOSNotificationEnabledMetrics(z);
            try {
                this.coralService.request(DMPSHandlerDefault.SET_PERMISSION_URL).post(MobilePushNotificationsRequests.getSetPermissionData(this.deviceInformation.getDeviceSerialNumber(), "OS_NOTIFICATIONS", z)).as(Void.class).toObservable().subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.dee.app.services.messaging.-$$Lambda$MessagingSettings$gA0JDW5GWTCmDNxxdWUbPGZhMu4
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        MessagingSettings.this.lambda$setAndroidOSNotificationsEnabled$2$MessagingSettings(z, (Void) obj);
                    }
                }, $$Lambda$MessagingSettings$tj5cdcAGYWF6BVZ12WXlMagX3J0.INSTANCE);
            } catch (DeviceInformationException e) {
                Log.e(TAG, e, "Error occurred in fetching DSN from DeviceInformation.", new Object[0]);
            }
        }
    }

    private boolean shouldRecordOSNotificationEnabledMetric(boolean z) {
        return !areAndroidOSNotificationsPreferencesRecorded() || z != areAndroidOSNotificationsEnabledInPersistentStorage();
    }

    public boolean areAndroidOSNotificationsEnabledInPersistentStorage() {
        boolean z = this.persistentDmpsStorage.getBoolean(ANDROID_OS_NOTIFICATIONS_ENABLED);
        new Object[1][0] = z ? "yes" : "no";
        return z;
    }

    public boolean areAndroidOSNotificationsPreferencesRecorded() {
        boolean contains = this.persistentDmpsStorage.contains(ANDROID_OS_NOTIFICATIONS_ENABLED);
        new Object[1][0] = contains ? "yes" : "no";
        return contains;
    }

    public boolean isDmpsEnabled() {
        boolean z = this.persistentDmpsStorage.getBoolean(DMPS_SERVICE_ENABLED) || !TextUtils.isEmpty(this.persistentDmpsStorage.getString("user.id"));
        Object[] objArr = new Object[1];
        objArr[0] = z ? "yes" : "no";
        String.format("DMPS is enabled to handle messages? %s", objArr);
        return z;
    }

    public boolean isDmpsRegistered(@NonNull String str, @Nullable String str2) {
        String string = this.persistentDmpsStorage.getString("user.id");
        String string2 = this.persistentDmpsStorage.getString(PERSON_ID);
        boolean z = !TextUtils.isEmpty(string) && TextUtils.equals(string, str) && !TextUtils.isEmpty(string2) && TextUtils.equals(string2, str2);
        Object[] objArr = new Object[3];
        objArr[0] = str;
        objArr[1] = str2;
        objArr[2] = z ? "yes" : "no";
        String.format("userId %s and personId %s combo is registered with DMPS? %s", objArr);
        return z;
    }

    public boolean isFcmEnabled() {
        boolean z = this.persistentFcmStorage.getBoolean(DMPS_SERVICE_ENABLED) || !TextUtils.isEmpty(this.persistentFcmStorage.getString("user.id"));
        Object[] objArr = new Object[1];
        objArr[0] = z ? "yes" : "no";
        String.format("FCM is enabled to handle messages? %s", objArr);
        return z;
    }

    public boolean isFcmRegistered(@NonNull String str, @Nullable String str2) {
        String string = this.persistentFcmStorage.getString("user.id");
        String string2 = this.persistentFcmStorage.getString(PERSON_ID);
        boolean z = !TextUtils.isEmpty(string) && TextUtils.equals(string, str) && !TextUtils.isEmpty(string2) && TextUtils.equals(string2, str2);
        Object[] objArr = new Object[3];
        objArr[0] = str;
        objArr[1] = str2;
        objArr[2] = z ? "yes" : "no";
        String.format("userId %s and personId %s combo is registered with FCM? %s", objArr);
        return z;
    }

    public /* synthetic */ void lambda$new$0$MessagingSettings(IdentityService identityService, Context context, Message message) {
        if (identityService.isAuthenticated(TAG)) {
            setAndroidOSNotificationsEnabled(NotificationManagerCompat.from(context).areNotificationsEnabled());
        }
    }

    public /* synthetic */ void lambda$new$1$MessagingSettings(IdentityService identityService, Context context, Message message) {
        if (identityService.isAuthenticated(TAG)) {
            setAndroidOSNotificationsEnabled(NotificationManagerCompat.from(context).areNotificationsEnabled());
        }
    }

    public /* synthetic */ void lambda$setAndroidOSNotificationsEnabled$2$MessagingSettings(boolean z, Void r3) {
        this.persistentDmpsStorage.edit().set(ANDROID_OS_NOTIFICATIONS_ENABLED, z).commit();
        new Object[1][0] = z ? "enabled" : FeatureState.DISABLED;
    }

    public void registerDmps(@NonNull String str, @Nullable String str2) {
        this.persistentDmpsStorage.edit().set("user.id", str).set(PERSON_ID, str2).set(DMPS_SERVICE_ENABLED, true).commit();
        String.format("Registered userId %s and personId %s to handle DMPS messages", str, str2);
    }

    public void registerFcm(@NonNull String str, @Nullable String str2) {
        this.persistentFcmStorage.edit().set("user.id", str).set(PERSON_ID, str2).set(FCM_MESSAGING, true).commit();
        String.format("Registered userId %s and personId %s combo to handle FCM messages", str, str2);
    }

    public void setDmpsEnabled(boolean z) {
        this.persistentDmpsStorage.edit().set(DMPS_SERVICE_ENABLED, z).commit();
        Object[] objArr = new Object[1];
        objArr[0] = z ? "enabled" : FeatureState.DISABLED;
        String.format("Setting the DMPS service to be %s", objArr);
    }

    public void setFcmEnabled(boolean z) {
        this.persistentFcmStorage.edit().set(FCM_MESSAGING, z).commit();
        Object[] objArr = new Object[1];
        objArr[0] = z ? "enabled" : FeatureState.DISABLED;
        String.format("Setting the FCM service to be %s", objArr);
    }

    public void unregisterDmps() {
        this.persistentDmpsStorage.edit().remove(DMPS_SERVICE_ENABLED).remove("user.id").remove(PERSON_ID).commit();
        Log.w(TAG, "disabled handling messaging for the current user");
    }

    public void unregisterFcm() {
        this.persistentFcmStorage.edit().remove(FCM_MESSAGING).remove("user.id").remove(PERSON_ID).commit();
        Log.w(TAG, "disabled handling messaging for the current user");
    }
}
