package com.amazon.alexa.drive.dependency;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes7.dex */
public class AndroidModule {
    Context context;

    public AndroidModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideAndroidContext() {
        return this.context;
    }
}
