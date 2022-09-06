package com.amazon.alexa.voice.tta.simba;

import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.voice.tta.dependencies.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.gson.Gson;
import java.util.Locale;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.Typography;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SimbaClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJV\u0010\u000f\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0018\u001a\u00020\u0012J.\u0010\u0019\u001a\u00020\u001a2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u001fJ.\u0010 \u001a\u00020!2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\"\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006$"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SimbaClient;", "", "gson", "Lcom/google/gson/Gson;", "(Lcom/google/gson/Gson;)V", "getGson", "()Lcom/google/gson/Gson;", "getFrictivePrompts", "Lcom/amazon/alexa/voice/tta/simba/FrictivePromptsSimbaResponse;", "coralService", "Lcom/dee/app/http/CoralService;", "deviceInfo", "Lcom/amazon/alexa/voice/tta/dependencies/DeviceInfo;", "locale", "Ljava/util/Locale;", "getSearchResults", "Lcom/amazon/alexa/voice/tta/simba/SearchSimbaResponse;", "queryText", "", "wasAvsResponseEmpty", "", "avsResponseToken", "promptId", "variant", "namespace", "getSuggestions", "Lcom/amazon/alexa/voice/tta/simba/SuggestionsSimbaResponse;", "postRequest", "Lokhttp3/Response;", "request", RouteParameter.PATH, "postRequest$AlexaMobileAndroidVoice_TTA_release", "updateInteraction", "Lcom/amazon/alexa/voice/tta/simba/UpdateInteractionSimbaResponse;", "resultId", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SimbaClient {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String SIMBA_FRICTIVE_PROMPTS_URL_PATH = "/api/simba/frictivePrompts";
    @NotNull
    public static final String SIMBA_SEARCH_RESULTS_URL_PATH = "/api/simba/searchResults";
    @NotNull
    public static final String SIMBA_SUGGESTIONS_URL_PATH = "/api/simba/suggestions";
    @NotNull
    public static final String SIMBA_UPDATE_INTERACTION_URL_PATH = "/api/simba/updateInteractions";
    @NotNull
    public static final String SIMBA_URL_PATH = "/api/simba";
    private static final String TAG = "SimbaClient";
    @NotNull
    private final Gson gson;

    /* compiled from: SimbaClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SimbaClient$Companion;", "", "()V", "SIMBA_FRICTIVE_PROMPTS_URL_PATH", "", "SIMBA_SEARCH_RESULTS_URL_PATH", "SIMBA_SUGGESTIONS_URL_PATH", "SIMBA_UPDATE_INTERACTION_URL_PATH", "SIMBA_URL_PATH", "TAG", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SimbaClient(@NotNull Gson gson) {
        Intrinsics.checkParameterIsNotNull(gson, "gson");
        this.gson = gson;
    }

    @NotNull
    public final FrictivePromptsSimbaResponse getFrictivePrompts(@Nullable CoralService coralService, @Nullable DeviceInfo deviceInfo, @Nullable Locale locale) {
        if (coralService != null) {
            if (deviceInfo == null) {
                throw new IllegalArgumentException("DeviceInfo can't be null".toString());
            }
            if (locale != null) {
                String uuid = UUID.randomUUID().toString();
                Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("?dsn=");
                outline107.append(deviceInfo.getDsn());
                outline107.append(Typography.amp);
                outline107.append("platform=");
                outline107.append(deviceInfo.getPlatform());
                outline107.append(Typography.amp);
                outline107.append("platformVersion=");
                outline107.append(deviceInfo.getPlatformVersion());
                outline107.append(Typography.amp);
                outline107.append("appVersion=");
                outline107.append(deviceInfo.getAppVersion());
                outline107.append(Typography.amp);
                outline107.append("locale=");
                outline107.append(locale);
                outline107.append(Typography.amp);
                outline107.append("marketplaceId=");
                outline107.append(deviceInfo.getMarketplaceId());
                outline107.append(Typography.amp);
                outline107.append("clientRequestId=");
                outline107.append(uuid);
                CoralService.Request request = coralService.request(SIMBA_FRICTIVE_PROMPTS_URL_PATH + outline107.toString());
                String str = "getFrictivePrompts: request : " + request;
                Response execute = request.get().asRaw().execute();
                String str2 = request + ": response code" + execute.code();
                String str3 = request + ": response message" + execute.message();
                String str4 = request + ": response message" + execute.peekBody(Long.MAX_VALUE).string();
                Gson gson = this.gson;
                ResponseBody body = execute.body();
                Object fromJson = gson.fromJson(body != null ? body.string() : null, (Class<Object>) FrictivePromptsSimbaResponse.class);
                Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(response.b…imbaResponse::class.java)");
                return (FrictivePromptsSimbaResponse) fromJson;
            }
            throw new IllegalArgumentException("locale can't be null".toString());
        }
        throw new IllegalArgumentException("CoralService can't be null".toString());
    }

    @NotNull
    public final Gson getGson() {
        return this.gson;
    }

    @NotNull
    public final SearchSimbaResponse getSearchResults(@Nullable CoralService coralService, @Nullable DeviceInfo deviceInfo, @NotNull String queryText, @Nullable Locale locale, boolean z, @NotNull String avsResponseToken, @NotNull String promptId, @Nullable String str, @NotNull String namespace) {
        String replace$default;
        Intrinsics.checkParameterIsNotNull(queryText, "queryText");
        Intrinsics.checkParameterIsNotNull(avsResponseToken, "avsResponseToken");
        Intrinsics.checkParameterIsNotNull(promptId, "promptId");
        Intrinsics.checkParameterIsNotNull(namespace, "namespace");
        if (coralService != null) {
            if (deviceInfo == null) {
                throw new IllegalArgumentException("DeviceInfo can't be null".toString());
            }
            if (locale != null) {
                String uuid = UUID.randomUUID().toString();
                Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
                String languageTag = locale.toLanguageTag();
                Intrinsics.checkExpressionValueIsNotNull(languageTag, "locale.toLanguageTag()");
                replace$default = StringsKt__StringsJVMKt.replace$default(languageTag, ProcessIdUtil.DEFAULT_PROCESSID, "_", false, 4, (Object) null);
                Response postRequest$AlexaMobileAndroidVoice_TTA_release = postRequest$AlexaMobileAndroidVoice_TTA_release(coralService, new SearchSimbaRequest(queryText, promptId, namespace, str, avsResponseToken, z, uuid, replace$default, deviceInfo.getMarketplaceId(), deviceInfo.getAppVersion(), deviceInfo.getPlatform(), deviceInfo.getPlatformVersion(), deviceInfo.getDsn(), false), SIMBA_SEARCH_RESULTS_URL_PATH);
                Gson gson = this.gson;
                ResponseBody body = postRequest$AlexaMobileAndroidVoice_TTA_release.body();
                Object fromJson = gson.fromJson(body != null ? body.string() : null, (Class<Object>) SearchSimbaResponse.class);
                Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(response.b…imbaResponse::class.java)");
                return (SearchSimbaResponse) fromJson;
            }
            throw new IllegalArgumentException("locale can't be null".toString());
        }
        throw new IllegalArgumentException("CoralService can't be null".toString());
    }

    @NotNull
    public final SuggestionsSimbaResponse getSuggestions(@Nullable CoralService coralService, @Nullable DeviceInfo deviceInfo, @Nullable String str, @Nullable Locale locale) {
        String replace$default;
        if (coralService != null) {
            if (deviceInfo == null) {
                throw new IllegalArgumentException("DeviceInfo can't be null".toString());
            }
            if (str == null) {
                throw new IllegalArgumentException("Query text can't be null".toString());
            }
            if (locale != null) {
                String uuid = UUID.randomUUID().toString();
                Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
                String languageTag = locale.toLanguageTag();
                Intrinsics.checkExpressionValueIsNotNull(languageTag, "locale.toLanguageTag()");
                replace$default = StringsKt__StringsJVMKt.replace$default(languageTag, ProcessIdUtil.DEFAULT_PROCESSID, "_", false, 4, (Object) null);
                Response postRequest$AlexaMobileAndroidVoice_TTA_release = postRequest$AlexaMobileAndroidVoice_TTA_release(coralService, new SuggestionsSimbaRequest(str, null, uuid, replace$default, deviceInfo.getMarketplaceId(), deviceInfo.getAppVersion(), deviceInfo.getDsn(), deviceInfo.getPlatform(), deviceInfo.getPlatformVersion(), false), SIMBA_SUGGESTIONS_URL_PATH);
                Gson gson = this.gson;
                ResponseBody body = postRequest$AlexaMobileAndroidVoice_TTA_release.body();
                Object fromJson = gson.fromJson(body != null ? body.string() : null, (Class<Object>) SuggestionsSimbaResponse.class);
                Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(response.b…imbaResponse::class.java)");
                return (SuggestionsSimbaResponse) fromJson;
            }
            throw new IllegalArgumentException("Locale can't be null".toString());
        }
        throw new IllegalArgumentException("CoralService can't be null".toString());
    }

    @NotNull
    public final Response postRequest$AlexaMobileAndroidVoice_TTA_release(@NotNull CoralService coralService, @NotNull Object request, @NotNull String path) {
        Intrinsics.checkParameterIsNotNull(coralService, "coralService");
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(path, "path");
        String str = "Request " + path + " , request: " + this.gson.toJson(request);
        Response response = coralService.request(path).withHeader("Content-Type", "application/json").post(request).asRaw().execute();
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(path, ": response code: ");
        outline113.append(response.code());
        outline113.append(" and message ");
        outline113.append(response.message());
        outline113.toString();
        String str2 = path + ": response message : " + response.peekBody(Long.MAX_VALUE).string();
        Intrinsics.checkExpressionValueIsNotNull(response, "response");
        return response;
    }

    @NotNull
    public final UpdateInteractionSimbaResponse updateInteraction(@Nullable CoralService coralService, @Nullable DeviceInfo deviceInfo, @Nullable String str, @Nullable Locale locale) {
        String replace$default;
        if (coralService != null) {
            if (deviceInfo == null) {
                throw new IllegalArgumentException("DeviceInfo can't be null".toString());
            }
            if (str == null) {
                throw new IllegalArgumentException("resultId can't be null".toString());
            }
            if (locale != null) {
                String uuid = UUID.randomUUID().toString();
                Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
                String languageTag = locale.toLanguageTag();
                Intrinsics.checkExpressionValueIsNotNull(languageTag, "locale.toLanguageTag()");
                replace$default = StringsKt__StringsJVMKt.replace$default(languageTag, ProcessIdUtil.DEFAULT_PROCESSID, "_", false, 4, (Object) null);
                Response postRequest$AlexaMobileAndroidVoice_TTA_release = postRequest$AlexaMobileAndroidVoice_TTA_release(coralService, new UpdateInteractionSimbaRequest(str, uuid, replace$default, deviceInfo.getMarketplaceId(), deviceInfo.getAppVersion(), deviceInfo.getPlatform(), deviceInfo.getPlatformVersion(), deviceInfo.getDsn(), false), SIMBA_UPDATE_INTERACTION_URL_PATH);
                Gson gson = this.gson;
                ResponseBody body = postRequest$AlexaMobileAndroidVoice_TTA_release.body();
                Object fromJson = gson.fromJson(body != null ? body.string() : null, (Class<Object>) UpdateInteractionSimbaResponse.class);
                Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(response.b…imbaResponse::class.java)");
                return (UpdateInteractionSimbaResponse) fromJson;
            }
            throw new IllegalArgumentException("locale can't be null".toString());
        }
        throw new IllegalArgumentException("CoralService can't be null".toString());
    }
}
