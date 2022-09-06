package com.amazon.alexa.enrollment.unified.speakerid.provider;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
/* loaded from: classes7.dex */
public class SchedulerProvider {
    public Scheduler ioThread() {
        return Schedulers.io();
    }

    public Scheduler uiThread() {
        return AndroidSchedulers.mainThread();
    }
}
