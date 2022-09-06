package com.amazon.alexa.tarazed.dmps;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.tarazed.eventbus.EventBusHandler;
import com.amazon.alexa.tarazed.utils.RetryUtil;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.gson.JsonObject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
/* compiled from: DMPSHandlerDefault.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 (2\u00020\u0001:\u0001(BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\n\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J \u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u001cH\u0002J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u001cH\u0002J\b\u0010$\u001a\u00020\u0016H\u0016J\u0010\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u001cH\u0002J\u000e\u0010'\u001a\u00020\u001c*\u0004\u0018\u00010\u0019H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/amazon/alexa/tarazed/dmps/DMPSHandlerDefault;", "Lcom/amazon/alexa/tarazed/dmps/DMPSHandler;", "deviceInfo", "Lcom/amazon/alexa/device/api/DeviceInformation;", "identityService", "Lcom/amazon/alexa/identity/api/IdentityService;", "coralService", "Lcom/dee/app/http/CoralService;", "dmpsPersistentStorage", "Lcom/amazon/alexa/protocols/storage/PersistentStorage;", "eventBus", "Lcom/amazon/alexa/eventbus/api/EventBus;", "eventBusHandler", "Lcom/amazon/alexa/tarazed/eventbus/EventBusHandler;", "environmentService", "Lcom/amazon/alexa/protocols/environment/EnvironmentService;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/alexa/device/api/DeviceInformation;Lcom/amazon/alexa/identity/api/IdentityService;Lcom/dee/app/http/CoralService;Lcom/amazon/alexa/protocols/storage/PersistentStorage;Lcom/amazon/alexa/eventbus/api/EventBus;Lcom/amazon/alexa/tarazed/eventbus/EventBusHandler;Lcom/amazon/alexa/protocols/environment/EnvironmentService;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "disableTarazedPermission", "", "enableTarazedPermission", "getDSN", "", "getStorageKey", "isTarazedNotificationEnabled", "", "setPermissionRequestPayload", "Lcom/google/gson/JsonObject;", "deviceId", DMPSHandlerDefault.SET_PERMISSION_PAYLOAD_NOTIFICATION_TYPE_KEY, DMPSHandlerDefault.SET_PERMISSION_PAYLOAD_HAS_ACCESS_KEY, "setPersistentStorage", "tarazedPermissionEnabled", "subscribe", "updatePermissionStatus", "permissionStatus", "isTarazedEvent", "Companion", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes10.dex */
public final class DMPSHandlerDefault implements DMPSHandler {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EVENT_BUS_TARAZED_LAUNCH_EVENT_TYPE = "tarazed::stateChange::launch";
    @NotNull
    public static final String EVENT_BUS_TARAZED_PAUSE_EVENT_TYPE = "tarazed::stateChange::pause";
    public static final int MAX_RETRY_COUNT = 10;
    @NotNull
    public static final String METRIC_DMPS_SET_TARAZED_PERMISSION = "DMPSSetPermissionTarazed";
    @NotNull
    public static final String SET_PERMISSION_PAYLOAD_DEVICE_ID_KEY = "deviceId";
    @NotNull
    public static final String SET_PERMISSION_PAYLOAD_HAS_ACCESS_KEY = "hasAccess";
    @NotNull
    public static final String SET_PERMISSION_PAYLOAD_NOTIFICATION_TYPE_KEY = "notificationType";
    @NotNull
    public static final String SET_PERMISSION_URL = "/api/mobilepushnotifications/setpermission";
    private static final String TAG = "TarazedDMPSHandler";
    @NotNull
    public static final String TARAZED_NOTIFICATIONS_ENABLED = "service.tarazed_enabled";
    @NotNull
    public static final String TARAZED_SET_PERMISSION_NOTIFICATION_TYPE = "TarazedNotification";
    private final CoralService coralService;
    private final DeviceInformation deviceInfo;
    private final PersistentStorage dmpsPersistentStorage;
    private final EnvironmentService environmentService;
    private final EventBus eventBus;
    private final EventBusHandler eventBusHandler;
    private IdentityService identityService;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;

    /* compiled from: DMPSHandlerDefault.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/tarazed/dmps/DMPSHandlerDefault$Companion;", "", "()V", "EVENT_BUS_TARAZED_LAUNCH_EVENT_TYPE", "", "EVENT_BUS_TARAZED_PAUSE_EVENT_TYPE", "MAX_RETRY_COUNT", "", "METRIC_DMPS_SET_TARAZED_PERMISSION", "SET_PERMISSION_PAYLOAD_DEVICE_ID_KEY", "SET_PERMISSION_PAYLOAD_HAS_ACCESS_KEY", "SET_PERMISSION_PAYLOAD_NOTIFICATION_TYPE_KEY", "SET_PERMISSION_URL", "TAG", "TARAZED_NOTIFICATIONS_ENABLED", "TARAZED_SET_PERMISSION_NOTIFICATION_TYPE", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DMPSHandlerDefault(@NotNull DeviceInformation deviceInfo, @NotNull IdentityService identityService, @NotNull CoralService coralService, @NotNull PersistentStorage dmpsPersistentStorage, @NotNull EventBus eventBus, @NotNull EventBusHandler eventBusHandler, @NotNull EnvironmentService environmentService, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        Intrinsics.checkParameterIsNotNull(identityService, "identityService");
        Intrinsics.checkParameterIsNotNull(coralService, "coralService");
        Intrinsics.checkParameterIsNotNull(dmpsPersistentStorage, "dmpsPersistentStorage");
        Intrinsics.checkParameterIsNotNull(eventBus, "eventBus");
        Intrinsics.checkParameterIsNotNull(eventBusHandler, "eventBusHandler");
        Intrinsics.checkParameterIsNotNull(environmentService, "environmentService");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.deviceInfo = deviceInfo;
        this.identityService = identityService;
        this.coralService = coralService;
        this.dmpsPersistentStorage = dmpsPersistentStorage;
        this.eventBus = eventBus;
        this.eventBusHandler = eventBusHandler;
        this.environmentService = environmentService;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
    }

    private final String getDSN() {
        try {
            return this.deviceInfo.getDeviceSerialNumber();
        } catch (DeviceInformationException e) {
            this.logger.e(TAG, "failed to retrieve DSN from AlexaMobile Device API.", e);
            return null;
        }
    }

    private final String getStorageKey() {
        String dsn = getDSN();
        UserIdentity user = this.identityService.getUser(TAG);
        String id = user != null ? user.getId() : null;
        String coralHost = this.environmentService.getCoralHost();
        Intrinsics.checkExpressionValueIsNotNull(coralHost, "environmentService.coralHost");
        if (dsn != null && id != null) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("dsn=", dsn, ", customerId=", id, ", coralHost=");
            outline116.append(coralHost);
            tarazedSessionLogger.d(TAG, outline116.toString());
            return id + JsonReaderKt.COLON + dsn + JsonReaderKt.COLON + coralHost + ".service.tarazed_enabled";
        }
        this.logger.e(TAG, "failed to retrieve customer metadata");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTarazedEvent(@Nullable String str) {
        return Intrinsics.areEqual(str, EVENT_BUS_TARAZED_LAUNCH_EVENT_TYPE) || Intrinsics.areEqual(str, EVENT_BUS_TARAZED_PAUSE_EVENT_TYPE);
    }

    private final JsonObject setPermissionRequestPayload(String str, String str2, boolean z) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceId", str);
        jsonObject.addProperty(SET_PERMISSION_PAYLOAD_NOTIFICATION_TYPE_KEY, str2);
        jsonObject.addProperty(SET_PERMISSION_PAYLOAD_HAS_ACCESS_KEY, Boolean.valueOf(z));
        return jsonObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPersistentStorage(boolean z) {
        String storageKey = getStorageKey();
        if (storageKey != null) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            tarazedSessionLogger.i(TAG, "Caching Tarazed permission status in storage: key=" + storageKey + ", permission=" + z);
            this.dmpsPersistentStorage.edit().set(storageKey, z).commit();
        }
    }

    private final void updatePermissionStatus(final boolean z) {
        final String dsn = getDSN();
        if (dsn != null) {
            this.coralService.request(SET_PERMISSION_URL).post(setPermissionRequestPayload(dsn, TARAZED_SET_PERMISSION_NOTIFICATION_TYPE, z)).asRaw().toObservable().subscribeOn(Schedulers.io()).retryWhen(RetryUtil.exponentialBackoff(10)).subscribe(new Action1<Response>() { // from class: com.amazon.alexa.tarazed.dmps.DMPSHandlerDefault$updatePermissionStatus$$inlined$let$lambda$1
                @Override // rx.functions.Action1
                public final void call(Response response) {
                    TarazedSessionLogger tarazedSessionLogger;
                    tarazedSessionLogger = this.logger;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("calling DMPS to setPermission for dsn: ");
                    outline107.append(dsn);
                    tarazedSessionLogger.i("TarazedDMPSHandler", outline107.toString());
                }
            }, new Action1<Throwable>() { // from class: com.amazon.alexa.tarazed.dmps.DMPSHandlerDefault$updatePermissionStatus$$inlined$let$lambda$2
                @Override // rx.functions.Action1
                public final void call(Throwable error) {
                    TarazedSessionLogger tarazedSessionLogger;
                    tarazedSessionLogger = this.logger;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error occurred in set permission in DMPS for dsn: ");
                    outline107.append(dsn);
                    String sb = outline107.toString();
                    Intrinsics.checkExpressionValueIsNotNull(error, "error");
                    tarazedSessionLogger.e("TarazedDMPSHandler", sb, error);
                    this.setPersistentStorage(false);
                }
            }, new Action0() { // from class: com.amazon.alexa.tarazed.dmps.DMPSHandlerDefault$updatePermissionStatus$$inlined$let$lambda$3
                @Override // rx.functions.Action0
                public final void call() {
                    TarazedSessionLogger tarazedSessionLogger;
                    tarazedSessionLogger = this.logger;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully setPermission in DMPS [dsn=");
                    outline107.append(dsn);
                    outline107.append(", permission=");
                    outline107.append(z);
                    outline107.append("].");
                    tarazedSessionLogger.i("TarazedDMPSHandler", outline107.toString());
                    this.setPersistentStorage(z);
                }
            });
        }
    }

    @Override // com.amazon.alexa.tarazed.dmps.DMPSHandler
    public void disableTarazedPermission() {
        this.logger.i(TAG, "disabling Tarazed Permission");
        if (isTarazedNotificationEnabled()) {
            updatePermissionStatus(false);
        } else {
            this.logger.i(TAG, "Tarazed Permission is not enabled yet, Skip calling DMPS");
        }
    }

    @Override // com.amazon.alexa.tarazed.dmps.DMPSHandler
    public void enableTarazedPermission() {
        this.logger.i(TAG, "enabling Tarazed Permission");
        if (!isTarazedNotificationEnabled()) {
            updatePermissionStatus(true);
            this.metricsHelper.addCount(TAG, METRIC_DMPS_SET_TARAZED_PERMISSION, 1L);
            return;
        }
        this.logger.i(TAG, "Tarazed Permission is already enabled, Skip calling DMPS");
    }

    @Override // com.amazon.alexa.tarazed.dmps.DMPSHandler
    public boolean isTarazedNotificationEnabled() {
        String storageKey = getStorageKey();
        if (storageKey != null) {
            if (this.dmpsPersistentStorage.contains(storageKey)) {
                if (this.dmpsPersistentStorage.getBoolean(storageKey)) {
                    TarazedSessionLogger tarazedSessionLogger = this.logger;
                    tarazedSessionLogger.i(TAG, "Cached status of Tarazed permission for " + storageKey + ": enabled");
                    return true;
                }
                TarazedSessionLogger tarazedSessionLogger2 = this.logger;
                tarazedSessionLogger2.i(TAG, "Cached status of Tarazed permission for " + storageKey + ": disabled");
                return false;
            }
            TarazedSessionLogger tarazedSessionLogger3 = this.logger;
            tarazedSessionLogger3.i(TAG, "Tarazed permission for " + storageKey + " is not enabled and not cached");
            return false;
        }
        this.logger.w(TAG, "Cannot check cached status of Tarazed permission due to invalid storage key");
        return false;
    }

    @Override // com.amazon.alexa.tarazed.dmps.DMPSHandler
    public void subscribe() {
        this.logger.i(TAG, "MSS subscribed to Event Bus");
        this.eventBus.getNewSubscriber().subscribeFilter(new MessageFilter() { // from class: com.amazon.alexa.tarazed.dmps.DMPSHandlerDefault$subscribe$1
            @Override // com.amazon.alexa.eventbus.api.MessageFilter
            public final boolean isMatch(@NotNull Message message) {
                boolean isTarazedEvent;
                Intrinsics.checkParameterIsNotNull(message, "message");
                isTarazedEvent = DMPSHandlerDefault.this.isTarazedEvent(message.getEventType());
                return isTarazedEvent;
            }
        }, new MessageHandler() { // from class: com.amazon.alexa.tarazed.dmps.DMPSHandlerDefault$subscribe$2
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(@NotNull Message message) {
                EventBusHandler eventBusHandler;
                Intrinsics.checkParameterIsNotNull(message, "message");
                eventBusHandler = DMPSHandlerDefault.this.eventBusHandler;
                eventBusHandler.handle(message);
            }
        });
    }
}
