package rx.plugins;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintStream;
import java.util.concurrent.ScheduledExecutorService;
import rx.Completable;
import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.OnSubscribeOnAssembly;
import rx.internal.operators.OnSubscribeOnAssemblyCompletable;
import rx.internal.operators.OnSubscribeOnAssemblySingle;
@Experimental
/* loaded from: classes5.dex */
public final class RxJavaHooks {
    static volatile boolean lockdown;
    static volatile Func1<Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe> onCompletableCreate;
    static volatile Func1<Completable.CompletableOperator, Completable.CompletableOperator> onCompletableLift;
    static volatile Func2<Completable, Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe> onCompletableStart;
    static volatile Func1<Throwable, Throwable> onCompletableSubscribeError;
    static volatile Func1<Scheduler, Scheduler> onComputationScheduler;
    static volatile Action1<Throwable> onError;
    static volatile Func0<? extends ScheduledExecutorService> onGenericScheduledExecutorService;
    static volatile Func1<Scheduler, Scheduler> onIOScheduler;
    static volatile Func1<Scheduler, Scheduler> onNewThreadScheduler;
    static volatile Func1<Observable.OnSubscribe, Observable.OnSubscribe> onObservableCreate;
    static volatile Func1<Observable.Operator, Observable.Operator> onObservableLift;
    static volatile Func1<Subscription, Subscription> onObservableReturn;
    static volatile Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> onObservableStart;
    static volatile Func1<Throwable, Throwable> onObservableSubscribeError;
    static volatile Func1<Action0, Action0> onScheduleAction;
    static volatile Func1<Single.OnSubscribe, Single.OnSubscribe> onSingleCreate;
    static volatile Func1<Observable.Operator, Observable.Operator> onSingleLift;
    static volatile Func1<Subscription, Subscription> onSingleReturn;
    static volatile Func2<Single, Observable.OnSubscribe, Observable.OnSubscribe> onSingleStart;
    static volatile Func1<Throwable, Throwable> onSingleSubscribeError;

    static {
        init();
    }

    private RxJavaHooks() {
        throw new IllegalStateException("No instances!");
    }

    public static void clear() {
        if (lockdown) {
            return;
        }
        onError = null;
        onObservableCreate = null;
        onObservableStart = null;
        onObservableReturn = null;
        onObservableSubscribeError = null;
        onObservableLift = null;
        onSingleCreate = null;
        onSingleStart = null;
        onSingleReturn = null;
        onSingleSubscribeError = null;
        onSingleLift = null;
        onCompletableCreate = null;
        onCompletableStart = null;
        onCompletableSubscribeError = null;
        onCompletableLift = null;
        onComputationScheduler = null;
        onIOScheduler = null;
        onNewThreadScheduler = null;
        onScheduleAction = null;
        onGenericScheduledExecutorService = null;
    }

    public static void clearAssemblyTracking() {
        if (lockdown) {
            return;
        }
        onObservableCreate = null;
        onSingleCreate = null;
        onCompletableCreate = null;
    }

    public static void enableAssemblyTracking() {
        if (lockdown) {
            return;
        }
        onObservableCreate = new Func1<Observable.OnSubscribe, Observable.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.17
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Observable.OnSubscribe mo13102call(Observable.OnSubscribe onSubscribe) {
                return new OnSubscribeOnAssembly(onSubscribe);
            }
        };
        onSingleCreate = new Func1<Single.OnSubscribe, Single.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.18
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Single.OnSubscribe mo13102call(Single.OnSubscribe onSubscribe) {
                return new OnSubscribeOnAssemblySingle(onSubscribe);
            }
        };
        onCompletableCreate = new Func1<Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe>() { // from class: rx.plugins.RxJavaHooks.19
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Completable.CompletableOnSubscribe mo13102call(Completable.CompletableOnSubscribe completableOnSubscribe) {
                return new OnSubscribeOnAssemblyCompletable(completableOnSubscribe);
            }
        };
    }

    public static Func1<Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe> getOnCompletableCreate() {
        return onCompletableCreate;
    }

    public static Func1<Completable.CompletableOperator, Completable.CompletableOperator> getOnCompletableLift() {
        return onCompletableLift;
    }

    public static Func2<Completable, Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe> getOnCompletableStart() {
        return onCompletableStart;
    }

    public static Func1<Throwable, Throwable> getOnCompletableSubscribeError() {
        return onCompletableSubscribeError;
    }

    public static Func1<Scheduler, Scheduler> getOnComputationScheduler() {
        return onComputationScheduler;
    }

    public static Action1<Throwable> getOnError() {
        return onError;
    }

    public static Func0<? extends ScheduledExecutorService> getOnGenericScheduledExecutorService() {
        return onGenericScheduledExecutorService;
    }

    public static Func1<Scheduler, Scheduler> getOnIOScheduler() {
        return onIOScheduler;
    }

    public static Func1<Scheduler, Scheduler> getOnNewThreadScheduler() {
        return onNewThreadScheduler;
    }

    public static Func1<Observable.OnSubscribe, Observable.OnSubscribe> getOnObservableCreate() {
        return onObservableCreate;
    }

    public static Func1<Observable.Operator, Observable.Operator> getOnObservableLift() {
        return onObservableLift;
    }

    public static Func1<Subscription, Subscription> getOnObservableReturn() {
        return onObservableReturn;
    }

    public static Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> getOnObservableStart() {
        return onObservableStart;
    }

    public static Func1<Throwable, Throwable> getOnObservableSubscribeError() {
        return onObservableSubscribeError;
    }

    public static Func1<Action0, Action0> getOnScheduleAction() {
        return onScheduleAction;
    }

    public static Func1<Single.OnSubscribe, Single.OnSubscribe> getOnSingleCreate() {
        return onSingleCreate;
    }

    public static Func1<Observable.Operator, Observable.Operator> getOnSingleLift() {
        return onSingleLift;
    }

    public static Func1<Subscription, Subscription> getOnSingleReturn() {
        return onSingleReturn;
    }

    public static Func2<Single, Observable.OnSubscribe, Observable.OnSubscribe> getOnSingleStart() {
        return onSingleStart;
    }

    public static Func1<Throwable, Throwable> getOnSingleSubscribeError() {
        return onSingleSubscribeError;
    }

    static void init() {
        onError = new Action1<Throwable>() { // from class: rx.plugins.RxJavaHooks.1
            @Override // rx.functions.Action1
            public void call(Throwable th) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        };
        onObservableStart = new Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.2
            @Override // rx.functions.Func2
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Observable.OnSubscribe mo13094call(Observable observable, Observable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeStart(observable, onSubscribe);
            }
        };
        onObservableReturn = new Func1<Subscription, Subscription>() { // from class: rx.plugins.RxJavaHooks.3
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Subscription mo13102call(Subscription subscription) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeReturn(subscription);
            }
        };
        onSingleStart = new Func2<Single, Observable.OnSubscribe, Observable.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.4
            @Override // rx.functions.Func2
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Observable.OnSubscribe mo13094call(Single single, Observable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeStart(single, onSubscribe);
            }
        };
        onSingleReturn = new Func1<Subscription, Subscription>() { // from class: rx.plugins.RxJavaHooks.5
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Subscription mo13102call(Subscription subscription) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeReturn(subscription);
            }
        };
        onCompletableStart = new Func2<Completable, Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe>() { // from class: rx.plugins.RxJavaHooks.6
            @Override // rx.functions.Func2
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Completable.CompletableOnSubscribe mo13094call(Completable completable, Completable.CompletableOnSubscribe completableOnSubscribe) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeStart(completable, completableOnSubscribe);
            }
        };
        onScheduleAction = new Func1<Action0, Action0>() { // from class: rx.plugins.RxJavaHooks.7
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Action0 mo13102call(Action0 action0) {
                return RxJavaPlugins.getInstance().getSchedulersHook().onSchedule(action0);
            }
        };
        onObservableSubscribeError = new Func1<Throwable, Throwable>() { // from class: rx.plugins.RxJavaHooks.8
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Throwable mo13102call(Throwable th) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeError(th);
            }
        };
        onObservableLift = new Func1<Observable.Operator, Observable.Operator>() { // from class: rx.plugins.RxJavaHooks.9
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Observable.Operator mo13102call(Observable.Operator operator) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onLift(operator);
            }
        };
        onSingleSubscribeError = new Func1<Throwable, Throwable>() { // from class: rx.plugins.RxJavaHooks.10
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Throwable mo13102call(Throwable th) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeError(th);
            }
        };
        onSingleLift = new Func1<Observable.Operator, Observable.Operator>() { // from class: rx.plugins.RxJavaHooks.11
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Observable.Operator mo13102call(Observable.Operator operator) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onLift(operator);
            }
        };
        onCompletableSubscribeError = new Func1<Throwable, Throwable>() { // from class: rx.plugins.RxJavaHooks.12
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Throwable mo13102call(Throwable th) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeError(th);
            }
        };
        onCompletableLift = new Func1<Completable.CompletableOperator, Completable.CompletableOperator>() { // from class: rx.plugins.RxJavaHooks.13
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Completable.CompletableOperator mo13102call(Completable.CompletableOperator completableOperator) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onLift(completableOperator);
            }
        };
        initCreate();
    }

    static void initCreate() {
        onObservableCreate = new Func1<Observable.OnSubscribe, Observable.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.14
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Observable.OnSubscribe mo13102call(Observable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onCreate(onSubscribe);
            }
        };
        onSingleCreate = new Func1<Single.OnSubscribe, Single.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.15
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Single.OnSubscribe mo13102call(Single.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onCreate(onSubscribe);
            }
        };
        onCompletableCreate = new Func1<Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe>() { // from class: rx.plugins.RxJavaHooks.16
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Completable.CompletableOnSubscribe mo13102call(Completable.CompletableOnSubscribe completableOnSubscribe) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onCreate(completableOnSubscribe);
            }
        };
    }

    public static boolean isLockdown() {
        return lockdown;
    }

    public static void lockdown() {
        lockdown = true;
    }

    public static Throwable onCompletableError(Throwable th) {
        Func1<Throwable, Throwable> func1 = onCompletableSubscribeError;
        return func1 != null ? func1.mo13102call(th) : th;
    }

    public static <T, R> Completable.CompletableOperator onCompletableLift(Completable.CompletableOperator completableOperator) {
        Func1<Completable.CompletableOperator, Completable.CompletableOperator> func1 = onCompletableLift;
        return func1 != null ? func1.mo13102call(completableOperator) : completableOperator;
    }

    public static <T> Completable.CompletableOnSubscribe onCompletableStart(Completable completable, Completable.CompletableOnSubscribe completableOnSubscribe) {
        Func2<Completable, Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe> func2 = onCompletableStart;
        return func2 != null ? func2.mo13094call(completable, completableOnSubscribe) : completableOnSubscribe;
    }

    public static Scheduler onComputationScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> func1 = onComputationScheduler;
        return func1 != null ? func1.mo13102call(scheduler) : scheduler;
    }

    public static <T> Observable.OnSubscribe<T> onCreate(Observable.OnSubscribe<T> onSubscribe) {
        Func1<Observable.OnSubscribe, Observable.OnSubscribe> func1 = onObservableCreate;
        return func1 != null ? func1.mo13102call(onSubscribe) : onSubscribe;
    }

    public static void onError(Throwable th) {
        Action1<Throwable> action1 = onError;
        if (action1 != null) {
            try {
                action1.call(th);
                return;
            } catch (Throwable th2) {
                PrintStream printStream = System.err;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The onError handler threw an Exception. It shouldn't. => ");
                outline107.append(th2.getMessage());
                printStream.println(outline107.toString());
                th2.printStackTrace();
                signalUncaught(th2);
            }
        }
        signalUncaught(th);
    }

    public static Scheduler onIOScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> func1 = onIOScheduler;
        return func1 != null ? func1.mo13102call(scheduler) : scheduler;
    }

    public static Scheduler onNewThreadScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> func1 = onNewThreadScheduler;
        return func1 != null ? func1.mo13102call(scheduler) : scheduler;
    }

    public static Throwable onObservableError(Throwable th) {
        Func1<Throwable, Throwable> func1 = onObservableSubscribeError;
        return func1 != null ? func1.mo13102call(th) : th;
    }

    public static <T, R> Observable.Operator<R, T> onObservableLift(Observable.Operator<R, T> operator) {
        Func1<Observable.Operator, Observable.Operator> func1 = onObservableLift;
        return func1 != null ? func1.mo13102call(operator) : operator;
    }

    public static Subscription onObservableReturn(Subscription subscription) {
        Func1<Subscription, Subscription> func1 = onObservableReturn;
        return func1 != null ? func1.mo13102call(subscription) : subscription;
    }

    public static <T> Observable.OnSubscribe<T> onObservableStart(Observable<T> observable, Observable.OnSubscribe<T> onSubscribe) {
        Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> func2 = onObservableStart;
        return func2 != null ? func2.mo13094call(observable, onSubscribe) : onSubscribe;
    }

    public static Action0 onScheduledAction(Action0 action0) {
        Func1<Action0, Action0> func1 = onScheduleAction;
        return func1 != null ? func1.mo13102call(action0) : action0;
    }

    public static Throwable onSingleError(Throwable th) {
        Func1<Throwable, Throwable> func1 = onSingleSubscribeError;
        return func1 != null ? func1.mo13102call(th) : th;
    }

    public static <T, R> Observable.Operator<R, T> onSingleLift(Observable.Operator<R, T> operator) {
        Func1<Observable.Operator, Observable.Operator> func1 = onSingleLift;
        return func1 != null ? func1.mo13102call(operator) : operator;
    }

    public static Subscription onSingleReturn(Subscription subscription) {
        Func1<Subscription, Subscription> func1 = onSingleReturn;
        return func1 != null ? func1.mo13102call(subscription) : subscription;
    }

    public static <T> Observable.OnSubscribe<T> onSingleStart(Single<T> single, Observable.OnSubscribe<T> onSubscribe) {
        Func2<Single, Observable.OnSubscribe, Observable.OnSubscribe> func2 = onSingleStart;
        return func2 != null ? func2.mo13094call(single, onSubscribe) : onSubscribe;
    }

    public static void reset() {
        if (lockdown) {
            return;
        }
        init();
        onComputationScheduler = null;
        onIOScheduler = null;
        onNewThreadScheduler = null;
        onGenericScheduledExecutorService = null;
    }

    public static void resetAssemblyTracking() {
        if (lockdown) {
            return;
        }
        initCreate();
    }

    public static void setOnCompletableCreate(Func1<Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe> func1) {
        if (lockdown) {
            return;
        }
        onCompletableCreate = func1;
    }

    public static void setOnCompletableLift(Func1<Completable.CompletableOperator, Completable.CompletableOperator> func1) {
        if (lockdown) {
            return;
        }
        onCompletableLift = func1;
    }

    public static void setOnCompletableStart(Func2<Completable, Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe> func2) {
        if (lockdown) {
            return;
        }
        onCompletableStart = func2;
    }

    public static void setOnCompletableSubscribeError(Func1<Throwable, Throwable> func1) {
        if (lockdown) {
            return;
        }
        onCompletableSubscribeError = func1;
    }

    public static void setOnComputationScheduler(Func1<Scheduler, Scheduler> func1) {
        if (lockdown) {
            return;
        }
        onComputationScheduler = func1;
    }

    public static void setOnError(Action1<Throwable> action1) {
        if (lockdown) {
            return;
        }
        onError = action1;
    }

    public static void setOnGenericScheduledExecutorService(Func0<? extends ScheduledExecutorService> func0) {
        if (lockdown) {
            return;
        }
        onGenericScheduledExecutorService = func0;
    }

    public static void setOnIOScheduler(Func1<Scheduler, Scheduler> func1) {
        if (lockdown) {
            return;
        }
        onIOScheduler = func1;
    }

    public static void setOnNewThreadScheduler(Func1<Scheduler, Scheduler> func1) {
        if (lockdown) {
            return;
        }
        onNewThreadScheduler = func1;
    }

    public static void setOnObservableCreate(Func1<Observable.OnSubscribe, Observable.OnSubscribe> func1) {
        if (lockdown) {
            return;
        }
        onObservableCreate = func1;
    }

    public static void setOnObservableLift(Func1<Observable.Operator, Observable.Operator> func1) {
        if (lockdown) {
            return;
        }
        onObservableLift = func1;
    }

    public static void setOnObservableReturn(Func1<Subscription, Subscription> func1) {
        if (lockdown) {
            return;
        }
        onObservableReturn = func1;
    }

    public static void setOnObservableStart(Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> func2) {
        if (lockdown) {
            return;
        }
        onObservableStart = func2;
    }

    public static void setOnObservableSubscribeError(Func1<Throwable, Throwable> func1) {
        if (lockdown) {
            return;
        }
        onObservableSubscribeError = func1;
    }

    public static void setOnScheduleAction(Func1<Action0, Action0> func1) {
        if (lockdown) {
            return;
        }
        onScheduleAction = func1;
    }

    public static void setOnSingleCreate(Func1<Single.OnSubscribe, Single.OnSubscribe> func1) {
        if (lockdown) {
            return;
        }
        onSingleCreate = func1;
    }

    public static void setOnSingleLift(Func1<Observable.Operator, Observable.Operator> func1) {
        if (lockdown) {
            return;
        }
        onSingleLift = func1;
    }

    public static void setOnSingleReturn(Func1<Subscription, Subscription> func1) {
        if (lockdown) {
            return;
        }
        onSingleReturn = func1;
    }

    public static void setOnSingleStart(Func2<Single, Observable.OnSubscribe, Observable.OnSubscribe> func2) {
        if (lockdown) {
            return;
        }
        onSingleStart = func2;
    }

    public static void setOnSingleSubscribeError(Func1<Throwable, Throwable> func1) {
        if (lockdown) {
            return;
        }
        onSingleSubscribeError = func1;
    }

    static void signalUncaught(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static <T> Single.OnSubscribe<T> onCreate(Single.OnSubscribe<T> onSubscribe) {
        Func1<Single.OnSubscribe, Single.OnSubscribe> func1 = onSingleCreate;
        return func1 != null ? func1.mo13102call(onSubscribe) : onSubscribe;
    }

    public static Completable.CompletableOnSubscribe onCreate(Completable.CompletableOnSubscribe completableOnSubscribe) {
        Func1<Completable.CompletableOnSubscribe, Completable.CompletableOnSubscribe> func1 = onCompletableCreate;
        return func1 != null ? func1.mo13102call(completableOnSubscribe) : completableOnSubscribe;
    }
}
