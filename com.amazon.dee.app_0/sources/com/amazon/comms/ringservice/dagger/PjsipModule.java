package com.amazon.comms.ringservice.dagger;

import com.amazon.comms.ringservice.pjsip.PjsipLogWriter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.pjsip.pjsua2.LogWriter;
@Module
/* loaded from: classes12.dex */
public class PjsipModule {
    @Provides
    @Singleton
    public static LogWriter provideLogWriter() {
        return new PjsipLogWriter();
    }
}
