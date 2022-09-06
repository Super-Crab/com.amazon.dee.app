package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.EncodedImageOrigin;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class NetworkFetchProducer implements Producer<EncodedImage> {
    public static final String INTERMEDIATE_RESULT_PRODUCER_EVENT = "intermediate_result";
    public static final String PRODUCER_NAME = "NetworkFetchProducer";
    private static final int READ_SIZE = 16384;
    @VisibleForTesting
    static final long TIME_BETWEEN_PARTIAL_RESULTS_MS = 100;
    private final ByteArrayPool mByteArrayPool;
    private final NetworkFetcher mNetworkFetcher;
    protected final PooledByteBufferFactory mPooledByteBufferFactory;

    public NetworkFetchProducer(PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, NetworkFetcher networkFetcher) {
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mByteArrayPool = byteArrayPool;
        this.mNetworkFetcher = networkFetcher;
    }

    protected static float calculateProgress(int downloaded, int total) {
        return total > 0 ? downloaded / total : 1.0f - ((float) Math.exp((-downloaded) / 50000.0d));
    }

    @Nullable
    private Map<String, String> getExtraMap(FetchState fetchState, int byteSize) {
        if (!fetchState.getListener().requiresExtraMap(fetchState.getContext(), PRODUCER_NAME)) {
            return null;
        }
        return this.mNetworkFetcher.getExtraMap(fetchState, byteSize);
    }

    protected static void notifyConsumer(PooledByteBufferOutputStream pooledOutputStream, int status, @Nullable BytesRange responseBytesRange, Consumer<EncodedImage> consumer, ProducerContext context) {
        EncodedImage encodedImage;
        CloseableReference of = CloseableReference.of(pooledOutputStream.mo6913toByteBuffer());
        try {
            encodedImage = new EncodedImage(of);
            try {
                encodedImage.setBytesRange(responseBytesRange);
                encodedImage.parseMetaData();
                context.setEncodedImageOrigin(EncodedImageOrigin.NETWORK);
                consumer.onNewResult(encodedImage, status);
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(of);
            } catch (Throwable th) {
                th = th;
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(of);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            encodedImage = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCancellation(FetchState fetchState) {
        fetchState.getListener().onProducerFinishWithCancellation(fetchState.getContext(), PRODUCER_NAME, null);
        fetchState.getConsumer().onCancellation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFailure(FetchState fetchState, Throwable e) {
        fetchState.getListener().onProducerFinishWithFailure(fetchState.getContext(), PRODUCER_NAME, e, null);
        fetchState.getListener().onUltimateProducerReached(fetchState.getContext(), PRODUCER_NAME, false);
        fetchState.getContext().putOriginExtra("network");
        fetchState.getConsumer().onFailure(e);
    }

    private boolean shouldPropagateIntermediateResults(FetchState fetchState) {
        if (!fetchState.getContext().isIntermediateResultExpected()) {
            return false;
        }
        return this.mNetworkFetcher.shouldPropagate(fetchState);
    }

    @VisibleForTesting
    protected long getSystemUptime() {
        return SystemClock.uptimeMillis();
    }

    protected void handleFinalResult(PooledByteBufferOutputStream pooledOutputStream, FetchState fetchState) {
        Map<String, String> extraMap = getExtraMap(fetchState, pooledOutputStream.size());
        ProducerListener2 listener = fetchState.getListener();
        listener.onProducerFinishWithSuccess(fetchState.getContext(), PRODUCER_NAME, extraMap);
        listener.onUltimateProducerReached(fetchState.getContext(), PRODUCER_NAME, true);
        fetchState.getContext().putOriginExtra("network");
        notifyConsumer(pooledOutputStream, fetchState.getOnNewResultStatusFlags() | 1, fetchState.getResponseBytesRange(), fetchState.getConsumer(), fetchState.getContext());
    }

    protected void maybeHandleIntermediateResult(PooledByteBufferOutputStream pooledOutputStream, FetchState fetchState) {
        long systemUptime = getSystemUptime();
        if (!shouldPropagateIntermediateResults(fetchState) || systemUptime - fetchState.getLastIntermediateResultTimeMs() < TIME_BETWEEN_PARTIAL_RESULTS_MS) {
            return;
        }
        fetchState.setLastIntermediateResultTimeMs(systemUptime);
        fetchState.getListener().onProducerEvent(fetchState.getContext(), PRODUCER_NAME, INTERMEDIATE_RESULT_PRODUCER_EVENT);
        notifyConsumer(pooledOutputStream, fetchState.getOnNewResultStatusFlags(), fetchState.getResponseBytesRange(), fetchState.getConsumer(), fetchState.getContext());
    }

    protected void onResponse(FetchState fetchState, InputStream responseData, int responseContentLength) throws IOException {
        PooledByteBufferOutputStream mo6911newOutputStream;
        if (responseContentLength > 0) {
            mo6911newOutputStream = this.mPooledByteBufferFactory.mo6912newOutputStream(responseContentLength);
        } else {
            mo6911newOutputStream = this.mPooledByteBufferFactory.mo6911newOutputStream();
        }
        byte[] mo6905get = this.mByteArrayPool.mo6905get(16384);
        while (true) {
            try {
                int read = responseData.read(mo6905get);
                if (read < 0) {
                    this.mNetworkFetcher.onFetchCompletion(fetchState, mo6911newOutputStream.size());
                    handleFinalResult(mo6911newOutputStream, fetchState);
                    return;
                } else if (read > 0) {
                    mo6911newOutputStream.write(mo6905get, 0, read);
                    maybeHandleIntermediateResult(mo6911newOutputStream, fetchState);
                    fetchState.getConsumer().onProgressUpdate(calculateProgress(mo6911newOutputStream.size(), responseContentLength));
                }
            } finally {
                this.mByteArrayPool.release(mo6905get);
                mo6911newOutputStream.close();
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext context) {
        context.getProducerListener().onProducerStart(context, PRODUCER_NAME);
        final FetchState mo6923createFetchState = this.mNetworkFetcher.mo6923createFetchState(consumer, context);
        this.mNetworkFetcher.fetch(mo6923createFetchState, new NetworkFetcher.Callback() { // from class: com.facebook.imagepipeline.producers.NetworkFetchProducer.1
            @Override // com.facebook.imagepipeline.producers.NetworkFetcher.Callback
            public void onCancellation() {
                NetworkFetchProducer.this.onCancellation(mo6923createFetchState);
            }

            @Override // com.facebook.imagepipeline.producers.NetworkFetcher.Callback
            public void onFailure(Throwable throwable) {
                NetworkFetchProducer.this.onFailure(mo6923createFetchState, throwable);
            }

            @Override // com.facebook.imagepipeline.producers.NetworkFetcher.Callback
            public void onResponse(InputStream response, int responseLength) throws IOException {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("NetworkFetcher->onResponse");
                }
                NetworkFetchProducer.this.onResponse(mo6923createFetchState, response, responseLength);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            }
        });
    }
}
