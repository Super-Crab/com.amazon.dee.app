package com.amazon.deecomms.notifications.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.accessories.monitors.AlexaMessageNotificationMonitor;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.CommsActivityMonitor;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.common.service.CommsJobIntentService;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.common.util.provider.MMSDKManager;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.contacts.util.GetOrCreateContact;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.model.ConversationNotification;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.messaging.model.payload.MessagePayload;
import com.amazon.deecomms.messaging.model.payload.SharingMessagePayload;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.notifications.InboundAnnouncementCoordinator;
import com.amazon.deecomms.notifications.model.announcement.AnnouncementPushNotification;
import com.amazon.deecomms.notifications.util.NotificationUtils;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class CreateNotificationService extends CommsJobIntentService {
    private static final String ANNOUNCEMENT_PAYLOAD_ANNOUNCEMENT_ID = "announcementId";
    private static final String ANNOUNCEMENT_PAYLOAD_ANNOUNCEMENT_TEXT = "announcementText";
    private static final String ANNOUNCEMENT_PAYLOAD_MEDIA_ID = "mediaId";
    private static final String ANNOUNCEMENT_PAYLOAD_ORIGIN_TIME = "createTimeUtc";
    private static final String ANNOUNCEMENT_PAYLOAD_SOURCE_NAME = "sourceName";
    private static final String ANNOUNCEMENT_PAYLOAD_TRANSCRIPT = "transcript";
    @Inject
    CommsActivityMonitor activityMonitor;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    CommsNotificationManager commsNotificationManager;
    private final JacksonJSONConverter jsonConverter = new JacksonJSONConverter();
    @Inject
    InboundAnnouncementCoordinator mAnnouncementCoordinator;
    @Inject
    MetricsService mMetricsService;
    @Inject
    ModeSwitchHelper modeSwitchHelper;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CreateNotificationService.class);
    private static final int JOB_ID = CommsJobIntentService.generateJobId(CreateNotificationService.class);

    @VisibleForTesting
    public CreateNotificationService(CommsNotificationManager commsNotificationManager) {
        this.commsNotificationManager = commsNotificationManager;
    }

    private void addMessageContentToPayload(Message message) {
        MessagePayload payload = message.getPayload();
        if (payload instanceof SharingMessagePayload) {
            ((SharingMessagePayload) payload).extractMetadataFromMessage(message);
        }
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, CreateNotificationService.class, JOB_ID, intent);
    }

    private Date parseDateTime(String str) {
        return ISODateTimeFormat.dateTime().parseDateTime(str).toDate();
    }

    private void processMessageNotification(String str, String str2) {
        String commsId = this.commsIdentityManager.getCommsId("processMessageNotification", false);
        String homeGroupId = this.commsIdentityManager.getHomeGroupId("processMessageNotification", false);
        Message message = (Message) this.jsonConverter.fromJson(str2, Message.class);
        addMessageContentToPayload(message);
        if (supressedNotification(message, commsId, str)) {
            return;
        }
        if ("event/call".equals(message.getType())) {
            NotificationUtils.logDebug(LOG, str, "Not creating notification as event type is event/call");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_PROCESSED_METRIC);
        } else if (!TextUtils.isEmpty(commsId) && !TextUtils.isEmpty(homeGroupId)) {
            if (!this.modeSwitchHelper.isMultiModalMode() && !MMSDKManager.isCommsOnLenovoSmartTabletEnabled(getApplicationContext())) {
                List<String> participantsInConversation = new MessagingProviderWrapper(this, commsId, homeGroupId).getParticipantsInConversation(message.getConversationId());
                if (homeGroupId.equals(message.getSenderCommsId()) || commsId.equals(message.getSenderCommsId())) {
                    ArrayList arrayList = new ArrayList(participantsInConversation);
                    if (arrayList.isEmpty()) {
                        NotificationUtils.logWarning(LOG, str, "Did not create notification because message was from ourself or homegroup but the conversation did not exist");
                        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_PROCESSED_METRIC);
                        return;
                    }
                    arrayList.remove(homeGroupId);
                    arrayList.remove(commsId);
                    if (arrayList.size() > 0) {
                        NotificationUtils.logWarning(LOG, str, "Did not create notification because hg msg to other user");
                        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_PROCESSED_METRIC);
                        return;
                    } else if ("event/missed-call".equals(message.getType()) && commsId.equals(message.getSenderCommsId())) {
                        NotificationUtils.logWarning(LOG, str, "Did not create notification because missed call from user to self or homegroup");
                        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_DROPPED_METRIC);
                        return;
                    }
                }
                String senderCommsId = message.getSenderCommsId();
                ContactsProviderUtils.removeUngettableCommsIDIfObtained(this, new String[]{senderCommsId});
                ContactEntry contactEntry = new GetOrCreateContact("CreateNotificationService.onHandleIntent").getContactEntry(senderCommsId);
                FullContactName fullContactName = contactEntry != null ? contactEntry.getFullContactName() : null;
                String fullName = ContactUtils.getFullName(fullContactName);
                if (!homeGroupId.equalsIgnoreCase(message.getSenderCommsId()) && participantsInConversation.size() == 1 && homeGroupId.equalsIgnoreCase(participantsInConversation.get(0))) {
                    String partialName = ContactUtils.getPartialName(fullContactName);
                    String string = getResources().getString(R.string.to_home);
                    Object[] objArr = new Object[1];
                    if (TextUtils.isEmpty(partialName)) {
                        partialName = getResources().getString(R.string.unknown_contact);
                    }
                    objArr[0] = partialName;
                    fullName = MessageFormat.format(string, objArr);
                }
                AlexaMessageNotificationMonitor.onMessageNotificationStatusChanged(true);
                this.commsNotificationManager.addNotificationMessage(new ConversationNotification(message, fullName));
                NotificationUtils.logInfo(LOG, str, "CommsNotificationManager requested addNotificationMessage");
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_PROCESSED_METRIC);
                return;
            }
            NotificationUtils.logDebug(LOG, str, "Not creating notification as device is in multimodal mode");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_PROCESSED_METRIC);
        } else {
            NotificationUtils.logWarning(LOG, str, "Comms id or homegroup id is null. returning");
        }
    }

    private boolean supressedNotification(@NonNull Message message, @Nullable String str, @Nullable String str2) {
        if (!Utils.getBooleanPreferenceFromSharedPrefs(getApplicationContext(), Constants.IS_APP_IN_FOREGROUND, false) || (!NotificationUtils.isNotificationSuppressed(message.getConversationId()) && !NotificationUtils.isNotificationSuppressed(message.getSenderCommsId()))) {
            return false;
        }
        if (!message.getSenderCommsId().equals(str)) {
            Utils.vibrate(this, 400L);
        }
        NotificationUtils.logInfo(LOG, str2, "Notification suppressed");
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_PROCESSED_SILENT);
        return true;
    }

    @VisibleForTesting
    protected JSONObject getMessageAsJson(String str) throws JSONException {
        return new JSONObject(str);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            LOG.w("Unable to create notification as the amznMessage payload was missing");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_DROPPED_METRIC);
            return;
        }
        String string = extras.getString(Constants.AMP_KEY);
        if (TextUtils.isEmpty(string)) {
            LOG.w("Unable to create notification as the amznMessage payload was missing");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_DROPPED_METRIC);
            return;
        }
        String string2 = extras.getString(Constants.GCM_MESSAGE_ID_KEY);
        if (extras.getString("target", "").equalsIgnoreCase(Constants.NOTIFICATION_TARGET_ANNOUNCEMENT)) {
            processAnnouncementNotification(string2, string);
        } else {
            processMessageNotification(string2, string);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0068 A[Catch: Exception -> 0x00aa, TryCatch #0 {Exception -> 0x00aa, blocks: (B:3:0x0007, B:5:0x000e, B:7:0x0014, B:9:0x001b, B:12:0x0027, B:14:0x0031, B:21:0x0043, B:23:0x0049, B:28:0x005d, B:30:0x0068, B:32:0x0073, B:34:0x0090, B:38:0x009c, B:27:0x0052, B:17:0x0039, B:20:0x003f), top: B:43:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0090 A[Catch: Exception -> 0x00aa, TRY_LEAVE, TryCatch #0 {Exception -> 0x00aa, blocks: (B:3:0x0007, B:5:0x000e, B:7:0x0014, B:9:0x001b, B:12:0x0027, B:14:0x0031, B:21:0x0043, B:23:0x0049, B:28:0x005d, B:30:0x0068, B:32:0x0073, B:34:0x0090, B:38:0x009c, B:27:0x0052, B:17:0x0039, B:20:0x003f), top: B:43:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0097  */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    com.amazon.deecomms.notifications.model.announcement.AnnouncementPushNotification parseAnnouncementPushNotification(java.lang.String r13) {
        /*
            r12 = this;
            java.lang.String r0 = "mediaId"
            java.lang.String r1 = "createTimeUtc"
            java.lang.String r2 = "transcript"
            org.json.JSONObject r13 = r12.getMessageAsJson(r13)     // Catch: java.lang.Exception -> Laa
            java.lang.String r3 = "payload"
            org.json.JSONObject r3 = r13.getJSONObject(r3)     // Catch: java.lang.Exception -> Laa
            java.lang.String r4 = "announcementId"
            java.lang.String r6 = r13.getString(r4)     // Catch: java.lang.Exception -> Laa
            java.lang.String r4 = "sourceName"
            java.lang.String r7 = r3.getString(r4)     // Catch: java.lang.Exception -> Laa
            boolean r4 = r3.has(r2)     // Catch: java.lang.Exception -> Laa
            java.lang.String r5 = "null"
            if (r4 == 0) goto L3d
            java.lang.String r2 = r3.getString(r2)     // Catch: java.lang.Exception -> Laa
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> Laa
            if (r4 != 0) goto L37
            boolean r4 = r2.equalsIgnoreCase(r5)     // Catch: java.lang.Exception -> Laa
            if (r4 == 0) goto L43
        L37:
            java.lang.String r4 = "comms.notif.ann.audio.missingTranscript"
            com.amazon.deecomms.common.metrics.MetricsHelper.recordSingleOccurrenceOperational(r4)     // Catch: java.lang.Exception -> Laa
            goto L43
        L3d:
            java.lang.String r2 = "announcementText"
            java.lang.String r2 = r3.getString(r2)     // Catch: java.lang.Exception -> Laa
        L43:
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> Laa
            if (r4 != 0) goto L52
            boolean r4 = r2.equalsIgnoreCase(r5)     // Catch: java.lang.Exception -> Laa
            if (r4 == 0) goto L50
            goto L52
        L50:
            r8 = r2
            goto L5d
        L52:
            android.content.Context r2 = r12.getApplicationContext()     // Catch: java.lang.Exception -> Laa
            int r4 = com.amazon.deecomms.R.string.notification_announcement_no_content     // Catch: java.lang.Exception -> Laa
            java.lang.String r2 = r2.getString(r4)     // Catch: java.lang.Exception -> Laa
            goto L50
        L5d:
            java.util.Date r2 = new java.util.Date     // Catch: java.lang.Exception -> Laa
            r2.<init>()     // Catch: java.lang.Exception -> Laa
            boolean r4 = r13.has(r1)     // Catch: java.lang.Exception -> Laa
            if (r4 == 0) goto L72
            java.lang.String r13 = r13.getString(r1)     // Catch: java.lang.Exception -> Laa
            java.util.Date r13 = r12.parseDateTime(r13)     // Catch: java.lang.Exception -> Laa
            r9 = r13
            goto L73
        L72:
            r9 = r2
        L73:
            java.util.Date r13 = new java.util.Date     // Catch: java.lang.Exception -> Laa
            r13.<init>()     // Catch: java.lang.Exception -> Laa
            long r1 = r13.getTime()     // Catch: java.lang.Exception -> Laa
            r4 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r4
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r1 = r1 % r4
            int r13 = (int) r1     // Catch: java.lang.Exception -> Laa
            int r13 = r13 + (-1000)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch: java.lang.Exception -> Laa
            boolean r1 = r3.has(r0)     // Catch: java.lang.Exception -> Laa
            if (r1 == 0) goto L97
            java.lang.String r0 = r3.getString(r0)     // Catch: java.lang.Exception -> Laa
            java.lang.String r1 = "announcement/audio"
            goto L9b
        L97:
            java.lang.String r0 = ""
            java.lang.String r1 = "announcement/text"
        L9b:
            r11 = r1
            com.amazon.deecomms.notifications.model.announcement.AnnouncementPushNotification r1 = new com.amazon.deecomms.notifications.model.announcement.AnnouncementPushNotification     // Catch: java.lang.Exception -> Laa
            int r10 = r13.intValue()     // Catch: java.lang.Exception -> Laa
            r5 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Exception -> Laa
            r1.setMediaId(r0)     // Catch: java.lang.Exception -> Laa
            return r1
        Laa:
            r13 = move-exception
            com.amazon.comms.log.CommsLogger r0 = com.amazon.deecomms.notifications.service.CreateNotificationService.LOG
            java.lang.String r1 = "Unable to parse the announcement notification"
            r0.w(r1, r13)
            r13 = 0
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.notifications.service.CreateNotificationService.parseAnnouncementPushNotification(java.lang.String):com.amazon.deecomms.notifications.model.announcement.AnnouncementPushNotification");
    }

    @VisibleForTesting
    protected void processAnnouncementNotification(String str, String str2) {
        AnnouncementPushNotification parseAnnouncementPushNotification = parseAnnouncementPushNotification(str2);
        if (parseAnnouncementPushNotification == null) {
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_ERRORED_METRIC);
            return;
        }
        this.commsNotificationManager.addAnnouncementPushNotification(parseAnnouncementPushNotification);
        NotificationUtils.logInfo(LOG, str, "CommsNotificationManager requested addNotificationMessage for an annnouncement");
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_PROCESSED_METRIC);
    }

    @VisibleForTesting
    public CreateNotificationService(CommsNotificationManager commsNotificationManager, InboundAnnouncementCoordinator inboundAnnouncementCoordinator, MetricsService metricsService) {
        this.commsNotificationManager = commsNotificationManager;
        this.mAnnouncementCoordinator = inboundAnnouncementCoordinator;
        this.mMetricsService = metricsService;
    }

    public CreateNotificationService() {
        CommsDaggerWrapper.getComponent().inject(this);
    }
}
