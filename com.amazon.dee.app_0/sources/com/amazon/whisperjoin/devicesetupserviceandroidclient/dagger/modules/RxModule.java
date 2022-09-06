package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Named;
@Module
/* loaded from: classes13.dex */
public class RxModule {
    public static final String BACKGROUND_SCHEDULER = "background-scheduler";
    public static final String MAIN_THREAD_SCHEDULER = "main-scheduler";

    @Provides
    @Named(BACKGROUND_SCHEDULER)
    public Scheduler providesBackgroundWorkerScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named(MAIN_THREAD_SCHEDULER)
    public Scheduler providesMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
