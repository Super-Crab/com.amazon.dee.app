package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.Priority;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
public class PriorityStarvingThrottlingProducer<T> implements Producer<T> {
    public static final String PRODUCER_NAME = "PriorityStarvingThrottlingProducer";
    private final Executor mExecutor;
    private final Producer<T> mInputProducer;
    private final int mMaxSimultaneousRequests;
    @GuardedBy("this")
    private final Queue<Item<T>> mPendingRequests = new PriorityQueue(11, new PriorityComparator());
    @GuardedBy("this")
    private int mNumCurrentRequests = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Item<T> {
        final Consumer<T> consumer;
        final ProducerContext producerContext;
        final long time;

        Item(Consumer<T> consumer, ProducerContext producerContext, long time) {
            this.consumer = consumer;
            this.producerContext = producerContext;
            this.time = time;
        }
    }

    /* loaded from: classes2.dex */
    static class PriorityComparator<T> implements Comparator<Item<T>> {
        PriorityComparator() {
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Object o1, Object o2) {
            return compare((Item) ((Item) o1), (Item) ((Item) o2));
        }

        public int compare(Item<T> o1, Item<T> o2) {
            Priority priority = o1.producerContext.getPriority();
            Priority priority2 = o2.producerContext.getPriority();
            if (priority == priority2) {
                return Double.compare(o1.time, o2.time);
            }
            return priority.ordinal() > priority2.ordinal() ? -1 : 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ThrottlerConsumer extends DelegatingConsumer<T, T> {
        private void onRequestFinished() {
            final Item item;
            synchronized (PriorityStarvingThrottlingProducer.this) {
                item = (Item) PriorityStarvingThrottlingProducer.this.mPendingRequests.poll();
                if (item == null) {
                    PriorityStarvingThrottlingProducer.access$210(PriorityStarvingThrottlingProducer.this);
                }
            }
            if (item != null) {
                PriorityStarvingThrottlingProducer.this.mExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.producers.PriorityStarvingThrottlingProducer.ThrottlerConsumer.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PriorityStarvingThrottlingProducer.this.produceResultsInternal(item);
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

    public PriorityStarvingThrottlingProducer(int maxSimultaneousRequests, Executor executor, final Producer<T> inputProducer) {
        this.mMaxSimultaneousRequests = maxSimultaneousRequests;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
    }

    static /* synthetic */ int access$210(PriorityStarvingThrottlingProducer priorityStarvingThrottlingProducer) {
        int i = priorityStarvingThrottlingProducer.mNumCurrentRequests;
        priorityStarvingThrottlingProducer.mNumCurrentRequests = i - 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void produceResultsInternal(Item<T> item) {
        item.producerContext.getProducerListener().onProducerFinishWithSuccess(item.producerContext, PRODUCER_NAME, null);
        this.mInputProducer.produceResults(new ThrottlerConsumer(item.consumer), item.producerContext);
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<T> consumer, final ProducerContext producerContext) {
        boolean z;
        long nanoTime = System.nanoTime();
        producerContext.getProducerListener().onProducerStart(producerContext, PRODUCER_NAME);
        synchronized (this) {
            z = true;
            if (this.mNumCurrentRequests >= this.mMaxSimultaneousRequests) {
                this.mPendingRequests.add(new Item<>(consumer, producerContext, nanoTime));
            } else {
                this.mNumCurrentRequests++;
                z = false;
            }
        }
        if (!z) {
            produceResultsInternal(new Item<>(consumer, producerContext, nanoTime));
        }
    }
}
