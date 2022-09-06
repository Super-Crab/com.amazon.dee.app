package com.amazon.alexa.accessory.notificationpublisher.directiveconsumer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentDirective;
import com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentDirectiveConsumer;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationEventManager;
import com.amazon.alexa.accessory.notificationpublisher.consumption.ReplyReadBackComponent;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.urldownloader.AudioFileDownloader;
import com.amazon.alexa.accessory.notificationpublisher.urldownloader.DownloadResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationFileHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class ZionAnnounceWithContentDirectiveConsumer extends DownloadResponseHandler implements AnnounceWithContentDirectiveConsumer {
    private static final String TAG = "ZionAnnounceWithContentDirectiveConsumer";
    private Map<String, String> announcementMap = new ConcurrentHashMap();
    private Map<String, String> contentMap = new ConcurrentHashMap();
    private BroadcastReceiver broadcastReceiver = new BroadcastDirectiveReceiver();

    /* loaded from: classes.dex */
    private class BroadcastDirectiveReceiver extends BroadcastReceiver {
        private BroadcastDirectiveReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(ZionAnnounceWithContentDirectiveConsumer.TAG, "received directive for notification uuid %s", intent.getStringExtra(DirectiveConstants.INTENT_KEY_NOTIFICATION_UUID));
            String string = getResultExtras(true).getString(DirectiveConstants.INTENT_KEY_LAST_RECEIVER);
            if (!Strings.isNullOrEmpty(string)) {
                MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
                metricsRecorder.recordCounter(MetricsConstants.FOCUS_FILTER_DIRECTIVE_ROUTE_SUCCESS_PREFIX + string);
                return;
            }
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.CONSUME_DIRECTIVE_NOTIFICATION_NOT_IN_INCOMING_MAP);
        }
    }

    private boolean cleanupIfNotificationDismissed(@NonNull String str) {
        NotificationEventManager notificationEventManager = NotificationEventManager.getInstance();
        if (notificationEventManager.isNotificationDismissed(str)) {
            String str2 = TAG;
            Log.i(str2, "cleanupIfNotificationDismissed - Notification has been dismissed " + str);
            notificationEventManager.removeDismissedNotification(str);
            notificationEventManager.removeIncomingNotification(str);
            return true;
        }
        return false;
    }

    private void consumeDirectiveForReadBack(AnnounceWithContentDirective announceWithContentDirective) {
        Log.i(TAG, "consumeDirectiveForReadBack");
        String notificationUuidFromReadBackRequestId = NotificationEventManager.getInstance().getNotificationUuidFromReadBackRequestId(announceWithContentDirective.getNotificationUuid());
        if (!ReplyReadBackComponent.getInstance().isExpectingReadBack(notificationUuidFromReadBackRequestId)) {
            Log.i(TAG, "consumeDirectiveForReadBack - Dropping directive because read back is not expected");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(MetricsConstants.CUSTOM_VALUES_KEY, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(5));
        new NotificationFileHelper(DependencyProvider.getContext()).removeReadBackFile();
        MetricsRecorder.getInstance().recordCounter(String.format(Locale.US, MetricsConstants.FETCH_START_TEMPLATE, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(5)));
        AudioFileDownloader.downloadUrl(announceWithContentDirective.getAnnouncementUri(), notificationUuidFromReadBackRequestId, 5, this, false, null, null, hashMap);
    }

    private void enqueueForConsumption(@NonNull String str) {
        Log.d(TAG, "enqueueForConsumption called");
        NotificationEventManager notificationEventManager = NotificationEventManager.getInstance();
        Log.d(TAG, "concat ContactSays audio with content audio if needed Before Enqueue");
        notificationEventManager.concatContactSaysWithContentIfNeeded(str);
        if (!cleanupIfNotificationDismissed(str) && FeatureToggleModule.getInstance().hasConnectedEnabledDevices()) {
            JSONObject andRemoveIncomingNotification = notificationEventManager.getAndRemoveIncomingNotification(str);
            if (andRemoveIncomingNotification == null) {
                return;
            }
            notificationEventManager.onNotificationDownloaded(andRemoveIncomingNotification);
            return;
        }
        notificationEventManager.removeNotificationAudioFile(str);
    }

    private boolean isValidDirective(@NonNull AnnounceWithContentDirective announceWithContentDirective) {
        return !Strings.isNullOrEmpty(announceWithContentDirective.getAnnouncementUri()) && !Strings.isNullOrEmpty(announceWithContentDirective.getContentUri()) && !Strings.isNullOrEmpty(announceWithContentDirective.getNotificationUuid());
    }

    private boolean isValidTtsRequest(@Nullable JSONObject jSONObject) {
        return jSONObject != null && !Strings.isNullOrEmpty(jSONObject.optString("requestId")) && !Strings.isNullOrEmpty(jSONObject.optString(NotificationConstants.PRIMARY_TEXT)) && DownloadResponseHandler.TYPE_TO_NAME_MAP.containsKey(Integer.valueOf(jSONObject.optInt(NotificationConstants.PRIMARY_TEXT_TYPE))) && !Strings.isNullOrEmpty(jSONObject.optString("locale")) && !Strings.isNullOrEmpty(jSONObject.optString(NotificationConstants.REQUEST_TYPE));
    }

    private boolean isValidTtsRequestWithTwoText(@Nullable JSONObject jSONObject) {
        return isValidTtsRequest(jSONObject) && !Strings.isNullOrEmpty(jSONObject.optString(NotificationConstants.SECONDARY_TEXT)) && DownloadResponseHandler.TYPE_TO_NAME_MAP.containsKey(Integer.valueOf(jSONObject.optInt(NotificationConstants.SECONDARY_TEXT_TYPE)));
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentDirectiveConsumer
    public synchronized void consumeDirective(AnnounceWithContentDirective announceWithContentDirective) throws Exception {
        Preconditions.notNull(announceWithContentDirective, "directive");
        if (!isValidDirective(announceWithContentDirective)) {
            Log.w(TAG, "consumeDirective - Announce with content directive is invalid");
            if (isReadBackDirective(announceWithContentDirective)) {
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.PROCESS_READ_BACK_DIRECTIVE_ERROR);
            }
            throw new IllegalArgumentException("Announce with content directive is invalid");
        }
        boolean isNotificationInIncomingMap = NotificationEventManager.getInstance().isNotificationInIncomingMap(announceWithContentDirective.getNotificationUuid());
        boolean isRequestInIncomingTtsMap = NotificationEventManager.getInstance().isRequestInIncomingTtsMap(announceWithContentDirective.getNotificationUuid());
        if (isReadBackDirective(announceWithContentDirective)) {
            consumeDirectiveForReadBack(announceWithContentDirective);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.PROCESS_READ_BACK_DIRECTIVE_SUCCESS);
        } else if (isRequestInIncomingTtsMap) {
            consumeDirectiveForTTSRequest(announceWithContentDirective);
        } else if (isNotificationInIncomingMap) {
            consumeDirectiveForPhoneNotifications(announceWithContentDirective);
        } else {
            Log.d(TAG, "no matching, route to top contact");
            Intent intent = new Intent(DirectiveConstants.INTENT_ACTION_ROUTE_DIRECTIVE);
            intent.putExtra(DirectiveConstants.INTENT_KEY_ANNOUNCEMENT_URI, announceWithContentDirective.getAnnouncementUri());
            intent.putExtra(DirectiveConstants.INTENT_KEY_CONTENT_URI, announceWithContentDirective.getContentUri());
            intent.putExtra(DirectiveConstants.INTENT_KEY_NOTIFICATION_UUID, announceWithContentDirective.getNotificationUuid());
            intent.putExtra(DirectiveConstants.INTENT_KEY_DIRECTIVE_NAME, announceWithContentDirective.getName());
            intent.putExtra(DirectiveConstants.INTENT_KEY_DIRECTIVE_NAMESPACE, announceWithContentDirective.getNamespace());
            DependencyProvider.getContext().sendOrderedBroadcast(intent, "com.amazon.alexa.accessory.notificationpublisher.notificationlistener", this.broadcastReceiver, null, 0, null, null);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.FOCUS_FILTER_BROADCAST_DIRECTIVE);
        }
    }

    @VisibleForTesting
    void consumeDirectiveForPhoneNotifications(AnnounceWithContentDirective announceWithContentDirective) {
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.CREATE_MOBILE_NOTIFICATION_DIRECTIVE_RECEIVED);
        if (!cleanupIfNotificationDismissed(announceWithContentDirective.getNotificationUuid())) {
            String notificationUuid = announceWithContentDirective.getNotificationUuid();
            String announcementUri = announceWithContentDirective.getAnnouncementUri();
            String contentUri = announceWithContentDirective.getContentUri();
            GeneratedOutlineSupport1.outline166("consumeDirectiveForPhoneNotifications - send download request for notification ", notificationUuid, TAG);
            this.announcementMap.put(notificationUuid, announcementUri);
            this.contentMap.put(notificationUuid, contentUri);
            AudioFileDownloader.downloadUrl(announcementUri, notificationUuid, 1, this, true);
            MetricsRecorder.getInstance().recordCounter(String.format(Locale.US, MetricsConstants.FETCH_START_TEMPLATE, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(1)));
            AudioFileDownloader.downloadUrl(contentUri, notificationUuid, 2, this, true);
            MetricsRecorder.getInstance().recordCounter(String.format(Locale.US, MetricsConstants.FETCH_START_TEMPLATE, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(2)));
            return;
        }
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.CONSUME_DIRECTIVE_NOTIFICATION_DISMISSED);
    }

    @VisibleForTesting
    void consumeDirectiveForTTSRequest(AnnounceWithContentDirective announceWithContentDirective) {
        String notificationUuid = announceWithContentDirective.getNotificationUuid();
        JSONObject andRemoveIncomingTts = NotificationEventManager.getInstance().getAndRemoveIncomingTts(notificationUuid);
        if (!isValidTtsRequest(andRemoveIncomingTts)) {
            String str = TAG;
            Log.w(str, "consumeDirective - invalid TTS request" + notificationUuid);
            return;
        }
        GeneratedOutlineSupport1.outline166("consumeDirective - send download request for tts - requestId: ", notificationUuid, TAG);
        String str2 = TAG;
        Log.d(str2, "consumeDirective - send download request for tts - " + andRemoveIncomingTts);
        HashMap outline133 = GeneratedOutlineSupport1.outline133(MetricsConstants.CUSTOM_VALUES_KEY, andRemoveIncomingTts.optString(NotificationConstants.REQUEST_TYPE, "None"));
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.CREATE_DOWNLOADABLE_SPEECH_FROM_TEXT_DIRECTIVE_RECEIVED, outline133);
        String announcementUri = announceWithContentDirective.getAnnouncementUri();
        String optString = andRemoveIncomingTts.optString(NotificationConstants.PRIMARY_TEXT);
        int optInt = andRemoveIncomingTts.optInt(NotificationConstants.PRIMARY_TEXT_TYPE);
        String optString2 = andRemoveIncomingTts.optString("locale");
        this.announcementMap.put(notificationUuid, announcementUri);
        AudioFileDownloader.downloadUrl(announcementUri, notificationUuid, optInt, this, false, optString2, optString, outline133);
        MetricsRecorder.getInstance().recordCounter(String.format(Locale.US, MetricsConstants.FETCH_START_TEMPLATE, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(Integer.valueOf(optInt))));
        if (!isValidTtsRequestWithTwoText(andRemoveIncomingTts)) {
            return;
        }
        String optString3 = andRemoveIncomingTts.optString(NotificationConstants.SECONDARY_TEXT);
        int optInt2 = andRemoveIncomingTts.optInt(NotificationConstants.SECONDARY_TEXT_TYPE);
        String contentUri = announceWithContentDirective.getContentUri();
        this.contentMap.put(notificationUuid, contentUri);
        if (optInt2 == 4) {
            optString3 = andRemoveIncomingTts.optString(NotificationConstants.NOTIFICATION_UUID);
        }
        AudioFileDownloader.downloadUrl(contentUri, notificationUuid, optInt2, this, false, optString2, optString3, outline133);
        MetricsRecorder.getInstance().recordCounter(String.format(Locale.US, MetricsConstants.FETCH_START_TEMPLATE, DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(Integer.valueOf(optInt2))), outline133);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00bd A[Catch: all -> 0x0121, TryCatch #0 {, blocks: (B:6:0x0009, B:8:0x0026, B:10:0x002e, B:24:0x00bd, B:12:0x0048, B:14:0x0065, B:16:0x006d, B:18:0x0086, B:21:0x00a8, B:25:0x00da, B:29:0x00f7, B:31:0x0103, B:32:0x0116), top: B:38:0x0005 }] */
    @Override // com.amazon.alexa.accessory.notificationpublisher.urldownloader.DownloadResponseHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void handleDownloadResponse(boolean r6, java.lang.String r7, int r8, boolean r9, java.util.Map<java.lang.String, java.lang.Object> r10) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.directiveconsumer.ZionAnnounceWithContentDirectiveConsumer.handleDownloadResponse(boolean, java.lang.String, int, boolean, java.util.Map):void");
    }

    @VisibleForTesting
    boolean isReadBackDirective(AnnounceWithContentDirective announceWithContentDirective) {
        return announceWithContentDirective.getNotificationUuid().contains(NotificationEventManager.REPLY_READ_BACK_UUID);
    }
}
