package com.amazon.alexa.enrollment.ui;

import android.view.View;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.concurrent.TimeUnit;
/* loaded from: classes7.dex */
public abstract class DebounceOnClickListener implements View.OnClickListener {
    private static final long THROTTLE_WINDOW = 1000;
    private final PublishSubject<View> publishSubject = PublishSubject.create();
    private final Disposable disposable = this.publishSubject.throttleFirst(1000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.enrollment.ui.-$$Lambda$DebounceOnClickListener$cXnT5JUjMoXEp01sYuubRtqbnaE
        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Object obj) {
            DebounceOnClickListener.this.lambda$new$0$DebounceOnClickListener((View) obj);
        }
    });

    public void cleanUp() {
        this.disposable.dispose();
    }

    /* renamed from: debounceClick */
    public abstract void lambda$new$0$DebounceOnClickListener(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.publishSubject.onNext(view);
    }
}
