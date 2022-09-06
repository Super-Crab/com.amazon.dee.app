package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public interface ThumbnailProducer<T> extends Producer<T> {
    boolean canProvideImageForSize(@Nullable ResizeOptions resizeOptions);
}
