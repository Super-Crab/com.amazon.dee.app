package com.amazon.dee.app.framework;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class EventBusMessagingReceiver implements MessagingReceiver {
    public static final String EVENT_DEEPLINK_VIEW = "notification::DeepLinkView";
    public static final String EVENT_NOTIFICATION_RECEIVED = "notification::NotificationReceived";
    public static final String NOTIFICATION_EVENT_TYPE_KEY = "key";
    public static final String NOTIFICATION_URL_TYPE_ALEXA = "alexaUrl";
    public static final String NOTIFICATION_URL_TYPE_DEEP_LINK = "deepLinkUrl";
    public static final String NOTIFICATION_URL_TYPE_STORE = "storeUrl";
    public static final String NOTIFICATION_URL_TYPE_WEB = "webUrl";
    private static final String TAG = Log.tag(EventBusMessagingReceiver.class);
    private EventBus eventBus;

    /* loaded from: classes12.dex */
    public static class NotificationEvent {
        public static final String ORIGINAL_URI = "original_uri";
        public static final String URI_FRAGMENT = "uri_fragment";
        public static final String URI_KEY = "uri_key";
        public static final String URI_PATH = "uri_path";
        public static final String URI_QUERY = "uri_query";
        private String fragment;
        private String path;
        private String query;
        private String url;
        private String urlKey;

        public NotificationEvent(@NonNull String str, @NonNull String str2, String str3, String str4, String str5) {
            this.urlKey = str;
            this.url = str2;
            this.fragment = str3;
            this.query = str4;
            this.path = str5;
        }

        public String getFragment() {
            return this.fragment;
        }

        public String getPath() {
            return this.path;
        }

        public String getQuery() {
            return this.query;
        }

        public String getUrl() {
            return this.url;
        }

        public String getUrlKey() {
            return this.urlKey;
        }

        public JSONObject toJson() throws JSONException {
            if (this.urlKey == null || this.url == null) {
                return null;
            }
            JSONObject put = new JSONObject().put(URI_KEY, this.urlKey).put(ORIGINAL_URI, this.url);
            String str = this.fragment;
            if (str != null) {
                put.put(URI_FRAGMENT, str);
            }
            String str2 = this.query;
            if (str2 != null) {
                put.put(URI_QUERY, str2);
            }
            String str3 = this.path;
            if (str3 != null) {
                put.put(URI_PATH, str3);
            }
            return put;
        }
    }

    public EventBusMessagingReceiver(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    private JSONObject buildJsonObject(String str, @NonNull JSONArray jSONArray, @NonNull String str2) {
        if (str == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str)) {
            try {
                Uri parse = Uri.parse(str);
                if (parse != null) {
                    jSONObject.put(str2, new NotificationEvent(str2, str, parse.getFragment(), parse.getQuery(), parse.getPath()).toJson());
                    jSONArray.put(jSONObject);
                }
            } catch (JSONException | Exception unused) {
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("returning jsonObject: ");
        outline107.append(jSONObject.toString());
        outline107.toString();
        return jSONObject;
    }

    private void sendMessageToEventBus(@NonNull String str, @NonNull String str2) {
        Message build;
        try {
            String str3 = "Publishing message to event bus: " + build.getEventType();
            this.eventBus.publish(new Message.Builder().setDate(System.currentTimeMillis()).setEventType(str2).setSource(Message.Source.Local).setPayload(str).build());
            String str4 = "Publish to event bus done! " + build.getPayloadAsString();
        } catch (EventBusException unused) {
        }
    }

    private void sendToEventBus(Bundle bundle) {
        JSONArray jSONArray = new JSONArray();
        for (String str : bundle.keySet()) {
            buildJsonObject(bundle.getString(str), jSONArray, str);
        }
        String string = bundle.getString("key");
        buildJsonObject(bundle.getString(NOTIFICATION_URL_TYPE_WEB), jSONArray, NOTIFICATION_URL_TYPE_WEB);
        if (string == null) {
            string = EVENT_NOTIFICATION_RECEIVED;
        }
        sendMessageToEventBus(jSONArray.toString(), string);
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingReceiver
    public void onReceive(@NonNull com.amazon.alexa.protocols.messaging.Message message) {
        Bundle bundle = message.get();
        String str = "Received message: " + bundle;
        sendToEventBus(bundle);
    }

    public void publishDeepLinkClick(@NonNull Uri uri) {
        JSONArray jSONArray = new JSONArray();
        buildJsonObject(uri.toString(), jSONArray, NOTIFICATION_URL_TYPE_DEEP_LINK);
        sendMessageToEventBus(jSONArray.toString(), "notification::DeepLinkView");
    }
}
