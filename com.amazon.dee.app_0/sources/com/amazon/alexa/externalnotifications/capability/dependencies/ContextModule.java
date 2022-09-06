package com.amazon.alexa.externalnotifications.capability.dependencies;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes7.dex */
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context providesContext() {
        return this.context;
    }
}
