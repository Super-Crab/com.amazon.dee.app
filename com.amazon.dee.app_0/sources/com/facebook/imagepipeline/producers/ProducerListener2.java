package com.facebook.imagepipeline.producers;

import androidx.annotation.NonNull;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public interface ProducerListener2 {
    void onProducerEvent(@NonNull ProducerContext producerContext, @NonNull String producerName, @NonNull String eventName);

    void onProducerFinishWithCancellation(@NonNull ProducerContext producerContext, @NonNull String producerName, @Nullable Map<String, String> extraMap);

    void onProducerFinishWithFailure(@NonNull ProducerContext producerContext, String producerName, Throwable t, @Nullable Map<String, String> extraMap);

    void onProducerFinishWithSuccess(@NonNull ProducerContext producerContext, @NonNull String producerName, @Nullable Map<String, String> extraMap);

    void onProducerStart(@NonNull ProducerContext producerContext, @NonNull String producerName);

    void onUltimateProducerReached(@NonNull ProducerContext producerContext, @NonNull String producerName, boolean successful);

    boolean requiresExtraMap(@NonNull ProducerContext producerContext, @NonNull String producerName);
}
