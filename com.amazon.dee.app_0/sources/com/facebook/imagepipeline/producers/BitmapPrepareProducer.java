package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class BitmapPrepareProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String PRODUCER_NAME = "BitmapPrepareProducer";
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final int mMaxBitmapSizeBytes;
    private final int mMinBitmapSizeBytes;
    private final boolean mPreparePrefetch;

    /* loaded from: classes2.dex */
    private static class BitmapPrepareConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private final int mMaxBitmapSizeBytes;
        private final int mMinBitmapSizeBytes;

        BitmapPrepareConsumer(Consumer<CloseableReference<CloseableImage>> consumer, int minBitmapSizeBytes, int maxBitmapSizeBytes) {
            super(consumer);
            this.mMinBitmapSizeBytes = minBitmapSizeBytes;
            this.mMaxBitmapSizeBytes = maxBitmapSizeBytes;
        }

        private void internalPrepareBitmap(@Nullable CloseableReference<CloseableImage> newResult) {
            CloseableImage closeableImage;
            Bitmap underlyingBitmap;
            if (newResult == null || !newResult.isValid() || (closeableImage = newResult.get()) == null || closeableImage.isClosed() || !(closeableImage instanceof CloseableStaticBitmap) || (underlyingBitmap = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap()) == null) {
                return;
            }
            int height = underlyingBitmap.getHeight() * underlyingBitmap.getRowBytes();
            if (height < this.mMinBitmapSizeBytes || height > this.mMaxBitmapSizeBytes) {
                return;
            }
            underlyingBitmap.prepareToDraw();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> newResult, int status) {
            internalPrepareBitmap(newResult);
            getConsumer().onNewResult(newResult, status);
        }
    }

    public BitmapPrepareProducer(final Producer<CloseableReference<CloseableImage>> inputProducer, int minBitmapSizeBytes, int maxBitmapSizeBytes, boolean preparePrefetch) {
        Preconditions.checkArgument(Boolean.valueOf(minBitmapSizeBytes <= maxBitmapSizeBytes));
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
        this.mMinBitmapSizeBytes = minBitmapSizeBytes;
        this.mMaxBitmapSizeBytes = maxBitmapSizeBytes;
        this.mPreparePrefetch = preparePrefetch;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        if (producerContext.isPrefetch() && !this.mPreparePrefetch) {
            this.mInputProducer.produceResults(consumer, producerContext);
        } else {
            this.mInputProducer.produceResults(new BitmapPrepareConsumer(consumer, this.mMinBitmapSizeBytes, this.mMaxBitmapSizeBytes), producerContext);
        }
    }
}
