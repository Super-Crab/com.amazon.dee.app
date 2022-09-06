package com.amazon.alexa.drive.navigation.traffic;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.drive.navigation.AutoValueAdapterFactory;
import com.amazon.alexa.drive.navigation.traffic.AutoValue_GetCommuteDetailsRequest;
import com.amazon.alexa.drive.navigation.traffic.C$AutoValue_GetCommuteDetailsRequest;
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
public abstract class GetCommuteDetailsRequest implements JsonObjectSerializable {
    private static final String TAG = "GetCommuteDetailsRequest";
    public static final String URL = "/api/traffic/commute/%s/details?clientId=%s&correlationId=%s";

    @AutoValue.Builder
    /* loaded from: classes7.dex */
    static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract GetCommuteDetailsRequest build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setClientId(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setCorrelationId(String str);

        abstract Builder setRankingCriteria(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setRequestId(String str);

        abstract Builder setTransportMode(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder() {
        return new C$AutoValue_GetCommuteDetailsRequest.Builder();
    }

    private String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public static TypeAdapter<GetCommuteDetailsRequest> typeAdapter(Gson gson) {
        return new AutoValue_GetCommuteDetailsRequest.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getClientId();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getCorrelationId();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String getRankingCriteria();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getRequestId();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String getTransportMode();

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject(new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create().toJson(this));
    }

    public String toUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(URL, encode(getRequestId()), encode(getClientId()), encode(getCorrelationId())));
        if (getRankingCriteria() != null) {
            sb.append("&rankingCriteria=");
            sb.append(encode(getRankingCriteria()));
        }
        if (getTransportMode() != null) {
            sb.append("&transportMode=");
            sb.append(encode(getTransportMode()));
        }
        return sb.toString();
    }
}
