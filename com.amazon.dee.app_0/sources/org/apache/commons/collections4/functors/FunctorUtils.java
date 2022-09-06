package org.apache.commons.collections4.functors;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
class FunctorUtils {
    private FunctorUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T> Closure<T> coerce(Closure<? super T> closure) {
        return closure;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Predicate<T> coerce(Predicate<? super T> predicate) {
        return predicate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <I, O> Transformer<I, O> coerce(Transformer<? super I, ? extends O> transformer) {
        return transformer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Predicate<T>[] copy(Predicate<? super T>... predicateArr) {
        if (predicateArr == null) {
            return null;
        }
        return (Predicate[]) predicateArr.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validate(Predicate<?>... predicateArr) {
        if (predicateArr != null) {
            for (int i = 0; i < predicateArr.length; i++) {
                if (predicateArr[i] == null) {
                    throw new NullPointerException(GeneratedOutlineSupport1.outline52("The predicate array must not contain a null predicate, index ", i, " was null"));
                }
            }
            return;
        }
        throw new NullPointerException("The predicate array must not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Closure<E>[] copy(Closure<? super E>... closureArr) {
        if (closureArr == null) {
            return null;
        }
        return (Closure[]) closureArr.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <I, O> Transformer<I, O>[] copy(Transformer<? super I, ? extends O>... transformerArr) {
        if (transformerArr == null) {
            return null;
        }
        return (Transformer[]) transformerArr.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Predicate<? super T>[] validate(Collection<? extends Predicate<? super T>> collection) {
        if (collection != null) {
            Predicate<? super T>[] predicateArr = new Predicate[collection.size()];
            int i = 0;
            for (Predicate<? super T> predicate : collection) {
                predicateArr[i] = predicate;
                if (predicateArr[i] == null) {
                    throw new NullPointerException(GeneratedOutlineSupport1.outline52("The predicate collection must not contain a null predicate, index ", i, " was null"));
                }
                i++;
            }
            return predicateArr;
        }
        throw new NullPointerException("The predicate collection must not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validate(Closure<?>... closureArr) {
        if (closureArr != null) {
            for (int i = 0; i < closureArr.length; i++) {
                if (closureArr[i] == null) {
                    throw new NullPointerException(GeneratedOutlineSupport1.outline52("The closure array must not contain a null closure, index ", i, " was null"));
                }
            }
            return;
        }
        throw new NullPointerException("The closure array must not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validate(Transformer<?, ?>... transformerArr) {
        if (transformerArr != null) {
            for (int i = 0; i < transformerArr.length; i++) {
                if (transformerArr[i] == null) {
                    throw new NullPointerException(GeneratedOutlineSupport1.outline52("The transformer array must not contain a null transformer, index ", i, " was null"));
                }
            }
            return;
        }
        throw new NullPointerException("The transformer array must not be null");
    }
}
