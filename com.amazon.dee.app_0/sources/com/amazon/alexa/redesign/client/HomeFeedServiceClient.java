package com.amazon.alexa.redesign.client;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.redesign.poller.PollingManager;
import com.amazon.alexa.redesign.utils.Constants;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import com.google.common.base.Joiner;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class HomeFeedServiceClient implements PollingManager.NetworkApi {
    private static final String HOME_FEED_API = "/api/content";
    private CoralService coralService;

    public HomeFeedServiceClient(CoralService coralService) {
        this.coralService = coralService;
    }

    private CoralService.Call<Object> applyMethod(CoralService.Request request, String str, @Nullable Object obj) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == 70454) {
            if (str.equals("GET")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 2461856) {
            if (hashCode == 2012838315 && str.equals(Constants.REQUEST_METHOD_DELETE)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("POST")) {
                c = 2;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                return request.delete();
            }
            if (c != 2) {
                return request.get();
            }
            return request.post(obj.toString());
        }
        return request.get();
    }

    private JSONObject fromResponse(Response response) throws IOException, JSONException {
        if (response == null || !response.isSuccessful()) {
            if (response == null) {
                throw new IOException("response_null");
            }
            throw new IOException(String.valueOf(response.code()));
        }
        return new JSONObject(response.body().string());
    }

    @VisibleForTesting
    String buildUrl(String str, Map<String, String> map) {
        if (map == null) {
            return str;
        }
        HashMap hashMap = new HashMap(map);
        hashMap.put("timezone", TimeZone.getDefault().getID());
        return str + WebConstants.UriConstants.QUESTIONMARK_KEY + Joiner.on(WebConstants.UriConstants.AMPERSAND_KEY).withKeyValueSeparator(Config.Compare.EQUAL_TO).join(hashMap);
    }

    @Override // com.amazon.alexa.redesign.poller.PollingManager.NetworkApi
    public Single<JSONObject> getContent(String str) {
        return request(str, null, "GET", null);
    }

    public Single<JSONObject> getRawCardFeeds(@Nullable final Map<String, String> map) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.redesign.client.-$$Lambda$HomeFeedServiceClient$481XulmxlZZvm6PgVpeu25I9tNQ
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                HomeFeedServiceClient.this.lambda$getRawCardFeeds$0$HomeFeedServiceClient(map, singleEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$getRawCardFeeds$0$HomeFeedServiceClient(Map map, SingleEmitter singleEmitter) throws Throwable {
        try {
            singleEmitter.onSuccess(fromResponse(this.coralService.request(buildUrl("/api/content", map)).get().asRaw().execute()));
        } catch (CoralServiceException | IOException | JSONException e) {
            singleEmitter.onError(e);
        }
    }

    public /* synthetic */ void lambda$request$1$HomeFeedServiceClient(String str, Map map, String str2, Object obj, SingleEmitter singleEmitter) throws Throwable {
        try {
            singleEmitter.onSuccess(fromResponse(applyMethod(this.coralService.request(buildUrl(str, map)), str2, obj).asRaw().execute()));
        } catch (CoralServiceException | IOException | JSONException e) {
            singleEmitter.onError(e);
        }
    }

    public Single<JSONObject> request(final String str, final Map<String, String> map, final String str2, @Nullable final Object obj) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.redesign.client.-$$Lambda$HomeFeedServiceClient$rIPExuUZ0mBWVW2aJlS_lktF3_k
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                HomeFeedServiceClient.this.lambda$request$1$HomeFeedServiceClient(str, map, str2, obj, singleEmitter);
            }
        });
    }
}
