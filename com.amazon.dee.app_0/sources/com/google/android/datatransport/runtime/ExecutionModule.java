package com.google.android.datatransport.runtime;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Singleton;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@Module
/* loaded from: classes2.dex */
abstract class ExecutionModule {
    ExecutionModule() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static Executor executor() {
        return Executors.newSingleThreadExecutor();
    }
}
