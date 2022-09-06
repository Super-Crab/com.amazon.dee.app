package com.facebook.imagepipeline.backends.okhttp3;

import android.os.Looper;
import android.os.SystemClock;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* loaded from: classes2.dex */
public class OkHttpNetworkFetcher extends BaseNetworkFetcher<OkHttpNetworkFetchState> {
    private static final String FETCH_TIME = "fetch_time";
    private static final String IMAGE_SIZE = "image_size";
    private static final String QUEUE_TIME = "queue_time";
    private static final String TOTAL_TIME = "total_time";
    @Nullable
    private final CacheControl mCacheControl;
    private final Call.Factory mCallFactory;
    private Executor mCancellationExecutor;

    /* loaded from: classes2.dex */
    public static class OkHttpNetworkFetchState extends FetchState {
        public long fetchCompleteTime;
        public long responseTime;
        public long submitTime;

        public OkHttpNetworkFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }

    public OkHttpNetworkFetcher(OkHttpClient okHttpClient) {
        this(okHttpClient, okHttpClient.dispatcher().executorService());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleException(final Call call, final Exception e, final NetworkFetcher.Callback callback) {
        if (call.isCanceled()) {
            callback.onCancellation();
        } else {
            callback.onFailure(e);
        }
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    /* renamed from: createFetchState  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FetchState mo6923createFetchState(Consumer consumer, ProducerContext context) {
        return mo6923createFetchState((Consumer<EncodedImage>) consumer, context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fetchWithRequest(final OkHttpNetworkFetchState fetchState, final NetworkFetcher.Callback callback, final Request request) {
        final Call newCall = this.mCallFactory.newCall(request);
        fetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.1
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    OkHttpNetworkFetcher.this.mCancellationExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            newCall.cancel();
                        }
                    });
                } else {
                    newCall.cancel();
                }
            }
        });
        newCall.enqueue(new Callback() { // from class: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                OkHttpNetworkFetcher.this.handleException(call, e, callback);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                fetchState.responseTime = SystemClock.elapsedRealtime();
                ResponseBody body = response.body();
                try {
                    if (body != null) {
                        try {
                        } catch (Exception e) {
                            OkHttpNetworkFetcher.this.handleException(call, e, callback);
                        }
                        if (!response.isSuccessful()) {
                            OkHttpNetworkFetcher.this.handleException(call, new IOException("Unexpected HTTP code " + response), callback);
                            return;
                        }
                        BytesRange fromContentRangeHeader = BytesRange.fromContentRangeHeader(response.header("Content-Range"));
                        if (fromContentRangeHeader != null && (fromContentRangeHeader.from != 0 || fromContentRangeHeader.to != Integer.MAX_VALUE)) {
                            fetchState.setResponseBytesRange(fromContentRangeHeader);
                            fetchState.setOnNewResultStatusFlags(8);
                        }
                        long contentLength = body.contentLength();
                        if (contentLength < 0) {
                            contentLength = 0;
                        }
                        callback.onResponse(body.byteStream(), (int) contentLength);
                        return;
                    }
                    OkHttpNetworkFetcher.this.handleException(call, new IOException("Response body null: " + response), callback);
                } finally {
                    body.close();
                }
            }
        });
    }

    public OkHttpNetworkFetcher(Call.Factory callFactory, Executor cancellationExecutor) {
        this(callFactory, cancellationExecutor, true);
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    /* renamed from: createFetchState */
    public OkHttpNetworkFetchState mo6923createFetchState(Consumer<EncodedImage> consumer, ProducerContext context) {
        return new OkHttpNetworkFetchState(consumer, context);
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public void fetch(final OkHttpNetworkFetchState fetchState, final NetworkFetcher.Callback callback) {
        fetchState.submitTime = SystemClock.elapsedRealtime();
        try {
            Request.Builder builder = new Request.Builder().url(fetchState.getUri().toString()).get();
            if (this.mCacheControl != null) {
                builder.cacheControl(this.mCacheControl);
            }
            BytesRange bytesRange = fetchState.getContext().getImageRequest().getBytesRange();
            if (bytesRange != null) {
                builder.addHeader("Range", bytesRange.toHttpRangeHeaderValue());
            }
            fetchWithRequest(fetchState, callback, builder.build());
        } catch (Exception e) {
            callback.onFailure(e);
        }
    }

    @Override // com.facebook.imagepipeline.producers.BaseNetworkFetcher, com.facebook.imagepipeline.producers.NetworkFetcher
    public Map<String, String> getExtraMap(OkHttpNetworkFetchState fetchState, int byteSize) {
        HashMap hashMap = new HashMap(4);
        hashMap.put(QUEUE_TIME, Long.toString(fetchState.responseTime - fetchState.submitTime));
        hashMap.put(FETCH_TIME, Long.toString(fetchState.fetchCompleteTime - fetchState.responseTime));
        hashMap.put(TOTAL_TIME, Long.toString(fetchState.fetchCompleteTime - fetchState.submitTime));
        hashMap.put(IMAGE_SIZE, Integer.toString(byteSize));
        return hashMap;
    }

    @Override // com.facebook.imagepipeline.producers.BaseNetworkFetcher, com.facebook.imagepipeline.producers.NetworkFetcher
    public void onFetchCompletion(OkHttpNetworkFetchState fetchState, int byteSize) {
        fetchState.fetchCompleteTime = SystemClock.elapsedRealtime();
    }

    public OkHttpNetworkFetcher(Call.Factory callFactory, Executor cancellationExecutor, boolean disableOkHttpCache) {
        this.mCallFactory = callFactory;
        this.mCancellationExecutor = cancellationExecutor;
        this.mCacheControl = disableOkHttpCache ? new CacheControl.Builder().noStore().build() : null;
    }
}
