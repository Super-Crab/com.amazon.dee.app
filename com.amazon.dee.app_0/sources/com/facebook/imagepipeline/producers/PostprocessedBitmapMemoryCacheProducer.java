package com.facebook.imagepipeline.producers;

import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class PostprocessedBitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String PRODUCER_NAME = "PostprocessedBitmapMemoryCacheProducer";
    @VisibleForTesting
    static final String VALUE_FOUND = "cached_value_found";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

    /* loaded from: classes2.dex */
    public static class CachedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private final CacheKey mCacheKey;
        private final boolean mIsMemoryCachedEnabled;
        private final boolean mIsRepeatedProcessor;
        private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

        public CachedPostprocessorConsumer(final Consumer<CloseableReference<CloseableImage>> consumer, final CacheKey cacheKey, final boolean isRepeatedProcessor, final MemoryCache<CacheKey, CloseableImage> memoryCache, boolean isMemoryCachedEnabled) {
            super(consumer);
            this.mCacheKey = cacheKey;
            this.mIsRepeatedProcessor = isRepeatedProcessor;
            this.mMemoryCache = memoryCache;
            this.mIsMemoryCachedEnabled = isMemoryCachedEnabled;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> newResult, int status) {
            CloseableReference<CloseableImage> closeableReference = null;
            if (newResult == null) {
                if (!BaseConsumer.isLast(status)) {
                    return;
                }
                getConsumer().onNewResult(null, status);
            } else if (BaseConsumer.isNotLast(status) && !this.mIsRepeatedProcessor) {
            } else {
                if (this.mIsMemoryCachedEnabled) {
                    closeableReference = this.mMemoryCache.cache(this.mCacheKey, newResult);
                }
                try {
                    getConsumer().onProgressUpdate(1.0f);
                    Consumer<CloseableReference<CloseableImage>> consumer = getConsumer();
                    if (closeableReference != null) {
                        newResult = closeableReference;
                    }
                    consumer.onNewResult(newResult, status);
                } finally {
                    CloseableReference.closeSafely(closeableReference);
                }
            }
        }
    }

    public PostprocessedBitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> inputProducer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = inputProducer;
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        ProducerListener2 producerListener = producerContext.getProducerListener();
        ImageRequest imageRequest = producerContext.getImageRequest();
        Object callerContext = producerContext.getCallerContext();
        Postprocessor postprocessor = imageRequest.getPostprocessor();
        if (postprocessor != null && postprocessor.getPostprocessorCacheKey() != null) {
            producerListener.onProducerStart(producerContext, getProducerName());
            CacheKey postprocessedBitmapCacheKey = this.mCacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, callerContext);
            CloseableReference<CloseableImage> closeableReference = this.mMemoryCache.get(postprocessedBitmapCacheKey);
            Map<String, String> map = null;
            if (closeableReference != null) {
                String producerName = getProducerName();
                if (producerListener.requiresExtraMap(producerContext, getProducerName())) {
                    map = ImmutableMap.of("cached_value_found", "true");
                }
                producerListener.onProducerFinishWithSuccess(producerContext, producerName, map);
                producerListener.onUltimateProducerReached(producerContext, PRODUCER_NAME, true);
                producerContext.putOriginExtra("memory_bitmap", "postprocessed");
                consumer.onProgressUpdate(1.0f);
                consumer.onNewResult(closeableReference, 1);
                closeableReference.close();
                return;
            }
            CachedPostprocessorConsumer cachedPostprocessorConsumer = new CachedPostprocessorConsumer(consumer, postprocessedBitmapCacheKey, postprocessor instanceof RepeatedPostprocessor, this.mMemoryCache, producerContext.getImageRequest().isMemoryCacheEnabled());
            String producerName2 = getProducerName();
            if (producerListener.requiresExtraMap(producerContext, getProducerName())) {
                map = ImmutableMap.of("cached_value_found", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
            }
            producerListener.onProducerFinishWithSuccess(producerContext, producerName2, map);
            this.mInputProducer.produceResults(cachedPostprocessorConsumer, producerContext);
            return;
        }
        this.mInputProducer.produceResults(consumer, producerContext);
    }
}
