package com.facebook.react.modules.network;

import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.dee.app.metrics.MetricsConstants;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeNetworkingAndroidSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.StandardCharsets;
import com.facebook.react.common.network.OkHttpCallUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.ByteString;
import okio.GzipSource;
import okio.Okio;
import org.apache.commons.fileupload.FileUploadBase;
@ReactModule(name = NetworkingModule.NAME)
/* loaded from: classes2.dex */
public final class NetworkingModule extends NativeNetworkingAndroidSpec {
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    public static final String NAME = "Networking";
    private static final String REQUEST_BODY_KEY_BASE64 = "base64";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String TAG = "NetworkingModule";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";
    @Nullable
    private static CustomClientBuilder customClientBuilder;
    private final OkHttpClient mClient;
    private final ForwardingCookieHandler mCookieHandler;
    private final CookieJarContainer mCookieJarContainer;
    @Nullable
    private final String mDefaultUserAgent;
    private final List<RequestBodyHandler> mRequestBodyHandlers;
    private final Set<Integer> mRequestIds;
    private final List<ResponseHandler> mResponseHandlers;
    private boolean mShuttingDown;
    private final List<UriHandler> mUriHandlers;

    /* loaded from: classes2.dex */
    public interface CustomClientBuilder {
        void apply(OkHttpClient.Builder builder);
    }

    /* loaded from: classes2.dex */
    public interface RequestBodyHandler {
        boolean supports(ReadableMap readableMap);

        RequestBody toRequestBody(ReadableMap readableMap, String str);
    }

    /* loaded from: classes2.dex */
    public interface ResponseHandler {
        boolean supports(String str);

        WritableMap toResponseData(ResponseBody responseBody) throws IOException;
    }

    /* loaded from: classes2.dex */
    public interface UriHandler {
        WritableMap fetch(Uri uri) throws IOException;

        boolean supports(Uri uri, String str);
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, @Nullable String str, OkHttpClient okHttpClient, @Nullable List<NetworkInterceptorCreator> list) {
        super(reactApplicationContext);
        this.mRequestBodyHandlers = new ArrayList();
        this.mUriHandlers = new ArrayList();
        this.mResponseHandlers = new ArrayList();
        if (list != null) {
            OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
            for (NetworkInterceptorCreator networkInterceptorCreator : list) {
                newBuilder.addNetworkInterceptor(networkInterceptorCreator.create());
            }
            okHttpClient = newBuilder.build();
        }
        this.mClient = okHttpClient;
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
        this.mCookieJarContainer = (CookieJarContainer) this.mClient.cookieJar();
        this.mShuttingDown = false;
        this.mDefaultUserAgent = str;
        this.mRequestIds = new HashSet();
    }

    private synchronized void addRequest(int i) {
        this.mRequestIds.add(Integer.valueOf(i));
    }

    private static void applyCustomBuilder(OkHttpClient.Builder builder) {
        CustomClientBuilder customClientBuilder2 = customClientBuilder;
        if (customClientBuilder2 != null) {
            customClientBuilder2.apply(builder);
        }
    }

    private synchronized void cancelAllRequests() {
        for (Integer num : this.mRequestIds) {
            cancelRequest(num.intValue());
        }
        this.mRequestIds.clear();
    }

    private void cancelRequest(final int i) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.network.NetworkingModule.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                OkHttpCallUtil.cancelTag(NetworkingModule.this.mClient, Integer.valueOf(i));
            }
        }.execute(new Void[0]);
    }

    @Nullable
    private MultipartBody.Builder constructMultipartBody(ReadableArray readableArray, String str, int i) {
        MediaType mediaType;
        DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("constructMultipartBody");
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MediaType.parse(str));
        int size = readableArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ReadableMap mo6944getMap = readableArray.mo6944getMap(i2);
            Headers extractHeaders = extractHeaders(mo6944getMap.getArray(HttpClientModule.ElementsRequestKey.HEADERS), null);
            if (extractHeaders == null) {
                ResponseUtil.onRequestError(eventEmitter, i, "Missing or invalid header format for FormData part.", null);
                return null;
            }
            String str2 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
            if (str2 != null) {
                mediaType = MediaType.parse(str2);
                extractHeaders = extractHeaders.newBuilder().removeAll(CONTENT_TYPE_HEADER_NAME).build();
            } else {
                mediaType = null;
            }
            if (mo6944getMap.hasKey(REQUEST_BODY_KEY_STRING)) {
                builder.addPart(extractHeaders, RequestBody.create(mediaType, mo6944getMap.getString(REQUEST_BODY_KEY_STRING)));
            } else if (!mo6944getMap.hasKey("uri")) {
                ResponseUtil.onRequestError(eventEmitter, i, "Unrecognized FormData part.", null);
            } else if (mediaType == null) {
                ResponseUtil.onRequestError(eventEmitter, i, "Binary FormData part needs a content-type header.", null);
                return null;
            } else {
                String string = mo6944getMap.getString("uri");
                InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), string);
                if (fileInputStream == null) {
                    ResponseUtil.onRequestError(eventEmitter, i, "Could not retrieve file for uri " + string, null);
                    return null;
                }
                builder.addPart(extractHeaders, RequestBodyUtil.create(mediaType, fileInputStream));
            }
        }
        return builder;
    }

    @Nullable
    private Headers extractHeaders(@Nullable ReadableArray readableArray, @Nullable ReadableMap readableMap) {
        String str;
        if (readableArray == null) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        int size = readableArray.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ReadableArray mo6943getArray = readableArray.mo6943getArray(i);
            if (mo6943getArray != null && mo6943getArray.size() == 2) {
                String stripHeaderName = HeaderUtil.stripHeaderName(mo6943getArray.getString(0));
                String stripHeaderValue = HeaderUtil.stripHeaderValue(mo6943getArray.getString(1));
                if (stripHeaderName != null && stripHeaderValue != null) {
                    builder.add(stripHeaderName, stripHeaderValue);
                }
            }
            return null;
        }
        if (builder.get(USER_AGENT_HEADER_NAME) == null && (str = this.mDefaultUserAgent) != null) {
            builder.add(USER_AGENT_HEADER_NAME, str);
        }
        if (readableMap != null && readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
            z = true;
        }
        if (!z) {
            builder.removeAll(CONTENT_ENCODING_HEADER_NAME);
        }
        return builder.build();
    }

    private DeviceEventManagerModule.RCTDeviceEventEmitter getEventEmitter(String str) {
        if (getReactApplicationContextIfActiveOrWarn() != null) {
            return (DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readWithProgress(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, ResponseBody responseBody) throws IOException {
        long j;
        long j2 = -1;
        try {
            ProgressResponseBody progressResponseBody = (ProgressResponseBody) responseBody;
            j = progressResponseBody.totalBytesRead();
            try {
                j2 = progressResponseBody.contentLength();
            } catch (ClassCastException unused) {
            }
        } catch (ClassCastException unused2) {
            j = -1;
        }
        ProgressiveStringDecoder progressiveStringDecoder = new ProgressiveStringDecoder(responseBody.contentType() == null ? StandardCharsets.UTF_8 : responseBody.contentType().charset(StandardCharsets.UTF_8));
        InputStream byteStream = responseBody.byteStream();
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int read = byteStream.read(bArr);
                if (read == -1) {
                    return;
                }
                ResponseUtil.onIncrementalDataReceived(rCTDeviceEventEmitter, i, progressiveStringDecoder.decodeNext(bArr, read), j, j2);
            }
        } finally {
            byteStream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void removeRequest(int i) {
        this.mRequestIds.remove(Integer.valueOf(i));
    }

    public static void setCustomClientBuilder(CustomClientBuilder customClientBuilder2) {
        customClientBuilder = customClientBuilder2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldDispatch(long j, long j2) {
        return j2 + 100000000 < j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WritableMap translateHeaders(Headers headers) {
        Bundle bundle = new Bundle();
        for (int i = 0; i < headers.size(); i++) {
            String name = headers.name(i);
            if (bundle.containsKey(name)) {
                bundle.putString(name, bundle.getString(name) + ", " + headers.value(i));
            } else {
                bundle.putString(name, headers.value(i));
            }
        }
        return Arguments.fromBundle(bundle);
    }

    private RequestBody wrapRequestBodyWithProgressEmitter(RequestBody requestBody, final DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, final int i) {
        if (requestBody == null) {
            return null;
        }
        return RequestBodyUtil.createProgressRequest(requestBody, new ProgressListener() { // from class: com.facebook.react.modules.network.NetworkingModule.3
            long last = System.nanoTime();

            @Override // com.facebook.react.modules.network.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                long nanoTime = System.nanoTime();
                if (z || NetworkingModule.shouldDispatch(nanoTime, this.last)) {
                    ResponseUtil.onDataSend(rCTDeviceEventEmitter, i, j, j2);
                    this.last = nanoTime;
                }
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void abortRequest(double d) {
        int i = (int) d;
        cancelRequest(i);
        removeRequest(i);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void addListener(String str) {
    }

    public void addRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.add(requestBodyHandler);
    }

    public void addResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.add(responseHandler);
    }

    public void addUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.add(uriHandler);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    @ReactMethod
    public void clearCookies(Callback callback) {
        this.mCookieHandler.clearCookies(callback);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        this.mCookieJarContainer.setCookieJar(new JavaNetCookieJar(this.mCookieHandler));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
        cancelAllRequests();
        this.mCookieHandler.destroy();
        this.mCookieJarContainer.removeCookieJar();
        this.mRequestBodyHandlers.clear();
        this.mResponseHandlers.clear();
        this.mUriHandlers.clear();
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void removeListeners(double d) {
    }

    public void removeRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.remove(requestBodyHandler);
    }

    public void removeResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.remove(responseHandler);
    }

    public void removeUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.remove(uriHandler);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void sendRequest(String str, String str2, double d, ReadableArray readableArray, ReadableMap readableMap, String str3, boolean z, double d2, boolean z2) {
        int i = (int) d;
        try {
            sendRequestInternal(str, str2, i, readableArray, readableMap, str3, z, (int) d2, z2);
        } catch (Throwable th) {
            FLog.e(TAG, "Failed to send url request: " + str2, th);
            DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("sendRequest error");
            if (eventEmitter == null) {
                return;
            }
            ResponseUtil.onRequestError(eventEmitter, i, th.getMessage(), th);
        }
    }

    public void sendRequestInternal(String str, String str2, final int i, ReadableArray readableArray, ReadableMap readableMap, final String str3, final boolean z, int i2, boolean z2) {
        RequestBodyHandler requestBodyHandler;
        RequestBody emptyBody;
        final DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("sendRequestInternal");
        try {
            Uri parse = Uri.parse(str2);
            for (UriHandler uriHandler : this.mUriHandlers) {
                if (uriHandler.supports(parse, str3)) {
                    ResponseUtil.onDataReceived(eventEmitter, i, uriHandler.fetch(parse));
                    ResponseUtil.onRequestSuccess(eventEmitter, i);
                    return;
                }
            }
            try {
                Request.Builder url = new Request.Builder().url(str2);
                if (i != 0) {
                    url.tag(Integer.valueOf(i));
                }
                OkHttpClient.Builder newBuilder = this.mClient.newBuilder();
                applyCustomBuilder(newBuilder);
                if (!z2) {
                    newBuilder.cookieJar(CookieJar.NO_COOKIES);
                }
                if (z) {
                    newBuilder.addNetworkInterceptor(new Interceptor() { // from class: com.facebook.react.modules.network.NetworkingModule.1
                        @Override // okhttp3.Interceptor
                        public Response intercept(Interceptor.Chain chain) throws IOException {
                            Response proceed = chain.proceed(chain.request());
                            return proceed.newBuilder().body(new ProgressResponseBody(proceed.body(), new ProgressListener() { // from class: com.facebook.react.modules.network.NetworkingModule.1.1
                                long last = System.nanoTime();

                                @Override // com.facebook.react.modules.network.ProgressListener
                                public void onProgress(long j, long j2, boolean z3) {
                                    long nanoTime = System.nanoTime();
                                    if ((z3 || NetworkingModule.shouldDispatch(nanoTime, this.last)) && !str3.equals("text")) {
                                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                        ResponseUtil.onDataReceivedProgress(eventEmitter, i, j, j2);
                                        this.last = nanoTime;
                                    }
                                }
                            })).build();
                        }
                    });
                }
                if (i2 != this.mClient.connectTimeoutMillis()) {
                    newBuilder.connectTimeout(i2, TimeUnit.MILLISECONDS);
                }
                OkHttpClient build = newBuilder.build();
                Headers extractHeaders = extractHeaders(readableArray, readableMap);
                if (extractHeaders == null) {
                    ResponseUtil.onRequestError(eventEmitter, i, "Unrecognized headers format", null);
                    return;
                }
                String str4 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
                String str5 = extractHeaders.get(CONTENT_ENCODING_HEADER_NAME);
                url.headers(extractHeaders);
                if (readableMap != null) {
                    Iterator<RequestBodyHandler> it2 = this.mRequestBodyHandlers.iterator();
                    while (it2.hasNext()) {
                        requestBodyHandler = it2.next();
                        if (requestBodyHandler.supports(readableMap)) {
                            break;
                        }
                    }
                }
                requestBodyHandler = null;
                if (readableMap == null || str.toLowerCase().equals(MetricsConstants.Method.CACHE_GET) || str.toLowerCase().equals(TtmlNode.TAG_HEAD)) {
                    emptyBody = RequestBodyUtil.getEmptyBody(str);
                } else if (requestBodyHandler != null) {
                    emptyBody = requestBodyHandler.toRequestBody(readableMap, str4);
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
                    if (str4 == null) {
                        ResponseUtil.onRequestError(eventEmitter, i, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    String string = readableMap.getString(REQUEST_BODY_KEY_STRING);
                    MediaType parse2 = MediaType.parse(str4);
                    if (RequestBodyUtil.isGzipEncoding(str5)) {
                        emptyBody = RequestBodyUtil.createGzip(parse2, string);
                        if (emptyBody == null) {
                            ResponseUtil.onRequestError(eventEmitter, i, "Failed to gzip request body", null);
                            return;
                        }
                    } else {
                        Charset charset = StandardCharsets.UTF_8;
                        if (parse2 != null) {
                            charset = parse2.charset(charset);
                        }
                        emptyBody = RequestBody.create(parse2, string.getBytes(charset));
                    }
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_BASE64)) {
                    if (str4 == null) {
                        ResponseUtil.onRequestError(eventEmitter, i, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    emptyBody = RequestBody.create(MediaType.parse(str4), ByteString.decodeBase64(readableMap.getString(REQUEST_BODY_KEY_BASE64)));
                } else if (readableMap.hasKey("uri")) {
                    if (str4 == null) {
                        ResponseUtil.onRequestError(eventEmitter, i, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    String string2 = readableMap.getString("uri");
                    InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), string2);
                    if (fileInputStream == null) {
                        ResponseUtil.onRequestError(eventEmitter, i, "Could not retrieve file for uri " + string2, null);
                        return;
                    }
                    emptyBody = RequestBodyUtil.create(MediaType.parse(str4), fileInputStream);
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_FORMDATA)) {
                    if (str4 == null) {
                        str4 = FileUploadBase.MULTIPART_FORM_DATA;
                    }
                    MultipartBody.Builder constructMultipartBody = constructMultipartBody(readableMap.getArray(REQUEST_BODY_KEY_FORMDATA), str4, i);
                    if (constructMultipartBody == null) {
                        return;
                    }
                    emptyBody = constructMultipartBody.build();
                } else {
                    emptyBody = RequestBodyUtil.getEmptyBody(str);
                }
                url.method(str, wrapRequestBodyWithProgressEmitter(emptyBody, eventEmitter, i));
                addRequest(i);
                build.newCall(url.build()).enqueue(new okhttp3.Callback() { // from class: com.facebook.react.modules.network.NetworkingModule.2
                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException iOException) {
                        String sb;
                        if (NetworkingModule.this.mShuttingDown) {
                            return;
                        }
                        NetworkingModule.this.removeRequest(i);
                        if (iOException.getMessage() != null) {
                            sb = iOException.getMessage();
                        } else {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error while executing request: ");
                            outline107.append(iOException.getClass().getSimpleName());
                            sb = outline107.toString();
                        }
                        ResponseUtil.onRequestError(eventEmitter, i, sb, iOException);
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) throws IOException {
                        if (NetworkingModule.this.mShuttingDown) {
                            return;
                        }
                        NetworkingModule.this.removeRequest(i);
                        ResponseUtil.onResponseReceived(eventEmitter, i, response.code(), NetworkingModule.translateHeaders(response.headers()), response.request().url().toString());
                        try {
                            ResponseBody body = response.body();
                            if ("gzip".equalsIgnoreCase(response.header("Content-Encoding")) && body != null) {
                                GzipSource gzipSource = new GzipSource(body.source());
                                String header = response.header("Content-Type");
                                body = ResponseBody.create(header != null ? MediaType.parse(header) : null, -1L, Okio.buffer(gzipSource));
                            }
                            for (ResponseHandler responseHandler : NetworkingModule.this.mResponseHandlers) {
                                if (responseHandler.supports(str3)) {
                                    ResponseUtil.onDataReceived(eventEmitter, i, responseHandler.toResponseData(body));
                                    ResponseUtil.onRequestSuccess(eventEmitter, i);
                                    return;
                                }
                            }
                            if (z && str3.equals("text")) {
                                NetworkingModule.this.readWithProgress(eventEmitter, i, body);
                                ResponseUtil.onRequestSuccess(eventEmitter, i);
                                return;
                            }
                            String str6 = "";
                            if (str3.equals("text")) {
                                try {
                                    str6 = body.string();
                                } catch (IOException e) {
                                    if (!response.request().method().equalsIgnoreCase("HEAD")) {
                                        ResponseUtil.onRequestError(eventEmitter, i, e.getMessage(), e);
                                    }
                                }
                            } else if (str3.equals(NetworkingModule.REQUEST_BODY_KEY_BASE64)) {
                                str6 = Base64.encodeToString(body.bytes(), 2);
                            }
                            ResponseUtil.onDataReceived(eventEmitter, i, str6);
                            ResponseUtil.onRequestSuccess(eventEmitter, i);
                        } catch (IOException e2) {
                            ResponseUtil.onRequestError(eventEmitter, i, e2.getMessage(), e2);
                        }
                    }
                });
            } catch (Exception e) {
                ResponseUtil.onRequestError(eventEmitter, i, e.getMessage(), null);
            }
        } catch (IOException e2) {
            ResponseUtil.onRequestError(eventEmitter, i, e2.getMessage(), e2);
        }
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, @Nullable String str, OkHttpClient okHttpClient) {
        this(reactApplicationContext, str, okHttpClient, null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null, OkHttpClientProvider.createClient(reactApplicationContext), null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, List<NetworkInterceptorCreator> list) {
        this(reactApplicationContext, null, OkHttpClientProvider.createClient(reactApplicationContext), list);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, String str) {
        this(reactApplicationContext, str, OkHttpClientProvider.createClient(reactApplicationContext), null);
    }
}
