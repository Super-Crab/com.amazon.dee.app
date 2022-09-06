package com.dee.app.data.reactnative.bridges;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.dee.app.services.coral.GZIPRequestInterceptor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.DataBlob;
import com.dee.app.data.reactnative.ElementsDataRequest;
import com.dee.app.data.reactnative.ElementsDataRequestAuthentication;
import com.dee.app.data.reactnative.ElementsDataResponse;
import com.dee.app.data.reactnative.ElementsDataService;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;
import rx.Observer;
/* loaded from: classes2.dex */
public class HttpClientModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "HttpClientModule";
    static final String TAG = "HttpClientModule";
    public static final int VERSION_NUMBER = 1;
    private final ElementsDataService client;

    /* loaded from: classes2.dex */
    public static final class ElementsBodyKey {
        public static final String COMPRESS = "compress";
        public static final String CONTENT_TYPE = "contentType";
        public static final String TEXT = "text";

        private ElementsBodyKey() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class ElementsCacheKey {
        public static final String RETRIEVE = "retrieve";
        public static final String STORE = "store";
        public static final String TTL = "ttl";

        private ElementsCacheKey() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class ElementsRequestKey {
        public static final String AUTH = "auth";
        public static final String AUTH_HEADER_KEY = "authHeaderKey";
        public static final String BEARER = "bearer";
        public static final String BODY = "body";
        public static final String CACHE = "cache";
        public static final String CONNECTION_TIMEOUT = "connection_timeout";
        public static final String DIRECTED_ID = "directedId";
        public static final String FORCE_REFRESH = "forceRefresh";
        public static final String HEADERS = "headers";
        public static final String MODULE = "module";
        public static final String OPERATION_NAME = "operationName";
        public static final String READ_TIMEOUT = "read_timeout";
        public static final String REQUEST_ID = "requestId";
        public static final String SINGLE_RESULT = "singleResult";
        public static final String SUPPRESS_BEARER = "suppressBearer";
        public static final String TYPE = "type";
        public static final String USE_DELEGATED_TOKEN = "useDelegatedToken";
        public static final String WEB = "web";

        private ElementsRequestKey() {
        }
    }

    /* loaded from: classes2.dex */
    static class EventEmitterObserver implements Observer<WritableMap> {
        private static final String COMPLETE = "complete";
        private static final String ERROR_TYPE = "error";
        private static final String HTTP_EVENT = "httpEvent";
        private final WeakReference<DeviceEventManagerModule.RCTDeviceEventEmitter> emitterRef;
        private final ElementsDataRequest request;

        /* loaded from: classes2.dex */
        public static final class ElementsCompleteKey {
            public static final String REQUEST_ID = "requestId";
            public static final String TYPE = "type";

            private ElementsCompleteKey() {
            }
        }

        /* loaded from: classes2.dex */
        public static final class ElementsErrorKey {
            public static final String ERROR = "error";
            public static final String MESSAGE = "message";
            public static final String REQUEST_ID = "requestId";
            public static final String STACK = "stack";
            public static final String TYPE = "type";

            private ElementsErrorKey() {
            }
        }

        public EventEmitterObserver(ElementsDataRequest elementsDataRequest, DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter) {
            this.request = elementsDataRequest;
            this.emitterRef = new WeakReference<>(rCTDeviceEventEmitter);
        }

        @Nullable
        private DeviceEventManagerModule.RCTDeviceEventEmitter getEmitter() {
            return this.emitterRef.get();
        }

        private static WritableMap serializeComplete(String str) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("requestId", str);
            writableNativeMap.putString("type", COMPLETE);
            return writableNativeMap;
        }

        private static WritableMap serializeError(String str, Throwable th) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("requestId", str);
            writableNativeMap.putString("type", "error");
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            writableNativeMap2.putString("message", th.getMessage());
            writableNativeMap2.putString(ElementsErrorKey.STACK, th.toString());
            writableNativeMap.putMap("error", writableNativeMap2);
            return writableNativeMap;
        }

        @Override // rx.Observer
        public void onCompleted() {
            DeviceEventManagerModule.RCTDeviceEventEmitter emitter = getEmitter();
            if (emitter == null) {
                return;
            }
            emitter.emit(HTTP_EVENT, serializeComplete(this.request.id));
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            DeviceEventManagerModule.RCTDeviceEventEmitter emitter = getEmitter();
            if (emitter == null) {
                return;
            }
            emitter.emit(HTTP_EVENT, serializeError(this.request.id, th));
        }

        @Override // rx.Observer
        public void onNext(WritableMap writableMap) {
            DeviceEventManagerModule.RCTDeviceEventEmitter emitter = getEmitter();
            if (emitter == null) {
                return;
            }
            emitter.emit(HTTP_EVENT, writableMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class PromiseObserver implements Observer<WritableMap> {
        @NonNull
        private final Promise promise;

        public PromiseObserver(@NonNull Promise promise) {
            this.promise = promise;
        }

        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.promise.reject(th);
        }

        @Override // rx.Observer
        public void onNext(WritableMap writableMap) {
            this.promise.resolve(writableMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class WritableMapEncodingDataServiceObserver implements Observer<ElementsDataResponse> {
        private final Observer<WritableMap> observer;
        private final ElementsDataRequest request;
        private final long startTimestamp = System.currentTimeMillis();

        /* loaded from: classes2.dex */
        public static final class ElementsResponseKey {
            public static final String CONTENT_TYPE = "contentType";
            public static final String DATA = "data";
            public static final String HTTP = "http";
            public static final String REQUEST_ID = "requestId";
            public static final String STATUS = "status";
            public static final String STATUS_TEXT = "statusText";
            public static final String TEXT = "text";
            public static final String TIMESTAMP = "timestamp";
            public static final String TYPE = "type";
            public static final String URI = "uri";

            private ElementsResponseKey() {
            }
        }

        public WritableMapEncodingDataServiceObserver(ElementsDataRequest elementsDataRequest, Observer<WritableMap> observer) {
            this.request = elementsDataRequest;
            this.observer = observer;
        }

        private static WritableMap serializeResponse(ElementsDataResponse elementsDataResponse) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("requestId", elementsDataResponse.id);
            writableNativeMap.putString("type", "data");
            if (elementsDataResponse.data != null) {
                WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                writableNativeMap2.putString("contentType", elementsDataResponse.data.contentType);
                writableNativeMap2.putString("text", elementsDataResponse.data.text);
                writableNativeMap.putMap("data", writableNativeMap2);
            }
            if (elementsDataResponse.cacheMetadata != null) {
                WritableNativeMap writableNativeMap3 = new WritableNativeMap();
                writableNativeMap3.putDouble("timestamp", elementsDataResponse.cacheMetadata.timestamp);
                writableNativeMap.putMap(ElementsRequestKey.CACHE, writableNativeMap3);
            }
            WritableNativeMap writableNativeMap4 = new WritableNativeMap();
            writableNativeMap4.putInt("status", elementsDataResponse.statusCode);
            writableNativeMap4.putString(ElementsResponseKey.STATUS_TEXT, elementsDataResponse.statusText);
            writableNativeMap4.putString("uri", elementsDataResponse.uri);
            WritableNativeMap writableNativeMap5 = new WritableNativeMap();
            Map<String, String> map = elementsDataResponse.headers;
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    writableNativeMap5.putString(entry.getKey(), entry.getValue());
                }
            }
            writableNativeMap4.putMap(ElementsRequestKey.HEADERS, writableNativeMap5);
            writableNativeMap.putMap("http", writableNativeMap4);
            return writableNativeMap;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.observer.onCompleted();
            long currentTimeMillis = System.currentTimeMillis();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("(onComplete) [");
            outline107.append(this.request.uri);
            outline107.append("] ");
            outline107.append(currentTimeMillis - this.startTimestamp);
            outline107.append("ms.");
            outline107.toString();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.observer.onError(th);
            String str = "(onError) [" + this.request.uri + "] " + th.getMessage();
        }

        @Override // rx.Observer
        public void onNext(ElementsDataResponse elementsDataResponse) {
            this.observer.onNext(serializeResponse(elementsDataResponse));
            long currentTimeMillis = System.currentTimeMillis();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("(onNext) [");
            outline107.append(this.request.uri);
            outline107.append("] ");
            outline107.append(currentTimeMillis - this.startTimestamp);
            outline107.append("ms. cached: ");
            outline107.append(Boolean.toString(elementsDataResponse.cacheMetadata != null));
            outline107.append(" ");
            outline107.append(Thread.currentThread().getName());
            outline107.toString();
        }
    }

    public HttpClientModule(ReactApplicationContext reactApplicationContext, ElementsDataService elementsDataService) {
        super(reactApplicationContext);
        this.client = elementsDataService;
    }

    @NonNull
    private static ElementsDataRequest createRequest(ElementsDataRequest.Method method, String str, ReadableMap readableMap) {
        ReadableMap mo6945getMap;
        ElementsDataRequest elementsDataRequest = new ElementsDataRequest();
        elementsDataRequest.id = readableMap.getString("requestId");
        elementsDataRequest.method = method;
        elementsDataRequest.uri = str;
        if (readableMap.hasKey(ElementsRequestKey.OPERATION_NAME)) {
            elementsDataRequest.operationName = readableMap.getString(ElementsRequestKey.OPERATION_NAME);
        }
        if (readableMap.hasKey(ElementsRequestKey.MODULE)) {
            elementsDataRequest.domain = readableMap.getString(ElementsRequestKey.MODULE);
        }
        ReadableMap mo6945getMap2 = readableMap.mo6945getMap(ElementsRequestKey.HEADERS);
        if (mo6945getMap2 != null) {
            ReadableMapKeySetIterator keySetIterator = mo6945getMap2.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                elementsDataRequest.headers.put(nextKey, mo6945getMap2.getString(nextKey));
            }
        }
        if (readableMap.hasKey("body") && (mo6945getMap = readableMap.mo6945getMap("body")) != null) {
            String string = mo6945getMap.getString("text");
            if (mo6945getMap.hasKey(ElementsBodyKey.COMPRESS) && mo6945getMap.getBoolean(ElementsBodyKey.COMPRESS)) {
                elementsDataRequest.headers.put(GZIPRequestInterceptor.GZIP_HEADER, "true");
            }
            elementsDataRequest.body = new DataBlob(string, mo6945getMap.getString("contentType"));
        }
        if (readableMap.hasKey(ElementsRequestKey.AUTH)) {
            ReadableMap mo6945getMap3 = readableMap.mo6945getMap(ElementsRequestKey.AUTH);
            String string2 = mo6945getMap3.getString("type");
            if ("web".equals(string2)) {
                elementsDataRequest.getAuth().type = ElementsDataRequestAuthentication.ELEMENTS_AUTH_HEADER_TYPE_COOKIES;
                if (mo6945getMap3.hasKey("directedId")) {
                    elementsDataRequest.getAuth().directedId = mo6945getMap3.getString("directedId");
                }
                if (mo6945getMap3.hasKey(ElementsRequestKey.FORCE_REFRESH)) {
                    elementsDataRequest.getAuth().forceRefresh = mo6945getMap3.getBoolean(ElementsRequestKey.FORCE_REFRESH);
                }
            } else if (ElementsRequestKey.BEARER.equals(string2)) {
                if (mo6945getMap3.hasKey(ElementsRequestKey.SUPPRESS_BEARER) && mo6945getMap3.getBoolean(ElementsRequestKey.SUPPRESS_BEARER)) {
                    elementsDataRequest.getAuth().type = ElementsDataRequestAuthentication.ELEMENTS_AUTH_HEADER_TYPE_PLAIN;
                } else {
                    elementsDataRequest.getAuth().type = ElementsDataRequestAuthentication.ELEMENTS_AUTH_HEADER_TYPE_BEARER;
                }
                if (mo6945getMap3.hasKey(ElementsRequestKey.AUTH_HEADER_KEY)) {
                    String string3 = mo6945getMap3.getString(ElementsRequestKey.AUTH_HEADER_KEY);
                    if (!TextUtils.isEmpty(string3)) {
                        elementsDataRequest.getAuth().authHeaderKey = string3;
                    }
                }
            }
            if (mo6945getMap3.hasKey(ElementsRequestKey.USE_DELEGATED_TOKEN) && mo6945getMap3.getBoolean(ElementsRequestKey.USE_DELEGATED_TOKEN)) {
                elementsDataRequest.getAuth().useDelegatedToken = true;
            }
        }
        if (readableMap.hasKey(ElementsRequestKey.SINGLE_RESULT)) {
            elementsDataRequest.singleResult = readableMap.getBoolean(ElementsRequestKey.SINGLE_RESULT);
        }
        if (readableMap.hasKey(ElementsRequestKey.CACHE)) {
            ReadableMap mo6945getMap4 = readableMap.mo6945getMap(ElementsRequestKey.CACHE);
            if (mo6945getMap4.hasKey("ttl")) {
                elementsDataRequest.cache.ttl = mo6945getMap4.getInt("ttl");
            }
            if (mo6945getMap4.hasKey(ElementsCacheKey.RETRIEVE)) {
                elementsDataRequest.cache.retrieve = mo6945getMap4.getBoolean(ElementsCacheKey.RETRIEVE);
            }
            if (mo6945getMap4.hasKey(ElementsCacheKey.STORE)) {
                elementsDataRequest.cache.store = mo6945getMap4.getBoolean(ElementsCacheKey.STORE);
            }
        }
        if (readableMap.hasKey(ElementsRequestKey.CONNECTION_TIMEOUT)) {
            elementsDataRequest.connectionTimeout = Integer.valueOf(readableMap.getInt(ElementsRequestKey.CONNECTION_TIMEOUT));
        }
        if (readableMap.hasKey(ElementsRequestKey.READ_TIMEOUT)) {
            elementsDataRequest.readTimeout = Integer.valueOf(readableMap.getInt(ElementsRequestKey.READ_TIMEOUT));
        }
        return elementsDataRequest;
    }

    @ReactMethod
    private void request(String str, String str2, ReadableMap readableMap, Promise promise) {
        ElementsDataRequest.Method fromString = ElementsDataRequest.Method.fromString(str);
        if (fromString == null) {
            String str3 = TAG;
            Log.e(str3, "Invalid HTTP method " + str + " supplied to HttpClientModule.");
            promise.reject("Method " + str + " was not one of: GET/POST/PUT/DELETE/PATCH.", new IllegalArgumentException());
            return;
        }
        request(fromString, str2, readableMap, promise);
    }

    @ReactMethod
    public void delete(String str, ReadableMap readableMap, Promise promise) {
        request(ElementsDataRequest.Method.DELETE, str, readableMap, promise);
    }

    @ReactMethod
    public void get(String str, ReadableMap readableMap, Promise promise) {
        request(ElementsDataRequest.Method.GET, str, readableMap, promise);
    }

    @ReactMethod
    public void getAsStream(String str, ReadableMap readableMap) throws IOException {
        ElementsDataRequest createRequest = createRequest(ElementsDataRequest.Method.GET, str, readableMap);
        this.client.execute(createRequest).subscribe(new WritableMapEncodingDataServiceObserver(createRequest, new EventEmitterObserver(createRequest, (DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class))));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "HttpClientModule";
    }

    @ReactMethod
    public void patch(String str, ReadableMap readableMap, Promise promise) {
        request(ElementsDataRequest.Method.PATCH, str, readableMap, promise);
    }

    @ReactMethod
    public void post(String str, ReadableMap readableMap, Promise promise) {
        request(ElementsDataRequest.Method.POST, str, readableMap, promise);
    }

    @ReactMethod
    public void put(String str, ReadableMap readableMap, Promise promise) {
        request(ElementsDataRequest.Method.PUT, str, readableMap, promise);
    }

    private void request(@NonNull ElementsDataRequest.Method method, String str, ReadableMap readableMap, Promise promise) {
        ElementsDataRequest createRequest = createRequest(method, str, readableMap);
        createRequest.singleResult = true;
        this.client.execute(createRequest).subscribe(new WritableMapEncodingDataServiceObserver(createRequest, new PromiseObserver(promise)));
    }
}
