package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: AudioFormatConvertingAttachmentModule_ProvideAudioFormatConvertingAttachmentFactoryFactory.java */
/* loaded from: classes.dex */
public final class OfG implements Factory<Vyl> {
    public final Provider<C0228ryy> BIo;
    public final Swg zZm;

    public OfG(Swg swg, Provider<C0228ryy> provider) {
        this.zZm = swg;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Vyl) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
