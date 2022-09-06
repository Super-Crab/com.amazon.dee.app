package com.amazon.alexa.accessory.notificationpublisher.utils;

import androidx.annotation.NonNull;
import com.google.common.base.Strings;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class GroupNotificationHelper {
    public static final String GROUP_TYPE_NAMED = "named";
    public static final String GROUP_TYPE_UNNAMED = "unnamed";
    public static final int INVALID_NOTIFICATION = -1;
    public static final int NAMED_GROUP_MESSAGE = 1;
    public static final int NOT_GROUP_MESSAGE = 0;
    public static final String PARSER_GROUP_NAME_KEY = "groupName";
    public static final String PARSER_GROUP_RENDER_KEY = "groupRender";
    public static final String PARSER_GROUP_TYPE_KEY = "groupType";
    public static final String PARSER_IS_GROUP_NOTIFICATION_KEY = "isCommsGroupNotification";
    private static final String PARSER_SUBTYPE_GROUP_MESSAGES_VALUE = "GroupMessage";
    private static final String PARSER_SUBTYPE_KEY = "subType";
    private static final Set<String> SET_OF_APPS_ONLY_SUPPORT_UNNAMED_GROUP = new HashSet(Arrays.asList("com.samsung.android.messaging"));
    private static final String TAG = "GroupNotificationHelper";
    public static final int UNNAMED_GROUP_MESSAGE = 2;

    private GroupNotificationHelper() {
    }

    public static int getGroupMessageType(JSONObject jSONObject) {
        if (jSONObject == null) {
            return -1;
        }
        if (!isGroupMessage(jSONObject)) {
            return 0;
        }
        return jSONObject.optString(PARSER_GROUP_TYPE_KEY).equalsIgnoreCase(GROUP_TYPE_NAMED) ? 1 : 2;
    }

    public static boolean isGroupMessage(JSONObject jSONObject) {
        return jSONObject != null && !Strings.isNullOrEmpty(jSONObject.optString(PARSER_GROUP_NAME_KEY, "")) && jSONObject.optString(PARSER_SUBTYPE_KEY, "").equalsIgnoreCase(PARSER_SUBTYPE_GROUP_MESSAGES_VALUE);
    }

    public static boolean onlyUnnamedGroupSupported(@NonNull JSONObject jSONObject) {
        String optString = jSONObject.optString("packageIdentifier");
        return !Strings.isNullOrEmpty(optString) && SET_OF_APPS_ONLY_SUPPORT_UNNAMED_GROUP.contains(optString);
    }
}
