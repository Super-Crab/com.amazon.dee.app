package com.amazon.alexa.accessory.notificationpublisher.consumption;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.AlexaService;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler;
import com.amazon.alexa.accessory.notificationpublisher.bluetooth.BluetoothA2dpConnectionHandler;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.AnnounceWithContentRequestBody;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.AnnounceWithContentRequestSender;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.AnnounceWithContentResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.CreateDownloadableSpeechFromTextRequestBody;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.CreateDownloadableSpeechFromTextRequestSender;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.CreateDownloadableSpeechFromTextResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.urldownloader.DownloadResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.utils.BaseFileHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.DownloadableAudioFileHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.LRUHashMap;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationFileHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class NotificationEventManager extends BaseComponent implements AnnounceWithContentResponseHandler, CreateDownloadableSpeechFromTextResponseHandler {
    private static final long DEFAULT_AMNS_THROTTLE_TIME_MS = TimeUnit.SECONDS.toMillis(1);
    private static final long DEFAULT_DUPLICATE_POSTTIME_GAP = TimeUnit.SECONDS.toMillis(5);
    private static final int DEFAULT_RECENT_NOTIFICATION_LENGTH = 10;
    public static final int EVENT_NOTIFICATION_NEW = 1;
    public static final int EVENT_NOTIFICATION_REMOVE = 2;
    public static final int EVENT_NOTIFICATION_REMOVE_ALL = 3;
    public static final String REPLY_READ_BACK_UUID = "reply_read_back";
    private static final String TAG = "NotificationEventManager";
    private static NotificationEventManager instance;
    private volatile long amnsLastRequestStartTimestamp;
    private final Runnable amnsProcessRequestQueueRunnable;
    private volatile AtomicInteger amnsRequestCounter;
    private Handler amnsRequestHandler;
    private final Queue<JSONObject> amnsRequestQueue;
    private HandlerThread amnsRequestQueueProcessorThread;
    private final Set<String> dismissedNotificationsSet;
    private final Map<String, JSONObject> incomingNotificationsMap;
    private Map<String, JSONObject> incomingTtsMap;
    private boolean isFirstAttemptForAlexaServicesConnection;
    private final Map<String, String> recentCalenderNotificationMap;
    private final List<JSONObject> recentNotificationList;
    private ImmutableSet<String> setOfAppsThatRepeatOriginalMessageOnReply;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class CreateMobileNotificationAsync implements Runnable {
        private final JSONObject notification;

        @Override // java.lang.Runnable
        public void run() {
            NotificationEventManager.this.createMobileNotification(this.notification);
        }

        private CreateMobileNotificationAsync(@NonNull JSONObject jSONObject) {
            this.notification = jSONObject;
        }
    }

    private NotificationEventManager() {
        super(1);
        this.incomingNotificationsMap = new ConcurrentHashMap();
        this.incomingTtsMap = new ConcurrentHashMap();
        this.dismissedNotificationsSet = new HashSet();
        this.recentNotificationList = new ArrayList();
        this.recentCalenderNotificationMap = new LRUHashMap(100);
        this.amnsRequestCounter = new AtomicInteger(0);
        this.amnsLastRequestStartTimestamp = 0L;
        this.amnsProcessRequestQueueRunnable = new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationEventManager.1
            @Override // java.lang.Runnable
            public void run() {
                NotificationEventManager.this.processNextCreateMobileNotificationRequest();
                NotificationEventManager.this.amnsRequestHandler.postDelayed(NotificationEventManager.this.amnsProcessRequestQueueRunnable, NotificationEventManager.DEFAULT_AMNS_THROTTLE_TIME_MS);
            }
        };
        this.amnsRequestQueue = new LinkedList();
        this.isFirstAttemptForAlexaServicesConnection = true;
        this.setOfAppsThatRepeatOriginalMessageOnReply = new ImmutableSet.Builder().mo7849add((ImmutableSet.Builder) "com.oneplus.mms").mo7852build();
    }

    private boolean addAllDismissedNotifications() {
        try {
            Iterator<String> notificationKeysIterator = NotificationQueue.getInstance().getNotificationKeysIterator();
            Log.i(TAG, "addAllDismissedNotifications - Adding all notifications to dismissedNotificationsSet");
            while (notificationKeysIterator.hasNext()) {
                String next = notificationKeysIterator.next();
                if (!Strings.isNullOrEmpty(next)) {
                    this.dismissedNotificationsSet.add(next);
                }
            }
            return true;
        } catch (Exception e) {
            Log.w(TAG, "addAllDismissedNotifications - Exception adding notifications to set.", e);
            return false;
        }
    }

    private boolean addDismissedNotification(@NonNull JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("uuid");
            if (Strings.isNullOrEmpty(string)) {
                return false;
            }
            String str = TAG;
            Log.i(str, "addDismissedNotification - Adding notification UUID - " + string);
            this.dismissedNotificationsSet.add(string);
            return true;
        } catch (JSONException e) {
            Log.w(TAG, "addDismissedNotification - Exception adding notification to set.", e);
            return false;
        }
    }

    private boolean addIncomingNotification(@NonNull JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("uuid");
            if (Strings.isNullOrEmpty(string)) {
                return false;
            }
            String str = TAG;
            Log.i(str, "addIncomingNotification - Adding notification UUID - " + string);
            this.incomingNotificationsMap.put(string, jSONObject);
            return true;
        } catch (JSONException e) {
            Log.w(TAG, "addIncomingNotification - Exception adding notification to map ", e);
            return false;
        }
    }

    private boolean createAndSendAmnsRequest(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        if (!Strings.isNullOrEmpty(str) && !Strings.isNullOrEmpty(str2) && !Strings.isNullOrEmpty(str3) && !Strings.isNullOrEmpty(str4)) {
            String str5 = TAG;
            Log.i(str5, "createAndSendAmnsRequest " + str);
            MetricsRecorder.getInstance().startTimer("FocusFilter_createMobileNotification_latency", str);
            MetricsRecorder.getInstance().startTimer(MetricsConstants.NOTIFICATION_NETWORK_LATENCY, str);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.NOTIFICATION_NETWORK_START);
            if (str2.contains(REPLY_READ_BACK_UUID)) {
                MetricsRecorder.getInstance().startTimer(MetricsConstants.REPLY_READBACK_NETWORK_LATENCY, str2);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACK_FETCH_START);
            }
            try {
                AlexaService.wakeUp(DependencyProvider.getContext());
                AnnounceWithContentRequestBody build = new AnnounceWithContentRequestBody.Builder().setAnnouncement(str3).setDetailedContent(str4).setNotificationId(str2).setDeviceType(DependencyProvider.getDeviceType()).setDeviceSerialNumber(DependencyProvider.getSerialNumber()).build();
                String str6 = TAG;
                Log.d(str6, "createAndSendAmnsRequest - Request payload " + build.toString());
                AnnounceWithContentRequestSender.createAndSendAnnounceWithContentRequest(build, this);
                return true;
            } catch (Exception e) {
                Log.e(TAG, "createAndSendAmnsRequest - Exception when sending request: ", e);
                MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_error");
                MetricsRecorder.getInstance().cancelTimer("FocusFilter_createMobileNotification_latency", str);
                MetricsRecorder.getInstance().cancelTimer(MetricsConstants.NOTIFICATION_NETWORK_LATENCY, str);
                if (str2.contains(REPLY_READ_BACK_UUID)) {
                    MetricsRecorder.getInstance().cancelTimer(MetricsConstants.REPLY_READBACK_NETWORK_LATENCY, str2);
                }
                if (str2.contains(REPLY_READ_BACK_UUID)) {
                    return false;
                }
                removeDismissedNotification(str);
                removeIncomingNotification(str);
                return false;
            }
        }
        Log.w(TAG, "createAndSendAmnsRequest - Invalid input, return");
        return false;
    }

    private void fetchContactSaysAudioIfNeeded(JSONObject jSONObject) throws Exception {
        Log.i(TAG, "fetchContactSaysAudioIfNeeded");
        if (!jSONObject.optBoolean(NotificationConstants.IS_DETAILED_CONTENT_WITHOUT_PREFIX, false) || Strings.isNullOrEmpty(jSONObject.optString(NotificationConstants.CONTENT_PREFIX))) {
            return;
        }
        DownloadableAudioFileHelper downloadableAudioFileHelper = new DownloadableAudioFileHelper(DependencyProvider.getContext(), jSONObject.getString(NotificationConstants.CONTENT_PREFIX), NotificationConstants.DEFAULT_LOCALE.toString(), DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(3));
        if (downloadableAudioFileHelper.doesFileExists() && !downloadableAudioFileHelper.aboutToPastRetentionPeriod()) {
            Log.i(TAG, "fetchContactSaysAudioIfNeeded - contact says audio file already exists.");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.CONTACT_SAYS_FILE_EXISTS);
            return;
        }
        Log.i(TAG, "fetchContactSaysAudioIfNeeded - contact says audio file not exists.");
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.CONTACT_SAYS_FILE_NOT_EXIST);
        JSONObject createContactSaysTtsRequest = createContactSaysTtsRequest(jSONObject);
        if (createContactSaysTtsRequest == null || !addIncomingTtsRequest(createContactSaysTtsRequest)) {
            return;
        }
        createDownloadableSpeechFromText(createContactSaysTtsRequest);
    }

    public static synchronized NotificationEventManager getInstance() {
        NotificationEventManager notificationEventManager;
        synchronized (NotificationEventManager.class) {
            if (instance == null) {
                instance = new NotificationEventManager();
                Log.i(TAG, "NotificationEventManager - Setting up AMNS throttling");
                instance.amnsRequestQueueProcessorThread = new HandlerThread("AMNSThrottleThread");
                instance.amnsRequestQueueProcessorThread.start();
                instance.amnsRequestHandler = new Handler(instance.amnsRequestQueueProcessorThread.getLooper());
                instance.amnsRequestHandler.post(instance.amnsProcessRequestQueueRunnable);
            }
            notificationEventManager = instance;
        }
        return notificationEventManager;
    }

    private long getScheduleDelayTime() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = DEFAULT_AMNS_THROTTLE_TIME_MS;
        if (currentTimeMillis - this.amnsLastRequestStartTimestamp < j) {
            return j - (currentTimeMillis - this.amnsLastRequestStartTimestamp);
        }
        return 0L;
    }

    private boolean isDuplicatedNotification(@NonNull JSONObject jSONObject) {
        boolean equals = jSONObject.optString("type").equals(ProcessNotificationModule.NotificationType.COMMS.name());
        Log.i(TAG, "isDuplicatedNotification - is COMMS app? " + equals);
        if (!equals) {
            boolean equals2 = jSONObject.optString(BaseCustomAppParser.CUSTOM_GENERIC_APP_CATEGORY).equals(BaseCustomAppParser.CALENDAR_APP_CATEGORY);
            boolean equals3 = jSONObject.optString(BaseCustomAppParser.NOTIFICATION_STYLE).equals(BaseCustomAppParser.NOTIFICATION_STYLE_INBOX);
            Log.i(TAG, "isDuplicatedNotification - is Calender app? " + equals2);
            Log.i(TAG, "isDuplicatedNotification - is InBoxStyle? " + equals3);
            if (equals2 && !equals3) {
                String optString = jSONObject.optString(NotificationConstants.DETAILED_CONTENT);
                String optString2 = jSONObject.optString("uuid");
                if (this.recentCalenderNotificationMap.containsKey(optString)) {
                    Log.i(TAG, String.format("isDuplicatedNotification - Calender notification %s is a duplicate notification of %s.", optString2, this.recentCalenderNotificationMap.get(optString)));
                    this.recentCalenderNotificationMap.put(optString, optString2);
                    return true;
                }
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isDuplicatedNotification - Calender notification from ");
                outline107.append(jSONObject.optString("packageIdentifier"));
                outline107.append(" is NOT duplicated notification");
                Log.i(str, outline107.toString());
                this.recentCalenderNotificationMap.put(optString, optString2);
                return false;
            }
            for (int i = 0; i < this.recentNotificationList.size(); i++) {
                JSONObject jSONObject2 = this.recentNotificationList.get(i);
                if (jSONObject.optString(Constants.BUNDLE_KEY_NOTIFICATION_ID).equals(jSONObject2.optString(Constants.BUNDLE_KEY_NOTIFICATION_ID))) {
                    if (jSONObject.optString(NotificationConstants.DETAILED_CONTENT).equals(jSONObject2.optString(NotificationConstants.DETAILED_CONTENT)) && jSONObject.optString(BaseCustomAppParser.INBOX_TEXT_LINES).equals(jSONObject2.optString(BaseCustomAppParser.INBOX_TEXT_LINES)) && jSONObject.optLong("postTime") - jSONObject2.optLong("postTime") < DEFAULT_DUPLICATE_POSTTIME_GAP) {
                        String str2 = TAG;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("isDuplicatedNotification - notification from ");
                        outline1072.append(jSONObject.optString("packageIdentifier"));
                        outline1072.append(" is duplicated notification");
                        Log.i(str2, outline1072.toString());
                        String str3 = TAG;
                        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("isDuplicatedNotification - duplicated notification: ");
                        outline1073.append(jSONObject.toString());
                        Log.d(str3, outline1073.toString());
                        this.recentNotificationList.remove(i);
                        this.recentNotificationList.add(jSONObject);
                        return true;
                    } else if (equals3 && BaseCustomAppParser.NOTIFICATION_STYLE_INBOX.equals(jSONObject2.optString(BaseCustomAppParser.NOTIFICATION_STYLE))) {
                        String str4 = TAG;
                        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("isDuplicatedNotification - notification from ");
                        outline1074.append(jSONObject.optString("packageIdentifier"));
                        outline1074.append(" is NOT duplicated notification");
                        Log.i(str4, outline1074.toString());
                        String str5 = TAG;
                        StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("isDuplicatedNotification - old inbox style notification removed: ");
                        outline1075.append(jSONObject2.toString());
                        Log.d(str5, outline1075.toString());
                        this.recentNotificationList.remove(i);
                        this.recentNotificationList.add(jSONObject);
                        return false;
                    }
                }
            }
            String str6 = TAG;
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("isDuplicatedNotification - notification from ");
            outline1076.append(jSONObject.optString("packageIdentifier"));
            outline1076.append(" is NOT duplicated notification");
            Log.i(str6, outline1076.toString());
            if (this.recentNotificationList.size() >= 10) {
                String str7 = TAG;
                StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("isDuplicatedNotification - recent notification list size is ");
                outline1077.append(this.recentNotificationList.size());
                outline1077.append(", remove the earliest notification");
                Log.i(str7, outline1077.toString());
                this.recentNotificationList.remove(0);
            }
            this.recentNotificationList.add(jSONObject);
        }
        return false;
    }

    private boolean isValidNewNotification(@Nullable JSONObject jSONObject) {
        return isValidRemovedNotification(jSONObject) && !Strings.isNullOrEmpty(jSONObject.optString(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT)) && !Strings.isNullOrEmpty(jSONObject.optString(NotificationConstants.DETAILED_CONTENT)) && jSONObject.has("isInvitation") && !Strings.isNullOrEmpty(jSONObject.optString("type"));
    }

    private boolean isValidRemovedNotification(@Nullable JSONObject jSONObject) {
        return jSONObject != null && !Strings.isNullOrEmpty(jSONObject.optString("uuid")) && !Strings.isNullOrEmpty(jSONObject.optString("packageIdentifier")) && !Strings.isNullOrEmpty(jSONObject.optString(SettingsStorageModule.FILTER_SETTINGS_APP_NAME_KEY));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void processNextCreateMobileNotificationRequest() {
        if (!this.amnsRequestQueue.isEmpty()) {
            int andIncrement = this.amnsRequestCounter.getAndIncrement();
            String str = TAG;
            Log.i(str, "processNextCreateMobileNotificationRequest - Old counter = " + andIncrement + " New Counter = " + this.amnsRequestCounter.get());
            JSONObject poll = this.amnsRequestQueue.poll();
            long scheduleDelayTime = getScheduleDelayTime();
            if (scheduleDelayTime > 0) {
                String str2 = TAG;
                Log.i(str2, "processNextCreateMobileNotificationRequest - Throttle time limitnot met. Issuing a runnable after " + scheduleDelayTime);
                scheduleCreateMobileNotificationTask(poll, scheduleDelayTime);
            } else {
                Log.i(TAG, "processNextCreateMobileNotificationRequest - Sending new request immediately");
                startCreateMobileNotificationAsyncTask(poll);
            }
        }
    }

    private void recordMetrics(String str, String str2) {
        HashMap hashMap = new HashMap();
        if (Strings.isNullOrEmpty(str2)) {
            str2 = "None";
        }
        hashMap.put(MetricsConstants.CUSTOM_VALUES_KEY, str2);
        MetricsRecorder.getInstance().recordCounter(str, hashMap);
    }

    private void recordMetricsForNoA2dpConnectionNotificationDropped() {
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.NO_ACTIVE_A2DP_NOTIFICATION_DROPPED);
    }

    private void recordMetricsForQuietModeNotificationDropped() {
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.QUIET_MODE_NOTIFICATION_DROPPED);
    }

    public static synchronized void releaseInstance() {
        synchronized (NotificationEventManager.class) {
            instance.amnsRequestHandler.removeCallbacks(instance.amnsProcessRequestQueueRunnable);
            instance.amnsRequestQueueProcessorThread.quit();
            instance = null;
        }
    }

    private void scheduleCreateMobileNotificationTask(JSONObject jSONObject, long j) {
        String str = TAG;
        Log.i(str, "scheduleCreateMobileNotificationTask with delay = " + j);
        this.amnsRequestHandler.postDelayed(new CreateMobileNotificationAsync(jSONObject), j);
    }

    private synchronized void startThrottledCreateMobileNotificationTask(JSONObject jSONObject) {
        Log.d(TAG, "startThrottledCreateMobileNotificationTask");
        long scheduleDelayTime = getScheduleDelayTime();
        if (this.amnsRequestCounter.get() == 0 && scheduleDelayTime == 0) {
            this.amnsRequestCounter.incrementAndGet();
            Log.i(TAG, "startThrottledCreateMobileNotificationTask - No pending requests");
            startCreateMobileNotificationAsyncTask(jSONObject);
        } else {
            Log.i(TAG, "startThrottledCreateMobileNotificationTask - Add to queue");
            this.amnsRequestQueue.offer(jSONObject);
        }
    }

    public boolean addIncomingTtsRequest(@NonNull JSONObject jSONObject) {
        try {
            String str = NotificationConstants.TTS_REQUEST_ID_PREFIX + UUID.randomUUID().toString();
            jSONObject.put("requestId", str);
            this.incomingTtsMap.put(str, jSONObject);
            return true;
        } catch (JSONException e) {
            Log.w(TAG, "addIncomingTtsRequest - Exception adding request to map ", e);
            return false;
        }
    }

    public void clearIncomingNotifications() {
        Log.i(TAG, "clearIncomingNotifications");
        for (String str : this.incomingNotificationsMap.keySet()) {
            addDismissedNotification(getAndRemoveIncomingNotification(str));
        }
    }

    public void concatContactSaysWithContentIfNeeded(String str) {
        try {
            NotificationFileHelper notificationFileHelper = new NotificationFileHelper(DependencyProvider.getContext(), str);
            JSONObject jSONObject = this.incomingNotificationsMap.get(str);
            if (!jSONObject.optBoolean(NotificationConstants.IS_DETAILED_CONTENT_WITHOUT_PREFIX, false) || Strings.isNullOrEmpty(jSONObject.optString(NotificationConstants.CONTENT_PREFIX)) || notificationFileHelper.doesFileExists(notificationFileHelper.getMergedContentFilePath())) {
                return;
            }
            BaseFileHelper.concatTwoAudioFilesToNewPath(new DownloadableAudioFileHelper(DependencyProvider.getContext(), jSONObject.getString(NotificationConstants.CONTENT_PREFIX), NotificationConstants.DEFAULT_LOCALE.toString(), DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(3)).getFile(), notificationFileHelper.getContentFile(), notificationFileHelper.getMergedContentFilePath());
        } catch (Exception e) {
            Log.e(TAG, "concatContactSaysWithContentIfNeeded failed with exception", e);
        }
    }

    public JSONObject createContactSaysTtsRequest(@NonNull JSONObject jSONObject) throws JSONException {
        JSONObject put = new JSONObject().put(NotificationConstants.PRIMARY_TEXT, jSONObject.optString(NotificationConstants.CONTENT_PREFIX, null)).put(NotificationConstants.SECONDARY_TEXT, jSONObject.optString(NotificationConstants.CONTENT_WITH_PREFIX, null)).put(NotificationConstants.PRIMARY_TEXT_TYPE, 3).put(NotificationConstants.SECONDARY_TEXT_TYPE, 4).put(NotificationConstants.NOTIFICATION_UUID, jSONObject.optString("uuid")).put("locale", NotificationConstants.DEFAULT_LOCALE).put("notification", jSONObject).put(NotificationConstants.REQUEST_TYPE, MetricsConstants.CONTACT_SAYS);
        String str = TAG;
        Log.d(str, "createContactSaysTtsRequest: " + put);
        return put;
    }

    public void createDownloadableSpeechFromText(@NonNull JSONObject jSONObject) throws Exception {
        String optString = jSONObject.optString("requestId");
        String str = TAG;
        Log.i(str, "createDownloadableSpeechFromText requestId:" + optString);
        String optString2 = jSONObject.optString(NotificationConstants.REQUEST_TYPE, "None");
        MetricsRecorder.getInstance().startTimer("FocusFilter_createMobileNotification_latency", optString);
        MetricsRecorder.getInstance().startTimer(MetricsConstants.CREATE_DOWNLOADABLE_SPEECH_FROM_TEXT_NETWORK_LATENCY, optString);
        HashMap hashMap = new HashMap();
        hashMap.put(MetricsConstants.CUSTOM_VALUES_KEY, optString2);
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.CREATE_DOWNLOADABLE_SPEECH_FROM_TEXT_START, hashMap);
        try {
            AlexaService.wakeUp(DependencyProvider.getContext());
            CreateDownloadableSpeechFromTextRequestBody build = new CreateDownloadableSpeechFromTextRequestBody.Builder().setPrimaryText(jSONObject.getString(NotificationConstants.PRIMARY_TEXT)).setSecondaryText(jSONObject.optString(NotificationConstants.SECONDARY_TEXT, null)).setRequestId(optString).setDeviceType(DependencyProvider.getDeviceType()).setDeviceSerialNumber(DependencyProvider.getSerialNumber()).build();
            String str2 = TAG;
            Log.d(str2, "createDownloadableSpeechFromText - Body payload " + build.toString());
            CreateDownloadableSpeechFromTextRequestSender.createDownloadableSpeechFromTextRequest(build, this, hashMap);
        } catch (Exception e) {
            Log.e(TAG, "Failed to createDownloadableSpeechFromText.", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_error", hashMap);
            MetricsRecorder.getInstance().cancelTimer("FocusFilter_createMobileNotification_latency", optString);
            MetricsRecorder.getInstance().cancelTimer(MetricsConstants.CREATE_DOWNLOADABLE_SPEECH_FROM_TEXT_NETWORK_LATENCY, optString);
            removeIncomingTtsRequest(optString);
            throw e;
        }
    }

    public void createMobileNotification(@NonNull JSONObject jSONObject) {
        String optString = jSONObject.optString("uuid");
        String optString2 = jSONObject.optString(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT);
        String optString3 = jSONObject.optString(NotificationConstants.DETAILED_CONTENT);
        GeneratedOutlineSupport1.outline166("createMobileNotification ", optString, TAG);
        if (createAndSendAmnsRequest(optString, optString, optString2, optString3)) {
            this.amnsLastRequestStartTimestamp = System.currentTimeMillis();
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("createMobileNotification - Last request timestamp = ");
            outline107.append(this.amnsLastRequestStartTimestamp);
            Log.i(str, outline107.toString());
            return;
        }
        this.amnsRequestCounter.decrementAndGet();
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("createMobileNotification - Decremented count = ");
        outline1072.append(this.amnsRequestCounter.get());
        Log.d(str2, outline1072.toString());
    }

    public boolean createReplyReadBackTts(@NonNull String str, @NonNull String str2) {
        Log.i(TAG, "createReplyReadBackTts");
        return createAndSendAmnsRequest(str, REPLY_READ_BACK_UUID + str, str2, str2);
    }

    @Nullable
    public JSONObject getAndRemoveIncomingNotification(@NonNull String str) {
        GeneratedOutlineSupport1.outline166("getAndRemoveIncomingNotification for UUID - ", str, TAG);
        JSONObject jSONObject = this.incomingNotificationsMap.get(str);
        removeIncomingNotification(str);
        return jSONObject;
    }

    @Nullable
    public JSONObject getAndRemoveIncomingTts(@NonNull String str) {
        GeneratedOutlineSupport1.outline166("getAndRemoveIncomingTts for RequestId - ", str, TAG);
        JSONObject jSONObject = this.incomingTtsMap.get(str);
        removeIncomingTtsRequest(str);
        return jSONObject;
    }

    public String getNotificationUuidFromReadBackRequestId(String str) {
        return (Strings.isNullOrEmpty(str) || !str.contains(REPLY_READ_BACK_UUID)) ? "" : str.substring(15);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.servicerequest.AnnounceWithContentResponseHandler
    public void handleAnnounceWithContentResponse(boolean z, AnnounceWithContentRequestBody announceWithContentRequestBody) {
        this.amnsRequestCounter.decrementAndGet();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("handleAnnounceWithContentResponse - Decremented count = ");
        outline107.append(this.amnsRequestCounter.get());
        Log.i(str, outline107.toString());
        String notificationId = announceWithContentRequestBody.getNotificationId();
        if (!z) {
            MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_error");
            MetricsRecorder.getInstance().cancelTimer("FocusFilter_createMobileNotification_latency", notificationId);
            MetricsRecorder.getInstance().cancelTimer(MetricsConstants.NOTIFICATION_NETWORK_LATENCY, notificationId);
            Log.w(TAG, "handleAnnounceWithContentResponse request failed");
            removeIncomingNotification(announceWithContentRequestBody.getNotificationId());
            removeDismissedNotification(announceWithContentRequestBody.getNotificationId());
            return;
        }
        MetricsRecorder.getInstance().pauseAndRecordTimer("FocusFilter_createMobileNotification_latency", notificationId);
        MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_success");
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.servicerequest.CreateDownloadableSpeechFromTextResponseHandler
    public void handleDownloadableSpeechFromTextResponse(boolean z, CreateDownloadableSpeechFromTextRequestBody createDownloadableSpeechFromTextRequestBody, Map<String, Object> map) {
        String requestId = createDownloadableSpeechFromTextRequestBody.getRequestId();
        if (!z) {
            MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_error", map);
            MetricsRecorder.getInstance().cancelTimer("FocusFilter_createMobileNotification_latency", requestId);
            MetricsRecorder.getInstance().cancelTimer(MetricsConstants.CREATE_DOWNLOADABLE_SPEECH_FROM_TEXT_NETWORK_LATENCY, requestId);
            String str = TAG;
            Log.w(str, "handleDownloadableSpeechFromTextResponse request failed " + requestId);
            removeIncomingTtsRequest(requestId);
            return;
        }
        String str2 = TAG;
        Log.i(str2, "handleDownloadableSpeechFromTextResponse request succeed " + requestId);
        MetricsRecorder.getInstance().pauseAndRecordTimer("FocusFilter_createMobileNotification_latency", requestId);
        MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_success", map);
    }

    @VisibleForTesting
    boolean isNotificationDiscarded(JSONObject jSONObject) {
        Log.d(TAG, "isNotificationDiscarded");
        if (jSONObject == null) {
            Log.w(TAG, "isNotificationDiscarded - Notification JSON is null");
            return false;
        }
        String optString = jSONObject.optString(Constants.BUNDLE_KEY_NOTIFICATION_ID);
        if (this.setOfAppsThatRepeatOriginalMessageOnReply.contains(jSONObject.optString("packageIdentifier"))) {
            Log.i(TAG, "isNotificationDiscarded - Set contains this app");
            if (DependencyProvider.getNotificationServiceCommunicator() != null) {
                return !DependencyProvider.getNotificationServiceCommunicator().safeCallDoesNotificationExistInStatusBar(optString);
            }
        }
        return false;
    }

    public boolean isNotificationDismissed(@NonNull String str) {
        return !Strings.isNullOrEmpty(str) && this.dismissedNotificationsSet.contains(str);
    }

    public boolean isNotificationInIncomingMap(@NonNull String str) {
        return !Strings.isNullOrEmpty(str) && this.incomingNotificationsMap.containsKey(str);
    }

    public boolean isRequestInIncomingTtsMap(@NonNull String str) {
        return !Strings.isNullOrEmpty(str) && this.incomingTtsMap.containsKey(str);
    }

    public void onNewNotification(@NonNull JSONObject jSONObject) {
        String optString = jSONObject.optString("uuid");
        if (FeatureToggleModule.getInstance().hasConnectedEnabledDevices() && isValidNewNotification(jSONObject) && !isDuplicatedNotification(jSONObject) && !isNotificationDismissed(optString) && addIncomingNotification(jSONObject)) {
            if (!AudioInteractionScheduler.getInstance().isAlexaServiceConnected() && this.isFirstAttemptForAlexaServicesConnection) {
                Log.d(TAG, "onNewNotification - Connect Alexa service connection");
                AudioInteractionScheduler.getInstance().connectAlexaServiceConnection();
                this.isFirstAttemptForAlexaServicesConnection = false;
            }
            boolean z = true;
            try {
                fetchContactSaysAudioIfNeeded(jSONObject);
            } catch (Exception unused) {
                Log.i(TAG, "onNewNotification - fetchContactSaysAudioIfNeeded failed with error, reset detailedContent to contentWithPrefix.");
                try {
                    jSONObject.put(NotificationConstants.IS_DETAILED_CONTENT_WITHOUT_PREFIX, false).put(NotificationConstants.DETAILED_CONTENT, jSONObject.optString(NotificationConstants.CONTENT_WITH_PREFIX, jSONObject.optString(NotificationConstants.DETAILED_CONTENT)));
                } catch (JSONException e) {
                    Log.i(TAG, "onNewNotification - reset detailedContent to contentWithPrefix failed", e);
                }
            }
            if (DistractionModeProvider.getCurrentDistractionMode() != 4) {
                z = false;
            }
            boolean isAtLeastOneA2dpConnected = BluetoothA2dpConnectionHandler.getInstance().isAtLeastOneA2dpConnected();
            Log.d(TAG, "onNewNotification - No distraction/Accessory DND enabled = " + z);
            Log.d(TAG, "onNewNotification - At least one active A2DP connection = " + isAtLeastOneA2dpConnected);
            if (!z && isAtLeastOneA2dpConnected) {
                startThrottledCreateMobileNotificationTask(jSONObject);
                return;
            } else if (z) {
                Log.i(TAG, "onNewNotification - Dropping notification because Accessory DND is enabled");
                recordMetricsForQuietModeNotificationDropped();
                return;
            } else {
                Log.i(TAG, "onNewNotification - Dropping notification because No Active A2DP Connection Found");
                recordMetricsForNoA2dpConnectionNotificationDropped();
                return;
            }
        }
        Log.w(TAG, "Invalid new notification - discard: " + optString);
        removeDismissedNotification(optString);
    }

    public void onNotificationDownloaded(@NonNull JSONObject jSONObject) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onNotificationDownloaded called ");
        outline107.append(jSONObject.optString("uuid"));
        Log.i(str, outline107.toString());
        if (isValidNewNotification(jSONObject)) {
            boolean z = DistractionModeProvider.getCurrentDistractionMode() == 4;
            boolean isAtLeastOneA2dpConnected = BluetoothA2dpConnectionHandler.getInstance().isAtLeastOneA2dpConnected();
            String str2 = TAG;
            Log.d(str2, "onNotificationDownloaded - No distraction/Accessory DND enabled = " + z);
            String str3 = TAG;
            Log.d(str3, "onNotificationDownloaded - At least one active A2DP connection = " + isAtLeastOneA2dpConnected);
            String optString = jSONObject.optString("uuid");
            boolean isNotificationDiscarded = isNotificationDiscarded(jSONObject);
            String str4 = TAG;
            Log.i(str4, "onNotificationDownloaded - notificationDiscarded = " + isNotificationDiscarded);
            if (!z && isAtLeastOneA2dpConnected && !isNotificationDiscarded) {
                postEventMessage(1, jSONObject);
                return;
            }
            if (z) {
                Log.i(TAG, "onNotificationDownloaded - Dropping and removing notification because Accessory DND is enabled");
                recordMetricsForQuietModeNotificationDropped();
            } else if (!isAtLeastOneA2dpConnected) {
                Log.i(TAG, "onNotificationDownloaded - Dropping notification because No Active A2DP Connection Found");
                recordMetricsForNoA2dpConnectionNotificationDropped();
            }
            removeNotificationAudioFile(optString);
            return;
        }
        throw new IllegalArgumentException("onNotificationDownloaded - Notification is not valid");
    }

    public void onRemoveAllNotification(JSONObject jSONObject) {
        if (isValidRemovedNotification(jSONObject)) {
            addAllDismissedNotifications();
            postEventMessage(3, jSONObject);
            return;
        }
        Log.w(TAG, "onRemoveAllNotification: - Invalid notification associated with removed all");
    }

    public void onRemoveNotification(JSONObject jSONObject) {
        if (isValidRemovedNotification(jSONObject)) {
            addDismissedNotification(jSONObject);
            postEventMessage(2, jSONObject);
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid removed notification: ");
        outline107.append(jSONObject.optString("uuid"));
        Log.w(str, outline107.toString());
    }

    public boolean removeAllNotificationAudioFile() {
        Log.d(TAG, "removeAllNotificationAudioFile");
        try {
            return NotificationFileHelper.deleteAllFiles();
        } catch (Exception e) {
            Log.w(TAG, "removeAllNotificationAudioFile failed", e);
            return false;
        }
    }

    public void removeDismissedAllNotification() {
        this.dismissedNotificationsSet.clear();
    }

    public void removeDismissedNotification(@NonNull String str) {
        if (isNotificationDismissed(str)) {
            GeneratedOutlineSupport1.outline166("removeDismissedNotification - Removing notification UUID - ", str, TAG);
            this.dismissedNotificationsSet.remove(str);
        }
    }

    public void removeIncomingNotification(@NonNull String str) {
        if (isNotificationInIncomingMap(str)) {
            GeneratedOutlineSupport1.outline166("removeIncomingNotification - Removing notification UUID - ", str, TAG);
            this.incomingNotificationsMap.remove(str);
        }
    }

    public void removeIncomingTtsRequest(@NonNull String str) {
        if (isRequestInIncomingTtsMap(str)) {
            GeneratedOutlineSupport1.outline166("removeIncomingTtsRequest - Removing requestId - ", str, TAG);
            this.incomingTtsMap.remove(str);
        }
    }

    public boolean removeNotificationAudioFile(@NonNull String str) {
        Exception e;
        boolean z;
        GeneratedOutlineSupport1.outline165("removeNotificationAudioFile ", str, TAG);
        boolean z2 = true;
        try {
            NotificationFileHelper notificationFileHelper = new NotificationFileHelper(DependencyProvider.getContext(), str);
            if (notificationFileHelper.getAnnouncementFile() != null) {
                z = notificationFileHelper.getAnnouncementFile().delete();
                try {
                    try {
                        Log.d(TAG, "removeNotificationAudioFile announcement " + str + " removed " + z);
                    } catch (Exception e2) {
                        e = e2;
                        Log.w(TAG, "removeNotificationAudioFile failed for " + str + ".", e);
                        return false;
                    }
                } catch (Throwable unused) {
                    return z;
                }
            } else {
                z = true;
            }
            if (notificationFileHelper.getContentFile() != null) {
                z = notificationFileHelper.getContentFile().delete() && z;
                Log.d(TAG, "removeNotificationAudioFile content " + str + " removed " + z);
            }
            if (notificationFileHelper.getMergedContentFile() == null) {
                return z;
            }
            if (!notificationFileHelper.getMergedContentFile().delete() || !z) {
                z2 = false;
            }
            Log.d(TAG, "removeNotificationAudioFile merged content " + str + " removed " + z2);
            return z2;
        } catch (Exception e3) {
            boolean z3 = z2;
            e = e3;
            z = z3;
        } catch (Throwable unused2) {
            return z2;
        }
    }

    public void startCreateMobileNotificationAsyncTask(@NonNull JSONObject jSONObject) {
        Executors.newCachedThreadPool().submit(new CreateMobileNotificationAsync(jSONObject));
    }

    /* renamed from: clone */
    public NotificationEventManager m337clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
