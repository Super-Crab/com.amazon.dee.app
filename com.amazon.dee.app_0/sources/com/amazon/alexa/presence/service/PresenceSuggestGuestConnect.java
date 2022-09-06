package com.amazon.alexa.presence.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.presence.library.Compatibility;
import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import com.amazon.alexa.presence.service.guardrails.FeatureRefusalGuardrail;
import com.amazon.alexa.presence.service.guardrails.FrequencyGuardrail;
import com.amazon.alexa.presence.service.guardrails.PresenceFeatureEnabledGuardrail;
import com.amazon.alexa.presence.service.guardrails.VoiceProfileGuardrail;
import com.amazon.alexa.presence.service.receivers.PresenceSuggestOnCancelReceiver;
import com.amazon.alexa.presence.service.receivers.PresenceSuggestOnClickReceiver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;
/* loaded from: classes9.dex */
public class PresenceSuggestGuestConnect implements PresenceFeatureSuggestionInterface {
    private static final String ALEXA_GENERAL_NOTIFICATION_CHANNEL = "alexa_notification_channel";
    public static final String DATE_SENT = "dateSent";
    public static final String DISMISS_PRESENCE_BASED_SUGGESTION = "com.amazon.alexa.intent.action.PRESENCE_SUGGEST_DISMISSED";
    private static final String FEATURE_NAME = "GUEST_CONNECT";
    public static final String GUEST_CONNECT_DOMAIN = "roamingguest";
    private static final String GUEST_CONNECT_PRIMER_PAGE = "https://alexa.amazon.com/spa/index.html#v2/roaming/roamerEnablementPrimer?context=LANDING_PAGE_CONTEXT-jst_discovery";
    public static final String OPEN_PRESENCE_BASED_SUGGESTION = "com.amazon.alexa.intent.action.PRESENCE_SUGGEST_OPENED";
    public static final String PRESENCE_BASED_SUGGESTION = "com.amazon.alexa.intent.action.PRESENCE_SUGGEST";
    private static final String PRESENCE_EXPERIENCE_GUEST_CONNECT = "presence-experience-guestconnect";
    public static final int ROAMING_SUGGESTION_NOTIFICATION_ID = 70160;
    private static final String TAG = "com.amazon.alexa.presence.service.PresenceSuggestGuestConnect";
    private final CoralService coralService;
    private final Context ctx;
    private final DeviceInformation deviceInformation;
    private final IdentityService identityService;
    private final Locale locale;
    private final PersistentMap persistentMap;
    private final PersistentLocalStorage.PresenceDataStore presenceDataStore;

    public PresenceSuggestGuestConnect(Context context) {
        this(context, PersistentLocalStorage.getWrapper());
    }

    private NotificationChannel getAlexaGeneralNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(ALEXA_GENERAL_NOTIFICATION_CHANNEL, this.ctx.getResources().getString(R.string.presence_service_notification_general_channel_title), 2);
        notificationChannel.setShowBadge(false);
        return notificationChannel;
    }

    private GuestConnectNotifInfo getNotifInfo() {
        String str = this.persistentMap.get((Object) PRESENCE_EXPERIENCE_GUEST_CONNECT);
        if (str == null) {
            return new GuestConnectNotifInfo(0, 0L, 0L, false);
        }
        return GuestConnectNotifInfo.deserialize(str);
    }

    private void setupNotificationChannel() {
        Compatibility.ifAndroidOOrLater(new Callable() { // from class: com.amazon.alexa.presence.service.-$$Lambda$PresenceSuggestGuestConnect$gh9Jl6MdIDhcrGg5yGFDS9XEZx4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return PresenceSuggestGuestConnect.this.lambda$setupNotificationChannel$0$PresenceSuggestGuestConnect();
            }
        });
    }

    @Override // com.amazon.alexa.presence.service.PresenceFeatureSuggestionInterface
    public String getFeatureName() {
        return FEATURE_NAME;
    }

    public /* synthetic */ Object lambda$setupNotificationChannel$0$PresenceSuggestGuestConnect() throws Exception {
        ((NotificationManager) this.ctx.getSystemService(NotificationManager.class)).createNotificationChannel(getAlexaGeneralNotificationChannel());
        return null;
    }

    @Override // com.amazon.alexa.presence.service.PresenceFeatureSuggestionInterface
    public void sendPresenceFeatureSuggestNotification() {
        setupNotificationChannel();
        String string = this.ctx.getResources().getString(R.string.presence_suggest_guest_connect);
        Intent intent = new Intent(this.ctx, PresenceSuggestOnClickReceiver.class);
        intent.setAction(OPEN_PRESENCE_BASED_SUGGESTION);
        intent.setData(Uri.parse(GUEST_CONNECT_PRIMER_PAGE));
        long time = new Date().getTime();
        intent.putExtra(DATE_SENT, time);
        PendingIntent broadcast = PendingIntent.getBroadcast(this.ctx, 0, intent, 0);
        Intent intent2 = new Intent(this.ctx, PresenceSuggestOnCancelReceiver.class);
        intent2.setAction(DISMISS_PRESENCE_BASED_SUGGESTION);
        intent2.putExtra(DATE_SENT, time);
        NotificationManagerCompat.from(this.ctx).notify(ROAMING_SUGGESTION_NOTIFICATION_ID, new NotificationCompat.Builder(this.ctx, ALEXA_GENERAL_NOTIFICATION_CHANNEL).setContentText(string).setStyle(new NotificationCompat.BigTextStyle().bigText(string)).setSmallIcon(R.drawable.ic_alexaicon).setColor(this.ctx.getResources().getColor(R.color.notification_color)).setPriority(0).setVisibility(1).setContentIntent(broadcast).setDeleteIntent(PendingIntent.getBroadcast(this.ctx, 0, intent2, 0)).setShowWhen(false).setAutoCancel(true).build());
        Log.i(TAG, "Showed Guest Connect Notification");
    }

    @Override // com.amazon.alexa.presence.service.PresenceFeatureSuggestionInterface
    public boolean shouldNotify() {
        if (this.coralService != null && this.identityService != null && this.deviceInformation != null && this.presenceDataStore != null) {
            try {
                Log.i(TAG, "Checking guardrails to show Guest Connect");
                PresenceFeatureEnabledGuardrail presenceFeatureEnabledGuardrail = new PresenceFeatureEnabledGuardrail(GUEST_CONNECT_DOMAIN, this.presenceDataStore, this.ctx);
                GuestConnectNotifInfo notifInfo = getNotifInfo();
                FrequencyGuardrail frequencyGuardrail = new FrequencyGuardrail(notifInfo);
                FeatureRefusalGuardrail featureRefusalGuardrail = new FeatureRefusalGuardrail(notifInfo);
                VoiceProfileGuardrail voiceProfileGuardrail = new VoiceProfileGuardrail(this.coralService, this.identityService, this.deviceInformation, this.locale);
                if (!presenceFeatureEnabledGuardrail.checkGuardrail() || !frequencyGuardrail.checkGuardrail() || !featureRefusalGuardrail.checkGuardrail()) {
                    return false;
                }
                return voiceProfileGuardrail.checkGuardrail();
            } catch (Exception e) {
                String str = TAG;
                Log.w(str, "Guardrail checks failed with exception: " + e);
            }
        }
        return false;
    }

    public void updateStorage(boolean z, long j, long j2) {
        GuestConnectNotifInfo guestConnectNotifInfo;
        String str = this.persistentMap.get((Object) PRESENCE_EXPERIENCE_GUEST_CONNECT);
        if (str == null) {
            guestConnectNotifInfo = new GuestConnectNotifInfo(0, j, j2, z);
        } else {
            this.persistentMap.remove((Object) PRESENCE_EXPERIENCE_GUEST_CONNECT);
            guestConnectNotifInfo = new GuestConnectNotifInfo(GuestConnectNotifInfo.deserialize(str).getNumSent() + 1, j, j2, z);
        }
        this.persistentMap.put(PRESENCE_EXPERIENCE_GUEST_CONNECT, guestConnectNotifInfo.serialize());
    }

    public PresenceSuggestGuestConnect(Context context, PersistentLocalStorage.PresenceDataStore presenceDataStore) {
        this.ctx = context;
        this.persistentMap = new PersistentMap(context);
        this.identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        this.deviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline21(DeviceInformation.class);
        this.coralService = (CoralService) GeneratedOutlineSupport1.outline21(CoralService.class);
        this.locale = new Locale("en-US");
        this.presenceDataStore = presenceDataStore;
    }
}
