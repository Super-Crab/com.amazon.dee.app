package com.amazon.alexa.accessory.notificationpublisher.voice;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationListenerConstants;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.StatusBarNotificationParser;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.CommsNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.GenericNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.GroupNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.parser.CustomAppParser;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.reply.ReplyClientHelper;
import com.amazon.alexa.accessory.notificationpublisher.reply.ReplyServiceHelper;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.transformer.GenericTransformer;
import com.amazon.alexa.accessory.notificationpublisher.transformer.TypeTransformer;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.alexa.externalnotifications.capability.models.Application;
import com.amazon.alexa.externalnotifications.capability.models.Contact;
import com.amazon.alexa.externalnotifications.capability.models.Content;
import com.amazon.alexa.externalnotifications.capability.models.Group;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import com.amazon.alexa.externalnotifications.capability.models.Source;
import com.amazon.alexa.externalnotifications.capability.models.SourceType;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class NotificationProcessor {
    private static final String TAG = "NotificationProcessor";
    private BaseTemplateProvider baseTemplateProvider;
    private Context context;
    private SettingsStorageModule settingsModule;
    private StatusBarNotificationParser statusBarNotificationParser;

    public NotificationProcessor(Context context, BaseTemplateProvider baseTemplateProvider, @Nullable SettingsStorageModule settingsStorageModule) {
        this.baseTemplateProvider = baseTemplateProvider;
        this.context = context;
        this.statusBarNotificationParser = new StatusBarNotificationParser(context);
        this.settingsModule = settingsStorageModule;
    }

    @Nullable
    private Notification convertTransformedNotification(JSONObject jSONObject) {
        Source create;
        SourceType sourceType;
        String string;
        Notification.Builder builder = Notification.builder();
        try {
            String string2 = jSONObject.getString("uuid");
            String string3 = jSONObject.getString("packageIdentifier");
            String string4 = jSONObject.getString(SettingsStorageModule.FILTER_SETTINGS_APP_NAME_KEY);
            String optString = jSONObject.optString("appNameForTts");
            String string5 = jSONObject.getString("postTime");
            builder.setNotificationId(NotificationId.create(string2));
            if (!TextUtils.isEmpty(optString)) {
                string4 = optString;
            }
            builder.setApplication(Application.create(string3, string4));
            builder.setTimestamp(new Date(Long.parseLong(string5)));
            ProcessNotificationModule.NotificationType notificationType = ProcessNotificationModule.getNotificationType(jSONObject);
            if (ProcessNotificationModule.NotificationType.OTHER.equals(notificationType)) {
                if (TypeTransformer.CUSTOM_GENERIC_SUB_TYPE.equals(jSONObject.optString("subType"))) {
                    string = jSONObject.getString(NotificationConstants.DETAILED_CONTENT);
                } else {
                    string = jSONObject.getString("content");
                }
                builder.setContent(Content.create(string));
            } else if (ProcessNotificationModule.NotificationType.COMMS.equals(notificationType)) {
                builder.setContent(Content.create(jSONObject.getString("message")));
                String string6 = jSONObject.getString(ContactProviderContract.CONTACT_PATH);
                String optString2 = jSONObject.optString("alias");
                if (TextUtils.isEmpty(optString2)) {
                    optString2 = string6;
                }
                Contact create2 = Contact.create(string6, optString2);
                if (GroupNotificationHelper.isGroupMessage(jSONObject)) {
                    int groupMessageType = GroupNotificationHelper.getGroupMessageType(jSONObject);
                    if (groupMessageType == 1) {
                        sourceType = SourceType.NAMED_GROUP;
                    } else if (groupMessageType == 2) {
                        sourceType = SourceType.UNNAMED_GROUP;
                    } else {
                        String str = TAG;
                        Log.e(str, "convertTransformedNotification: Invalid group type" + groupMessageType);
                        return null;
                    }
                    String string7 = jSONObject.getString(GroupNotificationHelper.PARSER_GROUP_NAME_KEY);
                    String optString3 = jSONObject.optString(GroupNotificationHelper.PARSER_GROUP_RENDER_KEY);
                    if (TextUtils.isEmpty(optString3)) {
                        optString3 = string7;
                    }
                    create = Source.create(create2, Group.create(string7, optString3), sourceType);
                } else {
                    create = Source.create(create2, SourceType.CONTACT);
                }
                builder.setSource(create);
            } else {
                String str2 = TAG;
                Log.w(str2, "convertTransformedNotification: Unrecognized notification type " + notificationType);
                return null;
            }
            return builder.build();
        } catch (JSONException e) {
            String str3 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JSONException: ");
            outline107.append(e.getMessage());
            Log.e(str3, outline107.toString());
            return null;
        }
    }

    private JSONObject getParsedNotification(JSONObject jSONObject) {
        return getCustomAppParserForNotification(jSONObject).parse();
    }

    private boolean isNotificationSameAsSentReply(JSONObject jSONObject) {
        return ReplyClientHelper.isReplyNotification(jSONObject.optString(Constants.BUNDLE_KEY_NOTIFICATION_ID), jSONObject.optString("title"), jSONObject.optString("text"));
    }

    private void populateDefaultVipFilterSettings(JSONObject jSONObject) throws JSONException, RxBlockingCallException, UnsupportedEncodingException {
        SettingsStorageModule.FilterSettingsState stateFromSettingsJson;
        SettingsStorageModule.FilterSettingsState contactFilterSettingsState;
        ProcessNotificationModule.NotificationType notificationType = ProcessNotificationModule.getNotificationType(jSONObject);
        String str = TAG;
        Log.i(str, "populateDefaultVipFilterSettings - type: " + notificationType + " uuid: " + jSONObject.optString("uuid"));
        String sourceId = new GenericNotificationSource(jSONObject).getSourceId();
        JSONObject appFilterSettings = this.settingsModule.getAppFilterSettings(sourceId);
        if (appFilterSettings == null) {
            Log.i(TAG, "populateDefaultVipFilterSettings - populating default app filter settings");
            stateFromSettingsJson = this.settingsModule.createAndPutAppFilterSettings(jSONObject);
        } else {
            ProcessNotificationModule.NotificationType typeFromAppSettingsJson = this.settingsModule.getTypeFromAppSettingsJson(appFilterSettings);
            if (typeFromAppSettingsJson.equals(ProcessNotificationModule.NotificationType.OTHER) && !notificationType.equals(typeFromAppSettingsJson)) {
                if (this.settingsModule.getStateFromSettingsJson(appFilterSettings).equals(SettingsStorageModule.FilterSettingsState.REJECTED)) {
                    stateFromSettingsJson = this.settingsModule.updateAppFilterWithNewTypeAndState(sourceId, appFilterSettings, notificationType, SettingsStorageModule.FilterSettingsState.REJECTED);
                } else {
                    stateFromSettingsJson = this.settingsModule.updateAppFilterWithNewType(sourceId, appFilterSettings, notificationType);
                }
            } else {
                stateFromSettingsJson = this.settingsModule.getStateFromSettingsJson(appFilterSettings);
            }
        }
        boolean equals = notificationType.equals(ProcessNotificationModule.NotificationType.COMMS);
        if (stateFromSettingsJson == SettingsStorageModule.FilterSettingsState.ACCEPTED) {
            Log.i(TAG, "populateDefaultVipFilterSettings - app filter accepted");
            if (!equals) {
                return;
            }
            if (GroupNotificationHelper.isGroupMessage(jSONObject)) {
                contactFilterSettingsState = this.settingsModule.getGroupFilterSettingsState(new GroupNotificationSource(jSONObject));
            } else {
                contactFilterSettingsState = this.settingsModule.getContactFilterSettingsState(new CommsNotificationSource(jSONObject));
            }
            if (contactFilterSettingsState != null) {
                return;
            }
            Log.i(TAG, "populateDefaultVipFilterSettings - populating default comms filter settings");
            this.settingsModule.createAndPutFilterSettings(jSONObject);
        }
    }

    private boolean shouldIgnoreNotification(JSONObject jSONObject) {
        JSONObject ignoredNotificationsTemplateByAppId;
        String optString = jSONObject.optString("packageIdentifier");
        if (TextUtils.isEmpty(optString) || (ignoredNotificationsTemplateByAppId = this.baseTemplateProvider.getIgnoredNotificationsTemplateByAppId(optString)) == null) {
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

    private JSONObject transformNotification(JSONObject jSONObject) throws JSONException, RxBlockingCallException, UnsupportedEncodingException {
        JSONObject contactFilterSettingsJson;
        jSONObject.put("isInvitation", false);
        ProcessNotificationModule.NotificationType notificationType = ProcessNotificationModule.getNotificationType(jSONObject);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transformNotification uuid: ");
        outline107.append(jSONObject.optString("uuid"));
        outline107.append(" type: ");
        outline107.append(notificationType);
        Log.i(str, outline107.toString());
        if (notificationType.equals(ProcessNotificationModule.NotificationType.OTHER) && !TypeTransformer.CUSTOM_GENERIC_SUB_TYPE.equals(jSONObject.optString("subType"))) {
            return new GenericTransformer(this.context, this.baseTemplateProvider, jSONObject).transform();
        }
        if (notificationType.equals(ProcessNotificationModule.NotificationType.COMMS) && !GroupNotificationHelper.isGroupMessage(jSONObject)) {
            String optString = jSONObject.optString(ContactProviderContract.CONTACT_PATH);
            try {
                if (SettingsStorageModule.getInstance().getFocusFilterEnabledWithDefault().booleanValue() && (contactFilterSettingsJson = SettingsStorageModule.getInstance().getContactFilterSettingsJson(new CommsNotificationSource(jSONObject))) != null) {
                    optString = contactFilterSettingsJson.optString(SettingsStorageModule.FILTER_SETTINGS_ALIAS_KEY);
                }
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("transformNotification: Got an exception as: "), TAG);
            }
            jSONObject.put("alias", optString);
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("transformNotificationByType alias empty: ");
            outline1072.append(Strings.isNullOrEmpty(optString));
            Log.i(str2, outline1072.toString());
        }
        return new TypeTransformer(this.context, jSONObject, notificationType, this.baseTemplateProvider).transform();
    }

    @VisibleForTesting
    CustomAppParser getCustomAppParserForNotification(JSONObject jSONObject) {
        return new CustomAppParser(this.context, this.baseTemplateProvider, jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Notification> processNotificationList(List<StatusBarNotification> list) {
        ArrayList arrayList = new ArrayList();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of active notifications: ");
        outline107.append(list.size());
        Log.i(str, outline107.toString());
        for (StatusBarNotification statusBarNotification : list) {
            Notification processStatusBarNotification = processStatusBarNotification(statusBarNotification);
            if (processStatusBarNotification != null) {
                arrayList.add(processStatusBarNotification);
            }
        }
        return arrayList;
    }

    @Nullable
    public Notification processParsedStatusBarNotification(JSONObject jSONObject) {
        if (isNotificationSameAsSentReply(jSONObject)) {
            return null;
        }
        JSONObject parsedNotification = getParsedNotification(jSONObject);
        if (parsedNotification != null) {
            if (shouldIgnoreNotification(parsedNotification)) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("processParsedStatusBarNotification - notification ignored by filtered templates: ");
                outline107.append(parsedNotification.optString("uuid"));
                Log.i(str, outline107.toString());
                return null;
            }
            try {
                if (this.settingsModule != null && AccessoryProvider.getConnectedAccessoryList().isEmpty()) {
                    populateDefaultVipFilterSettings(parsedNotification);
                }
                JSONObject transformNotification = transformNotification(parsedNotification);
                if (transformNotification == null) {
                    Log.i(TAG, "processParsedStatusBarNotification - No valid transformed JSON, discard");
                    return null;
                }
                return convertTransformedNotification(transformNotification);
            } catch (RxBlockingCallException | UnsupportedEncodingException | JSONException e) {
                GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Exception encountered while processing notification: "), TAG);
            }
        } else {
            Log.i(TAG, "processParsedStatusBarNotification - Parsed notification is null");
        }
        return null;
    }

    @Nullable
    public Notification processStatusBarNotification(StatusBarNotification statusBarNotification) {
        Preconditions.checkNotNull(statusBarNotification, "statusBarNotification cannot be null");
        ReplyServiceHelper.updateReplyActionMap(statusBarNotification);
        JSONObject parseNotification = this.statusBarNotificationParser.parseNotification(NotificationListenerConstants.NOTIFICATION_POST, statusBarNotification);
        if (parseNotification == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("processStatusBarNotification - Parsed StatusBarNotification is null for package: ");
            outline107.append(statusBarNotification.getPackageName());
            outline107.toString();
            return null;
        }
        return processParsedStatusBarNotification(parseNotification);
    }

    @Nullable
    public List<Notification> processStatusBarNotifications(StatusBarNotification[] statusBarNotificationArr) {
        Preconditions.checkNotNull(statusBarNotificationArr, "statusBarNotifications are null.");
        return processNotificationList(Arrays.asList(statusBarNotificationArr));
    }
}
