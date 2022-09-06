package com.amazon.deecomms.common.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import rx.Observable;
/* loaded from: classes12.dex */
public abstract class Processor<S, T> {
    private List<Task<S, T>> mTaskList = new ArrayList();

    public Processor add(Task<S, T> task) {
        this.mTaskList.add(task);
        return this;
    }

    public Observable<S> deferredExecute(final T t) {
        return Observable.fromCallable(new Callable<S>() { // from class: com.amazon.deecomms.common.processor.Processor.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public S call() {
                return (S) Processor.this.execute(t);
            }
        });
    }

    public S execute(T t) {
        S s = null;
        for (Task<S, T> task : this.mTaskList) {
            s = task.execute(t);
            if (terminateEarly(s)) {
                break;
            }
        }
        return s;
    }

    protected abstract boolean terminateEarly(S s);
}
