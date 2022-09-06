package com.amazon.alexa.accessory.notificationpublisher.parser;

import android.telephony.PhoneNumberUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class BaseCustomAppParser extends BaseParser {
    static final String ALEXA_APP_ID = "com.amazon.dee.app";
    public static final String CALENDAR_APP_CATEGORY = "Calendar";
    public static final String CUSTOM_GENERIC_APP_CATEGORY = "appCategory";
    public static final String INBOX_TEXT_LINES = "inboxTextLines";
    public static final String NOTIFICATION_STYLE = "notificationStyle";
    public static final String NOTIFICATION_STYLE_INBOX = "android.app.Notification$InboxStyle";
    public static final String NOTIFICATION_STYLE_MESSAGING = "android.app.Notification$MessagingStyle";
    private String logTag;
    protected JSONObject metaData;
    Pattern multiAlexaMessagesTitlePattern;
    static final Pattern PATTERN_GROUP_TITLE_WITH_PHONE = Pattern.compile("^(?:.*\\,\\s)*?((\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4})(?:\\,\\s.*)*?$|^(?:.*\\,\\s)*?((\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3})(?:\\,\\s.*)*?$|^(?:.*\\,\\s)*?((\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2})(?:\\,\\s.*)*?$");
    static final Pattern PATTERN_GROUP_TITLE_WITH_CONTACTS = Pattern.compile("^(.+),\\s(.*)$");
    static final ImmutableSet<String> REPLACE_PHONE_NUMBER_APP_LIST = new ImmutableSet.Builder().mo7849add((ImmutableSet.Builder) "com.samsung.android.messaging").mo7849add((ImmutableSet.Builder) "com.google.android.talk").mo7852build();

    public BaseCustomAppParser(@NonNull JSONObject jSONObject) {
        super(jSONObject);
        this.multiAlexaMessagesTitlePattern = Pattern.compile("^\\d+\\snew\\s(?:(message)|(announcement))(?:s)?+$");
        this.logTag = getClass().getSimpleName();
        String str = this.logTag;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Construct BaseCustomAppParser -- ");
        outline107.append(jSONObject.toString());
        Log.d(str, outline107.toString());
        JSONObject optJSONObject = jSONObject.optJSONObject("metaData");
        this.metaData = optJSONObject == null ? new JSONObject() : optJSONObject;
    }

    private String getPackageVersion(String str) {
        try {
            return DependencyProvider.getContext().getPackageManager().getPackageInfo(str, 128).versionName;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("getPackageVersion - Exception getting package version: ", e, this.logTag);
            return "";
        }
    }

    private String handleReplaceRule(String str, JSONObject jSONObject, String str2) {
        if (jSONObject.opt(str2) instanceof JSONArray) {
            String str3 = this.logTag;
            Log.d(str3, "performIterativeReplacement for: " + str2);
            return performIterativeReplacement(str, jSONObject.optJSONArray(str2));
        } else if (!(jSONObject.opt(str2) instanceof JSONObject)) {
            return null;
        } else {
            String str4 = this.logTag;
            Log.d(str4, "performSingleReplacement for: " + str2);
            return performSingleReplacement(str, jSONObject.optJSONObject(str2));
        }
    }

    private boolean isMatchOneBlackRule(JSONObject jSONObject) {
        if (jSONObject.names() == null || jSONObject.names().length() == 0) {
            return false;
        }
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
            } catch (IllegalArgumentException e) {
                Log.w(this.logTag, "Match pattern throws exception.", e);
            }
            if (!Pattern.matches(jSONObject.optString(str), this.notification.optString(str, this.metaData.optString(str)))) {
                return false;
            }
        }
        String str2 = this.logTag;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("black rule matching -- ");
        outline107.append(jSONObject.toString());
        outline107.append(" ");
        outline107.append(this.notification.toString());
        Log.d(str2, outline107.toString());
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006e, code lost:
        if (com.google.common.base.Strings.isNullOrEmpty(r9) != false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONObject matchOneWhiteRule(org.json.JSONObject r14, org.json.JSONObject r15) {
        /*
            r13 = this;
            java.lang.String r0 = "appCategory"
            java.lang.String r1 = "subType"
            java.lang.String r2 = "type"
            org.json.JSONArray r3 = r14.names()
            if (r3 == 0) goto Le3
            org.json.JSONArray r3 = r14.names()
            int r3 = r3.length()
            if (r3 != 0) goto L1a
            goto Le3
        L1a:
            java.lang.String r3 = "pattern"
            org.json.JSONObject r3 = r14.optJSONObject(r3)
            java.lang.String r4 = "output"
            org.json.JSONObject r4 = r14.optJSONObject(r4)
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>()
            java.util.Iterator r6 = r4.keys()
        L31:
            boolean r7 = r6.hasNext()
            java.lang.String r8 = "JSON exception."
            if (r7 == 0) goto La2
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            org.json.JSONObject r9 = r4.optJSONObject(r7)
            java.lang.String r10 = r13.extraField(r9, r3)
            if (r15 == 0) goto L70
            org.json.JSONObject r11 = r15.optJSONObject(r7)
            if (r11 != 0) goto L55
            org.json.JSONArray r11 = r15.optJSONArray(r7)
            if (r11 == 0) goto L70
        L55:
            if (r10 != 0) goto L65
            org.json.JSONObject r11 = r13.notification
            java.lang.String r12 = "where"
            java.lang.String r9 = r9.optString(r12)
            java.lang.String r9 = r11.optString(r9)
            goto L66
        L65:
            r9 = r10
        L66:
            java.lang.String r9 = r13.handleReplaceRule(r9, r15, r7)
            boolean r11 = com.google.common.base.Strings.isNullOrEmpty(r9)
            if (r11 == 0) goto L71
        L70:
            r9 = r10
        L71:
            java.lang.String r10 = r13.logTag
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "matchOneWhiteRule -- field info : "
            r11.append(r12)
            r11.append(r7)
            java.lang.String r12 = " - "
            r11.append(r12)
            r11.append(r9)
            java.lang.String r11 = r11.toString()
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r10, r11)
            boolean r10 = com.google.common.base.Strings.isNullOrEmpty(r9)
            if (r10 != 0) goto La0
            r5.put(r7, r9)     // Catch: org.json.JSONException -> L99
            goto L31
        L99:
            r14 = move-exception
            java.lang.String r15 = r13.logTag
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(r15, r8, r14)
            goto Le1
        La0:
            r14 = 0
            return r14
        La2:
            r15 = 0
            java.lang.String r3 = r14.optString(r2, r15)     // Catch: org.json.JSONException -> Ldb
            r5.put(r2, r3)     // Catch: org.json.JSONException -> Ldb
            java.lang.String r2 = r14.optString(r1, r15)     // Catch: org.json.JSONException -> Ldb
            r5.put(r1, r2)     // Catch: org.json.JSONException -> Ldb
            java.lang.String r15 = r14.optString(r0, r15)     // Catch: org.json.JSONException -> Ldb
            r5.put(r0, r15)     // Catch: org.json.JSONException -> Ldb
            java.lang.String r15 = r13.logTag
            java.lang.String r0 = "matchOneWhiteRule -- "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            java.lang.String r14 = r14.toString()
            r0.append(r14)
            java.lang.String r14 = " "
            r0.append(r14)
            java.lang.String r14 = r5.toString()
            r0.append(r14)
            java.lang.String r14 = r0.toString()
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r15, r14)
            return r5
        Ldb:
            r14 = move-exception
            java.lang.String r15 = r13.logTag
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(r15, r8, r14)
        Le1:
            r14 = 0
            return r14
        Le3:
            r14 = 0
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser.matchOneWhiteRule(org.json.JSONObject, org.json.JSONObject):org.json.JSONObject");
    }

    private String performIterativeReplacement(String str, JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            String performSingleReplacement = performSingleReplacement(str, jSONArray.optJSONObject(i));
            if (performSingleReplacement != null) {
                str = performSingleReplacement;
            }
        }
        return str;
    }

    private String performSingleReplacement(String str, JSONObject jSONObject) {
        Pattern compile = Strings.isNullOrEmpty(jSONObject.optString("replaceUnmatchedRule")) ? null : Pattern.compile(jSONObject.optString("replaceUnmatchedRule"));
        Pattern compile2 = Strings.isNullOrEmpty(jSONObject.optString("replaceMatchedRule")) ? null : Pattern.compile(jSONObject.optString("replaceMatchedRule"));
        if (compile != null && !compile.matcher(str).matches()) {
            return jSONObject.optString("replaceValue");
        }
        String optString = jSONObject.optString("fullyMatchedReplaceValue");
        String optString2 = jSONObject.optString("partialMatchedReplaceValue");
        if (compile2 != null && !Strings.isNullOrEmpty(optString) && compile2.matcher(str).matches()) {
            return optString;
        }
        if (compile2 != null && !Strings.isNullOrEmpty(optString2) && compile2.matcher(str).find()) {
            return str.replaceAll(jSONObject.optString("replaceMatchedRule"), jSONObject.optString("partialMatchedReplaceValue"));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject addFieldsForGroupNotification(@NonNull JSONObject jSONObject, @Nullable JSONObject jSONObject2) {
        String format;
        if (jSONObject2 == null) {
            try {
                jSONObject2 = new JSONObject();
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline157("addFieldsForGroupNotification - Exception: ", e, this.logTag);
            }
        }
        String str = GroupNotificationHelper.GROUP_TYPE_UNNAMED;
        String optString = jSONObject.optString(GroupNotificationHelper.PARSER_GROUP_NAME_KEY, "");
        if (PATTERN_GROUP_TITLE_WITH_PHONE.matcher(optString).matches()) {
            Log.i(this.logTag, "addFieldsForGroupNotification - Phone number in group name pattern matches");
            format = jSONObject2.optString("unnamedGroupWithPhoneNumber", " on a group");
        } else if (PATTERN_GROUP_TITLE_WITH_CONTACTS.matcher(optString).matches()) {
            String[] split = optString.split(",");
            Log.i(this.logTag, "addFieldsForGroupNotification - Contacts in group name pattern matches");
            if (jSONObject2.optBoolean("useLongFormatForContactsRender", false) && split.length > 1) {
                Log.d(this.logTag, "addFieldsForGroupNotification - Use long format for contacts render");
                format = String.format(Locale.US, jSONObject2.optString("unnamedGroupWithContactsLong", "   on a group with %s and %s"), split[0].trim(), split[1].trim());
            } else if (split.length > 0) {
                Log.d(this.logTag, "addFieldsForGroupNotification - Use short format for contacts render");
                format = String.format(Locale.US, jSONObject2.optString("unnamedGroupWithContactsShort", "   on a group with %s"), split[0].trim());
            } else {
                Log.d(this.logTag, "addFieldsForGroupNotification - Unknown state for contacts render, fallback");
                format = "  on a group";
            }
        } else {
            Log.i(this.logTag, "addFieldsForGroupNotification - Named group found");
            format = String.format(Locale.US, jSONObject2.optString("namedGroup", "  on group %s"), optString);
            str = GroupNotificationHelper.GROUP_TYPE_NAMED;
        }
        jSONObject.put(GroupNotificationHelper.PARSER_GROUP_TYPE_KEY, str);
        jSONObject.put(GroupNotificationHelper.PARSER_GROUP_RENDER_KEY, format);
        jSONObject.put(GroupNotificationHelper.PARSER_IS_GROUP_NOTIFICATION_KEY, true);
        return jSONObject;
    }

    abstract JSONObject customParsingWithTemplate(JSONObject jSONObject) throws Exception;

    public String extraField(JSONObject jSONObject, JSONObject jSONObject2) {
        String optString = jSONObject.optString("where");
        int optInt = jSONObject.optInt("group", -1);
        String optString2 = jSONObject2.optString(optString);
        String str = this.logTag;
        Log.d(str, "extraField -- patternString: " + optString2 + " - groupIndex:  " + optInt);
        String optString3 = this.notification.optString(optString, this.metaData.optString(optString));
        GeneratedOutlineSupport1.outline165("extraField -- fieldString: ", optString3, this.logTag);
        if (optInt >= 0 && !Strings.isNullOrEmpty(optString3) && !Strings.isNullOrEmpty(optString2)) {
            try {
                Matcher matcher = Pattern.compile(optString2).matcher(optString3);
                if (matcher.find()) {
                    if (REPLACE_PHONE_NUMBER_APP_LIST.contains(this.notification.optString("packageIdentifier")) && "title".equals(optString)) {
                        return replaceMessagePhoneNumber(matcher.group(optInt));
                    }
                    return matcher.group(optInt);
                }
            } catch (IllegalArgumentException e) {
                Log.w(this.logTag, "extraField error.", e);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject genericParsingWhenCustomParsingFailed() {
        Log.i(this.logTag, "customParsingWithTemplate -- no white rule matching, use generic parser instead.");
        HashMap hashMap = new HashMap();
        String optString = this.notification.optString("packageIdentifier", "ErrorGettingPkgId");
        hashMap.put(MetricsConstants.CUSTOM_VALUES_KEY, String.format(Locale.US, "%s_%s", optString, optString.equalsIgnoreCase("ErrorGettingPkgId") ? "" : getPackageVersion(optString)));
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.PARSE_FALLBACK_TO_GENERIC, hashMap);
        return new GenericParser(this.notification).parse();
    }

    abstract JSONObject getCommsAppTemplate();

    abstract JSONObject getCustomGenericAppTemplate();

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInboxStyleMultiAlexaMessages(@NonNull JSONObject jSONObject) {
        boolean z;
        if (!"com.amazon.dee.app".equalsIgnoreCase(jSONObject.optString("packageIdentifier"))) {
            return false;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("metaData");
        String optString = optJSONObject == null ? "" : optJSONObject.optString(NOTIFICATION_STYLE);
        String optString2 = jSONObject.optString("title");
        GeneratedOutlineSupport1.outline165("isInboxStyleAlexaMessages title: ", optString2, this.logTag);
        try {
            z = this.multiAlexaMessagesTitlePattern.matcher(optString2).matches();
        } catch (PatternSyntaxException e) {
            Log.w(this.logTag, "multiAlexaMessagesTitlePattern match Exception.", e);
            z = false;
        }
        if (JSONObject.NULL != jSONObject.opt("text")) {
            return false;
        }
        return z || optString.equalsIgnoreCase(NOTIFICATION_STYLE_INBOX);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isMatchBlackRules(JSONArray jSONArray) {
        if (jSONArray == null) {
            return false;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            if (isMatchOneBlackRule(jSONArray.optJSONObject(i))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject matchWhiteRules(JSONArray jSONArray, JSONObject jSONObject) {
        if (jSONArray == null) {
            return null;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject matchOneWhiteRule = matchOneWhiteRule(jSONArray.optJSONObject(i), jSONObject);
            if (matchOneWhiteRule != null) {
                return matchOneWhiteRule;
            }
        }
        return null;
    }

    public String replaceMessagePhoneNumber(String str) {
        if (Pattern.compile("^(\\+\\d+)(?:\\(\\d+\\))?$").matcher(str).matches()) {
            Log.i(this.logTag, "replaceMessagePhoneNumber -- format phone number for comms app");
            return PhoneNumberUtils.formatNumber(str, "US");
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject updateNumbersReplacedWithDigits(JSONObject jSONObject, JSONArray jSONArray) throws JSONException {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                String optString = jSONArray.optString(i);
                String optString2 = jSONObject.optString(optString);
                if (!Strings.isNullOrEmpty(optString2)) {
                    Matcher matcher = Pattern.compile("[0-9]+").matcher(optString2);
                    while (matcher.find()) {
                        String group = matcher.group();
                        optString2 = optString2.replaceFirst(group, group.replaceAll(".(?!$)", "$0 "));
                    }
                    jSONObject.put(optString, optString2);
                }
            }
        }
        return jSONObject;
    }
}
