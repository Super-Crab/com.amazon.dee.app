package com.amazon.alexa.accessory.notificationpublisher;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PowerManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationEventManager;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationListenerConstants;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.CommsNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.GenericNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.GroupNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.parser.CustomAppParser;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.TemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.transformer.GenericTransformer;
import com.amazon.alexa.accessory.notificationpublisher.transformer.TypeTransformer;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class ProcessNotificationModule {
    private static final String TAG = "ProcessNotificationModule";
    private static ProcessNotificationModule processModule;
    private SettingsStorageModule settingsModule;
    private NotificationReceiver notificationReceiver = new NotificationReceiver(this, null);
    private NotificationProcessThread notificationProcessThread = new NotificationProcessThread("NotificationProcessThread", 10);

    /* renamed from: com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState = new int[SettingsStorageModule.FilterSettingsState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState[SettingsStorageModule.FilterSettingsState.ACCEPTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState[SettingsStorageModule.FilterSettingsState.NOT_DETERMINED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$storage$SettingsStorageModule$FilterSettingsState[SettingsStorageModule.FilterSettingsState.REJECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class NotificationProcessThread extends HandlerThread {
        Handler handler;

        NotificationProcessThread(String str, int i) {
            super(str, i);
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            this.handler = new Handler(getLooper());
        }
    }

    /* loaded from: classes.dex */
    private class NotificationReceiver extends BroadcastReceiver {
        private NotificationReceiver() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onReceiveNewNotification(JSONObject jSONObject) throws Exception {
            String str = ProcessNotificationModule.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onReceiveNewNotification - ");
            outline107.append(jSONObject.optString("uuid"));
            Log.i(str, outline107.toString());
            MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
            metricsRecorder.recordCounter(MetricsConstants.NOTIFICATION_RECEIVED, (Map<String, Object>) null);
            metricsRecorder.recordDailyEvents();
            if (!FeatureAccessChecker.hasBlockActivePhoneNotificationFeatureAccess() || !ProcessNotificationModule.this.isPhoneActive(DependencyProvider.getContext())) {
                JSONObject parseNotification = ProcessNotificationModule.this.parseNotification(jSONObject);
                if (parseNotification == null) {
                    Log.i(ProcessNotificationModule.TAG, "onReceiveNewNotification - No valid parsed JSON, discard.");
                    metricsRecorder.recordCounter(MetricsConstants.PARSE_INVALIDJSON, (Map<String, Object>) null);
                    return;
                }
                String str2 = ProcessNotificationModule.TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("onReceiveNewNotification - parsedJSON: ");
                outline1072.append(parseNotification.toString());
                Log.d(str2, outline1072.toString());
                if (ProcessNotificationModule.this.isIgnoredNotificationFilterOutByTemplate(parseNotification)) {
                    String str3 = ProcessNotificationModule.TAG;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("onReceiveNewNotification - notification ignored by filtered templates: ");
                    outline1073.append(parseNotification.optString("uuid"));
                    Log.i(str3, outline1073.toString());
                    return;
                }
                boolean isGroupMessage = GroupNotificationHelper.isGroupMessage(parseNotification);
                boolean groupMessagesMasterToggleState = FeatureToggleModule.getInstance().getGroupMessagesMasterToggleState();
                String str4 = ProcessNotificationModule.TAG;
                Log.i(str4, "onReceiveNewNotification - isGroupMessage: " + isGroupMessage + ", groupMessagesEnabled: " + groupMessagesMasterToggleState);
                if (isGroupMessage && !groupMessagesMasterToggleState) {
                    String str5 = ProcessNotificationModule.TAG;
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("onReceiveNewNotification - don't have access to group message feature, ignore: ");
                    outline1074.append(parseNotification.optString("uuid"));
                    Log.i(str5, outline1074.toString());
                    return;
                }
                boolean equals = parseNotification.optString("type").equals(NotificationType.COMMS.name());
                ProcessNotificationModule.this.recordMetric(MetricsConstants.PARSE_SUCCESS, equals);
                if (ProcessNotificationModule.this.settingsModule.getFocusFilterEnabledWithDefault().booleanValue()) {
                    if (!ProcessNotificationModule.this.filterNotification(parseNotification)) {
                        Log.i(ProcessNotificationModule.TAG, "onReceiveNewNotification - Blocked by filter, discard.");
                        return;
                    }
                } else {
                    ProcessNotificationModule.this.recordMetric(MetricsConstants.FILTER_BYPASSED, equals);
                }
                JSONObject transformNotification = ProcessNotificationModule.this.transformNotification(parseNotification);
                if (transformNotification == null) {
                    Log.i(ProcessNotificationModule.TAG, "onReceiveNewNotification - No valid transformed JSON, discard");
                    ProcessNotificationModule.this.recordMetric(MetricsConstants.TRANSFORM_INVALIDJSON, equals);
                    return;
                }
                String str6 = ProcessNotificationModule.TAG;
                StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("onReceiveNewNotification - transformedJSON: ");
                outline1075.append(transformNotification.toString());
                Log.d(str6, outline1075.toString());
                ProcessNotificationModule.this.recordMetric(MetricsConstants.TRANSFORM_SUCCESS, equals);
                NotificationEventManager.getInstance().onNewNotification(transformNotification);
                return;
            }
            Log.i(ProcessNotificationModule.TAG, "onReceiveNewNotification - phone is active, discard notification");
            metricsRecorder.recordCounter(MetricsConstants.PHONE_IS_ACTIVE_DISCARD_NOTIFICATION);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onReceiveRemoveAllNotification(JSONObject jSONObject) throws Exception {
            Log.i(ProcessNotificationModule.TAG, "onReceiveRemoveAllNotification");
            NotificationEventManager.getInstance().onRemoveAllNotification(jSONObject);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.NOTIFICATION_DISMISSED, NotificationQueue.getInstance().size());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onReceiveRemoveNotification(JSONObject jSONObject) throws Exception {
            String str = ProcessNotificationModule.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onReceiveRemoveNotification - ");
            outline107.append(jSONObject.optString("uuid"));
            Log.i(str, outline107.toString());
            NotificationEventManager.getInstance().onRemoveNotification(jSONObject);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.NOTIFICATION_DISMISSED);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            Log.d(ProcessNotificationModule.TAG, "NotificationReceiver - onReceive called");
            if (intent == null || intent.getAction() == null || intent.getExtras() == null || !intent.getExtras().containsKey(NotificationListenerConstants.INTENT_KEY_NOTIFICATION_JSON_EXTRA)) {
                return;
            }
            Log.d(ProcessNotificationModule.TAG, "NotificationReceiver - Intent has extras with INTENT_KEY_NOTIFICATION_JSON_EXTRA");
            String stringExtra = intent.getStringExtra(NotificationListenerConstants.INTENT_KEY_NOTIFICATION_JSON_EXTRA);
            if (Strings.isNullOrEmpty(stringExtra)) {
                return;
            }
            Log.i(ProcessNotificationModule.TAG, "NotificationReceiver - Got valid notification data");
            try {
                final JSONObject jSONObject = new JSONObject(stringExtra);
                ProcessNotificationModule.this.notificationProcessThread.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule.NotificationReceiver.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (intent.getAction().equals(NotificationListenerConstants.NOTIFICATION_POST)) {
                                NotificationReceiver.this.onReceiveNewNotification(jSONObject);
                            } else if (intent.getAction().equals(NotificationListenerConstants.NOTIFICATION_REMOVED)) {
                                NotificationReceiver.this.onReceiveRemoveNotification(jSONObject);
                            } else if (intent.getAction().equals(NotificationListenerConstants.NOTIFICATION_REMOVED_ALL)) {
                                NotificationReceiver.this.onReceiveRemoveAllNotification(jSONObject);
                            } else {
                                Log.w(ProcessNotificationModule.TAG, "Invalid intent");
                            }
                        } catch (Exception e) {
                            Log.e(ProcessNotificationModule.TAG, "NotificationProcess - Exception.", e);
                        }
                    }
                });
            } catch (Exception e) {
                Log.e(ProcessNotificationModule.TAG, "NotificationReceiver - Exception.", e);
            }
        }

        /* synthetic */ NotificationReceiver(ProcessNotificationModule processNotificationModule, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public enum NotificationType {
        COMMS,
        OTHER
    }

    private ProcessNotificationModule() {
        this.notificationProcessThread.start();
    }

    private boolean filterForApprovalOnAccessory(@NonNull JSONObject jSONObject) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("filterForApprovalOnAccessory ");
        outline107.append(jSONObject.optString("uuid"));
        Log.i(str, outline107.toString());
        try {
            if (!this.settingsModule.getApproveInvitationOnAccessoryWithDefault().booleanValue()) {
                return false;
            }
            jSONObject.put("isInvitation", true);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "filterForApprovalOnAccessory - Error getting ApproveInvitationOnAccessory with default", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean filterNotification(@NonNull JSONObject jSONObject) throws Exception {
        SettingsStorageModule.FilterSettingsState stateFromSettingsJson;
        SettingsStorageModule.FilterSettingsState contactFilterSettingsState;
        NotificationType notificationType = getNotificationType(jSONObject);
        String str = TAG;
        Log.i(str, "filterNotification - type: " + notificationType + " uuid: " + jSONObject.optString("uuid"));
        String sourceId = new GenericNotificationSource(jSONObject).getSourceId();
        JSONObject appFilterSettings = this.settingsModule.getAppFilterSettings(sourceId);
        if (appFilterSettings == null) {
            stateFromSettingsJson = this.settingsModule.createAndPutAppFilterSettings(jSONObject);
        } else {
            NotificationType typeFromAppSettingsJson = this.settingsModule.getTypeFromAppSettingsJson(appFilterSettings);
            if (typeFromAppSettingsJson.equals(NotificationType.OTHER) && !notificationType.equals(typeFromAppSettingsJson)) {
                if (this.settingsModule.getStateFromSettingsJson(appFilterSettings).equals(SettingsStorageModule.FilterSettingsState.REJECTED)) {
                    stateFromSettingsJson = this.settingsModule.updateAppFilterWithNewTypeAndState(sourceId, appFilterSettings, notificationType, SettingsStorageModule.FilterSettingsState.REJECTED);
                } else {
                    stateFromSettingsJson = this.settingsModule.updateAppFilterWithNewType(sourceId, appFilterSettings, notificationType);
                }
            } else {
                stateFromSettingsJson = this.settingsModule.getStateFromSettingsJson(appFilterSettings);
            }
        }
        boolean equals = notificationType.equals(NotificationType.COMMS);
        int ordinal = stateFromSettingsJson.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                recordMetric(MetricsConstants.FILTER_RESULT_REJECTED, equals);
                Log.i(TAG, "filterNotification - app filter rejected, discard.");
                return false;
            } else if (ordinal != 2) {
                recordMetric(MetricsConstants.FILTER_ERROR, equals);
                String str2 = TAG;
                Log.e(str2, "filterNotification - invalid app filter state " + stateFromSettingsJson);
                return false;
            } else {
                recordMetric(MetricsConstants.FILTER_RESULT_UNKNOWN, equals);
                return filterForApprovalOnAccessory(jSONObject);
            }
        }
        Log.i(TAG, "filterNotification - app filter accepted");
        if (equals) {
            if (GroupNotificationHelper.isGroupMessage(jSONObject)) {
                contactFilterSettingsState = this.settingsModule.getGroupFilterSettingsState(new GroupNotificationSource(jSONObject));
            } else {
                contactFilterSettingsState = this.settingsModule.getContactFilterSettingsState(new CommsNotificationSource(jSONObject));
            }
            if (contactFilterSettingsState == null) {
                contactFilterSettingsState = this.settingsModule.createAndPutFilterSettings(jSONObject);
            }
            int ordinal2 = contactFilterSettingsState.ordinal();
            if (ordinal2 == 0) {
                recordMetric(MetricsConstants.FILTER_RESULT_ACCEPTED, true);
                return true;
            } else if (ordinal2 == 1) {
                recordMetric(MetricsConstants.FILTER_RESULT_REJECTED, true);
                Log.i(TAG, "filterNotification - contact or group filter rejected, discard.");
                return false;
            } else if (ordinal2 != 2) {
                recordMetric(MetricsConstants.FILTER_ERROR, true);
                String str3 = TAG;
                Log.e(str3, "filterNotification - invalid contact or group filter state " + contactFilterSettingsState);
                return false;
            } else {
                recordMetric(MetricsConstants.FILTER_RESULT_UNKNOWN, true);
                return filterForApprovalOnAccessory(jSONObject);
            }
        }
        recordMetric(MetricsConstants.FILTER_RESULT_ACCEPTED, false);
        return true;
    }

    public static NotificationType getNotificationType(@NonNull JSONObject jSONObject) {
        return NotificationType.valueOf(jSONObject.optString("type"));
    }

    public static synchronized ProcessNotificationModule getProcessNotificationModule() throws IllegalStateException {
        ProcessNotificationModule processNotificationModule;
        synchronized (ProcessNotificationModule.class) {
            if (processModule != null) {
                processNotificationModule = processModule;
            } else {
                Log.e(TAG, "getProcessNotificationModule called before calling initProcessNotificationModule. Throw an exception");
                throw new IllegalStateException("ProcessNotificationModule is not initialized, initProcessNotificationModule must be called before calling this method.");
            }
        }
        return processNotificationModule;
    }

    public static synchronized void initProcessNotificationModule(@NonNull Context context) throws IllegalArgumentException {
        synchronized (ProcessNotificationModule.class) {
            Log.d(TAG, "initProcessNotificationModule called");
            if (processModule == null) {
                Log.i(TAG, "initProcessNotificationModule - First time init");
                if (context != null) {
                    processModule = new ProcessNotificationModule();
                    processModule.settingsModule = SettingsStorageModule.getInstance();
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(NotificationListenerConstants.NOTIFICATION_POST);
                    intentFilter.addAction(NotificationListenerConstants.NOTIFICATION_REMOVED);
                    intentFilter.addAction(NotificationListenerConstants.NOTIFICATION_REMOVED_ALL);
                    LocalBroadcastManager.getInstance(context).registerReceiver(processModule.notificationReceiver, intentFilter);
                } else {
                    Log.e(TAG, "initProcessNotificationModule - Context is null, throw exception");
                    throw new IllegalArgumentException("Cannot initialize ProcessNotificationModule with a null Context.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isIgnoredNotificationFilterOutByTemplate(JSONObject jSONObject) {
        JSONObject ignoredNotificationsTemplateByAppId;
        String optString = jSONObject.optString("packageIdentifier");
        if (TextUtils.isEmpty(optString) || (ignoredNotificationsTemplateByAppId = TemplateProvider.getInstance().getIgnoredNotificationsTemplateByAppId(optString)) == null) {
            return false;
        }
        Iterator keys = ignoredNotificationsTemplateByAppId.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            String optString2 = jSONObject.optString(str);
            String optString3 = ignoredNotificationsTemplateByAppId.optString(str);
            if (!TextUtils.isEmpty(optString2) && optString2.contains(optString3)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPhoneActive(Context context) {
        return isPhoneUnlocked(context) && isPhoneInteractive(context);
    }

    private boolean isPhoneInteractive(Context context) {
        boolean isInteractive = ((PowerManager) context.getSystemService("power")).isInteractive();
        String str = TAG;
        Log.i(str, "isPhoneInteractive - isPhoneInteractive ? " + isInteractive);
        return isInteractive;
    }

    private boolean isPhoneUnlocked(Context context) {
        boolean z = !((KeyguardManager) context.getSystemService("keyguard")).isDeviceLocked();
        Log.i(TAG, "isPhoneUnlocked - isPhoneUnlocked ? " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject parseNotification(@NonNull JSONObject jSONObject) throws Exception {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("parseNotification raw notification uuid: ");
        outline107.append(jSONObject.optString("uuid"));
        Log.i(str, outline107.toString());
        return new CustomAppParser(DependencyProvider.getContext(), TemplateProvider.getInstance(), jSONObject).parse();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMetric(String str, boolean z) {
        MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
        if (z) {
            str = GeneratedOutlineSupport1.outline72(str, "_comms");
        }
        metricsRecorder.recordCounter(str, (Map<String, Object>) null);
    }

    public static synchronized void releaseProcessNotificationModule(Context context) {
        synchronized (ProcessNotificationModule.class) {
            if (processModule != null) {
                Log.d(TAG, "releaseProcessNotificationModule - Unregister BroadcastReceiver and clear the instance");
                LocalBroadcastManager.getInstance(context).unregisterReceiver(processModule.notificationReceiver);
                processModule.notificationProcessThread.quit();
                processModule.notificationProcessThread = null;
                processModule = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject transformNotification(@NonNull JSONObject jSONObject) throws Exception {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transformNotification filteredNotification uuid: ");
        outline107.append(jSONObject.optString("uuid"));
        Log.i(str, outline107.toString());
        if (jSONObject.isNull("isInvitation")) {
            jSONObject.put("isInvitation", false);
        }
        return transformNotificationByType(jSONObject);
    }

    private JSONObject transformNotificationByType(@NonNull JSONObject jSONObject) throws Exception {
        String optString;
        NotificationType notificationType = getNotificationType(jSONObject);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transformNotificationByType uuid: ");
        outline107.append(jSONObject.optString("uuid"));
        outline107.append(" type: ");
        outline107.append(notificationType);
        Log.i(str, outline107.toString());
        if (notificationType.equals(NotificationType.OTHER) && !TypeTransformer.CUSTOM_GENERIC_SUB_TYPE.equals(jSONObject.optString("subType"))) {
            return new GenericTransformer(DependencyProvider.getContext(), TemplateProvider.getInstance(), jSONObject).transform();
        }
        if (notificationType.equals(NotificationType.COMMS) && !GroupNotificationHelper.isGroupMessage(jSONObject)) {
            if (SettingsStorageModule.getInstance().getFocusFilterEnabledWithDefault().booleanValue()) {
                optString = SettingsStorageModule.getInstance().getContactFilterSettingsJson(new CommsNotificationSource(jSONObject)).optString(SettingsStorageModule.FILTER_SETTINGS_ALIAS_KEY);
            } else {
                optString = jSONObject.optString(ContactProviderContract.CONTACT_PATH);
            }
            jSONObject.put("alias", optString);
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("transformNotificationByType alias empty: ");
            outline1072.append(Strings.isNullOrEmpty(optString));
            Log.i(str2, outline1072.toString());
        }
        return new TypeTransformer(DependencyProvider.getContext(), jSONObject, notificationType, TemplateProvider.getInstance()).transform();
    }

    public void removePendingNotificationProcessRequest() {
        NotificationProcessThread notificationProcessThread = this.notificationProcessThread;
        if (notificationProcessThread == null || !notificationProcessThread.isAlive()) {
            return;
        }
        Log.i(TAG, "removePendingNotificationProcessRequest");
        this.notificationProcessThread.handler.removeCallbacksAndMessages(null);
    }
}
