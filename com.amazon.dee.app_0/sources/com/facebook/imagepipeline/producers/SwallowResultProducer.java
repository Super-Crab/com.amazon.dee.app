package com.facebook.imagepipeline.producers;

import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class SwallowResultProducer<T> implements Producer<Void> {
    private final Producer<T> mInputProducer;

    public SwallowResultProducer(Producer<T> inputProducer) {
        this.mInputProducer = inputProducer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<Void> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new DelegatingConsumer<T, Void>(consumer) { // from class: com.facebook.imagepipeline.producers.SwallowResultProducer.1
            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            protected void onNewResultImpl(@Nullable T newResult, int status) {
                if (BaseConsumer.isLast(status)) {
                    getConsumer().onNewResult(null, status);
                }
            }
        }, producerContext);
    }
}
