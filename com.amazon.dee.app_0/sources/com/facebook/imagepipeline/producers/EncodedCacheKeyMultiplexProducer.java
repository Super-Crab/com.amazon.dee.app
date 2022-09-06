package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class EncodedCacheKeyMultiplexProducer extends MultiplexProducer<Pair<CacheKey, ImageRequest.RequestLevel>, EncodedImage> {
    private final CacheKeyFactory mCacheKeyFactory;

    public EncodedCacheKeyMultiplexProducer(CacheKeyFactory cacheKeyFactory, boolean keepCancelledFetchAsLowPriority, Producer inputProducer) {
        super(inputProducer, "EncodedCacheKeyMultiplexProducer", ProducerContext.ExtraKeys.MULTIPLEX_ENCODED_COUNT, keepCancelledFetchAsLowPriority);
        this.mCacheKeyFactory = cacheKeyFactory;
    }

    @Override // com.facebook.imagepipeline.producers.MultiplexProducer
    @Nullable
    public EncodedImage cloneOrNull(@Nullable EncodedImage encodedImage) {
        return EncodedImage.cloneOrNull(encodedImage);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.imagepipeline.producers.MultiplexProducer
    /* renamed from: getKey */
    public Pair<CacheKey, ImageRequest.RequestLevel> mo6917getKey(ProducerContext producerContext) {
        return Pair.create(this.mCacheKeyFactory.getEncodedCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext()), producerContext.getLowestPermittedRequestLevel());
    }
}
