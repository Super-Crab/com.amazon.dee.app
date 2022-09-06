package com.amazon.alexa;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
/* compiled from: VolumeUpdateScheduler.java */
@Singleton
/* loaded from: classes.dex */
public class EuG {
    public final ScheduledExecutorService BIo;
    public ScheduledFuture<?> zQM;
    public final Provider<Kfo> zZm;

    @Inject
    public EuG(Provider<Kfo> provider, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService) {
        this.zZm = provider;
        this.BIo = scheduledExecutorService;
    }
}
