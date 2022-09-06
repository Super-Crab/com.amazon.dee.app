package com.amazon.alexa.handsfree.uservoicerecognition.ui;

import android.view.View;
import androidx.annotation.VisibleForTesting;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.concurrent.TimeUnit;
/* loaded from: classes8.dex */
public abstract class DebounceOnClickListener implements View.OnClickListener {
    private static final long THROTTLE_WINDOW = 1000;
    private final Disposable disposable;
    private final PublishSubject<View> publishSubject;

    public DebounceOnClickListener() {
        this(PublishSubject.create(), AndroidSchedulers.mainThread());
    }

    public void cleanUp() {
        this.disposable.dispose();
    }

    public abstract void debounceClick(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.publishSubject.onNext(view);
    }

    @VisibleForTesting
    DebounceOnClickListener(Scheduler scheduler) {
        this(PublishSubject.create(), scheduler);
    }

    @VisibleForTesting
    DebounceOnClickListener(PublishSubject<View> publishSubject, Scheduler scheduler) {
        this.publishSubject = publishSubject;
        this.disposable = this.publishSubject.throttleFirst(1000L, TimeUnit.MILLISECONDS).observeOn(scheduler).subscribe(new Consumer() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.-$$Lambda$uFCEA5_O3e-x57sEruJ-BBWqA8c
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DebounceOnClickListener.this.debounceClick((View) obj);
            }
        });
    }
}
