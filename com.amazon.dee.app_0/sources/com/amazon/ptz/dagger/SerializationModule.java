package com.amazon.ptz.dagger;

import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes13.dex */
public class SerializationModule {
    @Provides
    @Singleton
    public RcSerializer provideRcSerializer() {
        return RcSerializer.INSTANCE;
    }
}
