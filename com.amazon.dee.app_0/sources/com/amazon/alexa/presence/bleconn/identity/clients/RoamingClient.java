package com.amazon.alexa.presence.bleconn.identity.clients;

import android.net.Uri;
import android.util.Log;
import android.webkit.CookieManager;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.identity.auth.device.authorization.RegionUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* loaded from: classes9.dex */
public class RoamingClient {
    private static final Gson GSON = new Gson();
    private static final String TAG = "com.amazon.alexa.presence.bleconn.identity.clients.RoamingClient";
    private final Config config;
    private final OkHttpClient httpClient;

    /* loaded from: classes9.dex */
    public static class Config {
        private final String endpoint;

        public Config(String str) {
            this.endpoint = str;
        }

        public String getEndpoint() {
            return this.endpoint;
        }
    }

    @Inject
    public RoamingClient(Config config, OkHttpClient okHttpClient) {
        this.config = config;
        this.httpClient = okHttpClient;
    }

    private Response call(final Request request) {
        final Ref ref = new Ref();
        new Runnable() { // from class: com.amazon.alexa.presence.bleconn.identity.clients.-$$Lambda$RoamingClient$IL5baTIa4LnzCQXYg1YQ8zG1Eng
            @Override // java.lang.Runnable
            public final void run() {
                RoamingClient.this.lambda$call$0$RoamingClient(ref, request);
            }
        }.run();
        if (ref.getRef() == null) {
            Log.e(TAG, "Unable to retrieve response for request.");
            return null;
        }
        return (Response) ref.getRef();
    }

    private String getRoamingSettingValue(String str) {
        try {
            String roamingSettingsUri = roamingSettingsUri(str);
            String cookie = CookieManager.getInstance().getCookie(roamingSettingsUri);
            if (cookie == null) {
                return null;
            }
            Response call = call(new Request.Builder().header("Accept", "application/json").header("Accept-Encoding", "gzip").header("Accept-Language", "en-US,en;").header("Cookie", cookie).url(roamingSettingsUri).get().build());
            if (call == null) {
                Log.w(TAG, "No response received.");
                return null;
            }
            int code = call.code();
            String str2 = "Status Code: " + code;
            if (code >= 400) {
                Log.w(TAG, "Unable to access API");
            }
            ResponseBody body = call.body();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                contentType.toString();
            }
            String string = body.string();
            String str3 = "Response: " + string;
            Map map = (Map) GSON.fromJson(string, (Class<Object>) Map.class);
            if (!map.containsKey("settingState")) {
                return null;
            }
            return (String) map.get("settingState");
        } catch (Throwable th) {
            Log.e(TAG, "Error attempting to communicate with Roaming.", th);
            return null;
        }
    }

    private String roamingSettingsUri(String str) {
        return new Uri.Builder().scheme("https").authority(this.config.getEndpoint()).appendPath("api").appendPath("roaming").appendPath("v1").appendPath("settings").appendQueryParameter("principalType", "PERSONS").appendQueryParameter("?settingName", "Roaming.enabled").appendQueryParameter("?principalIdentifier", str).build().toString().replaceAll("%3F", WebConstants.UriConstants.QUESTIONMARK_KEY);
    }

    public boolean isRoamingEnabled(String str) {
        String roamingSettingValue = getRoamingSettingValue(str);
        if (roamingSettingValue == null) {
            Log.w(TAG, "No setting value retrieved.  Defaulting to off.");
            return false;
        }
        char c = 65535;
        int hashCode = roamingSettingValue.hashCode();
        if (hashCode != 2527) {
            if (hashCode != 78159) {
                if (hashCode == 2020783 && roamingSettingValue.equals(RegionUtil.REGION_STRING_AUTO)) {
                    c = 2;
                }
            } else if (roamingSettingValue.equals("OFF")) {
                c = 0;
            }
        } else if (roamingSettingValue.equals("ON")) {
            c = 1;
        }
        if (c == 0 || c == 1) {
            return false;
        }
        if (c == 2) {
            return true;
        }
        GeneratedOutlineSupport1.outline164("Unknown setting returned from cloud: ", roamingSettingValue, TAG);
        return false;
    }

    public /* synthetic */ void lambda$call$0$RoamingClient(Ref ref, Request request) {
        try {
            ref.setRef(this.httpClient.newCall(request).execute());
        } catch (Throwable th) {
            Log.w(TAG, "Error encountered retrieving roaming setting", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Ref<T> {
        private T ref;

        Ref() {
            this.ref = null;
        }

        public T getRef() {
            return this.ref;
        }

        public void setRef(T t) {
            this.ref = t;
        }

        Ref(T t) {
            this.ref = null;
            this.ref = t;
        }
    }
}
