package com.amazon.deecomms.calling.api;
/* loaded from: classes12.dex */
public abstract class Result<T> {

    /* loaded from: classes12.dex */
    public static final class Error<T> extends Result<T> {
        private final Exception exception;

        public Error(Exception exc) {
            super();
            this.exception = exc;
        }

        public Exception getException() {
            return this.exception;
        }
    }

    /* loaded from: classes12.dex */
    public static final class Success<T> extends Result<T> {
        private final T data;

        public Success(T t) {
            super();
            this.data = t;
        }

        public T getData() {
            return this.data;
        }
    }

    private Result() {
    }
}
