package com.amazon.alexa.biloba.generated.network;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/* loaded from: classes6.dex */
public class AppSchedulerProvider implements SchedulerProvider {
    @Override // com.amazon.alexa.biloba.generated.network.SchedulerProvider
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override // com.amazon.alexa.biloba.generated.network.SchedulerProvider
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
