package io.reactivex.rxjava3.android.schedulers;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.Callable;
/* compiled from: lambda */
/* renamed from: io.reactivex.rxjava3.android.schedulers.-$$Lambda$AndroidSchedulers$2crO82bOkOgbwToxlZgj1sEtdZQ  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$AndroidSchedulers$2crO82bOkOgbwToxlZgj1sEtdZQ implements Callable {
    public static final /* synthetic */ $$Lambda$AndroidSchedulers$2crO82bOkOgbwToxlZgj1sEtdZQ INSTANCE = new $$Lambda$AndroidSchedulers$2crO82bOkOgbwToxlZgj1sEtdZQ();

    private /* synthetic */ $$Lambda$AndroidSchedulers$2crO82bOkOgbwToxlZgj1sEtdZQ() {
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        Scheduler scheduler;
        scheduler = AndroidSchedulers.MainHolder.DEFAULT;
        return scheduler;
    }
}
