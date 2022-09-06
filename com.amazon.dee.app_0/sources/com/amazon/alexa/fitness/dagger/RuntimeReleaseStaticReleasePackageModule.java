package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.fitness.dagger.StaticReleasePackageModule;
import com.amazon.alexa.fitness.logs.AndroidLog;
import com.amazon.alexa.fitness.logs.ILog;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Module(includes = {StaticReleasePackageModule.ModuleBinds.class})
/* loaded from: classes8.dex */
public class RuntimeReleaseStaticReleasePackageModule extends StaticReleasePackageModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public RuntimeReleaseStaticReleasePackageModule(@NonNull Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public ILog provideILog() {
        return new AndroidLog.Release();
    }
}
