package com.amazon.dee.app.dependencies;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class ExternalUIModule {
    Activity activity;

    public ExternalUIModule(Activity activity) {
        this.activity = activity;
    }

    @ExternalUIScope
    @Provides
    public Activity provideActivity() {
        return this.activity;
    }
}
