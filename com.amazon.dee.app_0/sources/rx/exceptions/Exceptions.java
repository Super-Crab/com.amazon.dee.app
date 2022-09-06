package rx.exceptions;

import java.util.HashSet;
import java.util.List;
import rx.Observer;
import rx.SingleSubscriber;
import rx.annotations.Experimental;
/* loaded from: classes5.dex */
public final class Exceptions {
    private static final int MAX_DEPTH = 25;

    private Exceptions() {
        throw new IllegalStateException("No instances!");
    }

    public static void addCause(Throwable th, Throwable th2) {
        HashSet hashSet = new HashSet();
        int i = 0;
        while (th.getCause() != null) {
            int i2 = i + 1;
            if (i >= 25) {
                return;
            }
            th = th.getCause();
            if (!hashSet.contains(th.getCause())) {
                hashSet.add(th.getCause());
                i = i2;
            }
        }
        try {
            th.initCause(th2);
        } catch (Throwable unused) {
        }
    }

    public static Throwable getFinalCause(Throwable th) {
        int i = 0;
        while (th.getCause() != null) {
            int i2 = i + 1;
            if (i >= 25) {
                return new RuntimeException("Stack too deep to get final cause");
            }
            th = th.getCause();
            i = i2;
        }
        return th;
    }

    public static RuntimeException propagate(Throwable th) {
        if (!(th instanceof RuntimeException)) {
            if (th instanceof Error) {
                throw ((Error) th);
            }
            throw new RuntimeException(th);
        }
        throw ((RuntimeException) th);
    }

    public static void throwIfAny(List<? extends Throwable> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (list.size() == 1) {
            Throwable th = list.get(0);
            if (!(th instanceof RuntimeException)) {
                if (th instanceof Error) {
                    throw ((Error) th);
                }
                throw new RuntimeException(th);
            }
            throw ((RuntimeException) th);
        }
        throw new CompositeException(list);
    }

    public static void throwIfFatal(Throwable th) {
        if (!(th instanceof OnErrorNotImplementedException)) {
            if (!(th instanceof OnErrorFailedException)) {
                if (!(th instanceof OnCompletedFailedException)) {
                    if (!(th instanceof StackOverflowError)) {
                        if (!(th instanceof VirtualMachineError)) {
                            if (!(th instanceof ThreadDeath)) {
                                if (th instanceof LinkageError) {
                                    throw ((LinkageError) th);
                                }
                                return;
                            }
                            throw ((ThreadDeath) th);
                        }
                        throw ((VirtualMachineError) th);
                    }
                    throw ((StackOverflowError) th);
                }
                throw ((OnCompletedFailedException) th);
            }
            throw ((OnErrorFailedException) th);
        }
        throw ((OnErrorNotImplementedException) th);
    }

    @Experimental
    public static void throwOrReport(Throwable th, Observer<?> observer, Object obj) {
        throwIfFatal(th);
        observer.onError(OnErrorThrowable.addValueAsLastCause(th, obj));
    }

    @Experimental
    public static void throwOrReport(Throwable th, Observer<?> observer) {
        throwIfFatal(th);
        observer.onError(th);
    }

    @Experimental
    public static void throwOrReport(Throwable th, SingleSubscriber<?> singleSubscriber) {
        throwIfFatal(th);
        singleSubscriber.onError(th);
    }
}
