package com.dee.app.data.reactnative;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.CacheException;
import com.dee.app.cachemanager.CacheMetadata;
import com.dee.app.cachemanager.HttpResponseWrapper;
import com.dee.app.data.reactnative.DefaultElementsDataService;
import com.dee.app.data.reactnative.ElementsDataRequest;
import com.dee.app.data.reactnative.Utils.HttpClientSerializerHelper;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import com.dee.app.metrics.MetricsConstants;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import okhttp3.Headers;
import okhttp3.Response;
import rx.Completable;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/* loaded from: classes2.dex */
public class DefaultElementsDataService implements ElementsDataService {
    private static final Completable.CompletableSubscriber CACHE_ERROR_OBSERVER = new Completable.CompletableSubscriber() { // from class: com.dee.app.data.reactnative.DefaultElementsDataService.1
        @Override // rx.Completable.CompletableSubscriber
        public void onCompleted() {
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onError(Throwable th) {
            Log.e(DefaultElementsDataService.LOG_TAG, "Async cache write error", th);
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
        }
    };
    private static final String CONTENT_TYPE = "Content-Type";
    private static final int HTTP_204_NO_CONTENT = 204;
    private static final String LOG_TAG = "com.dee.app.data.reactnative.DefaultElementsDataService";
    private static final String X_AMZN_REQUEST_ID = "x-amzn-RequestId";
    private final Cache<HttpResponseWrapper> cache;
    private final CoralService client;

    /* renamed from: com.dee.app.data.reactnative.DefaultElementsDataService$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$dee$app$data$reactnative$ElementsDataRequest$Method = new int[ElementsDataRequest.Method.values().length];

        static {
            try {
                $SwitchMap$com$dee$app$data$reactnative$ElementsDataRequest$Method[ElementsDataRequest.Method.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dee$app$data$reactnative$ElementsDataRequest$Method[ElementsDataRequest.Method.POST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$dee$app$data$reactnative$ElementsDataRequest$Method[ElementsDataRequest.Method.PUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$dee$app$data$reactnative$ElementsDataRequest$Method[ElementsDataRequest.Method.DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$dee$app$data$reactnative$ElementsDataRequest$Method[ElementsDataRequest.Method.PATCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum ResponseType {
        NETWORK,
        CACHE
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ResponseWrapper {
        private final ElementsDataResponse response;
        private final ResponseType type;

        public ResponseWrapper(ElementsDataResponse elementsDataResponse, ResponseType responseType) {
            this.type = responseType;
            this.response = elementsDataResponse;
        }

        public ElementsDataResponse getResponse() {
            return this.response;
        }

        public ResponseType getType() {
            return this.type;
        }
    }

    /* loaded from: classes2.dex */
    private class TimeoutValues implements CoralService.Timeout {
        Integer connectTimeout;
        Integer readTimeout;

        public TimeoutValues(Integer num, Integer num2) {
            this.readTimeout = num;
            this.connectTimeout = num2;
        }

        @Override // com.dee.app.http.CoralService.Timeout
        public Integer getConnectionTimeout() {
            return this.connectTimeout;
        }

        @Override // com.dee.app.http.CoralService.Timeout
        public Integer getReadTimeout() {
            return this.readTimeout;
        }
    }

    public DefaultElementsDataService(CoralService coralService, Cache<HttpResponseWrapper> cache) {
        this.client = coralService;
        this.cache = cache;
    }

    private CoralService.Call<Response> createCall(@NonNull CoralService.Request request, @NonNull ElementsDataRequest.Method method, @NonNull DataBlob dataBlob) throws CoralServiceException {
        int ordinal = method.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return setContentTypeHeader(request, dataBlob.contentType).post(dataBlob.text).asRaw();
            }
            if (ordinal == 2) {
                return setContentTypeHeader(request, dataBlob.contentType).put(dataBlob.text).asRaw();
            }
            if (ordinal == 3) {
                if (dataBlob.text != null) {
                    return setContentTypeHeader(request, dataBlob.contentType).delete(dataBlob.text).asRaw();
                }
                return request.delete().asRaw();
            } else if (ordinal == 4) {
                return setContentTypeHeader(request, dataBlob.contentType).patch(dataBlob.text).asRaw();
            } else {
                throw new CoralServiceException("The http method '" + method + "' is not supported.");
            }
        }
        return request.get().asRaw();
    }

    private Observable<ResponseWrapper> fetchFromCache(final ElementsDataRequest elementsDataRequest) {
        return this.cache.get(elementsDataRequest.uri, new CacheMetadata(elementsDataRequest.domain)).filter($$Lambda$DefaultElementsDataService$QnicEOctRx3eobWLrTaAzZbrpw.INSTANCE).map($$Lambda$DefaultElementsDataService$aEBU8aFJE5R72Fe3e_WTk1_KjxI.INSTANCE).filter(new Func1() { // from class: com.dee.app.data.reactnative.-$$Lambda$DefaultElementsDataService$fK1BRei9E7ZuSP_YKspGmlg_yeQ
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                Boolean valueOf;
                ElementsDataRequest elementsDataRequest2 = ElementsDataRequest.this;
                HttpResponseWrapper httpResponseWrapper = (HttpResponseWrapper) obj;
                valueOf = Boolean.valueOf(System.currentTimeMillis() - r5.getLastModifiedDate() <= ((long) r4.cache.ttl));
                return valueOf;
            }
        }).map(new Func1() { // from class: com.dee.app.data.reactnative.-$$Lambda$DefaultElementsDataService$amT2RGZ-l9FoqHw174xsoLA3zzw
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return DefaultElementsDataService.lambda$fetchFromCache$7(ElementsDataRequest.this, (HttpResponseWrapper) obj);
            }
        }).doOnError($$Lambda$DefaultElementsDataService$YWAyjWyahlNYjWVbQBkOqFKLxs.INSTANCE).onErrorReturn($$Lambda$DefaultElementsDataService$0xfY8BAd3r66IUt5442faGI9xRQ.INSTANCE);
    }

    private Observable<ResponseWrapper> fetchFromNetwork(final ElementsDataRequest elementsDataRequest) {
        final ElementsDataRequestAuthentication auth = elementsDataRequest.getAuth();
        return Observable.defer(new Func0() { // from class: com.dee.app.data.reactnative.-$$Lambda$DefaultElementsDataService$DnvWfsWGBMoe1kVqXJEjW8POIqk
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return DefaultElementsDataService.this.lambda$fetchFromNetwork$3$DefaultElementsDataService(elementsDataRequest, auth);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ HttpResponseWrapper lambda$fetchFromCache$5(Optional optional) {
        return (HttpResponseWrapper) optional.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ResponseWrapper lambda$fetchFromCache$7(ElementsDataRequest elementsDataRequest, HttpResponseWrapper httpResponseWrapper) {
        return new ResponseWrapper(HttpClientSerializerHelper.deserializeFromDataWrapperObject(httpResponseWrapper, elementsDataRequest.id), ResponseType.CACHE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ResponseWrapper lambda$fetchFromCache$9(Throwable th) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$requestSubscriber$1(ElementsDataRequest elementsDataRequest, ResponseWrapper responseWrapper) {
        boolean z = true;
        if (responseWrapper.type != ResponseType.NETWORK) {
            if (responseWrapper.type != ResponseType.CACHE || !elementsDataRequest.singleResult) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
        return true;
    }

    private Observable<ElementsDataResponse> requestSubscriber(final ElementsDataRequest elementsDataRequest) {
        ArrayList arrayList = new ArrayList();
        if (!elementsDataRequest.getAuth().forceRefresh && elementsDataRequest.method == ElementsDataRequest.Method.GET && elementsDataRequest.cache.retrieve) {
            arrayList.add(fetchFromCache(elementsDataRequest));
        }
        arrayList.add(fetchFromNetwork(elementsDataRequest));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((Observable) it2.next()).subscribeOn(Schedulers.io());
        }
        return Observable.merge(arrayList).filter($$Lambda$DefaultElementsDataService$G4akfjF027eUpos9Br90jvq3T1c.INSTANCE).takeUntil(new Func1() { // from class: com.dee.app.data.reactnative.-$$Lambda$DefaultElementsDataService$v_2_jWCWOyuEleUpl3AjiCwlFew
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return DefaultElementsDataService.lambda$requestSubscriber$1(ElementsDataRequest.this, (DefaultElementsDataService.ResponseWrapper) obj);
            }
        }).map($$Lambda$DefaultElementsDataService$8fxihY6RQXA72uBuuepM_4BeDI.INSTANCE);
    }

    private CoralService.Request setContentTypeHeader(@NonNull CoralService.Request request, @Nullable String str) {
        return !TextUtils.isEmpty(str) ? request.withHeader("Content-Type", str) : request;
    }

    private Map<String, String> toSingleValueMap(Headers headers) {
        Set<String> names = headers.names();
        if (names != null && !names.isEmpty()) {
            HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(names.size());
            for (String str : names) {
                newHashMapWithExpectedSize.put(str, headers.get(str));
            }
            return newHashMapWithExpectedSize;
        }
        return Collections.emptyMap();
    }

    @Override // com.dee.app.data.reactnative.ElementsDataService
    public Observable<ElementsDataResponse> execute(ElementsDataRequest elementsDataRequest) {
        return requestSubscriber(elementsDataRequest).subscribeOn(Schedulers.io()).observeOn(Schedulers.computation());
    }

    public /* synthetic */ Observable lambda$fetchFromNetwork$3$DefaultElementsDataService(ElementsDataRequest elementsDataRequest, ElementsDataRequestAuthentication elementsDataRequestAuthentication) {
        CoralService.Request request;
        if (TextUtils.isEmpty(elementsDataRequest.uri)) {
            return Observable.error(new IllegalArgumentException("uri was null in request."));
        }
        if (elementsDataRequest.method == null) {
            return Observable.error(new IllegalArgumentException("method was null in request."));
        }
        if (elementsDataRequest.connectionTimeout == null && elementsDataRequest.readTimeout == null) {
            request = this.client.request(elementsDataRequest.uri);
        } else {
            request = this.client.request(elementsDataRequest.uri, new TimeoutValues(elementsDataRequest.connectionTimeout, elementsDataRequest.readTimeout));
        }
        request.getMetadata().mo6800setDomain(elementsDataRequest.domain).mo6804setOperationName(elementsDataRequest.operationName).mo6802setMetricsSource(MetricsConstants.Source.NATIVE_ELEMENTS);
        for (Map.Entry<String, String> entry : elementsDataRequest.headers.entrySet()) {
            request.withHeader(entry.getKey(), entry.getValue());
        }
        request.withHeader(X_AMZN_REQUEST_ID, elementsDataRequest.id);
        String str = elementsDataRequestAuthentication.type;
        if (str != null) {
            request.withHeader(ElementsDataRequestAuthentication.ELEMENTS_AUTH_HEADER_TYPE, str);
        }
        String str2 = elementsDataRequestAuthentication.directedId;
        if (str2 != null) {
            request.withHeader(ElementsDataRequestAuthentication.ELEMENTS_AUTH_DIRECTED_ID, str2);
        }
        if (elementsDataRequestAuthentication.forceRefresh) {
            request.withHeader(ElementsDataRequestAuthentication.ELEMENTS_AUTH_FORCE, "true");
        }
        String str3 = elementsDataRequestAuthentication.authHeaderKey;
        if (!TextUtils.isEmpty(str3)) {
            request.withHeader(ElementsDataRequestAuthentication.ELEMENTS_AUTH_CUSTOM_HEADER, str3);
        }
        if (elementsDataRequestAuthentication.useDelegatedToken) {
            request.withHeader(ElementsDataRequestAuthentication.ELEMENTS_AUTH_USE_DELEGATED_TOKEN, "true");
        }
        try {
            DataBlob dataBlob = elementsDataRequest.body;
            String str4 = null;
            if (dataBlob == null) {
                dataBlob = new DataBlob(null, null);
            }
            Response execute = createCall(request, elementsDataRequest.method, dataBlob).execute();
            if (execute.body() != null && execute.code() != 204) {
                try {
                    str4 = execute.body().string();
                } catch (EOFException unused) {
                    str4 = "";
                }
            }
            if (elementsDataRequest.method == ElementsDataRequest.Method.GET && elementsDataRequest.cache.store && execute.isSuccessful()) {
                try {
                    this.cache.put(elementsDataRequest.uri, HttpClientSerializerHelper.serializeToDataWrapperObject(elementsDataRequest, execute, str4), new CacheMetadata(elementsDataRequest.domain)).toCompletable().subscribe(CACHE_ERROR_OBSERVER);
                } catch (CacheException e) {
                    Log.e(LOG_TAG, "Cache write exception", e);
                }
            }
            ElementsDataResponse elementsDataResponse = new ElementsDataResponse();
            elementsDataResponse.id = elementsDataRequest.id;
            elementsDataResponse.uri = elementsDataRequest.uri;
            elementsDataResponse.statusCode = execute.code();
            elementsDataResponse.statusText = execute.message();
            elementsDataResponse.contentType = execute.header("Content-Type");
            elementsDataResponse.headers = toSingleValueMap(execute.headers());
            if (str4 != null) {
                elementsDataResponse.data = new DataBlob(str4, elementsDataResponse.contentType);
            }
            return Observable.just(new ResponseWrapper(elementsDataResponse, ResponseType.NETWORK));
        } catch (CoralServiceException | IOException e2) {
            return Observable.error(e2);
        }
    }
}
