package com.facebook.imagepipeline.producers;

import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class RemoveImageTransformMetaDataProducer implements Producer<CloseableReference<PooledByteBuffer>> {
    private final Producer<EncodedImage> mInputProducer;

    /* loaded from: classes2.dex */
    private class RemoveImageTransformMetaDataConsumer extends DelegatingConsumer<EncodedImage, CloseableReference<PooledByteBuffer>> {
        private RemoveImageTransformMetaDataConsumer(Consumer<CloseableReference<PooledByteBuffer>> consumer) {
            super(consumer);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable EncodedImage newResult, int status) {
            CloseableReference<PooledByteBuffer> closeableReference = null;
            try {
                if (EncodedImage.isValid(newResult)) {
                    closeableReference = newResult.getByteBufferRef();
                }
                getConsumer().onNewResult(closeableReference, status);
            } finally {
                CloseableReference.closeSafely(closeableReference);
            }
        }
    }

    public RemoveImageTransformMetaDataProducer(Producer<EncodedImage> inputProducer) {
        this.mInputProducer = inputProducer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<PooledByteBuffer>> consumer, ProducerContext context) {
        this.mInputProducer.produceResults(new RemoveImageTransformMetaDataConsumer(consumer), context);
    }
}
