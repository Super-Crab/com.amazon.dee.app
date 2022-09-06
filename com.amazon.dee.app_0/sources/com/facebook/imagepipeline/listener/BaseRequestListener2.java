package com.facebook.imagepipeline.listener;

import androidx.annotation.NonNull;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class BaseRequestListener2 implements RequestListener2 {
    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerEvent(@NonNull ProducerContext producerContext, @NonNull String producerName, @NonNull String eventName) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithCancellation(@NonNull ProducerContext producerContext, @NonNull String producerName, @Nullable Map<String, String> extraMap) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithFailure(@NonNull ProducerContext producerContext, String producerName, Throwable t, @Nullable Map<String, String> extraMap) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithSuccess(@NonNull ProducerContext producerContext, @NonNull String producerName, @Nullable Map<String, String> extraMap) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerStart(@NonNull ProducerContext producerContext, @NonNull String producerName) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestCancellation(@NonNull ProducerContext producerContext) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestFailure(@NonNull ProducerContext producerContext, Throwable throwable) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestStart(@NonNull ProducerContext producerContext) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestSuccess(@NonNull ProducerContext producerContext) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onUltimateProducerReached(@NonNull ProducerContext producerContext, @NonNull String producerName, boolean successful) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public boolean requiresExtraMap(@NonNull ProducerContext producerContext, @NonNull String producerName) {
        return false;
    }
}
