package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvideAWSCredentialsFactory.java */
/* loaded from: classes.dex */
public final class oeH implements Factory<AWSCredentialsProvider> {
    public final Provider<Context> BIo;
    public final Provider<ClientConfiguration> zQM;
    public final kbj zZm;

    public oeH(kbj kbjVar, Provider<Context> provider, Provider<ClientConfiguration> provider2) {
        this.zZm = kbjVar;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (AWSCredentialsProvider) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
