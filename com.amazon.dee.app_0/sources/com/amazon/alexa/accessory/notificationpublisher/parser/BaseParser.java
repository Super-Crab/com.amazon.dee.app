package com.amazon.alexa.accessory.notificationpublisher.parser;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.deecomms.common.Constants;
import org.json.JSONObject;
/* loaded from: classes.dex */
abstract class BaseParser {
    static final String[] FIELDS_TO_KEEP = {Constants.BUNDLE_KEY_NOTIFICATION_ID, "uuid", "packageIdentifier", SettingsStorageModule.FILTER_SETTINGS_APP_NAME_KEY, "postTime", "when", "title", "text", GroupNotificationHelper.PARSER_GROUP_TYPE_KEY, GroupNotificationHelper.PARSER_GROUP_NAME_KEY, GroupNotificationHelper.PARSER_GROUP_RENDER_KEY, GroupNotificationHelper.PARSER_IS_GROUP_NOTIFICATION_KEY, BaseCustomAppParser.CUSTOM_GENERIC_APP_CATEGORY, BaseCustomAppParser.NOTIFICATION_STYLE, BaseCustomAppParser.INBOX_TEXT_LINES};
    protected JSONObject notification;

    public BaseParser(@NonNull JSONObject jSONObject) {
        this.notification = jSONObject;
    }

    public abstract JSONObject parse();
}
