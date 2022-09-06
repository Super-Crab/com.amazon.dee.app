package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.google.common.base.Preconditions;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        Preconditions.checkNotNull(context, "Context cannot be null");
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Context providesContext() {
        return this.context;
    }
}
