package com.amazon.alexa;

import com.amazon.alexa.utils.TimeProvider;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/* compiled from: UnlockActivityModule.java */
@Module
/* loaded from: classes.dex */
public class Mit {
    @Provides
    @Singleton
    public TimeProvider zZm() {
        return new TimeProvider();
    }
}
