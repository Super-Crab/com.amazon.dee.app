package com.amazon.deecomms.oobe;
/* loaded from: classes12.dex */
public final class Result<T, E> {
    private final E error;
    private final boolean hasResult;
    private final T value;

    private Result(T t, E e, boolean z) {
        this.value = t;
        this.error = e;
        this.hasResult = z;
    }

    public static <T, E> Result<T, E> error(E e) {
        return new Result<>(null, e, false);
    }

    public static <T, E> Result<T, E> value(T t) {
        return new Result<>(t, null, true);
    }

    public E getError() {
        if (!hasValue()) {
            return this.error;
        }
        throw new UnsupportedOperationException("Cannot call getError on a successful Result.");
    }

    public T getValue() {
        if (hasValue()) {
            return this.value;
        }
        throw new UnsupportedOperationException("Cannot call getValue on a failed Result.");
    }

    public boolean hasValue() {
        return this.hasResult;
    }
}
