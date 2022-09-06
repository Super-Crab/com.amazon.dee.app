package com.dee.app.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.dee.app.http.CoralService;
import com.dee.app.http.HttpCoralService;
import com.dee.app.metrics.MetricComponentName;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.dee.app.metrics.MetricsConstants;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func0;
/* loaded from: classes2.dex */
public class HttpCoralService implements CoralService {
    private Gson gson;
    private OkHttpClient httpClient;
    private MetricsServiceV2 metricsService;
    private Queue<RequestInterceptor> requestInterceptors;
    private Queue<ResponseInterceptor> responseInterceptors;
    private UrlResolver urlResolver;
    private boolean useOkHttpCache;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface Deserializer<T> {
        T deserialize(Response response) throws CoralServiceException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DeserializerChain<T> implements ResponseInterceptor.Chain<T> {
        Deserializer<T> deserializer;
        HttpRequest httpRequest;
        Response response;

        DeserializerChain(HttpRequest httpRequest, Response response, Deserializer<T> deserializer) {
            this.httpRequest = httpRequest;
            this.response = response;
            this.deserializer = deserializer;
        }

        @Override // com.dee.app.http.HttpCoralService.ResponseInterceptor.Chain
        public T proceed(Response response) throws CoralServiceException {
            return this.deserializer.deserialize(response);
        }

        @Override // com.dee.app.http.HttpCoralService.ResponseInterceptor.Chain
        public HttpRequest request() {
            return this.httpRequest;
        }

        @Override // com.dee.app.http.HttpCoralService.ResponseInterceptor.Chain
        public Response response() {
            return this.response;
        }
    }

    /* loaded from: classes2.dex */
    public class HttpCall<T> implements CoralService.Call<T> {
        volatile Call call;
        Deserializer<T> deserializer;
        HttpRequest request;

        public HttpCall(HttpRequest httpRequest) {
            this.request = httpRequest;
        }

        static /* synthetic */ Response lambda$asRaw$1(Response response) throws CoralServiceException {
            return response;
        }

        @Override // com.dee.app.http.CoralService.Call
        public <E> CoralService.Call<E> as(@NonNull final Class<E> cls) {
            this.deserializer = new Deserializer() { // from class: com.dee.app.http.-$$Lambda$HttpCoralService$HttpCall$VmNIaqvUoquFH3H66wjl0rEE38I
                @Override // com.dee.app.http.HttpCoralService.Deserializer
                public final Object deserialize(Response response) {
                    return HttpCoralService.HttpCall.this.lambda$as$0$HttpCoralService$HttpCall(cls, response);
                }
            };
            return this;
        }

        @Override // com.dee.app.http.CoralService.Call
        public CoralService.Call<Response> asRaw() {
            this.deserializer = $$Lambda$HttpCoralService$HttpCall$EypX9jaP6opD5e3e6EH52bhKUq8.INSTANCE;
            return this;
        }

        @Override // com.dee.app.http.CoralService.Call
        public void cancel() {
            if (this.call != null) {
                this.call.cancel();
            }
        }

        @Override // com.dee.app.http.CoralService.Call
        public void enqueue(final CoralService.Callback<T> callback) {
            verifyPreconditions();
            this.call = HttpCoralService.this.httpClient.newCall(this.request.build());
            this.call.enqueue(new Callback() { // from class: com.dee.app.http.HttpCoralService.HttpCall.1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    HttpCall.this.request.recordFetchFailure(iOException.getLocalizedMessage());
                    callback.onFailure(HttpCall.this, new CoralServiceException(iOException));
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        callback.onResult(HttpCall.this, HttpCoralService.this.resolve(HttpCall.this.request, response, HttpCall.this.deserializer));
                        HttpCall.this.request.recordFetchSuccess();
                    } catch (CoralServiceException e) {
                        HttpCall.this.request.recordFetchFailure(e.getLocalizedMessage());
                        callback.onFailure(HttpCall.this, e);
                    }
                }
            });
        }

        @Override // com.dee.app.http.CoralService.Call
        public T execute() throws CoralServiceException {
            verifyPreconditions();
            OkHttpClient okHttpClient = HttpCoralService.this.httpClient;
            CoralService.Timeout timeout = this.request.getTimeout();
            if (timeout != null) {
                OkHttpClient.Builder newBuilder = HttpCoralService.this.httpClient.newBuilder();
                Integer connectionTimeout = timeout.getConnectionTimeout();
                if (connectionTimeout != null) {
                    newBuilder.connectTimeout(connectionTimeout.intValue(), TimeUnit.MILLISECONDS);
                }
                Integer readTimeout = timeout.getReadTimeout();
                if (readTimeout != null) {
                    newBuilder.readTimeout(readTimeout.intValue(), TimeUnit.MILLISECONDS);
                }
                okHttpClient = newBuilder.build();
            }
            this.call = okHttpClient.newCall(this.request.build());
            try {
                long currentTimeMillis = System.currentTimeMillis();
                Response execute = this.call.execute();
                HttpCoralService.this.metricsService.recordTime(this.request.createMetricDescriptor(MetricsConstants.Source.NATIVE_CORAL), System.currentTimeMillis() - currentTimeMillis);
                T t = (T) HttpCoralService.this.resolve(this.request, execute, this.deserializer);
                this.request.recordFetchSuccess();
                return t;
            } catch (IOException e) {
                this.request.recordFetchFailure(e.getLocalizedMessage());
                throw new CoralServiceException(e);
            }
        }

        @Override // com.dee.app.http.CoralService.Call
        public boolean isCanceled() {
            return this.call != null && this.call.isCanceled();
        }

        public /* synthetic */ Object lambda$as$0$HttpCoralService$HttpCall(Class cls, Response response) throws CoralServiceException {
            try {
                try {
                    return HttpCoralService.this.gson.fromJson(response.body().charStream(), (Class<Object>) cls);
                } catch (JsonParseException e) {
                    throw new CoralServiceException(e);
                }
            } finally {
                response.close();
            }
        }

        public /* synthetic */ Observable lambda$toObservable$2$HttpCoralService$HttpCall() {
            this.call = HttpCoralService.this.httpClient.newCall(this.request.build());
            try {
                long currentTimeMillis = System.currentTimeMillis();
                Response execute = this.call.execute();
                HttpCoralService.this.metricsService.recordTime(this.request.createMetricDescriptor(MetricsConstants.Source.NATIVE_CORAL), System.currentTimeMillis() - currentTimeMillis);
                Observable just = Observable.just(HttpCoralService.this.resolve(this.request, execute, this.deserializer));
                this.request.recordFetchSuccess();
                return just;
            } catch (CoralServiceException e) {
                this.request.recordFetchFailure(e.getLocalizedMessage());
                return Observable.error(e);
            } catch (IOException e2) {
                this.request.recordFetchFailure(e2.getLocalizedMessage());
                return Observable.error(new CoralServiceException(e2));
            }
        }

        @Override // com.dee.app.http.CoralService.Call
        public Observable<T> toObservable() {
            verifyPreconditions();
            return Observable.defer(new Func0() { // from class: com.dee.app.http.-$$Lambda$HttpCoralService$HttpCall$UzJraVwunR7AqbQfm6fA-Rco-iQ
                @Override // rx.functions.Func0, java.util.concurrent.Callable
                /* renamed from: call */
                public final Object mo13098call() {
                    return HttpCoralService.HttpCall.this.lambda$toObservable$2$HttpCoralService$HttpCall();
                }
            }).doOnUnsubscribe(new Action0() { // from class: com.dee.app.http.-$$Lambda$wTzDIVwyivoBpwvCu9yg5O-EMhc
                @Override // rx.functions.Action0
                public final void call() {
                    HttpCoralService.HttpCall.this.cancel();
                }
            });
        }

        void verifyPreconditions() {
            if (this.deserializer != null) {
                return;
            }
            throw new IllegalStateException("Class type was not specified. Use Request.as() or Request.asRaw()");
        }
    }

    /* loaded from: classes2.dex */
    public interface HttpRequest extends CoralService.Request {
        Request build();

        MetricDescriptor createMetricDescriptor(String str);

        Map<String, String> getHeaders();

        String getMethod();

        Object getPayload();

        String getUrl();

        void recordFetchFailure(String str);

        void recordFetchSuccess();
    }

    /* loaded from: classes2.dex */
    private class OkHttpBuildableRequest implements HttpRequest {
        static final String METHOD_DELETE = "DELETE";
        static final String METHOD_GET = "GET";
        static final String METHOD_PATCH = "PATCH";
        static final String METHOD_POST = "POST";
        static final String METHOD_PUT = "PUT";
        public final Map<String, String> headers;
        private CoralService.Request.Metadata metadata;
        private String method;
        private MetricsServiceV2 metricsService;
        private Object payload;
        private CoralService.Timeout timeout;
        private String url;

        OkHttpBuildableRequest(String str, MetricsServiceV2 metricsServiceV2) {
            this.metadata = new DefaultRequestMetadata();
            this.timeout = null;
            this.url = str;
            this.method = "GET";
            this.headers = new HashMap();
            this.metricsService = metricsServiceV2;
        }

        private RequestBody createBody() {
            if (this.payload == null) {
                if (!"GET".equals(this.method) && !"DELETE".equals(this.method)) {
                    return RequestBody.create((MediaType) null, new byte[0]);
                }
                return null;
            }
            MediaType contentType = getContentType();
            if (this.payload.getClass() == String.class) {
                return RequestBody.create(contentType, (String) this.payload);
            }
            if (this.payload.getClass() == byte[].class) {
                return RequestBody.create(contentType, (byte[]) this.payload);
            }
            if (contentType.subtype().contains("json")) {
                return RequestBody.create(contentType, HttpCoralService.this.gson.toJson(this.payload));
            }
            return RequestBody.create(contentType, this.payload.toString());
        }

        private Map<String, Object> createCustomEntriesFromRequest(HttpRequest httpRequest) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", httpRequest.getUrl());
            hashMap.put(MetricsConstants.NativeFetch.METHOD, httpRequest.getMethod());
            return hashMap;
        }

        private MediaType getContentType() {
            String str = this.headers.get("Content-Type");
            if (str == null) {
                str = "application/json";
            }
            return MediaType.parse(str);
        }

        @Override // com.dee.app.http.HttpCoralService.HttpRequest
        public Request build() {
            Request.Builder url = new Request.Builder().url(this.url);
            for (RequestInterceptor requestInterceptor : HttpCoralService.this.requestInterceptors) {
                requestInterceptor.intercept(this);
            }
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                url.addHeader(entry.getKey(), entry.getValue());
            }
            url.method(this.method, createBody());
            if (!HttpCoralService.this.useOkHttpCache) {
                url.cacheControl(CacheControl.FORCE_NETWORK);
            }
            return url.build();
        }

        @Override // com.dee.app.http.HttpCoralService.HttpRequest
        public MetricDescriptor createMetricDescriptor(String str) {
            CoralService.Request.Metadata metadata = getMetadata();
            return new MetricDescriptor.Builder(new MetricName.Builder(MetricsConstants.Id.HTTP_CLIENT_FETCH).withModule(metadata.getDomain()).withSource(str).build(), new MetricComponentName.Builder().withCategoryId(metadata.getOperationName()).build()).withCustomEntries(createCustomEntriesFromRequest(this)).build();
        }

        @Override // com.dee.app.http.CoralService.Request
        public <T> CoralService.Call<T> delete() {
            return delete(null);
        }

        @Override // com.dee.app.http.CoralService.Request
        public <T> CoralService.Call<T> get() {
            this.method = "GET";
            return new HttpCall(this);
        }

        @Override // com.dee.app.http.HttpCoralService.HttpRequest
        public Map<String, String> getHeaders() {
            return this.headers;
        }

        @Override // com.dee.app.http.CoralService.Request
        @NonNull
        public CoralService.Request.Metadata getMetadata() {
            return this.metadata;
        }

        @Override // com.dee.app.http.HttpCoralService.HttpRequest
        public String getMethod() {
            return this.method;
        }

        @Override // com.dee.app.http.HttpCoralService.HttpRequest
        public Object getPayload() {
            return this.payload;
        }

        @Override // com.dee.app.http.CoralService.Request
        public CoralService.Timeout getTimeout() {
            return this.timeout;
        }

        @Override // com.dee.app.http.HttpCoralService.HttpRequest
        public String getUrl() {
            return this.url;
        }

        @Override // com.dee.app.http.CoralService.Request
        public <T> CoralService.Call<T> patch(@Nullable Object obj) {
            this.method = METHOD_PATCH;
            this.payload = obj;
            return new HttpCall(this);
        }

        @Override // com.dee.app.http.CoralService.Request
        public <T> CoralService.Call<T> post(@Nullable Object obj) {
            this.method = "POST";
            this.payload = obj;
            return new HttpCall(this);
        }

        @Override // com.dee.app.http.CoralService.Request
        public <T> CoralService.Call<T> put(@Nullable Object obj) {
            this.method = "PUT";
            this.payload = obj;
            return new HttpCall(this);
        }

        @Override // com.dee.app.http.HttpCoralService.HttpRequest
        public void recordFetchFailure(@NonNull String str) {
            this.metricsService.recordFailure(createMetricDescriptor(getMetadata().getMetricsSource()), str);
        }

        @Override // com.dee.app.http.HttpCoralService.HttpRequest
        public void recordFetchSuccess() {
            this.metricsService.recordSuccess(createMetricDescriptor(getMetadata().getMetricsSource()));
        }

        @Override // com.dee.app.http.CoralService.Request
        public CoralService.Request withHeader(@NonNull String str, String str2) {
            this.headers.put(str, str2);
            return this;
        }

        @Override // com.dee.app.http.CoralService.Request
        public <T> CoralService.Call<T> delete(@Nullable Object obj) {
            this.method = "DELETE";
            this.payload = obj;
            return new HttpCall(this);
        }

        OkHttpBuildableRequest(String str, MetricsServiceV2 metricsServiceV2, CoralService.Timeout timeout) {
            this.metadata = new DefaultRequestMetadata();
            this.timeout = null;
            this.url = str;
            this.method = "GET";
            this.headers = new HashMap();
            this.metricsService = metricsServiceV2;
            this.timeout = timeout;
        }
    }

    /* loaded from: classes2.dex */
    public interface RequestInterceptor {
        void intercept(HttpRequest httpRequest);
    }

    /* loaded from: classes2.dex */
    public interface ResponseInterceptor {

        /* loaded from: classes2.dex */
        public interface Chain<T> {
            T proceed(Response response) throws CoralServiceException;

            HttpRequest request();

            Response response();
        }

        <T> T intercept(Chain<T> chain) throws CoralServiceException;
    }

    public HttpCoralService(OkHttpClient okHttpClient, Gson gson, UrlResolver urlResolver, MetricsServiceV2 metricsServiceV2) {
        this.responseInterceptors = new ConcurrentLinkedQueue();
        this.requestInterceptors = new ConcurrentLinkedQueue();
        this.httpClient = okHttpClient;
        this.gson = gson;
        this.urlResolver = urlResolver;
        this.useOkHttpCache = true;
        this.metricsService = metricsServiceV2;
    }

    private <T> ResponseInterceptor.Chain<T> chain(final Iterator<ResponseInterceptor> it2, final HttpRequest httpRequest, Response response, final Deserializer<T> deserializer) {
        if (it2.hasNext()) {
            return new DeserializerChain(httpRequest, response, new Deserializer() { // from class: com.dee.app.http.-$$Lambda$HttpCoralService$aApdAzb4MkTXN59R5EwJ0KduiC4
                @Override // com.dee.app.http.HttpCoralService.Deserializer
                public final Object deserialize(Response response2) {
                    return HttpCoralService.this.lambda$chain$0$HttpCoralService(it2, httpRequest, deserializer, response2);
                }
            });
        }
        return new DeserializerChain(httpRequest, response, deserializer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T resolve(HttpRequest httpRequest, Response response, Deserializer<T> deserializer) throws CoralServiceException {
        return chain(this.responseInterceptors.iterator(), httpRequest, response, deserializer).proceed(response);
    }

    public void addRequestInterceptor(RequestInterceptor requestInterceptor) {
        this.requestInterceptors.add(requestInterceptor);
    }

    public void addResponseInterceptor(ResponseInterceptor responseInterceptor) {
        this.responseInterceptors.add(responseInterceptor);
    }

    public /* synthetic */ Object lambda$chain$0$HttpCoralService(Iterator it2, HttpRequest httpRequest, Deserializer deserializer, Response response) throws CoralServiceException {
        return ((ResponseInterceptor) it2.next()).intercept(chain(it2, httpRequest, response, deserializer));
    }

    @Override // com.dee.app.http.CoralService
    public CoralService.Request request(@NonNull String str) {
        return new OkHttpBuildableRequest(this.urlResolver.resolve(str), this.metricsService);
    }

    @Override // com.dee.app.http.CoralService
    public CoralService.Request request(@NonNull String str, CoralService.Timeout timeout) {
        return new OkHttpBuildableRequest(this.urlResolver.resolve(str), this.metricsService, timeout);
    }

    public HttpCoralService(OkHttpClient okHttpClient, Gson gson, UrlResolver urlResolver, MetricsServiceV2 metricsServiceV2, boolean z) {
        this.responseInterceptors = new ConcurrentLinkedQueue();
        this.requestInterceptors = new ConcurrentLinkedQueue();
        this.httpClient = okHttpClient;
        this.gson = gson;
        this.urlResolver = urlResolver;
        this.metricsService = metricsServiceV2;
        this.useOkHttpCache = z;
    }
}
