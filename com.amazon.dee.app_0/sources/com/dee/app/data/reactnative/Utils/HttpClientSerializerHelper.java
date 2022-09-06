package com.dee.app.data.reactnative.Utils;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.dee.app.cachemanager.HttpResponseWrapper;
import com.dee.app.data.reactnative.DataBlob;
import com.dee.app.data.reactnative.ElementsCacheMetadata;
import com.dee.app.data.reactnative.ElementsDataRequest;
import com.dee.app.data.reactnative.ElementsDataResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* loaded from: classes2.dex */
public final class HttpClientSerializerHelper {
    private HttpClientSerializerHelper() {
    }

    @Nullable
    public static ElementsDataResponse deserializeFromDataWrapperObject(@NonNull HttpResponseWrapper httpResponseWrapper, String str) {
        ElementsDataResponse elementsDataResponse = new ElementsDataResponse();
        elementsDataResponse.id = str;
        elementsDataResponse.cacheMetadata = new ElementsCacheMetadata();
        elementsDataResponse.cacheMetadata.timestamp = httpResponseWrapper.getLastModifiedDate();
        elementsDataResponse.statusCode = httpResponseWrapper.getStatusCode();
        elementsDataResponse.statusText = httpResponseWrapper.getStatusText();
        elementsDataResponse.headers = deserializeHttpHeaders(httpResponseWrapper.getHeaders());
        elementsDataResponse.uri = httpResponseWrapper.getUri();
        elementsDataResponse.data = new DataBlob(httpResponseWrapper.getData(), httpResponseWrapper.getContentType());
        elementsDataResponse.contentType = httpResponseWrapper.getContentType();
        return elementsDataResponse;
    }

    private static Map<String, String> deserializeHttpHeaders(String[] strArr) {
        if (strArr.length % 2 == 0) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < strArr.length; i += 2) {
                hashMap.put(strArr[i], strArr[i + 1]);
            }
            return hashMap;
        }
        throw new IllegalArgumentException("Uneven amount of key/value pairs for http headers.");
    }

    private static String[] serializeHttpHeaders(Headers headers) {
        ArrayList arrayList = new ArrayList();
        for (String str : headers.names()) {
            String join = TextUtils.join(", ", headers.values(str));
            arrayList.add(str);
            arrayList.add(join);
        }
        if (arrayList.size() % 2 == 0) {
            return (String[]) Arrays.copyOf(arrayList.toArray(), arrayList.size(), String[].class);
        }
        throw new IllegalStateException("Uneven amount of key/value pairs for http headers.");
    }

    public static HttpResponseWrapper serializeToDataWrapperObject(@NonNull ElementsDataRequest elementsDataRequest, @NonNull Response response, String str) {
        ResponseBody body = response.body();
        return new HttpResponseWrapper(elementsDataRequest.uri, response.code(), response.message(), serializeHttpHeaders(response.headers()), (body == null || body.contentType() == null) ? null : body.contentType().toString(), str, elementsDataRequest.cache.evictionTier);
    }
}
