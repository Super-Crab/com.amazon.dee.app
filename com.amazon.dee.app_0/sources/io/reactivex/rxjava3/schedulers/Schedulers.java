package io.reactivex.rxjava3.schedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.schedulers.ComputationScheduler;
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler;
import io.reactivex.rxjava3.internal.schedulers.IoScheduler;
import io.reactivex.rxjava3.internal.schedulers.NewThreadScheduler;
import io.reactivex.rxjava3.internal.schedulers.SchedulerPoolFactory;
import io.reactivex.rxjava3.internal.schedulers.SingleScheduler;
import io.reactivex.rxjava3.internal.schedulers.TrampolineScheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.Executor;
/* loaded from: classes3.dex */
public final class Schedulers {
    @NonNull
    static final Scheduler SINGLE = RxJavaPlugins.initSingleScheduler(new SingleTask());
    @NonNull
    static final Scheduler COMPUTATION = RxJavaPlugins.initComputationScheduler(new ComputationTask());
    @NonNull
    static final Scheduler IO = RxJavaPlugins.initIoScheduler(new IOTask());
    @NonNull
    static final Scheduler TRAMPOLINE = TrampolineScheduler.instance();
    @NonNull
    static final Scheduler NEW_THREAD = RxJavaPlugins.initNewThreadScheduler(new NewThreadTask());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ComputationHolder {
        static final Scheduler DEFAULT = new ComputationScheduler();

        ComputationHolder() {
        }
    }

    /* loaded from: classes3.dex */
    static final class ComputationTask implements Supplier<Scheduler> {
        ComputationTask() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public Scheduler mo10365get() {
            return ComputationHolder.DEFAULT;
        }
    }

    /* loaded from: classes3.dex */
    static final class IOTask implements Supplier<Scheduler> {
        IOTask() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public Scheduler mo10365get() {
            return IoHolder.DEFAULT;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class IoHolder {
        static final Scheduler DEFAULT = new IoScheduler();

        IoHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class NewThreadHolder {
        static final Scheduler DEFAULT = new NewThreadScheduler();

        NewThreadHolder() {
        }
    }

    /* loaded from: classes3.dex */
    static final class NewThreadTask implements Supplier<Scheduler> {
        NewThreadTask() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public Scheduler mo10365get() {
            return NewThreadHolder.DEFAULT;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class SingleHolder {
        static final Scheduler DEFAULT = new SingleScheduler();

        SingleHolder() {
        }
    }

    /* loaded from: classes3.dex */
    static final class SingleTask implements Supplier<Scheduler> {
        SingleTask() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public Scheduler mo10365get() {
            return SingleHolder.DEFAULT;
        }
    }

    private Schedulers() {
        throw new IllegalStateException("No instances!");
    }

    @NonNull
    public static Scheduler computation() {
        return RxJavaPlugins.onComputationScheduler(COMPUTATION);
    }

    @NonNull
    public static Scheduler from(@NonNull Executor executor) {
        return new ExecutorScheduler(executor, false, false);
    }

    @NonNull
    public static Scheduler io() {
        return RxJavaPlugins.onIoScheduler(IO);
    }

    @NonNull
    public static Scheduler newThread() {
        return RxJavaPlugins.onNewThreadScheduler(NEW_THREAD);
    }

    public static void shutdown() {
        computation().shutdown();
        io().shutdown();
        newThread().shutdown();
        single().shutdown();
        trampoline().shutdown();
        SchedulerPoolFactory.shutdown();
    }

    @NonNull
    public static Scheduler single() {
        return RxJavaPlugins.onSingleScheduler(SINGLE);
    }

    public static void start() {
        computation().start();
        io().start();
        newThread().start();
        single().start();
        trampoline().start();
        SchedulerPoolFactory.start();
    }

    @NonNull
    public static Scheduler trampoline() {
        return TRAMPOLINE;
    }

    @NonNull
    public static Scheduler from(@NonNull Executor executor, boolean interruptibleWorker) {
        return new ExecutorScheduler(executor, interruptibleWorker, false);
    }

    @NonNull
    public static Scheduler from(@NonNull Executor executor, boolean interruptibleWorker, boolean fair) {
        return new ExecutorScheduler(executor, interruptibleWorker, fair);
    }
}
