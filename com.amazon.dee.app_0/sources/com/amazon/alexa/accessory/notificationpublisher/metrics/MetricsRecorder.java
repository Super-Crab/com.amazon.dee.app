package com.amazon.alexa.accessory.notificationpublisher.metrics;

import android.app.ActivityManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.PhoneNotificationListenerService;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.storage.AsyncLocalStorage;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.LRUHashMap;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsCounter;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsTimer;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class MetricsRecorder {
    private static final int DEFAULT_MAX_LENGTH_FOR_CUSTOM_VALUE = 64;
    private static final int DEFAULT_MAX_LENGTH_FOR_MESSAGE = 20;
    private static final String MAX_MILLIS_OF_THE_DAY = "maxMillisOfTheDay";
    private static final String METRICS_STORAGE_TAG = "NotificationFilterMetrics";
    private static final String MOBILYTICS_IDENTIFIER = "4c22bd9e-f936-44d2-95c3-249c93bd6042";
    private static final String OWNER_IDENTIFIER_KEY = "ownerIdentifier";
    private static final String TAG = "MetricsRecorder";
    private static final String TIMESTAMP_ACCESSORY_SESSION_CONNECTED = "accessorySessionConnected";
    private static final String ZION_DEVICE_TYPE = "A3IYPH06PH1HRA";
    private static MetricsRecorder recorder;
    private AsyncLocalStorage asyncLocalStorage;
    private Map<String, Long> debounceMap;
    private long debounceTime;
    private final ExecutorService executorService;
    private MetricsService metricsService;
    private static final long DEFAULT_DEBOUNCE_TIME = TimeUnit.MINUTES.toMillis(1);
    private static final String[] SHOULD_SKIP_FOR_S3 = {MetricsConstants.NOTIFICATION_LISTENER_SERVICE_CONNECTION_BROKEN, MetricsConstants.NOTIFICATION_LISTENER_SERVICE_REMOTE_EXCEPTION, MetricsConstants.SETTINGS_GET_SUCCESS, MetricsConstants.SETTINGS_GET_HTTP_PREFIX, MetricsConstants.SETTINGS_GET_EXCEPTION, MetricsConstants.SETTINGS_GET_EXCEPTION_CLIENT, MetricsConstants.SETTINGS_PUT_SUCCESS, MetricsConstants.SETTINGS_PUT_HTTP_PREFIX, MetricsConstants.SETTINGS_PUT_EXCEPTION, MetricsConstants.SETTINGS_PUT_EXCEPTION_CLIENT, MetricsConstants.PREFERENCES_GET_SUCCESS, MetricsConstants.PREFERENCES_GET_EXCEPTION, MetricsConstants.PREFERENCES_GET_HTTP_PREFIX, MetricsConstants.PREFERENCES_GET_EXCEPTION_CLIENT, MetricsConstants.STORAGEWRAPPER_GET_SUCCESS, MetricsConstants.STORAGEWRAPPER_GET_ERROR, MetricsConstants.STORAGEWRAPPER_PUT_ERROR, MetricsConstants.STORAGEWRAPPER_PUT_SYNC_ERROR, MetricsConstants.STORAGEWRAPPER_CLEAR, MetricsConstants.COOKIE_MANAGER_EXCEPTION};

    private MetricsRecorder() {
        this(Executors.newCachedThreadPool());
    }

    private String buildUniqueTimerName(String str, String str2) {
        return (str == null || str2 == null) ? "DefaultUniqueTimerName" : GeneratedOutlineSupport1.outline72(str, str2);
    }

    public static Map<String, Object> customAttributesForDeviceType(String str) {
        return GeneratedOutlineSupport1.outline133("deviceType_accessory", str);
    }

    public static Map<String, Object> customAttributesForDeviceTypeAndException(String str, Exception exc) {
        Map<String, Object> customAttributesForException = customAttributesForException(exc);
        customAttributesForException.put("deviceType_accessory", str);
        return customAttributesForException;
    }

    public static Map<String, Object> customAttributesForException(Exception exc) {
        return customAttributesForException(exc, 64);
    }

    private void doRecordCounter(String str, String str2, double d, Map<String, Object> map) {
        final MetricsCounter startCounter = this.metricsService.startCounter(str, str2, map);
        startCounter.incrementCounterByValue(d);
        this.executorService.submit(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.1
            @Override // java.lang.Runnable
            public void run() {
                MetricsRecorder.this.metricsService.recordCounter(startCounter);
            }
        });
        Log.d(TAG, String.format("Recorded focus filter metrics counter: %s, value: %f, attributes %s", str, Double.valueOf(d), map));
    }

    private String[] getDailyEventsToRecord() {
        return new String[]{MetricsConstants.DAILY_HEARTBEAT, SettingsStorageModule.getInstance().getReplySettingsWithDefault().booleanValue() ? MetricsConstants.REPLY_TOGGLEVALUE_ON : MetricsConstants.REPLY_TOGGLEVALUE_OFF};
    }

    private String getDeviceType() {
        return FeatureAccessChecker.hasOtgVipFilterAccess() ? AccessoryProvider.getAccessoryDeviceType() : "A3IYPH06PH1HRA";
    }

    public static synchronized MetricsRecorder getInstance() {
        MetricsRecorder metricsRecorder;
        synchronized (MetricsRecorder.class) {
            if (recorder == null) {
                Log.d(TAG, "getInstance - Creating new instance");
                recorder = new MetricsRecorder();
            }
            metricsRecorder = recorder;
        }
        return metricsRecorder;
    }

    private long getMaxMillisOfSameDay(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTimeInMillis();
    }

    private String getSwitchValues() {
        SettingsStorageModule settingsStorageModule = SettingsStorageModule.getInstance();
        try {
            boolean booleanValue = settingsStorageModule.getForwardNotificationToAccessoryWithDefault().booleanValue();
            boolean booleanValue2 = settingsStorageModule.getFocusFilterEnabledWithDefault().booleanValue();
            boolean booleanValue3 = settingsStorageModule.getApproveInvitationOnAccessoryWithDefault().booleanValue();
            boolean isNotificationListenerServiceRunning = isNotificationListenerServiceRunning();
            boolean safeCallIsListenerConnected = DependencyProvider.getNotificationServiceCommunicator().safeCallIsListenerConnected();
            return booleanValue + "_" + booleanValue2 + "_" + booleanValue3 + "_" + isNotificationListenerServiceRunning + "_" + safeCallIsListenerConnected;
        } catch (Exception e) {
            Log.i(TAG, "Get switch values failed. Exception: %s", e);
            return "None";
        }
    }

    private boolean isNotificationListenerServiceRunning() {
        try {
            for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) DependencyProvider.getContext().getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getRunningServices(Integer.MAX_VALUE)) {
                if (runningServiceInfo.service.getClassName().equalsIgnoreCase(PhoneNotificationListenerService.class.getName())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("isNotificationListenerServiceRunning - Exception: ", e, TAG);
            return false;
        }
    }

    private Map<String, Object> processCustomEntries(String str, Map<String, Object> map) {
        HashMap hashMap;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        if (shouldSkipForS3(str)) {
            GeneratedOutlineSupport1.outline166("Skip adding additional attributes for ", str, TAG);
            return hashMap;
        }
        if (!hashMap.containsKey(MetricsConstants.CUSTOM_VALUES_KEY)) {
            hashMap.put(MetricsConstants.CUSTOM_VALUES_KEY, "None");
        }
        if (!hashMap.containsKey("deviceType_accessory")) {
            hashMap.put("deviceType_accessory", getDeviceType());
        }
        if (!hashMap.containsKey("firmware_accessory")) {
            hashMap.put("firmware_accessory", "None");
        }
        Object obj = hashMap.get(MetricsConstants.CUSTOM_VALUES_KEY);
        if (obj == null) {
            obj = "None";
        }
        hashMap.put("deviceId_accessory", obj);
        hashMap.put("sourceContext", obj);
        hashMap.put("ownerIdentifier", "4c22bd9e-f936-44d2-95c3-249c93bd6042");
        return hashMap;
    }

    @VisibleForTesting
    static void resetInstance(ExecutorService executorService) {
        recorder = new MetricsRecorder(executorService);
    }

    private boolean shouldSkipForS3(String str) {
        if (str == null) {
            return true;
        }
        for (String str2 : SHOULD_SKIP_FOR_S3) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public static String truncateExceptionMessage(@NonNull Exception exc) {
        return truncateExceptionMessage(exc, 20);
    }

    public void cancelTimer(String str, String str2) {
        this.metricsService.cancelTimer(buildUniqueTimerName(str, str2));
    }

    public void pauseAndRecordTimer(final String str, String str2) {
        final String buildUniqueTimerName = buildUniqueTimerName(str, str2);
        this.metricsService.pauseTimer(buildUniqueTimerName);
        this.executorService.submit(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.2
            @Override // java.lang.Runnable
            public void run() {
                MetricsRecorder.this.metricsService.recordTimerAs(buildUniqueTimerName, str);
                Log.d(MetricsRecorder.TAG, String.format("Recorded a metrics timer: %s as: %s", buildUniqueTimerName, str));
            }
        });
    }

    public void recordAccessorySessionConnected() {
        String switchValues = getSwitchValues();
        HashMap hashMap = new HashMap();
        hashMap.put(MetricsConstants.CUSTOM_VALUES_KEY, switchValues);
        recordCounter(MetricsConstants.DEVICE_CONNECTED, hashMap);
        try {
            this.asyncLocalStorage.putSync(TIMESTAMP_ACCESSORY_SESSION_CONNECTED, Long.valueOf(System.currentTimeMillis()), null);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "Put accessory session connected timestamp failed with RxBlockingCallException", e);
            recordCounter("FocusFilter_rx_blocking_call_exception_recordAccessorySessionConnected", customAttributesForException(e));
        } catch (Exception e2) {
            Log.i(TAG, "Put accessory session connected timestamp failed. Exception:", e2);
        }
    }

    public void recordAccessorySessionDisconnected() {
        Long l = null;
        try {
            Object sync = this.asyncLocalStorage.getSync(TIMESTAMP_ACCESSORY_SESSION_CONNECTED, null);
            long currentTimeMillis = System.currentTimeMillis();
            if (sync != null && currentTimeMillis > ((Long) sync).longValue()) {
                l = Long.valueOf(currentTimeMillis - ((Long) sync).longValue());
            }
            this.asyncLocalStorage.removeSync(TIMESTAMP_ACCESSORY_SESSION_CONNECTED, false);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "Get accessory session connected timestamp failed with RxBlockingCallException", e);
            recordCounter("FocusFilter_rx_blocking_call_exception_recordAccessorySessionDisconnected", customAttributesForException(e));
        } catch (Exception e2) {
            Log.i(TAG, "Get accessory session connected timestamp failed. Exception: %s", e2);
        }
        HashMap hashMap = new HashMap();
        if (l != null) {
            hashMap.put(MetricsConstants.CUSTOM_VALUES_KEY, l.toString());
        }
        if (FeatureAccessChecker.hasOtgVipFilterAccess()) {
            hashMap.put("deviceType_accessory", AccessoryProvider.getLastDisconnectedAccessoryDeviceType());
        }
        recordCounter(MetricsConstants.DEVICE_DISCONNECTED, MetricsConstants.COMPONENT_NAME, l == null ? 0L : l.longValue() / 1000, hashMap);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void recordBIMetric(String str, String str2, Object obj) {
        char c;
        switch (str2.hashCode()) {
            case -632704118:
                if (str2.equals(SettingsStorageModule.GROUP_MESSAGES_MASTER_TOGGLE_KEY)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -502436310:
                if (str2.equals(SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1846186485:
                if (str2.equals(SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1869150543:
                if (str2.equals(SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        String str3 = "";
        if (c != 0 && c != 1 && c != 2 && c != 3) {
            GeneratedOutlineSupport1.outline166("Key is not recorded as metric in reportBiMetric: ", str2, TAG);
        } else {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115(str3, str2, ".");
            outline115.append(obj.toString());
            outline115.append(".");
            outline115.append(str);
            str3 = outline115.toString();
        }
        String str4 = str3;
        if (!str4.isEmpty()) {
            doRecordCounter(str4, MetricsConstants.COMPONENT_NAME, 1.0d, processCustomEntries(str4, null));
        }
    }

    public void recordCounter(String str, String str2, double d, Map<String, Object> map) {
        Map<String, Object> processCustomEntries = processCustomEntries(str, map);
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            Object obj = processCustomEntries.get("deviceType_accessory");
            if ("A3IYPH06PH1HRA".equals(obj) && str.contains(MetricsConstants.FOCUS_FILTER_PREFIX)) {
                doRecordCounter(str, str2, d, processCustomEntries);
            }
            doRecordCounter(str.replace(MetricsConstants.FOCUS_FILTER_PREFIX, MetricsConstants.VIP_FILTER_PREFIX), str2, d, processCustomEntries);
            if (obj == null || String.valueOf(obj).isEmpty()) {
                return;
            }
            doRecordCounter(str.replace(MetricsConstants.FOCUS_FILTER_PREFIX, MetricsConstants.VIP_FILTER_PREFIX) + ":" + String.valueOf(obj), str2, d, processCustomEntries);
            return;
        }
        doRecordCounter(str, str2, d, processCustomEntries);
    }

    public void recordCounterWithDebounce(String str, @Nullable Map<String, Object> map) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.debounceMap.containsKey(str) && currentTimeMillis - this.debounceMap.get(str).longValue() < this.debounceTime) {
            this.debounceMap.put(str, Long.valueOf(currentTimeMillis));
            return;
        }
        this.debounceMap.put(str, Long.valueOf(currentTimeMillis));
        recordCounter(str, MetricsConstants.COMPONENT_NAME, 1.0d, map);
    }

    public void recordDailyEvents() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Object sync = this.asyncLocalStorage.getSync(MAX_MILLIS_OF_THE_DAY, null);
            if (sync != null && currentTimeMillis <= ((Long) sync).longValue()) {
                return;
            }
            String[] dailyEventsToRecord = getDailyEventsToRecord();
            this.asyncLocalStorage.putSync(MAX_MILLIS_OF_THE_DAY, Long.valueOf(getMaxMillisOfSameDay(currentTimeMillis)), null);
            for (String str : dailyEventsToRecord) {
                recordCounter(str);
            }
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "Record daily heartbeat attempt failed with RxBlockingCallException", e);
            recordCounter("FocusFilter_rx_blocking_call_exception_recordDailyHeartbeatIfNeeded", customAttributesForException(e));
        } catch (Exception e2) {
            Log.i(TAG, "Record daily heartbeat attempt failed. Exception: %s", e2);
        }
    }

    public void setDebounceTime(long j) {
        this.debounceTime = j;
    }

    public MetricsTimer startTimer(String str, String str2) {
        HashMap outline134 = GeneratedOutlineSupport1.outline134(MetricsConstants.CUSTOM_VALUES_KEY, "None", "deviceId_accessory", "None");
        outline134.put("deviceType_accessory", getDeviceType());
        outline134.put("firmware_accessory", "None");
        outline134.put("ownerIdentifier", "4c22bd9e-f936-44d2-95c3-249c93bd6042");
        return startTimer(str, str2, outline134);
    }

    private MetricsRecorder(ExecutorService executorService) {
        this.debounceTime = DEFAULT_DEBOUNCE_TIME;
        this.debounceMap = new LRUHashMap();
        this.metricsService = DependencyProvider.getMetricsService();
        this.asyncLocalStorage = DependencyProvider.createAsyncLocalStorage(METRICS_STORAGE_TAG);
        this.executorService = executorService;
    }

    public static Map<String, Object> customAttributesForException(Exception exc, int i) {
        String exc2 = exc == null ? "None" : exc.toString();
        if (exc2.length() > i) {
            exc2 = exc2.substring(0, i);
        }
        return GeneratedOutlineSupport1.outline133(MetricsConstants.CUSTOM_VALUES_KEY, exc2);
    }

    public static String truncateExceptionMessage(@NonNull Exception exc, int i) {
        String message = exc.getMessage();
        return message.substring(0, Math.min(message.length(), i));
    }

    private MetricsTimer startTimer(String str, String str2, Map<String, Object> map) {
        String buildUniqueTimerName = buildUniqueTimerName(str, str2);
        MetricsTimer startTimer = this.metricsService.startTimer(buildUniqueTimerName, MetricsConstants.COMPONENT_NAME, map);
        Log.d(TAG, String.format("Started a metrics timer: %s", buildUniqueTimerName));
        return startTimer;
    }

    public void recordCounterWithDebounce(String str) {
        recordCounterWithDebounce(str, null);
    }

    public void recordCounter(String str, Map<String, Object> map) {
        recordCounter(str, MetricsConstants.COMPONENT_NAME, 1.0d, map);
    }

    public void recordCounter(String str) {
        recordCounter(str, MetricsConstants.COMPONENT_NAME, 1.0d, null);
    }

    public void recordCounter(String str, double d) {
        recordCounter(str, MetricsConstants.COMPONENT_NAME, d, null);
    }
}
