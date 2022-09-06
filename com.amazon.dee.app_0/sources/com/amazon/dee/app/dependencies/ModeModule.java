package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.service.ModeServiceFactory;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class ModeModule {
    @Provides
    @ApplicationScope
    public ModeService provideModeService(Context context) {
        return ModeServiceFactory.create(context);
    }
}
