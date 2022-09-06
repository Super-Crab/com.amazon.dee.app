package com.facebook.imagepipeline.producers;

import com.dee.app.metrics.MetricsConstants;
import com.facebook.common.internal.Closeables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class LocalFetchProducer implements Producer<EncodedImage> {
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* JADX INFO: Access modifiers changed from: protected */
    public LocalFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EncodedImage getByteBufferBackedEncodedImage(InputStream inputStream, int length) throws IOException {
        CloseableReference of;
        CloseableReference closeableReference = null;
        try {
            if (length <= 0) {
                of = CloseableReference.of(this.mPooledByteBufferFactory.mo6908newByteBuffer(inputStream));
            } else {
                of = CloseableReference.of(this.mPooledByteBufferFactory.mo6909newByteBuffer(inputStream, length));
            }
            closeableReference = of;
            return new EncodedImage(closeableReference);
        } finally {
            Closeables.closeQuietly(inputStream);
            CloseableReference.closeSafely(closeableReference);
        }
    }

    @Nullable
    protected abstract EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public EncodedImage getEncodedImage(InputStream inputStream, int length) throws IOException {
        return getByteBufferBackedEncodedImage(inputStream, length);
    }

    protected abstract String getProducerName();

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra("local", MetricsConstants.Id.HTTP_CLIENT_FETCH);
        final StatefulProducerRunnable<EncodedImage> statefulProducerRunnable = new StatefulProducerRunnable<EncodedImage>(consumer, producerListener, producerContext, getProducerName()) { // from class: com.facebook.imagepipeline.producers.LocalFetchProducer.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            public void disposeResult(EncodedImage result) {
                EncodedImage.closeSafely(result);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.common.executors.StatefulRunnable
            @Nullable
            /* renamed from: getResult */
            public EncodedImage mo6924getResult() throws Exception {
                EncodedImage encodedImage = LocalFetchProducer.this.getEncodedImage(imageRequest);
                if (encodedImage == null) {
                    producerListener.onUltimateProducerReached(producerContext, LocalFetchProducer.this.getProducerName(), false);
                    producerContext.putOriginExtra("local");
                    return null;
                }
                encodedImage.parseMetaData();
                producerListener.onUltimateProducerReached(producerContext, LocalFetchProducer.this.getProducerName(), true);
                producerContext.putOriginExtra("local");
                return encodedImage;
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.LocalFetchProducer.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                statefulProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(statefulProducerRunnable);
    }
}
