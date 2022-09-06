package com.amazon.alexa.redesign.actions;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.redesign.entity.ViewModelFactory;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public final class ActionFactoryUtil {
    private static final String ACCESSIBILITY_LABEL = "accessibilityLabel";
    private static final String ACTIONS = "actions";
    private static final String ALBUM_ART_URL = "albumArtUrl";
    private static final String ASIN = "asin";
    private static final String AUDIBLE = "audible";
    private static final String AUDIBLE_URI_VALUE = "/api/audible/queue-and-play";
    private static final String COMMAND = "command";
    private static final String DEVICE_TYPE_KEY = "deviceType";
    private static final String DEVICE_TYPE_VALUE = "$deviceType";
    private static final String DSN_KEY = "deviceSerialNumber";
    private static final String DSN_VALUE = "$dsn";
    private static final String FALLBACK_IMAGE = "fallbackImage";
    private static final String FALLBACK_IMAGE_DEFAULT = "MUSIC";
    private static final String LAUNCH_DEVICE_PICKER_EVENT = "CommonDevicePicker";
    private static final String LAUNCH_DEVICE_PICKER_EVENT_HOME = "launch:device:picker";
    private static final String LINK_ACCOUNT_EVENT = "link:account";
    private static final String MEDIA_OWNER_CUSTOMER_ID = "mediaOwnerCustomerId";
    private static final String META_ACTION_TYPE = "metaActionType";
    private static final String METHOD_KEY = "method";
    private static final String METHOD_VALUE = "POST";
    private static final String OPEN = "Open";
    private static final String OPEN_URL_EVENT = "open:url";
    private static final String PAYLOAD = "payload";
    private static final String PAYLOAD_TEMPLATE = "payloadTemplate";
    private static final String PROVIDER_ID = "providerId";
    private static final String PROVIDER_ID_DEFAULT = "ROBIN";
    private static final String QUEUE_ID = "queueId";
    private static final String SELECTED_MEDIA = "selectedMedia";
    private static final String SUBTEXT = "subtext";
    private static final String SUB_TITLE = "subtitle";
    private static final String TEXT = "text";
    private static final String TITLE = "title";
    private static final String URI_KEY = "uri";
    private static final String URI_VALUE = "/api/media/play-historical-queue";

    private ActionFactoryUtil() {
    }

    public static String getAccessibilityLabel(JSONObject jSONObject) {
        return jSONObject.optString("accessibilityLabel");
    }

    public static String getActionMetaTypeForMetric(JSONObject jSONObject) {
        return jSONObject.optString(META_ACTION_TYPE);
    }

    public static String getAndroidSettingsUrl(JSONObject jSONObject) {
        return jSONObject.optString("androidSettingsUrl");
    }

    public static JSONObject getBody(JSONObject jSONObject) {
        return jSONObject.optJSONObject("body");
    }

    public static ViewTypeModel getBottomSheetModel(JSONObject jSONObject, ViewModelFactory viewModelFactory) {
        try {
            return viewModelFactory.getModel(jSONObject.optJSONObject("view"));
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("HomeChannelNative: View not found in BottomSheetAction json", e);
        }
    }

    public static Message getDevicePickerMessageFromJson(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        JSONObject jSONObject5 = new JSONObject();
        JSONObject jSONObject6 = new JSONObject();
        try {
            jSONObject2.put("command", OPEN);
            boolean optBoolean = jSONObject.optBoolean(AUDIBLE, false);
            boolean has = jSONObject.has("asin");
            String optString = jSONObject.optString("asin", "");
            if (optBoolean && has) {
                jSONObject4.put("asin", optString);
            }
            jSONObject4.put("queueId", jSONObject.optString("queueId", ""));
            jSONObject4.put("deviceType", DEVICE_TYPE_VALUE);
            jSONObject4.put("deviceSerialNumber", DSN_VALUE);
            jSONObject4.put("mediaOwnerCustomerId", jSONObject.optString("mediaOwnerCustomerId", ""));
            jSONObject5.put("uri", optBoolean ? AUDIBLE_URI_VALUE : URI_VALUE);
            jSONObject5.put("method", "POST");
            jSONObject5.put("payloadTemplate", jSONObject4.toString());
            jSONObject6.put("title", jSONObject.optString("text", ""));
            jSONObject6.put(SUB_TITLE, jSONObject.optString(SUBTEXT, ""));
            jSONObject6.put(PROVIDER_ID, jSONObject.optString(PROVIDER_ID, PROVIDER_ID_DEFAULT));
            jSONObject6.put(ALBUM_ART_URL, jSONObject.optString(ALBUM_ART_URL, ""));
            jSONObject6.put(FALLBACK_IMAGE, jSONObject.optString(FALLBACK_IMAGE, FALLBACK_IMAGE_DEFAULT));
            jSONObject6.put("actions", jSONObject5);
            jSONObject3.put(SELECTED_MEDIA, jSONObject6);
            jSONObject2.put("payload", jSONObject3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Message.Builder().setEventType(LAUNCH_DEVICE_PICKER_EVENT).setPayload(jSONObject2.toString()).build();
    }

    public static String getEndpoint(JSONObject jSONObject) {
        return jSONObject.optString("endpoint");
    }

    public static Message getHomeDevicePickerMessageFromJson(JSONObject jSONObject) {
        return new Message.Builder().setEventType(LAUNCH_DEVICE_PICKER_EVENT_HOME).setPayload(jSONObject.toString()).build();
    }

    public static Message getLinkAccountMessageFromJson(JSONObject jSONObject) {
        return new Message.Builder().setEventType(LINK_ACCOUNT_EVENT).setPayload(jSONObject.toString()).build();
    }

    public static String getMethod(JSONObject jSONObject) {
        return jSONObject.optString("method");
    }

    public static Message getOpenUrlMessageFromJson(JSONObject jSONObject) {
        return new Message.Builder().setEventType(OPEN_URL_EVENT).setPayload(jSONObject.toString()).build();
    }

    public static String getPlaystoreName(JSONObject jSONObject) {
        return jSONObject.optString("playStoreName");
    }

    public static String getPlaystoreUrl(JSONObject jSONObject) {
        return jSONObject.optString("playStoreUrl");
    }

    public static String getRouteFromJson(JSONObject jSONObject) {
        String optString = jSONObject.optString("route");
        if (optString == null || optString.isEmpty()) {
            throw new IllegalArgumentException("HomeChannelNative: Route not found in json object.");
        }
        return optString;
    }

    public static String getUrl(JSONObject jSONObject) {
        return jSONObject.optString("url");
    }
}
