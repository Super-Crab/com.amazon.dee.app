package com.amazon.alexa;

import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.google.gson.Gson;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: PersistedDataLoaderFactory.java */
@Singleton
/* loaded from: classes.dex */
public class UCV {
    public final TimeProvider BIo;
    public final Gson zQM;
    public final Lazy<PersistentStorage> zZm;

    @Inject
    public UCV(@Named("capability_agents") Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Gson gson) {
        this.zZm = lazy;
        this.BIo = timeProvider;
        this.zQM = gson;
    }

    public uTP zZm(String str, long j) {
        Lazy<PersistentStorage> lazy = this.zZm;
        TimeProvider timeProvider = this.BIo;
        Gson gson = this.zQM;
        return new uTP(str, j, true, lazy, ManagedExecutorFactory.newSingleThreadCachedThreadPool(str + "_data_loader"), timeProvider, gson);
    }
}
