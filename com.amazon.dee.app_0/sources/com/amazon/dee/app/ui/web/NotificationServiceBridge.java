package com.amazon.dee.app.ui.web;

import android.content.Context;
import androidx.core.app.NotificationManagerCompat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class NotificationServiceBridge extends JavaScriptBridge {
    private static final String KEY_CURRENT_NOTIFICATION_SETTINGS = "currentNotificationSettings";
    private static final String TAG = "com.amazon.dee.app.ui.web.NotificationServiceBridge";
    Context context;
    private Map<String, JavaScriptBridgeMethod> mMethods;
    NotificationManagerCompat notificationManagerCompat;

    /* loaded from: classes12.dex */
    private class GetCurrentUserNotificationSettingsMethod implements JavaScriptBridgeMethod {
        private GetCurrentUserNotificationSettingsMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            boolean areNotificationsEnabled = NotificationServiceBridge.this.notificationManagerCompat.areNotificationsEnabled();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(NotificationServiceBridge.KEY_CURRENT_NOTIFICATION_SETTINGS, areNotificationsEnabled ? 1 : 0);
            javaScriptResponse.setResponse(jSONObject2);
            NotificationServiceBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public NotificationServiceBridge(Context context, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.notificationManagerCompat = NotificationManagerCompat.from(context);
        this.context = context;
        this.mMethods.put("getCurrentUserNotificationSettings", new GetCurrentUserNotificationSettingsMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "NotificationServiceBridge";
    }

    NotificationServiceBridge(NotificationManagerCompat notificationManagerCompat, Context context, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.notificationManagerCompat = notificationManagerCompat;
        this.context = context;
        this.mMethods.put("getCurrentUserNotificationSettings", new GetCurrentUserNotificationSettingsMethod());
    }
}
