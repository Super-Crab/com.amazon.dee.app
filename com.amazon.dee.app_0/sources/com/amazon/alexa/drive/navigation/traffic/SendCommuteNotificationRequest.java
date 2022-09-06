package com.amazon.alexa.drive.navigation.traffic;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.drive.navigation.AutoValueAdapterFactory;
import com.amazon.alexa.drive.navigation.traffic.AutoValue_SendCommuteNotificationRequest;
import com.amazon.alexa.drive.navigation.traffic.C$AutoValue_SendCommuteNotificationRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;
@AutoValue
/* loaded from: classes7.dex */
public abstract class SendCommuteNotificationRequest implements JsonObjectSerializable {
    private static final String TAG = "SendCommuteNotificationRequest";
    public static final String URL = "/api/traffic/commute/%s/sendNotification?clientId=%s&customerId=%s&deviceSerialNumber=%s&deviceType=%s&commuteDestinationLabel=%s&commuteDestinationType=%s";

    @AutoValue.Builder
    /* loaded from: classes7.dex */
    static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract SendCommuteNotificationRequest build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setClientId(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setCommuteDestinationLabel(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setCommuteDestinationType(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setCustomerId(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setDeviceSerialNumber(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setDeviceType(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setPersonId(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setRequestId(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder() {
        return new C$AutoValue_SendCommuteNotificationRequest.Builder();
    }

    private String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public static TypeAdapter<SendCommuteNotificationRequest> typeAdapter(Gson gson) {
        return new AutoValue_SendCommuteNotificationRequest.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getClientId();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getCommuteDestinationLabel();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getCommuteDestinationType();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getCustomerId();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getDeviceSerialNumber();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getDeviceType();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String getPersonId();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getRequestId();

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject(new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create().toJson(this));
    }

    public String toUrl() {
        String format = String.format(URL, encode(getRequestId()), encode(getClientId()), encode(getCustomerId()), encode(getDeviceSerialNumber()), encode(getDeviceType()), encode(getCommuteDestinationLabel()), encode(getCommuteDestinationType()));
        if (getPersonId() != null) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(format, "&personId=");
            outline113.append(encode(getPersonId()));
            return outline113.toString();
        }
        return format;
    }
}
