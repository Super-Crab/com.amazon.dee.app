package com.amazon.alexa.accessory.notificationpublisher.parser;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class GenericParser extends BaseParser {
    private static final String TAG = "GenericParser";
    protected String appName;
    protected String text;
    protected String title;

    public GenericParser(@NonNull JSONObject jSONObject) {
        super(jSONObject);
        this.appName = null;
        this.title = null;
        this.text = null;
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Construct GenericParser -- ");
        outline107.append(jSONObject.toString());
        Log.d(str, outline107.toString());
        populateNotificationAttributesFromJson();
    }

    private String getContent() {
        if (!Strings.isNullOrEmpty(this.text)) {
            if (!Strings.isNullOrEmpty(this.title)) {
                if (this.appName.equalsIgnoreCase(this.title)) {
                    return this.text;
                }
                return this.title + " " + this.text;
            }
            return this.text;
        }
        return this.title;
    }

    private void populateNotificationAttributesFromJson() {
        this.appName = this.notification.optString(SettingsStorageModule.FILTER_SETTINGS_APP_NAME_KEY);
        if (!this.notification.isNull("title")) {
            this.title = this.notification.optString("title");
        } else {
            this.title = null;
        }
        if (!this.notification.isNull("text")) {
            this.text = this.notification.optString("text");
        } else {
            this.text = null;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.parser.BaseParser
    public JSONObject parse() {
        JSONObject jSONObject = null;
        try {
            String str = TAG;
            Log.d(str, "parse -- uuid: " + this.notification.optString("uuid"));
            if (Strings.isNullOrEmpty(this.appName)) {
                Log.w(TAG, "parse -- Missing app name, discard");
                return null;
            }
            JSONObject jSONObject2 = new JSONObject(this.notification, BaseParser.FIELDS_TO_KEEP);
            try {
                String content = getContent();
                jSONObject2.put("type", ProcessNotificationModule.NotificationType.OTHER);
                boolean isNullOrEmpty = Strings.isNullOrEmpty(content);
                Object obj = content;
                if (isNullOrEmpty) {
                    obj = JSONObject.NULL;
                }
                jSONObject2.put("content", obj);
                return jSONObject2;
            } catch (Exception e) {
                e = e;
                jSONObject = jSONObject2;
                Log.e(TAG, "Generic Parsing Exception", e);
                return jSONObject;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }
}
