package com.amazon.alexa.accessory.notificationpublisher.transformer;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser;
import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class BaseTransformer {
    private static final String ADDITIONAL_FORMAT_TEMPLATE_KEY = "additionalFormatRules";
    private static final String TAG = "BaseTransformer";
    private JSONArray additionalTemplate;
    protected String appName;
    protected boolean isInvitation;
    protected JSONObject parsedNotification;
    protected ProcessNotificationModule.NotificationType type;
    static final String[] FIELDS_TO_KEEP = {Constants.BUNDLE_KEY_NOTIFICATION_ID, "uuid", "packageIdentifier", SettingsStorageModule.FILTER_SETTINGS_APP_NAME_KEY, "postTime", "when", "content", ContactProviderContract.CONTACT_PATH, "message", "type", "subType", "isInvitation", "alias", GroupNotificationHelper.PARSER_GROUP_TYPE_KEY, GroupNotificationHelper.PARSER_GROUP_NAME_KEY, GroupNotificationHelper.PARSER_GROUP_RENDER_KEY, GroupNotificationHelper.PARSER_IS_GROUP_NOTIFICATION_KEY, BaseCustomAppParser.CUSTOM_GENERIC_APP_CATEGORY, BaseCustomAppParser.NOTIFICATION_STYLE, BaseCustomAppParser.INBOX_TEXT_LINES, "positiveGesture", "negativeGesture", NotificationConstants.CONTENT_PREFIX, NotificationConstants.CONTENT_WITH_PREFIX, NotificationConstants.IS_DETAILED_CONTENT_WITHOUT_PREFIX};
    static final ImmutableMap<String, String> APP_NAME_TTS_MAP = new ImmutableMap.Builder().mo7828put("Messages", "Messages App").mo7826build();

    public BaseTransformer(@NonNull BaseTemplateProvider baseTemplateProvider, @NonNull JSONObject jSONObject, @NonNull ProcessNotificationModule.NotificationType notificationType) {
        this.appName = "";
        this.parsedNotification = jSONObject;
        this.type = notificationType;
        this.appName = jSONObject.optString(SettingsStorageModule.FILTER_SETTINGS_APP_NAME_KEY);
        if (!jSONObject.isNull("isInvitation")) {
            this.isInvitation = jSONObject.optBoolean("isInvitation");
            JSONObject transformerTemplateByType = baseTemplateProvider.getTransformerTemplateByType(notificationType);
            this.additionalTemplate = transformerTemplateByType == null ? null : transformerTemplateByType.optJSONArray(ADDITIONAL_FORMAT_TEMPLATE_KEY);
            return;
        }
        throw new IllegalArgumentException("isInvitation is missing.");
    }

    private String checkRepeatedContact(String str) {
        String optString = this.parsedNotification.optString(ContactProviderContract.CONTACT_PATH, "");
        String optString2 = this.parsedNotification.optString("alias", "");
        String outline93 = GeneratedOutlineSupport1.outline93(GeneratedOutlineSupport1.outline116("((", optString, ")|(", optString2, ")) (("), optString, " )|(", optString2, " ))");
        if (Pattern.compile(outline93).matcher(str).find()) {
            return str.replaceAll(outline93, optString2.isEmpty() ? GeneratedOutlineSupport1.outline72(optString, " ") : GeneratedOutlineSupport1.outline72(optString2, " "));
        }
        return "";
    }

    private String formatSpecialChars(String str) {
        return str.replace("$", "\\$");
    }

    private Object[] generateParamValueObj(@NonNull JSONArray jSONArray) {
        Object[] objArr = new Object[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray optJSONArray = jSONArray.optJSONArray(i);
            int i2 = 0;
            while (true) {
                if (i2 >= optJSONArray.length()) {
                    break;
                }
                String optString = this.parsedNotification.optString(optJSONArray.optString(i2));
                if (!Strings.isNullOrEmpty(optString)) {
                    objArr[i] = optString;
                    break;
                }
                i2++;
            }
            if (objArr[i] == null) {
                return null;
            }
        }
        return objArr;
    }

    private String replaceUnsupChars(Matcher matcher, String str) {
        int end = matcher.end();
        while (end < str.length() && str.charAt(matcher.end()) == '\'') {
            str = str.substring(0, end) + "%27" + str.substring(end + 1);
            matcher.reset(str);
            matcher.find();
            end = matcher.end();
        }
        return str;
    }

    protected String formatStringWithParameter(String str, JSONArray jSONArray) {
        if (Strings.isNullOrEmpty(str)) {
            return null;
        }
        if (jSONArray == null || jSONArray.length() == 0) {
            return str;
        }
        Object[] generateParamValueObj = generateParamValueObj(jSONArray);
        if (generateParamValueObj == null) {
            return null;
        }
        try {
            return String.format(Locale.US, str, generateParamValueObj);
        } catch (IllegalFormatException e) {
            Log.w(TAG, "formatStringWithParameter -- String.format Exception.", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getKeyForTransformer() {
        String str = this.isInvitation ? BaseTemplateProvider.INVITATION_KEY : BaseTemplateProvider.NON_INVITATION_KEY;
        int groupMessageType = GroupNotificationHelper.getGroupMessageType(this.parsedNotification);
        if (groupMessageType == 1) {
            if (this.isInvitation) {
                return BaseTemplateProvider.NAMED_GROUP_MESSAGE_INVITATION_KEY;
            }
        } else if (groupMessageType != 2) {
            return str;
        } else {
            if (this.isInvitation) {
                return BaseTemplateProvider.UNNAMED_GROUP_MESSAGE_INVITATION_KEY;
            }
        }
        return BaseTemplateProvider.GROUP_MESSAGE_NON_INVITATION_KEY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @VisibleForTesting
    public ReplacedContent replaceDetailedContent(String str) {
        if (this.additionalTemplate != null) {
            String str2 = str;
            for (int i = 0; i < this.additionalTemplate.length(); i++) {
                JSONObject optJSONObject = this.additionalTemplate.optJSONObject(i);
                String optString = optJSONObject.optString("format");
                JSONArray optJSONArray = optJSONObject.optJSONArray("parameter");
                String optString2 = optJSONObject.optString("replaceRegex");
                boolean optBoolean = optJSONObject.optBoolean("fullyMatch", false);
                int optInt = optJSONObject.optInt("id", -1);
                if (!Strings.isNullOrEmpty(optString2)) {
                    Matcher matcher = Pattern.compile(optString2).matcher(str2);
                    if (matcher.find()) {
                        str2 = replaceUnsupChars(matcher, str2);
                    }
                    matcher.reset(str2);
                    if ((optBoolean && matcher.matches()) || (!optBoolean && matcher.find())) {
                        String formatSpecialChars = formatSpecialChars(formatStringWithParameter(optString, optJSONArray));
                        if (optInt == 3) {
                            String checkRepeatedContact = checkRepeatedContact(formatSpecialChars);
                            if (!checkRepeatedContact.isEmpty()) {
                                formatSpecialChars = checkRepeatedContact;
                            }
                        }
                        return new ReplacedContent(str2.replaceAll(optString2, formatSpecialChars), optInt);
                    }
                }
            }
            str = str2;
        }
        return new ReplacedContent(str);
    }

    protected abstract JSONObject transform();

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSONObject transformByTemplate(@NonNull JSONArray jSONArray) throws Exception {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject transformOneRule = transformOneRule(jSONArray.optJSONObject(i));
            if (transformOneRule != null && !Strings.isNullOrEmpty(transformOneRule.optString(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT)) && !Strings.isNullOrEmpty(transformOneRule.optString(NotificationConstants.DETAILED_CONTENT))) {
                return transformOneRule;
            }
        }
        return null;
    }

    JSONObject transformOneRule(JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2 = new JSONObject();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            String formatStringWithParameter = formatStringWithParameter(optJSONObject.optString("format"), optJSONObject.optJSONArray("parameter"));
            if (formatStringWithParameter != null) {
                jSONObject2.put(str, formatStringWithParameter);
            } else if (!optJSONObject.optBoolean("optional", false)) {
                return null;
            }
        }
        return jSONObject2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public class ReplacedContent {
        public static final int NO_TEMPLATE_USED = -1;
        public static final int REMOVE_CONTENT_PREFIX_TEMPLATE_ID = 3;
        private String content;
        private int templateId;

        public ReplacedContent(String str, int i) {
            this.content = str;
            this.templateId = i;
        }

        public String getContent() {
            return this.content;
        }

        public int getTemplateId() {
            return this.templateId;
        }

        public boolean isValidTemplate() {
            return this.templateId != -1;
        }

        public ReplacedContent(String str) {
            this.content = str;
            this.templateId = -1;
        }
    }

    private BaseTransformer() {
        this.appName = "";
    }
}
