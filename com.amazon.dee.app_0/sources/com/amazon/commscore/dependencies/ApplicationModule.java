package com.amazon.commscore.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.commscore.dependencies.annotation.ApplicationContext;
import com.amazon.commscore.dependencies.annotation.BuildType;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class ApplicationModule {
    private Context context;

    public ApplicationModule(@NonNull Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationContext
    public Context providesApplicationContext() {
        return this.context;
    }

    @Provides
    @BuildType
    public String providesBuildType() {
        return "release";
    }
}
