package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.internal.util.ObservableStream;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.streams.control.ControlMessage;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.io.IOException;
/* loaded from: classes.dex */
public final class MappedResponseAction<T> implements ActionQueue.Action {
    private volatile boolean aborted;
    private final ControlMessage controlMessage;
    private final ControlStream controlStream;
    private volatile Disposable disposable;
    private final Object lock;
    private final Mapper<T> mapper;
    private final Producer.Result<T> result;

    /* loaded from: classes.dex */
    public interface Mapper<T> {
        T map(Accessories.Response response) throws IOException;
    }

    public MappedResponseAction(ControlMessage controlMessage, ControlStream controlStream, Producer.Result<T> result, Mapper<T> mapper) {
        Preconditions.notNull(controlMessage, "controlMessage");
        Preconditions.notNull(controlStream, "controlStream");
        Preconditions.notNull(mapper, "mapper");
        Preconditions.notNull(result, "result");
        this.controlMessage = controlMessage;
        this.controlStream = controlStream;
        this.result = result;
        this.mapper = mapper;
        this.lock = new Object();
    }

    private boolean actionHasStarted() {
        boolean z;
        synchronized (this.lock) {
            z = this.disposable != null;
        }
        return z;
    }

    @Override // com.amazon.alexa.accessory.internal.ActionQueue.Action
    public void abort() {
        synchronized (this.lock) {
            this.aborted = true;
            if (actionHasStarted()) {
                ObservableUtils.dispose(this.disposable);
            } else {
                this.result.completeWithError(new IOException("Action was aborted"));
            }
        }
    }

    public /* synthetic */ void lambda$run$0$MappedResponseAction(ActionQueue.Action.Callback callback) throws Throwable {
        this.result.completeWithError(new IOException("Action was disposed"));
        callback.onFinished();
    }

    @Override // com.amazon.alexa.accessory.internal.ActionQueue.Action
    public void run(final ActionQueue.Action.Callback callback) {
        Preconditions.notNull(callback, "callback");
        synchronized (this.lock) {
            if (!actionHasStarted()) {
                if (!this.aborted) {
                    Single<Accessories.Response> dispatchSingleSuccessOnErrorResponse = ObservableStream.dispatchSingleSuccessOnErrorResponse(this.controlStream, this.controlMessage);
                    final Mapper<T> mapper = this.mapper;
                    mapper.getClass();
                    Single doOnDispose = dispatchSingleSuccessOnErrorResponse.map(new Function() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$c3AVEFdL6ZoEdOEzL5rtlLAgCSk
                        @Override // io.reactivex.rxjava3.functions.Function
                        /* renamed from: apply */
                        public final Object mo10358apply(Object obj) {
                            return MappedResponseAction.Mapper.this.map((Accessories.Response) obj);
                        }
                    }).doOnDispose(new Action() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$MappedResponseAction$KtWFnQ6azzZg38S7_kPXJoPbaWQ
                        @Override // io.reactivex.rxjava3.functions.Action
                        public final void run() {
                            MappedResponseAction.this.lambda$run$0$MappedResponseAction(callback);
                        }
                    });
                    callback.getClass();
                    Single<T> doAfterTerminate = doOnDispose.doAfterTerminate(new Action() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$y8U6MRVHjvLpV31FuM4tDXiIzy8
                        @Override // io.reactivex.rxjava3.functions.Action
                        public final void run() {
                            ActionQueue.Action.Callback.this.onFinished();
                        }
                    });
                    final Producer.Result<T> result = this.result;
                    result.getClass();
                    Consumer<? super T> consumer = new Consumer() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$XgsSpnUV1wNP9ySdzfRzhtE9kW8
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        public final void accept(Object obj) {
                            Producer.Result.this.complete(obj);
                        }
                    };
                    final Producer.Result<T> result2 = this.result;
                    result2.getClass();
                    this.disposable = doAfterTerminate.subscribe(consumer, new Consumer() { // from class: com.amazon.alexa.accessory.internal.-$$Lambda$25dTK8BRpWxdQZXVVmIUFgH5OIU
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        public final void accept(Object obj) {
                            Producer.Result.this.completeWithError((Throwable) obj);
                        }
                    });
                } else {
                    throw new IllegalStateException("Action cannot be run since it was aborted");
                }
            } else {
                throw new IllegalStateException("Action was already started!");
            }
        }
    }
}
