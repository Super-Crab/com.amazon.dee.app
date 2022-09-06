package com.facebook.imagepipeline.listener;

import androidx.annotation.NonNull;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.producers.ProducerListener2;
/* loaded from: classes2.dex */
public interface RequestListener2 extends ProducerListener2 {
    void onRequestCancellation(@NonNull ProducerContext producerContext);

    void onRequestFailure(@NonNull ProducerContext producerContext, Throwable throwable);

    void onRequestStart(@NonNull ProducerContext producerContext);

    void onRequestSuccess(@NonNull ProducerContext producerContext);
}
