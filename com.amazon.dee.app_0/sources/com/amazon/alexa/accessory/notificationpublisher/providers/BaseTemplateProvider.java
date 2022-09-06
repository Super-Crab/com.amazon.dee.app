package com.amazon.alexa.accessory.notificationpublisher.providers;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.JSONLoader;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public abstract class BaseTemplateProvider {
    public static final String CUSTOM_GENERIC_KEY = "CUSTOM_GENERIC";
    public static final String GROUP_MESSAGE_NON_INVITATION_KEY = "group_normal";
    public static final String IGNORED_NOTIFICATION_TEMPLATE_FILENAME = "ignored_templates.json";
    public static final String INVITATION_KEY = "invitation";
    public static final String NAMED_GROUP_MESSAGE_INVITATION_KEY = "named_group_invitation";
    public static final String NON_INVITATION_KEY = "normal";
    public static final String OTG_TRANSFORMER_TEMPLATE_FILENAME = "otg_transformer_templates.json";
    public static final String PARSER_TEMPLATE_FILENAME = "parser_templates.json";
    public static final String TRANSFORMER_TEMPLATE_FILENAME = "transformer_templates.json";
    public static final String UNNAMED_GROUP_MESSAGE_INVITATION_KEY = "unnamed_group_invitation";
    private Context context;
    private static final String TAG = TemplateProvider.class.getSimpleName();
    static JSONObject parserTemplate = null;
    static JSONObject transformerTemplate = null;
    static JSONObject transformerTemplateHolder = null;
    static JSONObject otgTransformerTemplateHolder = null;
    static JSONObject ignoredNotificationsTemplate = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseTemplateProvider(@NonNull Context context) {
        this.context = context;
    }

    private JSONObject fetchTemplates(@NonNull Context context, @NonNull String str) {
        JSONObject jSONObject;
        try {
            jSONObject = JSONLoader.loadJSONObjectFromAsset(context, str);
            try {
                String str2 = TAG;
                Log.d(str2, "fetchTemplates - template: " + jSONObject);
            } catch (JSONException e) {
                e = e;
                Log.w(TAG, "fetchTemplates - json exception.", e);
                return jSONObject;
            }
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
        }
        return jSONObject;
    }

    private Object getTemplateByKey(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            Log.w(TAG, "Templates Not Found");
            return null;
        }
        return jSONObject.opt(str);
    }

    static void init(Context context) {
    }

    public synchronized boolean checkIfAnyTemplatesIsNull() {
        boolean z;
        if (parserTemplate == null && transformerTemplate == null) {
            if (ignoredNotificationsTemplate == null) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void create() {
        parserTemplate = fetchTemplates(this.context, PARSER_TEMPLATE_FILENAME);
        transformerTemplateHolder = fetchTemplates(this.context, TRANSFORMER_TEMPLATE_FILENAME);
        otgTransformerTemplateHolder = fetchTemplates(this.context, OTG_TRANSFORMER_TEMPLATE_FILENAME);
        ignoredNotificationsTemplate = fetchTemplates(this.context, IGNORED_NOTIFICATION_TEMPLATE_FILENAME);
    }

    public abstract JSONObject getCustomGenericParserTemplateByAppId(String str);

    public abstract JSONObject getCustomGenericTransformerTemplateByAppId(String str);

    public JSONObject getIgnoredNotificationsTemplateByAppId(String str) {
        if (ignoredNotificationsTemplate == null) {
            Log.i(TAG, "getIgnoredNotificationsTemplateByAppId - ignoredTemplate is null, re-init all templates");
            create();
        }
        return getTemplateJSONObjectByKey(ignoredNotificationsTemplate, str);
    }

    public abstract JSONObject getParserTemplateByAppId(String str);

    protected JSONArray getTemplateJSONArrayByKey(JSONObject jSONObject, String str) {
        if (getTemplateByKey(jSONObject, str) == null) {
            return null;
        }
        return (JSONArray) JSONArray.class.cast(getTemplateByKey(jSONObject, str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject getTemplateJSONObjectByKey(JSONObject jSONObject, String str) {
        if (getTemplateByKey(jSONObject, str) == null) {
            return null;
        }
        return (JSONObject) JSONObject.class.cast(getTemplateByKey(jSONObject, str));
    }

    public abstract JSONObject getTransformerTemplateByType(ProcessNotificationModule.NotificationType notificationType);

    public synchronized void updateTemplateFromS3(String str, JSONObject jSONObject) {
        if (PARSER_TEMPLATE_FILENAME.equals(str)) {
            parserTemplate = jSONObject;
        } else if (TRANSFORMER_TEMPLATE_FILENAME.equals(str)) {
            transformerTemplateHolder = jSONObject;
        } else if (OTG_TRANSFORMER_TEMPLATE_FILENAME.equals(str)) {
            otgTransformerTemplateHolder = jSONObject;
        } else if (IGNORED_NOTIFICATION_TEMPLATE_FILENAME.equals(str)) {
            ignoredNotificationsTemplate = jSONObject;
        }
    }
}
