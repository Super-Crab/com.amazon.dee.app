package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
public class ThrottlingProducer<T> implements Producer<T> {
    public static final String PRODUCER_NAME = "ThrottlingProducer";
    private final Executor mExecutor;
    private final Producer<T> mInputProducer;
    private final int mMaxSimultaneousRequests;
    @GuardedBy("this")
    private final ConcurrentLinkedQueue<Pair<Consumer<T>, ProducerContext>> mPendingRequests = new ConcurrentLinkedQueue<>();
    @GuardedBy("this")
    private int mNumCurrentRequests = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ThrottlerConsumer extends DelegatingConsumer<T, T> {
        private void onRequestFinished() {
            final Pair pair;
            synchronized (ThrottlingProducer.this) {
                pair = (Pair) ThrottlingProducer.this.mPendingRequests.poll();
                if (pair == null) {
                    ThrottlingProducer.access$210(ThrottlingProducer.this);
                }
            }
            if (pair != null) {
                ThrottlingProducer.this.mExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.producers.ThrottlingProducer.ThrottlerConsumer.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ThrottlingProducer throttlingProducer = ThrottlingProducer.this;
                        Pair pair2 = pair;
                        throttlingProducer.produceResultsInternal((Consumer) pair2.first, (ProducerContext) pair2.second);
                    }
                });
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onCancellationImpl() {
            getConsumer().onCancellation();
            onRequestFinished();
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onFailureImpl(Throwable t) {
            getConsumer().onFailure(t);
            onRequestFinished();
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        protected void onNewResultImpl(@Nullable T newResult, int status) {
            getConsumer().onNewResult(newResult, status);
            if (BaseConsumer.isLast(status)) {
                onRequestFinished();
            }
        }

        private ThrottlerConsumer(Consumer<T> consumer) {
            super(consumer);
        }
    }

    public ThrottlingProducer(int maxSimultaneousRequests, Executor executor, final Producer<T> inputProducer) {
        this.mMaxSimultaneousRequests = maxSimultaneousRequests;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
    }

    static /* synthetic */ int access$210(ThrottlingProducer throttlingProducer) {
        int i = throttlingProducer.mNumCurrentRequests;
        throttlingProducer.mNumCurrentRequests = i - 1;
        return i;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<T> consumer, final ProducerContext producerContext) {
        boolean z;
        producerContext.getProducerListener().onProducerStart(producerContext, PRODUCER_NAME);
        synchronized (this) {
            z = true;
            if (this.mNumCurrentRequests >= this.mMaxSimultaneousRequests) {
                this.mPendingRequests.add(Pair.create(consumer, producerContext));
            } else {
                this.mNumCurrentRequests++;
                z = false;
            }
        }
        if (!z) {
            produceResultsInternal(consumer, producerContext);
        }
    }

    void produceResultsInternal(Consumer<T> consumer, ProducerContext producerContext) {
        producerContext.getProducerListener().onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, null);
        this.mInputProducer.produceResults(new ThrottlerConsumer(consumer), producerContext);
    }
}
